package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.repository.ExcepcionOperativaRepository;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.ExcepcionOperativaClienteWrapper;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Stateless(mappedName = "excepcionOperativaRepository")
public class ExcepcionOperativaRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcionOperativa> implements ExcepcionOperativaRepository {
    @Override
    public List<TbQoExcepcionOperativa> listAllByParams(PaginatedListWrapper<TbQoExcepcionOperativa> plw, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion, List<Long> nivelAprobacion) throws RelativeException {
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
            if (nivelAprobacion != null && !nivelAprobacion.isEmpty()) {
                predicates.add(excepcion.get("nivelAprobacion").in(nivelAprobacion));
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
    @SuppressWarnings("unchecked")
	@Override
    public List<ExcepcionOperativaClienteWrapper> listAllByParamsClient(String cedula, List<Long> nivelAprobacion) throws RelativeException {
        try {
            // Consulta base
            String querySelect = "SELECT o.id, "
                    + "CAST(o.id_negociacion AS int) AS id_negociacion, "
                    + "o.codigo, o.codigo_operacion, o.tipo_excepcion, o.nivel_aprobacion, "
                    + "COALESCE(o.monto_involucrado, 0) AS monto_involucrado, "
                    + "o.usuario_solicitante, "
                    + "COALESCE(CAST(o.fecha_solicitud AS varchar), '') AS fecha_solicitud, "
                    + "COALESCE(o.observacion_asesor, '') AS observacion_asesor, "
                    + "COALESCE(o.observacion_aprobador, '') AS observacion_aprobador, "
                    + "COALESCE(o.usuario_aprobador, '') AS usuario_aprobador, "
                    + "c.nombre_completo, c.cedula_cliente, "
                    + "COALESCE(r.numero_operacion, '') AS numero_operacion "
                    + "FROM tb_qo_excepcion_operativa o "
                    + "LEFT JOIN tb_qo_negociacion n ON o.id_negociacion = n.id "
                    + "LEFT JOIN tb_qo_credito_negociacion r ON r.id_negociacion = n.id "
                    + "LEFT JOIN tb_qo_cliente c ON c.id = n.id_cliente "
                    + "WHERE estado_excepcion = 'PENDIENTE'";

            // Construcción dinámica del query
            StringBuilder strQry = new StringBuilder(querySelect);

            if (StringUtils.isNotBlank(cedula)) {
                strQry.append(" AND c.cedula_cliente ILIKE :cedula");
            }

            if (nivelAprobacion != null && !nivelAprobacion.isEmpty()) {
                strQry.append(" AND o.nivel_aprobacion IN (:nivelAprobacion)");
            }

            // Crear y configurar query
            Query query = this.getEntityManager().createNativeQuery(strQry.toString());

            if (StringUtils.isNotBlank(cedula)) {
                query.setParameter("cedula", "%" + cedula + "%");
            }

            if (nivelAprobacion != null && !nivelAprobacion.isEmpty()) {
                query.setParameter("nivelAprobacion", nivelAprobacion);
            }


            return QuskiOroUtil.getResultList(query.getResultList(), ExcepcionOperativaClienteWrapper.class);
        } catch (Exception e) {

            throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al listar excepciones operativas por parámetros: " + e.getMessage());
        }
    }


    @Override
    public Integer countListAllByParams(String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion, List<Long> nivelAprobacion) throws RelativeException {
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
            if (nivelAprobacion != null && !nivelAprobacion.isEmpty()) {
                predicates.add(excepcion.get("nivelAprobacion").in(nivelAprobacion));
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
    @Override
    public List<TbQoExcepcionOperativa> findByNegociacion(Long idNegociacion) throws RelativeException {
        try {
            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoExcepcionOperativa> cq = cb.createQuery(TbQoExcepcionOperativa.class);
            Root<TbQoExcepcionOperativa> excepcion = cq.from(TbQoExcepcionOperativa.class);

            List<Predicate> predicates = new ArrayList<>();
            if (idNegociacion != null) {
                predicates.add(cb.equal(excepcion.get("idNegociacion").get("id"), idNegociacion));
            }
            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.desc(excepcion.get("id")));
            List<TbQoExcepcionOperativa>  rest = em.createQuery(cq).getResultList();
            if(rest == null || rest.isEmpty()){
                return null;
            }
            return rest;
        } catch (IllegalArgumentException  e) {

            throw new RelativeException(Constantes.ERROR_CODE_READ, "findByNegociacionAndTipo " + e.getMessage());
        }
    }
}
