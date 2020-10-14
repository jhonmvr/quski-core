package com.relative.quski.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoClientePago;



@Local
public interface ClientePagoRepository extends CrudRepository<Long, TbQoClientePago> {
	
	
	public List<TbQoClientePago>  findByParams(PaginatedWrapper pw, String cedula, String nombreCliente, 
			String codigoCuentaMupi, String codigoOperacion, String observacion, 
			String tipoCredito, BigDecimal valorDepositado, BigDecimal valorPrecancelado, String estado, String tipo) throws RelativeException;
	
	
	public Long countByParams(String cedula, String nombreCliente, 
			String codigoCuentaMupi, String codigoOperacion, String observacion, 
			String tipoCredito, BigDecimal valorDepositado, BigDecimal valorPrecancelado,String estado, String tipo) throws RelativeException;




	TbQoClientePago finClientePago(String cedula) throws RelativeException;


	/**
	 * Metodo que busca en clientePago por id y estado
	 * @param idClientePago
	 * @param pendienteAprobacion
	 * @return TbQoClientePago
	 * @throws RelativeException
	 */
	public TbQoClientePago findByIdAndEstado(Long id, EstadoEnum estado, String tipo) throws RelativeException;
}
