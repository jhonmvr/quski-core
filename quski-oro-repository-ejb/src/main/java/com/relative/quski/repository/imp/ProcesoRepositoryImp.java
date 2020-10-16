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
							"				else NULL end end ID, " + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION' )  then " + 
							"			(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else NULL end end CODIGO_OPERACION,  " + 
							"		case when ( proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION' ) then " + 
							"			(select cli.nombre_completo from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id )" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select DEVO.NOMBRE_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia )" + 
							"				else NULL end end nombre_cliente, " + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id )" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia )" + 
							"				else NULL end end cedula_cliente," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select cre.MONTO_PREAPROBADO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
							"			else 0 end MONTO_PREAPROBADO," + 
							"		proceso.FECHA_CREACION," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select cre.ID_AGENCIA from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.ID_AGENCIA from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else NULL end end AGENCIA," + 
							"		proceso.ESTADO_PROCESO," + 
							"		proceso.PROCESO," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
							"				else NULL end  end ASESOR," + 
							"		proceso.USUARIO," + 
							"		case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
							"			(select tra.ACTIVIDAD from TB_QO_TRACKING tra, tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.CODIGO = tra.CODIGO_BPM and cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia ORDER BY tra.FECHA_INICIO DESC limit 1)" + 
							"			else case when (proceso.proceso ='DEVOLUCION') then " + 
							"				(select tra.ACTIVIDAD from TB_QO_DEVOLUCION devo, TB_QO_TRACKING tra where devo.id = proceso.id_referencia and devo.CODIGO = tra.CODIGO_BPM ORDER BY tra.FECHA_INICIO DESC limit 1)" + 
							"				else NULL end  end ACTIVIDAD " + 
							"from tb_qo_proceso proceso ";

			StringBuilder strQry = new StringBuilder( queryS )
				.append(" where proceso.USUARIO =:asesor ");
				log.info("================> WHERE: ASESOR ==============> "+ wp.getAsesor() +" <=====");
			
			if (wp.getEstado() != null) {
				log.info("================> WHERE: ESTADO ==============> "+ wp.getEstado() +" <=====");
				strQry.append(" and proceso.ESTADO_PROCESO =:estado ");
			}
			if (wp.getProceso() != null) {
				log.info("================> WHERE: PROCESO =============> "+ wp.getProceso() +" <====");
				strQry.append(" and proceso.PROCESO =:proceso ");
			}	
			if (wp.getFechaCreacionDesde() != null) {
				log.info("================> WHERE: DESDE ==> "+ wp.getFechaCreacionDesde() +" <====");
				strQry.append(" and proceso.FECHA_CREACION >=:desde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				log.info("================> WHERE: HASTA ==> "+ wp.getFechaCreacionHasta() +" <====");
				strQry.append(" and PROCESO.FECHA_CREACION <=:hasta ");
			}
			if(wp.getActividad() != null) {
				log.info("================> WHERE: ACTIVIDAD ==> "+ wp.getActividad() +" <====");
				strQry.append(" and case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
						"				(select tra.ACTIVIDAD from TB_QO_TRACKING tra, tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.CODIGO = tra.CODIGO_BPM and cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia ORDER BY tra.FECHA_INICIO DESC limit 1) " + 
						"				else case when (proceso.proceso ='DEVOLUCION') then " + 
						"					(select tra.ACTIVIDAD from TB_QO_DEVOLUCION devo, TB_QO_TRACKING tra where devo.id = proceso.id_referencia and devo.CODIGO = tra.CODIGO_BPM ORDER BY tra.FECHA_INICIO DESC limit 1) " + 
						"					else '' end end =:actividad ");
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
						"				else '' end end =:cedula ");
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
				log.info("=========> SET: DESDE ==> "+ QuskiOroUtil.adicionEnDias( wp.getFechaCreacionDesde(), (long) 1) +" <====");
				query.setParameter("desde", QuskiOroUtil.adicionEnDias( wp.getFechaCreacionDesde(), (long) 1) );
			}	
			if (wp.getFechaCreacionHasta() != null) {
				log.info("=========> SET: HASTA ==> "+ QuskiOroUtil.adicionEnDias( wp.getFechaCreacionHasta(), (long) 1) +" <====");
				query.setParameter("hasta", QuskiOroUtil.adicionEnDias( wp.getFechaCreacionHasta(), (long) 1) );
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
//	@SuppressWarnings("unchecked")
//	public List<OperacionesWrapper> findOperacion( BusquedaOperacionesWrapper wp) throws RelativeException {
//		try {
//			log.info("===>>> creación de consulta");
//			StringBuilder strQry = new StringBuilder("SELECT ld.id as id,").append(
//					" coalesce(ld.assigned_user_id, '') as assignedUserId, coalesce(ld.created_by, '') as createdBy, ")
//					.append(" ld.date_entered  as dateEntered, "
//							+ " ld.date_modified as dateModified, ld.deleted as deleted,  ")
//					.append(" coalesce(ld.first_name, '') as firstName, coalesce(ld.modified_user_id, '') as modifiedUserId, "
//							+ " coalesce(ld.phone_home, '') as phoneHome, coalesce(ld.phone_mobile, '') as phoneMobile, ")
//					.append(" coalesce(ld.phone_other, '') as phoneOther, coalesce(ld.phone_work, '') as phoneWork, coalesce(ld.lead_source, '') as leadSource, coalesce(ld.lead_source_description, '') as leadSourceDescription,"
//							+ " coalesce(ld.status, '') as status, coalesce(ldc.cedula_c, '') as cedulaC, ")
//					.append(" coalesce(ldc.estatus_c, '') as estatusC, coalesce(em.email_address, '') as emailAddress, "
//							+ " coalesce(em.email_address_caps, '') as emailAddressCaps, coalesce(emr.email_address_id, '') as emailAddressId, ")
//					.append(" coalesce(emr.bean_module, '') as beanModule ")
//					.append(" FROM leads ld, leads_cstm ldc, email_addresses em, email_addr_bean_rel emr ")
//					.append(" WHERE  ld.id = ldc.id_c AND emr.bean_id = ld.id AND emr.email_address_id = em.id ");
//			
//			if (wp != null) {
//				log.info("===>>> creación de consulta Agregando parametro");
//				strQry.append(" AND ldc.cedula_c =:ced ");
//			}
//			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
//			if (wp != null) {
//				log.info("===>>> creación de consulta Seteando parametro");
//				query.setParameter("ced", wp);
//			}
//			return QuskiOroUtil.getResultList(query.getResultList(), OperacionesWrapper.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RelativeException(Constantes.ERROR_CODE_READ,
//					"ClienteWrapper ERROR CONSULTA DE PROSPECTO IMP " + e.getMessage());
//		}
//	}
}
