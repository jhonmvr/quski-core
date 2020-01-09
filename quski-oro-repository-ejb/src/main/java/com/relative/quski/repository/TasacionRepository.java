package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTasacion;

@Local
public interface TasacionRepository extends CrudRepository<Long, TbQoTasacion>{

}
