package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.BusquedaDevolucionWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "devolucionRepository")
public class DevolucionRepositoryImp extends GeneralRepositoryImp<Long, TbQoDevolucion>
		implements DevolucionRepository {

	@Inject
	Logger log;

	@Override
	public List<DevolucionProcesoWrapper> findOperaciones(BusquedaDevolucionWrapper bdw) throws RelativeException {
		// List<MovimientosDetalleCierreCajaWrapper> listMovimientos = null;
		try {

			String querySelect = " select " + 
					"	j.id, " + 
					"	j.fecha_creacion, " + 
					"	coalesce(j.nombre_agencia_solicitud, '') nombre_agencia_solicitud, " + 
					"	coalesce(j.codigo_operacion_madre, '') codigo_operacion_madre, " + 
					"	coalesce(j.codigo_operacion, '') codigo_operacion, " + 
					"	coalesce(j.nombre_cliente, '') nombre_cliente, " + 
					"	coalesce(j.cedula_cliente, '') cedula_cliente, " + 
					"	coalesce(j.funda_madre, '') funda_madre, " + 
					"	coalesce(j.funda_actual, '') funda_actual, " + 
					"	coalesce(j.ciudad_tevcol, '') ciudad_tevcol, " + 
					"	coalesce(to_char(j.fecha_arribo, 'DD/MM/YYYY'), '') fecha_arribo, " + 
					"	coalesce(j.asesor, '') asesor, " + 
					"	coalesce(to_char(j.fecha_aprobacion_solicitud, 'DD/MM/YYYY'), '') fecha_aprobacion_solicitud " + 
					"from " + 
					"	tb_qo_devolucion j " + 
					"inner join ( " + 
					"	select " + 
					"		* " + 
					"	from " + 
					"		tb_qo_proceso d " + 
					"	where " + 
					"		d.proceso = 'DEVOLUCION') as foo on " + 
					"	foo.id_referencia = j.id " + 
					"where " + 
					"	1 = 1 ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(bdw.getCodigoOperacion())) {
				
				strQry.append(" and j.codigo_operacion =:c ");
			}
			if (StringUtils.isNotBlank(bdw.getAgencia())) {
				strQry.append(" and j.agencia_entrega=:agencia ");
			}

			if (StringUtils.isNotBlank(bdw.getIdentificacion() )) {
				strQry.append(" and j.cedula_cliente=:identificacion   ");
			}

			if (bdw.getFechaAprobacionDesde() != null) {
				strQry.append(" and j.fecha_aprobacion  >=:desde ");
			}
			if (bdw.getFechaAprobacionHasta() != null) {
				strQry.append(" and and j.fecha_aprobacion  >=:hasta ");
			}
			if (bdw.getEstado() != null) {
				strQry.append(" and d.estado =:estado ");
			}

			strQry.append("ORDER BY j.fecha_creacion ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(bdw.getCodigoOperacion() )) {

				query.setParameter("c", bdw.getCodigoOperacion());

			}
			if (bdw.getEstado() != null) {
				log.info("=========> SET: ESTADO ==> " + bdw.getEstado() + " <====");
				query.setParameter("estado", bdw.getEstado().toString());
			}
			if (StringUtils.isNotBlank(bdw.getIdentificacion())) {

				query.setParameter("identificacion", bdw.getIdentificacion().toString());
			}
			if (bdw.getFechaAprobacionDesde() != null) {

				query.setParameter("desde", bdw.getFechaAprobacionDesde());
			}
			if (bdw.getFechaAprobacionHasta() != null) {

				query.setParameter("hasta", bdw.getFechaAprobacionDesde());
			}
			if (StringUtils.isNotBlank(bdw.getAgencia() )) {

				query.setParameter("agencia", bdw.getAgencia());
			}

			query.setFirstResult(bdw.getNumberPage());
			query.setMaxResults(bdw.getNumberItems());
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}

	}

	public Integer countOperaciones(BusquedaDevolucionWrapper bdw) throws RelativeException {
		// List<MovimientosDetalleCierreCajaWrapper> listMovimientos = null;
		try {

			String querySelect = " select count (j.id) " + 
					"from " + 
					"	tb_qo_devolucion j " + 
					"inner join ( " + 
					"	select " + 
					"		* " + 
					"	from " + 
					"		tb_qo_proceso d " + 
					"	where " + 
					"		d.proceso = 'DEVOLUCION') as foo on " + 
					"	foo.id_referencia = j.id " + 
					"where " + 
					"	1 = 1";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(bdw.getCodigoOperacion())) {
				
				strQry.append(" and j.codigo_operacion =:c ");
			}
			if (StringUtils.isNotBlank(bdw.getAgencia())) {
				strQry.append(" and j.agencia_entrega=:agencia ");
			}

			if (StringUtils.isNotBlank(bdw.getIdentificacion() )) {
				strQry.append(" and j.cedula_cliente=:identificacion   ");
			}

			if (bdw.getFechaAprobacionDesde() != null) {
				strQry.append(" and j.fecha_aprobacion  >=:desde ");
			}
			if (bdw.getFechaAprobacionHasta() != null) {
				strQry.append(" and and j.fecha_aprobacion  >=:hasta ");
			}
			if (bdw.getEstado() != null) {
				strQry.append(" and d.estado =:estado ");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(bdw.getCodigoOperacion() )) {

				query.setParameter("c", bdw.getCodigoOperacion());

			}
			if (bdw.getEstado() != null) {
				query.setParameter("estado", bdw.getEstado().toString());
			}
			if (StringUtils.isNotBlank(bdw.getIdentificacion())) {

				query.setParameter("identificacion", bdw.getIdentificacion().toString());
			}
			if (bdw.getFechaAprobacionDesde() != null) {

				query.setParameter("desde", bdw.getFechaAprobacionDesde());
			}
			if (bdw.getFechaAprobacionHasta() != null) {

				query.setParameter("hasta", bdw.getFechaAprobacionDesde());
			}
			if (StringUtils.isNotBlank(bdw.getAgencia() )) {

				query.setParameter("agencia", bdw.getAgencia());
			}

			return ((BigInteger) query.getSingleResult()).intValue();

		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}

	}

}
