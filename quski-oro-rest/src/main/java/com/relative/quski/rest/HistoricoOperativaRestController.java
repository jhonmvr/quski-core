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
import com.relative.quski.model.TbQoHistoricoOperativa;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.ReportService;
import com.relative.quski.wrapper.HistoricoOperativaWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/historicoOperativaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "HistoricoOperativaRestController - REST CRUD")
public class HistoricoOperativaRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoHistoricoOperativa, GenericWrapper<TbQoHistoricoOperativa>> {
	
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	@Inject
	ParametroRepository ps;
	
	@Inject 
	ReportService rs;
	public HistoricoOperativaRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoOperativa>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoHistoricoOperativa", response = GenericWrapper.class)
	public GenericWrapper<TbQoHistoricoOperativa> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoHistoricoOperativa> loc = new GenericWrapper<>();
		TbQoHistoricoOperativa a = this.qos.findHistoricoOperativaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoHistoricoOperativa>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoHistoricoOperativa", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbQoHistoricoOperativa> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoHistoricoOperativa> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoHistoricoOperativa> plw = new PaginatedListWrapper<>(pw);
		List<TbQoHistoricoOperativa> actions = this.qos.findAllHistoricoOperativa(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countHistoricoOperativa().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoOperativa>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoHistoricoOperativa", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoHistoricoOperativa> persistEntity(GenericWrapper<TbQoHistoricoOperativa> wp) throws RelativeException {
		GenericWrapper<TbQoHistoricoOperativa> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageHistoricoOperativa(wp.getEntidad()));
		return loc;
	}




	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}	
	
	@GET
	@Path("/byIdCredito")
	@ApiOperation(value = "GenericWrapper<TbQoHistoricoOperativa>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoHistoricoOperativa", response = GenericWrapper.class)
	public GenericWrapper<HistoricoOperativaWrapper> byIdCredito(@QueryParam("idCredito") String idCredito) throws RelativeException {
		GenericWrapper<HistoricoOperativaWrapper> loc = new GenericWrapper<>();
		if(StringUtils.isBlank(idCredito)) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DEL CREDITO - HISTORICO OBSERVACION");
		}
		loc.setEntidades(this.qos.findHistoricoOperativaByIdCredito(Long.valueOf(idCredito)) );
		return loc;
	}
	
}
