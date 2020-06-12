package com.relative.quski.wrapper;

import java.io.Serializable;

public class EnvioWrapper implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5499174987159509681L;
	
	private String pmethodPost ;
	private String pmethodGet;
	private String pconnectTimeout;
	private String preadTimeout;
	private String pcontentJson;
	private String pxmlContent;
	private String pcontentTypeJson;
	private String pcontentTypeXML;
	private String puser;
	private String ppassword;
	private String pauthorization;
	private String purlVerificacion;
	private String purlUpdSiniestro;
	private String purlReservaIcore;
	private String pauthType;
	private String phandleResponseErrors;
	private Long pidSiniestro;
	private Long pidProceso;
	private Long pidTarea;
	private String pnumeroTramite;
	private String pestadoSiniestro;
	private String pmensajeError;
	private String pmailBody;
	private String pmailSubject;
	private String pmailFrom;
	private String pmailTo;
	private String pobservacion;
	private String pjsonContentNotificacion;
	private String purlGeneral;
	private String pestadoPoliza;
	private String preservaNumeroICORE;
	private String ptipoPerdida;
	private Long pidCosecha;
	private Long pidAvisoCosecha;
	private String paplicaNegativa;
	private String pesPagoManual;

	private String purlPagoIcore;

	private String pdeducible;
	private String pvalorPagado;
	private String pbeneficiario;
	private String prucBeneficiario;
	private String pfechaNegativa;
	private String pfechaPago;
	
	

	
	
	
	public EnvioWrapper(  String apiUrlResources, String apiUrlService, String amReserveVersion,String connectTimeout,String readTimeout  ) {
		pmethodPost="POST";
	    pmethodGet="GET";
	    //pconnectTimeout="60000";
	    //preadTimeout="60000";
	    pconnectTimeout=connectTimeout;
	    preadTimeout=readTimeout;
	    pcontentJson="";
	    pxmlContent="";
	    pcontentTypeJson="application/json";
	    pcontentTypeXML="text/xml";
	    puser="admin";
	    ppassword="admin";
	    pauthorization="";
	    purlVerificacion= apiUrlResources + "informeInspeccionRestController/verifySiniestroBpms";
	    purlUpdSiniestro= apiUrlResources + "informeInspeccionRestController/updateEstadoSiniestroBpms";
	    purlReservaIcore=apiUrlService + "siniestro-agricola/reserva/"+ amReserveVersion;
	    pauthType="NONE";
	    phandleResponseErrors="false";
	    pidSiniestro=(long)0;
	    pidProceso=(long)0;
	    pidTarea=(long)0;
	    pnumeroTramite="";
	    pestadoSiniestro="";
	    pmensajeError="";
	    pmailBody="";
	    pmailSubject="";
	    pmailFrom="";
	    pmailTo="";
	    pobservacion="";
	    pjsonContentNotificacion="";
	    purlGeneral="";
	    pestadoPoliza="";
	    preservaNumeroICORE="";
	    ptipoPerdida="";
	    pidCosecha=(long)0;
	    pidAvisoCosecha=(long)0;
	    pdeducible="0.00";
	    pvalorPagado="0.00";
	    pbeneficiario="";
	    pfechaNegativa ="";
	    pfechaPago ="";
	    prucBeneficiario = "";
	    
	}

	public String getPmethodPost() {
		return pmethodPost;
	}

	public void setPmethodPost(String pmethodPost) {
		this.pmethodPost = pmethodPost;
	}

	public String getPmethodGet() {
		return pmethodGet;
	}

	public void setPmethodGet(String pmethodGet) {
		this.pmethodGet = pmethodGet;
	}

	public String getPconnectTimeout() {
		return pconnectTimeout;
	}

	public void setPconnectTimeout(String pconnectTimeout) {
		this.pconnectTimeout = pconnectTimeout;
	}

	public String getPreadTimeout() {
		return preadTimeout;
	}

	public void setPreadTimeout(String preadTimeout) {
		this.preadTimeout = preadTimeout;
	}

	public String getPcontentJson() {
		return pcontentJson;
	}

	public void setPcontentJson(String pcontentJson) {
		this.pcontentJson = pcontentJson;
	}

	public String getPxmlContent() {
		return pxmlContent;
	}

	public void setPxmlContent(String pxmlContent) {
		this.pxmlContent = pxmlContent;
	}

	public String getPcontentTypeJson() {
		return pcontentTypeJson;
	}

	public void setPcontentTypeJson(String pcontentTypeJson) {
		this.pcontentTypeJson = pcontentTypeJson;
	}

	public String getPcontentTypeXML() {
		return pcontentTypeXML;
	}

	public void setPcontentTypeXML(String pcontentTypeXML) {
		this.pcontentTypeXML = pcontentTypeXML;
	}

	public String getPuser() {
		return puser;
	}

	public void setPuser(String puser) {
		this.puser = puser;
	}

	public String getPpassword() {
		return ppassword;
	}

	public void setPpassword(String ppassword) {
		this.ppassword = ppassword;
	}

	public String getPauthorization() {
		return pauthorization;
	}

	public void setPauthorization(String pauthorization) {
		this.pauthorization = pauthorization;
	}

	public String getPurlVerificacion() {
		return purlVerificacion;
	}

	public void setPurlVerificacion(String purlVerificacion) {
		this.purlVerificacion = purlVerificacion;
	}

	public String getPurlUpdSiniestro() {
		return purlUpdSiniestro;
	}

	public void setPurlUpdSiniestro(String purlUdpSiniestro) {
		this.purlUpdSiniestro = purlUdpSiniestro;
	}

	public String getPurlReservaIcore() {
		return purlReservaIcore;
	}

	public void setPurlReservaIcore(String purlReservaIcore) {
		this.purlReservaIcore = purlReservaIcore;
	}

	public String getPauthType() {
		return pauthType;
	}

	public void setPauthType(String pauthType) {
		this.pauthType = pauthType;
	}

	public String getPhandleResponseErrors() {
		return phandleResponseErrors;
	}

	public void setPhandleResponseErrors(String phandleResponseErrors) {
		this.phandleResponseErrors = phandleResponseErrors;
	}

	public Long getPidSiniestro() {
		return pidSiniestro;
	}

	public void setPidSiniestro(Long pidSiniestro) {
		this.pidSiniestro = pidSiniestro;
	}

	public Long getPidProceso() {
		return pidProceso;
	}

	public void setPidProceso(Long pidProceso) {
		this.pidProceso = pidProceso;
	}

	public Long getPidTarea() {
		return pidTarea;
	}

	public void setPidTarea(Long pidTarea) {
		this.pidTarea = pidTarea;
	}

	public String getPnumeroTramite() {
		return pnumeroTramite;
	}

	public void setPnumeroTramite(String pnumeroTramite) {
		this.pnumeroTramite = pnumeroTramite;
	}

	public String getPestadoSiniestro() {
		return pestadoSiniestro;
	}

	public void setPestadoSiniestro(String pestadoSiniestro) {
		this.pestadoSiniestro = pestadoSiniestro;
	}

	public String getPmensajeError() {
		return pmensajeError;
	}

	public void setPmensajeError(String pmensajeError) {
		this.pmensajeError = pmensajeError;
	}

	public String getPmailBody() {
		return pmailBody;
	}

	public void setPmailBody(String pmailBody) {
		this.pmailBody = pmailBody;
	}

	public String getPmailSubject() {
		return pmailSubject;
	}

	public void setPmailSubject(String pmailSubject) {
		this.pmailSubject = pmailSubject;
	}

	public String getPmailFrom() {
		return pmailFrom;
	}

	public void setPmailFrom(String pmailFrom) {
		this.pmailFrom = pmailFrom;
	}

	public String getPmailTo() {
		return pmailTo;
	}

	public void setPmailTo(String pmailTo) {
		this.pmailTo = pmailTo;
	}
	
	public String getPobservacion() {
		return pobservacion;
	}

	public void setPobservacion(String pobservacion) {
		this.pobservacion = pobservacion;
	}
	
	public String getPjsonContentNotificacion() {
		return pjsonContentNotificacion;
	}

	public void setPjsonContentNotificacion(String pjsonContentNotificacion) {
		this.pjsonContentNotificacion = pjsonContentNotificacion;
	}


	public String getPurlGeneral() {
		return purlGeneral;
	}

	public void setPurlGeneral(String purlGeneral) {
		this.purlGeneral = purlGeneral;
	}

	public String getPestadoPoliza() {
		return pestadoPoliza;
	}

	public void setPestadoPoliza(String pestadoPoliza) {
		this.pestadoPoliza = pestadoPoliza;
	}

	public String getPreservaNumeroICORE() {
		return preservaNumeroICORE;
	}

	public void setPreservaNumeroICORE(String preservaNumeroICORE) {
		this.preservaNumeroICORE = preservaNumeroICORE;
	}

	public String getPtipoPerdida() {
		return ptipoPerdida;
	}

	public void setPtipoPerdida(String ptipoPerdida) {
		this.ptipoPerdida = ptipoPerdida;
	}

	public Long getPidCosecha() {
		return pidCosecha;
	}

	public void setPidCosecha(Long pidCosecha) {
		this.pidCosecha = pidCosecha;
	}	

	public Long getPidAvisoCosecha() {
		return pidAvisoCosecha;
	}

	public void setPidAvisoCosecha(Long pidAvisoCosecha) {
		this.pidAvisoCosecha = pidAvisoCosecha;
	}

	
	public String getPaplicaNegativa() {
		return paplicaNegativa;
	}

	public void setPaplicaNegativa(String paplicaNegativa) {
		this.paplicaNegativa = paplicaNegativa;
	}

	public String getPesPagoManual() {
		return pesPagoManual;
	}

	public void setPesPagoManual(String pesPagoManual) {
		this.pesPagoManual = pesPagoManual;
	}
	
	

	public String getPurlPagoIcore() {
		return purlPagoIcore;
	}

	public void setPurlPagoIcore(String purlPagoIcore) {
		this.purlPagoIcore = purlPagoIcore;
	}

	public String getPdeducible() {
		return pdeducible;
	}

	public void setPdeducible(String pdeducible) {
		this.pdeducible = pdeducible;
	}

	public String getPvalorPagado() {
		return pvalorPagado;
	}

	public void setPvalorPagado(String pvalorPagado) {
		this.pvalorPagado = pvalorPagado;
	}

	public String getPbeneficiario() {
		return pbeneficiario;
	}

	public void setPbeneficiario(String pbeneficiario) {
		this.pbeneficiario = pbeneficiario;
	}

	public String getPrucBeneficiario() {
		return prucBeneficiario;
	}

	public void setPrucBeneficiario(String prucBeneficiario) {
		this.prucBeneficiario = prucBeneficiario;
	}

	
	

	public String getPfechaNegativa() {
		return pfechaNegativa;
	}

	public void setPfechaNegativa(String pfechaNegativa) {
		this.pfechaNegativa = pfechaNegativa;
	}

	public String getPfechaPago() {
		return pfechaPago;
	}

	public void setPfechaPago(String pfechaPago) {
		this.pfechaPago = pfechaPago;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("=====>Envo Wrapper " );
		sb.append("\n")
		.append(pmethodPost)
		.append("\n")
		.append(pmethodGet)
		.append("\n")
		.append(pconnectTimeout)
		.append("\n")
		.append(preadTimeout)
		.append("\n")
		.append(pcontentJson)
		.append("\n")
		.append(pxmlContent)
		.append("\n")
		.append(pcontentTypeJson)
		.append("\n")
		.append(pcontentTypeXML)
		.append("\n")
		.append(puser)
		.append("\n")
		.append(ppassword)
		.append("\n")
		.append(pauthorization)
		.append("\n")
		.append(purlVerificacion)
		.append("\n")
		.append(purlUpdSiniestro)
		.append("\n")
		.append(purlReservaIcore)
		.append("\n")
		.append(pauthType)
		.append("\n")
		.append(phandleResponseErrors)
		.append("\n")
		.append(pidSiniestro)
		.append("\n")
		.append(pidProceso)
		.append("\n")
		.append(pidTarea)
		.append("\n")
		.append(pnumeroTramite)
		.append("\n")
		.append(pestadoSiniestro)
		.append("\n")
		.append(pmensajeError)
		.append("\n")
		.append(pmailBody)
		.append("\n")
		.append(pmailSubject)
		.append("\n")
		.append(pmailFrom)
		.append("\n")
		.append(pmailTo)
		.append("\n")
		.append(pobservacion)
		.append("\n")
		.append(pjsonContentNotificacion)
		.append("\n")
		.append(purlGeneral)
		.append("\n")
		.append(pestadoPoliza)
		.append("\n")
		.append(preservaNumeroICORE)
		.append(ptipoPerdida)
		.append("\n")
		.append(pidCosecha)
		.append("\n")
		.append(pidAvisoCosecha)
		.append("\n")
		.append(pdeducible)
		.append("\n")
		.append(pvalorPagado)
		.append("\n")
		.append(pbeneficiario)
		.append("\n")
		.append(prucBeneficiario)
		.append("\n")
		.append(pfechaPago)
		.append("\n")
		.append(pfechaNegativa)
		.append("\n")
		.append(paplicaNegativa)
		.append("\n")	
		.append(pesPagoManual)
		.append("\n")	
		.append(purlPagoIcore)
		.append("\n");	
		
		return sb.toString();
	}

	

}
