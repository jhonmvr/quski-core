package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoCatalogo;
import com.relative.quski.repository.CatalogoRepository;
import com.relative.quski.repository.spec.CatalogoByNombreSpec;
import com.relative.quski.repository.spec.CatalogoByTipoSpec;
@Stateless(mappedName = "catalogoRepository")
public class CatalogoRepositoryImp extends GeneralRepositoryImp<Long, TbQoCatalogo> implements CatalogoRepository{

	@Override
	public List<TbQoCatalogo> findByNombreCatalogo(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String nombre) throws RelativeException {
	
		try {
			return findAllBySpecificationPaged(
					new CatalogoByNombreSpec(nombre), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error al buscar catalogo");
		

	}
	}

	@Override
	public List<TbQoCatalogo> findByNombreCatalogo(String nombre) throws RelativeException {
		try {
			return findAllBySpecification(
					new CatalogoByNombreSpec(nombre));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR precios de oro por cotizador");
		}
	}

	@Override
	public Long countByNombreCatalogo(String nombre) throws RelativeException {
		try {
			return countBySpecification(
					new CatalogoByNombreSpec(nombre));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR catalogo");
		}
	}

	@Override
	public List<TbQoCatalogo> findByTipoCatalogo(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String tipo) throws RelativeException {
		try {
			return findAllBySpecificationPaged(
					new CatalogoByTipoSpec(tipo), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error al buscar catalogo");
		

	}
	
	}

	@Override
	public List<TbQoCatalogo> findByTipoCatalogo(String tipo) throws RelativeException {
		try {
			return findAllBySpecification(
					new CatalogoByTipoSpec(tipo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR tipo en catalogo");
		}
	}

	@Override
	public Long countByTipoCatalogo(String tipo) throws RelativeException {
		try {
			return countBySpecification(
					new CatalogoByTipoSpec(tipo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "AL BUSCAR catalogo");
		}
	}
	
	

	
}
