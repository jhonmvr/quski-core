package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.TrackingRepository;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "trackingRepository")
public class TrackingRepositoryImp extends GeneralRepositoryImp<Long, TbQoTracking> implements TrackingRepository  {

    /**
     * Default constructor. 
     */
    public TrackingRepositoryImp() {
    }
   
}
