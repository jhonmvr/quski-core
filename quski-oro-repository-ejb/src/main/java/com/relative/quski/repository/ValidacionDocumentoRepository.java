package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoValidacionDocumento;
@Local
public interface ValidacionDocumentoRepository extends CrudRepository<Long, TbQoValidacionDocumento> {

	void deleteAllByIdCredito(Long id) throws RelativeException;

	List<TbQoValidacionDocumento> findAllByIdNegociacion(Long idNegociacion) throws RelativeException;

}
