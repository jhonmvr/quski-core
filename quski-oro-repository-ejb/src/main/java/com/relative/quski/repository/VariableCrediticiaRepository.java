package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoVariableCrediticia;
 

@Local
public interface VariableCrediticiaRepository extends CrudRepository<Long, TbQoVariableCrediticia> {
	public List<TbQoVariableCrediticia> findByIdCotizador(int startRecord, Integer pageSize, String sortFields,
			String sortDirections,  String idCotizador) throws RelativeException ;

	public List<TbQoVariableCrediticia> findByIdCotizador(String idCotizador) throws RelativeException ;

	public Long countByIdCotizador(String idCotizador) throws RelativeException ;

	public List<TbQoVariableCrediticia> findByIdCotizacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections,  Long idCotizador) throws RelativeException ;
	public List<TbQoVariableCrediticia> findByIdCotizacion(Long idCotizador) throws RelativeException ;

	public Long countfindByIdCotizacion(Long idCotizador) throws RelativeException ;
	
}