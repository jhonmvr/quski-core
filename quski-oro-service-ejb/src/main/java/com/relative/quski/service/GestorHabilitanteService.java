package com.relative.quski.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoRolTipoDocumento;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.RolTipoDocumentoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.spec.RolTipoDocumentoByParamsSpec;
import com.relative.quski.repository.spec.TipoDocumentoWithDocumentoByAndProRefEstOpSpec;
import com.relative.quski.wrapper.DocumentoHabilitanteWrapper;

@Stateless
public class GestorHabilitanteService {
	
	@Inject
	Logger log;

	@Inject
	TipoDocumentoRepository dhr;
	
	@Inject
	RolTipoDocumentoRepository rtdr;
	
	
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
			Long idTipoDocumento, Long idReferencia, ProcessEnum proceso,EstadoOperacionEnum estadoOperacion){
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
	
	public Long countDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion(
			Long idTipoDocumento, Long idReferencia, ProcessEnum proceso,EstadoOperacionEnum estadoOperacion){
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
			Long idRol, Long idTipoDocumento, Long idReferencia, ProcessEnum proceso,EstadoOperacionEnum estadoOperacion ){
		log.info("==========>generateDocumentoHabilitante");
		List<DocumentoHabilitanteWrapper> tdsw= new ArrayList<>();
		List<TbQoTipoDocumento> tds = findDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion(pw, idTipoDocumento, null, proceso, estadoOperacion);
		log.info("==========>generateDocumentoHabilitante 1 "); 
		if( tds != null && !tds.isEmpty() ) {
			log.info("==========>generateDocumentoHabilitante 2 " + tds.size()); 
			List<TbQoTipoDocumento> tdsWithDoc = findDocumentoHabilitanteByTipoProcesoReferenciaEstadoOperacion(pw, idTipoDocumento, idReferencia, proceso, estadoOperacion);
			log.info("==========>generateDocumentoHabilitante 3 "); 
			if(tdsWithDoc != null && !tdsWithDoc.isEmpty() ) {
				log.info("==========>generateDocumentoHabilitante 4 " + tdsWithDoc.size()); 
				Set<Long> ids = tdsWithDoc.stream().map(td->td.getId()).collect(Collectors.toSet());
				//List<TbQoTipoDocumento> diff = tds.stream().filter(td -> !ids.contains(td.getId())).collect(Collectors.toList());
				log.info("==========>generateDocumentoHabilitante 5 ");
				log.info("==========>generateDocumentoHabilitante 5.1 " + ids); 
				tds.forEach(td->{
					log.info("==========>generateDocumentoHabilitante 6 " + td.getId()); 
					if( ids != null && !ids.isEmpty() && ids.contains( td.getId() ) ) {
						log.info("==========>generateDocumentoHabilitante 6.1 "); 
						tdsw.add(   
								new DocumentoHabilitanteWrapper.DocumentoHabilitanteBuilder()
								.idTipoDocumento( td.getId() ).descripcionTipoDocumento( td.getDescripcion() )
								.estadoOperacion( td.getEstadoOperacion()!=null?td.getEstadoOperacion().toString():null ).pantilla( td.getPlantilla() )
								.proceso( td.getProceso()!=null?td.getProceso().toString():null ).roles( getIdRolesFromRolTipoDocumento(td, idRol) )
								.estaCargado( Boolean.TRUE ).idReferencia(idReferencia)
								.build() ) ;
					} else {
						log.info("==========>generateDocumentoHabilitante 6.2 "); 
						tdsw.add(   
								new DocumentoHabilitanteWrapper.DocumentoHabilitanteBuilder()
								.idTipoDocumento( td.getId() ).descripcionTipoDocumento( td.getDescripcion() )
								.estadoOperacion( td.getEstadoOperacion()!=null?td.getEstadoOperacion().toString():null ).pantilla( td.getPlantilla() )
								.proceso( td.getProceso()!=null?td.getProceso().toString():null ).roles( getIdRolesFromRolTipoDocumento(td, idRol) )
								.estaCargado( Boolean.FALSE )
								.build() ) ;
					}
				});
			} else {
				log.info("==========>generateDocumentoHabilitante 7 "); 
				tds.forEach(td->{
					log.info("==========>generateDocumentoHabilitante 7 " + td.getId()); 
					tdsw.add(   
							new DocumentoHabilitanteWrapper.DocumentoHabilitanteBuilder()
							.idTipoDocumento( td.getId() ).descripcionTipoDocumento( td.getDescripcion() )
							.estadoOperacion( td.getEstadoOperacion()!=null?td.getEstadoOperacion().toString():null ).pantilla( td.getPlantilla() )
							.proceso( td.getProceso()!=null?td.getProceso().toString():null ).roles( getIdRolesFromRolTipoDocumento(td, idRol) )
							.estaCargado( Boolean.FALSE )
							.build() ) ;
				});
			}
			return tdsw;
		}
		return Collections.emptyList();
	}
	
	private List<Long> getIdRolesFromRolTipoDocumento( TbQoTipoDocumento td , Long idRol ){
		List<TbQoRolTipoDocumento> rtds= rtdr.findAllBySpecification( new RolTipoDocumentoByParamsSpec(td.getId(), idRol, td.getProceso(), td.getEstadoOperacion()) );
		Set<Long> ids = rtds.stream().map(r->r.getId()).collect(Collectors.toSet());
		if( ids != null && !ids.isEmpty() ) {
			return new ArrayList<>(ids);
		}
		return Collections.emptyList();
	}
	
	
	
}
