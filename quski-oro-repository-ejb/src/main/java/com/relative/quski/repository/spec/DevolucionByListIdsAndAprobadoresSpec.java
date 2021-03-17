package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoDevolucion;



public class DevolucionByListIdsAndAprobadoresSpec  extends AbstractSpecification<TbQoDevolucion> {
	
		
	private List<String> aprobadores;
	private Long ids;

 
	public DevolucionByListIdsAndAprobadoresSpec(Long ids,	List<String> aprobadores) {
		super();
		this.ids = ids;
		this.aprobadores = aprobadores;
	}

	@Override
	public boolean isSatisfiedBy(TbQoDevolucion arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDevolucion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if( this.ids != null ) {
			where.add(cb.equal(poll.<Long>get("id"), this.ids));
		}
		if( this.aprobadores != null && !this.aprobadores.isEmpty() ) {
			where.add(poll.<String>get("aprobador").in( this.aprobadores ) );
		}else {
			where.add(cb.isNotNull(poll.<String>get("aprobador")) );
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}
		
		
		
}
