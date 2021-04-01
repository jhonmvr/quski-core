package com.relative.quski.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.ProcesoEnum;

public class ReGenericFactory {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> generateDynamicReportParameter( String objectFullName,
			String methodName,String idHabilitante, String idTipoDocumento, 
			ProcesoEnum proceso, EstadoOperacionEnum estadoOperacion, String idReferencia) throws RelativeException{
		
        try {
        	Class<?> repClass = Class.forName(objectFullName); 
			@SuppressWarnings("deprecation")
			Object repAux = repClass.newInstance();
			Method setNameMethod = repAux.getClass().getMethod(methodName, String.class);
			return (Map<String, Object>)setNameMethod.invoke(repAux, idHabilitante,idTipoDocumento, proceso, estadoOperacion, idReferencia );
		} catch (InstantiationException e) {
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR InstantiationException generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR IllegalAccessException generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR NoSuchMethodException generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR SecurityException generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR  IllegalArgumentException generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR InvocationTargetException generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM,"ERROR Exception generateDynamicReportParameter " + 
					objectFullName + "  " + methodName + "  " + idHabilitante + " " + idTipoDocumento +  " " 
					+ proceso + " " + estadoOperacion + " " + idReferencia);
		}
		
	}

}
