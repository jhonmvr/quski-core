package com.relative.quski.bpms.api;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.CatalogoResponseWrapper;
import com.relative.quski.wrapper.EquifaxConsultaPersonaWrapper;
import com.relative.quski.wrapper.Informacion;
import com.relative.quski.wrapper.InformacionWrapper;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.SimularResponse;
import com.relative.quski.wrapper.TokenWrapper;

public class ApiGatewayClient {

	private static final Log log = LogFactory.getLog(ApiGatewayClient.class);
    private static final String urlToken = "token?";
    private static final String urlCalculadora = "quski-calculadora/1.0";
    private static final String urlHost = "https://apigw.quski.ec:";
    private static final String urlPort = "8243/";
    private static final String empty="{}";




	public static void main(String[] args) {
		try {
		
			String content ="<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\">\r\n" + 
					"   <soap:Header/>\r\n" + 
					"   <soap:Body>\r\n" + 
					"      <tem:ConsultaCliente>\r\n" + 
					"         <!--Optional:-->\r\n" + 
					"         <tem:XMlConsulta>\r\n" + 
					"         <![CDATA[\r\n" + 
					"<carga>\r\n" + 
					"    <XmlConsultaCliente><DatosCliente><TIPO_IDENTIFICACION>C</TIPO_IDENTIFICACION><IDENTIFICACION>1001161916</IDENTIFICACION><TIPO_CONSULTA>IF</TIPO_CONSULTA></DatosCliente></XmlConsultaCliente></carga>\r\n" + 
					"]]>\r\n" + 
					"       </tem:XMlConsulta>\r\n" + 
					"      </tem:ConsultaCliente>\r\n" + 
					" \r\n" + 
					"   </soap:Body>\r\n" + 
					"</soap:Envelope>";
		/*	content =	content.replace("--tipoidentificacion--", "C")
					.replace("--identificacion--", "1721395125")
					.replace("--tipoconsulta--", "CC")
					.replace("--calificacionmupi--","N");*/
			TokenWrapper token = getToken("https://apigw.quski.ec:8243/token?grant_type=client_credentials", "Basic RmlpeUhKUjN2SHIyanFqZzNpWjQ2WHVZaHJNYTpGcDFJY3pmT3Fsd19xQXVBOVZ0WG9hazNQOWNh");
			System.out.println("token======> getAccess_token " + token.getAccess_token());
			System.out.println("token======> getTokenType " + token.getToken_type());
			System.out.println("token======> getError " + token.getError());
			System.out.println("token======> getErrorDescription " + token.getError_description());
			System.out.println("token======> getExpires_in " + token.getExpires_in());
			InformacionWrapper s = ApiGatewayClient.callCuentaRest("https://apigw.quski.ec:8243/service-cuenta/1.0",
					token.getToken_type()+" "+token.getAccess_token(), content);
			System.out.println("valor del oro === >>>" + s.getINFOFINAN().getNUMEROCUENTA());
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
	public static Informacion callConsultarClienteEquifaxRest(String urlService, String authorization,String content)
			throws RelativeException, UnsupportedEncodingException {
		try {
			
			log.info("=====> wrapper " + content);
			String service = urlService;
			log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, content, RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, EquifaxConsultaPersonaWrapper.class);
			log.info("===> Respuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				String respuesta = String.valueOf(response.get(ReRestClient.RETURN_OBJECT));
				//log.info("nuevo resp " + respuesta);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				ByteArrayInputStream input =  new ByteArrayInputStream(respuesta.getBytes("UTF-8"));
				Document doc = builder.parse(input);
				Element root = doc.getDocumentElement();
				//log.info( "mensaje x " + root.getElementsByTagName("ConsultaNuevoClienteResult") );
				NodeList chn=root.getElementsByTagName("ConsultaNuevoClienteResult");
				for( int i=0; i<chn.getLength();i++) {
					log.info( "mensaje ConsultaNuevoClienteResult=====>>>>>>>>" + i + " - " + chn.item(i).getNodeName() + " " + chn.item(i).getTextContent()  );
					return ReRestClient.fromXml( chn.item(i).getTextContent(), Informacion.class);
				}
				return null;
				
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static SimularResponse callCalculadoraRest(String urlService,String authorization, String content)
			throws RelativeException {
		try {
			log.info("<================>  CONTENT  <================>"); 
			log.info(""+content); 
			//Gson gson = new Gson();
			//String jsonString = gson.toJson(wrapper);
		
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, content, RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, urlService, String.class);
	        
			log.info("==========>  RESPONSE =================> " + response + " <==================="); 
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				//Gson gsons = new GsonBuilder().create();
				//return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CalculadoraRespuestaWrapper.class);
				String stringResult =String.valueOf(response.get("resultado"));
				//log.info("===============> STRING RESULT ======> " + stringResult );
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				ByteArrayInputStream input =  new ByteArrayInputStream(stringResult.getBytes("UTF-8"));
				Document doc = builder.parse(input);
				Element root = doc.getDocumentElement();
				NodeList chn=root.getElementsByTagName("CalculadoraQuskiResult");
				for( int i=0; i<chn.getLength();i++) {
					log.info( "mensaje ConsultaNuevoClienteResult=====>>>>>>>>" + i + " - " + chn.item(i).getNodeName() + " " + chn.item(i).getTextContent()  );
					return ReRestClient.fromXml( chn.item(i).getTextContent(), SimularResponse.class);
				}
				return null;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static InformacionWrapper callCuentaRest(String urlService,String authorization, String content)
			throws RelativeException {
		try {
			log.info("<================>  CONTENT  <================>"); 
			log.info(""+content); 
			//Gson gson = new Gson();
			//String jsonString = gson.toJson(wrapper);
		
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, content, RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, urlService, String.class);
	        
			log.info("==========>  RESPONSE =================> " + response + " <==================="); 
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				//Gson gsons = new GsonBuilder().create();
				//return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CalculadoraRespuestaWrapper.class);
				String stringResult =String.valueOf(response.get("resultado"));
				//log.info("===============> STRING RESULT ======> " + stringResult );
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				ByteArrayInputStream input =  new ByteArrayInputStream(stringResult.getBytes("UTF-8"));
				Document doc = builder.parse(input);
				Element root = doc.getDocumentElement();
				NodeList chn=root.getElementsByTagName("ConsultaClienteResult");
				for( int i=0; i<chn.getLength();i++) {
					log.info( "mensaje INFO_FINAN=====>>>>>>>>" + i + " - " + chn.item(i).getNodeName() + " " + chn.item(i).getTextContent()  );
					return ReRestClient.fromXml( chn.item(i).getTextContent(), InformacionWrapper.class);
				}
				return null;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static TokenWrapper getToken(String urlService,String autorizacion) throws RelativeException{		
       
        String service = urlService;
        Map<String,Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
        		RestClientWrapper.CONTENT_TYPE_X_WWW_FORM, autorizacion, 
        		empty, RestClientWrapper.METHOD_POST, 
      		   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
      		   Boolean.FALSE,Boolean.TRUE, service,  TokenWrapper.class );
        log.info("==========>  RESPONSE =================> " + response ); 
        if( response != null && response.get("error") == null) {
        	
        	Gson gsons = new GsonBuilder().create();
        	TokenWrapper tmp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), TokenWrapper.class);
        	log.info("==========>  TOKEN WRAPPER =================> " + tmp ); 
        	if( tmp.getError() != null ) {
        		throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA OBTENCION DEL TOKEN DE SEGURIDAD, POR FAVOR CONSULTE A SU ADMINISTRADOR " + tmp.getError_description() );

        	} else {
        		return tmp;        		
        	}
        } else {
        	throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, response.get("error_description").toString() );
        }
        
    }
	
	public static CatalogoResponseWrapper getTipoOro(String urlService) throws RelativeException {		
       
        try {
			String service = urlService;
			Map<String,Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
					RestClientWrapper.CONTENT_TYPE_JSON, null, 
					empty, RestClientWrapper.METHOD_POST, 
				   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
				   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
				   Boolean.FALSE,Boolean.TRUE, service,  String.class );
			if( response != null && response.get("error") == null) {
				Gson gsons = new GsonBuilder().create();
				CatalogoResponseWrapper tmp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CatalogoResponseWrapper.class);

				if( tmp.getExisteError()) {
					throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, tmp.getMensaje() );

				} else {
					return tmp;        		
				}
			} else {
				throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, response.get("error_description").toString() );
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR LEER JSON");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
        
    }
}
