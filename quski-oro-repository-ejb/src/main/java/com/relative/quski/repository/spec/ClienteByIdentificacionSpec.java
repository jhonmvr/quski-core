package com.relative.quski.repository.spec;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;

public class ClienteByIdentificacionSpec extends AbstractSpecification<TbQoCliente> {
	private String identificacion;

	public ClienteByIdentificacionSpec(String identificacion) {

		this.identificacion = identificacion == null ? "" : identificacion;
	}

	public ClienteByIdentificacionSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoCliente arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCliente> poll, CriteriaBuilder cb) {

		return cb.and(cb.like(poll.<String>get("cedulaCliente"),"%"+this.identificacion+"%"), cb.equal(poll.<String>get("estado"), EstadoEnum.ACT.toString()));
	}

}
