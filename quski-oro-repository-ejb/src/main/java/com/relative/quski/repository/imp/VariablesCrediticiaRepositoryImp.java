package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.VariablesCrediticiaRepository;
import com.relative.quski.repository.spec.VariablesCrediticiasByIdCotizacionSpec;
import com.relative.quski.repository.spec.VariablesCrediticiasByIdNegociacionSpec;
import com.relative.quski.wrapper.VariableCrediticiaWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "variablesCrediticiaRepository")
public class VariablesCrediticiaRepositoryImp extends GeneralRepositoryImp<Long, TbQoVariablesCrediticia>
		implements VariablesCrediticiaRepository {
	@Inject
	Logger log;

	@Override
	public List<TbQoVariablesCrediticia> findByIdCotizacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idCotizador) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new VariablesCrediticiasByIdCotizacionSpec(idCotizador), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}

	}

	@Override
	public List<TbQoVariablesCrediticia> findByIdCotizacion(Long idCotizador) throws RelativeException {
		try {
			return findAllBySpecification(new VariablesCrediticiasByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public Long countByIdCotizacion(Long idCotizador) throws RelativeException {

		try {
			return countBySpecification(new VariablesCrediticiasByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}

	}

	/**
	 * Realiza la búsqueda de la variable crediticia por Id de cotizador mediante
	 * una consulta directa a la base
	 * 
	 * @author KLÉBER GUERRA Relative - Engine
	 * @param idCotizador
	 * @return List<VariableCrediticiaWrapper>
	 * @throws RelativeException
	 */
	@Override
	public List<VariableCrediticiaWrapper> findByIdCotizadorCustom(Long idCotizador) throws RelativeException {
		try {
			StringBuilder queryStr = new StringBuilder(
					"SELECT  NEW com.relative.quski.wrapper.VariableCrediticiaWrapper(");
			queryStr.append(
					"vc.id as id, vc.tbQoCotizador.id as idCotizador, vc.orden as orden,vc.nombre as nombre,vc.valor as valor)");
			queryStr.append(" FROM TbQoVariablesCrediticia AS vc ");
			queryStr.append(" where vc.tbQoCotizador.id=:idCotizador ");
			log.info("===> query gfenerado " + queryStr.toString());
			TypedQuery<VariableCrediticiaWrapper> query = this.getEntityManager().createQuery(queryStr.toString(),
					VariableCrediticiaWrapper.class);
			query.setParameter("idCotizador", idCotizador);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"Error en la busqueda findByIdCotizador " + e.getMessage());
		}
	}

	@Override
	public List<TbQoVariablesCrediticia> findByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			return findAllBySpecification(new VariablesCrediticiasByIdNegociacionSpec( idNegociacion ));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
}