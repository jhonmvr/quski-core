package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoNegociacionCalculo;

@Local
public interface NegociacionCalculoRepository extends CrudRepository<Long, TbQoNegociacionCalculo> {
	public List<TbQoNegociacionCalculo> findById(long id) throws RelativeException;

}
