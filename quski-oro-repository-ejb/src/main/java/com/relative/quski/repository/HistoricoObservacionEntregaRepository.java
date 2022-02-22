package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoHistoricoObservacionEntrega;

@Local
public interface HistoricoObservacionEntregaRepository  extends CrudRepository<Long, TbQoHistoricoObservacionEntrega>{

	List<TbQoHistoricoObservacionEntrega> findByIdCredito(Long idCredito) throws RelativeException;

}
