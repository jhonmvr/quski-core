package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoHistoricoOperativa;
import com.relative.quski.wrapper.HistoricoOperativaWrapper;

@Local
public interface HistoricoOperativaRepository  extends CrudRepository<Long, TbQoHistoricoOperativa>{

	List<HistoricoOperativaWrapper> findByIdCredito(Long idCredito) throws RelativeException;

}
