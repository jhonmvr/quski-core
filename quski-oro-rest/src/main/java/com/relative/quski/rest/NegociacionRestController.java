package com.relative.quski.rest;



import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.CalculadoraOpcionWrapper;
import com.relative.quski.wrapper.EquifaxVariableWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;
import com.relative.quski.wrapper.TipoOroWrapper;

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
	public GenericWrapper<NegociacionWrapper> iniciarNegociacion(@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacion(cedula, asesor, Long.valueOf(idAgencia));
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/iniciarNegociacionEquifax")
	public GenericWrapper<NegociacionWrapper> iniciarNegociacionEquifax(@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacionEquifax(cedula, asesor, Long.valueOf(idAgencia));
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/iniciarNegociacionFromCot")
	public GenericWrapper<NegociacionWrapper> iniciarNegociacionFromCot(@QueryParam("idCotizacion") String idCotizacion, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacionFromCot(Long.valueOf( idCotizacion ), asesor, Long.valueOf(idAgencia));
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

	@GET
	@Path("/pruebaWSPersona")
	public GenericWrapper<EquifaxVariableWrapper> iniciarNegociacion(@QueryParam("cedula") String cedula) throws RelativeException {
		GenericWrapper<EquifaxVariableWrapper> loc = new GenericWrapper<>();
		//List<EquifaxVariableWrapper> a = this.qos.traerVariablesEquifax(cedula);
		//loc.setEntidades(a);
		return loc;
	}
	
	@POST
	@Path("/agregarJoya")
	public GenericWrapper<TbQoTasacion> agregarJoya(TbQoTasacion joya, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		List<TbQoTasacion> a = this.qos.agregarJoya(joya, asesor);
		loc.setEntidades(a);
		return loc;
	}

	@POST
	@Path("/guardarOpcionCredito")
	public GenericWrapper<TbQoCreditoNegociacion> guardarOpcionCredito(List<CalculadoraOpcionWrapper> opcionCredito, @QueryParam("asesor") String asesor, @QueryParam("idCredito") String idCredito) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCredito)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL CREDITO");
		}
		TbQoCreditoNegociacion a = this.qos.guardarOpcionCredito(opcionCredito, asesor, Long.valueOf(idCredito) );
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/verPrecio")
	public GenericWrapper<TipoOroWrapper> verPrecio(TbQoCliente cliente, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<TipoOroWrapper> loc = new GenericWrapper<>();
		List<TipoOroWrapper> a = this.qos.verPrecio(cliente);
		loc.setEntidades(a);
		return loc;
	}
	@POST
	@Path("/solicitarExcepcion")
	public GenericWrapper<TbQoExcepcion> solicitarExcepcion(TbQoExcepcion excepcion) throws RelativeException {
		GenericWrapper<TbQoExcepcion> loc = new GenericWrapper<>();
		TbQoExcepcion a = this.qos.solicitarExcepcion(excepcion);
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/actualizarVariables")
	public GenericWrapper<TbQoVariablesCrediticia> actualizarVariables( GenericWrapper<TbQoVariablesCrediticia> wrapper, @QueryParam("idNego") String idNego) throws RelativeException {
		GenericWrapper<TbQoVariablesCrediticia> loc = new GenericWrapper<>();
		ArrayList<TbQoVariablesCrediticia> l = this.qos.actualizarVariables(wrapper.getEntidades(), Long.valueOf( idNego ));
		loc.setEntidades(l);
		return loc;
	}
	
	
	
	
}
