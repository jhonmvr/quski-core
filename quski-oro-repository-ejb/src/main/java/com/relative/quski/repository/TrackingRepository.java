package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.model.TbQoTracking;

@Local
public interface TrackingRepository extends CrudRepository<Long, TbQoTracking>{
	public List<TbQoTracking>  findByParams(PaginatedWrapper pw, String proceso,
			String actividad, String seccion, String codigoBPM, String codigoOperacionSoftbank, String fechaCreacion,
			String fechaAprobacion, String usuario) throws RelativeException;
	
	
	public Long countByParams(String proceso,
			String actividad, String seccion, String codigoBPM, String codigoOperacionSoftbank, String fechaCreacion,
			String fechaAprobacion, String usuario) throws RelativeException;


	TbQoTracking findTrackingByProceso(String proceso) throws RelativeException;
	
	

}
