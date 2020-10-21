package com.relative.quski.util;

import com.relative.quski.enums.ProcesoEnum;

/**
 * 
 * @author LUIS TAMAYO - RELATIVE ENGINE
 *
 */
public class QueryConstantes {
	private QueryConstantes() {}
	public static final String WHEN_NEGO = " (proceso.proceso ='"+ ProcesoEnum.CREDITO_NUEVO.toString() +"' or proceso.proceso ='" + ProcesoEnum.RENOVACION.toString() + "' ) ";
	public static final String WHEN_DEVO = " (proceso.proceso ='"+ ProcesoEnum.DEVOLUCION.toString() +"') ";
	public static final String WHEN_PAGO = " (proceso.proceso ='"+ ProcesoEnum.PAGO.toString()+"') ";
	public static final String WHEN_VERI = " (proceso.proceso ='"+ ProcesoEnum.VERIFICACION_TELEFONICA.toString()+"') ";
}
