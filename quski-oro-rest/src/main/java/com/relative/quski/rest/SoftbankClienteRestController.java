package com.relative.quski.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.SoftbankClienteWrapper;

@Path("/softbankClienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SoftbankClienteRestController extends BaseRestController
		implements CrudRestControllerInterface<SoftbankClienteWrapper, GenericWrapper<SoftbankClienteWrapper>> {

	public SoftbankClienteRestController() throws RelativeException {
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
	public GenericWrapper<SoftbankClienteWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PaginatedListWrapper<SoftbankClienteWrapper> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GenericWrapper<SoftbankClienteWrapper> persistEntity(GenericWrapper<SoftbankClienteWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Path("/getClienteSoftbank")
	public GenericWrapper<SoftbankClienteWrapper> getClienteSoftbank(@QueryParam("cedula") String cedula) throws RelativeException {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
		SoftbankClienteWrapper a = this.qos.findClienteSoftbank(cedula);
		loc.setEntidad(a);
		return loc;
	}
}
