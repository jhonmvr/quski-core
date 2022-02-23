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
import com.relative.quski.model.TbQoHistoricoObservacionEntrega;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/historicoObservacionEntregaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "historicoObservacionEntregaRestController - REST CRUD")
public class HistoricoObservacionEntregaRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoHistoricoObservacionEntrega, GenericWrapper<TbQoHistoricoObservacionEntrega>> {
	
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	ParametroRepository ps;
	
	@Inject 
	ReportService rs;
	public HistoricoObservacionEntregaRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoObservacionEntrega>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoHistoricoObservacionEntrega", response = GenericWrapper.class)
	public GenericWrapper<TbQoHistoricoObservacionEntrega> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoHistoricoObservacionEntrega> loc = new GenericWrapper<>();
		TbQoHistoricoObservacionEntrega a = this.qos.findHistoricoObservacionEntregaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoHistoricoObservacionEntrega>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoHistoricoObservacionEntrega", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoHistoricoObservacionEntrega> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoHistoricoObservacionEntrega> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoHistoricoObservacionEntrega> plw = new PaginatedListWrapper<>(pw);
		List<TbQoHistoricoObservacionEntrega> actions = this.qos.findAllHistoricoObservacionEntrega(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countHistoricoObservacionEntrega().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoObservacionEntrega>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoHistoricoObservacionEntrega", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoHistoricoObservacionEntrega> persistEntity(GenericWrapper<TbQoHistoricoObservacionEntrega> wp) throws RelativeException {
		GenericWrapper<TbQoHistoricoObservacionEntrega> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageHistoricoObservacionEntrega(wp.getEntidad()));
		return loc;
	}




	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}	
	
	@GET
	@Path("/byIdEntrega")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoObservacionEntrega>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoHistoricoObservacionEntrega", response = GenericWrapper.class)
	public GenericWrapper<TbQoHistoricoObservacionEntrega> byIdCredito(@QueryParam("idEntrega") String idEntrega) throws RelativeException {
		GenericWrapper<TbQoHistoricoObservacionEntrega> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idEntrega)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL CREDITO - HISTORICO OBSERVACION");
		}
		loc.setEntidades(this.qos.findHistoricoObservacionEntregaByIdCredito(Long.valueOf(idEntrega)) );
		return loc;
	}
	
}
