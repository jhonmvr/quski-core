package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.wrapper.BusquedaDevolucionWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

@Local
public interface DevolucionRepository extends CrudRepository<Long, TbQoDevolucion>{
	
	
	public List<DevolucionProcesoWrapper> findOperaciones(BusquedaDevolucionWrapper  bdw) throws RelativeException;


	public Integer countOperaciones(BusquedaDevolucionWrapper  bdw) throws RelativeException;
	

}
