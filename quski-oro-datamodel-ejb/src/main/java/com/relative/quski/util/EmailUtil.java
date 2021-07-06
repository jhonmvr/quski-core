package com.relative.quski.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.enums.EmailSecurityTypeEnum;
import com.relative.core.util.enums.EmailStorageTypeEnum;
import com.relative.core.util.mail.ByteArrayDataSource;
import com.relative.core.util.mail.EmailDefinition;
import com.relative.core.util.mail.MailMessageDefinition;
import com.relative.core.util.main.Constantes;


public class EmailUtil {

	private static Logger log = Logger.getLogger(EmailUtil.class.getName());

	public static void main(String args[]) {
		/*Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.NOVEMBER, 26);

		Calendar calendarx = Calendar.getInstance();
		calendarx.set(2016, Calendar.NOVEMBER, 29);
		
		EmailDefinition ed = new EmailDefinition.Builder().emailSecurityType(EmailSecurityTypeEnum.SSL)
				.smtpHostServer("imap.gmail.com").port("465").sfPort("465").auth(Boolean.TRUE).password("relative.2020")
				.user("desa.twelve@gmail.com")
				.mailFolder( "[Gmail]/Sent Mail" )
				.dateRangeBegin(calendar.getTime()).dateRangeEnd(calendarx.getTime()).build();
		readInbox(ed, EmailStorageTypeEnum.IMAP);
		*/
		EmailDefinition ed = new EmailDefinition.Builder().emailSecurityType(EmailSecurityTypeEnum.TLS)
				.smtpHostServer("smtp.gmail.com").port("587").sfPort("465").auth(Boolean.TRUE).password("Quski*2018")
				.user("soporte@quski.ec")
				.subject("PRUEBA ZER TESTE")
				.tos( Arrays.asList( new String[] {"jeroham126@gmail.com"} ) )
				.fromEmail( "soporte@quski.ec" )
				.message("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"><head> <meta charset=\"UTF-8\"> <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> <meta name=\"x-apple-disable-message-reformatting\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <meta content=\"telephone=no\" name=\"format-detection\"> <title>Correo del cash</title> <!--[if (mso 16)]><style type=\"text/css\"> a {text-decoration: none;} </style><![endif]--> <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> <!--[if gte mso 9]><xml> <o:OfficeDocumentSettings> <o:AllowPNG></o:AllowPNG> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml><![endif]--> <!--[if !mso]><!-- --> <link href=\"https://fonts.googleapis.com/css?family=Merriweather:400,400i,700,700i\" rel=\"stylesheet\"> <link href=\"https://fonts.googleapis.com/css?family=Merriweather+Sans:400,400i,700,700i\" rel=\"stylesheet\"> <!--<![endif]--> <style type=\"text/css\"> #outlook a { padding: 0; } .ExternalClass { width: 100%; } .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; } .es-button { mso-style-priority: 100 !important; text-decoration: none !important; } a[x-apple-data-detectors] { color: inherit !important; text-decoration: none !important; font-size: inherit !important; font-family: inherit !important; font-weight: inherit !important; line-height: inherit !important; } .es-desk-hidden { display: none; float: left; overflow: hidden; width: 0; max-height: 0; line-height: 0; mso-hide: all; } [data-ogsb] .es-button { border-width: 0 !important; padding: 10px 20px 10px 20px !important; } @media only screen and (max-width:600px) { p, ul li, ol li, a { line-height: 150% !important } h1 { font-size: 30px !important; text-align: left; line-height: 120% !important } h2 { font-size: 26px !important; text-align: left; line-height: 120% !important } h3 { font-size: 20px !important; text-align: left; line-height: 120% !important } h1 a { text-align: left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size: 30px !important } h2 a { text-align: left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size: 26px !important } h3 a { text-align: left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size: 20px !important } .es-menu td a { font-size: 14px !important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size: 14px !important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size: 16px !important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size: 14px !important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size: 12px !important } *[class=\"gmail-fix\"] { display: none !important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align: center !important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align: right !important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align: left !important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display: inline !important } .es-button-border { display: block !important } a.es-button, button.es-button { font-size: 20px !important; display: block !important; border-left-width: 0px !important; border-right-width: 0px !important } .es-btn-fw { border-width: 10px 0px !important; text-align: center !important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width: 100% !important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width: 100% !important; max-width: 600px !important } .es-adapt-td { display: block !important; width: 100% !important } .adapt-img { width: 100% !important; height: auto !important } .es-m-p0 { padding: 0px !important } .es-m-p0r { padding-right: 0px !important } .es-m-p0l { padding-left: 0px !important } .es-m-p0t { padding-top: 0px !important } .es-m-p0b { padding-bottom: 0 !important } .es-m-p20b { padding-bottom: 20px !important } .es-mobile-hidden, .es-hidden { display: none !important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width: auto !important; overflow: visible !important; float: none !important; max-height: inherit !important; line-height: inherit !important } tr.es-desk-hidden { display: table-row !important } table.es-desk-hidden { display: table !important } td.es-desk-menu-hidden { display: table-cell !important } .es-menu td { width: 1% !important } table.es-table-not-adapt, .esd-block-html table { width: auto !important } table.es-social { display: inline-block !important } table.es-social td { display: inline-block !important } } </style></head><body style=\"width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"> <div class=\"es-wrapper-color\" style=\"background-color:#EFEFEF\"> <!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" color=\"#efefef\"></v:fill> </v:background><![endif]--> <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\"> <tr style=\"border-collapse:collapse\"> <td valign=\"top\" style=\"padding:0;Margin:0\"> <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> <tr style=\"border-collapse:collapse\"> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-header-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#E6EBEF;width:600px\"> <tr style=\"border-collapse:collapse\"> <td align=\"left\" style=\"padding:20px;Margin:0\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr style=\"border-collapse:collapse\"> <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr style=\"border-collapse:collapse\"> <td align=\"center\" style=\"padding:10px;Margin:0\"> <h3 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#333333\"> <b>RECHAZO DE VERIFICACION DE FIRMAS</b> </h3> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> <tr style=\"border-collapse:collapse\"> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\"> <tr style=\"border-collapse:collapse\"> <td align=\"left\" style=\"Margin:0;padding-left:30px;padding-right:30px;padding-top:40px;padding-bottom:40px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr style=\"border-collapse:collapse\"> <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:540px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr style=\"border-collapse:collapse\"> <td align=\"left\" style=\"padding:0;Margin:0;padding-top:15px\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#999999;font-size:14px\"> Estimado(a) <b>estefania.granda</b>, se ha negado la verificacion de firmas del proceso de devolucion: DEVC0000020. Por favor vuelva a solicitar la aprobacion. &nbsp; Un gusto servirle.<br><br>Saludos cordiales.</p> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> <tr style=\"border-collapse:collapse\"> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-footer-body\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#E6EBEF;width:600px\"> <tr style=\"border-collapse:collapse\"> <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr style=\"border-collapse:collapse\"> <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr style=\"border-collapse:collapse\"> <td align=\"center\" style=\"padding:0;Margin:0\"> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:20px;color:#999999;font-size:13px\"> <br></p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'merriweather sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#999999;font-size:18px;text-align:center\"> <a target=\"_blank\" href=\"tel:123456789\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#999999;font-size:18px;font-family:'merriweather sans', 'helvetica neue', helvetica, arial, sans-serif\">Q</a>uski Core</p> <p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:23px;color:#999999;font-size:15px\"> Correo generado automaticamente.</p> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </div></body></html>")
				.hasFiles(Boolean.FALSE)
				.build();
		//TSL
		/*byte[] fileContent =null;
		byte[] attachmentUno =null;
		byte[] attachmentDos =null;
		Map<String, byte[]> as=new HashMap<>();
		try {
			File f = new File("C:/Users/root/Downloads/WhatsAppImage2019-01-04.jpeg");
			fileContent = Files.readAllBytes(f.toPath() );
			f = new File("C:/Users/root/Downloads/871736379144.pdf");
			attachmentUno = Files.readAllBytes(f.toPath() );
			f = new File("C:/Users/root/Downloads/871733196067.pdf");
			attachmentDos = Files.readAllBytes(f.toPath() );
			
			as.put("attachmentUno", attachmentUno);
			as.put("attachmentDos", attachmentDos);
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		EmailDefinition ed = new EmailDefinition.Builder().emailSecurityType(EmailSecurityTypeEnum.TLS)
				.smtpHostServer("smtp.gmail.com").port("587").sfPort("587").auth(Boolean.TRUE).password("abadon")
				.user("luisatmd@gmail.com")
				.subject("PRUEBA LOCAL")
				.tos( Arrays.asList( new String[] {"luis.alberto.tamayo@gmail.com"} ) )
				.fromEmail( "test@relative-engine.com" )
				.message("<table style=\"background-color: #ff0000;\"><tr>"
						+ "<td>mensaje de prueba<img src=\"cid:image\"></td></tr></table>")
				.image(fileContent)
				.attachments(as)
				.hasFiles(Boolean.TRUE)
				.build();*/
		//SIMPLE
		/*EmailDefinition ed = new EmailDefinition.Builder().emailSecurityType(EmailSecurityTypeEnum.TLS)
				.smtpHostServer("172.16.101.105").port("25").sfPort("25").auth(Boolean.FALSE).password("Seguros2018")
				.user("notificacionesn")
				.subject("PRUEBA LOCAL")
				.tos( Arrays.asList( new String[] {"nmera@segurossucre.fin.ec"} ) )
				.fromEmail( "notificaciones@segurossucre.fin.ec" )
				.message("<table><tr><td>mensaje de prueba</td></tr></table>")
				.build();*/
		try {
			//ed.setSession( provideSession(ed, EmailSecurityTypeEnum.SIMPLE) );
			ed.setSession( provideSession(ed, EmailSecurityTypeEnum.SSL) );
			sendEmail( ed );
		} catch (RelativeException e) {
			System.out.println("ERROR " + e.getDetalle() + "  " + e.getMensaje());
		}
		
	}

