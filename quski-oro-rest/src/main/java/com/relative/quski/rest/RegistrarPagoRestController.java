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

import org.apache.commons.lang.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.service.PagoService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.RegistrarBloqueoFondoWrapper;
import com.relative.quski.wrapper.RegistrarPagoWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/registrarPagoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "registrarPagoRestController - REST CRUD")
public class RegistrarPagoRestController extends BaseRestController
		implements CrudRestControllerInterface<TbQoRegistrarPago, GenericWrapper<TbQoRegistrarPago>> {

	public RegistrarPagoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Inject
	Logger log;
	@Inject
	QuskiOroService qos;
	
	@Inject
	PagoService ps;
	
	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRegistrarPago> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		TbQoRegistrarPago a = this.qos.findRegistrarPagoById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoRegistrarPago", 
	response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoRegistrarPago> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));
	}

	private PaginatedListWrapper<TbQoRegistrarPago> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoRegistrarPago> plw = new PaginatedListWrapper<>(pw);
		List<TbQoRegistrarPago> actions = this.qos.findAllRegistrarPago(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countRegistrarPago().intValue());
			plw.setList(actions);
		}
		return plw;
	}

	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRegistrarPago> persistEntity(GenericWrapper<TbQoRegistrarPago> wp)
			throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		log.info("INGRESA AL REST DE persistEntity registrarPago ");		
		loc.setEntidad(this.qos.manageRegistrarPago(wp.getEntidad()));
		return loc;
		
	}
	@POST
	@Path("/crearRegistrarPago")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<RegistrarPagoWrapper> crearRegistrarPago(RegistrarPagoWrapper registroPago, String autentication) 
			throws RelativeException {
		GenericWrapper<RegistrarPagoWrapper> loc = new GenericWrapper<>();
		
		try {
			loc.setEntidad( this.ps.crearRegistrarPago(registroPago,autentication) );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"INTENTAR GUARDAR EN LOCAL STORAGE ");
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
		return loc;
	}
	@POST
	@Path("/bloqueoFondo")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post registra los bloqueo de fondos o credito en la entidad TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<RegistrarBloqueoFondoWrapper> bloqueoFondo(RegistrarBloqueoFondoWrapper bloqueoFondo, String autentication,FileWrapper fw) 
			throws RelativeException, UnsupportedEncodingException {
		GenericWrapper<RegistrarBloqueoFondoWrapper> loc = new GenericWrapper<>();
		
		try {
			loc.setEntidad( this.ps.bloqueoFondo(bloqueoFondo, autentication, fw.getFile()));
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"INTENTAR GUARDAR EN LOCAL STORAGE ");
		}
		return loc;
	}
	@GET
	@Path("/findByIdClientePago")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbQoRegistrarPago", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoRegistrarPago> findByIdClientePago(@QueryParam("id")  String id) throws RelativeException {
		GenericWrapper<TbQoRegistrarPago> loc = new GenericWrapper<>();
		
		loc.setEntidades( this.ps.findRegistrarPagoByIdClientePago(StringUtils.isNotBlank(id)?Long.valueOf(id):null) );
		return loc;
	}
	
	@GET
	@Path("/aprobar")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post aprobar que aprueba un pago o bloqueo de fondos", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoClientePago> aprobar(@QueryParam("id")  String id,
			@QueryParam("tipo")  String tipo,
			@QueryParam("mail")  String mail,
			@QueryParam("nombre")  String nombre) 
			throws RelativeException {
		GenericWrapper<TbQoClientePago> loc = new GenericWrapper<TbQoClientePago>();
		Constantes.MSG_ERROR_BUSQUEDA.concat(id);	
		loc.setEntidad( this.ps.aprobarPago(StringUtils.isNotBlank(id)?Long.valueOf(id):null,tipo,nombre,mail) );
		return loc;
	}
	
	@GET
	@Path("/rechazar")
	@ApiOperation(value = "GenericWrapper<TbQoRegistrarPago>", 
	notes = "Metodo Post aprobar que aprueba un pago o bloqueo de fondos", 
	response = GenericWrapper.class)
	public GenericWrapper<TbQoClientePago> rechazar(@QueryParam("id")  String id,
			@QueryParam("tipo")  String tipo,
			@QueryParam("mail")  String mail,
			@QueryParam("nombre")  String nombre) 
			throws RelativeException { 
		GenericWrapper<TbQoClientePago> loc = new GenericWrapper<TbQoClientePago>();
		Constantes.MSG_ERROR_BUSQUEDA.concat(id);
		loc.setEntidad( this.ps.rechazarPago(StringUtils.isNotBlank(id)?Long.valueOf(id):null,tipo,nombre,mail) );
		return loc;
	}
	
	
}