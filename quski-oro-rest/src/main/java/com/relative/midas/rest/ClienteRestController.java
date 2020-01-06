package com.relative.midas.rest;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.service.MidasOroService;

import io.swagger.annotations.Api;

@Path("/clienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clienteRestController - REST CRUD")
public class ClienteRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoCliente, GenericWrapper<TbQoCliente>>{


	public ClienteRestController() throws RelativeException {
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
	public GenericWrapper<TbQoCliente> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<TbQoCliente> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericWrapper<TbQoCliente> persistEntity(GenericWrapper<TbQoCliente> arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
