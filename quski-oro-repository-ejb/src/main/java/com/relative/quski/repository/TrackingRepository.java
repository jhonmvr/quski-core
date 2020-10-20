package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.wrapper.BusquedaTrackingWrapper;

@Local
public interface TrackingRepository extends CrudRepository<Long, TbQoTracking>{
	
	public TbQoTracking findById(Long id) throws RelativeException;
	
	public Long countTracking(BusquedaTrackingWrapper wp) throws RelativeException;
	
	public List<TbQoTracking> findByParams(BusquedaTrackingWrapper wp) throws RelativeException;
}
