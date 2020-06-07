package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDireccionCliente;

@Local
public interface DireccionClienteRepository extends CrudRepository<Long, TbQoDireccionCliente> {
	
}

