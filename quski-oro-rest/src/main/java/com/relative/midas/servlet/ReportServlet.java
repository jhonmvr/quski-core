package com.relative.midas.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ParametrosSingleton;
import com.relative.quski.service.ReportService;
import com.relative.quski.util.MidasOroConstantes;

/**
 * Servlet implementation class ReporteTicketsServlet
 */
@WebServlet("/reportServletMidas")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(ReportServlet.class);

	@Inject
	private ReportService reportService;

	@Inject
	private ParametrosSingleton ps;

	@Inject
	private QuskiOroService mos;
	


	/*
	 * @Inject private AvisoSiniestroService ass;
	 * 
	 * @Inject private SiniestroAgricolaService sas;
	 */

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("=========>ENTRA EN SERVELT REPORTE PDF ");
		String fechaDesde=request.getParameter( "fechaDesde" );
		String fechaHasta=request.getParameter( "fechaHasta" );
		String codigoContrato=request.getParameter( "codigoContrato" );
		String tipoContrato=request.getParameter( "tipoContrato" );
		String idContrato=request.getParameter( "idContrato" );
		String idAbono=request.getParameter("idAbono");
		String codigoJoya=request.getParameter( "codigoJoya" );
		String idJoya=request.getParameter( "idJoya" );
		String estado=request.getParameter( "estado" );
		String idAgencia=request.getParameter("idAgencia");
		String idVentaLote=request.getParameter( "idVentaLote" );
		String mainReportName=request.getParameter( "mainReportName" );
		String subReportOneName=request.getParameter( "subReportOneName" );
		String subReportTwoName=request.getParameter( "subReportTwoName" );
		String subReportThreeName=request.getParameter( "subReportThreeName" );
		String format=request.getParameter( "format" );
		String usuario=request.getParameter( "usuario" );
		Boolean isConsulta=request.getParameter( "consulta" )!= null && !request.getParameter( "consulta" ).isEmpty()?true:false;
		String path= this.ps.getParametros().get(MidasOroConstantes.PATH_REPORTE).getValor();
		log.info("=========>Es consulta " + isConsulta);
	
		try {
			Map<String, Object> map = this.getParameters(request);
			
			
			map.put("subReportOneName",  subReportOneName );
			map.put("subReportTwoName", subReportTwoName );
			map.put("subReportThreeName", subReportThreeName);
			map.put("mainReportName", mainReportName);
			map.put("REPORT_PATH", path );
			
			byte[] reportFile = null;
			
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8 " + path+mainReportName );
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8 1" + path+subReportOneName );
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8 2" + path+subReportTwoName );
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8 3" + path+subReportThreeName );
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 8.1 " + MidasOroConstantes.PREFIX_REPORT_MAIN_PATH +mainReportName );
			if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
				//reportFile = this.reportService.generateReporteFromBeanPDF(sins , map,
				reportFile = this.reportService.generateReporteFromBeanPDF(null , map, 
						path+mainReportName);
				log.info("=========>ENTRA EN SERVELT REPORTE PDF 9");
				response.setContentType("application/pdf");
				log.info("=========>ENTRA EN SERVELT REPORTE PDF 10");
				response.addHeader("Content-Disposition", "attachment; filename=reporteconsolidado.pdf");
			} else {
				//reportFile = this.reportService.generateReporteBeanCsv(sins,map,
				reportFile = this.reportService.generateReporteBeanCsv(null,map,		
						path+mainReportName );
				log.info("=========>ENTRA EN SERVELT REPORTE EXCEL 9");
				//response.setContentType("application/vnd.ms-excel");
				response.setContentType("text/csv");
				log.info("=========>ENTRA EN SERVELT REPORTE EXCCEL 10");
				//response.addHeader("Content-Disposition", "attachment; filename=reporteconsolidado.xls");
				response.addHeader("Content-Disposition", "attachment; filename=reporteconsolidado.csv");
			}			
			
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 11");
			response.setContentLength((int) reportFile.length);
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 12");
			ByteArrayInputStream fileInputStream = new ByteArrayInputStream(reportFile);
			OutputStream responseOutputStream = response.getOutputStream();
			log.info("=========>ENTRA EN SERVELT REPORTE PDF 13");
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			log.info( "====>error dopost " + e.getMessage() + "  " + e.getDetalle());
			throw new ServletException("====>error dopost " + e.getMensaje()) ; 
		}catch (Exception e) {
			e.printStackTrace();
			log.info( "====>error dopost " + e.getMessage() );
			throw new ServletException("====>error dopost " + e.getMessage()); 
		}
		
	}

	private Map<String, Object> getParameters(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String fechaDesde = request.getParameter("fechaDesde");
		String fechaHasta = request.getParameter("fechaHasta");
		String codigoContrato = request.getParameter("codigoContrato");
		String idContrato = request.getParameter("idContrato");
		String codigoJoya = request.getParameter("codigoJoya");
		String idJoya = request.getParameter("idJoya");
		String idAgencia = request.getParameter("idAgencia");
		String idAbono = request.getParameter("idAbono");
		String estado = request.getParameter("estado");
		String idVentaLote = request.getParameter("idVentaLote");

		/*
		 * String noOficio=request.getParameter("No_OFICIO"); String
		 * asegurado=request.getParameter("ASEGURADO"); String
		 * canal1=request.getParameter("CANAL"); String
		 * noPoliza=request.getParameter("No_POLIZA"); String
		 * noReclamo=request.getParameter("No_RECLAMO"); String
		 * noTramite=request.getParameter("No_TRAMITE"); String
		 * provincia=request.getParameter("PROVINCIA"); String
		 * cultivo=request.getParameter("CULTIVO"); String
		 * causa1=request.getParameter("CAUSA"); String
		 * fechaOcurrencia=request.getParameter("FECHA_OCURRENCIA"); String
		 * rendimiento=request.getParameter("RENDIMIENTO"); String
		 * valorAjuste=request.getParameter("VALOR_AJUSTE"); String
		 * valorCosecha=request.getParameter("VALOR_COSECHA"); String
		 * montoRealinver=request.getParameter("MONT_REAL_INV"); String
		 * cultivoPoliza=request.getParameter("CULTIVO_POLIZA"); String
		 * cultivoInspeccion=request.getParameter("CULTIVO_INSPEC"); String
		 * sitioContratado=request.getParameter("SITIO_CONTRATADO"); String
		 * sitioInspeccion=request.getParameter("SITIO INSPECCION"); String
		 * fechaIniciovigencia=request.getParameter("FECH_INI_VIGENCIA"); String
		 * fechaFinvigencia=request.getParameter("FECH_FIN_VIGENCIA"); String
		 * fechaAvisocosecha=request.getParameter("FECHA_AVI_COSE"); String
		 * diasPresentadoaviso=request.getParameter("DIAS_PRESENT_AVISO");
		 */

		log.info("=========>PARAMETRO fechaDesde " + fechaDesde);
		log.info("=========>PARAMETRO fechaHasta " + fechaHasta);
		log.info("=========>PARAMETRO codigoContrato " + codigoContrato);
		log.info("=========>PARAMETRO codigoJoya " + codigoJoya);
		log.info("=========>PARAMETRO idJoya " + idJoya);
		log.info("=========>PARAMETRO estado " + estado);
		log.info("=========>PARAMETRO idAbono" + idAbono);
		log.info("=========>PARAMETRO idAbono" + idVentaLote);
		log.info("=========>PARAMETRO idAbono" + idAgencia);
		

		map.put("fechaDesde", fechaDesde);
		map.put("fechaHasta", fechaHasta);
		map.put("codigoContrato", codigoContrato);
		map.put("idContrato", idContrato);
		map.put("idJoya", idJoya);
		map.put("codigoJoya", codigoJoya);
		map.put("idAbono", idAbono);
		map.put("estado", estado);
		map.put("codigoJoya", codigoJoya);
		map.put("idAgencia", idAgencia);
		map.put(idVentaLote, idVentaLote);

		/*
		 * map.put("No_RECLAMO", noReclamo); map.put("No_TRAMITE", noTramite);
		 * map.put("PROVINCIA", provincia); map.put("CULTIVO", cultivo);
		 * map.put("CAUSA", causa1); map.put("FECHA_OCURRENCIA", fechaOcurrencia);
		 * map.put("RENDIMIENTO", rendimiento); map.put("VALOR_AJUSTE", valorAjuste);
		 * map.put("VALOR_COSECHA", valorCosecha); map.put("MONT_REAL_INV",
		 * montoRealinver); map.put("CULTIVO_POLIZA", cultivoPoliza);
		 * map.put("CULTIVO_INSPEC", cultivoInspeccion); map.put("SITIO_CONTRATADO",
		 * sitioContratado); map.put("SITIO INSPECCION", sitioInspeccion);
		 * map.put("FECH_INI_VIGENCIA", fechaIniciovigencia);
		 * map.put("FECH_FIN_VIGENCIA", fechaFinvigencia); map.put("FECHA_AVI_COSE",
		 * fechaAvisocosecha); map.put("DIAS_PRESENT_AVISO", diasPresentadoaviso);
		 */
		return map;
	}

}
