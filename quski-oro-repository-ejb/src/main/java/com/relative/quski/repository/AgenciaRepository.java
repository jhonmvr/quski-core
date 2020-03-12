/**
 * 
 */
package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoAgencia;

@Local
public interface AgenciaRepository extends CrudRepository<Long, TbQoAgencia> {
	public List<TbQoAgencia> findById(long id)throws RelativeException;
	}
