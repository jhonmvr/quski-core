package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.wrapper.TrackingWrapper;

@Local
public interface TrackingRepository extends CrudRepository<Long, TbQoTracking>{
	
	
	public List<TbQoTracking> findByParams(TrackingWrapper wp, int strat, Integer size, String sort, String direction) throws RelativeException;

	public Long countTracking(TrackingWrapper wp) throws RelativeException;

	public List<TbQoTracking> findByParams(TrackingWrapper wp)throws RelativeException;

	public List<ProcesoEnum> findListProcesos()throws RelativeException;

	public List<ActividadEnum> findListActividadByProceso(ProcesoEnum proceso)throws RelativeException;

	public List<SeccionEnum> findListSeccionByActividad(ActividadEnum actividad)throws RelativeException;

	
	
}
