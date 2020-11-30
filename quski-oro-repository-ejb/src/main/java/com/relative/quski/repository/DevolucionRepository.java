package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.wrapper.BusquedaDevolucionWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

@Local
public interface DevolucionRepository extends CrudRepository<Long, TbQoDevolucion>{
	
	
	public List<DevolucionProcesoWrapper> findOperaciones(BusquedaDevolucionWrapper  bdw) throws RelativeException;


	public Integer countOperaciones(BusquedaDevolucionWrapper  bdw) throws RelativeException;
	
	public List<DevolucionProcesoWrapper> findOperacionesArribos(BusquedaDevolucionWrapper  bdw) throws RelativeException;


	public Integer countOperacionesArribos(BusquedaDevolucionWrapper  bdw) throws RelativeException;
	

}
