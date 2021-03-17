package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoVerificacionTelefonica;



public class VerificacionByListIdsAndAprobadoresSpec  extends AbstractSpecification<TbQoVerificacionTelefonica> {
	
		
	private List<String> aprobadores;
	private List<Long> ids;

 
	public VerificacionByListIdsAndAprobadoresSpec(List<Long> ids,	List<String> aprobadores) {
		super();
		this.ids = ids;
		this.aprobadores = aprobadores;
	}

	
	 
		@Override
		public boolean isSatisfiedBy(TbQoVerificacionTelefonica arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoVerificacionTelefonica> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if( this.ids != null && !this.ids.isEmpty() ) {
				where.add(poll.<Long>get("id").in(this.ids));
			}
			if( this.aprobadores != null && !this.aprobadores.isEmpty() ) {
				where.add(poll.<String>get("aprobador").in( this.aprobadores ) );
			}
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
