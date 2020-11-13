package com.relative.quski.rest;

import java.io.UnsupportedEncodingException;
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
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;

import io.swagger.annotations.ApiOperation;

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
	SoftBankApiClient csfs;
	@Inject
	Logger log;
	
	
	@POST
	@Path("/consultarClienteSoftbank")
	public GenericWrapper<SoftbankClienteWrapper> getClienteSoftbank(SoftbankConsultaWrapper wrapper,String autentication) 
			throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
		SoftbankClienteWrapper a = SoftBankApiClient.callConsultaClienteRest(wrapper);
		loc.setEntidad( a );
		return loc;
	}
	
	@POST
	@Path("/crearClienteSoftbank")
	public GenericWrapper<SoftbankClienteWrapper> crearClienteSoftbank(SoftbankClienteWrapper wrapper) throws RelativeException {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
		try {
			List<SoftbankClienteWrapper> a = (List<SoftbankClienteWrapper>) SoftBankApiClient.callCrearClienteRest(wrapper);
			loc.setEntidades(a);
			return loc;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR GUARDAR DATOS EN SOFTBANK ");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR GUARDAR DATOS EN SOFTBANK ");
		}
	}
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Path("/getEntity")
	public GenericWrapper<SoftbankClienteWrapper> getEntity(SoftbankConsultaWrapper wrapper) throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<SoftbankClienteWrapper> loc = new GenericWrapper<>();
	
		SoftbankClienteWrapper a = SoftBankApiClient.callConsultaClienteRest(wrapper);
		loc.setEntidad(a);
		
		return loc;
	}

	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<SoftbankClienteWrapper>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en SoftbankClienteWrapper", response = PaginatedListWrapper.class)
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
