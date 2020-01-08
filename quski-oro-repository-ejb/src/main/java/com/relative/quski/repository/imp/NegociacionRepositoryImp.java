package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.NegociacionRepository;


@Stateless(mappedName = "negociacionRepository")
public class NegociacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoNegociacion>
		implements NegociacionRepository {

	

}
