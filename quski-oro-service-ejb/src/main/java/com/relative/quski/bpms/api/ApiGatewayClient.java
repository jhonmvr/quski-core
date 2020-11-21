package com.relative.quski.bpms.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.JsonSyntaxException;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.EquifaxConsultaPersonaWrapper;
import com.relative.quski.wrapper.Informacion;
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
			String content ="<soap:Envelope\r\n" + 
					"	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n" + 
					"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
					"	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\r\n" + 
					"	<soap:Body>\r\n" + 
					"		<CalculadoraQuski\r\n" + 
					"			xmlns=\"http://tempuri.org/\">\r\n" + 
					"			<XMlConsulta>\r\n" + 
					"			<![CDATA[	<carga>\r\n" + 
					"					<XmlParametrosRiesgo>\r\n" + 
					"						<ParametrosRiesgo>\r\n" + 
					"				              <PerfilRiesgo>4</PerfilRiesgo>\r\n" + 
					"				              <OrigenOperacion>N</OrigenOperacion>\r\n" + 
					"				              <RiesgoTotal>15323.65</RiesgoTotal>\r\n" + 
					"				              <FechaNacimiento>28/07/1990</FechaNacimiento>\r\n" + 
					"				              <PerfilPreferencia>B</PerfilPreferencia>\r\n" + 
					"				              <AgenciaOriginacion>014</AgenciaOriginacion>\r\n" + 
					"				              <IdentificacionCliente>1722668702</IdentificacionCliente>\r\n" + 
					"				              <CalificacionMupi>S</CalificacionMupi>\r\n" + 
					"				              <CoberturaExcepcionada>0</CoberturaExcepcionada>\r\n" + 
					"				            </ParametrosRiesgo>\r\n" + 
					"					</XmlParametrosRiesgo>\r\n" + 
					"					<XmlGarantias>\r\n" + 
					"						<Garantias>\r\n" + 
					"							<Garantia>\r\n" + 
					"								<TipoJoya>ANI</TipoJoya>\r\n" + 
					"								<Descripcion>BUEN ESTADO</Descripcion>\r\n" + 
					"								<EstadoJoya>BUE</EstadoJoya>\r\n" + 
					"								<TipoOroKilataje>18K</TipoOroKilataje>\r\n" + 
					"								<PesoGr>7.73</PesoGr>\r\n" + 
					"								<TienePiedras>S</TienePiedras>\r\n" + 
					"								<DetallePiedras>PIEDRAS</DetallePiedras>\r\n" + 
					"								<DescuentoPesoPiedras>0.73</DescuentoPesoPiedras>\r\n" + 
					"								<PesoNeto>7.00</PesoNeto>\r\n" + 
					"								<PrecioOro>263.72</PrecioOro>\r\n" + 
					"								<ValorAplicableCredito>293.02</ValorAplicableCredito>\r\n" + 
					"								<ValorRealizacion>232.07</ValorRealizacion>\r\n" + 
					"								<NumeroPiezas>1</NumeroPiezas>\r\n" + 
					"								<DescuentoSuelda>0.00</DescuentoSuelda>\r\n" + 
					"							</Garantia>\r\n" + 
					"						</Garantias>\r\n" + 
					"					</XmlGarantias>\r\n" + 
					"					<XmlDescuentosNuevaOperacion>\r\n" + 
					"						<DescuentosOperacion>\r\n" + 
					"							<SaldoMontoCreditoAnterior></SaldoMontoCreditoAnterior>\r\n" + 
					"							<SaldoInteresCreditoAnterior></SaldoInteresCreditoAnterior>\r\n" + 
					"							<MoraCreditoAnterior></MoraCreditoAnterior>\r\n" + 
					"							<CobranzaCreditoAnterior></CobranzaCreditoAnterior>\r\n" + 
					"							<TipoCartera></TipoCartera>\r\n" + 
					"							<MontoFinanciadoCreditoAnterior></MontoFinanciadoCreditoAnterior>\r\n" + 
					"							<PlazoCreditoAnterior></PlazoCreditoAnterior>\r\n" + 
					"							<TipoCreditoAnterior></TipoCreditoAnterior>\r\n" + 
					"							<EstadoCreditoAnterior></EstadoCreditoAnterior>\r\n" + 
					"							<FechaEfectivaCreditoAnterior></FechaEfectivaCreditoAnterior>\r\n" + 
					"							<FechaVencimientoCreditoAnterior></FechaVencimientoCreditoAnterior>\r\n" + 
					"							<MontoSolicitadoCliente>0</MontoSolicitadoCliente>\r\n" + 
					"							<NumeroOperacionMadre></NumeroOperacionMadre>\r\n" + 
					"							<NumeroOperacionRenovar></NumeroOperacionRenovar>\r\n" + 
					"							<REFERENCIA_ADICIONAL></REFERENCIA_ADICIONAL>\r\n" + 
					"							<OperacionPropia></OperacionPropia>\r\n" + 
					"						</DescuentosOperacion>\r\n" + 
					"					</XmlDescuentosNuevaOperacion>\r\n" + 
					"				</carga> ]]>\r\n" + 
					"			</XMlConsulta>\r\n" + 
					"		</CalculadoraQuski>\r\n" + 
					"	</soap:Body>\r\n" + 
					"</soap:Envelope>";
		/*	content =	content.replace("--tipoidentificacion--", "C")
					.replace("--identificacion--", "1721395125")
					.replace("--tipoconsulta--", "CC")
					.replace("--calificacionmupi--","N");*/
			TokenWrapper token = getToken("https://apigw.quski.ec:8243/token?grant_type=client_credentials", "Basic RmlpeUhKUjN2SHIyanFqZzNpWjQ2WHVZaHJNYTpGcDFJY3pmT3Fsd19xQXVBOVZ0WG9hazNQOWNh");
			
			ApiGatewayClient.callCalculadoraRest("https://apigw.quski.ec:8243/quski-calculadora/1.0",
					token.getTokenType()+" "+token.getAccessToken(), content);
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
			throws RelativeException, UnsupportedEncodingException {
		try {
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
        	 TokenWrapper tmp = (TokenWrapper)response.get(ReRestClient.RETURN_OBJECT);
            log.info("==========>  TOKEN WRAPPER =================> " + tmp ); 
        	//Gson gsons = new GsonBuilder().create();
        	//TokenWrapper tmp = gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), TokenWrapper.class);
        	//log.info("==========>  TOKEN WRAPPER =================> " + tmp ); 
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
