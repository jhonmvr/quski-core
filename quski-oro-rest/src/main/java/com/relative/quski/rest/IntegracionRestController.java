package com.relative.quski.rest;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.BaseWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.GenericWrapper;
//import com.relative.integracion.calculadora.cliente.consumer.ClienteCalculadoraOfertaGarantiaConsumer;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
//import com.relative.integracion.calculadora.garantia.wrapper.EnvioInformacionPersonaOfertaWrapper;
import com.relative.integracion.calculadora.oferta.wrapper.SimularResponse;
import com.relative.quski.model.TbQoReasignacionActividad;
import com.relative.quski.model.TbQoTipoOro;
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
			@QueryParam("tipoIdentificacion")  String tipoIdentificacion,
			@QueryParam("identificacion")  String identificacion,
			@QueryParam("tipoConsulta")  String tipoConsulta,
			@QueryParam("calificacion")  String calificacion) throws RelativeException {
		 BaseWrapper<Informacion> info =new BaseWrapper<>();
		info.setEntidad((Informacion) this.is.getClienteInformacion(tipoIdentificacion, identificacion, tipoConsulta,
				calificacion));
		return info;
	}

	@GET
	@Path("/getInformacionOferta")
	public BaseWrapper<SimularResponse> getInformacionPersonaOferta(
			@QueryParam("perfilRiesgo")  String perfilRiesgo,
			@QueryParam("origenOperacion")  String origenOperacion,
			@QueryParam("riesgoTotal")  String riesgoTotal,
			@QueryParam("fechaNacimiento")  String fechaNacimiento,
			@QueryParam("perfilPreferencia")  String perfilPreferencia,
			@QueryParam("agenciaOriginacion")  String agenciaOriginacion,
			@QueryParam("identificacionCliente")  String identificacionCliente,
			@QueryParam("calificacionMupi")  String calificacionMupi,
			@QueryParam("coberturaExcepcionada")  String coberturaExcepcionada,
			@QueryParam("tipoJoya")  String tipoJoya,
			@QueryParam("descripcion")  String descripcion,
			@QueryParam("estadoJoya")  String estadoJoya,
			@QueryParam("tipoOroKilataje")  String tipoOroKilataje,
			@QueryParam("pesoGr")  String pesoGr,
			@QueryParam("tienePiedras")  String tienePiedras,
			@QueryParam("detallePiedras")  String detallePiedras,
			@QueryParam("descuentoPesoPiedras")  String descuentoPesoPiedras,
			@QueryParam("pesoNeto")  String pesoNeto,
			@QueryParam("precioOro")  String precioOro,
			@QueryParam("valorAplicableCredito")  String valorAplicableCredito,
			@QueryParam("valorRealizacion")  String valorRealizacion,
			@QueryParam("numeroPiezas")  String numeroPiezas,
			@QueryParam("descuentoSuelda")  String descuentoSuelda) throws RelativeException {
		 BaseWrapper<SimularResponse> oferta =new BaseWrapper<>();
		oferta.setEntidad((SimularResponse) this.is.getClienteInformacionOferta( perfilRiesgo,  origenOperacion,  riesgoTotal,
				 fechaNacimiento,  perfilPreferencia,  agenciaOriginacion,  identificacionCliente,
				 calificacionMupi,  coberturaExcepcionada,  tipoJoya,  descripcion,
				 estadoJoya,  tipoOroKilataje,  pesoGr,  tienePiedras,  detallePiedras,
				 descuentoPesoPiedras,  pesoNeto,  precioOro,  valorAplicableCredito,
				 valorRealizacion,  numeroPiezas,  descuentoSuelda));
		return oferta;
	}
//	@POST
//	@Path("/getInformacionOfertaGarantia")
//	public BaseWrapper<SimularResponse> getInformacionPersonaOferta(EnvioInformacionPersonaOfertaWrapper envioInformacionPersonaOfertaWrapper) throws RelativeException {
//		 BaseWrapper<SimularResponse> oferta =new BaseWrapper<>();
//		oferta.setEntidad((SimularResponse) this.is.getClienteInformacionOfertaGarantia(envioInformacionPersonaOfertaWrapper ));
//		return oferta;
//	}
	
}


