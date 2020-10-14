package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoDocumentoHabilitante;

public class DocumentoById extends AbstractSpecification<TbQoDocumentoHabilitante> {

	private Long id;

	public DocumentoById(Long id) {
		this.id = id;
	}

	public DocumentoById() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoDocumentoHabilitante arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDocumentoHabilitante> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if (this.id != null) {
			where.add(cb.equal(poll.<Long>get("id"), this.id));
		}
		return cb.and(where.toArray(new Predicate[] {}));
	}

}
