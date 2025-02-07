package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.olap4j.impl.ArrayMap;

import com.google.gson.Gson;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.bpms.api.ApiGatewayClient;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.ClientePagoRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AbonoWrapper;
import com.relative.quski.wrapper.CrearRenovacionWrapper;
import com.relative.quski.wrapper.FileObjectStorage;
import com.relative.quski.wrapper.InicioProcesoBloqueoWrapper;
import com.relative.quski.wrapper.InicioProcesoPagoWrapper;
import com.relative.quski.wrapper.PagosNovacionSoftWrapper;
import com.relative.quski.wrapper.RegistroPagoRenovacionWrapper;
import com.relative.quski.wrapper.ResponsePagoMupi;
import com.relative.quski.wrapper.ResponseWebMupi;
import com.relative.quski.wrapper.RespuestaAbonoWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;
import com.relative.quski.wrapper.RespuestaProcesoPagoBloqueoWrapper;

@Stateless
public class PagoService {
	@Inject
	Logger log;
	@Inject
	private QuskiOroService qos;
	@Inject
	private RegistrarPagoRepository registrarPagoRepository;
	@Inject
	private ClientePagoRepository clientePagoRepository;
	@Inject
	private ParametroRepository parametroRepository;
	
	public RespuestaProcesoPagoBloqueoWrapper crearRegistrarPago(InicioProcesoPagoWrapper wrapper, String autorizacion	)throws RelativeException {
		try {
			if (wrapper.getPagos() == null || wrapper.getPagos().size() < 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DE LOS PAGOS.");
			}
			TbQoClientePago clienteCast = new TbQoClientePago();
			clienteCast.setCedula( wrapper.getCedula() );
			clienteCast.setCodigoOperacion( wrapper.getNumeroOperacion() );
			clienteCast.setNombreCliente( wrapper.getNombreCompleto() );
			clienteCast.setObservacion( wrapper.getObservacion() );
			clienteCast.setAsesor( wrapper.getAsesor() );
			clienteCast.setUsuarioCreacion( wrapper.getAsesor() );
			clienteCast.setIdAgencia( wrapper.getAgencia() );
			clienteCast.setTipo( "PAGO" );
			clienteCast.setTipoCredito( wrapper.getTipoCredito() );
			clienteCast.setValorDepositado( wrapper.getValorDepositado() );
			clienteCast.setValorPrecancelado( wrapper.getValorPrecancelado() );
			clienteCast.setCodigoCuentaMupi( String.valueOf( wrapper.getIdBanco() ));
			clienteCast.setTipoPagoProceso( wrapper.getTipoPagoProceso() );
			clienteCast.setMailAsesor(wrapper.getMailAsesor());
			clienteCast.setMontoCredito(wrapper.getMontoCredito());
			clienteCast.setPlazoCredito(wrapper.getPlazoCredito());
			clienteCast.setNumeroCuentaCliente(wrapper.getNumeroCuentaCliente());
			clienteCast.setNombreAsesor(wrapper.getNombreAsesor());
			clienteCast.setCodigoOperacionMupi(wrapper.getCodigoOperacionMupi());
			clienteCast = qos.manageClientePago( clienteCast );
			RespuestaProcesoPagoBloqueoWrapper result = new RespuestaProcesoPagoBloqueoWrapper();
			result.setCliente( clienteCast );
			result.setProceso( qos.createProcesoPago( clienteCast.getId(), QuskiOroConstantes.EN_COLA ) );
			if(result.getProceso() == null) {
				throw new RelativeException( QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
			List<TbQoRegistrarPago> pagos = new ArrayList<>();
			for (RegistroPagoRenovacionWrapper e : wrapper.getPagos()) {
				TbQoRegistrarPago pago = new TbQoRegistrarPago();
				if(e.getComprobante() != null && StringUtils.isNotEmpty(e.getComprobante().getFileBase64())) {
					FileObjectStorage file = new FileObjectStorage();
					file.setFileBase64(e.getComprobante().getFileBase64());
					file.setName( e.getComprobante().getName() );
					file.setProcess(EstadoEnum.ACT);
					RespuestaObjectWrapper objeto;
					objeto = LocalStorageClient.createObjectBig(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
							parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
							parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),file, autorizacion );					
					pago.setIdComprobante(objeto.getEntidad());					
				}				
				pago.setCuentas(e.getCuenta());
				pago.setTipoPago( e.getTipoPago() );
				pago.setFechaPago(e.getFechaPago());
				pago.setInstitucionFinanciera(e.getIntitucionFinanciera());
				pago.setNumeroDeposito( e.getNumeroDeposito() );
				pago.setValorPagado(e.getValorDepositado());
				pago.setEstado(EstadoEnum.ACT);
				pago.setTbQoClientePago(clienteCast);
				pago.setUsuarioCreacion( clienteCast.getAsesor() );
				pagos.add( qos.manageRegistrarPago(pago) ); 
			}
			
		
			
			//registrar Traking
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("SOLICITUD PAGO");
			traking.setCodigoBpm(clienteCast.getCodigo());
			traking.setCodigoOperacionSoftbank(clienteCast.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(clienteCast.getNombreAsesor());
			traking.setUsuarioCreacion(clienteCast.getAsesor());
			traking.setObservacion(clienteCast.getObservacion());
			traking.setProceso(ProcesoEnum.PAGO);
			traking.setSeccion("ENVIADO A APROBAR");
			
			this.qos.registrarTraking(traking);
			
			TbQoTracking trakinga = new TbQoTracking();
			trakinga.setActividad("RESPUESTA PAGO");
			trakinga.setCodigoBpm(clienteCast.getCodigo());
			trakinga.setCodigoOperacionSoftbank(clienteCast.getCodigoOperacion());
			trakinga.setEstado(EstadoEnum.ACT);
			trakinga.setFechaActualizacion(new Date());
			trakinga.setFechaCreacion(new Date());
			trakinga.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			trakinga.setNombreAsesor(clienteCast.getNombreAsesor());
			trakinga.setUsuarioCreacion(clienteCast.getAsesor());
			trakinga.setObservacion(clienteCast.getObservacion());
			trakinga.setProceso(ProcesoEnum.PAGO);
			trakinga.setSeccion("PENDIENTE APROBACION");
			
			this.qos.registrarTraking(trakinga);
		 	this.enviarCorreoSolicitudPago( clienteCast);
			result.setPagos( pagos );
			
			
			
			return result;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException( QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION, " EN METODO, crearRegistrarPago FALLO. ");
		}
	}
	
	public RespuestaProcesoPagoBloqueoWrapper crearRegistrarBloqueo(InicioProcesoBloqueoWrapper wrapper, String autorizacion )throws RelativeException {
		try {
			if (wrapper.getPagos() == null || wrapper.getPagos().size() < 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DE LOS PAGOS.");
			}
			TbQoClientePago clienteCast = new TbQoClientePago();
			clienteCast.setCedula( wrapper.getCedula() );
			clienteCast.setCodigoOperacion( "NO APLICA" );
			clienteCast.setNombreCliente( wrapper.getNombreCompleto() );
			clienteCast.setObservacion( wrapper.getObservacion() );
			clienteCast.setAsesor( wrapper.getAsesor() );
			clienteCast.setUsuarioCreacion( wrapper.getAsesor() );
			clienteCast.setIdAgencia( wrapper.getAgencia() );
			clienteCast.setTipo( "BLOQ" );
			clienteCast.setTipoCredito( "NO APLICA" );
			clienteCast.setValorDepositado( wrapper.getValorDepositado() );
			clienteCast.setValorPrecancelado( null );
			clienteCast.setCodigoCuentaMupi( String.valueOf( wrapper.getIdBanco() ));
			clienteCast.setMailAsesor(wrapper.getMailAsesor());
			clienteCast.setMontoCredito(wrapper.getMontoCredito());
			clienteCast.setPlazoCredito(wrapper.getPlazoCredito());
			clienteCast.setNumeroCuentaCliente(wrapper.getNumeroCuentaCliente());
			clienteCast.setNombreAsesor(wrapper.getNombreAsesor());
			clienteCast = qos.manageClientePago( clienteCast );
			RespuestaProcesoPagoBloqueoWrapper result = new RespuestaProcesoPagoBloqueoWrapper();
			result.setCliente( clienteCast );
			result.setProceso( qos.createProcesoPago( clienteCast.getId(), QuskiOroConstantes.EN_COLA) );
			if(result.getProceso() == null) {
				throw new RelativeException( QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
			List<TbQoRegistrarPago> pagos = new ArrayList<>();
			for (RegistroPagoRenovacionWrapper e : wrapper.getPagos()) {

				TbQoRegistrarPago pago = new TbQoRegistrarPago();
				
				if(e.getComprobante() != null && StringUtils.isNotBlank(e.getComprobante().getFileBase64())) {
					FileObjectStorage file = new FileObjectStorage();
					file.setFileBase64(e.getComprobante().getFileBase64());
					file.setName( e.getComprobante().getName() );
					file.setProcess(EstadoEnum.ACT);			
					RespuestaObjectWrapper objeto;
					objeto = LocalStorageClient.createObjectBig(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
							parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
							parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),file, autorizacion );
					pago.setIdComprobante(objeto.getEntidad());		
				}
							
				pago.setCuentas(e.getCuenta());
				pago.setFechaPago(e.getFechaPago());
				pago.setInstitucionFinanciera(e.getIntitucionFinanciera());
				pago.setNumeroDeposito(e.getNumeroDeposito());
				pago.setValorPagado(e.getValorDepositado());
				pago.setEstado(EstadoEnum.ACT);
				pago.setTbQoClientePago(clienteCast);
				pago.setUsuarioCreacion( clienteCast.getAsesor() );
				pagos.add( qos.manageRegistrarPago(pago) ); 
			}		
			this.enviarCorreoSolicitudPago( clienteCast);
			result.setPagos( pagos );
			return result;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException( QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION, " EN METODO, crearRegistrarBloqueo FALLO. ");
		}
	}
	public List<PagosNovacionSoftWrapper> crearRegistrarComprobanteRenovacion(CrearRenovacionWrapper registro)throws RelativeException, UnsupportedEncodingException {
		try {
			if (registro == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL CREDITO");
			}
			if (registro.getPagos() == null || registro.getPagos().isEmpty()) {
				return null;
			}
			List<PagosNovacionSoftWrapper> pagosNovacion = new ArrayList<>();
			for(RegistroPagoRenovacionWrapper e : registro.getPagos()){
				PagosNovacionSoftWrapper pagoNovacion = new PagosNovacionSoftWrapper();
				pagoNovacion.setNumeroDocumento( e.getNumeroDeposito() );
				pagoNovacion.setFechaPago( QuskiOroUtil.dateToString(e.getFechaPago(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));
				pagoNovacion.setValor( e.getValorDepositado() );
				pagosNovacion.add( pagoNovacion ); 
				TbQoRegistrarPago pago = new TbQoRegistrarPago();
//				if(e.getComprobante() != null) {
//					FileObjectStorage file = new FileObjectStorage();
//					file.setFileBase64(e.getComprobante().getFileBase64());
//					file.setName( e.getComprobante().getName() );
//					file.setProcess(EstadoEnum.ACT);
//					RespuestaObjectWrapper objeto;
//					String url = parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor();
//					String base = parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor();
//					String coleccion = parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor();
//					objeto = LocalStorageClient.createObject(url,base,coleccion,file, null );
//					pago.setIdComprobante(objeto.getEntidad());
//				}
				pago.setTipoPago(e.getTipoPago());
				pago.setCuentas(e.getCuenta());
				pago.setFechaPago(e.getFechaPago());
				pago.setInstitucionFinanciera(e.getIntitucionFinanciera());
				pago.setNumeroDeposito(e.getNumeroDeposito());
				pago.setValorPagado(e.getValorDepositado());
				pago.setEstado(EstadoEnum.ACT);
				pago.setTbQoCreditoNegociacion( this.qos.findCreditoNegociacionById( registro.getCredito().getId() ));
				pago.setUsuarioCreacion( registro.getAsesor() );
				pago.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				qos.manageRegistrarPago(pago);
			}
			return pagosNovacion;
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch( Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	public String getQueryParametersNotificacion(String hash, String tipo, String idUsuario,
			List<FileObjectStorage> notificacion) throws RelativeException, UnsupportedEncodingException {
		StringBuilder params = new StringBuilder();
		Gson gson = new Gson();
		String jsonString = gson.toJson(notificacion);
		byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
		String encodedString = Base64.getEncoder().encodeToString(content);
		// log.info("=====> DE NOTIFICACION " + encodedString);
		params.append("?");
		params.append("databaseName").append("=")
				.append(parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor());
		params.append("&&");
		params.append("collectionName").append("=")
				.append(parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor());
		params.append("&&");
		params.append("hash").append("=").append(hash);
		params.append("&&");
		params.append("tipo").append("=").append(tipo);
		params.append("&&");
		params.append("idUsuario").append("=").append(idUsuario);
		params.append("&&");
		params.append("topicType").append("=").append("PRODUCER");
		params.append("&&");
		params.append("objectEncripted").append("=").append(encodedString);
		return params.toString();
	}

	

	public List<TbQoRegistrarPago> findRegistrarPagoByIdClientePago(Long id) throws RelativeException {
		// TODO Auto-generated method stub
		return registrarPagoRepository.findByIdClientePago(id);
	}

	public List<TbQoClientePago> findClientePagoByIdClientePago(Long id) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public TbQoProceso aprobarPago(Long id, Boolean isRegistro, String nombreAprobador, String mailAprobador, Double valorAprobador, String observacionAprobador, String autorizacion) throws RelativeException {
		TbQoClientePago clientePago = null;
		TbQoProceso proceso = null;
		try {
			
			clientePago = clientePagoRepository.findById(id);
			if(clientePago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN ESTADO PENDIENTE");
			}
			clientePago.setUsuarioActualizacion(nombreAprobador);
			clientePago.setAprobador(nombreAprobador);
			clientePago.setObservacionAprobador(observacionAprobador);
			if(valorAprobador != null) {
				clientePago.setValorDepositado( BigDecimal.valueOf( valorAprobador ));
			}
			clientePago = qos.manageClientePago(clientePago);
			if(isRegistro) {
				AbonoWrapper abono = new AbonoWrapper( 
						clientePago.getCodigoOperacion(), 
						clientePago.getCodigo(), 
						clientePago.getObservacion(),
						clientePago.getIdAgencia().toString(),
						clientePago.getAsesor(),
						clientePago.getCedula(),
						clientePago.getNombreCliente(),
						clientePago.getValorDepositado(),
						QuskiOroUtil.dateToString(clientePago.getFechaCreacion(), QuskiOroUtil.DATE_FORMAT_SOFTBANK)
						);
				RespuestaAbonoWrapper abonoRespuesta = this.qos.aplicarAbono( abono, autorizacion );
				if(abonoRespuesta == null || abonoRespuesta.getExisteError()) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL REGISTRAR EL PAGO EN SOFTBANK.");
				}
			}
			proceso = qos.cambiarEstado( clientePago.getId(), ProcesoEnum.PAGO, EstadoProcesoEnum.APROBADO, null);
			if(proceso == null) {
				throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION+" EL PROCESO.");
			}
			this.enviarCorreoPagoAprobado(isRegistro, clientePago, clientePago.getMailAsesor());
			//registrar Traking
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("RESPUESTA PAGO");
			traking.setCodigoBpm(clientePago.getCodigo());
			traking.setCodigoOperacionSoftbank(clientePago.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(clientePago.getNombreAsesor());
			traking.setUsuarioCreacion(clientePago.getAsesor());
			traking.setObservacion(clientePago.getObservacionAprobador());
			traking.setProceso(ProcesoEnum.PAGO);
			traking.setTiempoTranscurrido(new Long (0));
			traking.setSeccion("APROBADO");
			traking.setFechaFin(new Timestamp(System.currentTimeMillis()));
			this.qos.registrarTraking(traking);
			return proceso;
		} catch (RelativeException e) {
			//e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
		}
	}
	private void enviarCorreoPagoAprobado(Boolean isRegistro, TbQoClientePago clientePago, String mailAsesor) throws RelativeException {
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_SOLICITUD_DE_PAGO).getValor();
		asunto =  asunto
				.replace("--tipoPago--", isRegistro?"PAGO": "BLOQUEO")
				.replace("--statusAprobada--", EstadoProcesoEnum.APROBADO.toString())
				.replace("--nombreCliente--", clientePago.getNombreCliente())
				.replace("--cedulaCliente--", clientePago.getCedula())
				.replace("--codigoBPM--", clientePago.getCodigo())
				.replace("--numeroOperacion--", clientePago.getCodigoOperacion());
		if(isRegistro) { //pago
			String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_PAGO).getValor();
			textoContenido=textoContenido
					.replace("--codigoBPM--",clientePago.getCodigo())
					.replace("--numeroOperacion--", clientePago.getCodigoOperacion())
					.replace("--nombreCliente--", clientePago.getNombreCliente())
					.replace("--asesor--", clientePago.getNombreAsesor())
					.replace("--valorPago--", String.valueOf(clientePago.getValorDepositado().doubleValue()) )
					.replace("--observacionAsesor--", clientePago.getObservacion())
					.replace("--observacionAprobador--", StringUtils.isNotBlank(clientePago.getObservacionAprobador())? clientePago.getObservacionAprobador() : " ");
			String[] para= {mailAsesor};
			qos.mailNotificacion(para, asunto, textoContenido, null);
		}else { 	//bloqueo
			String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_BLOQUEO).getValor();
			textoContenido=textoContenido
					.replace("--codigoBPM--",clientePago.getCodigo())
					.replace("--numeroOperacion--", clientePago.getCodigoOperacion())
					.replace("--nombreCliente--", clientePago.getNombreCliente())
					.replace("--asesor--", clientePago.getNombreAsesor())
					.replace("--valorPago--", String.valueOf(clientePago.getValorDepositado().doubleValue()) )
					.replace("--observacionAsesor--", clientePago.getObservacion())
					.replace("--observacionAprobador--", StringUtils.isNotBlank(clientePago.getObservacionAprobador())? clientePago.getObservacionAprobador() : " ");
			ArrayMap<java.lang.String,byte[]> adjunto = new ArrayMap<java.lang.String,byte[]>();
			String[] para= {mailAsesor};
			log.info("CONTENIDO ENVIA "+para+"--"+asunto+"--"+textoContenido+"--"+adjunto);
			qos.mailNotificacion(para, asunto, textoContenido, null);	
		}
	}

	public TbQoProceso rechazarPago(Long id, Boolean isRegistro, String nombreAprobador, String observacionAprobador) throws RelativeException {
		try {
			TbQoClientePago clientePago = clientePagoRepository.findById(id);
			if(clientePago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN ESTADO PENDIENTE");
			}
			clientePago.setUsuarioActualizacion(nombreAprobador);
			clientePago.setAprobador(nombreAprobador);
			clientePago.setEstado(EstadoEnum.ACT);
			clientePago.setObservacionAprobador(observacionAprobador);
			TbQoProceso proceso = qos.cambiarEstado( clientePago.getId(),  ProcesoEnum.PAGO, EstadoProcesoEnum.RECHAZADO, null);
			if(proceso == null) {
				throw new RelativeException( QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
			}
			TbQoClientePago rPago = qos.manageClientePago(clientePago);
			String[] para= {clientePago.getMailAsesor()};
			String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_SOLICITUD_DE_PAGO).getValor();
			asunto =  asunto
					.replace("--tipoPago--", isRegistro?"PAGO": "BLOQUEO")
					.replace("--statusAprobada--", EstadoProcesoEnum.RECHAZADO.toString())
					.replace("--nombreCliente--", clientePago.getNombreCliente())
					.replace("--cedulaCliente--", clientePago.getCedula())
					.replace("--codigoBPM--", clientePago.getCodigo())
					.replace("--numeroOperacion--", clientePago.getCodigoOperacion());
			if(isRegistro) { // pago
				String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_PAGO).getValor();
				textoContenido=textoContenido
					.replace("--codigoBPM--",clientePago.getCodigo())
					.replace("--numeroOperacion--", clientePago.getCodigoOperacion())
					.replace("--nombreCliente--", clientePago.getNombreCliente())
					.replace("--asesor--", clientePago.getAsesor())
					.replace("--valorPago--", String.valueOf(clientePago.getValorDepositado().doubleValue()) )
					.replace("--observacionAsesor--", clientePago.getObservacion())
					.replace("--observacionAprobador--", StringUtils.isNotBlank(observacionAprobador)? observacionAprobador : " ");
				
				
				qos.mailNotificacion(para, asunto, textoContenido, null);
				} else{ 	//bloqueo
					String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_BLOQUEO).getValor();
					textoContenido=textoContenido
							.replace("--codigoBPM--",clientePago.getCodigo())
							.replace("--numeroOperacion--", clientePago.getCodigoOperacion())
							.replace("--nombreCliente--", clientePago.getNombreCliente())
							.replace("--asesor--", clientePago.getAsesor())
							.replace("--observacionAsesor--", clientePago.getObservacion())
							.replace("--observacionAprobador--", StringUtils.isNotBlank(observacionAprobador)? observacionAprobador : " ");
					qos.mailNotificacion(para, asunto, textoContenido, null);
				}	
			//registrar Traking
			TbQoTracking traking = new TbQoTracking();
			traking.setActividad("RESPUESTA PAGO");
			traking.setCodigoBpm(clientePago.getCodigo());
			traking.setCodigoOperacionSoftbank(clientePago.getCodigoOperacion());
			traking.setEstado(EstadoEnum.ACT);
			traking.setFechaActualizacion(new Date());
			traking.setFechaCreacion(new Date());
			traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
			traking.setNombreAsesor(clientePago.getNombreAsesor());
			traking.setUsuarioCreacion(clientePago.getAsesor());
			traking.setObservacion(clientePago.getObservacionAprobador());
			traking.setProceso(ProcesoEnum.PAGO);
			traking.setTiempoTranscurrido(new Long (0));
			traking.setSeccion("RECHAZADO");
			traking.setFechaFin(new Timestamp(System.currentTimeMillis()));
			this.qos.registrarTraking(traking);
			return proceso;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
		}
		
	}

	
	/**
	 * metodo para enviar mail al solicitar pago
	*/

	private void enviarCorreoSolicitudPago( TbQoClientePago clientePago) throws RelativeException {
	
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_SOLICITUD_DE_PAGO).getValor();
		asunto =  asunto
				.replace("--nombreCliente--", clientePago.getNombreCliente())
				.replace("--cedulaCliente--", clientePago.getCedula())
				.replace("--codigoBPM--", clientePago.getCodigo())
				.replace("--numeroOperacion--", StringUtils.isNotBlank( clientePago.getCodigoOperacionMupi()) ? clientePago.getCodigoOperacionMupi() : clientePago.getCodigoOperacion() )
				.replace("--tipoPago--", clientePago.getTipoPagoProceso())
				.replace("--statusAprobada--", "SOLICITUD");
		String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_SOLICITUD_DE_PAGO).getValor();
		textoContenido=textoContenido
				.replace("--codigoBPM--", clientePago.getCodigo())
				.replace("--numeroOperacion--", StringUtils.isNotBlank(clientePago.getCodigoOperacionMupi())? clientePago.getCodigoOperacionMupi() : clientePago.getCodigoOperacion())
				.replace("--nombreCliente--", clientePago.getNombreCliente())
				.replace("--cedulaCliente--", clientePago.getCedula())
				.replace("--asesor--", clientePago.getAsesor())
				.replace("--valorPago--", clientePago.getValorDepositado().toString())
				.replace("--observacionesAsesor--", clientePago.getObservacion());

