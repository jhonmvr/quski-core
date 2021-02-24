
package com.relative.quski.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.ActaEntregaRecepcionApoderadoWrapper;
import com.relative.quski.wrapper.ActaEntregaRecepcionHerederoWrapper;
import com.relative.quski.wrapper.ActaEntregaRecepcionWrapper;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.HabilitanteTerminacionContratoWrapper;
import com.relative.quski.wrapper.HerederoWrapper;
import com.relative.quski.wrapper.ListHerederoWrapper;
import com.relative.quski.wrapper.ProcesoDevolucionWrapper;
import com.relative.quski.wrapper.RegistroFechaArriboWrapper;
import com.relative.quski.wrapper.RespuestaBooleanaWrapper;
import com.relative.quski.wrapper.SolicitudDevolucionApoderadoWrapper;
import com.relative.quski.wrapper.SolicitudDevolucionHerederoWrapper;
import com.relative.quski.wrapper.SolicitudDevolucionWrapper;

@Stateless
public class DevolucionService {
	@Inject
	Logger log;
	@Inject 
	private DevolucionRepository devolucionRepository;
	@Inject
	private QuskiOroService qos;
	
	
	
	public TbQoDevolucion findDevolucionById(Long id) throws RelativeException {

		try {
			return devolucionRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<TbQoDevolucion> findAllDevolucion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.devolucionRepository.findAll(TbQoDevolucion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.devolucionRepository.findAll(TbQoDevolucion.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.devolucionRepository.findAll(TbQoDevolucion.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	public Long countDevolucion() throws RelativeException {
		try {
			return devolucionRepository.countAll(TbQoDevolucion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoDevolucion manageDevolucion(TbQoDevolucion send) throws RelativeException {
		try {
			TbQoDevolucion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findDevolucionById(send.getId());
				return this.updateDevolucion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				send.setEstado( EstadoEnum.ACT );
				TbQoDevolucion devolucion = devolucionRepository.add(send);
				return crearCodigoDev(devolucion);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, " AL ACTUALIZAR LA DEVOLUCION. " + e.getMessage());
		}
	}
	
	public ProcesoDevolucionWrapper registrarSolicitudDevolucion(TbQoDevolucion send) throws RelativeException {
		ProcesoDevolucionWrapper result = new ProcesoDevolucionWrapper();
		TbQoProceso proceso = new TbQoProceso();
		if( send.getId() != null) {
			proceso = this.qos.findProcesoByIdReferencia(send.getId(), ProcesoEnum.DEVOLUCION );
		}
		proceso.setProceso( ProcesoEnum.DEVOLUCION );
		proceso.setIdReferencia( send.getId()  );
		proceso.setUsuario( send.getAsesor() );
		proceso.setEstadoProceso( EstadoProcesoEnum.CREADO );			
		result.setProceso(this.qos.manageProceso(proceso));
		result.setDevolucion(this.manageDevolucion(send));
		return result;
	}
	public ProcesoDevolucionWrapper buscarProcesoDevolucion(Long idDevolucion) throws RelativeException {
		ProcesoDevolucionWrapper result = new ProcesoDevolucionWrapper();
		result.setDevolucion( devolucionRepository.findById(idDevolucion) );
		result.setProceso( this.qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION ));
		return result;
	}
	
	
	public TbQoDevolucion updateDevolucion(TbQoDevolucion send, TbQoDevolucion persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setAsesor(send.getAsesor());
			persisted.setEstado( EstadoEnum.ACT );
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setAprobador(send.getAprobador());
			persisted.setIdAgencia(send.getIdAgencia());
			persisted.setCedulaCliente(send.getCedulaCliente());
			persisted.setCodigoOperacion(send.getCodigoOperacion());
			persisted.setNivelEducacion(send.getNivelEducacion());
			persisted.setEstadoCivil(send.getEstadoCivil());
			persisted.setSeparacionBienes(send.getSeparacionBienes());
			persisted.setFechaNacimiento(send.getFechaNacimiento());
			persisted.setNacionalidad(send.getNacionalidad());
			persisted.setLugarNacimiento(send.getLugarNacimiento());
			persisted.setTipoCliente(send.getTipoCliente());
			persisted.setObservaciones(send.getObservaciones());
			persisted.setAgenciaEntregaId(send.getAgenciaEntregaId());
			persisted.setNombreAgenciaSolicitud(send.getNombreAgenciaSolicitud());
			persisted.setGenero(send.getGenero());
			persisted.setAgenciaEntrega(send.getAgenciaEntrega());
			persisted.setValorCustodiaAprox(send.getValorCustodiaAprox());
			persisted.setCodeHerederos(send.getCodeHerederos());
			persisted.setCodeDetalleCredito(send.getCodeDetalleCredito());
			persisted.setCodeDetalleGarantia(send.getCodeDetalleGarantia());
			persisted.setFechaArribo(send.getFechaArribo());
			persisted.setFechaAprobacionSolicitud(send.getFechaAprobacionSolicitud());
			persisted.setFundaActual(send.getFundaActual());
			persisted.setFundaMadre(send.getFundaMadre());
			persisted.setCodigoOperacionMadre(send.getCodigoOperacionMadre());
			persisted.setArribo(send.getArribo());
			persisted.setValorAvaluo(send.getValorAvaluo());
			persisted.setPesoBruto(send.getPesoBruto());
			persisted.setDevuelto(send.getDevuelto());
			persisted.setObservacionAprobador(send.getObservacionAprobador());
			persisted.setFechaEfectiva(send.getFechaEfectiva());
			persisted.setCiudadTevcol(send.getCiudadTevcol());
			persisted.setCiudadEntrega(send.getCiudadEntrega());
			
			return devolucionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Agente " + e.getMessage());
		}
	}
	
	
	private TbQoDevolucion crearCodigoDev(TbQoDevolucion persisted) throws RelativeException {
		try {
			String cod = QuskiOroConstantes.CODIGO_DEVOLUCION.concat(StringUtils.leftPad(persisted.getId().toString(), 7, "0"));
			persisted.setCodigo(cod);
			return this.devolucionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	
	public TbQoDevolucion aprobarSolicitudDevolucion(Long id ) throws RelativeException {
		TbQoDevolucion devolucion = devolucionRepository.findById(id);	
		devolucion.setFechaAprobacionSolicitud(new Date());
		
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_FECHA);
		this.manageDevolucion(devolucion);
		return devolucion;
	}
	
	public TbQoDevolucion rechazarSolicitudDevolucion(Long id ) throws RelativeException {
		TbQoDevolucion devolucion = devolucionRepository.findById(id);	
		devolucion.setFechaAprobacionSolicitud(new Date());
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.RECHAZADO);
		this.manageDevolucion(devolucion);
		return devolucion;
	}
	
	

	public List<DevolucionProcesoWrapper> findOperacion(PaginatedWrapper pw, String codigoOperacion, String agencia,
			String fechaAprobacionDesde, String fechaAprobacionHasta, String identificacion
			) throws RelativeException {
		
		try {
			List<DevolucionProcesoWrapper> actions = this.devolucionRepository.findOperaciones(pw, codigoOperacion, agencia ,
					fechaAprobacionDesde, fechaAprobacionHasta, identificacion );
		
			log.info("la lista" + actions);
			return actions;
		} catch (RelativeException e) {
		
			e.printStackTrace();
			throw e;
		}catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}
	
	public Integer countOperacion(String codigoOperacion, String agencia,
			String fechaAprobacionDesde, String fechaAprobacionHasta, String identificacion) throws RelativeException {
	
	
		
		return devolucionRepository.countOperaciones(codigoOperacion, agencia ,
				fechaAprobacionDesde, fechaAprobacionHasta, identificacion);
	}
	
	
	public List<DevolucionPendienteArribosWrapper> findOperacionArribo(PaginatedWrapper pw, String codigoOperacion, String agencia) throws RelativeException {
		
		try {
		
			List<DevolucionPendienteArribosWrapper> actions = this.devolucionRepository.findOperacionArribo(pw, codigoOperacion, agencia , EstadoProcesoEnum.PENDIENTE_ARRIBO);
		
			log.info("la lista" + actions);
			return actions;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public Integer countOperacionArribo(String codigoOperacion, String agencia) throws RelativeException {
	
	
		
		return devolucionRepository.countOperacionArribo(codigoOperacion, agencia, EstadoProcesoEnum.PENDIENTE_ARRIBO);
	}
	
	public List<TbQoDevolucion> registrarFechaArribo(RegistroFechaArriboWrapper rfaw  ) throws RelativeException {
		List<TbQoDevolucion> devoluciones = new ArrayList<>();
		for(Long id : rfaw.getIdDevoluciones()) {
			TbQoDevolucion devolucion = devolucionRepository.findById(id);
			if(devolucion.getFechaArribo() == null || devolucion.getFechaArribo().toString().isEmpty()) {
				devolucion.setFechaArribo(QuskiOroUtil.formatSringToDate(rfaw.getFechaArribo()));
				qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_ARRIBO);
				devolucion= manageDevolucion(devolucion);
				
				devoluciones.add(devolucion);
			}else {
				//DEVOLVER LO QUE NO SE HA PROCESADO
			}
				
			
		}
	
		
		return devoluciones;
	}
	
	public List<TbQoDevolucion> registrarArriboAgencia(List<Long> idDevoluciones) throws RelativeException {
		List<TbQoDevolucion> devoluciones = new ArrayList<>();
		for(Long id : idDevoluciones) {
			TbQoDevolucion devolucion = devolucionRepository.findById(id);
			qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.ARRIBADO);
			if(devolucion.getArribo() == null && !this.existeProcesoCancelacionVigente(id)) {
				devolucion.setArribo(true);
				devolucion= manageDevolucion(devolucion);
				
				devoluciones.add(devolucion);
			}else {
				
			}
		}
	
		
		return devoluciones;
	}
	
	
	public TbQoDevolucion cancelarSolicitudDevolucion(Long id , String usuario) throws RelativeException {
		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia( id, ProcesoEnum.DEVOLUCION );
			TbQoDevolucion devolucion = devolucionRepository.findById(id);
			TbQoProceso proceso = new TbQoProceso();
			proceso.setIdReferencia(devolucion.getId());
			proceso.setProceso(ProcesoEnum.CANCELACION_DEVOLUCION);
			proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
			proceso.setUsuario(usuario);
			qos.manageProceso(persisted);
			qos.manageProceso(proceso);
			
			return devolucion;
		}catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
		
	}
	
	
	public TbQoDevolucion mandarAprobarSolicitudDevolucion(Long id , String usuario) throws RelativeException {
		TbQoDevolucion devolucion = devolucionRepository.findById(id);
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_APROBACION);
	//	qos.
		this.manageDevolucion(devolucion);	
		return devolucion;
		
	}
	
	public TbQoDevolucion aprobarCancelacionSolicitudDevolucion(Long id ) throws RelativeException {
		TbQoDevolucion devolucion = devolucionRepository.findById(id);
		qos.cambiarEstado(id, ProcesoEnum.CANCELACION_DEVOLUCION, EstadoProcesoEnum.CANCELADO);
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.CANCELADO);
		this.manageDevolucion(devolucion);
		
		return devolucion;
	}
	public TbQoDevolucion rechazarCancelacionSolicitudDevolucion(Long id ) throws RelativeException {
		TbQoDevolucion devolucion = devolucionRepository.findById(id);
		qos.cambiarEstado(id, ProcesoEnum.CANCELACION_DEVOLUCION, EstadoProcesoEnum.RECHAZADO);
		this.manageDevolucion(devolucion);
		
		return devolucion;
	}
	
	
	
public Boolean validateAprobarCancelacionSolicitud(Long idDevolucion) throws RelativeException {
		
	try {
	TbQoProceso persisted = qos.findProcesoByIdReferencia( idDevolucion, ProcesoEnum.CANCELACION_DEVOLUCION );
		
		if(persisted.getProceso().equals(ProcesoEnum.CANCELACION_DEVOLUCION) && persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION)) {
			return true;
		}else {
			return false;
		}
	}
	catch (RelativeException e) {
		throw new RelativeException(Constantes.ERROR_CODE_READ,
				QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
	}
	
}

public RespuestaBooleanaWrapper validateCancelacionSolicitud(Long idDevolucion) throws RelativeException {
	RespuestaBooleanaWrapper respuesta = new RespuestaBooleanaWrapper();
	try {
	TbQoProceso persisted = qos.findProcesoByIdReferencia( idDevolucion, ProcesoEnum.DEVOLUCION );
		if(this.existeProcesoCancelacionVigente(idDevolucion)) {
			respuesta.setBandera(false);
			respuesta.setMensaje("YA EXISTE UN PROCESO DE CANCELACION");
			return respuesta;
		}else {
			if(persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION) ||
					persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_ARRIBO)||
					persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_FECHA) ||
					persisted.getEstadoProceso().equals(EstadoProcesoEnum.ARRIBADO)) {
				respuesta.setBandera(true);
				respuesta.setMensaje("ES POSIBLE REALIZAR LA CANCELACION");
				return respuesta;
			}else {
				respuesta.setBandera(false);
				respuesta.setMensaje("NO ES FACTIBLE REALIZAR LA CANCELACION EN ESTADO " +persisted.getEstadoProceso());
				return respuesta;
			
			}
			}
		
	}catch (RelativeException e) {
		throw new RelativeException(Constantes.ERROR_CODE_READ,
				QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
	}
}
public Boolean existeProcesoCancelacionVigente(Long idDevolucion) throws RelativeException{
	try {
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		
	
		if ( qos.findProcesoByIdReferencia( idDevolucion, ProcesoEnum.CANCELACION_DEVOLUCION )!= null){
			TbQoProceso proceso = qos.findProcesoByIdReferencia( idDevolucion, ProcesoEnum.CANCELACION_DEVOLUCION );
			if (proceso.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION)) {
				return true;
				
			} else {
				return false;
			}
		}else {
			return false;
		}
	} catch (RelativeException e) {
		throw new RelativeException(Constantes.ERROR_CODE_READ,
				QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
	}
}

public TbQoDevolucion guardarEntregaRecepcion(Long id ) throws RelativeException {
	TbQoDevolucion devolucion = devolucionRepository.findById(id);
	qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA);
	this.manageDevolucion(devolucion);

