package com.relative.quski.rest;

import java.util.List;

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
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;

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
						StringUtils.isNotBlank(proceso) ? QuskiOroUtil.getEnumFromString(EstadoEnum.class, estado)
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
	@Path("/crearOperacion")
	@ApiOperation(value = "GenericWrapper<CrearOperacionRespuestaWrapper>", notes = "Metodo Post perfeccionarContrato Retorna GenericWrapper de informacion TbMiContrato", response = GenericWrapper.class)
	public GenericWrapper<CrearOperacionRespuestaWrapper> crearOperacion(GenericWrapper<CrearOperacionEntradaWrapper> datosEntradaOperacion,
			@QueryParam("usuario") String usuario)throws RelativeException {
		GenericWrapper<CrearOperacionRespuestaWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.crearOperacion(datosEntradaOperacion.getEntidad()));
		return loc;
	}
	
	@GET
	@Path("/traerCreditoNegociacionExistente")
	public GenericWrapper<AprobacionWrapper> traerCreditoNegociacionExistente(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<AprobacionWrapper> loc = new GenericWrapper<>();
		AprobacionWrapper a = this.qos.traerCreditoNegociacionExistente(Long.valueOf( id ));
		loc.setEntidad(a);
		return loc;
	}
}
