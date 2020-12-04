package com.relative.quski.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_qo_cliente database table.
 * 
 */
@Entity
@Table(name = "tb_qo_cliente")
public class TbQoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TB_QO_CLIENTE_ID_GENERATOR", sequenceName = "SEQ_CLIENTE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QO_CLIENTE_ID_GENERATOR")
	private Long id;

	@Column(name = "actividad_economica")
	private String actividadEconomica;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	private Long agencia;
	
	private String usuario;
	
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	private String campania;

	@Column(name = "canal_contacto")
	private String canalContacto;

	@Column(name = "nombre_completo")
	private String nombreCompleto;
	
	@Column(name = "cargas_familiares")
	private Long cargasFamiliares;

	@Column(name = "cedula_cliente")
	private String cedulaCliente;

	private Long edad;

	private String email;
	
	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	private String genero;

	@Column(name = "lugar_nacimiento")
	private String lugarNacimiento;

	private Long nacionalidad;

	@Column(name = "nivel_educacion")
	private String nivelEducacion;

	@Column(name = "primer_nombre")
	private String primerNombre;

	private String profesion;

	private String publicidad;

	@Column(name = "segundo_nombre")
	private String segundoNombre;

	@Column(name = "separacion_bienes")
	private String separacionBienes;

	@Column(name = "aprobado_web_mupi")
	private String aprobacionMupi;
	
	private BigDecimal ingresos;
	private BigDecimal egresos;
	private BigDecimal pasivos;
	private BigDecimal activos;

	// bi-directional many-to-one association to TbQoArchivoCliente
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoArchivoCliente> tbQoArchivoClientes;

	// bi-directional many-to-one association to TbQoCotizador
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoCotizador> tbQoCotizador;

	// bi-directional many-to-one association to TbQoDireccionCliente
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoDireccionCliente> tbQoDireccionClientes;

	// bi-directional many-to-one association to TbQoDocumentoHabilitante
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes;

	// bi-directional many-to-one association to TbQoNegociacion
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoNegociacion> tbQoNegociacions;

	// bi-directional many-to-one association to TbQoReferenciaPersonal
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoReferenciaPersonal> tbQoReferenciaPersonals;
	
	// bi-directional many-to-one association to TbQoReferenciaPersonal
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoTelefonoCliente> tbQoTelefonoClientes;
	
	// bi-directional many-to-one association to TbQoReferenciaPersonal
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoCuentaBancariaCliente> tbQoCuentaBancariaClientes;
	
	// bi-directional many-to-one association to TbQoReferenciaPersonal
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoDatoTrabajoCliente> tbQoDatoTrabajoClientes;
	
	// bi-directional many-to-one association to TbQoRiesgoAcumulado
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoRiesgoAcumulado> tbQoRiesgoAcumulados;

	public TbQoCliente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActividadEconomica() {
		return this.actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public Long getAgencia() {
		return agencia;
	}

	public void setAgencia(Long agencia) {
		this.agencia = agencia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCampania() {
		return this.campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getCanalContacto() {
		return this.canalContacto;
	}

	public void setCanalContacto(String canalContacto) {
		this.canalContacto = canalContacto;
	}

	public Long getCargasFamiliares() {
		return this.cargasFamiliares;
	}

	public void setCargasFamiliares(Long cargasFamiliares) {
		this.cargasFamiliares = cargasFamiliares;
	}

	public String getCedulaCliente() {
		return this.cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public Long getEdad() {
		return this.edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLugarNacimiento() {
		return this.lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public Long getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(Long nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getAprobacionMupi() {
		return aprobacionMupi;
	}

	public void setAprobacionMupi(String aprobacionMupi) {
		this.aprobacionMupi = aprobacionMupi;
	}

	public String getNivelEducacion() {
		return this.nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getPublicidad() {
		return this.publicidad;
	}

	public void setPublicidad(String publicidad) {
		this.publicidad = publicidad;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getSeparacionBienes() {
		return this.separacionBienes;
	}

	public void setSeparacionBienes(String separacionBienes) {
		this.separacionBienes = separacionBienes;
	}

	public List<TbQoArchivoCliente> getTbQoArchivoClientes() {
		return this.tbQoArchivoClientes;
	}

	public void setTbQoArchivoClientes(List<TbQoArchivoCliente> tbQoArchivoClientes) {
		this.tbQoArchivoClientes = tbQoArchivoClientes;
	}

	public TbQoArchivoCliente addTbQoArchivoCliente(TbQoArchivoCliente tbQoArchivoCliente) {
		getTbQoArchivoClientes().add(tbQoArchivoCliente);
		tbQoArchivoCliente.setTbQoCliente(this);

		return tbQoArchivoCliente;
	}

	public TbQoArchivoCliente removeTbQoArchivoCliente(TbQoArchivoCliente tbQoArchivoCliente) {
		getTbQoArchivoClientes().remove(tbQoArchivoCliente);
		tbQoArchivoCliente.setTbQoCliente(null);

		return tbQoArchivoCliente;
	}

	public List<TbQoCotizador> getTbQoCotizador() {
		return tbQoCotizador;
	}

	public void setTbQoCotizador(List<TbQoCotizador> tbQoCotizador) {
		this.tbQoCotizador = tbQoCotizador;
	}

	public TbQoCotizador addTbQoCotizador(TbQoCotizador tbQoCotizador) {
		getTbQoCotizador().add(tbQoCotizador);
		tbQoCotizador.setTbQoCliente(this);

		return tbQoCotizador;
	}

	public TbQoCotizador removeTbQoCotizador(TbQoCotizador tbQoCotizador) {
		getTbQoCotizador().remove(tbQoCotizador);
		tbQoCotizador.setTbQoCliente(null);

		return tbQoCotizador;
	}

	public List<TbQoDireccionCliente> getTbQoDireccionClientes() {
		return this.tbQoDireccionClientes;
	}

	public void setTbQoDireccionClientes(List<TbQoDireccionCliente> tbQoDireccionClientes) {
		this.tbQoDireccionClientes = tbQoDireccionClientes;
	}

	public TbQoDireccionCliente addTbQoDireccionCliente(TbQoDireccionCliente tbQoDireccionCliente) {
		getTbQoDireccionClientes().add(tbQoDireccionCliente);
		tbQoDireccionCliente.setTbQoCliente(this);

		return tbQoDireccionCliente;
	}

	public TbQoDireccionCliente removeTbQoDireccionCliente(TbQoDireccionCliente tbQoDireccionCliente) {
		getTbQoDireccionClientes().remove(tbQoDireccionCliente);
		tbQoDireccionCliente.setTbQoCliente(null);

		return tbQoDireccionCliente;
	}

	public List<TbQoDocumentoHabilitante> getTbQoDocumentoHabilitantes() {
		return this.tbQoDocumentoHabilitantes;
	}

	public void setTbQoDocumentoHabilitantes(List<TbQoDocumentoHabilitante> tbQoDocumentoHabilitantes) {
		this.tbQoDocumentoHabilitantes = tbQoDocumentoHabilitantes;
	}

	public TbQoDocumentoHabilitante addTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().add(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoCliente(this);

		return tbQoDocumentoHabilitante;
	}

	public TbQoDocumentoHabilitante removeTbQoDocumentoHabilitante(TbQoDocumentoHabilitante tbQoDocumentoHabilitante) {
		getTbQoDocumentoHabilitantes().remove(tbQoDocumentoHabilitante);
		tbQoDocumentoHabilitante.setTbQoCliente(null);

		return tbQoDocumentoHabilitante;
	}

	public List<TbQoNegociacion> getTbQoNegociacions() {
		return this.tbQoNegociacions;
	}

	public void setTbQoNegociacions(List<TbQoNegociacion> tbQoNegociacions) {
		this.tbQoNegociacions = tbQoNegociacions;
	}

	public TbQoNegociacion addTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		getTbQoNegociacions().add(tbQoNegociacion);
		tbQoNegociacion.setTbQoCliente(this);

		return tbQoNegociacion;
	}

	public TbQoNegociacion removeTbQoNegociacion(TbQoNegociacion tbQoNegociacion) {
		getTbQoNegociacions().remove(tbQoNegociacion);
		tbQoNegociacion.setTbQoCliente(null);

		return tbQoNegociacion;
	}

	public List<TbQoReferenciaPersonal> getTbQoReferenciaPersonals() {
		return this.tbQoReferenciaPersonals;
	}

	public void setTbQoReferenciaPersonals(List<TbQoReferenciaPersonal> tbQoReferenciaPersonals) {
		this.tbQoReferenciaPersonals = tbQoReferenciaPersonals;
	}

	public TbQoReferenciaPersonal addTbQoReferenciaPersonal(TbQoReferenciaPersonal tbQoReferenciaPersonal) {
		getTbQoReferenciaPersonals().add(tbQoReferenciaPersonal);
		tbQoReferenciaPersonal.setTbQoCliente(this);
		return tbQoReferenciaPersonal;
	}

	public TbQoReferenciaPersonal removeTbQoReferenciaPersonal(TbQoReferenciaPersonal tbQoReferenciaPersonal) {
		getTbQoReferenciaPersonals().remove(tbQoReferenciaPersonal);
		tbQoReferenciaPersonal.setTbQoCliente(null);

		return tbQoReferenciaPersonal;
	}

	public List<TbQoRiesgoAcumulado> getTbQoRiesgoAcumulados() {
		return this.tbQoRiesgoAcumulados;
	}

	public void setTbQoRiesgoAcumulados(List<TbQoRiesgoAcumulado> tbQoRiesgoAcumulados) {
		this.tbQoRiesgoAcumulados = tbQoRiesgoAcumulados;
	}

	public TbQoRiesgoAcumulado addTbQoRiesgoAcumulado(TbQoRiesgoAcumulado tbQoRiesgoAcumulado) {
		getTbQoRiesgoAcumulados().add(tbQoRiesgoAcumulado);
		tbQoRiesgoAcumulado.setTbQoCliente(this);

		return tbQoRiesgoAcumulado;
	}

	public TbQoRiesgoAcumulado removeTbQoRiesgoAcumulado(TbQoRiesgoAcumulado tbQoRiesgoAcumulado) {
		getTbQoRiesgoAcumulados().remove(tbQoRiesgoAcumulado);
		tbQoRiesgoAcumulado.setTbQoCliente(null);

		return tbQoRiesgoAcumulado;
	}

	public List<TbQoTelefonoCliente> getTbQoTelefonoClientes() {
		return tbQoTelefonoClientes;
	}

	public void setTbQoTelefonoClientes(List<TbQoTelefonoCliente> tbQoTelefonoClientes) {
		this.tbQoTelefonoClientes = tbQoTelefonoClientes;
	}

	public List<TbQoCuentaBancariaCliente> getTbQoCuentaBancariaClientes() {
		return tbQoCuentaBancariaClientes;
	}

	public void setTbQoCuentaBancariaClientes(List<TbQoCuentaBancariaCliente> tbQoCuentaBancariaClientes) {
		this.tbQoCuentaBancariaClientes = tbQoCuentaBancariaClientes;
	}

	public List<TbQoDatoTrabajoCliente> getTbQoDatoTrabajoClientes() {
		return tbQoDatoTrabajoClientes;
	}

	public void setTbQoDatoTrabajoClientes(List<TbQoDatoTrabajoCliente> tbQoDatoTrabajoClientes) {
		this.tbQoDatoTrabajoClientes = tbQoDatoTrabajoClientes;
	}

	public BigDecimal getIngresos() {
		return ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public BigDecimal getEgresos() {
		return egresos;
	}

	public void setEgresos(BigDecimal egresos) {
		this.egresos = egresos;
	}

	public BigDecimal getPasivos() {
		return pasivos;
	}

	public void setPasivos(BigDecimal pasivos) {
		this.pasivos = pasivos;
	}

	public BigDecimal getActivos() {
		return activos;
	}

	public void setActivos(BigDecimal activos) {
		this.activos = activos;
	}

 
	
	

 

	
 
	
	

}