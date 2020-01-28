package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCatalogo;

@Local
public interface CatalogoRepository extends CrudRepository<Long, TbQoCatalogo> {
	

}
