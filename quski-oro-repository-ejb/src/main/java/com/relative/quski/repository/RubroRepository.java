package com.relative.quski.repository;

import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoRubro;


@Local
public interface RubroRepository extends CrudRepository<Long, TbQoRubro>{
	
}
