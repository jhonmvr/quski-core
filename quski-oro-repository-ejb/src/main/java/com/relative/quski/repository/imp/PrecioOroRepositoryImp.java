package com.relative.quski.repository.imp;
import javax.ejb.Stateless;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.repository.PrecioOroRepository;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "precioOroRepository")
public class PrecioOroRepositoryImp extends GeneralRepositoryImp<Long, TbQoPrecioOro> implements PrecioOroRepository {

}
