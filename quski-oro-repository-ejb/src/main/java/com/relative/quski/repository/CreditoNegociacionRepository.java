package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCreditoNegociacion;
@Local
public interface CreditoNegociacionRepository extends CrudRepository<Long, TbQoCreditoNegociacion>{

	List<TbQoCreditoNegociacion> findAsignacionByParams(String codigoOperacion, String nombreAgencia, String nombreProceso,
			int cedula);

}
