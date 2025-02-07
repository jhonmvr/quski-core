package com.relative.quski.rest;

import java.math.BigDecimal;
import java.util.List;
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
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.ResponseValidarDocumentoWrapper;
import com.relative.quski.model.TbQoCompromisoPago;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoValidacionDocumento;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ValidacionCreditoService;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AprobacionNovacionWrapper;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.CompromisoParamsWrapper;
import com.relative.quski.wrapper.CompromisoReporteWrapper;
import com.relative.quski.wrapper.CrearRenovacionWrapper;
import com.relative.quski.wrapper.CreditoCompromisoWrapper;
import com.relative.quski.wrapper.CreditoCreadoSoftbank;
import com.relative.quski.wrapper.CuotasAmortizacionWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import com.relative.quski.wrapper.OpcionAndGarantiasWrapper;
import com.relative.quski.wrapper.OperacionCreditoNuevoWrapper;
import com.relative.quski.wrapper.RenovacionWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/creditoNegociacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "creditoNegociacionRestController - REST CRUD")
public class CreditoNegociacionRestController extends BaseRestController implements CrudRestControllerInterface<TbQoCreditoNegociacion, GenericWrapper<TbQoCreditoNegociacion>>{
	
	@Inject
	QuskiOroService qos;
	@Inject
	SoftBankApiClient sbac;
	@Inject
	Logger log;
	@Inject
	ValidacionCreditoService validacionCreditoService;
	
