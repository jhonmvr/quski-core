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
import com.relative.quski.wrapper.ListadoOperacionDevueltaWrapper;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "creditoNegociacionRepository")
public class CreditoNegociacionImp  extends GeneralRepositoryImp<Long, TbQoCreditoNegociacion> implements CreditoNegociacionRepository {

	@Override
	public Long countByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoOperacionEnum estado,
			String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
/*
	@Override
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,
			EstadoEnum estado, String identificacion, int startRecord, Integer pageSize, String sortFields,
			String sortDirections) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(new CreditoNegociacionByParamsSpec(fechaDesde, fechaHasta, codigoOperacion, estado, identificacion),
					startRecord, pageSize, sortFields, sortDirections);

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de credito por parametros");
		}
	}

	@Override
	public List<TbQoCreditoNegociacion> findByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion,
			EstadoEnum estado, String identificacion) throws RelativeException {
		try {
			return this.findAllBySpecification(new CreditoNegociacionByParamsSpec(fechaDesde, fechaHasta, codigoOperacion, estado, identificacion));

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de credito por parametros");
		}
	}

	@Override
	public Long countByParams(Date fechaDesde, Date fechaHasta, String codigoOperacion, EstadoEnum estado,
			String identificacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

*/
	public List<TbQoCreditoNegociacion> findAsignacionByParams(String codigoProceso, String nombreAgencia, String nombreProceso, int cedula) {
			return 	findAllBySpecification(
						new AsignacionByParamsSpec(codigoProceso, nombreAgencia, nombreProceso, cedula)
					);
	}
@Override
public List<TbQoCreditoNegociacion> findPorCustomFilterCreditos(PaginatedWrapper pw, String fechaDesde,
		String fechaHasta, String codigoOperacion, String proceso, String identificacion, String agencia)
		throws RelativeException {
	try {
		return this.findAllBySpecificationPaged(
				new CreditoNegociacionByParamsSpec(fechaDesde,
						fechaHasta, codigoOperacion,  proceso, identificacion, agencia),
				pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
	} catch (Exception e) {
		throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al listar creditos " + e.getMessage());
	}
}

public List<ListadoOperacionDevueltaWrapper> listOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia,
		String proceso, String cedula) throws RelativeException {
	try {
		// ~~> QUERY
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ListadoOperacionDevueltaWrapper> query = cb.createQuery(ListadoOperacionDevueltaWrapper.class);
		// ~~> FROM
		Root<TbQoCreditoNegociacion> poll = query.from(TbQoCreditoNegociacion.class);
		Join<TbQoCreditoNegociacion, TbQoAgencia> joinContratoAgencia = poll.join("tbQoAgencia");
		Join<TbQoCreditoNegociacion, TbQoProceso> joinCreditoProceso = poll.join("tbQoProceso");
		Join<TbQoCreditoNegociacion, TbQoNegociacion> joinCreditoNegociacion = poll.join("tbQoNegociacion");
		Join<TbQoNegociacion, TbQoCliente> joinNegociacionCliente = joinCreditoNegociacion.join("tbMiCliente");
		// ~~> WHERE
		Predicate where = cb.and(cb.like(joinContratoAgencia.get("nombre_agencia"), agencia),
				cb.like(joinCreditoProceso.get("nombre_proceso"), proceso), cb.like(joinNegociacionCliente.get("cedula_cliente"), cedula));
		query.where(where);
		// ~~> SELECT
		query.multiselect(poll.get("codigo_operacion"), poll.get("nombre_proceso"), poll.get("proceso"), poll.get("fecha_creacion"),
				joinNegociacionCliente.get("primer_nombre"), joinNegociacionCliente.get("segundo_nombre"), joinNegociacionCliente.get("apellido_paterno"),
				joinNegociacionCliente.get("apellido_materno"), joinNegociacionCliente.get("cedula_cliente, "), poll.get("descripcion"),
				joinContratoAgencia.get("nombre_agencia"), poll.get("actividad_actual"), poll.get("usuario_ejecutor"), poll.get("motivo_devolucion"))
				.distinct(true);
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
public Integer countOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia,
		String proceso, String cedula) throws RelativeException {
	try {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		// ~~> FROM
		Root<TbQoCreditoNegociacion> poll = query.from(TbQoCreditoNegociacion.class);
		Join<TbQoCreditoNegociacion, TbQoAgencia> joinContratoAgencia = poll.join("tbQoAgencia");
		Join<TbQoCreditoNegociacion, TbQoProceso> joinCreditoProceso = poll.join("tbQoProceso");
		Join<TbQoCreditoNegociacion, TbQoNegociacion> joinCreditoNegociacion = poll.join("tbQoNegociacion");
		Join<TbQoNegociacion, TbQoCliente> joinNegociacionCliente = joinCreditoNegociacion.join("tbMiCliente");
		// ~~> WHERE
		Predicate where = cb.and(cb.like(joinContratoAgencia.get("nombre_agencia"), agencia),
				cb.like(joinCreditoProceso.get("nombre_proceso"), proceso), cb.like(joinNegociacionCliente.get("cedula_cliente"), cedula));
		query.where(where);
		query.multiselect(cb.countDistinct(poll.get("id")));
		TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
		return createQuery.getSingleResult().intValue();
	} catch (Exception e) {
		throw new RelativeException("" + e);
	}
}




}
