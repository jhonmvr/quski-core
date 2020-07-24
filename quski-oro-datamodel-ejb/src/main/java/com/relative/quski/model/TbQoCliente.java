package com.relative.quski.model;

import java.io.Serializable;
import javax.persistence.*;

import com.relative.quski.enums.EstadoEnum;

import java.math.BigDecimal;
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

	@Column(name = "actividad_economica_empresa")
	private String actividadEconomicaEmpresa;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apoderado_cliente")
	private String apoderadoCliente;

	private String campania;

	@Column(name = "canal_contacto")
	private String canalContacto;

	@Column(name = "cargas_familiares")
	private BigDecimal cargasFamiliares;

	private String cargo;

	@Column(name = "cedula_cliente")
	private String cedulaCliente;

	private BigDecimal edad;

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

	private String nacionalidad;

	@Column(name = "nivel_educacion")
	private String nivelEducacion;

	@Column(name = "nombre_empresa")
	private String nombreEmpresa;

	private String ocupacion;

	@Column(name = "origen_ingreso")
	private String origenIngreso;

	@Column(name = "primer_nombre")
	private String primerNombre;

	private String profesion;

	private String publicidad;

	@Column(name = "relacion_dependencia")
	private String relacionDependencia;

	@Column(name = "segundo_nombre")
	private String segundoNombre;

	@Column(name = "separacion_bienes")
	private String separacionBienes;

	@Column(name = "telefono_adicional")
	private String telefonoAdicional;

	@Column(name = "telefono_fijo")
	private String telefonoFijo;

	@Column(name = "telefono_movil")
	private String telefonoMovil;

	@Column(name = "telefono_trabajo")
	private String telefonoTrabajo;

	@Column(name = "aprobado_web_mupi")
	private String aprobacionMupi;

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

	// bi-directional many-to-one association to TbQoIngresoEgresoCliente
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoIngresoEgresoCliente> tbQoIngresoEgresoClientes;

	// bi-directional many-to-one association to TbQoNegociacion
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoNegociacion> tbQoNegociacions;

	// bi-directional many-to-one association to TbQoPatrimonio
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoPatrimonio> tbQoPatrimonios;

	// bi-directional many-to-one association to TbQoReferenciaPersonal
	@OneToMany(mappedBy = "tbQoCliente")
	private List<TbQoReferenciaPersonal> tbQoReferenciaPersonals;

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

	public String getActividadEconomicaEmpresa() {
		return this.actividadEconomicaEmpresa;
	}

	public void setActividadEconomicaEmpresa(String actividadEconomicaEmpresa) {
		this.actividadEconomicaEmpresa = actividadEconomicaEmpresa;
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

	public String getApoderadoCliente() {
		return this.apoderadoCliente;
	}

	public void setApoderadoCliente(String apoderadoCliente) {
		this.apoderadoCliente = apoderadoCliente;
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

	public BigDecimal getCargasFamiliares() {
		return this.cargasFamiliares;
	}

	public void setCargasFamiliares(BigDecimal cargasFamiliares) {
		this.cargasFamiliares = cargasFamiliares;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCedulaCliente() {
		return this.cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public BigDecimal getEdad() {
		return this.edad;
	}

	public void setEdad(BigDecimal edad) {
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

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
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

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getOrigenIngreso() {
		return this.origenIngreso;
	}

	public void setOrigenIngreso(String origenIngreso) {
		this.origenIngreso = origenIngreso;
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

	public String getRelacionDependencia() {
		return this.relacionDependencia;
	}

	public void setRelacionDependencia(String relacionDependencia) {
		this.relacionDependencia = relacionDependencia;
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

	public String getTelefonoAdicional() {
		return this.telefonoAdicional;
	}

	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return this.telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getTelefonoTrabajo() {
		return this.telefonoTrabajo;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
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

	public List<TbQoIngresoEgresoCliente> getTbQoIngresoEgresoClientes() {
		return this.tbQoIngresoEgresoClientes;
	}

	public void setTbQoIngresoEgresoClientes(List<TbQoIngresoEgresoCliente> tbQoIngresoEgresoClientes) {
		this.tbQoIngresoEgresoClientes = tbQoIngresoEgresoClientes;
	}

	public TbQoIngresoEgresoCliente addTbQoIngresoEgresoCliente(TbQoIngresoEgresoCliente tbQoIngresoEgresoCliente) {
		getTbQoIngresoEgresoClientes().add(tbQoIngresoEgresoCliente);
		tbQoIngresoEgresoCliente.setTbQoCliente(this);

		return tbQoIngresoEgresoCliente;
	}

	public TbQoIngresoEgresoCliente removeTbQoIngresoEgresoCliente(TbQoIngresoEgresoCliente tbQoIngresoEgresoCliente) {
		getTbQoIngresoEgresoClientes().remove(tbQoIngresoEgresoCliente);
		tbQoIngresoEgresoCliente.setTbQoCliente(null);

		return tbQoIngresoEgresoCliente;
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

	public List<TbQoPatrimonio> getTbQoPatrimonios() {
		return this.tbQoPatrimonios;
	}

	public void setTbQoPatrimonios(List<TbQoPatrimonio> tbQoPatrimonios) {
		this.tbQoPatrimonios = tbQoPatrimonios;
	}

	public TbQoPatrimonio addTbQoPatrimonio(TbQoPatrimonio tbQoPatrimonio) {
		getTbQoPatrimonios().add(tbQoPatrimonio);
		tbQoPatrimonio.setTbQoCliente(this);

		return tbQoPatrimonio;
	}

	public TbQoPatrimonio removeTbQoPatrimonio(TbQoPatrimonio tbQoPatrimonio) {
		getTbQoPatrimonios().remove(tbQoPatrimonio);
		tbQoPatrimonio.setTbQoCliente(null);

		return tbQoPatrimonio;
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

	@Override
	public String toString() {
		return "TbQoCliente [id=" + id + ", actividadEconomica=" + actividadEconomica + ", actividadEconomicaEmpresa="
				+ actividadEconomicaEmpresa + ", apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno="
				+ apellidoPaterno + ", apoderadoCliente=" + apoderadoCliente + ", campania=" + campania
				+ ", canalContacto=" + canalContacto + ", cargasFamiliares=" + cargasFamiliares + ", cargo=" + cargo
				+ ", cedulaCliente=" + cedulaCliente + ", edad=" + edad + ", email=" + email + ", estado=" + estado
				+ ", estadoCivil=" + estadoCivil + ", fechaActualizacion=" + fechaActualizacion + ", fechaCreacion="
				+ fechaCreacion + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", lugarNacimiento="
				+ lugarNacimiento + ", nacionalidad=" + nacionalidad + ", nivelEducacion=" + nivelEducacion
				+ ", nombreEmpresa=" + nombreEmpresa + ", ocupacion=" + ocupacion + ", origenIngreso=" + origenIngreso
				+ ", primerNombre=" + primerNombre + ", profesion=" + profesion + ", publicidad=" + publicidad
				+ ", relacionDependencia=" + relacionDependencia + ", segundoNombre=" + segundoNombre
				+ ", separacionBienes=" + separacionBienes + ", telefonoAdicional=" + telefonoAdicional
				+ ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", telefonoTrabajo="
				+ telefonoTrabajo + ", aprobacionMupi=" + aprobacionMupi + ", tbQoArchivoClientes="
				+ tbQoArchivoClientes + ", tbQoCotizador=" + tbQoCotizador + ", tbQoDireccionClientes="
				+ tbQoDireccionClientes + ", tbQoDocumentoHabilitantes=" + tbQoDocumentoHabilitantes
				+ ", tbQoIngresoEgresoClientes=" + tbQoIngresoEgresoClientes + ", tbQoNegociacions=" + tbQoNegociacions
				+ ", tbQoPatrimonios=" + tbQoPatrimonios + ", tbQoReferenciaPersonals=" + tbQoReferenciaPersonals
				+ ", tbQoRiesgoAcumulados=" + tbQoRiesgoAcumulados + "]";
	}

	
 
	
	

}