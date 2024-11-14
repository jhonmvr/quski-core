package com.relative.quski.bpms.api;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import javax.inject.Inject;

import com.relative.quski.wrapper.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;

public class LocalStorageClient {

	private static final Log log = LogFactory.getLog(LocalStorageClient.class);

	private static final String urlToken = "token/";

	private static final String empty = "{}";

	@Inject
	//private ParametroRepository parametroRepository;

	public static void main(String[] args) {
		try {
			String code = "W3siY2VkdWxhIjoiMTcyMDgxMjIzNyIsIm5vbWJyZSI6IkRpZWdvIFNlcnJhbm8gIn1d";
			String decodedUrl = Base64.getEncoder().encodeToString(code.getBytes());
			//String decodedUrl = Arrays.toString(byteArray);
			@SuppressWarnings("unused")
			Gson gsons = new GsonBuilder().create();
		/*	Class<? extends ArrayList> listType = new ArrayList<HerederoWrapper>().getClass();
			List<HerederoWrapper> list =   gsons.fromJson((String) decodedUrl, listType);*/
			System.out.print("HOLI" + decodedUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static RespuestaObjectWrapper updateObjectBigZ(String urlService, String databaseName, String collectionName, String objectId, NodoWrapper wrapper, String authorization)
			throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);

			// Construir la URL del servicio con parámetros
			String service = urlService.concat("mongoRestController/updateObjectBigZ?")
					.concat("databaseName=").concat(databaseName)
					.concat("&collectionName=").concat(collectionName)
					.concat("&objectId=").concat(objectId);

			// Preparar el contenido JSON con el objeto encriptado
			byte[] content = new String("{ \r\n" +
					"  \"objectEncripted\":\"" + Base64.getEncoder().encodeToString(jsonString.getBytes()) + "\"\r\n" +
					"}").getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);

			log.info("===> call updateObjectBigZ con servicio " + service);

			// Realizar la llamada REST
			Map<String, Object> response = com.relative.migracion.api.ReRestClient.callRestApi(
					RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON,
					authorization,
					new String(content),
					RestClientWrapper.METHOD_POST,
					null,
					null,
					null,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					Boolean.FALSE,
					Boolean.FALSE,
					service,
					String.class
			);

			log.info("===> Respuesta de servicio " + response);

			// Verificar el estado de la respuesta
			Long status = Long.valueOf(String.valueOf(response.get(com.relative.migracion.api.ReRestClient.RETURN_STATUS)));

			if (status >= 200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(com.relative.migracion.api.ReRestClient.RETURN_OBJECT), RespuestaObjectWrapper.class);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR AL LLAMAR SERVICIO DE ACTUALIZACIÓN: " +
						String.valueOf(response.get(com.relative.migracion.api.ReRestClient.RETURN_MESSAGE)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR AL LLAMAR SERVICIO DE updateObjectBigZ:");
		}
	}

	public static RespuestaObjectWrapper createObject(String urlService,String databaseName, String collectionName,FileObjectStorage wrapper,
			String authorization)
			throws RelativeException, UnsupportedEncodingException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(wrapper);
		