	public CreditoNegociacionRestController() throws RelativeException {
		super();
		//  Auto-generated constructor stub

	}


	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//  Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "TbQoCreditoNegociacion", notes = "Metodo que retorna credito negociacion por id", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCreditoNegociacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		TbQoCreditoNegociacion a = this.qos.findCreditoNegociacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	@Override
	public PaginatedListWrapper<TbQoCreditoNegociacion> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Path("/creditoNegociacionByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCreditoNegociacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAgente", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoCreditoNegociacion> creditoNegociacionByParams(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("fechaDesde") String fechaDesde, 
			@QueryParam("fechaHasta") String fechaHasta,
			@QueryParam("codigoOperacion") String codigoOperacion, 
			@QueryParam("proceso") String proceso,
			@QueryParam("identificacion") String identificacion,
			@QueryParam("cliente") String cliente,
			@QueryParam("agencia") String agencia,
			@QueryParam("estado") String estado
			) throws RelativeException {
		return creditoNegociacionByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				fechaDesde, fechaHasta, StringUtils.isNotBlank(codigoOperacion)?codigoOperacion:null,
						StringUtils.isNotBlank(proceso) ? QuskiOroUtil.getEnumFromString(ProcesoEnum.class, proceso)
						: null, identificacion,  Long.valueOf(agencia), cliente, 
						StringUtils.isNotBlank(estado) ? QuskiOroUtil.getEnumFromString(EstadoEnum.class, estado)
								: null);
		
	}
	private PaginatedListWrapper<TbQoCreditoNegociacion> creditoNegociacionByParams(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String codigoOperacion, ProcesoEnum proceso, String identificacion, Long agencia, String cliente,
			EstadoEnum estado ) throws RelativeException {
		
		PaginatedListWrapper<TbQoCreditoNegociacion> plw = new PaginatedListWrapper<>(pw);
		
		List<TbQoCreditoNegociacion> actions =null; 
			actions=this.qos.findCreditoNegociacionByParams(pw, fechaDesde, fechaHasta, identificacion, 
				proceso,codigoOperacion,  agencia, cliente, estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCreditoNegociacionByParams(fechaDesde, fechaHasta, identificacion, 
					proceso,codigoOperacion,  agencia, cliente, estado));
			plw.setList(actions);
		}
		return plw;
	}
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCreditoNegociacion>", notes = "Metodo Post persistEntity Registra  y retorna la entidad TbQoCreditoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCreditoNegociacion> persistEntity(GenericWrapper<TbQoCreditoNegociacion> wp)
			throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageCreditoNegociacion(wp.getEntidad()));
		return loc;
	}

	
	@GET
	@Path("/traerCreditoNegociacionExistente")
	@ApiOperation(value = "idNegociacion", notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada AprobacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<AprobacionWrapper> traerCreditoNegociacionExistente(@QueryParam("autorizacion") String autorizacion,@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<AprobacionWrapper> loc = new GenericWrapper<>();
		AprobacionWrapper a = this.qos.traerCreditoNegociacionExistente(Long.valueOf( idNegociacion ), autorizacion);

		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerCreditonovacionPorAprobar")
	@ApiOperation(value = "idNegociacion", notes = "Metodo Get  Retorna GenericWrapper de la entidad encontrada AprobacionNovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<AprobacionNovacionWrapper> traerCreditonovacionPorAprobar(@QueryParam("autorizacion") String autorizacion,@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<AprobacionNovacionWrapper> loc = new GenericWrapper<>();
		AprobacionNovacionWrapper a = this.qos.traerCreditonovacionPorAprobar(Long.valueOf( idNegociacion ), autorizacion);

		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/devolverAprobar")
	@ApiOperation(value = "idCredito", notes = "Metodo Get  Retorna GenericWrapper booleano", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Boolean> devolverAprobar(@QueryParam("idCredito") String idCredito, @QueryParam("cash") String cash, @QueryParam("descripcion") String descripcion, @QueryParam("codigo") String codigo ) throws RelativeException {
		throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO IMPLEMENTADO");
	}	
	@GET
	@Path("/aprobarNuevo")
	@ApiOperation(value = "	String idCredito ,String descripcion, String cash,String agencia,"
			+ " String usuario,String codigoMotivo, String aprobar", notes = "Metodo Get aprobarNuevo Retorna un  GenericWrapper TbQoProceso aprobado", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> aprobarNuevo(@QueryParam("autorizacion") String autorizacion,
			@QueryParam("idCredito") String idCredito, 
			@QueryParam("descripcion") String descripcion,
			@QueryParam("cash") String cash, 
			@QueryParam("agencia") String agencia,
			@QueryParam("usuario") String usuario,
			@QueryParam("codigoMotivo") String codigoMotivo,
			@QueryParam("aprobar") String aprobar			
			) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		TbQoProceso a = this.qos.aprobarNuevo( Long.valueOf(idCredito),  descripcion, cash, codigoMotivo, Long.valueOf(agencia), usuario, Boolean.valueOf(aprobar) , autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/aprobarNovacion")
	@ApiOperation(value = "	String idCredito ,String descripcion, String cash,String agencia,"
			+ " String usuario,String codigoMotivo, String aprobar", notes = "Metodo Get aprobarNuevo Retorna un  GenericWrapper TbQoProceso aprobada una novacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> aprobarNovacion(@QueryParam("autorizacion") String autorizacion,
			@QueryParam("valorCash") String valorCash, 
			@QueryParam("idCredito") String idCredito, 
			@QueryParam("descripcion") String descripcion,
			@QueryParam("cash") String cash, 
			@QueryParam("agencia") String agencia,
			@QueryParam("usuario") String usuario,
			@QueryParam("codigoMotivo") String codigoMotivo,
			@QueryParam("aprobar") String aprobar			
			) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		TbQoProceso a = this.qos.aprobarNovacion(StringUtils.isNotBlank(valorCash)? BigDecimal.valueOf(Double.valueOf(valorCash)):null, Long.valueOf(idCredito),  descripcion, cash, codigoMotivo, Long.valueOf(agencia), usuario, Boolean.valueOf(aprobar) , autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerCreditoNuevo")
	@ApiOperation(value = "idNegociacion", notes = "Metodo Get traerCreditoNuevo Retorna un  GenericWrapper OperacionCreditoNuevoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<OperacionCreditoNuevoWrapper> traerCreditoNuevo(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<OperacionCreditoNuevoWrapper> loc = new GenericWrapper<>();
		log.info("INGRESA A traerCreditoNuevo  -----> "+ Long.valueOf( idNegociacion ));
		OperacionCreditoNuevoWrapper a = this.qos.traerCreditoNuevo(Long.valueOf( idNegociacion ));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/consultarTablaAmortizacion")
	@ApiOperation(value = "numeroOperacion, usuario, agencia", notes = "Metodo Get consultarTablaAmortizacion Retorna un  GenericWrapper CuotasAmortizacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CuotasAmortizacionWrapper> consultarTablaAmortizacion(@QueryParam("autorizacion") String autorizacion,
			@QueryParam("numeroOperacion") String numeroOperacion,
			@QueryParam("usuario") String usuario,
			@QueryParam("agencia") String agencia
			) throws RelativeException {
		GenericWrapper<CuotasAmortizacionWrapper> loc = new GenericWrapper<>();
		List<CuotasAmortizacionWrapper> list = this.qos.consultarTablaAmortizacion(numeroOperacion, usuario, Long.valueOf( agencia ), autorizacion);
		loc.setEntidades( list );
		return loc;
	}
	@POST
	@Path("/crearOperacionNuevo")
	@ApiOperation(value = "GenericWrapper<TbQoCreditoNegociacion>", notes = "Metodo Post crearOperacionNuevo Retorna un  GenericWrapper CreditoCreadoSoftbank", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CreditoCreadoSoftbank> crearOperacionNuevo(@QueryParam("autorizacion") String autorizacion,GenericWrapper<TbQoCreditoNegociacion> wp) throws RelativeException {
		GenericWrapper<CreditoCreadoSoftbank> loc = new GenericWrapper<>();
		if(wp.getEntidad().getId() != null) {
			CreditoCreadoSoftbank a = this.qos.crearOperacionNuevo( wp.getEntidad() , autorizacion);
			loc.setEntidad(a);
		}else {
			loc.setEntidad(null);			
		}
		return loc;			
	}
	@POST
	@Path("/crearOperacionRenovacion")
	@ApiOperation(value = "CrearRenovacionWrapper", notes = "Metodo Post crearOperacionRenovacion Retorna un  GenericWrapper CreditoCreadoSoftbank", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CreditoCreadoSoftbank> crearOperacionRenovacion(@QueryParam("autorizacion") String autorizacion, CrearRenovacionWrapper wp) throws RelativeException {
		GenericWrapper<CreditoCreadoSoftbank> loc = new GenericWrapper<>();
		if(wp.getCredito().getId() == null) {
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "NO SE PUDO LEER EL ID DEL CREDITO EN LA CREACION DE LA OPERACION");			
		}
		CreditoCreadoSoftbank a = this.qos.crearOperacionRenovacion( wp, autorizacion );
		loc.setEntidad(a);
		return loc;			
	}
	
	@POST
	@Path("/optenerNumeroDeFunda")
	@ApiOperation(value = "GenericWrapper<TbQoCreditoNegociacion>", notes = "Metodo Post optenerNumeroDeFunda Retorna un  GenericWrapper TbQoCreditoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCreditoNegociacion> optenerNumeroDeFunda(@QueryParam("autorizacion") String autorizacion,GenericWrapper<TbQoCreditoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		if(wp.getEntidad().getId() != null) {
			TbQoCreditoNegociacion a = this.qos.optenerNumeroDeFunda( wp.getEntidad(), autorizacion );
			loc.setEntidad(a);
		}else {
			loc.setEntidad(null);			
		}
		return loc;			
	}
	@GET
	@Path("/traerCreditoVigente")
	@ApiOperation(value = "numeroOperacion", notes = "Metodo Get traerCreditoVigente Retorna un  GenericWrapper DetalleCreditoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<DetalleCreditoWrapper> traerCreditoVigente(@QueryParam("autorizacion") String autorizacion,@QueryParam("numeroOperacion") String numeroOperacion) throws RelativeException {
		GenericWrapper<DetalleCreditoWrapper> loc = new GenericWrapper<>();
		if( !numeroOperacion.isEmpty() ) { 
			DetalleCreditoWrapper a = this.qos.traerCreditoVigente( numeroOperacion , autorizacion);
			loc.setEntidad(a);
			return loc;
		}
		loc.setEntidad(null);
		return loc;
	}

	@GET
	@Path("/buscarRenovacionByNumeroOperacionMadre")
	@ApiOperation(value = "numeroOperacion", notes = "Metodo Get buscarRenovacionByNumeroOperacionMadre Retorna un  GenericWrapper RenovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RenovacionWrapper> buscarRenovacionByNumeroOperacionMadre(@QueryParam("autorizacion") String autorizacion,@QueryParam("numeroOperacion") String numeroOperacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		if( !numeroOperacion.isEmpty() ) { 
			RenovacionWrapper a = this.qos.buscarRenovacionOperacionMadre( numeroOperacion, autorizacion );
			loc.setEntidad(a);
			return loc;
		}
		loc.setEntidad(null);
		return loc;
	}

	@GET
	@Path("/traerCreditoNegociacion")
	@ApiOperation(value = "idNegociacion", notes = "Metodo Get traerCreditoNegociacion Retorna un  GenericWrapper DetalleCreditoEnProcesoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<DetalleCreditoEnProcesoWrapper> traerCreditoNegociacion(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<DetalleCreditoEnProcesoWrapper> loc = new GenericWrapper<>();
		DetalleCreditoEnProcesoWrapper a = this.qos.traerCreditoNegociacion(Long.valueOf( idNegociacion ));
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/crearCreditoRenovacion")
	@ApiOperation(value = "numeroOperacion, numeroOperacionMadre, asesor, idAgencia, idNegociacion", notes = "Metodo Post crearCreditoRenovacion Retorna un  GenericWrapper RenovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RenovacionWrapper> crearCreditoRenovacion( @QueryParam("autorizacion") String autorizacion,@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("nombreAgencia") String nombreAgencia, @QueryParam("telefonoAsesor") String telefonoAsesor,
			OpcionAndGarantiasWrapper wp, @QueryParam("numeroOperacion") String numeroOperacion,  @QueryParam("numeroOperacionMadre") String numeroOperacionMadre,
			 @QueryParam("correoAsesor") String correoAsesor, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia, @QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		RenovacionWrapper a = this.qos.crearCreditoRenovacion( wp, numeroOperacion, idNegociacion != null ? Long.valueOf( idNegociacion ): null,
				asesor, Long.valueOf(idAgencia), numeroOperacionMadre, autorizacion,nombreAgencia, telefonoAsesor,nombreAsesor, correoAsesor);
		loc.setEntidad(a);
		return loc;			
	}
	@POST
	@Path("/crearCreditoRenovacionApp")
	@ApiOperation(value = "numeroOperacion, numeroOperacionMadre, asesor, idAgencia, idNegociacion", notes = "Metodo Post crearCreditoRenovacion Retorna un  GenericWrapper RenovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RenovacionWrapper> crearCreditoRenovacionApp( @QueryParam("autorizacion") String autorizacion,@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("nombreAgencia") String nombreAgencia, @QueryParam("telefonoAsesor") String telefonoAsesor,
			OpcionAndGarantiasWrapper wp, @QueryParam("numeroOperacion") String numeroOperacion,  @QueryParam("numeroOperacionMadre") String numeroOperacionMadre,
			 @QueryParam("correoAsesor") String correoAsesor, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia, @QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		RenovacionWrapper a = this.qos.crearRenovacionApp( wp, numeroOperacion, asesor, StringUtils.isNotBlank(idAgencia)? Long.valueOf(idAgencia): null,
				numeroOperacionMadre, autorizacion,nombreAgencia, telefonoAsesor,nombreAsesor, correoAsesor);
		loc.setEntidad(a);
		return loc;			
	}
	@GET
	@Path("/buscarRenovacionByIdNegociacion")
	@ApiOperation(value = "idNegociacion", notes = "Metodo Get buscarRenovacionByIdNegociacion Retorna un  GenericWrapper RenovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RenovacionWrapper> buscarRenovacionByIdNegociacion(@QueryParam("autorizacion") String autorizacion,@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		if( !idNegociacion.isEmpty() ) { 
			RenovacionWrapper a = this.qos.buscarRenovacionNegociacion( Long.valueOf( idNegociacion ) , autorizacion);
			loc.setEntidad(a);
			return loc;
		}
		loc.setEntidad(null);
		return loc;
	}
	
	@GET
	@Path("/solicitarAprobacionRenovacion")
	@ApiOperation(value = "idNegociacion, correoAsesor, nombreAsesor, observacionAsesor", notes = "Metodo Get solicitarAprobacionRenovacion Retorna un  GenericWrapper TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> solicitarAprobacionRenovacion(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("correoAsesor") String correoAsesor,
			@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("observacionAsesor") String observacionAsesor) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idNegociacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID NEGOCIACION");
		}
		TbQoProceso a = this.qos.solicitarAprobacionRenovacion( Long.valueOf(idNegociacion), nombreAsesor, correoAsesor,observacionAsesor );
		loc.setEntidad(a);
		return loc;			
	}
	@GET
	@Path("/solicitarAprobacionNuevo")
	@ApiOperation(value = "idNegociacion, correoAsesor, nombreAsesor, observacionAsesor", notes = "Metodo Get solicitarAprobacionNuevo Retorna un  GenericWrapper de la entidad encontrda TbQoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoNegociacion> solicitarAprobacionNuevo(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("correoAsesor") String correoAsesor,
			@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("observacionAsesor") String observacionAsesor) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idNegociacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID NEGOCIACION");
		}
		TbQoNegociacion a = this.qos.solicitarAprobacionNuevo( Long.valueOf(idNegociacion), correoAsesor, nombreAsesor,observacionAsesor);
		loc.setEntidad(a);
		return loc;			
	}
	@POST
	@Path("/aprobacionDeFlujo")
	@ApiOperation(value = "idNegociacion, correoAsesor, nombreAsesor, observacionAsesor", notes = "Metodo Get solicitarAprobacionRenovacion Retorna un  GenericWrapper TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> aprobacionDeFlujo(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("correoAsesor") String correoAsesor,
			@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("observacionAsesor") String observacionAsesor,
			@QueryParam("tipoProceso") ProcesoEnum tipoProceso,
			TbQoExcepcionOperativa ex
			) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idNegociacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID NEGOCIACION");
		}
		TbQoProceso a = this.qos.aprobacionDeFlujo(Long.valueOf(idNegociacion),nombreAsesor,correoAsesor,observacionAsesor,tipoProceso,ex);
		loc.setEntidad(a);
		return loc;			
	}
	
	
	
	@GET
	@Path("/enviarCorreoDevolucionOperaciones")
	public GenericWrapper<Boolean> enviarCorreoDevolucionOperaciones(
			@QueryParam("idNego") String idNego) throws RelativeException{
		
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idNego)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CODIGO NEGOCIACION");
		}
		try {
			
			TbQoCreditoNegociacion credito = this.qos.findCreditoByIdNegociacion( Long.valueOf( idNego ));
			this.qos.enviarCorreoDevolucionOperaciones( credito );	
			
		}catch( RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CODIGO NEGOCIACION");
		}
		return loc;			
	}
	
	@GET
	@Path("/enviarCorreoExcepcionAsesor")
	public GenericWrapper<Boolean> enviarCorreoExcepcionAsesor(
			@QueryParam("idNego") String idNego,
			@QueryParam("tipoExcepcion") String tipoExcepcion,
			@QueryParam("estadoExcepcion") String estadoExcepcion) throws RelativeException{
		
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idNego)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CODIGO NEGOCIACION");
		}
		try {
			
			TbQoCreditoNegociacion credito = this.qos.findCreditoByIdNegociacion( Long.valueOf( idNego ));
			TbQoExcepcion excepcion = this.qos.findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion(Long.valueOf( idNego ), tipoExcepcion, estadoExcepcion);
			this.qos.enviarCorreoExcepcionAsesor( credito, excepcion);	
			
		}catch( RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CODIGO NEGOCIACION");
		}
		return loc;			
	}
	
	
	@GET
	@Path("/guardarEstadoMotivo")
	public TbQoNegociacion guardarEstadoMotivo(
			@QueryParam("idNego") String idNego,
			@QueryParam("estadoCredito") String estadoCredito,
			@QueryParam("motivo") String motivo) throws RelativeException{
		
	
		return this.qos.guardarEstadoMotivo(StringUtils.isNotBlank(idNego)?Long.valueOf(idNego): null,estadoCredito,motivo);			
	}

	@GET
	@Path("/solicitarValidarDocumento")
	@ApiOperation(value = "idNegociacion, correoAsesor, nombreAsesor, observacionAsesor", notes = "Metodo Get solicitarValidarDocumento Retorna un  GenericWrapper de la entidad encontrda TbQoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public ResponseValidarDocumentoWrapper solicitarValidarDocumento(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("autorizacion") String autorizacion) throws RelativeException {
		if(StringUtils.isBlank(idNegociacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID NEGOCIACION");
		}
		return this.validacionCreditoService.validarDocumento( Long.valueOf(idNegociacion), autorizacion);
				
	}

	@GET
	@Path("/listValidacionDocumento")
	@ApiOperation(value = "idNegociacion, correoAsesor, nombreAsesor, observacionAsesor", notes = "Metodo Get solicitarValidarDocumento Retorna un  GenericWrapper de la entidad encontrda TbQoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public List<TbQoValidacionDocumento> listValidacionDocumento(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("autorizacion") String autorizacion) throws RelativeException {
		if(StringUtils.isBlank(idNegociacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID NEGOCIACION");
		}
		return this.validacionCreditoService.listValidacionDocumento( Long.valueOf(idNegociacion));
				
	}
	@GET
	@Path("/traerCreditoCompromiso")
	@ApiOperation(value = "numeroOperacion", notes = "Metodo Get traerCreditoVigente Retorna un  GenericWrapper DetalleCreditoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CreditoCompromisoWrapper> traerCreditoCompromiso(
			@QueryParam("numeroOperacion") String numeroOperacion,
			@QueryParam("procesoCompromiso") ProcesoEnum procesoCompromiso,
			@QueryParam("usuario") String usuario,
			@QueryParam("autorizacion") String autorizacion
			) throws RelativeException {
		GenericWrapper<CreditoCompromisoWrapper> loc = new GenericWrapper<>();
		try {
			CreditoCompromisoWrapper a = this.qos.traerCreditoCompromiso( numeroOperacion, procesoCompromiso, usuario, autorizacion);
			loc.setEntidad(a);			
		}catch( RelativeException e) {
			throw e;
		}catch(Exception e ){
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getStackTrace());
		}
		return loc;
	}
	
	@POST
	@Path("/solicitarCompromiso")
	@ApiOperation(value = "numeroOperacion, numeroOperacionMadre, asesor, idAgencia, idNegociacion", notes = "Metodo Post crearCreditoRenovacion Retorna un  GenericWrapper RenovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCompromisoPago> solicitarCompromiso( 
			@QueryParam("proceso") ProcesoEnum proceso,
			@QueryParam("usuario") String usuario,
			@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("autorizacion") String autorizacion,
			TbQoCompromisoPago compromiso) throws RelativeException {
		GenericWrapper<TbQoCompromisoPago> loc = new GenericWrapper<>();
		TbQoCompromisoPago a = this.qos.solicitarCompromiso( proceso, usuario, nombreAsesor, compromiso, autorizacion);
		loc.setEntidad(a);
		return loc;			
	}
	@POST
	@Path("/resolucionCompromiso")
	@ApiOperation(value = "numeroOperacion, numeroOperacionMadre, asesor, idAgencia, idNegociacion", notes = "Metodo Post crearCreditoRenovacion Retorna un  GenericWrapper RenovacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCompromisoPago> solicitarCompromiso( 
			@QueryParam("proceso") ProcesoEnum proceso,
			@QueryParam("aprobado") Boolean aprobado,
			@QueryParam("usuario") String usuario,
			@QueryParam("nombreAsesor") String nombreAsesor,
			@QueryParam("autorizacion") String autorizacion,
			TbQoCompromisoPago compromiso) throws RelativeException {
		GenericWrapper<TbQoCompromisoPago> loc = new GenericWrapper<>();
		TbQoCompromisoPago a = this.qos.resolucionCompromiso( proceso, aprobado, usuario, nombreAsesor, compromiso, autorizacion);
		loc.setEntidad(a);
		return loc;			
	}
	@GET
	@Path("/findCompromisoByNumeroOperacionEstado")
	@ApiOperation(value = "PaginatedListWrapper<ExcepcionRolWrapper>", notes = "Metodo Get indByRolAndIdentificacion Retorna entidades encontradas en TbQoExcepcion por id de Negociacion", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CreditoCompromisoWrapper> findCompromisoByNumeroOperacionEstado( @QueryParam("numeroOperacion" )  String numeroOperacion, @QueryParam("estado" )  EstadoProcesoEnum estado, @QueryParam("autorizacion") String autorizacion) throws RelativeException {
		GenericWrapper<CreditoCompromisoWrapper> loc = new GenericWrapper<>();
		CreditoCompromisoWrapper a = this.qos.findCompromisoByNumeroOperacionEstado(numeroOperacion, estado);
		loc.setEntidad(a);
		return loc;	
	}
	@POST
	@Path("/reporteCompromiso")
	@ApiOperation(value = "PaginatedListWrapper<DevolucionReporteWrapper>", notes = "Metodo Get reporteDevolucion Retorna wrapper de informacion de paginacion y entidades encontradas en DevolucionPendienteArribosWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<CompromisoReporteWrapper> reporteCompromiso(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, CompromisoParamsWrapper wp
			) throws RelativeException {
		PaginatedWrapper pw = new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated);
		PaginatedListWrapper<CompromisoReporteWrapper> plw = new PaginatedListWrapper<>(pw);
		List<CompromisoReporteWrapper> actions = this.qos.findCompromisoReporte(pw, wp );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCompromisoReporte(wp));
			plw.setList(actions);
		}
		return plw;
		
	}
}

