package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.quski.model.TbQoComprobante;
import com.relative.quski.repository.ComprobanteRepository;
import com.relative.quski.repository.ParametroRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ComprobanteService {
    @Inject
    Logger log;
    @Inject
    private ComprobanteRepository comprobanteRepository;

    public List<TbQoComprobante> findAllByIdNegociacion(Long idNegociacion) throws RelativeException{
        return comprobanteRepository.listAllByIdNegociacion(idNegociacion);
    }
}
