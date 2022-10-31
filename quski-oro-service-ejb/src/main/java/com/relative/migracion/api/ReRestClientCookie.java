package com.relative.migracion.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.RestClientWrapper;

public class ReRestClientCookie<T> {

	private static final Log logger = LogFactory.getLog(ReRestClientCookie.class);

	private static final int DEFAULT_TOTAL_POOL_CONNECTIONS = 500;
	private static final int DEFAULT_MAX_POOL_CONNECTIONS_PER_ROUTE = 50;
	static final List<String> cookiesKeys = new ArrayList<String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 7472659337780027295L;

		{
			add("JSESSIONID");
			add("TS0140ebf1");
			add("visid_incap_2414820");
			add("TS016a8bdb");
			add("incap_ses_1209_2414820");
			add("JSESSIONIDSSO");
			add("TS019ebd25");
		}
	};

	private static PoolingHttpClientConnectionManager connectionManager;
	private static CloseableHttpClient cachedClient;

	private RestClientWrapper cw;
	private HttpClient httpClient;
	private Object methodObject;
	private List<NameValuePair> nvps;

	public List<NameValuePair> getNvps() {
		return nvps;
	}

	public void setNvps(List<NameValuePair> nvps) {
		this.nvps = nvps;
	}

	public static final String RETURN_OBJECT = "resultado";
	public static final String RETURN_MESSAGE = "message";
	public static final String RETURN_ENTIDAD = "entidad";
	public static final String RETURN_RESPONSE = "response";

	// public static final String RETURN_STATUS="estado";
	public static final String RETURN_STATUS = "codigoServicio";

	private static final CookieStore cookieStore = new BasicCookieStore();
	private static final HttpContext httpContext = new BasicHttpContext();

	static {

		// setup pooling connection manager with default connections totals
		connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(DEFAULT_TOTAL_POOL_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_POOL_CONNECTIONS_PER_ROUTE);
	}

	public ReRestClientCookie(RestClientWrapper cw) {
		this.cw = cw;
		this.httpClient = getHttpClient(cw.getReadTimeout().intValue(), cw.getConnectTimeout().intValue());

		this.methodObject = configureRequest(cw.getMethod(), cw.getUrlStr(), cw.getAcceptHeader(),
				cw.getAcceptCharset(), cw.getHeaders());

		// this.classLoader = this.getClass().getClassLoader();
	}

	@SuppressWarnings("unused")
	public static void main2(String[] args) throws RelativeException {

		try {
			HttpClient client = new DefaultHttpClient();
			String url = expandSingleLevel("https://webmpsis.mutualistapichincha.com/cartera/app/home.jsf", client);
			System.out.println(url);
			/*HttpGet request = new HttpGet("https://webmpsis.mutualistapichincha.com/cartera/app/home.jsf");
			request.setHeader(HttpHeaders.CONTENT_TYPE, RestClientWrapper.CONTENT_TYPE_X_WWW_FORM);
			HttpResponse httpResponse = client.execute(request);
			String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			System.out.println(responseBody);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String expandSingleLevel(String url, HttpClient client) throws IOException {
		HttpGet request = null;
		HttpEntity httpEntity = null;
		InputStream entityContentStream = null;
		try {
			request = new HttpGet(url);
			HttpResponse httpResponse = client.execute(request);
			httpEntity = httpResponse.getEntity();
			entityContentStream = httpEntity.getContent();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 301 && statusCode != 302) {
				return url;
			}
			Header[] headers = httpResponse.getHeaders(HttpHeaders.LOCATION);
			//Preconditions.checkState(headers.length == 1);
			String newUrl = headers[0].getValue();
			return newUrl;
		} catch (IllegalArgumentException uriEx) {
			return url;
		} finally {
			if (request != null) {
				request.releaseConnection();
			}
			if (entityContentStream != null) {
				entityContentStream.close();
			}
			if (httpEntity != null) {
				EntityUtils.consume(httpEntity);
			}
		}
	}

	public static void main(String[] args) {

		try {
			RestClientWrapper cw = new RestClientWrapper();
			setParameters(cw);
			cw.setContent("");

			cw.setUrlStr("https://webmpsis.mutualistapichincha.com/cartera/app/home.jsf");
			ReRestClientCookie<String> b = new ReRestClientCookie<>(cw);
			httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			Map<String, Object> retorno = b.execute(String.class, httpContext);

			// RestClientWrapper cw2= new RestClientWrapper();
			// setParameters( cw2);
			StringBuilder headers = new StringBuilder();
			for (Cookie co : cookieStore.getCookies()) {
				if (cookiesKeys.remove(co.getName())) {
					headers.append(co.getName() + "=" + co.getValue() + "; ");
				}

			}
			cw.setCookie(headers.toString());
			// cw.setHeaders(headers.toString());
			// cw2.setHeaders("accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9;content-type=application/x-www-form-urlencoded");

			// System.out.println(httpContext.getAttribute(HttpClientContext.COOKIE_STORE));

			// System.out.println("retorno: " + retorno );
			// application/x-www-form-urlencoded
			// System.out.println(cw.toString());
			// ReRestClientCookie<String> conn1= new ReRestClientCookie<>( cw );
			// .execute(String.class,httpContext);

			//cw.setContent("j_username=3154&j_password=a80cb10d1201ae697a3cafd34cff19eaeae73f97b360c0c06352ab4dce4099a1");

			cw.setUrlStr("https://webmpsis.mutualistapichincha.com/cartera/app/j_security_check");
			System.out.println(cw.toString());

			ReRestClientCookie<String> conn2 = new ReRestClientCookie<>(cw);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("j_username", "3154"));
			nvps.add(new BasicNameValuePair("j_password",
					"a80cb10d1201ae697a3cafd34cff19eaeae73f97b360c0c06352ab4dce4099a1"));
			conn2.setNvps(nvps);
			Map<String, Object> retorno2 = conn2.execute(String.class, httpContext);
			// System.out.println("retorno: " + retorno2 );

			/*
			 * TaskSummaryWrapper r=(TaskSummaryWrapper)retorno.get("resultado");
			 * System.out.println("===>sumary " + r.getTaskSummary());
			 * System.out.println("===>sumary " + r.getTaskSummary().get(0).getTaskId());
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setParameters(RestClientWrapper cw) {
		cw.setAcceptCharset("UTF-8");
		cw.setAcceptHeader(
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		// cw.setAuthorization("Basic a2llc2VydmVyOmtpZXNlcnZlcjEh");
		cw.setConnectTimeout(15000);

		// cw.setContent( "{}" );
		String content = "";
		// String content = "{}";
		cw.setContent(content);
		cw.setContentType(RestClientWrapper.CONTENT_TYPE_X_WWW_FORM);
		// cw.setHeaders("Content-Type=application/json;charset=utf-8");
		cw.setMethod(RestClientWrapper.METHOD_POST);
		// cw.setMethod( RestClientWrapper.METHOD_GET );
		cw.setPassword(null);
		cw.setUser(null);
		cw.setReadTimeout(15000);
		cw.setRequireLogin(Boolean.FALSE);
		// cw.setUrlStr("http://172.16.101.60:8280/kie-server/services/rest/server/containers/SINIESTRO-AGRICOLA_1.0.1/tasks/136?withInputData=true&withOutputData=true");
		// cw.setUrlStr("http://172.16.101.60:8280/kie-server/services/rest/server/queries/tasks/instances/process/46");
	}

	/**
	 * Metodo que se encarga de realizar la llamada a un api rest
	 * 
	 * @param contentType     Tipo de contenido puede ser json o xml
	 * @param athorization    Cabecera de tipo Authorization Ej: Basic w34dsdsds=
	 * @param contentAsString Contenido en formato JSON o XML
	 * @param method          Metodo de envio POST, PUT, GET
	 * @param headers         Cabeceras adicioanles en formato key=valor;key2=valor2
	 * @param user            usuario en caso de requerirse para authenticacion
	 * @param password        password en caso de requerirse para authenticacion
	 * @param readTimeout     Time out de conexion en electura de informacion
	 * @param connectTimeout  Timeout de conexion
	 * @param requireLogin    True si requiere usuari y password, false si solo
	 *                        requiere authorizacion
	 * @param serviceUrl      Url del servicio a utilizat
	 * @return Mapa de parametros que contiene:<br>
	 *         resultado: objeto retornado de tipo T en el servicio. <br>
	 *         message: mensaje de retorno.<br>
	 *         estado: codigo de estado de retorno
	 * @throws RelativeException
	 */
	public static <T> Map<String, Object> callRestApi(String contentTypeAccept, String contentType, String athorization,
			String contentAsString, String method, String headers, String user, String password, Integer readTimeout,
			Integer connectTimeout, Boolean requireLogin, Boolean transform, String serviceUrl, Class<T> classType)
			throws RelativeException {
		try {
			httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
			RestClientWrapper cw = new RestClientWrapper();
			cw.setAcceptCharset(QuskiOroConstantes.BPMS_REST_DEFAULT_CHARSET);
			setContentTypeAccept(contentTypeAccept, cw);
			setContentType(contentType, cw);
			cw.setAuthorization(athorization);
			cw.setConnectTimeout(connectTimeout);
			cw.setContent(contentAsString);
			cw.setHeaders(headers);
			setMethod(method, cw);
			cw.setPassword(password);
			cw.setUser(user);
			cw.setReadTimeout(readTimeout);
			cw.setRequireLogin(requireLogin);
			cw.setUrlStr(serviceUrl);
			cw.setTransform(transform);
			System.out.println("=========================>>>>>>");
			System.out.println(cw.toString());
			System.out.println("=========================>>>>>>");
			ReRestClientCookie<T> b = new ReRestClientCookie<>(cw);
			return b.execute(classType, httpContext);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR EN LA LLAMADA A API REST " + serviceUrl + "  " + e.getMessage());
		}

	}

	private static void setContentTypeAccept(String contentType, RestClientWrapper cw) throws RelativeException {
		if (RestClientWrapper.CONTENT_TYPE_JSON.equalsIgnoreCase(contentType)) {
			cw.setAcceptHeader(RestClientWrapper.CONTENT_TYPE_JSON);
		} else if (RestClientWrapper.CONTENT_TYPE_XML.equalsIgnoreCase(contentType)) {
			cw.setAcceptHeader(RestClientWrapper.CONTENT_TYPE_XML);
		} else if (RestClientWrapper.CONTENT_TYPE_X_WWW_FORM.equalsIgnoreCase(contentType)) {
			cw.setAcceptHeader(RestClientWrapper.CONTENT_TYPE_X_WWW_FORM);
		} else if (RestClientWrapper.CONTENT_TYPE_TEXT_XML.equalsIgnoreCase(contentType)) {
			cw.setAcceptHeader(RestClientWrapper.CONTENT_TYPE_TEXT_XML);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "DEBE DEFINIR TIPO DE CONTENIDO CONTENT-TYPE");

		}
	}

	private static void setContentType(String contentType, RestClientWrapper cw) throws RelativeException {
		if (RestClientWrapper.CONTENT_TYPE_JSON.equalsIgnoreCase(contentType)) {
			cw.setContentType(RestClientWrapper.CONTENT_TYPE_JSON);
		} else if (RestClientWrapper.CONTENT_TYPE_XML.equalsIgnoreCase(contentType)) {
			cw.setContentType(RestClientWrapper.CONTENT_TYPE_XML);
		} else if (RestClientWrapper.CONTENT_TYPE_X_WWW_FORM.equalsIgnoreCase(contentType)) {
			cw.setContentType(RestClientWrapper.CONTENT_TYPE_X_WWW_FORM);
		} else if (RestClientWrapper.CONTENT_TYPE_TEXT_XML.equalsIgnoreCase(contentType)) {
			cw.setContentType(RestClientWrapper.CONTENT_TYPE_TEXT_XML);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "DEBE DEFINIR TIPO DE CONTENIDO CONTENT-TYPE");
		}
	}

	private static void setMethod(String method, RestClientWrapper cw) throws RelativeException {
		if (RestClientWrapper.METHOD_GET.equalsIgnoreCase(method)) {
			cw.setMethod(RestClientWrapper.METHOD_GET);
		} else if (RestClientWrapper.METHOD_POST.equalsIgnoreCase(method)) {
			cw.setMethod(RestClientWrapper.METHOD_POST);
		} else if (RestClientWrapper.METHOD_PUT.equalsIgnoreCase(method)) {
			cw.setMethod(RestClientWrapper.METHOD_PUT);
		} else if (RestClientWrapper.METHOD_DELETE.equalsIgnoreCase(method)) {
			cw.setMethod(RestClientWrapper.METHOD_DELETE);
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "DEBE DEFINIR METODO DE ENVIO, GET POST PUT");
		}
	}

	public Map<String, Object> execute(Class<T> classType, HttpContext httpContext) throws RelativeException {
		try {
			Map<String, Object> results = new HashMap<String, Object>();
			HttpResponse response = this.doRequestWithAuthorization(this.httpClient, (RequestBuilder) this.methodObject,
					httpContext);
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
				results.put(ReRestClientCookie.RETURN_MESSAGE, "request to endpoint " + this.cw.getUrlStr()
						+ " successfully completed " + statusLine.getReasonPhrase());
			} else {
				logger.warn("Unsuccessful response from REST server (status: " + responseCode + ", endpoint: "
						+ this.cw.getUrlStr() + ", response: " + responseBody);
				results.put(ReRestClientCookie.RETURN_MESSAGE, "Unsuccessful response from REST server (status: "
						+ responseCode + ", endpoint: " + this.cw.getUrlStr() + ", response: " + responseBody);
			}
			results.put(ReRestClientCookie.RETURN_STATUS, responseCode);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR EN EL llamada SERVICIO REST " + e.getMessage());
		} finally {
			try {
				close(httpClient, methodObject);
			} catch (Exception e) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN el cierre de httpclien " + e.getMessage());
			}
		}
	}

	public Map<String, Object> execute(Class<T> classType) throws RelativeException {
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
			if ( responseCode >=300 && responseCode <400) {
				Header[] headers = response.getHeaders(HttpHeaders.LOCATION);
				//Preconditions.checkState(headers.length == 1);
				String newUrl = headers[0].getValue();
				cw.setUrlStr(newUrl);
				System.out.println("Nueva URL ===>>> " + newUrl);
				//return this.execute(classType);
			}
			if (responseCode >= 200 && responseCode < 300) {
				postProcessResult(responseBody, classType, contentType, results);
				results.put(ReRestClientCookie.RETURN_MESSAGE, "request to endpoint " + this.cw.getUrlStr()
						+ " successfully completed " + statusLine.getReasonPhrase());
			} else {
				logger.warn("Unsuccessful response from REST server (status: " + responseCode + ", endpoint: "
						+ this.cw.getUrlStr() + ", response: " + responseBody);
				results.put(ReRestClientCookie.RETURN_MESSAGE, "Unsuccessful response from REST server (status: "
						+ responseCode + ", endpoint: " + this.cw.getUrlStr() + ", response: " + responseBody);
			}
			results.put(ReRestClientCookie.RETURN_STATUS, responseCode);
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR EN EL llamada SERVICIO REST " + e.getMessage());
		} finally {
			try {
				close(httpClient, methodObject);
			} catch (Exception e) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN el cierre de httpclien " + e.getMessage());
			}
		}
	}

	private HttpClient getHttpClient(Integer readTimeout, Integer connectTimeout) {
		RequestConfig config = RequestConfig.custom().setSocketTimeout(readTimeout).setConnectTimeout(connectTimeout)
				// .setRelativeRedirectsAllowed(false)
				// .setRedirectsEnabled(false)
				.setCookieSpec(CookieSpecs.STANDARD).setConnectionRequestTimeout(connectTimeout).build();

		HttpClientBuilder clientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(config);// .disableRedirectHandling();
		HttpClient httpClient = clientBuilder.build();
		/*
		 * httpClient.setRedirectStrategy(new DefaultRedirectStrategy() { public boolean
		 * isRedirected(HttpRequest request, HttpResponse response, HttpContext context)
		 * { boolean isRedirect=false; try { isRedirect = super.isRedirected(request,
		 * response, context); } catch (ProtocolException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } if (!isRedirect) { int responseCode =
		 * response.getStatusLine().getStatusCode(); if (responseCode == 301 ||
		 * responseCode == 302) { return true; } } return isRedirect; }
		 */
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
			addCookieHeader(builder, this.cw.getCookie());
			addContentType(builder, this.cw.getContentType());
			setBodyForm(builder, this.nvps);

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

	private void addContentType(RequestBuilder builder, String charset) {
		if (charset != null) {
			builder.addHeader(HttpHeaders.CONTENT_TYPE, charset);
		}
	}
	private void setCharset(RequestBuilder builder, String charset) {
		if (charset != null) {
			builder.addHeader(HttpHeaders.ACCEPT_CHARSET, charset);
		}
	}

	private void addCookieHeader(RequestBuilder builder, String charset) {
		if (charset != null) {
			builder.addHeader("cookie", charset);
		}
	}

	private void addAcceptHeader(RequestBuilder builder, String value) {
		if (value != null) {
			builder.addHeader(HttpHeaders.ACCEPT, value);
		}
	}

	private void addTokenHeader(RequestBuilder builder, String value) {

		if (StringUtils.isNotBlank(value) && !value.equalsIgnoreCase("null")) {
			logger.info("xxxxxxxxxxxxxxxxxEntra a addTokenHeader builder con valor: " + value);
			builder.addHeader(HttpHeaders.AUTHORIZATION, value);
		}
	}

	private void setBody(RequestBuilder builder) {
		// backwards compat to "Content" parameter
		if (StringUtils.isNotBlank(this.cw.getContent())) {
			System.out.println("Cannot set body for REST request [" + "] ");
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

	private void setBodyForm(RequestBuilder builder, List<NameValuePair> nvps) {
		// backwards compat to "Content" parameter
		if (nvps != null) {
			try {
				String content = this.cw.getContent();
				if (!(content instanceof String)) {
					content = transformRequest(content, this.cw.getContentType());
				}
				builder.setEntity(new UrlEncodedFormEntity(nvps, this.cw.getAcceptCharset()));
			} catch (UnsupportedCharsetException | UnsupportedEncodingException e) {
				throw new RuntimeException(
						"Cannot set body for REST request [" + builder.getMethod() + "] " + builder.getUri(), e);
			}
		}
	}
	/*
	 * private void setBody(RequestBuilder builder) { // backwards compat to
	 * "Content" parameter if (this.cw.getContent() != null &&
	 * !this.cw.getContent().isEmpty()) { try { String content =
	 * this.cw.getContent();
	 * 
	 * StringEntity entity = new StringEntity((String) content,
	 * this.cw.getAcceptCharset()); builder.setEntity(entity); } catch
	 * (UnsupportedCharsetException e) { throw new RuntimeException(
	 * "Cannot set body for REST request [" + builder.getMethod() + "] " +
	 * builder.getUri(), e); } } }
	 */

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
		if (headers != null && !headers.isEmpty()) {
			for (String h : headers.split(";")) {
				String[] headerParts = h.split("=");
				if (headerParts.length == 2) {
					consumer.accept(headerParts[0], headerParts[1]);
				}
			}
		}
	}

	private HttpResponse doRequestWithAuthorization(HttpClient httpclient, RequestBuilder requestBuilder,
			HttpContext httpContext) {
		// no authorization
		if (!this.cw.getRequireLogin()) {
			HttpUriRequest request = requestBuilder.build();
			System.out.println("doRequestWithAuthorization httpContext ====>");
			for (Header h : request.getAllHeaders()) {
				System.out.println(h.getName() + ":" + h.getValue());
			}
			System.out.println(request.getAllHeaders());
			try {
				return httpclient.execute(request, httpContext);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(
						"Could not execute request [" + request.getMethod() + "] " + request.getURI(), e);
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

	private HttpResponse doRequestWithAuthorization(HttpClient httpclient, RequestBuilder requestBuilder) {
		// no authorization
		if (!this.cw.getRequireLogin()) {
			HttpUriRequest request = requestBuilder.build();
			System.out.println("doRequestWithAuthorization ====>");
			for (Header h : request.getAllHeaders()) {
				System.out.println(h.getName() + ":" + h.getValue());
			}
			System.out.println(request.getAllHeaders());
			try {
				return httpclient.execute(request);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(
						"Could not execute request [" + request.getMethod() + "] " + request.getURI(), e);
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
		if (cw.getTransform() != null && cw.getTransform()) {
			if (result != null && !StringUtils.isEmpty(result)) {
				try {
					Object resultObject = transformResult(classType, contentType, result);
					results.put(ReRestClientCookie.RETURN_OBJECT, resultObject);
				} catch (Throwable e) {
					throw new RuntimeException("Unable to transform respose to object", e);
				}
			} else {
				results.put(ReRestClientCookie.RETURN_OBJECT, result);
			}
		} else {
			results.put(ReRestClientCookie.RETURN_OBJECT, result);
		}
	}

	private Object transformResult(Class<?> clazz, String contentType, String content) throws Exception {

		if (contentType.toLowerCase().contains(RestClientWrapper.CONTENT_TYPE_JSON)) {
			// ObjectMapper mapper = new ObjectMapper();
			return content;
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

	public static <T> T fromXml(String xml, Class<T> clazz) throws RelativeException {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setAdapter(new NormalizedStringAdapter());

			Object o = unmarshaller.unmarshal(new StringReader(xml));
			return clazz.cast(o);
		} catch (IllegalStateException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"Error while deserializing a XML text to Object of type " + clazz + " " + e.getMessage());
		} catch (JAXBException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"Error while deserializing a XML text to Object of type " + clazz + " " + e.getMessage());
		}
	}

}
