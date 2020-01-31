package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTipoArchivo;

@Local
public interface TipoArchivoRepository extends CrudRepository<Long, TbQoTipoArchivo>{

}
