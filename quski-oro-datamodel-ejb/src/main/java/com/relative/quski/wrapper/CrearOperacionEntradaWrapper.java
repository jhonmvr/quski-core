package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.relative.quski.model.TbQoRubro;


public class CrearOperacionEntradaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idTipoIdentificacion;
	private String identificacion;
	private String nombreCliente;
	private List<DatosImpComWrapper> datosImpCom;
	private DatosGarantiasWrapper datosGarantias;
	
	private String fechaEfectiva;
	private String codigoTablaAmortizacionQuski;
	private String codigoTipoCarteraQuski;
	private String codigoTipoPrestamo;
	private BigDecimal montoFinanciado;
	private BigDecimal montoSolicitado;
	private Long pagoDia;
	private String codigoGradoInteres;
	private DatosRegistroWrapper datosRegistro; 
	private List<DatosCuentaClienteWrapper> datosCuentaCliente;
	
	
	public CrearOperacionEntradaWrapper(String identificacion, String nombreCliente) {
		super();
		this.identificacion = identificacion;
		this.nombreCliente = nombreCliente;
		this.idTipoIdentificacion = Long.valueOf( 1 );
		
	}
	
	public void cargarImpCom(List<TbQoRubro> rubros) {
		List<DatosImpComWrapper> list = new ArrayList<>();
		rubros.forEach(e->{
			DatosImpComWrapper impCom = new DatosImpComWrapper(e.getCodigo(), e.getFormaPago(), e.getValor() );
			list.add( impCom );
		});
		this.setDatosImpCom(list);
	}



	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getFechaEfectiva() {
		return fechaEfectiva;
	}
	public void setFechaEfectiva(String fechaEfectiva) {
		this.fechaEfectiva = fechaEfectiva;
	}
	public String getCodigoTablaAmortizacionQuski() {
		return codigoTablaAmortizacionQuski;
	}
	public void setCodigoTablaAmortizacionQuski(String codigoTablaAmortizacionQuski) {
		this.codigoTablaAmortizacionQuski = codigoTablaAmortizacionQuski;
	}
	public String getCodigoTipoCarteraQuski() {
		return codigoTipoCarteraQuski;
	}
	public void setCodigoTipoCarteraQuski(String codigoTipoCarteraQuski) {
		this.codigoTipoCarteraQuski = codigoTipoCarteraQuski;
	}
	public String getCodigoTipoPrestamo() {
		return codigoTipoPrestamo;
	}
	public void setCodigoTipoPrestamo(String codigoTipoPrestamo) {
		this.codigoTipoPrestamo = codigoTipoPrestamo;
	}
	public BigDecimal getMontoFinanciado() {
		return montoFinanciado;
	}
	public void setMontoFinanciado(BigDecimal montoFinanciado) {
		this.montoFinanciado = montoFinanciado;
	}
	public Long getPagoDia() {
		return pagoDia;
	}
	public void setPagoDia(Long pagoDia) {
		this.pagoDia = pagoDia;
	}
	public String getCodigoGradoInteres() {
		return codigoGradoInteres;
	}
	public void setCodigoGradoInteres(String codigoGradoInteres) {
		this.codigoGradoInteres = codigoGradoInteres;
	}

	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public DatosGarantiasWrapper getDatosGarantias() {
		return datosGarantias;
	}
	public void setDatosGarantias(DatosGarantiasWrapper datosGarantias) {
		this.datosGarantias = datosGarantias;
	}
	public DatosRegistroWrapper getDatosRegistro() {
		return datosRegistro;
	}
	public void setDatosRegistro(DatosRegistroWrapper datosRegistro) {
		this.datosRegistro = datosRegistro;
	}
	public List<DatosImpComWrapper> getDatosImpCom() {
		return datosImpCom;
	}
	public void setDatosImpCom(List<DatosImpComWrapper> datosImpCom) {
		this.datosImpCom = datosImpCom;
	}
	public List<DatosCuentaClienteWrapper> getDatosCuentaCliente() {
		return datosCuentaCliente;
	}
	public void setDatosCuentaCliente(List<DatosCuentaClienteWrapper> datosCuentaCliente) {
		this.datosCuentaCliente = datosCuentaCliente;
	}
}
