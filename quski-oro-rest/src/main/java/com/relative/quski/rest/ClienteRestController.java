package com.relative.quski.rest;

import java.util.List;

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
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.service.CotizacionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AsignacionesWrapper;
import com.relative.quski.wrapper.ClienteWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	CotizacionService cos;

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoCliente", response = GenericWrapper.class)
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
	public GenericWrapper<TbQoCliente> persistEntity(GenericWrapper<TbQoCliente> wp) throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageCliente(wp.getEntidad()));
		return loc;
	}

	/**
	 * METODO QUE BUSCA AL CLIENTE POR IDENTIFICACION
	 * 
	 * @param identificacion
	 * @return GenericWrapper<TbQoCliente>
	 * @throws RelativeException
	 */
	@GET
	@Path("/findclienteByIdentificacion")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiCliente", response = GenericWrapper.class)
	public GenericWrapper<TbQoCliente> findclienteByIdentificacion(@QueryParam("identificacion") String identificacion)
			throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		TbQoCliente a = this.qos.findClienteByIdentificacion(identificacion);
		loc.setEntidad(a);
		return loc;
	}
//	public TbQoCliente findClienteByIdentificacion(String identificacion) throws RelativeException {
//		TbQoCliente tmp = ;
//		if (tmp != null && !tmp.isEmpty()) {
//			return tmp.get(0);
//		}
//		return null;
//	}

	@GET
	@Path("/findByParams")
	@ApiOperation(value = "PaginatedListWrapper<TbMiCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbMiCliente", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbQoCliente> findByParams(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("identificacion") String identificacion, @QueryParam("primerNombre") String primerNombre,
			@QueryParam("apellidoPaterno") String apellidoPaterno, @QueryParam("segundoNombre") String segundoNombre,
			@QueryParam("apellidoMaterno") String apellidoMaterno, @QueryParam("telefono") String telefono,
			@QueryParam("celular") String celular, @QueryParam("correo") String correo,
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
				StringUtils.isNotBlank(estado) ? QuskiOroUtil.getEnumFromString(EstadoEnum.class, estado) : null);

	}

	private PaginatedListWrapper<TbQoCliente> findByParams(PaginatedWrapper pw, String identificacion,
			String primerNombre, String apellidoPaterno, String segundoNombre, String apellidoMaterno, String telefono,
			String celular, String correo, EstadoEnum estado) throws RelativeException {

		PaginatedListWrapper<TbQoCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbQoCliente> actions = this.qos.findClienteByParams(pw, identificacion, primerNombre, apellidoPaterno,
				segundoNombre, apellidoMaterno, telefono, celular, correo, estado);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countClienteByParams(identificacion, primerNombre, apellidoPaterno,
					segundoNombre, apellidoMaterno, telefono, celular, correo, estado).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@GET
	@Path("/obtenerCliente")
	@ApiOperation(value = "GenericWrapper<DetalleCreditoWrapper>", notes = "Metodo DetalleCreditoWrapper Retorna wrapper de entidades encontradas en DetalleCreditoWrapper", response = GenericWrapper.class)

	public GenericWrapper<ClienteWrapper> obtenerCliente() throws RelativeException {
		GenericWrapper<ClienteWrapper> loc = new GenericWrapper<>();
		ClienteWrapper gestion = new ClienteWrapper();
		// gestion.setNombresCompletos("ESTEBAN PAUL JAMI LOPEZ");
		gestion.setCedulaCliente("1708764053");
		gestion.setPrimerNombre("ESTEBAN");
		gestion.setSegundoNombre("PAUL");
		gestion.setApellidoPaterno("JAMI");
		gestion.setApellidoMaterno("LOPEZ");
		gestion.setEdad("32");
		gestion.setNacionalidad("ECUADOR");
		gestion.setTelefonoFijo("3262055");
		gestion.setTelefonoMovil("0998569332");
		gestion.setEmail("XXXXX@XXX.COM");
		gestion.setPublicidad("FACEBOOK");
		gestion.setCampania("INBOUND");
		loc.setEntidad(gestion);
		return loc;
	}

	@GET
	@Path("/getClienteByCodigoOperacion")
	@ApiOperation(value = "GenericWrapper<AsignacionesWrapper>", notes = "Metodo getEntity Retorna wrapper de entidad encontrada en AsignacionesWrapper", response = GenericWrapper.class)
	public GenericWrapper<AsignacionesWrapper> getClienteByCodigoOperacion(
			@QueryParam("codigoOperacion") String codigoOperacion) throws RelativeException {
		GenericWrapper<AsignacionesWrapper> loc = new GenericWrapper<>();
		AsignacionesWrapper a = this.findClienteBycodigoOperacion(codigoOperacion);
		loc.setEntidad(a);
		return loc;
	}

	private AsignacionesWrapper findClienteBycodigoOperacion(String codigoOperacion) throws RelativeException {
		List<AsignacionesWrapper> tmp = this.qos.findClienteBycodigoOperacion(codigoOperacion);
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		}
		return null;
	}

	@POST
	@Path("/crearCliente")
	@ApiOperation(value = "GenericWrapper<TbQoCliente>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoCliente", response = GenericWrapper.class)
	public GenericWrapper<TbQoCliente> crearCliente(GenericWrapper<TbQoCliente> wp) throws RelativeException {
		GenericWrapper<TbQoCliente> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.crearCliente(wp.getEntidad()));
		return loc;
	}

	/*
	 * @GET
	 * 
	 * @Path("/buscarCotizacionesAnterioresByIdCliente")
	 * 
	 * @ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes =
	 * "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoCotizador",
	 * response = GenericWrapper.class) public GenericWrapper<TbQoCotizador>
	 * buscarCotizacionesAnterioresByIdCliente(@QueryParam("id") String id) throws
	 * RelativeException { GenericWrapper<TbQoCotizador> loc = new
	 * GenericWrapper<>(); TbQoCotizador a =
	 * this.qos.findCotizadorById(Long.valueOf(id)); loc.setEntidad(a); return loc;
	 * } private TbQoCotizador findCotizadorById(Long id) throws RelativeException {
	 * List<TbQoCotizador> tmp = this.qos.findCotizadorById(id) if (tmp != null &&
	 * !tmp.isEmpty()) { return tmp.get(0); } return null; }
	 */

}
