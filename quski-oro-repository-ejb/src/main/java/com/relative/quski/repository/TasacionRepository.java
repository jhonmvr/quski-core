package com.relative.quski.repository;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTasacion;

@Local
public interface TasacionRepository extends CrudRepository<Long, TbQoTasacion>{
	public TbQoTasacion findById(Long id)  throws RelativeException;
	
	public List<TbQoTasacion> findByIdCredito(Long id)  throws RelativeException;
	public List<TbQoTasacion> findByIdCredito(Long id, int page, int pageSize, String order,
			String direction) throws RelativeException;
	public Long countFindByIdCredito(Long idCreditoNegociacion) throws RelativeException;


	public void deleteTasacionByNegociacionId(Long idNegociacion)throws RelativeException;

	
	public List<TbQoTasacion> findByIdNegociacion( Long idNegociacion ) throws RelativeException;
	public Long countFindByIdNegociacion(Long idNegociacion) throws RelativeException;
	public List<TbQoTasacion> findByIdCotizador(Long id)  throws RelativeException;

	public BigDecimal totalAvaluo(Long id)throws RelativeException;

}
