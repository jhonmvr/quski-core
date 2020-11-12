package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCuentaBancariaCliente;

public class CuentaBancariaByIdClienteSpec extends AbstractSpecification<TbQoCuentaBancariaCliente> {

private Long id;
	
	public CuentaBancariaByIdClienteSpec(Long id){
		this.id=id;
	}
	@Override
	public boolean isSatisfiedBy(TbQoCuentaBancariaCliente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCuentaBancariaCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		Long a = this.id;
			where.add(cb.equal(poll.get("tbQoCliente").get("id"), a));
			where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}
}
