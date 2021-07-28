package com.relative.quski.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.ObjetoHabilitanteWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;

@Stateless
public class CreditoNuevoService {
	@Inject
	Logger log;
	@Inject
	private ParametroRepository parametroRepository;
	@Inject
	private CreditoNegociacionRepository creditoNegociacionRepository;

	public RespuestaObjectWrapper plantillaOperacionHabilitante(String codigoOperacion, String autorizacion)
			throws RelativeException {
		try {
			RespuestaObjectWrapper objeto = LocalStorageClient.findObjectById(
					parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
					parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
					parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
					SoftBankApiClient.generarHabilitanteCredito(codigoOperacion,autorizacion, parametroRepository
							.findByNombre(QuskiOroConstantes.SOFTBANK_GENERAR_HABILITANTE).getValor()).getUriHabilitantes(),autorizacion);

			return objeto;

		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public ObjetoHabilitanteWrapper generarHabilitanteCredito(Long idNegociacion, String autorizacion)
			throws RelativeException {
		try {
			ObjetoHabilitanteWrapper ohw = new ObjetoHabilitanteWrapper();
			TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
			RespuestaObjectWrapper objeto = plantillaOperacionHabilitante(credito.getNumeroOperacion(),autorizacion);
			if(objeto != null && objeto.getEntidad() != null) {				
				ohw.setObjectoStorage(objeto.getEntidad());
			}
			return ohw;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public byte[]  generarHabilitanteCreditozip(Long idNegociacion, String autorizacion)
			throws RelativeException {
		try {
			TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
			RespuestaObjectWrapper objeto = plantillaOperacionHabilitante(credito.getNumeroOperacion(),autorizacion);
			if(objeto != null && objeto.getEntidad() != null) {				
				return zip(objeto.getEntidad());
			}
			return null;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL COMPRIMIR ARCHIVO");
		}
	}
	
	private byte[] zip(String contenido) throws IOException {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ZipOutputStream zout = null;
	    try {
	        zout = new ZipOutputStream(bos);
            ZipEntry ze = new ZipEntry("file.pdf");
            zout.putNextEntry(ze);
            zout.write(contenido.getBytes());
            zout.closeEntry();
	    } finally {
	        if (zout != null) {
	            zout.close();
	        }
	    }
	    return bos.toByteArray();
	}

	

}
