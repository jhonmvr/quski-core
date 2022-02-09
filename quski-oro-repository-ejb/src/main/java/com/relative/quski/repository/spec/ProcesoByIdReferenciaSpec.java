package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;

public class ProcesoByIdReferenciaSpec extends AbstractSpecification<TbQoProceso>  {
	private Long id;
	private ProcesoEnum proceso;

	public ProcesoByIdReferenciaSpec(Long id, ProcesoEnum proceso) {
		this.id = id;
		this.proceso = proceso;
	}
	@Override
	public boolean isSatisfiedBy(TbQoProceso arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoProceso> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("idReferencia"), this.id));
		where.add(cb.equal(poll.get("proceso"), this.proceso));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		where.add(cb.notEqual(poll.get("estadoProceso"), EstadoProcesoEnum.RECHAZADO));
		where.add(cb.notEqual(poll.get("estadoProceso"), EstadoProcesoEnum.CANCELADO));
		where.add(cb.notEqual(poll.get("estadoProceso"), EstadoProcesoEnum.CADUCADO));
		where.add(cb.notEqual(poll.get("estadoProceso"), EstadoProcesoEnum.APROBADO));
		return cb.and(where.toArray(new Predicate[] {}));

	}

}
