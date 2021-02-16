package com.relative.quski.repository.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.VariablesCrediticiaRepository;
import com.relative.quski.repository.spec.VariablesCrediticiasByIdCotizacionSpec;
import com.relative.quski.repository.spec.VariablesCrediticiasByIdNegociacionSpec;
import com.relative.quski.wrapper.VariableCrediticiaWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "variablesCrediticiaRepository")
public class VariablesCrediticiaRepositoryImp extends GeneralRepositoryImp<Long, TbQoVariablesCrediticia>
		implements VariablesCrediticiaRepository {
	@Inject
	Logger log;

	@Override
	public List<TbQoVariablesCrediticia> findByIdCotizacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idCotizador) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new VariablesCrediticiasByIdCotizacionSpec(idCotizador), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}

	}

	@Override
	public List<TbQoVariablesCrediticia> findByIdCotizacion(Long idCotizador) throws RelativeException {
		try {
			return findAllBySpecification(new VariablesCrediticiasByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public Long countByIdCotizacion(Long idCotizador) throws RelativeException {

		try {
			return countBySpecification(new VariablesCrediticiasByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}

	}

	/**
	 * Realiza la búsqueda de la variable crediticia por Id de cotizador mediante
	 * una consulta directa a la base
	 * 
	 * @author KLÉBER GUERRA Relative - Engine
	 * @param idCotizador
	 * @return List<VariableCrediticiaWrapper>
	 * @throws RelativeException
	 */
	@Override
	public List<VariableCrediticiaWrapper> findByIdCotizadorCustom(Long idCotizador) throws RelativeException {
		try {
			StringBuilder queryStr = new StringBuilder(
					"SELECT  NEW com.relative.quski.wrapper.VariableCrediticiaWrapper(");
			queryStr.append(
					"vc.id as id, vc.tbQoCotizador.id as idCotizador, vc.orden as orden,vc.nombre as nombre,vc.valor as valor)");
			queryStr.append(" FROM TbQoVariablesCrediticia AS vc ");
			queryStr.append(" where vc.tbQoCotizador.id=:idCotizador ");
			log.info("===> query gfenerado " + queryStr.toString());
			TypedQuery<VariableCrediticiaWrapper> query = this.getEntityManager().createQuery(queryStr.toString(),
					VariableCrediticiaWrapper.class);
			query.setParameter("idCotizador", idCotizador);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"Error en la busqueda findByIdCotizador " + e.getMessage());
		}
	}

	@Override
	public List<TbQoVariablesCrediticia> findByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			List<TbQoVariablesCrediticia> list = findAllBySpecification(new VariablesCrediticiasByIdNegociacionSpec( idNegociacion ));
			if(list.isEmpty()) {
				return null;
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR VARIABLES CREDITICIAS POR ID DE NEGOCIACION");
		}
	}

	@Override
	public VariableCrediticiaWrapper findByIdNegociacionAndCodigo(Long idNegociacion, String codigo) throws RelativeException {


		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			CriteriaQuery<VariableCrediticiaWrapper> query = cb.createQuery(VariableCrediticiaWrapper.class);
			Root<TbQoVariablesCrediticia> poll = query.from(TbQoVariablesCrediticia.class);
			List<Predicate> where = new ArrayList<>();

			if (StringUtils.isNotBlank(codigo)) {
				where.add(cb.equal(poll.get("codigo"), codigo));
			}
			if (idNegociacion != null) {
				where.add(cb.equal(poll.get("tbQoNegociacion").get("id"), idNegociacion));
			}
			

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// 
			query.multiselect(poll.get("id"),poll.get("tbQoCotizador").get("id"), poll.get("orden"), poll.get("nombre"), poll.get("valor"));

			// ~~> EJECUTAR CONSULTA

			TypedQuery<VariableCrediticiaWrapper> createQuery = this.getEntityManager().createQuery(query);
			
			List<VariableCrediticiaWrapper> tmp = createQuery.getResultList();
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL BUSCAR VARIABLE CREDITICIA POR ID NEGOCIACION Y CODIGO");
		}
	
	}

	@Override
	public void deleteVariablesByNegociacionId(Long idNegociacion) throws RelativeException {

		try {
			if(idNegociacion == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR VARIABLES CREDITICIAS ID NEGOCIACION ES OBLIGATORIO");
			}
		
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_variables_crediticias where 1=1 ");
			
			queryStr.append("and id_negociacion =:idNegociacion ");
			Query query = this.getEntityManager().createNativeQuery(queryStr.toString());
			
			query.setParameter("idNegociacion", idNegociacion);
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

	@Override
	public void deleteVariablesByCotizacionId(Long idCotizador) throws RelativeException {
		try {
			if(idCotizador == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRAR VARIABLES CREDITICIAS ID COTIZADOR ES OBLIGATORIO");
			}
		
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_variables_crediticias where 1=1 ");
			
			queryStr.append("and id_cotizador =:idCotizador ");
			Query query = this.getEntityManager().createNativeQuery(queryStr.toString());
			
			query.setParameter("idCotizador", idCotizador);
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