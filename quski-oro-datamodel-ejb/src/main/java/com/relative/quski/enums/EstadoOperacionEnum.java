package com.relative.quski.enums;

public enum EstadoOperacionEnum {
	VIGENTE,
	CANCELADO,
	NOVADO,
	PENDIENTE_APROBACION,
	DISPONIBLE,
	EN_EJECUCION,
	ARRIBO,
	SOLICITUD,
	CANCELACION,
	ENTREGAAPODERADO,
	ENTREGA,
	ENTREGAHEREDEROS,
	SOLICITUDHEREDEROS,
	SOLICITUDAPODERADO,
	PENDIENTE_ARRIBO,
	CUSTODIA_AGENCIA,
	RECHAZADO,
	ARRIBADO,
	CREADO,									// NUEVO = INICIO DE NEGOCIACION
	APROBADO,								// NUEVO = FINAL DE NEGOCIACION    - RENOVACION  
	PENDIENTE_APROBACION_DEVUELTO,			// NUEVO - RENOVACION	
	DEVUELTO,								// NUEVO - RENOVACION
	PENDIENTE_EXCEPCION,					// NUEVO - RENOVACION
	EXCEPCIONADO,							// NUEVO - RENOVACION
	PENDIENTE_FECHA,
	PENDIENTE_APROBACION_FIRMA,
	DETALLE,
	EDITAR,
	AUTORIZACION,
	REGULARIZACION_DOCUMENTOS

}
