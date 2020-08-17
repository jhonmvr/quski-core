package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoExcepcione;

public class ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec extends AbstractSpecification<TbQoExcepcione> {
	private Long idNegociacion;
	private String tipoExcepcion;
	private String caracteristica;
	public ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec(String tipoExcepcion, Long idNegociacion, String caracteristica) {

		this.idNegociacion = idNegociacion;
		this.tipoExcepcion = tipoExcepcion;
		this.caracteristica = caracteristica;
	}
	@Override
	public boolean isSatisfiedBy(TbQoExcepcione arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoExcepcione> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();

		if (this.idNegociacion != null && this.idNegociacion != 0) {
			where.add(cb.equal(poll.get("tbQoNegociacion"), this.idNegociacion));
			
		}	
		if (this.tipoExcepcion != null && !this.tipoExcepcion.isEmpty()) {
			where.add(cb.equal(poll.get("tipoExcepcion"), this.tipoExcepcion));
		}	
		if (this.caracteristica != null && !this.caracteristica.isEmpty()) {
			where.add(cb.equal(poll.get("caracteristica"), this.caracteristica));
		}	
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}


}
