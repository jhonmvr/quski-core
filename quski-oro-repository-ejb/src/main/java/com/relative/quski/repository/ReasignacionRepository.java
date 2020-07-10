package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoReasignacionActividad;
@Local
public interface ReasignacionRepository extends CrudRepository<Long, TbQoReasignacionActividad> {
	
	
	public List<TbQoReasignacionActividad> findBycodigoReasignacion(String id, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException;

	public Long countByParamPaged(String id) throws RelativeException;

}
