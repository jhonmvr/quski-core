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

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.service.QuskiOroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/precioOroRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "PrecioOroRestController - REST CRUD")
public class PrecioOroRestController extends BaseRestController
implements CrudRestControllerInterface<TbQoPrecioOro, GenericWrapper<TbQoPrecioOro>>  {
	
	@Inject 
	Logger log;

	public PrecioOroRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Inject
	QuskiOroService qos;
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoPrecioOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoPrecioOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoPrecioOro> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoPrecioOro> loc = new GenericWrapper<>();
		TbQoPrecioOro a = this.qos.findPrecioOroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoPrecioOro>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoPrecioOro", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoPrecioOro> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoPrecioOro> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoPrecioOro> plw = new PaginatedListWrapper<>(pw);
		List<TbQoPrecioOro> actions = this.qos.findAllPrecioOro(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countPrecioOro().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoPrecioOro>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoPrecioOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoPrecioOro> persistEntity(GenericWrapper<TbQoPrecioOro> wp) throws RelativeException {
		GenericWrapper<TbQoPrecioOro> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.managePrecioOro(wp.getEntidad()));
		return loc;
	}	
	
	/**
	 * @author Jeroham Cadenas - developer Twelve
	 * @param  String page
	 * @param  String pageSize
	 * @param  String sortFields
	 * @param  String sortDirections
	 * @param  String isPaginated
	 * @param  Stringcedula
	 * @return PaginatedListWrapper<TbQoPrecioOro>
	 * @throws RelativeException
	 */
	@GET
	@Path("/findByCedula")
	@ApiOperation(value = "GenericWrapper<TbQoPrecioOro>", notes = "Retorna PaginatedListWrapper de entidades encontradas en TbQoPrecioOro", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoPrecioOro> findByCedula(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("cedula") String cedula
			) throws RelativeException {
		return findPendienteByIdentificacion(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),cedula);
	}
	
	private PaginatedListWrapper<TbQoPrecioOro> findPendienteByIdentificacion(PaginatedWrapper pw, String cedula) throws RelativeException {
		PaginatedListWrapper<TbQoPrecioOro> plw = new PaginatedListWrapper<>(pw);
		List<TbQoPrecioOro> actions = this.qos.listByCedula(pw, cedula);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countByCedula(cedula).intValue());
			plw.setList(actions);
		}
		return plw;
	}	
	
	
	@GET
	@Path("/detalleCotizacionById")
	@ApiOperation(value = "GenericWrapper<TbQoPrecioOro>", notes = "Metodo CotizadorByIdCotizador Retorna wrapper de entidades encontradas en TbQoCotizador", response = GenericWrapper.class)
	public PaginatedListWrapper<TbQoPrecioOro> detalleCotizacionById(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("id") String id
			) throws RelativeException {
		return detalleCotizacionById(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf(id));
	}
	
	private PaginatedListWrapper<TbQoPrecioOro> detalleCotizacionById(PaginatedWrapper pw, Long id) throws RelativeException {
		PaginatedListWrapper<TbQoPrecioOro> plw = new PaginatedListWrapper<>(pw);
		List<TbQoPrecioOro> actions = this.qos.findByIdCotizacion(pw, id);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countByIdCotizacion(id).intValue());
			plw.setList(actions);
		}
		return plw;
	}	
	
	@POST
	@Path("/crearPrecioOro")
	@ApiOperation(value = "GenericWrapper<TbQoPrecioOro>", notes = "Metodo CotizadorByIdCotizador Retorna wrapper de entidades encontradas en TbQoCotizador", response = GenericWrapper.class)
	public GenericWrapper<TbQoPrecioOro> crearPrecioOro( GenericWrapper<TbQoPrecioOro> pow )throws RelativeException{
		log.info("=================> ingresa a crearPrecioOro");
		pow.setEntidad( this.qos.registrarPrecioOroByCotizacion( pow.getEntidad() ) );
		return pow;
	}
	
	 
	@GET
	@Path("/eliminarPrecioOro")
	@ApiOperation(value = "GenericWrapper<TbQoPrecioOro>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoPrecioOro", response = GenericWrapper.class)
	public GenericWrapper<TbQoPrecioOro> eliminarPrecioOro(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoPrecioOro> loc = new GenericWrapper<>();
		TbQoPrecioOro a = this.qos.eliminarPrecioOro(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	
	
}
