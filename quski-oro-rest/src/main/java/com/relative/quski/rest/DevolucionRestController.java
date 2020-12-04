package com.relative.quski.rest;

import java.util.ArrayList;
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
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.service.DevolucionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.BusquedaDevolucionWrapper;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.RegistroFechaArriboWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/devolucionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "devolucionRestController - REST CRUD")
public class DevolucionRestController extends BaseRestController implements CrudRestControllerInterface<TbQoDevolucion, GenericWrapper<TbQoDevolucion>>{
	
	@Inject
	QuskiOroService qos;
	@Inject
	SoftBankApiClient sbac;
	@Inject
	Logger log;
	@Inject
	DevolucionService dos; 
	
	public DevolucionRestController() throws RelativeException {
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
	@ApiOperation(value = "TbQoDevolucion", notes = "Metodo que retorna devolucion por id", response = GenericWrapper.class)
	public GenericWrapper<TbQoDevolucion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		TbQoDevolucion a = this.dos.findDevolucionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	
	@Override
	@POST
	@Path("/persistEntity")
	public GenericWrapper<TbQoDevolucion> persistEntity(GenericWrapper<TbQoDevolucion> wp)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.manageDevolucion(wp.getEntidad()));
		return loc;
	}
	
	
	@POST
	@Path("/registrarSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> registrarSolicitudDevolucion(GenericWrapper<TbQoDevolucion> wp,
			@QueryParam("usuario") String usuario)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.registrarSolicitudDevolucion(wp.getEntidad(), usuario));
		return loc;
	}
	
	
	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoDevolucion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoDevolucion", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoDevolucion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<TbQoDevolucion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoDevolucion> plw = new PaginatedListWrapper<>(pw);
		List<TbQoDevolucion> actions = this.dos.findAllDevolucion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.dos.countDevolucion().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@POST
	@Path("/aprobarSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> aprobarSolicitudDevolucion(GenericWrapper<TbQoDevolucion> wp,
			@QueryParam("id") String id)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarSolicitudDevolucion(Long.valueOf(id)));
		return loc;
	}
	
	@POST
	@Path("/rechazarSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> rechazarSolicitudDevolucion(GenericWrapper<TbQoDevolucion> wp,
			@QueryParam("id") String id)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarSolicitudDevolucion(Long.valueOf(id)));
		return loc;
	}
	
	
	@POST
	@Path("/buscarDevolucion")
	@ApiOperation(value = "PaginatedListWrapper<ResultOperacionesWrapper>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiContrato", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<DevolucionProcesoWrapper> listarSeleccionFecha(BusquedaDevolucionWrapper bdw ) throws RelativeException {
		PaginatedListWrapper<DevolucionProcesoWrapper> plw = this.dos.findOperacion(bdw);
	
		return plw;
	}
	
	@GET
	@Path("/buscarDevolucionPendienteArribo")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCreditoNegociacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiAgente", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<DevolucionPendienteArribosWrapper> listarPendienteArribo(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigoOperacion") String codigoOperacion,
			@QueryParam("agencia") String agencia


			) throws RelativeException {
		return listarPendienteArribo(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				 StringUtils.isNotBlank(codigoOperacion)?codigoOperacion:null,
						 StringUtils.isNotBlank(agencia)?agencia:null);
		
	}
	private PaginatedListWrapper<DevolucionPendienteArribosWrapper> listarPendienteArribo(PaginatedWrapper pw, String codigoOperacion, String agencia ) throws RelativeException {
		
		PaginatedListWrapper<DevolucionPendienteArribosWrapper> plw = new PaginatedListWrapper<>(pw);
		
		List<DevolucionPendienteArribosWrapper> actions = this.dos.findOperacionArribo(pw, codigoOperacion, agencia);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.dos.countOperacionArribo(codigoOperacion, agencia));
			plw.setList(actions);
		}
		return plw;
	}
	
	@POST
	@Path("/registrarFechaArribo")
	public List<TbQoDevolucion> registrarFechaArribo( RegistroFechaArriboWrapper rfaw ) throws RelativeException {
		List<TbQoDevolucion> loc = new ArrayList<>();
		loc= this.dos.registrarFechaArribo(rfaw);
		return loc;
	}
	@POST
	@Path("/registrarArribo")
	public List<TbQoDevolucion> registrarArribo( List<Long> idDevoluciones) throws RelativeException {
		List<TbQoDevolucion> loc = new ArrayList<>();
		loc= this.dos.registrarArriboAgencia(idDevoluciones);
		return loc;
	}
	
	@POST
	@Path("/cancelarSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> cancelarSolicitudDevolucion(@QueryParam("id") String idDevolucion)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.cancelarSolicitudDevolucion(Long.valueOf(idDevolucion)));
		return loc;
	}
	@POST
	@Path("/aprobarCancelacionSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> aprobarCancelacionSolicitudDevolucion(
			@QueryParam("id") String idDevolucion)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarCancelacionSolicitudDevolucion(Long.valueOf(idDevolucion)));
		return loc;
	}
	
	@POST
	@Path("/rechazarCancelacionSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> rechazarCancelacionSolicitudDevolucion(@QueryParam("id") String idDevolucion)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.rechazarCancelacionSolicitudDevolucion(Long.valueOf(idDevolucion)));
		return loc;
	}
	
	@GET
	@Path("/validateAprobarCancelarSolicitud")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateAprobacionCancelarSolicitud(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
		if (StringUtils.isBlank(idDevolucion)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.dos.validateAprobarCancelacionSolicitud(Long.valueOf(idDevolucion));
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/validateCancelarSolicitud")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> validateCancelarSolicitud(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
		if (StringUtils.isBlank(idDevolucion)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.dos.validateCancelacionSolicitud(Long.valueOf(idDevolucion));
		loc.setEntidad(a);
		return loc;
	}
	
	
}
