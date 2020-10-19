package com.relative.quski.repository.spec;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.wrapper.BusquedaTrackingWrapper;

public class TrackingByParamsSpec extends AbstractSpecification<TbQoTracking> {

	private ActividadEnum actividad;
	private ProcesoEnum proceso;
	private SeccionEnum seccion;
	private EstadoEnum estado;
	private String codigoBpm;
	private String codigoOperacionSoftbank;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private TbQoTracking TbQoTracking;
	
	public TrackingByParamsSpec(BusquedaTrackingWrapper wp) {
		super();
		this.proceso = proceso;
		this.actividad = actividad;
		this.seccion = seccion;
		this.estado = estado;
		this.codigoBpm = codigoBpm;
		this.codigoOperacionSoftbank = codigoOperacionSoftbank;
}


	@Override
	public boolean isSatisfiedBy(TbQoTracking arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Predicate toPredicate(Root<TbQoTracking> poll, CriteriaBuilder cb) {
		return cb.and(cb.equal(poll.get("TbQoTracking").get("proceso"), this.proceso));
	}

}
