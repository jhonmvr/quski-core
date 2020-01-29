package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCatalogo;

@Local
public interface CatalogoRepository extends CrudRepository<Long, TbQoCatalogo> {
	public List<TbQoCatalogo> findByNombreCatalogo(int startRecord, Integer pageSize, String sortFields,
			String sortDirections,  String nombre) throws RelativeException ;

	public List<TbQoCatalogo> findByNombreCatalogo(String nombre) throws RelativeException ;

	public Long countByNombreCatalogo(String nombre) throws RelativeException ;

	

}
