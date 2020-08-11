package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoDetalleCredito;
/**
 * 
 * @author Jeroham Cadenas - Developer Twelve
 *
 */
public class DetalleCreditoByIdCotizadorSpec extends AbstractSpecification<TbQoDetalleCredito> {

private Long idCotizador;
	
	public DetalleCreditoByIdCotizadorSpec(Long idCotizador){
		this.idCotizador = idCotizador;
	}
	@Override
	public boolean isSatisfiedBy(TbQoDetalleCredito t) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoDetalleCredito> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		where.add(cb.equal(poll.get("tbQoCotizador").get("id"),this.idCotizador));			
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT ));
		return cb.and(where.toArray(new Predicate[0]));
			
	}
}
