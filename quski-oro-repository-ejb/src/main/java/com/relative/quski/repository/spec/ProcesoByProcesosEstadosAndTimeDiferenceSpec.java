package com.relative.quski.repository.spec;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;



public class ProcesoByProcesosEstadosAndTimeDiferenceSpec  extends AbstractSpecification<TbQoProceso> {
		
	private List<ProcesoEnum> procesos;
	private List<EstadoProcesoEnum> estados;
	private Long horaBase;

 
	public ProcesoByProcesosEstadosAndTimeDiferenceSpec(List<ProcesoEnum> procesos,	List<EstadoProcesoEnum> estados, Long horaBase) {
		super();
		this.procesos = procesos;
		this.horaBase = horaBase;
		this.estados = estados;
	}

	
	 
		@Override
		public boolean isSatisfiedBy(TbQoProceso arg0) {
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoProceso> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if(this.procesos != null && !this.procesos.isEmpty()) {
				where.add(poll.<ProcesoEnum>get("proceso").in(this.procesos));
			}
			if(this.estados != null && !this.estados.isEmpty()) {
				where.add(poll.<EstadoProcesoEnum>get("estadoProceso").in( this.estados ) );
			}
			if( this.horaBase != null ) {
				long result = new Timestamp( System.currentTimeMillis() ).getTime() - horaBase;
				Timestamp resultTime = new Timestamp( result );
				where.add(cb.lessThanOrEqualTo(poll.get("horaAprobador"), resultTime));
			}
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
