package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoAgencia;

public class AgenciaByParamsSpec extends AbstractSpecification<TbQoAgencia> {
	private String nombreAgencia;
	private String direccionAgencia;

	public AgenciaByParamsSpec(String nombreAgencia, String nombreProvincia) {

		this.nombreAgencia = nombreAgencia;
		this.direccionAgencia = nombreProvincia;
	}

	@Override
	public boolean isSatisfiedBy(TbQoAgencia arg0) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoAgencia> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		if (StringUtils.isNotBlank(this.nombreAgencia)) {
			where.add(cb.equal(poll.get("nombreAgencia"), this.nombreAgencia));
		}
		if (StringUtils.isNotBlank(this.direccionAgencia)) {
			where.add(cb.equal(poll.get("direccionAgencia"), this.direccionAgencia));
		}
		where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[0]));
	}

}
