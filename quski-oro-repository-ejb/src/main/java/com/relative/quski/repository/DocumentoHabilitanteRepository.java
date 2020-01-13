package com.relative.quski.repository;
import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoDocumentoHabilitante;

@Local
public interface DocumentoHabilitanteRepository extends CrudRepository<Long, TbQoDocumentoHabilitante>{

}
