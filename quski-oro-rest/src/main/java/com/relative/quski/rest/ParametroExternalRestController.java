

package com.relative.quski.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.cache.CacheUtil;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.web.security.JWTTokenValidation;
import com.relative.core.web.util.BaseRestController;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.service.ParametrosSingleton;
import com.relative.quski.service.QuskiOroService;
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
	@JWTTokenValidation
	@ApiOperation(value = "Map<String,String> ", notes = "Metodo getParametrosSingleton", 
	response = Map.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = Map.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public Map<String,String> getParametrosSingleton(@QueryParam("prefix")  String prefix) throws RelativeException {
		try {
			log.info("==> Ingresa a relative " + prefix );
			Map<String,String> ext=new HashMap<>();
			Map<String,TbMiParametro> tmp=CacheUtil.getMap( TbMiParametro.class ,CacheUtil.DEFAULT_MAP  );
			String nameKeyUnencrypt=prefix + QuskiOroConstantes.PARAM_EXT_KEY_UNENCRYPT;
			if( tmp == null || tmp.isEmpty() ) {
				List<TbMiParametro> params= this.sas.findAllParametro(null);
				params.forEach( p->{
					try {
						CacheUtil.actionMap( CacheUtil.ACCION_UPDATE , p.getNombre() , p , CacheUtil.DEFAULT_MAP );
					} catch (RelativeException e) {
						e.printStackTrace();
					}
				} );
			}
			Map<String,TbMiParametro> cacheMap=CacheUtil.getMap( TbMiParametro.class ,CacheUtil.DEFAULT_MAP  );
			Set<String> keys= cacheMap.keySet();
			String keyUnencrypt= cacheMap.get(nameKeyUnencrypt).getValor();
			ext.put( nameKeyUnencrypt, QuskiOroUtil.encodeBase64( keyUnencrypt) );
			log.info("Llenando parametros nameKeyUnencrypt " + keyUnencrypt ) ;
			keys.forEach( k->{
				if( !k.equalsIgnoreCase(nameKeyUnencrypt) && k.startsWith(prefix+"RE") ) {
					log.info("Llenando parametros " + k ) ;
					try {
						ext.put( k , modifyParametro(cacheMap.get( k ),keyUnencrypt ) );
					} catch (RelativeException e) {
						e.printStackTrace();
					}
				}
			} );
			
			return ext;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR ParametroExternalRestController getRelative, " + e.getMessage());
		}
		
	}
	
	
	
	private String modifyParametro(TbMiParametro p, String key ) throws RelativeException{
		return  QuskiOroUtil.encodeBase64( p.getValor()+  key);
	}
	
	
	
	
}

