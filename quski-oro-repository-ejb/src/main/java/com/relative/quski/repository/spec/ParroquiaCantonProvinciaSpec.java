package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.Parroquia;

public class ParroquiaCantonProvinciaSpec extends AbstractSpecification<Parroquia> {
	private String nombre;

	public ParroquiaCantonProvinciaSpec(String nombre) {

		this.nombre=nombre;
	}
	public ParroquiaCantonProvinciaSpec() {
	}
	@Override
	public boolean isSatisfiedBy(Parroquia arg0) {
	
		return false;
	}

	@Override
	public Predicate toPredicate(Root<Parroquia> poll, CriteriaBuilder cb) {
		return cb.or(
				cb.like(poll.get("canton").get("nombreCanton"), "%"+this.nombre+"%"),
				cb.like(poll.get("canton").get("provincia").get("nombreProvincia"), "%"+this.nombre+"%"),
				cb.like(poll.get("nombreParroquia"), "%"+this.nombre+"%")
				);
	}
}