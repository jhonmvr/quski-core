package com.relative.quski.repository.imp;
import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.Provincia;
import com.relative.quski.repository.ProvinciaRepository;

/**
 * Session Bean implementation class RenovacionRepositoryImp
 */
@Stateless(mappedName = "provinciaRepository")
public class ProvinciaRepositoryImp extends GeneralRepositoryImp<Long, Provincia> implements ProvinciaRepository  {

    public ProvinciaRepositoryImp() {
    }
}

