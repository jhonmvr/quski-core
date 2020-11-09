package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoExcepcion;

public class ExcepcionesByTipoExcepcionAndIdNegociacionSpec extends AbstractSpecification<TbQoExcepcion> {
	private Long idNegociacion;
	private String tipoExcepcion;
	public ExcepcionesByTipoExcepcionAndIdNegociacionSpec(String tipoExcepcion, Long idNegociacion) {

		this.idNegociacion = idNegociacion;
		this.tipoExcepcion = tipoExcepcion;
	}
	@Override
	public boolean isSatisfiedBy(TbQoExcepcion arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoExcepcion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();

		if (this.idNegociacion != null && this.idNegociacion != 0) {
			where.add(cb.equal(poll.get("tbQoNegociacion"), this.idNegociacion));
			
		}	
		if (this.tipoExcepcion != null && !this.tipoExcepcion.isEmpty()) {
			where.add(cb.equal(poll.get("tipoExcepcion"), this.tipoExcepcion));
		}	
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}


}
