package com.relative.quski.repository;
import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCotizador;

@Local
public interface CotizadorRepository extends CrudRepository<Long, TbQoCotizador>{

}
