package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoHistoricoObservacion;
import com.relative.quski.wrapper.HistoricoObservacionWrapper;

@Local
public interface HistoricoObservacionRepository  extends CrudRepository<Long, TbQoHistoricoObservacion>{

	List<HistoricoObservacionWrapper> findByIdCredito(Long idCredito) throws RelativeException;

}
