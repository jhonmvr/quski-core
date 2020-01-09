package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoPrecioOro;

@Local
public interface PrecioOroRepository extends CrudRepository<Long, TbQoPrecioOro> {
	public List<TbQoPrecioOro> findByIdCotizador(int startRecord, Integer pageSize, String sortFields,
			String sortDirections,  String idCotizador) throws RelativeException ;

	public List<TbQoPrecioOro> findByIdCotizador(String idCotizador) throws RelativeException ;

	public Long countByIdCotizador(String idCotizador) throws RelativeException ;

}
