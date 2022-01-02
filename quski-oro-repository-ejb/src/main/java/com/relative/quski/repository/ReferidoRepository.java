package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoReferido;

@Local
public interface ReferidoRepository extends CrudRepository<Long, TbQoReferido>{

	public TbQoReferido findByIdNegociacion(Long id) throws RelativeException;
	
	
}
