package com.relative.quski.repository;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoExcepcionOperativa;

import java.util.List;

public interface ExcepcionOperativaRepository extends CrudRepository<Long, TbQoExcepcionOperativa> {
    List<TbQoExcepcionOperativa> listAllByParams(PaginatedListWrapper<TbQoExcepcionOperativa> plw, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException;


    Integer countListAllByParams(String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException;

    TbQoExcepcionOperativa findByNegociacionAndTipo(Long idNegociacion, String tipoExcepcion, EstadoExcepcionEnum pendiente) throws RelativeException;
}
