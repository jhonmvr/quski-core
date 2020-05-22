package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbMiParametro;

public class ParametroByParamSpec extends AbstractSpecification<TbMiParametro> {
	private String nombre;
	private String tipo;
	private EstadoEnum estado;
	private String caracteriticaUno;
	private String caracteristicaDos;

	public ParametroByParamSpec() {
	}

	public ParametroByParamSpec(String nombre, String tipo, EstadoEnum estado, String caracteriticaUno,
			String caracteristicaDos) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
		this.caracteriticaUno = caracteriticaUno;
		this.caracteristicaDos = caracteristicaDos;
	}

	@Override
	public boolean isSatisfiedBy(TbMiParametro arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbMiParametro> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if (this.nombre != null && !this.nombre.isEmpty()) {
			patientLevelPredicates.add(cb.like(poll.<String>get("nombre"), "%" + this.nombre + "%"));
		}
		if (this.estado != null) {
			patientLevelPredicates.add(cb.equal(poll.<EstadoEnum>get("estado"), estado));
		}
		if (this.caracteriticaUno != null && !this.caracteriticaUno.isEmpty()) {
			patientLevelPredicates
					.add(cb.like(poll.<String>get("caracteriticaUno"), "%" + this.caracteriticaUno + "%"));
		}
		if (this.caracteristicaDos != null && !this.caracteristicaDos.isEmpty()) {
			patientLevelPredicates
					.add(cb.like(poll.<String>get("caracteristicaDos"), "%" + this.caracteristicaDos + "%"));
		}
		if (this.tipo != null && !this.tipo.isEmpty()) {
			patientLevelPredicates.add(cb.like(poll.<String>get("tipo"), "%" + this.tipo + "%"));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}
}
