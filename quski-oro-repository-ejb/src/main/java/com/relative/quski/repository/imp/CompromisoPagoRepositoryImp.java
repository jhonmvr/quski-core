package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoCompromisoPago;
import com.relative.quski.repository.CompromisoPagoRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "CompromisoPagoRepository")
public class CompromisoPagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoCompromisoPago> implements CompromisoPagoRepository {
    @Override
    public List<TbQoCompromisoPago> findByNumeroOperacion(String numeroOperacion) throws RelativeException {
        try {
            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoCompromisoPago> cq = cb.createQuery(TbQoCompromisoPago.class);
            Root<TbQoCompromisoPago> compromiso = cq.from(TbQoCompromisoPago.class);

            List<Predicate> predicates = new ArrayList<>();
            if (numeroOperacion != null) {
                predicates.add(cb.equal(compromiso.get("numeroOperacion"), numeroOperacion));
            }
            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.desc(compromiso.get("id")));
            List<TbQoCompromisoPago>  rest = em.createQuery(cq).getResultList();
            if(rest == null || rest.isEmpty()){
                return new ArrayList<TbQoCompromisoPago>();
            }
            return rest;
        } catch (IllegalArgumentException  e) {
            throw new RelativeException(Constantes.ERROR_CODE_READ, "findByNegociacionAndTipo " + e.getMessage());
        }catch(Exception e ){
			throw new RelativeException(e.getMessage());
		}
    }
}
