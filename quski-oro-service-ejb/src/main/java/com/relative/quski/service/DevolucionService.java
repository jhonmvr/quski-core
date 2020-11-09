
package com.relative.quski.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.BusquedaDevolucionWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

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
			persisted.setIdAgenciaEntrega(send.getIdAgenciaEntrega());
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
		TbQoDevolucion devolucion = new TbQoDevolucion();
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_ARRIBO);
	
		
		return devolucion;
	}
	
	
	public TbQoDevolucion rechazarSolicitudDevolucion(Long id ) throws RelativeException {
		TbQoDevolucion devolucion = new TbQoDevolucion();
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.RECHAZADO);
	
		
		return devolucion;
	}
	
	
	public PaginatedListWrapper<DevolucionProcesoWrapper> findOperacion(BusquedaDevolucionWrapper bdw) throws RelativeException {
		
		PaginatedListWrapper<DevolucionProcesoWrapper> plw = new PaginatedListWrapper<>();
		List<DevolucionProcesoWrapper> actions = this.devolucionRepository.findOperaciones(bdw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.devolucionRepository.countOperaciones(bdw).intValue());
			plw.setList(actions);
		}
		
		return plw;
	}
	
	public Integer countOperacion(BusquedaDevolucionWrapper bdw) throws RelativeException {
	
	
		
		return devolucionRepository.countOperaciones(bdw);
	}
	
	
	
}
