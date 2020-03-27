package com.relative.quski.rest;



import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.AsignacionesWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Path("/asignacionesRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Asignacion Bajo Demanda - REST CRUD")
public class AsignacionesPendientesRestController extends BaseRestController implements CrudRestControllerInterface<AsignacionesWrapper, GenericWrapper<AsignacionesWrapper>> {  

	@Inject
	QuskiOroService qos;
	
	
	
	public AsignacionesPendientesRestController() throws RelativeException {
		super();
	}
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//Auto-generated
	}

//	@GET
//	@Path("/findByParams")
//	@ApiOperation(value = "GenericWrapper<AsignacionesWrapper>", notes = "Metodo que retorna la lista de operaciones pendientes",
//	response = GenericWrapper.class)
//	@ApiResponses (value = {
//			@ApiResponse(code = 200, message = "Retorno exitoso de informacion", response = GenericWrapper.class),
//			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = GenericWrapper.class)
//	})
//	public GenericWrapper<AsignacionesWrapper> findByParams (
//			@QueryParam("codigoProceso") String codigoProceso,
//			@QueryParam("nombreAgencia") String nombreAgencia,
//			@QueryParam("nombreProceso") String nombreProceso,
//			@QueryParam("cedula") String cedula) throws RelativeException {
//		GenericWrapper<AsignacionesWrapper> plw = new GenericWrapper<>();
//		List<AsignacionesWrapper> actions = this.qos.findAsignacionesByParams(codigoProceso, nombreAgencia, nombreProceso, cedula);
//		if (actions != null) {
//			plw.setEntidades(actions);
//		}return plw;	
//	}
	@GET
	@Path("/findByParamsPaginated")
	@ApiOperation(value = "PaginatedListWrapper<AsignacionesWrapper>", notes = "Metodo Get findByParamsPaginated Retorna wrapper de informacion de paginacion y Asignaciones encontradas encontradas", 
	response = PaginatedListWrapper.class)
	@ApiResponses (value = {
			@ApiResponse(code = 200, message = "Retorno exitoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = GenericWrapper.class)})
	public PaginatedListWrapper<AsignacionesWrapper> findByParamsPaginated (
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("codigoOperacion")  String codigoOperacion,
			@QueryParam("nombreAgencia")  String nombreAgencia,
			@QueryParam("nombreProceso")  String nombreProceso,
			@QueryParam("cedula") String cedula) throws RelativeException {
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,sortDirections, isPaginated),
				codigoOperacion, nombreAgencia, nombreProceso, cedula);
	}
	private PaginatedListWrapper<AsignacionesWrapper> findByParams(PaginatedWrapper pw, String codigoOperacion, String nombreAgencia,
			String nombreProceso, String cedula) 
			throws RelativeException {
		PaginatedListWrapper<AsignacionesWrapper> plw = new PaginatedListWrapper<>(pw);
		List<AsignacionesWrapper> actions = this.qos.findAsignacionesByParamsPaginated(pw, codigoOperacion, nombreAgencia, nombreProceso, cedula);
		if (actions != null && !actions.isEmpty()) {
			plw.setList(actions);
		}return plw;
	}
	@Override
	public GenericWrapper<AsignacionesWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PaginatedListWrapper<AsignacionesWrapper> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GenericWrapper<AsignacionesWrapper> persistEntity(GenericWrapper<AsignacionesWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
}
