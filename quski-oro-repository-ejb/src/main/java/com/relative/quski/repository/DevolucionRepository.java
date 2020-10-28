package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDevolucion;

@Local
public interface DevolucionRepository extends CrudRepository<Long, TbQoDevolucion>{


	
	

}
