package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.spec.TasacionByIdCreditoNegociacionSpec;
import com.relative.quski.repository.spec.TasacionByIdNegociacionSpec;
import com.relative.quski.repository.spec.TasacionByIdSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "tasacionRepository")
public class TasacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoTasacion> implements TasacionRepository {

	@Override
	public TbQoTasacion findById(Long id) throws RelativeException {
		List<TbQoTasacion> tmp;
		try {
			tmp = this.findAllBySpecification( new TasacionByIdSpec( id ) );
			if (tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por id Credito Negociacion" + e.getMessage());
		}
		return null;
	}
	@Override
	public Long countFindByIdCredito(Long idCreditoNegociacion) throws RelativeException {
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
	@Override
	public List<TbQoTasacion> findByIdCredito(Long id) throws RelativeException {
		try {
			List<TbQoTasacion> list = this.findAllBySpecification( new TasacionByIdCreditoNegociacionSpec( id ) );
			if( !list.isEmpty() ) {
				return list;
			} else {
				return null;				
			}
		}catch (Exception e) {
			throw new RelativeException(": Al buscar tasacion por id de negociacion imp " + e.getMessage());
		}
	}
	@Override
	public List<TbQoTasacion> findByIdCredito(Long id, int page, int pageSize,
			String order, String direction) throws RelativeException {
		List<TbQoTasacion> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new TasacionByIdCreditoNegociacionSpec(id), page,
					pageSize, order, direction);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por id Credito Negociacion" + e.getMessage());
		}
		return null;
	}
	@Override
	public List<TbQoTasacion> findByIdNegociacion( Long idNegociacion ) throws RelativeException {
		try {
			List<TbQoTasacion> list  =  this.findAllBySpecification( new TasacionByIdNegociacionSpec( idNegociacion ) );
			if(list.isEmpty() ) {
				return null;
			}		
			return list;
		} catch (Exception e) {
			throw new RelativeException(": Al buscar contrato por id de Negociacion imp " + e.getMessage());
		}
	}
	@Override
	public Long countFindByIdNegociacion(Long idNegociacion) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification( new TasacionByIdNegociacionSpec( idNegociacion ) );
			if (tmp != null) {
				return tmp;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(": Al contar los registro de tasacion por id de negociacion " + e.getMessage());
		}
		
	}
	@Override
	public void deleteTasacionByNegociacionId(Long idCredito) throws RelativeException {

		try {
			if(idCredito == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRARTASACION ID CREDITO ES OBLIGATORIO");
			}
		
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_tasacion where 1=1 ");
			
			queryStr.append("and id_credito_negociacion =:idCredito ");
			Query query = this.getEntityManager().createNativeQuery(queryStr.toString());
			
			query.setParameter("idCredito", idCredito);
			query.executeUpdate();
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR LAS VARIABLES CREDITICIAS");
		}
		
	
	}
	
	
	
}
