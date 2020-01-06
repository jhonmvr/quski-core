package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoCliente;

@Local
public interface ClienteRepository extends CrudRepository<Long, TbQoCliente> {

}
