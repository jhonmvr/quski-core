package com.relative.midas.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoCalculoNegociacion;
import com.relative.quski.service.QuskiOroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/calculoNegociacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "calculoNegociacionRestController - REST CRUD")
public class CalculoNegociacionRestController extends BaseRestController implements CrudRestControllerInterface<TbQoCalculoNegociacion, GenericWrapper<TbQoCalculoNegociacion>>{
	
	@Inject
	QuskiOroService qos;
	public CalculoNegociacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericWrapper<TbQoCalculoNegociacion> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<TbQoCalculoNegociacion> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCalculoNegociacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCalculoNegociacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoCalculoNegociacion> persistEntity(GenericWrapper<TbQoCalculoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoCalculoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageCalculoNegociacion(wp.getEntidad()));
		return loc;
	}

}
