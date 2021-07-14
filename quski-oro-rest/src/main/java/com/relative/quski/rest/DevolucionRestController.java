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
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.DevolucionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
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
	public GenericWrapper<ProcesoDevolucionWrapper> buscarProcesoCancelacion(@QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.buscarProcesoCancelacion( Long.valueOf( idDevolucion )) );
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
	public GenericWrapper<ProcesoDevolucionWrapper> aprobarNegarSolicitudDevolucion(@QueryParam("idDevolucion") String idDevolucion, @QueryParam("aprobado") String aprobado)throws RelativeException {
		GenericWrapper<ProcesoDevolucionWrapper> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarNegarSolicitudDevolucion(Long.valueOf(idDevolucion), Boolean.valueOf( aprobado )));
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
	public List<TbQoDevolucion> registrarArribo( List<Long> idDevoluciones,@QueryParam("asesor") String asesor) throws RelativeException {
		List<TbQoDevolucion> loc = new ArrayList<>();
		loc= this.dos.registrarArriboAgencia(idDevoluciones,asesor);
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
	public GenericWrapper<TbQoProceso> aprobarCancelacionSolicitudDevolucion(@QueryParam("id") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad(this.dos.aprobarCancelacionSolicitudDevolucion(Long.valueOf(idDevolucion)));
		return loc;
	}
	
	@POST
	@Path("/rechazarCancelacionSolicitudDevolucion")
	@ApiOperation(value = "id", notes = "Metodo Post rechazarCancelacionSolicitudDevolucion Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> rechazarCancelacionSolicitudDevolucion(@QueryParam("id") String idDevolucion)
			throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.rechazarCancelacionSolicitudDevolucion(Long.valueOf(idDevolucion)));
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
	public GenericWrapper<TbQoDevolucion> aprobarVerificacionFirmas( @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.aprobarVerificacionFirmas((Long.valueOf(idDevolucion))));
		return loc;
	}
	
	
	@POST
	@Path("/rechazarVerificacionFirmas")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Post rechazarVerificacionFirmas Retorna GenericWrapper de la entidad encontrada TbQoDevolucion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDevolucion> rechazarVerificacionFirmas( @QueryParam("idDevolucion") String idDevolucion) throws RelativeException {
		GenericWrapper<TbQoDevolucion> loc = new GenericWrapper<>();
		
		loc.setEntidad(this.dos.rechazarVerificacionFirmas((Long.valueOf(idDevolucion))));
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
	public GenericWrapper<TbQoProceso> validateSolicitarAprobacion(@QueryParam("idDevolucion") String idDevolucion)
			throws RelativeException {
			if (StringUtils.isBlank(idDevolucion)) {
			}
			GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
			TbQoProceso a = this.dos.validateSolicitarAprobacion(Long.valueOf(idDevolucion));
			loc.setEntidad(a);
			return loc;
		}
	
	@GET
	@Path("/validateAprobarRechazarSolicitud")
	@ApiOperation(value = "idDevolucion", notes = "Metodo Get validateAprobarRechazarSolicitud retorna un GenericWrapper de la entidadad encontrada RespuestaBooleanaWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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

}
