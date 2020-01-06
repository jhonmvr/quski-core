package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.repository.CotizadorRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "cotizadorRepository")
public class CotizadorRepositoryImp extends GeneralRepositoryImp<Long, TbQoCotizador> implements CotizadorRepository {

}
