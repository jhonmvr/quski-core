package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoReasignacionActividad;

public class ReasignacionByCodigoParamSpec extends AbstractSpecification<TbQoReasignacionActividad> {
	private String id;
	

	public ReasignacionByCodigoParamSpec(String id) {
		this.id = id;
	
	}


	@Override
	public boolean isSatisfiedBy(TbQoReasignacionActividad arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Predicate toPredicate(Root<TbQoReasignacionActividad> poll, CriteriaBuilder cb) {
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		if (this.id != null) {
			patientLevelPredicates.add(cb.like(poll.get("id"), "%" + id + "%"));
		}
		return null;
	}

	
}
