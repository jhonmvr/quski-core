package com.relative.quski.repository.imp;
import javax.ejb.Stateless;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.repository.DetalleCreditoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "detalleCreditoRepository")
public class DetalleCreditoRepositoryImp extends GeneralRepositoryImp<Long, TbQoDetalleCredito> implements DetalleCreditoRepository{

}
