package com.relative.quski.util;


/**
 * 
 * @author LUIS TAMAYO - RELATIVE ENGINE
 *
 */
public class QuskiOroConstantes {
	private QuskiOroConstantes() {}
	
	/**
	 * Archivo de configuraciones con informacion general de notificaciones
	 */
	public static final  String FILE_NAME_NOTIFICACION_FILE_PROPS="sa.properties";
	public static final String PREFIX_REPORT_MAIN_PATH="report.main.path.";
	public static final String PREFIX_REPORT_SUB_ONE_PATH="report.sub.one.path.";
	public static final String PREFIX_REPORT_SUB_TWO_PATH="report.sub.two.path.";
	public static final String PREFIX_REPORT_SUB_THREE_PATH="report.sub.three.path.";
	public static final String PATH_REPORTE="PATH_REPORTE";
	public static final String REPORT_MAIN_PATH="PATH_REPORTE";
	/**
	 * Varible en sistema correspondiente al home de widlfly
	 */
	public static final String JBOSS_CONFIG_DIR_PROPS="jboss.home.dir";

	public static final String PDF_DUMMY_PATH_PROPS="siniestros.pdf.dummypath";
	
	public static final String SINIESTRO_FIRMA_PATH_PROPS="siniestros.img.path.firma";
	public static final String SINIESTRO_LOGO_PATH_PROPS="siniestros.img.path.logo";
	
	public static final String SINIESTRO_AGRICOLA_CONF_DIR="/portalservicios_dir/midas_oro/";
	
	public static final String MIDAS_DATE_FORMAT_FRONT="dd/MM/yyyy";

	/**
	 * @MAIL
	 */
	public static final String PARA_EXC = "PARA-EXC";
	public static final String M_ASUNTO = "M_ASUNTO";
	public static final String M_CONTENIDO = "M_CONTENIDO";

	public static final String SIN_EXCEPCION = "SIN_EXCEPCION";
	/**
	 * Formato de envio JSON
	 */
	public static final String CONTENT_TYPE_JSON="application/json";
	
	/**
	 * Metodo HTTP GET
	 */
	public static final String METHOD_HTTP_GET="GET";
	
	/**
	 * Metodo HTTP POST
	 */
	public static final String METHOD_HTTP_POST="POST";
	
	/**
	 * Plantilla del mensaje
	 */
	public static final String MESSAGE_TEMPLATE="message.template";
	
	
	/**
	 * SEGURIDADES
	 */
	public static final String SEGURIDAD_URL_PROPS="seguridad.url";
	public static final String SEGURIDAD_URL_UXR_PROPS="seguridad.url.uxr";
	public static final String SEGURIDAD_ICON_LOCATION_PROPS="seguridad.icon.location";
	
	
	
	
	/**
	 * CODIGOS DE OPERACION BPM
	 */
	public static final String CODIGO_COTIZADOR					= "COTZ";
	public static final String CODIGO_NUEVO						= "NUEV";
	public static final String CODIGO_RENOVACION				= "RENV";
	public static final String CODIGO_DEVOLUCION 				= "DEVC";
	public static final String CODIGO_PAGO 						= "PAGO";
	public static final String CODIGO_VERIFICACION_TELEFONICA 	= "VETF";
	
	/**
	 * FUNDAS
	 */
	public static final String FUNDA_PREFIX = "CODIGO-";
    
