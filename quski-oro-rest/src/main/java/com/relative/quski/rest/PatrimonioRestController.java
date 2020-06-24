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
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/patrimonioRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " TbQoPatrimonio - REST CRUD")
public class PatrimonioRestController extends BaseRestController implements CrudRestControllerInterface<TbQoPatrimonio, GenericWrapper<TbQoPatrimonio>> {

	@Inject
	QuskiOroService qos;
	
	public PatrimonioRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		//NO UTILIZABLE
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoPatrimonio>", notes = "Metodo Get Retorna GenericWrapper de el registro de TbQoPatrimonio del cliente", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoPatrimonio> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoPatrimonio> loc = new GenericWrapper<>();
		TbQoPatrimonio c = this.qos.findPatrimonioById(Long.valueOf(id));
		loc.setEntidad(c);
		return loc;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoPatrimonio>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoPatrimonio", response = GenericWrapper.class)
	public GenericWrapper<TbQoPatrimonio> persistEntity(GenericWrapper<TbQoPatrimonio> wp)
			throws RelativeException {
		GenericWrapper<TbQoPatrimonio> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.managePatrimonio(wp.getEntidad()));
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoPatrimonio> listAllEntities(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws RelativeException {
		return null;
	}
	
	

}