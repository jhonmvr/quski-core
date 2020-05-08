package com.relative.quski.repository.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoAgencia;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.spec.AsignacionByParamsSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.repository.spec.ReasignacionByCodigoAndEstadoParamSpec;
import com.relative.quski.wrapper.AsignacionesWrapper;
import com.relative.quski.wrapper.ListadoOperacionDevueltaWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion>
		implements CreditoNegociacionRepository {

	@Override
	public Long countByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoOperacionEnum estado,
			String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TbQoCreditoNegociacion> findPorCustomFilterCreditos(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String codigoOperacion, String proceso, String identificacion, String agencia)
			throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(
					new CreditoNegociacionByParamsSpec(fechaDesde, fechaHasta, codigoOperacion, proceso, identificacion,
							agencia),
					pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al listar creditos " + e.getMessage());
		}
	}

	public List<ListadoOperacionDevueltaWrapper> listOperacionesDevueltas(PaginatedWrapper pw, String codigo,
			String agencia, String proceso, String cedula) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ListadoOperacionDevueltaWrapper> query = cb
					.createQuery(ListadoOperacionDevueltaWrapper.class);
			// ~~> FROM
			Root<TbQoCreditoNegociacion> poll = query.from(TbQoCreditoNegociacion.class);
			Join<TbQoCreditoNegociacion, TbQoAgencia> joinContratoAgencia = poll.join("tbQoAgencia");
			Join<TbQoCreditoNegociacion, TbQoProceso> joinCreditoProceso = poll.join("tbQoProceso");
			Join<TbQoCreditoNegociacion, TbQoNegociacion> joinCreditoNegociacion = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinNegociacionCliente = joinCreditoNegociacion.join("tbQoCliente");
			// ~~> WHERE
			Predicate where = cb.and(cb.like(joinContratoAgencia.get("nombreAgencia"), agencia),
					cb.like(joinCreditoProceso.get("nombre_proceso"), proceso),
					cb.like(joinNegociacionCliente.get("cedulaCliente"), cedula));
			query.where(where);
			// ~~> SELECT
			query.multiselect(poll.get("codigoOperacion"), poll.get("nombreProceso"), poll.get("proceso"),
					poll.get("fechaCreacion"), joinNegociacionCliente.get("primerNombre"),
					joinNegociacionCliente.get("segundoNombre"), joinNegociacionCliente.get("apellidoPaterno"),
					joinNegociacionCliente.get("apellidoMaterno"), joinNegociacionCliente.get("cedulaCliente, "),
					poll.get("descripcion"), joinContratoAgencia.get("nombreAgencia"), poll.get("actividadActual"),
					poll.get("usuarioEjecutor"), poll.get("motivoDevolucion")).distinct(true);
			// ~~> ORDER BY
			if (pw.getSortDirections().equals("asc")) {
				query.orderBy(cb.asc(poll.get(pw.getSortFields())));
			} else {
				query.orderBy(cb.desc(poll.get(pw.getSortFields())));
			}
			// ~~> EJECUTAR CONSULTA
			TypedQuery<ListadoOperacionDevueltaWrapper> createQuery = this.getEntityManager().createQuery(query);
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return createQuery.setFirstResult(pw.getStartRecord()).setMaxResults(pw.getPageSize()).getResultList();
			} else {
				return createQuery.getResultList();
			}
			// ~~> FIN
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	public Integer countOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia, String proceso,
			String cedula) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			// ~~> FROM
			Root<TbQoCreditoNegociacion> poll = query.from(TbQoCreditoNegociacion.class);
			Join<TbQoCreditoNegociacion, TbQoAgencia> joinContratoAgencia = poll.join("tbQoAgencia");
			Join<TbQoCreditoNegociacion, TbQoProceso> joinCreditoProceso = poll.join("tbQoProceso");
			Join<TbQoCreditoNegociacion, TbQoNegociacion> joinCreditoNegociacion = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinNegociacionCliente = joinCreditoNegociacion.join("tbQoCliente");
			// ~~> WHERE
			Predicate where = cb.and(cb.like(joinContratoAgencia.get("nombre_agencia"), agencia),
					cb.like(joinCreditoProceso.get("nombre_proceso"), proceso),
					cb.like(joinNegociacionCliente.get("cedula_cliente"), cedula));
			query.where(where);
			query.multiselect(cb.countDistinct(poll.get("id")));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
		} catch (Exception e) {
			throw new RelativeException("" + e);
		}
	}

	@Override
	public List<AsignacionesWrapper> findAsignacionesByParamsPaginated(PaginatedWrapper pw, String codigoOperacion,
			String nombreAgencia, String nombreProceso, String cedula) throws RelativeException {
		try {
			if (pw == null) {
				return findAllBySpecification(
						new AsignacionByParamsSpec(codigoOperacion, nombreAgencia, nombreProceso, cedula));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return findAllBySpecificationPaged(
							new AsignacionByParamsSpec(codigoOperacion, nombreAgencia, nombreProceso, cedula),
							pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return findAllBySpecification(
							new AsignacionByParamsSpec(codigoOperacion, nombreAgencia, nombreProceso, cedula));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer Asignaciones, " + e.getMessage());
		}
	}

	@Override
	public List<TbQoCreditoNegociacion> findBycodigOpEstado(String codigOp, EstadoOperacionEnum estado, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		List<TbQoCreditoNegociacion> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new ReasignacionByCodigoAndEstadoParamSpec(codigOp, estado),
					startRecord, pageSize, sortFields, sortDirections);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);

		}
		return null;

	}

	@Override
	public List<TbQoCreditoNegociacion> findBycodigOpEstado(String codigOp, EstadoOperacionEnum estado)
			throws RelativeException {
		List<TbQoCreditoNegociacion> tmp;
		try {
			tmp = this.findAllBySpecification(new ReasignacionByCodigoAndEstadoParamSpec(codigOp, estado));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);

		}
		return null;

	}

	@Override
	public Long countfindBycodigOpEstado(String codigOp, EstadoOperacionEnum estado) throws RelativeException {
		try {
			return this.countBySpecification(new ReasignacionByCodigoAndEstadoParamSpec(codigOp, estado));

		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION TIPO JOYA PARA ID " + estado);

		}

	}

}
