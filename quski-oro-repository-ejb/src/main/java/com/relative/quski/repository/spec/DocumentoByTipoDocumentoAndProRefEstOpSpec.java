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
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoTipoDocumento;



public class DocumentoByTipoDocumentoAndProRefEstOpSpec  extends AbstractSpecification<TbQoDocumentoHabilitante> {
	
	
 private Long idTipoDocumento;
 private Long idReferencia;	
 private ProcessEnum proceso;
 private EstadoOperacionEnum estadoOperacion;
 

	public DocumentoByTipoDocumentoAndProRefEstOpSpec(Long idTipoDocumento, Long idReferencia, ProcessEnum proceso,
		EstadoOperacionEnum estadoOperacion) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idReferencia = idReferencia;
		this.proceso = proceso;
		this.estadoOperacion = estadoOperacion;
	}

	public DocumentoByTipoDocumentoAndProRefEstOpSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbQoDocumentoHabilitante arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoDocumentoHabilitante> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if(idReferencia != null) {
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
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
