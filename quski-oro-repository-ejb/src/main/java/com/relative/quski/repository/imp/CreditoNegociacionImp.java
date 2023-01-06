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
import com.relative.quski.repository.spec.CreditoByCodigoBpmSpec;
import com.relative.quski.repository.spec.CreditoByIdNegociacionSpec;
import com.relative.quski.repository.spec.CreditoByNumeroOperacionMadre;
import com.relative.quski.repository.spec.CreditoByNumeroOperacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.util.QuskiOroConstantes;

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

	@Override
	public TbQoCreditoNegociacion findCreditoByNumeroOperacionMadre(String numeroOperacion) throws RelativeException {
		try {
			List<TbQoCreditoNegociacion> list = this.findAllBySpecification( new CreditoByNumeroOperacionMadre( numeroOperacion ) );
			if(!list.isEmpty() ) {
				if(list.size() == 1) {
					return list.get(0);
				}
			}
			return null;
		}catch(Exception e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}

	@Override
	public TbQoCreditoNegociacion findCreditoByCodigoBpm(String codigoBpm) throws RelativeException {
		try {
			List<TbQoCreditoNegociacion> tmp = this.findAllBySpecification(new CreditoByCodigoBpmSpec(codigoBpm));
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

	@Override
	public TbQoCreditoNegociacion findCreditoByNumeroOperacion(String operacion) throws RelativeException {
		try {
			List<TbQoCreditoNegociacion> list = this.findAllBySpecification( new CreditoByNumeroOperacionSpec( operacion ) );
			if(list != null && !list.isEmpty() ) {
				return list.get(0);
			}
			return null;
		}catch(Exception e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
}
