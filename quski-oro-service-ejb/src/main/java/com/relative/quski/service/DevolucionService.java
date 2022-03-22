
package com.relative.quski.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.ActaEntregaRecepcionApoderadoWrapper;
import com.relative.quski.wrapper.ActaEntregaRecepcionHerederoWrapper;
import com.relative.quski.wrapper.ActaEntregaRecepcionWrapper;
import com.relative.quski.wrapper.BloqueoWrapper;
import com.relative.quski.wrapper.CatalogoAgenciaWrapper;
import com.relative.quski.wrapper.ConsultaOperacionGlobalWrapper;
import com.relative.quski.wrapper.DevolucionParamsWrapper;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.DevolucionReporteWrapper;
import com.relative.quski.wrapper.EntregaGarantiasReporteWrapper;
import com.relative.quski.wrapper.HabilitanteTerminacionContratoWrapper;
import com.relative.quski.wrapper.HerederoConsolidadoWrapper;
import com.relative.quski.wrapper.HerederoWrapper;
import com.relative.quski.wrapper.ListHerederoWrapper;
import com.relative.quski.wrapper.ObjetoHabilitanteWrapper;
import com.relative.quski.wrapper.ProcesoDevoActivoWrapper;
import com.relative.quski.wrapper.ProcesoDevolucionWrapper;
import com.relative.quski.wrapper.RegistroFechaArriboWrapper;
import com.relative.quski.wrapper.RespuestaBooleanaWrapper;
import com.relative.quski.wrapper.RespuestaConsultaGlobalWrapper;
import com.relative.quski.wrapper.RespuestaValidacionWrapper;
import com.relative.quski.wrapper.SolicitudDevolucionApoderadoWrapper;
import com.relative.quski.wrapper.SolicitudDevolucionHerederoWrapper;
import com.relative.quski.wrapper.SolicitudDevolucionWrapper;
import com.relative.quski.wrapper.TrakingProcesoWrapper;

@Stateless
public class DevolucionService {
	@Inject
	Logger log;
	@Inject
	private DevolucionRepository devolucionRepository;
	@Inject
	private QuskiOroService qos;
	@Inject
	private ParametroRepository parametroRepository;
	
	@Inject
	private DocumentoHabilitanteRepository documentoHabilitanteRepository;
	@Inject 
	ReportService rs;
	

