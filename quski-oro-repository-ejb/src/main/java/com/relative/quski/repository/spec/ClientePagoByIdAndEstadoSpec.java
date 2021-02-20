package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoClientePago;

public class ClientePagoByIdAndEstadoSpec extends AbstractSpecification<TbQoClientePago> {

	private Long idClientePago;
	
	public ClientePagoByIdAndEstadoSpec(Long idClientePago) {
		super();
		this.idClientePago = idClientePago;
	}

	@Override
	public boolean isSatisfiedBy(TbQoClientePago arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoClientePago> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("id"), this.idClientePago));
		return cb.and(where.toArray(new Predicate[0]));
	}

}
