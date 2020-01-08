package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoNegociacion;


@Local
public interface NegociacionRepository extends CrudRepository<Long, TbQoNegociacion> {

}