	/**
	 * BODEGAS 
	 */
	public static final String CAJA_FUERTE ="CAJA_FUERTE";
	public static final String CAJA_FUERTE_CUSTODIA ="CAJA_FUERTE_CUSTODIA";
	public static final String CAJA_FUERTE_VITRINA ="VITRINA";
    
    
    public static final String  PARAM_APGW_SINIESTROS_KEY="APGW_SINIESTROS_KEY";
    public static final String  PARAM_BPMS_REST_AUTH_KEY="BPMS_REST_AUTH_KEY";
    public static final String  PARAM_API_URL_RESOURCES="apiUrlResources";
    public static final String  PARAM_API_URL_ROOT="apiUrlRoot";
    public static final String  PARAM_API_URL_ROOT_WEB_SOCKET="apiUrlRootWebSocket";
    public static final String  PARAM_API_URL_SERVICE="apiUrlService";
    public static final String  PARAM_API_URL_TOKEN="apiUrlToken";
    public static final String  PARAM_BPMS_URL_SERVICES="bpmsUrlService";
    public static final String  PARAM_API_SEG_AUTHE_URL="apiSegAutheUrl";
    public static final String  PARAM_API_SEG_AUTHO_URL="apiSegAuthoUrl";
    public static final String  PARAM_API_SEG_AUTHO_USER_ROL_URL="apiSegAuthoUserRolUrl";
    public static final String  PARAM_API_SEG_AUTHO_APP="apiSegAuthoAPP";
    public static final String  PARAM_SEG_AUTH_TOKEN="segAuthorizationToken";
    public static final String  PARAM_CONTENT_TYPE_TOKEN="contentTypeToken";
    public static final String  PARAM_GRANT_TYPE_TOKEN="grantTypeToken";
    public static final String  PARAM_AUTH_PREFIX_API="authorizationPrefixApi";
    public static final String  PARAM_AUTH_PREFIX_BPMS="authorizationPrefixBpms";
    public static final String  PARAM_RAMO_AGRICOLA="RAMO_AGRICOLA";
    public static final String  PARAM_TOKEN_KEY="TOKEN_KEY";
    public static final String  PARAM_REFRESH_TOKEN_KEY="REFRESH_TOKEN_KEY";
    public static final String  PARAM_EXPIRATION_TOKEN_KEY="EXPIRATION_TOKEN_KEY";
    public static final String  PARAM_ICORE_API_RESERVA_SERVICE="ICORE_API_RESERVA_SERVICE";
    public static final String  PARAM_ICORE_API_RESERVA_VERSION="ICORE_API_RESERVA_VERSION";
    public static final String  PARAM_ICORE_API_PAGO_VERSION="ICORE_API_PAGO_VERSION";
    public static final String  PARAM_BPMS_PROJECT="BPMS_PROJECT";
    public static final String  PARAM_BPMS_PROJECT_VERSION="BPMS_PROJECT_VERSION";
    public static final String  PARAM_BPMS_PROJECT_MAIN_BPM="BPMS_PROJECT_MAIN_BPM";
    public static final String  PARAM_BPMS_PROJECT_MAIN_BPM_VERSION="BPMS_PROJECT_MAIN_BPM_VERSION";
    public static final String  PARAM_AM_RESERVA_VERSION="AM_RESERVA_VERSION";
    public static final String  PARAM_VALOR_RESERVA="VALOR_RESERVA";
    public static final String  PARAM_AUTHENTICATION_SERVER="AUTHENTICATION_SERVER";
    public static final String  PARAM_MESSAGE_RESERVA_XML="MESSAGE_RESERVA_XML";
    public static final String  PARAM_MESSAGE_PAGO_XML="MESSAGE_PAGO_XML";
    public static final String  PARAM_MESSAGE_NEGATIVA_XML="MESSAGE_NEGATIVA_XML";
    //parametros MessageDefinition
    public static final String  PARAM_BROKER_NAME_SMS="brokerNameSms";
    public static final String  PARAM_QUEUE_NAME="queueName";
    public static final String  PARAM_IS_VIRTUAL="isVirtual";
    public static final String  PARAM_VERSION_MESSAGE="versionMessage";
    public static final String  PARAM_ACTION_NAME_SMS="actionNameSms";
    public static final String  PARAM_VERSION_ACTION_SMS="versionActionSms";
    public static final String  PARAM_TEMPLATE_SMS="templateSms";
    public static final String  PARAM_BROKER_NAME_EMAIL="brokerNameEmail";
    public static final String  PARAM_ACTION_NAME_EMAIL="actionNameEmail";
    public static final String  PARAM_TEMPLATE_EMAIL_SINIESTRO="templateEmailSiniestro";
    public static final String  PARAM_TEMPLATE_EMAIL_COSECHA="templateEmailCosecha";
    public static final String  PARAM_TEMPLATE_EMAIL_NEGATIVA="templateEmailNegativa";
    public static final String  PARAM_TEMPLATE_EMAIL_PAGO="templateEmailPago";
    public static final String  PARAM_TEMPLATE_EMAIL_USUARIO="templateEmailUsuario";
    public static final String  PARAM_REPLY_TO="replyTo";
    public static final String  PARAM_SUBJECT="subject";
	public static final String  PARAM_TEMPLATE_SMS_SINIESTRO="templateSmsSiniestro";
	public static final String  PARAM_TEMPLATE_SMS_COSECHA="templateSmsCosecha";
	public static final String  PARAM_TEMPLATE_SMS_NEGATIVA="templateSmsNegativa";
	public static final String  PARAM_TEMPLATE_SMS_PAGO="templateSmsPago";
	public static final String  PARAM_TEMPLATE_SMS_USUARIO="templateSmsUsuario";
	public static final String  PARAM_FROM_EMAIL="fromEmail";
	public static final String  PARAM_API_URL_NOTIFICACION="apiUrlNotificacion";
	public static final String  PARAM_NOMBRE_RESPONSABLE_NEGATIVA="nombreResponsableNegativa";    
	public static final String  PARAM_CARGO_RESPONSABLE_NEGATIVA="cargoResponsableNegativa";   
	public static final String  PARAM_CONNECT_TIMEOUT="pconnectTimeout";
	public static final String  PARAM_READ_TIMEOUT="preadTimeout";
	public static final String  PARAM_FIRMA_RESPONSABLE_NEGATIVA="FIRMA_PATH";
	public static final String  PARAM_CAUSA_NEGATIVA="causaNegativa";    
    
