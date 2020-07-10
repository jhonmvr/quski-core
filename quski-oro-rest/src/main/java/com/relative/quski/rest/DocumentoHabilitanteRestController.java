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
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.util.QuskiOroUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/documentoHabilitanteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "documentoHabilitanteController - REST CRUD")
public class DocumentoHabilitanteRestController  extends BaseRestController
implements CrudRestControllerInterface<TbQoDocumentoHabilitante, GenericWrapper<TbQoDocumentoHabilitante>> {

	public DocumentoHabilitanteRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Inject
	QuskiOroService qos;


	@Override
	@GET
	@Path("/getEntity")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoDocumentoHabilitante", response = GenericWrapper.class)
	public GenericWrapper<TbQoDocumentoHabilitante> getEntity(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<TbQoDocumentoHabilitante> loc = new GenericWrapper<>();
		TbQoDocumentoHabilitante a = this.qos.findDocumentoHabilitanteById(Long.valueOf(id));
		loc.setEntidad(a);
		return loc;
	}
	
	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<TbQoCotizador>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoDocumentoHabilitante", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<TbQoDocumentoHabilitante> listAllEntities(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}

	private PaginatedListWrapper<TbQoDocumentoHabilitante> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<TbQoDocumentoHabilitante> plw = new PaginatedListWrapper<>(pw);
		List<TbQoDocumentoHabilitante> actions = this.qos.findAllDocumentoHabilitante(pw);
		if (actions != null && !actions.isEmpty()) {
			plw.setTotalResults(this.qos.countDocumentoHabilitante().intValue());
			plw.setList(actions);
		}
		return plw;
	}


	@Override
	@POST
	@Path("/persistEntity")
	@ApiOperation(value = "GenericWrapper<TbMiDocumentoHabilitante>", notes = "Metodo Post persistEntity Retorna GenericWrapper de informacion de paginacion y listado de entidades encontradas TbMiDocumentoHabilitante", response = GenericWrapper.class)
	public GenericWrapper<TbQoDocumentoHabilitante> persistEntity(GenericWrapper<TbQoDocumentoHabilitante> wp)
			throws RelativeException {
		GenericWrapper<TbQoDocumentoHabilitante> loc = new GenericWrapper<>();
		loc.setEntidad(this.qos.manageDocumentoHabilitante(wp.getEntidad()));
		return loc;
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}	
	
	
	
	
	/*
	@GET
	@Path("/downloadHabilitante")
	@ApiOperation(value = "byte ", notes = "Metodo findByIdDocumentoHabilitante Retorna wrapper de entidades encontradas en TbQoDocumentoHabilitante", response = byte.class)
	public byte[] downloadHabilitante(
			@QueryParam("id") String idTipoDocumento,
			@QueryParam("identificacionCliente") String identificacionCliente,
			@QueryParam("idCotizacion") String idCotizacion,
			@QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
		log.info("===> idTipoDocumento " + idTipoDocumento);
		log.info("===> codigoContrato " + identificacionCliente);
		log.info("===> idCotizacion " + idCotizacion);
		log.info("===> idNegociacion " + idNegociacion);
		
		if (idTipoDocumento != null &&  identificacionCliente != null && !identificacionCliente.isEmpty() ) {
			TbQoDocumentoHabilitante a = this.qos.findDocumentoHabilitanteByTipoDocumentoAndCodigoContrato(codigoContrato, Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		} else if (idTipoDocumento != null &&  idJoya != null && !idJoya.isEmpty() ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					Long.valueOf( idJoya ),null,null,null, Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		} else if (idTipoDocumento != null &&  StringUtils.isNotBlank(idAbono) ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					null ,Long.valueOf( idAbono ), null,null,Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		}else if (idTipoDocumento != null &&  StringUtils.isNotBlank(idVentaLote) ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					null ,null,Long.valueOf( idVentaLote ),null,Long.valueOf( idTipoDocumento));
			return getArchivo(a);
		} 
		else if (idTipoDocumento != null &&  StringUtils.isNotBlank(idCorteCaja) ) { 
			TbMiDocumentoHabilitante a = this.mis.findDocumentoHabilitanteByTipoDocumentoAndIdJoyaAndIdAbono(
					null ,null,null,Long.valueOf( idCorteCaja ),Long.valueOf( idTipoDocumento));
			return getArchivo(a);
			
		} 
		else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "EL ID DE DOCUMENTOS NO PUEDE SER NULO O VACIO ");
		}

	}*/
	

	@GET
	@Path("/getByProcesoTipoDocumentoReferencia")
	@ApiOperation(value = "GenericWrapper<TbQoCotizador>", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoDocumentoHabilitante", response = GenericWrapper.class)
	public GenericWrapper<TbQoDocumentoHabilitante> getByTipoDocumentoProcesoReferencia(@QueryParam("idTipoDocumento") String idTipoDocumento, 
			@QueryParam("proceso") String proceso, @QueryParam("referencia") String referencia) throws RelativeException {
		GenericWrapper<TbQoDocumentoHabilitante> loc = new GenericWrapper<>();
		TbQoDocumentoHabilitante a = this.qos.findDocumentoHabilitanteByTipoDocumentoReferenciaProceso(idTipoDocumento.isEmpty() ? null : Long.valueOf(idTipoDocumento), 
				QuskiOroUtil.getEnumFromString(ProcessEnum.class, proceso), referencia.isEmpty() ? null : Long.valueOf(referencia));
		loc.setEntidad(a);
		return loc;
	}
}
