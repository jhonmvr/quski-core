package com.relative.quski.bpms.api;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.xml.bind.JAXBContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.RestClientWrapper;

public class ReRestClient<T> {

	private static final Log logger = LogFactory.getLog(ReRestClient.class);

	private static final int DEFAULT_TOTAL_POOL_CONNECTIONS = 500;
	private static final int DEFAULT_MAX_POOL_CONNECTIONS_PER_ROUTE = 50;

	private static PoolingHttpClientConnectionManager connectionManager;
	private static CloseableHttpClient cachedClient;

	private RestClientWrapper cw;
	private HttpClient httpClient;
	private Object methodObject;
	
	public static final String RETURN_OBJECT="resultado";
	public static final String RETURN_MESSAGE="message";
	public static final String RETURN_ENTIDAD="entidad";

	// public static final String RETURN_STATUS="estado";
	public static final String RETURN_STATUS="codigoServicio";
	

	static {

		// setup pooling connection manager with default connections totals
		connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(DEFAULT_TOTAL_POOL_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_POOL_CONNECTIONS_PER_ROUTE);
	}

	public ReRestClient(RestClientWrapper cw) {
		this.cw = cw;
		this.httpClient = getHttpClient(cw.getReadTimeout().intValue(), cw.getConnectTimeout().intValue());

		this.methodObject = configureRequest(cw.getMethod(), cw.getUrlStr(), cw.getAcceptHeader(),
				cw.getAcceptCharset(), cw.getHeaders());

		//this.classLoader = this.getClass().getClassLoader();
	}
	
