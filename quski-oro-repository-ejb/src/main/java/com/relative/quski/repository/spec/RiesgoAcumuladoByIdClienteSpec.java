package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoRiesgoAcumulado;

public class RiesgoAcumuladoByIdClienteSpec extends AbstractSpecification<TbQoRiesgoAcumulado> {
	private Long idCliente;

	public RiesgoAcumuladoByIdClienteSpec(Long idCliente) {

		this.idCliente = idCliente;

	}
	@Override
	public boolean isSatisfiedBy(TbQoRiesgoAcumulado arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoRiesgoAcumulado> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCliente"), this.idCliente));
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}


}
