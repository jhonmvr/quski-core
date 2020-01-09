package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoVariableCrediticia;
 

@Local
public interface VariableCrediticiaRepository extends CrudRepository<Long, TbQoVariableCrediticia> {
}