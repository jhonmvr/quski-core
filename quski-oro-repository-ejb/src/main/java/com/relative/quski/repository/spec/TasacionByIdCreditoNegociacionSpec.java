package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
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
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		where.add(cb.equal(poll.get("tbQoCreditoNegociacion").get("id"), this.idCreditoNegociacion));
		return cb.and(where.toArray(new Predicate[0]));
	}
}