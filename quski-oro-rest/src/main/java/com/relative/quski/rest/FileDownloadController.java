package com.relative.quski.rest;

import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.cache.CacheUtil;
import com.relative.core.util.main.Constantes;
import com.relative.core.web.util.BaseRestController;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.service.GestorHabilitanteService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ReportService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.util.ReGenericFactory;
import com.relative.quski.wrapper.FileWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/downloadRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " FileUploadController - REST CRUD")
//@Api(value = "UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES",tags = {"UTILITARIO DE CARGA DE LOS ENUMERADORES EXISTENTES"})
public class FileDownloadController extends BaseRestController {
	
	@Inject 
	Logger log;
	
	@Inject
	private ReportService reportService;
	
	public FileDownloadController() throws RelativeException{
		super();
	}
	
	@POST
	@Path("/downloadFileHabilitante")
	@ApiOperation(value = "FileWrapper ", notes = "Metodo Post loadFile", 
	response = FileWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = FileWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public byte[]  downloadFileHabilitante(
			@QueryParam("objectFullName") String objectFullName,@QueryParam("methodName") String methodName,
			@QueryParam("idHabilitante")String idHabilitante, @QueryParam("idTipoDocumento")String idTipoDocumento, 
			@QueryParam("proceso")String proceso, @QueryParam("estadoOperacion") String estadoOperacion, 
			@QueryParam("idReferencia") String idReferencia,
			@QueryParam("format") String format,
			@QueryParam("mainReportName") String mainReportName
			) throws RelativeException {
		try {
			log.info("===============>downloadFileHabilitante "  );
			log.info("===============>downloadFileHabilitante objectFullName " + objectFullName);
			log.info("===============>downloadFileHabilitante methodName " + methodName );
			log.info("===============>downloadFileHabilitante idHabilitante " + idHabilitante );
			log.info("===============>downloadFileHabilitante idTipoDocumento " + idTipoDocumento );
			log.info("===============>downloadFileHabilitante proceso " + proceso );
			log.info("===============>downloadFileHabilitante estadoOperacion " + estadoOperacion );
			log.info("===============>downloadFileHabilitante idReferencia " + idReferencia );
			
			Map<String, Object> map = ReGenericFactory.generateDynamicReportParameter(
					objectFullName, methodName, idHabilitante, idTipoDocumento, 
					StringUtils.isEmpty(proceso)?null:QuskiOroUtil.getEnumFromString( ProcesoEnum.class , proceso), 
					StringUtils.isEmpty(estadoOperacion)?null:QuskiOroUtil.getEnumFromString( EstadoOperacionEnum.class , estadoOperacion), 
					idReferencia);
			byte[] reportFile= null;
			
			String pathReportes=CacheUtil.<TbMiParametro>getMapItem(
					"PATH_REPORTE",CacheUtil.DEFAULT_MAP , "", CacheUtil.getAmbiente()).getValor();
			map.put("REPORT_PATH", pathReportes );
			if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
				//reportFile = this.reportService.generateReporteFromBeanPDF(sins , map,
				reportFile = this.reportService.generateReporteFromBeanPDF(null , map, 
						pathReportes+mainReportName);
			} else {
				//reportFile = this.reportService.generateReporteBeanCsv(sins,map,
				reportFile = this.reportService.generateReporteBeanCsv(null,map,		
						pathReportes+mainReportName );
				log.info("=========>ENTRA EN SERVELT REPORTE EXCEL 9");
				
			}			
			return reportFile;
		}catch (RelativeException e) {
			throw e;
		}  
		 catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR Exception REGISTRO DE ARCHIVO");
		} 
	}
	
	
}
