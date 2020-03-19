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
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@Path("/procesoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ProcesoRestController - REST CRUD")
public class ProcesoRestController extends BaseRestController implements CrudRestControllerInterface<TbQoProceso, GenericWrapper<TbQoProceso>> {

	
	@Inject
	QuskiOroService sas;
		
	public ProcesoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//Unused
	}

	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoProceso> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbQoProceso", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoProceso> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbQoProceso> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoProceso> plw = new PaginatedListWrapper<>(pw);
		List<TbQoProceso> actions = this.sas.findAllProceso(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countProceso().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@Override
	public GenericWrapper<TbQoProceso> persistEntity(GenericWrapper<TbQoProceso> arg0) throws RelativeException {
		// Unused
		return null;
	}

	@Override
	public GenericWrapper<TbQoProceso> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

