package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoTipoArchivo;
import com.relative.quski.repository.TipoArchivoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "TipoDocumentoRepository")
public class TipoArchivoRepositoryImp extends GeneralRepositoryImp<Long, TbQoTipoArchivo> implements TipoArchivoRepository {

}
