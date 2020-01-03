package com.relative.quski.repository;
import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoTipoOro;

@Local
public interface TipoOroRepository  extends CrudRepository<Long, TbQoTipoOro>{

}
