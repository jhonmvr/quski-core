package com.relative.quski.bpms.api;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.CrmConsultaWrapper;
import com.relative.quski.wrapper.CrmGuardarProspectoWrapper;
import com.relative.quski.wrapper.CrmProspectoCortoWrapper;
import com.relative.quski.wrapper.CrmProspectoWrapper;
import com.relative.quski.wrapper.CrmRespuestaPersistWrapper;
import com.relative.quski.wrapper.CrmRespuestaWrapper;
import com.relative.quski.wrapper.RestClientWrapper;

public class CrmApiClient {

	private static final Log log = LogFactory.getLog(CrmApiClient.class);

	public static void main(String[] args) {
		
	}
	/**
	 * 
	 * @param urlService
	 * @param authorization
	 * @param wrapper
	 * @return
	 * @throws RelativeException
	 * @throws UnsupportedEncodingException
	 */
	public static CrmProspectoCortoWrapper callConsultaProspectoRest(String cedula) throws RelativeException, UnsupportedEncodingException {
		try {
			CrmConsultaWrapper wrapper = new CrmConsultaWrapper(cedula);
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> WRAPPER CRM CONSULTA " + new String(content));
			String service = QuskiOroConstantes.URL_CRM_PROSPECTO_CORTO;
			log.info("===> URL CRM " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, CrmRespuestaWrapper.class);
			log.info("===> Respuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				CrmRespuestaWrapper respuesta = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CrmRespuestaWrapper.class);
				return respuesta.getEntidad(); 
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	public static CrmProspectoWrapper callPersistProspectoRest(CrmGuardarProspectoWrapper wrapper ) throws RelativeException, UnsupportedEncodingException {
		try {
	
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> WRAPPER CRM CONSULTA " + new String(content));
			String service = QuskiOroConstantes.URL_CRM_PERSIST;
			log.info("===> URL CRM " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, CrmRespuestaPersistWrapper.class);
			log.info("===> Respuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				CrmRespuestaPersistWrapper respuesta = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CrmRespuestaPersistWrapper.class);
				return respuesta.getEntidades().get(0); 
			}else {
				return null;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
}
