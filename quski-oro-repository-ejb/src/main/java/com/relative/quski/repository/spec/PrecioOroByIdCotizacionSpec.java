package com.relative.quski.repository.spec;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoPrecioOro;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bouncycastle.crypto.paddings.TBCPadding;

public class PrecioOroByIdCotizacionSpec extends AbstractSpecification<TbQoPrecioOro> {

private String cedulaCliente;
	
	public PrecioOroByIdCotizacionSpec(String cedulaCliente){
		this.cedulaCliente=cedulaCliente;
	}
	@Override
	public boolean isSatisfiedBy(TbQoPrecioOro t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoPrecioOro> poll, CriteriaBuilder cb) {
		Join<TbQoPrecioOro, TbQoCotizador> joinAgenciaContrato = poll.join("tbQoCotizador");
		Join<TbQoCotizador, TbQoCliente> joinContratoJoya = joinAgenciaContrato.join("tbQoCliente");
		return cb.and(joinContratoJoya.get("cedulaCliente").in(this.cedulaCliente));
		//return cb.and( cb.equal(poll.get("tbQoCotizador").get("id"), idCotizador));
			
	}
}
