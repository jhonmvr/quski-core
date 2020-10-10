package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoProceso;

@Local
public interface ProcesoRepository extends CrudRepository<Long, TbQoProceso> {
	public TbQoProceso findById(Long id) throws RelativeException;

}
