package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoClientePago;

public class ClientePagoByIdAndEstadoSpec extends AbstractSpecification<TbQoClientePago> {

	private Long idClientePago;
	private EstadoEnum estado;
	
	public ClientePagoByIdAndEstadoSpec(Long idClientePago, EstadoEnum estado) {
		super();
		this.idClientePago = idClientePago;
		this.estado = estado;
	}

	@Override
	public boolean isSatisfiedBy(TbQoClientePago arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoClientePago> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("id"), this.idClientePago),
				cb.equal(poll.get("estado"), this.estado));
	}

}
