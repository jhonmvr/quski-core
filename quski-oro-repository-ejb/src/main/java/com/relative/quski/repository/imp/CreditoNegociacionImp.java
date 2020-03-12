package com.relative.quski.repository.imp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.repository.spec.AsignacionByParamsSpec;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp  extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion> implements CreditoNegociacionRepository {

	@Override
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,
			EstadoEnum estado, String identificacion, int startRecord, Integer pageSize, String sortFields,
			String sortDirections) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,
			EstadoOperacionEnum estado, String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbQoCreditoNegociacion> findPorCustomFilterContratos(PaginatedWrapper pw, Date fechaDesde,
			Date fechaHasta, String codigoOperacion, EstadoOperacionEnum estado, String identificacion)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoOperacionEnum estado,
			String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	@Override
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,
			EstadoEnum estado, String identificacion, int startRecord, Integer pageSize, String sortFields,
			String sortDirections) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(new CreditoNegociacionByParamsSpec(fechaDesde, fechaHasta, codigoOperacion, estado, identificacion),
					startRecord, pageSize, sortFields, sortDirections);

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de credito por parametros");
		}
	}

	@Override
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,
			EstadoEnum estado, String identificacion) throws RelativeException {
		try {
			return this.findAllBySpecification(new CreditoNegociacionByParamsSpec(fechaDesde, fechaHasta, codigoOperacion, estado, identificacion));

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de credito por parametros");
		}
	}

	@Override
	public Long countByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoEnum estado,
			String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

*/
	public List<TbQoCreditoNegociacion> findAsignacionByParams(String codigoProceso, String nombreAgencia, String nombreProceso, int cedula) {
			return 	findAllBySpecification(
						new AsignacionByParamsSpec(codigoProceso, nombreAgencia, nombreProceso, cedula)
					);
	}

}
