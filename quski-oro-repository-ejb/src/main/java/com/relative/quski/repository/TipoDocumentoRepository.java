package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.wrapper.DocumentoHabilitanteWrapper;

@Local
public interface TipoDocumentoRepository extends CrudRepository<Long, TbQoTipoDocumento>{
	public List<DocumentoHabilitanteWrapper> findByTipoProcesoReferenciaEstadoOperacion(PaginatedWrapper pw, 
			Long idTipoDocumento, Long idReferencia, ProcessEnum proceso,EstadoOperacionEnum estadoOperacion) throws RelativeException;


}
