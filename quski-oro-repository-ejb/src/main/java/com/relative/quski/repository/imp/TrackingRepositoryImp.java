package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.spec.TrackingByParamsSpec;
import com.relative.quski.wrapper.TrackingWrapper;

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
}
