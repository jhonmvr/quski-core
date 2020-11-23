package com.relative.quski.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.CalculadoraEntradaWrapper;
import com.relative.quski.wrapper.CalculadoraRespuestaWrapper;
import com.relative.quski.wrapper.SimularResponse;


@Path("/calculadoraRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculadoraRestController extends BaseRestController
		implements CrudRestControllerInterface<CalculadoraRespuestaWrapper, GenericWrapper<CalculadoraRespuestaWrapper>> {

	public CalculadoraRestController() throws RelativeException {
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
	public GenericWrapper<CalculadoraRespuestaWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PaginatedListWrapper<CalculadoraRespuestaWrapper> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GenericWrapper<CalculadoraRespuestaWrapper> persistEntity(GenericWrapper<CalculadoraRespuestaWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@POST
	@Path("/simularOferta")
	public GenericWrapper<SimularResponse> simularOferta(GenericWrapper<CalculadoraEntradaWrapper> wrapper) throws RelativeException {
		GenericWrapper<SimularResponse> loc = new GenericWrapper<>();
		SimularResponse a = this.qos.simularOfertasCalculadora(wrapper.getEntidad());
		loc.setEntidad(a);
		return loc;
	}
	
	
	
}
