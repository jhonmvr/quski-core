package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoDevolucion;

/**
 * 
 * @author Jeroham Cadenas - Developer Twelve
 *
 */
public class DevolucionByNumeroOperacionSpec extends AbstractSpecification<TbQoDevolucion> {

	private String numeroOperacion;

	public DevolucionByNumeroOperacionSpec(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	@Override
	public boolean isSatisfiedBy(TbQoDevolucion t) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDevolucion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("codigoOperacion"), this.numeroOperacion));
		return cb.and(where.toArray(new Predicate[0]));
	}
}
