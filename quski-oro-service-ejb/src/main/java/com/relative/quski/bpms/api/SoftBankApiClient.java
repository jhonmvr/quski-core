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
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;
import com.relative.quski.wrapper.SoftbankRespuestaWrapper;
import com.relative.quski.wrapper.SoftbankRiesgoWrapper;
import com.relative.quski.wrapper.SoftbankTablaAmortizacionWrapper;

public class SoftBankApiClient {

	private static final Log log = LogFactory.getLog(SoftBankApiClient.class);

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
	public static SoftbankRespuestaWrapper callCrearClienteRest(SoftbankClienteWrapper wrapper)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_CLIENTE;
			log.info("=========> WRAPPER CREAR ========> " + new String(content));
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankClienteWrapper.class);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankRespuestaWrapper.class);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
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
	/**
	 * 
	 * @param urlService
	 * @param authorization
	 * @param consulta
	 * @return
	 * @throws RelativeException
	 * @throws UnsupportedEncodingException
	 */
	public static SoftbankClienteWrapper callConsultaClienteRest(String urlService, String authorization, SoftbankConsultaWrapper consulta)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(consulta);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			String service = urlService;
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, authorization, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankClienteWrapper.class);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankClienteWrapper.class);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
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
	public static SoftbankRiesgoWrapper callConsultaRiesgoRest(SoftbankConsultaWrapper consulta)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(consulta);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CONSULTA ========> " + new String(content));
			String service = QuskiOroConstantes.URL_SOFTBANK_RIESGO_ACUMULADO;
			log.info("=========> SERVICIO URL ========> " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankRiesgoWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			log.info("=========> VALOR DEL STATUS ========> "+ status);
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankRiesgoWrapper.class);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
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
	public static SoftbankTablaAmortizacionWrapper callConsultaTablaAmortizacionRest(String numero)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(numero);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CONSULTA TABLA AMORTIZACION ========> " + new String(content));
			String service = QuskiOroConstantes.URL_SOFTBANK_RIESGO_ACUMULADO;
			log.info("=========> SERVICIO URL ========> " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankTablaAmortizacionWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			log.info("=========> VALOR DEL STATUS ========> "+ status);
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankTablaAmortizacionWrapper.class);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
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
	/**
	 * 
	 * @param urlService
	 * @param authorization
	 * @param consulta
	 * @return
	 * @throws RelativeException
	 * @throws UnsupportedEncodingException
	 */
	public static SoftbankRespuestaWrapper callEditarClienteRest(SoftbankClienteWrapper consulta)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(consulta);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER EDITAR CLIENTE ========> " + new String(content));
			String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE;
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankClienteWrapper.class);
			
			
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			log.info("=========> VALOR DEL STATUS ========> "+ status);
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankRespuestaWrapper.class);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:");
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:");
		}
	}
	
	public static CrearOperacionRespuestaWrapper callCrearOperacion01Rest(CrearOperacionEntradaWrapper datosEntradaOperacion)
			throws RelativeException, UnsupportedEncodingException {
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(datosEntradaOperacion);
		byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
		log.info("=====> un01 " + new String(content));
		String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_OPERACION;
		log.info("===> callBpmsInitProcesss con servcio " + service);
		Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
				RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, "", "",
				"", QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
				QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, CrearOperacionRespuestaWrapper.class);
		log.info("===> REspuesta de servicio " + response);
		Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
		if(status>=200 && status < 300) {
			Gson gsons = new GsonBuilder().create();
			CrearOperacionRespuestaWrapper respuestaWrapper = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CrearOperacionRespuestaWrapper.class);
			log.info("============> respuesta servicio objeto "+ respuestaWrapper.getExisteError());
			if(respuestaWrapper.getExisteError() ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL CREAR OPERACION" + respuestaWrapper.getMensaje() );
	
			}
			return respuestaWrapper;
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO UN01:"+
					String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
		}
	}
	

	
	
}
