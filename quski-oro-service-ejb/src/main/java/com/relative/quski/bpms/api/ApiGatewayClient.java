package com.relative.quski.bpms.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.ResponseValidarDocumentoWrapper;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.CatalogoResponseWrapper;
import com.relative.quski.wrapper.CuentaSoftBankResponseWrapper;
import com.relative.quski.wrapper.EquifaxConsultaPersonaWrapper;
import com.relative.quski.wrapper.Informacion;
import com.relative.quski.wrapper.InformacionWrapper;
import com.relative.quski.wrapper.IntegracionRespuestaWrapper;
import com.relative.quski.wrapper.ResponsePagoMupi;
import com.relative.quski.wrapper.ResponseWebMupi;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.SimularResponse;
import com.relative.quski.wrapper.SimularResponseExcepcion;
import com.relative.quski.wrapper.SoftbankRespuestaWrapper;
import com.relative.quski.wrapper.TokenWrapper;
import com.relative.quski.wrapper.ValidarDocumentoWrapper;

public class ApiGatewayClient {

	private static final Log log = LogFactory.getLog(ApiGatewayClient.class);
    private static final String empty="{}";




	public static void main(String[] args) {
		try {
		
		
			ResponseWebMupi res =  consultaWebMupi("JHON VALDEMAR ROMERO NAVARRETE", "JHON VALDEMAR",
					"ROMERO NAVARRETE", "1721395125", "Basic RmlpeUhKUjN2SHIyanFqZzNpWjQ2WHVZaHJNYTpGcDFJY3pmT3Fsd19xQXVBOVZ0WG9hazNQOWNh",
					"http://chandresh.info:8000/API",
					"3154",
					"Quski2020)");
			if(res != null) {
				System.out.println("detalle === >>>" + res.getDetalle() + " estado" + res.getEstado() + "mensaje " +res.getMensaje());
			}else {
				System.out.print("error");
			}
			
					
		} catch (RelativeException  e) {
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
	@SuppressWarnings("unused")
	public static Informacion callConsultarClienteEquifaxRest(String urlService, String authorization,String content)
			throws RelativeException, UnsupportedEncodingException {
		try {
			
			log.info("=====> wrapper " + content);
			String service = urlService;
			//log.info("===> callBpmsInitProcesss con servcio " + service);
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, content, RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, EquifaxConsultaPersonaWrapper.class);
			//log.info("===> Respuesta de servicio " + response);
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			
				try {
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO Equifax:"+ String.valueOf(response.get(ReRestClient.RETURN_OBJECT)));
				}
				
		
		}  catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO Equifax:");
		}
	}
	@SuppressWarnings("unused")
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
	
	
	
	@SuppressWarnings("unused")
	public static SimularResponseExcepcion callCalculadoraExcepcionadoRest(String urlService,String authorization, String content)
			throws RelativeException, ParserConfigurationException, UnsupportedEncodingException {
		try {
			log.info("<================>  CONTENT  <================>"); 
			log.info(""+content); 
		
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, content, RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, urlService, String.class);
	        
			log.info("==========>  RESPONSE =================> " + response + " <==================="); 
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				String stringResult =String.valueOf(response.get("resultado"));
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				ByteArrayInputStream input =  new ByteArrayInputStream(stringResult.getBytes("UTF-8"));
				Document doc = builder.parse(input);
				Element root = doc.getDocumentElement();
				NodeList chn=root.getElementsByTagName("CalculadoraQuskiResult");
				for( int i=0; i<chn.getLength();i++) {
					log.info( "mensaje ConsultaNuevoClienteResult=====>>>>>>>>" + i + " - " + chn.item(i).getNodeName() + " " + chn.item(i).getTextContent()  );
					return ReRestClient.fromXml( chn.item(i).getTextContent(), SimularResponseExcepcion.class);
				}
				return null;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:"+ String.valueOf(response.get(ReRestClient.RETURN_MESSAGE)));
			}
		} catch (NumberFormatException | SAXException | IOException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL LLAMAR SERVICIO wrapper:");
		} 
	}
