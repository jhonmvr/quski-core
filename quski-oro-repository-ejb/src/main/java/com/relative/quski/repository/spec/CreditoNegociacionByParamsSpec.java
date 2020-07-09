package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;

public class CreditoNegociacionByParamsSpec extends AbstractSpecification<TbQoCreditoNegociacion> {

	private String fechaDesde;
	private String fechaHasta;

	private String identificacion;
	private String agencia;

	public CreditoNegociacionByParamsSpec(String fechaDesde, String fechaHasta, String identificacion, String agencia) {

		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
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

	

		if (StringUtils.isNotBlank(this.fechaDesde)) {

			Calendar cal = Calendar.getInstance();
			Date fecha = cal.getTime();
			Log.info("+++++FECHA>>>>>>>>>>" + fecha);

			patientLevelPredicates.add(cb.greaterThanOrEqualTo(credito.<Date>get("fechaCreacion"), fecha));
		}

		if (StringUtils.isNotBlank(this.fechaHasta)) {
			Calendar cal = Calendar.getInstance();
			Date fecha = cal.getTime();
			patientLevelPredicates.add(cb.lessThanOrEqualTo(credito.<Date>get("fechaCreacion"), fecha));
		}
		if (StringUtils.isNotBlank(this.identificacion)) {
			patientLevelPredicates.add(cb.like(credito.<TbQoCliente>get("tbQoCliente").<String>get("identificacion"),
					"%" + this.identificacion + "%"));
		}

		if (StringUtils.isNotBlank(this.agencia)) {
			patientLevelPredicates.add(cb.equal(credito.get("tbQoAgencia").get("nombreAgencia"), this.agencia));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
