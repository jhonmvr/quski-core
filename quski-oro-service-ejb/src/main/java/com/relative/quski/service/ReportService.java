package com.relative.quski.service;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.util.ReportGenerator;

/**
 * Session Bean implementation class ReportService
 */
@Stateless(mappedName = "reportService")
public class ReportService {

	@Inject
	Logger log;
	
	//@Resource(lookup="java:/datasources/midasDS")
    private DataSource sassDS;
	
    /**
     * Default constructor. 
     */
    public ReportService() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    public byte[] generateReportePDF( Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
        		log.info( "++++++>> Ingresa en el generateReportPdf con conexion  path " +  reportFilePath );
        		log.info( "++++++>> Ingresa en el generateReportPdf con para,etros " + parameters );
    			ReportGenerator rg = new ReportGenerator(sassDS.getConnection(),parameters,reportFilePath);
    			ByteArrayOutputStream baos = rg.generateReportWithDbConnection();
    			log.info( "++++++>> termina generateReportWithDbConnection ");
    			if( baos != null ){
    				return baos.toByteArray();
    			}
        	return null;
		} catch (SQLException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR EN LA GENERACION DE LA CONEXION A LA BDD");
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    
    public  byte[] generateReporteFromBeanPDF( @SuppressWarnings("rawtypes") List dataSource, Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
    		log.info( "++++++>> Ingresa en el generateReportPdf con conexion " + dataSource + " path " +  reportFilePath );
    		log.info( "++++++>> Ingresa en el generateReportPdf con parametros " + parameters );
			ReportGenerator rg = new ReportGenerator(dataSource,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportWithListDatasource();
			if( baos != null ){
				return baos.toByteArray();
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    
    public  byte[] generateReporteFromJSONPDF( String dataSource, Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
    		log.info( "++++++>> Ingresa en el generateReportPdf con conexion " + dataSource + " path " +  reportFilePath );
    		log.info( "++++++>> Ingresa en el generateReportPdf con para,etros " + parameters );
			ReportGenerator rg = new ReportGenerator(dataSource,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportWithJSONDatasource();
			if( baos != null ){
				return baos.toByteArray();
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    
    
    public byte[] generateReporteExcel( Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
    		ReportGenerator rg = new ReportGenerator(sassDS.getConnection(),parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportAsExcelJdbc();
			if( baos != null ){
				return baos.toByteArray();
			}
			return null;
		} catch (SQLException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR EN LA GENERACION DE LA CONEXION A LA BDD");
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    
    public byte[] generateReporteBeanExcel(@SuppressWarnings("rawtypes") List dataSource, Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
    		ReportGenerator rg = new ReportGenerator(dataSource,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportExcelWithListDatasource();
			if( baos != null ){
				return baos.toByteArray();
			}
			return null;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    
    public byte[] generateReporteBeanCsv(@SuppressWarnings("rawtypes") List dataSource, Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
    		ReportGenerator rg = new ReportGenerator(dataSource,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportCSVWithListDatasource();
			if( baos != null ){
				return baos.toByteArray();
			}
			return null;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    
    public byte[] generateReporteJSONExcel(String dataSource, Map<String, Object> parameters, String reportFilePath ) throws RelativeException{
    	try {
    		ReportGenerator rg = new ReportGenerator(dataSource,parameters,reportFilePath);
			ByteArrayOutputStream baos = rg.generateReportWithJSONDatasourceExcel();
			if( baos != null ){
				return baos.toByteArray();
			}
			return null;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"ERROR GENERAL generateAvisoSiniestro " + e.getMessage());
		}
    }
    

}
