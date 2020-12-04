package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoTelefonoCliente;

public class TelefonoAllByIdClienteSpec extends AbstractSpecification<TbQoTelefonoCliente> {

private Long id;
	
	public TelefonoAllByIdClienteSpec(Long id){
		this.id=id;
	}
	@Override
	public boolean isSatisfiedBy(TbQoTelefonoCliente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoTelefonoCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCliente").get("id"), this.id));
		return cb.and(where.toArray(new Predicate[0]));
	}
}
