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
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoFunda;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/fundaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "fundaRestController - REST CRUD")
public class FundaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoFunda, GenericWrapper<TbQoFunda>> {

	public FundaRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;
	@Inject
	Logger log;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoFunda>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoFunda", response = GenericWrapper.class)
	public GenericWrapper<TbQoFunda> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoFunda> loc = new GenericWrapper<>();
		TbQoFunda a = this.qos.findFundaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoCliente", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoFunda> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoFunda> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoFunda> plw = new PaginatedListWrapper<>(pw);
		List<TbQoFunda> actions = this.qos.findAllFunda(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countFunda().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCliente", response = GenericWrapper.class)
	public GenericWrapper<TbQoFunda> persistEntity(GenericWrapper<TbQoFunda> wp) throws RelativeException {
		GenericWrapper<TbQoFunda> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageFunda(wp.getEntidad()));
		return loc;
	}


	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbQoFunda>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCliente", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbQoFunda> findByParams(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigo") String codigo, @QueryParam("peso") String peso,
			@QueryParam("estado") String estado) throws RelativeException {
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				StringUtils.isNotBlank(codigo) ? codigo : null,
				StringUtils.isNotBlank(peso) ? BigDecimal.valueOf(Double.valueOf(peso)) : null,
				StringUtils.isNotBlank(estado) ? QuskiOroUtil.getEnumFromString(EstadoEnum.class, estado) : null);

	}

	private PaginatedListWrapper<TbQoFunda> findByParams(PaginatedWrapper pw, String codigo,
			BigDecimal peso, EstadoEnum estado) throws RelativeException {

		PaginatedListWrapper<TbQoFunda> plw = new PaginatedListWrapper<>(pw);
		List<TbQoFunda> actions = this.qos.findFundaByParams(pw, codigo, peso, estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countFundaByParams(codigo, peso, estado).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	
	@GET
	@Path("/reservarFunda")
	@ApiOperation(value = "GenericWrapper<TbMiFunda>", notes = "Metodo reservarFunda Retorna wrapper de entidad encontrad en TbMiFunda", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoFunda> reservarFunda(@QueryParam("peso") String peso, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<TbQoFunda> loc = new GenericWrapper<>();
		TbQoFunda a =this.qos.reservarFunda(BigDecimal.valueOf(Long.valueOf(peso)), usuario);
		loc.setEntidad(a);
		return loc;
	}
}
