package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.VariableCrediticiaRepository;
import com.relative.quski.repository.spec.PrecioOroByIdCotizacionSpec;
import com.relative.quski.repository.spec.VariablesCrediticiasByIdCotizacionSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "variableCrediticiaRepository")
public class VariableCrediticiaRepositoryImp extends GeneralRepositoryImp<Long, TbQoVariablesCrediticia> implements VariableCrediticiaRepository		 {

	@Override
	public List<TbQoVariablesCrediticia> findByIdCotizacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idCotizador)
					throws RelativeException {
				try {
					return findAllBySpecificationPaged(
							new VariablesCrediticiasByIdCotizacionSpec(idCotizador), startRecord,
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
			return findAllBySpecification(
					new VariablesCrediticiasByIdCotizacionSpec( idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}
	@Override
	public Long countByIdCotizacion(Long idCotizador) throws RelativeException {

		try {
			return countBySpecification(
					new VariablesCrediticiasByIdCotizacionSpec(idCotizador));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	

	}
}