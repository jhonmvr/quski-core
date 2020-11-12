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
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.NegociacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/negociacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "NegociacionController - REST CRUD")
public class NegociacionRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoNegociacion, GenericWrapper<TbQoNegociacion>> {

	
	@Inject
	QuskiOroService qos;

	public NegociacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoNegociacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacion> persistEntity(GenericWrapper<TbQoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageNegociacion(wp.getEntidad()));
		return loc;
	}	

	
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoNegociacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoNegociacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		TbQoNegociacion a = this.qos.findNegociacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	public PaginatedListWrapper<TbQoNegociacion> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Path("/iniciarNegociacion")
	public GenericWrapper<NegociacionWrapper> iniciarNegociacion(@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacion(cedula, asesor);
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/iniciarNegociacionEquifax")
	public GenericWrapper<NegociacionWrapper> iniciarNegociacionEquifax(@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacionEquifax(cedula, asesor);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/iniciarNegociacionFromCot")
	public GenericWrapper<NegociacionWrapper> iniciarNegociacionFromCot(@QueryParam("idCotizacion") String idCotizacion, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacionFromCot(Long.valueOf( idCotizacion ), asesor);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerNegociacionExistente")
	public GenericWrapper<NegociacionWrapper> traerNegociacionExistente(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.traerNegociacionExistente(Long.valueOf( id ));
		loc.setEntidad(a);
		return loc;
	}

}
