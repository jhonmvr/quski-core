package com.relative.midas.rest;

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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoVariableCrediticia;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@Path("/variableCrediticiaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "VariableCrediticiaRestController - REST CRUD")
public class VariableCrediticiaRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoVariableCrediticia, GenericWrapper<TbQoVariableCrediticia>> {
	
	
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
	@ApiOperation(value = "GenericWrapper<TbQoTipoOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoTipoOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoVariableCrediticia> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoVariableCrediticia> loc = new GenericWrapper<>();
		TbQoVariableCrediticia a = this.qos.findVariableCrediticiaById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoVariableCrediticia>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoTipoOro", response = PaginatedListWrapper.class)

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
	
	@GET
	@Path("/variableCrediticiaByIdCotizador")
	@ApiOperation(value = "GenericWrapper<TbQoVariableCrediticia>", notes = "Metodo variableCrediticiaByIdCotizador Retorna lista de entidades encontradas de TbQoVariableCrediticia", response = GenericWrapper.class)
	public GenericWrapper<TbQoVariableCrediticia>  variableCrediticiaByidCotizador(@QueryParam("idCotizador") Long idCotizador)
			throws RelativeException {
		GenericWrapper<TbQoVariableCrediticia> loc = new GenericWrapper<>();
		TbQoVariableCrediticia  a = this.findidCotizador(idCotizador);
		loc.setEntidad(a);
		return loc;
	}
	public TbQoVariableCrediticia findidCotizador(Long idCotizador) throws RelativeException {
	 List<TbQoVariableCrediticia> tmp = this.qos.findVariableCrediticiaByidCotizador(idCotizador);
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		}
		return null;
	}
	
	
	
	@GET
	@Path("/variableCrediticiaByIdCotizacion")
	@ApiOperation(value = "GenericWrapper<TbQoVariableCrediticia>", notes = "Metodo variableCrediticiaByIdCotizador Retorna wrapper de entidades encontradas en TbQoVariableCrediticia", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoVariableCrediticia> variableCrediticiaByIdCotizador(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCotizador") String idCotizador
			) throws RelativeException {
		return variableCrediticiaByIdCotizador(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf(idCotizador));
	}
	
	private PaginatedListWrapper<TbQoVariableCrediticia> variableCrediticiaByIdCotizador(PaginatedWrapper pw, Long idCotizador) throws RelativeException {
		PaginatedListWrapper<TbQoVariableCrediticia> plw = new PaginatedListWrapper<>(pw);
		List<TbQoVariableCrediticia> actions = this.qos.findVariableCrediticiaByIdCotizacion(pw, idCotizador);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countVariblesCrediticiaByIdCotizacion(idCotizador).intValue());
			plw.setList(actions);
		}
		return plw;
	}	
	
	

}
