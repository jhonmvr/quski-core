package com.relative.quski.repository.imp;
import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoRolTipoDocumento;
import com.relative.quski.repository.RolTipoDocumentoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "rolTipoDocumentoRepository")
public class RolTipoDocumentoRepositoryImp extends GeneralRepositoryImp<Long, TbQoRolTipoDocumento> implements RolTipoDocumentoRepository {

}
