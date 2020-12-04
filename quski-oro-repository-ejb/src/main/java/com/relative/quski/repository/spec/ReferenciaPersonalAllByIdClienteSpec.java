/**
 * 
 */
package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoReferenciaPersonal;

public class ReferenciaPersonalAllByIdClienteSpec extends AbstractSpecification<TbQoReferenciaPersonal> {
	private Long id;

	public ReferenciaPersonalAllByIdClienteSpec(Long id) {
		this.id = id;
	}

	@Override
	public boolean isSatisfiedBy(TbQoReferenciaPersonal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoReferenciaPersonal> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCliente").get("id"), this.id));
		return cb.and(where.toArray(new Predicate[0]));
	}

}
