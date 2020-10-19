package com.relative.quski.repository.imp;

import java.util.List;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.TrackingWrapper;
import com.relative.quski.repository.spec.TrackingByParamsSpec;
import com.relative.quski.wrapper.BusquedaTrackingWrapper;


public class TrackingRepositoryImp extends GeneralRepositoryImp<Long, TbQoTracking> implements TrackingRepository  {

	@Override
	public List<TrackingWrapper> findBusquedaParametros(BusquedaTrackingWrapper wp) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<TrackingWrapper> findByParams(BusquedaTrackingWrapper wp)
			throws RelativeException {
		try {
			List<TbQoTracking> tmp;
			tmp= this.findAllBySpecification(new TrackingByParamsSpec(wp));
			if(tmp != null && !tmp.isEmpty()) {
				return (List<TrackingWrapper>) tmp.get(0);
			}
			
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL INTENTAR BUSCAR EN TB_QO_CLIENTE_PAGO POR ID Y ESTADO");
		}
	}



	@Override
	public Long countTracking(BusquedaTrackingWrapper wp) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
}
