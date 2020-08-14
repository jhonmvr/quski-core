package com.relative.quski.repository.spec;

import java.math.BigDecimal;
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
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoFunda;

public class FundaByParamsSpec extends AbstractSpecification<TbQoFunda> {

	private String codigo;
	private BigDecimal peso;
	private EstadoEnum estado;


	public FundaByParamsSpec(String codigo, BigDecimal peso, EstadoEnum estado) {

		this.codigo = codigo;
		this.peso = peso;
		this.estado = estado;
	

	}

	@Override
	public boolean isSatisfiedBy(TbQoFunda arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoFunda> funda, CriteriaBuilder cb) {

		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

	

		if (StringUtils.isNotBlank(this.codigo)) {
			patientLevelPredicates.add(cb.equal(funda.<String>get("codigo"),
					 this.codigo ));
		}

		 if((this.peso!= null  && this.peso.compareTo(BigDecimal.ZERO) != 0)) {
			patientLevelPredicates.add(cb.equal(funda.get("peso"), this.peso));
		}
		 if(this.estado != null) {
			patientLevelPredicates.add(funda.<EstadoEnum>get("estado").in(this.estado));
			}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
