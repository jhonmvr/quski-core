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
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/detalleCreditoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "DetalleCreditoRestController - REST CRUD")
public class DetalleCreditoRestControlles  extends BaseRestController
implements CrudRestControllerInterface<TbQoDetalleCredito, GenericWrapper<TbQoDetalleCredito>>{
	public DetalleCreditoRestControlles() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbQoDetalleCredito>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoDetalleCredito", response = GenericWrapper.class)
	public GenericWrapper<TbQoDetalleCredito> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoDetalleCredito> loc = new GenericWrapper<>();
		TbQoDetalleCredito a = this.qos.findDetalleCreditoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoDetalleCredito>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoDetalleCredito", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoDetalleCredito> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoDetalleCredito> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoDetalleCredito> plw = new PaginatedListWrapper<>(pw);
		List<TbQoDetalleCredito> actions = this.qos.findAllDetalleCredito(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countDetalleCredito().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoDetalleCredito>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoDetalleCredito", response = GenericWrapper.class)
	public GenericWrapper<TbQoDetalleCredito> persistEntity(GenericWrapper<TbQoDetalleCredito> wp) throws RelativeException {
		GenericWrapper<TbQoDetalleCredito> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageDetalleCredito(wp.getEntidad()));
		return loc;
	}	
}