	@SuppressWarnings("unused")
	public static void main( String[] args ) {
		RestClientWrapper cw= new RestClientWrapper();
		cw.setAcceptCharset( "utf-8" );
		cw.setAcceptHeader( RestClientWrapper.CONTENT_TYPE_JSON );
		cw.setAuthorization("Basic a2llc2VydmVyOmtpZXNlcnZlcjEh");
		cw.setConnectTimeout( 15000 );
		cw.setTransform(false);
		//cw.setContent( "{}" );
		String content="{\r\n" + 
				"  \"montoContrato\": \"3500.00\",\r\n" + 
				"  \"plazoContrato\": \"92\",\r\n" + 
				"  \"porcentajeTasacion\":\"0.04\",\r\n" + 
				"  \"procentajeTransporte\":\"0.02\",\r\n" + 
				"  \"porcentajeGastoAdministrativo\":\"0.16\",\r\n" + 
				"  \"porcentajeRevaloracion\":\"0.04\"\r\n" + 
				"}";
		cw.setContent( content );
		cw.setContentType(RestClientWrapper.CONTENT_TYPE_JSON  );
		cw.setHeaders(null);
		cw.setMethod( RestClientWrapper.METHOD_POST );
		//cw.setMethod( RestClientWrapper.METHOD_GET );
		cw.setPassword(null);
		cw.setUser(null);
		cw.setReadTimeout(15000);
		cw.setRequireLogin(Boolean.FALSE);
		//cw.setUrlStr("http://172.16.101.60:8280/kie-server/services/rest/server/containers/SINIESTRO-AGRICOLA_1.0.1/tasks/136?withInputData=true&withOutputData=true");
		//cw.setUrlStr("http://172.16.101.60:8280/kie-server/services/rest/server/queries/tasks/instances/process/46");
		//cw.setUrlStr("http://172.16.101.60:8280/kie-server/services/rest/server/containers/SINIESTRO-AGRICOLA_1.0.4/processes/SINIESTRO-AGRICOLA.modeloFuncionalSiniestroAgricola/instances");
		cw.setUrlStr("http://192.168.100.182:8780/kie-server/services/rest/server/containers/midas-oro-bre_1.0.10/processes/midas-oro-bre.bpm-midas-bre/instances");
		//cw.setUrlStr("http://192.168.100.182:8780/kie-server/services/rest/server/queries/processes/instances/34/variables/instances");
		ReRestClient<String> b= new ReRestClient<>( cw );
		try {
			 Map<String, Object> retorno=b.execute(String.class);
			 System.out.println("retorno: " + retorno.get("resultado") );
			 Gson gson = new Gson();
			 Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
			 Map<String, Long> variables = gson.fromJson(String.valueOf(retorno.get("resultado")), empMapType);
			 //System.out.println("===>datos has " +variables);
			 /*System.out.println("===>datos has variable " +variables.get("variable-instance")); 
			 System.out.println("===>datos has variable " +variables.get("variable-instance").getClass().getName());
			 List<Map<String, Object>> ls=(List<Map<String, Object>>)variables.get("variable-instance");
			 if( ls != null ) {
				 for( Map<String, Object> x:ls ) {
					 System.out.println("===>datos has variable iterando " +x); 
				 }
			 }*/
			 /*TaskSummaryWrapper r=(TaskSummaryWrapper)retorno.get("resultado");
			 System.out.println("===>sumary " + r.getTaskSummary());
			 System.out.println("===>sumary " + r.getTaskSummary().get(0).getTaskId());*/
		} catch (RelativeException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Metodo que se encarga de realizar la llamada a un api rest
	 * @param contentType Tipo de contenido puede ser json o xml
	 * @param athorization Cabecera de tipo Authorization Ej: Basic w34dsdsds=
	 * @param contentAsString Contenido en formato JSON o XML
	 * @param method Metodo de envio POST, PUT, GET
	 * @param headers Cabeceras adicioanles en formato key=valor;key2=valor2
	 * @param user usuario en caso de requerirse para authenticacion
	 * @param password password  en caso de requerirse para authenticacion
	 * @param readTimeout Time out de conexion en electura de informacion
	 * @param connectTimeout Timeout de conexion
	 * @param requireLogin True si requiere usuari y password, false si solo requiere authorizacion
	 * @param serviceUrl Url del servicio a utilizat
	 * @return Mapa de parametros que contiene:<br> resultado: objeto retornado de tipo T en el servicio. <br>message: mensaje de retorno.<br>
	 * estado: codigo de estado de retorno 
	 * @throws RelativeException
	 */
	public static <T> Map<String, Object> callRestApi(String contentTypeAccept, String contentType, String athorization, String contentAsString, String method,
			String headers,String user, String password,
			Integer readTimeout, Integer connectTimeout, Boolean requireLogin, Boolean transform,
			String serviceUrl, Class<T> classType) throws RelativeException{
		try {
			RestClientWrapper cw= new RestClientWrapper();
			cw.setAcceptCharset( QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET );
			setContentTypeAccept(contentTypeAccept,cw);
			setContentType(contentType,cw);
			cw.setAuthorization(athorization);
			cw.setConnectTimeout( connectTimeout );
			cw.setContent( contentAsString );
			cw.setHeaders(headers);
			setMethod( method, cw );
			cw.setPassword(password);
			cw.setUser(user);
			cw.setReadTimeout(readTimeout);
			cw.setRequireLogin(requireLogin);
			cw.setUrlStr(serviceUrl);
			cw.setTransform(transform);
			ReRestClient<T> b= new ReRestClient<>( cw );
			return b.execute(classType);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR EN LA LLAMADA A API REST " + serviceUrl + "  " + e.getMessage());
		} 
		
	}
	
	private static void setContentTypeAccept( String contentType, RestClientWrapper cw) throws RelativeException {
		if( RestClientWrapper.CONTENT_TYPE_JSON.equalsIgnoreCase( contentType ) ) {
			cw.setAcceptHeader( RestClientWrapper.CONTENT_TYPE_JSON );
		} else if( RestClientWrapper.CONTENT_TYPE_XML.equalsIgnoreCase( contentType ) ) {
			cw.setAcceptHeader( RestClientWrapper.CONTENT_TYPE_XML );
		} else if( RestClientWrapper.CONTENT_TYPE_X_WWW_FORM.equalsIgnoreCase( contentType )){
			cw.setAcceptHeader( RestClientWrapper.CONTENT_TYPE_X_WWW_FORM );
		} else if( RestClientWrapper.CONTENT_TYPE_TEXT_XML.equalsIgnoreCase( contentType ) ){
			cw.setAcceptHeader( RestClientWrapper.CONTENT_TYPE_TEXT_XML );
		}else {
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"DEBE DEFINIR TIPO DE CONTENIDO CONTENT-TYPE" );
			
		}
	}
	
	private static void setContentType( String contentType, RestClientWrapper cw) throws RelativeException {
		if( RestClientWrapper.CONTENT_TYPE_JSON.equalsIgnoreCase( contentType ) ) {
			cw.setContentType( RestClientWrapper.CONTENT_TYPE_JSON );
		} else if( RestClientWrapper.CONTENT_TYPE_XML.equalsIgnoreCase( contentType ) ) {
			cw.setContentType( RestClientWrapper.CONTENT_TYPE_XML );
		} else if( RestClientWrapper.CONTENT_TYPE_X_WWW_FORM.equalsIgnoreCase( contentType ) ) {
			cw.setContentType( RestClientWrapper.CONTENT_TYPE_X_WWW_FORM );
		} else if(RestClientWrapper.CONTENT_TYPE_TEXT_XML.equalsIgnoreCase( contentType ) ) {
			cw.setContentType( RestClientWrapper.CONTENT_TYPE_TEXT_XML );
		}else {			
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"DEBE DEFINIR TIPO DE CONTENIDO CONTENT-TYPE" );
		}
	}
	
