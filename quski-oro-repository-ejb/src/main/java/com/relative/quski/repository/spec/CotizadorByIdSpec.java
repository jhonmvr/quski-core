package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCotizador;

public class CotizadorByIdSpec extends AbstractSpecification<TbQoCotizador> {

	private Long id;

	public CotizadorByIdSpec(Long id) {
		this.id = id;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCotizador t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCotizador> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if (this.id > 0 ) {
			where.add(cb.equal(poll.get("id"), this.id));
		}
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));

	}
}
