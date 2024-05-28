package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.repository.ExcepcionOperativaRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;



@Stateless
public class ExcepcionOperativaService {
    private static final BigDecimal COBRANZAS_NIVEL1 = new BigDecimal(10);
    private static final BigDecimal COBRANZAS_NIVEL2 = new BigDecimal(30);

    @Inject
    Logger log;
    @Inject
    private ExcepcionOperativaRepository excepcionOperativaRepository;
    @Inject
    private QuskiOroService qos;
    @Inject
    private RegularizacionDocumentosRepository regularizacionDocumentosRepository;

    public PaginatedListWrapper<TbQoExcepcionOperativa> listAllByParams(Integer firstItem, Integer pageSize, String sortFields, String sortDirections, String isPaginated, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException {
        PaginatedListWrapper<TbQoExcepcionOperativa> plw = new PaginatedListWrapper<>(new PaginatedWrapper(firstItem, pageSize, sortFields, sortDirections, isPaginated));
        plw.setIsPaginated(isPaginated);
        List<TbQoExcepcionOperativa> actions = this.excepcionOperativaRepository.listAllByParams(plw, usuario, estado,codigo,codigoOperacion,idNegociacion);
        if (actions != null && !actions.isEmpty()) {
            plw.setTotalResults(this.excepcionOperativaRepository.countListAllByParams(usuario, estado,codigo,codigoOperacion,idNegociacion));
            plw.setList(actions);
        }
        return plw;
    }

    public TbQoExcepcionOperativa solicitarExcepcionServicios(TbQoExcepcionOperativa ex, ProcesoEnum pro) throws RelativeException {
        TbQoProceso proceso =  this.qos.findProcesoByIdReferencia(ex.getIdNegociacion().getId(),pro);
        if(proceso == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO PROCESO ACTIVO");
        }
        proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_EXCEPCION_OPERATIVA);
        this.qos.manageProceso(proceso);
        ex.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
        if(ex.getMontoInvolucrado().compareTo(COBRANZAS_NIVEL1)<=0){
            ex.setNivelAprobacion(2);
        } else if(ex.getMontoInvolucrado().compareTo(COBRANZAS_NIVEL2)<=0){
            ex.setNivelAprobacion(3);
        } else if(ex.getMontoInvolucrado().compareTo(COBRANZAS_NIVEL2)>0){
            ex.setNivelAprobacion(4);
        }
        return this.excepcionOperativaRepository.add(ex);
    }

    public TbQoExcepcionOperativa findByNegociacionAndTipo(Long idNegociacion, String tipoExcepcion, EstadoExcepcionEnum estado ) throws RelativeException {
        return excepcionOperativaRepository.findByNegociacionAndTipo(idNegociacion, tipoExcepcion, estado);
    }

    public TbQoExcepcionOperativa resolverExcepcion(TbQoExcepcionOperativa ex, ProcesoEnum proceso) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(ex.getId());
        if(!excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.PENDIENTE.toString())){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ESTA EXCEPCION YA FUE PROCESADA");
        }
        excepcion.setEstadoExcepcion(ex.getEstadoExcepcion());
        excepcion.setFechaRespuesta(new Timestamp(System.currentTimeMillis()));
        excepcion.setObservacionAprobador(ex.getObservacionAprobador());
        excepcion.setUsuarioAprobador(ex.getUsuarioAprobador());
        TbQoProceso proce =  this.qos.findProcesoByIdReferencia(excepcion.getIdNegociacion().getId(),proceso);
        if(!proce.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_EXCEPCION_OPERATIVA)){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ESTA EXCEPCION YA FUE PROCESADA");
        }
        if(ex.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO.toString())){

            if(excepcion.getNivelAprobacion() == 1){
                proce.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
                this.regularizacionDocumentosRepository.add(getTbQoRegularizacionDocumento(excepcion));
            }else{
                proce.setEstadoProceso(EstadoProcesoEnum.EXCEPCIONADO_OPERATIVA);
            }
        }else{

            if(excepcion.getNivelAprobacion() == 1){
                proce.setEstadoProceso(EstadoProcesoEnum.EXCEPCIONADO_OPERATIVA);
            }else{
                proce.setEstadoProceso(EstadoProcesoEnum.EXCEPCIONADO_OPERATIVA);
            }
        }

        this.qos.manageProceso(proce);
        return  this.excepcionOperativaRepository.update(excepcion);
    }

    private TbQoRegularizacionDocumento getTbQoRegularizacionDocumento(TbQoExcepcionOperativa excepcion) {
        TbQoRegularizacionDocumento documento = new TbQoRegularizacionDocumento();
        documento.setTipoExcepcion(excepcion.getTipoExcepcion());
        documento.setCodigoOperacion(excepcion.getCodigoOperacion());
        documento.setIdNegociacion(excepcion.getIdNegociacion());
        documento.setFechaSolicitud(excepcion.getFechaSolicitud());
        documento.setUsuarioSolicitante(excepcion.getUsuarioSolicitante());
        documento.setIdentificacionCliente(excepcion.getIdNegociacion().getTbQoCliente().getCedulaCliente());
        documento.setEstadoRegularizacion(EstadoExcepcionEnum.PENDIENTE.toString());
        return documento;
    }

    public DetalleCreditoEnProcesoWrapper traerCreditoNegociacionByExcepcionOperativa(Long idExcepcionOperativa) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(idExcepcionOperativa);
        if(excepcion == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO EXCEPCION OPERATIVA CON ID:"+ idExcepcionOperativa);
        }
        DetalleCreditoEnProcesoWrapper detalle = this.qos.traerCreditoNegociacion(excepcion.getIdNegociacion().getId());
        detalle.setExcepcionOperativa(excepcion);
        return detalle;
    }

    public TbQoExcepcionOperativa solicitarExcepcionFabrica(TbQoExcepcionOperativa ex, ProcesoEnum pro) throws RelativeException {
        TbQoProceso proceso =  this.qos.findProcesoByIdReferencia(ex.getIdNegociacion().getId(),pro);
        proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_EXCEPCION_OPERATIVA);
        this.qos.manageProceso(proceso);
        ex.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
        ex.setNivelAprobacion(1);

        return this.excepcionOperativaRepository.add(ex);
    }

    public AprobacionWrapper traerCreditoNegociacionExistenteByExcepcionOperativa(Long idExcepcionOperativa, String autorizacion) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(idExcepcionOperativa);
        if(excepcion == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO EXCEPCION OPERATIVA CON ID:"+ idExcepcionOperativa);
        }
        AprobacionWrapper detalle = this.qos.traerCreditoNegociacionExistente(excepcion.getIdNegociacion().getId(), autorizacion);
        detalle.setExcepcion(excepcion);
        return detalle;
    }
}
