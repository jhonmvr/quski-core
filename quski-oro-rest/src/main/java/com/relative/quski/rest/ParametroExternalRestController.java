

package com.relative.quski.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.web.util.BaseRestController;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ParametrosSingleton;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@Path("/relative")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ParametroRestController - REST CRUD")
public class ParametroExternalRestController extends BaseRestController {

	@Inject
	Logger log;
	
	@Inject
	QuskiOroService sas;
	
	@Inject
	ParametrosSingleton ps;
	
	public ParametroExternalRestController() throws RelativeException {
		super();
	}

	
	
	@GET
	@Path("/getRelative")
	@ApiOperation(value = "Map<String,String> ", notes = "Metodo getParametrosSingleton", 
	response = Map.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = Map.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public Map<String,String> getParametrosSingleton(@QueryParam("re000") String usuario) throws RelativeException {
		try {
			log.info("==> Ingresa a relative " );
			Map<String,String> ext=new HashMap<>();
			String keyUnencrypt= ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_KEY_UNENCRYPT).getValor();
			ext.put(QuskiOroConstantes.PARAM_EXT_SEG_API_URL, 
					modifyParametro(ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SEG_API_URL),keyUnencrypt ) );
			ext.put(QuskiOroConstantes.PARAM_EXT_SEG_URL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SEG_URL),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_SEG_USR_ROL_URL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SEG_USR_ROL_URL),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_SEG_ROL_OPTION_URL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SEG_ROL_OPTION_URL),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_SEG_API_GTW_CREDENTIAL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SEG_API_GTW_CREDENTIAL),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_API_URL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_API_URL),keyUnencrypt));
			ext.put(QuskiOroConstantes.PARAM_EXT_ROOT_URL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_ROOT_URL),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_ROOT_WEBSOCKET, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_ROOT_WEBSOCKET),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_API_URL_SERVICE, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_API_URL_SERVICE),keyUnencrypt) );
			ext.put(QuskiOroConstantes.PARAM_EXT_API_URL_TOKEN, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_API_URL_TOKEN),keyUnencrypt ) );
			ext.put(QuskiOroConstantes.PARAM_EXT_KEY_UNENCRYPT, ps.getParametros().get(
					QuskiOroConstantes.PARAM_EXT_KEY_UNENCRYPT).getValor() );
			
			ext.put(QuskiOroConstantes.PARAM_EXT_SEG_API_URL_TOKEN, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SEG_API_URL_TOKEN),keyUnencrypt ) );
			ext.put(QuskiOroConstantes.PARAM_EXT_API_GTW_CREDENTIAL, modifyParametro(
					ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_API_GTW_CREDENTIAL),keyUnencrypt ) );
          
		      ext.put(QuskiOroConstantes.PARAM_EXT_IDLE_START, modifyParametro(
							ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_IDLE_START),keyUnencrypt ) );    
		      ext.put(QuskiOroConstantes.PARAM_EXT_IDLE_TIMEOUT, modifyParametro(
							ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_IDLE_TIMEOUT),keyUnencrypt ) ); 
		      ext.put(QuskiOroConstantes.PARAM_EXT_VERSION_FRONT, modifyParametro(
							ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_VERSION_FRONT),keyUnencrypt ) );         
		      ext.put(QuskiOroConstantes.PARAM_MEDIDA_CONVERSION, modifyParametro(
						ps.getParametros().get(QuskiOroConstantes.PARAM_MEDIDA_CONVERSION),keyUnencrypt ) );  
		      ext.put(QuskiOroConstantes.PARAM_DIAS_CP, modifyParametro(
						ps.getParametros().get(QuskiOroConstantes.PARAM_DIAS_CP),keyUnencrypt ) );  
			this.setParameterNotificacion(ext, keyUnencrypt);
			this.setParameterOro(ext, keyUnencrypt);
			this.setParameterBpm(ext, keyUnencrypt);
			return ext;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR ParametroExternalRestController getRelative, " + e.getMessage());
		}
		
	}
	
	private void setParameterNotificacion(Map<String,String> ext, String keyUnencrypt) throws RelativeException {
		ext.put(QuskiOroConstantes.PARAM_EXT_API_URL_NOTIFICACION, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_API_URL_NOTIFICACION),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_QUEUE_NAME_SMS, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_QUEUE_NAME_SMS),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_BROKER_NAME_SMS, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_BROKER_NAME_SMS),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_VERSION_ACTION_SMS, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_VERSION_ACTION_SMS),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_ACTION_NAME_SMS, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_ACTION_NAME_SMS),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_QUEUE_NAME_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_QUEUE_NAME_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_BROKER_NAME_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_BROKER_NAME_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_ACTION_NAME_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_ACTION_NAME_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_VERSION_ACTION_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_VERSION_ACTION_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_REPLY_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_REPLY_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_SUBJECT_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_SUBJECT_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_FROM_EMAIL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_FROM_EMAIL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_BROKER_VIRTUAL, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_BROKER_VIRTUAL),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_EXT_TEMPLATE_RESETEO, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_EXT_TEMPLATE_RESETEO),keyUnencrypt ) );
	}
	
	private String modifyParametro(TbMiParametro p, String key ) throws RelativeException{
		return  QuskiOroUtil.encodeBase64( p.getValor()+  key);
	}
	
	private void setParameterOro(Map<String,String> ext, String keyUnencrypt) throws RelativeException {
		ext.put(QuskiOroConstantes.PARAM_PRECIO_ORO_CD, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PRECIO_ORO_CD),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PRECIO_ORO_CP, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PRECIO_ORO_CP),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PROCENTAJE_TASACION, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PROCENTAJE_TASACION),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PORCENTAJE_TRANSPORTE, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PORCENTAJE_TRANSPORTE),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PORCENTAJE_GASTOS, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PORCENTAJE_GASTOS),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PORCENTAJE_REVALORACION, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PORCENTAJE_REVALORACION),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PORCENTAJE_CUSTODIA, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PORCENTAJE_CUSTODIA),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_PORCENTAJE_RENOVACION, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_PORCENTAJE_RENOVACION),keyUnencrypt ) );
	}
	
	private void setParameterBpm(Map<String,String> ext, String keyUnencrypt) throws RelativeException {
		ext.put(QuskiOroConstantes.PARAM_BPM_URL_BASE_API, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_BPM_URL_BASE_API),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_BPM_AUTH_API, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_BPM_AUTH_API),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_BPM_CONTAINER_ID, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_BPM_CONTAINER_ID),keyUnencrypt ) );
		ext.put(QuskiOroConstantes.PARAM_BPM_BUSINESS_PROCESS, modifyParametro(
				ps.getParametros().get(QuskiOroConstantes.PARAM_BPM_BUSINESS_PROCESS),keyUnencrypt ) );
		
	}
	
	
}

