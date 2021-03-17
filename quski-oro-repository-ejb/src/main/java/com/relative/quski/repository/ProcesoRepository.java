package com.relative.quski.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.OpPorAprobarWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;
import com.relative.quski.wrapper.ProcesoCaducadoWrapper;
import com.relative.quski.wrapper.ProcesoDevoActivoWrapper;

@Local
public interface ProcesoRepository extends CrudRepository<Long, TbQoProceso> {
	
	public TbQoProceso findById(Long id) throws RelativeException;
	TbQoProceso findByIdReferencia(Long id, ProcesoEnum proceso) throws RelativeException;
	
	public List<ProcesoDevoActivoWrapper> findDevolucionesActivas(String numeroOperacion) throws RelativeException;
	
	public List<TbQoProceso> findProcesosByAsesor(String asesor) throws RelativeException;
	
	public TbQoProceso findByIdCreditoNuevo(Long id)throws RelativeException;
	public TbQoProceso findByIdCreditoNovacion(Long id)throws RelativeException;

	public List<OperacionesWrapper> findOperacion( BusquedaOperacionesWrapper wp ) throws RelativeException;
	public Long countOperacion( BusquedaOperacionesWrapper wp ) throws RelativeException;
	
	public List<OpPorAprobarWrapper> findOperacionPorAprobar( BusquedaPorAprobarWrapper wp ) throws RelativeException;
	public Long countOperacionAprobar( BusquedaPorAprobarWrapper wp ) throws RelativeException;
	
	public Long caducarProcesos() throws RelativeException;
	
	public List<ProcesoCaducadoWrapper> findByTiempoBaseAprobadorProcesoEstadoProceso( Timestamp tiempoBase, List<String> aprobadores, List<ProcesoEnum> procesos, List<EstadoProcesoEnum> estados ) throws RelativeException;

}
