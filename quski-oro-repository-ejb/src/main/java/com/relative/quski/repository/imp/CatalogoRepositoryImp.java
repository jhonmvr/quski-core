package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoCatalogo;
import com.relative.quski.repository.CatalogoRepository;
@Stateless(mappedName = "catalogoRepository")
public class CatalogoRepositoryImp extends GeneralRepositoryImp<Long, TbQoCatalogo> implements CatalogoRepository{

}
