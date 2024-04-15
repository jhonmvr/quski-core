package com.relative.quski.service;

import com.relative.quski.repository.ExcepcionOperativaRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class RegularizacionDocumentosService {
    @Inject
    Logger log;
    @Inject
    private RegularizacionDocumentosRepository regularizacionDocumentosRepository;
}
