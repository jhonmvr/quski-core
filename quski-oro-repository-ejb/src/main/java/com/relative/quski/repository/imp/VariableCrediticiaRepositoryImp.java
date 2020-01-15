package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoVariableCrediticia;
import com.relative.quski.repository.TipoOroRepository;
import com.relative.quski.repository.VariableCrediticiaRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "variableCrediticiaRepository")
public class VariableCrediticiaRepositoryImp extends GeneralRepositoryImp<Long, TbQoVariableCrediticia> implements VariableCrediticiaRepository
 {


}
