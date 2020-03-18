package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class AsignacionByParamsSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	private String codigoProceso;
	private String nombreAgencia;
	private String nombreProceso;
	private String cedula;

	

	public AsignacionByParamsSpec(String codigoProceso, String nombreAgencia, String nombreProceso, String cedula) {
		super();

		this.codigoProceso = codigoProceso;
		this.nombreAgencia = nombreAgencia;
		this.nombreProceso = nombreProceso;
		this.cedula = cedula;
		
	}
	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if (StringUtils.isNotBlank(this.codigoProceso)) {
			where.add(cb.equal(poll.get("tbQoProceso").get("codigoProceso"), this.codigoProceso));
		}
		if (StringUtils.isNotBlank(this.nombreAgencia)) {
			where.add(cb.equal(poll.get("tbQoAgencia").get("nombreAgencia"), this.nombreAgencia));
		}
		if (StringUtils.isNotBlank(this.nombreProceso)) {
			where.add(cb.equal(poll.get("tbQoProceso").get("nombreProceso"), this.nombreProceso));
		}
		if (StringUtils.isNotBlank(this.cedula)) {
			where.add(cb.equal(poll.get("tbQoNegociacion").get("tbQoCliente").get("cedulaCliente"), this.cedula));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
