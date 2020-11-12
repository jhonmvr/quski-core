package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoRubro;


@Local
public interface RubroRepository extends CrudRepository<Long, TbQoRubro>{
	
	List<TbQoRubro> findByIdCredito( Long id) throws RelativeException; 
}
