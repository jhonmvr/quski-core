package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCotizador;

public class CotizadorByIdentificacionClienteSpec extends AbstractSpecification<TbQoCotizador> {
	@Inject
	Logger log;

	private String cedulaCliente;

	public CotizadorByIdentificacionClienteSpec(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	@Override
	public boolean isSatisfiedBy(TbQoCotizador t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCotizador> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		String a = this.cedulaCliente;
		
		if (StringUtils.isNotBlank(a)) {
		
			where.add(cb.equal(poll.get("tbQoCliente").get("cedulaCliente"), a));
			where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT.toString()));
		}
		return cb.and(where.toArray(new Predicate[0]));

	}
}