	return devolucion;
}

public TbQoDevolucion aprobarVerificacionFirmas(Long id ) throws RelativeException {
	TbQoDevolucion devolucion = devolucionRepository.findById(id);
	qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.APROBADO);
	this.manageDevolucion(devolucion);

	return devolucion;
}

public TbQoDevolucion rechazarVerificacionFirmas(Long id ) throws RelativeException {
	TbQoDevolucion devolucion = devolucionRepository.findById(id);
	qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.ARRIBADO);
	devolucion.setDevuelto(true);
	this.manageDevolucion(devolucion);

	return devolucion;
}

/**
 * HABILITANTES devolucion
 */

public HabilitanteTerminacionContratoWrapper setHabilitanteTerminacionContrato(Long idDevolucion)
		throws RelativeException {

	HabilitanteTerminacionContratoWrapper habilitante = new HabilitanteTerminacionContratoWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	
	habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
	log.info("entra yal metodo habilitante" + habilitante.getNombreCompletoCliente());
//	habilitante.setFechaElaboracionContrato(QuskiOroUtil.dateToFullString(devolucion.getFechaEfectiva()));
	log.info("entra yal metodo habilitante" + habilitante.getNombreCompletoCliente());
	habilitante.setFechaActual(QuskiOroUtil.dateToFullString(new Date()));
	habilitante.setApoderadoMutualista("Quemaado hasta mientras");
	habilitante.setNombreUsuario("Quemaado hasta mientras");
	habilitante.setRolUsuario("rolUsuario");
	habilitante.setCedulaCliente(devolucion.getCedulaCliente());
	return habilitante;
}

