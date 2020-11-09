package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoExcepcion;

public class ExcepcionesByIdClienteSpec extends AbstractSpecification<TbQoExcepcion> {
	private Long idCliente;

	public ExcepcionesByIdClienteSpec(Long idCliente) {

		this.idCliente = idCliente;

	}
	@Override
	public boolean isSatisfiedBy(TbQoExcepcion arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoExcepcion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();

		if (this.idCliente != null && this.idCliente != 0) {
			where.add(cb.equal(poll.get("tbQoNegociacion").get("tbQoCliente"), this.idCliente));
		}	
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));

		return cb.and(where.toArray(new Predicate[0]));
	}


}
