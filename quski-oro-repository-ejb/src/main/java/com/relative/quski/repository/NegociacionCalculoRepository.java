package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoNegociacionCalculo;

@Local
public interface NegociacionCalculoRepository extends CrudRepository<Long, TbQoNegociacionCalculo>{

}