	public TbQoDevolucion findDevolucionById(Long id) throws RelativeException {
		return devolucionRepository.findById(id);
		
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
				send.setEstado(EstadoEnum.ACT);
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
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					" AL ACTUALIZAR LA DEVOLUCION. " + e.getMessage());
		}
	}

	public ProcesoDevolucionWrapper registrarSolicitudDevolucion(TbQoDevolucion send) throws RelativeException {
		try {
			ProcesoDevolucionWrapper result = new ProcesoDevolucionWrapper();
			TbQoProceso proceso = new TbQoProceso();
			if (send.getId() != null ) {
				proceso = this.qos.findProcesoByIdReferencia(send.getId(), ProcesoEnum.DEVOLUCION);
			}else {
				if( StringUtils.isBlank( send.getCodigoOperacion() )) {
					throw new RelativeException(" FALTA CODIGO OPERACION SOFTBANK. ");				
				}
				List<TbQoDevolucion> devoluciones = this.devolucionRepository.findByNumeroOperacion( send.getCodigoOperacion() );
				if(devoluciones != null) {
					for(TbQoDevolucion e : devoluciones) {
						TbQoProceso procesoActivo = this.qos.findProcesoByIdReferencia( e.getId(), ProcesoEnum.DEVOLUCION);
						if(procesoActivo != null && procesoActivo.getEstadoProceso() != EstadoProcesoEnum.CANCELADO && procesoActivo.getEstadoProceso() != EstadoProcesoEnum.RECHAZADO) {
							throw new RelativeException(" YA EXISTE UN PROCESO DE DEVOLUCION ACTIVO PARA ESTE CREDITO: " +e.getCodigo() );				
						}		
					}
				}
			}
			send.setUsuarioSolicitud(send.getAsesor());
			send = this.manageDevolucion(send);
			proceso.setProceso(ProcesoEnum.DEVOLUCION);
			proceso.setIdReferencia(send.getId());
			proceso.setUsuario(send.getAsesor());
			proceso.setEstadoProceso(EstadoProcesoEnum.CREADO);
			result.setProceso(this.qos.manageProceso(proceso));
			result.setDevolucion(send);
			return result;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE," AL ACTUALIZAR LA DEVOLUCION. " + e.getMessage());
		}
		
	}
	
	public ProcesoDevolucionWrapper buscarProcesoCancelacion( Long idProceso) throws RelativeException {
		ProcesoDevolucionWrapper result = new ProcesoDevolucionWrapper();
		
		result.setProceso(this.qos.findProcesoById(idProceso));
		result.setDevolucion(devolucionRepository.findById(result.getProceso().getIdReferencia()));
		return result;
	}
	public ProcesoDevolucionWrapper buscarProcesoDevolucion(Long idDevolucion) throws RelativeException {
		ProcesoDevolucionWrapper result = new ProcesoDevolucionWrapper();
		result.setDevolucion(devolucionRepository.findById(idDevolucion));
		result.setProceso(this.qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION));
		return result;
	}
	public RespuestaValidacionWrapper validarProcesoActivo( String numeroOperacion ) throws RelativeException {
		try {
			RespuestaValidacionWrapper result = new RespuestaValidacionWrapper();
			List<ProcesoDevoActivoWrapper>  list = this.qos.findProcesoByIdReferencia( numeroOperacion );
			log.info(" ===========> LISTA DE DEVOLUCIONES =============================>" + list.size() );
			if( list == null || list.size() < 1 ) {
				result.setExiste( Boolean.FALSE );
				result.setMensaje( "NO HAY PROCESOS ACTIVOS." );
			}else {
				result.setExiste( Boolean.TRUE );
				result.setMensaje( "EXISTE UN PROCESO ACTIVO PARA ESTE CREDITO: "+ list.get(0).getCodigo() );
			}
			return result;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE," AL VALIDAR LA DEVOLUCION. " );			
		}
	}

	public TbQoDevolucion updateDevolucion(TbQoDevolucion send, TbQoDevolucion persisted) throws RelativeException {
		try {
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

			if (StringUtils.isNotBlank(send.getAgenciaEntrega())) {
				persisted.setAgenciaEntrega(send.getAgenciaEntrega());
			}
			if (StringUtils.isNotBlank(send.getObservacionCancelacion())) {
				persisted.setObservacionCancelacion(send.getObservacionCancelacion());
			}
			if ( send.getEsMigrado() != null ) {
				persisted.setEsMigrado(send.getEsMigrado());
			}
			if (StringUtils.isNotBlank(send.getAprobador())) {
				persisted.setAprobador(send.getAprobador());
			}
			if (StringUtils.isNotBlank(send.getAsesor())) {
				persisted.setAsesor(send.getAsesor());
			}
			if (StringUtils.isNotBlank(send.getCedulaCliente())) {
				persisted.setCedulaCliente(send.getCedulaCliente());
			}
			if (StringUtils.isNotBlank(send.getCodeDetalleCredito())) {
				persisted.setCodeDetalleCredito(send.getCodeDetalleCredito());
			}
			if (StringUtils.isNotBlank(send.getCodeDetalleGarantia())) {
				persisted.setCodeDetalleGarantia(send.getCodeDetalleGarantia());
			}
			if (StringUtils.isNotBlank(send.getCodeHerederos())) {
				persisted.setCodeHerederos(send.getCodeHerederos());
			}
			if (StringUtils.isNotBlank(send.getCodigo())) {
				persisted.setCodigo(send.getCodigo());
			}
			if (StringUtils.isNotBlank(send.getCodigoOperacion())) {
				persisted.setCodigoOperacion(send.getCodigoOperacion());
			}
			if (StringUtils.isNotBlank(send.getGenero())) {
				persisted.setGenero(send.getGenero());
			}
			if (send.getEstado() != null) {
				persisted.setEstado(send.getEstado());
			}
			if (StringUtils.isNotBlank(send.getEstadoCivil())) {
				persisted.setEstadoCivil(send.getEstadoCivil());
			}
			if (send.getFechaNacimiento() != null) {
				persisted.setFechaNacimiento(send.getFechaNacimiento());
			}
			if (send.getIdAgencia() != null) {
				persisted.setIdAgencia(send.getIdAgencia());
			}
			if (StringUtils.isNotBlank(send.getNombreAgenciaSolicitud())) {
				persisted.setNombreAgenciaSolicitud(send.getNombreAgenciaSolicitud());
			}
			if (send.getAgenciaEntregaId() != null) {
				persisted.setAgenciaEntregaId(send.getAgenciaEntregaId());
			}
			if (StringUtils.isNotBlank(send.getLugarNacimiento())) {
				persisted.setLugarNacimiento(send.getLugarNacimiento());
			}
			if (StringUtils.isNotBlank(send.getNacionalidad())) {
				persisted.setNacionalidad(send.getNacionalidad());
			}
			if (StringUtils.isNotBlank(send.getNivelEducacion())) {
				persisted.setNivelEducacion(send.getNivelEducacion());
			}
			if (StringUtils.isNotBlank(send.getNombreCliente())) {
				persisted.setNombreCliente(send.getNombreCliente());
			}
			if (StringUtils.isNotBlank(send.getObservaciones())) {
				persisted.setObservaciones(send.getObservaciones());
			}
			if (StringUtils.isNotBlank(send.getSeparacionBienes())) {
				persisted.setSeparacionBienes(send.getSeparacionBienes());
			}
			if (StringUtils.isNotBlank(send.getTipoCliente())) {
				persisted.setTipoCliente(send.getTipoCliente());
			}
			if (send.getFechaArribo() != null) {
				persisted.setFechaArribo(send.getFechaArribo());
			}
			if (send.getFechaAprobacionSolicitud() != null) {
				persisted.setFechaAprobacionSolicitud(send.getFechaAprobacionSolicitud());
			}
			if (send.getFechaEfectiva() != null) {
				persisted.setFechaEfectiva(send.getFechaEfectiva());
			}
			if (StringUtils.isNotBlank(send.getFundaActual())) {
				persisted.setFundaActual(send.getFundaActual());
			}
			if (StringUtils.isNotBlank(send.getFundaMadre())) {
				persisted.setFundaMadre(send.getFundaMadre());
			}
			if (StringUtils.isNotBlank(send.getCodigoOperacionMadre())) {
				persisted.setCodigoOperacionMadre(send.getCodigoOperacionMadre());
			}
			if (StringUtils.isNotBlank(send.getObservacionAprobador())) {
				persisted.setObservacionAprobador(send.getObservacionAprobador());
			}
			if (send.getValorAvaluo() != null) {
				persisted.setValorAvaluo(send.getValorAvaluo());
			}
			if (send.getValorCustodiaAprox() != null) {
				persisted.setValorCustodiaAprox(send.getValorCustodiaAprox());
			}
			if (send.getArribo() != null) {
				persisted.setArribo(send.getArribo());
			}
			if (send.getDevuelto() != null) {
				persisted.setDevuelto(send.getDevuelto());
			}
			if (send.getPesoBruto() != null) {
				persisted.setPesoBruto(send.getPesoBruto());
			}
			if (StringUtils.isNotBlank(send.getCiudadTevcol())) {
				persisted.setCiudadTevcol(send.getCiudadTevcol());
			}
			if (StringUtils.isNotBlank(send.getNombreApoderado())) {
				persisted.setNombreApoderado(send.getNombreApoderado());
			}
			if (StringUtils.isNotBlank(send.getCedulaApoderado())) {
				persisted.setCedulaApoderado(send.getCedulaApoderado());
			}
			if (StringUtils.isNotBlank(send.getCiudadEntrega())) {
				persisted.setCiudadEntrega(send.getCiudadEntrega());
			}
			if (StringUtils.isNotBlank(send.getCorreoCliente())) {
				persisted.setCorreoCliente(send.getCorreoCliente());
			}
			if (StringUtils.isNotBlank(send.getCorreoAsesor())) {
				persisted.setCorreoAsesor(send.getCorreoAsesor());
			}
			if (send.getMontoCredito() != null) {
				persisted.setMontoCredito(send.getMontoCredito());
			}
			if (StringUtils.isNotBlank(send.getPlazoCredito())) {
				persisted.setPlazoCredito(send.getPlazoCredito());
			}
			if (StringUtils.isNotBlank(send.getTipoCredito())) {
				persisted.setTipoCredito(send.getTipoCredito());
			}
			if (StringUtils.isNotBlank(send.getNumeroCuentaCliente())) {
				persisted.setNumeroCuentaCliente(send.getNumeroCuentaCliente());
			}
			if (StringUtils.isNotBlank(send.getNombreAsesor())) {
				persisted.setNombreAsesor(send.getNombreAsesor());
			}
			return devolucionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Agente " + e.getMessage());
		}
	}

	private TbQoDevolucion crearCodigoDev(TbQoDevolucion persisted) throws RelativeException {
		try {
			String cod = QuskiOroConstantes.CODIGO_DEVOLUCION
					.concat(StringUtils.leftPad(persisted.getId().toString(), 7, "0"));
			persisted.setCodigo(cod);
			return this.devolucionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public ProcesoDevolucionWrapper aprobarNegarSolicitudDevolucion(Long idDevolucion, Boolean aprobado, String usuario, String autorizacion, String motivo)
			throws RelativeException {
		try {
			TbQoProceso proceso = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION);
			if ( !proceso.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION) ) {
				throw new RelativeException(Constantes.ERROR_CODE_UPDATE,"EL PROCESO DE DEVOLUCION YA FUE APROBADO O RECHAZADO. ESTADO ACTUAL: " + proceso.getEstadoProceso());				
			}
			TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
			ProcesoDevolucionWrapper result = new ProcesoDevolucionWrapper();
			devolucion.setFechaAprobacionSolicitud(new Timestamp(System.currentTimeMillis()));
			devolucion.setObservacionAprobador(motivo);
			result.setDevolucion(this.manageDevolucion(devolucion));
			//traking 
			TbQoTracking traking = new TbQoTracking();
			
			traking.setCodigoBpm(devolucion.getCodigo());
			traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(usuario);
			traking.setUsuarioCreacion(usuario);
			traking.setObservacion(devolucion.getObservacionAprobador());
			traking.setProceso(ProcesoEnum.DEVOLUCION);
			
			if (aprobado) {
				proceso = this.qos.cambiarEstado(idDevolucion, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_FECHA, usuario);
				result.setProceso(proceso);
				bloquear(proceso, devolucion, QuskiOroConstantes.CODIGO_BLOQUEO_A,Boolean.TRUE, autorizacion);
				traking.setActividad("PENDIENTE_DE_FECHA_DE_ARRIBO");
				traking.setSeccion("PENDIENTE_DE_FECHA_DE_ARRIBO");
				//this.notificarDevolucionAprobacion(aprobado, result.getDevolucion(), motivo);
			} else {
				result.setProceso(this.qos.cambiarEstado(idDevolucion, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.RECHAZADO, usuario));
				bloquear(proceso, devolucion, QuskiOroConstantes.CODIGO_BLOQUEO_F,Boolean.FALSE, autorizacion);
				traking.setActividad("RECHAZO_DE_SOLICITUD_DE_DEVOLUCION");
				traking.setSeccion("RECHAZO_DE_SOLICITUD_DE_DEVOLUCION");
				//this.notificarDevolucionAprobacion(aprobado, result.getDevolucion(), motivo);
			}
			this.mailSolicitudEntregaNegada(devolucion, proceso);
			this.qos.guardaraObservacionEntrega(devolucion.getObservacionAprobador(), BigDecimal.valueOf(devolucion.getId()), usuario);
		
			qos.registrarTraking(traking);
			
			return result;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getCause());
		}
		
	}
	public void notificarDevolucionAprobacion(Boolean aprobado, TbQoDevolucion dev, String motivo) {
		try {
			List<TbMiParametro> paras = this.parametroRepository.findByNombreAndTipoOrdered(null, QuskiOroConstantes.MAIL_DEVOLUCION_PARA, false);
			List<String> array = new ArrayList<String>();
			if(aprobado) {
				
				for (int i = 0; i < paras.size(); ++i) {
					array.add(paras.get(i).getValor()
							.replace("--correoAsesor--", dev.getCorreoAsesor())
							.replace("--correoCliente--",  dev.getCorreoCliente()) );
				}
				
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_DEVOLUCION_APROBACION).getValor();
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_DEVOLUCION_APROBACION).getValor()
						.replace("--nombreCliente--", dev.getNombreCliente() )
						.replace("--nombreAgenciaEntrega--",  dev.getAgenciaEntrega())
						.replace("--motivo--",  StringUtils.isNotBlank(motivo)?motivo:" ")
						.replace("--fechaAprobacion--",  QuskiOroUtil.dateToFullString( dev.getFechaAprobacionSolicitud() ) );
				this.qos.mailNotificacion(array.toArray(new String[]{}) , asunto, contenido, null);
			}else {
				
				for (int i = 0; i < paras.size(); ++i) {
					if(!paras.get(i).getValor().equalsIgnoreCase("--correoCliente--")) {
						if(StringUtils.isBlank(dev.getCorreoAsesor())) {
							throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL EMAIL DEL ASESOR");
						}
						array.add( paras.get(i).getValor()
								.replace("--correoAsesor--",  dev.getCorreoAsesor()) );
					}
				}
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_DEVOLUCION_RECHAZO).getValor();
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_DEVOLUCION_RECHAZO).getValor()
						.replace("--nombreAsesor--", dev.getAsesor() != null ? dev.getAsesor() : dev.getUsuarioSolicitud() )
						.replace("--nombreAgenciaEntrega--",  dev.getAgenciaEntrega())
						.replace("--motivo--",  StringUtils.isNotBlank(motivo)?motivo:" ")
						.replace("--fechaAprobacion--",  QuskiOroUtil.dateToFullString( dev.getFechaAprobacionSolicitud() ) );
				this.qos.mailNotificacion(array.toArray(new String[]{}) , asunto, contenido, null);
			}		
		} catch (RelativeException e) {
			e.printStackTrace();
			log.info("ERROR ========>" + QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
			//throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
		}
	}
	public void notificarDevolucionVerificacionFirmas(Boolean aprobado, TbQoDevolucion dev) {
		try {
			List<TbMiParametro> paras = this.parametroRepository.findByNombreAndTipoOrdered(null, QuskiOroConstantes.MAIL_DEVOLUCION_PARA, false);
			List<String> array = new ArrayList<String>();
			if(aprobado) {
				
				for (int i = 0; i < paras.size(); ++i) {
					array.add(paras.get(i).getValor()
							.replace("--correoAsesor--",  dev.getCorreoAsesor())
							.replace("--correoCliente--",  dev.getCorreoCliente()) );
				}
				
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_VERIFICACION_FIRMA_APROBACION).getValor();
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_VERIFICACION_FIRMA_APROBACION).getValor()
						.replace("--nombreCliente--", dev.getNombreCliente() )
						.replace("--codigoBpm--",  dev.getCodigo());
				this.qos.mailNotificacion(array.toArray(new String[]{}) , asunto, contenido, null);
			}else {
				for (int i = 0; i < paras.size(); ++i) {
					if(!paras.get(i).getValor().equalsIgnoreCase("--correoCliente--")) {
						if(StringUtils.isBlank(dev.getCorreoAsesor())) {
							throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL EMAIL DEL ASESOR");
						}
						array.add(paras.get(i).getValor()
								.replace("--correoAsesor--",  dev.getCorreoAsesor()));
					}
				}
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_VERIFICACION_FIRMA_RECHAZO).getValor();
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_VERIFICACION_FIRMA_RECHAZO).getValor()
						.replace("--nombreAsesor--", dev.getAsesor() != null ? dev.getAsesor() : dev.getUsuarioSolicitud() )
						.replace("--codigoBpm--",  dev.getCodigo());
				this.qos.mailNotificacion(array.toArray(new String[]{}) , asunto, contenido, null);
			}		
		} catch (RelativeException e) {
			e.printStackTrace();
			log.info("ERROR ========>" + QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
			//throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
		}
	}
	public void notificarCancelacionDevolucion(Boolean aprobado, TbQoDevolucion dev) {
		try {
			List<TbMiParametro> paras = this.parametroRepository.findByNombreAndTipoOrdered(null, QuskiOroConstantes.MAIL_DEVOLUCION_PARA, false);
			List<String> array = new ArrayList<String>();
			for (int i = 0; i < paras.size(); ++i) {
				if(!paras.get(i).getValor().equalsIgnoreCase("--correoCliente--")) {
					if(StringUtils.isBlank(dev.getCorreoAsesor())) {
						throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL EMAIL DEL ASESOR");
					}
					array.add(paras.get(i).getValor()
							.replace("--correoAsesor--",  dev.getCorreoAsesor()) );
				}
				
			}
			
			if(aprobado) {
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_CANCELACION_DEVOLUCION_APROBACION).getValor();
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_CANCELACION_DEVOLUCION_APROBACION).getValor()
						.replace("--nombreAsesor--",  dev.getAsesor() != null ? dev.getAsesor() : dev.getUsuarioSolicitud() )
						.replace("--codigoBpmDevolucion--",  dev.getCodigo());
				this.qos.mailNotificacion(array.toArray(new String[]{}) , asunto, contenido, null);
			}else {
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_CANCELACION_DEVOLUCION_RECHAZO).getValor();
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_CANCELACION_DEVOLUCION_RECHAZO).getValor()
						.replace("--nombreAsesor--", dev.getAsesor() != null ? dev.getAsesor() : dev.getUsuarioSolicitud() )
						.replace("--codigoBpmDevolucion--",  dev.getCodigo());
				this.qos.mailNotificacion(array.toArray(new String[]{}) , asunto, contenido, null);
			}		
		} catch (RelativeException e) {
			e.printStackTrace();
			log.info("ERROR ========>" + QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getCause());
			//throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
		}
	}

	private void bloquear(TbQoProceso proceso, TbQoDevolucion devolucion, String tipoBloqueo,Boolean esBloqueo, String autorizacion) throws RelativeException {
		BloqueoWrapper bloqueo = new BloqueoWrapper();
		bloqueo.setCodigoMotivoBloqueo(parametroRepository.findByNombre(tipoBloqueo).getValor());
		bloqueo.setCodigoUsuario(proceso.getUsuario());
		bloqueo.setEsBloqueo(esBloqueo);
		bloqueo.setIdentificacion(devolucion.getCedulaCliente());
		bloqueo.setIdTipoIdentificacion(Long.valueOf("1"));
		bloqueo.setNumeroOperacion(devolucion.getCodigoOperacion());
		bloqueo.setReferenciaBpm(devolucion.getCodigo());
		SoftBankApiClient.procesarBloqueo(bloqueo,parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_PROCESAR_BLOQUEO).getValor(), autorizacion );
	}

	public List<DevolucionProcesoWrapper> findOperacion(PaginatedWrapper pw, String codigoOperacion, String agencia,
			String fechaAprobacionDesde, String fechaAprobacionHasta, String identificacion) throws RelativeException {

		try {
			List<DevolucionProcesoWrapper> actions = this.devolucionRepository.findOperaciones(pw, codigoOperacion,
					agencia, fechaAprobacionDesde, fechaAprobacionHasta, identificacion);

			log.info("la lista" + actions);
			return actions;
		} catch (RelativeException e) {

			e.printStackTrace();
			throw e;
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}

	public Integer countOperacion(String codigoOperacion, String agencia, String fechaAprobacionDesde,
			String fechaAprobacionHasta, String identificacion) throws RelativeException {

		return devolucionRepository.countOperaciones(codigoOperacion, agencia, fechaAprobacionDesde,
				fechaAprobacionHasta, identificacion);
	}

	public List<DevolucionPendienteArribosWrapper> findOperacionArribo(PaginatedWrapper pw, String codigoOperacion,
			Long agencia) throws RelativeException {
		try {
			List<DevolucionPendienteArribosWrapper> actions = this.devolucionRepository.findOperacionArribo(pw,
					codigoOperacion, agencia, EstadoProcesoEnum.PENDIENTE_ARRIBO);
			log.info("la lista" + actions);
			return actions;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public Integer countOperacionArribo(String codigoOperacion, Long agencia) throws RelativeException {

		return devolucionRepository.countOperacionArribo(codigoOperacion, agencia, EstadoProcesoEnum.PENDIENTE_ARRIBO);
	}

	public List<TbQoDevolucion> registrarFechaArribo(RegistroFechaArriboWrapper rfaw) throws RelativeException {
		try {
			List<TbQoDevolucion> devoluciones = new ArrayList<>();
			for (Long id : rfaw.getIdDevoluciones()) {
				
				TbQoDevolucion devolucion = devolucionRepository.findById(id);
				if( devolucion == null) {
					throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION RELACIONADO A ESTE ID: " + id );
				}
				TbQoProceso proceso = this.qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
				/*if( proceso == null || !proceso.getEstadoProceso().equals( EstadoProcesoEnum.PENDIENTE_FECHA) ) {
					throw new RelativeException(" EL PROCESO: " + devolucion.getCodigo()+" NO SE ENCUENTRA EL ESTADO CORRECTO. ESTADO ACTUAL: "+ proceso.getEstadoProceso() );
				}*/
				if (devolucion.getFechaArribo() == null && proceso != null && proceso.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_FECHA)) {
					devolucion.setFechaArribo(QuskiOroUtil.formatSringToDate(rfaw.getFechaArribo()));
					qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_ARRIBO, QuskiOroConstantes.EN_COLA);
					TbQoTracking traking = new TbQoTracking();
					traking.setActividad("PENDIENTE_DE_ARRIBO");
					traking.setCodigoBpm(devolucion.getCodigo());
					traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
					traking.setEstado(EstadoEnum.ACT);
					traking.setFechaActualizacion(new Date());
					traking.setFechaCreacion(new Date());
					traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
					traking.setNombreAsesor(devolucion.getAsesor());
				//	traking.setUsuarioCreacion(devolucion.getAsesor());
				//	traking.setObservacion(observacionAsesor);
					traking.setProceso(ProcesoEnum.DEVOLUCION);
					traking.setSeccion("PENDIENTE_DE_ARRIBO");
					qos.registrarTraking(traking);
					devolucion = manageDevolucion(devolucion);
					devoluciones.add(devolucion);
			
					this.mailFechaArribo(devolucion, proceso);
					this.mailFechaArriboCliente(devolucion, proceso);
				}
			}
			return devoluciones;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public List<TbQoDevolucion> registrarArriboAgencia(List<Long> idDevoluciones, String asesor, String autorizacion) throws RelativeException {
		try {
			List<TbQoDevolucion> devoluciones = new ArrayList<>();
			for (Long id : idDevoluciones) {
				
				TbQoDevolucion devolucion = devolucionRepository.findById(id);
				qos.reasignarOperacion(id, ProcesoEnum.DEVOLUCION, asesor);
				if( devolucion == null) {
					throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION RELACIONADO A ESTE ID: " + id );
				}
				TbQoProceso proceso = this.qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
				if( proceso == null || !proceso.getEstadoProceso().equals( EstadoProcesoEnum.PENDIENTE_ARRIBO) ) {
					throw new RelativeException(" EL PROCESO: " + devolucion.getCodigo()+" NO SE ENCUENTRA EL ESTADO CORRECTO. ESTADO ACTUAL: "+ proceso.getEstadoProceso() );
				}
				if ( devolucion.getArribo() == null ) {
					qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.ARRIBADO, devolucion.getAsesor() );
					devolucion.setArribo(Boolean.TRUE);
					devolucion.setFechaEfectiva(new Timestamp(System.currentTimeMillis()));
					TbQoTracking traking = new TbQoTracking();
					traking.setActividad("ARRIBADO_A_AGENCIA");
					traking.setCodigoBpm(devolucion.getCodigo());
					traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
					traking.setEstado(EstadoEnum.ACT);
					traking.setFechaActualizacion(new Date());
					traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
					traking.setNombreAsesor(devolucion.getAsesor());
					traking.setUsuarioCreacion(asesor);
				//	traking.setObservacion(observacionAsesor);
					traking.setProceso(ProcesoEnum.DEVOLUCION);
					traking.setSeccion("ARRIBADO_A_AGENCIA");
					qos.registrarTraking(traking);
					
					devolucion = manageDevolucion(devolucion);
					devoluciones.add(devolucion);
					bloquear(proceso, devolucion, QuskiOroConstantes.CODIGO_BLOQUEO_B, Boolean.TRUE, autorizacion);
					
				}
			}
			return devoluciones;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public TbQoProceso iniciarProcesoCancelacion(Long id, String usuario,  String motivo ) throws RelativeException {
		try {
			TbQoProceso procesoDevolucion = qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
			TbQoDevolucion devolucion = devolucionRepository.findById(id); 
			TbQoProceso procesoCancelacion = qos.findProcesoByIdReferencia(id, ProcesoEnum.CANCELACION_DEVOLUCION);
			if(devolucion == null || procesoDevolucion == null || 
					procesoDevolucion.getEstadoProceso() == EstadoProcesoEnum.APROBADO || 
					procesoDevolucion.getEstadoProceso() == EstadoProcesoEnum.RECHAZADO ||
					procesoDevolucion.getEstadoProceso() == EstadoProcesoEnum.CANCELADO 
					) {
				throw new RelativeException( "NO EXISTE PROCESO DE DEVOLUCION ACTIVO.");
			}
			if( !devolucion.getAsesor().equalsIgnoreCase( usuario )) {
				throw new RelativeException( "NO TIENES PERMITIDO CANCELAR EL PROCESO, CONTACTE CON EL ASESOR RESPONSABLE DEL PROCESO.");
			}
			if( procesoCancelacion != null && procesoCancelacion.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION) ) {
				throw new RelativeException( " YA EXISTE UN PROCESO DE CANCELACION DE DEVOLUCION ACTIVO: "+ devolucion.getCodigo() );
			}
			procesoCancelacion = new TbQoProceso();
			procesoCancelacion.setIdReferencia(devolucion.getId());
			procesoCancelacion.setProceso(ProcesoEnum.CANCELACION_DEVOLUCION);
			procesoCancelacion.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
			procesoCancelacion.setUsuario(QuskiOroConstantes.EN_COLA);
			procesoCancelacion = this.qos.manageProceso(procesoCancelacion);
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("CANCELACION_DEVOLUCION_PENDIENTE_APROBACION");
			traking.setCodigoBpm(devolucion.getCodigo());
			traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(devolucion.getAsesor());
			traking.setUsuarioCreacion(devolucion.getAsesor());
			traking.setObservacion(motivo);
			traking.setProceso(ProcesoEnum.CANCELACION_DEVOLUCION);
			traking.setSeccion("CANCELACION_DEVOLUCION_PENDIENTE_APROBACION");
			qos.registrarTraking(traking);
			
			devolucion.setObservacionCancelacion(motivo);
			this.manageDevolucion(devolucion);
			return procesoCancelacion;
		} catch ( RelativeException e ) {
			e.printStackTrace();
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}

	public TbQoDevolucion mandarAprobarSolicitudDevolucion(Long id, String usuario) throws RelativeException {
		TbQoDevolucion devolucion = devolucionRepository.findById(id);
		qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_APROBACION, QuskiOroConstantes.EN_COLA);
		this.manageDevolucion(devolucion);
		return devolucion;

	}

	public TbQoProceso aprobarCancelacionSolicitudDevolucion(Long id, String usuario, String autorizacion) throws RelativeException {
		try {
			TbQoProceso procesoDevolucion = qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
			TbQoDevolucion devolucion = devolucionRepository.findById(id); 
			if(devolucion == null || procesoDevolucion == null) {
				throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION PARA ESTE CREDITO. " );
			}
			if(procesoDevolucion == null || procesoDevolucion.getEstadoProceso() == EstadoProcesoEnum.RECHAZADO || 
					procesoDevolucion.getEstadoProceso() == EstadoProcesoEnum.APROBADO || 
					procesoDevolucion.getEstadoProceso() == EstadoProcesoEnum.CANCELADO ) {
				throw new RelativeException( "EL PROCESO DE DEVOLUCION YA FUE FINALIZADO, NO SE PUEDE REALIZAR LA CANCELACION.");
			}
			TbQoProceso procesoCancelacion = this.qos.findProcesoByIdReferencia(id,  ProcesoEnum.CANCELACION_DEVOLUCION );
			if(procesoCancelacion == null || procesoCancelacion.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION ) {
				throw new RelativeException( "EL PROCESO DE CANCELACION NO SE ENCUENTRA EN ESTADO PENDIENTE DE APROBACION.");
			}
			RespuestaConsultaGlobalWrapper bloqueo = SoftBankApiClient.callConsultarOperacionRest(new ConsultaOperacionGlobalWrapper(devolucion.getCodigoOperacion()), autorizacion,this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GLOBAL).getValor());
			if(bloqueo != null && bloqueo.getOperaciones()  != null && !bloqueo.getOperaciones().isEmpty() && bloqueo.getOperaciones().get(0).getDatosBloqueo() != null ) {
				procesoDevolucion.setUsuario(usuario);
				bloquear(procesoDevolucion, devolucion, bloqueo.getOperaciones().get(0).getDatosBloqueo().getCodigoMotivoBloqueo() ,Boolean.FALSE, autorizacion);
				qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.CANCELADO, usuario);
				this.notificarCancelacionDevolucion(Boolean.TRUE, devolucion);
				TbQoProceso pro = qos.cambiarEstado(id, ProcesoEnum.CANCELACION_DEVOLUCION, EstadoProcesoEnum.APROBADO, usuario);
				TbQoTracking traking = new TbQoTracking();
				traking.setActividad("APROBADO_CANCELACION_DEVOLUCION");
				traking.setCodigoBpm(devolucion.getCodigo());
				traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
				traking.setEstado(EstadoEnum.ACT);
				traking.setFechaActualizacion(new Date());
				traking.setFechaCreacion(new Date());
				traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				traking.setNombreAsesor(devolucion.getAsesor());
				traking.setUsuarioCreacion(usuario);
			//	traking.setObservacion(observacionAsesor);
				traking.setProceso(ProcesoEnum.CANCELACION_DEVOLUCION);
				traking.setSeccion("APROBADO_CANCELACION_DEVOLUCION");
				qos.registrarTraking(traking);
				return pro;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL BLOQUEO");
			}
			
		} catch ( RelativeException e ) {
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	public TbQoProceso rechazarCancelacionSolicitudDevolucion(Long id, String usuario) throws RelativeException {
		try {
			TbQoProceso procesoDevolucion = qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
			TbQoDevolucion devolucion = devolucionRepository.findById(id); 
			if(devolucion == null || procesoDevolucion == null) {
				throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION PARA ESTE CREDITO. " );
			}

			TbQoProceso procesoCancelacion = this.qos.findProcesoByIdReferencia(id,  ProcesoEnum.CANCELACION_DEVOLUCION );
			if(procesoCancelacion == null || procesoCancelacion.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION ) {
				throw new RelativeException( "EL PROCESO DE CANCELACION NO SE ENCUENTRA EN ESTADO PENDIENTE DE APROBACION.");
			}
			procesoDevolucion.setUsuario(usuario);
			//bloquear(procesoDevolucion, devolucion, QuskiOroConstantes.CODIGO_BLOQUEO_D,Boolean.FALSE);
			this.notificarCancelacionDevolucion(Boolean.FALSE, devolucion);
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("RECHAZADO_CANCELACION_DEVOLUCION");
			traking.setCodigoBpm(devolucion.getCodigo());
			traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(devolucion.getAsesor());
			traking.setUsuarioCreacion(usuario);
		//	traking.setObservacion(observacionAsesor);
			traking.setProceso(ProcesoEnum.CANCELACION_DEVOLUCION);
			traking.setSeccion("RECHAZADO_CANCELACION_DEVOLUCION");
			qos.registrarTraking(traking);
			return qos.cambiarEstado(id, ProcesoEnum.CANCELACION_DEVOLUCION, EstadoProcesoEnum.RECHAZADO, null);
		} catch ( RelativeException e ) {
			e.printStackTrace();
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
		
	}

	public TbQoDevolucion guardarEntregaRecepcion(Long id) throws RelativeException {
		try {
			TbQoProceso procesoDevolucion = qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
			TbQoDevolucion devolucion = devolucionRepository.findById(id); 
			if(devolucion == null || procesoDevolucion == null) {
				throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION PARA ESTE CREDITO. " );
			}
			if( !procesoDevolucion.getEstadoProceso().equals( EstadoProcesoEnum.ARRIBADO ) ) {
				throw new RelativeException(" EL PROCESO: " + devolucion.getCodigo()+" NO SE ENCUENTRA EL ESTADO CORRECTO. ESTADO ACTUAL: "+ procesoDevolucion.getEstadoProceso() );
			}
			qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA, QuskiOroConstantes.EN_COLA);
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("ENVIADO A VERIFICACION DE FIRMAS");
			traking.setCodigoBpm(devolucion.getCodigo());
			traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(devolucion.getAsesor());
			traking.setUsuarioCreacion(devolucion.getAsesor());
		//	traking.setObservacion(observacionAsesor);
			traking.setProceso(ProcesoEnum.DEVOLUCION);
			traking.setSeccion("ENVIADO A VERIFICACION DE FIRMAS");
			qos.registrarTraking(traking);

			return this.manageDevolucion(devolucion);
		} catch ( RelativeException e ) {
			e.printStackTrace();
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	public TbQoDevolucion aprobarVerificacionFirmas(Long id, String autorizacion, String motivo, String usuario) throws RelativeException {
		try {
			TbQoProceso procesoDevolucion = qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
			TbQoProceso cancelar = qos.findProcesoByIdReferencia(id, ProcesoEnum.CANCELACION_DEVOLUCION);
			TbQoDevolucion devolucion = devolucionRepository.findById(id); 
			if(devolucion == null || procesoDevolucion == null) {
				throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION PARA ESTE CREDITO. " );
			}
			if( !procesoDevolucion.getEstadoProceso().equals( EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA ) ) {
				throw new RelativeException(" EL PROCESO: " + devolucion.getCodigo()+" NO SE ENCUENTRA EL ESTADO CORRECTO. ESTADO ACTUAL: "+ procesoDevolucion.getEstadoProceso() );
			}
			if(cancelar != null) {
				this.qos.cambiarEstado(id, ProcesoEnum.CANCELACION_DEVOLUCION, EstadoProcesoEnum.RECHAZADO , null);
			}
			this.qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.APROBADO , null);
			devolucion.setFechaEntrega(new Date());
			devolucion.setObservacionAprobador(motivo);
			devolucion = this.manageDevolucion(devolucion);
			bloquear(procesoDevolucion, devolucion, QuskiOroConstantes.CODIGO_BLOQUEO_C, Boolean.TRUE, autorizacion);
			this.qos.guardaraObservacionEntrega(devolucion.getObservacionAprobador(), BigDecimal.valueOf(devolucion.getId()), usuario);
			this.notificarDevolucionVerificacionFirmas(Boolean.TRUE, devolucion);
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("APROBADO VERIFICACION DE FIRMAS");
			traking.setCodigoBpm(devolucion.getCodigo());
			traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(devolucion.getAsesor());
			traking.setUsuarioCreacion(usuario);
		//	traking.setObservacion(observacionAsesor);
			traking.setProceso(ProcesoEnum.DEVOLUCION);
			traking.setSeccion("APROBADO  VERIFICACION DE FIRMAS");
			qos.registrarTraking(traking);
			return devolucion;
		} catch ( RelativeException e ) {
			e.printStackTrace();
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	public TbQoDevolucion rechazarVerificacionFirmas(Long id, String motivo, String usuario) throws RelativeException {
		try {
			TbQoProceso procesoDevolucion = qos.findProcesoByIdReferencia(id, ProcesoEnum.DEVOLUCION);
			TbQoDevolucion devolucion = devolucionRepository.findById(id); 
			if(devolucion == null || procesoDevolucion == null) {
				throw new RelativeException(" NO EXISTE PROCESO DE DEVOLUCION PARA ESTE CREDITO. " );
			}
			if( !procesoDevolucion.getEstadoProceso().equals( EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA ) ) {
				throw new RelativeException(" EL PROCESO: " + devolucion.getCodigo()+" NO SE ENCUENTRA EL ESTADO CORRECTO. ESTADO ACTUAL: "+ procesoDevolucion.getEstadoProceso() );
			}
			qos.cambiarEstado(id, ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.ARRIBADO, devolucion.getAsesor() );
			devolucion.setDevuelto(true);
			devolucion.setObservacionAprobador(motivo);
			devolucion =  this.manageDevolucion(devolucion);
			this.qos.guardaraObservacionEntrega(devolucion.getObservacionAprobador(), BigDecimal.valueOf(devolucion.getId()), usuario);
			this.notificarDevolucionVerificacionFirmas(Boolean.FALSE, devolucion);
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("RECHAZADO_VERIFICACION_DE_FIRMAS");
			traking.setCodigoBpm(devolucion.getCodigo());
			traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(devolucion.getAsesor());
			traking.setUsuarioCreacion(usuario);
		//	traking.setObservacion(observacionAsesor);
			traking.setProceso(ProcesoEnum.DEVOLUCION);
			traking.setSeccion("RECHAZADO_VERIFICACION_DE_FIRMAS");
			qos.registrarTraking(traking);
			return devolucion;
		} catch ( RelativeException e ) {
			e.printStackTrace();
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	/**
	 * Validaciones
	 */
	public Boolean existeProcesoCancelacionVigente(Long idDevolucion) throws RelativeException {
		try {
			TbQoProceso procesoCancelacion = this.qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.CANCELACION_DEVOLUCION);
			if ( procesoCancelacion != null) {
				if (procesoCancelacion.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public RespuestaBooleanaWrapper validateCancelacionSolicitud(Long idDevolucion) throws RelativeException {
		RespuestaBooleanaWrapper respuesta = new RespuestaBooleanaWrapper();
		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION);
			if (this.existeProcesoCancelacionVigente(idDevolucion)) {
				respuesta.setBandera(false);
				respuesta.setMensaje("YA EXISTE UN PROCESO DE CANCELACION");
				return respuesta;
			} else {
				if (persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION)
						|| persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_ARRIBO)
						|| persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_FECHA)
						|| persisted.getEstadoProceso().equals(EstadoProcesoEnum.ARRIBADO)) {
					respuesta.setBandera(true);
					respuesta.setMensaje("ES POSIBLE REALIZAR LA CANCELACION");
					return respuesta;
				} else {
					respuesta.setBandera(false);
					respuesta.setMensaje(
							"NO ES FACTIBLE REALIZAR LA CANCELACION EN ESTADO " + persisted.getEstadoProceso());
					return respuesta;

				}
			}

		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
		}
	}

	public Boolean validateAprobarCancelacionSolicitud(Long idDevolucion) throws RelativeException {

		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.CANCELACION_DEVOLUCION);

			if (persisted.getProceso().equals(ProcesoEnum.CANCELACION_DEVOLUCION)
					&& persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION)) {
				return true;
			} else {
				return false;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	public TbQoProceso validateSolicitarAprobacion(Long idDevolucion, String usuario, String autorizacion) throws RelativeException {
		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION);

			if (persisted.getEstadoProceso().equals(EstadoProcesoEnum.CREADO)) {
				TbQoProceso pro = this.qos.cambiarEstado( Long.valueOf( idDevolucion ), ProcesoEnum.DEVOLUCION, EstadoProcesoEnum.PENDIENTE_APROBACION, QuskiOroConstantes.EN_COLA);
				TbQoDocumentoHabilitante doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("9"),
						ProcessEnum.SOLICITUD, String.valueOf(idDevolucion));
				if(doc == null || StringUtils.isBlank(doc.getObjectId())) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS FIRMADOS");
				}
				pro.setUsuario(usuario);
				TbQoDevolucion devolucion =  this.findDevolucionById(idDevolucion);
				this.qos.guardaraObservacionEntrega(devolucion.getObservaciones(), BigDecimal.valueOf(devolucion.getId()), devolucion.getAsesor());
				bloquear(pro, devolucion,QuskiOroConstantes.CODIGO_BLOQUEO_F, Boolean.TRUE, autorizacion);
				this.mailSolicitudEntrega(devolucion,pro );
				TbQoTracking traking = new TbQoTracking();
				traking.setActividad("ENVIADO_A_APROBAR_SOLICITUD_DEVOLUCION");
				traking.setCodigoBpm(devolucion.getCodigo());
				traking.setCodigoOperacionSoftbank(devolucion.getCodigoOperacion());
				traking.setEstado(EstadoEnum.ACT);
				traking.setFechaActualizacion(new Date());
				traking.setFechaCreacion(new Date());
				traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				//traking.setNombreAsesor(nombreAsesor);
				traking.setUsuarioCreacion(devolucion.getAsesor());
			//	traking.setObservacion(observacionAsesor);
				traking.setProceso(ProcesoEnum.DEVOLUCION);
				traking.setSeccion("ENVIADO_A_APROBAR_SOLICITUD_DEVOLUCION");
				qos.registrarTraking(traking);
				return pro;
			} else {
				throw new RelativeException("EL PROCESO DE DEVOLUCION NO SE ENCUENTRA EN EL ESTADO REQUERIDO. ESTADO ACTUAL: " + persisted.getEstadoProceso());
			}
		}catch ( RelativeException e ) {
			e.printStackTrace();
			throw e;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}


	public RespuestaBooleanaWrapper validateAprobarRechazarSolicitud(Long idDevolucion, String usuario) throws RelativeException {
		RespuestaBooleanaWrapper respuesta = new RespuestaBooleanaWrapper();
		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION);

			if (persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION)) {
				respuesta.setBandera(true);
				respuesta.setMensaje("ES FACTIBLE REALIZAR EL PROCESO");
				return respuesta;
			} else {
				respuesta.setBandera(false);
				respuesta.setMensaje("NO ES FACTIBLE REALIZAR LA ACCION EN ESTADO " + persisted.getEstadoProceso());
				return respuesta;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	public RespuestaBooleanaWrapper validateEntregaRecepcion(Long idDevolucion) throws RelativeException {
		RespuestaBooleanaWrapper respuesta = new RespuestaBooleanaWrapper();
		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION);

			if (persisted.getEstadoProceso().equals(EstadoProcesoEnum.ARRIBADO)) {
				respuesta.setBandera(true);
				respuesta.setMensaje("ES FACTIBLE REALIZAR EL PROCESO");
				return respuesta;
			} else {
				respuesta.setBandera(false);
				respuesta.setMensaje("NO ES FACTIBLE REALIZAR GUARDAR EN ESTADO " + persisted.getEstadoProceso());
				return respuesta;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	public RespuestaBooleanaWrapper validateVerificacionFirma(Long idDevolucion) throws RelativeException {
		RespuestaBooleanaWrapper respuesta = new RespuestaBooleanaWrapper();
		try {
			TbQoProceso persisted = qos.findProcesoByIdReferencia(idDevolucion, ProcesoEnum.DEVOLUCION);

			if (persisted.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA)) {
				respuesta.setBandera(true);
				respuesta.setMensaje("ES FACTIBLE REALIZAR EL PROCESO");
				return respuesta;
			} else {
				respuesta.setBandera(false);
				respuesta.setMensaje("NO ES FACTIBLE REALIZAR LA ACCION EN ESTADO " + persisted.getEstadoProceso());
				return respuesta;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	/**
	 * HABILITANTES devolucion
	 */

	public HabilitanteTerminacionContratoWrapper setHabilitanteTerminacionContrato(Long idDevolucion, String nombreAsesor)
			throws RelativeException {

		HabilitanteTerminacionContratoWrapper habilitante = new HabilitanteTerminacionContratoWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);

		habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
		log.info("entra yal metodo habilitante" + habilitante.getNombreCompletoCliente());
//	habilitante.setFechaElaboracionContrato(QuskiOroUtil.dateToFullString(devolucion.getFechaEfectiva()));
		log.info("entra yal metodo habilitante" + habilitante.getNombreCompletoCliente());
		habilitante.setFechaActual(QuskiOroUtil.dateToCompletelyFullString(new Date()));
		habilitante.setApoderadoMutualista(parametroRepository.findByNombre(QuskiOroConstantes.APODERADO_MUPI_DEVOLUCION).getValor());
	//	habilitante.setNombreUsuario("Quemaado hasta mientras");
	//	habilitante.setRolUsuario("rolUsuario");
		habilitante.setCedulaCliente(devolucion.getCedulaCliente());
		return habilitante;
	}

	public ActaEntregaRecepcionWrapper setHabilitanteActaEntrega(Long idDevolucion, String nombreAsesor) throws RelativeException {

		ActaEntregaRecepcionWrapper habilitante = new ActaEntregaRecepcionWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		habilitante.setCiudad(devolucion.getCiudadEntrega());
		habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
		habilitante.setFechaDevolucion(QuskiOroUtil.dateToFullString(new Date()));
		habilitante.setNumeroFunda(devolucion.getFundaActual());
		habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
		habilitante.setNombreAsesor(nombreAsesor);
		habilitante.setCedulaCliente(
				StringUtils.isNotBlank(devolucion.getCedulaCliente()) ? devolucion.getCedulaCliente() : "");

		return habilitante;
	}

	public ActaEntregaRecepcionApoderadoWrapper setHabilitanteActaEntregaApoderado(Long idDevolucion, String nombreAsesor)
			throws RelativeException {

		ActaEntregaRecepcionApoderadoWrapper habilitante = new ActaEntregaRecepcionApoderadoWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		habilitante.setCiudad(devolucion.getCiudadEntrega());
		habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
		habilitante.setFechaDevolucion(QuskiOroUtil.dateToFullString(new Date()));
		habilitante.setNumeroFunda(devolucion.getFundaActual());
		habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
		habilitante.setNombreAsesor(nombreAsesor);
		habilitante.setCedulaCliente(devolucion.getCedulaCliente());
		habilitante.setCedulaApoderado(devolucion.getCedulaApoderado());
		habilitante.setNombreApoderado(devolucion.getNombreApoderado());
		return habilitante;
	}

	public ActaEntregaRecepcionHerederoWrapper setHabilitanteActaEntregaHeredero(Long idDevolucion, String nombreAsesor)
			throws RelativeException {

		ActaEntregaRecepcionHerederoWrapper habilitante = new ActaEntregaRecepcionHerederoWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		habilitante.setCiudad(devolucion.getCiudadEntrega());
		habilitante.setNombreCompletoCliente(devolucion.getNombreCliente());
		habilitante.setFechaDevolucion(QuskiOroUtil.dateToFullString(new Date()));
		habilitante.setNumeroFunda(devolucion.getFundaActual());
		habilitante.setCedulaCliente(devolucion.getCedulaCliente());
		habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
		habilitante.setNombreAsesor(nombreAsesor);
		habilitante.setHerederos((setStringHeredero(getHerederos(idDevolucion))));
		return habilitante;
	}

	public List<HerederoWrapper> getHerederos(Long idDevolucion) throws RelativeException {

		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		String herederos = devolucion.getCodeHerederos();
		String decodedUrl = new String(Base64.getDecoder().decode(herederos));
		Gson gsons = new GsonBuilder().create();
		ListHerederoWrapper listHeredero = gsons.fromJson(decodedUrl, ListHerederoWrapper.class);

		return listHeredero.getHeredero();
	}
	
	public List<HerederoWrapper> getHerederos(TbQoDevolucion devolucion) throws RelativeException {

		String herederos = devolucion.getCodeHerederos();
		String decodedUrl = new String(Base64.getDecoder().decode(herederos));
		Gson gsons = new GsonBuilder().create();
		ListHerederoWrapper listHeredero = gsons.fromJson(decodedUrl, ListHerederoWrapper.class);

		return listHeredero.getHeredero();
	}

	public List<HerederoConsolidadoWrapper> setListaHerederosString(Long idDevolucion) throws RelativeException{
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		 List<HerederoWrapper> herederos = this.getHerederos(devolucion);
		 List<HerederoConsolidadoWrapper> herederosList = new ArrayList<HerederoConsolidadoWrapper>();
		//log.info("HEREDEROS ¡¡¡¡¡¡¡¡¡¡¡¡¡¡:::::::::::::::::::::::::::::::::::::::::::");
		for (HerederoWrapper h : herederos) {
			HerederoConsolidadoWrapper heredero = new HerederoConsolidadoWrapper();
			heredero.setCampoCompleto("SR.(A) ".concat(h.getNombre().concat("\n ").concat("C.I.").concat(h.getCedula()).concat("\n\n").concat("Heredero (a) del señor (a) \n").
					concat(devolucion.getNombreCliente())));
			//log.info(heredero.getCampoCompleto());
		herederosList.add(heredero);
		} 
		
		
		return herederosList;
	}
	
	public static String setStringHeredero(List<HerederoWrapper> herederos) throws RelativeException {
		String respuestaHerederos = "";
		for (HerederoWrapper h : herederos) {

			if (h.equals(herederos.get(0))) {

				respuestaHerederos = respuestaHerederos.concat(h.getNombre()  + ", con cédula de ciudadanía No. " + h.getCedula());
			} else {
				if (h.equals(herederos.get(herederos.size() - 1))) {
					respuestaHerederos = respuestaHerederos.concat(" y " +h.getNombre()  + ", con cédula de ciudadanía No. " + h.getCedula() );
				} else {
					respuestaHerederos = respuestaHerederos.concat(", " + h.getNombre() + ", con cédula de ciudadanía No. " + h.getCedula());
				}
			}

		}
		System.out.println(respuestaHerederos);
		return respuestaHerederos;

	}

	public SolicitudDevolucionWrapper setHabilitanteSolicitudDevolucion(Long idDevolucion, String nombreAsesor) throws RelativeException {

		SolicitudDevolucionWrapper habilitante = new SolicitudDevolucionWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		habilitante.setAgenciaEntrega(devolucion.getAgenciaEntrega());
		habilitante.setFechaSolicitud(QuskiOroUtil.dateToString(new Date(),QuskiOroUtil.DATE_FORMAT_SOFTBANK));
		habilitante.setAgenciaSolicitante(devolucion.getNombreAgenciaSolicitud());
		habilitante.setNumeroFunda(devolucion.getFundaActual());
		habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
		habilitante.setAsesor(nombreAsesor);
		habilitante.setNombreCliente(devolucion.getNombreCliente());
		habilitante.setCedulaCliente(devolucion.getCedulaCliente());

		return habilitante;
	}

	public SolicitudDevolucionApoderadoWrapper setHabilitanteSolicitudDevolucionApoderado(Long idDevolucion, String nombreAsesor)
			throws RelativeException {

		SolicitudDevolucionApoderadoWrapper habilitante = new SolicitudDevolucionApoderadoWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		habilitante.setAgenciaEntrega(devolucion.getAgenciaEntrega());
		habilitante.setFechaSolicitud(QuskiOroUtil.dateToString(new Date(),QuskiOroUtil.DATE_FORMAT_SOFTBANK));
		habilitante.setAgenciaSolicitante(devolucion.getNombreAgenciaSolicitud());
		habilitante.setNumeroFunda(devolucion.getFundaActual());
		habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
		habilitante.setAsesor(nombreAsesor);
		habilitante.setNombreCliente(devolucion.getNombreCliente());
		habilitante.setCedulaCliente(devolucion.getCedulaCliente());
		habilitante.setNombreApoderado(devolucion.getNombreApoderado());
		habilitante.setCedulaApoderado(devolucion.getCedulaApoderado());

		return habilitante;
	}

	public SolicitudDevolucionHerederoWrapper setHabilitanteSolicitudDevolucionHeredero(Long idDevolucion, String nombreAsesor)
			throws RelativeException {

		SolicitudDevolucionHerederoWrapper habilitante = new SolicitudDevolucionHerederoWrapper();
		TbQoDevolucion devolucion = devolucionRepository.findById(idDevolucion);
		habilitante.setAgenciaEntrega(devolucion.getAgenciaEntrega());
		habilitante.setFechaSolicitud(QuskiOroUtil.dateToString(new Date(),QuskiOroUtil.DATE_FORMAT_SOFTBANK));
		habilitante.setAgenciaSolicitante(devolucion.getNombreAgenciaSolicitud());
		habilitante.setNumeroFunda(devolucion.getFundaActual());
		habilitante.setNumeroOperacion(devolucion.getCodigoOperacion());
		habilitante.setAsesor(nombreAsesor);
		habilitante.setNombreCliente(devolucion.getNombreCliente());
		habilitante.setCedulaCliente(devolucion.getCedulaCliente());
		habilitante.setHeredero(setStringHeredero(getHerederos(idDevolucion)));

		return habilitante;
	}


	private void mailSolicitudEntrega(TbQoDevolucion devolucion, TbQoProceso proceso) throws RelativeException {
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_CORREO_SOLICITUD_DE_GARANTIA).getValor();
		String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_SOLICITUD_DE_GARANTIA).getValor();
		
		asunto = asunto
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getNombreCliente())?devolucion.getNombreCliente() : "")
				.replace("--cedulaCliente--", StringUtils.isNotBlank(devolucion.getCedulaCliente())?devolucion.getCedulaCliente() : "")
				.replace("--codigoBPM--", StringUtils.isNotBlank(devolucion.getCodigo())?devolucion.getCodigo() : "")
				.replace("--numeroOperacion--", StringUtils.isNotBlank(devolucion.getCodigoOperacion())?devolucion.getCodigoOperacion() : "")
				.replace("--flujoCredito--", proceso.getProceso().toString())
				.replace("--operacionNovada--", StringUtils.isNotBlank(devolucion.getCodigoOperacionMadre())?devolucion.getCodigoOperacionMadre() : "");
		textoContenido = textoContenido
				.replace("--codigoBPM--", StringUtils.isNotBlank(devolucion.getCodigo())?devolucion.getCodigo() : "")
				.replace("--numeroOperacion--", StringUtils.isNotBlank(devolucion.getCodigoOperacion())?devolucion.getCodigoOperacion() : "")
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getNombreCliente())?devolucion.getNombreCliente() : "")
				.replace("--asesor--", StringUtils.isNotBlank(devolucion.getNombreAsesor())?devolucion.getNombreAsesor() : "")
				.replace("--monto--", String.valueOf(devolucion.getMontoCredito().doubleValue()))
				.replace("--plazo--",  StringUtils.isNotBlank(devolucion.getPlazoCredito())?devolucion.getPlazoCredito() :"" )
				.replace("--observacion--", StringUtils.isNotBlank(devolucion.getObservaciones())?devolucion.getObservaciones() :"" );
		String[] para = Stream.of(this.parametroRepository.findByNombre(QuskiOroConstantes.MAIL_SOLICITUD_ENTREGA).getValor()).toArray(String[]::new);
		
		this.qos.mailNotificacion(para, asunto, textoContenido, null);
		
	}
	
	private void mailFechaArribo(TbQoDevolucion devolucion, TbQoProceso proceso) throws RelativeException {
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_CORREO_DE_NOTIFICACION_FECHA_DE_ARRIBO).getValor();
		String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_DE_FECHA_DE_ARRIBO_ASESOR).getValor();
		
		asunto = asunto
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getNombreCliente())?devolucion.getNombreCliente() : "")
				.replace("--cedulaCliente--", StringUtils.isNotBlank(devolucion.getCedulaCliente())?devolucion.getCedulaCliente() : "")
				.replace("--codigoBPM--", StringUtils.isNotBlank(devolucion.getCodigo())?devolucion.getCodigo() : "")
				.replace("--numeroOperacion--", StringUtils.isNotBlank(devolucion.getCodigoOperacion())?devolucion.getCodigoOperacion() : "");
		textoContenido = textoContenido
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getCodigo())?devolucion.getCodigo() : "")
				.replace("--asesor--", StringUtils.isNotBlank(devolucion.getNombreAsesor())?devolucion.getNombreAsesor() : "")
				.replace("--fechaArribo--", devolucion.getFechaArribo() != null ?QuskiOroUtil.dateToString(devolucion.getFechaArribo(), QuskiOroUtil.DATE_FORMAT_QUSKI) : "")
				.replace("--agencia--", StringUtils.isNotBlank(devolucion.getAgenciaEntrega())?devolucion.getAgenciaEntrega() : "")
				.replace("--observacionAsesor--",  StringUtils.isNotBlank(devolucion.getObservaciones())?devolucion.getObservaciones() :"" )
				.replace("--observacionAprobador--", StringUtils.isNotBlank(devolucion.getObservacionAprobador())?devolucion.getObservacionAprobador() :"" );
		String[] para = Stream.of(devolucion.getCorreoAsesor()).toArray(String[]::new);
		
		this.qos.mailNotificacion(para, asunto, textoContenido, null);
		
	}
	
	private void mailFechaArriboCliente(TbQoDevolucion devolucion, TbQoProceso proceso) throws RelativeException {
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_CORREO_NOTIFICACION_FECHA_ARRIBO_CLIENTE).getValor();
		String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_NOTIFICACION_FECHA_ARRIBO_CLIENTE).getValor();
		
		asunto = asunto
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getNombreCliente())?devolucion.getNombreCliente() : "")
				.replace("--cedulaCliente--", StringUtils.isNotBlank(devolucion.getCedulaCliente())?devolucion.getCedulaCliente() : "");
		textoContenido = textoContenido
				.replace("--agencia--", StringUtils.isNotBlank(devolucion.getAgenciaEntrega())?devolucion.getAgenciaEntrega() : "")
				.replace("--fechaEntrega--", devolucion.getFechaArribo() != null ?QuskiOroUtil.dateToString(devolucion.getFechaArribo(), QuskiOroUtil.DATE_FORMAT_QUSKI) : "");
		String[] para = Stream.of(devolucion.getCorreoCliente()).toArray(String[]::new);
		
		this.qos.mailNotificacion(para, asunto, textoContenido, null);
		
	}
	
	private void mailSolicitudEntregaNegada(TbQoDevolucion devolucion, TbQoProceso proceso) throws RelativeException {
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_SOLICITUD_DEVOLUCION_GARANTIA_DEVUELTA).getValor();
		String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_SOLICITUD_DEVOLUCION_DE_GARANTIA_DEVUELTA).getValor();
		
		asunto = asunto
				.replace("--statusAprobada--", proceso.getEstadoProceso().toString())
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getNombreCliente())?devolucion.getNombreCliente() : "")
				.replace("--cedulaCliente--", StringUtils.isNotBlank(devolucion.getCedulaCliente())?devolucion.getCedulaCliente() : "")
				.replace("--codigoBPM--", StringUtils.isNotBlank(devolucion.getCodigo())?devolucion.getCodigo() : "")
				.replace("--numeroOperacion--", StringUtils.isNotBlank(devolucion.getCodigoOperacion())?devolucion.getCodigoOperacion() : "");
		textoContenido = textoContenido
				.replace("--codigoBPM--", StringUtils.isNotBlank(devolucion.getCodigo())?devolucion.getCodigo() : "")
				.replace("--numeroOperacion--", StringUtils.isNotBlank(devolucion.getCodigoOperacion())?devolucion.getCodigoOperacion() : "")
				.replace("--nombreCliente--", StringUtils.isNotBlank(devolucion.getNombreCliente())?devolucion.getNombreCliente() : "")
				.replace("--asesor--", StringUtils.isNotBlank(devolucion.getNombreAsesor())?devolucion.getNombreAsesor() : "")
				.replace("--observacionesAsesor--", StringUtils.isNotBlank(devolucion.getObservaciones())?devolucion.getObservaciones() :"" )
				.replace("--observacionesAprobador--", StringUtils.isNotBlank(devolucion.getObservacionAprobador())?devolucion.getObservacionAprobador() :"" )
				.replace("--observacionAprobador--", StringUtils.isNotBlank(devolucion.getObservacionAprobador())?devolucion.getObservacionAprobador() :"" );
		String[] para = Stream.of(devolucion.getCorreoAsesor()).toArray(String[]::new);
		
		this.qos.mailNotificacion(para, asunto, textoContenido, null);
		
	}
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<DevolucionReporteWrapper> findDevolucionReporte(PaginatedWrapper pw,
			DevolucionParamsWrapper wp) throws RelativeException {
		if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.devolucionRepository.findDevolucionReporte(pw.getStartRecord(), pw.getPageSize(),pw.getSortFields(), pw.getSortDirections(),wp);
		} else {
			return this.devolucionRepository.findDevolucionReporte(wp);
		}
		
	}

	public Integer countDevolucionReporte(DevolucionParamsWrapper wp) throws RelativeException {
		return this.devolucionRepository.countDevolucionReporte(wp);
	}

	public ObjetoHabilitanteWrapper descargarReporte(DevolucionParamsWrapper wp, String autorizacion) throws RelativeException {
		
		try {
			byte[] reportFile = null;
			Map<String, Object> map = new HashMap<>();
			String path= this.parametroRepository.findByNombre(QuskiOroConstantes.PATH_REPORTE).getValor();
			path = path+ "ReporteEntregaGarantiasNew.jasper";
			map.put("mainReportName", "");
			map.put("REPORT_PATH", path );
			List<EntregaGarantiasReporteWrapper> list = this.setDataReporte(wp,autorizacion);
			map.put("LIST_DS",list );
			ObjetoHabilitanteWrapper ohw = new ObjetoHabilitanteWrapper();
			reportFile = this.rs.generateReporteBeanExcel(list,map, path );
			ohw.setDocumentoHabilitanteByte(reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
			
			return ohw;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	private List<EntregaGarantiasReporteWrapper>  setDataReporte(DevolucionParamsWrapper wp, String autorizacion) throws RelativeException {
		List<DevolucionReporteWrapper> list = this.devolucionRepository.findDevolucionReporte(wp);
		List<EntregaGarantiasReporteWrapper> result = new ArrayList<>();
		String service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_AGENCIA).getValor();
		List<CatalogoAgenciaWrapper> agencias= SoftBankApiClient.callCatalogoAgenciaRest( service, autorizacion );
		if(list != null && !list.isEmpty() && agencias != null && !agencias.isEmpty()) {
			log.info("=========>=========>MAP TO REPORTE ENTREGA con mapear agencias");
			result = list.stream().map(i -> new EntregaGarantiasReporteWrapper(i.getCodigoOperacion() ,  i.getCodigoBpm(),  i.getNombreCliente(),
					i.getCedulaCliente(),  i.getEstadoProceso(), 
					i.getIgAgenciaEntrega() != null?   agencias.stream().filter(p-> p.getId() == i.getIgAgenciaEntrega().longValue() ).findFirst().get().getNombre():"",
					i.getIdAgencia() != null? agencias.stream().filter(p-> p.getId() == i.getIdAgencia().longValue() ).findFirst().get().getNombre():"",
					i.getFechaCreacion(), i.getFechaArribo(),  i.getFechaEngrega()))
		    .collect(Collectors.toList());
		} else if (list != null && !list.isEmpty()) {
			
			log.info("=========>=========>MAP TO REPORTE ENTREGA sin agencias");
			result = list.stream().map(i -> new EntregaGarantiasReporteWrapper(i.getCodigoOperacion() ,  i.getCodigoBpm(),  i.getNombreCliente(),
					i.getCedulaCliente(),  i.getEstadoProceso(),  i.getIgAgenciaEntrega() != null?   i.getIgAgenciaEntrega().toString():"",  i.getIdAgencia() != null? i.getIdAgencia().toString():"",
							i.getFechaCreacion(), i.getFechaArribo(),  i.getFechaEngrega()))
		    .collect(Collectors.toList());
		
		}
		
		return result;
	}

}
