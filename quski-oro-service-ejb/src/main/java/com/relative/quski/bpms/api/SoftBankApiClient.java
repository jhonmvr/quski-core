package com.relative.quski.bpms.api;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.SoftbankClientWrapper;

public class SoftBankApiClient {

	private static final Log log = LogFactory.getLog(SoftBankApiClient.class);

	private static final String urlToken = "token/";

	private static final String empty = "{}";

	public static void main(String[] args) {
		
	}
	public static String callpun01Rest(String urlService, String authorization,SoftbankClientWrapper wrapper)
			throws RelativeException, UnsupportedEncodingException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(wrapper);
		byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
		log.info("=====> wrapper " + new String(content));
		String service = urlService;
		log.info("===> callBpmsInitProcesss con servcio " + service);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_JSON, authorization, new String(content), RestClientWrapper.METHOD_POST, null, null,
				null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankClientWrapper.class);
		log.info("===> REspuesta de servicio " + response);
		Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
		if(status>=200 && status < 300) {
			Gson gsons = new GsonBuilder().create();
			
			SoftbankClientWrapper GenericWrapper = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankClientWrapper.class);
			//SoftbankClientWrapper respuestaWrapper = (SoftbankClientWrapper)response.get(ReRestClient.RETURN_OBJECT);
			log.info("============> respuesta servicio objeto "+ GenericWrapper.getCodigoEducacion().charAt(0));
			log.info("============> respuesta servicio objeto "+ GenericWrapper.getCodigoEstadoCivil().getClass() );
			
			/*
			 * String
			 * huboErrores=respuestaWrapper.getSdtRespuestaUN01().get(0).getHubo_Errores();
			 * if(StringUtils.isNotBlank(huboErrores) && huboErrores.equalsIgnoreCase("S"))
			 * { throw new
			 * SegSucreException(Constantes.ERROR_CODE_CUSTOM,"EN LOS DATOS UN01: "
			 * +respuestaWrapper.getSdtRespuestaUN01().get(0).getObservacion()); }
			 */
			return String.valueOf(response.get(ReRestClient.RETURN_OBJECT));
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
					String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
		}
	}


}
