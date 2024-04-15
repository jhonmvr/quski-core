package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.repository.ExcepcionOperativaRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ExcepcionOperativaService {
    @Inject
    Logger log;
    @Inject
    private ExcepcionOperativaRepository excepcionOperativaRepository;

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
}
