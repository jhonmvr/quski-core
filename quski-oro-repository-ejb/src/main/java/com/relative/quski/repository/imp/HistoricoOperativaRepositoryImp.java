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
import com.relative.quski.model.TbQoHistoricoOperativa;
import com.relative.quski.repository.HistoricoOperativaRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.HistoricoOperativaWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "historicoOperativaRepository")
public class HistoricoOperativaRepositoryImp extends GeneralRepositoryImp<Long, TbQoHistoricoOperativa> implements HistoricoOperativaRepository {

	@Override
	public List<HistoricoOperativaWrapper> findByIdCredito(Long idCredito) throws RelativeException {

		
		

		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			CriteriaQuery<HistoricoOperativaWrapper> query = cb.createQuery(HistoricoOperativaWrapper.class);
			// ~~> FROM
			Root<TbQoHistoricoOperativa> poll = query.from(TbQoHistoricoOperativa.class);

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();

			
			where.add(cb.equal(poll.get("tbQoNegociacion").get("id"), idCredito));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> Long id, Date fechaRegularizacion, String excepcion, String usuario
			query.multiselect(poll.get("id"),poll.get("fechaRegularizacion"), poll.get("excepcion"), poll.get("usuario"));

			// ~~> EJECUTAR CONSULTA

			TypedQuery<HistoricoOperativaWrapper> createQuery = this.getEntityManager().createQuery(query);

			return createQuery.getResultList();
		}  catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}

}
