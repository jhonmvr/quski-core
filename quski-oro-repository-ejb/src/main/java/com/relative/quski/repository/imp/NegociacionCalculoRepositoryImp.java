package com.relative.quski.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoNegociacionCalculo;
import com.relative.quski.repository.DetalleCreditoRepository;
import com.relative.quski.repository.NegociacionCalculoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "negociacionCalculoRepositoryImp")
public class NegociacionCalculoRepositoryImp extends GeneralRepositoryImp<Long, TbQoNegociacionCalculo> implements NegociacionCalculoRepository {

}
