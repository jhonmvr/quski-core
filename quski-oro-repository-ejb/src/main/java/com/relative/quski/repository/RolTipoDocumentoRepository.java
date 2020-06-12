package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoRolTipoDocumento;

@Local
public interface RolTipoDocumentoRepository  extends CrudRepository<Long, TbQoRolTipoDocumento>{

}
