package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
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
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.repository.spec.DevolucionByNumeroOperacionSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DevolucionParamsWrapper;
import com.relative.quski.wrapper.DevolucionPendienteArribosWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;
import com.relative.quski.wrapper.DevolucionReporteWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<DevolucionReporteWrapper> findDevolucionReporte(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, DevolucionParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select codigo_operacion, codigo, nombre_cliente, cedula_cliente, pro.estado_proceso, pro.proceso, id_agencia, id_agencia_entrega, " + 
					"coalesce(to_char(dev.fecha_creacion at time zone 'utc', 'YYYY-MM-dd'),' ') as fc, " + 
					"coalesce(to_char(dev.fecha_arribo at time zone 'utc', 'YYYY-MM-dd'),' ') as fa, " + 
					"coalesce(to_char(dev.fecha_entrega at time zone 'utc', 'YYYY-MM-dd'),' ') as fe, " + 
					"dev.funda_actual " + 
					"from tb_qo_devolucion dev inner join tb_qo_proceso pro on pro.id_referencia = dev.id and proceso = 'DEVOLUCION' where 1=1";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and dev.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and dev.codigo  iLIKE :codigoBpm ");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				strQry.append(" and pro.estado_proceso  = :estado ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and dev.cedula_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and dev.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (wp.getAgenciaEntrega() != null) {
				strQry.append(" and dev.id_agencia_entrega  = :agenciaEntrega ");
			}
			if (wp.getAgenciaRecepcion() != null) {
				strQry.append(" and dev.id_agencia  = :agenciaRecepcion ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and dev.fecha_creacion >=:fcdesde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and dev.fecha_creacion <=:fchasta ");
			}
			if (wp.getFechaArriboDesde() != null) {
				strQry.append(" and dev.fecha_arribo >=:fadesde ");
			}	
			if (wp.getFechaArriboHasta() != null) {
				strQry.append(" and dev.fecha_arribo <=:fahasta ");
			}
			if (wp.getFechaEntregaDesde() != null) {
				strQry.append(" and dev.fecha_entrega >=:fedesde ");
			}	
			if (wp.getFechaEntregaHasta() != null) {
				strQry.append(" and dev.fecha_entrega <=:fehasta ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+ wp.getCodigoBpm()+"%");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				query.setParameter("estado",  wp.getEstado());
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (wp.getAgenciaEntrega() != null) {
				query.setParameter("agenciaEntrega",  wp.getAgenciaEntrega());
			}
			if (wp.getAgenciaRecepcion() != null) {
				query.setParameter("agenciaRecepcion",  wp.getAgenciaRecepcion());
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("fcdesde", wp.getFechaCreacionDesde());
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("fchasta", wp.getFechaCreacionHasta());
			}
			if (wp.getFechaArriboDesde() != null) {
				query.setParameter("fadesde", wp.getFechaArriboDesde());
			}	
			if (wp.getFechaArriboHasta() != null) {
				query.setParameter("fahasta", wp.getFechaArriboHasta());
			}
			if (wp.getFechaEntregaDesde() != null) {
				query.setParameter("fedesde", wp.getFechaEntregaDesde());
			}	
			if (wp.getFechaEntregaHasta() != null) {
				query.setParameter("fehasta", wp.getFechaEntregaHasta());
			}
			
			

			query.setFirstResult(startRecord);
			query.setMaxResults(pageSize);
			

		
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionReporteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DevolucionReporteWrapper> findDevolucionReporte(DevolucionParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select codigo_operacion, codigo, nombre_cliente, cedula_cliente, pro.estado_proceso, pro.proceso, id_agencia, id_agencia_entrega, " + 
					"coalesce(to_char(dev.fecha_creacion at time zone 'utc', 'YYYY-MM-dd'),' ') as fc, " + 
					"coalesce(to_char(dev.fecha_arribo at time zone 'utc', 'YYYY-MM-dd'),' ') as fa, " + 
					"coalesce(to_char(dev.fecha_entrega at time zone 'utc', 'YYYY-MM-dd'),' ') as fe, " + 
					"dev.funda_actual " + 
					"from tb_qo_devolucion dev inner join tb_qo_proceso pro on pro.id_referencia = dev.id and proceso = 'DEVOLUCION' where 1=1";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and dev.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and dev.codigo  iLIKE :codigoBpm ");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				strQry.append(" and pro.estado_proceso  = :estado ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and dev.cedula_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and dev.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (wp.getAgenciaEntrega() != null) {
				strQry.append(" and dev.id_agencia_entrega  = :agenciaEntrega ");
			}
			if (wp.getAgenciaRecepcion() != null) {
				strQry.append(" and dev.id_agencia  = :agenciaRecepcion ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and dev.fecha_creacion >=:fcdesde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and dev.fecha_creacion <=:fchasta ");
			}
			if (wp.getFechaArriboDesde() != null) {
				strQry.append(" and dev.fecha_arribo >=:fadesde ");
			}	
			if (wp.getFechaArriboHasta() != null) {
				strQry.append(" and dev.fecha_arribo <=:fahasta ");
			}
			if (wp.getFechaEntregaDesde() != null) {
				strQry.append(" and dev.fecha_entrega >=:fedesde ");
			}	
			if (wp.getFechaEntregaHasta() != null) {
				strQry.append(" and dev.fecha_entrega <=:fehasta ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+ wp.getCodigoBpm()+"%");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				query.setParameter("estado",  wp.getEstado());
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (wp.getAgenciaEntrega() != null) {
				query.setParameter("agenciaEntrega",  wp.getAgenciaEntrega());
			}
			if (wp.getAgenciaRecepcion() != null) {
				query.setParameter("agenciaRecepcion",  wp.getAgenciaRecepcion());
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("fcdesde", wp.getFechaCreacionDesde());
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("fchasta", wp.getFechaCreacionHasta());
			}
			if (wp.getFechaArriboDesde() != null) {
				query.setParameter("fadesde", wp.getFechaArriboDesde());
			}	
			if (wp.getFechaArriboHasta() != null) {
				query.setParameter("fahasta", wp.getFechaArriboHasta());
			}
			if (wp.getFechaEntregaDesde() != null) {
				query.setParameter("fedesde", wp.getFechaEntregaDesde());
			}	
			if (wp.getFechaEntregaHasta() != null) {
				query.setParameter("fehasta", wp.getFechaEntregaHasta());
			}
					
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionReporteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}
	

	@Override
	public Integer countDevolucionReporte(DevolucionParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select count(*) " + 
					"from tb_qo_devolucion dev inner join tb_qo_proceso pro on pro.id_referencia = dev.id and proceso = 'DEVOLUCION'";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and dev.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and dev.codigo  iLIKE :codigoBpm ");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				strQry.append(" and pro.estado_proceso  = :estado ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and dev.cedula_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and dev.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (wp.getAgenciaEntrega() != null) {
				strQry.append(" and dev.id_agencia_entrega  = :agenciaEntrega ");
			}
			if (wp.getAgenciaRecepcion() != null) {
				strQry.append(" and dev.id_agencia  = :agenciaRecepcion ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and dev.fecha_creacion >=:fcdesde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and dev.fecha_creacion <=:fchasta ");
			}
			if (wp.getFechaArriboDesde() != null) {
				strQry.append(" and dev.fecha_arribo >=:fadesde ");
			}	
			if (wp.getFechaArriboHasta() != null) {
				strQry.append(" and dev.fecha_arribo <=:fahasta ");
			}
			if (wp.getFechaEntregaDesde() != null) {
				strQry.append(" and dev.fecha_entrega >=:fedesde ");
			}	
			if (wp.getFechaEntregaHasta() != null) {
				strQry.append(" and dev.fecha_entrega <=:fehasta ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+ wp.getCodigoBpm()+"%");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				query.setParameter("estado",  wp.getEstado());
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (wp.getAgenciaEntrega() != null) {
				query.setParameter("agenciaEntrega",  wp.getAgenciaEntrega());
			}
			if (wp.getAgenciaRecepcion() != null) {
				query.setParameter("agenciaRecepcion",  wp.getAgenciaRecepcion());
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("fcdesde", wp.getFechaCreacionDesde());
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("fchasta", wp.getFechaCreacionHasta());
			}
			if (wp.getFechaArriboDesde() != null) {
				query.setParameter("fadesde", wp.getFechaArriboDesde());
			}	
			if (wp.getFechaArriboHasta() != null) {
				query.setParameter("fahasta", wp.getFechaArriboHasta());
			}
			if (wp.getFechaEntregaDesde() != null) {
				query.setParameter("fedesde", wp.getFechaEntregaDesde());
			}	
			if (wp.getFechaEntregaHasta() != null) {
				query.setParameter("fehasta", wp.getFechaEntregaHasta());
			}

		
			return ((BigInteger) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DevolucionReporteWrapper> findDevolucionProceso(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, DevolucionParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select dev.id,codigo_operacion, codigo, nombre_cliente, cedula_cliente, pro.estado_proceso, pro.proceso, id_agencia, id_agencia_entrega,  " + 
					"coalesce(to_char(dev.fecha_creacion at time zone 'utc', 'YYYY-MM-dd'),' ') as fc,  " + 
					"coalesce(to_char(dev.fecha_arribo at time zone 'utc', 'YYYY-MM-dd'),' ') as fa,  " + 
					"coalesce(to_char(dev.fecha_entrega at time zone 'utc', 'YYYY-MM-dd'),' ') as fe,  " + 
					"dev.funda_madre, " + 
					"dev.funda_actual, " + 
					"dev.asesor, " + 
					"coalesce(to_char(dev.fecha_aprobacion_solicitud at time zone 'utc', 'YYYY-MM-dd'),' ') as faprobacion,  " + 
					"coalesce(to_char(proc.hora_aprobador  at time zone 'utc', 'YYYY-MM-dd HH24:MI:SS'),' ') as fanulacion  " + 
					"from tb_qo_devolucion dev inner join tb_qo_proceso pro on pro.id_referencia = dev.id and pro.proceso = 'DEVOLUCION' " + 
					"left join tb_qo_proceso proc on proc.id_referencia = dev.id and proc.proceso = 'CANCELACION_DEVOLUCION' and proc.estado_proceso = 'APROBADO' " + 
					"where 1=1 ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and dev.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and dev.codigo  iLIKE :codigoBpm ");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				strQry.append(" and pro.estado_proceso  = :estado ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and dev.cedula_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and dev.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (wp.getAgenciaEntrega() != null) {
				strQry.append(" and dev.id_agencia_entrega  = :agenciaEntrega ");
			}
			if (wp.getAgenciaRecepcion() != null) {
				strQry.append(" and dev.id_agencia  = :agenciaRecepcion ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and dev.fecha_creacion >=:fcdesde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and dev.fecha_creacion <=:fchasta ");
			}
			if (wp.getFechaArriboDesde() != null) {
				strQry.append(" and dev.fecha_arribo >=:fadesde ");
			}	
			if (wp.getFechaArriboHasta() != null) {
				strQry.append(" and dev.fecha_arribo <=:fahasta ");
			}
			if (wp.getFechaEntregaDesde() != null) {
				strQry.append(" and dev.fecha_entrega >=:fedesde ");
			}	
			if (wp.getFechaEntregaHasta() != null) {
				strQry.append(" and dev.fecha_entrega <=:fehasta ");
			}
			if(wp.getEstados() != null && !wp.getEstados().isEmpty()) {
				String st = wp.getEstados().stream().map(EstadoProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and pro.estado_proceso in ('"+st+"') ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+ wp.getCodigoBpm()+"%");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				query.setParameter("estado",  wp.getEstado());
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (wp.getAgenciaEntrega() != null) {
				query.setParameter("agenciaEntrega",  wp.getAgenciaEntrega());
			}
			if (wp.getAgenciaRecepcion() != null) {
				query.setParameter("agenciaRecepcion",  wp.getAgenciaRecepcion());
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("fcdesde", wp.getFechaCreacionDesde());
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("fchasta", wp.getFechaCreacionHasta());
			}
			if (wp.getFechaArriboDesde() != null) {
				query.setParameter("fadesde", wp.getFechaArriboDesde());
			}	
			if (wp.getFechaArriboHasta() != null) {
				query.setParameter("fahasta", wp.getFechaArriboHasta());
			}
			if (wp.getFechaEntregaDesde() != null) {
				query.setParameter("fedesde", wp.getFechaEntregaDesde());
			}	
			if (wp.getFechaEntregaHasta() != null) {
				query.setParameter("fehasta", wp.getFechaEntregaHasta());
			}
			
			

			query.setFirstResult(startRecord);
			query.setMaxResults(pageSize);
			

		
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionReporteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DevolucionReporteWrapper> findDevolucionProceso(DevolucionParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select dev.id,codigo_operacion, codigo, nombre_cliente, cedula_cliente, pro.estado_proceso, pro.proceso, id_agencia, id_agencia_entrega,  " + 
					"coalesce(to_char(dev.fecha_creacion at time zone 'utc', 'YYYY-MM-dd'),' ') as fc,  " + 
					"coalesce(to_char(dev.fecha_arribo at time zone 'utc', 'YYYY-MM-dd'),' ') as fa,  " + 
					"coalesce(to_char(dev.fecha_entrega at time zone 'utc', 'YYYY-MM-dd'),' ') as fe,  " + 
					"dev.funda_madre, " + 
					"dev.funda_actual, " + 
					"dev.asesor, " + 
					"coalesce(to_char(dev.fecha_aprobacion_solicitud at time zone 'utc', 'YYYY-MM-dd'),' ') as faprobacion,  " + 
					"coalesce(to_char(proc.hora_aprobador  at time zone 'utc', 'YYYY-MM-dd HH24:MI:SS'),' ') as fanulacion  " + 
					"from tb_qo_devolucion dev inner join tb_qo_proceso pro on pro.id_referencia = dev.id and pro.proceso = 'DEVOLUCION' " + 
					"left join tb_qo_proceso proc on proc.id_referencia = dev.id and proc.proceso = 'CANCELACION_DEVOLUCION' and proc.estado_proceso = 'APROBADO' " + 
					"where 1=1 ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and dev.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and dev.codigo  iLIKE :codigoBpm ");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				strQry.append(" and pro.estado_proceso  = :estado ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and dev.cedula_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and dev.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (wp.getAgenciaEntrega() != null) {
				strQry.append(" and dev.id_agencia_entrega  = :agenciaEntrega ");
			}
			if (wp.getAgenciaRecepcion() != null) {
				strQry.append(" and dev.id_agencia  = :agenciaRecepcion ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and dev.fecha_creacion >=:fcdesde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and dev.fecha_creacion <=:fchasta ");
			}
			if (wp.getFechaArriboDesde() != null) {
				strQry.append(" and dev.fecha_arribo >=:fadesde ");
			}	
			if (wp.getFechaArriboHasta() != null) {
				strQry.append(" and dev.fecha_arribo <=:fahasta ");
			}
			if (wp.getFechaEntregaDesde() != null) {
				strQry.append(" and dev.fecha_entrega >=:fedesde ");
			}	
			if (wp.getFechaEntregaHasta() != null) {
				strQry.append(" and dev.fecha_entrega <=:fehasta ");
			}
			if(wp.getEstados() != null && !wp.getEstados().isEmpty()) {
				String st = wp.getEstados().stream().map(EstadoProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and pro.estado_proceso in ('"+st+"') ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+ wp.getCodigoBpm()+"%");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				query.setParameter("estado",  wp.getEstado());
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (wp.getAgenciaEntrega() != null) {
				query.setParameter("agenciaEntrega",  wp.getAgenciaEntrega());
			}
			if (wp.getAgenciaRecepcion() != null) {
				query.setParameter("agenciaRecepcion",  wp.getAgenciaRecepcion());
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("fcdesde", wp.getFechaCreacionDesde());
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("fchasta", wp.getFechaCreacionHasta());
			}
			if (wp.getFechaArriboDesde() != null) {
				query.setParameter("fadesde", wp.getFechaArriboDesde());
			}	
			if (wp.getFechaArriboHasta() != null) {
				query.setParameter("fahasta", wp.getFechaArriboHasta());
			}
			if (wp.getFechaEntregaDesde() != null) {
				query.setParameter("fedesde", wp.getFechaEntregaDesde());
			}	
			if (wp.getFechaEntregaHasta() != null) {
				query.setParameter("fehasta", wp.getFechaEntregaHasta());
			}
					
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionReporteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@Override
	public Integer countDevolucionProceso(DevolucionParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select count(*) " + 
					"from tb_qo_devolucion dev inner join tb_qo_proceso pro on pro.id_referencia = dev.id and pro.proceso = 'DEVOLUCION' " + 
					"left join tb_qo_proceso proc on proc.id_referencia = dev.id and proc.proceso = 'CANCELACION_DEVOLUCION' and proc.estado_proceso = 'APROBADO' " + 
					"where 1=1 ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and dev.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and dev.codigo  iLIKE :codigoBpm ");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				strQry.append(" and pro.estado_proceso  = :estado ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and dev.cedula_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and dev.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (wp.getAgenciaEntrega() != null) {
				strQry.append(" and dev.id_agencia_entrega  = :agenciaEntrega ");
			}
			if (wp.getAgenciaRecepcion() != null) {
				strQry.append(" and dev.id_agencia  = :agenciaRecepcion ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and dev.fecha_creacion >=:fcdesde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and dev.fecha_creacion <=:fchasta ");
			}
			if (wp.getFechaArriboDesde() != null) {
				strQry.append(" and dev.fecha_arribo >=:fadesde ");
			}	
			if (wp.getFechaArriboHasta() != null) {
				strQry.append(" and dev.fecha_arribo <=:fahasta ");
			}
			if (wp.getFechaEntregaDesde() != null) {
				strQry.append(" and dev.fecha_entrega >=:fedesde ");
			}	
			if (wp.getFechaEntregaHasta() != null) {
				strQry.append(" and dev.fecha_entrega <=:fehasta ");
			}
			if(wp.getEstados() != null && !wp.getEstados().isEmpty()) {
				String st = wp.getEstados().stream().map(EstadoProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and pro.estado_proceso in ('"+st+"') ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+ wp.getCodigoBpm()+"%");
			}
			if (StringUtils.isNotBlank(wp.getEstado())) {
				query.setParameter("estado",  wp.getEstado());
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (wp.getAgenciaEntrega() != null) {
				query.setParameter("agenciaEntrega",  wp.getAgenciaEntrega());
			}
			if (wp.getAgenciaRecepcion() != null) {
				query.setParameter("agenciaRecepcion",  wp.getAgenciaRecepcion());
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("fcdesde", wp.getFechaCreacionDesde());
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("fchasta", wp.getFechaCreacionHasta());
			}
			if (wp.getFechaArriboDesde() != null) {
				query.setParameter("fadesde", wp.getFechaArriboDesde());
			}	
			if (wp.getFechaArriboHasta() != null) {
				query.setParameter("fahasta", wp.getFechaArriboHasta());
			}
			if (wp.getFechaEntregaDesde() != null) {
				query.setParameter("fedesde", wp.getFechaEntregaDesde());
			}	
			if (wp.getFechaEntregaHasta() != null) {
				query.setParameter("fehasta", wp.getFechaEntregaHasta());
			}

		
			return ((BigInteger) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

}
