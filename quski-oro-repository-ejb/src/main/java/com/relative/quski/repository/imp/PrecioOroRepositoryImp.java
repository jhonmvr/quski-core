package com.relative.quski.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.repository.PrecioOroRepository;
import com.relative.quski.repository.spec.PrecioOroByIdCotizacionSpec;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "precioOroRepository")
public class PrecioOroRepositoryImp extends GeneralRepositoryImp<Long, TbQoPrecioOro> implements PrecioOroRepository {

	@Override
	public List<TbQoPrecioOro> findByIdCotizador(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String idCotizador)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(
					new PrecioOroByIdCotizacionSpec(idCotizador), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}

	}

	@Override
	public List<TbQoPrecioOro> findByIdCotizador(
			String idCotizador) throws RelativeException {
		try {
			return findAllBySpecification(
					new PrecioOroByIdCotizacionSpec( idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}


	@Override
	public Long countByIdCotizador(String idCotizador)
			throws RelativeException {

		try {
			return countBySpecification(
					new PrecioOroByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	
}
