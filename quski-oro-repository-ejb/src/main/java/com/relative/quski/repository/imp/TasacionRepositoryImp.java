package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.spec.TasacionByIdCreditoNegociacionSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "tasacionRepository")
public class TasacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoTasacion> implements TasacionRepository {

	@Override
	public List<TbQoTasacion> findByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException {
		List<TbQoTasacion> tmp;
		try {
			tmp = this.findAllBySpecification(new TasacionByIdCreditoNegociacionSpec(idCreditoNegociacion));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		}catch (Exception e) {
			throw new RelativeException("Error al buscar tasacion por id del credito negociacion " + e);
		}
		return null;
	}

	@Override
	public List<TbQoTasacion> findByIdCreditoNegociacionPaged(Long idCreditoNegociacion, int page, int pageSize,
			String order, String direction) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countFindByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new TasacionByIdCreditoNegociacionSpec(idCreditoNegociacion));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar por id Credito negociacion " + e);
		}
		return null;
	}
	
	
	
}
