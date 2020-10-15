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
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.ProcesoByAsesorSpec;
import com.relative.quski.repository.spec.ProcesoByIdSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "procesoRepository")
public class ProcesoRepositoryImp extends GeneralRepositoryImp<Long, TbQoProceso> implements ProcesoRepository {

	@Override
	public TbQoProceso findById(Long id) throws RelativeException {
		try {
			List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByIdSpec(id));
			if (!list.isEmpty()) {
				if (list.size() == 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);
				}
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}

	@Override
	public List<TbQoProceso> findProcesosByAsesor(String asesor) throws RelativeException {
		List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByAsesorSpec(asesor));
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public Long countOperaciones(BusquedaOperacionesWrapper wp) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperacionesWrapper> findOperacionJoinNegociacion(BusquedaOperacionesWrapper wp) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
