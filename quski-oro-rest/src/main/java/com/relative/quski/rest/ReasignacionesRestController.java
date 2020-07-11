package com.relative.quski.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.service.ProcesoService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.AsignacionesWrapper;

import io.swagger.annotations.Api;

@Path("/reasignacionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Asignacion Bajo Demanda - REST CRUD")
public class ReasignacionesRestController extends BaseRestController
		implements CrudRestControllerInterface<AsignacionesWrapper, GenericWrapper<AsignacionesWrapper>> {
	@Inject
	QuskiOroService qos;
	@Inject
	ProcesoService procesoService;
	@Inject
	Logger log;

	public ReasignacionesRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// Auto-generated
	}

//	@GET
//	@Path("/findReasignacionByParamsPaginated")
//	@ApiOperation(value = "PaginatedListWrapper<AsignacionesWrapper>", notes = "Metodo Get findByParamsPaginated Retorna wrapper de informacion de paginacion y operaciones activas encontradas", response = PaginatedListWrapper.class)
//	@ApiResponses(value = {
//			@ApiResponse(code = 200, message = "Retorno exitoso de informacion", response = GenericWrapper.class),
//			@ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = GenericWrapper.class) })
//	public PaginatedListWrapper<TbQoCreditoNegociacion> findByParamsPaginated(
//			@QueryParam("page") @DefaultValue("1") String page,
//			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
//			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
//			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
//			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
//			@QueryParam("codigoOperacion") String codigoOperacion, @QueryParam("estado") String estado)
//			throws RelativeException {
//
//		return findByParams(
//				new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields, sortDirections,
//						isPaginated),
//				StringUtils.isNotBlank(codigoOperacion) ? codigoOperacion : null,
//				StringUtils.isNotBlank(estado) ? QuskiOroUtil.getEnumFromString(EstadoOperacionEnum.class, estado)
//						: null);
//	}

//	private PaginatedListWrapper<TbQoCreditoNegociacion> findByParams(PaginatedWrapper pw, String codigoOperacion,
//			EstadoOperacionEnum estado) throws RelativeException {
//		log.info("===================> getPlantilla" + estado);
//		PaginatedListWrapper<TbQoCreditoNegociacion> plw = new PaginatedListWrapper<>(pw);
//		List<TbQoCreditoNegociacion> actions = null;
//
//		actions = this.procesoService.findByCreditoNegociacion(pw, codigoOperacion, estado);
//		if (actions != null && !actions.isEmpty()) {
//			plw.setTotalResults(
//					this.procesoService.countfindBycodigOperacionEstado(codigoOperacion, estado).intValue());
//			plw.setList(actions);
//		}
//
//		return plw;
//	}
	
	/*
	 * @Override
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
	 */
	
	@Override
	public GenericWrapper<AsignacionesWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<AsignacionesWrapper> listAllEntities(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericWrapper<AsignacionesWrapper> persistEntity(GenericWrapper<AsignacionesWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

}
