package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.Provincia;
@Local
public interface ProvinciaRepository   extends CrudRepository<Long ,Provincia> {

}
