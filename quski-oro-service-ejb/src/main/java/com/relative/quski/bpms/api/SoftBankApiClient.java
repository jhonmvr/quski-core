package com.relative.quski.bpms.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.TokenWrapper;

public class SoftBankApiClient {

	private static final Log log = LogFactory.getLog(SoftBankApiClient.class);

	private static final String urlToken = "token/";

	private static final String empty = "{}";

	public static void main(String[] args) {
		
	}
/*
	public static String callpun01Rest(String urlService, String authorization,UN01Wrapper un01)
			throws RelativeException, UnsupportedEncodingException {
		UN01ColeccionWrapper un01coleccion = new UN01ColeccionWrapper();
		un01coleccion.setUN01Coleccion(new ArrayList<>());
		un01coleccion.getUN01Coleccion().add(un01);
		Gson gson = new Gson();
		String jsonString = gson.toJson(un01coleccion);
		byte[] content = jsonString.getBytes(SiniestroAgricolaConstantes.BPMS_REST_DEFAULT_CHARSET);
		log.info("=====> un01 " + new String(content));
		String service = urlService;
		log.info("===> callBpmsInitProcesss con servcio " + service);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_JSON, authorization, new String(content), RestClientWrapper.METHOD_POST, null, null,
				null, SiniestroAgricolaConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				SiniestroAgricolaConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, service, UN01ColeccionResponseWrapper.class);
		log.info("===> REspuesta de servicio " + response);
		Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
		if(status>=200 && status < 300) {
			Gson gsons = new GsonBuilder().create();
			UN01ColeccionResponseWrapper respuestaWrapper = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), UN01ColeccionResponseWrapper.class);
			//UN01ColeccionResponseWrapper respuestaWrapper = (UN01ColeccionResponseWrapper)response.get(ReRestClient.RETURN_OBJECT);
			log.info("============> respuesta servicio objeto "+ respuestaWrapper.getSdtRespuestaUN01().size() );
			log.info("============> respuesta servicio objeto "+ respuestaWrapper.getSdtRespuestaUN01().get(0) );
			String huboErrores=respuestaWrapper.getSdtRespuestaUN01().get(0).getHubo_Errores();
			if(StringUtils.isNotBlank(huboErrores) && huboErrores.equalsIgnoreCase("S")) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"EN LOS DATOS UN01: "
			+respuestaWrapper.getSdtRespuestaUN01().get(0).getObservacion());
			}
			
			return String.valueOf(response.get(ReRestClient.RETURN_OBJECT));
		}else {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO UN01:"+
					String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
		}
	}

	public static TokenWrapper getToken(String amUrlService, String authorization) throws SegSucreException {
		log.info("===>x getToken con authorization " + authorization);
		StringBuilder param = new StringBuilder();
		param.append("grant_type").append("=").append("client_credentials");
		String service = amUrlService + urlToken + "?" + param.toString();
		log.info("===> url token " + service);
		log.info("===> authorization token " + authorization);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_X_WWW_FORM, authorization, empty, RestClientWrapper.METHOD_POST, null,
				null, null, SiniestroAgricolaConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				SiniestroAgricolaConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, service, TokenWrapper.class);
		log.info("===> getToken wraooer generados " + response);
		if (response != null && response.get("message") != null
				&& response.get("message").toString().indexOf("Unsuccessful") < 0) {
			TokenWrapper tmp = (TokenWrapper) response.get(ReRestClient.RETURN_OBJECT);
			if (tmp.getError() != null) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN LA OBTENCION DEL TOKEN DE SEGURIDAD, POR FAVOR CONSULTE A SU ADMINISTRADOR "
								+ tmp.getErrorDescription());
			} else if (Long.valueOf(tmp.getExpiresIn()) <= 60) {
				throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM,
						"POR RAZONES DE SEGURIDAD SU TOKEN ESTA A PUNTO DE CADUCAR, POR FAVOR INTENTE EN 60 SEGUNDOS");
			}
			return tmp;
		} else {
			throw new SegSucreException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA EJECUCION DE LA LLAMADA REST "
					+ amUrlService + " INTENTE EN 30 SEGUNDOS, SI EL ERROR PERSISTE CONSULTE A SU ADMINISTRADOR");
		}

	}
*/
}
