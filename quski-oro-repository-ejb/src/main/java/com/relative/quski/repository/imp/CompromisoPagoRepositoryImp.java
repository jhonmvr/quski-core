package com.relative.quski.repository.imp;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.model.TbQoCompromisoPago;
import com.relative.quski.repository.CompromisoPagoRepository;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.CompromisoParamsWrapper;
import com.relative.quski.wrapper.CompromisoReporteWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless(mappedName = "compromisoPagoRepository")
public class CompromisoPagoRepositoryImp extends GeneralRepositoryImp<Long, TbQoCompromisoPago> implements CompromisoPagoRepository {
	@Inject
	Logger log;
	@Override
    public List<TbQoCompromisoPago> findByNumeroOperacion(String numeroOperacion) throws RelativeException {
        try {
            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoCompromisoPago> cq = cb.createQuery(TbQoCompromisoPago.class);
            Root<TbQoCompromisoPago> compromiso = cq.from(TbQoCompromisoPago.class);
			log.info("==== repository ========> " );

            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(numeroOperacion)) {
    			log.info("==== repository if ========> " );
                predicates.add(cb.equal(compromiso.get("numeroOperacion"), numeroOperacion));
            }
            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.desc(compromiso.get("id")));
            List<TbQoCompromisoPago>  rest = em.createQuery(cq).getResultList();
            if(rest == null || rest.isEmpty()){
                return new ArrayList<TbQoCompromisoPago>();
            }
            return rest;
        } catch (IllegalArgumentException  e) {
            throw new RelativeException(Constantes.ERROR_CODE_READ, "findByNegociacionAndTipo " + e.getMessage());
        }catch(Exception e ){
			throw new RelativeException(e.getMessage());
		}
    }
    @Override
    public List<TbQoCompromisoPago> findByNumeroOperacionEstado(String numeroOperacion, EstadoProcesoEnum estado) throws RelativeException {
        try {
            EntityManager em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TbQoCompromisoPago> cq = cb.createQuery(TbQoCompromisoPago.class);
            Root<TbQoCompromisoPago> compromiso = cq.from(TbQoCompromisoPago.class);
            List<Predicate> predicates = new ArrayList<>();
			log.info("==== repository ========> " );

            if (StringUtils.isNotBlank(numeroOperacion)) {
    			log.info("==== repository if numeroOperacion ========> " + numeroOperacion);
                predicates.add(cb.equal(compromiso.get("numeroOperacion"), numeroOperacion));
            }
            if (estado != null) {
    			log.info("==== repository if estado ========> " + estado);
                predicates.add(cb.equal(compromiso.<EstadoProcesoEnum>get("estadoCompromiso"), estado));
            }

            cq.where(predicates.toArray(new Predicate[0]));
            cq.orderBy(cb.desc(compromiso.get("id")));
            List<TbQoCompromisoPago>  rest = em.createQuery(cq).getResultList();
			log.info("==== repository rest ========> " + rest.size() );
            if(rest == null || rest.isEmpty()){
                return new ArrayList<TbQoCompromisoPago>();
            }
            return rest;
        } catch (IllegalArgumentException  e) {
            throw new RelativeException(Constantes.ERROR_CODE_READ, "findByNegociacionAndTipo " + e.getMessage());
        }catch(Exception e ){
			throw new RelativeException(e.getMessage());
		}
    }
    @SuppressWarnings("unchecked")
	@Override
	public List<CompromisoReporteWrapper> findCompromisoReporte(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, CompromisoParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select coalesce(codigo, '')	as codigo, "
					+ "	coalesce(codigo_operacion, '')	as codigo_operacion, "
					+ "	coalesce(tipo_compromiso , '')	as tipo_compromiso, "
					+ "	coalesce(estado_compromiso, '')	as estado_compromiso, "
					+ "	coalesce(to_char(fecha_compromiso_pago at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_compromiso_pago, "
					+ "	coalesce(usuario_solicitud, '')	as usuario_solicitud, "
					+ "	coalesce(usuario_aprobador, '')	as usuario_aprobador, "
					+ "	coalesce(observacion_solicitud, '')	as observacion_solicitud, "
					+ "	coalesce(observacion_aprobador, '')	as observacion_aprobador, "
					+ "	coalesce(to_char(fecha_solicitud at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_solicitud, "
					+ "	coalesce(to_char(fecha_aprobador at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_aprobador, "
					+ "	coalesce(numero_operacion, '')	as numero_operacion, "
					+ "	coalesce(to_char(fecha_compromiso_pago_anterior at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_compromiso_pago_anterior, "
					+ "	coalesce(nombre_cliente, '')	as nombre_cliente, "
					+ "	coalesce(identificacion_cliente, '')	as identificacion_cliente, "
					+ "	coalesce(pro.proceso, '') as proceso "
					+ "from tb_qo_compromiso_pago pag  "
					+ "inner join tb_qo_proceso pro on pro.id_referencia = pag.id where 1=1 ";


			StringBuilder strQry = new StringBuilder(querySelect);

			if (StringUtils.isNotBlank(wp.getCodigo())) {
				strQry.append(" and pag.codigo  iLIKE :codigo ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and pag.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getTipoCompromiso())) {
				strQry.append(" and pag.tipo_compromiso  = :tipoCompromiso ");
			}
			if (StringUtils.isNotBlank(wp.getEstadoCompromiso())) {
				strQry.append(" and pag.estado_compromiso  = :estadoCompromiso ");
			}
			if (wp.getFechaCompromisoDesde() != null) {
				strQry.append(" and pag.fecha_compromiso_pago >=:fechaCompromisoDesde ");
			}
			if (wp.getFechaCompromisoHasta() != null) {
				strQry.append(" and pag.fecha_compromiso_pago <=:fechaCompromisoHasta ");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioSolicitud())) {
				strQry.append(" and pag.usuario_solicitud  iLIKE :usuarioSolicitud ");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioAprobador())) {
				strQry.append(" and pag.usuario_aprobador  iLIKE :usuarioAprobador ");
			}
			if (wp.getFechaSolicitudDesde() != null) {
				strQry.append(" and pag.fecha_solicitud >=:fechaSolicitudDesde ");
			}
			if (wp.getFechaSolicitudHasta() != null) {
				strQry.append(" and pag.fecha_solicitud <=:fechaSolicitudHasta ");
			}
			if (StringUtils.isNotBlank(wp.getNumeroOperacion())) {
				strQry.append(" and pag.numero_operacion  iLIKE :numeroOperacion ");
			}
			if (wp.getFechaCompromisoAnteriorDesde() != null) {
				strQry.append(" and pag.fecha_compromiso_pago_anterior >=:fechaCompromisoAnteriorDesde ");
			}
			if (wp.getFechaCompromisoAnteriorHasta() != null) {
				strQry.append(" and pag.fecha_compromiso_pago_anterior <=:fechaCompromisoAnteriorHasta ");
			}

			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and pag.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and pag.identificacion_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getProceso())) {
				strQry.append(" and pro.proceso  = :proceso ");
			}else {
				strQry.append(" and pro.proceso in ('CAMBIO_COMPROMISO_PAGO','COMPROMISO_PAGO') ");
			}
			strQry.append(" order by fecha_solicitud ");

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			
			if (StringUtils.isNotBlank(wp.getCodigo())) {
				query.setParameter("codigo", "%"+ wp.getCodigo()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getTipoCompromiso())) {
				query.setParameter("tipoCompromiso",  wp.getTipoCompromiso());
			}
			if (StringUtils.isNotBlank(wp.getEstadoCompromiso())) {
				query.setParameter("estadoCompromiso",  wp.getEstadoCompromiso());
			}
			if (wp.getFechaCompromisoDesde() != null) {
				query.setParameter("fechaCompromisoDesde", wp.getFechaCompromisoDesde());
			}
			if (wp.getFechaCompromisoHasta() != null) {
				query.setParameter("fechaCompromisoHasta", wp.getFechaCompromisoHasta());
			}
			if (StringUtils.isNotBlank(wp.getUsuarioSolicitud())) {
				query.setParameter("usuarioSolicitud", "%"+ wp.getUsuarioSolicitud()+"%");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioAprobador())) {
				query.setParameter("usuarioAprobador", "%"+ wp.getUsuarioAprobador()+"%");
			}
			if (wp.getFechaSolicitudDesde() != null) {
				query.setParameter("fechaSolicitudDesde", wp.getFechaSolicitudDesde());
			}
			if (wp.getFechaSolicitudHasta() != null) {
				query.setParameter("fechaSolicitudHasta", wp.getFechaSolicitudHasta());
			}
			if (StringUtils.isNotBlank(wp.getNumeroOperacion())) {
				query.setParameter("numeroOperacion", "%"+ wp.getNumeroOperacion()+"%");
			}
			if (wp.getFechaCompromisoAnteriorDesde() != null) {
				query.setParameter("fechaCompromisoAnteriorDesde", wp.getFechaCompromisoAnteriorDesde());
			}
			if (wp.getFechaCompromisoAnteriorHasta() != null) {
				query.setParameter("fechaCompromisoAnteriorHasta", wp.getFechaCompromisoAnteriorHasta());
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getProceso())) {
				query.setParameter("proceso", wp.getProceso());
			}
			query.setFirstResult(startRecord);
			query.setMaxResults(pageSize);
			return QuskiOroUtil.getResultList(query.getResultList(), CompromisoReporteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al consultar compromisos " + e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompromisoReporteWrapper> findCompromisoReporte(CompromisoParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select coalesce(codigo, '')	as codigo, "
					+ "	coalesce(codigo_operacion, '')	as codigo_operacion, "
					+ "	coalesce(tipo_compromiso , '')	as tipo_compromiso, "
					+ "	coalesce(estado_compromiso, '')	as estado_compromiso, "
					+ "	coalesce(to_char(fecha_compromiso_pago at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_compromiso_pago, "
					+ "	coalesce(usuario_solicitud, '')	as usuario_solicitud, "
					+ "	coalesce(usuario_aprobador, '')	as usuario_aprobador, "
					+ "	coalesce(observacion_solicitud, '')	as observacion_solicitud, "
					+ "	coalesce(observacion_aprobador, '')	as observacion_aprobador, "
					+ "	coalesce(to_char(fecha_solicitud at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_solicitud, "
					+ "	coalesce(to_char(fecha_aprobador at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_aprobador, "
					+ "	coalesce(numero_operacion, '')	as numero_operacion, "
					+ "	coalesce(to_char(fecha_compromiso_pago_anterior at time zone 'utc', 'YYYY-MM-dd'),' ') as fecha_compromiso_pago_anterior, "
					+ "	coalesce(nombre_cliente, '')	as nombre_cliente, "
					+ "	coalesce(identificacion_cliente, '')	as identificacion_cliente, "
					+ "	coalesce(pro.proceso, '') as proceso "
					+ "from tb_qo_compromiso_pago pag  "
					+ "inner join tb_qo_proceso pro on pro.id_referencia = pag.id where 1=1 ";


			StringBuilder strQry = new StringBuilder(querySelect);

			if (StringUtils.isNotBlank(wp.getCodigo())) {
				strQry.append(" and pag.codigo  iLIKE :codigo ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and pag.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getTipoCompromiso())) {
				strQry.append(" and pag.tipo_compromiso  = :tipoCompromiso ");
			}
			if (StringUtils.isNotBlank(wp.getEstadoCompromiso())) {
				strQry.append(" and pag.estado_compromiso  = :estadoCompromiso ");
			}
			if (wp.getFechaCompromisoDesde() != null) {
				strQry.append(" and pag.fecha_compromiso_pago >=:fechaCompromisoDesde ");
			}
			if (wp.getFechaCompromisoHasta() != null) {
				strQry.append(" and pag.fecha_compromiso_pago <=:fechaCompromisoHasta ");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioSolicitud())) {
				strQry.append(" and pag.usuario_solicitud  iLIKE :usuarioSolicitud ");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioAprobador())) {
				strQry.append(" and pag.usuario_aprobador  iLIKE :usuarioAprobador ");
			}
			if (wp.getFechaSolicitudDesde() != null) {
				strQry.append(" and pag.fecha_solicitud >=:fechaSolicitudDesde ");
			}
			if (wp.getFechaSolicitudHasta() != null) {
				strQry.append(" and pag.fecha_solicitud <=:fechaSolicitudHasta ");
			}
			if (StringUtils.isNotBlank(wp.getNumeroOperacion())) {
				strQry.append(" and pag.numero_operacion  iLIKE :numeroOperacion ");
			}
			if (wp.getFechaCompromisoAnteriorDesde() != null) {
				strQry.append(" and pag.fecha_compromiso_pago_anterior >=:fechaCompromisoAnteriorDesde ");
			}
			if (wp.getFechaCompromisoAnteriorHasta() != null) {
				strQry.append(" and pag.fecha_compromiso_pago_anterior <=:fechaCompromisoAnteriorHasta ");
			}

			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and pag.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and pag.identificacion_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getProceso())) {
				strQry.append(" and pro.proceso  = :proceso ");
			}else {
				strQry.append(" and pro.proceso in ('CAMBIO_COMPROMISO_PAGO','COMPROMISO_PAGO') ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			
			if (StringUtils.isNotBlank(wp.getCodigo())) {
				query.setParameter("codigo", "%"+ wp.getCodigo()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getTipoCompromiso())) {
				query.setParameter("tipoCompromiso",  wp.getTipoCompromiso());
			}
			if (StringUtils.isNotBlank(wp.getEstadoCompromiso())) {
				query.setParameter("estadoCompromiso",  wp.getEstadoCompromiso());
			}
			if (wp.getFechaCompromisoDesde() != null) {
				query.setParameter("fechaCompromisoDesde", wp.getFechaCompromisoDesde());
			}
			if (wp.getFechaCompromisoHasta() != null) {
				query.setParameter("fechaCompromisoHasta", wp.getFechaCompromisoHasta());
			}
			if (StringUtils.isNotBlank(wp.getUsuarioSolicitud())) {
				query.setParameter("usuarioSolicitud", "%"+ wp.getUsuarioSolicitud()+"%");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioAprobador())) {
				query.setParameter("usuarioAprobador", "%"+ wp.getUsuarioAprobador()+"%");
			}
			if (wp.getFechaSolicitudDesde() != null) {
				query.setParameter("fechaSolicitudDesde", wp.getFechaSolicitudDesde());
			}
			if (wp.getFechaSolicitudHasta() != null) {
				query.setParameter("fechaSolicitudHasta", wp.getFechaSolicitudHasta());
			}
			if (StringUtils.isNotBlank(wp.getNumeroOperacion())) {
				query.setParameter("numeroOperacion", "%"+ wp.getNumeroOperacion()+"%");
			}
			if (wp.getFechaCompromisoAnteriorDesde() != null) {
				query.setParameter("fechaCompromisoAnteriorDesde", wp.getFechaCompromisoAnteriorDesde());
			}
			if (wp.getFechaCompromisoAnteriorHasta() != null) {
				query.setParameter("fechaCompromisoAnteriorHasta", wp.getFechaCompromisoAnteriorHasta());
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getProceso())) {
				query.setParameter("proceso", wp.getProceso());
			}
					
			return QuskiOroUtil.getResultList(query.getResultList(), CompromisoReporteWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}
	

	@Override
	public Integer countCompromisoReporte(CompromisoParamsWrapper wp) throws RelativeException {
		try {

			String querySelect = "select count(*) " + 
					"from tb_qo_compromiso_pago pag inner join tb_qo_proceso pro on pro.id_referencia = pag.id where 1=1 ";

			StringBuilder strQry = new StringBuilder(querySelect);

			if (StringUtils.isNotBlank(wp.getCodigo())) {
				strQry.append(" and pag.codigo  iLIKE :codigo ");
			}
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				strQry.append(" and pag.codigo_operacion  iLIKE :codigoOperacion ");
			}
			if (StringUtils.isNotBlank(wp.getTipoCompromiso())) {
				strQry.append(" and pag.tipo_compromiso  = :tipoCompromiso ");
			}
			if (StringUtils.isNotBlank(wp.getEstadoCompromiso())) {
				strQry.append(" and pag.estado_compromiso  = :estadoCompromiso ");
			}
			if (wp.getFechaCompromisoDesde() != null) {
				strQry.append(" and pag.fecha_compromiso_pago >=:fechaCompromisoDesde ");
			}
			if (wp.getFechaCompromisoHasta() != null) {
				strQry.append(" and pag.fecha_compromiso_pago <=:fechaCompromisoHasta ");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioSolicitud())) {
				strQry.append(" and pag.usuario_solicitud  iLIKE :usuarioSolicitud ");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioAprobador())) {
				strQry.append(" and pag.usuario_aprobador  iLIKE :usuarioAprobador ");
			}
			if (wp.getFechaSolicitudDesde() != null) {
				strQry.append(" and pag.fecha_solicitud >=:fechaSolicitudDesde ");
			}
			if (wp.getFechaSolicitudHasta() != null) {
				strQry.append(" and pag.fecha_solicitud <=:fechaSolicitudHasta ");
			}
			if (StringUtils.isNotBlank(wp.getNumeroOperacion())) {
				strQry.append(" and pag.numero_operacion  iLIKE :numeroOperacion ");
			}
			if (wp.getFechaCompromisoAnteriorDesde() != null) {
				strQry.append(" and pag.fecha_compromiso_pago_anterior >=:fechaCompromisoAnteriorDesde ");
			}
			if (wp.getFechaCompromisoAnteriorHasta() != null) {
				strQry.append(" and pag.fecha_compromiso_pago_anterior <=:fechaCompromisoAnteriorHasta ");
			}

			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				strQry.append(" and pag.nombre_cliente  iLIKE :nombreCliente ");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				strQry.append(" and pag.identificacion_cliente  iLIKE :identificacionCliente ");
			}
			if (StringUtils.isNotBlank(wp.getProceso())) {
				strQry.append(" and pro.proceso  = :proceso ");
			}else {
				strQry.append(" and pro.proceso in ('CAMBIO_COMPROMISO_PAGO','COMPROMISO_PAGO') ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			
			if (StringUtils.isNotBlank(wp.getCodigo())) {
				query.setParameter("codigo", "%"+ wp.getCodigo()+"%");
			}
			if (StringUtils.isNotBlank(wp.getCodigoOperacion())) {
				query.setParameter("codigoOperacion", "%"+ wp.getCodigoOperacion()+"%");
			}
			if (StringUtils.isNotBlank(wp.getTipoCompromiso())) {
				query.setParameter("tipoCompromiso",  wp.getTipoCompromiso());
			}
			if (StringUtils.isNotBlank(wp.getEstadoCompromiso())) {
				query.setParameter("estadoCompromiso",  wp.getEstadoCompromiso());
			}
			if (wp.getFechaCompromisoDesde() != null) {
				query.setParameter("fechaCompromisoDesde", wp.getFechaCompromisoDesde());
			}
			if (wp.getFechaCompromisoHasta() != null) {
				query.setParameter("fechaCompromisoHasta", wp.getFechaCompromisoHasta());
			}
			if (StringUtils.isNotBlank(wp.getUsuarioSolicitud())) {
				query.setParameter("usuarioSolicitud", "%"+ wp.getUsuarioSolicitud()+"%");
			}
			if (StringUtils.isNotBlank(wp.getUsuarioAprobador())) {
				query.setParameter("usuarioAprobador", "%"+ wp.getUsuarioAprobador()+"%");
			}
			if (wp.getFechaSolicitudDesde() != null) {
				query.setParameter("fechaSolicitudDesde", wp.getFechaSolicitudDesde());
			}
			if (wp.getFechaSolicitudHasta() != null) {
				query.setParameter("fechaSolicitudHasta", wp.getFechaSolicitudHasta());
			}
			if (StringUtils.isNotBlank(wp.getNumeroOperacion())) {
				query.setParameter("numeroOperacion", "%"+ wp.getNumeroOperacion()+"%");
			}
			if (wp.getFechaCompromisoAnteriorDesde() != null) {
				query.setParameter("fechaCompromisoAnteriorDesde", wp.getFechaCompromisoAnteriorDesde());
			}
			if (wp.getFechaCompromisoAnteriorHasta() != null) {
				query.setParameter("fechaCompromisoAnteriorHasta", wp.getFechaCompromisoAnteriorHasta());
			}
			if (StringUtils.isNotBlank(wp.getNombreCliente())) {
				query.setParameter("nombreCliente", "%"+ wp.getNombreCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getIdentificacionCliente())) {
				query.setParameter("identificacionCliente", "%"+ wp.getIdentificacionCliente()+"%");
			}
			if (StringUtils.isNotBlank(wp.getProceso())) {
				query.setParameter("proceso", wp.getProceso());
			}

		
			return ((BigInteger) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}
}
