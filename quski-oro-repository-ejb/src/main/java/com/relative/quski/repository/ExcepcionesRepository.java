/**
 * 
 */
package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoExcepcion;
/**
 * 
 * @author Jeroham Cadenas - Developer twelve 
 *
 */
@Local
public interface ExcepcionesRepository extends CrudRepository<Long, TbQoExcepcion> {
	
	public TbQoExcepcion findById(Long id)throws RelativeException;
	
	public List<TbQoExcepcion> findByIdNegociacion( int startRecord, Integer pageSize, String sortFields, String sortDirections, Long idNegociacion ) throws RelativeException;
	public List<TbQoExcepcion> findByIdNegociacion( Long idNegociacion ) throws RelativeException;
	public Long countByIdNegociacion(Long idNegociacion) throws RelativeException ;
	
	public List<TbQoExcepcion> findByIdCliente(int startRecord, Integer pageSize, String sortFields, String sortDirections,  Long idCliente ) throws RelativeException;
	public List<TbQoExcepcion> findByIdCliente( Long idCliente ) throws RelativeException;
	public Long countByIdCliente(Long idCliente) throws RelativeException ;
	
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacion( String tipoExcepcion, Long idNegociacion ) throws RelativeException;
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacion( int startRecord, Integer pageSize, String sortFields, String sortDirections, String tipoExcepcion, Long idNegociacion) throws RelativeException;
	public Long countByTipoExcepcionAndIdNegociacion( String tipoExcepcion, Long idNegociacion ) throws RelativeException ;
	
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacionAndCaracteristica( String tipoExcepcion, Long idNegociacion, String caracteristica ) throws RelativeException;
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacionAndCaracteristica( int startRecord, Integer pageSize, String sortFields, String sortDirections, String tipoExcepcion, Long idNegociacion, String caracteristica) throws RelativeException;
	public Long countByTipoExcepcionAndIdNegociacionAndCaracteristica( String tipoExcepcion, Long idNegociacion, String caracteristica ) throws RelativeException ;
	
	public TbQoExcepcion findByTipoExcepcionAndIdNegociacionAndestadoExcepcion( Long idNegociacion, String tipoExcepcion, EstadoExcepcionEnum estadoExcepcion ) throws RelativeException;

	public void inactivarExcepcionByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion)throws RelativeException ;

	
}
