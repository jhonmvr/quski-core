package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.spec.DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec;
import com.relative.quski.repository.spec.DocumentoByTipoDocumentoAndProRefEstOpSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "documentoHabilitanteRepository")
public class DocumentoHabilitanteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDocumentoHabilitante>
		implements DocumentoHabilitanteRepository {

	public TbQoDocumentoHabilitante findByTipoDocumentoAndCliAndCotAndNeg(Long idTipoDocumento,
			String identificacionCliente, Long idCotizador, Long idNegociacion) {
		List<TbQoDocumentoHabilitante> tmp = this
				.findAllBySpecification((new DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec(idTipoDocumento,
						identificacionCliente, idCotizador, idNegociacion)));
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		} else {
			return null;
		}
	}

	@Override
	public TbQoDocumentoHabilitante findByTipoDocumentoAndReferenciaAndProceso(Long idTipoDocumento,
			ProcessEnum proceso, Long referencia) {
		List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(
				(new DocumentoByTipoDocumentoAndProRefEstOpSpec(idTipoDocumento, referencia, proceso, null)));
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		} else {
			return null;
		}
	}

	@Override
	public TbQoDocumentoHabilitante findByIdCreditoNegociacion(Long idCreditoNegociacion) {
		// List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(new DocumentoById(idCreditoNegociacion));
		return null;
	}

}
