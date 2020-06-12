package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.spec.DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec;

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

}
