package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.repository.ExcepcionOperativaRepository;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "excepcionOperativaRepository")
public class ExcepcionOperativaRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcionOperativa> implements ExcepcionOperativaRepository {
    @Override
    public List<TbQoExcepcionOperativa> listAllByParams(PaginatedListWrapper<TbQoExcepcionOperativa> plw, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException {
        try {

            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoExcepcionOperativa> cq = cb.createQuery(TbQoExcepcionOperativa.class);
            Root<TbQoExcepcionOperativa> excepcion = cq.from(TbQoExcepcionOperativa.class);

            List<Predicate> predicates = new ArrayList<>();

            if (usuario != null && !usuario.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("usuarioSolicitante"), usuario));
            }
            if (estado != null && !estado.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("estadoExcepcion"), estado));
            }
            if (StringUtils.isNotBlank(codigo)) {
                predicates.add(cb.equal(excepcion.get("codigo"), codigo));
            }
            if (StringUtils.isNotBlank(codigoOperacion)) {
                predicates.add(cb.equal(excepcion.get("codigoOperacion"), codigoOperacion));
            }
            if (StringUtils.isNotBlank(idNegociacion)) {
                predicates.add(cb.equal(excepcion.get("idNegociacion").get("id"), Long.parseLong(idNegociacion)));
            }

            cq.where(predicates.toArray(new Predicate[0]));

            if (plw.getIsPaginated() !=null && plw.getIsPaginated().equals(PaginatedListWrapper.YES)) {
                return em.createQuery(cq)
                        .setFirstResult(plw.getCurrentPage())
                        .setMaxResults(plw.getPageSize())
                        .getResultList();
            } else {
                return em.createQuery(cq).getResultList();
            }
        } catch (IllegalArgumentException  e) {

            throw new RelativeException(Constantes.ERROR_CODE_READ, "listAllByParams " + e.getMessage());
        }
    }


    @Override
    public Integer countListAllByParams(String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException {
        try {
            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<TbQoExcepcionOperativa> excepcion = cq.from(TbQoExcepcionOperativa.class);

            List<Predicate> predicates = new ArrayList<>();

            if (usuario != null && !usuario.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("usuarioSolicitante"), usuario));
            }
            if (estado != null && !estado.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("estadoExcepcion"), estado));
            }
            if (StringUtils.isNotBlank(codigo)) {
                predicates.add(cb.equal(excepcion.get("codigo"), codigo));
            }
            if (StringUtils.isNotBlank(codigoOperacion)) {
                predicates.add(cb.equal(excepcion.get("codigoOperacion"), codigoOperacion));
            }
            if (StringUtils.isNotBlank(idNegociacion)) {
                predicates.add(cb.equal(excepcion.get("idNegociacion").get("id"), Long.parseLong(idNegociacion)));
            }

            cq.select(cb.count(excepcion)).where(predicates.toArray(new Predicate[0]));

            return em.createQuery(cq).getSingleResult().intValue();
        } catch (IllegalArgumentException  e) {

            throw new RelativeException(Constantes.ERROR_CODE_READ, "listAllByParams " + e.getMessage());
        }

    }

    @Override
    public TbQoExcepcionOperativa findByNegociacionAndTipo(Long idNegociacion, String tipoExcepcion, EstadoExcepcionEnum pendiente) throws RelativeException {
        try {
            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoExcepcionOperativa> cq = cb.createQuery(TbQoExcepcionOperativa.class);
            Root<TbQoExcepcionOperativa> excepcion = cq.from(TbQoExcepcionOperativa.class);

            List<Predicate> predicates = new ArrayList<>();

            if (idNegociacion != null) {
                predicates.add(cb.equal(excepcion.get("idNegociacion").get("id"), idNegociacion));
            }
            if (StringUtils.isNotBlank(tipoExcepcion)) {
                predicates.add(cb.equal(excepcion.get("tipoExcepcion"), tipoExcepcion));
            }
            if (pendiente != null) {
                predicates.add(cb.equal(excepcion.get("estadoExcepcion"), pendiente.toString()));
            }

            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.desc(excepcion.get("id")));
            List<TbQoExcepcionOperativa>  rest = em.createQuery(cq).getResultList();
            if(rest == null || rest.isEmpty()){
                return null;
            }
            return rest.get(0);
        } catch (IllegalArgumentException  e) {

            throw new RelativeException(Constantes.ERROR_CODE_READ, "findByNegociacionAndTipo " + e.getMessage());
        }
    }
}
