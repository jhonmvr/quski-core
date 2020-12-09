package com.relative.quski.rest;



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

import com.relative.core.exception.RelativeException;
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
import com.relative.quski.wrapper.ResultOperacionesAprobarWrapper;
import com.relative.quski.wrapper.ResultOperacionesWrapper;


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
	public GenericWrapper<TbQoProceso> cancelarNegociacion(@QueryParam("idNegociacion") String idNegociacion, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.cancelarNegociacion( Long.valueOf( idNegociacion ), usuario ) );
		return loc;
	}	
	@GET
	@Path("/findByIdReferencia")
	public GenericWrapper<TbQoProceso> findByIdReferencia(@QueryParam("id") String id, @QueryParam("proceso") ProcesoEnum proceso) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.findProcesoByIdReferencia( Long.valueOf( id ), proceso ) );
		return loc;
	}	
	@GET
	@Path("/cambiarEstadoProceso")
	public GenericWrapper<TbQoProceso> cambiarEstadoProceso(@QueryParam("idReferencia") String idReferencia, 
			@QueryParam("proceso") ProcesoEnum proceso, 
			@QueryParam("newEstado") EstadoProcesoEnum newEstado) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.cambiarEstado( Long.valueOf( idReferencia ), proceso, newEstado ) );
		return loc;
	}	
	@GET
	@Path("/getEntity")
	public GenericWrapper<TbQoProceso> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		TbQoProceso inEg = this.qos.findProcesoById(Long.valueOf(id));
		loc.setEntidad(inEg);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	public GenericWrapper<TbQoProceso> persistEntity(GenericWrapper<TbQoProceso> wp) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.manageProceso( wp.getEntidad() ) );
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
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
	@Path("/buscarOperacionesAprobador")
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
	public GenericWrapper<String> getProcesos() throws RelativeException {
		GenericWrapper<String> w = new GenericWrapper<>();
		List<ProcesoEnum> enumProcesos = Arrays.asList(ProcesoEnum.values());
		List<String> stringsProcesos = new ArrayList<String>();
		enumProcesos.forEach(f -> stringsProcesos.add(f.toString().replace('_', ' ')));
		w.setEntidades(stringsProcesos);
		return w;
	}
	@GET	
	@Path("/getEstadosProceso")
	public GenericWrapper<String> getEstadosProceso() throws RelativeException {
		GenericWrapper<String> w = new GenericWrapper<>();
		List<EstadoProcesoEnum> enumEstadosProceso = Arrays.asList(EstadoProcesoEnum.values());
		List<String> stringsEstadosProceso = new ArrayList<String>();
		enumEstadosProceso.forEach(f -> stringsEstadosProceso.add(f.toString().replace('_', ' ')));
		w.setEntidades(stringsEstadosProceso);
		return w;
	}
	@GET	
	@Path("/getActividades")
	public GenericWrapper<String> getActividades() throws RelativeException {
		GenericWrapper<String> w = new GenericWrapper<>();
		List<ActividadEnum> enumActividades = Arrays.asList(ActividadEnum.values());
		List<String> stringsActividades = new ArrayList<String>();
		enumActividades.forEach(f -> stringsActividades.add(f.toString().replace('_', ' ')));
		w.setEntidades(stringsActividades);
		return w;
	}
	@GET
	@Path("/reasignarOperacion")
	public GenericWrapper<Boolean> reasignarOperacion(@QueryParam("id") String id, @QueryParam("proceso") ProcesoEnum proceso, @QueryParam("usuario") String usuario) throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.reasignarOperacion( Long.valueOf( id ),proceso, usuario ) );
		return loc;
	}	
	@GET
	@Path("/asignarAprobador")
	public GenericWrapper<String> asignarAprobador(@QueryParam("idReferencia") String idReferencia, @QueryParam("proceso") ProcesoEnum proceso, @QueryParam("aprobador") String aprobador) throws RelativeException {
		GenericWrapper<String> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.asignarAprobador( Long.valueOf( idReferencia ),proceso, aprobador ) );
		return loc;
	}	
	
	@POST
	@Path("/upDateEstadoProceso")
	public GenericWrapper<TbQoProceso> upDateEstadoProceso(TbQoProceso proceso) throws RelativeException {
		GenericWrapper<TbQoProceso> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.cambiarEstado( proceso.getIdReferencia(), proceso.getProceso(), proceso.getEstadoProceso() ) );
		return loc;
	}	
	
}