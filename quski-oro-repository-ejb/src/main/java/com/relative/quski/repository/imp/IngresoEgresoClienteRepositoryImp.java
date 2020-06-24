package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.repository.IngresoEgresoClienteRepository;


@Stateless(mappedName = "ingresoEgresoClienteRepository")
public class IngresoEgresoClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoIngresoEgresoCliente> implements IngresoEgresoClienteRepository {
	
	

	
}
