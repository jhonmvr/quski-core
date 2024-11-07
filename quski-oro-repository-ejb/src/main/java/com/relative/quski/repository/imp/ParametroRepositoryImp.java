package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.spec.ParametroByNombreSpec;
import com.relative.quski.repository.spec.ParametroByNombreTipoOrderedSpec;
import com.relative.quski.repository.spec.ParametroByParamSpec;

@Stateless(mappedName = "parametroRepository")
public class ParametroRepositoryImp extends GeneralRepositoryImp<Long, TbMiParametro> implements ParametroRepository {
	/**
	 * Default constructor.
	 */
	public ParametroRepositoryImp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que busca por nombre de parametros
	 * 
	 * @param
	 * @return nombre de parametro
	 */
	public TbMiParametro findByNombre(String nombre) throws RelativeException {
		try {
			List<TbMiParametro> p = this.findAllBySpecification(new ParametroByNombreSpec(nombre));
			if (p != null && !p.isEmpty()) {
				return p.get(0);
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL BUSCAR PARAMETRO POR NOMBRE");
		}
	}

	/**
	 * Busqueda por nombre y tipo de parametros por like
	 * 
	 * @param nombre  Nombre o parte del nombre
	 * @param tipo    Tipo o parte del tipo
	 * @param ordered Define si se ordena o no
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findByNombreAndTipoOrdered(String nombre, String tipo, Boolean ordered)
			throws RelativeException {
		try {
			List<TbMiParametro> p = this.findAllBySpecification(new ParametroByNombreTipoOrderedSpec(nombre, tipo));
			if (p != null && !p.isEmpty()) {
				if (ordered) {
					p.sort((p1, p2) -> p1.getOrden().compareTo(p2.getOrden()));
				}
				return p;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL LISTAR PARAMETROS POR TIPO Y ORDEN" + e);
		}
	}
	public TbMiParametro findByNombreAndTipo(String nombre, String tipo)
			throws RelativeException {
		try {
			List<TbMiParametro> p = this.findAllBySpecification(new ParametroByNombreTipoOrderedSpec(nombre, tipo));
			if (p != null && !p.isEmpty()) {
				return p.get(0);
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,"AL LISTAR PARAMETROS POR TIPO Y ORDEN" + e);
		}
	}

	public List<TbMiParametro> findByParamPaged(String nombre, String tipo, EstadoEnum estado, String caracteriticaUno,
			String caracteristicaDos, int page, int pageSize, String order, String direction) throws RelativeException {
		List<TbMiParametro> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(
					new ParametroByParamSpec(nombre, tipo, estado, caracteriticaUno, caracteristicaDos), page, pageSize,
					order, direction);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("AL LISTAR PARAMETROS" + e);
		}
		return null;
	}

	public Long countByParamPaged(String nombre, String tipo, EstadoEnum estado, String caracteriticaUno,
			String caracteristicaDos) throws RelativeException {
		return this.countBySpecification(
				new ParametroByParamSpec(nombre, tipo, estado, caracteriticaUno, caracteristicaDos));
	}
}
