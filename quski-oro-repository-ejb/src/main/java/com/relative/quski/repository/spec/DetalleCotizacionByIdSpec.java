package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoPrecioOro;

public class DetalleCotizacionByIdSpec extends AbstractSpecification<TbQoPrecioOro> {

	private Long id;
	private EstadoEnum estado;



	public DetalleCotizacionByIdSpec(Long id, EstadoEnum estado) {
		super();
		this.id = id;
		this.estado = estado;
	}
	@Override
	public boolean isSatisfiedBy(TbQoPrecioOro t) {
		return false;
	}
	/*
	 * @Override public Predicate toPredicate(Root<TbQoCotizador> poll,
	 * CriteriaBuilder cb) {
	 * 
	 * String a = this.cedulaCliente;
	 * 
	 * if (StringUtils.isNotBlank(a)) {
	 * 
	 * where.add(cb.equal(poll.get("tbQoCliente").get("cedulaCliente"), a));
	 * where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT)); } return
	 * cb.and(where.toArray(new Predicate[0]));
	 * 
	 * where.add(cb.equal(poll.get("tbQoCliente").get("id"), this.idCliente));
	 *************************************************************************
	 * 
	 * @Override public Predicate toPredicate(Root<TbQoPrecioOro> poll,
	 * CriteriaBuilder cb) { Join<TbQoPrecioOro, TbQoCotizador> joinAgenciaContrato
	 * = poll.join("tbQoCotizador"); Join<TbQoCotizador, TbQoCliente>
	 * joinContratoJoya = joinAgenciaContrato.join("tbQoPrecioOro"); return
	 * cb.and(joinContratoJoya.get("tbQoCotizador").in(this.id)); //return cb.and(
	 * cb.equal(poll.get("tbQoCotizador").get("id"), idCotizador));
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public Predicate toPredicate(Root<TbQoPrecioOro> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<>();
		//Join<TbQoPrecioOro, TbQoCotizador> joinAgenciaContrato = poll.join("tbQoCotizador");
		//Join<TbQoCotizador, TbQoCliente> joinContratoJoya = joinAgenciaContrato.join("tbQoPrecioOro");
		where.add(cb.equal(poll.get("tbQoCotizador").get("id"), this.id));
		where.add(cb.equal(poll.get("estado"), EstadoEnum.ACT));
	//	where.add(cb.equal(joinContratoJoya.<Long>get("id"),this.id));
		
		

		return cb.and(where.toArray(new Predicate[0]));

	}
}