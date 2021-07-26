package com.relative.quski.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import com.relative.quski.wrapper.OpcionWrapper;
import com.relative.quski.wrapper.SimularResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@Path("/simularOfertaCotizacion")
	@ApiOperation(value = "idCotizador", notes = "Metodo Get que simula las ofertas Cotizacion  y retorna la entidad SimularResponse", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SimularResponse> simularOfertaCotizacion(@QueryParam("idCotizador") String idCotizador) throws RelativeException {
		GenericWrapper<SimularResponse> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCotizador)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL COTIZADOR");
		}
		
		SimularResponse a = this.qos.simularOfertasCalculadoraCotizacion(Long.valueOf(idCotizador));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/simularOferta")
	@ApiOperation(value = "idCredito", notes = "Metodo Get que simula las ofertas y retorna la entidad SimularResponse", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SimularResponse> simularOferta(@QueryParam("idCredito") String idCredito,
			@QueryParam("montoSolicitado") String montoSolicitado,
			@QueryParam("riesgoTotal") String riesgoTotal,
			@QueryParam("codigoAgencia") String codigoAgencia) throws RelativeException {
		GenericWrapper<SimularResponse> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCredito)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL CREDITO");
		}
		SimularResponse a = this.qos.simularOfertasCalculadora(Long.valueOf(idCredito), 
				StringUtils.isNotBlank(montoSolicitado) ? BigDecimal.valueOf(Double.valueOf(montoSolicitado)) : BigDecimal.ZERO,
				StringUtils.isNotBlank(riesgoTotal) ? BigDecimal.valueOf(Double.valueOf(riesgoTotal)) : BigDecimal.ZERO, 
						codigoAgencia);
		loc.setEntidad(a);
		return loc;
	}
	
	@POST
	@Path("/simularOfertaRenovacion")
	@ApiOperation(value = "GenericWrapper<DetalleCreditoWrapper>", notes = "Metodo Get que simula las ofertas de Renovacion  y retorna la entidad SimularResponse", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SimularResponse> simularOfertaRenovacion(DetalleCreditoWrapper credito,
			@QueryParam("riesgoTotal") String riesgoTotal,
			@QueryParam("coberturaExcepcionada") String coberturaExcepcionada,
			@QueryParam("codigoAgencia") String codigoAgencia,
			@QueryParam("montoSolicitado") String montoSolicitado) throws RelativeException {
		GenericWrapper<SimularResponse> loc = new GenericWrapper<>();
		if(credito == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL CREDITO");
		}
		SimularResponse a = this.qos.simularOfertasCalculadoraRenovacion(credito, StringUtils.isNotBlank(riesgoTotal) ? BigDecimal.valueOf(Double.valueOf(riesgoTotal)):BigDecimal.ZERO, 
				codigoAgencia,coberturaExcepcionada, StringUtils.isNotBlank(montoSolicitado) ? BigDecimal.valueOf(Double.valueOf(montoSolicitado)):BigDecimal.ZERO);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/simularOfertaRenovacionExcepcion")
	@ApiOperation(value = "idCredito,  cobertura", notes = "Metodo Get que simula las ofertas de Renovacion con Excepcion  y retorna la entidad SimularResponse", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SimularResponse> simularOfertaRenovacionExcepcion(@QueryParam("autorizacion") String autorizacion,
			@QueryParam("idCredito") String idCredito,@QueryParam("cobertura") String cobertura) throws RelativeException {
		GenericWrapper<SimularResponse> loc = new GenericWrapper<>();
		SimularResponse a = this.qos.simularOfertaRenovacionExcepcion( Long.valueOf( idCredito ), cobertura , autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/simularOfertaExcepcionada")
	@ApiOperation(value = "idCredito,  cobertura, idAgencia", notes = "Metodo Get que simula las ofertas excepcionadas  y retorna la entidad SimularResponse", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<OpcionWrapper> simularOfertaExcepcionada(@QueryParam("codigoAgencia") String codigoAgencia,
			@QueryParam("idCredito") String idCredito, @QueryParam("cobertura") String cobertura, @QueryParam("idAgencia") String idAgencia) throws NumberFormatException, Exception {
		GenericWrapper<OpcionWrapper> loc = new GenericWrapper<>();
		List<OpcionWrapper> as = this.qos.simularOfertaExcepcionada(Long.valueOf( idCredito ), Long.valueOf( cobertura ), Long.valueOf( idAgencia ), codigoAgencia);
		loc.setEntidades( as );
		return loc;
	}	
	@GET
	@Path("/simularOfertaExcepcionadaRenovacion")
	@ApiOperation(value =  "codigoAgencia, idCredito,  cobertura", notes = "Metodo Get que simula las ofertas excepcionadas  de renovacion  y retorna la entidad SimularResponse", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<OpcionWrapper> simularOfertaExcepcionadaRenovacion(@QueryParam("autorizacion") String autorizacion,
			@QueryParam("codigoAgencia") String codigoAgencia,
			@QueryParam("idCredito") String idCredito, @QueryParam("cobertura") String cobertura) throws NumberFormatException, Exception {
		GenericWrapper<OpcionWrapper> loc = new GenericWrapper<>();
		List<OpcionWrapper> as = this.qos.simularOfertaExcepcionadaRenovacion(Long.valueOf( idCredito ), cobertura,codigoAgencia , autorizacion);
		loc.setEntidades( as );
		return loc;
	}
}
