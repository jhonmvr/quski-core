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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/riesgoAcumuladoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "RiesgoAcumuladoRestController - REST CRUD")

public class RiesgoAcumuladoRestController extends BaseRestController
implements CrudRestControllerInterface<TbQoRiesgoAcumulado, GenericWrapper<TbQoRiesgoAcumulado>> {
	@Inject
	QuskiOroService qos;
	
	public RiesgoAcumuladoRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
	}

	@Override
	public GenericWrapper<TbQoRiesgoAcumulado> getEntity(String arg0) throws RelativeException {
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	public PaginatedListWrapper<TbQoRiesgoAcumulado> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<TbQoRiesgoAcumulado> findAll(PaginatedWrapper pw) {
			return null;
	}
	/**
	 * 
	 * @note   Servicio para crear varios riesgo acumulado a la vez.
	 * @author Jeroham Cadenas
	 * @param  GenericWrapper<TbQoRiesgoAcumulado> ra
	 * @return GenericWrapper<TbQoRiesgoAcumulado>
	 * @throws RelativeException
	 */
	@POST
	@Path("/persistEntities")
	@ApiOperation(value = "GenericWrapper<TbQoRiesgoAcumulado> ", notes = "Metodo persistEntity Retorna wrapper de entidades encontradas en TbQoRiesgoAcumulado", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRiesgoAcumulado> persistEntities(GenericWrapper<TbQoRiesgoAcumulado> ra) throws RelativeException {
		GenericWrapper<TbQoRiesgoAcumulado> gw= new GenericWrapper<>();
		gw.setEntidades(this.qos.manageListRiesgoAcumulados( ra.getEntidades() ));
		return gw;
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param  idNegociacion
	 * @return List<TbQoVariablesCrediticia>
	 * @throws RelativeException
	 */
	@GET
	@Path("/findRiesgoAcumuladoByIdCliente")
	@ApiOperation(value = "PaginatedListWrapper<TbQoRiesgoAcumulado>", notes = "Metodo Retorna List de entidades encontradas en TbQoRiesgoAcumulado", response = List.class)
	public PaginatedListWrapper<TbQoRiesgoAcumulado> findRiesgoAcumuladoByIdCliente( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCliente") String idCliente ) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return listByIdCliente(	new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated), Long.valueOf( idCliente ));
		
		
	}
	private PaginatedListWrapper<TbQoRiesgoAcumulado> listByIdCliente(PaginatedWrapper pw, Long idCliente) throws RelativeException {
		PaginatedListWrapper<TbQoRiesgoAcumulado> plw = new PaginatedListWrapper<>(pw);
		List<TbQoRiesgoAcumulado> actions = this.qos.findRiesgoAcumuladoByIdCliente(pw, idCliente);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countRiesgoAcumuladoByIdCliente( idCliente ).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	public GenericWrapper<TbQoRiesgoAcumulado> persistEntity(GenericWrapper<TbQoRiesgoAcumulado> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	

}

