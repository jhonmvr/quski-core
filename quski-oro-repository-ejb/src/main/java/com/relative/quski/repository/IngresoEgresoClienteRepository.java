package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoIngresoEgresoCliente;


@Local
public interface IngresoEgresoClienteRepository extends CrudRepository<Long, TbQoIngresoEgresoCliente> {
	
	public List<TbQoIngresoEgresoCliente> findByIdCliente(Long id) throws RelativeException;
	
}

