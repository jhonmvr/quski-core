package com.relative.quski.util;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.wrapper.CalculadoraEntradaWrapper;
import com.relative.quski.wrapper.YearMonthDay;

public class QuskiOroUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_FORMAT_QUSKI = "dd/MM/yyyy";
	public final static String DATE_FORMAT_SOFTBANK = "yyyy-MM-dd";
	public final static String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static String garantias = "";

	public static void main(String[] args) {
		System.out.println(adicionEnDias(new Date(), Long.valueOf("95")));

		String x = "oso@gamil.com";
		String y = "oso@gamil.com";
		x = encodeBase64(x);
		System.out.println("encoded: " + x);
		x = decodeBase64(x);
		System.out.println("deencoded: " + x);
		y = encodeId(y);
		System.out.println("encoded id: " + y);

		try {
			y = decodeId(y);
			System.out.println("deencoded id: " + y);
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String encodeBase64(String origin) {
		return Base64.getEncoder().encodeToString(origin.getBytes());
	}

	public static String decodeBase64(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		return new String(decodedBytes);
	}

	public static String encodeId(String id) {
		return UUID.randomUUID() + "|" + encodeBase64(id);
	}

	public static String decodeId(String encoded) throws RelativeException {
		try {
			String sep[] = encoded.split("\\|");
			if (sep != null && sep.length > 1) {
				String tmp = decodeBase64(sep[1]);
				if (tmp.indexOf("@") <= 0) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR ID INVALIDO");
				}
				return tmp;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR ID INVALIDO");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "ERROR ID INVALIDO " + e.getMessage());
		}
	}

	/**
	 * Convierte la cadena que viene en base 64 a un arreglo de bytes
	 * 
	 * @param base64
	 * @return
	 */
	public static byte[] convertBase64ToByteArray(String base64) throws RelativeException {
		try {
			return Base64.getDecoder().decode(base64);
		} catch (Exception e) {
			throw new RelativeException("ERROR AL CONVERTIR LA BASE 64 STRING EN ARREGLO DE BYTES");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T getEnumFromString(Class<T> enumClass, String value) {
		if (enumClass == null) {
			throw new IllegalArgumentException("EnumClass value can't be null.");
		}

		for (Enum<?> enumValue : enumClass.getEnumConstants()) {
			if (enumValue.toString().equalsIgnoreCase(value)) {
				return (T) enumValue;
			}
		}

		// Construct an error message that indicates all possible values for the enum.
		StringBuilder errorMessage = new StringBuilder();
		boolean bFirstTime = true;
		for (Enum<?> enumValue : enumClass.getEnumConstants()) {
			errorMessage.append(bFirstTime ? "" : ", ").append(enumValue);
			bFirstTime = false;
		}
		throw new IllegalArgumentException(value + " is invalid value. Supported values are " + errorMessage);
	}

	private static <T> T map(Class<T> type, Object[] tuple) {
		List<Class<?>> tupleTypes = new ArrayList<>();
		for (Object field : tuple) {

			tupleTypes.add(field.getClass());

		}
		try {
			Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
			return ctor.newInstance(tuple);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> List<T> map(Class<T> type, List<Object[]> records) {
		List<T> result = new LinkedList<>();
		for (Object[] record : records) {
			result.add(map(type, record));
		}
		return result;
	}

	public static <T> List<T> getResultList(List<Object[]> records, Class<T> type) {
		return map(type, records);
	}

	public static String convertListToString(List<String> strings) {
		if (strings != null && !strings.isEmpty()) {
			String tmp = "";
			for (String item : strings) {
				tmp = tmp + item + ",";
			}
			return tmp;
		} else {
			return "";
		}

	}

	@SuppressWarnings("deprecation")
	public static String dateToString(Date date) throws RelativeException {
		try {
			if (date != null) {
				String str;
				str = String.valueOf(date.getDay()) + "/" + String.valueOf(date.getMonth() + 1) + "/"
						+ String.valueOf(date.getYear() + 1900);
				return str;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException("dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}
	
	@SuppressWarnings("deprecation")
	public static String dateToStringYearMonthDay(Date date) throws RelativeException {
		try {
			if (date != null) {
				String str;
				str = String.valueOf(date.getYear() + 1900 +"/"+ "/" + String.valueOf(date.getMonth() + 1) + "/"
				+ String.valueOf(date.getDay())
				 );
				return str;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException("dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}
	

	public static String dateToString(Date date, String format) throws RelativeException {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(date);
		} catch (Exception e) {
			throw new RelativeException("dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}

	}

	/**
	 * transforma una fecha a texto
	 * 
	 * @param date
	 * @return 4 de julio del 2019
	 * @throws RelativeException
	 */
	@SuppressWarnings("deprecation")
	public static String dateToFullString(Date date) throws RelativeException {
		try {
			String[] mes = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
					"octubre", "noviembre", "diciembre" };
			String fechaSalida;
			fechaSalida = String.valueOf(date.getDate()).concat(" de ").concat(mes[date.getMonth()]).concat(" del ")
					.concat(String.valueOf(date.getYear() + 1900));
			return fechaSalida;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE TRANSFORMAR A TEXTO");
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String dateToCompletelyFullString(Date date) throws RelativeException {
		try {
			String[] mes = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
					"octubre", "noviembre", "diciembre" };
			String fechaSalida;
			fechaSalida = String.valueOf(date.getDate()).concat(" días del mes de ").concat(mes[date.getMonth()]).concat(" del ")
					.concat(String.valueOf(date.getYear() + 1900));
			return fechaSalida;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO SE PUEDE TRANSFORMAR A TEXTO");
		}
	}

	public static Long diasFecha(Date fechaIni, Date fechaFin) {
		return ((fechaFin.getTime() - fechaIni.getTime()) / 86400000);
	}
	public static Long horasFecha(Date fechaIni, Date fechaFin) {
		return ((fechaFin.getTime() - fechaIni.getTime()) / 86400000);
	}
	public static Long getEdad(Date fecha) {
		Date toDate = new Date();
		Long anios = (((toDate.getTime() - fecha.getTime()) / 86400000) / 365);
		return (long) Math.floor(anios);
	}

	public static Date adicionEnDias(Date fecha, Long numeroDias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, numeroDias.intValue()); // minus number would decrement the days
		return cal.getTime();
		/*
		 * Date fechaNueva; fechaNueva = new Date( (fecha.getTime()/86400000 +
		 * numeroDias) * 86400000 ); return fechaNueva;
		 */
	}

	public static Date formatSringToDate(String date) throws RelativeException {
		try {
			SimpleDateFormat dfIn = new SimpleDateFormat(DATE_FORMAT);
			// System.out.println("===> formatSringToDate impresion de fecha convergtida " +
			// new Date(dfIn.parse(date ).getTime()));
			return new Date(dfIn.parse(date).getTime());
		} catch (ParseException e) {
			throw new RelativeException(
					"SmsESRestController formatearCadenaFechaDate ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}
		/*
		 * DateTime dateTime = new DateTime(date); return
		 * DateTimeFormat.forPattern(DATE_FORMAT).print(dateTime);
		 */
	}

	public static Date formatSringToDate(String date, String dateFormat) throws RelativeException {
		try {
			SimpleDateFormat dfIn = new SimpleDateFormat(dateFormat);
			// System.out.println("===> formatSringToDate impresion de fecha convergtida " +
			// new Date(dfIn.parse(date ).getTime()));
			return new Date(dfIn.parse(date).getTime());
		} catch (ParseException e) {
			throw new RelativeException(
					"SmsESRestController formatearCadenaFechaDate ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}
		/*
		 * DateTime dateTime = new DateTime(date); return
		 * DateTimeFormat.forPattern(DATE_FORMAT).print(dateTime);
		 */
	}
	
	public static String formatSringToDate(Timestamp ts, String dateFormat) throws RelativeException {
		try {
			Date date = new Date();
			date.setTime(ts.getTime());
			return new SimpleDateFormat("yyyyMMdd").format(date);
		} catch (Exception e) {
			return "";
		}
		/*
		 * DateTime dateTime = new DateTime(date); return
		 * DateTimeFormat.forPattern(DATE_FORMAT).print(dateTime);
		 */
	}

	@SuppressWarnings("deprecation")
	public static String dateToStringFormat(Date date) throws RelativeException {
		try {
			String ano = String.valueOf(date.getYear() + 1900);
			String mes = String.valueOf(date.getMonth() + 1);
			String dia = String.valueOf(date.getDate());

			String fecha = "";
			fecha.concat(ano).concat("-").concat(mes).concat("-").concat(dia);
			// format YYYY-MM-DD
			return fecha;
		} catch (Exception e) {
			throw new RelativeException(
					"SmsESRestController dateToStringFormat ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}
		/*
		 * DateTime dateTime = new DateTime(date); return
		 * DateTimeFormat.forPattern(DATE_FORMAT).print(dateTime);
		 */
	}

	public static Date formatSringToDateFull(String date) throws RelativeException {
		try {
			SimpleDateFormat dfIn = new SimpleDateFormat(DATE_FORMAT_FULL);
			// System.out.println("===> formatSringToDate impresion de fecha convergtida " +
			// new Date(dfIn.parse(date ).getTime()) + "fechaaa ======>>" + new
			// Date(dfIn.parse(date ).getTime()));
			return new Date(dfIn.parse(date).getTime());
		} catch (ParseException e) {
			throw new RelativeException(
					"SmsESRestController formatearCadenaFechaDate ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}
		/*
		 * DateTime dateTime = new DateTime(date); return
		 * DateTimeFormat.forPattern(DATE_FORMAT).print(dateTime);
		 */
	}

	public static LocalDate convertDateToLocalDate(Date date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return instant.atZone(defaultZoneId).toLocalDate();
	}

	public static int calculateEdad(Date fechaNac) throws RelativeException {
		LocalDate fechaNacTemp = convertDateToLocalDate(fechaNac);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNacTemp, ahora);
		return periodo.getYears();
	}

	public static YearMonthDay countDaysBetweenDates(Date dateInicio) throws RelativeException {
		try {
			LocalDate startLocalDate = convertDateToLocalDate(dateInicio);
			LocalDate endLocalDate = LocalDate.now();
			Period periodo = Period.between(startLocalDate, endLocalDate);
			return new YearMonthDay(periodo.get(YEARS), periodo.get(MONTHS), periodo.get(DAYS));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, e.getMessage());
		}
	}

	public static StringBuilder CalculadoraToStringXml(CalculadoraEntradaWrapper wrapper) throws RelativeException {
		StringBuilder xml = new StringBuilder();
		wrapper.getGarantias().forEach(e -> {
			StringBuilder garantiaBuider = new StringBuilder();
			garantiaBuider.append("							<Garantia>\n" + "								<TipoJoya>"
					+ e.getTipoJoya() + "</TipoJoya>\n" + "								<Descripcion>"
					+ e.getDescripcion() + "</Descripcion>\n" + "								<EstadoJoya>"
					+ e.getEstadoJoya() + "</EstadoJoya>\n" + "								<TipoOroKilataje>"
					+ e.getTipoOroKilataje() + "</TipoOroKilataje>\n" + "								<PesoGr>"
					+ e.getPesoGr() + "</PesoGr>\n" + "								<TienePiedras>"
					+ e.getTienePiedras() + "</TienePiedras>\n" + "								<DetallePiedras>"
					+ e.getDetallePiedras() + "</DetallePiedras>\n"
					+ "								<DescuentoPesoPiedras>" + e.getDescuentoPesoPiedras()
					+ "</DescuentoPesoPiedras>\n" + "								<PesoNeto>" + e.getPesoNeto()
					+ "</PesoNeto>\n" + "								<PrecioOro>" + e.getPrecioOro()
					+ "</PrecioOro>\n" + "								<ValorAplicableCredito>"
					+ e.getValorAplicableCredito() + "</ValorAplicableCredito>\n"
					+ "								<ValorRealizacion>" + e.getValorRealizacion()
					+ "</ValorRealizacion>\n" + "								<NumeroPiezas>" + e.getNumeroPiezas()
					+ "</NumeroPiezas>\n" + "								<DescuentoSuelda>" + e.getDescuentoSuelda()
					+ "</DescuentoSuelda>\n" + "							</Garantia>\n");

			garantias += garantiaBuider.toString();
		});

		xml.append("<soap:Envelope\n" + "	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
				+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" + "	<soap:Body>\n" + "		<CalculadoraQuski\n"
				+ "			xmlns=\"http://tempuri.org/\">\n" + "			<XMlConsulta>\n"
				+ "			<![CDATA[	<carga>\n" + "					<XmlParametrosRiesgo>\n"
				+ "						<ParametrosRiesgo>\n" + "				              <PerfilRiesgo>"
				+ wrapper.getParametroRiesgo().getPerfilRiesgo() + "</PerfilRiesgo>\n"
				+ "				              <OrigenOperacion>" + wrapper.getParametroRiesgo().getOrigenOperacion()
				+ "</OrigenOperacion>\n" + "				              <RiesgoTotal>"
				+ wrapper.getParametroRiesgo().getRiesgoTotal() + "</RiesgoTotal>\n"
				+ "				              <FechaNacimiento>" + wrapper.getParametroRiesgo().getFechaNacimiento()
				+ "</FechaNacimiento>\n" + "				              <PerfilPreferencia>"
				+ wrapper.getParametroRiesgo().getPerfilPreferencia() + "</PerfilPreferencia>\n"
				+ "				              <AgenciaOriginacion>"
				+ wrapper.getParametroRiesgo().getAgenciaOriginacion() + "</AgenciaOriginacion>\n"
				+ "				              <IdentificacionCliente>"
				+ wrapper.getParametroRiesgo().getIdentificacionCliente() + "</IdentificacionCliente>\n"
				+ "				              <CalificacionMupi>" + wrapper.getParametroRiesgo().getCalificacionMupi()
				+ "</CalificacionMupi>\n" + "				              <CoberturaExcepcionada>"
				+ wrapper.getParametroRiesgo().getCoberturaExcepcionada() + "</CoberturaExcepcionada>\n"
				+ "				            </ParametrosRiesgo>\n" + "					</XmlParametrosRiesgo>\n"
				+ "					<XmlGarantias>\n" + "						<Garantias>\n" + garantias
				+ "						</Garantias>\n" + "					</XmlGarantias>\n"
				+ "					<XmlDescuentosNuevaOperacion>\n" + "						<DescuentosOperacion>\n"
				+ "							<SaldoMontoCreditoAnterior></SaldoMontoCreditoAnterior>\n"
				+ "							<SaldoInteresCreditoAnterior></SaldoInteresCreditoAnterior>\n"
				+ "							<MoraCreditoAnterior></MoraCreditoAnterior>\n"
				+ "							<CobranzaCreditoAnterior></CobranzaCreditoAnterior>\n"
				+ "							<TipoCartera></TipoCartera>\n"
				+ "							<MontoFinanciadoCreditoAnterior></MontoFinanciadoCreditoAnterior>\n"
				+ "							<PlazoCreditoAnterior></PlazoCreditoAnterior>\n"
				+ "							<TipoCreditoAnterior></TipoCreditoAnterior>\n"
				+ "							<EstadoCreditoAnterior></EstadoCreditoAnterior>\n"
				+ "							<FechaEfectivaCreditoAnterior></FechaEfectivaCreditoAnterior>\n"
				+ "							<FechaVencimientoCreditoAnterior></FechaVencimientoCreditoAnterior>\n"
				+ "							<MontoSolicitadoCliente>0</MontoSolicitadoCliente>\n"
				+ "							<NumeroOperacionMadre></NumeroOperacionMadre>\n"
				+ "							<NumeroOperacionRenovar></NumeroOperacionRenovar>\n"
				+ "							<REFERENCIA_ADICIONAL></REFERENCIA_ADICIONAL>\n"
				+ "							<OperacionPropia></OperacionPropia>\n"
				+ "						</DescuentosOperacion>\n"
				+ "					</XmlDescuentosNuevaOperacion>\n" + "				</carga> ]]>\n"
				+ "			</XMlConsulta>\n" + "		</CalculadoraQuski>\n" + "	</soap:Body>\n"
				+ "</soap:Envelope>");
		return xml;

	}
//			public static CalculadoraRespuestaWrapper xmlToStringCalculadora( Sintr) throws RelativeException {
//				CalculadoraRespuestaWrapper wrapper = new CalculadoraRespuestaWrapper();
//				System.out.println("=====> RESERVA SINIESTRO SALIENDO DE REST PARA CIERRE XML respuesta " + xmlResponse);
//				if( xmlResponse != null && !xmlResponse.isEmpty() ){
//				  org.w3c.dom.Element  element =null;
//				  org.w3c.dom.Node  child=null;
//				  org.w3c.dom.CharacterData cd=null;
//				  javax.xml.parsers.DocumentBuilder db = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
//				  org.xml.sax.InputSource is = new org.xml.sax.InputSource();
//				  is.setCharacterStream(new java.io.StringReader( xmlResponse.toString() ));
//				  System.out.println("=====> paso seteo de objecto en input ");
//				  org.w3c.dom.Document xmlDoc = db.parse(is);
//				  if( xmlDoc.getElementsByTagName("Errores") != null  &&  xmlDoc.getElementsByTagName("Errores").getLength()>0){
//				    
//				    System.out.println("=====> existe erroress ");
//				    //mensajeError=xmlDoc.getElementsByTagName("Errores").item(0).childNodes[0].nodeValue;
//				    //MENSAJE ERROR===========================================================
//				    System.out.println("=====>LEER XML MENSAJE ERROR ");
//				    element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("Errores").item(0);
//				    System.out.println("=====>SACO ELEMENTO ");
//				    child =element.getFirstChild();
//				    System.out.println("=====>SACO HIJO ");
//				    cd = (org.w3c.dom.CharacterData) child;
//				    System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				    if( cd != null && cd.getData() !=null )  {
//				      mensajeError =cd.getData();
//				      mensajeError=mensajeError.replace("á","a").replace("é","e").replace("í","i").replace("ó","o").replace("ú","u").replace("ñ","n");
//				      //siniestro.setMensaje(mensajeError);
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      if( mensajeError.length()>134 ){
//				        kcontext.setVariable("mensajeError",mensajeError.substring(0,135));
//				        siniestro.setMensaje(mensajeError.substring(0,135));
//				      }  else {
//				        kcontext.setVariable("mensajeError",mensajeError);
//				        siniestro.setMensaje(mensajeError);
//				      }
//				      siniestro.setEstadoSiniestro("MANUAL");
//				    } else {
//				      mensajeError =null;
//				    }
//				    //============================================================================
//				    if( mensajeError == null || mensajeError.isEmpty() ){
//				      //ICORE ANIO===========================================================
//				      System.out.println("=====>LEER XML ANIO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SininfgAno").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreReservaAnio(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================ 
//				      //ICORE NUMERO===========================================================
//				      System.out.println("=====>LEER XML NUMERO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SininfgNum").item(0);
//				      SystCalculadoraRespuestaWrapper wrapper = new CalculadoraRespuestaWrapper();
//						System.out.println("=====> RESERVA SINIESTRO SALIENDO DE REST PARA CIERRE XML respuesta " + xmlResponse);
//						if( xmlResponse != null && !xmlResponse.isEmpty() ){
//						  org.w3c.dom.Element  element =null;
//						  org.w3c.dom.Node  child=null;
//						  org.w3c.dom.CharacterData cd=null;
//						  javax.xml.parsers.DocumentBuilder db = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
//						  org.xml.sax.InputSource is = new org.xml.sax.InputSource();
//						  is.setCharacterStream(new java.io.StringReader( xmlResponse.toString() ));
//						  System.out.println("=====> paso seteo de objecto en input ");
//						  org.w3c.dom.Document xmlDoc = db.parse(is);
//						  if( xmlDoc.getElementsByTagName("Errores") != null  &&  xmlDoem.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreReservaNumero(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      //ICORE CODIGO===========================================================
//				      System.out.println("=====>LEER XML CODIGO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SinepiNum").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreReservaCodigo(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      //ICORE ORDEN DE PAGO===========================================================
//				      System.out.println("=====>LEER XML ORDEN DE PAGO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SinfacOrdPagID").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreOrdenPagoCodigo(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      //ICORE CXP===========================================================
//				      System.out.println("=====>LEER XML CXP");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SinfacCtaxpagcID").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreCxPCodigo(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      siniestro.setEstadoSiniestro("ENVIADO_A_INSPECCION");
//				      
//				      }
//				      kcontext.setVariable("siniestro",siniestro);
//				    }  if( xmlDoc.getElementsByTagName("faultstring") != null  &&  xmlDoc.getElementsByTagName("faultstring").getLength()>0){  
//				           System.out.println("=====>ERROR EN CARGA DE WSDL O PROBLEMA TECNICO ICORE " + xmlDoc.getElementsByTagName("faultstring"));
//				           kcontext.setVariable("mensajeError","ERROR TECNICO LLAMADA ICORE CONSULTE A SU ADMINISTRADOR");
//				           siniestro.setEstadoSiniestro("MANUAL");
//				            siniestro.setMensaje("ERROR TECNICO LLAMADA ICORE CONSULTE A SU ADMINISTRADOR");
//				            kcontext.setVariable("siniestro",siniestro);
//				    } else  if( mensajeError == null || mensajeError.isEmpty() ){
//				      System.out.println("=====>NOOOO existe erroress ");
//				      kcontext.setVariable("mensajeError",null);
//				      //ICORE ANIO===========================================================
//				      System.out.println("=====>LEER XML ANIO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SininfgAno").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreReservaAnio(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================ 
//				      //ICORE NUMERO===========================================================
//				      System.out.println("=====>LEER XML NUMERO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SininfgNum").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreReservaNumero(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      //ICORE CODIGO===========================================================
//				      System.out.println("=====>LEER XML CODIGO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SinepiNum").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreReservaCodigo(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      //ICORE ORDEN DE PAGO===========================================================
//				      System.out.println("=====>LEER XML ORDEN DE PAGO ");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SinfacOrdPagID").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreOrdenPagoCodigo(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      //ICORE CXP===========================================================
//				      System.out.println("=====>LEER XML CXP");
//				      element =null;
//				      element =(org.w3c.dom.Element) xmlDoc.getElementsByTagName("SinfacCtaxpagcID").item(0);
//				      System.out.println("=====>SACO ELEMENTO ");
//				      child =element.getFirstChild();
//				      System.out.println("=====>SACO HIJO ");
//				      cd = (org.w3c.dom.CharacterData) child;
//				      System.out.println("=====>CONVIRTIO A CARACETER DATA");
//				      siniestro.setIcoreCxPCodigo(cd.getData());
//				      System.out.println("=====> mensaje obtenido " + mensajeError);
//				      //============================================================================
//				      siniestro.setEstadoSiniestro("ENVIADO_A_INSPECCION");
//				      kcontext.setVariable("siniestro",siniestro);
//				  }
//				} else {
//				  kcontext.setVariable("mensajeError","ERROR EJECUCION ICORE SERVICIO DE RESERVA"); 
//				  siniestro.setEstadoSiniestro("MANUAL");
//				  siniestro.setMensaje("ERROR EJECUCION ICORE SERVICIO DE RESERVA");
//				  kcontext.setVariable("siniestro",siniestro);
//				}
//		    }

}
