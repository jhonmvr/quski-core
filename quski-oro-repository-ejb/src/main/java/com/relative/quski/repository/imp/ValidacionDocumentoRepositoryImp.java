package com.relative.quski.repository.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoValidacionDocumento;
import com.relative.quski.repository.ValidacionDocumentoRepository;
@Stateless(mappedName = "ValidacionDocumentoRepository")
public class ValidacionDocumentoRepositoryImp extends GeneralRepositoryImp<Long, TbQoValidacionDocumento>
		implements ValidacionDocumentoRepository {

	@Override
	public void deleteAllByIdCredito(Long idCredito) throws RelativeException {
		try {
			if(idCredito == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"BORRARTASACION ID CREDITO ES OBLIGATORIO");
			}
		
			StringBuilder queryStr =  new StringBuilder();
			queryStr.append("DELETE FROM tb_qo_validacion_documento where 1=1 ");
			
			queryStr.append("and id_credito =:idCredito ");
			Query query = this.getEntityManager().createNativeQuery(queryStr.toString());
			
			query.setParameter("idCredito", idCredito);
			query.executeUpdate();
		} catch (RelativeException e) {
			
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BORRAR LAS VARIABLES DOCUMENTO");
		}
		
	}

	@Override
	public List<TbQoValidacionDocumento> findAllByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			CriteriaQuery<TbQoValidacionDocumento> query = cb.createQuery(TbQoValidacionDocumento.class);
			// ~~> FROM
			Root<TbQoValidacionDocumento> poll = query.from(TbQoValidacionDocumento.class);

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();

			
			where.add(cb.equal(poll.get("tbQoCreditoNegociacion").get("tbQoNegociacion").get("id"), idNegociacion));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT Long id, String usuario, Timestamp fecha, String observacion, Long idCredito
			//query.select(poll);
			query.multiselect(poll.get("coincidencia"), poll.get("confianza"), poll.get("item"), poll.get("valor"));

			// ~~> EJECUTAR CONSULTA

			TypedQuery<TbQoValidacionDocumento> createQuery = this.getEntityManager().createQuery(query);

			return createQuery.getResultList();

		}catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"findAllByIdNegociacion");
		}
	}

	
}
