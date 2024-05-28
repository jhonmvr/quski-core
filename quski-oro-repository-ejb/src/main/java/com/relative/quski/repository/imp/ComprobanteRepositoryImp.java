package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoComprobante;
import com.relative.quski.repository.ComprobanteRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "comprobanteRepository")
public class ComprobanteRepositoryImp extends GeneralRepositoryImp<Long, TbQoComprobante> implements ComprobanteRepository {
    @Override
    public List<TbQoComprobante> listAllByIdNegociacion(Long idNegociacion) throws RelativeException {
        try {

            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoComprobante> cq = cb.createQuery(TbQoComprobante.class);
            Root<TbQoComprobante> comprobante = cq.from(TbQoComprobante.class);

            List<Predicate> predicates = new ArrayList<>();

            if (idNegociacion != null) {
                predicates.add(cb.equal(comprobante.get("idNegociacion").get("id"), idNegociacion));
            }


            cq.where(predicates.toArray(new Predicate[0]));

            return em.createQuery(cq).getResultList();

        } catch (IllegalArgumentException e) {

            throw new RelativeException(Constantes.ERROR_CODE_READ, "listAllByIdNegociacion " + e.getMessage());
        }
    }
}
