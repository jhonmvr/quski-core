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
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.service.QuskiOroService;
import io.swagger.annotations.Api;
@Path("/trackingRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Traking - REST CRUD")
public class TrackingRestController extends BaseRestController implements CrudRestControllerInterface<TbQoTracking, GenericWrapper<TbQoTracking>> {  

	public TrackingRestController() throws RelativeException {
		super();
		//  Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//  Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	public GenericWrapper<TbQoTracking> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTracking> loc = new GenericWrapper<>();
		TbQoTracking a = this.qos.findTrackingById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	public PaginatedListWrapper<TbQoTracking> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
	}
	private PaginatedListWrapper<TbQoTracking> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTracking> plw = new PaginatedListWrapper<>(pw);
		List<TbQoTracking> actions = this.qos.findAllTracking(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countTracking().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	public GenericWrapper<TbQoTracking> persistEntity(GenericWrapper<TbQoTracking> wp) throws RelativeException {
		GenericWrapper<TbQoTracking> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageTracking(wp.getEntidad()));
		return loc;
	}
	

}
