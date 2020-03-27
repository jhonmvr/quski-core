package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoNegociacionByParamsSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	
	private String fechaDesde;
	private String fechaHasta;
	private String codigoOperacion;
	private String proceso;
	private String identificacion;
	private String agencia;
	
	public CreditoNegociacionByParamsSpec(String fechaDesde, String fechaHasta, String codigoOperacion,
			String proceso, String identificacion,  String agencia) {
		super();
		
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.codigoOperacion = codigoOperacion;
		this.proceso = proceso;
		this.identificacion = identificacion;
		this.agencia = agencia;
		
	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> credito, CriteriaBuilder cb) {
		
		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();
		
		this.codigoOperacion = this.codigoOperacion.trim();
		this.identificacion = this.identificacion.trim();
		
		if(StringUtils.isNotBlank(this.fechaDesde)) {
			@SuppressWarnings("deprecation")
			Date fecha = new Date(this.fechaDesde);
			patientLevelPredicates.add(cb.greaterThanOrEqualTo(credito.<Date>get("fechaCreacion"), fecha));
		}
		
		if(StringUtils.isNotBlank(this.fechaHasta)) {
			@SuppressWarnings("deprecation")
			Date fecha = new Date(this.fechaHasta);
			patientLevelPredicates.add(cb.lessThanOrEqualTo(credito.<Date>get("fechaCreacion"), fecha));
		}
		
		if(StringUtils.isNotBlank(this.codigoOperacion)) {
			patientLevelPredicates.add(cb.like(credito.<String>get("codigo"), "%" + this.codigoOperacion + "%"));
		}
		if(StringUtils.isNotBlank(this.proceso)) {
			patientLevelPredicates.add(cb.equal(credito.get("tbQoProceso").<String>get("id"), this.proceso));
		}
		if(StringUtils.isNotBlank(this.identificacion)) {
			patientLevelPredicates.add(cb.like(credito.<TbQoCliente>get("tbQoCliente").<String>get("identificacion"), "%" + this.identificacion + "%"));
		}
	
		if(StringUtils.isNotBlank(this.agencia)) {
			patientLevelPredicates.add(cb.equal(credito.get("tbQoAgencia").get("nombreAgencia"), this.agencia));
		}
		
		return cb.and(patientLevelPredicates.toArray(new Predicate[]{}));
	}

}
