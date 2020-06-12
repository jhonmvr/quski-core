package com.relative.quski.rest;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.BaseWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
import com.relative.integracion.calculadora.oferta.wrapper.SimularResponse;
import com.relative.quski.service.IntegracionServiceImp;

import io.swagger.annotations.ApiOperation;

@Path("/integracionRestController")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@ApiOperation(value = "IntegracionRestController", notes = "Metodo Get que retorna la informacion del cliente mediante SOAP")
public class IntegracionRestController extends BaseRestController {
	@Inject
	IntegracionServiceImp is;

	public IntegracionRestController() throws RelativeException {
	}

	@GET
	@Path("/getInformacionPersona")
	public BaseWrapper<Informacion> getInformacionPersona(
			@QueryParam("tipoIdentificacion") final String tipoIdentificacion,
			@QueryParam("identificacion") final String identificacion,
			@QueryParam("tipoConsulta") final String tipoConsulta,
			@QueryParam("calificacion") final String calificacion) throws RelativeException {
		final BaseWrapper<Informacion> info = (BaseWrapper<Informacion>) new BaseWrapper();
		info.setEntidad((Informacion) this.is.getClienteInformacion(tipoIdentificacion, identificacion, tipoConsulta,
				calificacion));
		return info;
	}

	@GET
	@Path("/getInformacionOferta")
	public BaseWrapper<SimularResponse> getInformacionPersonaOferta(
			@QueryParam("perfilRiesgo") final String perfilRiesgo,
			@QueryParam("origenOperacion") final String origenOperacion,
			@QueryParam("riesgoTotal") final String riesgoTotal,
			@QueryParam("fechaNacimiento") final String fechaNacimiento,
			@QueryParam("perfilPreferencia") final String perfilPreferencia,
			@QueryParam("agenciaOriginacion") final String agenciaOriginacion,
			@QueryParam("identificacionCliente") final String identificacionCliente,
			@QueryParam("calificacionMupi") final String calificacionMupi,
			@QueryParam("coberturaExcepcionada") final String coberturaExcepcionada,
			@QueryParam("tipoJoya") final String tipoJoya,
			@QueryParam("descripcion") final String descripcion,
			@QueryParam("estadoJoya") final String estadoJoya,
			@QueryParam("tipoOroKilataje") final String tipoOroKilataje,
			@QueryParam("pesoGr") final String pesoGr,
			@QueryParam("tienePiedras") final String tienePiedras,
			@QueryParam("detallePiedras") final String detallePiedras,
			@QueryParam("descuentoPesoPiedras") final String descuentoPesoPiedras,
			@QueryParam("pesoNeto") final String pesoNeto,
			@QueryParam("precioOro") final String precioOro,
			@QueryParam("valorAplicableCredito") final String valorAplicableCredito,
			@QueryParam("valorRealizacion") final String valorRealizacion,
			@QueryParam("numeroPiezas") final String numeroPiezas,
			@QueryParam("descuentoSuelda") final String descuentoSuelda) throws RelativeException {
		final BaseWrapper<SimularResponse> oferta = (BaseWrapper<SimularResponse>) new BaseWrapper();
		oferta.setEntidad((SimularResponse) this.is.getClienteInformacionOferta( perfilRiesgo,  origenOperacion,  riesgoTotal,
				 fechaNacimiento,  perfilPreferencia,  agenciaOriginacion,  identificacionCliente,
				 calificacionMupi,  coberturaExcepcionada,  tipoJoya,  descripcion,
				 estadoJoya,  tipoOroKilataje,  pesoGr,  tienePiedras,  detallePiedras,
				 descuentoPesoPiedras,  pesoNeto,  precioOro,  valorAplicableCredito,
				 valorRealizacion,  numeroPiezas,  descuentoSuelda));
		return oferta;
	}

}