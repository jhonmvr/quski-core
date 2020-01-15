package com.relative.quski.repository.imp;
import javax.ejb.Stateless;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoCalculoNegociacion;
import com.relative.quski.repository.CalculoNegociacionRepository;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "calculoNegociacionRepository")
public class CalculoNegociacionImp  extends GeneralRepositoryImp<Long, TbQoCalculoNegociacion> implements CalculoNegociacionRepository {

}
