package com.relative.quski.reporte.rest;

import java.util.HashMap;
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
import com.relative.core.util.main.ParametroWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.service.ReportService;
import com.relative.quski.util.QuskiBaseReport;
import com.relative.quski.util.QuskiOroUtil;


@Path("/ejemploReporteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjemploReporteRestController extends BaseRestController implements QuskiBaseReport {
	
	@Inject 
	Logger log;
	
	@Inject
	private ReportService reportService;

	public EjemploReporteRestController() throws RelativeException {
		super();
	}

	@Override
	@POST
	@Path("/generateReporte")
	public byte[] generateReporte(
			@QueryParam("idHabilitante")String idHabilitante, @QueryParam("idTipoDocumento")String idTipoDocumento, 
			@QueryParam("proceso")String proceso, @QueryParam("estadoOperacion") String estadoOperacion, 
			@QueryParam("idReferencia") String idReferencia,
			@QueryParam("format") String format) throws RelativeException {
		log.info("===============>downloadFileHabilitante "  );
		log.info("===============>downloadFileHabilitante format " + format );
		log.info("===============>downloadFileHabilitante idHabilitante " + idHabilitante );
		log.info("===============>downloadFileHabilitante idTipoDocumento " + idTipoDocumento );
		log.info("===============>downloadFileHabilitante proceso " + proceso );
		log.info("===============>downloadFileHabilitante estadoOperacion " + estadoOperacion );
		log.info("===============>downloadFileHabilitante idReferencia " + idReferencia );
		try {
			//COMUN
			Map<String, Object> map= new HashMap<>();
			ProcesoEnum procesoEnum=StringUtils.isEmpty( proceso )?QuskiOroUtil.getEnumFromString( ProcesoEnum.class , proceso):null;
			EstadoOperacionEnum estadoOperacionEnum=StringUtils.isEmpty( proceso )?QuskiOroUtil.getEnumFromString( EstadoOperacionEnum.class , estadoOperacion):null;
			
			String pathReportes=CacheUtil.<ParametroWrapper>getMapItem(
					"PATH_REPORTE",CacheUtil.DEFAULT_MAP , "local", CacheUtil.<ParametroWrapper>getAmbiente().getValor()).getValor();
			map.put("REPORT_PATH", pathReportes );
			byte[] reportFile= null;
			//FINALIZA COMUN
			
			//TU CODIGO AQUI
			String mainReportName="Test.jasper";
			map.put("subReportOneName",  "..." );
			map.put("subReportTwoName", "..." );
			map.put("subReportThreeName", "...");
			map.put("mainReportName", mainReportName);
			
			map.put("REFERENCIA", idReferencia);
			map.put("TIPO_DOCUMENTO", idTipoDocumento);
			map.put("PROCESO", proceso);
			map.put("ESTADO_OPERACION", estadoOperacion);
			//FINALIZA TU CODIGO AQUI
			
			
			//COMUN
			if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
				reportFile = this.reportService.generateReporteFromBeanPDF(null , map, pathReportes+mainReportName);
				log.info("=========>ENTRA EN SERVELT REPORTE PDF");
			} else {
				reportFile = this.reportService.generateReporteBeanCsv(null,map,pathReportes+mainReportName );
				log.info("=========>ENTRA EN SERVELT REPORTE EXCEL");
			}			
			//FINALIZA COMUM
			return reportFile;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA GENERACION DE REPORTE" );
		}

	}

	

	
	
}
