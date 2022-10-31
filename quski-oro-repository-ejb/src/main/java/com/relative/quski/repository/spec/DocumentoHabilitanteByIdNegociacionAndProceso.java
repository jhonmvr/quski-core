package com.relative.quski.repository.spec;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;

public class DocumentoHabilitanteByIdNegociacionAndProceso extends AbstractSpecification<TbQoDocumentoHabilitante> {

	private String idReferencia;
	private List<ProcessEnum> proceso;
	
	
	
	public DocumentoHabilitanteByIdNegociacionAndProceso(String idReferencia, List<ProcessEnum> proceso) {
		super();
		this.idReferencia = idReferencia;
		this.proceso = proceso;
	}

	@Override
	public boolean isSatisfiedBy(TbQoDocumentoHabilitante arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDocumentoHabilitante> poll, CriteriaBuilder cb) {
		
		return cb.and(poll.get("proceso").in(this.proceso), cb.equal(poll.get("idReferencia"), this.idReferencia));
	}

}
