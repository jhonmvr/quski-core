/**
 * 
 */
package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class CreditoNegociacionByIdSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	private EstadoEnum estado;
	private Long id;
	
	

	public CreditoNegociacionByIdSpec(EstadoEnum estado, Long id) {
		 
		this.estado = estado;
		this.id = id;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(this.estado != null ) {
			where.add(poll.<EstadoEnum>get("estado").in(this.estado));
		}
		where.add(cb.equal(poll.get("tbQoCreditoNegociacion").get("id"),this.id));			
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT ));
		return cb.and(where.toArray(new Predicate[] {}));
	}

}
