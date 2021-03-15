package com.relative.quski.rest;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoRolTipoDocumento;
import com.relative.quski.service.CreditoNuevoService;
import com.relative.quski.service.GestorHabilitanteService;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DocumentoHabilitanteWrapper;
import com.relative.quski.wrapper.RespuestaHabilitanteCreditoWrapper;

@Path("/documentoHabilitanteExtendedRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentoHabilitanteExtendedRestController extends BaseRestController{
	
	@Inject
	Logger log;
	
	@Inject
	GestorHabilitanteService gdh;
	@Inject
	CreditoNuevoService cs;

	public DocumentoHabilitanteExtendedRestController() throws RelativeException {
		super();
	}
	
	@GET
	@Path("/loadHabilitantes")
	public PaginatedListWrapper<DocumentoHabilitanteWrapper> loadHabilitantes (  
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated, 
			@QueryParam("idRol") String idRol, @QueryParam("idTipoDocumento") String idTipoDocumento, 
			@QueryParam("idReferencia")String idReferencia, @QueryParam("proceso") String proceso,@QueryParam("estadoOperacion")String estadoOperacion)throws RelativeException{
		log.info("============> loadHabilitantes");
		log.info("============> loadHabilitantes idRol " + idRol);
		log.info("============> loadHabilitantes idTipoDocumento  " +  idTipoDocumento );
		log.info("============> loadHabilitantes idReferencia " + idReferencia );
		log.info("============> loadHabilitantes proceso " + proceso );
		log.info("============> loadHabilitantes estadoOperacion " + estadoOperacion );
		
		return this.findHabilitantes(new PaginatedWrapper(Integer.valueOf(page) * Integer.valueOf(pageSize), 
				Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated) ,
				idRol, idTipoDocumento, idReferencia, proceso, estadoOperacion);
		
	}
	
	private PaginatedListWrapper<DocumentoHabilitanteWrapper> findHabilitantes(PaginatedWrapper pw,String idRol, String idTipoDocumento, String idReferencia, 
			String proceso,String estadoOperacion) throws RelativeException {
		PaginatedListWrapper<DocumentoHabilitanteWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DocumentoHabilitanteWrapper> actions = this.gdh.generateDocumentoHabilitante(pw, 
				StringUtils.isNotBlank(idRol)?Long.valueOf( idRol ):null,  
						StringUtils.isNotBlank(idTipoDocumento)?Long.valueOf( idTipoDocumento ):null,
								StringUtils.isNotBlank(idReferencia)?idReferencia:null, 
				StringUtils.isNotBlank(proceso)?Arrays.stream(proceso.split(",")).map(ProcessEnum::valueOf).collect(Collectors.toList()):null,
				StringUtils.isNotBlank(estadoOperacion )?Arrays.stream(estadoOperacion.split(",")).map(EstadoOperacionEnum::valueOf).collect(Collectors.toList()):null);
		if (actions != null && !actions.isEmpty() ) {
			plw.setTotalResults(this.gdh.countDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion( 
					StringUtils.isNotBlank(idTipoDocumento)?Long.valueOf( idTipoDocumento ):null,
							StringUtils.isNotBlank(idReferencia)?idReferencia:null,  
									StringUtils.isNotBlank(proceso)?Arrays.stream(proceso.split(",")).map(ProcessEnum::valueOf).collect(Collectors.toList()):null,
											StringUtils.isNotBlank(estadoOperacion )?Arrays.stream(estadoOperacion.split(",")).map(EstadoOperacionEnum::valueOf).collect(Collectors.toList()):null).intValue() );
			plw.setList(actions);
		}
		return plw;
	}
	
	@GET
	@Path("/getPermisoHabilitanteRol")
	public GenericWrapper<TbQoRolTipoDocumento> getPermisoHabilitanteRol(
			@QueryParam("idTipoDocumento") String idTipoDocumento,@QueryParam("idRol") String  idRol,
			@QueryParam("proceso") String  proceso,@QueryParam("estadoOperacion") String estadoOperacion
			) throws RelativeException{
		GenericWrapper<TbQoRolTipoDocumento> permisos= new GenericWrapper<>();
		permisos.setEntidades(this.gdh.findRolTipoDocumentoByParams(!StringUtils.isEmpty(idTipoDocumento)?Long.valueOf(idTipoDocumento):null, 
				!StringUtils.isEmpty( idRol )?Long.valueOf(idRol):null, 
						StringUtils.isNotBlank(proceso)?Arrays.stream(proceso.split(",")).map(ProcessEnum::valueOf).collect(Collectors.toList()):null,
								StringUtils.isNotBlank(estadoOperacion )?Arrays.stream(estadoOperacion.split(",")).map(EstadoOperacionEnum::valueOf).collect(Collectors.toList()):null));
		return permisos;
	}
	
	@GET
	@Path("/getDocumentoCredito")
	public RespuestaHabilitanteCreditoWrapper getDocumentoCredito(
			@QueryParam("idReferencia") String idReferencia) throws RelativeException{
	if(StringUtils.isBlank(idReferencia)) {
		throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ID DE REFERENCIA"); 
	}
		return cs.generarHabilitanteCredito(Long.valueOf(idReferencia));
	}
	
}
