package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoOperacionEnum;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoReasignacionActividad;
import com.relative.quski.repository.ReasignacionRepository;
import com.relative.quski.repository.spec.ReasignacionByCodigoAndEstadoParamSpec;
import com.relative.quski.repository.spec.ReasignacionByCodigoParamSpec;
@Stateless(mappedName = "reasignacionRepository")
public class ReasignacionRepositoryImp extends GeneralRepositoryImp<Long, TbQoReasignacionActividad> implements ReasignacionRepository {

	public ReasignacionRepositoryImp() {
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TbQoReasignacionActividad> findBycodigoReasignacion(String id, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {List<TbQoReasignacionActividad> tmp;
			try {
				tmp = this.findAllBySpecification(new ReasignacionByCodigoParamSpec(id));
				if (tmp != null && !tmp.isEmpty()) {
					return tmp;
				}
			} catch (Exception e) {
				// log.info("NO EXISTE REGISTROS BUSCADOS POR EL PARAMETRO " +e);
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"ERROR: NO EXISTE INFORMACION DE REASIGNACION POR ID " + id);

			}
			return null;
	}

	@Override
	public Long countByParamPaged(String id) throws RelativeException {
		try {
			return this.countBySpecification(new ReasignacionByCodigoParamSpec(id));

		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA EL PARAMETRO " +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION DE REASIGNACION POR ID" + id);

		}
	}



 

	
}
