package com.relative.quski.rest;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.Canton;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/cantonRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Canton - REST CRUD")
public class CantonRestController extends BaseRestController implements CrudRestControllerInterface<Canton, GenericWrapper<Canton>> {

	@Inject
	QuskiOroService qos;
	
	public CantonRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	 	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Canton>", notes = "Metodo Get Retorna wrapper de entidades encontradas en Canton", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Canton> getEntity(
			@QueryParam("provinciaId") String  provinciaId,
			@QueryParam("cantonId") String id) throws RelativeException {
		GenericWrapper<Canton> loc = new GenericWrapper<>();
		Canton c = this.qos.findCantonById(Long.valueOf(id));
		loc.setEntidad(c);
		return loc;
	}
	
	
	@Override
	public GenericWrapper<Canton> getEntity( String  id) throws RelativeException {
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Canton>", notes = "Metodo Get listAllEntities Retorna wrapper de entidades encontradas en Canton", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<Canton> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Canton> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<Canton> plw = new PaginatedListWrapper<>(pw);
		List<Canton> actions = this.qos.findAllCanton(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.qos.countCanton().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Canton> persistEntity(GenericWrapper<Canton> arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@GET
	@Path("/listAllEntitiesByProvincia")
	@ApiOperation(value = "PaginatedListWrapper<Canton>", notes = "Metodo Get listAllEntitiesByProvincia Retorna wrapper de entidades encontradas en Canton", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<Canton> listAllEntitiesByProvincia(
			@QueryParam("provincia") @DefaultValue("01") String provincia,
			@QueryParam("order") @DefaultValue("asc") String order) throws RelativeException {
		PaginatedListWrapper<Canton> pw = new PaginatedListWrapper<>();
		pw.setList( this.qos.findCantonesByProvincia(provincia, order) );
		return pw;
	}
	
	

}