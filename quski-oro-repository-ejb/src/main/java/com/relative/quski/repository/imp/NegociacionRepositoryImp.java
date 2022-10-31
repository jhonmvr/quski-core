package com.relative.quski.repository.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.spec.NegociacionByIdSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.ListadoOperacionIdNegociacionWrapper;


@Stateless(mappedName = "negociacionRepository")
public class NegociacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoNegociacion> implements NegociacionRepository {
	@Inject
	Logger log;
	
	@Override
	public TbQoNegociacion findById( Long id ) throws RelativeException{
		try {
			List<TbQoNegociacion> list = this.findAllBySpecification(new NegociacionByIdSpec( id ));
			if (!list.isEmpty()) {
				log.info("ESTOY BUSCANDO UNA NEGOCIACION =====> " + list.size());
				if (list.size() <= 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							"EXISTE MAS DE UNA NEGOCIACION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				return null;
			}
		} catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
	@Override
	public List<ListadoOperacionIdNegociacionWrapper> traerListaOperaciones() throws RelativeException {

		try {
			List<ListadoOperacionIdNegociacionWrapper> list = new ArrayList<>();
			StringBuilder consultaOperaciones = new StringBuilder("select   numero_operacion ,"
					+ " id_negociacion from tb_qo_credito_negociacion "
					+ "where id_negociacion in ((select distinct cast ( id_referencia as integer) from  tb_qo_documento_habilitante tqdh where proceso in('NOVACION','FUNDA', 'NUEVO')))"
					+ " and numero_operacion  is not null");

			Query q = this.getEntityManager().createNativeQuery(consultaOperaciones.toString());

			list = QuskiOroUtil.getResultList(q.getResultList(), ListadoOperacionIdNegociacionWrapper.class);

			if (list != null && !list.isEmpty()) {
				return list;
			}
			return null;

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "indefinido" + e.getMessage());
		}
	}
	

}
