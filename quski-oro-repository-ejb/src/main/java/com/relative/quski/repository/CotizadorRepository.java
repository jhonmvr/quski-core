package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCotizador;

@Local
public interface CotizadorRepository extends CrudRepository<Long, TbQoCotizador>{
	public List<TbQoCotizador> findByCliente(int startRecord, Integer pageSize, String sortFields,
			String sortDirections,  String cedulaCliente) throws RelativeException ;
	public List<TbQoCotizador> findByCliente(String cedulaCliente) throws RelativeException ;

	public Long countByCliente(String cedulaCliente) throws RelativeException ;
	
	
	
	
	
}
