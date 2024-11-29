package com.relative.quski.repository;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCompromisoPago;
import java.util.List;

public interface CompromisoPagoRepository extends CrudRepository<Long, TbQoCompromisoPago> {
    List<TbQoCompromisoPago> findByNumeroOperacion(String numeroOperacion) throws RelativeException;
}
