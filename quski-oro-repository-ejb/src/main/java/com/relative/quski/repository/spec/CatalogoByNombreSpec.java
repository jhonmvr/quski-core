package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCatalogo;
import com.relative.quski.model.TbQoCliente;

public class CatalogoByNombreSpec extends AbstractSpecification<TbQoCatalogo>{
	private String nombre;
	public CatalogoByNombreSpec(String nombre) {

		this.nombre = nombre == null ? "" : nombre;
	}

	public CatalogoByNombreSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoCatalogo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCatalogo> poll, CriteriaBuilder cb) {

		return cb.and(cb.like(poll.<String>get("nombreCatalogo"),"%"+this.nombre+"%"), cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
	}

}
