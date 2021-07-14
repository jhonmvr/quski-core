package com.relative.quski.rest;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.EnumsWrapper;
import com.relative.quski.wrapper.TrackingWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Path("/trackingRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Traking - REST CRUD")
public class TrackingRestController extends BaseRestController implements CrudRestControllerInterface<TbQoTracking, GenericWrapper<TbQoTracking>> {  
	@Inject
	Logger log;
	
	public TrackingRestController() throws RelativeException {
		super();
		//  Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//  Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTracking>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoTracking", response = GenericWrapper.class)
	public GenericWrapper<TbQoTracking> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoTracking> loc = new GenericWrapper<>();
		TbQoTracking a = this.qos.findTrackingById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoTracking>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoTracking", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoTracking> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
	}
	private PaginatedListWrapper<TbQoTracking> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTracking> plw = new PaginatedListWrapper<>(pw);
		List<TbQoTracking> actions = this.qos.findAllTracking(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countTracking().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTracking>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoTracking", response = GenericWrapper.class)
	public GenericWrapper<TbQoTracking> persistEntity(GenericWrapper<TbQoTracking> wp) throws RelativeException {
		GenericWrapper<TbQoTracking> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageTracking(wp.getEntidad()));
		return loc;
	}
	
//	@POST
//	@Path("/busqueda")
//	public PaginatedListWrapper<TbQoTracking> findBusquedaParametros(
//			@QueryParam("page") @DefaultValue("1") String page,
//			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
//			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
//			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
//			@QueryParam("isPaginated") @DefaultValue("Y") String isPaginated,
//			@QueryParam("proceso") ProcesoEnum proceso,
//			@QueryParam("actividad") String actividad,
//			@QueryParam("seccion") String seccion,
//			@QueryParam("codigoBpm") String codigoBpm,
//			@QueryParam("codigoOperacionSoftbank") String codigoOperacionSoftbank,
//			@QueryParam("usuarioCreacion") String usuarioCreacion,
//			@QueryParam("fechaCreacion") Date fechaCreacion,
//			@QueryParam("fechaDesde") Date fechaDesde,
//			@QueryParam("fechaHasta") Date fechaHasta
//			) throws RelativeException {
//		PaginatedListWrapper<TbQoTracking> plw = new PaginatedListWrapper<>(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
//		log.info("-------->>>>"+plw.getPageSize() );
//		log.info("-------->>>>"+plw.getIsPaginated());
//
//		List<TbQoTracking> actions = this.qos.findBusquedaParametros(wp,plw);
//		if (actions != null && !actions.isEmpty()) {
//			plw.setTotalResults(this.qos.countTracking(wp).intValue());
//			plw.setList(actions);
//		}
//		return plw;
//	}
	@GET
	@Path("/busquedaTracking")
	@ApiOperation(value = "", notes = "Metodo que devuelve la lista de procesos encontrados en TbQoTracking", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoTracking> busquedaTracking(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("Y") String isPaginated,
			@QueryParam("proceso") ProcesoEnum proceso,
			@QueryParam("actividad") String actividad,
			@QueryParam("seccion") String seccion,
			@QueryParam("codigoBpm") String codigoBpm,
			@QueryParam("codigoOperacionSoftbank") String codigoOperacionSoftbank,
			@QueryParam("usuarioCreacion") String usuarioCreacion,
			@QueryParam("fechaDesde") Date fechaDesde,
			@QueryParam("fechaHasta") Date fechaHasta
			) throws RelativeException {
		Integer firstItem = Integer.valueOf(page);
		TrackingWrapper tra = new TrackingWrapper();
		tra.setProceso(proceso);
		tra.setActividad(actividad);
		tra.setSeccion(seccion);
		tra.setCodigoBpm(codigoBpm);
		tra.setCodigoOperacionSoftbank(codigoOperacionSoftbank);
		tra.setUsuarioCreacion(usuarioCreacion);
		tra.setFechaDesde(fechaDesde);
		tra.setFechaHasta(fechaHasta);
		return findAllBusqueda( tra, new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
	}
	private PaginatedListWrapper<TbQoTracking> findAllBusqueda( TrackingWrapper wrapper, PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoTracking> plw = new PaginatedListWrapper<>(pw);
		List<TbQoTracking> actions = this.qos.findBusquedaParametros(wrapper, pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countTracking(wrapper).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/listProceso")
	@ApiOperation(value = "", notes = "Metodo que devuelve la lista de procesos encontrados en ProcesoEnum", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ProcesoEnum> listProceso() throws RelativeException {
		GenericWrapper<ProcesoEnum> loc = new GenericWrapper<>();
		List<ProcesoEnum> a = this.qos.findListProcesos();
		loc.setEntidades(a);
		return loc;
	}
	@GET
	@Path("/listActividad")
	@ApiOperation(value = "String proceso", notes = "Metodo que devuelve la lista de actividades encontradas en ActividadEnum", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ActividadEnum> listActividad(@QueryParam("proceso") String proceso) throws RelativeException {
		GenericWrapper<ActividadEnum> loc = new GenericWrapper<>();
		List<ActividadEnum> a = this.qos.findListActividadByProceso(proceso);
		loc.setEntidades(a);
		return loc;
	}
	@GET
	@Path("/listSeccion")
	@ApiOperation(value = "String actividad", notes = "Metodo que devuelve la lista de secciones encontradas en SeccionEnum", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SeccionEnum> listSeccion(@QueryParam("actividad") String actividad) throws RelativeException {
		GenericWrapper<SeccionEnum> loc = new GenericWrapper<>();
		List<SeccionEnum> a = this.qos.findListSeccionByActividad(actividad);
		loc.setEntidades(a);
		return loc;
	}
	
	@POST
	@Path("/registrar")
	@ApiOperation(value = "GenericWrapper<TbQoTracking>", notes = "Metodo Post persistEntity Registra  y retorna la entidad TbQoTracking", response = TbQoTracking.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoTracking> registrar(TbQoTracking wp) throws RelativeException {
		GenericWrapper<TbQoTracking> loc = new GenericWrapper<>();
		TbQoTracking a = this.qos.registrarTraking(wp);
		loc.setEntidad(a);
		return loc;
	}

	@GET	
	@Path("/getActividadesProcesosAndSecciones")
	@ApiOperation(value = "", notes = "Metodo Get getActividadesProcesosAndSecciones Retorna GenericWrapper de la entidad encontrada EnumsWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<EnumsWrapper> getActividadesProcesosAndSecciones() throws RelativeException {
		GenericWrapper<EnumsWrapper> w = new GenericWrapper<>();
		EnumsWrapper enums = new EnumsWrapper();
		
		List<ProcesoEnum> enumProcesos = Arrays.asList(ProcesoEnum.values());
		List<String> stringsProcesos = new ArrayList<String>();
		enumProcesos.forEach(f -> stringsProcesos.add(f.toString().replace('_', ' ')));
		enums.setProcesos(stringsProcesos); 
		
		List<ActividadEnum> enumActividades = Arrays.asList(ActividadEnum.values());
		List<String> stringActividades = new ArrayList<String>();
		enumActividades.forEach(f -> stringActividades.add(f.toString().replace('_', ' ')));
		enums.setActividades(stringActividades); 
		
		List<SeccionEnum> enumSecciones = Arrays.asList(SeccionEnum.values());
		List<String> stringSecciones = new ArrayList<String>();
		enumSecciones.forEach(f -> stringSecciones.add(f.toString().replace('_', ' ')));
		enums.setSecciones(stringSecciones); 
		w.setEntidad( enums );
		return w;
	}

}
