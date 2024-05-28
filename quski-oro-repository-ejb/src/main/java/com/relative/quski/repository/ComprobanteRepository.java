package com.relative.quski.repository;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.model.TbQoComprobante;
import com.relative.quski.model.TbQoRegularizacionDocumento;

import javax.ejb.Local;
import java.util.List;
@Local
public interface ComprobanteRepository extends CrudRepository<Long, TbQoComprobante> {
    List<TbQoComprobante> listAllByIdNegociacion(Long idNegociacion) throws RelativeException;

}
