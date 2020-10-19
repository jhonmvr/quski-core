package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoTasacion;

public class TasacionByIdNegociacionSpec extends AbstractSpecification<TbQoTasacion> {

	private Long idNegociacion;

	public TasacionByIdNegociacionSpec(Long idNegociacion) {

		this.idNegociacion = idNegociacion;
	}

	public TasacionByIdNegociacionSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoTasacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * 	
	 */
	@Override
	public Predicate toPredicate(Root<TbQoTasacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		//where.add(cb.equal(poll.get("tbQoCreditoNegociacion").get("tbQoNegociacion").get("id"), this.idNegociacion));
		where.add(cb.equal(poll.get("tbQoCreditoNegociacion").get("id"), this.idNegociacion));
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}
}