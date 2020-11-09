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
import com.relative.quski.model.TbQoRubro;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/rubroRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "rubroRestController - REST CRUD")
public class RubroRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoRubro, GenericWrapper<TbQoRubro>> {

	public RubroRestController() throws RelativeException {
		super();
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
	public GenericWrapper<TbQoRubro> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoRubro> loc = new GenericWrapper<>();
		TbQoRubro a = this.qos.findRubroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	public PaginatedListWrapper<TbQoRubro> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoRubro> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoRubro> plw = new PaginatedListWrapper<>(pw);
		List<TbQoRubro> actions = this.qos.findAllRubros(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCotizador().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoRubro> persistEntity(GenericWrapper<TbQoRubro> wp) throws RelativeException {
		GenericWrapper<TbQoRubro> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageRubro(wp.getEntidad()));
		return loc;
	}
}
