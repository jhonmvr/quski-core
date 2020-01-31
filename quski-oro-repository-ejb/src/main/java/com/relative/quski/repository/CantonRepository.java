package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.Canton;

@Local
public interface CantonRepository extends CrudRepository<Long, Canton> {

	public List<Canton> findByProvincia(String provincia, String order)throws RelativeException;
}
