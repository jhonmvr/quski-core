package com.relative.quski.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.web.util.BaseRestController;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.service.GestorHabilitanteService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.FileWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/uploadRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " FileUploadController - REST CRUD")
//@Api(value = "UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES",tags = {"UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES"})
public class FileUploadController extends BaseRestController {
	
	@Inject 
	Logger log;
	
	@Inject
	QuskiOroService qos;
	
	@Inject
	GestorHabilitanteService ghs;
	
	public FileUploadController() throws RelativeException{
		super();
	}
	
	@POST
	@Path("/loadFileHabilitante")
	@ApiOperation(value = "FileWrapper ", notes = "Metodo Post loadFileHabilitante Retorna  la entidad encontrada loadFile", response= FileWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = FileWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public FileWrapper  loadFile(FileWrapper fw) throws RelativeException {
		try {
			log.info("===============>loadFile "  );
			log.info("===============>loadFile FW getRelatedIdStr " + fw.getRelatedIdStr()  );
			log.info("===============>loadFile FW getProcess " + fw.getProcess() );
			log.info("===============>loadFile FW getTypeAction " + fw.getTypeAction() );
			if( fw.getFileBase64() == null || fw.getFileBase64().isEmpty() ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO LLEGA ARCHIVO");
			}
			
			fw.setFile(  QuskiOroUtil.convertBase64ToByteArray( fw.getFileBase64() ));
			log.info("===============>GENERADO " + fw.getFileBase64() );
			TbQoDocumentoHabilitante da= this.qos.generateDocumentoHabilitante(fw);
			log.info("===============>loadedeFile " + da.getId() );
			return fw;
		}catch (RelativeException e) {
			throw e;
		}  
		 catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR Exception REGISTRO DE ARCHIVO");
		} 
	}
	
	
	@POST
	@Path("/loadFileHabilitanteBuro")
	@ApiOperation(value = "FileWrapper ", notes = "Metodo Post loadFileHabilitante Retorna  la entidad encontrada loadFile", response= FileWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = FileWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public FileWrapper  loadFileHabilitanteBuro(FileWrapper fw) throws RelativeException {
		try {
			log.info("===============>loadFile "  );
			log.info("===============>loadFile FW getRelatedIdStr " + fw.getRelatedIdStr()  );
			log.info("===============>loadFile FW getProcess " + fw.getProcess() );
			log.info("===============>loadFile FW getTypeAction " + fw.getTypeAction() );
			if( fw.getFileBase64() == null || fw.getFileBase64().isEmpty() ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO LLEGA ARCHIVO");
			}
			
			fw.setFile(  QuskiOroUtil.convertBase64ToByteArray( fw.getFileBase64() ));
			log.info("===============>GENERADO " + fw.getFileBase64() );
			this.qos.generateDocumentoHabilitanteBuro(fw);
			
			return fw;
		}catch (RelativeException e) {
			throw e;
		}  
		 catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR Exception REGISTRO DE ARCHIVO");
		} 
	}
	
	@POST
	@Path("/loadFileHabilitanteSimplified")
	@ApiOperation(value = "FileWrapper ", notes = "Metodo Post loadFileHabilitanteSimplified Retorna  la entidad encontrada FileWrapper", response= FileWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = FileWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public FileWrapper  loadFileHabilitanteSimplified(FileWrapper fw) throws RelativeException {
		try {
			log.info("===============>loadFile "  );
			log.info("===============>loadFile FW getRelatedIdStr " + fw.getRelatedIdStr()  );
			log.info("===============>loadFile FW getRelatedId " + fw.getRelatedId()  );
			log.info("===============>loadFile FW getProcess " + fw.getProcess() );
			log.info("===============>loadFile FW tipo docimento id " + fw.getTypeAction() );
			log.info("===============>loadFile FW getEstadoOperacion " + fw.getEstadoOperacion() );
			//log.info("===============>GENERADO " + fw.getFileBase64() );
			TbQoDocumentoHabilitante da= this.ghs.generateDocumentoHabilitanteSimplified(fw);
			log.info("===============>loadedeFile " + da );
			fw.setRelatedId( da.getId() );
			return fw;
		}catch (RelativeException e) {
			throw e;
		}  
		 catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR Exception REGISTRO DE ARCHIVO");
		} 
	}
	
	
	
	
	
}
