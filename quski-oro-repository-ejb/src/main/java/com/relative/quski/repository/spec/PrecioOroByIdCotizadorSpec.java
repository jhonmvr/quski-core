package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoPrecioOro;
/**
 * Esta clase es el spect para precio Oro por cotizador realiza la validación por el id de la cotización
 * @author KLÉBER GUERRA Relative Engine
 *  
 */
public class PrecioOroByIdCotizadorSpec extends AbstractSpecification<TbQoPrecioOro> {
	private Long id;

	public PrecioOroByIdCotizadorSpec(Long id) {
		this.id = id;
	}

	public PrecioOroByIdCotizadorSpec() {}

	@Override
	public boolean isSatisfiedBy(TbQoPrecioOro arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoPrecioOro> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.<TbQoCotizador>get("tbQoCotizador").get("id"), this.id));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
		return cb.and(where.toArray(new Predicate[] {}));

	}

}
