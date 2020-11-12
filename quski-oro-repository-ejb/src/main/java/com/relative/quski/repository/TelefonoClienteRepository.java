package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTelefonoCliente;

@Local
public interface TelefonoClienteRepository extends CrudRepository<Long, TbQoTelefonoCliente> {

	public List<TbQoTelefonoCliente> findByIdCliente(Long id) throws RelativeException ;

	
	
	
}

