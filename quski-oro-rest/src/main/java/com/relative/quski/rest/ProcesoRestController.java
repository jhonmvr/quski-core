package com.relative.quski.rest;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.CabeceraWrapper;
import com.relative.quski.wrapper.ProcesoCaducadoWrapper;
import com.relative.quski.wrapper.ResultOperacionesAprobarWrapper;
import com.relative.quski.wrapper.ResultOperacionesWrapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/procesoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcesoRestController extends BaseRestController implements CrudRestControllerInterface<TbQoProceso, GenericWrapper<TbQoProceso>> {

	@Inject
	QuskiOroService qos;
	
	public ProcesoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//NO UTILIZABLE
	}

	@GET
	@Path("/cancelarNegociacion")
	@ApiOperation(value = "idNegociacion, usuario", notes = "Metodo Get cancelarNegociacion Retorna wrapper de entidades encontradas en TbQoProceso", response = GenericWrapper.class)
	public GenericWrapper<TbQoProceso> cancelarNegociacion(@QueryParam("idNegociacion") String idNegociacion, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.cancelarNegociacion( Long.valueOf( idNegociacion ), usuario ) );
		return loc;
	}	
	@GET
	@Path("/findByIdReferencia")
	@ApiOperation(value = "id, proceso", notes = "Metodo Get findByIdReferencia Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	public GenericWrapper<TbQoProceso> findByIdReferencia(@QueryParam("id") String id, @QueryParam("proceso") ProcesoEnum proceso) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.findProcesoByIdReferencia( Long.valueOf( id ), proceso ) );
		return loc;
	}	
	@GET
	@Path("/cambiarEstadoProceso")
	@ApiOperation(value = "idReferencia, proceso, newEstado, usuario ", notes = "Metodo Get cambiarEstadoProceso Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	public GenericWrapper<TbQoProceso> cambiarEstadoProceso(@QueryParam("idReferencia") String idReferencia, 
			@QueryParam("proceso") ProcesoEnum proceso, 
			@QueryParam("newEstado") EstadoProcesoEnum newEstado,
			@QueryParam("usuario") String usuario
			) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.cambiarEstado( Long.valueOf( idReferencia ), proceso, newEstado, usuario ) );
		return loc;
	}	
	@GET
	@Path("/validarAprobador")
	@ApiOperation(value = "idReferencia, proceso, aprobador ", notes = "Metodo Get validarAprobador Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	public GenericWrapper<String> validarAprobador(@QueryParam("idReferencia") String idReferencia, 
			@QueryParam("proceso") ProcesoEnum proceso, @QueryParam("idProceso") String idProceso,
			@QueryParam("aprobador") String aprobador
			) throws RelativeException {
		GenericWrapper<String> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.validarAprobador( Long.valueOf( idReferencia ), proceso, aprobador, Long.valueOf( idProceso ) ) );
		return loc;
	}	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	public GenericWrapper<TbQoProceso> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		TbQoProceso inEg = this.qos.findProcesoById(Long.valueOf(id));
		loc.setEntidad(inEg);
		return loc;
	}
	@GET
	@Path("/listAlertaDeProcesos")
	@ApiOperation(value = "", notes = "Metodo Get listAlertaDeProcesos Retorna GenericWrapper de la entidad encontrada ProcesoCaducadoWrapper", response = GenericWrapper.class)
	public GenericWrapper<ProcesoCaducadoWrapper> listAlertaDeProcesos() throws RelativeException {
		GenericWrapper<ProcesoCaducadoWrapper> loc = new GenericWrapper<>();
		List<ProcesoCaducadoWrapper> a = this.qos.listAlertaDeProcesos();
		loc.setEntidades(a);
		return loc;
	}
	@GET
	@Path("/listAlertaDeProcesosAprobador")
	@ApiOperation(value = "aprobador", notes = "Metodo Get listAlertaDeProcesosAprobador Retorna GenericWrapper de la entidad encontrada ProcesoCaducadoWrapper", response = GenericWrapper.class)
	public GenericWrapper<ProcesoCaducadoWrapper> listAlertaDeProcesosAprobador(@QueryParam("aprobador") String aprobador) throws RelativeException {
		GenericWrapper<ProcesoCaducadoWrapper> loc = new GenericWrapper<>();
		List<ProcesoCaducadoWrapper> a = this.qos.listAlertaDeProcesosAprobador( aprobador );
		loc.setEntidades( a );
		return loc;
	}
