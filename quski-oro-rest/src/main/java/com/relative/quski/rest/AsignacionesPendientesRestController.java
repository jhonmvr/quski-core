package com.relative.quski.rest;

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
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Path("/asignacionesPendientesRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Asignacion Bajo Demanda - REST CRUD")
public class AsignacionesPendientesRestController extends BaseRestController implements CrudRestControllerInterface<TbQoCreditoNegociacion, GenericWrapper<TbQoCreditoNegociacion>> {  

	@Inject
	QuskiOroService qos;
	
	
	
	public AsignacionesPendientesRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//Auto-generated
	}

	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCreditoNegociacion>", notes = "Metodo GET Retorna un Wrapper de Operaciones en espera de aprobacion",
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno exitoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = GenericWrapper.class)})
	public GenericWrapper<TbQoCreditoNegociacion> getEntity(
			@QueryParam("codigoProceso") @DefaultValue("null") String codigoProceso,
			@QueryParam("nombreAgencia") @DefaultValue("null") String nombreAgencia,
			@QueryParam("nombreProceso") @DefaultValue("null") String nombreProceso,
			@QueryParam("cedula") @DefaultValue("0") int cedula) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		TbQoCreditoNegociacion a = (TbQoCreditoNegociacion) this.qos.findAsignacionByParams(codigoProceso, nombreAgencia, nombreProceso, cedula);
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	public PaginatedListWrapper<TbQoCreditoNegociacion> listAllEntities(String arg0, String arg1, String arg2, String arg3, String arg4)
			throws RelativeException {
		return null;
	}
	@Override
	public GenericWrapper<TbQoCreditoNegociacion> persistEntity(GenericWrapper<TbQoCreditoNegociacion> arg0)
			throws RelativeException {
		return null;
	}

	@Override
	public GenericWrapper<TbQoCreditoNegociacion> getEntity(String arg0) throws RelativeException {
		return null;
	}

}
