package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCreditoNegociacion;



public class CreditoByListIdsAndAprobadoresSpec  extends AbstractSpecification<TbQoCreditoNegociacion> {
	
		
	private List<String> aprobadores;
	private Long id;

 
	public CreditoByListIdsAndAprobadoresSpec(Long id,	List<String> aprobadores) {
		super();
		this.id = id;
		this.aprobadores = aprobadores;
	}

	
	 
		@Override
		public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if( this.id != null  ) {
				where.add(cb.equal(poll.<Long>get("tbQoNegociacion").get("id"), this.id));
			}
			if( this.aprobadores != null && !this.aprobadores.isEmpty() ) {
				where.add(poll.<String>get("tbQoNegociacion").get("aprobador").in( this.aprobadores ) );
			}
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
