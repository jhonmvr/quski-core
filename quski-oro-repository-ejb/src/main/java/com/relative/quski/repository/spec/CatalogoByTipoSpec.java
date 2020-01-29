package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCatalogo;

public class CatalogoByTipoSpec extends AbstractSpecification<TbQoCatalogo> {
	private String nombre;
	public CatalogoByTipoSpec(String nombre) {

		this.nombre = nombre ;
	}

	public CatalogoByTipoSpec() {
	}

	@Override
	public boolean isSatisfiedBy(TbQoCatalogo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCatalogo> poll, CriteriaBuilder cb) {
		List<Predicate>  arg =new ArrayList<>();
		if(StringUtils.isNotBlank(this.nombre)) {
			arg.add(cb.like(poll.<String>get("tipoCatalogo"),"%"+this.nombre+"%"));	
		}arg.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		return cb.and(arg.toArray(new Predicate []{}));
	}

}
