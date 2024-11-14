package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoTipoDocumento;



public class DocumentoByTipoDocumentoAndProRefEstOpSpec  extends AbstractSpecification<TbQoDocumentoHabilitante> {
	
	
	private Long idTipoDocumento;
	private String idReferencia;
	private ProcessEnum proceso;
	private List<ProcessEnum> procesoEnumList;
 	private EstadoOperacionEnum estadoOperacion;
 	private List<EstadoOperacionEnum> estadoOperacionEnumList;
 

	public DocumentoByTipoDocumentoAndProRefEstOpSpec(Long idTipoDocumento, String idReferencia, ProcessEnum proceso,
		EstadoOperacionEnum estadoOperacion) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idReferencia = idReferencia;
		this.proceso = proceso;
		this.estadoOperacion = estadoOperacion;
	}

	public DocumentoByTipoDocumentoAndProRefEstOpSpec() {}

	public DocumentoByTipoDocumentoAndProRefEstOpSpec(List<EstadoOperacionEnum> estadoOperacionEnumList) {
		this.estadoOperacionEnumList = estadoOperacionEnumList;
	}

	public DocumentoByTipoDocumentoAndProRefEstOpSpec(List<ProcessEnum> procesoEnumList, String idReferencia, List<EstadoOperacionEnum> estadoOperacionEnumList) {
		this.procesoEnumList = procesoEnumList;
		this.idReferencia = idReferencia;
		this.estadoOperacionEnumList = estadoOperacionEnumList;

	}

	@Override
		public boolean isSatisfiedBy(TbQoDocumentoHabilitante arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoDocumentoHabilitante> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if(StringUtils.isNotBlank(idReferencia)) {
				where.add(cb.equal(poll.<Long>get("idReferencia"), this.idReferencia ));
			}
			if(idTipoDocumento != null) {
				where.add(cb.equal(poll.<TbQoTipoDocumento>get("tbQoTipoDocumento").<Long>get("id"),idTipoDocumento ));
			}
			if(this.proceso != null) {
				where.add(cb.equal(poll.<ProcessEnum>get("proceso"), proceso));
			}
			if(this.estadoOperacion != null) {
				where.add(cb.equal(poll.<EstadoOperacionEnum>get("estadoOperacion"), estadoOperacion));
			}
			if (estadoOperacionEnumList != null && !estadoOperacionEnumList.isEmpty()) {
				where.add(poll.<EstadoOperacionEnum>get("estadoOperacion").in(estadoOperacionEnumList));
			}
			if (estadoOperacionEnumList != null && !estadoOperacionEnumList.isEmpty()) {
				where.add(poll.<ProcessEnum>get("proceso").in(estadoOperacionEnumList));
			}
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
