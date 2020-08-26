package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoByIdNegociacionSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	private Long idNegociacion;
	
	

	public CreditoByIdNegociacionSpec(Long idNegociacion) {
		super();
		this.idNegociacion = idNegociacion;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("tbQoNegociacion").get("id"), this.idNegociacion));
	}

}
