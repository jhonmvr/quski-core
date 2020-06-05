package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoTipoDocumento;



public class TipoDocumentoWithDocumentoByAndProRefEstOpSpec  extends AbstractSpecification<TbQoTipoDocumento> {
	
	
 private Long idTipoDocumento;
 private Long idReferencia;	
 private ProcessEnum proceso;
 private EstadoOperacionEnum estadoOperacion;
 

	public TipoDocumentoWithDocumentoByAndProRefEstOpSpec(Long idTipoDocumento, Long idReferencia, ProcessEnum proceso,
		EstadoOperacionEnum estadoOperacion) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idReferencia = idReferencia;
		this.proceso = proceso;
		this.estadoOperacion = estadoOperacion;
	}

	
	 
		@Override
		public boolean isSatisfiedBy(TbQoTipoDocumento arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoTipoDocumento> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if(idReferencia != null) {
				Join<TbQoTipoDocumento, TbQoDocumentoHabilitante> join=poll.join("tbQoDocumentoHabilitantes",JoinType.INNER);
				where.add(cb.equal(join.<String>get("idReferencia"), this.idReferencia ));
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
