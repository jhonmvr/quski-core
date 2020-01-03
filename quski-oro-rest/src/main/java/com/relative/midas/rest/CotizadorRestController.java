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
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.service.MidasOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/cotizadorRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "CotizadorRestController - REST CRUD")
public class CotizadorRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoCotizador, GenericWrapper<TbQoCotizador>> {

	public CotizadorRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Inject
	MidasOroService mis;
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoCotizador> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoCotizador> loc = new GenericWrapper<>();
		TbQoCotizador a = this.mis.findCotizadorById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCotizador>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoCotizador", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoCotizador> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoCotizador> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoCotizador> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCotizador> actions = this.mis.findAllCotizador(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.mis.countCotizador().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoCotizador> persistEntity(GenericWrapper<TbQoCotizador> wp) throws RelativeException {
		GenericWrapper<TbQoCotizador> loc = new GenericWrapper<>();
		loc.setEntidad(this.mis.manageCotizador(wp.getEntidad()));
		return loc;
	}	
}
