package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoDireccionCliente;

public class DireccionByIdClienteAndTipoDireccionSpec extends AbstractSpecification<TbQoDireccionCliente> {

private Long id;
private String tipoDireccion;
	
	public DireccionByIdClienteAndTipoDireccionSpec(Long id, String tipoDireccion){
		this.id=id;
		this.tipoDireccion=tipoDireccion;
	}
	@Override
	public boolean isSatisfiedBy(TbQoDireccionCliente t) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDireccionCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		EstadoEnum e = EstadoEnum.ACT;
		Long a = this.id;
		String d = this.tipoDireccion;
			where.add(cb.equal(poll.get("tbQoCliente").get("id"), a));
			where.add(cb.equal(poll.get("tipoDireccion"), d));
			where.add(cb.equal(poll.get("estado"), e));
		return cb.and(where.toArray(new Predicate[0]));
	}
}