public ActaEntregaRecepcionWrapper setHabilitanteActaEntrega(Long idDevolucion)
		throws RelativeException {

	ActaEntregaRecepcionWrapper habilitante = new ActaEntregaRecepcionWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	habilitante.setCiudad(devolucion.getCiudadEntrega());
	habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
	habilitante.setFechaDevolucion(QuskiOroUtil.dateToFullString(devolucion.getFechaCreacion()));
	habilitante.setNumeroFunda(devolucion.getFundaActual());
	habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
	habilitante.setNombreAsesor(devolucion.getAsesor());
	habilitante.setCedulaCliente(devolucion.getCedulaCliente());
	return habilitante;
}

public ActaEntregaRecepcionApoderadoWrapper setHabilitanteActaEntregaApoderado(Long idDevolucion)
		throws RelativeException {

	ActaEntregaRecepcionApoderadoWrapper habilitante = new ActaEntregaRecepcionApoderadoWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	habilitante.setCiudad(devolucion.getCiudadEntrega());
	habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
	habilitante.setFechaDevolucion(QuskiOroUtil.dateToFullString(devolucion.getFechaCreacion()));
	habilitante.setNumeroFunda(devolucion.getFundaActual());
	habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
	habilitante.setNombreAsesor(devolucion.getAsesor());
	habilitante.setCedulaApoderado(devolucion.getCedulaApoderado());
	habilitante.setNombreApoderado(devolucion.getNombreApoderado());
	return habilitante;
}

