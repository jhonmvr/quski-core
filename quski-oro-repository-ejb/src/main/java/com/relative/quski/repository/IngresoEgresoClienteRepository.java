package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoIngresoEgresoCliente;

@Local
public interface IngresoEgresoClienteRepository extends CrudRepository<Long, TbQoIngresoEgresoCliente> {
	
}

