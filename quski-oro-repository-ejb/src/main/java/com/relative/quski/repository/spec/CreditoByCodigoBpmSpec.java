package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoByCodigoBpmSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	private String codigoBpm;
	
	

	public CreditoByCodigoBpmSpec(String codigoBpm) {
		super();
		this.codigoBpm = codigoBpm;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("codigo"), this.codigoBpm));
		return cb.and(where.toArray(new Predicate[0]));
	}

}
