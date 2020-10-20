package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoProceso;

public class ProcesoByAsesorSpec extends AbstractSpecification<TbQoProceso> {
	private String asesor;
	public ProcesoByAsesorSpec(String asesor) {
		this.asesor = asesor;
	}
	@Override
	public boolean isSatisfiedBy(TbQoProceso arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoProceso> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		where.add(cb.equal(poll.get("asesor"), this.asesor));
		return cb.and(where.toArray(new Predicate[] {}));
	}

}
