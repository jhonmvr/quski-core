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
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.NodoWrapper;
import com.relative.quski.wrapper.RegularizacionClienteWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;
import com.relative.quski.wrapper.mongo.DocumentoMongo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    public TbQoRegularizacionDocumento enviarRespuesta(TbQoRegularizacionDocumento regularizacion, String autorizacion) throws RelativeException {
        TbQoRegularizacionDocumento regu = this.regularizacionDocumentosRepository.findById(regularizacion.getId());
        if(regu == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION");
        }
        if(!regu.getEstadoRegularizacion().equals(EstadoOperacionEnum.PENDIENTE_APROBACION.toString())){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION PENDIENTE");
        }
        regu.setEstadoRegularizacion(regularizacion.getEstadoRegularizacion());
        regu.setUsuarioAprobador(regularizacion.getUsuarioAprobador());
        regu.setFechaRespuesta(new Timestamp(System.currentTimeMillis()));
        if(regularizacion.getEstadoRegularizacion().equals(EstadoOperacionEnum.APROBADO.toString())){
            actualizarDocumentosHabilitantestemporales(regu.getId(),autorizacion);
        }
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
                    detalle.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(),
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
            if(documentoCredito != null){
                documentoCredito = documentoCredito.stream()
                        .filter(doc -> doc.getTbQoTipoDocumento().getEstadoOperacion() == null)
                        .collect(Collectors.toList());
            }


            List<TbQoDocumentoHabilitante> documentoCliente =  this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Collections.singletonList(ProcessEnum.CLIENTE),
                    detalle.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(),null
            );
            if(documentoCliente != null){
                documentoCliente = documentoCliente.stream()
                        .filter(doc -> doc.getTbQoTipoDocumento().getEstadoOperacion() == null)
                        .collect(Collectors.toList());
            }


            List<TbQoDocumentoHabilitante> documentoAutorizacion =  this.documentoHabilitanteRepository.findByProcesoAndReferenciaAndEstadoProceso(
                    Collections.singletonList(ProcessEnum.AUTORIZACION),
                    detalle.getCredito().getNumeroOperacion(),null
            );
            if(documentoAutorizacion != null){
                documentoAutorizacion = documentoAutorizacion.stream()
                        .filter(doc -> doc.getTbQoTipoDocumento().getEstadoOperacion() == null)
                        .collect(Collectors.toList());
            }

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
    private void crearDocumento(TbQoDocumentoHabilitante documentR, String autorizacion) throws RelativeException{
        try {
            RespuestaObjectWrapper repuesta = LocalStorageClient.findObjectById(
                    parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
                    parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
                    parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
                    documentR.getObjectId(),
                    autorizacion);

            Gson gsons = new GsonBuilder().create();
            DocumentoMongo wrapper = gsons.fromJson((String) QuskiOroUtil.decodeBase64(repuesta.getEntidad()), DocumentoMongo.class);
            wrapper.setObjectId(documentR.getObjectId());

            RespuestaObjectWrapper repuestaC = LocalStorageClient.createObjectBigZ(
                    parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
                    wrapper,
                    parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
                    parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
                    documentR.getObjectId(),
                    autorizacion);
            documentR.setObjectId(repuestaC.getEntidad());
            qos.manageDocumentoHabilitante(documentR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, e.getMessage());
        }
    }
    private TbQoDocumentoHabilitante buscarDocumentoPorIdMapeado(List<TbQoDocumentoHabilitante> documentos, Long idMapeado) {
        if (documentos == null || documentos.isEmpty()) {
            return null; // Retornar null si la lista es vacía o nula
        }
        // Usar stream para buscar el documento con el ID mapeado
        return documentos.stream()
                .filter(doc -> doc.getTbQoTipoDocumento() != null && idMapeado.equals(doc.getTbQoTipoDocumento().getId()))
                .findFirst()
                .orElse(null); // Retorna el primer documento encontrado o null si no existe
    }
    private void comparacionDocumento(
            String autorizacion,
            List<TbQoDocumentoHabilitante> documentoR,
            List<TbQoDocumentoHabilitante> documentos
    ) throws RelativeException {

        // Definir las relaciones de equivalencia entre los IDs de tipo de documento
        Map<Long, Long> idTipoDocumentoMap = new HashMap<>();
        idTipoDocumentoMap.put(2L, 31L);
        idTipoDocumentoMap.put(3L, 32L);
        idTipoDocumentoMap.put(4L, 33L);
        idTipoDocumentoMap.put(6L, 34L);
        idTipoDocumentoMap.put(7L, 35L);
        idTipoDocumentoMap.put(8L, 36L);
        idTipoDocumentoMap.put(9L, 37L);
        idTipoDocumentoMap.put(10L, 38L);
        idTipoDocumentoMap.put(11L, 39L);
        idTipoDocumentoMap.put(16L, 40L);
        idTipoDocumentoMap.put(17L, 41L);
        idTipoDocumentoMap.put(18L, 42L);
        idTipoDocumentoMap.put(19L, 43L);
        idTipoDocumentoMap.put(1L, 44L);
        idTipoDocumentoMap.put(20L, 45L);
        // Generar valores inversos
        Map<Long, Long> valoresInversos = new HashMap<>();
        idTipoDocumentoMap.forEach((key, value) -> {
            valoresInversos.put(value, key); // Invertir las claves y valores
        });

        // Combinar los mapas
        idTipoDocumentoMap.putAll(valoresInversos);
        if (documentos != null && !documentos.isEmpty() && documentoR != null && !documentoR.isEmpty()) {
            for (TbQoDocumentoHabilitante documentR : documentoR) {
                for (TbQoDocumentoHabilitante documento : documentos) {
                    Long idDocumento = documento.getTbQoTipoDocumento().getId();
                    Long idReferencia = documentR.getTbQoTipoDocumento().getId();
                    Long idDocumentoMapeado = idTipoDocumentoMap.getOrDefault(idDocumento, idDocumento);
                    if (documento.getProceso().equals(documentR.getProceso()) &&
                            documento.getIdReferencia().equals(documentR.getIdReferencia()) &&
                            idDocumentoMapeado.equals(idReferencia)) {
                        actualizarObject(documento, documentR, autorizacion);
                    }
                }
            }
        }

        if (documentoR != null && !documentoR.isEmpty()) {
            for (TbQoDocumentoHabilitante documentR : documentoR) {
                Long idDocumentoActual = documentR.getTbQoTipoDocumento().getId();
                Long idDocumentoMapeado = idTipoDocumentoMap.getOrDefault(idDocumentoActual, idDocumentoActual);
                if(buscarDocumentoPorIdMapeado(documentos, idDocumentoMapeado) == null){
                    // Actualizar el ID del tipo de documento en el documento habilitante

                    TbQoDocumentoHabilitante duplicado = new TbQoDocumentoHabilitante();
                    TbQoTipoDocumento tipodocumento = new TbQoTipoDocumento();
                    tipodocumento.setId(idDocumentoMapeado);
                    duplicado.setTbQoTipoDocumento(tipodocumento);
                    duplicado.setArchivo(documentR.getArchivo());
                    duplicado.setNombreArchivo(documentR.getNombreArchivo());
                    duplicado.setEstado(documentR.getEstado());
                    duplicado.setProceso(documentR.getProceso());
                    duplicado.setIdReferencia(documentR.getIdReferencia());
                    duplicado.setObjectId(documentR.getObjectId());
                    duplicado.setEstadoOperacion(documentR.getEstadoOperacion());
                    duplicado.setFechaActualizacion(new Date());
                    duplicado.setFechaCreacion(new Date());
                    crearDocumento(duplicado, autorizacion);
                }

            }
        }
    }


    private void actualizarObject(TbQoDocumentoHabilitante habilitanteCredito, TbQoDocumentoHabilitante habilitanteRegularizacion,String autorizacion) throws  RelativeException{
        try{
            RespuestaObjectWrapper repuesta = LocalStorageClient.findObjectById(
                    parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
                    parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
                    parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
                    habilitanteRegularizacion.getObjectId(), autorizacion);

            Gson gsons = new GsonBuilder().create();
            DocumentoMongo wrapper = gsons.fromJson((String) QuskiOroUtil.decodeBase64(repuesta.getEntidad()), DocumentoMongo.class);
            wrapper.setObjectId(habilitanteCredito.getObjectId());

            LocalStorageClient.updateObjectBigZ(
                    parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor(),
                    wrapper,
                    parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor(),
                    parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor(),
                    habilitanteCredito.getObjectId(),
                    autorizacion);
        }catch (RelativeException e){
            throw e;
        }catch (Exception e){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,e.getMessage());
        }

    }

}
