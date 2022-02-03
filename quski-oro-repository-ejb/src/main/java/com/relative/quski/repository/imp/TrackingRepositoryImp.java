package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.spec.TrackingByParamsSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.TrackingWrapper;
import com.relative.quski.wrapper.TrakingProcesoWrapper;

@Stateless(mappedName = "TrackingRepository")
public class TrackingRepositoryImp extends GeneralRepositoryImp<Long, TbQoTracking> implements TrackingRepository {

	@Override
	public List<TbQoTracking> findByParams(TrackingWrapper wp, int start, Integer size, String sort, String direction)
			throws RelativeException {
		try {
			List<TbQoTracking> tmp;
			tmp = this.findAllBySpecificationPaged(new TrackingByParamsSpec(wp), start, size, sort, direction);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}

			return null;            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL INTENTAR BUSCAR EN TbQoTracking");
		}
	}

	@Override
	public Long countTracking(TrackingWrapper wp) throws RelativeException {
		try {
			return this.countBySpecification(new TrackingByParamsSpec(wp));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL INTENTAR BUSCAR TbQoTracking");
		}
	}

	@Override
	public List<TbQoTracking> findByParams(TrackingWrapper wp) throws RelativeException {
		try {
			List<TbQoTracking> tmp;
			tmp = this.findAllBySpecification(new TrackingByParamsSpec(wp));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}

			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL INTENTAR BUSCAR EN TbQoTracking");
		}
	}

	@Override
	public List<ProcesoEnum> findListProcesos() throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ProcesoEnum> query = cb.createQuery(ProcesoEnum.class);
			Root<TbQoTracking> poll = query.from(TbQoTracking.class);
			query.select(poll.get("proceso")).distinct(true);
			TypedQuery<ProcesoEnum> tq = this.getEntityManager().createQuery(query);
			List<ProcesoEnum> resultList = tq.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL LEER LOS PROCESOS DE TRAKING");
		}
	}

	@Override
	public List<ActividadEnum> findListActividadByProceso(ProcesoEnum proceso) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ActividadEnum> query = cb.createQuery(ActividadEnum.class);
			Root<TbQoTracking> poll = query.from(TbQoTracking.class);
			query.where(cb.equal(poll.get("proceso"), proceso));
			query.select(poll.get("actividad")).distinct(true);
			TypedQuery<ActividadEnum> tq = this.getEntityManager().createQuery(query);
			List<ActividadEnum> resultList = tq.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL LEER LAS ACTIVIDADES DE TRAKING");
		}
	}

	@Override
	public List<SeccionEnum> findListSeccionByActividad(ActividadEnum actividad) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<SeccionEnum> query = cb.createQuery(SeccionEnum.class);
			Root<TbQoTracking> poll = query.from(TbQoTracking.class);
			query.where(cb.equal(poll.get("actividad"), actividad));
			query.select(poll.get("seccion")).distinct(true);
			TypedQuery<SeccionEnum> tq = this.getEntityManager().createQuery(query);
			List<SeccionEnum> resultList = tq.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL LEER LAS ACTIVIDADES DE TRAKING");
		}
	}

	@Override
	public Long countByParamPaged(TrackingWrapper wp) throws RelativeException {
		return this.countBySpecification(new TrackingByParamsSpec(wp));
	}

	@Override
	public TbQoTracking findByParams(String codigoBpm, ProcesoEnum proceso) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<TbQoTracking> query = cb.createQuery(TbQoTracking.class);
			Root<TbQoTracking> poll = query.from(TbQoTracking.class);
			query.where(cb.and(cb.equal(poll.get("codigoBpm"), codigoBpm),cb.equal(poll.get("proceso"), proceso)));
			query.select(poll);
			query.orderBy(cb.desc(poll.get("id")));
			TypedQuery<TbQoTracking> tq = this.getEntityManager().createQuery(query);
			List<TbQoTracking> resultList = tq.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList.get(0);
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL LEER LAS ACTIVIDADES DE TRAKING");
		}
	}

	@Override
	public List<String> getActividad(List<ProcesoEnum> proceso) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<String> query = cb.createQuery(String.class);
			Root<TbQoTracking> poll = query.from(TbQoTracking.class);
			if(proceso != null && !proceso.isEmpty()) {
				query.where(poll.get("proceso").in(proceso));
			}
			query.select(poll.get("actividad")).distinct(true);
			TypedQuery<String> tq = this.getEntityManager().createQuery(query);
			List<String> resultList = tq.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL LEER LAS ACTIVIDADES DE TRAKING");
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TrakingProcesoWrapper> trakingByCodigoBpm(String codigoBpm, Integer startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		try {
			String querySelect = "select codigo_bpm, proceso,coalesce(codigoSoftbank,' ') as codigoSoftbank  , fechacreacion , coalesce(to_char(horainicio, 'HH24:MI:SS'),' ') as horainicio  ,   coalesce(to_char(horafin, 'HH24:MI:SS'),' ') as horafin ,coalesce(tiempotranscurrido,' ') as tiempotranscurrido  ,coalesce(vendedor,' ') as vendedor ,coalesce(aprobador,' ') as aprobador  , coalesce(observacion,' ') as observacion from (" + 
					"select  codigo_bpm , " + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"(select codigo_operacion_softbank   from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm and codigo_operacion_softbank is not null and codigo_operacion_softbank <> '' order by id desc limit 1)  as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"(select fecha_inicio     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id asc limit 1) as horaInicio," + 
					"(select fecha_fin     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as horaFin," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"sum(tiempo_transcurrido)," + 
					"(select nego.asesor  from tb_qo_credito_negociacion cre inner join tb_qo_negociacion nego on cre.id_negociacion  = nego.id where codigo = traking.codigo_bpm limit 1) as vendedor," + 
					"(select nego.aprobador  from tb_qo_credito_negociacion cre inner join tb_qo_negociacion nego on cre.id_negociacion  = nego.id where codigo = traking.codigo_bpm limit 1) as aprobador," + 
					"(select his.observacion from tb_qo_historico_observacion his inner join tb_qo_credito_negociacion cre on cre.id = his.id_credito where cre.codigo = traking.codigo_bpm order by his.id desc limit 1) as observacion " + 
					"from tb_qo_tracking traking " + 
					"where  proceso in ('RENOVACION','NUEVO') " + 
					"group by codigo_bpm " + 
					"union all " + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"(select codigo_operacion_softbank   from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm and codigo_operacion_softbank is not null and codigo_operacion_softbank <> '' order by id desc limit 1)  as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"(select fecha_inicio     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id asc limit 1) as horaInicio," + 
					"(select fecha_fin     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as horaFin," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"sum(tiempo_transcurrido)," + 
					"(select devo.asesor  from tb_qo_devolucion devo where devo.codigo = traking.codigo_bpm limit 1) as vendedor," + 
					"(select devo.aprobador  from tb_qo_devolucion devo where devo.codigo = traking.codigo_bpm limit 1) as aprobador," + 
					"(select devo.observaciones  from tb_qo_devolucion devo where devo.codigo = traking.codigo_bpm limit 1) as observacion " + 
					"from tb_qo_tracking traking " + 
					"where  proceso in ('DEVOLUCION','CANCELACION_DEVOLUCION') " + 
					"group by codigo_bpm " + 
					"union all " + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"' '  as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"(select fecha_inicio     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id asc limit 1) as horaInicio," + 
					"(select fecha_fin     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as horaFin," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"sum(tiempo_transcurrido)," + 
					"(select pago.asesor  from tb_qo_cliente_pago pago where pago.codigo = traking.codigo_bpm limit 1) as vendedor," + 
					"(select pago.aprobador  from tb_qo_cliente_pago pago where pago.codigo = traking.codigo_bpm limit 1) as aprobador," + 
					"(select pago.observacion  from tb_qo_cliente_pago pago where pago.codigo = traking.codigo_bpm limit 1) as observacion " + 
					"from tb_qo_tracking traking " + 
					"where  proceso in ('PAGO') " + 
					"group by codigo_bpm  " + 
					") as TBL  where 1=1 ";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm like :codigo ");
			}
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo", "%"+codigoBpm.trim().toUpperCase()+"%");
			}
			query.setParameter("limite", pageSize );
			
			Long salto =  Long.valueOf(startRecord);
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), TrakingProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public Long trakingByCodigoBpm(String codigoBpm) throws RelativeException {
		try {
			String querySelect ="select count(*) from (" + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"(select codigo_operacion_softbank   from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm and codigo_operacion_softbank is not null and codigo_operacion_softbank <> '' order by id desc limit 1)  as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"(select fecha_inicio     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id asc limit 1) as horaInicio," + 
					"(select fecha_fin     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as horaFin," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"sum(tiempo_transcurrido)," + 
					"(select nego.asesor  from tb_qo_credito_negociacion cre inner join tb_qo_negociacion nego on cre.id_negociacion  = nego.id where codigo = traking.codigo_bpm limit 1) as vendedor," + 
					"(select nego.aprobador  from tb_qo_credito_negociacion cre inner join tb_qo_negociacion nego on cre.id_negociacion  = nego.id where codigo = traking.codigo_bpm limit 1) as aprobador," + 
					"(select his.observacion from tb_qo_historico_observacion his inner join tb_qo_credito_negociacion cre on cre.id = his.id_credito where cre.codigo = traking.codigo_bpm order by his.id desc limit 1) as observacion " + 
					"from tb_qo_tracking traking " + 
					"where  proceso in ('RENOVACION','NUEVO') " + 
					"group by codigo_bpm " + 
					"union all " + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"(select codigo_operacion_softbank   from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm and codigo_operacion_softbank is not null and codigo_operacion_softbank <> '' order by id desc limit 1)  as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"(select fecha_inicio     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id asc limit 1) as horaInicio," + 
					"(select fecha_fin     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as horaFin," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"sum(tiempo_transcurrido)," + 
					"(select devo.asesor  from tb_qo_devolucion devo where devo.codigo = traking.codigo_bpm limit 1) as vendedor," + 
					"(select devo.aprobador  from tb_qo_devolucion devo where devo.codigo = traking.codigo_bpm limit 1) as aprobador," + 
					"(select devo.observaciones  from tb_qo_devolucion devo where devo.codigo = traking.codigo_bpm limit 1) as observacion " + 
					"from tb_qo_tracking traking " + 
					"where  proceso in ('DEVOLUCION','CANCELACION_DEVOLUCION') " + 
					"group by codigo_bpm " + 
					"union all " + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"' '  as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"(select fecha_inicio     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id asc limit 1) as horaInicio," + 
					"(select fecha_fin     from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as horaFin," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"sum(tiempo_transcurrido)," + 
					"(select pago.asesor  from tb_qo_cliente_pago pago where pago.codigo = traking.codigo_bpm limit 1) as vendedor," + 
					"(select pago.aprobador  from tb_qo_cliente_pago pago where pago.codigo = traking.codigo_bpm limit 1) as aprobador," + 
					"(select pago.observacion  from tb_qo_cliente_pago pago where pago.codigo = traking.codigo_bpm limit 1) as observacion " + 
					"from tb_qo_tracking traking " + 
					"where  proceso in ('PAGO') " + 
					"group by codigo_bpm  " + 
					") as TBL  where 1=1 ";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm like :codigo ");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo", "%"+codigoBpm.trim().toUpperCase()+"%");
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrakingProcesoWrapper> trakingActividadByCodigoBpm(String codigoBpm, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		try {
			String querySelect = "select codigo_bpm, proceso, codigoSoftbank, fechaCreacion, actividad, tiempoTranscurrido, usuario from (" + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"coalesce((select codigo_operacion_softbank   from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm and codigo_operacion_softbank is not null and codigo_operacion_softbank <> '' order by id desc limit 1),' ') as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"actividad," + 
					"coalesce(to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS'),' ') as tiempoTranscurrido ," + 
					"coalesce(string_agg (distinct  usuario_creacion ,','),' ') as usuario," + 
					"coalesce((select orden from tb_qo_traking_orden where etiqueta = actividad limit 1),0) as orden " + 
					"from tb_qo_tracking traking " + 
					"group by codigo_bpm , actividad order by orden" + 
					") as tbl where 1=1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo",codigoBpm.trim().toUpperCase());
			}
			query.setParameter("limite", pageSize );
			
			Long salto =  Long.valueOf(startRecord);
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), TrakingProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public Long trakingActividadByCodigoBpm(String codigoBpm) throws RelativeException {
		try {
			String querySelect = "select count(*) from (" + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"coalesce((select codigo_operacion_softbank   from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm and codigo_operacion_softbank is not null and codigo_operacion_softbank <> '' order by id desc limit 1),' ') as codigoSoftbank ," + 
					"(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = traking.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"actividad," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido ," + 
					"coalesce(string_agg (distinct  usuario_creacion ,','),' ') as usuario " + 
					"from tb_qo_tracking traking " + 
					"group by codigo_bpm , actividad" + 
					") as tbl where 1=1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo", codigoBpm.trim().toUpperCase());
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TrakingProcesoWrapper> trakingSeccionByCodigoBpm(String codigoBpm, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		try {
			String querySelect = "select codigo_bpm ,proceso, coalesce(codigo_operacion_softbank,' ') as codigo_softbank, fecha_Creacion, actividad, seccion, usuario_Creacion, coalesce(to_char(fecha_Inicio, 'HH24:MI:SS'),' ') as horainicio ,"
					+ " coalesce(to_char(fecha_fin, 'HH24:MI:SS'),' ') as horaFin, coalesce(to_char(to_timestamp(extract( epoch  from (fecha_fin - fecha_inicio))) at time zone 'utc', 'HH24:MI:SS'),' ') as tiempoTranscurrido ,"
					+ " coalesce(observacion,' ') as observacion from tb_qo_tracking where 1=1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}
			strQry.append(" order by id ");
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo",codigoBpm.trim().toUpperCase());
			}
			query.setParameter("limite", pageSize );
			
			Long salto =  Long.valueOf(startRecord);
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), TrakingProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}


	@Override
	public Long trakingSeccionByCodigoBpm(String codigoBpm) throws RelativeException {
		try {
			String querySelect = "select count(*) from tb_qo_tracking where 1=1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo", codigoBpm.trim().toUpperCase());
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrakingProcesoWrapper> trakingSeccionConsolidadoByCodigoBpm(String codigoBpm, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		try {
			String querySelect = "select * from (" + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"seccion," + 
					"coalesce(to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS'),' ') as tiempoTranscurrido " + 
					"from tb_qo_tracking traking " + 
					"group by codigo_bpm , seccion" + 
					") as tbl where 1=1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo",codigoBpm.trim().toUpperCase());
			}
			query.setParameter("limite", pageSize );
			
			Long salto =  Long.valueOf(startRecord);
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), TrakingProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public Long trakingSeccionConsolidadoByCodigoBpm(String codigoBpm) throws RelativeException {
		try {
			String querySelect = "select count(*) from (" + 
					"select  codigo_bpm ," + 
					"(select proceso from tb_qo_tracking where codigo_bpm = traking.codigo_bpm limit 1 ) as proceso," + 
					"seccion," + 
					"to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS') as tiempoTranscurrido " + 
					"from tb_qo_tracking traking " + 
					"group by codigo_bpm , seccion" + 
					") as tbl where 1=1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo", codigoBpm.trim().toUpperCase());
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrakingProcesoWrapper> trakingAreaByCodigoBpm(String codigoBpm, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		try {
			String querySelect = "select * from (" + 
					"select " + 
					"	codigo_bpm, " + 
					"	(select proceso from tb_qo_tracking where codigo_bpm = tbl.codigo_bpm limit 1 ) as proceso," + 
					"	(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = tbl.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"	coalesce(area,' ') as area," + 
					"	coalesce(to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS'),' ') as tiempoTranscurrido " + 
					"from(" + 
					"	select codigo_bpm, proceso, fecha_creacion, actividad,  (select area from tb_qo_traking_area tra where tra.actividad = traking.actividad limit 1), fecha_fin, fecha_inicio from tb_qo_tracking traking " + 
					") as tbl " + 
					"group by codigo_bpm, area ) as tbll where 1 = 1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo",codigoBpm.trim().toUpperCase());
			}
			query.setParameter("limite", pageSize );
			
			Long salto =  Long.valueOf(startRecord);
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), TrakingProcesoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public Long trakingAreaByCodigoBpm(String codigoBpm) throws RelativeException {
		try {
			String querySelect = "select count(*) from (" + 
					"select " + 
					"	codigo_bpm, " + 
					"	(select proceso from tb_qo_tracking where codigo_bpm = tbl.codigo_bpm limit 1 ) as proceso," + 
					"	(select fecha_creacion    from tb_qo_tracking  where codigo_bpm = tbl.codigo_bpm order by id desc limit 1) as fechaCreacion," + 
					"	coalesce(area,' ') as area," + 
					"	coalesce(to_char(to_timestamp(sum(extract( epoch  from (fecha_fin - fecha_inicio)))) at time zone 'utc', 'HH24:MI:SS'),' ') as tiempoTranscurrido " + 
					"from(" + 
					"	select codigo_bpm, proceso, fecha_creacion, actividad,  (select area from tb_qo_traking_area tra where tra.actividad = traking.actividad limit 1), fecha_fin, fecha_inicio from tb_qo_tracking traking " + 
					") as tbl " + 
					"group by codigo_bpm, area ) as tbll where 1 = 1";
			StringBuilder strQry = new StringBuilder( querySelect );
			if(StringUtils.isNotBlank(codigoBpm) ) {
				strQry.append(" and codigo_bpm = :codigo ");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(StringUtils.isNotBlank(codigoBpm)) {
				query.setParameter("codigo", codigoBpm.trim().toUpperCase());
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
}
