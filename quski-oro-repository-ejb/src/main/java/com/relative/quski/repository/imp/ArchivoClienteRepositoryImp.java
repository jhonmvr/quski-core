package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoArchivoCliente;
import com.relative.quski.repository.ArchivoClienteRepository;


@Stateless(mappedName = "archivoClienteRepository")
public class ArchivoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoArchivoCliente> implements ArchivoClienteRepository {
	
	

	
}
