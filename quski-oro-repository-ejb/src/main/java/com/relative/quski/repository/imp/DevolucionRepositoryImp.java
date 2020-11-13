package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.BusquedaDevolucionWrapper;
import com.relative.quski.wrapper.DevolucionProcesoWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "devolucionRepository")
public class DevolucionRepositoryImp extends GeneralRepositoryImp<Long, TbQoDevolucion>
		implements DevolucionRepository {

	@Inject
	Logger log;

	
	@Override
	public List<DevolucionProcesoWrapper> findOperaciones(BusquedaDevolucionWrapper  bdw) throws RelativeException {
			//List<MovimientosDetalleCierreCajaWrapper> listMovimientos = null;
		try {
			String querySelect ="select j.fecha_solicitud,j.agencia_solicitud,j.codigo_operacion_madre"
					+ ", j.codigo_operacion, j.nombre_cliente, j.cedula_cliente, j.funda_madre, j.funda_actual"
					+ "j.ciudad_tevcol, j.fecha_arribo, j.asesor, j,fecha_aprobacion_solicitud "
					+ "from TbQoDevolucion j inner join (select d from TbQoProceso d where d.proceso = 'DEVOLUCION')";
			
			StringBuilder strQry = new StringBuilder( querySelect );
			if (bdw.getCodigoOperacion() != null) {
				strQry.append(" where j.codigo_operacion =:c  ");
			} 
			if(bdw.getAgencia() != null) {
				strQry.append(" and j.agencia_entrega=:agencia ");
			}

			
			if (bdw.getIdentificacion() != null) {
				strQry.append(" and j.cedula_cliente=:identificacion   ");
				}	
			
			if (bdw.getFechaAprobacionDesde() != null) {
				strQry.append(" and j.fecha_aprobacion  >=:desde ");
			}	
			if (bdw.getFechaAprobacionHasta() != null) {
				strQry.append(" and and j.fecha_aprobacion  >=:hasta ");
			}
			if(bdw.getEstado() != null) {
				strQry.append(" and d.estado =:estado ");
			}

			strQry.append(" ORDER BY ORDEN LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (bdw.getCodigoOperacion() != null) {

				query.setParameter("if (wp.getCodigoOperacion() != null)", bdw.getCodigoOperacion() );
				
}
			if (bdw.getEstado() != null) {
				log.info("=========> SET: ESTADO ==> "+ bdw.getEstado() +" <====");
				query.setParameter("estado", bdw.getEstado().toString());
			}
			if (bdw.getIdentificacion() != null) {

				query.setParameter("identificacion", bdw.getIdentificacion().toString());
}	
			if (bdw.getFechaAprobacionDesde() != null) {
				
				query.setParameter("desde", bdw.getFechaAprobacionDesde() );
			}	
			if (bdw.getFechaAprobacionHasta() != null) {
			
				query.setParameter("hasta", bdw.getFechaAprobacionDesde() );
			}
			if(bdw.getAgencia() != null) {
				
				query.setParameter("agencia", bdw.getAgencia());
			}


			query.setParameter("limite", bdw.getNumberItems() );
			Long salto = bdw.getNumberItems() * (bdw.getNumberPage());
			query.setParameter("salto", salto );
			
			return QuskiOroUtil.getResultList(query.getResultList(), DevolucionProcesoWrapper.class) ;
		}catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS, "ERROR AL consultar " + e);
		}
		
		
	}
	
	public Integer countOperaciones(BusquedaDevolucionWrapper  bdw) throws RelativeException {
		//List<MovimientosDetalleCierreCajaWrapper> listMovimientos = null;
	try {
		String querySelect ="select j.fecha_solicitud,j.agencia_solicitud,j.codigo_operacion_madre"
				+ ", j.codigo_operacion, j.nombre_cliente, j.cedula_cliente, j.funda_madre, j.funda_actual"
				+ "j.ciudad_tevcol, j.fecha_arribo, j.asesor, j,fecha_aprobacion_solicitud "
				+ "from TbQoDevolucion j inner join (select d from TbQoProceso d where d.proceso = 'DEVOLUCION')";
		
		StringBuilder strQry = new StringBuilder( querySelect );
		if (bdw.getCodigoOperacion() != null) {
			strQry.append(" where j.codigo_operacion =:c  ");
		} 
		if(bdw.getAgencia() != null) {
			strQry.append(" and j.agencia_entrega=:agencia ");
		}

		
		if (bdw.getIdentificacion() != null) {
			strQry.append(" and j.cedula_cliente=:identificacion   ");
			}	
		
		if (bdw.getFechaAprobacionDesde() != null) {
			strQry.append(" and j.fecha_aprobacion  >=:desde ");
		}	
		if (bdw.getFechaAprobacionHasta() != null) {
			strQry.append(" and and j.fecha_aprobacion  >=:hasta ");
		}
		if(bdw.getEstado() != null) {
			strQry.append(" and d.estado =:estado ");
		}

		strQry.append(" ORDER BY ORDEN LIMIT :limite OFFSET :salto ");
		Query query = this.getEntityManager().createNativeQuery(strQry.toString());

		if (bdw.getCodigoOperacion() != null) {

			query.setParameter("if (wp.getCodigoOperacion() != null)", bdw.getCodigoOperacion() );
			
}
		if (bdw.getEstado() != null) {
			log.info("=========> SET: ESTADO ==> "+ bdw.getEstado() +" <====");
			query.setParameter("estado", bdw.getEstado().toString());
		}
		if (bdw.getIdentificacion() != null) {

			query.setParameter("identificacion", bdw.getIdentificacion().toString());
}	
		if (bdw.getFechaAprobacionDesde() != null) {
			
			query.setParameter("desde", bdw.getFechaAprobacionDesde() );
		}	
		if (bdw.getFechaAprobacionHasta() != null) {
		
			query.setParameter("hasta", bdw.getFechaAprobacionDesde() );
		}
		if(bdw.getAgencia() != null) {
			
			query.setParameter("agencia", bdw.getAgencia());
		}


		query.setParameter("limite", bdw.getNumberItems() );
		Long salto = bdw.getNumberItems() * (bdw.getNumberPage());
		query.setParameter("salto", salto );
		
		return ((BigInteger) query.getSingleResult()).intValue();
		
	}catch (Exception e) {
		e.getStackTrace();
		
		throw new RelativeException(QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS, "ERROR AL consultar " + e);
	}
	
	
}


	
}
