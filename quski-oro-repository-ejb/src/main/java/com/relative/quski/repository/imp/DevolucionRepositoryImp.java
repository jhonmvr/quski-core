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
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.repository.spec.DevolucionByNumeroOperacionSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "devolucionRepository")
public class DevolucionRepositoryImp extends GeneralRepositoryImp<Long, TbQoDevolucion>
		implements DevolucionRepository {

	@Inject
	Logger log;

	@SuppressWarnings("unchecked")
	@Override
	public List<DevolucionProcesoWrapper> findOperaciones(PaginatedWrapper pw, String codigoOperacion, String agencia, 
			String fechaAprobacionDesde, String fechaAprobacionHasta, String identificacion) throws RelativeException {
		// List<MovimientosDetalleCierreCajaWrapper> listMovimientos = null;
		try {

			String querySelect = " select " + 
					"	j.id, " + 
					"	j.fecha_creacion, " + 
					"	coalesce(j.nombre_agencia_solicitud, '') nombre_agencia_solicitud, " + 
					"	coalesce(j.agencia_entrega, '') agencia_entrega, " + 
					"	coalesce(j.codigo_operacion_madre, '') codigo_operacion_madre, " + 
					"	coalesce(j.codigo_operacion, '') codigo_operacion, " + 
					"	coalesce(j.nombre_cliente, '') nombre_cliente, " + 
					"	coalesce(j.cedula_cliente, '') cedula_cliente, " + 
					"	coalesce(j.codigo, '') codigo, " + 
					"	coalesce(j.funda_madre, '') funda_madre, " + 
					"	coalesce(j.funda_actual, '') funda_actual, " + 
					"	coalesce(j.ciudad_tevcol, '') ciudad_tevcol, " + 
					"	coalesce(to_char(j.fecha_arribo, 'YYYY/MM/DD'), '') fecha_arribo, " + 
					"	coalesce(j.asesor, '') asesor, " + 
					"	coalesce(to_char(j.fecha_aprobacion_solicitud, 'YYYY/MM/DD'), '') fecha_aprobacion_solicitud " + 
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
			if (StringUtils.isNotBlank(codigoOperacion)) {
				
				strQry.append(" and j.codigo_operacion like :c ");
			}
			if (StringUtils.isNotBlank(agencia)) {
				strQry.append(" and j.agencia_entrega=:agencia ");
			}

			if (StringUtils.isNotBlank(identificacion )) {
				strQry.append(" and j.cedula_cliente like :identificacion   ");
			}

			if ( StringUtils.isNotBlank(fechaAprobacionDesde ) ) {
				strQry.append(" and j.fecha_aprobacion_solicitud  >= TO_DATE(:desde, 'YYYY-MM-DD') ");
			}
			if ( StringUtils.isNotBlank(fechaAprobacionHasta )  ) {
				strQry.append(" and  j.fecha_aprobacion_solicitud  <= TO_DATE(:hasta, 'YYYY-MM-DD') ");
			}
			
			strQry.append(" and (foo.estado_proceso ='PENDIENTE_FECHA' OR foo.estado_proceso = 'PENDIENTE_ARRIBO') ");
		

			strQry.append("ORDER BY j.fecha_creacion ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(codigoOperacion)) {

				query.setParameter("c", "%"+ codigoOperacion+"%");

			}
	
			if (StringUtils.isNotBlank(identificacion)) {

				query.setParameter("identificacion","%"+ identificacion+"%");
			}
			if (StringUtils.isNotBlank(fechaAprobacionDesde )) {

				query.setParameter("desde",fechaAprobacionDesde);
			}
			if (StringUtils.isNotBlank(fechaAprobacionHasta ) ) {

				query.setParameter("hasta", fechaAprobacionHasta);
			}
			if (StringUtils.isNotBlank(agencia )) {

				query.setParameter("agencia", agencia);
			}

			query.setFirstResult(pw.getStartRecord());
			query.setMaxResults(pw.getPageSize());
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}

	}

	public Integer countOperaciones(String codigoOperacion, String agencia, 
			String fechaAprobacionDesde, String fechaAprobacionHasta, String identificacion) throws RelativeException {
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
			if (StringUtils.isNotBlank(codigoOperacion)) {
				
				strQry.append(" and j.codigo_operacion like :c ");
			}
			if (StringUtils.isNotBlank(agencia)) {
				strQry.append(" and j.agencia_entrega =:agencia ");
			}

			if (StringUtils.isNotBlank(identificacion )) {
				strQry.append(" and j.cedula_cliente like :identificacion   ");
			}

			if (StringUtils.isNotBlank(fechaAprobacionDesde )) {
				strQry.append(" and j.fecha_aprobacion_solicitud  >= TO_DATE(:desde, 'YYYY-MM-DD') ");
			}
			if (StringUtils.isNotBlank(fechaAprobacionHasta ) ) {
				strQry.append(" and  j.fecha_aprobacion_solicitud  <= TO_DATE(:hasta, 'YYYY-MM-DD') ");
			}
			
				strQry.append(" and (foo.estado_proceso ='PENDIENTE_FECHA' OR foo.estado_proceso = 'PENDIENTE_ARRIBO')  ");
		

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(codigoOperacion)) {

				query.setParameter("c", "%"+ codigoOperacion+"%");

			}
	
			if (StringUtils.isNotBlank(identificacion)) {

				query.setParameter("identificacion","%"+ identificacion+"%");
			}
			if (StringUtils.isNotBlank(fechaAprobacionDesde )) {

				query.setParameter("desde",fechaAprobacionDesde);
			}
			if (StringUtils.isNotBlank(fechaAprobacionHasta ) ) {

				query.setParameter("hasta", fechaAprobacionHasta);
			}
			if (StringUtils.isNotBlank(agencia )) {

				query.setParameter("agencia", agencia);
			}

			return ((BigInteger) query.getSingleResult()).intValue();

		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DevolucionPendienteArribosWrapper> findOperacionArribo(PaginatedWrapper pw, String codigoOperacion, Long agencia, 
			EstadoProcesoEnum estado)
			throws RelativeException {
		try {

			String querySelect = " select " + 
					"	j.id, " + 
					"	j.fecha_creacion, "  + 
					"	coalesce(j.codigo_operacion_madre, '') codigo_operacion_madre, " + 
					"	coalesce(j.codigo_operacion, '') codigo_operacion, " + 
					"	coalesce(j.codigo, '') codigo, " + 
					"	coalesce(j.nombre_cliente, '') nombre_cliente, " + 
					"	coalesce(j.cedula_cliente, '') cedula_cliente, " + 
					"	coalesce(j.funda_madre, '') funda_madre, " + 
					"	coalesce(j.funda_actual, '') funda_actual, " + 
					"	coalesce(j.ciudad_tevcol, '') ciudad_tevcol, " + 
					"	coalesce(to_char(j.fecha_arribo, 'YYYY-MM-DD'), '') fecha_arribo, " + 
					"	coalesce(to_char(j.fecha_aprobacion_solicitud, 'YYYY-MM-DD'), '') fecha_aprobacion_solicitud, " +
					"	coalesce(j.valor_avaluo, 0) valor_avaluo, " + 
					"	coalesce(j.peso_bruto, 0) peso_bruto " + 
					"from " +  
					"	tb_qo_devolucion j " + 
					"inner join ( " + 
					"	select " + 
					"		* " + 
					"	from " + 
					"		tb_qo_proceso  " + 
					"	where " + 
					"		proceso = 'DEVOLUCION') as foo on " + 
					"	foo.id_referencia = j.id " + 
					"where " + 
					"	1 = 1 ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(codigoOperacion)) {
				
				strQry.append(" and j.codigo_operacion  like :c ");
			}
			if (agencia != null ) {
				strQry.append(" and j.id_agencia_entrega = :agencia ");
			}

			if (estado != null) {
				strQry.append(" and foo.estado_proceso =:estado ");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(codigoOperacion)){
				query.setParameter("c", "%"+ codigoOperacion+"%");
			}
			if (estado != null) {
				log.info("=========> SET: ESTADO ==> " + estado.toString() + " <====");
				query.setParameter("estado", estado.toString());
			}
			if (agencia != null ) {
				query.setParameter("agencia", agencia);
			}

			query.setFirstResult(pw.getStartRecord());
			query.setMaxResults(pw.getPageSize());
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionPendienteArribosWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}

	}

	@Override
	public Integer countOperacionArribo(String codigoOperacion, Long agencia, EstadoProcesoEnum estado) throws RelativeException {
		// TODO Auto-generated method stub
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
		if (StringUtils.isNotBlank(codigoOperacion)) {
			strQry.append(" and j.codigo_operacion like :c  ");
		}
		if (agencia != null) {
			strQry.append(" and j.id_agencia_entrega =:agencia ");
		}
		if (estado != null) {
			strQry.append(" and foo.estado_proceso =:estado ");
		}
		Query query = this.getEntityManager().createNativeQuery(strQry.toString());

		if (StringUtils.isNotBlank(codigoOperacion)){
			query.setParameter("c", "%"+ codigoOperacion+"%");

		}
		if (estado != null) {
			log.info("=========> SET: ESTADO ==> " + estado + " <====");
			query.setParameter("estado", estado.toString());
		}

		if ( agencia != null ) {

			query.setParameter("agencia", agencia);
		}
		
		return ((BigInteger) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@Override
	public List<TbQoDevolucion> findByNumeroOperacion(String codigoOperacion) throws RelativeException {
		try {
			List<TbQoDevolucion> list = this.findAllBySpecification( new DevolucionByNumeroOperacionSpec( codigoOperacion ));
			if(list.size() >  Long.valueOf( 0 ) ) {
				return list;
			}else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, " AL TRAER LOS RESULTADOS DE LA CONSULTA. ");
		}
	}

}
