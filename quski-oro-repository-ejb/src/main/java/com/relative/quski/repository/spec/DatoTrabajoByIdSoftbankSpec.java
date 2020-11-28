package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoDatoTrabajoCliente;

public class DatoTrabajoByIdSoftbankSpec extends AbstractSpecification<TbQoDatoTrabajoCliente> {

private Long id;
	
	public DatoTrabajoByIdSoftbankSpec(Long id){
		this.id=id;
	}
	@Override
	public boolean isSatisfiedBy(TbQoDatoTrabajoCliente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDatoTrabajoCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		Long a = this.id;
		where.add(cb.equal(poll.get("idSoftbank"), a));
		return cb.and(where.toArray(new Predicate[0]));
	}
}
