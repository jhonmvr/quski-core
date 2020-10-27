package com.relative.quski.repository;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoDevolucion;

@Local
public interface DevolucionRepository extends CrudRepository<Long, TbQoDevolucion>{


	
	

}
