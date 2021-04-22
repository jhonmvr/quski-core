package com.relative.quski.repository.spec;

//import java.time.Period;
//import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.wrapper.TrackingWrapper;

public class TrackingByParamsSpec extends AbstractSpecification<TbQoTracking> {

	private String actividad;
	private ProcesoEnum proceso;
	private String seccion;
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
		if (StringUtils.isNotBlank(this.actividad ) ) {
			where.add(cb.like(poll.<String>get("actividad"), "%"+this.actividad+"%"));
		}
		if (StringUtils.isNotBlank(this.seccion ) ) {
			where.add(cb.like(poll.<String>get("seccion"), "%"+this.seccion+"%"));
		}
		if (StringUtils.isNotBlank(this.codigoBpm )) {
			where.add(cb.like(poll.<String>get("codigoBpm"), "%"+this.codigoBpm+"%"));
		}
		if (StringUtils.isNotBlank(this.codigoOperacionSoftbank) ){
			where.add(cb.like(poll.<String>get("codigoOperacionSoftbank"),"%"+this.codigoOperacionSoftbank+"%"));
		}
		if (StringUtils.isNotBlank(this.usuarioCreacion )) {
			where.add(cb.like(poll.<String>get("usuarioCreacion"), "%"+this.usuarioCreacion+"%"));
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
