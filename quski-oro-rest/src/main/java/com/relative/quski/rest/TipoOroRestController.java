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
import com.relative.quski.model.TbQoTipoOro;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/tipoOroRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "TipoOroRestController - REST CRUD")
public class TipoOroRestController extends BaseRestController
implements CrudRestControllerInterface<TbQoTipoOro, GenericWrapper<TbQoTipoOro>> {


	public TipoOroRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbQoTipoOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoTipoOro> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTipoOro> loc = new GenericWrapper<>();
		TbQoTipoOro a = this.qos.findTipoOroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoTipoOro>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoTipoOro", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoTipoOro> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoTipoOro> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTipoOro> plw = new PaginatedListWrapper<>(pw);
		List<TbQoTipoOro> actions = this.qos.findAllTipoOro(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countTipoOro().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTipoOro>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoTipoOro> persistEntity(GenericWrapper<TbQoTipoOro> wp) throws RelativeException {
		GenericWrapper<TbQoTipoOro> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageTipoOro(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/tipoOroByQuilate")
	@ApiOperation(value = "GenericWrapper<TbQoTipoOro>", notes = "Metodo tipoOroByQuilate Retorna lista de entidades encontradas de TbQoTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoTipoOro> tipoOroByQuilate(@QueryParam("quilate") String quilate)
			throws RelativeException {
		GenericWrapper<TbQoTipoOro> loc = new GenericWrapper<>();
		TbQoTipoOro  a = this.findTipoOro(quilate);
		loc.setEntidad(a);
		return loc;
	}
	public TbQoTipoOro findTipoOro(String quilate) throws RelativeException {
		List<TbQoTipoOro> tmp = this.qos.findTipoOroByQuilate(quilate);
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		}
		return null;
	}
	
}
