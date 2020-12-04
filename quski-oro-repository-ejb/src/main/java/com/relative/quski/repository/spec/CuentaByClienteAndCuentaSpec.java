package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCuentaBancariaCliente;

public class CuentaByClienteAndCuentaSpec extends AbstractSpecification<TbQoCuentaBancariaCliente>{

	private Long id;
	private String cuenta;
	
	public CuentaByClienteAndCuentaSpec(Long id, String cuenta) {
		super();
		this.id = id;
		this.cuenta = cuenta;
	}
	

	@Override
	public boolean isSatisfiedBy(TbQoCuentaBancariaCliente arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCuentaBancariaCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCliente").get("id"), this.id));
		where.add(cb.equal(poll.get("cuenta"), this.cuenta));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}

	

}
