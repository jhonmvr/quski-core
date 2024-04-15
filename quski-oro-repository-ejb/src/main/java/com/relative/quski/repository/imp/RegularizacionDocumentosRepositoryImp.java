package com.relative.quski.repository.imp;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.repository.ExcepcionOperativaRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;

import javax.ejb.Stateless;

@Stateless(mappedName = "regularizacionDocumentosRepository")
public class RegularizacionDocumentosRepositoryImp extends GeneralRepositoryImp<Long, TbQoRegularizacionDocumento> implements RegularizacionDocumentosRepository {
}
