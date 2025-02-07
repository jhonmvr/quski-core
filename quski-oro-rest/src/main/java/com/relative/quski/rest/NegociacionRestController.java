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
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.CalculadoraOpcionWrapper;
import com.relative.quski.wrapper.ClienteYReferidoWrapper;
import com.relative.quski.wrapper.EquifaxVariableWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;
import com.relative.quski.wrapper.TipoOroWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
	@ApiOperation(value = "GenericWrapper<TbQoNegociacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoNegociacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoNegociacion> persistEntity(GenericWrapper<TbQoNegociacion> wp) throws RelativeException {
		GenericWrapper<TbQoNegociacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageNegociacion(wp.getEntidad()));
		return loc;
	}	

	
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna wrapper de entidades encontradas en TbQoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
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
	@ApiOperation(value = "cedula, asesor, idAgencia", notes = "Metodo Get iniciarNegociacion Retorna wrapper de entidades encontradas en NegociacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<NegociacionWrapper> iniciarNegociacion(@QueryParam("autorizacion") String autorizacion, @QueryParam("nombreAgencia") String nombreAgencia, @QueryParam("telefonoAsesor") String telefonoAsesor,
			@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacion(cedula, asesor, Long.valueOf(idAgencia), autorizacion,nombreAgencia, telefonoAsesor);
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/iniciarNegociacionEquifax")
	@ApiOperation(value = "cedula, asesor, idAgencia", notes = "Metodo Get iniciarNegociacionEquifax Retorna wrapper de entidades encontradas en NegociacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<NegociacionWrapper> iniciarNegociacionEquifax(@QueryParam("autorizacion") String autorizacion, @QueryParam("nombreAgencia") String nombreAgencia, @QueryParam("telefonoAsesor") String telefonoAsesor,
			@QueryParam("cedula") String cedula, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacionEquifax(cedula, asesor, Long.valueOf(idAgencia), autorizacion,nombreAgencia, telefonoAsesor);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/iniciarNegociacionFromCot")
	@ApiOperation(value = "idCotizacion, asesor, idAgencia", notes = "Metodo Get iniciarNegociacionFromCot Retorna wrapper de entidades encontradas en NegociacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<NegociacionWrapper> iniciarNegociacionFromCot(@QueryParam("autorizacion") String autorizacion, @QueryParam("nombreAgencia") String nombreAgencia, @QueryParam("telefonoAsesor") String telefonoAsesor,
			@QueryParam("idCotizacion") String idCotizacion, @QueryParam("asesor") String asesor, @QueryParam("idAgencia") String idAgencia) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.iniciarNegociacionFromCot(Long.valueOf( idCotizacion ), asesor, Long.valueOf(idAgencia), autorizacion,nombreAgencia, telefonoAsesor);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerNegociacionExistente")
	@ApiOperation(value = "id", notes = "Metodo Get traerNegociacionExistente Retorna wrapper de entidades encontradas en NegociacionWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<NegociacionWrapper> traerNegociacionExistente(@QueryParam("autorizacion") String autorizacion,@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<NegociacionWrapper> loc = new GenericWrapper<>();
		NegociacionWrapper a = this.qos.traerNegociacionExistente(Long.valueOf( id ), autorizacion);
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/pruebaWSPersona")
	@ApiOperation(value = "cedula", notes = "Metodo Get pruebaWSPersona Retorna wrapper de entidades encontradas en EquifaxVariableWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<EquifaxVariableWrapper> iniciarNegociacion(@QueryParam("cedula") String cedula) throws RelativeException {
		GenericWrapper<EquifaxVariableWrapper> loc = new GenericWrapper<>();
		//List<EquifaxVariableWrapper> a = this.qos.traerVariablesEquifax(cedula);
		//loc.setEntidades(a);
		return loc;
	}
	
	@POST
	@Path("/agregarJoya")
	@ApiOperation(value = "asesor", notes = "Metodo Post agregarJoya Retorna wrapper de entidades encontradas en TbQoTasacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoTasacion> agregarJoya(@QueryParam("nombreAsesor") String nombreAsesor, @QueryParam("correoAsesor") String correoAsesor,TbQoTasacion joya, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<TbQoTasacion> loc = new GenericWrapper<>();
		List<TbQoTasacion> a = this.qos.agregarJoya(joya, asesor,nombreAsesor,correoAsesor);
		loc.setEntidades(a);
		return loc;
	}

	@POST
	@Path("/guardarOpcionCredito")
	@ApiOperation(value = "List<CalculadoraOpcionWrapper> ", notes = "Metodo Post guardarOpcionCredito Retorna wrapper de entidades encontradas en TbQoCreditoNegociacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCreditoNegociacion> guardarOpcionCredito(@QueryParam("autorizacion") String autorizacion,ClienteYReferidoWrapper opcionCredito,
			@QueryParam("asesor") String asesor,@QueryParam("nombreAsesor") String nombreAsesor, @QueryParam("idCredito") String idCredito, @QueryParam("correoAsesor") String correoAsesor) throws RelativeException {
		GenericWrapper<TbQoCreditoNegociacion> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCredito)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL CREDITO");
		}
		TbQoCreditoNegociacion a = this.qos.guardarOpcionCredito(opcionCredito, asesor, Long.valueOf(idCredito) , autorizacion,nombreAsesor, correoAsesor);
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/verPrecio")
	@ApiOperation(value = "cliente, asesor", notes = "Metodo Post verPrecio Retorna wrapper de entidades encontradas en TipoOroWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TipoOroWrapper> verPrecio(ClienteYReferidoWrapper cliente, @QueryParam("asesor") String asesor) throws RelativeException {
		GenericWrapper<TipoOroWrapper> loc = new GenericWrapper<>();
		List<TipoOroWrapper> a = this.qos.verPrecio(cliente);
		loc.setEntidades(a);
		return loc;
	}
	
	
	@POST
	@Path("/solicitarExcepcion")
	@ApiOperation(value = "TbQoExcepcion", notes = "Metodo Post solicitarExcepcion Retorna wrapper de entidades encontradas en TbQoExcepcion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoExcepcion> solicitarExcepcion(@QueryParam("nombreAsesor") String nombreAsesor,@QueryParam("correoAsesor") String correoAsesor,@QueryParam("autorizacion") String autorizacion,TbQoExcepcion excepcion) throws RelativeException {
		GenericWrapper<TbQoExcepcion> loc = new GenericWrapper<>();
		TbQoExcepcion a = this.qos.solicitarExcepcion(excepcion,autorizacion,correoAsesor,nombreAsesor);
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/actualizarVariables")
	@ApiOperation(value = "GenericWrapper<TbQoVariablesCrediticia>", notes = "Metodo Post actualizarVariables Retorna wrapper de entidades encontradas en TbQoVariablesCrediticia", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoVariablesCrediticia> actualizarVariables( GenericWrapper<TbQoVariablesCrediticia> wrapper, @QueryParam("idNego") String idNego) throws RelativeException {
		GenericWrapper<TbQoVariablesCrediticia> loc = new GenericWrapper<>();
		ArrayList<TbQoVariablesCrediticia> l = this.qos.actualizarVariables(wrapper.getEntidades(), Long.valueOf( idNego ));
		loc.setEntidades(l);
		return loc;
	}
	
	
	
	
}
