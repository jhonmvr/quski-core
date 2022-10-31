package com.relative.quski.service;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.migracion.api.LocalStorageClient;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.FileObjectStorageWrapper;
import com.relative.quski.wrapper.FileObjectStorageWrapperList;
import com.relative.quski.wrapper.ListadoOperacionIdNegociacionWrapper;
import com.relative.quski.wrapper.NodoWrapper;
import com.relative.quski.wrapper.ObjectEncriptedWrapper;
import com.relative.quski.wrapper.PerfilesWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;
import com.relative.quski.wrapper.UsuarioWrapper;
import com.relative.quski.wrapper.WrapperArbol;
import com.relative.quski.wrapper.WrapperMetadatosHabilitante;
import com.relative.quski.wrapper.WrapperMetadatosHabilitanteOperaciones;
@Stateless
public class MigracionService {
	@Inject
	Logger log;
	@Inject
	private DocumentoHabilitanteRepository documentoHabilitanteRepository;
	
	@Inject
	private NegociacionRepository negociacionRepository;

	/** *********************************************        QUSKI        **/
	public List<WrapperMetadatosHabilitante> listAllMetadataDocument() throws RelativeException {
		try {
			List<WrapperMetadatosHabilitante> listQuski = this.documentoHabilitanteRepository.listAllMetadataDocument();
			return listQuski;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, " Error en listAllMetadataDocument" + e.getMessage());
		}
	}
	public List<WrapperMetadatosHabilitanteOperaciones> listAllMetadataDocumentNuevosNovaciones() throws RelativeException {
		try {
			List<WrapperMetadatosHabilitanteOperaciones> listQuski = this.documentoHabilitanteRepository.listAllMetadataDocumentNuevosNovaciones();
			return listQuski;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, " Error en listAllMetadataDocument" + e.getMessage());
		}
	}

	
	/** *****************************************        LOCAL_STORAGE    **/
	public FileObjectStorageWrapper findMetadatosQuskiById( String id ) throws RelativeException {
		try {
			String urlService = QuskiOroConstantes.URL_STORAGE;
			RespuestaObjectWrapper objeto = LocalStorageClient.findMetadatosQuskiById(urlService, id);
			Gson gsons = new GsonBuilder().create();
			FileObjectStorageWrapper file = gsons.fromJson( new String(Base64.getDecoder().decode(objeto.getEntidad()) ), FileObjectStorageWrapper.class);
			return file;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	} 
	public FileObjectStorageWrapperList findAllMetadatosQuski( ) throws RelativeException {
		try {
			String urlService = QuskiOroConstantes.URL_STORAGE;
			RespuestaObjectWrapper objeto = LocalStorageClient.findAllMetadatosQuski(urlService);
			Gson gsons = new GsonBuilder().create();
			FileObjectStorageWrapperList file = gsons.fromJson( new String(Base64.getDecoder().decode(objeto.getEntidades()) ), FileObjectStorageWrapperList.class);
			return file;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	} 
	
	
	
	/** ********************************************        ARBOL    **/
	public WrapperArbol generarArbol() throws RelativeException {
		try {
			List<WrapperMetadatosHabilitanteOperaciones> listOp = listAllMetadataDocumentNuevosNovaciones();
			WrapperArbol arbolRaiz = new WrapperArbol();
			WrapperArbol operaciones = new WrapperArbol();

			
			arbolRaiz.setId( Long.valueOf(1) );
			arbolRaiz.setNombre( "Documentos Quski");
			arbolRaiz.setIdPadre( null );
			arbolRaiz.setTipoDocumento( null );
			
			operaciones.setId( Long.valueOf(2) );
			operaciones.setNombre( "Operaciones");
			operaciones.setIdPadre( Long.valueOf(1) );
			operaciones.setTipoDocumento( null );
			
			List<WrapperArbol> hijos1 = new ArrayList<>();
			hijos1.add(operaciones);
			arbolRaiz.setHijos( hijos1 );
			
			List<WrapperArbol> listOperaciones = new ArrayList<>();
			Long iterador = Long.valueOf(2);
			for ( WrapperMetadatosHabilitanteOperaciones e : listOp) {
				WrapperArbol operacion = new WrapperArbol();
				iterador = Long.sum(iterador, Long.valueOf(1));
				operacion.setId( iterador );
				operacion.setNombre( e.getCodigo() );
				operacion.setIdPadre( operaciones.getId() );
				operacion.setTipoDocumento( null );
				listOperaciones.add(operacion);
			}
			operaciones.setHijos(listOperaciones);
			return arbolRaiz;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "indefinido" + e.getMessage());
		}
	}

	public List<NodoWrapper> generarArbolNew() throws RelativeException {
		try {
			List<ListadoOperacionIdNegociacionWrapper> listaOperacionesNegociacion = this.negociacionRepository.traerListaOperaciones();
			List<NodoWrapper> arbol = new ArrayList<NodoWrapper>();
			if(listaOperacionesNegociacion != null) {
				
				for (ListadoOperacionIdNegociacionWrapper operacion : listaOperacionesNegociacion) {
					//definir cada nodo para guardar
					NodoWrapper operacionCarpeta = new NodoWrapper();
					operacionCarpeta.setComment(null);
					operacionCarpeta.setDateCreated("26/10/2021");
					operacionCarpeta.setDateModified("26/10/2021");
					operacionCarpeta.setFileBase64(null);
					operacionCarpeta.setFiletype(null);
					operacionCarpeta.setName(operacion.getCodigo());
					operacionCarpeta.setParentId("61772214c7df560de0b6e034");
					operacionCarpeta.setReferenceCollection(null);
					operacionCarpeta.setReferenceObjectId(null);
					operacionCarpeta.setSize(null);
					operacionCarpeta.setType("CARPETA");
					operacionCarpeta.setUsers(new ArrayList<UsuarioWrapper>());
					operacionCarpeta.setVersion(null);
					UsuarioWrapper user = new UsuarioWrapper();
					user.setCreate(true);
					user.setDelete(true);
					user.setId("admin");
					user.setRead(true);
					user.setUpdate(true);
					operacionCarpeta.getUsers().add(user);
					operacionCarpeta.setProfiles(new ArrayList<PerfilesWrapper>());
					PerfilesWrapper perfil = new PerfilesWrapper();
					perfil.setCreate(true);
					perfil.setDelete(true);
					perfil.setId("admin");
					perfil.setRead(true);
					perfil.setUpdate(true);
					operacionCarpeta.getProfiles().add(perfil);
					arbol.add(operacionCarpeta);
					//guardo el nodo
					String idPrent = guardarEnMongo(operacionCarpeta);
					
					//documento
					NodoWrapper garantiaCarpeta = new NodoWrapper();
					garantiaCarpeta.setComment(null);
					garantiaCarpeta.setDateCreated("26/10/2021");
					garantiaCarpeta.setDateModified("26/10/2021");
					garantiaCarpeta.setFileBase64(null);
					garantiaCarpeta.setFiletype(null);
					garantiaCarpeta.setName("Documento");
					garantiaCarpeta.setParentId(idPrent);
					garantiaCarpeta.setReferenceCollection(null);
					garantiaCarpeta.setReferenceObjectId(null);
					garantiaCarpeta.setSize(null);
					garantiaCarpeta.setType("CARPETA");
					garantiaCarpeta.setUsers(new ArrayList<UsuarioWrapper>());
					garantiaCarpeta.setVersion(null);
					user.setCreate(true);
					user.setDelete(true);
					user.setId("admin");
					user.setRead(true);
					user.setUpdate(true);
					garantiaCarpeta.getUsers().add(user);
					garantiaCarpeta.setProfiles(new ArrayList<PerfilesWrapper>());
					perfil.setCreate(true);
					perfil.setDelete(true);
					perfil.setId("admin");
					perfil.setRead(true);
					perfil.setUpdate(true);
					garantiaCarpeta.getProfiles().add(perfil);
					arbol.add(garantiaCarpeta);
					String idCarpetaDocumento = guardarEnMongo(garantiaCarpeta);
					
					List<ProcessEnum> proceso =  new ArrayList<ProcessEnum>();
					proceso.add(ProcessEnum.NUEVO);
					proceso.add(ProcessEnum.NOVACION);
					List<TbQoDocumentoHabilitante> documento = 
							this.documentoHabilitanteRepository.getByIdNegociacionAndProceso(operacion.getIdNegociacion(),proceso);
					if(documento != null) {
						//defino el nodo archivo 
						for( TbQoDocumentoHabilitante archivo : documento) {
							NodoWrapper nodoArchivo = new NodoWrapper();
							nodoArchivo.setComment(null);
							nodoArchivo.setDateCreated("26/10/2021");
							nodoArchivo.setDateModified("26/10/2021");
							nodoArchivo.setFileBase64(null);
							nodoArchivo.setFiletype(null);
							nodoArchivo.setName(archivo.getNombreArchivo());
							nodoArchivo.setParentId(idCarpetaDocumento);
							nodoArchivo.setReferenceCollection("documento-habilitante");
							nodoArchivo.setReferenceObjectId(archivo.getObjectId());
							nodoArchivo.setSize(null);
							nodoArchivo.setType("ARCHIVO");
							nodoArchivo.setUsers(new ArrayList<UsuarioWrapper>());
							nodoArchivo.setVersion(null);
							UsuarioWrapper user1 = new UsuarioWrapper();
							user1.setCreate(true);
							user1.setDelete(true);
							user1.setId("admin");
							user1.setRead(true);
							user1.setUpdate(true);
							nodoArchivo.getUsers().add(user);
							nodoArchivo.setProfiles(new ArrayList<PerfilesWrapper>());
							PerfilesWrapper perfil1 = new PerfilesWrapper();
							perfil1.setCreate(true);
							perfil1.setDelete(true);
							perfil1.setId("admin");
							perfil1.setRead(true);
							perfil1.setUpdate(true);
							nodoArchivo.getProfiles().add(perfil);
							arbol.add(nodoArchivo);
							guardarEnMongo(nodoArchivo);
						}
					}
					//garantia
					garantiaCarpeta = new NodoWrapper();
					garantiaCarpeta.setComment(null);
					garantiaCarpeta.setDateCreated("26/10/2021");
					garantiaCarpeta.setDateModified("26/10/2021");
					garantiaCarpeta.setFileBase64(null);
					garantiaCarpeta.setFiletype(null);
					garantiaCarpeta.setName("Garantia");
					garantiaCarpeta.setParentId(idPrent);
					garantiaCarpeta.setReferenceCollection(null);
					garantiaCarpeta.setReferenceObjectId(null);
					garantiaCarpeta.setSize(null);
					garantiaCarpeta.setType("CARPETA");
					garantiaCarpeta.setUsers(new ArrayList<UsuarioWrapper>());
					garantiaCarpeta.setVersion(null);
					user.setCreate(true);
					user.setDelete(true);
					user.setId("admin");
					user.setRead(true);
					user.setUpdate(true);
					garantiaCarpeta.getUsers().add(user);
					garantiaCarpeta.setProfiles(new ArrayList<PerfilesWrapper>());
					perfil.setCreate(true);
					perfil.setDelete(true);
					perfil.setId("admin");
					perfil.setRead(true);
					perfil.setUpdate(true);
					garantiaCarpeta.getProfiles().add(perfil);
					arbol.add(garantiaCarpeta);
					
					String idGarantiaCarpeta = guardarEnMongo(garantiaCarpeta);
					
					proceso =  new ArrayList<ProcessEnum>();
					proceso.add(ProcessEnum.FUNDA);
					documento = 
							this.documentoHabilitanteRepository.getByIdNegociacionAndProceso(operacion.getIdNegociacion(),proceso);
					if(documento != null) {
						//defino el nodo archivo 
						for( TbQoDocumentoHabilitante archivo : documento) {
							NodoWrapper nodoArchivo = new NodoWrapper();
							nodoArchivo.setComment(null);
							nodoArchivo.setDateCreated("26/10/2021");
							nodoArchivo.setDateModified("26/10/2021");
							nodoArchivo.setFileBase64(null);
							nodoArchivo.setFiletype(null);
							nodoArchivo.setName(archivo.getNombreArchivo());
							nodoArchivo.setParentId(idGarantiaCarpeta);
							nodoArchivo.setReferenceCollection("documento-habilitante");
							nodoArchivo.setReferenceObjectId(archivo.getObjectId());
							nodoArchivo.setSize(null);
							nodoArchivo.setType("ARCHIVO");
							nodoArchivo.setUsers(new ArrayList<UsuarioWrapper>());
							nodoArchivo.setVersion(null);
							UsuarioWrapper user1 = new UsuarioWrapper();
							user1.setCreate(true);
							user1.setDelete(true);
							user1.setId("admin");
							user1.setRead(true);
							user1.setUpdate(true);
							nodoArchivo.getUsers().add(user);
							nodoArchivo.setProfiles(new ArrayList<PerfilesWrapper>());
							PerfilesWrapper perfil1 = new PerfilesWrapper();
							perfil1.setCreate(true);
							perfil1.setDelete(true);
							perfil1.setId("admin");
							perfil1.setRead(true);
							perfil1.setUpdate(true);
							nodoArchivo.getProfiles().add(perfil);
							arbol.add(nodoArchivo);
							guardarEnMongo(nodoArchivo);
						}
					}
					
					
				}
			}
			
			return arbol;
			}
			
			
		 catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "indefinido" + e.getMessage());
		}
	}
	
	String guardarEnMongo(NodoWrapper nodo) throws RelativeException {
		
		String urlService = "http://localhost:8080/generic-relative-rest/resources/mongoRestController/";
		String databaseName ="quski-core-documento";
		String collectionName = "estructura-archivos";
		try {
			return LocalStorageClient.createObjectBig(urlService, databaseName, collectionName, nodo, null).getEntidad();
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
			
		}
		
	}
	
	
	public  RespuestaObjectWrapper createObjectStorage (String databaseName, 
			 String collectionName, ObjectEncriptedWrapper wrapper) throws RelativeException{
		String urlService = "http://localhost:8080/generic-relative-rest/resources/mongoRestController/";
		try {
			return null;//LocalStorageClient.createObjectBig(urlService, databaseName, collectionName, wrapper, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	
}
	
