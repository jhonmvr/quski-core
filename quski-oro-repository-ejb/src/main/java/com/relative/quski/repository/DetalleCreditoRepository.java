package com.relative.quski.repository;
import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDetalleCredito;

@Local
public interface DetalleCreditoRepository extends CrudRepository<Long, TbQoDetalleCredito>{
	
	

}
