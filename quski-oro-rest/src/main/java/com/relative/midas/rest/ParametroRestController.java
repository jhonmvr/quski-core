package com.relative.midas.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import com.relative.core.util.main.FechaUtil;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.PaisesEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.service.MidasOroService;
import com.relative.quski.service.ParametrosSingleton;
import com.relative.quski.util.MidasOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@Path("/parametroRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " ParametroRestController - REST CRUD")
public class ParametroRestController extends BaseRestController implements CrudRestControllerInterface<TbMiParametro, GenericWrapper<TbMiParametro>> {

	@Inject
	Logger log;
	
	@Inject
	MidasOroService sas;
	
	@Inject
	ParametrosSingleton ps;
	
	public ParametroRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbMiParametro> ", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbMiParametro", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbMiParametro> getEntity(
			@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbMiParametro> loc = new GenericWrapper<>();
		TbMiParametro a =this.sas.findParametroById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiParametro> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbMiParametro", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbMiParametro> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAll(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiParametro> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiParametro> plw = new PaginatedListWrapper<>(pw);
		List<TbMiParametro> actions = this.sas.findAllParametro(pw);
		if (actions != null && !actions.isEmpty()) {
			
			plw.setTotalResults(this.sas.countParametros().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	
	@POST
	@Path("/persistEntity")
	@Override
	@ApiOperation(value = "GenericWrapper<TbMiParametro> ", notes = "Metodo persistEntity Retorna wrapper de entidades encontradas en TbMiParametro", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbMiParametro> persistEntity(GenericWrapper<TbMiParametro> ucw)
			throws RelativeException {
		try {
			GenericWrapper<TbMiParametro> gw= new GenericWrapper<>();
			gw.setEntidad(this.sas.manageParametro( ucw.getEntidad() ));
			return gw;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController persistEntity, " + e.getMessage());
		}
	}
	
	@GET
	@Path("/getEntityByNombre")
	@ApiOperation(value = "GenericWrapper<TbMiParametro> ", notes = "Metodo getEntityByNombre Retorna wrapper de entidades encontradas en TbMiParametro", 
	response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<TbMiParametro> getEntityByNombre(
			@QueryParam("nombre") String nombre) throws RelativeException {
		try {
			GenericWrapper<TbMiParametro> loc = new GenericWrapper<>();
			TbMiParametro a =this.sas.findByNombre(nombre);
			loc.setEntidad(a);
			return loc;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController getEntityByNombre, " + e.getMessage());
		}
		
	}
	
	@GET
	@Path("/getParametrosSingleton")
	@ApiOperation(value = "Map<String,TbMiParametro> ", notes = "Metodo getParametrosSingleton", 
	response = Map.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = Map.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public Map<String,TbMiParametro> getParametrosSingleton() throws RelativeException {
		try {
			return ps.getParametros();
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController getEntityByNombre, " + e.getMessage());
		}
		
	}
	
	@GET
	@Path("/getSystemDate")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = Map.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Date> getSystemDate() throws RelativeException {
		try {
			GenericWrapper<Date> sd= new GenericWrapper<Date>();
			sd.setEntidad( new Date( System.currentTimeMillis() ));
			return sd;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController getEntityByNombre, " + e.getMessage());
		}
		
	}
	
	@GET
	@Path("/addDaysToDate")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = Map.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<String> addDaysToDate( @QueryParam("fecha") String fecha, @QueryParam("dias") String dias,@QueryParam("format") String format  ) throws RelativeException {
		try {
			log.info("====>ingresa a addDaysToDate");
			log.info("====>ingresa a addDaysToDate fecha " + fecha);
			log.info("====>ingresa a addDaysToDate dias " + dias );
			log.info("====>ingresa a addDaysToDate dias " + format );
			GenericWrapper<String> sd= new GenericWrapper<String>();
			Date local= new Date();
			if( fecha != null && !fecha.isEmpty() ) {
				local =MidasOroUtil.formatSringToDate(fecha, format);
				log.info("====>ingresa a addDaysToDate fecha convertida " + local );
				local=FechaUtil.sumarRestarDiasFecha( local , Integer.valueOf(dias));
				log.info("====>ingresa a addDaysToDate fecha calculada " + local );
				sd.setEntidad( MidasOroUtil.dateToString( local, format ) );
				log.info("====>ingresa a addDaysToDate fecha retrono " + sd.getEntidad( ) );
			} else {
				local = new Date( System.currentTimeMillis() );
				log.info("====>ingresa a addDaysToDate sysdate fecha convertida " + local );
				local=FechaUtil.sumarRestarDiasFecha( local , Integer.valueOf(dias));
				log.info("====>ingresa a addDaysToDate sysdate fecha calculada " + local );
				sd.setEntidad( MidasOroUtil.dateToString( local, format ) );
				log.info("====>ingresa a addDaysToDate sysdate fecha retrono " + sd.getEntidad( ) );
			}
			return sd;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController getEntityByNombre, " + e.getMessage());
		}
		
	}
	
	@GET
	@Path("/countDaysBetweenDate")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = Map.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<Long> countDaysBetweenDate( @QueryParam("fechaIni") String fechaIni, 
			@QueryParam("fechaFin") String fechaFin,@QueryParam("format") String format ) throws RelativeException {
		try {
			log.info("====>ingresa a countDaysBetweenDate");
			log.info("====>ingresa a countDaysBetweenDate fechaIni " + fechaIni);
			log.info("====>ingresa a countDaysBetweenDate fechaFin " + fechaFin );
			log.info("====>ingresa a countDaysBetweenDate format " + format );
			GenericWrapper<Long> sd= new GenericWrapper<>();
			Date localIni=null;
			Date localFin=null;
			if( fechaIni != null && !fechaIni.isEmpty() &&  fechaFin != null && !fechaFin.isEmpty() ) {
				localIni =MidasOroUtil.formatSringToDate(fechaIni, format);
				log.info("====>ingresa a countDaysBetweenDate fecha ini " + localIni );
				localFin =MidasOroUtil.formatSringToDate(fechaFin, format);
				log.info("====>ingresa a countDaysBetweenDate fecha fin " + localFin );
				sd.setEntidad( MidasOroUtil.diasFecha(localIni, localFin) );
				log.info("====>ingresa a countDaysBetweenDate dias retrono " + sd.getEntidad( ) );
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,"DEBE INGRESAR LA FECHA DE INICIO Y FINALIZACION PARA CALCULAR LOS DIAS ");
			}
			return sd;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,"ERROR CONTROLADOR usuarioCanalRestController getEntityByNombre, " + e.getMessage());
		}
		
	}
	@GET
	@Path("/getPaises")
	@ApiOperation(
			value = "GenericWrapper<String>", 
			notes = "Retorna una lista de paises String.class",
			response = GenericWrapper.class) 
	public  GenericWrapper<String> getPaises()throws RelativeException{
		GenericWrapper<String> w = new GenericWrapper<>();
		List<PaisesEnum> enumPaises = Arrays.asList(PaisesEnum.values());
		List<String> stringsPaises = new ArrayList<String>();
		enumPaises.forEach(f -> stringsPaises.add(f.toString().replace('_', ' ')));
		w.setEntidades(stringsPaises);
		return w;
	}
	
	@GET
	@Path("/findByNombreTipoOrdered")
	public  GenericWrapper<TbMiParametro> findByNombreTipoOrdered(
			@QueryParam("nombre") String nombre, @QueryParam("tipo")  String tipo, @QueryParam("ordered")  String ordered)
	throws RelativeException{
		log.info("====>ingresa a findByNombreTipoOrdered");
		log.info("====>ingresa a findByNombreTipoOrdered nombre " + nombre);
		log.info("====>ingresa a findByNombreTipoOrdered tipo " + tipo);
		log.info("====>ingresa a findByNombreTipoOrdered ordered " + ordered);
		GenericWrapper<TbMiParametro> w= new GenericWrapper<>();
		w.setEntidades( this.sas.findByNombreTipoOrdered( nombre, tipo, 
			ordered != null && ordered.equalsIgnoreCase( "Y" )?Boolean.TRUE:Boolean.FALSE	) );
		return w;
	}
	
	@GET
	@Path("/listByParamEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbMiParametro> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbMiParametro", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<TbMiParametro> listByParamEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("nombre") String nombre,
			@QueryParam("tipo") String tipo,
			@QueryParam("estado") String estado,
			@QueryParam("caracteristicaUno") String caracteristicaUno,
			@QueryParam("caracteristicaDos") String caracteristicaDos
			) throws RelativeException {
		return findByParams(nombre, tipo, estado, caracteristicaUno,
				 caracteristicaDos,new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbMiParametro> findByParams(String nombre, String tipo, String estado, String caracteristicaUno,
			String caracteristicaDos,PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbMiParametro> plw = new PaginatedListWrapper<>(pw);
		List<TbMiParametro> actions = this.sas.findParametroByParam(nombre, tipo, 
				estado != null && !estado.isEmpty()?MidasOroUtil.getEnumFromString(EstadoEnum.class, estado):null
				, caracteristicaUno, caracteristicaDos, pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.sas.countParametros( nombre, tipo, 
					estado != null && !estado.isEmpty()?MidasOroUtil.getEnumFromString(EstadoEnum.class, estado):null
							, caracteristicaUno, caracteristicaDos ).intValue());
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/listAllTipos")
	@ApiOperation(value = "PaginatedListWrapper<TbMiParametro> ", notes = "Metodo listAllEntities Retorna wrapper de entidades encontradas en TbMiParametro", 
	response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = PaginatedListWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<String> listAllTipos(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			) throws RelativeException {
		return findAllTipos(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated));
	}

	private PaginatedListWrapper<String> findAllTipos(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<String> plw = new PaginatedListWrapper<>(pw);
		List<String> tipos=new ArrayList<>();
		List<TbMiParametro> actions = this.sas.findAllParametro(pw);
		if (actions != null && !actions.isEmpty()) {
			actions.forEach(p->{
				tipos.add(p.getTipo()==null?"":p.getTipo());
			});
		}
		plw.setList( tipos.stream().distinct().collect(Collectors.toList()) );
		return plw;
	}
	
	
	
	/*
	 * @GET
	 * 
	 * @Path("/getDiffBetweenDateInicioActual")
	 * 
	 * @ApiOperation( value = "GenericWrapper<YearMonthDay>", notes =
	 * "Retorna la diferencia entre fecha inicio y fecha actual", response =
	 * GenericWrapper.class) public GenericWrapper<YearMonthDay>
	 * getDiffBetweenDateInicioActual(
	 * 
	 * @QueryParam("fechaInicio") String fechaInicio,
	 * 
	 * @QueryParam("formato") String formato) throws RelativeException {
	 * if(StringUtils.isBlank(fechaInicio) || StringUtils.isBlank(formato)) { throw
	 * new RelativeException(Constantes.ERROR_CODE_CUSTOM,
	 * "Fecha inicio o formato no pueden ser nulos"); } GenericWrapper<YearMonthDay>
	 * w = new GenericWrapper<>(); w.setEntidad(MidasOroUtil.countDaysBetweenDates(
	 * MidasOroUtil.formatSringToDate(fechaInicio, formato))); return w; }
	 */
	
}

