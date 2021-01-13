package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoRegistrarPago;

public class RegistrarPagoByIdCreditoSpec extends AbstractSpecification<TbQoRegistrarPago> {
	
	private Long idCredito;

	public RegistrarPagoByIdCreditoSpec(Long idCredito) {
		super();
		this.idCredito = idCredito;
	}

	@Override
	public boolean isSatisfiedBy(TbQoRegistrarPago arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoRegistrarPago> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbQoCreditoNegociacion").get("id"), this.idCredito));
	}

}
