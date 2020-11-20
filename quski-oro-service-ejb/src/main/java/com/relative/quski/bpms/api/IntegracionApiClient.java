package com.relative.quski.bpms.api;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.IntegracionConsultaWrapper;
import com.relative.quski.wrapper.IntegracionEntidadWrapper;
import com.relative.quski.wrapper.IntegracionRespuestaWrapper;
import com.relative.quski.wrapper.RestClientWrapper;

public class IntegracionApiClient {

	private static final Log log = LogFactory.getLog(IntegracionApiClient.class);

	public static void main(String[] args) {
		
	}
	/**
	 * 
	 * @param cedula
	 * @return
	 * @throws RelativeException
	 */
	public static IntegracionEntidadWrapper xcallPersonaRest(String cedula) throws RelativeException {
		try {
			IntegracionConsultaWrapper wrapper = new IntegracionConsultaWrapper(cedula);
			String service = QuskiOroConstantes.URL_PERSONA + QuskiOroConstantes.PARAMETRO_1 + wrapper.getTipoIdentificacion() + QuskiOroConstantes.PARAMETRO_2 + wrapper.getIdentificacion() + QuskiOroConstantes.PARAMETRO_3 + wrapper.getTipoConsulta() + QuskiOroConstantes.PARAMETRO_4 + wrapper.getCalificacion();
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, null, RestClientWrapper.METHOD_GET, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, IntegracionRespuestaWrapper.class);
			log.info("===> Respuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				IntegracionRespuestaWrapper respuesta = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), IntegracionRespuestaWrapper.class);
				if(respuesta.getEntidad().getIdentificacion() > 0 ) {
					log.info("Respuesta de servicio EQUIFAX A SERVICE");
					return respuesta.getEntidad();
				} else {
					return null;
				}
			}else {
				return null;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	
	
}
