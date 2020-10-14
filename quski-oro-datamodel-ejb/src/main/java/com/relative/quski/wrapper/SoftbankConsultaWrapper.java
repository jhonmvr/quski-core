package com.relative.quski.wrapper;

import java.io.Serializable;
import com.relative.quski.util.QuskiOroConstantes;

public class SoftbankConsultaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public SoftbankConsultaWrapper( String identificacion ) {
		this.identificacion = identificacion;
		this.idTipoIdentificacion = QuskiOroConstantes.TIPO_CEDULA;
		
	}
	private String identificacion;
	
	private Integer idTipoIdentificacion ;
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Integer getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
}
