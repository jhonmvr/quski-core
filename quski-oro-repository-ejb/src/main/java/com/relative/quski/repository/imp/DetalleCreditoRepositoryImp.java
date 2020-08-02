package com.relative.quski.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.repository.DetalleCreditoRepository;
import com.relative.quski.repository.spec.DetalleCreditoByIdCotizadorSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "detalleCreditoRepository")
public class DetalleCreditoRepositoryImp extends GeneralRepositoryImp<Long, TbQoDetalleCredito> implements DetalleCreditoRepository{
	String mensaje = "CAPTURA EXCEPCION NO IDENTIFICADA EN BUSQUEDA DE DETALLE DE CREDITO (IMP)";
	
	@Override
	public List<TbQoDetalleCredito> findDetalleCreditoByIdCotizador(Long idCotizador) throws RelativeException {
		try {
			return this.findAllBySpecification(new DetalleCreditoByIdCotizadorSpec( idCotizador ));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, this.mensaje);
		}
	}
	@Override
	public List<TbQoDetalleCredito> findDetalleCreditoByIdCotizador(Long idCotizador, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged( new DetalleCreditoByIdCotizadorSpec( idCotizador ), startRecord, pageSize,
					sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, this.mensaje);
		}
	}
	@Override
	public Long countByIdCotizador(Long idCotizador) throws RelativeException {
		try {
			return countBySpecification(new DetalleCreditoByIdCotizadorSpec(idCotizador));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, this.mensaje);
		}
	}

}
