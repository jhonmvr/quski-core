package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoArchivoCliente;

@Local
public interface ArchivoClienteRepository extends CrudRepository<Long, TbQoArchivoCliente>{
	
	 

}