public ActaEntregaRecepcionHerederoWrapper setHabilitanteActaEntregaHeredero(Long idDevolucion)
		throws RelativeException {

	ActaEntregaRecepcionHerederoWrapper habilitante = new ActaEntregaRecepcionHerederoWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	habilitante.setCiudad(devolucion.getCiudadEntrega());
	habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
	habilitante.setFechaDevolucion(QuskiOroUtil.dateToFullString(devolucion.getFechaCreacion()));
	habilitante.setNumeroFunda(devolucion.getFundaActual());
	habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
	habilitante.setNombreAsesor(devolucion.getAsesor());
	habilitante.setHerederos((setStringHeredero(getHerederos(idDevolucion))));
	return habilitante;
}

public  List<HerederoWrapper> getHerederos(Long idDevolucion) throws RelativeException {
	
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	String herederos = devolucion.getCodeHerederos();
	String decodedUrl = new String(Base64.getDecoder().decode(herederos));
	Gson gsons = new GsonBuilder().create();
	ListHerederoWrapper  listHeredero= gsons.fromJson(decodedUrl, ListHerederoWrapper.class);

	return listHeredero.getHeredero();	
}


public static  String setStringHeredero(List<HerederoWrapper> herederos) throws RelativeException {
	String respuestaHerederos = "";
	for (HerederoWrapper h : herederos) {
		
		if(h.equals(herederos.get(0))){
			respuestaHerederos = respuestaHerederos.concat(h.getCedula() +" " + h.getNombre());
		}else {
			if (h.equals(herederos.get(herederos.size()-1))) {
				respuestaHerederos = respuestaHerederos.concat(" y "+h.getCedula() +" " + h.getNombre());
		} else {
			respuestaHerederos = respuestaHerederos.concat(", "+h.getCedula() +" " + h.getNombre());
		}
		}
		
			
		
	}
	System.out.println(respuestaHerederos);
	return respuestaHerederos;
	
}





