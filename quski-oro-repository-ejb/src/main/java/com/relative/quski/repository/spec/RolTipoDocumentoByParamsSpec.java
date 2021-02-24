package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoRolTipoDocumento;



public class RolTipoDocumentoByParamsSpec  extends AbstractSpecification<TbQoRolTipoDocumento> {
	
	
 private Long idTipoDocumento;
 private Long idRol;	
 private List<ProcessEnum> proceso;
 private List<EstadoOperacionEnum> estadoOperacion;
 

	public RolTipoDocumentoByParamsSpec(Long idTipoDocumento, Long idRol, List<ProcessEnum> proceso,List<EstadoOperacionEnum> estadoOperacion) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idRol = idRol;
		this.proceso = proceso;
		this.estadoOperacion = estadoOperacion;
	}

	public RolTipoDocumentoByParamsSpec(Long idTipoDocumento, Long idRol, ProcessEnum proceso,EstadoOperacionEnum estadoOperacion) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idRol = idRol;
		if(proceso != null) {
			this.proceso = new ArrayList<>();
			this.proceso.add(proceso);
		}
		if(estadoOperacion != null) {
			this.estadoOperacion = new ArrayList<>();
			this.estadoOperacion.add(estadoOperacion);
		}
	}
	
	public RolTipoDocumentoByParamsSpec(Long idTipoDocumento, Long idRol, ProcessEnum proceso,List<EstadoOperacionEnum> estadoOperacion) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.idRol = idRol;
		if(proceso != null) {
			this.proceso = new ArrayList<>();
			this.proceso.add(proceso);
		}
		this.estadoOperacion = estadoOperacion;
	}
	 
		@Override
		public boolean isSatisfiedBy(TbQoRolTipoDocumento arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoRolTipoDocumento> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if(idRol != null) {
				where.add(cb.equal(poll.<Long>get("idRol"), this.idRol ));
			}
			if(idTipoDocumento != null) {
				where.add(cb.equal(poll.<Long>get("idTipoDocumento"),idTipoDocumento ));
			}
			if(this.proceso != null && !this.proceso.isEmpty()) {
				where.add(poll.<ProcessEnum>get("proceso").in(proceso));
			}
			if(this.estadoOperacion != null && !this.estadoOperacion.isEmpty()) {
				where.add(poll.<EstadoOperacionEnum>get("estadoOperacion").in(estadoOperacion));
			}
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
