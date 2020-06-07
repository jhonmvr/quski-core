package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoPatrimonio;

@Local
public interface PatrimonioRepository extends CrudRepository<Long, TbQoPatrimonio>{
	
}
