package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoTelefonoCliente;

public class TelefonoByClienteAndTipoSpec extends AbstractSpecification<TbQoTelefonoCliente> {
	
	private String identificacion;
	private String tipoTelefono;
	
	

	public TelefonoByClienteAndTipoSpec(String identificacion, String tipoTelefono) {
		super();
		this.identificacion = identificacion;
		this.tipoTelefono = tipoTelefono;
	}

	@Override
	public boolean isSatisfiedBy(TbQoTelefonoCliente arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoTelefonoCliente> poll, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return cb.and(cb.equal(poll.get("tbQoCliente").get("cedulaCliente"), this.identificacion),
				cb.equal(poll.get("tipoTelefono"), this.tipoTelefono));
	}

}
