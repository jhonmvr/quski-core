package com.relative.quski.repository.imp;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.spec.DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec;
import com.relative.quski.repository.spec.DocumentoByTipoDocumentoAndProRefEstOpSpec;
import com.relative.quski.repository.spec.DocumentoHabilitanteByIdNegociacionAndProceso;
import com.relative.quski.repository.spec.DocumentoSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.WrapperMetadatosHabilitante;
import com.relative.quski.wrapper.WrapperMetadatosHabilitanteOperaciones;

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
			ProcessEnum proceso, String referencia) {
		List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(
				(new DocumentoByTipoDocumentoAndProRefEstOpSpec(idTipoDocumento, referencia, proceso, null)));
		if (tmp != null && !tmp.isEmpty()) {
			return tmp.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<TbQoDocumentoHabilitante> findByProcesoAndReferenciaAndEstadoProceso(List<ProcessEnum> proceso, String referencia, List<EstadoOperacionEnum> estadoOperacion) {
		List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(
				(new DocumentoByTipoDocumentoAndProRefEstOpSpec(proceso, referencia, estadoOperacion)));
		if (tmp != null && !tmp.isEmpty()) {
			return tmp;
		} else {
			return null;
		}
	}

	@Override
	public List<TbQoDocumentoHabilitante> findByEstadoProceso(List<EstadoOperacionEnum> estadoOperacion) {
		List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(
				(new DocumentoByTipoDocumentoAndProRefEstOpSpec( estadoOperacion)));
		if (tmp != null && !tmp.isEmpty()) {
			return tmp;
		} else {
			return null;
		}
	}
	@Override
	public TbQoDocumentoHabilitante findByIdCreditoNegociacion(Long idCreditoNegociacion) {
		// List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(new DocumentoById(idCreditoNegociacion));
		return null;
	}
	
	
	@Override
	public List<TbQoDocumentoHabilitante> listAllDocument() throws RelativeException {
		List<TbQoDocumentoHabilitante> list = this.findAllBySpecification( new DocumentoSpec() );
		return list;
	}
	/** @Override
	public List<WrapperMetadatosHabilitante> listAllMetadataDocument() throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<WrapperMetadatosHabilitante> query = cb.createQuery(WrapperMetadatosHabilitante.class);
			// ~~> FROM
			Root<TbQoDocumentoHabilitante> poll = query.from(TbQoDocumentoHabilitante.class);
			// ~~> SELECT
			query.multiselect(
					poll.get("id"), 
					poll.get("proceso"), 
					poll.get("id_referencia"), 
					poll.get("object_id"), 
					poll.get("fecha_actualizacion"), 
					poll.get("fecha_creacion"), 
					poll.get("nombre_archivo"), 
					poll.get("tipo_ducumento"), 
					poll.get("id_tipo_documento"));
			
			// ~~> EJECUTAR CONSULTA
			TypedQuery<WrapperMetadatosHabilitante> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	} **/
	@SuppressWarnings("unchecked")
	@Override
	public List<WrapperMetadatosHabilitante> listAllMetadataDocument() throws RelativeException {
		try {
			String querySelect = "select id, coalesce(proceso, 'NO APLICA') as proceso, coalesce(id_referencia, 'NO APLICA') as id_referencia, coalesce(object_id, 'NO APLICA')  as object_id, coalesce(nombre_archivo, 'NO APLICA') as nombre_archivo, coalesce(tipo_ducumento, 'NO APLICA') as tipo_ducumento, id_tipo_documento from tb_qo_documento_habilitante where 1=1 ";
			Query query = this.getEntityManager().createNativeQuery(querySelect);
			return QuskiOroUtil.getResultList(query.getResultList(), WrapperMetadatosHabilitante.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, " Error en listAllMetadataDocument imp: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WrapperMetadatosHabilitanteOperaciones> listAllMetadataDocumentNuevosNovaciones() throws RelativeException {
		try {
			String querySelect = " select "
					+ "	h.id, "
					+ "	coalesce(proceso, 'NO APLICA') as proceso, "
					+ "	coalesce(id_referencia, 'NO APLICA') as id_referencia, "
					+ "	coalesce(object_id, 'NO APLICA')  as object_id, "
					+ "	coalesce(nombre_archivo, 'NO APLICA') as nombre_archivo, "
					+ "	coalesce(tipo_ducumento, 'NO APLICA') as tipo_ducumento, "
					+ "	id_tipo_documento, "
					+ "	t.codigo "
					+ " from tb_qo_documento_habilitante h "
					+ " join tb_qo_credito_negociacion t on  TO_NUMBER( h.id_referencia,'999999') = t.id_negociacion  "
					+ " where h.proceso = 'NUEVO' or h.proceso = 'NOVACION' ";
			Query query = this.getEntityManager().createNativeQuery(querySelect);
			return QuskiOroUtil.getResultList(query.getResultList(), WrapperMetadatosHabilitanteOperaciones.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, " Error en listAllMetadataDocument imp: " + e.getMessage());
		}
	}
	@Override
	public List<TbQoDocumentoHabilitante> getByIdNegociacionAndProceso(BigDecimal idNegociacion, List<ProcessEnum> proceso) throws RelativeException{
		try {
			List<TbQoDocumentoHabilitante> tmp = this.findAllBySpecification(new DocumentoHabilitanteByIdNegociacionAndProceso(String.valueOf(idNegociacion),proceso));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL LEER DOCUMENTO HABILITANTE POR IDREFERENCIA Y PROCESO");
		}
	}



}
