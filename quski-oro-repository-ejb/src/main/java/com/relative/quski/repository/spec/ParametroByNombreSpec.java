package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbMiParametro;


public class ParametroByNombreSpec extends AbstractSpecification<TbMiParametro> {
private String nombre;
	
	public ParametroByNombreSpec () {}


	public ParametroByNombreSpec(String nombre) {
	
		this.nombre = nombre;
	}

	@Override
	public boolean isSatisfiedBy(TbMiParametro arg0) {
		return false;
	}


	@Override
	public Predicate toPredicate(Root<TbMiParametro> poll, CriteriaBuilder cb) {
		return cb.and(
		        cb.equal(poll.<String>get("nombre"),this.nombre.trim())
		    );
	}
	

}
