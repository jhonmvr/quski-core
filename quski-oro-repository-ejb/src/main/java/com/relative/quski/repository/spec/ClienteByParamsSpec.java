package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;

public class ClienteByParamsSpec extends AbstractSpecification<TbQoCliente> {
	
	private String identificacion;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String telefono;
	private String celular;
	private String correo;
	private String nombreCompleto;
	private EstadoEnum estado;
	
	
	public ClienteByParamsSpec(String identificacion, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno,
			String telefono, String celular, String correo, String nombreCompleto, EstadoEnum estado) {
		super();
		this.identificacion = identificacion;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
		this.nombreCompleto = nombreCompleto;
		this.estado = estado;
	}

	public boolean isSatisfiedBy(TbQoCliente tb) {
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCliente> poll, CriteriaBuilder cb) {
		List<Predicate> where = new ArrayList<Predicate>();
		 
		if(StringUtils.isNotBlank(this.identificacion)) {
			where.add(cb.like(poll.<String>get("cedulaCliente"), "%" + this.identificacion + "%"));
		}
		if(StringUtils.isNotBlank(this.primerNombre)) {
			where.add(cb.like(poll.<String>get("primerNombre"), "%" + this.primerNombre.toUpperCase() + "%"));
		}
		if(StringUtils.isNotBlank(this.apellidoPaterno)) {
			where.add(cb.like(poll.<String>get("apellidoPaterno"), "%" + this.apellidoPaterno.toUpperCase() + "%"));
		}
		if(StringUtils.isNotBlank(this.segundoNombre)) {
			where.add(cb.like(poll.<String>get("segundoNombre"), "%" + this.segundoNombre.toUpperCase() + "%"));
		}
		if(StringUtils.isNotBlank(this.apellidoMaterno)) {
			where.add(cb.like(poll.<String>get("apellidoMaterno"), "%" + this.apellidoMaterno.toUpperCase() + "%"));
		}
		if(StringUtils.isNotBlank(this.telefono)) {
			where.add(cb.like(poll.<String>get("telefonoFijo"), "%" + this.telefono + "%"));
		}
		if(StringUtils.isNotBlank(this.celular)) {
			where.add(cb.like(poll.<String>get("telefonoCelular"), "%" + this.celular + "%"));
		}
		if(StringUtils.isNotBlank(this.correo)) {
			where.add(cb.like(poll.<String>get("email"), "%" + this.correo + "%"));
		}
		if(StringUtils.isNotBlank(this.nombreCompleto)) {
		     String[] arrOfStr = nombreCompleto.split(" ");
		     for (String a : arrOfStr) {
		    	 if(StringUtils.isNotBlank(a)) {
			    	 where.add(cb.like(poll.<String>get("nombreCompleto"), "%" + a.toUpperCase() + "%"));
		    	 }
		     }
		           
			
		}
		if(this.estado != null) {
			where.add(cb.equal(poll.<EstadoEnum>get("estado"), EstadoEnum.ACT));
		}
		
		return cb.and(where.toArray(new Predicate[]{}));
	}

}
