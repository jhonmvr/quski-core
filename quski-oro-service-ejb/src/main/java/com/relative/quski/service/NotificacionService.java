package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.RespuestaObjectWrapper;

@Stateless
public class NotificacionService {
	@Inject
	Logger log;
	@Inject
	private ParametroRepository parametroRepository;

	public RespuestaObjectWrapper plantillaOperacionHabilitante(String codigoOperacion, String autorizacion)
			throws RelativeException, UnsupportedEncodingException {
		try {
			RespuestaObjectWrapper objeto = LocalStorageClient.findObjectById(
					parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
					parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
					parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
					SoftBankApiClient.generarHabilitanteCredito(codigoOperacion, autorizacion, parametroRepository
							.findByNombre(QuskiOroConstantes.SOFTBANK_GENERAR_HABILITANTE).getValor()).getUriHabilitantes());

			return objeto;

		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	

}