// 1 - Un web service que busque por Estado Pendiente_Aprobacion y Todos los procesos y todos los aprobadores.
// 2 - Un web service que busque por Estado Pendiente_Aprobacion y Todos los procesos y resiva como parametro el nombre del usuario logeado.
//	@GET
//	@Path("/mookDeTiempoCaducado")
//	public GenericWrapper<Boolean> mookDeTiempoCaducado() throws RelativeException {
//		GenericWrapper<Boolean> loc = new GenericWrapper<>();
//		this.qos.findByTiempoBaseAprobadorProcesoEstadoProceso();
//		loc.setEntidad(Boolean.TRUE);
//		return loc;
//	}
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoProceso>", notes = "Metodo Get persistEntity Retorna GenericWrapper de la entidad encontrada TbQoProceso", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoProceso> persistEntity(GenericWrapper<TbQoProceso> wp) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.manageProceso( wp.getEntidad() ) );
		return loc;
	}
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoProceso>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoProceso", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoProceso> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll( new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated) );

	}

	private PaginatedListWrapper<TbQoProceso> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoProceso> plw = new PaginatedListWrapper<>(pw);
		List<TbQoProceso> actions = this.qos.findAllProceso(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countProceso().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@POST
	@Path("/buscarOperaciones")
	@ApiOperation(value = "BusquedaOperacionesWrapper", notes = "Metodo Post buscarOperaciones Retorna GenericWrapper de la entidad encontrada ResultOperacionesWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ResultOperacionesWrapper> buscarOperaciones( BusquedaOperacionesWrapper wp) throws RelativeException {
		GenericWrapper<ResultOperacionesWrapper> loc = new GenericWrapper<>();
		if(wp.getNumberPage() == null ) {
			wp.setNumberPage( Long.valueOf(0) );
		}
		if(wp.getNumberItems() == null) {
			wp.setNumberItems(Long.valueOf(5));
		}
		loc.setEntidad( this.qos.findOperaciones( wp ) );
		return loc;
	}
	
	@POST
	@Path("/getMontoFinanciado")
	@ApiOperation(value = "BusquedaOperacionesWrapper", notes = "Metodo Post buscarOperaciones Retorna GenericWrapper de la entidad encontrada ResultOperacionesWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<BigDecimal> getMontoFinanciado( BusquedaOperacionesWrapper wp) throws RelativeException {
		GenericWrapper<BigDecimal> loc = new GenericWrapper<>();
		if(wp.getNumberPage() == null ) {
			wp.setNumberPage( Long.valueOf(0) );
		}
		if(wp.getNumberItems() == null) {
			wp.setNumberItems(Long.valueOf(5));
		}
		loc.setEntidad( this.qos.getMontoFinanciado( wp ) );
		return loc;
	}
	@POST
	@Path("/buscarOperacionesAprobador")
	@ApiOperation(value = "BusquedaPorAprobarWrapper", notes = "Metodo Post buscarOperacionesAprobador Retorna GenericWrapper de la entidad encontrada ResultOperacionesAprobarWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ResultOperacionesAprobarWrapper> buscarOperacionesAprobador( BusquedaPorAprobarWrapper wp) throws RelativeException {
		GenericWrapper<ResultOperacionesAprobarWrapper> loc = new GenericWrapper<>();
		if(wp.getNumberPage() == null ) {
			wp.setNumberPage( Long.valueOf(0) );
		}
		if(wp.getNumberItems() == null) {
			wp.setNumberItems(Long.valueOf(5));
		}
		loc.setEntidad( this.qos.findOperacionesPorAprobar( wp ) );
		return loc;
	}
	@GET	
	@Path("/getProcesos")
	@ApiOperation(value = "", notes = "Metodo Get getProcesos Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> getProcesos() throws RelativeException {
		GenericWrapper<String> w = new GenericWrapper<>();
		List<ProcesoEnum> enumProcesos = Arrays.asList(ProcesoEnum.values());
		List<String> stringsProcesos = new ArrayList<String>();
		enumProcesos.forEach(f -> stringsProcesos.add(f.toString().replace('_', ' ')));
		w.setEntidades(stringsProcesos);
		return w;
	}
	@POST	
	@Path("/getEstadosProceso")
	@ApiOperation(value = "", notes = "Metodo Get getEstadosProceso Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<EstadoProcesoEnum> getEstadosProceso(List<ProcesoEnum> proceso) throws RelativeException {
		GenericWrapper<EstadoProcesoEnum> w = new GenericWrapper<>();
		List<EstadoProcesoEnum> enumEstadosProceso = Arrays.asList(EstadoProcesoEnum.values());
		List<String> stringsEstadosProceso = new ArrayList<String>();
		enumEstadosProceso.forEach(f -> stringsEstadosProceso.add(f.toString().replace('_', ' ')));
		w.setEntidades( this.qos.getEstadosProceso(proceso));
		return w;
	}
	@POST	
	@Path("/getActividades")
	@ApiOperation(value = "", notes = "Metodo Get getActividades Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> getActividades(List<ProcesoEnum> proceso) throws RelativeException {
		GenericWrapper<String> w = new GenericWrapper<>();
		List<ActividadEnum> enumActividades = Arrays.asList(ActividadEnum.values());
		List<String> stringsActividades = new ArrayList<String>();
		enumActividades.forEach(f -> stringsActividades.add(f.toString().replace('_', ' ')));
		w.setEntidades( this.qos.getActividades(proceso));
		return w;
	}
	@GET
	@Path("/reasignarOperacion")
	@ApiOperation(value = "id, proceso, usuario", notes = "Metodo Get reasignarOperacion Retorna GenericWrapper de la entidad encontrada Booleana", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Boolean> reasignarOperacion(@QueryParam("id") String id, @QueryParam("proceso") ProcesoEnum proceso, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.reasignarOperacion( Long.valueOf( id ),proceso, usuario ) );
		return loc;
	}	
	@GET
	@Path("/asignarAprobador")
	@ApiOperation(value = "idReferencia, proceso, aprobador ", notes = "Metodo Get asignarAprobador Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> asignarAprobador(@QueryParam("idReferencia") String idReferencia, @QueryParam("idProceso") String idProceso, 
			@QueryParam("proceso") ProcesoEnum proceso, @QueryParam("aprobador") String aprobador) throws RelativeException {
		GenericWrapper<String> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.asignarAprobador( Long.valueOf( idReferencia ),proceso, aprobador , Long.valueOf( idProceso )) );
		return loc;
	}	
	@GET
	@Path("/asignarAprobadorExcepcion")
	@ApiOperation(value = "idReferencia, aprobador", notes = "Metodo Get asignarAprobadorExcepcion Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> asignarAprobador(@QueryParam("idReferencia") String idReferencia,@QueryParam("aprobador") String aprobador) throws RelativeException {
		GenericWrapper<String> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.asignarAprobadorExcepcion( Long.valueOf( idReferencia ), aprobador ) );
		return loc;
	}
	@GET
	@Path("/traerNumeroOperacionMadre")
	@ApiOperation(value = "codigoBpm", notes = "Metodo Get traerNumeroOperacionMadre Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> traerNumeroOperacionMadre(@QueryParam("codigoBpm") String codigoBpm) throws RelativeException {
		GenericWrapper<String> loc = new GenericWrapper<>();
		String a = this.qos.traerNumeroOperacionMadre( codigoBpm );
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/caducarCreditos")
	@ApiOperation(value = "", notes = "Metodo Get caducarCreditos Retorna GenericWrapper de la entidad encontrada Long", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Long> caducarCreditos() throws RelativeException {
		GenericWrapper<Long> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.caducarCreditos() );
		return loc;
	}
	
	@GET
	@Path("/generarCodigo")
	@ApiOperation(value = "codigo", notes = "Metodo Get generarCodigo Retorna GenericWrapper de la entidad encontrada String", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> generarCodigo(@QueryParam("codigo") String codigo) throws RelativeException {
		GenericWrapper<String> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.generarCodigo(codigo) );
		return loc;
	}
	

	@GET
	@Path("/getCabecera")
	public CabeceraWrapper getCabecera( @QueryParam("idReferencia") String idReferencia, @QueryParam("proceso") String proceso) throws RelativeException{
		
		if(StringUtils.isBlank(idReferencia) || StringUtils.isBlank(proceso)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID REFERENCIA");
		}
		
		return this.qos.getCabecera(idReferencia, proceso);		
	}
}