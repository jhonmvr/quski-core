package com.relative.quski.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.ManagePropertiesFile;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroConstantes;



/**
 * Servlet implementation class ReporteTicketsServlet
 */
@WebServlet("/downloadSiniestroFileServlet")
public class DownloadFileHabilitanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Log log = LogFactory.getLog(DownloadFileHabilitanteServlet.class);
	
	@Inject
	private QuskiOroService sas;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFileHabilitanteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost( request,response );
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("=========>ENTRA EN SERVELT DOWNLOAD CONTRATO PDF PDF ");
		
		String idSiniestro=request.getParameter( "idHabiliante" );
		String tipo=request.getParameter( "tipo" );
		
		String idArchivo=request.getParameter( "idArchivo" );
		
		log.info("=========>PARAMETRO idSiniestro " + idSiniestro);
		log.info("=========>PARAMETRO tipo " + tipo);
		try {
			Properties props = ManagePropertiesFile.loadProperties(QuskiOroConstantes.FILE_NAME_NOTIFICACION_FILE_PROPS,
					System.getProperty(
					QuskiOroConstantes.JBOSS_CONFIG_DIR_PROPS) + 
					QuskiOroConstantes.SINIESTRO_AGRICOLA_CONF_DIR 
					 );
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 7");
			/*
			 * TbSaSiniestroArchivo sa=null; if( idArchivo != null && !idArchivo.isEmpty() )
			 * { sa = this.sas.findSiniestroArchivoById( Long.valueOf( idArchivo ) ); } else
			 * { sa = this.sas.findSiniestroArchivoBySiniestroAndTipo(
			 * Long.valueOf(idSiniestro), MidasOroUtil.getEnumFromString(FileTypeEnum.class,
			 * tipo)); }
			 */
			byte[] reportFile = null;
			
			/*
			 * if( sa == null || sa.getArchivo() == null ) { reportFile
			 * =Files.readAllBytes(new File(
			 * props.getProperty(MidasOroConstantes.PDF_DUMMY_PATH_PROPS) ).toPath() );
			 * //reportFile = IOUtils.toByteArray(new FileInputStream( )); } else {
			 * reportFile = sa.getArchivo(); }
			 */
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 9");
			response.setContentType("application/pdf");
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 10");
			response.addHeader("Content-Disposition", "attachment; filename=habilitante.pdf");
			
			
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 11");
			response.setContentLength((int) reportFile.length);
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 12");
			ByteArrayInputStream fileInputStream = new ByteArrayInputStream(reportFile);
			OutputStream responseOutputStream = response.getOutputStream();
			log.info("=========>ENTRA EN SERVELT DOWNLOAD SINIESTRO PDF PDF 13");
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}
		} catch (RelativeException e) {
			log.info( "====>error dopost " + e.getMessage() + "  " + e.getMensaje() );
			throw new ServletException("====>error dopost " + e.getMensaje()) ; 
		}catch (Exception e) {
			log.info( "====>error dopost " + e.getMessage() );
			throw new ServletException("====>error dopost " + e.getMessage()); 
		}
		
	}
	
	
}
