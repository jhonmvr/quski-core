package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.repository.ReferenciaPersonalRepository;

@Stateless(mappedName = "referenciaPersonalRepository")
public class ReferenciaPersonalRepositoryImp extends GeneralRepositoryImp<Long, TbQoReferenciaPersonal> implements ReferenciaPersonalRepository {

}
