package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTipoDocumento;

@Local
public interface TipoDocumentoRepository extends CrudRepository<Long, TbQoTipoDocumento>{

}
