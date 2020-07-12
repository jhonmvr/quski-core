package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoExcepcione;

public class ExcepcionesByIdNegociacionSpec extends AbstractSpecification<TbQoExcepcione> {
	private Long idNegociacion;

	public ExcepcionesByIdNegociacionSpec(Long idNegociacion) {

		this.idNegociacion = idNegociacion;

	}
	@Override
	public boolean isSatisfiedBy(TbQoExcepcione arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoExcepcione> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();

		if (this.idNegociacion != null && this.idNegociacion != 0) {
			where.add(cb.equal(poll.get("tbQoNegociacion"), this.idNegociacion));
		}	
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}


}
