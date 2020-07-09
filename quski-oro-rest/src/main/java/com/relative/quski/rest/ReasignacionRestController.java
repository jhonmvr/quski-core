package com.relative.quski.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoReasignacionActividad;
import com.relative.quski.service.ProcesoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/reasignacionesRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Reasignación Bajo Demanda - REST CRUD")
public class ReasignacionRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoReasignacionActividad, GenericWrapper<TbQoReasignacionActividad>> {
	@Inject
	ProcesoService procesoService;
	@Inject
	Logger log;

	public ReasignacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoReasignacionActividad>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoReasignacionActividad", response = GenericWrapper.class)
	public GenericWrapper<TbQoReasignacionActividad> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoReasignacionActividad> loc = new GenericWrapper<>();
		TbQoReasignacionActividad a = this.procesoService.findParametroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoReasignacionActividad> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoReasignacionActividad>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoReasignacionActividad", response = GenericWrapper.class)
	public GenericWrapper<TbQoReasignacionActividad> persistEntity(GenericWrapper<TbQoReasignacionActividad> wp)
			throws RelativeException {
		GenericWrapper<TbQoReasignacionActividad> loc = new GenericWrapper<>();
		loc.setEntidad(this.procesoService.manageReasignacion(wp.getEntidad()));
		return loc;
	}

}
