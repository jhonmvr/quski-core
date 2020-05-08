package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;

public class CotizadorByIdentificacionClienteSpec extends AbstractSpecification<TbQoCotizador> {

private String cedulaCliente;
	
	public CotizadorByIdentificacionClienteSpec(String cedulaCliente){
		this.cedulaCliente=cedulaCliente;
	}
	@Override
	public boolean isSatisfiedBy(TbQoCotizador t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCotizador> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		String e = EstadoEnum.ACT.toString();

		String a = this.cedulaCliente;
		if (StringUtils.isNotBlank(a)) {
			where.add(cb.equal(poll.get("tbQoCliente").get("cedulaCliente"), a));
			where.add(cb.equal(poll.get("estado"), e));
		}	
		return cb.and(where.toArray(new Predicate[0]));
		
		

		
	}
}
