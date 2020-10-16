package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.TrackingRepository;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "trackingRepository")
public class TrackingRepositoryImp extends GeneralRepositoryImp<Long, TbQoTracking> implements TrackingRepository  {

	@Override
	public List<TbQoTracking> findByParams(PaginatedWrapper pw, String proceso, String actividad, String seccion,
			String codigoBPM, String codigoOperacionSoftbank, String fechaCreacion, String fechaAprobacion,
			String usuario) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countByParams(String proceso, String actividad, String seccion, String codigoBPM,
			String codigoOperacionSoftbank, String fechaCreacion, String fechaAprobacion, String usuario)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TbQoTracking findTrackingByProceso(String proceso) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}


   
}
