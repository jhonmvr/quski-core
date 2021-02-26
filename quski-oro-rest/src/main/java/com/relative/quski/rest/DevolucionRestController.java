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
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.DevolucionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.ProcesoDevolucionWrapper;
import com.relative.quski.wrapper.RegistroFechaArriboWrapper;
import com.relative.quski.wrapper.RespuestaBooleanaWrapper;

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
	public GenericWrapper<ProcesoDevolucionWrapper> registrarSolicitudDevolucion(GenericWrapper<TbQoDevolucion> wp)
			throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.registrarSolicitudDevolucion(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/buscarProcesoDevolucion")
	public GenericWrapper<ProcesoDevolucionWrapper> buscarProcesoDevolucion(@QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.buscarProcesoDevolucion( Long.valueOf( idDevolucion )) );
		return loc;

	}
	
	
	@POST
	@Path("/mandarAprobacionSolicitudDevolucion")
	public GenericWrapper<TbQoDevolucion> mandarAprobarSolicitudDevolucion(@QueryParam("id") String idDevolucion,
			@QueryParam("usuario") String usuario
			)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.mandarAprobarSolicitudDevolucion(Long.valueOf(idDevolucion), usuario));
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
	
	@GET
	@Path("/aprobarNegarSolicitudDevolucion")
	public GenericWrapper<ProcesoDevolucionWrapper> aprobarNegarSolicitudDevolucion(@QueryParam("idDevolucion") String idDevolucion, @QueryParam("aprobado") String aprobado)throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarNegarSolicitudDevolucion(Long.valueOf(idDevolucion), Boolean.valueOf( aprobado )));
		return loc;
	}
	
	
	@GET
	@Path("/buscarDevolucion")
	public PaginatedListWrapper<DevolucionProcesoWrapper> listarSeleccionFecha(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigoOperacion") String codigoOperacion,
			@QueryParam("agencia") String agencia,
			@QueryParam("fechaAprobacionDesde") String fechaAprobacionDesde,
			@QueryParam("fechaAprobacionHasta") String fechaAprobacionHasta,
			@QueryParam("identificacion") String identificacion

			) throws RelativeException {
		return listarSeleccionFecha(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				 StringUtils.isNotBlank(codigoOperacion)?codigoOperacion:null,
				StringUtils.isNotBlank(agencia)?agencia:null, StringUtils.isNotBlank(fechaAprobacionDesde)?fechaAprobacionDesde:null,
				StringUtils.isNotBlank(fechaAprobacionHasta)?fechaAprobacionHasta:null, StringUtils.isNotBlank(identificacion)?identificacion:null );
		
	}
	
	
	
	private PaginatedListWrapper<DevolucionProcesoWrapper> listarSeleccionFecha(PaginatedWrapper pw, String codigoOperacion, String agencia,
			String fechaAprobacionDesde, String fechaAprobacionHasta, String identificacion ) throws RelativeException {
		
		PaginatedListWrapper<DevolucionProcesoWrapper> plw = new PaginatedListWrapper<>(pw);
		
		List<DevolucionProcesoWrapper> actions = this.dos.findOperacion(pw, codigoOperacion, agencia, fechaAprobacionDesde,
				fechaAprobacionHasta, identificacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.dos.countOperacion(codigoOperacion, agencia, fechaAprobacionDesde, fechaAprobacionHasta, identificacion));
			plw.setList(actions);
		}
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
	public GenericWrapper<TbQoDevolucion> cancelarSolicitudDevolucion(@QueryParam("id") String idDevolucion,
			@QueryParam("usuario") String usuario)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.cancelarSolicitudDevolucion(Long.valueOf(idDevolucion), usuario));
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

	
	@POST
	@Path("/guardarEntregaRecepcion")
	public GenericWrapper<TbQoDevolucion> guardarEntregaRecepcion( @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.guardarEntregaRecepcion((Long.valueOf(idDevolucion))));
		return loc;
	}
	
	
	@POST
	@Path("/aprobarVerificacionFirmas")
	public GenericWrapper<TbQoDevolucion> aprobarVerificacionFirmas( @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.aprobarVerificacionFirmas((Long.valueOf(idDevolucion))));
		return loc;
	}
	
	
	@POST
	@Path("/rechazarVerificacionFirmas")
	public GenericWrapper<TbQoDevolucion> rechazarVerificacionFirmas( @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.rechazarVerificacionFirmas((Long.valueOf(idDevolucion))));
		return loc;
	}
	///////////Validaciones
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
	public GenericWrapper<RespuestaBooleanaWrapper> validateCancelarSolicitud(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
		if (StringUtils.isBlank(idDevolucion)) {
		}
		GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
		RespuestaBooleanaWrapper a = this.dos.validateCancelacionSolicitud(Long.valueOf(idDevolucion));
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/existeProcesoCancelacionVigente")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<Boolean> existeProcesoCancelacionVigente(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
		if (StringUtils.isBlank(idDevolucion)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.dos.existeProcesoCancelacionVigente(Long.valueOf(idDevolucion));
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/validateSolicitarAprobacion")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<RespuestaBooleanaWrapper> validateSolicitarAprobacion(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
			RespuestaBooleanaWrapper a = this.dos.validateSolicitarAprobacion(Long.valueOf(idDevolucion));
			loc.setEntidad(a);
			return loc;
		}
	
	@GET
	@Path("/validateAprobarRechazarSolicitud")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<RespuestaBooleanaWrapper>  validateAprobarRechazarSolicitud(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
			RespuestaBooleanaWrapper a = this.dos.validateAprobarRechazarSolicitud(Long.valueOf(idDevolucion));
			loc.setEntidad(a);
			return loc;
		}
	
	@GET
	@Path("/validateEntregaRecepcion")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<RespuestaBooleanaWrapper> validateEntregaRecepcion(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
			RespuestaBooleanaWrapper a = this.dos.validateEntregaRecepcion(Long.valueOf(idDevolucion));
			loc.setEntidad(a);
			return loc;
		}
	
	
	@GET
	@Path("/validateVerificacionFirma")
	@ApiOperation(value = "GenericWrapper<Boolean>", notes = "valida el boton reverso perfeccionar en gestion de contratos", response = GenericWrapper.class)
	public GenericWrapper<RespuestaBooleanaWrapper> validateVerificacionFirma(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
			RespuestaBooleanaWrapper a = this.dos.validateVerificacionFirma(Long.valueOf(idDevolucion));
			loc.setEntidad(a);
			return loc;
		}

}
