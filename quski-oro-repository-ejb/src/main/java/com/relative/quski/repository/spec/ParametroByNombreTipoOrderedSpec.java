package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbMiParametro;
import org.apache.commons.lang3.StringUtils;

public class ParametroByNombreTipoOrderedSpec extends AbstractSpecification<TbMiParametro> {
	private String nombre;
	private String tipo;

	public ParametroByNombreTipoOrderedSpec() {
	}

	public ParametroByNombreTipoOrderedSpec(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;

	}

	@Override
	public boolean isSatisfiedBy(TbMiParametro arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiParametro> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if (StringUtils.isNotBlank(this.nombre)) {
			patientLevelPredicates.add(cb.like(poll.<String>get("nombre"), "%" + this.nombre + "%"));
		}
		if (StringUtils.isNotBlank(this.tipo)) {
			patientLevelPredicates.add(cb.like(poll.<String>get("tipo"), "%" + this.tipo + "%"));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
