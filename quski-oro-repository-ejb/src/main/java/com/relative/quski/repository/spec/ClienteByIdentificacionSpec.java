package com.relative.quski.repository.spec;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
/**
 * Clase que realiza el spect del cliente por identificacion valida si el estado es activo 
 * @author KLÉBER GUERRA Relative Engine
 *
 */
public class ClienteByIdentificacionSpec extends AbstractSpecification<TbQoCliente> {
	@Inject
	Logger log;
	private String identificacion;

	public ClienteByIdentificacionSpec(String identificacion) {

		this.identificacion = identificacion == null ? "" : identificacion;
	}

	public ClienteByIdentificacionSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoCliente arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCliente> poll, CriteriaBuilder cb) {

		List<Predicate> where = new ArrayList<>();
		if (StringUtils.isNotBlank(this.identificacion)) {
			where.add(cb.equal(poll.<String>get("cedulaCliente"), this.identificacion));
		}
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
	//	log.info("VALOR DE LA COMPARACION=======> "+cb.and(where.toArray(new Predicate[] {})));
		return cb.and(where.toArray(new Predicate[]{}));	
	}

}
