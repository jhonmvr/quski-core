package com.relative.quski.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.model.TbQoClientePago;



@Local
public interface ClientePagoRepository extends CrudRepository<Long, TbQoClientePago> {
	public List<TbQoClientePago>  findByParams(PaginatedWrapper pw, String cedula, String nombreCliente, 
			String codigoCuentaMupi, String codigoOperacion, String observacion, 
			String tipoCredito, BigDecimal valorDepositado, BigDecimal valorPrecancelado) throws RelativeException;
	
	
	public Long countByParams(String cedula, String nombreCliente, 
			String codigoCuentaMupi, String codigoOperacion, String observacion, 
			String tipoCredito, BigDecimal valorDepositado, BigDecimal valorPrecancelado) throws RelativeException;

	public TbQoClientePago finClientePago(String cedula)  throws RelativeException;
}
