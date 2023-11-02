package com.relative.quski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.bpms.api.ApiGatewayClient;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.ResponseValidarDocumentoWrapper;
import com.relative.quski.model.ResponseValidarDocumentoWrapper.ValidacionDocumento;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoValidacionDocumento;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.ValidacionDocumentoRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.ValidarDocumentoWrapper;
@Stateless
public class ValidacionCreditoService {
	@Inject
	Logger log;
	@Inject
	private ValidacionDocumentoRepository validacionDocumentoRepository;
	@Inject
	private ParametroRepository parametroRepository;
	@Inject
	private CreditoNegociacionRepository creditoNegociacionRepository;
	@Inject
	private NegociacionRepository negociacionRepository;
	@Inject
	private TasacionRepository tasacionRepository;
	@Inject
	private QuskiOroService qos;
	@Inject
	private DocumentoHabilitanteRepository documentoHabilitanteRepository;
	

	public ResponseValidarDocumentoWrapper validarDocumento(Long idNegociacion, String autorizacion) throws RelativeException {
		
		try {	
			
			TbQoCreditoNegociacion credito = creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
			if(credito == null ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA CREDITO ID: "+idNegociacion);	
			}
			String url = this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_VALIDACION_DOCUMENTO).getValor();	
			TbQoNegociacion negociacion = negociacionRepository.findById(idNegociacion);
			TbQoDocumentoHabilitante doc = null;
			if(StringUtils.isNotBlank(credito.getNumeroOperacionAnterior())) {
				url = url.concat("validardocnovacion");
				doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("10"),
						ProcessEnum.NOVACION, String.valueOf(credito.getTbQoNegociacion().getId()));
			}
			else {
				url = url.concat("validardocnuevos");
				doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("16"),
						ProcessEnum.NUEVO, String.valueOf(negociacion.getId()));
			}
			
			if(doc == null || StringUtils.isBlank(doc.getObjectId())) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS FIRMADOS");
			}
			
			List<TbQoTasacion> tasacion = this.tasacionRepository.findByIdCredito(credito.getId());
			ValidarDocumentoWrapper wp = new ValidarDocumentoWrapper();
			wp.setIdentificacion(negociacion.getTbQoCliente().getCedulaCliente());
			wp.setMonto(credito.getMontoFinanciado().doubleValue());
			wp.setNip(credito.getCodigo());
			wp.setNombre(negociacion.getTbQoCliente().getNombreCompleto());
			wp.setNumero_funda(credito.getNumeroFunda());
			wp.setNumero_operacion(credito.getNumeroOperacion());
			wp.setPeriodo_plazo(credito.getPeriodoPlazo());
			wp.setPlazo(credito.getPlazoCredito());
			wp.setValor_recibir(credito.getValorARecibir().doubleValue());
			wp.setId_documento(doc.getObjectId());
			if(tasacion == null || tasacion.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA TASACION");	
			}
			wp.setTotal_numero_piezas(tasacion.stream().mapToLong(TbQoTasacion::getNumeroPiezas).sum());
			wp.setTotal_peso_bruto(tasacion.stream().mapToDouble(p->p.getPesoBruto().doubleValue()).sum());
			wp.setTotal_peso_neto(tasacion.stream().mapToDouble(p->p.getPesoNeto().doubleValue()).sum());
			wp.setTotal_valor_avaluo(tasacion.stream().mapToDouble(p->p.getValorAvaluo().doubleValue()).sum());
			wp.setTotal_valor_realizacion(tasacion.stream().mapToDouble(p->p.getValorRealizacion().doubleValue()).sum());
		
				
			ResponseValidarDocumentoWrapper response = ApiGatewayClient.validarDocumentoBot(wp, autorizacion, url);

			
			if(response == null ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO HAY RESPUESTA DEL BOT VALIDACION DE DOCUMENTO");
			}
			credito.setMensajeBotDocumento(response.getMensaje());
			credito.setAciertosBotDocumento(response.getAciertos());
			List<TbQoValidacionDocumento> datosValidacion = new ArrayList<>();
			for( ValidacionDocumento dato:response.getDatos()) {
				TbQoValidacionDocumento a = new TbQoValidacionDocumento();
				a.setCoincidencia(dato.getCoincidencia());
				a.setConfianza(dato.getConfianza());
				a.setItem(dato.getItem());
				a.setTbQoCreditoNegociacion(credito);
				a.setValor(dato.getValor());
				datosValidacion.add(a);
			}
			this.validacionDocumentoRepository.deleteAllByIdCredito(credito.getId());
			this.validacionDocumentoRepository.add(datosValidacion);
			this.qos.manageCreditoNegociacion(credito);

			return response;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,e.getMessage());
		}
	}


	public List<TbQoValidacionDocumento> listValidacionDocumento(Long idNegociacion) throws RelativeException {
		return validacionDocumentoRepository.findAllByIdNegociacion(idNegociacion);
	}
	
}
