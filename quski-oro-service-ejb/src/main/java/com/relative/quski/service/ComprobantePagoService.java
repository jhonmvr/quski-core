package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.quski.model.TbQoComprobantePago;
import com.relative.quski.repository.ComprobantePagoRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ComprobantePagoService {
    @Inject
    Logger log;
    @Inject
    private ComprobantePagoRepository comprobantePagoRepository;

    public List<TbQoComprobantePago> findAllByIdNegociacion(Long idNegociacion) throws RelativeException{
        return comprobantePagoRepository.listAllByIdNegociacion(idNegociacion);
    }

    public TbQoComprobantePago agregarComprobantePago(TbQoComprobantePago ComprobantePago) throws RelativeException {

        return comprobantePagoRepository.add(ComprobantePago);
    }

    public void eliminarComprobantePago(Long id) throws RelativeException {
        TbQoComprobantePago ComprobantePago  = comprobantePagoRepository.findById(id);
        comprobantePagoRepository.remove(ComprobantePago);
    }
}
