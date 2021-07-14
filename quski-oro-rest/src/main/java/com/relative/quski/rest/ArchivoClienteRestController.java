package com.relative.quski.rest;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.relative.quski.model.TbQoArchivoCliente;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/archivoClienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "archivoClienteController - REST CRUD")
public class ArchivoClienteRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoArchivoCliente, GenericWrapper<TbQoArchivoCliente>> {

	public ArchivoClienteRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "id", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoArchivoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoArchivoCliente> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoArchivoCliente> loc = new GenericWrapper<>();
		TbQoArchivoCliente a = this.qos.findArchivoClienteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoArchivoCliente>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoArchivoCliente", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoArchivoCliente> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoArchivoCliente> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoArchivoCliente> plw = new PaginatedListWrapper<>(pw);
		List<TbQoArchivoCliente> actions = this.qos.findAllArchivoCliente(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countArchivoCliente().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoArchivoCliente>", notes = "Metodo Post persistEntity Registra  y retorna la entidad TbQoArchivoCliente", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoArchivoCliente> persistEntity(GenericWrapper<TbQoArchivoCliente> wp)
			throws RelativeException {
		GenericWrapper<TbQoArchivoCliente> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageArchivoCliente(wp.getEntidad()));
		return loc;
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub

	}

	@GET
	@Path("/enviarCorreoPrueba")
	@ApiOperation(value = "@QueryParam(\"para\") String para,\r\n" + "@QueryParam(\"asunto\") String asunto, @QueryParam(\"contenido\")"
			+ " String contenido, Map<String, byte[]> adjunto "
			, notes = "Metodo Get envia un correo y retorna un GenericWrapper booleano", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Boolean> enviarCorreoPrueba(@QueryParam("para") String para,
			@QueryParam("asunto") String asunto, @QueryParam("contenido") String contenido, Map<String, byte[]> adjunto) throws RelativeException {
		GenericWrapper<Boolean> loc = new GenericWrapper<>();
		Boolean a = this.qos.enviarCorreoPruebas(para, asunto, contenido,adjunto);
		loc.setEntidad(a);
		return loc;
	}
}
