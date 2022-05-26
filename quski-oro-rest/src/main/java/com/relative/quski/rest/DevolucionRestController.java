package com.relative.quski.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.DevolucionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.DevolucionParamsWrapper;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.DevolucionReporteWrapper;
import com.relative.quski.wrapper.EnvioTevcolWrapper;
import com.relative.quski.wrapper.ObjetoHabilitanteWrapper;
import com.relative.quski.wrapper.ProcesoDevolucionWrapper;
import com.relative.quski.wrapper.RegistroFechaArriboWrapper;
import com.relative.quski.wrapper.RespuestaBooleanaWrapper;
import com.relative.quski.wrapper.RespuestaValidacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@ApiOperation(value = "id", notes = "Metodo Get getEntity que retorna GenericWrapper TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDevolucion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		TbQoDevolucion a = this.dos.findDevolucionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoDevolucion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDevolucion> persistEntity(GenericWrapper<TbQoDevolucion> wp)
			throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.manageDevolucion(wp.getEntidad()));
		return loc;
	}
	
	
	@POST
	@Path("/registrarSolicitudDevolucion")
	@ApiOperation(value = "GenericWrapper<TbQoDevolucion>", notes = "Metodo Post registrarSolicitudDevolucion Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas ProcesoDevolucionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ProcesoDevolucionWrapper> registrarSolicitudDevolucion(GenericWrapper<TbQoDevolucion> wp)
			throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.registrarSolicitudDevolucion(wp.getEntidad()));
		return loc;
	}

	@GET
	@Path("/buscarProcesoDevolucion")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get buscarProcesoDevolucion Retorna GenericWrapper de la entidad encontrada ProcesoDevolucionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ProcesoDevolucionWrapper> buscarProcesoDevolucion(@QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.buscarProcesoDevolucion( Long.valueOf( idDevolucion )) );
		return loc;
	}	
	@GET
	@Path("/buscarProcesoCancelacion")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get buscarProcesoCancelacion Retorna GenericWrapper de la entidad encontrada ProcesoDevolucionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ProcesoDevolucionWrapper> buscarProcesoCancelacion(@QueryParam("idProceso") String idProceso) throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.buscarProcesoCancelacion( Long.valueOf( idProceso )) );
		return loc;
	}	
	@GET
	@Path("/validarProcesoActivo")
	@ApiOperation(value = "numeroOperacion", notes = "Metodo Get validarProcesoActivo Retorna GenericWrapper de la entidad encontrada RespuestaValidacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RespuestaValidacionWrapper> validarProcesoActivo(@QueryParam("numeroOperacion") String numeroOperacion) throws RelativeException {
		GenericWrapper<RespuestaValidacionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.validarProcesoActivo(  numeroOperacion ) );
		return loc;
	}
	
	
	@POST
	@Path("/mandarAprobacionSolicitudDevolucion")
	@ApiOperation(value = "id", notes = "Metodo Post mandarAprobacionSolicitudDevolucion Retorna GenericWrapper de la entidad encontrada TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiOperation(value = "idDevolucion, aprobado", notes = "Metodo Get aprobarNegarSolicitudDevolucion Retorna GenericWrapper de la entidad encontrada ProcesoDevolucionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ProcesoDevolucionWrapper> aprobarNegarSolicitudDevolucion(@QueryParam("motivo") String motivo,
			@QueryParam("autorizacion") String autorizacion,@QueryParam("idDevolucion") String idDevolucion, @QueryParam("aprobado") String aprobado
			, @QueryParam("usuario") String usuario)throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarNegarSolicitudDevolucion(Long.valueOf(idDevolucion), Boolean.valueOf( aprobado ), usuario, autorizacion, motivo));
		return loc;
	}
	
	
	@GET
	@Path("/buscarDevolucion")
	@ApiOperation(value = "PaginatedListWrapper<DevolucionProcesoWrapper>", notes = "Metodo Get buscarDevolucion Retorna wrapper de informacion de paginacion y entidades encontradas en DevolucionProcesoWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiOperation(value = "PaginatedListWrapper<DevolucionPendienteArribosWrapper>", notes = "Metodo Get buscarDevolucionPendienteArribo Retorna wrapper de informacion de paginacion y entidades encontradas en DevolucionPendienteArribosWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated),
				 StringUtils.isNotBlank(codigoOperacion)?codigoOperacion:null, StringUtils.isNotBlank(agencia)?agencia:null);
	}
	private PaginatedListWrapper<DevolucionPendienteArribosWrapper> listarPendienteArribo(PaginatedWrapper pw, String codigoOperacion, String agencia ) throws RelativeException {
		PaginatedListWrapper<DevolucionPendienteArribosWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DevolucionPendienteArribosWrapper> actions = this.dos.findOperacionArribo(pw, codigoOperacion, agencia != null ? Long.valueOf( agencia ) : null );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.dos.countOperacionArribo(codigoOperacion, agencia != null ? Long.valueOf( agencia ) : null));
			plw.setList(actions);
		}
		return plw;
	}
	
	@POST
	@Path("/registrarFechaArribo")
	@ApiOperation(value = "RegistroFechaArriboWrapper", notes = "Metodo Post registrarFechaArribo Retorna List de las entidadades encontradas TbQoDevolucion", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public List<TbQoDevolucion> registrarFechaArribo( RegistroFechaArriboWrapper rfaw ) throws RelativeException {
		List<TbQoDevolucion> loc = new ArrayList<>();
		loc= this.dos.registrarFechaArribo(rfaw);
		return loc;
	}
	@POST
	@Path("/registrarArribo")
	@ApiOperation(value = "idDevoluciones, asesor", notes = "Metodo Post registrarArribo Retorna List de las entidadades encontradas TbQoDevolucion", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public List<TbQoDevolucion> registrarArribo(@QueryParam("autorizacion") String autorizacion, List<Long> idDevoluciones,@QueryParam("usuario") String usuario) throws RelativeException {
		List<TbQoDevolucion> loc = new ArrayList<>();
		loc= this.dos.registrarArriboAgencia(idDevoluciones,usuario, autorizacion);
		return loc;
	}
	
	@GET
	@Path("/iniciarProcesoCancelacion")
	@ApiOperation(value = "id, usuario, motivo", notes = "Metodo Get iniciarProcesoCancelacion Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> iniciarProcesoCancelacion(@QueryParam("id") String id, @QueryParam("usuario") String usuario, @QueryParam("motivo") String motivo )
			throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.dos.iniciarProcesoCancelacion(Long.valueOf(id), usuario, motivo) );
		return loc;
	}
	
	@POST
	@Path("/aprobarCancelacionSolicitudDevolucion")
	@ApiOperation(value = "id", notes = "Metodo Post aprobarCancelacionSolicitudDevolucion Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> aprobarCancelacionSolicitudDevolucion(@QueryParam("autorizacion") String autorizacion,@QueryParam("id") String idDevolucion, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarCancelacionSolicitudDevolucion(Long.valueOf(idDevolucion), usuario, autorizacion));
		return loc;
	}
	
	@POST
	@Path("/rechazarCancelacionSolicitudDevolucion")
	@ApiOperation(value = "id", notes = "Metodo Post rechazarCancelacionSolicitudDevolucion Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> rechazarCancelacionSolicitudDevolucion(@QueryParam("id") String idDevolucion, @QueryParam("usuario") String usuario)
			throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.rechazarCancelacionSolicitudDevolucion(Long.valueOf(idDevolucion), usuario));
		return loc;
	}

	
	@POST
	@Path("/guardarEntregaRecepcion")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Post guardarEntregaRecepcion Retorna GenericWrapper de la entidad encontrada TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDevolucion> guardarEntregaRecepcion( @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.guardarEntregaRecepcion((Long.valueOf(idDevolucion))));
		return loc;
	}
	
	@GET
	@Path("/validarInicioDeFlujo")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validarInicioDeFlujo Retorna GenericWrapper Booleano", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Boolean> validarInicioDeFlujo(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
		if (StringUtils.isBlank(idDevolucion)) {
		}
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.dos.validateAprobarCancelacionSolicitud(Long.valueOf(idDevolucion));
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/aprobarVerificacionFirmas")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Post aprobarVerificacionFirmas Retorna GenericWrapper de la entidad encontrada TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDevolucion> aprobarVerificacionFirmas(@QueryParam("autorizacion") String autorizacion,@QueryParam("usuario") String usuario,@QueryParam("motivo") String motivo, @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.aprobarVerificacionFirmas(Long.valueOf(idDevolucion), autorizacion, motivo, usuario));
		return loc;
	}
	
	
	@POST
	@Path("/rechazarVerificacionFirmas")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Post rechazarVerificacionFirmas Retorna GenericWrapper de la entidad encontrada TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDevolucion> rechazarVerificacionFirmas( @QueryParam("idDevolucion") String idDevolucion,@QueryParam("usuario") String usuario, @QueryParam("motivo") String motivo) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.rechazarVerificacionFirmas(Long.valueOf(idDevolucion), motivo, usuario));
		return loc;
	}
	@GET
	@Path("/validateAprobarCancelarSolicitud")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validateAprobarCancelarSolicitud Retorna GenericWrapper Booleano", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@Path("/validateSolicitarAprobacion")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validateSolicitarAprobacion Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> validateSolicitarAprobacion(@QueryParam("autorizacion") String autorizacion,@QueryParam("idDevolucion") String idDevolucion,
			@QueryParam("usuario") String usuario)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
			TbQoProceso a = this.dos.validateSolicitarAprobacion(Long.valueOf(idDevolucion), usuario, autorizacion);
			loc.setEntidad(a);
			return loc;
		}
	
	@GET
	@Path("/validateAprobarRechazarSolicitud")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validateAprobarRechazarSolicitud retorna un GenericWrapper de la entidadad encontrada RespuestaBooleanaWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RespuestaBooleanaWrapper>  validateAprobarRechazarSolicitud(@QueryParam("idDevolucion") String idDevolucion, @QueryParam("usuario") String usuario)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
			RespuestaBooleanaWrapper a = this.dos.validateAprobarRechazarSolicitud(Long.valueOf(idDevolucion), usuario);
			loc.setEntidad(a);
			return loc;
		}
	
	@GET
	@Path("/validateEntregaRecepcion")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validateEntregaRecepcion retorna un GenericWrapper de la entidadad encontrada RespuestaBooleanaWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validateVerificacionFirma retorna un GenericWrapper de la entidadad encontrada RespuestaBooleanaWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RespuestaBooleanaWrapper> validateVerificacionFirma(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<RespuestaBooleanaWrapper> loc = new GenericWrapper<>();
			RespuestaBooleanaWrapper a = this.dos.validateVerificacionFirma(Long.valueOf(idDevolucion));
			loc.setEntidad(a);
			return loc;
		}
	
	@POST
	@Path("/reporteDevolucion")
	@ApiOperation(value = "PaginatedListWrapper<DevolucionReporteWrapper>", notes = "Metodo Get reporteDevolucion Retorna wrapper de informacion de paginacion y entidades encontradas en DevolucionPendienteArribosWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<DevolucionReporteWrapper> reporteDevolucion(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, DevolucionParamsWrapper wp
			) throws RelativeException {
		PaginatedWrapper pw = new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated);
		PaginatedListWrapper<DevolucionReporteWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DevolucionReporteWrapper> actions = this.dos.findDevolucionReporte(pw, wp );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.dos.countDevolucionReporte(wp));
			plw.setList(actions);
		}
		return plw;
		
	}
	
	@POST
	@Path("/procesoEntrega")
	@ApiOperation(value = "PaginatedListWrapper<DevolucionReporteWrapper>", notes = "Metodo Get reporteDevolucion Retorna wrapper de informacion de paginacion y entidades encontradas en DevolucionPendienteArribosWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<DevolucionReporteWrapper> procesoEntrega(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, DevolucionParamsWrapper wp
			) throws RelativeException {
		PaginatedWrapper pw = new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated);
		PaginatedListWrapper<DevolucionReporteWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DevolucionReporteWrapper> actions = this.dos.findDevolucionProceso(pw, wp );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.dos.countDevolucionProceso(wp));
			plw.setList(actions);
		}
		return plw;
		
	}
	
	@POST
	@Path("/descargarReporte")
	@ApiOperation(value = "PaginatedListWrapper<DevolucionReporteWrapper>", notes = "Metodo Get reporteDevolucion Retorna wrapper de informacion de paginacion y entidades encontradas en DevolucionPendienteArribosWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public ObjetoHabilitanteWrapper descargarReporte(@QueryParam("autorizacion") String autorizacion,DevolucionParamsWrapper wp) throws RelativeException {
		
	
		
		return  this.dos.descargarReporte( wp, autorizacion );
		
	}
	

	@POST
	@Path("/envioTevcol")
	@ApiOperation(value = "GenericWrapper", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas GenericWrapper", response = GenericWrapper.class)
	public Map<EnvioTevcolWrapper, String> envioTevcol(List<EnvioTevcolWrapper> wp, @QueryParam("autorizacion") String autorizacion, @QueryParam("usuario") String usuario) throws RelativeException {
		return this.dos.envioTevcol(wp, usuario, autorizacion);
	}
	@POST
	@Path("/transporteTevcol")
	@ApiOperation(value = "GenericWrapper", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas GenericWrapper", response = GenericWrapper.class)
	public Map<EnvioTevcolWrapper, String> transporteTevcol(List<EnvioTevcolWrapper> wp, @QueryParam("autorizacion") String autorizacion, @QueryParam("usuario") String usuario) throws RelativeException {
		return this.dos.transporteTevcol(wp, usuario, autorizacion);
	}
	@POST
	@Path("/noEnviadoTevcol")
	@ApiOperation(value = "GenericWrapper", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas GenericWrapper", response = GenericWrapper.class)
	public Map<EnvioTevcolWrapper, String> noEnviadoTevcol(List<EnvioTevcolWrapper> wp, @QueryParam("autorizacion") String autorizacion, @QueryParam("usuario") String usuario) throws RelativeException {
		return this.dos.noEnviadoTevcol(wp, usuario, autorizacion);
	}
	@POST
	@Path("/reportarTevcol")
	@ApiOperation(value = "GenericWrapper", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas GenericWrapper", response = GenericWrapper.class)
	public Map<EnvioTevcolWrapper, String> reportarTevcol(List<EnvioTevcolWrapper> wp, @QueryParam("autorizacion") String autorizacion, @QueryParam("usuario") String usuario) throws RelativeException {
		return this.dos.reportarTevcol(wp, usuario, autorizacion);
	}
	@POST
	@Path("/confirmarTevcol")
	@ApiOperation(value = "GenericWrapper", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas GenericWrapper", response = GenericWrapper.class)
	public Map<EnvioTevcolWrapper, String> confirmarTevcol(List<EnvioTevcolWrapper> wp, @QueryParam("autorizacion") String autorizacion, @QueryParam("usuario") String usuario) throws RelativeException {
		return this.dos.confirmarTevcol(wp, usuario, autorizacion);
	}
	
}
