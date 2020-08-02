package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDetalleCredito;

@Local
public interface DetalleCreditoRepository extends CrudRepository<Long, TbQoDetalleCredito>{

	List<TbQoDetalleCredito> findDetalleCreditoByIdCotizador(Long idCotizador) throws RelativeException;

	List<TbQoDetalleCredito> findDetalleCreditoByIdCotizador(Long idCotizador, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException;

	Long countByIdCotizador(Long idCotizador) throws RelativeException;
	
	

}
