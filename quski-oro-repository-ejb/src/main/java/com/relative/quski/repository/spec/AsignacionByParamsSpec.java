package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.wrapper.AsignacionesWrapper;

public class AsignacionByParamsSpec extends AbstractSpecification<AsignacionesWrapper> {
	private String nombreAgencia;
	private String cedula;

	

	public AsignacionByParamsSpec( String nombreAgencia, String cedula) {
		super();

		this.nombreAgencia = nombreAgencia;
		this.cedula = cedula;
		
	}
	@Override
	public boolean isSatisfiedBy(AsignacionesWrapper arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<AsignacionesWrapper> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		String e = EstadoOperacionEnum.PENDIENTE_APROBACION.toString();
		where.add(cb.equal(poll.get("estado"), e));

		if (StringUtils.isNotBlank(this.nombreAgencia)) {
			where.add(cb.equal(poll.get("agenciaWrapper").get("nombreAgencia"), this.nombreAgencia));
		}
		if (StringUtils.isNotBlank(this.cedula)) {
			where.add(cb.equal(poll.get("negociacionWrapper").get("tbQoCliente").get("cedulaCliente"), this.cedula));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
