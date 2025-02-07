package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCuentaBancariaCliente;

@Local
public interface CuentaBancariaRepository extends CrudRepository<Long, TbQoCuentaBancariaCliente> {

	public List<TbQoCuentaBancariaCliente> findByIdCliente(Long id) throws RelativeException ;
	public List<TbQoCuentaBancariaCliente> findByAllIdCliente(Long id) throws RelativeException ;
	public TbQoCuentaBancariaCliente findByClienteAndCuenta(Long id, String cuenta) throws RelativeException ;
	public void deleteAllByIdCliente(Long id) throws RelativeException ;

	
	
	
}