public SolicitudDevolucionWrapper setHabilitanteSolicitudDevolucion(Long idDevolucion)
		throws RelativeException {

	SolicitudDevolucionWrapper habilitante = new SolicitudDevolucionWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	habilitante.setAgenciaEntrega(devolucion.getAgenciaEntrega());
	habilitante.setFechaSolicitud(QuskiOroUtil.dateToString(devolucion.getFechaCreacion()));
	habilitante.setAgenciaSolicitante(devolucion.getNombreAgenciaSolicitud());
	habilitante.setNumeroFunda(devolucion.getFundaActual());
	habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
	habilitante.setAsesor(devolucion.getAsesor());
	habilitante.setNombreCliente(devolucion.getNombreCliente());
	habilitante.setCedulaCliente(devolucion.getCedulaCliente());
	

	return habilitante;
}


public SolicitudDevolucionApoderadoWrapper setHabilitanteSolicitudDevolucionApoderado(Long idDevolucion)
		throws RelativeException {

	SolicitudDevolucionApoderadoWrapper habilitante = new SolicitudDevolucionApoderadoWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	habilitante.setAgenciaEntrega(devolucion.getAgenciaEntrega());
	habilitante.setFechaSolicitud(QuskiOroUtil.dateToString(devolucion.getFechaCreacion()));
	habilitante.setAgenciaSolicitante(devolucion.getNombreAgenciaSolicitud());
	habilitante.setNumeroFunda(devolucion.getFundaActual());
	habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
	habilitante.setAsesor(devolucion.getAsesor());
	habilitante.setNombreCliente(devolucion.getNombreCliente());
	habilitante.setCedulaCliente(devolucion.getCedulaCliente());
	habilitante.setNombreApoderado(devolucion.getNombreApoderado());
	habilitante.setNombreApoderado(devolucion.getCedulaApoderado());

	return habilitante;
}



