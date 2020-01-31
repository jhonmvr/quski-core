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

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.Parroquia;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/parroquiaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Parroquia - REST CRUD")
public class ParroquiaRestController extends BaseRestController implements CrudRestControllerInterface<Parroquia, GenericWrapper<Parroquia>> {

	@Inject
	QuskiOroService qos;
	
	public ParroquiaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	 	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<Parroquia>", notes = "Metodo Get Retorna wrapper de entidades encontradas en Parroquia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Parroquia> getEntity(
			@QueryParam("provinciaId") String  provinciaId,
			@QueryParam("cantonId") String cantonId, @QueryParam("id") String id)
					throws RelativeException {
		GenericWrapper<Parroquia> loc = new GenericWrapper<>();
		Parroquia a = this.qos.findParroquiaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	public GenericWrapper<Parroquia> getEntity( String  id) throws RelativeException {
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<Parroquia>", notes = "Metodo Get listAllEntities Retorna wrapper de entidades encontradas en Parroquia", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<Parroquia> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<Parroquia> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<Parroquia> plw = new PaginatedListWrapper<>(pw);
		List<Parroquia> actions = this.qos.findAllParroquia(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.qos.countParroquia().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<Parroquia> persistEntity(GenericWrapper<Parroquia> arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@GET
	@Path("/listAllParroquiaByCantonProvincia")
	@ApiOperation(value = "PaginatedListWrapper<Parroquia>", notes = "Metodo Get listAllEntitiesByProvincia Retorna wrapper de entidades encontradas en Canton", 
	response = Parroquia.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Parroquia> listAllParroquiaByCantonProvincia(
			@QueryParam("nombre") String nombre) throws RelativeException {
		GenericWrapper<Parroquia> parroquias = new GenericWrapper<Parroquia>() ;
		parroquias.setEntidades(qos.finAllUbicacion(StringUtils.upperCase(nombre)));
		return parroquias;
	}


}