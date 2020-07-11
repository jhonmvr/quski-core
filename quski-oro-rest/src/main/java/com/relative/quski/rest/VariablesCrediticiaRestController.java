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
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Path("/variablesCrediticiaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "VariablesCrediticiaRestController - REST CRUD")
public class VariablesCrediticiaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoVariablesCrediticia, GenericWrapper<TbQoVariablesCrediticia>> {
	
	
	@Inject
	QuskiOroService qos;
	
	public VariablesCrediticiaRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoTipoOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoVariablesCrediticia> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoVariablesCrediticia> loc = new GenericWrapper<>();
		TbQoVariablesCrediticia a = this.qos.findVariablesCrediticiaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoVariableCrediticia>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoTipoOro", response = PaginatedListWrapper.class)

	public PaginatedListWrapper<TbQoVariablesCrediticia> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		 return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}
	private PaginatedListWrapper<TbQoVariablesCrediticia> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoVariablesCrediticia> plw = new PaginatedListWrapper<>(pw);
		List<TbQoVariablesCrediticia> actions = this.qos.findAllVariablesCrediticias(pw);
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
	public GenericWrapper<TbQoVariablesCrediticia> persistEntity(GenericWrapper<TbQoVariablesCrediticia> vc)
			throws RelativeException {
		try {
			GenericWrapper<TbQoVariablesCrediticia> gw= new GenericWrapper<>();
			gw.setEntidad(this.qos.manageVariablesCrediticia( vc.getEntidad() ));
			return vc;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController persistEntity, " + e.getMessage());
		}
	}
	@GET
	@Path("/variablesCrediticiaByIdCotizacion")
	@ApiOperation(value = "GenericWrapper<TbQoVariableCrediticia>", notes = "Metodo variableCrediticiaByIdCotizador Retorna wrapper de entidades encontradas en TbQoVariableCrediticia", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoVariablesCrediticia> variableCrediticiaByIdCotizador(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCotizador") String idCotizador
			) throws RelativeException {
		return variablesCrediticiaByIdCotizador(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf(idCotizador));
	}
	
	private PaginatedListWrapper<TbQoVariablesCrediticia> variablesCrediticiaByIdCotizador(PaginatedWrapper pw, Long idCotizador) throws RelativeException {
		PaginatedListWrapper<TbQoVariablesCrediticia> plw = new PaginatedListWrapper<>(pw);
		List<TbQoVariablesCrediticia> actions = this.qos.findVariablesCrediticiaByIdCotizacion(pw, idCotizador);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countVariblesCrediticiaByIdCotizacion(idCotizador).intValue());
			plw.setList(actions);
		}
		return plw;
	}
}	
	

