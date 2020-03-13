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
import com.relative.quski.model.TbQoAgencia;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@Path("/agenciaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " AgenciaRestController - REST CRUD")
public class AgenciaRestController extends BaseRestController implements CrudRestControllerInterface<TbQoAgencia, GenericWrapper<TbQoAgencia>> {

	
	@Inject
	QuskiOroService sas;
		
	public AgenciaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//Unused
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoAgencia> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoAgencia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoAgencia> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoAgencia> loc = new GenericWrapper<>();
		TbQoAgencia a =this.sas.findAgenciaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoAgencia> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbQoAgencia", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoAgencia> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbQoAgencia> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoAgencia> plw = new PaginatedListWrapper<>(pw);
		List<TbQoAgencia> actions = this.sas.findAllAgencia(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countAgencias().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@Override
	public GenericWrapper<TbQoAgencia> persistEntity(GenericWrapper<TbQoAgencia> arg0) throws RelativeException {
		// Unused
		return null;
	}
	
}

