package com.relative.quski.repository;
import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCalculoNegociacion;
@Local
public interface CalculoNegociacionRepository extends CrudRepository<Long, TbQoCalculoNegociacion>{

}
