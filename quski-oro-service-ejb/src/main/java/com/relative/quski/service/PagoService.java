package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.ClientePagoRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.FileLocalStorage;
import com.relative.quski.wrapper.RegistrarBloqueoFondoWrapper;
import com.relative.quski.wrapper.RegistrarPagoWrapper;
import com.relative.quski.wrapper.RegistroBloqueoFondoWrapper;
import com.relative.quski.wrapper.RegistroPagoWrapper;

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

	public RegistrarPagoWrapper crearRegistrarPago(RegistrarPagoWrapper registroPago, String autorizacion)
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
					/*List<FileLocalStorage> listFile = new ArrayList<>();
					FileLocalStorage file = new FileLocalStorage();
					file.setArchivo(registro.getArchivo());
					file.setNombreArchivo(registro.getNombreArchivo());
					file.setProceso("REGISTRO_PAGO");
					
					LocalStorageClient.saveFileSotage(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE)
							.getValor().concat(getQueryParametersNotificacion(UUID.randomUUID().toString(),
									"TIPO_CLIENTE", "", listFile)),
							autorizacion);
							 
							 */
					TbQoRegistrarPago pago = new TbQoRegistrarPago();
					pago.setCuentas(registro.getCuentas());
					pago.setFechaPago(registro.getFechadePago());
					pago.setInstitucionFinanciera(registro.getInstitucionFinanciera());
					pago.setNumeroDeposito(registro.getNumerodeDeposito());
					pago.setValorPagado(registro.getValorpagado());
					pago.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					pago.setEstado(EstadoEnum.ACT);
					pago.setTbQoClientePago(cliente);
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

	
	public TbQoClientePago aprobarPago(Long id, EstadoEnum estado) throws RelativeException {
		try {
			TbQoClientePago clientePago = clientePagoRepository.findByIdAndEstado(id, estado);
			if(clientePago == null) { 
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN ESTADO PENDIENTE");
			}
			
			clientePago.setEstado(EstadoEnum.APROBADO);
			TbQoClientePago pago = qos.manageClientePago(clientePago);
			//falta enviar el correo electronico con la observacion al asesor
			return pago;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

	public TbQoClientePago rechazarPago(Long id, EstadoEnum estado) throws RelativeException {
		try {
			TbQoClientePago clientePago = clientePagoRepository.findByIdAndEstado(id, estado);
			if(clientePago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EN ESTADO PENDIENTE");
			}
			
			clientePago.setEstado(EstadoEnum.RECHAZADO);
			TbQoClientePago pago = qos.manageClientePago(clientePago);
			//falta enviar el correo electronico con la observacion al asesor
			
			return pago;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	// REGISTRO BLOQUEO DE FONDOS EN LA TABLA REGISTRAR PAGO 
	public RegistrarBloqueoFondoWrapper bloqueoFondo(RegistrarBloqueoFondoWrapper bloqueoFondo, String autentication)throws RelativeException {
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
				/*List<FileLocalStorage> listFile = new ArrayList<>();
				FileLocalStorage file = new FileLocalStorage();
				file.setArchivo(registro.getArchivo());
				file.setNombreArchivo(registro.getNombreArchivo());
				file.setProceso("REGISTRO_PAGO");
				
				LocalStorageClient.saveFileSotage(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE)
						.getValor().concat(getQueryParametersNotificacion(UUID.randomUUID().toString(),
								"TIPO_CLIENTE", "", listFile)),
						autorizacion);
						 
						 */
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


	
	
}
