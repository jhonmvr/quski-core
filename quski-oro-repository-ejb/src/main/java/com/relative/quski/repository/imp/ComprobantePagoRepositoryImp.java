package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoComprobantePago;
import com.relative.quski.repository.ComprobantePagoRepository;
import com.relative.quski.repository.ComprobanteRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "comprobantePagoRepository")
public class ComprobantePagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoComprobantePago> implements ComprobantePagoRepository {
    @Override
    public List<TbQoComprobantePago> listAllByIdNegociacion(Long idNegociacion) throws RelativeException {
        try {

            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoComprobantePago> cq = cb.createQuery(TbQoComprobantePago.class);
            Root<TbQoComprobantePago> comprobante = cq.from(TbQoComprobantePago.class);

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
