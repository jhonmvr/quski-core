package com.relative.quski.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.wrapper.WrapperMetadatosHabilitante;
import com.relative.quski.wrapper.WrapperMetadatosHabilitanteOperaciones;

@Local
public interface DocumentoHabilitanteRepository extends CrudRepository<Long, TbQoDocumentoHabilitante> {

	public TbQoDocumentoHabilitante findByTipoDocumentoAndCliAndCotAndNeg(Long idTipoDocumento,
			String identificacionCliente, Long idCotizador, Long idNegociacion);

	public TbQoDocumentoHabilitante findByTipoDocumentoAndReferenciaAndProceso(Long idTipoDocumento,
			ProcessEnum proceso, String referencia);
	public TbQoDocumentoHabilitante findByIdCreditoNegociacion(Long idCreditoNegociacion);

	public List<WrapperMetadatosHabilitanteOperaciones> listAllMetadataDocumentNuevosNovaciones() throws RelativeException;

	public List<TbQoDocumentoHabilitante> getByIdNegociacionAndProceso(BigDecimal idNegociacion, List<ProcessEnum> proceso)
			throws RelativeException;

	
	public List<WrapperMetadatosHabilitante> listAllMetadataDocument() throws RelativeException;

	public List<TbQoDocumentoHabilitante> listAllDocument() throws RelativeException;
}
