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
import com.relative.quski.model.TbQoHistoricoObservacion;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ReportService;
import com.relative.quski.wrapper.HistoricoObservacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/historicoObservacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "HistoricoObservacionRestController - REST CRUD")
public class HistoricoObservacionRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoHistoricoObservacion, GenericWrapper<TbQoHistoricoObservacion>> {
	
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	ParametroRepository ps;
	
	@Inject 
	ReportService rs;
	public HistoricoObservacionRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoObservacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoHistoricoObservacion", response = GenericWrapper.class)
	public GenericWrapper<TbQoHistoricoObservacion> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoHistoricoObservacion> loc = new GenericWrapper<>();
		TbQoHistoricoObservacion a = this.qos.findHistoricoObservacionById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoHistoricoObservacion>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoHistoricoObservacion", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoHistoricoObservacion> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoHistoricoObservacion> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoHistoricoObservacion> plw = new PaginatedListWrapper<>(pw);
		List<TbQoHistoricoObservacion> actions = this.qos.findAllHistoricoObservacion(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countHistoricoObservacion().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoObservacion>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoHistoricoObservacion", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoHistoricoObservacion> persistEntity(GenericWrapper<TbQoHistoricoObservacion> wp) throws RelativeException {
		GenericWrapper<TbQoHistoricoObservacion> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageHistoricoObservacion(wp.getEntidad()));
		return loc;
	}




	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}	
	
	@GET
	@Path("/byIdCredito")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoObservacion>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoHistoricoObservacion", response = GenericWrapper.class)
	public GenericWrapper<HistoricoObservacionWrapper> byIdCredito(@QueryParam("idCredito") String idCredito) throws RelativeException {
		GenericWrapper<HistoricoObservacionWrapper> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCredito)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL CREDITO - HISTORICO OBSERVACION");
		}
		loc.setEntidades(this.qos.findHistoricoObservacionByIdCredito(Long.valueOf(idCredito)) );
		return loc;
	}
	
}