		String[] para= null;
		para = Stream.of(this.parametroRepository.findByNombre(QuskiOroConstantes.MAIL_SOLICITUD_CREDITO).getValor()).toArray(String[]::new);
		qos.mailNotificacion(para, asunto, textoContenido, null);
	
	
	} 
	/**
	 * @param clienteCast 
	 * @param wrapper
	 * @param autorizacion
	 * @param pagos
	 * @return 
	 * @throws RelativeException 
	 */
	public TbQoProceso aplicarPago(TbQoClientePago clienteCast, String autorizacion, List<TbQoRegistrarPago> pagos) throws RelativeException {
		
			Double valorDepositado =  pagos.stream().map(TbQoRegistrarPago::getValorPagado).mapToDouble(p->p.doubleValue()).sum(); 
			List<ResponsePagoMupi> resp=new ArrayList<>();
			for(TbQoRegistrarPago pago : pagos) {
				resp.add(this.aplicarPago(clienteCast.getCodigoOperacion(), QuskiOroConstantes.SECUENCIAL_BOT_PAGOS, pago.getNumeroDeposito(), pago.getValorPagado(), autorizacion));	
				
			}
			
			if(resp.stream().anyMatch(response -> response.getExisteError() || !response.getPagoAplicado())) {
				return null;
			}
			return aprobarPago(clienteCast.getId(), true, QuskiOroConstantes.NOMBRE_BOT_PAGOS, this.parametroRepository.findByNombre(QuskiOroConstantes.MAIL_SOLICITUD_CREDITO).getValor(),
					valorDepositado,"PAGO APLICADO", autorizacion);
			
		
	}
	
	public ResponsePagoMupi aplicarPago(String numeroPrestamo, String secuencial, String numeroTransaccion , BigDecimal valorDepositado,String autorizacion) throws RelativeException{
		
		return ApiGatewayClient.aplicarPago(numeroPrestamo, secuencial,numeroTransaccion,valorDepositado,autorizacion,
				this.parametroRepository.findByNombre(QuskiOroConstantes.URL_RUN_SERVER_WEB_MUPI_PAGO_ROBOT).getValor());
		
		
	}
	
}
