package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoNegociacionCalculo;

public class NegociacionCalculoByIdSpec extends AbstractSpecification<TbQoNegociacionCalculo> {
	private long id;

	public NegociacionCalculoByIdSpec(long id) {

		this.id = id;
	}

	@Override
	public boolean isSatisfiedBy(TbQoNegociacionCalculo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoNegociacionCalculo> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();

		Integer a = new Integer((int) this.id);
		if (a != null && a != 0) {
			where.add(cb.equal(poll.get("id"), this.id));
			where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		}

		return cb.and(where.toArray(new Predicate[0]));

	}
}
