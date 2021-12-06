package com.relative.quski.repository.imp;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoPublicidad;
import com.relative.quski.repository.PublicidadRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "publicidadRepository")
public class PublicidadRepositoryImp extends GeneralRepositoryImp<Long, TbQoPublicidad>
		implements PublicidadRepository {

	@Inject
	Logger log;

	

}
