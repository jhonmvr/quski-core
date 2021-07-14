package com.relative.quski.rest;



import java.util.List;

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
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/direccionClienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbQoDireccionCliente - REST CRUD")
public class DireccionClienteRestController extends BaseRestController implements CrudRestControllerInterface<TbQoDireccionCliente, GenericWrapper<TbQoDireccionCliente>> {

	@Inject
	QuskiOroService qos;
	
	public DireccionClienteRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//NO UTILIZABLE
	}

	 	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada TbQoDireccionCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDireccionCliente> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoDireccionCliente> loc = new GenericWrapper<>();
		TbQoDireccionCliente inEg = this.qos.findDireccionClienteById(Long.valueOf(id));
		loc.setEntidad(inEg);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoDireccionCliente", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoDireccionCliente", response = GenericWrapper.class)
		@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDireccionCliente> persistEntity(GenericWrapper<TbQoDireccionCliente> wp)
			throws RelativeException {
		GenericWrapper<TbQoDireccionCliente> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageDireccionCliente(wp.getEntidad()));
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoDireccionCliente> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		return null;
	}
	
	@GET
	@Path("/direccionByIdCliente")
	@ApiOperation(value = "idC, tipoDireccion", notes = "Metodo Get direccionByIdCliente Retorna Genericwrapper de entidades encontradas en TbQoDireccionCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoDireccionCliente> direccionByIdCliente(@QueryParam("idC") String idC, @QueryParam("tipoDireccion") String tipoDireccion)
			throws RelativeException {
		GenericWrapper<TbQoDireccionCliente> loc = new GenericWrapper<>();
		TbQoDireccionCliente  a = this.findDireccionClienteByIdClienteAndTipoDireccion(Long.valueOf(idC), tipoDireccion);
		loc.setEntidad(a);
		return loc;
	}
	public TbQoDireccionCliente findDireccionClienteByIdClienteAndTipoDireccion(Long idC, String tipoDireccion) throws RelativeException {
		List<TbQoDireccionCliente> tmp = this.qos.findDireccionClienteByIdClienteAndTipoDireccion(idC, tipoDireccion);
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		}
		return null;
	}

}