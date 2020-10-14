package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
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
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoNegociacion").get("id"), this.idNegociacion));
		where.add(cb.equal(poll.get("tbQoNegociacion").get("estado"), EstadoEnum.ACT));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}

}
