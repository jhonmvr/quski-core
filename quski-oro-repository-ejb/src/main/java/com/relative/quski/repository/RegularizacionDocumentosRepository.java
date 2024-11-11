package com.relative.quski.repository;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.wrapper.RegularizacionClienteWrapper;

import java.util.List;

public interface RegularizacionDocumentosRepository extends CrudRepository<Long, TbQoRegularizacionDocumento> {
    List<TbQoRegularizacionDocumento> listAllByParams(PaginatedListWrapper<TbQoRegularizacionDocumento> plw, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException;
    List<RegularizacionClienteWrapper> listAllByParamsClient(String cedula) throws RelativeException;

    Integer countListAllByParams(String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException;
}
