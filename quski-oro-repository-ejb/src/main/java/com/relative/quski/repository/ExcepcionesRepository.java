/**
 * 
 */
package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoExcepcione;
/**
 * 
 * @author Jeroham Cadenas - Developer twelve 
 *
 */
@Local
public interface ExcepcionesRepository extends CrudRepository<Long, TbQoExcepcione> {
	
	public TbQoExcepcione findById(Long id)throws RelativeException;
	
	public List<TbQoExcepcione> findByIdNegociacion( Long idNegociacion ) throws RelativeException;
	
	public List<TbQoExcepcione> findByIdCliente( Long idCliente ) throws RelativeException;
	
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacion( String tipoExcepcion, Long idNegociacion) throws RelativeException;
	
	public TbQoExcepcione findByTipoExcepcionAndIdNegociacionAndestadoExcepcion( String tipoExcepcion, Long idNegociacion, String estadoExcepcion ) throws RelativeException;
	
}
