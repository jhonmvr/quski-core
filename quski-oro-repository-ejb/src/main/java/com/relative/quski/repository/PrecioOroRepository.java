package com.relative.quski.repository;
import javax.ejb.Local;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoPrecioOro;

@Local
public interface PrecioOroRepository extends CrudRepository<Long, TbQoPrecioOro> {

}
