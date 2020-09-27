package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoRegistrarPago;

public class RegistrarPagoByIdClientePagoSpec extends AbstractSpecification<TbQoRegistrarPago> {
	
	private Long idClientePago;
	

	public RegistrarPagoByIdClientePagoSpec(Long idClientePago) {
		super();
		this.idClientePago = idClientePago;
	}

	@Override
	public boolean isSatisfiedBy(TbQoRegistrarPago arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoRegistrarPago> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("tbQoClientePago").get("id"), this.idClientePago));
	}

}
