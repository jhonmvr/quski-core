package com.relative.quski.rest;

import java.util.ArrayList;
import java.util.Date;
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
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.ListadoOperacionDevueltaWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/creditoNegociacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "creditoNegociacionRestController - REST CRUD")
public class CreditoNegociacionRestController extends BaseRestController implements CrudRestControllerInterface<TbQoCreditoNegociacion, GenericWrapper<TbQoCreditoNegociacion>>{
	
	@Inject
	QuskiOroService qos;
	public CreditoNegociacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericWrapper<TbQoCreditoNegociacion> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<TbQoCreditoNegociacion> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCalculoNegociacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCalculoNegociacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoCreditoNegociacion> persistEntity(GenericWrapper<TbQoCreditoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageCalculoNegociacion(wp.getEntidad()));
		return loc;
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
			@QueryParam("agencia") String agencia
			) throws RelativeException {
		
		return creditoNegociacionByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				fechaDesde, fechaHasta, StringUtils.isNotBlank(codigoOperacion)?codigoOperacion:null, proceso, identificacion,  agencia);
		
	}
	
	
	
	private PaginatedListWrapper<TbQoCreditoNegociacion> creditoNegociacionByParams(PaginatedWrapper pw, String fechaDesde, String fechaHasta,
			String codigoOperacion ,String proceso, String identificacion, String agencia) throws RelativeException {
		
		PaginatedListWrapper<TbQoCreditoNegociacion> plw = new PaginatedListWrapper<>(pw);
		
		List<TbQoCreditoNegociacion> actions =null; 
				actions=this.qos.findCreditoNegociacionByParams(pw, fechaDesde, fechaHasta, codigoOperacion,
				proceso, identificacion, agencia);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCreditoNegociacionByParams(fechaDesde, fechaHasta, codigoOperacion,
					proceso, identificacion, agencia));
			plw.setList(actions);
		}
		return plw;
	}

	/**
	 * Lista contratos por agencia y dos estado en clase personalizada
	 * 
	 * @param page
	 * @param pageSize
	 * @param sortFields
	 * @param sortDirections
	 * @param isPaginated
	 * @param idAgencia
	 * @param estado1
	 * @param estado2
	 * @return
	 * @throws RelativeException
	 */
	@GET
	@Path("/getOperacionesDevueltas")
	@ApiOperation(value = "PaginatedListWrapper<ContratosPerfecionados>", notes = "Metodo que retorna contratos por agencia y dos estado en clase personalizada ContratosPerfecionados", response = GenericWrapper.class)
	public PaginatedListWrapper<ListadoOperacionDevueltaWrapper> getOperacionesDevueltasByParams(
			@QueryParam("page") @DefaultValue("0") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigo") String codigo, @QueryParam("agencia") String agencia,
			@QueryParam("proceso") String proceso, @QueryParam("cedula") String cedula) throws RelativeException {
		
		return getOperacionesDevueltasByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				codigo, agencia,
				proceso, cedula);
	}

	
	
	private PaginatedListWrapper<ListadoOperacionDevueltaWrapper> getOperacionesDevueltasByParams(PaginatedWrapper pw, String codigo, String agencia, String proceso, String cedula) throws RelativeException {
		PaginatedListWrapper<ListadoOperacionDevueltaWrapper> plw = new PaginatedListWrapper<>(pw);
		List<ListadoOperacionDevueltaWrapper> actions = this.qos.listOperacionesDevueltas(pw, codigo, agencia, proceso, cedula);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countOperacionesDevueltas(pw, codigo, agencia, proceso, cedula));
			plw.setList(actions);
		}
		return plw;
	}
}
