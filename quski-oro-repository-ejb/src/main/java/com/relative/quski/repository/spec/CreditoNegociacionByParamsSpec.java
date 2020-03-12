package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoNegociacionByParamsSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	
	private Date fechaDesde;
	private Date fechaHasta;
	private String codigoOperacion;
	private EstadoOperacionEnum estado;
	private String identificacion;
	

	
	
	public CreditoNegociacionByParamsSpec(Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoOperacionEnum estado, String identificacion) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.codigoOperacion = codigoOperacion;
		this.identificacion = identificacion;
		this.estado = estado;
	}

	public boolean isSatisfiedBy(TbQoCreditoNegociacion tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		if(StringUtils.isNotBlank(this.identificacion)) {
			where.add(cb.like(poll.<String>get("identificacion"), "%" + this.identificacion + "%"));
		}
		if(StringUtils.isNotBlank((CharSequence) this.fechaDesde)) {
			where.add(cb.like(poll.<String>get("fechaDesde"), "%" + this.fechaDesde + "%"));
		}
		if(StringUtils.isNotBlank((CharSequence) this.fechaHasta)) {
			where.add(cb.like(poll.<String>get("fechaHasta"), "%" + this.fechaHasta + "%"));
		}
		if(StringUtils.isNotBlank(this.codigoOperacion)) {
			where.add(cb.like(poll.<String>get("codigoOperacion"), "%" + this.codigoOperacion + "%"));
		}
		if(StringUtils.isNotBlank(this.identificacion)) {
			where.add(cb.like(poll.<String>get("identificacion"), "%" + this.identificacion + "%"));
		}
		
		
		if(this.estado != null) {
			where.add(cb.equal(poll.<EstadoEnum>get("estado"), this.estado));
		}
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
