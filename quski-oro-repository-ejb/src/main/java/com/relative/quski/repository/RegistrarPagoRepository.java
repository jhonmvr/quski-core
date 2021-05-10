package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoRegistrarPago;



@Local
public interface RegistrarPagoRepository extends CrudRepository<Long, TbQoRegistrarPago> {

	List<TbQoRegistrarPago> findByIdClientePago(Long idClientePago) throws RelativeException;
	List<TbQoRegistrarPago> findByIdCredito(Long idCredito) throws RelativeException;
	void borrarPagos(Long id)throws RelativeException;


}
