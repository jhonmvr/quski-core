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
import com.relative.quski.model.Provincia;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/provinciaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ProvinciaRestController - REST CRUD")
public class ProvinciaRestController extends BaseRestController implements CrudRestControllerInterface<Provincia, GenericWrapper<Provincia>> {


	@Inject
	QuskiOroService qos;
	
	public ProvinciaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Provincia> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en Provincia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Provincia> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<Provincia> loc = new GenericWrapper<>();
		Provincia a =this.qos.findProvinciaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Provincia> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en Provincia", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<Provincia> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("provinciaid") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Provincia> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<Provincia> plw = new PaginatedListWrapper<>(pw);
		List<Provincia> actions = this.qos.findAllProvincia(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.qos.countProvincia().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Provincia> persistEntity(GenericWrapper<Provincia> arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}