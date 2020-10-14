/**
 * 
 */
package com.relative.quski.repository;

import java.util.List;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.wrapper.ExcepcionRolWrapper;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public interface ExcepcionRolRepository extends CrudRepository<Long, TbQoExcepcionRol> {
	public TbQoExcepcionRol findById(Long id)throws RelativeException;
		
	public List<ExcepcionRolWrapper> findByRolAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String rol, String identificacion) throws RelativeException ;

	public List<ExcepcionRolWrapper> findByRolAndIdentificacion(String rol, String identificacion) throws RelativeException ;

	public Integer countByRolAndIdentificacion(String rol, String identificacion) throws RelativeException ;


}
