package com.relative.quski.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoFunda;

@Local
public interface FundaRepository extends CrudRepository<Long, TbQoFunda> {
	
	
	public List<TbQoFunda> findPorCustomFilterFundas(PaginatedWrapper pw, String codigo,
			BigDecimal peso, EstadoEnum estado) throws RelativeException;
	
	public Long countByParams(String codigo,
			BigDecimal peso, EstadoEnum estado)throws RelativeException;
	
	public TbQoFunda reservarFunda(BigDecimal peso) throws RelativeException;
}
