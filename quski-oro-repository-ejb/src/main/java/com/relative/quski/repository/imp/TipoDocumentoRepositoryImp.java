package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.wrapper.DocumentoHabilitanteWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "TipoDocumentoRepository")
public class TipoDocumentoRepositoryImp extends GeneralRepositoryImp<Long, TbQoTipoDocumento> implements TipoDocumentoRepository {
	
	@Inject
	Logger log;

	
	public List<DocumentoHabilitanteWrapper> findByTipoProcesoReferenciaEstadoOperacion(PaginatedWrapper pw, 
			Long idTipoDocumento, Long idReferencia, List<ProcessEnum> proceso,List<EstadoOperacionEnum> estadoOperacion) throws RelativeException{
		try {
			StringBuilder queryStr = new StringBuilder("SELECT  NEW com.relative.quski.wrapper.DocumentoHabilitanteWrapper("); 
			queryStr.append("td.id as idTipoDocumento ,"); 
			if( idReferencia != null ) {
				queryStr.append("dh.id as idDocumentoHabilitante ,dh.idReferencia,dh.objectId,dh.tipoDocumento,"); 
			} 
			queryStr.append( "td.descripcion,td.estadoOperacion, td.proceso, "); 
			queryStr.append("td.plantilla, td.servicio ) " ); 
			queryStr.append(" FROM TbQoTipoDocumento AS td "); 
			if( idReferencia != null ) {
				queryStr.append("inner join td.tbQoDocumentoHabilitantes dh "); 
			}
			queryStr.append("where 1=1 ");
			if( proceso != null ) {
				queryStr.append(" and td.proceso in :proceso ");
			}
			if( idTipoDocumento != null ) {
				queryStr.append(" and td.id=:idTipoDocumento ");
			}
			if( estadoOperacion != null ) {
				queryStr.append(" and td.estadoOperacion in :estadoOperacion  ");
			}
			if( idReferencia != null ) {
				queryStr.append(" and dh.idReferencia=:idReferencia ");
			}
			if( !StringUtils.isEmpty(pw.getSortFields())  && !StringUtils.isEmpty(pw.getSortDirections()) ) {
				queryStr.append(" order by td.").append(pw.getSortFields()).append(" " ).append( pw.getSortDirections() );
			}
			log.info("===> query gfenerado " + queryStr.toString()) ;
			TypedQuery<DocumentoHabilitanteWrapper> query = this.getEntityManager().createQuery(queryStr.toString(), DocumentoHabilitanteWrapper.class);
			if( idTipoDocumento != null ) {
				query.setParameter("idTipoDocumento", idTipoDocumento);
			}
			if( proceso != null && !proceso.isEmpty()) {
				query.setParameter("proceso",proceso);
			}
			if( estadoOperacion != null && !estadoOperacion.isEmpty() ) {
				query.setParameter("estadoOperacion",estadoOperacion);
			}
			if( idReferencia != null ) {
				query.setParameter("idReferencia",idReferencia);
			}
			if( pw != null && pw.getIsPaginated().equalsIgnoreCase( PaginatedWrapper.YES ) ) {
				query.setFirstResult( pw.getStartRecord() );
				query.setMaxResults( pw.getPageSize() );
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "Error en la busqueda findByTipoProcesoReferenciaEstadoOperacion " + e.getMessage() );
		}
		
	} 

}
