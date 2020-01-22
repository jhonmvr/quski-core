package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.repository.PatrimonioRepository;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "patrimonioRepository")
public class PatrimonioRepositoryImp extends GeneralRepositoryImp<Long, TbQoPatrimonio> implements PatrimonioRepository{
}
