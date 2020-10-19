package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTracking;

@Local
public interface TrackingRepository extends CrudRepository<Long, TbQoTracking>{

}
