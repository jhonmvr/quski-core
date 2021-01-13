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
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class ProcesoByIdNovacion extends AbstractSpecification<TbQoProceso>  {
	private Long idNegociacion;

	public ProcesoByIdNovacion(Long idNegociacion) {
		this.idNegociacion = idNegociacion;
	}
	@Override
	public boolean isSatisfiedBy(TbQoProceso arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoProceso> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("idReferencia"), this.idNegociacion));
		where.add(cb.equal(poll.get("proceso"), ProcesoEnum.RENOVACION));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[] {}));

	}

}
