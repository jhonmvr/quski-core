package com.relative.quski.repository;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.TbQoCompromisoPago;
import com.relative.quski.wrapper.CompromisoParamsWrapper;
import com.relative.quski.wrapper.CompromisoReporteWrapper;
import java.util.List;

public interface CompromisoPagoRepository extends CrudRepository<Long, TbQoCompromisoPago> {
    List<TbQoCompromisoPago> findByNumeroOperacion(String numeroOperacion) throws RelativeException;
    List<TbQoCompromisoPago> findByNumeroOperacionEstado(String numeroOperacion, EstadoProcesoEnum estado) throws RelativeException;
	
    public List<CompromisoReporteWrapper> findCompromisoReporte(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, CompromisoParamsWrapper wp) throws RelativeException;
	public List<CompromisoReporteWrapper> findCompromisoReporte(CompromisoParamsWrapper wp) throws RelativeException;


	public Integer countCompromisoReporte(CompromisoParamsWrapper wp) throws RelativeException;
}
