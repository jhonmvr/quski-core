package com.relative.quski.repository.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoFunda;
import com.relative.quski.repository.FundaRepository;
import com.relative.quski.repository.spec.FundaByParamsSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "fundaRepository")
public class FundaRepositoryImp extends GeneralRepositoryImp<Long, TbQoFunda> implements FundaRepository {
	@Inject
	Logger log;

	@Override
	public List<TbQoFunda> findPorCustomFilterFundas(PaginatedWrapper pw, String codigo, BigDecimal peso,
			EstadoEnum estado) throws RelativeException {
		
		try {
			return this.findAllBySpecificationPaged(
					new FundaByParamsSpec(codigo, peso, estado),
					pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al listar creditos " + e.getMessage());
		}
	}

	@Override
	public Long countByParams(String codigo, BigDecimal peso, EstadoEnum estado) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new FundaByParamsSpec(codigo, peso, estado));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error no se encontro la funda:");
		}
		return null;
	}

	@Override
	public TbQoFunda reservarFunda(BigDecimal peso) throws RelativeException {
		TbQoFunda persisted = new TbQoFunda();
		List<TbQoFunda> listFunda = null;
		try {
			StringBuilder strQry = new StringBuilder(
					"select f from TbQoFunda f where f.estado =:estado "
					+ "and f.peso =:peso order by f.id asc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("estado", EstadoEnum.CREADA);
			q.setParameter("peso", peso);
			listFunda = q.getResultList();
			if (listFunda != null && !listFunda.isEmpty()) {
				persisted = listFunda.get(0);
				persisted.setEstado(EstadoEnum.RESERVADA);
				log.info(" xd" );
				return this.update(persisted);
				
			}
			return null;
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL RESERVAR FUNDA " + e);
		}
	}
	
	
	
	
}
