package com.relative.quski.bpms.api;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.AprobarWrapper;
import com.relative.quski.wrapper.CatalogoActividadWrapper;
import com.relative.quski.wrapper.CatalogoAgenciaWrapper;
import com.relative.quski.wrapper.CatalogoDivicionWrapper;
import com.relative.quski.wrapper.CatalogoIdResponseWrapper;
import com.relative.quski.wrapper.CatalogoIdWrapper;
import com.relative.quski.wrapper.CatalogoResponseActividadWrapper;
import com.relative.quski.wrapper.CatalogoResponseAgenciaWrapper;
import com.relative.quski.wrapper.CatalogoResponseDivicionWrapper;
import com.relative.quski.wrapper.CatalogoResponseWrapper;
import com.relative.quski.wrapper.CatalogoTablaAmoritzacionResponseWrapper;
import com.relative.quski.wrapper.CatalogoTablaAmortizacionWrapper;
import com.relative.quski.wrapper.CatalogoWrapper;
import com.relative.quski.wrapper.ConsultaGarantiaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalRespuestaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalWrapper;
import com.relative.quski.wrapper.ConsultaOperacionGlobalWrapper;
import com.relative.quski.wrapper.ConsultaRubrosWrapper;
import com.relative.quski.wrapper.ConsultaTablaWrapper;
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRenovacionWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.GarantiaOperacionWrapper;
import com.relative.quski.wrapper.RespuestaAprobarWrapper;
import com.relative.quski.wrapper.RespuestaConsultaGlobalWrapper;
import com.relative.quski.wrapper.RespuestaGarantiaWrapper;
import com.relative.quski.wrapper.RespuestaRubroWrapper;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.RubroOperacionWrapper;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;
import com.relative.quski.wrapper.SoftbankRespuestaWrapper;
import com.relative.quski.wrapper.SoftbankRiesgoWrapper;
import com.relative.quski.wrapper.SoftbankTablaAmortizacionWrapper;

public class SoftBankApiClient {

