package com.relative.quski.rest;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.service.QuskiOroService;
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
	@GET
	@Path("/simularOferta")
	public GenericWrapper<SimularResponse> simularOferta(@QueryParam("idCredito") String idCredito,
			@QueryParam("montoSolicitado") String montoSolicitado,
			@QueryParam("riesgoTotal") String riesgoTotal,
			@QueryParam("codigoAgencia") String codigoAgencia) throws RelativeException {
		GenericWrapper<SimularResponse> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCredito)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL CREDITO");
		}
		SimularResponse a = this.qos.simularOfertasCalculadora(Long.valueOf(idCredito), StringUtils.isNotBlank(montoSolicitado)?
				BigDecimal.valueOf(Double.valueOf(montoSolicitado)):BigDecimal.ZERO,riesgoTotal, codigoAgencia);
		loc.setEntidad(a);
		return loc;
	}
	
	
	
}
