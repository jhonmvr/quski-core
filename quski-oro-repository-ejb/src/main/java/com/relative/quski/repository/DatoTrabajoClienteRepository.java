package com.relative.quski.repository;


import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDatoTrabajoCliente;

@Local
public interface DatoTrabajoClienteRepository extends CrudRepository<Long, TbQoDatoTrabajoCliente> {

	public List<TbQoDatoTrabajoCliente> findByIdCliente(Long id) throws RelativeException ;
	public TbQoDatoTrabajoCliente findByIdSoftbank(Long id) throws RelativeException ;

	public List<TbQoDatoTrabajoCliente> findAllByIdCliente(Long id) throws RelativeException ;

	
	
	
}

