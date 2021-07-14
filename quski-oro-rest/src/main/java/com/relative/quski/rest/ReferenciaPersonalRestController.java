package com.relative.quski.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.service.QuskiOroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/referenciaPersonalRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "referenciaPersonalRestController - REST CRUD")
public class ReferenciaPersonalRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoReferenciaPersonal, GenericWrapper<TbQoReferenciaPersonal>>{


	public ReferenciaPersonalRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoReferenciaPersonal>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoReferenciaPersonal", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoReferenciaPersonal> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoReferenciaPersonal> loc = new GenericWrapper<>();
		TbQoReferenciaPersonal a = this.qos.findReferenciaPersonalById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoReferenciaPersonal>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoReferenciaPersonal", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoReferenciaPersonal> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<TbQoReferenciaPersonal> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoReferenciaPersonal> plw = new PaginatedListWrapper<>(pw);
		List<TbQoReferenciaPersonal> actions = this.qos.findAllReferenciaPersonal(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCliente().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoReferenciaPersonal>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoReferenciaPersonal", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoReferenciaPersonal> persistEntity(GenericWrapper<TbQoReferenciaPersonal> wp) throws RelativeException {
		GenericWrapper<TbQoReferenciaPersonal> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageReferenciaPersonal(wp.getEntidad()));
		return loc;
	}	

	
}

