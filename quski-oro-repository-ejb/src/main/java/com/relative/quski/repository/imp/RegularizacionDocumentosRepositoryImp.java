package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.repository.RegularizacionDocumentosRepository;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.RegularizacionClienteWrapper;

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

@Stateless(mappedName = "regularizacionDocumentosRepository")
public class RegularizacionDocumentosRepositoryImp extends GeneralRepositoryImp<Long, TbQoRegularizacionDocumento> implements RegularizacionDocumentosRepository {
    @Override
    public List<TbQoRegularizacionDocumento> listAllByParams(PaginatedListWrapper<TbQoRegularizacionDocumento> plw, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion) throws RelativeException {
        try {

            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoRegularizacionDocumento> cq = cb.createQuery(TbQoRegularizacionDocumento.class);
            Root<TbQoRegularizacionDocumento> excepcion = cq.from(TbQoRegularizacionDocumento.class);

            List<Predicate> predicates = new ArrayList<>();

            if (usuario != null && !usuario.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("usuarioSolicitante"), usuario));
            }
            if (estado != null && !estado.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("estadoRegularizacion"), estado));
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

            if (plw != null && plw.getIsPaginated() !=null && plw.getIsPaginated().equals(PaginatedListWrapper.YES)) {
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
            Root<TbQoRegularizacionDocumento> excepcion = cq.from(TbQoRegularizacionDocumento.class);

            List<Predicate> predicates = new ArrayList<>();

            if (usuario != null && !usuario.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("usuarioSolicitante"), usuario));
            }
            if (estado != null && !estado.isEmpty()) {
                predicates.add(cb.equal(excepcion.get("estadoRegularizacion"), estado));
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
    @SuppressWarnings("unchecked")
	@Override
    public List<RegularizacionClienteWrapper> listAllByParamsClient(String cedula) throws RelativeException {
    	try {
			String querySelect = "select o.id							as id, "
					+ "	CAST(o.id_negociacion as int) 					as id_negociacion, "
					+ "	o.codigo_operacion								as codigo_operacion, "
					+ "	o.tipo_excepcion								as tipo_excepcion, "
					+ "	coalesce(o.usuario_solicitante,'')				as usuario_solicitante, "
					+ "	coalesce(o.usuario_aprobador,'') 				as usuario_aprobador, "
					+ "	coalesce(cast(o.fecha_solicitud as varchar),'')	as fecha_solicitud, "
					+ "	o.identificacion_cliente						as identificacion_cliente, "
					+ "	coalesce(o.estado_regularizacion, '') 			as estado_regularizacion, "
					+ "	c.nombre_completo								as nombre_completo, "
					+ "	coalesce(r.numero_operacion,'') 				as numero_operacion "
					+ "from tb_qo_regularizacion_documentos o "
					+ "left join tb_qo_credito_negociacion r on r.id_negociacion = o.id_negociacion "
					+ "left join tb_qo_cliente c on c.cedula_cliente  = o.identificacion_cliente "
					+ "where o.estado_regularizacion = 'PENDIENTE_APROBACION' ";
			StringBuilder strQry = new StringBuilder( querySelect );
		
			if(StringUtils.isNotBlank(cedula)) {
				strQry.append(" and c.cedula_cliente iLIKE :cedula ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(StringUtils.isNotBlank(cedula)){
				query.setParameter("cedula", "%"+cedula+"%");
			}
		
			return QuskiOroUtil.getResultList(query.getResultList(), RegularizacionClienteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
    }
}
