package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCreditoNegociacion;
@Local
public interface CreditoNegociacionRepository extends CrudRepository<Long, TbQoCreditoNegociacion>{

	List<TbQoCreditoNegociacion> findByParams(String codigoProceso, String nombreAgencia, String nombreProceso,
			String cedula)throws RelativeException;

}
