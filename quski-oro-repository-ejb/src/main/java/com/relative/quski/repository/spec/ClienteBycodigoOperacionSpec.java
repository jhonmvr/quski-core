package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.core.persistence.Specification;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.wrapper.AsignacionesWrapper;

public class ClienteBycodigoOperacionSpec extends AbstractSpecification<AsignacionesWrapper> {
	private String codigoOperacion;

	

	public ClienteBycodigoOperacionSpec(String codigoOperacion) {
		super();
		this.codigoOperacion = codigoOperacion;

		
	}
	@Override
	public boolean isSatisfiedBy(AsignacionesWrapper arg0) {
		return false;
	}
	@Override
	public Predicate toPredicate(Root<AsignacionesWrapper> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if (StringUtils.isNotBlank(this.codigoOperacion)) {
			where.add(cb.equal(poll.get("codigoOperacion"), this.codigoOperacion));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}



}
