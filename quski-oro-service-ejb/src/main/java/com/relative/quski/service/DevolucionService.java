
package com.relative.quski.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.RegistroFechaArriboWrapper;
import com.relative.quski.wrapper.RespuestaBooleanaWrapper;

@Stateless
public class DevolucionService {
	@Inject
	Logger log;
	@Inject
	private CotizadorRepository cotizadorRepository;
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
			log.info("==> entra a manage Devolucion");
			TbQoDevolucion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findDevolucionById(send.getId());
				return this.updateDevolucion(send, persisted);
			} else if (send != null && send.getId() == null) {
				// send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaCreacion(new Date());
				
				return devolucionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Agente " + e.getMessage());
		}
	}
	
	public TbQoDevolucion registrarSolicitudDevolucion(TbQoDevolucion send , String usuario) throws RelativeException {
		TbQoDevolucion devolucion = manageDevolucion(send);
		devolucion = crearCodigoDev(devolucion);
		TbQoProceso proceso = new TbQoProceso();
		proceso.setIdReferencia(devolucion.getId());
		proceso.setProceso(ProcesoEnum.DEVOLUCION);
		proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
		proceso.setUsuario(usuario);
		qos.manageProceso(proceso);
		
		return devolucion;
	}
	
	
	
	public TbQoDevolucion updateDevolucion(TbQoDevolucion send, TbQoDevolucion persisted) throws RelativeException {
		try {
			persisted.setCodigo(send.getCodigo());
			persisted.setAsesor(send.getAsesor());
			persisted.setAprobador(send.getAprobador());
			persisted.setIdAgencia(send.getIdAgencia());
			persisted.setFechaActualizacion(new Date());
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
			persisted.setEstado(send.getEstado());
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

}
