package com.relative.quski.util;

import java.lang.reflect.Constructor;
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

public class MidasOroUtil {
	
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	
	public static void main(String[] args) {
		System.out.println(adicionEnDias(new Date(),Long.valueOf("95")));
		
		String x = "oso@gamil.com";
		String y = "oso@gamil.com";
		x=encodeBase64(x);
		System.out.println("encoded: " + x);
		x=decodeBase64(x);
		System.out.println("deencoded: " + x);
		y=encodeId(y);
		System.out.println("encoded id: " + y);
		
		
		try {
			y=decodeId(y);
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
		return UUID.randomUUID() +"|" +  encodeBase64(id);
	} 
	
	public static String decodeId(String encoded) throws RelativeException {
		try {
			String sep[] = encoded.split("\\|");
			if( sep != null && sep.length>1 ) {
				String tmp= decodeBase64( sep[1] );
				if( tmp.indexOf("@")<=0 ) {
					throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR ID INVALIDO" );
				}
				return tmp;
			} else {
				throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR ID INVALIDO" );
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM, "ERROR ID INVALIDO " + e.getMessage() );
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

	public static <T> List<T> getResultList( List<Object[]> records, Class<T> type) {
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
	
	public static String dateToString(Date date) throws RelativeException{
		try {
			if( date !=null ) {
				String str;
				str =  String.valueOf(date.getDay()) + "/" +String.valueOf(date.getMonth()+1) +"/" +  String.valueOf(date.getYear()+1900);
				return str;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(
					"dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}
		
	}
	
	public static String dateToString(Date date, String format) throws RelativeException{
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);  
            return dateFormat.format(date); 
		} catch (Exception e) {
			throw new RelativeException(
					"dateToString ERROR AL PARSEAR LA FECHA " + e.getMessage());
		}
		
	}
	
	/**
	 * transforma una fecha a texto 
	 * @param date
	 * @return 4 de julio del 2019
	 * @throws RelativeException
	 */
	public static String dateToFullString(Date date) throws RelativeException{
		try {
			String[] mes= {"enero", "febrero", "marzo", "abril", "mayo", "junio","julio","agosto","septiembre", "octubre", "noviembre", "diciembre"};
			String fechaSalida;
			fechaSalida = String.valueOf(date.getDate()).concat(" de ").concat(mes[date.getMonth()]).concat(" del ").concat(String.valueOf(date.getYear()+1900));
			return fechaSalida;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE TRANSFORMAR A TEXTO");
		}
	}
	
	public static Long diasFecha(Date fechaIni, Date fechaFin) {
		return ((fechaFin.getTime()-fechaIni.getTime())/86400000);
	}
	
	public static Long getEdad(Date fecha) {
		Date toDate = new Date();
		Long anios = (((toDate.getTime()-fecha.getTime())/86400000)/365);
		return (long) Math.floor(anios);
	}
	
	public static Date adicionEnDias(Date fecha, Long numeroDias) {
		 Calendar cal = Calendar.getInstance();
	        cal.setTime(fecha);
	        cal.add(Calendar.DATE, numeroDias.intValue()); //minus number would decrement the days
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
	
	public static String dateToStringFormat(Date date) throws RelativeException {
		try {
			String ano = String.valueOf(date.getYear()+1900);
			String mes = String.valueOf(date.getMonth()+1);
			String dia = String.valueOf(date.getDate());
			
			String fecha ="";
			fecha.concat(ano).concat("-").concat(mes).concat("-").concat(dia);
			//format YYYY-MM-DD
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
			//System.out.println("===> formatSringToDate impresion de fecha convergtida " +
			// new Date(dfIn.parse(date ).getTime()) + "fechaaa ======>>" + new Date(dfIn.parse(date ).getTime()));
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
	
	
	
	public static int calculateEdad(Date fechaNac) throws RelativeException{
		LocalDate fechaNacTemp= convertDateToLocalDate(fechaNac);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNacTemp, ahora);
		return periodo.getYears();
	}
	
}
