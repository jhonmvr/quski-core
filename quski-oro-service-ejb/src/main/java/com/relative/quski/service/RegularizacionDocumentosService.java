package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.repository.ExcepcionOperativaRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Timestamp;
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
        if(!regu.getEstadoRegularizacion().equals(EstadoExcepcionEnum.PENDIENTE)){
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
        if(!regu.getEstadoRegularizacion().equals(EstadoExcepcionEnum.PENDIENTE)){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR REGULARIZQACION PENDIENTE");
        }
        regu.setEstadoRegularizacion(regularizacion.getEstadoRegularizacion());
        regu.setUsuarioAprobador(regularizacion.getUsuarioAprobador());
        regu.setFechaRespuesta(new Timestamp(System.currentTimeMillis()));
        return this.regularizacionDocumentosRepository.update(regu);
    }
}