	private static void setMethod( String method, RestClientWrapper cw) throws RelativeException {
		if( RestClientWrapper.METHOD_GET.equalsIgnoreCase( method ) ) {
			cw.setMethod( RestClientWrapper.METHOD_GET );
		} else if( RestClientWrapper.METHOD_POST.equalsIgnoreCase( method ) ) {
			cw.setMethod( RestClientWrapper.METHOD_POST );
		} else if( RestClientWrapper.METHOD_PUT.equalsIgnoreCase( method ) ) {
			cw.setMethod( RestClientWrapper.METHOD_PUT );
		} else if( RestClientWrapper.METHOD_DELETE.equalsIgnoreCase( method ) ) {
			cw.setMethod( RestClientWrapper.METHOD_DELETE );
		} else {
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"DEBE DEFINIR METODO DE ENVIO, GET POST PUT" );
		}
	}
	
	

	public Map<String, Object>  execute(Class<T> classType) throws RelativeException{
		try {
			Map<String, Object> results = new HashMap<String, Object>();
			HttpResponse response = this.doRequestWithAuthorization(this.httpClient,
					(RequestBuilder) this.methodObject);
			StatusLine statusLine = response.getStatusLine();
			int responseCode = statusLine.getStatusCode();
			HttpEntity respEntity = response.getEntity();
			String responseBody = null;
			String contentType = null;
			if (respEntity != null) {
				responseBody = EntityUtils.toString(respEntity, this.cw.getAcceptCharset());
				System.out.println("====>responseBody " + responseBody);
				if (respEntity.getContentType() != null) {
					contentType = respEntity.getContentType().getValue();
				}
			}
			if (responseCode >= 200 && responseCode < 300) {
				postProcessResult(responseBody, classType, contentType, results);
				results.put(ReRestClient.RETURN_MESSAGE,"request to endpoint " + this.cw.getUrlStr() + " successfully completed " + statusLine.getReasonPhrase());
			} else {
					logger.warn("Unsuccessful response from REST server (status: " + responseCode + ", endpoint: " + this.cw.getUrlStr() + ", response: "+responseBody);
					results.put(ReRestClient.RETURN_MESSAGE, "Unsuccessful response from REST server (status: " + responseCode + ", endpoint: " + this.cw.getUrlStr() + ", response: "+responseBody);
			}
			results.put(ReRestClient.RETURN_STATUS, responseCode);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR EN EL llamada SERVICIO REST " + e.getMessage());
		} finally {
			try {
				close(httpClient, methodObject);
			} catch (Exception e) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR EN el cierre de httpclien " + e.getMessage());
			}
		}
	}

	private HttpClient getHttpClient(Integer readTimeout, Integer connectTimeout) {
		RequestConfig config = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectTimeout).build();
		HttpClientBuilder clientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(config);
		HttpClient httpClient = clientBuilder.build();
		return httpClient;
	}

	private Object configureRequest(String method, String urlStr, String acceptHeaderValue, String acceptCharset,
			String httpHeaders) {
		RequestBuilder builder = null;
		if ("GET".equals(method)) {
			builder = RequestBuilder.get().setUri(urlStr);
			addAcceptHeader(builder, acceptHeaderValue);
			addTokenHeader(builder, this.cw.getAuthorization());
			setCharset(builder, acceptCharset);
		} else if ("POST".equals(method)) {
			builder = RequestBuilder.post().setUri(urlStr);
			setBody(builder);
			addTokenHeader(builder, this.cw.getAuthorization());
			addAcceptHeader(builder, acceptHeaderValue);
		} else if ("PUT".equals(method)) {
			builder = RequestBuilder.put().setUri(urlStr);
			setBody(builder);
			addAcceptHeader(builder, acceptHeaderValue);
			addTokenHeader(builder, this.cw.getAuthorization());
		} else if ("DELETE".equals(method)) {
			builder = RequestBuilder.delete().setUri(urlStr);
			addAcceptHeader(builder, acceptHeaderValue);
			addTokenHeader(builder, this.cw.getAuthorization());
			setCharset(builder, acceptCharset);
		} else {
			throw new IllegalArgumentException("Method " + method + " is not supported");
		}
		addCustomHeaders(httpHeaders, builder::addHeader);
		return builder;

	}

	private void setCharset(RequestBuilder builder, String charset) {
		if (charset != null) {
			builder.addHeader(HttpHeaders.ACCEPT_CHARSET, charset);
		}
	}

	private void addAcceptHeader(RequestBuilder builder, String value) {
		if (value != null) {
			builder.addHeader(HttpHeaders.ACCEPT, value);
		}
	}

	private void addTokenHeader(RequestBuilder builder, String value) {
		logger.info("xxxxxxxxxxxxxxxxxEntra a addTokenHeader builder con valor: " + value);
		if (value != null) {
			builder.addHeader(HttpHeaders.AUTHORIZATION, value);
		}
	}

	private void setBody(RequestBuilder builder) {
		// backwards compat to "Content" parameter
		if (this.cw.getContent() != null && !this.cw.getContent().isEmpty()) {
			try {
				String content = this.cw.getContent();
				if (!(content instanceof String)) {
					content = transformRequest(content, this.cw.getContentType());
				}
				StringEntity entity = new StringEntity((String) content, ContentType.parse(this.cw.getContentType()));
				builder.setEntity(entity);
			} catch (UnsupportedCharsetException e) {
				throw new RuntimeException(
						"Cannot set body for REST request [" + builder.getMethod() + "] " + builder.getUri(), e);
			}
		}
	}

	private String transformRequest(Object data, String contentType) {
		try {
			if (contentType.toLowerCase().contains(RestClientWrapper.CONTENT_TYPE_JSON)) {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(data);
			} else if (contentType.toLowerCase().contains(RestClientWrapper.CONTENT_TYPE_XML)) {
				StringWriter stringRep = new StringWriter();
				JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { data.getClass() });
				jaxbContext.createMarshaller().marshal(data, stringRep);
				return stringRep.toString();
			}
		} catch (Exception e) {
			throw new RuntimeException("Unable to transform request to object", e);
		}
		throw new IllegalArgumentException(
				"Unable to find transformer for content type '" + contentType + "' to handle data " + data);
	}

	private void addCustomHeaders(String headers, BiConsumer<String, String> consumer) {
		if( headers != null && !headers.isEmpty() ) {
			for (String h : headers.split(";")) {
				String[] headerParts = h.split("=");
				if (headerParts.length == 2) {
					consumer.accept(headerParts[0], headerParts[1]);
				}
			}
		}
	}

	
	private HttpResponse doRequestWithAuthorization(HttpClient httpclient, RequestBuilder requestBuilder) {
		// no authorization
		if (!this.cw.getRequireLogin()) {
			HttpUriRequest request = requestBuilder.build();
			try {
				return httpclient.execute(request);
			} catch (Exception e) {
				throw new RuntimeException("Could not execute request [" + request.getMethod() + "] " + request.getURI(), e);
			}
		}

		// user/password
		String u = this.cw.getUser();
		String p = this.cw.getPassword();
		if (u == null) {
			throw new IllegalArgumentException("Could not find username");
		}
		if (p == null) {
			throw new IllegalArgumentException("Could not find password");
		}

		// basic auth
		URI requestUri = requestBuilder.getUri();

		HttpHost targetHost = new HttpHost(requestUri.getHost(), requestUri.getPort(), requestUri.getScheme());

		// Create AuthCache instance and add it: so that HttpClient thinks that it has
		// already queried (as per the HTTP spec)
		// - generate BASIC scheme object and add it to the local auth cache
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);

		// - add AuthCache to the execution context:
		HttpClientContext clientContext = HttpClientContext.create();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				// specify host and port, since that is safer/more secure
				new AuthScope(requestUri.getHost(), requestUri.getPort(), AuthScope.ANY_REALM),
				new UsernamePasswordCredentials(u, p));
		clientContext.setCredentialsProvider(credsProvider);
		clientContext.setAuthCache(authCache);

		// - execute request
		HttpUriRequest request = requestBuilder.build();
		try {
			return httpclient.execute(targetHost, request, clientContext);
		} catch (Exception e) {
			throw new RuntimeException("Could not execute request with preemptive authentication ["
					+ request.getMethod() + "] " + request.getURI(), e);
		}

	}

	private void postProcessResult(String result, Class<T> classType, String contentType, Map<String, Object> results) {
		if( cw.getTransform() ) {
			if ( result !=null && !StringUtils.isEmpty(result)) {
				try {
					Object resultObject = transformResult(classType, contentType, result);
					results.put(ReRestClient.RETURN_OBJECT, resultObject);
				} catch (Throwable e) {
					throw new RuntimeException("Unable to transform respose to object", e);
				}
			} else {
				results.put(ReRestClient.RETURN_OBJECT, result);
			}
		} else {
			results.put(ReRestClient.RETURN_OBJECT, result);
		}
	}

	private Object transformResult(Class<?> clazz, String contentType, String content) throws Exception {

		if (contentType.toLowerCase().contains(RestClientWrapper.CONTENT_TYPE_JSON)) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(content, clazz);
		} else if (contentType.toLowerCase().contains(RestClientWrapper.CONTENT_TYPE_XML)) {
			StringReader result = new StringReader(content);
			JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { clazz });
			return jaxbContext.createUnmarshaller().unmarshal(result);
		}
		logger.info(
				"Unable to find transformer for content type " + contentType + "  to handle for content " + content);
		// unknown content type, returning string representation
		return content;
	}

	protected void close(HttpClient httpClient, Object httpMethod) throws IOException {
			if (cachedClient == null) {
				((CloseableHttpClient) httpClient).close();
			}
	}

}
