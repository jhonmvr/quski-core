package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;

@Local
public interface DocumentoHabilitanteRepository extends CrudRepository<Long, TbQoDocumentoHabilitante> {

	public TbQoDocumentoHabilitante findByTipoDocumentoAndCliAndCotAndNeg(Long idTipoDocumento,
			String identificacionCliente, Long idCotizador, Long idNegociacion);

	public TbQoDocumentoHabilitante findByTipoDocumentoAndReferenciaAndProceso(Long idTipoDocumento,
			ProcessEnum proceso, String referencia);
	public TbQoDocumentoHabilitante findByIdCreditoNegociacion(Long idCreditoNegociacion);
}
