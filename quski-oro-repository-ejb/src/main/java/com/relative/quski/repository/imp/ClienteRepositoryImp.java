package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.repository.ClienteRepository;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "clienteRepository")
public class ClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoCliente> implements ClienteRepository {

}
