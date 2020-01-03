package com.relative.quski.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.relative.quski.enums.EstadoEnum;

public class SegUsuarioWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261619849015952407L;
	private String idUsuario;
	private String contrasena;
	private String emailPrincipal;
	private String emailSecundario;
	private EstadoEnum estado;
	private Date fchIns;
	private Date fchUpd;
	private String identificacion;
	private String primerApellido;
	private String primerNombre;
	private String segundoApellido;
	private String segundoNombre;
	private String telefonoPrincipal;
	private String telefonoSecundario;
	private String usrIns;
	private String usrUpd;	

	
	public SegUsuarioWrapper() {
		
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmailPrincipal() {
		return emailPrincipal;
	}

	public void setEmailPrincipal(String emailPrincipal) {
		this.emailPrincipal = emailPrincipal;
	}

	public String getEmailSecundario() {
		return emailSecundario;
	}

	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Date getFchIns() {
		return fchIns;
	}

	public void setFchIns(Date fchIns) {
		this.fchIns = fchIns;
	}

	public Date getFchUpd() {
		return fchUpd;
	}

	public void setFchUpd(Date fchUpd) {
		this.fchUpd = fchUpd;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getTelefonoPrincipal() {
		return telefonoPrincipal;
	}

	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}

	public String getTelefonoSecundario() {
		return telefonoSecundario;
	}

	public void setTelefonoSecundario(String telefonoSecundario) {
		this.telefonoSecundario = telefonoSecundario;
	}

	public String getUsrIns() {
		return usrIns;
	}

	public void setUsrIns(String usrIns) {
		this.usrIns = usrIns;
	}

	public String getUsrUpd() {
		return usrUpd;
	}

	public void setUsrUpd(String usrUpd) {
		this.usrUpd = usrUpd;
	}

	
	
	public String toJSON() {
		StringBuilder r = new StringBuilder();
		r.append("{\"entidad\":{");
		r.append("\"contrasena\":").append(this.contrasena).append(",");
		r.append("\"emailPrincipal\":").append(this.emailPrincipal).append(",");
		r.append("\"emailSecundario\":").append(this.emailSecundario).append(",");
		r.append("\"estado\":").append(this.estado).append(",");
		r.append("\"fchIns\":").append(this.fchIns).append(",");
		r.append("\"fchUpd\":").append(this.fchUpd).append(",");
		r.append("\"identificacion\":").append(this.identificacion).append(",");
		r.append("\"idUsuario\":").append(this.idUsuario).append(",");
		r.append("\"primerApellido\":").append(this.primerApellido).append(",");
		r.append("\"primerNombre\":").append(this.primerNombre).append(",");
		r.append("\"segundoApellido\":").append(this.segundoApellido).append(",");
		r.append("\"segundoNombre\":").append(this.segundoNombre).append(",");
		r.append("\"telefonoPrincipal\":").append(this.telefonoPrincipal).append(",");
		r.append("\"telefonoSecundario\":").append(this.telefonoSecundario).append(",");
		r.append("\"usrIns\":").append(this.usrIns).append(",");
		r.append("\"usrUpd\":").append(this.usrUpd);	
		r.append("}}");
		
		return r.toString();
	}
	
}
