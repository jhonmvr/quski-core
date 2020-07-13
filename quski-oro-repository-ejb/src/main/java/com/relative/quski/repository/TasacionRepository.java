package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTasacion;

@Local
public interface TasacionRepository extends CrudRepository<Long, TbQoTasacion>{
	public List<TbQoTasacion> findByIdCreditoNegociacion(Long idCreditoNegociacion)  throws RelativeException;
	public List<TbQoTasacion> findByIdCreditoNegociacionPaged(Long idCreditoNegociacion, int page, int pageSize, String order,
			String direction) throws RelativeException;
	
	public Long countFindByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException;
}
