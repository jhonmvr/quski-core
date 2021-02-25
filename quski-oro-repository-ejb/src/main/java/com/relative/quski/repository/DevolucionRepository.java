package com.relative.quski.repository;

import java.util.List;
import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

@Local
public interface DevolucionRepository extends CrudRepository<Long, TbQoDevolucion>{
	
	
	public List<DevolucionProcesoWrapper> findOperaciones(PaginatedWrapper pw, String 
			codigoOperacion, String agencia, String fechaAprobacionDesde, String fechaAprobacionHasta,
			String identificacion) throws RelativeException;


	public Integer countOperaciones(String 
			codigoOperacion, String agencia, String fechaAprobacionDesde, String fechaAprobacionHasta,
			String identificacion) throws RelativeException;
	
	public List<DevolucionPendienteArribosWrapper> findOperacionArribo(PaginatedWrapper pw, String 
			codigoOperacion, String agencia, EstadoProcesoEnum estado) throws RelativeException;


	public Integer countOperacionArribo(String codigoOperacion, String agencia, EstadoProcesoEnum estado) throws RelativeException;
	

}
