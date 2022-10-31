package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.wrapper.ListadoOperacionIdNegociacionWrapper;


@Local
public interface NegociacionRepository extends CrudRepository<Long, TbQoNegociacion> {
	
	public TbQoNegociacion findById(Long id) throws RelativeException;

	public List<ListadoOperacionIdNegociacionWrapper> traerListaOperaciones() throws RelativeException;
}
