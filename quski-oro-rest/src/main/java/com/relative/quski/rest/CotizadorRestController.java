package com.relative.quski.rest;

import java.io.UnsupportedEncodingException;
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
	public GenericWrapper<CotizacionWrapper> iniciarCotizacion(@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<CotizacionWrapper> loc = new GenericWrapper<>();
		CotizacionWrapper a = this.qos.iniciarCotizacion(cedula, asesor, Long.valueOf(idAgencia));
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/iniciarCotizacionEquifax")
	public GenericWrapper<CotizacionWrapper> iniciarCotizacionEquifax(@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<CotizacionWrapper> loc = new GenericWrapper<>();
		CotizacionWrapper a = this.qos.iniciarCotizacionEquifax(cedula, asesor, Long.valueOf(idAgencia));
		loc.setEntidad(a);
		return loc;
	}
	
	@POST
	@Path("/agregarJoya")
	public GenericWrapper<TbQoTasacion> agregarJoya(TbQoTasacion joya, @QueryParam("asesor") String asesor) throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		List<TbQoTasacion> a = this.qos.agregarJoyaCotizacion(joya, asesor);
		loc.setEntidades(a);
		return loc;
	}
	@POST
	@Path("/guardarGestion")
	public GenericWrapper<TbQoCotizador> guardarGestion(CotizacionWrapper wrapper) throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<TbQoCotizador> loc = new GenericWrapper<>();
		TbQoCotizador a = this.qos.guardarGestion(wrapper);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/eliminarJoya")
	public GenericWrapper<TbQoTasacion> eliminarJoya(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		TbQoTasacion a = this.qos.eliminarJoya(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/findByCedula")
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
