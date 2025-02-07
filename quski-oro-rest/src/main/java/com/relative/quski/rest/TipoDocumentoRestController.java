package com.relative.quski.rest;



import java.util.ArrayList;
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
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.enums.TipoPlantillaEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.service.DevolucionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ReportService;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.HabilitanteTerminacionContratoWrapper;
import com.relative.quski.wrapper.ObjetoHabilitanteWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/tipoDocumentoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "TipoDocumentoRestController - REST CRUD")
public class TipoDocumentoRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoTipoDocumento, GenericWrapper<TbQoTipoDocumento>> {
	
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	DevolucionService dos;
	@Inject
	DevolucionRepository devolucionRepository;
	

	@Inject
	private ParametroRepository parametroRepository;
	
	@Inject 
	ReportService rs;
	public TipoDocumentoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTipoDocumento>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiTipoDocumento", response = GenericWrapper.class)
	public GenericWrapper<TbQoTipoDocumento> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTipoDocumento> loc = new GenericWrapper<>();
		TbQoTipoDocumento a = this.qos.findTipoDocumentoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoTipoDocumento>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoCotizador", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoTipoDocumento> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoTipoDocumento> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTipoDocumento> plw = new PaginatedListWrapper<>(pw);
		List<TbQoTipoDocumento> actions = this.qos.findAllDocumento(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countdocumento().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTipoDocumento>", notes = "Metodo Post persistEntity registra y retorna GenericWrapper de la entidad  TbQoTipoDocumento", response = GenericWrapper.class)
	public GenericWrapper<TbQoTipoDocumento> persistEntity(GenericWrapper<TbQoTipoDocumento> wp) throws RelativeException {
		GenericWrapper<TbQoTipoDocumento> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageDocumento(wp.getEntidad()));
		return loc;
	}




	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}	
	
	@GET
	@Path("/getPlantilla")
	@ApiOperation(value = "id, format, identificacionCliente, nombreCliente, idCotizador, idNegociacion, idDevolucion, nombreAsesor, identificacionAsesor", 
	notes = "Metodo getPlantilla Retorna wrapper de entidades encontradas en ObjetoHabilitanteWrapper", 
	response = ObjetoHabilitanteWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
		public ObjetoHabilitanteWrapper getPlantilla(
			@QueryParam("id") String id,
			@QueryParam("format") String formato,
		    @QueryParam("identificacionCliente") String identificacionCliente,
		    @QueryParam("nombreCliente") String nombreCliente,
		    @QueryParam("idCotizador") String idCotizador,
		    @QueryParam("idNegociacion") String idNegociacion,
		    @QueryParam("idDevolucion") String idDevolucion,
		    @QueryParam("nombreAsesor") String nombreAsesor,
		    @QueryParam("identificacionAsesor") String identificacionAsesor
		  
		    ) throws RelativeException {
		log.info("===================> getPlantilla");
		log.info("===================> getPlantilla id " + id );
		log.info("===================> getPlantilla identificacionCliente " + identificacionCliente );
		log.info("===================> getPlantilla idCotizador " + idCotizador );
		log.info("===================> getPlantilla idNegociacion " + idNegociacion );
		log.info("===================> getPlantilla idDevolucion " + idDevolucion );
		
		log.info("================s===> getPlantilla format " + formato );
		Map<String, Object> map = new HashMap<>();
		//CAMBIAR PARA PONER EL PARAMETRO
		

		//String path= "C:\\WORKSPACE\\quski-oro-core\\quski-oro-rest\\src\\main\\resources\\reportes\\";
		//String path= "/home/relative/workspace/QUSKI/Quski-Oro/quski-oro-core/quski-oro-rest/src/main/resources/reportes/";
		
				
		String path= this.parametroRepository.findByNombre(QuskiOroConstantes.PATH_REPORTE).getValor();
		//log.info("================PATH===> P" +path);
		TbQoTipoDocumento td= this.qos.findTipoDocumentoById(Long.valueOf( id ) );
		this.setParameters(map,path,  identificacionCliente, nombreCliente, idCotizador, idNegociacion, idDevolucion, td);
		this.setReportData(map, path, identificacionCliente, nombreCliente, idCotizador, idNegociacion, idDevolucion, td);
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
			String idCotizador, String idNegociacion, String idDevolucion, TbQoTipoDocumento td){
		map.put("identificacionCliente", identificacionCliente);
		map.put("idCotizador", idCotizador);
		map.put("idNegociacion", idNegociacion);
		map.put("idDevolucion", idDevolucion);
		map.put("subReportOneName",  td.getPlantillaUno() );
		map.put("subReportTwoName", td.getPlantillaDos() );
		map.put("subReportThreeName", td.getPlantillaTres());
		map.put("mainReportName", td.getPlantilla());
		map.put("REPORT_PATH", path );
		//log.info("=========>ENTRA EN TipoDocumentoRestController setParameters " + path+td.getPlantilla() );
		//log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 1" + path+td.getPlantillaUno() );
		//log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 2" + path+td.getPlantillaDos() );
	//	log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 3" + path+td.getPlantillaTres() );
	}
	
	
	private void setReportData(Map<String, Object> map,String path, String identificacionCliente, String nombreCliente,
			String idCotizador, String idNegociacion, String idDevolucion,  TbQoTipoDocumento td) throws RelativeException{
		
		
		if( !StringUtils.isEmpty( identificacionCliente )  ) {
			if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.AB )==0  )  {
				map.put("BEAN_DS", qos.setAutorizacionBuroWrapper(identificacionCliente, nombreCliente, null, null) );
			} 
			
			
		}
		if( !StringUtils.isEmpty( idDevolucion )  ) {
			if(  td.getTipoPlantilla().compareTo( TipoPlantillaEnum.TC )==0  )  {
			//	map.put("BEAN_DS", dos.setAutorizacionBuroWrapper(identificacionCliente, nombreCliente) );
			} 
			
			
		}
	}
	
	private ObjetoHabilitanteWrapper generateReport(Map<String, Object> map,String path, String format,TbQoTipoDocumento td) throws RelativeException{
		byte[] reportFile = null;
		ObjetoHabilitanteWrapper ohw = new ObjetoHabilitanteWrapper();
		//String mainReportName = td.getPlantilla();
		log.info("REPORT PATH DATA ==>>>"+map.get("REPORT_PATH")+map.get("mainReportName"));
		if( Constantes.PDF_FILE_TYPE_EXTENSION.equalsIgnoreCase(format.trim()) ) {
			//reportFile = this.rs.generateReporteFromBeanPDF(sins , map,
			reportFile = this.rs.generateReporteFromBeanPDF(null , map, 
					map.get("REPORT_PATH")+map.get("mainReportName").toString());
			ohw.setDocumentoHabilitanteByte(reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport PDF9 " + reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport PDF9 " + reportFile.length);
		} else {
			reportFile = this.rs.generateReporteBeanCsv(null,map,		
					map.get("REPORT_PATH")+map.get("mainReportName").toString() );
			ohw.setDocumentoHabilitanteByte(reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile);
			log.info("=========>=========>ENTRA EN TipoDocumentoRestController generateReport EXCEL 9 " + reportFile.length);
		}	
		return ohw;
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
	
	@GET
	@Path("/getPlantillaDevolucion")
	@ApiOperation(value = "idTipoDocumento, format, idReferencia, nombreAsesor, identificacionAsesor", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en ObjetoHabilitanteWrapper", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
		public ObjetoHabilitanteWrapper getPlantillaDevolucion(
			@QueryParam("idTipoDocumento") String id,
			@QueryParam("format") String formato,
		    @QueryParam("idReferencia") String idDevolucion,
		    @QueryParam("nombreAsesor") String nombreAsesor,
		    @QueryParam("identificacionAsesor") String identificacionAsesor
		  
		    ) throws RelativeException {
		log.info("===================> getPlantilla");
		log.info("===================> getPlantilla id " + id );
		log.info("===================> getPlantilla idDevolucion " + idDevolucion );
		
		log.info("================s===> getPlantilla format " + formato );
		Map<String, Object> map = new HashMap<>();
		//CAMBIAR PARA PONER EL PARAMETRO
		

		//String path= "C:\\WORKSPACE\\quski-oro-core\\quski-oro-rest\\src\\main\\resources\\reportes\\";
		//String path= "/home/relative/workspace/QUSKI/Quski-Oro/quski-oro-core/quski-oro-rest/src/main/resources/reportes/";

		String path= this.parametroRepository.findByNombre(QuskiOroConstantes.PATH_REPORTE).getValor();
		//String path = "C:/Users/jukis/JaspersoftWorkspace/DevolucionQuski/";
		//log.info("================PATH===> P" +path);
		TbQoTipoDocumento td= this.qos.findTipoDocumentoById(Long.valueOf( id ) );
		this.setParametersDevolucion(map,path,    idDevolucion, td);
		this.setReportDataDevolucion(map, path,   idDevolucion, td,nombreAsesor);
		return this.generateReport(map, path, formato, td);
	}
	

	
	@GET
	@Path("/getPlantillaAutorizacion")
	@ApiOperation(value = "idTipoDocumento, format, idReferencia, nombreAsesor, identificacionAsesor", notes = "Metodo getEntityByTipoAndContrato Retorna wrapper de entidades encontradas en ObjetoHabilitanteWrapper", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
		public ObjetoHabilitanteWrapper getPlantillaAutorizacion(
			@QueryParam("idTipoDocumento") String id,
			@QueryParam("format") String formato,
		    @QueryParam("idReferencia") String idReferencia,
		    @QueryParam("nombreAsesor") String nombreAsesor,
		    @QueryParam("identificacionAsesor") String identificacionAsesor,
		    @QueryParam("autorizacion") String autorizacion,
		    @QueryParam("ciudadAgencia") String ciudadAgencia,
		    @QueryParam("proceso") String proceso
		  
		    ) throws RelativeException {
		log.info("===================> getPlantilla");
		log.info("===================> getPlantilla id " + id );
		log.info("===================> getPlantilla idReferencia " + idReferencia );
		
		log.info("================s===> getPlantilla format " + formato );
		Map<String, Object> map = new HashMap<>();
		//CAMBIAR PARA PONER EL PARAMETRO
		

		//String path= "C:\\WORKSPACE\\quski-oro-core\\quski-oro-rest\\src\\main\\resources\\reportes\\";
		//String path= "/home/relative/workspace/QUSKI/Quski-Oro/quski-oro-core/quski-oro-rest/src/main/resources/reportes/";

		String path= this.parametroRepository.findByNombre(QuskiOroConstantes.PATH_REPORTE).getValor();
		//String path = "C:/Users/jukis/JaspersoftWorkspace/DevolucionQuski/";
		//log.info("================PATH===> P" +path);
		TbQoCreditoNegociacion credito = null;
		TbQoTipoDocumento td= this.qos.findTipoDocumentoById(Long.valueOf( id ) );
		if(proceso.equals("AUTORIZACION")) {
			credito = this.qos.findCreditoByOperacionYOperacionMadre(idReferencia);
		}else {
			credito = this.qos.findCreditoByIdNegociacion(Long.valueOf(idReferencia));			
		}
		if(credito == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE ENCONTRO EL CREDITO ID: " + idReferencia);
		}
		String codigo = credito.getNumeroOperacion();
		if(StringUtils.isNotBlank(credito.getNumeroOperacionMadre())) {
			codigo = credito.getNumeroOperacionMadre();
		}
		
		map.put("BEAN_DS", qos.setAutorizacionBuroWrapper(credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente(),
				credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto(), ciudadAgencia,
				td.getProceso().equals(ProcessEnum.AUTORIZACION)? codigo : credito.getCodigo()) );
		map.put("subReportOneName",  td.getPlantillaUno() );
		map.put("subReportTwoName", td.getPlantillaDos() );
		map.put("subReportThreeName", td.getPlantillaTres());
		map.put("mainReportName", td.getPlantilla());
		map.put("REPORT_PATH", path );
		return this.generateReport(map, path, formato, td);
	}
	
	private void setParametersDevolucion(Map<String, Object> map,String path, 
		 String idDevolucion, TbQoTipoDocumento td){
	
		map.put("idDevolucion", idDevolucion);
	//	map.put("subReportOneName",  td.getPlantillaUno() );
	//	map.put("subReportTwoName", td.getPlantillaDos() );
	//	map.put("subReportThreeName", td.getPlantillaTres());
		
		map.put("REPORT_PATH", path );
		//log.info("=========>ENTRA EN TipoDocumentoRestController setParameters " + path+td.getPlantilla() );
		//log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 1" + path+td.getPlantillaUno() );
		//log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 2" + path+td.getPlantillaDos() );
	//	log.info("=========>ENTRA EN TipoDocumentoRestController setParameters  8 3" + path+td.getPlantillaTres() );
	}
	
	
	private void setReportDataDevolucion(Map<String, Object> map,String path, 
			 String idDevolucion,  TbQoTipoDocumento td, String nombreAsesor) throws RelativeException{
		
		
			
			
		log.info("Entra a set Report Data Devolucion ");
		if( !StringUtils.isEmpty( idDevolucion )  ) {
			TbQoDevolucion devolucion = this.devolucionRepository.findById(Long.valueOf(idDevolucion));
			if(devolucion == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA LA DEVOLUCION PARA EL ID:"+idDevolucion);
			}
			
			if(td.getProceso().equals(ProcessEnum.SOLICITUD)) {
				if(devolucion.getTipoCliente().equalsIgnoreCase(QuskiOroConstantes.DEUDOR)) {
					map.put("BEAN_DS", dos.setHabilitanteSolicitudDevolucion(Long.valueOf(idDevolucion), nombreAsesor) );
					map.put("mainReportName", td.getPlantilla());
					
				}else if(devolucion.getTipoCliente().equalsIgnoreCase(QuskiOroConstantes.HEREDERO)) {
					map.put("BEAN_DS", dos.setHabilitanteSolicitudDevolucionHeredero(Long.valueOf(idDevolucion),nombreAsesor) );
					map.put("LIST_DS", dos.setListaHerederosString(((Long.valueOf(idDevolucion)))));
					map.put("mainReportName", td.getPlantillaDos());
				}else if(devolucion.getTipoCliente().equalsIgnoreCase(QuskiOroConstantes.APODERADO)) {
					map.put("BEAN_DS", dos.setHabilitanteSolicitudDevolucionApoderado(Long.valueOf(idDevolucion),nombreAsesor ) );
					map.put("mainReportName", td.getPlantillaUno());
					
				} 
			}else if(td.getProceso().equals(ProcessEnum.ENTREGA)) {
				if(devolucion.getTipoCliente().equalsIgnoreCase(QuskiOroConstantes.DEUDOR)) {
					map.put("BEAN_DS", dos.setHabilitanteActaEntrega(Long.valueOf(idDevolucion),nombreAsesor ) );
					map.put("mainReportName", td.getPlantilla());
				}else if(devolucion.getTipoCliente().equalsIgnoreCase(QuskiOroConstantes.HEREDERO)) {
					map.put("BEAN_DS", dos.setHabilitanteActaEntregaHeredero(Long.valueOf(idDevolucion),nombreAsesor ) );
					map.put("LIST_DS", dos.setListaHerederosString(((Long.valueOf(idDevolucion)))));
					map.put("mainReportName", td.getPlantillaDos());
				}else if(devolucion.getTipoCliente().equalsIgnoreCase(QuskiOroConstantes.APODERADO)) {
					map.put("BEAN_DS", dos.setHabilitanteActaEntregaApoderado(Long.valueOf(idDevolucion),nombreAsesor ) );
					map.put("mainReportName", td.getPlantillaUno());
				} 
			}else if(td.getProceso().equals(ProcessEnum.TERMINACIONCONTRATO)) {
				List<HabilitanteTerminacionContratoWrapper> terminacionContrato = new ArrayList<>();
				terminacionContrato.add(dos.setHabilitanteTerminacionContrato(Long.valueOf(idDevolucion), nombreAsesor) );
				map.put("LIST_DS", terminacionContrato);
				map.put("mainReportName", td.getPlantilla());
			}
			
		
		
		}
	}
	
}
