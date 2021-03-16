package com.relative.quski.rest;

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
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AprobacionNovacionWrapper;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.CreditoCreadoSoftbank;
import com.relative.quski.wrapper.CuotasAmortizacionWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import com.relative.quski.wrapper.OpcionAndGarantiasWrapper;
import com.relative.quski.wrapper.OperacionCreditoNuevoWrapper;
import com.relative.quski.wrapper.RenovacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public GenericWrapper<TbQoCreditoNegociacion> persistEntity(GenericWrapper<TbQoCreditoNegociacion> wp)
			throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageCreditoNegociacion(wp.getEntidad()));
		return loc;
	}


	@GET
	@Path("/traerCreditoNegociacionExistente")
	public GenericWrapper<AprobacionWrapper> traerCreditoNegociacionExistente(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<AprobacionWrapper> loc = new GenericWrapper<>();
		AprobacionWrapper a = this.qos.traerCreditoNegociacionExistente(Long.valueOf( idNegociacion ));

		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerCreditonovacionPorAprobar")
	public GenericWrapper<AprobacionNovacionWrapper> traerCreditonovacionPorAprobar(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<AprobacionNovacionWrapper> loc = new GenericWrapper<>();
		AprobacionNovacionWrapper a = this.qos.traerCreditonovacionPorAprobar(Long.valueOf( idNegociacion ));

		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/devolverAprobar")
	public GenericWrapper<Boolean> devolverAprobar(@QueryParam("idCredito") String idCredito, @QueryParam("cash") String cash, @QueryParam("descripcion") String descripcion, @QueryParam("codigo") String codigo ) throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO IMPLEMENTADO");
	}	
	@GET
	@Path("/aprobarNuevo")
	public GenericWrapper<TbQoProceso> aprobarNuevo(
			@QueryParam("idCredito") String idCredito, 
			@QueryParam("descripcion") String descripcion,
			@QueryParam("cash") String cash, 
			@QueryParam("agencia") String agencia,
			@QueryParam("usuario") String usuario,
			@QueryParam("codigoMotivo") String codigoMotivo,
			@QueryParam("aprobar") String aprobar			
			) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		TbQoProceso a = this.qos.aprobarNuevo( Long.valueOf(idCredito),  descripcion, cash, codigoMotivo, Long.valueOf(agencia), usuario, Boolean.valueOf(aprobar) );
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerCreditoNuevo")
	public GenericWrapper<OperacionCreditoNuevoWrapper> traerCreditoNuevo(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<OperacionCreditoNuevoWrapper> loc = new GenericWrapper<>();
		log.info("INGRESA A traerCreditoNuevo  -----> "+ Long.valueOf( idNegociacion ));
		OperacionCreditoNuevoWrapper a = this.qos.traerCreditoNuevo(Long.valueOf( idNegociacion ));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/consultarTablaAmortizacion")
	public GenericWrapper<CuotasAmortizacionWrapper> consultarTablaAmortizacion(
			@QueryParam("numeroOperacion") String numeroOperacion,
			@QueryParam("usuario") String usuario,
			@QueryParam("agencia") String agencia
			) throws RelativeException {
		GenericWrapper<CuotasAmortizacionWrapper> loc = new GenericWrapper<>();
		List<CuotasAmortizacionWrapper> list = this.qos.consultarTablaAmortizacion(numeroOperacion, usuario, Long.valueOf( agencia ));
		loc.setEntidades( list );
		return loc;
	}
	@POST
	@Path("/crearOperacionNuevo")
	public GenericWrapper<CreditoCreadoSoftbank> crearOperacionNuevo(@QueryParam("correoAsesor") String correoAsesor, GenericWrapper<TbQoCreditoNegociacion> wp) throws RelativeException {
		GenericWrapper<CreditoCreadoSoftbank> loc = new GenericWrapper<>();
		if(wp.getEntidad().getId() != null) {
			CreditoCreadoSoftbank a = this.qos.crearOperacionNuevo( wp.getEntidad(), correoAsesor);
			loc.setEntidad(a);
		}else {
			loc.setEntidad(null);			
		}
		return loc;			
	}
	@POST
	@Path("/crearOperacionRenovacion")
	public GenericWrapper<CreditoCreadoSoftbank> crearOperacionRenovacion(GenericWrapper<TbQoCreditoNegociacion> wp) throws RelativeException {
		GenericWrapper<CreditoCreadoSoftbank> loc = new GenericWrapper<>();
		if(wp.getEntidad().getId() != null) {
			CreditoCreadoSoftbank a = this.qos.crearOperacionRenovacion( wp.getEntidad() );
			loc.setEntidad(a);
		}else {
			loc.setEntidad(null);			
		}
		return loc;			
	}
	
	@POST
	@Path("/optenerNumeroDeFunda")
	public GenericWrapper<TbQoCreditoNegociacion> optenerNumeroDeFunda(GenericWrapper<TbQoCreditoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		if(wp.getEntidad().getId() != null) {
			TbQoCreditoNegociacion a = this.qos.optenerNumeroDeFunda( wp.getEntidad() );
			loc.setEntidad(a);
		}else {
			loc.setEntidad(null);			
		}
		return loc;			
	}
	@GET
	@Path("/traerCreditoVigente")
	public GenericWrapper<DetalleCreditoWrapper> traerCreditoVigente(@QueryParam("numeroOperacion") String numeroOperacion) throws RelativeException {
		GenericWrapper<DetalleCreditoWrapper> loc = new GenericWrapper<>();
		if( !numeroOperacion.isEmpty() ) { 
			DetalleCreditoWrapper a = this.qos.traerCreditoVigente( numeroOperacion );
			loc.setEntidad(a);
			return loc;
		}
		loc.setEntidad(null);
		return loc;
	}
	@GET
	@Path("/buscarRenovacionByNumeroOperacionMadre")
	public GenericWrapper<RenovacionWrapper> buscarRenovacionByNumeroOperacionMadre(@QueryParam("numeroOperacion") String numeroOperacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		if( !numeroOperacion.isEmpty() ) { 
			RenovacionWrapper a = this.qos.buscarRenovacionOperacionMadre( numeroOperacion );
			loc.setEntidad(a);
			return loc;
		}
		loc.setEntidad(null);
		return loc;
	}

	@GET
	@Path("/traerCreditoNegociacion")
	public GenericWrapper<DetalleCreditoEnProcesoWrapper> traerCreditoNegociacion(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<DetalleCreditoEnProcesoWrapper> loc = new GenericWrapper<>();
		DetalleCreditoEnProcesoWrapper a = this.qos.traerCreditoNegociacion(Long.valueOf( idNegociacion ));
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/crearCreditoRenovacion")
	public GenericWrapper<RenovacionWrapper> crearCreditoRenovacion( OpcionAndGarantiasWrapper wp, @QueryParam("numeroOperacionMadre") String numeroOperacionMadre, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia, @QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		RenovacionWrapper a = this.qos.crearCreditoRenovacion( wp.getOpcion(), wp.getGarantias(), numeroOperacionMadre, idNegociacion != null ? Long.valueOf( idNegociacion ): null, asesor, Long.valueOf(idAgencia));
		loc.setEntidad(a);
		return loc;			
	}
	@GET
	@Path("/buscarRenovacionByIdNegociacion")
	public GenericWrapper<RenovacionWrapper> buscarRenovacionByIdNegociacion(@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<RenovacionWrapper> loc = new GenericWrapper<>();
		if( !idNegociacion.isEmpty() ) { 
			RenovacionWrapper a = this.qos.buscarRenovacionNegociacion( Long.valueOf( idNegociacion ) );
			loc.setEntidad(a);
			return loc;
		}
		loc.setEntidad(null);
		return loc;
	}
	
	@POST
	@Path("/solicitarAprobacionNuevo")
	public GenericWrapper<CreditoCreadoSoftbank> solicitarAprobacionNuevo(@QueryParam("correoAsesor") String correoAsesor,
			@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<CreditoCreadoSoftbank> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idNegociacion)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID NEGOCIACION");
		}
			CreditoCreadoSoftbank a = this.qos.solicitarAprobacionNuevo( Long.valueOf(idNegociacion),correoAsesor);
			loc.setEntidad(a);
		
		return loc;			
	}
}
