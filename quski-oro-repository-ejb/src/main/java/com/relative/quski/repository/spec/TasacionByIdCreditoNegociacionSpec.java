package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoTasacion;

public class TasacionByIdCreditoNegociacionSpec  extends AbstractSpecification<TbQoTasacion> {

	private Long idCreditoNegociacion;

	public TasacionByIdCreditoNegociacionSpec(Long idCreditoNegociacion) {

		this.idCreditoNegociacion = idCreditoNegociacion;
	}

	public TasacionByIdCreditoNegociacionSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoTasacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoTasacion> poll, CriteriaBuilder cb) {
		//Join<TbMiCliente,TbMiContra> joincont = poll.join("tbMiCliente")
		return cb.and(cb.equal(poll.<TbQoTasacion>get("tbQoCreditoNegociacion").get("id"), this.idCreditoNegociacion));
	}
}