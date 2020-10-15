package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;

@Local
public interface ProcesoRepository extends CrudRepository<Long, TbQoProceso> {
	public TbQoProceso findById(Long id) throws RelativeException;
	
	public List<TbQoProceso> findProcesosByAsesor(String asesor) throws RelativeException;

	public Long countOperaciones(BusquedaOperacionesWrapper wp) throws RelativeException;

	public List<OperacionesWrapper> findOperacionJoinNegociacion( BusquedaOperacionesWrapper wp ) throws RelativeException;
}
