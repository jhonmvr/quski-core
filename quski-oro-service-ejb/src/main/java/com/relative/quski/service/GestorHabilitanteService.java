package com.relative.quski.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoRolTipoDocumento;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.RolTipoDocumentoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.spec.DocumentoById;
import com.relative.quski.repository.spec.RolTipoDocumentoByParamsSpec;
import com.relative.quski.repository.spec.TipoDocumentoWithDocumentoByAndProRefEstOpSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DocumentoHabilitanteWrapper;
import com.relative.quski.wrapper.FileWrapper;

@Stateless
public class GestorHabilitanteService {
	
	@Inject
	Logger log;

	@Inject
	TipoDocumentoRepository dhr;
	
	@Inject
	RolTipoDocumentoRepository rtdr;
	
	@Inject
	DocumentoHabilitanteRepository dh;
	
	
	/**
	 * Metodo que retorna la informacion de tipos de documentos habilitantes 
	 * @param pw Objeto con datos de paginacion
	 * @param idTipoDocumento Tipo de documento a buscar
	 * @param idReferencia Id Referencia documento
	 * @param proceso Proceso al que se aplica este documento
	 * @param estadoOperacion estado de operacion en el que se utiliza este documento
	 * @return
	 */
	public List<TbQoTipoDocumento> findDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion( PaginatedWrapper pw, 
			Long idTipoDocumento, String idReferencia, List<ProcessEnum> proceso,List<EstadoOperacionEnum> estadoOperacion){
		if( pw != null && pw.getIsPaginated().equalsIgnoreCase( PaginatedWrapper.YES )) {
			if( !StringUtils.isEmpty(pw.getSortDirections())  ) {
				return dhr.findAllBySpecificationPaged(new TipoDocumentoWithDocumentoByAndProRefEstOpSpec(idTipoDocumento, idReferencia, proceso, estadoOperacion) ,
						pw.getStartRecord(),pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return dhr.findAllBySpecificationPaged(new TipoDocumentoWithDocumentoByAndProRefEstOpSpec(idTipoDocumento, idReferencia, proceso, estadoOperacion) ,
						pw.getStartRecord(),pw.getPageSize());
			}
		} else {
			return dhr.findAllBySpecification(new TipoDocumentoWithDocumentoByAndProRefEstOpSpec(idTipoDocumento, idReferencia, proceso, estadoOperacion) );
		}
		
	}
	
	public List<DocumentoHabilitanteWrapper> findDocumentoHabilitanteWrapperByTipoProcesoReferenciaEstadoOperacion( PaginatedWrapper pw, 
			Long idTipoDocumento, String idReferencia, List<ProcessEnum> proceso, List<EstadoOperacionEnum> estadoOperacion) throws RelativeException{
		return this.dhr.findByTipoProcesoReferenciaEstadoOperacion(pw, idTipoDocumento, idReferencia, proceso, estadoOperacion);
		
	}
	
	public Long countDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion(
			Long idTipoDocumento, String idReferencia, List<ProcessEnum> proceso, List<EstadoOperacionEnum> estadoOperacion){
		return dhr.countBySpecification(new TipoDocumentoWithDocumentoByAndProRefEstOpSpec(idTipoDocumento, idReferencia, proceso, estadoOperacion));
	}
	
	/**
	 * 
	 * @param pw
	 * @param idRol
	 * @param idTipoDocumento
	 * @param idReferencia
	 * @param proceso
	 * @param estadoOperacion
	 * @return
	 */
	public List<DocumentoHabilitanteWrapper> generateDocumentoHabilitante( PaginatedWrapper pw, 
			Long idRol, Long idTipoDocumento, String idReferencia, List<ProcessEnum> proceso,List<EstadoOperacionEnum> estadoOperacion ) throws RelativeException{
		log.info("==========>generateDocumentoHabilitante");
		List<DocumentoHabilitanteWrapper> tdsw= new ArrayList<>();
		List<DocumentoHabilitanteWrapper> tds = findDocumentoHabilitanteWrapperByTipoProcesoReferenciaEstadoOperacion(pw, idTipoDocumento, null, proceso, null);
		log.info("==========>generateDocumentoHabilitante 1 "); 
		if( tds != null && !tds.isEmpty() ) {
			log.info("==========>generateDocumentoHabilitante 2 " + tds.size()); 
			List<DocumentoHabilitanteWrapper> tdsWithDoc = findDocumentoHabilitanteWrapperByTipoProcesoReferenciaEstadoOperacion(pw, idTipoDocumento, idReferencia, proceso, null);
			log.info("==========>generateDocumentoHabilitante 3 "); 
			if(tdsWithDoc != null && !tdsWithDoc.isEmpty() ) {
				log.info("==========>generateDocumentoHabilitante 4 " + tdsWithDoc.size()); 
				log.info("==========>generateDocumentoHabilitante 5 ");
				List<Long> previous= new ArrayList<>();
				tds.forEach(td->{
					log.info("==========>generateDocumentoHabilitante 6 " + td.getIdTipoDocumento()); 
					DocumentoHabilitanteWrapper existeDocumentoHabilitante =null;
					existeDocumentoHabilitante = this.getDocumentId(tdsWithDoc, td.getIdTipoDocumento() , previous);
					td.setRoles( 
							rtdr.findAllBySpecification( new RolTipoDocumentoByParamsSpec(td.getIdTipoDocumento(), idRol, 
								 td.getProceso(),estadoOperacion ) ) 
							);
					if( existeDocumentoHabilitante != null ) {
						log.info("==========>generateDocumentoHabilitante 6.0 " + existeDocumentoHabilitante.getIdDocumentoHabilitante()); 
						td.setIdDocumentoHabilitante( existeDocumentoHabilitante.getIdDocumentoHabilitante() );
						td.setIdReferencia( existeDocumentoHabilitante.getIdReferencia() );
						td.setObjectId( existeDocumentoHabilitante.getObjectId() );
						td.setMimeType( existeDocumentoHabilitante.getMimeType() );
						previous.add( existeDocumentoHabilitante.getIdDocumentoHabilitante()  );
						log.info("==========>generateDocumentoHabilitante 6.1 " + td.getIdDocumentoHabilitante()); 
						
						td.setEstaCargado( Boolean.TRUE );
						tdsw.add( td) ;
					} else {		
						td.setEstaCargado( Boolean.FALSE );
						tdsw.add( td) ;
					}
					
				});
			} else {
				log.info("==========>generateDocumentoHabilitante 7 "); 
				tds.forEach(td->{
					log.info("==========>generateDocumentoHabilitante 7 " + td.getIdDocumentoHabilitante()); 
					td.setRoles( 
							rtdr.findAllBySpecification( new RolTipoDocumentoByParamsSpec(td.getIdTipoDocumento(), idRol, 
									td.getProceso(),estadoOperacion) ) 
							);
					td.setEstaCargado( Boolean.FALSE );
					tdsw.add( td) ;
				});
			}
			return tdsw;
		}
		return Collections.emptyList();
	}
	
	private DocumentoHabilitanteWrapper getDocumentId( List<DocumentoHabilitanteWrapper> dhws, Long idTipoDocumento, List<Long> previous ) {
		List<DocumentoHabilitanteWrapper> loc = dhws.stream().filter(twd -> idTipoDocumento == twd.getIdTipoDocumento() ).collect( Collectors.toList() );
		if( loc != null && !loc.isEmpty() ) {
			return loc.stream().filter(l -> !previous.contains(idTipoDocumento) ).findFirst().orElse( null );
		}
		return null;
	}
	
	
	public List<TbQoRolTipoDocumento> findRolTipoDocumentoByParams( Long idTipoDocumento, Long idRol, List<ProcessEnum> process, List<EstadoOperacionEnum> estadoOperacion )
			throws RelativeException{
		return rtdr.findAllBySpecification( new RolTipoDocumentoByParamsSpec(idTipoDocumento, idRol, process, estadoOperacion));
	}
	
	
	/**
	 * Metodo que se encarga de registrar el documento habilitante en la base de datos
	 * @param fw FileWrapper con los siguientes parametros:
	 * RelatedId id de documento habilitante
	 * RelatedIdStr id de referencia del documento habilitante
	 * Process proceso al que corresponde la carga de archivo
	 * EstadoOperacion operacion a la que corresponde la carga de archivo
	 * @return Tipo de habilitante con los datos registrados
	 * @throws RelativeException
	*/
	public TbQoDocumentoHabilitante generateDocumentoHabilitanteSimplified(FileWrapper fw) throws RelativeException {
		TbQoDocumentoHabilitante dhs = new TbQoDocumentoHabilitante();
		
		List<ProcessEnum> pe= new ArrayList<>();
		pe.addAll(StringUtils.isNotBlank(fw.getProcess())?Arrays.stream(fw.getProcess().split(",")).map(ProcessEnum::valueOf).collect(Collectors.toList()):null);
		List<EstadoOperacionEnum> eoe=new ArrayList<>();
		eoe.add(!StringUtils.isEmpty( fw.getEstadoOperacion() )?QuskiOroUtil.getEnumFromString(EstadoOperacionEnum.class,fw.getEstadoOperacion()):null );
		
		List<TbQoTipoDocumento> tds=findDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion(null, Long.valueOf(fw.getTypeAction()  ), 
				fw.getRelatedIdStr() , pe, eoe);
		if( tds != null && !tds.isEmpty() ) {
			dhs.setId( tds.get(0).getId() );
		}
		dhs.setId( fw.getRelatedId());
		dhs.setIdReferencia( fw.getRelatedIdStr() );
		dhs.setFechaCreacion( new Date(System.currentTimeMillis()) );
		dhs.setEstado( EstadoEnum.ACT );
		dhs.setEstadoOperacion(eoe.get(0));
		dhs.setObjectId( fw.getObjectId() );
		dhs.setProceso(pe.get(0));
		dhs.setNombreArchivo( fw.getName() );
		dhs.setTipoDocumento( fw.getType() );
		TbQoTipoDocumento td = dhr.findById(Long.valueOf(fw.getTypeAction())); 
		dhs.setTbQoTipoDocumento(td);		
		return this.manageDocumentoHabilitante(dhs);
	} 
	
	public TbQoDocumentoHabilitante manageDocumentoHabilitante(TbQoDocumentoHabilitante send) throws RelativeException {
		try {
			log.info("==> entra a manage TbQoDocumentoHabilitante" + send);
			if (send != null && send.getId() != null) {
				log.info("***Ingresa al findDocumento ****findDocumentoHabilitanteById " + send.getId());
					List<TbQoDocumentoHabilitante> temp = this.dh.findAllBySpecification( new DocumentoById( send.getId() ) );
					if( temp != null && !temp.isEmpty()  ) {
						return this.updateDocumentoHabilitante(send, temp.get(0));
					} else {
						throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "No existe Habilitantes para el id " + send.getId());
					}
			} else if (send != null && send.getId() == null) {
				log.info("INGRESO AL ELSE");
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return dh.add(send);
			} else {
				log.info("INGRESA AL ERRRORRRRR");
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la HistoricoJoya " + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante updateDocumentoHabilitante(TbQoDocumentoHabilitante send,
			TbQoDocumentoHabilitante persisted) throws RelativeException {
		log.info("INGRESA A+++++++++++++ updateDocumentoHabilitante " + persisted);
		try {
			if( send.getArchivo() != null ) {
				persisted.setArchivo(send.getArchivo());
			}
			if(send.getEstado() != null) {
				persisted.setEstado(send.getEstado());
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			if(send.getNombreArchivo() != null) {
				persisted.setNombreArchivo(send.getNombreArchivo());
			}
			if( send.getTbQoTipoDocumento() != null ) {
				persisted.setTbQoTipoDocumento(send.getTbQoTipoDocumento());
			}
			log.info("ANTES DEL IF EN updateDocumentoHabilitante " + send.getNombreArchivo());
			if (send.getTbQoCotizador() != null) {
				persisted.setTbQoCotizador(send.getTbQoCotizador());
			}
			if (send.getTbQoNegociacion() != null) {
				persisted.setTbQoNegociacion(send.getTbQoNegociacion());
			}
			if (send.getTbQoCliente() != null) {
				persisted.setTbQoCliente(send.getTbQoCliente());
			}
			if (send.getObjectId() != null) {
				persisted.setObjectId(send.getObjectId());
			}
			if (send.getTipoDocumento() != null) {
				persisted.setTipoDocumento(send.getTipoDocumento());
			}
			return dh.update(persisted);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando documentoHabilitanteRepository " + e.getMessage());
		}
	}
	
	
}
