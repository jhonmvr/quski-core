package com.relative.quski.repository.imp;


import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.repository.ClientePagoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "clientePagoRepository")
public class ClientePagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoClientePago> implements ClientePagoRepository {
	
}
