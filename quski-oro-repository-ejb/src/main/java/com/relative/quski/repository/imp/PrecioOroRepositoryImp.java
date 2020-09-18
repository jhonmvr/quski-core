package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.repository.PrecioOroRepository;
import com.relative.quski.repository.spec.PrecioOroByCedulaSpec;
import com.relative.quski.repository.spec.PrecioOroByIdCotizadorSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "precioOroRepository")
public class PrecioOroRepositoryImp extends GeneralRepositoryImp<Long, TbQoPrecioOro> implements PrecioOroRepository {

	@Inject
	Logger log;
	String mensaje = "PROBLEMA EN IMP. DE PRECIOS ORO";

	@Override
	public List<TbQoPrecioOro> findByCedula(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String cedula) throws RelativeException {
		try {
			List<TbQoPrecioOro> listPrecios = this.findAllBySpecificationPaged( new PrecioOroByCedulaSpec(cedula), startRecord, pageSize,
					sortFields, sortDirections);
			if (!listPrecios.isEmpty()) {
				return listPrecios;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, this.mensaje);
		}
	}

	@Override
	public List<TbQoPrecioOro> findByCedula(String cedula) throws RelativeException {
		try {
			List<TbQoPrecioOro> listPrecios = this.findAllBySpecification(new PrecioOroByCedulaSpec(cedula));
			if (!listPrecios.isEmpty()) {
				return listPrecios;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, this.mensaje);
		}
	}

	@Override
	public Long countByCedula(String cedula) throws RelativeException {

		try {
			return countBySpecification(new PrecioOroByCedulaSpec(cedula));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, this.mensaje);
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
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
}