	/**
	 * Metodo que genera la session de correo electronico
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @param es
	 *            tipo de seguridad a aplicar en el mail
	 * @return Session generada
	 */
	public static Session provideSession(EmailDefinition ed, EmailSecurityTypeEnum es) {
		switch (es) {
		case TLS:
			return provideSessionTLS(ed);
		case SSL:
			return provideSessionSSL(ed);
		case SIMPLE:
			return provideSessionSimple(ed);
		default:
			return provideSessionSimple(ed);
		}
	}

	/**
	 * Metodo que genera la session de correo electronico por defecto
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionSimple(EmailDefinition ed) {
		Properties props = new Properties();
    log.info( "===> llega a provideSessionSimple " + ed.getSmtpHostServer() ) ;
    log.info( "===> llega a provideSessionSimple " + ed.getAuth() ) ;       
    /*if( ed.getAuth() == null ){
      ed.setAuth(false);
    } */                                                                   
		props.put(Constantes.MAIL_SMTP_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_SMTP_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_SMTP_PORT_PROPS, ed.getPort());
		return Session.getInstance(props, ed.getAuth() ? getAuthenticator(ed.getUser(), ed.getPassword()) : null);
	}

	/**
	 * Metodo que genera la session de correo electronico con SSL
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionSSL(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_SMTP_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_SMTP_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_SMTP_PORT_PROPS, ed.getPort());
		props.put(Constantes.MAIL_SMTP_SOCKET_FACTORY_PORT_PROPS, ed.getSfPort());
		props.put(Constantes.MAIL_SMTP_SOCKET_FACTORY_CLASS_PROPS,
				Constantes.MAIL_SMTP_SOCKET_FACTORY_CLASS_VALUE_PROPS);
		return Session.getInstance(props, ed.getAuth() ? getAuthenticator(ed.getUser(), ed.getPassword()) : null);
	}

	/**
	 * Metodo que genera la session de correo electronico con TLS
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionTLS(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_SMTP_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_SMTP_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_SMTP_PORT_PROPS, ed.getPort());
		props.put(Constantes.MAIL_SMTP_TLS_PROPS, "true");
		return Session.getInstance(props, ed.getAuth() ? getAuthenticator(ed.getUser(), ed.getPassword()) : null);
	}

	/**
	 * Metodo que genera la session de correo electronico
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @param es
	 *            tipo de seguridad a aplicar en el mail
	 * @return Session generada
	 */
	public static Session provideSessionPop3(EmailDefinition ed, EmailSecurityTypeEnum es) {
		switch (es) {
		case TLS:
			return provideSessionPopTLS(ed);
		case SSL:
			return provideSessionPopSSL(ed);
		case SIMPLE:
			return provideSessionPopSimple(ed);
		default:
			return provideSessionPopSimple(ed);
		}
	}

	/**
	 * Metodo que genera la session de correo electronico por defecto
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionPopSimple(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_POP3_HOST_PROPS, ed.getSmtpHostServer());
    
		props.put(Constantes.MAIL_POP3_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_POP3_PORT_PROPS, ed.getPort());
		// return Session.getInstance(props, ed.getAuth()?getAuthenticator(ed.getUser(),
		// ed.getPassword()) : null);
		return Session.getInstance(props);
	}

	/**
	 * Metodo que genera la session de correo electronico con SSL
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionPopSSL(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_POP3_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_POP3_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_POP3_PORT_PROPS, ed.getPort());
		props.put(Constantes.MAIL_POP3_SOCKET_FACTORY_PORT_PROPS, ed.getSfPort());
		props.put(Constantes.MAIL_POP3_SOCKET_FACTORY_CLASS_PROPS,
				Constantes.MAIL_POP3_SOCKET_FACTORY_CLASS_VALUE_PROPS);
		return Session.getInstance(props, ed.getAuth() ? getAuthenticator(ed.getUser(), ed.getPassword()) : null);
	}

	/**
	 * Metodo que genera la session de correo electronico con TLS
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionPopTLS(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_POP3_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_POP3_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_POP3_PORT_PROPS, ed.getPort());
		props.put(Constantes.MAIL_POP3_TLS_PROPS, "true");
		return Session.getInstance(props, ed.getAuth() ? getAuthenticator(ed.getUser(), ed.getPassword()) : null);
	}

	/**
	 * Metodo que genera la session de correo electronico
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @param es
	 *            tipo de seguridad a aplicar en el mail
	 * @return Session generada
	 */
	public static Session provideSessionImap(EmailDefinition ed, EmailSecurityTypeEnum es) {
		switch (es) {
		case TLS:
			return provideSessionImapTLS(ed);
		case SSL:
			return provideSessionImapSSL(ed);
		case SIMPLE:
			return provideSessionImapSimple(ed);
		default:
			return provideSessionImapSimple(ed);
		}
	}

	/**
	 * Metodo que genera la session de correo electronico por defecto
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionImapSimple(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_POP3_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_POP3_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_POP3_PORT_PROPS, ed.getPort());
		// return Session.getInstance(props, ed.getAuth()?getAuthenticator(ed.getUser(),
		// ed.getPassword()) : null);
		return Session.getInstance(props);
	}

	/**
	 * Metodo que genera la session de correo electronico con SSL
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionImapSSL(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_IMAP_STORE_PROTOCOL_PROPS, Constantes.MAIL_IMAP);
		props.put(Constantes.MAIL_POP3_PORT_PROPS, ed.getPort());
		props.put(Constantes.MAIL_POP3_SOCKET_FACTORY_PORT_PROPS, ed.getSfPort());
		props.put(Constantes.MAIL_POP3_SOCKET_FACTORY_CLASS_PROPS,
				Constantes.MAIL_POP3_SOCKET_FACTORY_CLASS_VALUE_PROPS);
		// return Session.getInstance(props, ed.getAuth()?getAuthenticator(ed.getUser(),
		// ed.getPassword()) : null);
		return Session.getInstance(props);
	}

	/**
	 * Metodo que genera la session de correo electronico con TLS
	 * 
	 * @param ed
	 *            definicion de envio de correo
	 * @return Session generada
	 */
	public static Session provideSessionImapTLS(EmailDefinition ed) {
		Properties props = new Properties();
		props.put(Constantes.MAIL_POP3_HOST_PROPS, ed.getSmtpHostServer());
		props.put(Constantes.MAIL_POP3_AUTH_PROPS, ed.getAuth());
		props.put(Constantes.MAIL_POP3_PORT_PROPS, ed.getPort());
		props.put(Constantes.MAIL_POP3_TLS_PROPS, "true");
		return Session.getInstance(props, ed.getAuth() ? getAuthenticator(ed.getUser(), ed.getPassword()) : null);
	}

	/**
	 * Metodo que retorna un objeto de tipo autenticador para gestion en email
	 * 
	 * @param user
	 *            usuario a autenticar
	 * @param password
	 *            clave a autenticar
	 * @return Autenticador generado
	 */
	private static Authenticator getAuthenticator(String user, String password) {
		final String userLoc = user;
		final String passwordLoc = password;
		return new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userLoc, passwordLoc);
			}
		};
	}

	/**
	 * Metodo utilitario para envio de correo basico
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(EmailDefinition ed) throws RelativeException {

		if( ed.getHasFiles()==null || !ed.getHasFiles() ) {
			sendEmailNoMultipart( ed); 
		} else {
			sendEmailMultipart( ed); 
		}
	}
	
	private static void sendEmailNoMultipart(EmailDefinition ed) throws RelativeException {

		try {
			MimeMessage msg = new MimeMessage(ed.getSession());
			// set message headers
			msg.addHeader("Content-type",
					Constantes.MAIL_MIME_TYPE_TEXT + "; charset=" + Constantes.MAIL_DEFAULT_CHARSET);
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(ed.getFromEmail(), Boolean.TRUE));
			if (ed.getReplyTo() != null)
				msg.setReplyTo(InternetAddress.parse(ed.getReplyTo(), Boolean.TRUE));
			if (ed.getTos() != null && !ed.getTos().isEmpty())
				msg.setRecipients(RecipientType.TO, generateAddress(ed.getTos()));
			if (ed.getCcs() != null && !ed.getCcs().isEmpty())
				msg.setRecipients(RecipientType.CC, generateAddress(ed.getCcs()));
			msg.setSubject(ed.getSubject(), Constantes.MAIL_DEFAULT_CHARSET);
			//msg.setText(ed.getMessage(), Constantes.MAIL_DEFAULT_CHARSET);
			msg.setText(ed.getMessage(), Constantes.MAIL_DEFAULT_CHARSET, Constantes.MAIL_SUB_TYPE_HTML);
			msg.setSentDate(new Date());
			log.info("====>MENSAJE NO MULTIPART LISTO PARA ENVIO");
			Transport.send(msg);
			log.info("====>MENSAJE ENVIADO");
		} catch (AddressException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR sendEmailNoMultipart AddressException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
			log.info("erro enviar correo " + e.getMessage());
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR sendEmailNoMultipart MessagingException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		}
	}
	
	private static void sendEmailMultipart(EmailDefinition ed) throws RelativeException {

		try {
			MimeMessage msg = new MimeMessage(ed.getSession());
			// set message headers
			msg.addHeader("Content-type",
					Constantes.MAIL_MIME_TYPE_TEXT + "; charset=" + Constantes.MAIL_DEFAULT_CHARSET);
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(ed.getFromEmail(), Boolean.TRUE));
			if (ed.getReplyTo() != null)
				msg.setReplyTo(InternetAddress.parse(ed.getReplyTo(), Boolean.TRUE));
			if (ed.getTos() != null && !ed.getTos().isEmpty())
				msg.setRecipients(RecipientType.TO, generateAddress(ed.getTos()));
			if (ed.getCcs() != null && !ed.getCcs().isEmpty())
				msg.setRecipients(RecipientType.CC, generateAddress(ed.getCcs()));
			msg.setSubject(ed.getSubject(), Constantes.MAIL_DEFAULT_CHARSET);
			//msg.setText(ed.getMessage(), Constantes.MAIL_DEFAULT_CHARSET, Constantes.MAIL_SUB_TYPE_HTML);
			msg.setSentDate(new Date());
			// This mail has 2 part, the BODY and the embedded image
	        MimeMultipart multipart = new MimeMultipart("related");
	        // first part (the html)
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(ed.getMessage(), "text/html");
	        // add it
	        multipart.addBodyPart(messageBodyPart); 
	        // second part (the image)
	        if( ed.getImage() != null && ed.getImage().length>0 ) {
	        	// add image to the multipart
	        	multipart.addBodyPart(addImage( ed));
	        }
	        if( ed.getAttachments() != null && !ed.getAttachments().isEmpty() ) {
	        	Set<String> keys = ed.getAttachments().keySet();
	        	for( String k : keys ) {
	        		multipart.addBodyPart(addAttachent(k, ed.getAttachments().get(k)));
	        	}
	        }
	        
			log.info("====>MENSAJE MULTIPART LISTO PARA ENVIO");
			msg.setContent(multipart);
			Transport.send(msg);
			log.info("====>MENSAJE ENVIADO");
		} catch (AddressException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR sendEmailMultipart AddressException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR sendEmailMultipart MessagingException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		} catch (RelativeException e) {
			throw e;
		}
	}
	
	private static BodyPart addImage(EmailDefinition ed) throws RelativeException {
		
		try {
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource fds = new ByteArrayDataSource(ed.getImage(),Constantes.MAIL_MIME_TYPE_PNG);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", Constantes.MAIL_MULTIPART_IMAGE );
			return messageBodyPart;
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR addImage MessagingException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		}
	}
	
	private static BodyPart addAttachent(String fileName, byte[] attachment) throws RelativeException {
		
		try {
			BodyPart messageBodyPart = new MimeBodyPart();
			DataSource fds = new ByteArrayDataSource(attachment, Constantes.MAIL_MIME_TYPE_PDF);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setFileName(fileName);
			return messageBodyPart;
		} catch (MessagingException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR addAttachent MessagingException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		}
	}

	/**
	 * Metodo que genera los addres de envio en funcion del listado enviado
	 * 
	 * @param addresses
	 *            Listado de direcciones
	 * @return listado de direcciones en formato mail
	 * @throws RelativeException
	 */
	private static InternetAddress[] generateAddress(List<String> addresses) throws RelativeException {
		try {
			List<InternetAddress> locals = new ArrayList<>();
			for (String local : addresses) {
				locals.add(new InternetAddress(local, Boolean.TRUE));
			}
			return locals.toArray(new InternetAddress[locals.size()]);
		} catch (AddressException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR AddressException " + EmailUtil.class.getName() + " SENDEMAIL " + e.getMessage());
		}
	}
	
	/**
	 * Metodo que obtiene el storage (folders) del servidor de correo de la cuenta enviada
	 * @param ed Definicion del correo electronico
	 * @param es Tipo de stora a usar imap, pop, stmtp
	 * @return Stroage generado
	 * @throws RelativeException
	 */
	private static Store getStorage( EmailDefinition ed, EmailStorageTypeEnum es ) throws RelativeException{
		try {
			Session emailSession = null;
			if (EmailStorageTypeEnum.IMAP.equals(es)) {
				emailSession = provideSessionImap(ed, ed.getEmailSecurityType());
				return emailSession.getStore(Constantes.MAIL_IMAP);
			} else {
				emailSession = provideSessionPop3(ed, ed.getEmailSecurityType());
				return emailSession.getStore(Constantes.MAIL_POP3);
			}
		} catch (NoSuchProviderException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR NoSuchProviderException getStorage AL OBTENER LA INFORMACION DE STORA DEL SERVIDOR DE EMAIL" );
		}
	}
	
	private static Message[] getMessagesFromStore(EmailDefinition ed, Store store,Folder emailFolder) throws RelativeException{
		try {
			Message[] messages=null;
			SearchTerm searchTerm=null;
			if( ed.getDateRangeBegin() != null && ed.getDateRangeEnd() != null ) {
				// fecha inicio
				SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, ed.getDateRangeBegin());
				// fecha fin
				SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, ed.getDateRangeEnd());
				searchTerm = new AndTerm(olderThan, newerThan);
			} else if( ed.getDateRangeBegin() != null && ed.getDateRangeEnd() == null ) {
				searchTerm = new ReceivedDateTerm(ComparisonTerm.GT, ed.getDateRangeBegin());
			} else if( ed.getDateRangeBegin() == null && ed.getDateRangeEnd() != null ) {
				searchTerm  = new ReceivedDateTerm(ComparisonTerm.LT, ed.getDateRangeEnd());
			} else if( ed.getDateRangeBegin() == null && ed.getDateRangeEnd() != null ) {
				searchTerm  = new ReceivedDateTerm(ComparisonTerm.LT, ed.getDateRangeEnd());
			}
			emailFolder.open(Folder.READ_ONLY);
			if( searchTerm != null ) {
				messages= emailFolder.search(searchTerm);
			} else {
				messages=emailFolder.getMessages();
			}
			return messages;
		} catch (MessagingException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR NoSuchProviderException getStorage AL OBTENER LA INFORMACION DE STORA DEL SERVIDOR DE EMAIL" );
		}
	}

	public static List<MailMessageDefinition> readInbox(EmailDefinition ed, EmailStorageTypeEnum es) {
		try {
			List<MailMessageDefinition> inboxMessages = new ArrayList<>();
			Store store = getStorage(ed, es);
			store.connect(ed.getSmtpHostServer(), ed.getUser(), ed.getPassword());
			Folder[] f = store.getDefaultFolder().list("*");
			for (Folder fd : f) {
				log.info(">> " + fd.getName());
			}
			Folder emailFolder = store.getFolder(ed.getMailFolder());
			/*
			// fecha inicio
			SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, ed.getDateRangeBegin());
			// fecha fin
			SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, ed.getDateRangeEnd());
			SearchTerm andTerm = new AndTerm(olderThan, newerThan);
			emailFolder.open(Folder.READ_ONLY);
			Message[] messages = emailFolder.search(andTerm);*/
			Message[] messages = getMessagesFromStore(ed, store,emailFolder);
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				log.info("---------------------------------");
				log.info("Email Number " + (i + 1));
				log.info("Subject: " + message.getSubject());
				log.info("Swns DATE: " + message.getSentDate());
				log.info("receive DATE: " + message.getReceivedDate());
				inboxMessages.add(new MailMessageDefinition.Builder().froms(getAddresAsStringList(message.getFrom()))
						.subject(message.getSubject()).contentStr(message.getContent().toString())
						.sentDate( message.getSentDate() )
						.receivedDate( message.getReceivedDate() ).build());
				// log.info("Text: " + message.getContent().toString());

			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();
			return inboxMessages;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<String> getAddresAsStringList(Address[] addresses) {
		List<String> addrsStr = new ArrayList<>();
		for (Address a : addresses) {
			log.info("From: " + a);
			addrsStr.add(((InternetAddress) a).getAddress());
		}
		return addrsStr;
	}

}