package com.relative.quski.enums;

public enum EstadoProcesoEnum {
	
	PENDIENTE_ARRIBO,
	CUSTODIA_AGENCIA,
	RECHAZADO,
	ARRIBADO,
	CREADO,									// NUEVO = INICIO DE NEGOCIACION
	APROBADO,								// NUEVO = FINAL DE NEGOCIACION    - RENOVACION  
	PENDIENTE_APROBACION,					// NUEVO - RENOVACION	
	PENDIENTE_APROBACION_DEVUELTO,			// NUEVO - RENOVACION	
	DEVUELTO,								// NUEVO - RENOVACION
	CANCELADO,								// NUEVO = FINAL DE NEGOCIACION    - RENOVACION  
	PENDIENTE_EXCEPCION,					// NUEVO - RENOVACION
	EXCEPCIONADO,							// NUEVO - RENOVACION
	PENDIENTE_FECHA,
	PENDIENTE_APROBACION_FIRMA,
	CADUCADO								// NUEVO - RENOVACION
}
