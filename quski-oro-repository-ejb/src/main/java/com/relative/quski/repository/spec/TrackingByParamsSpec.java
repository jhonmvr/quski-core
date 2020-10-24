package com.relative.quski.repository.spec;

//import java.time.Period;
//import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.wrapper.TrackingWrapper;

public class TrackingByParamsSpec extends AbstractSpecification<TbQoTracking> {

	private ActividadEnum actividad;
	private ProcesoEnum proceso;
	private SeccionEnum seccion;
	private String codigoBpm;
	private String codigoOperacionSoftbank;
	private String usuarioCreacion;

	private Date fechaDesde;
	private Date fechaHasta;
	
	public TrackingByParamsSpec(TrackingWrapper wp) {
		super();
		this.proceso = wp.getProceso();
		this.actividad = wp.getActividad();
		this.seccion = wp.getSeccion();
		this.codigoBpm = wp.getCodigoBpm();
		this.codigoOperacionSoftbank = wp.getCodigoOperacionSoftbank();
		this.usuarioCreacion = wp.getUsuarioCreacion();
		this.fechaDesde = wp.getFechaDesde();
		this.fechaHasta = wp.getFechaHasta();
		
}

	@Override
	public Predicate toPredicate(Root<TbQoTracking> poll, CriteriaBuilder cb) {
		
		List<Predicate> where = new ArrayList<Predicate>();
		
		if (this.proceso != null) {
			where.add(cb.equal(poll.<ProcesoEnum>get("proceso"),this.proceso));
		}
		if (this.actividad != null) {
			where.add(cb.equal(poll.<ActividadEnum>get("actividad"), this.actividad));
		}
		if (this.seccion != null) {
			where.add(cb.equal(poll.<SeccionEnum>get("seccion"), this.seccion));
		}
		if (this.codigoBpm != null) {
			where.add(cb.equal(poll.<String>get("codigoBpm"), this.codigoBpm));
		}
		if (this.codigoOperacionSoftbank != null) {
			where.add(cb.equal(poll.<String>get("codigoOperacionSoftbank"), this.codigoOperacionSoftbank));
		}
		if (this.usuarioCreacion != null) {
			where.add(cb.equal(poll.<String>get("usuarioCreacion"), this.usuarioCreacion));
		}
		
		if (this.fechaDesde != null) {
			where.add(cb.greaterThanOrEqualTo(poll.<Date>get("fechaCreacion"),this.fechaDesde));
		}
		
		if (this.fechaHasta != null) {
			where.add(cb.lessThanOrEqualTo(poll.<Date>get("fechaCreacion"),this.fechaHasta));
		}
			
		return cb.and(where.toArray(new Predicate[] {}));
	}

	@Override
	public boolean isSatisfiedBy(TbQoTracking arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
