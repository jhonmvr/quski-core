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
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.spec.CreditoByIdNegociacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion>
		implements CreditoNegociacionRepository {

	@Override
	public Long countByParams(Date fechaDesde, Date fechaHasta, EstadoOperacionEnum estado,
			String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbQoCreditoNegociacion> findPorCustomFilterCreditos(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String codigoOperacion, ProcesoEnum proceso, String identificacion, Long agencia, String cliente,
			EstadoEnum estado)
			throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(
					new CreditoNegociacionByParamsSpec(fechaDesde, fechaHasta, identificacion, agencia, codigoOperacion, proceso, cliente, estado),
					pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al listar creditos " + e.getMessage());
		}
	}
	@Override
	public TbQoCreditoNegociacion findCreditoByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			List<TbQoCreditoNegociacion> tmp = this.findAllBySpecification(new CreditoByIdNegociacionSpec(idNegociacion));
			if(!tmp.isEmpty()) {
				if(tmp.size() == 1) {
					return tmp.get(0);
				} else {
					return null;
				}
			}else {
				return null;				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"NO SE PUEDO ENCONTRAR CREDITO POR ID_NEGOCIACION");
		}
		
	}





//	@Override
//	public List<TbQoCreditoNegociacion> findBycodigOpEstado(String codigOp, EstadoOperacionEnum estado, int startRecord,
//			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
//		List<TbQoCreditoNegociacion> tmp;
//		try {
//			tmp = this.findAllBySpecificationPaged(new ReasignacionByCodigoAndEstadoParamSpec(codigOp, estado),
//					startRecord, pageSize, sortFields, sortDirections);
//			if (tmp != null && !tmp.isEmpty()) {
//				return tmp;
//			}
//		} catch (Exception e) {
//			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
//			throw new RelativeException(Constantes.ERROR_CODE_READ,
//					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);
//
//		}
//		return null;
//
//	}
//
//	@Override
//	public List<TbQoCreditoNegociacion> findBycodigOpEstado(String codigOp, EstadoOperacionEnum estado)
//			throws RelativeException {
//		List<TbQoCreditoNegociacion> tmp;
//		try {
//			tmp = this.findAllBySpecification(new ReasignacionByCodigoAndEstadoParamSpec(codigOp, estado));
//			if (tmp != null && !tmp.isEmpty()) {
//				return tmp;
//			}
//		} catch (Exception e) {
//			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
//			throw new RelativeException(Constantes.ERROR_CODE_READ,
//					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);
//
//		}
//		return null;
//
//	}
//
//	@Override
//	public Long countfindBycodigOpEstado(String codigOp, EstadoOperacionEnum estado) throws RelativeException {
//		try {
//			return this.countBySpecification(new ReasignacionByCodigoAndEstadoParamSpec(codigOp, estado));
//
//		} catch (Exception e) {
//			// log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
//			throw new RelativeException(Constantes.ERROR_CODE_READ,
//					"ERROR: NO EXISTE INFORMACION TIPO JOYA PARA ID " + estado);
//
//		}
//
//	}

}
