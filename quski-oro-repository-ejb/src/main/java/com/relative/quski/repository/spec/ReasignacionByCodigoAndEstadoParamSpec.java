package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class ReasignacionByCodigoAndEstadoParamSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	private String codigOp;
	private EstadoOperacionEnum estado;

	public ReasignacionByCodigoAndEstadoParamSpec(String codigOp, EstadoOperacionEnum estado) {
		this.codigOp = codigOp;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {

		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if (this.estado != null) {
			patientLevelPredicates.add(cb.equal(poll.get("estado"), estado));
		}
		if (this.codigOp != null) {
			patientLevelPredicates.add(cb.like(poll.get("codigoOperacion"), "%" + codigOp + "%"));
		}
		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
