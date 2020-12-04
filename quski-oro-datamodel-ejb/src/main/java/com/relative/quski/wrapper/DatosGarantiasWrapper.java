package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.List;

public class DatosGarantiasWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoTipoFunda; 
	private String numeroFundaJoya;
	private List<JoyaWrapper> garantias;
	private String uriImagenSinFunda;
	private String uriImagenConFunda;
	
	public DatosGarantiasWrapper() {
		super();
	}
	
	public String getCodigoTipoFunda() {
		return codigoTipoFunda;
	}
	public void setCodigoTipoFunda(String codigoTipoFunda) {
		this.codigoTipoFunda = codigoTipoFunda;
	}
	public List<JoyaWrapper> getGarantias() {
		return garantias;
	}
	public void setGarantias(List<JoyaWrapper> garantias) {
		this.garantias = garantias;
	}
	public String getUriImagenSinFunda() {
		return uriImagenSinFunda;
	}
	public void setUriImagenSinFunda(String uriImagenSinFunda) {
		this.uriImagenSinFunda = uriImagenSinFunda;
	}
	public String getUriImagenConFunda() {
		return uriImagenConFunda;
	}
	public void setUriImagenConFunda(String uriImagenConFunda) {
		this.uriImagenConFunda = uriImagenConFunda;
	}

	public String getNumeroFundaJoya() {
		return numeroFundaJoya;
	}

	public void setNumeroFundaJoya(String numeroFundaJoya) {
		this.numeroFundaJoya = numeroFundaJoya;
	} 

}
