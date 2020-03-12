package com.relative.quski.repository;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
@Local
public interface CreditoNegociacionRepository extends CrudRepository<Long, TbQoCreditoNegociacion>{
	
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,EstadoEnum estado,
	String identificacion,int startRecord, Integer pageSize, String sortFields, String sortDirections)throws RelativeException;
	
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,EstadoOperacionEnum estado,
	String identificacion) throws RelativeException;
	
	public List<TbQoCreditoNegociacion> findPorCustomFilterContratos(PaginatedWrapper pw, Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoOperacionEnum estado,
			String identificacion) throws RelativeException ;
	
	
	public Long countByParams(Date fechaDesde, Date fechaHasta,String codigoOperacion, EstadoOperacionEnum estado, String identificacion)throws RelativeException;

	

}

