package com.relative.quski.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.relative.quski.enums.EstadoProcesoEnum;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_qo_compromiso_pago")
public class TbQoCompromisoPago implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_COMPROMISO_ID_GENERATOR", sequenceName = "TB_QO_COMPROMISO_PAGO_ID_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_COMPROMISO_ID_GENERATOR")
	private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_negociacion")
    private TbQoNegociacion idNegociacion;

    @Column(name = "codigo")
    private String codigo;

    
    @Column(name = "codigo_operacion")
    private String codigoOperacion;
    
    @Column(name = "tipo_compromiso")
    private String tipoCompromiso;
    
	@Enumerated(EnumType.STRING)
	@Column(name="estado_compromiso")
	private EstadoProcesoEnum estadoCompromiso;

    @Column(name = "fecha_compromiso_pago")
    private Timestamp fechaCompromisoPago;
    
    @Column(name = "usuario_solicitud")
    private String usuarioSolicitud;

    @Column(name = "usuario_aprobador")
    private String usuarioAprobador;
    
    @Column(name = "observacion_solicitud")
    private String observacionSolicitud;
    
    @Column(name = "observacion_aprobador")
    private String observacionAprobador;

    @Column(name = "fecha_solicitud")
    private Timestamp fechaSolicitud;

    @Column(name = "fecha_aprobador")
    private Timestamp fechaAprobador;

    @Column(name = "numero_operacion")
    private String numeroOperacion;
    
    @Column(name = "fecha_compromiso_pago_anterior")
    private Timestamp fechaCompromisoPagoAnterior;
    
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    
    @Column(name = "correo_solicitud")
    private String correoSolicitud;
    
    @Column(name = "identificacion_cliente")
    private String identificacionCliente;
    
    
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getCorreoSolicitud() {
		return correoSolicitud;
	}

	public void setCorreoSolicitud(String correoSolicitud) {
		this.correoSolicitud = correoSolicitud;
	}

	public Timestamp getFechaCompromisoPagoAnterior() {
		return fechaCompromisoPagoAnterior;
	}

	public void setFechaCompromisoPagoAnterior(Timestamp fechaCompromisoPagoAnterior) {
		this.fechaCompromisoPagoAnterior = fechaCompromisoPagoAnterior;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbQoNegociacion getIdNegociacion() {
		return idNegociacion;
	}

	public void setIdNegociacion(TbQoNegociacion idNegociacion) {
		this.idNegociacion = idNegociacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getTipoCompromiso() {
		return tipoCompromiso;
	}

	public void setTipoCompromiso(String tipoCompromiso) {
		this.tipoCompromiso = tipoCompromiso;
	}

	public EstadoProcesoEnum getEstadoCompromiso() {
		return estadoCompromiso;
	}

	public void setEstadoCompromiso(EstadoProcesoEnum estadoCompromiso) {
		this.estadoCompromiso = estadoCompromiso;
	}

	public Timestamp getFechaCompromisoPago() {
		return fechaCompromisoPago;
	}

	public void setFechaCompromisoPago(Timestamp fechaCompromisoPago) {
		this.fechaCompromisoPago = fechaCompromisoPago;
	}

	public String getUsuarioSolicitud() {
		return usuarioSolicitud;
	}

	public void setUsuarioSolicitud(String usuarioSolicitud) {
		this.usuarioSolicitud = usuarioSolicitud;
	}

	public String getUsuarioAprobador() {
		return usuarioAprobador;
	}

	public void setUsuarioAprobador(String usuarioAprobador) {
		this.usuarioAprobador = usuarioAprobador;
	}

	public String getObservacionSolicitud() {
		return observacionSolicitud;
	}

	public void setObservacionSolicitud(String observacionSolicitud) {
		this.observacionSolicitud = observacionSolicitud;
	}

	public String getObservacionAprobador() {
		return observacionAprobador;
	}

	public void setObservacionAprobador(String observacionAprobador) {
		this.observacionAprobador = observacionAprobador;
	}

	public Timestamp getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Timestamp getFechaAprobador() {
		return fechaAprobador;
	}

	public void setFechaAprobador(Timestamp fechaAprobador) {
		this.fechaAprobador = fechaAprobador;
	}

}