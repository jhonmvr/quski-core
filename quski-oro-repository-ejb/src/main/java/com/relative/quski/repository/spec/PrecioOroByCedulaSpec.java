package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoPrecioOro;
/**
 * 
 * @author Jeroham Cadenas - Developer Twelve
 *
 */
public class PrecioOroByCedulaSpec extends AbstractSpecification<TbQoPrecioOro> {

private String cedula;
	
	public PrecioOroByCedulaSpec(String cedula){
		this.cedula=cedula;
	}
	@Override
	public boolean isSatisfiedBy(TbQoPrecioOro t) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoPrecioOro> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCotizador").get("tbQoCliente").get("cedulaCliente"), this.cedula));
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
			
	}
}
