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
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.service.PagoService;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/clientePagoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientePagoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoClientePago, GenericWrapper<TbQoClientePago>> {

	public ClientePagoRestController() throws RelativeException {
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
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoClientePago>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoClientePago", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoClientePago> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbQoClientePago> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoClientePago> plw = new PaginatedListWrapper<>(pw);
		List<TbQoClientePago> actions = this.qos.findAllClientePago(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countRegistrarPago().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoClientePago>", notes = "Metodo Post persistEntity Registra  y retorna la entidad TbQoClientePago", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoClientePago> persistEntity(GenericWrapper<TbQoClientePago> wp)
			throws RelativeException {
		GenericWrapper<TbQoClientePago> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageClientePago( wp.getEntidad()));
		return loc;
		
	}
	
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada TbQoClientePago", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoClientePago> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoClientePago> loc = new GenericWrapper<>();
		TbQoClientePago a = this.qos.findClientePagoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/findByIdClientePago")
	@ApiOperation(value = "cedula", notes = "Metodo Get findByIdClientePago Retorna GenericWrapper de la entidad encontrada TbQoClientePago por cliente pago", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoClientePago> findByIdClientePago(@QueryParam("cedula")  String cedula) throws RelativeException {
		GenericWrapper<TbQoClientePago> loc = new GenericWrapper<>();
		loc.setEntidades( this.ps.findClientePagoByIdClientePago(StringUtils.isNotBlank(cedula)?Long.valueOf(cedula):null) );
		return loc;
	}
	
}