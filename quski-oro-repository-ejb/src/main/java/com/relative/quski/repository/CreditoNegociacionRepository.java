package com.relative.quski.repository;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.wrapper.AsignacionesWrapper;
@Local
public interface CreditoNegociacionRepository extends CrudRepository<Long, TbQoCreditoNegociacion>{
	

	
	public List<TbQoCreditoNegociacion> findPorCustomFilterCreditos(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String codigoOperacion, ProcesoEnum proceso, String identificacion, Long agencia, String cliente,
			EstadoEnum estado) throws RelativeException;
	
	public Long countByParams(Date fechaDesde, Date fechaHasta, EstadoOperacionEnum estado, String identificacion)throws RelativeException;

	//public List<ListadoOperacionDevueltaWrapper> listOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia, String cedula) throws RelativeException;
	
	public Integer countOperacionesDevueltas(PaginatedWrapper pw, String agencia,
			 String cedula) throws RelativeException;


	public List<AsignacionesWrapper> findAsignacionesByParamsPaginated(PaginatedWrapper pw, String nombreAgencia,
		 String cedula) throws RelativeException;
	//public List<TbQoCreditoNegociacion> findBycodigOpEstado(String codigOp, EstadoOperacionEnum estado, int startRecord,
	//		Integer pageSize, String sortFields, String sortDirections)throws RelativeException;
	//public List<TbQoCreditoNegociacion> findBycodigOpEstado(String codigOp, EstadoOperacionEnum estado)throws RelativeException;
	//public Long countfindBycodigOpEstado(String codigOp, EstadoOperacionEnum estado)throws RelativeException;

}

