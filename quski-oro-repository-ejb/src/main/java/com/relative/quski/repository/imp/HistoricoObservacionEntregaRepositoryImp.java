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
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoHistoricoObservacion;
import com.relative.quski.model.TbQoHistoricoObservacionEntrega;
import com.relative.quski.repository.HistoricoObservacionEntregaRepository;
import com.relative.quski.repository.spec.ParametroByNombreSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.HistoricoObservacionWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "historicoObservacionEntregaRepository")
public class HistoricoObservacionEntregaRepositoryImp extends GeneralRepositoryImp<Long, TbQoHistoricoObservacionEntrega> implements HistoricoObservacionEntregaRepository {

	@Override
	public List<TbQoHistoricoObservacionEntrega> findByIdCredito(Long idDevolucion) throws RelativeException {
		

		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			CriteriaQuery<TbQoHistoricoObservacionEntrega> query = cb.createQuery(TbQoHistoricoObservacionEntrega.class);
			// ~~> FROM
			Root<TbQoHistoricoObservacionEntrega> poll = query.from(TbQoHistoricoObservacionEntrega.class);

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();

			
			where.add(cb.equal(poll.get("idDevolucion"), idDevolucion));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			
			TypedQuery<TbQoHistoricoObservacionEntrega> createQuery = this.getEntityManager().createQuery(query);

			return createQuery.getResultList();
		}  catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}

}
