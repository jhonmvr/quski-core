package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.repository.DireccionClienteRepository;


@Stateless(mappedName = "direccionClienteRepository")
public class DireccionClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoDireccionCliente> implements DireccionClienteRepository {
	
	

	
}