/*
	@SuppressWarnings("unused")
	public static InformacionWrapper callCuentaRest(String urlService, String cedula)
			throws RelativeException {
		try {
			log.info("<================>  CONTENT  <================>"); 
			
			//Gson gson = new Gson();
			//String jsonString = gson.toJson(wrapper);
		
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, empty, empty, RestClientWrapper.METHOD_GET, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, urlService, String.class);
	        
			log.info("==========>  RESPONSE =================> " + response + " <==================="); 
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				String stringResult =String.valueOf(response.get("resultado"));
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
*/

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
	
	public static ResponseWebMupi consultaWebMupi(String nombreCompleto, String nombres, String apellidos,
			String cedulaCliente, String autorizacion, String urlService, String user, String pass) throws RelativeException {
		 try {
	        	StringBuilder param = new StringBuilder();
	            param.append( "nombreCompleto" ).append("=").append(nombreCompleto).append("&");
	            param.append( "nombres" ).append("=").append(nombres).append("&");
	            param.append( "apellidos" ).append("=").append(apellidos).append("&");
	            param.append( "cedulaCliente" ).append("=").append(cedulaCliente).append("&");
	            param.append( "user" ).append("=").append(user).append("&");
	            param.append( "password" ).append("=").append(pass);
	            urlService = urlService.concat("?").concat(param.toString());
	            String url = urlService.replace(" ", "%20").replace("'", "%27");
				Map<String,Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
						RestClientWrapper.CONTENT_TYPE_JSON, null, 
						empty, RestClientWrapper.METHOD_GET, 
					   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
					   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
					   Boolean.FALSE,Boolean.TRUE, url,  String.class );
				if( response != null && response.get("error") == null) {
					Gson gsons = new GsonBuilder().create();
					ResponseWebMupi tmp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), ResponseWebMupi.class);
					if(tmp.getExisteError()) {
						throw new RelativeException(tmp.getMensaje() );
					}
					return tmp;     
				} else {
					throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, response.get(ReRestClient.RETURN_OBJECT).toString() );
				}
			} catch (JsonSyntaxException e) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR LEER JSON");
			} catch (RelativeException e) {
				throw e;
			}
	}
	


	public static ResponsePagoMupi aplicarPago(String numeroPrestamo, String secuencial, String numeroTransaccion,
			BigDecimal valorDepositado, String autorizacion, String urlService) throws RelativeException {
		 try {
	        	StringBuilder body = new StringBuilder();
	        	body.append("{ "
	        			+ "   \"numero_prestamo\":\""+numeroPrestamo+"\", "
	        			+ "   \"secuencial\":\""+secuencial+"\",  "
	        			+ "   \"numero_transaccion\":\""+numeroTransaccion+"\", "
	        			+ "	 \"valor_depositado\":"+valorDepositado
	        			+ "}");
				Map<String,Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
						RestClientWrapper.CONTENT_TYPE_JSON, null, 
						body.toString(), RestClientWrapper.METHOD_POST, 
					   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
					   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
					   Boolean.FALSE,Boolean.TRUE, urlService,  String.class );
				if( response != null && response.get("error") == null) {
					Gson gsons = new GsonBuilder().create();
					ResponsePagoMupi tmp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), ResponsePagoMupi.class);
					if(tmp.getExisteError()) {
						throw new RelativeException(tmp.getMensaje() );
					}
					return tmp;     
				} else {
					throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, response.get(ReRestClient.RETURN_OBJECT).toString() );
				}
			} catch (JsonSyntaxException e) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR LEER JSON");
			} catch (RelativeException e) {
				throw e;
			}
	}

	public static ResponseValidarDocumentoWrapper validarDocumentoBot(ValidarDocumentoWrapper wrapper, String autorizacion, String urlService) throws RelativeException {
		 try {
			 Gson gson = new Gson();
			 String jsonString = gson.toJson(wrapper);
				Map<String,Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_JSON,
						RestClientWrapper.CONTENT_TYPE_JSON, null, 
						jsonString, RestClientWrapper.METHOD_POST, 
					   null, null, null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
					   QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, 
					   Boolean.FALSE,Boolean.TRUE, urlService,  String.class );
				if( response != null && response.get("error") == null) {
					Gson gsons = new GsonBuilder().create();
					ResponseValidarDocumentoWrapper tmp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), ResponseValidarDocumentoWrapper.class);
					if(tmp.getStatus_code()>299) {
						throw new RelativeException(tmp.getMensaje() );
					}
					return tmp;     
				} else {
					throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, response.get(ReRestClient.RETURN_OBJECT).toString() );
				}
			} catch (JsonSyntaxException e) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR LEER JSON");
			} catch (RelativeException e) {
				throw e;
			}
	}
}
