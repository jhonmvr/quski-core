package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoReferido;

public class ReferidoByIdNegociacion extends AbstractSpecification<TbQoReferido> {
	private Long idNegociacion;
	
	

	public ReferidoByIdNegociacion(Long idNegociacion) {
		this.idNegociacion = idNegociacion;
	}

	@Override
	public boolean isSatisfiedBy(TbQoReferido t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoReferido> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.equal(poll.get("tbQoNegociacion").get("id"), this.idNegociacion);
	}

}
