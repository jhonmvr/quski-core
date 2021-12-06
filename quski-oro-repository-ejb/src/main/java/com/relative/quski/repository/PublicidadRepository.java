package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoPublicidad;

@Local
public interface PublicidadRepository extends CrudRepository<Long, TbQoPublicidad>{
	
	
}
