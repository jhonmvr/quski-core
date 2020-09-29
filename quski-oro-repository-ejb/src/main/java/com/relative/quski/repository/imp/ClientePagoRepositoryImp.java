package com.relative.quski.repository.imp;


import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.repository.ClientePagoRepository;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "clientePagoRepository")
public class ClientePagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoClientePago> implements ClientePagoRepository {
	
	@Override
	public TbQoClientePago finClientePago(final String cedula) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<TbQoClientePago> findByParams(PaginatedWrapper pw, String cedula, String nombreCliente,
			String codigoCuentaMupi, String codigoOperacion, String observacion, String tipoCredito,
			BigDecimal valorDepositado, BigDecimal valorPrecancelado, String estado, String tipo)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countByParams(String cedula, String nombreCliente, String codigoCuentaMupi, String codigoOperacion,
			String observacion, String tipoCredito, BigDecimal valorDepositado, BigDecimal valorPrecancelado,
			String estado, String tipo) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
}