package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoPrecioOro;

public class DetalleCotizacionByIdSpec  extends AbstractSpecification<TbQoPrecioOro> {

private Long id;
	
	public DetalleCotizacionByIdSpec(Long id){
		this.id=id;
	}
	@Override
	public boolean isSatisfiedBy(TbQoPrecioOro t) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoPrecioOro> poll, CriteriaBuilder cb) {
		Join<TbQoPrecioOro, TbQoCotizador> joinAgenciaContrato = poll.join("tbQoCotizador");
		Join<TbQoCotizador, TbQoCliente> joinContratoJoya = joinAgenciaContrato.join("tbQoPrecioOro");
		return cb.and(joinContratoJoya.get("tbQoCotizador").in(this.id));
		//return cb.and( cb.equal(poll.get("tbQoCotizador").get("id"), idCotizador));	
			
	}
}