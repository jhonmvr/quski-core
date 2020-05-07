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
import com.relative.quski.model.TbQoAgencia;

/**
 * @author relative
 *
 */
public class AgenciaByIdSpec extends AbstractSpecification<TbQoAgencia> {
	private long id;

	public AgenciaByIdSpec(long id) {

		this.id = id;

	}

	@Override
	public boolean isSatisfiedBy(TbQoAgencia arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoAgencia> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
	
		Integer a = new Integer((int) this.id);
		if (a != null && a != 0) {
			where.add(cb.equal(poll.get("id"), this.id));
			where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		}	

		return cb.and(where.toArray(new Predicate[0]));
	}

}
