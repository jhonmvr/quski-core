package com.relative.quski.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.persistence.AbstractSpecification;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoTipoDocumento;



public class DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec  extends AbstractSpecification<TbQoDocumentoHabilitante> {
	
	
 private Long idTipoDocumento;
 private String identificacionCliente;
 private Long idCotizador;	
 private Long idNegociacion;

	
	 
	 

	public DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec(Long idTipoDocumento, String identificacionCliente, Long idCotizador, Long idNegociacion) {
	
		this.idTipoDocumento = idTipoDocumento;
		this.identificacionCliente = identificacionCliente;
		this.idCotizador=idCotizador;
		this.idNegociacion=idNegociacion;
		
	}

	public DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec() {}
	 
		@Override
		public boolean isSatisfiedBy(TbQoDocumentoHabilitante arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Predicate toPredicate(Root<TbQoDocumentoHabilitante> poll, CriteriaBuilder cb) {
			List<Predicate> where = new ArrayList<Predicate>();
			if(StringUtils.isNotBlank(this.identificacionCliente)) {
				where.add(cb.equal(poll.<TbQoCliente>get("tbQoCliente").<String>get("cedulaCliente"), this.identificacionCliente));
			}
			if(idTipoDocumento != null) {
				where.add(cb.equal(poll.<TbQoTipoDocumento>get("tbQoTipoDocumento").<Long>get("id"),idTipoDocumento ));
			}
			if(this.idCotizador != null) {
				where.add(cb.equal(poll.<TbQoCotizador>get("tbQoCotizador").<Long>get("id"), idCotizador));
			}
			if(this.idNegociacion != null) {
				where.add(cb.equal(poll.<TbQoNegociacion>get("tbQoNegociacion").<Long>get("id"), idNegociacion));
			}
			return cb.and(where.toArray(new Predicate[]{}));
		}
		
		
		
}
