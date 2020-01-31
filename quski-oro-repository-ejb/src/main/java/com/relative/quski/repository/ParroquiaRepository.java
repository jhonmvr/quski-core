package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.Parroquia;
@Local
public interface ParroquiaRepository extends CrudRepository<Long, Parroquia> {

	public List<Parroquia> findByCantonProvincia(String nombre)throws RelativeException;
}
