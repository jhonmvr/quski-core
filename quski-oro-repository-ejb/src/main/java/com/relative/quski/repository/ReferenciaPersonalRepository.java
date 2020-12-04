package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoReferenciaPersonal;

@Local
public interface ReferenciaPersonalRepository extends CrudRepository<Long, TbQoReferenciaPersonal>  {
	
	public List<TbQoReferenciaPersonal> findByIdCliente(Long id) throws RelativeException;
	
	public List<TbQoReferenciaPersonal> findAllByIdCliente(Long id) throws RelativeException;

}
