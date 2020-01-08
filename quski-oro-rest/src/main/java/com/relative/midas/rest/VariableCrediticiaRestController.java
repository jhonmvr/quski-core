package com.relative.midas.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoVariableCrediticia;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public class VariableCrediticiaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoVariableCrediticia, GenericWrapper<TbQoVariableCrediticia>> {
	
	@Inject
	Logger log;
	
	@Inject
	QuskiOroService qos;
	
	public VariableCrediticiaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoVariableCrediticia> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoVariableCrediticia", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbQoVariableCrediticia> getEntity(String id) throws RelativeException {
		GenericWrapper<TbQoVariableCrediticia> loc = new GenericWrapper<>();
		TbQoVariableCrediticia vc =this.qos.findVariableCrediticiaById(Long.valueOf(id));
		loc.setEntidad(vc);
		return loc;
	}
	
	@Override
	public PaginatedListWrapper<TbQoVariableCrediticia> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		 return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	private PaginatedListWrapper<TbQoVariableCrediticia> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoVariableCrediticia> plw = new PaginatedListWrapper<>(pw);
		List<TbQoVariableCrediticia> actions = this.qos.findAllVariablesCrediticias(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.qos.countVariablesCrediticias().intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	
	@POST
	@Path("/persistEntity")
	@Override
	@ApiOperation(value = "GenericWrapper<TbMiVariableCrediticia> ", notes = "Metodo persistEntity Retorna wrapper de entidades encontradas en TbMiVariableCrediticia", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoVariableCrediticia> persistEntity(GenericWrapper<TbQoVariableCrediticia> vc)
			throws RelativeException {
		try {
			GenericWrapper<TbQoVariableCrediticia> gw= new GenericWrapper<>();
			gw.setEntidad(this.qos.manageVariableCrediticia( vc.getEntidad() ));
			return vc;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController persistEntity, " + e.getMessage());
		}
	}

}
