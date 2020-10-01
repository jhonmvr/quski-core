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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.service.PagoService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.RegistrarPagoWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/bloquearFondoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "bloquearFondoRestController - REST CRUD")
public class BloquearFondoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoRegistrarPago, GenericWrapper<TbQoRegistrarPago>> {

	public BloquearFondoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	
	@Inject
	PagoService ps;
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRegistrarPago> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		TbQoRegistrarPago a = this.qos.findRegistrarPagoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoRegistrarPago", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoRegistrarPago> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbQoRegistrarPago> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoRegistrarPago> plw = new PaginatedListWrapper<>(pw);
		List<TbQoRegistrarPago> actions = this.qos.findAllRegistrarPago(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countRegistrarPago().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRegistrarPago> persistEntity(GenericWrapper<TbQoRegistrarPago> wp)
			throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		log.info("INGRESA AL REST DE persistEntity registrarPago ");		
		loc.setEntidad(this.qos.manageRegistrarPago(wp.getEntidad()));
		return loc;
		
	}
	@POST
	@Path("/crearRegistrarPago")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<RegistrarPagoWrapper> crearRegistrarPago(RegistrarPagoWrapper registroPago) throws RelativeException {
		GenericWrapper<RegistrarPagoWrapper> loc = new GenericWrapper<>();
		
		//loc.setEntidad( this.ps.crearRegistrarPago(registroPago) );
		return loc;
	}
	
	@GET
	@Path("/findByIdClientePago")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRegistrarPago> findByIdClientePago(@QueryParam("idClientePago")  String idClientePago) throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		
		loc.setEntidades( this.ps.findRegistrarPagoByIdClientePago(StringUtils.isNotBlank(idClientePago)?Long.valueOf(idClientePago):null) );
		return loc;
	}
	
}