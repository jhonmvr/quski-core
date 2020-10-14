package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.olap4j.impl.ArrayMap;

import com.google.gson.Gson;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.ClientePagoRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.FileLocalStorage;
import com.relative.quski.wrapper.RegistrarBloqueoFondoWrapper;
import com.relative.quski.wrapper.RegistrarPagoWrapper;
import com.relative.quski.wrapper.RegistroBloqueoFondoWrapper;
import com.relative.quski.wrapper.RegistroPagoWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;

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
	
	public RegistrarPagoWrapper crearRegistrarPago(RegistrarPagoWrapper registroPago, String autorizacion )
			throws RelativeException, UnsupportedEncodingException {
		try {
			if (registroPago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL PAGO");
			}
			if (registroPago.getCliente() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE PAGO");
			}

			TbQoClientePago cliente = qos.manageClientePago(registroPago.getCliente());
			cliente.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			cliente.setEstado(EstadoEnum.PENDIENTE_APROBACION);
			log.info(" ID de registro cliente: >>>>> " + cliente.getId());
			
			if (registroPago.getPagos() != null && !registroPago.getPagos().isEmpty()) {
				
				for (RegistroPagoWrapper registro : registroPago.getPagos()) {
					FileLocalStorage file = new FileLocalStorage();
					file.setFileBase64(registro.getArchivo());
					file.setName(registro.getNombreArchivo());
					file.setProcess(EstadoEnum.ACT);
					RespuestaObjectWrapper objeto = LocalStorageClient.createObject(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE)
							.getValor().concat("?databaseName=").concat("testrest&").concat("collectionName=").concat("documento-habilitante"),file,
							autorizacion);
					
					TbQoRegistrarPago pago = new TbQoRegistrarPago();
					pago.setCuentas(registro.getCuentas());
					pago.setFechaPago(registro.getFechaPago());
					pago.setInstitucionFinanciera(registro.getInstitucionFinanciera());
					pago.setNumeroDeposito(registro.getNumeroDeposito());
					pago.setValorPagado(registro.getValorPagado());
					pago.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					pago.setEstado(EstadoEnum.ACT);
					pago.setIdComprobante(objeto.getEntidad());
					pago.setTbQoClientePago(cliente);
					registro.setArchivo(objeto.getEntidad());
					qos.manageRegistrarPago(pago);
				}
			}
			
			registroPago.setCliente(cliente);
			return registroPago;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	// REGISTRO BLOQUEO DE FONDOS EN LA TABLA REGISTRAR PAGO 
	public RegistrarBloqueoFondoWrapper bloqueoFondo(RegistrarBloqueoFondoWrapper bloqueoFondo, String autorizacion, byte[] bs)
			throws RelativeException, UnsupportedEncodingException {
		try {
			if (bloqueoFondo == null) {
				throw new RelativeException( QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION+ "DEL PAGO");
			}
			if (bloqueoFondo.getCliente() == null) {
				throw new RelativeException( QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION+
						" DEL CLIENTE PAGO");
			}
			
			TbQoClientePago cliente = qos.manageClientePago(bloqueoFondo.getCliente());
			cliente.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			cliente.setEstado(EstadoEnum.PENDIENTE_APROBACION);
			log.info(" ID de registro cliente: >>>>> " + cliente.getId());
			
			if (bloqueoFondo.getBloqueos() != null && !bloqueoFondo.getBloqueos().isEmpty()) {
				
				for (RegistroBloqueoFondoWrapper registro : bloqueoFondo.getBloqueos()) {
					List<FileLocalStorage> listFile = new ArrayList<FileLocalStorage>();
					FileLocalStorage file = new FileLocalStorage();
					file.setFileBase64(registro.getArchivo());
					file.setName(registro.getNombreArchivo());
					file.setProcess(EstadoEnum.ACT);
					LocalStorageClient.createObject(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE)
							.getValor().concat("?databaseName=").concat("testrest&").concat("collectionName=").concat("documento-habilitante"),file,
							autorizacion);
					
					TbQoRegistrarPago bloqueo = new TbQoRegistrarPago();
					bloqueo.setCuentas(registro.getCuentas());
					bloqueo.setFechaPago(registro.getFechaPago());
					bloqueo.setInstitucionFinanciera(registro.getInstitucionFinanciera());
					bloqueo.setNumeroDeposito(registro.getNumeroDeposito());
					bloqueo.setValorPagado(registro.getValorPagado());
					bloqueo.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					bloqueo.setEstado(EstadoEnum.ACT);
					bloqueo.setTbQoClientePago(cliente);
					qos.manageRegistrarPago(bloqueo);
					
				}
			}
			
			bloqueoFondo.setCliente(cliente);
			return bloqueoFondo;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public String getQueryParametersNotificacion(String hash, String tipo, String idUsuario,
			List<FileLocalStorage> notificacion) throws RelativeException, UnsupportedEncodingException {
		StringBuilder params = new StringBuilder();
		Gson gson = new Gson();
		String jsonString = gson.toJson(notificacion);
		byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
		String encodedString = Base64.getEncoder().encodeToString(content);
		// log.info("=====> DE NOTIFICACION " + encodedString);
		params.append("?");
		params.append("databaseName").append("=")
				.append(parametroRepository.findByNombre(QuskiOroConstantes.databaseName).getValor());
		params.append("&&");
		params.append("collectionName").append("=")
				.append(parametroRepository.findByNombre(QuskiOroConstantes.collectionName).getValor());
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

	
	public TbQoClientePago aprobarPago(Long id, String tipo, String nombreAprobador, String mailAprobador) throws RelativeException {
		try {
			TbQoClientePago clientePago = clientePagoRepository.findByIdAndEstado(id, EstadoEnum.PENDIENTE_APROBACION, tipo);
			if(clientePago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN ESTADO PENDIENTE");
			}
			log.info("Aprobador ---->>>>"+ nombreAprobador);
			clientePago.setUsuarioActualizacion(nombreAprobador);
			clientePago.setEstado(EstadoEnum.APROBADO);
			TbQoClientePago rPago = qos.manageClientePago(clientePago);
			try {
				log.info("TIPO DE REGISTRO : ----->>>>"+tipo);
				if(tipo.equalsIgnoreCase("REGISTRO_PAGO")) { 
				String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_PAGO).getValor();
				textoContenido=textoContenido.replace("--Nombre Asesor--", "Nombre asesor").replace("--Estado Pago--", EstadoEnum.APROBADO.toString())
						.replace("--tipo--", "Pago").replace("--Nombre Cliente--", clientePago.getNombreCliente())
						.replace("--Identificacion Cliente--", clientePago.getCedula())
						.replace("--Cuenta Mupi--", clientePago.getCodigoCuentaMupi()).replace("--Operacion--", clientePago.getCodigoOperacion())
						.replace("--Fecha AprobacionRechazo--", QuskiOroUtil.dateToFullString( rPago.getFechaActualizacion()));
				ArrayMap<java.lang.String,byte[]> adjunto = new ArrayMap<java.lang.String,byte[]>();//= (LocalStorageClient);
				String[] para= {"hoscarly007@gmail.com"};//reemplazar el mailAprobador,
				
				String asunto ="Aprobacion de solictud "+ EstadoEnum.APROBADO.toString(); 
				log.info("CONTENIDO ENVIA "+para+"--"+asunto+"--"+textoContenido+"--"+adjunto);
				qos.mailNotificacion(para, asunto, textoContenido, null);
				}else if(tipo.equalsIgnoreCase("BLOQUEO_FONDO")) { 
						
						String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_PAGO).getValor();
						textoContenido=textoContenido.replace("--Nombre Asesor--", "Nombre asesor").replace("--Estado Pago--", EstadoEnum.APROBADO.toString())
								.replace("--tipo--", "BLOQUEO").replace("--Nombre Cliente--", clientePago.getNombreCliente())
								.replace("--Identificacion Cliente--", clientePago.getCedula())
								.replace("--Cuenta Mupi--", clientePago.getCodigoCuentaMupi()).replace("--Operacion--", clientePago.getCodigoOperacion())
								.replace("--Fecha AprobacionRechazo--", QuskiOroUtil.dateToFullString( rPago.getFechaActualizacion()));
						ArrayMap<java.lang.String,byte[]> adjunto = new ArrayMap<java.lang.String,byte[]>();//= (LocalStorageClient);
						String[] para= {"hoscarly007@gmail.com"};//reemplazar el mailAprobador,
						String asunto ="Aprobacion de solictud "+ EstadoEnum.APROBADO.toString(); 
						log.info("CONTENIDO ENVIA "+para+"--"+asunto+"--"+textoContenido+"--"+adjunto);
						qos.mailNotificacion(para, asunto, textoContenido, null);
						
						
						}
			} catch (Exception e) {
				log.info("----NO SE PUDO ENVIAR EL CORREO DE APROBAR PAGO----");
				e.printStackTrace();
			}
			return rPago;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public TbQoClientePago rechazarPago(Long id,String tipo,String nombreAprobador, String mailAprobador) throws RelativeException {
		try {
			TbQoClientePago clientePago = clientePagoRepository.findByIdAndEstado(id, EstadoEnum.PENDIENTE_APROBACION, tipo);
			if(clientePago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN ESTADO PENDIENTE");
			}
			log.info("Aprobador ---->>>>"+ nombreAprobador);
			clientePago.setUsuarioActualizacion(nombreAprobador);
			clientePago.setEstado(EstadoEnum.RECHAZADO);
			TbQoClientePago rPago = qos.manageClientePago(clientePago);
			try {
				if(tipo.equalsIgnoreCase("REGISTRO_PAGO")) {
				String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_PAGO).getValor();
				textoContenido=textoContenido.replace("--Nombre Asesor--", "Nombre asesor").replace("--Estado Pago--", EstadoEnum.RECHAZADO.toString())
						.replace("--tipo--", "Pago").replace("--Nombre Cliente--", clientePago.getNombreCliente())
						.replace("--Identificacion Cliente--", clientePago.getCedula())
						.replace("--Cuenta Mupi--", clientePago.getCodigoCuentaMupi()).replace("--Operacion--", clientePago.getCodigoOperacion())
						.replace("--Fecha AprobacionRechazo--", QuskiOroUtil.dateToFullString( rPago.getFechaActualizacion()));
				ArrayMap<java.lang.String,byte[]> adjunto = new ArrayMap<java.lang.String,byte[]>();//= (LocalStorageClient);
				String[] para= {"hoscarly007@gmail.com"};//reemplazar el mailAprobador,
				
				String asunto ="Aprobacion de solictud "+ EstadoEnum.RECHAZADO.toString(); 
				log.info("CONTENIDO ENVIA "+para+"--"+asunto+"--"+textoContenido+"--"+adjunto);
				qos.mailNotificacion(para, asunto, textoContenido, null);
				}else if(tipo.equalsIgnoreCase("BLOQUEO_FONDO")) { 
						
							String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.TEXTO_APROBACION_PAGO).getValor();
							textoContenido=textoContenido.replace("--Nombre Asesor--", "Nombre asesor").replace("--Estado Pago--", EstadoEnum.RECHAZADO.toString())
									.replace("--tipo--", "BLOQUEO").replace("--Nombre Cliente--", clientePago.getNombreCliente())
									.replace("--Identificacion Cliente--", clientePago.getCedula())
									.replace("--Cuenta Mupi--", clientePago.getCodigoCuentaMupi()).replace("--Operacion--", clientePago.getCodigoOperacion())
									.replace("--Fecha AprobacionRechazo--", QuskiOroUtil.dateToFullString( rPago.getFechaActualizacion()));
							ArrayMap<java.lang.String,byte[]> adjunto = new ArrayMap<java.lang.String,byte[]>();//= (LocalStorageClient);
							String[] para= {"hoscarly007@gmail.com"};//reemplazar el mailAprobador,
							
							String asunto ="Aprobacion de solictud "+ EstadoEnum.APROBADO.toString(); 
							log.info("CONTENIDO ENVIA "+para+"--"+asunto+"--"+textoContenido+"--"+adjunto);
							qos.mailNotificacion(para, asunto, textoContenido, null);
					}
						
			} catch (Exception e) {
				log.info("----NO SE PUDO ENVIAR EL CORREO DE RECHAZAR PAGO----");
				e.printStackTrace();
			}
			return rPago;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
