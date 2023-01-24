package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.core.persistence.Specification;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoNegociacionSpec extends AbstractSpecification<TbQoCreditoNegociacion>
		implements Specification<TbQoCreditoNegociacion> {
	List<Long> idNegociacion;
	

	public CreditoNegociacionSpec(List<Long> idNegociacion) {
		super();
		this.idNegociacion = idNegociacion;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		
		if( this.idNegociacion != null ) {
			where.add(poll.<Long>get("tbQoNegociacion").get("id").in( this.idNegociacion ) );
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