	private static final Log log = LogFactory.getLog(SoftBankApiClient.class);
	public static void main(String[] args) {
		
		try {
			
			
			
			SoftbankClienteWrapper x= callConsultaClienteRest("http://201.183.238.73:1991/api/cliente/consultar","131166441");
			System.out.println("==============>>>"+ x.getNombreCompleto());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static SoftbankRespuestaWrapper callCrearClienteRest(String service,SoftbankClienteWrapper wrapper)
			throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			//String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_CLIENTE;
			log.info("=========> WRAPPER CREAR ========> " + new String(content));
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankClienteWrapper.class);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				SoftbankRespuestaWrapper rest =  gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankRespuestaWrapper.class);
				if(rest != null && rest.getExisteError()) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
							rest.getMensaje() +" | validaciones: "+ rest.getValidaciones());
				}
				return rest;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:" + e.getMessage());
		} 
	}
	public static ConsultaGlobalRespuestaWrapper callConsultaGlobalRest(String service, ConsultaGlobalWrapper wrapper)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CREAR ========> " + new String(content));
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, ConsultaGlobalRespuestaWrapper.class);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), ConsultaGlobalRespuestaWrapper.class);
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
	public static RespuestaAprobarWrapper callAprobarRest(String service, AprobarWrapper wrapper) throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CREAR ========> " + new String(content));
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, RespuestaAprobarWrapper.class);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				RespuestaAprobarWrapper result = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaAprobarWrapper.class);
				if( result.getExisteError()) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," Existe Error: " + result.getMensaje() );
				}else {
					return result;
				}
			}else {
				log.info( " =================> " + String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)) + " <===================" );
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," Error:" + String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR SERVICIO APROBAR REST SOFTBANK:" + e.getMessage());
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
	public static SoftbankClienteWrapper callConsultaClienteRest(String service,String identificacion)
			throws RelativeException, UnsupportedEncodingException {
		try {
			SoftbankConsultaWrapper consulta = new SoftbankConsultaWrapper(identificacion);
			Gson gson = new Gson();
			String jsonString = gson.toJson(consulta);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			//String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CONSULTA_CLIENTE;
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
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
	public static SoftbankRiesgoWrapper callConsultaRiesgoRest(SoftbankConsultaWrapper consulta, String service)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(consulta);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CONSULTA ========> " + new String(content));
			//String service = QuskiOroConstantes.URL_SOFTBANK_RIESGO_ACUMULADO;
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
				SoftbankRiesgoWrapper resp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankRiesgoWrapper.class);
				if(resp.getExisteError()) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,resp.getMensaje());
				}
				return resp;
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
			throw e;
		}
	}
	public static SoftbankTablaAmortizacionWrapper callConsultaTablaAmortizacionRest(String service, ConsultaTablaWrapper cont)	throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(cont);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CONSULTA TABLA AMORTIZACION ========> " + new String(content));
			//String service = QuskiOroConstantes.URL_SOFTBANK_TABLA_AMORTIZACION;
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
	public static List<CatalogoTablaAmortizacionWrapper> callCatalogoTablaAmortizacionRest(String service) throws RelativeException {
		try {
			//String service = QuskiOroConstantes.URL_SOFTBANK_CATALOGO_TABLA_AMOTIZACION;
			log.info("=========> SERVICIO URL ========> " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String("{}"), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, CatalogoTablaAmoritzacionResponseWrapper.class);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			log.info("=========> VALOR DEL STATUS ========> "+ status);
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				CatalogoTablaAmoritzacionResponseWrapper  wrapper =  gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoTablaAmoritzacionResponseWrapper.class);
				if( wrapper.getExisteError() && wrapper.getTablaAmortizacion().isEmpty() ) {
					return null;
				}
				return wrapper.getTablaAmortizacion();
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
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	public static List<CatalogoAgenciaWrapper> callCatalogoAgenciaRest( String service ) throws RelativeException {
		try {
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON, RestClientWrapper.CONTENT_TYPE_JSON, null, "{}", RestClientWrapper.METHOD_POST, null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, 
					service, CatalogoResponseAgenciaWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			Gson gsons = new GsonBuilder().create();
			CatalogoResponseAgenciaWrapper  wrapper = status >= 200 && status < 300 ? gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoResponseAgenciaWrapper.class) : null;
			return wrapper != null && Boolean.FALSE.equals(wrapper.getExisteError()) && !wrapper.getCatalogo().isEmpty() ? wrapper.getCatalogo() : null;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	public static List<CatalogoActividadWrapper> callCatalogoActividadEconomicaRest( String service ) throws RelativeException {
		try {
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON, RestClientWrapper.CONTENT_TYPE_JSON, null, "{}", RestClientWrapper.METHOD_POST, null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, 
					service, CatalogoResponseActividadWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			Gson gsons = new GsonBuilder().create();
			CatalogoResponseActividadWrapper  wrapper = status >= 200 && status < 300 ? gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoResponseActividadWrapper.class) : null;
			return wrapper != null && Boolean.FALSE.equals(wrapper.getExisteError()) && !wrapper.getCatalogo().isEmpty() ? wrapper.getCatalogo() : null;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	public static List<CatalogoDivicionWrapper> callCatalogoDivicionPoliticaRest( String service ) throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson("{}");
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER CONSULTA ========> " + new String(content));
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON, RestClientWrapper.CONTENT_TYPE_JSON, null, "{}", RestClientWrapper.METHOD_POST, null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,
					service, CatalogoResponseDivicionWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			Gson gsons = new GsonBuilder().create();
			CatalogoResponseDivicionWrapper  wrapper = status >= 200 && status < 300 ? gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoResponseDivicionWrapper.class) : null;
			return wrapper != null && Boolean.FALSE.equals(wrapper.getExisteError()) && !wrapper.getCatalogo().isEmpty() ? wrapper.getCatalogo() : null;
		} catch (RelativeException | UnsupportedEncodingException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	public static List<CatalogoIdWrapper> callCatalogoConIdRest(String service) throws RelativeException {
		try {
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON, RestClientWrapper.CONTENT_TYPE_JSON, null, "{}", RestClientWrapper.METHOD_POST, null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, 
					service, CatalogoIdResponseWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			Gson gsons = new GsonBuilder().create();
			CatalogoIdResponseWrapper  wrapper = status >= 200 && status < 300 ? gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoIdResponseWrapper.class) : null;
			return wrapper != null && Boolean.FALSE.equals(wrapper.getExisteError()) && !wrapper.getCatalogo().isEmpty() ? wrapper.getCatalogo() : null;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		}
	}
	public static List<CatalogoWrapper> callCatalogoRest(String service) throws RelativeException {
		try {
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON, RestClientWrapper.CONTENT_TYPE_JSON, null, "{}", RestClientWrapper.METHOD_POST, null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE,
					service, CatalogoResponseWrapper.class);
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			Gson gsons = new GsonBuilder().create();
			CatalogoResponseWrapper  wrapper = status >= 200 && status < 300 ? gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoResponseWrapper.class) : null;
			return wrapper != null && Boolean.FALSE.equals(wrapper.getExisteError()) && !wrapper.getCatalogo().isEmpty() ? wrapper.getCatalogo() : null;
		} catch (RelativeException e) {
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
	public static SoftbankRespuestaWrapper callEditarClienteRest(SoftbankClienteWrapper consulta,String service)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(consulta);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=========> WRAPPER EDITAR CLIENTE ========> " + new String(content));
			//String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE;
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, SoftbankClienteWrapper.class);
			
			
			log.info("=========> RESPUESTA DEL SERVICIO response ========> "+ response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			log.info("=========> VALOR DEL STATUS ========> "+ status);
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				SoftbankRespuestaWrapper rest =  gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), SoftbankRespuestaWrapper.class);
				if(rest != null && rest.getExisteError()) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
							rest.getMensaje() +" | validaciones: "+ rest.getValidaciones());
				}
				return rest;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callEditarClienteRest:" + e.getMessage());
		} 
	}
	
	public static CrearOperacionRespuestaWrapper callCrearOperacion01Rest(CrearOperacionEntradaWrapper datosEntradaOperacion, String service)
			throws RelativeException {
		
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(datosEntradaOperacion);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> un01 " + new String(content));
			//String service = QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_OPERACION;
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
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO:"+
						String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		}  catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL CONSUMIR EL WS DE CREAR OPERACION SOFTBAN");
		}
	}
	public static CrearOperacionRespuestaWrapper callRenovarOperacionRest(CrearOperacionRenovacionWrapper datosEntradaOperacion, String service)
			throws RelativeException {
		
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(datosEntradaOperacion);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> un01 " + new String(content));
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
		}  catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL CONSUMIR EL WS DE CREAR OPERACION SOFTBAN");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static RespuestaConsultaGlobalWrapper callConsultarOperacionRest(ConsultaOperacionGlobalWrapper entrada, String service)throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson( entrada );
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> un01 " + new String(content));
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, "", "",
					"", QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, RespuestaConsultaGlobalWrapper.class);
			log.info("===> REspuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaConsultaGlobalWrapper.class);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO UN01:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callConsultarOperacionRest" );
		}
	}
	public static List<GarantiaOperacionWrapper> callConsultarGarantiasRest(ConsultaGarantiaWrapper entrada, String service)throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson( entrada );
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> un01 " + new String(content));
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, "", "",
					"", QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, RespuestaGarantiaWrapper.class);
			log.info("===> REspuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				RespuestaGarantiaWrapper respuestaWrapper = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaGarantiaWrapper.class);
				if( respuestaWrapper.getExisteError() ) {return null;}
				return respuestaWrapper.getGarantias();
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO UN01:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callConsultarOperacionRest" );
		}
	}
	public static List<RubroOperacionWrapper> callConsultarRubrosRest(ConsultaRubrosWrapper entrada, String service)throws RelativeException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson( entrada );
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> un01 " + new String(content));
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, new String(content), RestClientWrapper.METHOD_POST, "", "",
					"", QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, RespuestaRubroWrapper.class);
			log.info("===> REspuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				RespuestaRubroWrapper result = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), RespuestaRubroWrapper.class);
				if( result.getExisteError() ) {return null;}
				return result.getRubros();
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO UN01:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (RelativeException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO callConsultarOperacionRest" );
		}
	}
}