public SolicitudDevolucionHerederoWrapper setHabilitanteSolicitudDevolucionHeredero(Long idDevolucion)
		throws RelativeException {

	SolicitudDevolucionHerederoWrapper habilitante = new SolicitudDevolucionHerederoWrapper();
	TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
	habilitante.setAgenciaEntrega(devolucion.getAgenciaEntrega());
	habilitante.setFechaSolicitud(QuskiOroUtil.dateToString(devolucion.getFechaCreacion()));
	habilitante.setAgenciaSolicitante(devolucion.getNombreAgenciaSolicitud());
	habilitante.setNumeroFunda(devolucion.getFundaActual());
	habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
	habilitante.setAsesor(devolucion.getAsesor());
	habilitante.setNombreCliente(devolucion.getNombreCliente());
	habilitante.setCedulaCliente(devolucion.getCedulaCliente());
	habilitante.setHeredero(setStringHeredero(getHerederos(idDevolucion)));

	return habilitante;
}


public static void main(String[] args) {
	try {
		String code = "ewoJImhlcmVkZXJvIiA6IFt7ImNlZHVsYSI6IjE3MjA4MTIyMzciLCJub21icmUiOiJEaWVnbyBTZXJyYW5vIn0sIHsiY2VkdWxhIjoiMTcyMDgxMjIzOCIsIm5vbWJyZSI6IkRpZWdvIFNlcnJhbnUifV0KfQ==";
	
		String decodedUrl = new String(Base64.getDecoder().decode(code));
		Gson gsons = new GsonBuilder().create();
		//System.out.print("decode" + decodedUrl);
		ListHerederoWrapper  listHeredero= gsons.fromJson(decodedUrl, ListHerederoWrapper.class);
		String pepito = setStringHeredero(listHeredero.getHeredero());
	//	List<HerederoWrapper> lista = gsons.fromJson(decodedUrl, new ArrayList<HerederoWrapper>().getClass());
		//Class<? extends ArrayList> listType = new ArrayList<HerederoWrapper>().getClass();
		
		//List<HerederoWrapper> list =   gsons.fromJson((String) decodedUrl, listType);
		//HerederoWrapper heredero =  list.get(0);
		
		//System.out.print("HOLI" + listHeredero.getHeredero().get(0));
	/*	
		for (HerederoWrapper h : listHeredero.getHeredero()) {
			System.out.println(h.getCedula() +" " + h.getNombre() );
		}*/
		/*
		  for (int i = 0; i < list.size(); i++) {
			    gsons.fromJson((String) list.get(i), HerederoWrapper.class);
	            System.out.println(list.get(i));
	        }*/
		//System.out.print("HOLI" + list.get(1));
	} catch (Exception e) {
		e.printStackTrace();
	}

}



}
