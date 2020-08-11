package com.relative.quski.repository.spec;

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
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoNegociacion;

public class CreditoNegociacionByParamsSpec extends AbstractSpecification<TbQoCreditoNegociacion> {
	
	private String codigoOperacion;
	private String fechaDesde;
	private String fechaHasta;
	private ProcesoEnum proceso;
	private String identificacion;
	private Long agencia;
	private String cliente;
	private EstadoEnum estado;

	public CreditoNegociacionByParamsSpec(String fechaDesde, String fechaHasta, String identificacion, Long agencia, String codigoOperacion, ProcesoEnum proceso,
			String cliente,  EstadoEnum estado) {

		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.identificacion = identificacion;
		this.agencia = agencia;
		this.codigoOperacion = codigoOperacion;
		this.proceso = proceso;
		this.cliente = cliente;
		this.estado = estado;

	}

	@Override
	public boolean isSatisfiedBy(TbQoCreditoNegociacion arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Predicate toPredicate(Root<TbQoCreditoNegociacion> credito, CriteriaBuilder cb) {

		List<Predicate> patientLevelPredicates = new ArrayList<Predicate>();

	

		if (StringUtils.isNotBlank(this.fechaDesde)) {

			Calendar cal = Calendar.getInstance();
			Date fecha = cal.getTime();
			Log.info("+++++FECHA>>>>>>>>>>" + fecha);

			patientLevelPredicates.add(cb.greaterThanOrEqualTo(credito.<Date>get("fechaCreacion"), fecha));
		}

		if (StringUtils.isNotBlank(this.fechaHasta)) {
			Calendar cal = Calendar.getInstance();
			Date fecha = cal.getTime();
			patientLevelPredicates.add(cb.lessThanOrEqualTo(credito.<Date>get("fechaCreacion"), fecha));
		}
		if(StringUtils.isNotBlank(this.cliente)) {
			patientLevelPredicates.add(cb.or(cb.like(credito.<TbQoNegociacion>get("tbQoNegociacion").<TbQoCliente>get("tbQoCliente").<String>get("primer_nombre"), "%" + this.cliente.trim() + "%")));
		}
		
		if (StringUtils.isNotBlank(this.identificacion)) {
			patientLevelPredicates.add(cb.like(credito.<TbQoNegociacion>get("tbQoNegociacion").<TbQoCliente>get("tbQoCliente").<String>get("identificacion"),
					"%" + this.identificacion + "%"));
		}

		if (this.agencia != null) {
			patientLevelPredicates.add(cb.equal(credito.get("tbQoAgencia").get("nombreAgencia"), this.agencia));
		}
		if(this.estado != null ) {
			patientLevelPredicates.add(credito.<EstadoEnum>get("estado").in(this.estado));
		}
		if(this.proceso != null ) {
			patientLevelPredicates.add(credito.<EstadoEnum>get("proceso").in(this.proceso));
		}
		if(StringUtils.isNotBlank(this.codigoOperacion)) {
			patientLevelPredicates.add(cb.like(credito.<String>get("codigo"), "%" + this.codigoOperacion.trim() + "%"));
		}

		return cb.and(patientLevelPredicates.toArray(new Predicate[] {}));
	}

}
