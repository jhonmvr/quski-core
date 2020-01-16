package com.relative.quski.repository.imp;
import javax.ejb.Stateless;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.repository.CreditoNegociacionRepository;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp  extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion> implements CreditoNegociacionRepository {

}
