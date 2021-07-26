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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.CotizacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/cotizadorRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "CotizadorRestController - REST CRUD")
public class CotizadorRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoCotizador, GenericWrapper<TbQoCotizador>> {

	public CotizadorRestController() throws RelativeException {
		super();
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
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoCotizador> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoCotizador> loc = new GenericWrapper<>();
		TbQoCotizador a = this.qos.findCotizadorById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCotizador>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoCotizador", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoCotizador> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoCotizador> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoCotizador> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCotizador> actions = this.qos.findAllCotizador(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCotizador().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoCotizador> persistEntity(GenericWrapper<TbQoCotizador> wp) throws RelativeException {
		GenericWrapper<TbQoCotizador> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageCotizador(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/cotizadorByCliente")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo CotizadorBycedulaCliente Retorna wrapper de entidades encontradas en TbQoCotizador", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoCotizador> cotizadorByIdCotizador(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("cedulaCliente") String cedulaCliente) throws RelativeException {
		return findCotizadorByIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize),
				sortFields, sortDirections, isPaginated), cedulaCliente);
	}

	private PaginatedListWrapper<TbQoCotizador> findCotizadorByIdentificacion(PaginatedWrapper pw, String cedulaCliente)
			throws RelativeException {
		PaginatedListWrapper<TbQoCotizador> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCotizador> actions = this.qos.listByCliente(pw, cedulaCliente);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countByCliente(cedulaCliente).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/iniciarCotizacion")
	@ApiOperation(value = "cedula, asesor", notes = "Metodo iniciarCotizacion Retorna wrapper de entidades encontradas en CotizacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CotizacionWrapper> iniciarCotizacion(@QueryParam("autorizacion") String autorizacion,@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<CotizacionWrapper> loc = new GenericWrapper<>();
		CotizacionWrapper a = this.qos.iniciarCotizacion(cedula, asesor, Long.valueOf(idAgencia), autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/iniciarCotizacionEquifax")
	@ApiOperation(value = "cedula, asesor", notes = "Metodo iniciarCotizacionEquifax Retorna wrapper de entidades encontradas en CotizacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CotizacionWrapper> iniciarCotizacionEquifax(@QueryParam("autorizacion") String autorizacion,@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<CotizacionWrapper> loc = new GenericWrapper<>();
		CotizacionWrapper a = this.qos.iniciarCotizacionEquifax(cedula, asesor, Long.valueOf(idAgencia), autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	
	@POST
	@Path("/agregarJoya")
	@ApiOperation(value = "GenericWrapper<TbQoTasacion>, asesor", notes = "Metodo POST agregarJoya registra y retorna wrapper de entidades encontradas en CotizacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoTasacion> agregarJoya(TbQoTasacion joya, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		List<TbQoTasacion> a = this.qos.agregarJoyaCotizacion(joya, asesor);
		loc.setEntidades(a);
		return loc;
	}
	@POST
	@Path("/guardarGestion")
	@ApiOperation(value = "GenericWrapper<CotizacionWrapper>", notes = "Metodo POST guardarGestion registra y retorna wrapper de entidades encontradas en TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoCotizador> guardarGestion(CotizacionWrapper wrapper) throws RelativeException {
		GenericWrapper<TbQoCotizador> loc = new GenericWrapper<>();
		TbQoCotizador a = this.qos.guardarGestion(wrapper);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/eliminarJoya")
	@ApiOperation(value = "id", notes = "Metodo GET elimina la entidad encontradas en TbQoTasacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoTasacion> eliminarJoya(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		TbQoTasacion a = null;
		this.qos.eliminarJoya(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/findByCedula")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCotizador>", notes = "Metodo GET elimina la entidad encontradas en TbQoTasacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoCotizador> findByCedula(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("cedula") String cedula
			) throws RelativeException {
		return findIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),cedula);
	}
	@GET
	@Path("/buscarGestionCotizacion")
	@ApiOperation(value = "id", notes = "Metodo GET retorna la entidad encontrada en CotizacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CotizacionWrapper> iniciarCotizacionEquifax(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<CotizacionWrapper> loc = new GenericWrapper<>();
		CotizacionWrapper a = this.qos.buscarGestionCotizacion(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	private PaginatedListWrapper<TbQoCotizador> findIdentificacion(PaginatedWrapper pw, String cedula) throws RelativeException {
		PaginatedListWrapper<TbQoCotizador> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCotizador> actions = this.qos.cotizacionByCedula(pw, cedula);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.cotizacionCountByCedula(cedula).intValue());
			plw.setList(actions);
		}
		return plw;
	}	
}
