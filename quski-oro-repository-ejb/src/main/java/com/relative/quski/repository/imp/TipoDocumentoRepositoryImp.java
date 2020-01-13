package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.TipoDocumentoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "TipoDocumentoRepository")
public class TipoDocumentoRepositoryImp extends GeneralRepositoryImp<Long, TbQoTipoDocumento> implements TipoDocumentoRepository {

}
