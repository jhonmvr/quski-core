package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCotizador;
/**
 * 
 * @author Jeroham Cadenas - Developer Twelve
 *
 */
public class CotizacionByCedulaSpec extends AbstractSpecification<TbQoCotizador> {

private String cedula;
	
	public CotizacionByCedulaSpec(String cedula){
		this.cedula=cedula;
	}
	@Override
	public boolean isSatisfiedBy(TbQoCotizador t) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCotizador> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCliente").get("cedulaCliente"), this.cedula));
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}
}
