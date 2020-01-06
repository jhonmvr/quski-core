package com.relative.quski.repository.imp;
import javax.ejb.Stateless;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoTipoOro;
import com.relative.quski.repository.TipoOroRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "tipoOroRepository")
public class TipoOroRepositoryImp extends GeneralRepositoryImp<Long, TbQoTipoOro> implements TipoOroRepository {

}
