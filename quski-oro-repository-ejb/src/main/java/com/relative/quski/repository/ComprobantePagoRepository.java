package com.relative.quski.repository;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoComprobante;
import com.relative.quski.model.TbQoComprobantePago;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ComprobantePagoRepository extends CrudRepository<Long, TbQoComprobantePago> {
    List<TbQoComprobantePago> listAllByIdNegociacion(Long idNegociacion) throws RelativeException;

}
