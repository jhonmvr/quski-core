package com.relative.quski.wrapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.relative.quski.util.QuskiOroConstantes;

public class SoftbankClienteWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -365985272182192369L;
	public SoftbankClienteWrapper( String identificacion ) {
		this.idTipoIdentificacion = QuskiOroConstantes.TIPO_CEDULA;
		this.identificacion = identificacion;
	}
		private Integer idTipoIdentificacion;
		private String identificacion;                         
		private String nombreCompleto;                         
		private String primerApellido;                        
		private String segundoApellido;                        
		private String primerNombre;                           
		private String segundoNombre;                         
		private Boolean esCliente;                             
		private String codigoMotivoVisita;                         
		private String fechaIngreso;                              
		private Long idAgencia;                              
		private Long idPaisNacimiento;                        
		private Long idPais;                                  
		private Long idLugarNacimiento;                       
		private SoftbankActividadEconomicaWrapper actividadEconomica; 
		private String fechaNacimiento;                        
		private String codigoSexo;                           
		private String codigoProfesion;                      
		private String codigoEstadoCivil;                   
		private String codigoEducacion;                      
		private Long numeroCargasFamiliares;              
		private String email;                              
		private String codigoUsuario;                       
		private String codigoUsuarioAsesor; 			
		private List<SoftbankDireccionWrapper> direcciones;
		private List<SoftbankTelefonosWrapper> telefonos;		
		private List<SoftbankCuentasBancariasWrapper> cuentasBancariasCliente;
		private List<SoftbankContactosWrapper> contactosCliente;
		private List<SoftbankDatosTrabajoWrapper> datosTrabajoCliente;
		private BigDecimal activos;                              
		private BigDecimal pasivos;                               
		private BigDecimal ingresos;                               
		private BigDecimal egresos;
		
		
		//------------------->>Hasta aqui segun el body de Cloud
		private String mensaje; 
		private String codigoServicio;  
		private Boolean existeError; 
		private String validaciones; 
		private String integrantes; 
		private String representantes; 
		private String nombreComercial; 
		private String razonSocial; 
		private Boolean esGrupo; 
		private String registroLegal; 
		private String fechaCreacion; 
		private String fechaRegistroLegal; 

		
		public Integer getIdTipoIdentificacion() {
			return idTipoIdentificacion;
		}
		public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
			this.idTipoIdentificacion = idTipoIdentificacion;
		}
		public String getIdentificacion() {
			return identificacion;
		}
		public void setIdentificacion(String identificacion) {
			this.identificacion = identificacion;
		}
		public String getNombreCompleto() {
			return nombreCompleto;
		}
		public void setNombreCompleto(String nombreCompleto) {
			this.nombreCompleto = nombreCompleto;
		}
		public String getPrimerApellido() {
			return primerApellido;
		}
		public void setPrimerApellido(String primerApellido) {
			this.primerApellido = primerApellido;
		}
		public String getSegundoApellido() {
			return segundoApellido;
		}
		public void setSegundoApellido(String segundoApellido) {
			this.segundoApellido = segundoApellido;
		}
		public String getPrimerNombre() {
			return primerNombre;
		}
		public void setPrimerNombre(String primerNombre) {
			this.primerNombre = primerNombre;
		}
		public String getSegundoNombre() {
			return segundoNombre;
		}
		public void setSegundoNombre(String segundoNombre) {
			this.segundoNombre = segundoNombre;
		}
		public Boolean getEsCliente() {
			return esCliente;
		}
		public void setEsCliente(Boolean esCliente) {
			this.esCliente = esCliente;
		}
		public String getCodigoMotivoVisita() {
			return codigoMotivoVisita;
		}
		public void setCodigoMotivoVisita(String codigoMotivoVisita) {
			this.codigoMotivoVisita = codigoMotivoVisita;
		}
		public String getFechaIngreso() {
			return fechaIngreso;
		}
		public void setFechaIngreso(String fechaIngreso) {
			this.fechaIngreso = fechaIngreso;
		}
		public Long getIdAgencia() {
			return idAgencia;
		}
		public void setIdAgencia(Long idAgencia) {
			this.idAgencia = idAgencia;
		}
		public Long getIdPaisNacimiento() {
			return idPaisNacimiento;
		}
		public void setIdPaisNacimiento(Long idPaisNacimiento) {
			this.idPaisNacimiento = idPaisNacimiento;
		}
		public Long getIdPais() {
			return idPais;
		}
		public void setIdPais(Long idPais) {
			this.idPais = idPais;
		}
		public Long getIdLugarNacimiento() {
			return idLugarNacimiento;
		}
		public void setIdLugarNacimiento(Long idLugarNacimiento) {
			this.idLugarNacimiento = idLugarNacimiento;
		}
		public String getFechaNacimiento() {
			return fechaNacimiento;
		}
		public void setFechaNacimiento(String fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
		public String getCodigoSexo() {
			return codigoSexo;
		}
		public void setCodigoSexo(String codigoSexo) {
			this.codigoSexo = codigoSexo;
		}
		public String getCodigoProfesion() {
			return codigoProfesion;
		}
		public void setCodigoProfesion(String codigoProfesion) {
			this.codigoProfesion = codigoProfesion;
		}
		public String getCodigoEstadoCivil() {
			return codigoEstadoCivil;
		}
		public void setCodigoEstadoCivil(String codigoEstadoCivil) {
			this.codigoEstadoCivil = codigoEstadoCivil;
		}
		public String getCodigoEducacion() {
			return codigoEducacion;
		}
		public void setCodigoEducacion(String codigoEducacion) {
			this.codigoEducacion = codigoEducacion;
		}
		public Long getNumeroCargasFamiliares() {
			return numeroCargasFamiliares;
		}
		public void setNumeroCargasFamiliares(Long numeroCargasFamiliares) {
			this.numeroCargasFamiliares = numeroCargasFamiliares;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCodigoUsuario() {
			return codigoUsuario;
		}
		public void setCodigoUsuario(String codigoUsuario) {
			this.codigoUsuario = codigoUsuario;
		}
		public String getCodigoUsuarioAsesor() {
			return codigoUsuarioAsesor;
		}
		public void setCodigoUsuarioAsesor(String codigoUsuarioAsesor) {
			this.codigoUsuarioAsesor = codigoUsuarioAsesor;
		}
		public BigDecimal getActivos() {
			return activos;
		}
		public void setActivos(BigDecimal activos) {
			this.activos = activos;
		}
		public BigDecimal getPasivos() {
			return pasivos;
		}
		public void setPasivos(BigDecimal pasivos) {
			this.pasivos = pasivos;
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
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		public String getCodigoServicio() {
			return codigoServicio;
		}
		public void setCodigoServicio(String codigoServicio) {
			this.codigoServicio = codigoServicio;
		}
		public List<SoftbankDireccionWrapper> getDirecciones() {
			return direcciones;
		}
		public void setDirecciones(List<SoftbankDireccionWrapper> direcciones) {
			this.direcciones = direcciones;
		}
		public SoftbankActividadEconomicaWrapper getActividadEconomica() {
			return actividadEconomica;
		}
		public void setActividadEconomica(SoftbankActividadEconomicaWrapper actividadEconomica) {
			this.actividadEconomica = actividadEconomica;
		}
		public List<SoftbankTelefonosWrapper> getTelefonos() {
			return telefonos;
		}
		public void setTelefonos(List<SoftbankTelefonosWrapper> telefonos) {
			this.telefonos = telefonos;
		}
		public List<SoftbankCuentasBancariasWrapper> getCuentasBancariasCliente() {
			return cuentasBancariasCliente;
		}
		public void setCuentasBancariasCliente(List<SoftbankCuentasBancariasWrapper> cuentasBancariasCliente) {
			this.cuentasBancariasCliente = cuentasBancariasCliente;
		}
		public List<SoftbankContactosWrapper> getContactosCliente() {
			return contactosCliente;
		}
		public void setContactosCliente(List<SoftbankContactosWrapper> contactosCliente) {
			this.contactosCliente = contactosCliente;
		}
		public List<SoftbankDatosTrabajoWrapper> getDatosTrabajoCliente() {
			return datosTrabajoCliente;
		}
		public void setDatosTrabajoCliente(List<SoftbankDatosTrabajoWrapper> datosTrabajoCliente) {
			this.datosTrabajoCliente = datosTrabajoCliente;
		}
		public Boolean getExisteError() {
			return existeError;
		}
		public void setExisteError(Boolean existeError) {
			this.existeError = existeError;
		}
		public String getValidaciones() {
			return validaciones;
		}
		public void setValidaciones(String validaciones) {
			this.validaciones = validaciones;
		}
		public String getIntegrantes() {
			return integrantes;
		}
		public void setIntegrantes(String integrantes) {
			this.integrantes = integrantes;
		}
		public String getRepresentantes() {
			return representantes;
		}
		public void setRepresentantes(String representantes) {
			this.representantes = representantes;
		}
		public String getNombreComercial() {
			return nombreComercial;
		}
		public void setNombreComercial(String nombreComercial) {
			this.nombreComercial = nombreComercial;
		}
		public String getRazonSocial() {
			return razonSocial;
		}
		public void setRazonSocial(String razonSocial) {
			this.razonSocial = razonSocial;
		}
		public Boolean getEsGrupo() {
			return esGrupo;
		}
		public void setEsGrupo(Boolean esGrupo) {
			this.esGrupo = esGrupo;
		}
		public String getRegistroLegal() {
			return registroLegal;
		}
		public void setRegistroLegal(String registroLegal) {
			this.registroLegal = registroLegal;
		}
		public String getFechaCreacion() {
			return fechaCreacion;
		}
		public void setFechaCreacion(String fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}
		public String getFechaRegistroLegal() {
			return fechaRegistroLegal;
		}
		public void setFechaRegistroLegal(String fechaRegistroLegal) {
			this.fechaRegistroLegal = fechaRegistroLegal;
		}
}
