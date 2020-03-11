/**
 * 
 */
package com.relative.quski.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoAgencia;
@Local
public interface AgenciaRepository extends CrudRepository<Long, TbQoAgencia> {

}
