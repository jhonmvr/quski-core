package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoRegistrarPago;

public class RegistrarPagoByIdClientePagoSpec extends AbstractSpecification<TbQoRegistrarPago> {
	
	private Long idClientePago;
	private String tipo;

	public RegistrarPagoByIdClientePagoSpec(Long idClientePago) {
		super();
		this.idClientePago = idClientePago;
		//this.tipo= tipo;
	}

	@Override
	public boolean isSatisfiedBy(TbQoRegistrarPago arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoRegistrarPago> poll, CriteriaBuilder cb) {
		//cb.and(cb.equal(poll.get("tbQoClientePago").get(tipo), this.tipo));
		return cb.and(cb.equal(poll.get("tbQoClientePago").get("id"), this.idClientePago));
	}

}
