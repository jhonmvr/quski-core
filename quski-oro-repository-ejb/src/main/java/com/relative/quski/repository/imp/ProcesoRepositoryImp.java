package com.relative.quski.repository.imp;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.ProcesoByAsesorSpec;
import com.relative.quski.repository.spec.ProcesoByIdNegociacion;
import com.relative.quski.repository.spec.ProcesoByIdSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "procesoRepository")
public class ProcesoRepositoryImp extends GeneralRepositoryImp<Long, TbQoProceso> implements ProcesoRepository {
	@Inject
	Logger log;
	
	@Override
	public TbQoProceso findById(Long id) throws RelativeException {
		try {
			List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByIdSpec(id));
			if (!list.isEmpty()) {
				if (list.size() == 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);
				}
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}

	@Override
	public List<TbQoProceso> findProcesosByAsesor(String asesor) throws RelativeException {
		List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByAsesorSpec(asesor));
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperacionesWrapper> findOperacion( BusquedaOperacionesWrapper wp ) throws RelativeException {
		try {
			String queryS = "select case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION' )  then " + 
							"			(select nego.ID from tb_qo_negociacion nego where nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.ID from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else 0 end end ID, " + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION' )  then " + 
							"			(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else ' ' end end CODIGO_OPERACION,  " + 
							"		case when ( proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION' ) then " + 
							"			(select cli.nombre_completo from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id )" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select DEVO.NOMBRE_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia )" + 
							"				else ' ' end end nombre_cliente, " + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id )" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia )" + 
							"				else ' ' end end cedula_cliente," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select cre.MONTO_PREAPROBADO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
							"			else 0 end MONTO_PREAPROBADO," + 
							"		proceso.FECHA_CREACION," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select cre.ID_AGENCIA from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.ID_AGENCIA from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else 0 end end AGENCIA," + 
							"		proceso.ESTADO_PROCESO," + 
							"		proceso.PROCESO," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else ' ' end  end ASESOR," + 
							"		proceso.USUARIO," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select tra.ACTIVIDAD from TB_QO_TRACKING tra, tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.CODIGO = tra.CODIGO_BPM and cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia ORDER BY tra.FECHA_INICIO DESC limit 1)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select tra.ACTIVIDAD from TB_QO_DEVOLUCION devo, TB_QO_TRACKING tra where devo.id = proceso.id_referencia and devo.CODIGO = tra.CODIGO_BPM ORDER BY tra.FECHA_INICIO DESC limit 1)" + 
							"				else ' ' end  end ACTIVIDAD " + 
							"from tb_qo_proceso proceso ";

			StringBuilder strQry = new StringBuilder( queryS )
				.append(" where case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
						"			(select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia)" + 
						"			else case when (proceso.proceso ='DEVOLUCION') then \n" + 
						"				(select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
						"				else '' end  end =:asesor ");
				log.info("================> WHERE: ASESOR ==============> "+ wp.getAsesor() +" <=====");
			if (wp.getEstado() != null) {
				strQry.append(" and proceso.ESTADO_PROCESO =:estado ");
			}
			if (wp.getProceso() != null) {
				strQry.append(" and proceso.PROCESO =:proceso ");
			}	
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and proceso.FECHA_CREACION >=:desde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and PROCESO.FECHA_CREACION <=:hasta ");
			}
			if(wp.getActividad() != null) {
				log.info("================> WHERE: ACTIVIDAD ==> "+ wp.getActividad() +" <====");
				strQry.append(" and case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
						"				(select tra.ACTIVIDAD from TB_QO_TRACKING tra, tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.CODIGO = tra.CODIGO_BPM and cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia ORDER BY tra.FECHA_INICIO DESC limit 1) " + 
						"				else case when (proceso.proceso ='DEVOLUCION') then " + 
						"					(select tra.ACTIVIDAD from TB_QO_DEVOLUCION devo, TB_QO_TRACKING tra where devo.id = proceso.id_referencia and devo.CODIGO = tra.CODIGO_BPM ORDER BY tra.FECHA_INICIO DESC limit 1) " + 
						"					else ' ' end end =:actividad ");
			}
			if(wp.getNombreCompleto() != null) {
				log.info("================> WHERE: NOMBRE ==> "+ wp.getNombreCompleto() +" <====");
				strQry.append(" and case when ( proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION' ) then " + 
						"				(select cli.nombre_completo from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id ) " + 
						"				else case when (proceso.proceso ='DEVOLUCION') then " + 
						"					(select DEVO.NOMBRE_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia ) " + 
						"					else '' end end LIKE :nombre ");
			}
			if(wp.getIdentificacion() != null) {
				log.info("================> WHERE: CEDULA ==> "+ wp.getIdentificacion() +" <====");
				strQry.append(" and case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
						"			(select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id ) " + 
						"			else case when (proceso.proceso ='DEVOLUCION') then " + 
						"				(select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia ) " + 
						"				else ' ' end end =:cedula ");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			
			query.setParameter("asesor", wp.getAsesor() );

			if (wp.getEstado() != null) {
				log.info("=========> SET: ESTADO ==> "+ wp.getEstado() +" <====");
				query.setParameter("estado", wp.getEstado().toString());
			}
			if (wp.getProceso() != null) {
				log.info("=========> SET: PROCESO ==> "+ wp.getProceso() +" <====");
				query.setParameter("proceso", wp.getProceso().toString());
			}	
			if (wp.getFechaCreacionDesde() != null) {
				log.info("=========> SET: DESDE ==> "+ wp.getFechaCreacionDesde() +" <====");
				query.setParameter("desde", wp.getFechaCreacionDesde() );
			}	
			if (wp.getFechaCreacionHasta() != null) {
				log.info("=========> SET: HASTA ==> "+ wp.getFechaCreacionHasta() +" <====");
				query.setParameter("hasta", wp.getFechaCreacionHasta() );
			}
			if(wp.getActividad() != null) {
				log.info("=========> SET: ACTIVIDAD ==> "+ wp.getActividad() +" <====");
				query.setParameter("actividad", wp.getActividad());
			}
			if(wp.getNombreCompleto() != null) {
				log.info("=========> SET: NOMBRE ==> "+ wp.getNombreCompleto() +" <====");
				query.setParameter("nombre", "%"+wp.getNombreCompleto()+"%");
			}
			if(wp.getIdentificacion() != null) {
				log.info("=========> SET: CEDULA ==> "+ wp.getIdentificacion() +" <====");
				query.setParameter("cedula", wp.getIdentificacion());
			}
			return QuskiOroUtil.getResultList(query.getResultList(), OperacionesWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public TbQoProceso findByIdNegociacion(Long id) throws RelativeException {
		try {
			List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByIdNegociacion(id));
			if (!list.isEmpty()) {
				if (list.size() == 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);
				}
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}

	@Override
	public Long countOperaciones(BusquedaOperacionesWrapper wp) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OperacionesWrapper> findOperacionJoinNegociacion(BusquedaOperacionesWrapper wp)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
