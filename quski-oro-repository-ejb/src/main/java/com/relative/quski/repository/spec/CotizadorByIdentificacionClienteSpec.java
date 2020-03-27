package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;

public class CotizadorByIdentificacionClienteSpec extends AbstractSpecification<TbQoCotizador> {

private String cedulaCliente;
	
	public CotizadorByIdentificacionClienteSpec(String cedulaCliente){
		this.cedulaCliente=cedulaCliente;
	}
	@Override
	public boolean isSatisfiedBy(TbQoCotizador t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCotizador> poll, CriteriaBuilder cb) {
		Join<TbQoCotizador, TbQoCliente> joinAgenciaContrato = poll.join("tbQoCliente");
		//Join<TbQoCotizador, TbQoCliente> joinContratoJoya = joinAgenciaContrato.join("tbQoCliente");
		return cb.and(joinAgenciaContrato.get("cedulaCliente").in(this.cedulaCliente));
		//return cb.and( cb.equal(poll.get("tbQoCotizador").get("id"), idCotizador));
		

		
	}
}