    //PARAMETROS PARA CLIENTES EXTERNOS
	public static final String  PARAM_EXT_SEG_API_URL="RE001";   
	
	public static final String  PARAM_EXT_SEG_URL="RE002";   
	public static final String  PARAM_EXT_SEG_USR_ROL_URL="RE003";
	public static final String  PARAM_EXT_SEG_ROL_OPTION_URL="RE004";
	public static final String  PARAM_EXT_SEG_API_GTW_CREDENTIAL="RE005";
	
	public static final String  PARAM_EXT_API_URL="RE006";   
	public static final String  PARAM_EXT_ROOT_URL="RE007";   
	public static final String  PARAM_EXT_ROOT_WEBSOCKET="RE008";
	public static final String  PARAM_EXT_API_URL_SERVICE="RE009";
	public static final String  PARAM_EXT_API_URL_TOKEN="RE010";
	public static final String  PARAM_EXT_KEY_UNENCRYPT="RE011";
	
	public static final String  PARAM_EXT_SEG_API_URL_TOKEN="RE012";
	public static final String  PARAM_EXT_API_GTW_CREDENTIAL="RE013";
	
	public static final String  PARAM_EXT_API_URL_NOTIFICACION="RE014";
	public static final String  PARAM_EXT_QUEUE_NAME_SMS="RE015";
	public static final String  PARAM_EXT_BROKER_NAME_SMS="RE016";
	public static final String  PARAM_EXT_VERSION_ACTION_SMS="RE017";
	public static final String  PARAM_EXT_ACTION_NAME_SMS="RE018";
	public static final String  PARAM_EXT_QUEUE_NAME_EMAIL="RE019";
	public static final String  PARAM_EXT_BROKER_NAME_EMAIL="RE020";
	public static final String  PARAM_EXT_ACTION_NAME_EMAIL="RE021";
	public static final String  PARAM_EXT_VERSION_ACTION_EMAIL="RE022";
	public static final String  PARAM_EXT_REPLY_EMAIL="RE023";
	public static final String  PARAM_EXT_SUBJECT_EMAIL="RE024";
	public static final String  PARAM_EXT_FROM_EMAIL="RE025";
	public static final String  PARAM_EXT_BROKER_VIRTUAL="RE026";
	public static final String  PARAM_EXT_TEMPLATE_RESETEO="RE027";
	public static final String PARAM_EXT_IDLE_START = "RE028";
	public static final String PARAM_EXT_IDLE_TIMEOUT = "RE029";
	public static final String PARAM_EXT_VERSION_FRONT = "RE030";
	
	
	public static final String PARAM_PRECIO_ORO_CD = "RE031";
	public static final String PARAM_PRECIO_ORO_CP = "RE032";
	
