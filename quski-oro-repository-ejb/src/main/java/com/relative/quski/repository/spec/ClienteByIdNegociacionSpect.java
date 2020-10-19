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
import com.relative.quski.model.TbQoCliente;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
public class ClienteByIdNegociacionSpect extends AbstractSpecification<TbQoCliente> {
private Long idNegociacion;

public ClienteByIdNegociacionSpect(Long idNegociacion) {
	
	this.idNegociacion = idNegociacion;
}

@Override
public boolean isSatisfiedBy(TbQoCliente arg0) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Predicate toPredicate(Root<TbQoCliente> poll, CriteriaBuilder cb) {
	List<Predicate> where = new ArrayList<>();
	where.add(cb.equal(poll.get("tbQoNegociacions").get("id"), this.idNegociacion));
	where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
	return cb.and(where.toArray(new Predicate[]{}));
}

}
