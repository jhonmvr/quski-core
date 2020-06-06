package com.relative.quski.util;

import com.relative.core.exception.RelativeException;

public interface QuskiBaseReport {
	
	/**
	 * Metodo comun para generacion de reportes
	 * @param idHabilitante
	 * @param idTipoDocumento
	 * @param proceso
	 * @param estadoOperacion
	 * @param idReferencia
	 * @return
	 */
	public byte[] generateReporte( String idHabilitante, String idTipoDocumento, 
			String proceso, String estadoOperacion, String idReferencia, String formato) throws RelativeException; 

}
