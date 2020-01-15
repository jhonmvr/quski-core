package com.relative.midas.rest;



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
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/tasacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "tasacionRestController - REST CRUD")
public class TasacionRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoTasacion, GenericWrapper<TbQoTasacion>> {

	public TasacionRestController() throws RelativeException {
		super();
	}


	@Inject
	QuskiOroService qos;
	@Override
	public void deleteEntity(String arg0) throws RelativeException {

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTasacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoTasacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoTasacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		//TbQoTasacion a = this.qos.findTasacionById(Long.valueOf(id));
		//loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoTasacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoTasacion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoTasacion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoTasacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTasacion> plw = new PaginatedListWrapper<>(pw);
		/*List<TbQoTasacion> actions = this.qos.findAllTasacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countNegociacion().intValue());
			plw.setList(actions);
		}*/
		return plw;
	}
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTasacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoTasacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoTasacion> persistEntity(GenericWrapper<TbQoTasacion> wp) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageTasacion(wp.getEntidad()));
		return loc;
	}	
}
