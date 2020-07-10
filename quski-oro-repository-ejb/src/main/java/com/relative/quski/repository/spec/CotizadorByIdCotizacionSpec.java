package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.Join;
//import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//import org.apache.commons.lang3.StringUtils;
//import org.jfree.util.Log;

//import com.hazelcast.concurrent.atomicreference.operations.IsNullOperation;
import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
//import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
//import com.relative.quski.model.TbQoPrecioOro;
//import com.relative.quski.model.TbQoVariablesCrediticia;
//import com.relative.quski.wrapper.CotizadorWrapper;

public class CotizadorByIdCotizacionSpec extends AbstractSpecification<TbQoCotizador> {
	@Inject
	Logger log;
	private String idCliente;



	public CotizadorByIdCotizacionSpec(String idcliente) {
		
		this.idCliente = idcliente;
	}

	public CotizadorByIdCotizacionSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoCotizador arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCotizador> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCliente").get("id"), this.idCliente));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[]{}));	
		//Join<TbQoCotizador, TbQoCliente> joinTC = poll.join("tbQoCliente", JoinType.INNER);
		//Join<TbQoCotizador, TbQoPrecioOro> joinTPO = poll.join("tbQoPrecioOros", JoinType.INNER);
		//Join<TbQoCotizador, TbQoVariablesCrediticia> joinTVC = poll.join("tbQoVariablesCrediticias", JoinType.INNER);
		//List<Predicate> arg = new ArrayList<>();
		//arg.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		//return cb.and(cb.equal(joinTC.<String>get("cedulaCliente"), this.cedulaCliente));
	}

}
