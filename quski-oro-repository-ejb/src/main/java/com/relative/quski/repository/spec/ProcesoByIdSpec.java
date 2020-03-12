package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoProceso;

public class ProcesoByIdSpec extends AbstractSpecification<TbQoProceso> {
	private long id;
	


	public ProcesoByIdSpec(long id) {
		super();
		this.id = id;
	}

	@Override
	public Predicate toPredicate(Root<TbQoProceso> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if (this.id != 0) {
			where.add(cb.equal(poll.get("id"), this.id));			
		}
		return cb.and(where.toArray(new Predicate[0]));
	}

	@Override
	public boolean isSatisfiedBy(TbQoProceso arg0) {
		return false;
	}

}
