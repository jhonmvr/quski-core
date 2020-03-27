package com.relative.quski.repository;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.wrapper.AsignacionesWrapper;
import com.relative.quski.wrapper.ListadoOperacionDevueltaWrapper;
@Local
public interface CreditoNegociacionRepository extends CrudRepository<Long, TbQoCreditoNegociacion>{
	

	
	public List<TbQoCreditoNegociacion> findPorCustomFilterCreditos(PaginatedWrapper pw, String fechaDesde, String fechaHasta, String codigoOperacion,
			String proceso, String identificacion,  String agencia) throws RelativeException;
	
	public Long countByParams(Date fechaDesde, Date fechaHasta,String codigoOperacion, EstadoOperacionEnum estado, String identificacion)throws RelativeException;

	public List<ListadoOperacionDevueltaWrapper> listOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia, String proceso, String cedula) throws RelativeException;
	
	
	public Integer countOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia,
			String proceso, String cedula) throws RelativeException;


	public List<AsignacionesWrapper> findAsignacionesByParamsPaginated(PaginatedWrapper pw, String codigoOperacion, String nombreAgencia,
			String nombreProceso, String cedula) throws RelativeException;

}

