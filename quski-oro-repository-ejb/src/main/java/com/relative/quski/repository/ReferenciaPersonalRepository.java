package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoReferenciaPersonal;

@Local
public interface ReferenciaPersonalRepository extends CrudRepository<Long, TbQoReferenciaPersonal>  {

}
