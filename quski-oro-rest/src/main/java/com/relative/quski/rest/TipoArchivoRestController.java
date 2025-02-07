package com.relative.quski.rest;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.TipoPlantillaEnum;
import com.relative.quski.model.TbQoTipoArchivo;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ReportService;
import com.relative.quski.util.QuskiOroConstantes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/tipoArchivoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "TipoArchivoRestController - REST CRUD")
public class TipoArchivoRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoTipoArchivo, GenericWrapper<TbQoTipoArchivo>> {
	
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	ParametroRepository ps;
	
	@Inject 
	ReportService rs;
	public TipoArchivoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTipoArchivo>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoTipoArchivo", response = GenericWrapper.class)
	public GenericWrapper<TbQoTipoArchivo> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTipoArchivo> loc = new GenericWrapper<>();
		TbQoTipoArchivo a = this.qos.findTipoArchivoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoTipoArchivo>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoTipoArchivo", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoTipoArchivo> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoTipoArchivo> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTipoArchivo> plw = new PaginatedListWrapper<>(pw);
		List<TbQoTipoArchivo> actions = this.qos.findAllTipoArchivo(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countTipoArchivo().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTipoArchivo>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoTipoArchivo", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoTipoArchivo> persistEntity(GenericWrapper<TbQoTipoArchivo> wp) throws RelativeException {
		GenericWrapper<TbQoTipoArchivo> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageTipoArchivo(wp.getEntidad()));
		return loc;
	}




	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}	
	
	@GET
	@Path("/getPlantilla")
	@ApiOperation(value = "GenericWrapper<TbQoTipoDocumento>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbQoTipoDocumento", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
		public byte[] getPlantilla(
			@QueryParam("id") String id,
			@QueryParam("format") String formato,
		    @QueryParam("identificacionCliente") String identificacionCliente,
		    @QueryParam("nombreCliente") String nombreCliente,
		    @QueryParam("idCotizador") String idCotizador,
		    @QueryParam("idNegociacion") String idNegociacion
		  
		    ) throws RelativeException {
		log.info("===================> getPlantilla");
		log.info("===================> getPlantilla id " + id );
		log.info("===================> getPlantilla identificacionCliente " + identificacionCliente );
		log.info("===================> getPlantilla idCotizador " + idCotizador );
		log.info("===================> getPlantilla idNegociacion " + idNegociacion );
		
		log.info("================s===> getPlantilla format " + formato );
		Map<String, Object> map = new HashMap<>();
		
		//String path= "C:\\Users\\jukis\\JaspersoftWorkspace\\PrjQuskiReportes\\";
		String path = "";//= this.ps.getParametros().get(QuskiOroConstantes.PATH_REPORTE).getValor();
		TbQoTipoDocumento td= this.qos.findTipoDocumentoById(Long.valueOf( id ) );
		/*
		TbMiContrato ccc = null;
		if(StringUtils.isNotBlank(codigoContrato)) {
			ccc=this.mis.findContratoById( Long.valueOf( codigoContrato ) );
		}*/
		this.setParameters(map,path,  identificacionCliente, nombreCliente, idCotizador, idNegociacion, td);
		this.setReportData(map, path, identificacionCliente, nombreCliente, idCotizador, idNegociacion, td);
		return this.generateReport(map, path, formato, td);
	}
	/**
	 * LLena parametros base para el reporte
	 * @param map parametros a enviar al reporte
	 * @param identificacionCliente
	 * @param idCotizacion
	 * @param idNegociacion
	 * @param td
	 */
	private void setParameters(Map<String, Object> map,String path, String identificacionCliente,String nombreCliente,
			String idCotizador, String idNegociacion, TbQoTipoDocumento td){
		map.put("identificacionCliente", identificacionCliente);
		map.put("idCotizador", idCotizador);
		map.put("idNegociacion", idNegociacion);
		map.put("subReportOneName",  td.getPlantillaUno() );
		map.put("subReportTwoName", td.getPlantillaDos() );
		map.put("subReportThreeName", td.getPlantillaTres());
		map.put("mainReportName", td.getPlantilla());
		map.put("REPORT_PATH", path );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters " + path+td.getPlantilla() );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 1" + path+td.getPlantillaUno() );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 2" + path+td.getPlantillaDos() );
		log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 3" + path+td.getPlantillaTres() );
	}
	
	
	private void setReportData(Map<String, Object> map,String path, String identificacionCliente, String nombreCliente,
			String idCotizador, String idNegociacion,  TbQoTipoDocumento td) throws RelativeException{
		
		
		if( !StringUtils.isEmpty( identificacionCliente )  ) {

			log.info("*************************>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			log.info("Valor de tipo Plantilla"+td.getTipoPlantilla().toString());
			log.info("Valor del TipoPlantillaEnum"+TipoPlantillaEnum.AB);
			log.info("Valor comparado"+ ( td.getTipoPlantilla().compareTo( TipoPlantillaEnum.AB ) == 0  ));
			if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.AB ) == 0  )  {
				map.put("BEAN_DS", qos.setAutorizacionBuroWrapper(identificacionCliente, nombreCliente, null, null) );
			} 
			
			
		}
	}
	
	private byte[] generateReport(Map<String, Object> map,String path, String format,TbQoTipoDocumento td) throws RelativeException{
		byte[] reportFile = null;
		String mainReportName = td.getPlantilla();
		log.info("=========>ENTRA EN TipoDocumentoRestController generateReport  " + QuskiOroConstantes.PREFIX_REPORT_MAIN_PATH +td.getPlantilla() );
		if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
			//reportFile = this.rs.generateReporteFromBeanPDF(sins , map,
			reportFile = this.rs.generateReporteFromBeanPDF(null , map, 
					path+mainReportName);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport PDF9 " + reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport PDF9 " + reportFile.length);
		} else {
			reportFile = this.rs.generateReporteBeanCsv(null,map,		
					path+mainReportName );
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
		}	
		return reportFile;
	}
	/*
	@GET
	@Path("/getEntityByTipoDocumento")
	@ApiOperation(value = "GenericWrapper<TbQoTipoDocumento>", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en TbMiTipoDocumento", 
	response = GenericWrapper.class)
		public PaginatedListWrapper<TbQoTipoDocumento> getEntityByTipoDocumento(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoDocumento") String tipoDocumento,
		    @QueryParam("codigo") String codigo,
		    @QueryParam("idJoya") String idJoya,
		    @QueryParam("idAbono") String idAbono, 
		 	@QueryParam("idAgencia") String idAgencia,
	 		@QueryParam("idCorteCaja") String idCorteCaja) 
			throws RelativeException {
		log.info("===================> getEntityByTipoDocumento");
		log.info("===================> getEntityByTipoDocumento tipoDocumento " + tipoDocumento );
		log.info("===================> getEntityByTipoDocumento codigo " + codigo );
		return findAllDocumentoByParams(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
			sortFields, sortDirections, isPaginated),tipoDocumento,codigo, idJoya, idAbono, idCorteCaja);
		}
	
	
	
	*/
}
