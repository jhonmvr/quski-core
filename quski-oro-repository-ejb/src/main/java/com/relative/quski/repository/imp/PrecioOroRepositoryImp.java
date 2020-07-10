package com.relative.quski.repository.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.repository.PrecioOroRepository;
import com.relative.quski.repository.spec.DetalleCotizacionByIdSpec;
import com.relative.quski.repository.spec.PrecioOroByIdCotizacionSpec;
import com.relative.quski.repository.spec.PrecioOroByIdCotizadorSpec;
import com.relative.quski.wrapper.PrecioOroWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "precioOroRepository")
public class PrecioOroRepositoryImp extends GeneralRepositoryImp<Long, TbQoPrecioOro> implements PrecioOroRepository {

	@Inject
	Logger log;

	@Override
	public List<TbQoPrecioOro> findByIdCotizador(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String idCotizador) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new PrecioOroByIdCotizacionSpec(idCotizador), startRecord, pageSize,
					sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public List<TbQoPrecioOro> findByIdCotizador(String idCotizador) throws RelativeException {
		try {
			return findAllBySpecification(new PrecioOroByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public Long countByIdCotizador(String idCotizador) throws RelativeException {

		try {
			return countBySpecification(new PrecioOroByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	/**
	 * Método que busca el precio oro por id de cotizador es paginado
	 * 
	 * @author KLEBER GUERRA Relative - Engine
	 */
	public List<TbQoPrecioOro> findByIdCotizacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idCotizador) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new PrecioOroByIdCotizadorSpec(idCotizador), startRecord, pageSize,
					sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	/**
	 * Método que busca el precio oro por id de cotizador sin paginar
	 * 
	 * @author KLEBER GUERRA Relative - Engine
	 */
	public List<TbQoPrecioOro> findByIdCotizacion(Long idCotizador) throws RelativeException {
		try {
			return findAllBySpecification(new PrecioOroByIdCotizadorSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	/**
	 * Método que realiza el conteo de la cotizacón por Id
	 * 
	 * @author KLEBER GUERRA Relative - Engine
	 */
	public Long countfindByIdCotizacion(Long idCotizador) throws RelativeException {

		try {
			return countBySpecification(new PrecioOroByIdCotizadorSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	/**
	 * Realiza la búsqueda del precio oro por Id de cotizador por medio de una
	 * consulta directa a la base //Long id, Long idCotizador, Long idTipoOro,
	 * BigDecimal precio, BigDecimal pesoNetoEstimado, String quilate
	 * 
	 * @author KLÉBER GUERRA Relative - Engine
	 * @param idCotizador
	 * @return List<PrecioOroWrapper>
	 * @throws RelativeException
	 */

	/*
	 * Long id, Long idCotizador, Long idTipoOro, BigDecimal precio, BigDecimal
	 * pesoNetoEstimado, String quilate, EstadoEnum estado
	 */
	@Override
	public List<PrecioOroWrapper> findByIdCotizadorCustom(Long idCotizador) throws RelativeException {
		try {
			StringBuilder queryStr = new StringBuilder("SELECT  NEW com.relative.quski.wrapper.PrecioOroWrapper(");
			queryStr.append("po.id as id, po.tbQoCotizador.id as idCotizador, po.tbQoTipoOro.id as idTipoOro,");
			queryStr.append(
					"po.precio as precio,po.pesoNetoEstimado as pesoNetoEstimado,po.tbQoTipoOro.quilate as quilate,po.estado as estado)");
			queryStr.append(" FROM TbQoPrecioOro AS po ");
			queryStr.append(" where po.tbQoCotizador.id=:idCotizador ");
			log.info("===> query gfenerado " + queryStr.toString());
			TypedQuery<PrecioOroWrapper> query = this.getEntityManager().createQuery(queryStr.toString(),
					PrecioOroWrapper.class);
			query.setParameter("idCotizador", idCotizador);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"Error en la busqueda PrecioOroWrapper findByIdCotizadorCustom " + e.getMessage());
		}
	}

}
