package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoPatrimonio;

@Local
public interface PatrimonioRepository extends CrudRepository<Long, TbQoPatrimonio>{
	//public List<TbQoCotizador> findByCliente(int startRecord, Integer pageSize, String sortFields,
//			String sortDirections,  String idCotizador) throws RelativeException ;
//	public List<TbQoCotizador> findByCliente(String idCotizador) throws RelativeException ;

//	public Long countByCliente(String idCotizador) throws RelativeException ;
	
	
	
	
	
}
