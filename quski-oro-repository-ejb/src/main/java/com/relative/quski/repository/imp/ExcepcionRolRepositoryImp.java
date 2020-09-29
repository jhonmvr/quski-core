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
import com.relative.quski.model.TbQoExcepcione;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.ExcepcionRolRepository;
import com.relative.quski.wrapper.ExcepcionRolWrapper;
import com.relative.quski.wrapper.ExceptionWrapper;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
@Stateless(mappedName = "excepcionRolRepository")
public class ExcepcionRolRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcionRol>
		implements ExcepcionRolRepository {

	@Override
	public List<ExcepcionRolWrapper > findByRolAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String rol, String identificacion) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ExcepcionRolWrapper> query = cb.createQuery(ExcepcionRolWrapper.class);
			// ~~> FROM
			Root<TbQoExcepcione> poll = query.from(TbQoExcepcione.class);
			Join<TbQoExcepcione, TbQoExcepcionRol> joinExcepcionRol = poll.join("tipoExcepcion");
			Join<TbQoExcepcione, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if (StringUtils.isNotBlank(rol)) {
				where.add(cb.equal(joinExcepcionRol.get("rol"), rol));
			}

			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("tipoException"), joinCliente.get("cedulaCliente"),
					joinCliente.get("primerNombre"), joinCliente.get("apellidoPaterno"));

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
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ExcepcionRolWrapper> query = cb.createQuery(ExcepcionRolWrapper.class);
			// ~~> FROM
			Root<TbQoExcepcione> poll = query.from(TbQoExcepcione.class);
			Join<TbQoExcepcione, TbQoExcepcionRol> joinExcepcionRol = poll.join("tipoExcepcion");
			Join<TbQoExcepcione, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if (StringUtils.isNotBlank(rol)) {
				where.add(cb.equal(joinExcepcionRol.get("rol"), rol));
			}

			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));

			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"), poll.get("tipoException"), joinCliente.get("cedulaCliente"),
					joinCliente.get("primerNombre"), joinCliente.get("apellidoPaterno"));

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
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			// ~~> FROM
			Root<TbQoExcepcione> poll = query.from(TbQoExcepcione.class);
			Join<TbQoExcepcione, TbQoExcepcionRol> joinExcepcionRol = poll.join("tipoExcepcion");
			Join<TbQoExcepcione, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");

			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if (StringUtils.isNotBlank(rol)) {
				where.add(cb.equal(joinExcepcionRol.get("rol"), rol));
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
