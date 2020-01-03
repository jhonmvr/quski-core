package com.relative.midas.bpms.api;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.wrapper.RestClientWrapper;
import com.relative.quski.wrapper.SegUsuarioWrapper;

public class UsuarioRestClient {
	private RestClientWrapper ClienteWrapper;
	
	public UsuarioRestClient() {
		RestClientWrapper ClienteWrapper = new RestClientWrapper();
		ClienteWrapper.setAcceptCharset( "utf-8" );
		ClienteWrapper.setAcceptHeader( RestClientWrapper.CONTENT_TYPE_JSON );
		//ClienteWrapper.setAuthorization("Basic a2llc2VydmVyOmtpZXNlcnZlcjEh");
		ClienteWrapper.setConnectTimeout( 15000 );
		ClienteWrapper.setTransform(false);
		//ClienteWrapper.setContent( content );
		ClienteWrapper.setContentType(RestClientWrapper.CONTENT_TYPE_JSON  );
		ClienteWrapper.setHeaders(null);
		ClienteWrapper.setMethod( RestClientWrapper.METHOD_GET );
		//cw.setMethod( RestClientWrapper.METHOD_GET );
		ClienteWrapper.setPassword(null);
		ClienteWrapper.setUser(null);
		ClienteWrapper.setReadTimeout(15000);
		ClienteWrapper.setRequireLogin(Boolean.FALSE);
		this.ClienteWrapper = ClienteWrapper;
	}

	
	public static void main( String[] args )  {
		System.out.println("entrando a UsuarioRes");
		UsuarioRestClient r = new UsuarioRestClient();
		//r.getClienteWrapper().setContent(content);
		r.getClienteWrapper().setUrlStr("http://localhost:8080/core-security-web/resources/reusRestController/rese003");
		ReRestClient<SegUsuarioWrapper> b= new ReRestClient<>( r.getClienteWrapper() );
		try {
			
			
			
			/* Map<String, Object> retorno=b.execute(SegUsuarioWrapper.class);
			 System.out.println("retornoFull: " + retorno );
			 if(retorno.get("resultado") != null) {
				 System.out.println("retorno: " + retorno.get("resultado") );
			 }
			 Gson gson = new Gson();
			 Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
			 Map<String, Long> variables = gson.fromJson(String.valueOf(retorno.get("resultado")), empMapType);
			 System.out.println("===>datos has " +variables.get("list"));
			 gson = new Gson();
			 //variables = gson.fromJson(String.valueOf(variables.get("list")), empMapType);
			
			 PaginatedListWrapper<SegUsuarioWrapper> bean =  gson.fromJson(String.valueOf(retorno.get("resultado")), PaginatedListWrapper.class);
			 
			 System.out.println("===>nombre " +bean.getList().get(0).getSegundoNombre() );
			 

			 List<SegUsuarioWrapper> bean = (List<SegUsuarioWrapper>) new ObjectMapper().readValue(String.valueOf(variables), SegUsuarioWrapper.class);
			 System.out.println("===>datos has variable " +variables.get("variable-instance")); 
			 System.out.println("===>datos has variable " +variables.get("variable-instance").getClass().getName());
			 List<Map<String, Object>> ls=(List<Map<String, Object>>)variables.get("variable-instance");
			 if( ls != null ) {
				 for( Map<String, Object> x:ls ) {
					 System.out.println("===>datos has variable iterando " +x); 
				 }
			 }
			 TaskSummaryWrapper r=(TaskSummaryWrapper)retorno.get("resultado");
			 System.out.println("===>sumary " + r.getTaskSummary());
			 System.out.println("===>sumary " + r.getTaskSummary().get(0).getTaskId());*/
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public SegUsuarioWrapper addUsuario() {
		
		
		return null;
		
	}


	public RestClientWrapper getClienteWrapper() {
		return ClienteWrapper;
	}


	public void setClienteWrapper(RestClientWrapper clienteWrapper) {
		ClienteWrapper = clienteWrapper;
	}
}