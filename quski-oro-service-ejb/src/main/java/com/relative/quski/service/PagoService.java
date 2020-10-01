package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.BloquearFondosWrapper;
import com.relative.quski.wrapper.FileLocalStorage;
import com.relative.quski.wrapper.RegistrarPagoWrapper;
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
			if (registroPago.getPagos() != null && !registroPago.getPagos().isEmpty()) {
				
				for (RegistroPagoWrapper registro : registroPago.getPagos()) {
					List<FileLocalStorage> listFile = new ArrayList<>();
					FileLocalStorage file = new FileLocalStorage();
					file.setArchivo(registro.getArchivo());
					file.setNombreArchivo(registro.getNombreArchivo());
					file.setProceso("REGISTRO_PAGO");
					listFile.add(file);
					LocalStorageClient.saveFileSotage(parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE)
							.getValor().concat(getQueryParametersNotificacion(UUID.randomUUID().toString(),
									"TIPO_CLIENTE", "", listFile)),
							autorizacion);
					TbQoRegistrarPago pago = new TbQoRegistrarPago();
					pago.setCuentas(registro.getCuentas());
					pago.setFechaPago(registro.getFechadePago());
					pago.setInstitucionFinanciera(registro.getInstitucionFinanciera());
					pago.setNumeroDeposito(registro.getNumerodeDeposito());
					pago.setValorPagado(registro.getValorpagado());
					pago.setTbQoClientePago(cliente);
				}
			}
			registroPago.setCliente(cliente);
			// registroPago.setPagos(listaPagos);
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

	public BloquearFondosWrapper crearBloquearFondos(BloquearFondosWrapper bloquearFondos) throws RelativeException {
		try {
			if (bloquearFondos == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE LEER LA INFORMACION DEL PAGO");
			}
			if (bloquearFondos.getCliente() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE PAGO");
			}

			TbQoClientePago cliente = qos.manageClientePago(bloquearFondos.getCliente());
			List<TbQoRegistrarPago> listaPagos = null;
			if (bloquearFondos.getPagos() != null && !bloquearFondos.getPagos().isEmpty()) {
				listaPagos = new ArrayList<>();
				for (TbQoRegistrarPago registro : bloquearFondos.getPagos()) {
					registro.setTbQoClientePago(cliente);
					listaPagos.add(qos.manageRegistrarPago(registro));

				}
			}
			bloquearFondos.setCliente(cliente);
			bloquearFondos.setPagos(listaPagos);
			return bloquearFondos;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public List<TbQoRegistrarPago> findRegistrarPagoByIdClientePago(Long id) throws RelativeException {
		// TODO Auto-generated method stub
		return registrarPagoRepository.findByIdClientePago(id);
	}

	public List<TbQoClientePago> findClientePagoByIdClientePago(Long id) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

}
