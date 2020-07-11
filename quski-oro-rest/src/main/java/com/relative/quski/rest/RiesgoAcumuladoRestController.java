package com.relative.quski.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import com.relative.quski.wrapper.RiesgoAcumuladoWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/riesgoAcumuladoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "RiesgoAcumuladoRestController - REST CRUD")

public class RiesgoAcumuladoRestController extends BaseRestController
implements CrudRestControllerInterface<RiesgoAcumuladoWrapper, GenericWrapper<RiesgoAcumuladoWrapper>> {

	public RiesgoAcumuladoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
	}

	@Override
	public GenericWrapper<RiesgoAcumuladoWrapper> getEntity(String arg0) throws RelativeException {
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<RiesgoAcumuladoWrapper>", notes = "Metodo Get listAllEntities Retorna un mock de riesgo acumulado ", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<RiesgoAcumuladoWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<RiesgoAcumuladoWrapper> findAll(PaginatedWrapper pw) {
			return null;
	}

	@Override
	public GenericWrapper<RiesgoAcumuladoWrapper> persistEntity(GenericWrapper<RiesgoAcumuladoWrapper> arg0)
			throws RelativeException {
		return null;
	}

	
	

}

