/**
 * 
 */
package com.relative.quski.repository.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.ExcepcionRolRepository;
import com.relative.quski.wrapper.ExcepcionRolWrapper;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
@Stateless(mappedName = "excepcionRolRepository")
public class ExcepcionRolRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcionRol>
		implements ExcepcionRolRepository {

	@Override
	public List<ExcepcionRolWrapper> findByRolAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String rol, String identificacion) throws RelativeException {
		try {
			List<TbQoExcepcionRol> listRol = null;
			if (StringUtils.isNotBlank(rol)) {
				CriteriaBuilder cbb = getEntityManager().getCriteriaBuilder();
				CriteriaQuery<TbQoExcepcionRol> queryy = cbb.createQuery(TbQoExcepcionRol.class);
				Root<TbQoExcepcionRol> pollRol = queryy.from(TbQoExcepcionRol.class);
				queryy.where(cbb.and(cbb.equal(pollRol.get("rol"), rol)));
				TypedQuery<TbQoExcepcionRol> createQue = this.getEntityManager().createQuery(queryy);
				listRol = createQue.getResultList();
			}
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ExcepcionRolWrapper> query = cb.createQuery(ExcepcionRolWrapper.class);
			// ~~> FROM
			Root<TbQoExcepcion> poll = query.from(TbQoExcepcion.class);
			Join<TbQoExcepcion, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if (listRol != null && !listRol.isEmpty()) {
				List<String> tipos = new ArrayList<>();
				for (TbQoExcepcionRol l : listRol) {
					tipos.add(l.getExcepcion().toString());
				}
				where.add(poll.get("tipoExcepcion").in(tipos));
			}
			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.like(joinCliente.get("cedulaCliente"), "%"+identificacion+"%"));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("tipoExcepcion"), joinCliente.get("primerNombre"),
					joinCliente.get("apellidoPaterno"), joinNegocia.get("id"), joinCliente.get("cedulaCliente"),joinCliente.get("nombreCompleto"),
					poll.get("observacionAsesor"),poll.get("estadoExcepcion"));

			// ~~> ORDER BY
			if (sortDirections.equals("asc")) {
				query.orderBy(cb.asc(poll.get(sortFields)));
			} else {
				query.orderBy(cb.desc(poll.get(sortFields)));
			}
			// ~~> EJECUTAR CONSULTA

			TypedQuery<ExcepcionRolWrapper> createQuery = this.getEntityManager().createQuery(query);

			return createQuery.setFirstResult(startRecord).setMaxResults(pageSize).getResultList();

			// ~~> FIN
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}

	}

	@Override
	public List<ExcepcionRolWrapper> findByRolAndIdentificacion(String rol, String identificacion)
			throws RelativeException {
		try {

			List<TbQoExcepcionRol> listRol = null;
			if (StringUtils.isNotBlank(rol)) {
				CriteriaBuilder cbb = getEntityManager().getCriteriaBuilder();
				CriteriaQuery<TbQoExcepcionRol> queryy = cbb.createQuery(TbQoExcepcionRol.class);
				Root<TbQoExcepcionRol> pollRol = queryy.from(TbQoExcepcionRol.class);
				queryy.where(cbb.and(cbb.equal(pollRol.get("rol"), rol)));
				TypedQuery<TbQoExcepcionRol> createQue = this.getEntityManager().createQuery(queryy);
				listRol = createQue.getResultList();
			}

			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			CriteriaQuery<ExcepcionRolWrapper> query = cb.createQuery(ExcepcionRolWrapper.class);
			// ~~> FROM
			Root<TbQoExcepcion> poll = query.from(TbQoExcepcion.class);

			Join<TbQoExcepcion, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();

			if (listRol != null && !listRol.isEmpty()) {
				List<String> tipos = new ArrayList<>();
				for (TbQoExcepcionRol l : listRol) {
					tipos.add(l.getExcepcion().toString());
				}
				where.add(poll.get("tipoExcepcion").in(tipos));
			}

			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("tipoExcepcion"), joinCliente.get("primerNombre"),
					joinCliente.get("apellidoPaterno"), joinNegocia.get("id"), joinCliente.get("cedulaCliente"),joinCliente.get("nombreCompleto"),
					poll.get("observacionAsesor"),poll.get("estadoExcepcion"));

			// ~~> EJECUTAR CONSULTA

			TypedQuery<ExcepcionRolWrapper> createQuery = this.getEntityManager().createQuery(query);

			return createQuery.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException();
		}
	}

	@Override
	public Integer countByRolAndIdentificacion(String rol, String identificacion) throws RelativeException {
		try {
			List<TbQoExcepcionRol> listRol = null;
			if (StringUtils.isNotBlank(rol)) {
				CriteriaBuilder cbb = getEntityManager().getCriteriaBuilder();
				CriteriaQuery<TbQoExcepcionRol> queryy = cbb.createQuery(TbQoExcepcionRol.class);
				Root<TbQoExcepcionRol> pollRol = queryy.from(TbQoExcepcionRol.class);
				queryy.where(cbb.and(cbb.equal(pollRol.get("rol"), rol)));
				TypedQuery<TbQoExcepcionRol> createQue = this.getEntityManager().createQuery(queryy);
				listRol = createQue.getResultList();
			}
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			// ~~> FROM
			Root<TbQoExcepcion> poll = query.from(TbQoExcepcion.class);

			Join<TbQoExcepcion, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if (listRol != null && !listRol.isEmpty()) {
				List<String> tipos = new ArrayList<>();
				for (TbQoExcepcionRol l : listRol) {
					tipos.add(l.getExcepcion().toString());
				}
				where.add(poll.get("tipoExcepcion").in(tipos));
			}
			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			query.select(cb.count(poll.get("id")));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException();
		}
	}
}
