/**
 * 
 */
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
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.ExcepcionRolWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
@Path("/excepcionRolRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "excepcionRolRestController - REST CRUD")
public class ExcepcionRolRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoExcepcionRol, GenericWrapper<TbQoExcepcionRol>> {

	public ExcepcionRolRestController() throws RelativeException {
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
	@ApiOperation(value = "GenericWrapper<TbQoExcepcionRol>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoExcepcionRol", response = GenericWrapper.class)
	public GenericWrapper<TbQoExcepcionRol> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoExcepcionRol> loc = new GenericWrapper<>();
		TbQoExcepcionRol a = this.qos.findById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcionRol>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionRol", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoExcepcionRol> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoExcepcionRol> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcionRol> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcionRol> actions = this.qos.findAllExcepcionRol(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionRol().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoExcepcionRol>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoExcepcionRol", response = GenericWrapper.class)

	public GenericWrapper<TbQoExcepcionRol> persistEntity(GenericWrapper<TbQoExcepcionRol> wp)
			throws RelativeException {
		GenericWrapper<TbQoExcepcionRol> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageExcepcionRol(wp.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/findByRolAndIdentificacion")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcione>", notes = "Metodo PaginatedListWrapper Retorna entidades encontradas en TbQoExcepcione por id de Negociacion", response = GenericWrapper.class)
	public PaginatedListWrapper<ExcepcionRolWrapper> findByRolAndIdentificacion( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("rol")  String rol, @QueryParam("identificacion")  String identificacion
			) throws RelativeException {
	
		
		return findByRolAndIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),rol,identificacion);
	}
	private PaginatedListWrapper<ExcepcionRolWrapper> findByRolAndIdentificacion(PaginatedWrapper pw, String rol,String identificacion) throws RelativeException {
		PaginatedListWrapper<ExcepcionRolWrapper> plw = new PaginatedListWrapper<>(pw);
		List<ExcepcionRolWrapper> actions = this.qos.findExcepcionByRolAndIdentificacion(pw, rol,identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionByRolAndIdentificacion( rol,identificacion ).intValue());
			plw.setList(actions);
		}
		return plw;
	}

}
