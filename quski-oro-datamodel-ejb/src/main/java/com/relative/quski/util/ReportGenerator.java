package com.relative.quski.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ReportGenerator {
	
	private static final Log log = LogFactory.getLog(ReportGenerator.class);
	
	//@Resource(name="java:/datasources/geDS")
    private Connection geDB;
	
	private Map<String, Object> hm = null;
    private String reportName;
    private JRXmlDataSource jrxmlDS;
    @SuppressWarnings("rawtypes")
	private List dataSource;
    private String jsonObj;
     
 
    public ReportGenerator() {
       
 
    }
    
    public ReportGenerator(Connection geDB,Map<String, Object> map, String reportName) {
    	this.hm = map;
        this.reportName= reportName;
    	 this.geDB = geDB;
    }
    
    @SuppressWarnings("rawtypes")
	public ReportGenerator(List dataSource,Map<String, Object> map, String reportName) {
    	this.hm = map;
        this.reportName= reportName;
    	this.dataSource=dataSource;
    }
    
    public ReportGenerator(String jsonObj,Map<String, Object> map, String reportName) {
    	this.hm = map;
        this.reportName= reportName;
    	this.jsonObj=jsonObj;
    }
    
    
    @SuppressWarnings("unused")
	private static Connection mockJdbcConnection(){
    	try {
			String url = "jdbc:postgresql://localhost/relgedb";
			Properties props = new Properties();
			props.setProperty("user","relge");
			props.setProperty("password","relative.2012");
			//props.setProperty("ssl","true");
			return DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	//String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
    	//return DriverManager.getConnection(url);
    }
    
 
    public ReportGenerator(Map<String, Object> map) {
        this.hm = map;
        
 
    }
    
    public ReportGenerator(Map<String, Object> map, byte file[],String reportName, String xpathSource) throws FileNotFoundException, JRException{
        this.hm = map;
        this.reportName= reportName;
        this.jrxmlDS=new JRXmlDataSource(new ByteArrayInputStream( file ),xpathSource);
    }
    
    public ReportGenerator(Map<String, Object> map, String reportName) throws FileNotFoundException{
        this.hm = map;
        this.reportName= reportName;
    }
    
    public static void main(String[] args){
    	String path="C:\\wildfly-11.0.0.Final\\portalservicios_dir\\siniestro_agricola\\reporte\\";
    	String report="AGNG01.jasper";
    	try {
    		Map<String, Object> parameters= new HashMap<String,Object>();
    		parameters.put("ASEGURADO", "luis tamayo");
    		ReportGenerator rg = new ReportGenerator( "{}",parameters, path + report) ;
    		ByteArrayOutputStream baos = rg.generateReportWithJSONDatasource();
			if( baos != null ){
				Path pathx =Paths.get("C:\\wildfly-11.0.0.Final\\portalservicios_dir\\siniestro_agricola\\reporte\\testjona.pdf");
				Files.write(pathx, baos.toByteArray());
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	/*try {
    		String tipoReporte="TICKETS";
			String formato="PDF";
			String fechaDesde="2016-04-01";
			String fechaHasta="2016-04-31";
			String pos=" ";
			log.info("=======> pos " + pos );
			if( tipoReporte == null || tipoReporte.equalsIgnoreCase("") ||  
					formato == null || formato.equalsIgnoreCase("") ){
				throw new ServletException("ERROR ReporteVentasAndTicketsServlet NO SE ENVIA PARAMETROS REQUERIDOS TIPO REPORTE Y FORMATO" );
			}
			Properties prop = ManagePropertiesFile.loadProperties(Constantes.FILENAME_GLOBAL_PROPS);
			String path= prop.getProperty( Constantes.HOME_CONFIG_PROPS ) + 
					prop.getProperty( Constantes.REPORT_PATH_PROPS );
			Map<String, Object> parameters= new HashMap<String,Object>();
			if( fechaDesde != null && fechaDesde.length()>= 10 ){
				fechaDesde = fechaDesde.substring(0,10);
				Calendar cal = Calendar.getInstance();
				cal.setTime( UtilsGe.convertStringToDate(fechaDesde, Constantes.DATE_FORMAT_WEB) );
				if( cal.get( Calendar.DAY_OF_MONTH )> 0 ){
					cal.set( Calendar.DAY_OF_MONTH , cal.get( Calendar.DAY_OF_MONTH )-1);
				}
				parameters.put("FECHA_DESDE", cal.getTime() );
			}
			if( fechaHasta != null && fechaHasta.length()>= 10 ){
				fechaHasta = fechaHasta.substring(0,10);
				Calendar cal = Calendar.getInstance();
				cal.setTime( UtilsGe.convertStringToDate(fechaHasta, Constantes.DATE_FORMAT_WEB) );
				if( cal.get( Calendar.DAY_OF_MONTH )> 0 ){
					cal.set( Calendar.DAY_OF_MONTH , cal.get( Calendar.DAY_OF_MONTH )-1);
				}
				parameters.put("FECHA_HASTA", cal.getTime() );
			}
			parameters.put("POS", pos);
			String reportPath="";
			if( tipoReporte.equalsIgnoreCase( "TICKETS" ) ){
				parameters.put("SUBREPORT_DIR", path);
				reportPath=path +prop.getProperty(Constantes.REPORT_TICKETS_GENERAL_PROPS);
			} else {
				reportPath=path +prop.getProperty(Constantes.REPORT_VENTAS_GENERAL_PROPS);
			}
			byte[] reporte= null;
			log.info("=======> generando reporte"  );
			if( formato.equalsIgnoreCase( "XLS" ) ){
				reporte=  ReportGenerator.generateReportExcel(mockJdbcConnection(), reportPath , parameters );
			} else {
				reporte=  ReportGenerator.generateReportPdf(mockJdbcConnection(), reportPath , parameters );
			}
			log.info("=======> reporte generado " + reporte );
			FileOutputStream fileOuputStream = 
	                  new FileOutputStream("C:\\relge/report/reporteXX.pdf"); 
		    fileOuputStream.write(reporte);
		    fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    }
 
    
 
    
 
    /** this method will call the report from data source*/
    private ByteArrayOutputStream generateReport() {
        try {
            
            try {
            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, this.jrxmlDS);
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos); 
                return baos;
            } catch (JRException e) {
                e.printStackTrace();
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo que carga genera el reporte recibiendo la conexion de base de datos, la logica de datos se maneja en el reporte
     * @param reportFilePath path completo del nombre del archivo del reporte
     * @param parameters parametros enviados al reporte
     * @return reporte generado
     */
    public byte[] generateReportPdfAsByteArray(  ){
    	
		ByteArrayOutputStream baos = generateReportWithDbConnection();
		if( baos != null ){
			return baos.toByteArray();
		}
    	return null;
    }
    
    public ByteArrayOutputStream generateReportWithDbConnection() {
        try {
            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
            try {
            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
            	 log.info( "====================> INGRESA A generateReportWithDbConnection this.reportName  " + this.reportName );
            	 log.info( "====================> INGRESA A generateReportWithDbConnection this.geDB  " + this.geDB.isClosed() );
                JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, this.geDB);
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos); 
                return baos;
            } catch (JRException e) {
                e.printStackTrace();
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ByteArrayOutputStream generateReportWithListDatasource() throws RelativeException{
        try {
            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
            try {
            	JasperPrint jasperPrint =null;
            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
            	 if( this.dataSource != null && !this.dataSource.isEmpty()  ) {
            		 jasperPrint = JasperFillManager.fillReport(this.reportName, hm, new JRBeanArrayDataSource(this.dataSource.toArray()) );
            	 } else {
            		 jasperPrint = JasperFillManager.fillReport(this.reportName, hm, new JREmptyDataSource() );
            	 }
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos); 
                return baos;
            } catch (JRException e) {
            	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + e.getMessage());
                throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " +e.getMessage());
            }
           
        } catch (Exception ex) {
        	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
        }
    }
    
    public ByteArrayOutputStream generateReportWithListExcelDatasource() throws RelativeException{
        try {
            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
            try {
            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
            	 JasperPrint jasperPrint =null;
                 if( this.dataSource != null && !this.dataSource.isEmpty() ) {
                	 jasperPrint = JasperFillManager.fillReport(this.reportName, hm, new JRBeanArrayDataSource(this.dataSource.toArray()) );
                 } else {
                	 jasperPrint = JasperFillManager.fillReport(this.reportName, hm, new JREmptyDataSource() );
                 }
            	 
                 JRXlsExporter exporter = new JRXlsExporter();
	             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
	             SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
	             configuration.setOnePagePerSheet(true);
	             configuration.setDetectCellType(true);
	             configuration.setCollapseRowSpan(false);
	             exporter.setConfiguration(configuration);
	             exporter.exportReport();
	             return baos;
            } catch (JRException e) {
            	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + e.getMessage());
                throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " +e.getMessage());
            }
           
        } catch (Exception ex) {
        	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
        }
    }
    
    
    public ByteArrayOutputStream generateReportWithJSONDatasource() throws RelativeException{
        try {
            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
            try {
            	ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(this.jsonObj.getBytes());
                //Create json datasource from json stream
            	JsonDataSource ds = new JsonDataSource(jsonDataStream);
            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, ds );
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos); 
                return baos;
            } catch (JRException e) {
            	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + e.getMessage());
                throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " +e.getMessage());
            }
           
        } catch (Exception ex) {
        	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
        }
    }
    
    public ByteArrayOutputStream generateReportWithJSONDatasourceExcel() throws RelativeException{
        try {
            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
            try {
            	ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(this.jsonObj.getBytes());
                //Create json datasource from json stream
            	JsonDataSource ds = new JsonDataSource(jsonDataStream);
            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, ds );
                JRXlsExporter exporter = new JRXlsExporter();
	             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
	             SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
	             configuration.setOnePagePerSheet(true);
	             configuration.setDetectCellType(true);
	             configuration.setCollapseRowSpan(false);
	             exporter.setConfiguration(configuration);
	             exporter.exportReport();
	             return baos;
            } catch (JRException e) {
            	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + e.getMessage());
                throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " +e.getMessage());
            }
           
        } catch (Exception ex) {
        	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
        }
    }
    
    /**
     * Metodo que generar un reporte basado en el xml enviado.
     * @param reportFilePath path completo del nombre del archivo del reporte
     * @param xpathSource origen de datos del xml
     * @param xmlForDs archivo como bytes
     * @param parameters parametros enviados al reporte
     * @return reporte generado
     */
    public static byte[] generateReportPdf( String reportFilePath, String xpathSource, byte[] xmlForDs, Map<String, Object> parameters ){
    	
    	try {
			ReportGenerator rg = new ReportGenerator(parameters,xmlForDs,reportFilePath,xpathSource);
			ByteArrayOutputStream baos = rg.generateReport();
			if( baos != null ){
				return baos.toByteArray();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
   
    
    /**
     * Metodo que carga genera el reporte recibiendo la conexion de base de datos, la logica de datos se maneja en el reporte
     * @param reportFilePath path completo del nombre del archivo del reporte
     * @param parameters parametros enviados al reporte
     * @return reporte generado
     */
    public static byte[] generateReportPdf( Connection conn, String reportFilePath,  Map<String, Object> parameters ){
    	
    	try {
    		//log.info( "++++++>> Ingresa en el generateReportPdf con conexion " + conn + " path " +  reportFilePath );
    		//log.info( "++++++>> Ingresa en el generateReportPdf con para,etros " + parameters );
			ReportGenerator rg = new ReportGenerator(conn,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportWithDbConnection();
			if( baos != null ){
				return baos.toByteArray();
			}
		} catch (Exception e) {
			log.info( "++++++>> ERROR EN LA GNERACION DEL REPORTE  " + conn + " path " +  reportFilePath );
		} 
    	return null;
    }
    
    //List<RelgeTickets> ticketsWithId
    @SuppressWarnings("rawtypes")
	public static byte[] generateReportPdf( List dataSource, String reportFilePath,  Map<String, Object> parameters ) throws RelativeException{
    	
    	try {
    		log.info( "++++++>> Ingresa en el generateReportPdf con conexion " + dataSource + " path " +  reportFilePath );
    		log.info( "++++++>> Ingresa en el generateReportPdf con para,etros " + parameters );
			ReportGenerator rg = new ReportGenerator(dataSource,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportWithListDatasource();
			if( baos != null ){
				return baos.toByteArray();
			}
		} catch (Exception e) {
			log.info( "++++++>> ERROR EN LA GNERACION DEL REPORTE   path " +  reportFilePath );
		} 
    	return null;
    }
    
	 public static byte[] generateReportExcel( Connection conn, String reportFilePath,  Map<String, Object> parameters ){
			try {
				ReportGenerator rg = new ReportGenerator(conn,parameters,reportFilePath);
				ByteArrayOutputStream baos = rg.generateReportAsExcelJdbc();
				if( baos != null ){
					return baos.toByteArray();
				}
			} catch (Exception e) {
				log.info("============================================");
	             log.info("ERROR generateReportAsExcel JRException " + e.getMessage());
	             log.info("============================================");
			}
		
    	return null;
    }
  
	 public ByteArrayOutputStream generateReportAsExcelJdbc() {
	     try {
	         try {
	         	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	             JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, this.geDB);
	             JRXlsExporter exporter = new JRXlsExporter();
	             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
	             SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
	             configuration.setOnePagePerSheet(true);
	             configuration.setDetectCellType(true);
	             configuration.setCollapseRowSpan(false);
	             exporter.setConfiguration(configuration);
	             exporter.exportReport();
	             return baos;
	         } catch (JRException e) {
	         	log.info("============================================");
	             log.info("ERROR generateReportAsExcel JRException " + e.getMessage());
	             log.info("============================================");
	         }
	         return null;
	     } catch (Exception ex) {
	     	log.info("============================================");
	         log.info("ERROR generateReportAsExcel Exception " + ex.getMessage());
	         log.info("============================================");
	         return null;
	     }
	 }
	 
	 @SuppressWarnings("unused")
	private ByteArrayOutputStream generateReportAsExcel() {
	     try {
	         try {
	         	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	             JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, this.jrxmlDS);
	             JRXlsExporter exporter = new JRXlsExporter();
	             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
	             SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
	             configuration.setOnePagePerSheet(true);
	             configuration.setDetectCellType(true);
	             configuration.setCollapseRowSpan(false);
	             exporter.setConfiguration(configuration);
	             exporter.exportReport();
	             return baos;
	         } catch (JRException e) {
	         	log.info("============================================");
	             log.info("ERROR generateReportAsExcel JRException " + e.getMessage());
	             log.info("============================================");
	         }
	         return null;
	     } catch (Exception ex) {
	     	log.info("============================================");
	         log.info("ERROR generateReportAsExcel Exception " + ex.getMessage());
	         log.info("============================================");
	         return null;
	     }
	 }
	 
	 public ByteArrayOutputStream generateReportExcelWithListDatasource() throws RelativeException{
	        try {
	            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
	            try {
	            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, new JRBeanArrayDataSource(this.dataSource.toArray()) );
	                JRXlsExporter exporter = new JRXlsExporter();
		             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		             SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		             configuration.setOnePagePerSheet(true);
		             configuration.setDetectCellType(true);
		             configuration.setCollapseRowSpan(false);
		             exporter.setConfiguration(configuration);
		             exporter.exportReport();
		             return baos;
	            } catch (JRException e) {
	            	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + e.getMessage());
	                throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " +e.getMessage());
	            }
	           
	        } catch (Exception ex) {
	        	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
	            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
	        }
	    }
	 
	 public ByteArrayOutputStream generateReportCSVWithListDatasource() throws RelativeException{
	        try {
	            //log.info( "====================> INGRESA A generateReportWithDbConnection CON conexion  " + this.geDB );
	            try {
	            	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                JasperPrint jasperPrint = JasperFillManager.fillReport(this.reportName, hm, new JRBeanArrayDataSource(this.dataSource.toArray()) );
	                JRCsvExporter exporter = new JRCsvExporter();
		             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		             exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));
		             SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
		             configuration.setRecordDelimiter("\r\n");
		             configuration.setFieldDelimiter(";");
		             exporter.setConfiguration(configuration);
		             exporter.exportReport();
		             return baos;
	            } catch (JRException e) {
	            	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + e.getMessage());
	                throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " +e.getMessage());
	            }
	           
	        } catch (Exception ex) {
	        	log.info("====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
	            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "====>ERROR EN LA GENERACION DEL REPORTE  " + ex.getMessage());
	        }
	    }
	 
	 
	 

}
