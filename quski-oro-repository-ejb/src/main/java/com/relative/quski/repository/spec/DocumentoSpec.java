package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoDocumentoHabilitante;



public class DocumentoSpec  extends AbstractSpecification<TbQoDocumentoHabilitante> {
	
	public DocumentoSpec() {
	}
	@Override
	public boolean isSatisfiedBy(TbQoDocumentoHabilitante arg0) {
		// TODO Auto-generated method stub
		return false;
	}

		@Override
		public Predicate toPredicate(Root<TbQoDocumentoHabilitante> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
