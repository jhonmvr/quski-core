package com.relative.quski.wrapper;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * The persistent class for the tb_qo_agencia database table.
 * 
 */
@Entity
@Table(name="tb_qo_agencia")
public class AgenciaWrapper implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TB_QO_AGENCIA_ID_GENERATOR", sequenceName="SEG_TB_QO_AGENCIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TB_QO_AGENCIA_ID_GENERATOR")
	private Long id;


	@Column(name="nombre_agencia")
	private String nombreAgencia;

	
	public AgenciaWrapper() {
			
		}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreAgencia() {
		return this.nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

}
