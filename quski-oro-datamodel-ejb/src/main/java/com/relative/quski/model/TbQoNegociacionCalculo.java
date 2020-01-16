package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.relative.quski.enums.EstadoEnum;

/**
 * The persistent class for the tb_mi_tipo_oro database table.
 * 
 */
@Entity
@Table(name="tb_qo_negociacion_calculo")
public class TbQoNegociacionCalculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_NEGOCIACION_CALCULO_ID_GENERATOR", sequenceName="SEQ_NEGOCIACION_CALCULO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_NEGOCIACION_CALCULO_ID_GENERATOR")
		private Long id;
		@Column(name="costo_custodia")
		private BigDecimal costoCustodia;
		@Temporal(TemporalType.DATE)
		@Column(name="fecha_actualizacion")
		private Date fechaActualizacion;
		@Temporal(TemporalType.DATE)
		@Column(name="fecha_creacion")
		private Date fechaCreacion;
		@ManyToOne
		@JoinColumn(name="id_cedito_negociacion")
		private TbQoCreditoNegociacion tbQoCreditoNegociacion;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public BigDecimal getCostoCustodia() {
			return costoCustodia;
		}
		public void setCostoCustodia(BigDecimal costoCustodia) {
			this.costoCustodia = costoCustodia;
		}
		public Date getFechaActualizacion() {
			return fechaActualizacion;
		}
		public void setFechaActualizacion(Date fechaActualizacion) {
			this.fechaActualizacion = fechaActualizacion;
		}
		public Date getFechaCreacion() {
			return fechaCreacion;
		}
		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}
		public TbQoCreditoNegociacion getTbQoCreditoNegociacion() {
			return tbQoCreditoNegociacion;
		}
		public void setTbQoCreditoNegociacion(TbQoCreditoNegociacion tbQoCreditoNegociacion) {
			this.tbQoCreditoNegociacion = tbQoCreditoNegociacion;
		}
		
		
}