	public static final String PARAM_PROCENTAJE_TASACION = "RE033";
	public static final String PARAM_PORCENTAJE_TRANSPORTE = "RE034";
	public static final String PARAM_PORCENTAJE_GASTOS = "RE035";
	public static final String PARAM_PORCENTAJE_REVALORACION = "RE036";
	public static final String PARAM_PORCENTAJE_CUSTODIA = "RE037";
	public static final String PARAM_PORCENTAJE_RENOVACION = "RE038";	
	public static final String PARAM_MEDIDA_CONVERSION = "RE039";	
	public static final String PARAM_DIAS_CP = "RE040";	
	public static final String PARAM_PORCENTAJE_IVA = "RE046"; 
	public static final String PARAM_APODERADO_LEGAL = "APODERADO_LEGAL";  
	public static final String PARAM_CED_APODERADO_LEGAL = "CED_APODERADO_LEGAL";  
	
	//BPM
	public static final String PARAM_BPM_URL_BASE_API = "RE041";	
	public static final String PARAM_BPM_AUTH_API = "RE042";
	public static final String PARAM_BPM_CONTAINER_ID = "RE043";
	public static final String PARAM_BPM_BUSINESS_PROCESS = "RE044";
	public static final String PARAM_BPM_BUSINESS_PROCESS_APROBACION = "RE045";
	
	public static final  String  BPMS_REST_DEFAULT_CHARSET = "utf-8";
	public static final  Integer  BPMS_REST_TIMEOUT_DEFAULT = 60000;
	public static final  String  BPMS_DEFAULT_USER = "agente";
	
	
	
	public static final String MESSAGE_NOTIFICACION_FUNDA = "NOTIFICACION_FUNDA";	
	public static final String MESSAGE_NOTIFICACION_LIQUIDACION = "MESSAGE_NOTIFICACION_LIQUIDACION";
	public static final String MIN_LIQUIDACION = "MIN_FUNDA";
	public static final String MIN_FUNDA = "MIN_FUNDA";
	public static final String DIAS_DE_GRACIA = "DIAS_DE_GRACIA";
	public static final String BODEGA_MATRIZ = "CAJA_FUERTE,CAJA_FUERTE_CUSTODIA,VITRINA,CLIENTE";	
	public static final String BODEGA_SUCURSAL = "CAJA_FUERTE,CAJA_FUERTE_CUSTODIA,CLIENTE";
	public static final String ADMINISTRADOR = "ADMINISTRADOR";
	public static final String GERENTE_COMERCIAL = "GERENTE_COMERCIAL";
	public static final String GERENTE_GENERAL = "GERENTE_GENERAL";
	public static final String CODIGO_COMPROBANTE_INGRESOS_EGRESOS = "CIE";
	public static final String VALOR_IVA_CERO = "0.00";
	public static final String BILLETE = "BILLETE";
	public static final String MONEDA = "MONEDA";
	
