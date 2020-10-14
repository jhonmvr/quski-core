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
import com.relative.quski.wrapper.CalculadoraEntradaWrapper;
import com.relative.quski.wrapper.CalculadoraRespuestaWrapper;
import com.relative.quski.wrapper.EquifaxConsultaPersonaWrapper;
import com.relative.quski.wrapper.EquifaxPersonaWrapper;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.TokenWrapper;

public class ApiGatewayClient {

	private static final Log log = LogFactory.getLog(ApiGatewayClient.class);
    private static final String urlToken = "token?";
    private static final String urlCalculadora = "quski-calculadora/1.0";
    private static final String urlHost = "https://app.quski.ec";
    private static final String urlPort = ":8243/";
    private static final String empty="{}";




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
	public static EquifaxPersonaWrapper callConsultarClienteEquifaxRest(String urlService, String authorization,EquifaxConsultaPersonaWrapper wrapper)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			log.info("=====> wrapper " + new String(content));
			String service = urlService;
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, EquifaxConsultaPersonaWrapper.class);
			log.info("===> Respuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), EquifaxPersonaWrapper.class);
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
	public static CalculadoraRespuestaWrapper callCalculadoraRest(String authorization, CalculadoraEntradaWrapper wrapper)
			throws RelativeException, UnsupportedEncodingException {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(wrapper);
			byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			String service = urlHost + urlPort + urlCalculadora;			
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, new String(content), RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, CalculadoraEntradaWrapper.class);
	        log.info("==========>  RESPONSE =================> " + response + " <==================="); 
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				Gson gsons = new GsonBuilder().create();
				return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CalculadoraRespuestaWrapper.class);
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
	public static TokenWrapper getToken() throws RelativeException{
		
		RestClientWrapper rest = new RestClientWrapper();
		rest.setAuthorization(QuskiOroConstantes.AUTORIZACION);
        StringBuilder param = new StringBuilder();
        param.append( "grant_type" ).append("=").append("client_credentials");
        String service = urlHost + urlPort + urlToken + param.toString();
        Map<String,Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
        		RestClientWrapper.CONTENT_TYPE_X_WWW_FORM, rest.getAuthorization(), 
        		empty, RestClientWrapper.METHOD_POST, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE,Boolean.TRUE, service,  TokenWrapper.class );
        log.info("==========>  RESPONSE =================> " + response ); 
        if( response != null && response.get("error") == null) {
        	TokenWrapper tmp = (TokenWrapper)response.get(ReRestClient.RETURN_OBJECT);
            log.info("==========>  TOKEN WRAPPER =================> " + tmp ); 
        	Gson gsons = new GsonBuilder().create();
        	TokenWrapper tmp2 = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), TokenWrapper.class);
        	log.info("==========>  TOKEN WRAPPER =================> " + tmp2 ); 
        	if( tmp.getError() != null ) {
        		throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA OBTENCION DEL TOKEN DE SEGURIDAD, POR FAVOR CONSULTE A SU ADMINISTRADOR " + tmp.getErrorDescription() );

        	} else if( Long.valueOf(tmp.getExpiresIn() ) <= 60 ) {
        		throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "POR RAZONES DE SEGURIDAD SU TOKEN ESTA A PUNTO DE CADUCAR, POR FAVOR INTENTE EN 60 SEGUNDOS"  );

        	}else {
        		return tmp;        		
        	}
        } else {
        	throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, response.get("error_description").toString() );
        }
        
    }
}
