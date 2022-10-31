package com.relative.quski.rest;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.migracion.api.LocalStorageClient;
import com.relative.quski.service.MigracionService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.FileObjectStorageWrapper;
import com.relative.quski.wrapper.FileObjectStorageWrapperList;
import com.relative.quski.wrapper.NodoWrapper;
import com.relative.quski.wrapper.ObjectEncriptedWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;
import com.relative.quski.wrapper.WrapperArbol;
import com.relative.quski.wrapper.WrapperMetadatosHabilitante;
import com.relative.quski.wrapper.WrapperMetadatosHabilitanteOperaciones;

import io.swagger.annotations.Api;

@Path("/migracionRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Canton - REST CRUD")
public class MigracionRestController extends BaseRestController implements CrudRestControllerInterface<WrapperArbol, GenericWrapper<WrapperArbol>> {

	@Inject
	QuskiOroService qos;
	@Inject
	MigracionService mig;
	@Inject
	LocalStorageClient lsc;
	
	public MigracionRestController() throws RelativeException {
		super();
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericWrapper<WrapperArbol> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginatedListWrapper<WrapperArbol> listAllEntities(String arg0, String arg1, String arg2, String arg3, String arg4)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericWrapper<WrapperArbol> persistEntity(GenericWrapper<WrapperArbol> arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	@POST
	@Path("/generarObjetoStorage")
	public  GenericWrapper<RespuestaObjectWrapper> generarObjetoStorage(@QueryParam("databaseName") String databaseName, 
			@QueryParam("collectionName") String collectionName, ObjectEncriptedWrapper wrapper) throws RelativeException {
		GenericWrapper<RespuestaObjectWrapper> loc = new GenericWrapper<>();
	
			loc.setEntidad(this.mig.createObjectStorage( databaseName, collectionName, wrapper));
		
		return loc;
	}
	
	
	@GET
	@Path("/generarArbolNew")
	public  GenericWrapper<NodoWrapper> generarArbolNew() throws RelativeException {
		GenericWrapper<NodoWrapper> loc = new GenericWrapper<>();
	
		loc.setEntidades(this.mig.generarArbolNew());
		return loc;
	}
	
	@GET
	@Path("/generarArbol")
	public GenericWrapper<WrapperArbol> generarArbol() throws RelativeException {
		GenericWrapper<WrapperArbol> loc = new GenericWrapper<>();
		WrapperArbol a = this.mig.generarArbol();
		loc.setEntidad(a);
		return loc;
	}
	
	@GET
	@Path("/listAllMetadataDocument")
	public GenericWrapper<WrapperMetadatosHabilitante> listAllMetadataDocument() throws RelativeException {
		GenericWrapper<WrapperMetadatosHabilitante> loc = new GenericWrapper<>();
		List<WrapperMetadatosHabilitante> a = this.mig.listAllMetadataDocument();
		loc.setEntidades(a);
		return loc;
	}
	@GET
	@Path("/listAllMetadataDocumentNuevosNovaciones")
	public GenericWrapper<WrapperMetadatosHabilitanteOperaciones> listAllMetadataDocumentNuevosNovaciones() throws RelativeException {
		GenericWrapper<WrapperMetadatosHabilitanteOperaciones> loc = new GenericWrapper<>();
		List<WrapperMetadatosHabilitanteOperaciones> a = this.mig.listAllMetadataDocumentNuevosNovaciones();
		loc.setEntidades(a);
		return loc;
	}
	@GET
	@Path("/findMetadatosQuskiById")
	public GenericWrapper<FileObjectStorageWrapper> findMetadatosById(@QueryParam("id") String id) throws RelativeException {
		GenericWrapper<FileObjectStorageWrapper> loc = new GenericWrapper<>();
		FileObjectStorageWrapper a = this.mig.findMetadatosQuskiById(id);
		loc.setEntidad(a);
		return loc;
	}
	@GET
	@Path("/findAllMetadatosQuski")
	public GenericWrapper<FileObjectStorageWrapperList> findAllMetadatosQuski() throws RelativeException {
		GenericWrapper<FileObjectStorageWrapperList> loc = new GenericWrapper<>();
		FileObjectStorageWrapperList a = this.mig.findAllMetadatosQuski();
		loc.setEntidad(a);
		return loc;
	}


	

}