	// MENSAJES DE EXCEPCIONES
	public static final String ACCION_NO_ENCONTRADA = "ACCION NO ENCONTRADA -> ";
	public static final String ERROR_AL_REALIZAR_BUSQUEDA = "AL REALIZAR BUSQUEDA -> ";
	public static final String ERROR_SIN_PROCESO = "NO POSEE UN PROCESO RELACIONADO";
	public static final String ERROR_AL_REALIZAR_CREACION = "AL REALIZAR CREACION -> ";
	public static final String ERROR_CREATE_CLIENTE = "AL CREAR AL CLIENTE -> ";
	public static final String ERROR_CREATE_NOVACION = " AL CREAR EL CREDITO RENOVACION -> ";
	public static final String ERROR_NEGOCIACION = "AL CREAR TODA LA GESTION -> ";
	public static final String ERROR_AL_REALIZAR_ACTUALIZACION = "AL REALIZAR ACTUALIZACION -> ";
	public static final String ERROR_VALOR_NO_VALIDO = "INGRESE UN VALOR VALIDO";
	public static final String ERROR_ID_NO_EXISTE = "ID INGRESADO NO EXISTE ";
	public static final String ERROR_MAS_DE_UN_REGISTRO = "EL ID POSEE MAS DE UN REGISTRO. REVISAR BASE DE DATOS.";
	public static final String ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION = "AL REALIZAR ACTUALIZACION O CREACION -> ";
	public static final String FALTA_RELACION = "FALTA RELACION PARA CREAR. -> ";
	public static final String FALTA_CLIENTE = "FALTA RELACION AL CLIENTE PARA CREAR. -> ";
	public static final String CLIENTE_PAGO_NO_ENCONTRADO = "CLIENTE PAGO NO ENCONTRADO. -> ";	
	public static final String ERROR_AL_CONSUMIR_SERVICIOS = "ERROR AL CONSUMIR SERVICIOS. -> ";
	public static final String ERROR_INGRESE_ASESOR = "INGRESE UN CAMPO DE ASESOR VALIDO.";	
	public static final String ERROR_INGRESE_PROCESO = "INGRESE UN CAMPO DE PROCESO VALIDO.";


	// RUBROCREAR OPERACION
	public static final String COSTO_CUSTODIA      = "COSTO_CUSTODIA" ;
	public static final String COSTO_FIDEICOMISO   = "COSTO_FIDEICOMISO" ;
	public static final String COSTO_SEGURO        = "COSTO_SEGURO" ;
	public static final String COSTO_TASACION      = "COSTO_TASACION" ;
	public static final String COSTO_TRANSPORTE    = "COSTO_TRANSPORTE" ;
	public static final String COSTO_VALORACION    = "COSTO_VALORACION" ;
	public static final String SALDO_CAPITAL_RENOV = "SALDO_CAPITAL_RENOV" ;
	public static final String SALDO_INTERES       = "SALDO_INTERES" ;
	public static final String SALDO_MORA          = "SALDO_MORA" ;
	public static final String GASTO_COBRANZA      = "GASTO_COBRANZA" ;
	public static final String CUSTODIA_DEVENGADA  = "CUSTODIA_DEVENGADA" ;

	// SOFTBANK
	public static final String SOFT_TIPO_PRESTAMO = "001" ;
	public static final String SOFT_GRADO_INTERES = "000" ;
	public static final String SOFT_DATE_FORMAT="yyyy-MM-dd";
	public static final String SOFT_POR_DEFECTO="REFERENCIA POR DEFECTO";
	public static final String SOFT_COBERTURA="001";


