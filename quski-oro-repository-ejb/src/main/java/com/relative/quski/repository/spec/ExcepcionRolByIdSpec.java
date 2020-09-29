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
import com.relative.quski.model.TbQoExcepcionRol;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class ExcepcionRolByIdSpec extends AbstractSpecification<TbQoExcepcionRol> {
	private Long id;

	public ExcepcionRolByIdSpec(Long id) {

		this.id = id;
	}

	public ExcepcionRolByIdSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoExcepcionRol arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoExcepcionRol> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if (this.id != null) {
			where.add(cb.equal(poll.<Long>get("id"), this.id));
		}
		return cb.and(where.toArray(new Predicate[] {}));
	}

}
