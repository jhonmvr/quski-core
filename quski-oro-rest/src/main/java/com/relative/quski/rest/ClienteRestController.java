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
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.ClienteCompletoWrapper;
import com.relative.quski.wrapper.CreacionClienteRespuestaCoreWp;
import com.relative.quski.wrapper.CrmProspectoCortoWrapper;
import com.relative.quski.wrapper.CrmProspectoWrapper;
import com.relative.quski.wrapper.CuentaWrapper;
import com.relative.quski.wrapper.ResponseWebMupi;
import com.relative.quski.wrapper.RespuestaCrearClienteWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/clienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clienteRestController - REST CRUD")
public class ClienteRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoCliente, GenericWrapper<TbQoCliente>> {

	public ClienteRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;
	@Inject
	Logger log;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCliente> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		TbQoCliente a = this.qos.findClienteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoCliente", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoCliente> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoCliente> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCliente> actions = this.qos.findAllCliente(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countCliente().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCliente> persistEntity(GenericWrapper<TbQoCliente> wp) throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		log.info("INGRESA AL REST DE persistEntity cliente ");		
		loc.setEntidad(this.qos.manageCliente(wp.getEntidad()));
		return loc;
	}
	@GET
	@Path("/findClienteByIdentificacion")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo findByIdentificacion Retorna wrapper de entidad encontrada en TbQoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCliente> findClienteByIdentificacion(@QueryParam("identificacion") String identificacion)
			throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		TbQoCliente a = this.qos.findClienteByIdentifiacion(identificacion);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCliente", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoCliente> findByParams(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion, @QueryParam("primerNombre") String primerNombre,
			@QueryParam("apellidoPaterno") String apellidoPaterno, @QueryParam("segundoNombre") String segundoNombre,
			@QueryParam("apellidoMaterno") String apellidoMaterno, @QueryParam("telefono") String telefono,
			@QueryParam("celular") String celular, @QueryParam("correo") String correo,
			@QueryParam("nombreCompleto") String nombreCompleto, 
			@QueryParam("estado") String estado) throws RelativeException {
		return findByParams(
				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
						isPaginated),
				StringUtils.isNotBlank(identificacion) ? identificacion : null,
				StringUtils.isNotBlank(primerNombre) ? primerNombre : null,
				StringUtils.isNotBlank(segundoNombre) ? segundoNombre : null,
				StringUtils.isNotBlank(apellidoPaterno) ? apellidoPaterno : null,
				StringUtils.isNotBlank(apellidoMaterno) ? apellidoMaterno : null,
				StringUtils.isNotBlank(telefono) ? telefono : null, StringUtils.isNotBlank(celular) ? celular : null,
				StringUtils.isNotBlank(correo) ? correo : null,
				StringUtils.isNotBlank(nombreCompleto) ? nombreCompleto : null,
				StringUtils.isNotBlank(estado) ? QuskiOroUtil.getEnumFromString(EstadoEnum.class, estado) : null);
	}
	private PaginatedListWrapper<TbQoCliente> findByParams(PaginatedWrapper pw, String identificacion,
			String primerNombre, String apellidoPaterno, String segundoNombre, String apellidoMaterno, String telefono,
			String celular, String correo, String nombreCompleto, EstadoEnum estado) throws RelativeException {

		PaginatedListWrapper<TbQoCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCliente> actions = this.qos.findClienteByParams(pw, identificacion, primerNombre, apellidoPaterno,
				segundoNombre, apellidoMaterno, telefono, celular, correo, nombreCompleto, estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countClienteByParams(identificacion, primerNombre, apellidoPaterno,
					segundoNombre, apellidoMaterno, telefono, celular, correo, nombreCompleto, estado).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@POST
	@Path("/crearCliente")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de  de entidad encontrada RespuestaCrearClienteWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<RespuestaCrearClienteWrapper> crearCliente(GenericWrapper<TbQoCliente> wp,@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		GenericWrapper<RespuestaCrearClienteWrapper> loc = new GenericWrapper<>();
		
		loc.setEntidad( this.qos.crearCliente(wp.getEntidad(), StringUtils.isNotBlank(idNegociacion)? Long.valueOf(idNegociacion):null) );
		return loc;
	}
	@GET
	@Path("/traerClienteByIdNegociacion")
	@ApiOperation(value = "id", notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada ClienteCompletoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ClienteCompletoWrapper> traerClienteByIdNegociacion(@QueryParam("autorizacion") String autorizacion,@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<ClienteCompletoWrapper> loc = new GenericWrapper<>();
		ClienteCompletoWrapper a = this.qos.traerClienteByIdNegociacion(Long.valueOf( id ), autorizacion);
		loc.setEntidad(a);
		return loc;
	}	
	@GET
	@Path("/traerClienteByNumeroOperacion")
	@ApiOperation(value = "numeroOperacionMadre", notes = "Metodo Get  retorna GenericWrapper de la entidad encontrada ClienteCompletoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ClienteCompletoWrapper> traerClienteByNumeroOperacion(@QueryParam("autorizacion") String autorizacion,@QueryParam("numeroOperacionMadre") String numeroOperacionMadre) throws RelativeException {
		GenericWrapper<ClienteCompletoWrapper> loc = new GenericWrapper<>();
		ClienteCompletoWrapper a = this.qos.traerClienteByNumeroOperacion(numeroOperacionMadre, autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/traerClienteByCedula")
	@ApiOperation(value = "cedula", notes = "Metodo Get  retorna GenericWrapper de la entidad encontrada ClienteCompletoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ClienteCompletoWrapper> traerClienteByCedula(@QueryParam("autorizacion") String autorizacion, @QueryParam("cedula") String cedula) throws RelativeException {
		GenericWrapper<ClienteCompletoWrapper> loc = new GenericWrapper<>();
		ClienteCompletoWrapper a = this.qos.traerCliente( cedula, null , autorizacion);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/buscarClienteCrm")
	@ApiOperation(value = "cedula", notes = "Metodo Get retorna GenericWrapper de la entidad encontrada CrmProspectoCortoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CrmProspectoCortoWrapper> buscarClienteCrm(@QueryParam("autorizacion") String autorizacion,@QueryParam("cedula") String cedula) throws RelativeException {
		GenericWrapper<CrmProspectoCortoWrapper> loc = new GenericWrapper<>();
		CrmProspectoCortoWrapper a = this.qos.findProspectoCrm( cedula, autorizacion );
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/guardarEnCrm")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo POST guardarEnCrm Retorna GenericWrapper de la entidad encontrada CrmProspectoCortoWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CrmProspectoWrapper> guardarEnCrm(@QueryParam("autorizacion") String autorizacion, TbQoCliente cliente) throws RelativeException {
		GenericWrapper<CrmProspectoWrapper> loc = new GenericWrapper<>();
		CrmProspectoWrapper a = this.qos.guardarProspectoCrm( cliente, autorizacion );
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/consultarCuentaMupi")
	@ApiOperation(value = "cedula", notes = "Metodo GET consultarCuentaMupi  Retorna GenericWrapper de la entidad encontrada CuentaWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CuentaWrapper> consultarCuentaMupi(@QueryParam("cedula") String cedula) throws RelativeException {
		GenericWrapper<CuentaWrapper> loc = new GenericWrapper<>();
		CuentaWrapper a = this.qos.consultaCuentaApiGateWay( cedula );
		loc.setEntidad(a);
		return loc;
	}
	@POST
	@Path("/registrarCliente")
	@ApiOperation(value = "GenericWrapper<ClienteCompletoWrapper>", notes = "Metodo POST guardarEnCrm registra y retorna GenericWrapper de la entidad encontrada CreacionClienteRespuestaCoreWp", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CreacionClienteRespuestaCoreWp> registrarCliente(@QueryParam("autorizacion") String autorizacion,ClienteCompletoWrapper wp ) throws RelativeException {
		GenericWrapper<CreacionClienteRespuestaCoreWp> loc = new GenericWrapper<>();
		loc.setEntidad( this.qos.registrarCliente(wp, autorizacion) );
		return loc;
	}
	
	@POST
	@Path("/updateCliente")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoCliente> updateCliente(TbQoCliente cliente) throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		
		loc.setEntidad( this.qos.updateCliente(cliente) );
		return loc;
	}
	
	@GET
	@Path("/consultaWebMupi")
	@ApiOperation(value = "cedula", notes = "Metodo GET consultarCuentaMupi  Retorna GenericWrapper de la entidad encontrada CuentaWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public ResponseWebMupi consultaWebMupi(@QueryParam("autorizacion") String autorizacion,@QueryParam("idCliente") String idCliente) throws RelativeException {
		if(StringUtils.isBlank(idCliente)) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,"No se puede leer id_cliente");
		}
		return this.qos.consultaWebMupi( Long.valueOf(idCliente), autorizacion );
	}
	
}
