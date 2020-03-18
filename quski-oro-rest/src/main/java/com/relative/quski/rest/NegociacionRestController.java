package com.relative.quski.rest;



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
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/negociacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "NegociacionController - REST CRUD")
public class NegociacionRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoNegociacion, GenericWrapper<TbQoNegociacion>> {

	
	@Inject
	QuskiOroService qos;

	public NegociacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoVariableCrediticia> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoNegociacion", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoNegociacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		//TbQoNegociacion ng =this.qos.findNegociacionById(Long.valueOf(id));
		//loc.setEntidad(ng);
		return loc;
	}*/

	@Override
	public PaginatedListWrapper<TbQoNegociacion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		 return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
					sortDirections, isPaginated));
	}
	
	private PaginatedListWrapper<TbQoNegociacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoNegociacion> plw = new PaginatedListWrapper<>(pw);
		//List<TbQoNegociacion> actions = this.qos.findAllNegociacion(pw);
		// (actions != null && !actions.isEmpty()) {
			
			//plw.setTotalResults(this.qos.countNegociacion().intValue());
			//plw.setList(actions);
		//}
		return plw;
	}
	

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoNegociacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacion> persistEntity(GenericWrapper<TbQoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageNegociacion(wp.getEntidad()));
		return loc;
	}	
	
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoNegociacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoNegociacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		TbQoNegociacion a = this.qos.findNegociacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	

}
