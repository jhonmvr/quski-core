package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoVariableCrediticia;

public class VariablesCrediticiasByIdCotizacionSpec extends AbstractSpecification<TbQoVariableCrediticia> {
	private Long cedulaCliente;

	public VariablesCrediticiasByIdCotizacionSpec(Long cedulaCliente){
		this.cedulaCliente=cedulaCliente;
	}
	@Override
	public boolean isSatisfiedBy(TbQoVariableCrediticia t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoVariableCrediticia> poll, CriteriaBuilder cb) {
		Join<TbQoVariableCrediticia, TbQoCotizador> joinAgenciaContrato = poll.join("tbQoCotizador");
		Join<TbQoCotizador, TbQoCliente> joinContratoJoya = joinAgenciaContrato.join("tbQoCliente");
		return cb.and(joinContratoJoya.get("cedulaCliente").in(this.cedulaCliente));
		//return cb.and( cb.equal(poll.get("tbQoCotizador").get("id"), idCotizador));
		
	}

}
