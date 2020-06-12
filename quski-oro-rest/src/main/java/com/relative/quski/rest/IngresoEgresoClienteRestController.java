package com.relative.quski.rest;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/ingresoEgresoClienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbQoIngresoEgresoCliente - REST CRUD")
public class IngresoEgresoClienteRestController extends BaseRestController implements CrudRestControllerInterface<TbQoIngresoEgresoCliente, GenericWrapper<TbQoIngresoEgresoCliente>> {

	@Inject
	QuskiOroService qos;
	
	public IngresoEgresoClienteRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//NO UTILIZABLE
	}

	 	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoIngresoEgresoCliente>", notes = "Metodo Get Retorna GenericWrapper de el registro de ingres egreso del cliente", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoIngresoEgresoCliente> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoIngresoEgresoCliente> loc = new GenericWrapper<>();
		TbQoIngresoEgresoCliente inEg = this.qos.findIngresoEgresoClienteById(Long.valueOf(id));
		loc.setEntidad(inEg);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoIngresoEgresoCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoIngresoEgresoCliente", response = GenericWrapper.class)
	public GenericWrapper<TbQoIngresoEgresoCliente> persistEntity(GenericWrapper<TbQoIngresoEgresoCliente> wp)
			throws RelativeException {
		GenericWrapper<TbQoIngresoEgresoCliente> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageIngresoEgresoCliente(wp.getEntidad()));
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoIngresoEgresoCliente> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		return null;
	}
	
	

}