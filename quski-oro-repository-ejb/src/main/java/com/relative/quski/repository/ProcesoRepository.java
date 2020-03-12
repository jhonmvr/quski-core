package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoProceso;;

@Local
public interface ProcesoRepository extends CrudRepository<Long, TbQoProceso> {

	public List<TbQoProceso> findById(long id)throws RelativeException;
}
