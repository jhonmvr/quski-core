package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoVariableCrediticia;
import com.relative.quski.repository.VariableCrediticiaRepository;


@Stateless(mappedName = "variableCrediticiaRepository")
public class VariableCrediticiaRepositoryImp extends GeneralRepositoryImp<Long, TbQoVariableCrediticia>
		implements VariableCrediticiaRepository {


}
