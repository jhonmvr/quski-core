/**
 * 
 */
package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoRiesgoAcumulado;
/**
 * 
 * @author Jeroham Cadenas - Developer twelve 
 *
 */
@Local
public interface RiesgoAcumuladoRepository extends CrudRepository<Long, TbQoRiesgoAcumulado> {
	
	public TbQoRiesgoAcumulado findById( Long id )throws RelativeException;
	
	public List<TbQoRiesgoAcumulado> findByIdCliente( Long idCliente ) throws RelativeException;
	public List<TbQoRiesgoAcumulado> findByIdCliente(Long idCliente, int startRecord, Integer pageSize,String sortFields, String sortDirections) throws RelativeException;
	public Long countByIdCotizador(Long idCliente) throws RelativeException;

	
}
