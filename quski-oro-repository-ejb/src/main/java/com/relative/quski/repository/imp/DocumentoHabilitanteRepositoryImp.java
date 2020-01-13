package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.repository.DocumentoHabilitanteRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "documentoHabilitanteRepository")
public class DocumentoHabilitanteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDocumentoHabilitante> implements DocumentoHabilitanteRepository {

}
