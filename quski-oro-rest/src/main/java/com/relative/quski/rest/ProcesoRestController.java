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
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;


@Path("/procesoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcesoRestController extends BaseRestController implements CrudRestControllerInterface<TbQoProceso, GenericWrapper<TbQoProceso>> {

	@Inject
	QuskiOroService qos;
	
	public ProcesoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//NO UTILIZABLE
	}

	 	
	@GET
	@Path("/getEntity")
	public GenericWrapper<TbQoProceso> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		TbQoProceso inEg = this.qos.findProcesoById(Long.valueOf(id));
		loc.setEntidad(inEg);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	public GenericWrapper<TbQoProceso> persistEntity(GenericWrapper<TbQoProceso> wp) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.manageProceso( wp.getEntidad() ) );
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	public PaginatedListWrapper<TbQoProceso> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll( new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated) );

	}

	private PaginatedListWrapper<TbQoProceso> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoProceso> plw = new PaginatedListWrapper<>(pw);
		List<TbQoProceso> actions = this.qos.findAllProceso(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countProceso().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@POST
	@Path("/buscarOperaciones")
	public GenericWrapper<OperacionesWrapper> buscarOperaciones( BusquedaOperacionesWrapper wp) throws RelativeException {
		GenericWrapper<OperacionesWrapper> loc = new GenericWrapper<>();
		loc.setEntidades( this.qos.findOperaciones( wp ) );
		return loc;
	}
}