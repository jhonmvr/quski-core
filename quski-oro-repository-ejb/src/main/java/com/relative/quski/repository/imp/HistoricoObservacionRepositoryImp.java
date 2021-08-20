package com.relative.quski.repository.imp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoHistoricoObservacion;
import com.relative.quski.repository.HistoricoObservacionRepository;
import com.relative.quski.wrapper.HistoricoObservacionWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "rolTipoDocumentoRepository")
public class HistoricoObservacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoHistoricoObservacion> implements HistoricoObservacionRepository {

	@Override
	public List<HistoricoObservacionWrapper> findByIdCredito(Long idCredito) throws RelativeException {

		
		

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<HistoricoObservacionWrapper> query = cb.createQuery(HistoricoObservacionWrapper.class);
		// ~~> FROM
		Root<TbQoHistoricoObservacion> poll = query.from(TbQoHistoricoObservacion.class);

		// ~~> WHERE
		List<Predicate> where = new ArrayList<>();

		
		where.add(cb.equal(poll.get("tbQoCreditoNegociacion").get("id"), idCredito));

		query.where(cb.and(where.toArray(new Predicate[] {})));

		// ~~> SELECT Long id, String usuario, Timestamp fecha, String observacion, Long idCredito
		query.multiselect(poll.get("id"), poll.get("usuario"), poll.get("fechaCreacion"), poll.get("observacion"), poll.get("tbQoCreditoNegociacion").get("id"));

		// ~~> EJECUTAR CONSULTA

		TypedQuery<HistoricoObservacionWrapper> createQuery = this.getEntityManager().createQuery(query);

		return createQuery.getResultList();
	}

}
