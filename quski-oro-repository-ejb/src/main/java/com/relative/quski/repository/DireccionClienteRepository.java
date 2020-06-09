package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDireccionCliente;

@Local
public interface DireccionClienteRepository extends CrudRepository<Long, TbQoDireccionCliente> {

	List<TbQoDireccionCliente> findByIdCliente(Long id) throws RelativeException ;

	List<TbQoDireccionCliente> findByIdClienteAndTipoDireccion(Long idC, String tipoDireccion) throws RelativeException;
	
}

