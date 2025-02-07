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

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/excepcionesRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "excepcionesRestController - REST CRUD")
public class ExcepcionesRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoExcepcion, GenericWrapper<TbQoExcepcion>> {
	@Inject
	QuskiOroService qos;
	@Inject
	Logger log;

	
	public ExcepcionesRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		
	}

	@GET
	@Path("/findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion")
	@ApiOperation(value = "idNegociacion, tipoExcepcion, estadoExcepcion", notes = "Metodo Get findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion Retorna GenericWrapper de la entidad encontrada TbQoExcepcion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoExcepcion> findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("tipoExcepcion") String tipoExcepcion,
			@QueryParam("estadoExcepcion") String estadoExcepcion
			) throws RelativeException {
		GenericWrapper<TbQoExcepcion> loc = new GenericWrapper<>();
		TbQoExcepcion a = this.qos.findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion( Long.valueOf( idNegociacion ), tipoExcepcion, estadoExcepcion );
		loc.setEntidad(a);
		return loc;
	}
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna entidad encontradas en TbQoExcepcion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoExcepcion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoExcepcion> loc = new GenericWrapper<>();
		TbQoExcepcion a = this.qos.finExcepcionById(Long.valueOf( id ));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/negarExcepcion")
	@ApiOperation(value = "idExc, obsAprobador, aprobador, proceso", notes = "Metodo Get negarExcepcion Retorna GenericWrapper de la entidad encontrada booleana", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Boolean> negarExcepcion(@QueryParam("autorizacion") String autorizacion, @QueryParam("idExc") String idExc, @QueryParam("obsAprobador") String obsAprobador, @QueryParam("aprobador") String aprobador, @QueryParam("proceso") String proceso) throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		ProcesoEnum proc = null;
		if( proceso.equalsIgnoreCase(ProcesoEnum.NUEVO.toString())) {
			proc = ProcesoEnum.NUEVO;
		}
		if( proceso.equalsIgnoreCase(ProcesoEnum.RENOVACION.toString())) {
			proc = ProcesoEnum.RENOVACION;
		}
		Boolean a = this.qos.negarExcepcion( Long.valueOf( idExc ), obsAprobador, aprobador, proc, autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/aprobarCobertura")
	@ApiOperation(value = "idExc, obsAprobador, aprobador, cobertura, proceso", notes = "Metodo Get aprobarCobertura Retorna GenericWrapper de la entidad encontrada booleana", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Boolean> aprobarCobertura(@QueryParam("autorizacion") String autorizacion,@QueryParam("idExc") String idExc, @QueryParam("obsAprobador") String obsAprobador, @QueryParam("aprobador") String aprobador,@QueryParam("cobertura") String cobertura, @QueryParam("proceso") String proceso) throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		ProcesoEnum proc = null;
		if( proceso.equalsIgnoreCase(ProcesoEnum.NUEVO.toString())) {
			proc = ProcesoEnum.NUEVO;
		}
		if( proceso.equalsIgnoreCase(ProcesoEnum.RENOVACION.toString())) {
			proc = ProcesoEnum.RENOVACION;
		}
		Boolean a = this.qos.aprobarCobertura(Long.valueOf( idExc ), obsAprobador, aprobador, cobertura, proc,autorizacion);
		loc.setEntidad(a);
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoExcepcion> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws RelativeException {
		return null;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoExcepcion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoExcepcion> persistEntity(GenericWrapper<TbQoExcepcion> wp) throws RelativeException {
		log.info("INGRESA AL PERSIST====> "+wp);
		GenericWrapper<TbQoExcepcion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageExcepcion(wp.getEntidad()));
		return loc;

	}
	@GET
	@Path("/findByIdNegociacion")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcion>", notes = "Metodo Get findByIdNegociacion Retorna entidades encontradas en TbQoExcepcion", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoExcepcion> findByIdNegociacion( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idNegociacion")  String idNegociacion 
			
			) throws RelativeException {
	
		
		return findByIdNegociacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf( idNegociacion ));
	}
	private PaginatedListWrapper<TbQoExcepcion> findByIdNegociacion(PaginatedWrapper pw, Long idNegociacion) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcion> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcion> actions = this.qos.findByIdNegociacion(pw, idNegociacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByIdNegociacion( idNegociacion ).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@GET
	@Path("/findByIdCliente")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcion>", notes = "Metodo Get findByIdCliente Retorna entidades encontradas en TbQoExcepcion", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoExcepcion> findByIdCliente( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCliente")  String idCliente 
			) throws RelativeException {
		return findByIdCliente(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf( idCliente ));
	}
	private PaginatedListWrapper<TbQoExcepcion> findByIdCliente(PaginatedWrapper pw, Long idCliente) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcion> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcion> actions = this.qos.findByIdCliente(pw, idCliente);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByIdCliente( idCliente ).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/findByTipoExcepcionAndIdNegociacion")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcion>", notes = "Metodo Geeet findByTipoExcepcionAndIdNegociacion Retorna entidades encontradas en TbQoExcepcion", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoExcepcion> findByIdClfindByTipoExcepcionAndIdNegociacioniente( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoExcepcion")  String tipoExcepcion,
			@QueryParam("idNegociacion")  String idNegociacion 
			) throws RelativeException {
		return findByTipoExcepcionAndIdNegociacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), tipoExcepcion, Long.valueOf( idNegociacion ));
	}
	private PaginatedListWrapper<TbQoExcepcion> findByTipoExcepcionAndIdNegociacion(PaginatedWrapper pw, String tipoExcepcion,Long idNegociacion) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcion> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcion> actions = this.qos.findByTipoExcepcionAndIdNegociacion(pw, tipoExcepcion, idNegociacion );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByTipoExcepcionAndIdNegociacion( tipoExcepcion, idNegociacion ).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/findByTipoExcepcionAndIdNegociacionAndCaracteristica")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcion>", notes = "Metodo Get findByTipoExcepcionAndIdNegociacionAndCaracteristica Retorna entidades encontradas en TbQoExcepcion", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoExcepcion> findByIdClfindByTipoExcepcionAndIdNegociacionAndCaracteristica( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoExcepcion")  String tipoExcepcion,
			@QueryParam("idNegociacion")  String idNegociacion,
			@QueryParam("caracteristica")  String caracteristica
			
			) throws RelativeException {
		return findByTipoExcepcionAndIdNegociacionAndCaracteristica(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), tipoExcepcion, Long.valueOf(idNegociacion), caracteristica);
	}
	private PaginatedListWrapper<TbQoExcepcion> findByTipoExcepcionAndIdNegociacionAndCaracteristica(PaginatedWrapper pw, String tipoExcepcion,Long idNegociacion, String caracteristica) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcion> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcion> actions = this.qos.findByTipoExcepcionAndIdNegociacionAndCaracteristica(pw, tipoExcepcion, idNegociacion, caracteristica );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristica( tipoExcepcion, idNegociacion, caracteristica ).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/excepcionCliente")
	@ApiOperation(value = "id, obsAprobador, aprobador, aprobado ", notes = "Metodo Get excepcionCliente Retorna GenericWrapper de la entidad encontrada TbQoExcepcion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoExcepcion> excepcionCliente(@QueryParam("autorizacion") String autorizacion,@QueryParam("id") String id, @QueryParam("obsAprobador") String obsAprobador, @QueryParam("aprobador") String aprobador,@QueryParam("aprobado") String aprobado ) throws RelativeException {
		GenericWrapper<TbQoExcepcion> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(id)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER ID EXCEPCION");
		}
		if(StringUtils.isBlank(obsAprobador)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER OBSERVACION DEL APROBADOR");
		}
		if(StringUtils.isBlank(aprobador)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER ID APROBADOR");
		}
		if(StringUtils.isBlank(aprobado)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER APROBADO/RECHAZADO");
		}
		TbQoExcepcion a = this.qos.excepcionCliente(Long.valueOf( id ), obsAprobador, aprobador, aprobado,autorizacion );
		loc.setEntidad(a);
		return loc;
	}
	
}
