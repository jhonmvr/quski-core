package com.relative.quski.repository.imp;


import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.repository.ClientePagoRepository;
import com.relative.quski.repository.spec.ClientePagoByIdAndEstadoSpec;

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


	@Override
	public TbQoClientePago findByIdAndEstado(Long id, EstadoEnum estado, String tipo)
			throws RelativeException {
		try {
			List<TbQoClientePago> tmp;
			tmp= this.findAllBySpecification(new ClientePagoByIdAndEstadoSpec(id,estado, tipo));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL INTENTAR BUSCAR EN TB_QO_CLIENTE_PAGO POR ID Y ESTADO");
		}
	}
}