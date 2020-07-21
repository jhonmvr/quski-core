package com.relative.quski.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import com.relative.quski.model.TbQoExcepcione;
import com.relative.quski.service.QuskiOroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/excepcionesRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "excepcionesRestController - REST CRUD")
public class ExcepcionesRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoExcepcione, GenericWrapper<TbQoExcepcione>> {
	@Inject
	QuskiOroService qos;
	
	public ExcepcionesRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@GET
	@Path("/findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion")
	@ApiOperation(value = "GenericWrapper<TbQoExcepcione>", notes = "Metodo Especifico Retorna entidad encontradas en TbQoExcepcione por id de negociacion, tipo de excepcion y estado de la excepcion", response = GenericWrapper.class)
	public GenericWrapper<TbQoExcepcione> findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion(
			@QueryParam("idNegociacion") String idNegociacion,
			@QueryParam("tipoExcepcion") String tipoExcepcion,
			@QueryParam("estadoExcepcion") String estadoExcepcion
			) throws RelativeException {
		GenericWrapper<TbQoExcepcione> loc = new GenericWrapper<>();
		TbQoExcepcione a = this.qos.findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion( Long.valueOf( idNegociacion ), tipoExcepcion, estadoExcepcion );
		loc.setEntidad(a);
		return loc;
	}
	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoExcepcione>", notes = "Metodo getEntity Retorna entidad encontradas en TbQoExcepcione", response = GenericWrapper.class)
	public GenericWrapper<TbQoExcepcione> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoExcepcione> loc = new GenericWrapper<>();
		TbQoExcepcione a = this.qos.finExcepcionById(Long.valueOf( id ));
		loc.setEntidad(a);
		return loc;
	}



	@Override
	public PaginatedListWrapper<TbQoExcepcione> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws RelativeException {
		return null;
	}

	@Override
	public GenericWrapper<TbQoExcepcione> persistEntity(GenericWrapper<TbQoExcepcione> arg0) throws RelativeException {
		return null;
	}
	
	@GET
	@Path("/findByIdNegociacion")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcione>", notes = "Metodo PaginatedListWrapper Retorna entidades encontradas en TbQoExcepcione por id de Negociacion", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoExcepcione> findByIdNegociacion( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idNegociacion")  String idNegociacion 
			) throws RelativeException {
		return findByIdNegociacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf( idNegociacion ));
	}
	private PaginatedListWrapper<TbQoExcepcione> findByIdNegociacion(PaginatedWrapper pw, Long idNegociacion) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcione> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcione> actions = this.qos.findByIdNegociacion(pw, idNegociacion);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByIdNegociacion( idNegociacion ).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	@GET
	@Path("/findByIdCliente")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcione>", notes = "Metodo PaginatedListWrapper Retorna entidades encontradas en TbQoExcepcione por id de Cliente", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoExcepcione> findByIdCliente( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCliente")  String idCliente 
			) throws RelativeException {
		return findByIdCliente(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf( idCliente ));
	}
	private PaginatedListWrapper<TbQoExcepcione> findByIdCliente(PaginatedWrapper pw, Long idCliente) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcione> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcione> actions = this.qos.findByIdCliente(pw, idCliente);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByIdCliente( idCliente ).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/findByTipoExcepcionAndIdNegociacion")
	@ApiOperation(value = "PaginatedListWrapper<TbQoExcepcione>", notes = "Metodo PaginatedListWrapper Retorna entidades encontradas en TbQoExcepcione por id de Cliente", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoExcepcione> findByIdClfindByTipoExcepcionAndIdNegociacioniente( 
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("tipoExcepcion")  String tipoExcepcion,
			@QueryParam("idNegociacion")  String idNegociacion 
			) throws RelativeException {
		return findByTipoExcepcionAndIdNegociacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated), tipoExcepcion, Long.valueOf( idNegociacion ));
	}
	private PaginatedListWrapper<TbQoExcepcione> findByTipoExcepcionAndIdNegociacion(PaginatedWrapper pw, String tipoExcepcion,Long idNegociacion) throws RelativeException {
		PaginatedListWrapper<TbQoExcepcione> plw = new PaginatedListWrapper<>(pw);
		List<TbQoExcepcione> actions = this.qos.findByTipoExcepcionAndIdNegociacion(pw, tipoExcepcion, idNegociacion );
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countExcepcionesByTipoExcepcionAndIdNegociacion( tipoExcepcion, idNegociacion ).intValue());
			plw.setList(actions);
		}
		return plw;
	}

	
	
}
