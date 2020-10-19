package com.relative.quski.repository;

import java.util.List;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.wrapper.BusquedaTrackingWrapper;


public interface TrackingRepository extends CrudRepository<Long, TbQoTracking>{
	
	public TbQoTracking findById(Long id) throws RelativeException;
	
	public Long countTracking(BusquedaTrackingWrapper wp) throws RelativeException;
	
	public List<TrackingWrapper> findBusquedaParametros( BusquedaTrackingWrapper wp ) throws RelativeException;

	List<TrackingWrapper> findByParams(BusquedaTrackingWrapper wp) throws RelativeException;
}
