package com.relative.quski.wrapper;

import java.io.Serializable;

public class RestClientWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1500587640218947392L;
	public static final String PREFIX_BASIC="Basic ";
	public static final String PREFIX_BEARER="Bearer ";
	public static final String CONTENT_TYPE_JSON="application/json";
	public static final String CONTENT_TYPE_XML="application/xml";
	public static final String CONTENT_TYPE_TEXT_XML="text/xml";
	public static final String CONTENT_TYPE_X_WWW_FORM="application/x-www-form-urlencoded";
	public static final String METHOD_GET="GET";
	public static final String METHOD_POST="POST";
	public static final String METHOD_PUT="PUT";
	public static final String METHOD_DELETE="DELETE";
	
	
	private String urlStr;
	private String method;
	private String acceptHeader;
	private String acceptCharset;
	private String headers;
	private String user;
	private String password;
	private String authorization;
	private Integer connectTimeout;
	private Integer readTimeout;
	private String content;
	private String contentType;
	private Boolean transform;
	/**
	 * Este debe ir en false si no hay usuario pero si authorizacion
	 */
	private Boolean requireLogin;
	
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAcceptHeader() {
		return acceptHeader;
	}
	public void setAcceptHeader(String acceptHeader) {
		this.acceptHeader = acceptHeader;
	}
	public String getAcceptCharset() {
		return acceptCharset;
	}
	public void setAcceptCharset(String acceptCharset) {
		this.acceptCharset = acceptCharset;
	}
	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public Integer getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public Integer getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public static String getPrefixBasic() {
		return PREFIX_BASIC;
	}
	public static String getPrefixBearer() {
		return PREFIX_BEARER;
	}
	public static String getContentTypeJson() {
		return CONTENT_TYPE_JSON;
	}
	public static String getContentTypeXml() {
		return CONTENT_TYPE_XML;
	}
	public Boolean getRequireLogin() {
		return requireLogin;
	}
	public void setRequireLogin(Boolean requireLogin) {
		this.requireLogin = requireLogin;
	}
	
	
	
	public Boolean getTransform() {
		return transform;
	}
	public void setTransform(Boolean transform) {
		this.transform = transform;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("==============");
		sb.append("\n");
		sb.append("urlStr ");
		sb.append(urlStr);sb.append("\n");
		sb.append("method ");
		sb.append(method);sb.append("\n");
		sb.append("acceptHeader ");
		sb.append(acceptHeader);sb.append("\n");
		sb.append("acceptCharset ");
		sb.append(acceptCharset);sb.append("\n");
		sb.append("headers ");
		sb.append(headers);sb.append("\n");
		sb.append("user ");
		sb.append(user);sb.append("\n");
		sb.append("password ");
		sb.append(password);sb.append("\n");
		sb.append("authorization ");
		sb.append(authorization);sb.append("\n");
		sb.append("connectTimeout ");
		sb.append(connectTimeout);sb.append("\n");
		sb.append("readTimeout ");
		sb.append(readTimeout);sb.append("\n");
		sb.append("content ");
		sb.append(content);sb.append("\n");
		sb.append("contentType");
		sb.append(contentType);sb.append("\n");
		return sb.toString();
	}
	
	
	
}
