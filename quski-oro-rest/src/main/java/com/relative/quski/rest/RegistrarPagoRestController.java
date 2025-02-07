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
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.service.PagoService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.InicioProcesoBloqueoWrapper;
import com.relative.quski.wrapper.InicioProcesoPagoWrapper;
import com.relative.quski.wrapper.RespuestaProcesoPagoBloqueoWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/registrarPagoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "registrarPagoRestController - REST CRUD")
public class RegistrarPagoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoRegistrarPago, GenericWrapper<TbQoRegistrarPago>> {

	public RegistrarPagoRestController() throws RelativeException {
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
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada TbQoRegistrarPago", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoRegistrarPago> persistEntity(GenericWrapper<TbQoRegistrarPago> wp)
			throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		log.info("INGRESA AL REST DE persistEntity registrarPago ");		
		loc.setEntidad(this.qos.manageRegistrarPago(wp.getEntidad()));
		return loc;
		
	}
	@POST
	@Path("/iniciarProcesoRegistrarPago")
	@ApiOperation(value = "GenericWrapper<InicioProcesoPagoWrapper>", 
	notes = "Metodo Post iniciarProcesoRegistrarPago Retorna GenericWrapper de la entidad encontrada RespuestaProcesoPagoBloqueoWrapper", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RespuestaProcesoPagoBloqueoWrapper> iniciarProcesoRegistrarPago(@QueryParam("autorizacion") String autorizacion,InicioProcesoPagoWrapper wrapper) throws RelativeException {
		GenericWrapper<RespuestaProcesoPagoBloqueoWrapper> loc = new GenericWrapper<>();
		RespuestaProcesoPagoBloqueoWrapper wp  = this.ps.crearRegistrarPago( wrapper,autorizacion ); 
		loc.setEntidad( wp );
		return loc;
	}
	@POST
	@Path("/aprobarBotPago")
	@ApiOperation(value = "GenericWrapper<InicioProcesoPagoWrapper>", 
	notes = "Metodo Post iniciarProcesoRegistrarPago Retorna GenericWrapper de la entidad encontrada RespuestaProcesoPagoBloqueoWrapper", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public TbQoProceso aprobarBotPago(@QueryParam("autorizacion") String autorizacion,RespuestaProcesoPagoBloqueoWrapper wp) throws RelativeException {
		
		return this.ps.aplicarPago(wp.getCliente(), autorizacion, wp.getPagos());
	}
	@POST
	@Path("/iniciarProcesoRegistrarBloqueo")
	@ApiOperation(value = "GenericWrapper<InicioProcesoBloqueoWrapper>", 
	notes = "Metodo Post iniciarProcesoRegistrarBloqueo Retorna GenericWrapper de la entidad encontrada RespuestaProcesoPagoBloqueoWrapper", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RespuestaProcesoPagoBloqueoWrapper> iniciarProcesoRegistrarBloqueo(@QueryParam("autorizacion") String autorizacion,InicioProcesoBloqueoWrapper wrapper) throws RelativeException {
		GenericWrapper<RespuestaProcesoPagoBloqueoWrapper> loc = new GenericWrapper<>();
		
		try {
			loc.setEntidad( this.ps.crearRegistrarBloqueo(wrapper,autorizacion));
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"INTENTAR GUARDAR EN LOCAL STORAGE ");
		}
		return loc;
	}
	/*
	 * @POST
	 * 
	 * @Path("/crearRegistrarComprobanteRenovacion") public
	 * GenericWrapper<TbQoRegistrarPago>
	 * crearRegistrarComprobanteRenovacion(RegistrarPagoRenovacionWrapper registro)
	 * throws RelativeException { GenericWrapper<TbQoRegistrarPago> loc = new
	 * GenericWrapper<>(); try { loc.setEntidades(
	 * this.ps.crearRegistrarComprobanteRenovacion(registro) ); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); throw new
	 * RelativeException(Constantes.
	 * ERROR_CODE_CUSTOM,"INTENTAR GUARDAR EN LOCAL STORAGE "); } catch
	 * (RelativeException e) { e.printStackTrace(); throw e; } return loc; }
	 */

	@GET
	@Path("/findByIdClientePago")
	@ApiOperation(value = "id", 
	notes = "Metodo GET findByIdClientePago Retorna GenericWrapper de la entidad encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoRegistrarPago> findByIdClientePago(@QueryParam("id")  String id) throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		
		loc.setEntidades( this.ps.findRegistrarPagoByIdClientePago(StringUtils.isNotBlank(id)?Long.valueOf(id):null) );
		return loc;
	}
	@GET
	@Path("/enviarRespuesta")
	@ApiOperation(value = "id, isRegistro , isAprobar, nombreAprobador, correoAprobador, valorAprobador", 
	notes = "Metodo GET enviarRespuesta Retorna GenericWrapper de la entidad encontrada TbQoProceso", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> enviarRespuesta(@QueryParam("autorizacion") String autorizacion,
			@QueryParam("id") String id, 
			@QueryParam("isRegistro")  String isRegistro,
			@QueryParam("isAprobar")  String isAprobar,			
			@QueryParam("nombreAprobador")  String nombreAprobador,
			@QueryParam("correoAprobador")  String correoAprobador,
			@QueryParam("valorAprobador")  String valorAprobador,
			@QueryParam("observacionAprobador")  String observacionAprobador
			) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<TbQoProceso>();
		if( Boolean.valueOf( isAprobar )) {
			loc.setEntidad( this.ps.aprobarPago(Long.valueOf(id), Boolean.valueOf( isRegistro ), nombreAprobador,correoAprobador, 
					StringUtils.isNotBlank(valorAprobador)? Double.valueOf(valorAprobador) : null, observacionAprobador, autorizacion) );
		} else if(  !Boolean.valueOf( isAprobar ) ) {
			loc.setEntidad( this.ps.rechazarPago(Long.valueOf(id), Boolean.valueOf( isRegistro ), nombreAprobador, observacionAprobador) );
			
		}else {
			throw new RelativeException( Constantes.ERROR_CODE_READ, " AL LEER BOLLEANO DE ENTRADA.");
		}
		return loc;
	}
}