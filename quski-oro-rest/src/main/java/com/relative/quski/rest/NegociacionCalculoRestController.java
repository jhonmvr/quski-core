package com.relative.quski.rest;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoNegociacionCalculo;
import com.relative.quski.service.QuskiOroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/negociacionCalculoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "negociacionCalculoRestController - REST CRUD")

public class NegociacionCalculoRestController extends BaseRestController implements CrudRestControllerInterface<TbQoNegociacionCalculo, GenericWrapper<TbQoNegociacionCalculo>>{
	
	@Inject
	QuskiOroService qos;
	public NegociacionCalculoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoNegociacionCalculo>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCalculoNegociacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacionCalculo> persistEntity(GenericWrapper<TbQoNegociacionCalculo> wp) throws RelativeException {
		GenericWrapper<TbQoNegociacionCalculo> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageNegociacionCalculo(wp.getEntidad()));
		return loc;
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoNegociacionCalculo>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoNegociacionCalculo", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacionCalculo> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoNegociacionCalculo> loc =new GenericWrapper<>();
		TbQoNegociacionCalculo a =this.qos.findNegociacionCalculoById(Long.valueOf(id));
		loc.setEntidad(a);
		
		// TODO Auto-generated method stub
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoNegociacionCalculo> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}



}
