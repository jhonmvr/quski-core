package com.relative.quski.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.bpms.api.ReRestClient;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.NodoWrapper;
import com.relative.quski.wrapper.RegularizacionClienteWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class RegularizacionDocumentosService {
    @Inject
    Logger log;
    @Inject
    private RegularizacionDocumentosRepository regularizacionDocumentosRepository;
    @Inject
    private QuskiOroService qos;
    @Inject
    private DocumentoHabilitanteRepository documentoHabilitanteRepository;
    @Inject
    private ParametroRepository parametroRepository;


    public PaginatedListWrapper<TbQoRegularizacionDocumento> listAllByParams(Integer firstItem, Integer pageSize, String sortFields, String sortDirections, String isPaginated, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException {
        PaginatedListWrapper<TbQoRegularizacionDocumento> plw = new PaginatedListWrapper<>(new PaginatedWrapper(firstItem, pageSize, sortFields, sortDirections, isPaginated));
        plw.setIsPaginated(isPaginated);
        List<TbQoRegularizacionDocumento> actions = this.regularizacionDocumentosRepository.listAllByParams(plw, usuario, estado,codigo,codigoOperacion,idNegociacion);
        if (actions != null && !actions.isEmpty()) {
            plw.setTotalResults(this.regularizacionDocumentosRepository.countListAllByParams(usuario, estado,codigo,codigoOperacion,idNegociacion));
            plw.setList(actions);
        }
        return plw;
    }

    public DetalleCreditoEnProcesoWrapper traerCreditoNegociacionByRegularizacion(Long idRegularizacion) throws RelativeException {
        TbQoRegularizacionDocumento reg = this.regularizacionDocumentosRepository.findById(idRegularizacion);
        if(reg == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO EXCEPCION OPERATIVA CON ID:"+ idRegularizacion);
        }
        DetalleCreditoEnProcesoWrapper detalle = this.qos.traerCreditoNegociacion(reg.getIdNegociacion().getId());
        detalle.setTbQoRegularizacionDocumento(reg);
        return detalle;
    }

    public TbQoRegularizacionDocumento solicitarAprobacion(TbQoRegularizacionDocumento regularizacion) throws RelativeException {
        TbQoRegularizacionDocumento regu = this.regularizacionDocumentosRepository.findById(regularizacion.getId());
        if(regu == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION");
        }
        if(!regu.getEstadoRegularizacion().equals(EstadoExcepcionEnum.PENDIENTE.toString())){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION PENDIENTE");
        }
        //regu.setUsuarioAprobador(regularizacion.getUsuarioAprobador());
        regu.setEstadoRegularizacion("PENDIENTE_APROBACION");
        return this.regularizacionDocumentosRepository.update(regu);
    }

    public TbQoRegularizacionDocumento enviarRespuesta(TbQoRegularizacionDocumento regularizacion) throws RelativeException {
        TbQoRegularizacionDocumento regu = this.regularizacionDocumentosRepository.findById(regularizacion.getId());
        if(regu == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION");
        }
        if(!regu.getEstadoRegularizacion().equals(EstadoExcepcionEnum.PENDIENTE.toString())){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION PENDIENTE");
        }
        regu.setEstadoRegularizacion(regularizacion.getEstadoRegularizacion());
        regu.setUsuarioAprobador(regularizacion.getUsuarioAprobador());
        regu.setFechaRespuesta(new Timestamp(System.currentTimeMillis()));
        return this.regularizacionDocumentosRepository.update(regu);
    }
    public List<RegularizacionClienteWrapper> listAllByParamsClient( String usuario, String cedula) throws RelativeException {
        return this.regularizacionDocumentosRepository.listAllByParamsClient(cedula);
    }

    private void actualizarDocumentosHabilitantestemporales(Long idRegularizacion, String autorizacion) throws RelativeException{
        try{
            DetalleCreditoEnProcesoWrapper detalle = traerCreditoNegociacionByRegularizacion(idRegularizacion);
            //documentos Regularizacion
            List<TbQoDocumentoHabilitante> documentoCreditoR = this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Arrays.asList(ProcessEnum.NOVACION, ProcessEnum.NUEVO, ProcessEnum.FUNDA),
                    detalle.getCredito().getTbQoNegociacion().getId().toString(),
                    Collections.singletonList(EstadoOperacionEnum.REGULARIZACION_DOCUMENTOS)
            );

            List<TbQoDocumentoHabilitante> documentoClienteR =  this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Collections.singletonList(ProcessEnum.CLIENTE),
                    detalle.getCredito().getTbQoNegociacion().getId().toString(),
                    Collections.singletonList(EstadoOperacionEnum.REGULARIZACION_DOCUMENTOS)
            );

            List<TbQoDocumentoHabilitante> documentoAutorizacionR =  this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Collections.singletonList(ProcessEnum.AUTORIZACION),
                    detalle.getCredito().getNumeroOperacion(),
                    Collections.singletonList(EstadoOperacionEnum.REGULARIZACION_DOCUMENTOS)
            );

            //documentosCredito
            List<TbQoDocumentoHabilitante> documentoCredito = this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Arrays.asList(ProcessEnum.NOVACION, ProcessEnum.NUEVO, ProcessEnum.FUNDA),
                    detalle.getCredito().getTbQoNegociacion().getId().toString(),null
            );

            List<TbQoDocumentoHabilitante> documentoCliente =  this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Collections.singletonList(ProcessEnum.CLIENTE),
                    detalle.getCredito().getTbQoNegociacion().getId().toString(),null
            );

            List<TbQoDocumentoHabilitante> documentoAutorizacion =  this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Collections.singletonList(ProcessEnum.AUTORIZACION),
                    detalle.getCredito().getNumeroOperacion(),null
            );
            // Comparación y actualización de documentos de Credito
            comparacionDocumento(autorizacion, documentoCreditoR, documentoCredito);
            // Comparación y actualización de documentos de Cliente
            comparacionDocumento(autorizacion, documentoClienteR, documentoCliente);

            // Comparación y actualización de documentos de Autorización
            comparacionDocumento(autorizacion, documentoAutorizacionR, documentoAutorizacion);

        }catch (RelativeException ex){
            throw ex;
        }catch (Exception e){
            e.printStackTrace();
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,e.getMessage());
        }

    }

    private void comparacionDocumento(String autorizacion, List<TbQoDocumentoHabilitante> documentoR, List<TbQoDocumentoHabilitante> documentos) throws RelativeException {
        if (documentos != null && !documentos.isEmpty() && documentoR != null && !documentoR.isEmpty()) {
            for (TbQoDocumentoHabilitante documento : documentos) {
                for (TbQoDocumentoHabilitante documentR : documentoR) {
                    if (documento.getProceso().equals(documentR.getProceso()) && documento.getIdReferencia().equals(documentR.getIdReferencia())) {
                        actualizarObject(documento, documentR, autorizacion);
                    }
                }
            }
        }
    }

    private void actualizarObject(TbQoDocumentoHabilitante habilitanteCredito, TbQoDocumentoHabilitante habilitanteRegularizacion,String autorizacion) throws  RelativeException{

        RespuestaObjectWrapper repuesta = LocalStorageClient.findObjectById(
                parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
                parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
                parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
                habilitanteCredito.getObjectId(), autorizacion);
        Gson gsons = new GsonBuilder().create();
        NodoWrapper wrapper = gsons.fromJson((String) repuesta.getEntidad(), NodoWrapper.class);
        wrapper.setReferenceObjectId(habilitanteCredito.getObjectId());

        LocalStorageClient.updateObjectBigZ(
                parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
                parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
                parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
                habilitanteCredito.getObjectId(), wrapper, autorizacion);
    }

}
