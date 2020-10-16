package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoPatrimonio;

@Local
public interface PatrimonioRepository extends CrudRepository<Long, TbQoPatrimonio>{
	public List<TbQoPatrimonio> findByIdCliente(Long id) throws RelativeException;
}
