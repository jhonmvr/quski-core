package com.relative.quski.wrapper;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class SoftbankClientWrapper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -365985272182192369L;
	public SoftbankClientWrapper() {
		
	}
	
		private Long idTipoIdentificacion;
		private String identificacion;                         
		private String nombreCompleto;                         
		private String primerApellido;                        
		private String segundoApellido;                        
		private String primerNombre;                           
		private String segundoNombre;                         
		private Boolean esCliente;                             
		private String codigoMotivoVisita;                         
		private Date fechaIngreso;                              
		private String idAgencia;                              
		private Long idPaisNacimiento;                        
		private Long idPais;                                  
		private Long idLugarNacimiento;                       
		private Date fechaNacimiento;                        
		private String codigoSexo;                           
		private String codigoProfesion;                      
		private String codigoEstadoCivil;                   
		private String codigoEducacion;                      
		private Long numeroCargasFamiliares;              
		private String email;                              
		private Long codigoUsuario;                       
		private String codigoUsuarioAsesor; 			
		private Long activos;                              
		private Long pasivos;                               
		private Long ingresos;                               
		private Long egresos;                               
		private String mensaje;                               
		private String codigoServicio;                    
		private List<DireccionWrapper> direcciones;
		private ActividadEconomicaClienteWrapper actividadEconomica; 
		private List<TelefonosClienteWrapper>telefonos;		
		private List<CuentasBancariasClienteWrapper> cuentasBancariasCliente;
		private List<ContactosClienteWrapper>contactosCliente;
		private List<DatosTrabajoClienteWrapper>datosTrabajoCliente;
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
		public Date getFechaIngreso() {
			return fechaIngreso;
		}
		public void setFechaIngreso(Date fechaIngreso) {
			this.fechaIngreso = fechaIngreso;
		}
		public String getIdAgencia() {
			return idAgencia;
		}
		public void setIdAgencia(String idAgencia) {
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
		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}
		public void setFechaNacimiento(Date fechaNacimiento) {
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
		public Long getCodigoUsuario() {
			return codigoUsuario;
		}
		public void setCodigoUsuario(Long codigoUsuario) {
			this.codigoUsuario = codigoUsuario;
		}
		public String getCodigoUsuarioAsesor() {
			return codigoUsuarioAsesor;
		}
		public void setCodigoUsuarioAsesor(String codigoUsuarioAsesor) {
			this.codigoUsuarioAsesor = codigoUsuarioAsesor;
		}
		public Long getActivos() {
			return activos;
		}
		public void setActivos(Long activos) {
			this.activos = activos;
		}
		public Long getPasivos() {
			return pasivos;
		}
		public void setPasivos(Long pasivos) {
			this.pasivos = pasivos;
		}
		public Long getIngresos() {
			return ingresos;
		}
		public void setIngresos(Long ingresos) {
			this.ingresos = ingresos;
		}
		public Long getEgresos() {
			return egresos;
		}
		public void setEgresos(Long egresos) {
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
		public List<DireccionWrapper> getDirecciones() {
			return direcciones;
		}
		public void setDirecciones(List<DireccionWrapper> direcciones) {
			this.direcciones = direcciones;
		}
		public ActividadEconomicaClienteWrapper getActividadEconomica() {
			return actividadEconomica;
		}
		public void setActividadEconomica(ActividadEconomicaClienteWrapper actividadEconomica) {
			this.actividadEconomica = actividadEconomica;
		}
		public List<TelefonosClienteWrapper> getTelefonos() {
			return telefonos;
		}
		public void setTelefonos(List<TelefonosClienteWrapper> telefonos) {
			this.telefonos = telefonos;
		}
		public List<CuentasBancariasClienteWrapper> getCuentasBancariasCliente() {
			return cuentasBancariasCliente;
		}
		public void setCuentasBancariasCliente(List<CuentasBancariasClienteWrapper> cuentasBancariasCliente) {
			this.cuentasBancariasCliente = cuentasBancariasCliente;
		}
		public List<ContactosClienteWrapper> getContactosCliente() {
			return contactosCliente;
		}
		public void setContactosCliente(List<ContactosClienteWrapper> contactosCliente) {
			this.contactosCliente = contactosCliente;
		}
		public List<DatosTrabajoClienteWrapper> getDatosTrabajoCliente() {
			return datosTrabajoCliente;
		}
		public void setDatosTrabajoCliente(List<DatosTrabajoClienteWrapper> datosTrabajoCliente) {
			this.datosTrabajoCliente = datosTrabajoCliente;
		}	  

}
