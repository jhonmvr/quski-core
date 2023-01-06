package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.core.persistence.Specification;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoByNumeroOperacionSpec extends AbstractSpecification<TbQoCreditoNegociacion>
		implements Specification<TbQoCreditoNegociacion> {
	private String operacion;
	

	public CreditoByNumeroOperacionSpec(String operacion) {
		super();
		this.operacion = operacion;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("numeroOperacion"), this.operacion));
		where.add(cb.equal(poll.get("numeroOperacionMadre"), this.operacion));
		return cb.or(where.toArray(new Predicate[0]));
	}

}
