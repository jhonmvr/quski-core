package com.relative.quski.bpms.api;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
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
    private static final String urlHost = "https://apigw.quski.ec:";
    private static final String urlPort = "8243/";
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
	public static CalculadoraRespuestaWrapper callCalculadoraRest(String authorization, String wrapper)
			throws RelativeException, UnsupportedEncodingException {
		try {
			//Gson gson = new Gson();
			//String jsonString = gson.toJson(wrapper);
			String jsonString = "<soap:Envelope\r\n" + 
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
			//byte[] content = jsonString.getBytes(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			String service = urlHost + urlPort + urlCalculadora;			
			Map<String, Object> response = ReRestClient.callRestApi(RestClientWrapper.CONTENT_TYPE_TEXT_XML,
					RestClientWrapper.CONTENT_TYPE_TEXT_XML, authorization, jsonString, RestClientWrapper.METHOD_POST, null, null,
					null, QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT,
					QuskiOroConstantes.BPMS_REST_TIMEOUT_DEFAULT, Boolean.FALSE, Boolean.FALSE, service, String.class);
	        
			log.info("==========>  RESPONSE =================> " + response + " <==================="); 
			Long status = Long.valueOf(String.valueOf(response.get(ReRestClient.RETURN_STATUS)));
			if(status>=200 && status < 300) {
				//Gson gsons = new GsonBuilder().create();
				//return gsons.fromJson((String) response.get(ReRestClient.RETURN_OBJECT), CalculadoraRespuestaWrapper.class);
				String stringResult = StringEscapeUtils.unescapeXml( String.valueOf(response.get("resultado")));
				log.info("===============> STRING RESULT ======> " + stringResult );
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
