package com.relative.quski.repository.imp;


import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.RegistrarPagoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "registraPagoRepository")
public class RegistrarPagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoRegistrarPago> implements RegistrarPagoRepository {
	
}