		String service = urlService.concat("createObject?").concat("databaseName=").concat(databaseName).concat("&collectionName=").concat(collectionName)
				.concat("&objectEncripted=").concat(Base64.getEncoder().encodeToString(jsonString.getBytes()));
		log.info("===> callBpmsInitProcesss con servcio " + service);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_JSON, authorization, null, RestClientWrapper.METHOD_GET, null, null,
				null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,service, String.class);
		//log.info("===> REspuesta de servicio " + response);
		Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
		
		
		
		if(status>=200 && status < 300) {
			Gson gsons = new GsonBuilder().create();
			return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaObjectWrapper.class);
			
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO DE NOTIFICACION:"+
					String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
		}
	}
	

	public static RespuestaObjectWrapper updateObject(String urlService,String databaseName, String collectionName,FileObjectStorage wrapper,
			String idObject, String autorizacion)
			throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			
			String service = urlService.concat("updateObject?").concat("databaseName=").concat(databaseName).concat("&collectionName=").concat(collectionName)
					.concat("&objectEncripted=").concat(Base64.getEncoder().encodeToString(jsonString.getBytes())).concat("&objectId=").concat(idObject);
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, autorizacion, null, RestClientWrapper.METHOD_GET, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,service, String.class);
			//log.info("===> REspuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			
			
			
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaObjectWrapper.class);
				
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO DE updateObject:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO DE updateObject:");
		} 
	}
	
	public static RespuestaObjectWrapper createObjectBig(String urlService,String databaseName, String collectionName,FileObjectStorage wrapper,
			String authorization)
			throws RelativeException, UnsupportedEncodingException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(wrapper);
		String service = urlService.concat("createObjectBig?").
				concat("&databaseName=").concat(databaseName).
				concat("&collectionName=").concat(collectionName);
		
		byte[] content = new String("{ \r\n" + 
				"  \"objectEncripted\":\""+Base64.getEncoder().encodeToString(jsonString.getBytes())+"\"\r\n" + 
				"}").getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
		log.info("===> callBpmsInitProcesss con servcio " + service);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_JSON, authorization, new String(content), RestClientWrapper.METHOD_POST, null, null,
				null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,service, String.class);
		//log.info("===> REspuesta de servicio " + response);
		Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
		
		
		
		if(status>=200 && status < 300) {
			Gson gsons = new GsonBuilder().create();
			return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaObjectWrapper.class);
			
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO DE NOTIFICACION:"+
					String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
		}
	}
	
	
	public static RespuestaObjectWrapper findObjectById(String urlService,String databaseName, String collectionName,String objectId, String autorizacion)
			throws RelativeException {
	
		try {
			String service = urlService.concat("findObjectById?").
					concat("&databaseName=").concat(databaseName).
					concat("&collectionName=").concat(collectionName).
					concat("&objectId=").concat(objectId);
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, autorizacion, null, RestClientWrapper.METHOD_GET, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,service, String.class);
			//log.info("===> REspuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			
			
			
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaObjectWrapper.class);
				
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL LLAMAR SERVICIO OBJECT STORAGE:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"OBJECT STORAGE");

		} 
	}
	

	public static String saveFileSotage(String urlService, String authorization)
			throws RelativeException, UnsupportedEncodingException {
	
		String service = urlService;
		log.info("===> callBpmsInitProcesss con servcio " + service);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_JSON, authorization, null, RestClientWrapper.METHOD_GET, null, null,
				null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,service, String.class);
		//log.info("===> REspuesta de servicio " + response);
		Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
		if(status>=200 && status < 300) {
					
			return String.valueOf(response.get(ReRestClient.RETURN_OBJECT));
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO DE NOTIFICACION:"+
					String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
		}
	}

	public static TokenWrapper getToken(String amUrlService, String authorization) throws RelativeException {
		log.info("===>x getToken con authorization " + authorization);
		StringBuilder param = new StringBuilder();
		param.append("grant_type").append("=").append("client_credentials");
		String service = amUrlService + urlToken + "?" + param.toString();
		log.info("===> url token " + service);
		log.info("===> authorization token " + authorization);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_X_WWW_FORM, authorization, empty, RestClientWrapper.METHOD_POST, null,
				null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE,Boolean.FALSE, service, TokenWrapper.class);
		//log.info("===> getToken wraooer generados " + response);
		if (response != null && response.get("message") != null
				&& response.get("message").toString().indexOf("Unsuccessful") < 0) {
			TokenWrapper tmp = (TokenWrapper) response.get(ReRestClient.RETURN_OBJECT);
			if (tmp.getError() != null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN LA OBTENCION DEL TOKEN DE SEGURIDAD, POR FAVOR CONSULTE A SU ADMINISTRADOR "
								+ tmp.getError_description());
			} else if (Long.valueOf(tmp.getExpires_in()) <= 60) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"POR RAZONES DE SEGURIDAD SU TOKEN ESTA A PUNTO DE CADUCAR, POR FAVOR INTENTE EN 60 SEGUNDOS");
			}
			return tmp;
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA EJECUCION DE LA LLAMADA REST "
					+ amUrlService + " INTENTE EN 30 SEGUNDOS, SI EL ERROR PERSISTE CONSULTE A SU ADMINISTRADOR");
		}

	}
	
	

}
