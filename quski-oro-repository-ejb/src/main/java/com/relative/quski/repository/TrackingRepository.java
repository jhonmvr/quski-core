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
import com.relative.quski.wrapper.TrakingProcesoWrapper;

@Local
public interface TrackingRepository extends CrudRepository<Long, TbQoTracking>{
	
	
	public List<TbQoTracking> findByParams(TrackingWrapper wp, int strat, Integer size, String sort, String direction) throws RelativeException;

	public Long countTracking(TrackingWrapper wp) throws RelativeException;

	public List<TbQoTracking> findByParams(TrackingWrapper wp)throws RelativeException;

	public List<ProcesoEnum> findListProcesos()throws RelativeException;

	public List<ActividadEnum> findListActividadByProceso(ProcesoEnum proceso)throws RelativeException;

	public List<SeccionEnum> findListSeccionByActividad(ActividadEnum actividad)throws RelativeException;

	public Long countByParamPaged(TrackingWrapper wp)throws RelativeException;

	public TbQoTracking findByParams(String codigoBpm, ProcesoEnum proceso)throws RelativeException;

	public List<String> getActividad(List<ProcesoEnum> proceso) throws RelativeException;
	

	public List<TrakingProcesoWrapper> trakingByCodigoBpm(String codigoBpm, Integer startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

	public Long trakingByCodigoBpm(String codigoBpm) throws RelativeException;

	public List<TrakingProcesoWrapper> trakingActividadByCodigoBpm(String codigoBpm, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

	public Long trakingActividadByCodigoBpm(String codigoBpm) throws RelativeException;

	public Long trakingSeccionByCodigoBpm(String codigoBpm) throws RelativeException;

	public List<TrakingProcesoWrapper> trakingSeccionByCodigoBpm(String codigoBpm, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

	public List<TrakingProcesoWrapper> trakingSeccionConsolidadoByCodigoBpm(String codigoBpm, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException;

	public Long trakingSeccionConsolidadoByCodigoBpm(String codigoBpm) throws RelativeException;

	public List<TrakingProcesoWrapper> trakingAreaByCodigoBpm(String codigoBpm, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

	public Long trakingAreaByCodigoBpm(String codigoBpm) throws RelativeException;

	public TbQoTracking verActividad(String codigoBpm) throws RelativeException;

	
	
}
