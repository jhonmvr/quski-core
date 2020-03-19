package com.relative.quski.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoVariablesCrediticia;

public class VariablesCrediticiasByIdCotizacionSpec extends AbstractSpecification<TbQoVariablesCrediticia> {
	private Long idCotizador;

	public VariablesCrediticiasByIdCotizacionSpec(Long idCotizador) {

		this.idCotizador=idCotizador;
	}
	public VariablesCrediticiasByIdCotizacionSpec() {
	}
	@Override
	public boolean isSatisfiedBy(TbQoVariablesCrediticia arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoVariablesCrediticia> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("tbQoCotizador").<Long>get("id"), this.idCotizador));
	}
}