	public static final String URLCLOUDSTUDIO ="URLSOFTBANK";
	public static final Integer TIPO_CEDULA = 1;
	public static final String AUTORIZACION = "Basic RmlpeUhKUjN2SHIyanFqZzNpWjQ2WHVZaHJNYTpGcDFJY3pmT3Fsd19xQXVBOVZ0WG9hazNQOWNh";
	public static final String URL_SOFTBANK_RIESGO_ACUMULADO = "URL_SOFTBANK_RIESGO_ACUMULADO";
	public static final String SOFTBANK_CONSULTA_CLIENTE = "URL_SERVICIO_SOFTBANK_CONSULTA_CLIENTE";
	public static final String URL_SERVICIO_SOFTBANK_CREAR_CLIENTE = "URL_SERVICIO_SOFTBANK_CREAR_CLIENTE";
	public static final String SOFTBANK_CONSULTA_GLOBAL = "SOFTBANK_CONSULTA_GLOBAL";

	public static final String URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE = "URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE";
	public static final String URL_SERVICIO_SOFTBANK_CREAR_OPERACION = "URL_SERVICIO_SOFTBANK_CREAR_OPERACION";
	public static final String SOFTBANK_RENOVAR_OPERACION = "SOFTBANK_RENOVAR_OPERACION";
	public static final String SOFTBANK_CONSULTA_GARANTIA = "SOFTBANK_CONSULTA_GARANTIA";
	public static final String SOFTBANK_CONSULTA_RUBRO = "SOFTBANK_CONSULTA_RUBRO";


	public static final String URL_SOFTBANK_TABLA_AMORTIZACION= "URL_SOFTBANK_TABLA_AMORTIZACION";
	
	public static final String CATALOGO_TABLA_AMOTIZACION = "URL_SOFTBANK_CATALOGO_TABLA_AMOTIZACION";
	
	public static final String CATALOGO_ACTIVIDAD_ECONOMICA = "URL_SOFTBANK_CATALOGO_ACTIVIDAD_ECONOMICA";
	public static final String CATALOGO_ACTIVIDAD_ECONOMICA_MUPI = "URL_SOFTBANK_CATALOGO_ACTIVIDAD_ECONOMICA_MUPI";
	public static final String CATALOGO_AGENCIA = "URL_SOFTBANK_CATALOGO_AGENCIA";
	public static final String CATALOGO_BANCO = "URL_SOFTBANK_CATALOGO_BANCO";
	public static final String CATALOGO_DIVISION_POLITICA = "URL_SOFTBANK_CATALOGO_DIVISION_POLITICA";
	public static final String CATALOGO_EDUCACION = "URL_SOFTBANK_CATALOGO_EDUCACION";
	public static final String CATALOGO_ESTADO_CIVIL = "URL_SOFTBANK_CATALOGO_ESTADO_CIVIL";
	public static final String CATALOGO_ESTADO_JOYA = "URL_SOFTBANK_CATALOGO_ESTADO_JOYA";
	public static final String CATALOGO_ESTADO_PROCESO = "URL_SOFTBANK_CATALOGO_ESTADO_PROCESO";
	public static final String CATALOGO_FIRMANTE_OPERACION = "URL_SOFTBANK_CATALOGO_FIRMANTE_OPERACION";
	public static final String CATALOGO_IMP_COM = "URL_SOFTBANK_CATALOGO_IMP_COM";
	public static final String CATALOGO_MOTIVO_DEVOLUCION_APROBACION = "URL_SOFTBANK_CATALOGO_MOTIVO_DEVOLUCION_APROBACION";
	public static final String CATALOGO_PAIS = "URL_SOFTBANK_CATALOGO_PAIS";
	public static final String CATALOGO_PROFESION = "URL_SOFTBANK_CATALOGO_PROFESION";
	public static final String CATALOGO_SECTOR_VIVIENDA = "URL_SOFTBANK_CATALOGO_SECTOR_VIVIENDA";
	public static final String CATALOGO_SEXO = "URL_SOFTBANK_CATALOGO_SEXO";
	public static final String CATALOGO_TIPO_DIRECCION = "URL_SOFTBANK_CATALOGO_TIPO_DIRECCION";
	public static final String CATALOGO_TIPO_FUNDA = "URL_SOFTBANK_CATALOGO_TIPO_FUNDA";
	public static final String CATALOGO_TIPO_JOYA = "URL_SOFTBANK_CATALOGO_TIPO_JOYA";
	public static final String CATALOGO_TIPO_ORO = "URL_SOFTBANK_CATALOGO_TIPO_ORO";
	public static final String CATALOGO_TIPO_REFERENCIA = "URL_SOFTBANK_CATALOGO_TIPO_REFERENCIA";
	public static final String CATALOGO_TIPO_VIVIENDA = "URL_SOFTBANK_CATALOGO_TIPO_VIVIENDA";
	public static final String CATALOGO_OCUPACION = "URL_SOFTBANK_CATALOGO_OCUPACION";
	public static final String CATALOGO_CARGO = "URL_SOFTBANK_CATALOGO_CARGO";
	public static final String CATALOGO_ORIGEN_INGRESOS = "URL_SOFTBANK_CATALOGO_ORIGEN_INGRESOS";
	
	
	
	
	
