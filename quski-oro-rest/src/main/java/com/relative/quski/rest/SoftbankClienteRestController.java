package com.relative.quski.rest;

import java.io.UnsupportedEncodingException;
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
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.CatalogosSoftbankWrapper;
import com.relative.quski.wrapper.ConsultaGlobalRespuestaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalWrapper;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;
import com.relative.quski.wrapper.SoftbankRespuestaWrapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/softbankClienteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SoftbankClienteRestController extends BaseRestController
		implements CrudRestControllerInterface<SoftbankClienteWrapper, GenericWrapper<SoftbankClienteWrapper>> {


	public SoftbankClienteRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Inject
	QuskiOroService qos;
	@Inject
	Logger log;
	@Inject
	ParametroRepository parametroRepository;
	
	
	@POST
	@Path("/consultarClienteSoftbank")
	@ApiOperation(value = "SoftbankConsultaWrapper, autentication", notes = "Metodo Get consultarClienteSoftbank Retorna GenericWrapper de la entidad encontrada SoftbankClienteWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SoftbankClienteWrapper> getClienteSoftbank(@QueryParam("autorizacion") String autorizacion,SoftbankConsultaWrapper wrapper) 
			throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
		SoftbankClienteWrapper a = qos.findClienteSoftbank(wrapper.getIdentificacion(), autorizacion);
		loc.setEntidad( a );
		return loc;
	}
	
	@POST
	@Path("/crearClienteSoftbank")
	@ApiOperation(value = "SoftbankConsultaWrapper", notes = "Metodo Post crearClienteSoftbank registra y retorna GenericWrapper de la entidad  SoftbankClienteWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SoftbankRespuestaWrapper> crearClienteSoftbank(@QueryParam("autorizacion") String autorizacion,SoftbankClienteWrapper wrapper) throws RelativeException {
		GenericWrapper<SoftbankRespuestaWrapper> loc = new GenericWrapper<>();
		try {
			SoftbankRespuestaWrapper a = SoftBankApiClient.callCrearClienteRest(this.parametroRepository
					.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_CLIENTE).getValor(), autorizacion,wrapper);
			loc.setEntidad(a);
			return loc;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
	}
	@POST
	@Path("/buscarCreditos")
	@ApiOperation(value = "ConsultaGlobalWrapper",
	notes = "Metodo Get buscarCreditos Retorna GenericWrapper de la entidad encontrada ConsultaGlobalRespuestaWrapper", response = ConsultaGlobalRespuestaWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<ConsultaGlobalRespuestaWrapper> buscarCreditos(@QueryParam("autorizacion") String autorizacion,ConsultaGlobalWrapper wrapper) throws RelativeException {
		GenericWrapper<ConsultaGlobalRespuestaWrapper> loc = new GenericWrapper<>();
		try {
			ConsultaGlobalRespuestaWrapper a = qos.buscarCreditos( wrapper, autorizacion );
			loc.setEntidad(a);
			return loc;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR CARGAR DATOS EN SOFTBANK ");
		}
	}
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "SoftbankConsultaWrapper", 
	notes = "Metodo Get getEntity Retorna GenericWrapper de la entidad encontrada SoftbankClienteWrapper", response = SoftbankClienteWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SoftbankClienteWrapper> getEntity(@QueryParam("autorizacion") String autorizacion,SoftbankConsultaWrapper wrapper) throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
	
		SoftbankClienteWrapper a = qos.findClienteSoftbank(wrapper.getIdentificacion(), autorizacion);
		loc.setEntidad(a);
		
		return loc;
	}
	@GET
	@Path("/traerCatalogos")
	@ApiOperation(value = "", 
	notes = "Metodo Get getEntity Retorna GenericWrapper de las entidades encontradas CatalogosSoftbankWrapper", response = CatalogosSoftbankWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<CatalogosSoftbankWrapper> traerCatalogos(@QueryParam("autorizacion") String autorizacion) throws RelativeException {
		GenericWrapper<CatalogosSoftbankWrapper> loc = new GenericWrapper<>();
		CatalogosSoftbankWrapper a = qos.traerCatalogos(autorizacion);
		loc.setEntidad(a);
		return loc;
	}

	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<SoftbankClienteWrapper>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en SoftbankClienteWrapper", response = PaginatedListWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public PaginatedListWrapper<SoftbankClienteWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<SoftbankClienteWrapper> findAll(PaginatedWrapper pw) {
		PaginatedListWrapper<SoftbankClienteWrapper> plw = new PaginatedListWrapper<>(pw);
		return plw;
	}
	
	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<SoftbankClienteWrapper>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas SoftbankClienteWrapper", response = GenericWrapper.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
	public GenericWrapper<SoftbankClienteWrapper> persistEntity(GenericWrapper<SoftbankClienteWrapper> pw) {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
		return loc;
	}
	@Override
	public GenericWrapper<SoftbankClienteWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
