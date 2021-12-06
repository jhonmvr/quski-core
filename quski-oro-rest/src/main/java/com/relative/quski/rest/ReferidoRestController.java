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
import com.relative.quski.model.TbQoPublicidad;
import com.relative.quski.model.TbQoReferido;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/referidoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "ReferidoRestController - REST CRUD")
public class ReferidoRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoReferido, GenericWrapper<TbQoReferido>>{
	public ReferidoRestController() throws RelativeException {
		super();
	}

	@Inject
	QuskiOroService qos;
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Standar de metodo vacio;
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna wrapper de entidades encontradas en TbQoPublicidad", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoReferido> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoReferido> loc = new GenericWrapper<>();
		TbQoReferido a = this.qos.findReferidoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoPublicidad>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoDetalleCredito", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoReferido> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<TbQoReferido> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoReferido> plw = new PaginatedListWrapper<>(pw);
		List<TbQoReferido> actions = this.qos.findAllReferido(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countReferido().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoReferido>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoDetalleCredito", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoReferido> persistEntity(GenericWrapper<TbQoReferido> wp) throws RelativeException {
		GenericWrapper<TbQoReferido> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageReferido(wp.getEntidad()));
		return loc;
	}	
	@POST
	@Path("/persistEntities")
	@ApiOperation(value = "GenericWrapper<TbQoReferido>", notes = "Metodo Post persistEntities Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoDetalleCredito", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoReferido> persistEntities(GenericWrapper<TbQoReferido> wp) throws RelativeException {
		GenericWrapper<TbQoReferido> loc = new GenericWrapper<>();
		loc.setEntidades( this.qos.manageReferidos( wp.getEntidades() ) );
		return loc;
	}	
}
