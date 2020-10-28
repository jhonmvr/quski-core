package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoRubro;
import com.relative.quski.repository.RubroRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "rubroRepository")
public class RubroRepositoryImp extends GeneralRepositoryImp<Long, TbQoRubro> implements RubroRepository{
	
	
}