	public static final String URL_APIGW = "URL_APIGW";
	public static final String AUTH_APIGW = "AUTH_APIGW";
	public static final String URL_WS_PERSONA = "URL_WS_PERSONA";
	public static final String URL_WS_QUSKI_CALCULADORA = "URL_WS_QUSKI_CALCULADORA";
	public static final String URL_WS_QUSKI_CUENTA_MUPI = "URL_WS_QUSKI_CUENTA_MUPI";
	public static final String CONTENT_XML_PERSONA = "CONTENT_XML_PERSONA";
	public static final String CONTENT_XML_QUSKI_CALCULADORA = "CONTENT_XML_QUSKI_CALCULADORA";
	public static final String CONTENT_XML_QUSKI_CALCULADORA_RENOVAR = "CONTENT_XML_QUSKI_CALCULADORA_RENOVAR";
	public static final String CONTENT_XML_GARANTIA = "CONTENT_XML_GARANTIA";
	public static final String CONTENT_XML_CUENTA_MUPI = "CONTENT_XML_CUENTA_MUPI";

	// INTEGRACION
	public static final String URL_PERSONA ="http://localhost:28080/quski-oro-rest/resources/integracionRestController/getInformacionPersona";
	public static final String PARAMETRO_1 ="?tipoIdentificacion=";
	public static final String PARAMETRO_2 ="&identificacion=";
	public static final String PARAMETRO_3 ="&tipoConsulta=";
	public static final String PARAMETRO_4 ="&calificacion=";

	// CRM SERVICES 
	public static final String URL_CRM_PROSPECTO_CORTO ="URL_CRM_PROSPECTO_CORTO";
	public static final String URL_CRM_PERSIST ="URL_CRM_PERSIST";

	
	//STORAGE
	public static final String COLLECTION_NAME = "COLLECTION_NAME";
	public static final String DATA_BASE_NAME = "DATA_BASE_NAME";
	public static final String URL_STORAGE = "URL_STORAGE";
	
	//PAGO SERVICE
	public static final String ERROR_AL_INTENTAR_LEER_LA_INFORMACION = "ERROR AL INTENTAR LEER LA INFORMACION";
	//E-MAIL
	public static final String emailSecurityType = "emailSecurityType";
	public static final String smtpHostServer = "smtpHostServer";
	public static final String portEmail = "portEmail";
	public static final String sfPortEmail = "sfPortEmail";
	public static final String userEmail = "userEmail";
	public static final String fromEmailDesa = "fromEmailDesa";
	public static final String authEmail = "authEmail";
	public static final String passwordEmail = "passwordEmail";
	public static final String TIPO = "MAIL";
	public static final String TEXTO_APROBACION_PAGO = "TEXTO_APROBACION_PAGO";
	public static final String CODIGO_BANCO_MUPI = "CODIGO_BANCO_MUPI";
	
	

  
}
