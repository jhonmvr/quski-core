package com.relative.quski.repository.imp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.model.TbQoVerificacionTelefonica;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.CreditoByListIdsAndAprobadoresSpec;
import com.relative.quski.repository.spec.CreditoNegociacionSpec;
import com.relative.quski.repository.spec.DevolucionByListIdsAndAprobadoresSpec;
import com.relative.quski.repository.spec.PagoByListIdsAndAprobadoresSpec;
import com.relative.quski.repository.spec.ProcesoByAsesorSpec;
import com.relative.quski.repository.spec.ProcesoByIdNegociacion;
import com.relative.quski.repository.spec.ProcesoByIdNovacion;
import com.relative.quski.repository.spec.ProcesoByIdReferenciaSpec;
import com.relative.quski.repository.spec.ProcesoByIdSpec;
import com.relative.quski.repository.spec.ProcesoByProcesosEstadosAndTimeDiferenceSpec;
import com.relative.quski.repository.spec.VerificacionByListIdsAndAprobadoresSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.CabeceraWrapper;
import com.relative.quski.wrapper.OpPorAprobarWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;
import com.relative.quski.wrapper.ProcesoCaducadoWrapper;
import com.relative.quski.wrapper.ProcesoDevoActivoWrapper;

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
			if (list != null && !list.isEmpty()) {
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
	public TbQoProceso findByIdReferencia(Long id, ProcesoEnum proceso) throws RelativeException {
		
			List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByIdReferenciaSpec(id, proceso));
			if (list != null && !list.isEmpty()) {
				if (list.size() == 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							QuskiOroConstantes.ERROR_MAS_DE_UN_REGISTRO);
				}
			} else {
				return null;
			}
		
	}

	@Override
	public List<TbQoProceso> findProcesosByAsesor(String asesor) throws RelativeException {
		List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByAsesorSpec(asesor));
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public Long countOperacion(BusquedaOperacionesWrapper wp) throws RelativeException {
		try {
			String querySelect = "select count(*) from (select cre.id_negociacion as id , cre.codigo, coalesce(cre.numero_operacion,' ') as operacion,coalesce(cre.numero_operacion_anterior,' ') as operacion_ant, cli.nombre_completo, cli.cedula_cliente, coalesce(cre.monto_financiado,0) as monto, proceso.FECHA_CREACION, cre.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, nego.asesor, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD'  as actividad   " + 
					"	from tb_qo_credito_negociacion cre inner join  tb_qo_negociacion nego on nego.id=cre.id_negociacion inner join tb_qo_cliente cli on cli.id = nego.id_cliente inner join tb_qo_proceso proceso on proceso.id_referencia = cre.id_negociacion and (cre.numero_operacion_madre is null and proceso = 'NUEVO' or cre.numero_operacion_madre is not null and proceso = 'RENOVACION')  " + 
					"union  " + 
					"select devo.id,devo.codigo,coalesce(devo.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, DEVO.NOMBRE_CLIENTE, devo.CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, devo.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, devo.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad  " + 
					"	from tb_qo_devolucion devo inner join tb_qo_proceso proceso on proceso.id_referencia = devo.id and proceso.proceso in('DEVOLUCION','CANCELACION_DEVOLUCION')  " + 
					"union  " + 
					"select pago.id,pago.codigo,coalesce(pago.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, pago.NOMBRE_CLIENTE, pago.CEDULA as CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, pago.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, pago.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad  " + 
					"	from tb_qo_cliente_pago pago inner join tb_qo_proceso proceso on proceso.id_referencia = pago.id and proceso.proceso ='PAGO'  " + 
					"union  " + 
					"select veri.id,veri.codigo,coalesce(veri.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, veri.NOMBRE_CLIENTE, veri.CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, veri.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, veri.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad  " + 
					"	from TB_QO_VERIFICACION_TELEFONICA veri inner join tb_qo_proceso proceso on proceso.id_referencia = veri.id and proceso.proceso ='VERIFICACION_TELEFONICA') as TABL where 1=1 ";
			StringBuilder strQry = new StringBuilder( querySelect );
		
			
			if (wp.getEstado() == null || wp.getEstado().isEmpty()) {
				strQry.append(" and (ESTADO_PROCESO != 'CANCELADO' and  ESTADO_PROCESO != 'APROBADO' and ESTADO_PROCESO != 'RECHAZADO'  and ESTADO_PROCESO != 'CADUCADO') ");
			} else {
				String st = wp.getEstado().stream().map(EstadoProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and ESTADO_PROCESO in ('"+st+"') ");
			}
			if(wp.getAsesor() != null && !wp.getAsesor().isEmpty() ) {
				strQry.append(" and asesor in :asesor");
			}
			if(wp.getAgencia() != null && !wp.getAgencia().isEmpty() ) {
				strQry.append(" and ID_AGENCIA in :agencia");
			}
			if (wp.getProceso() != null && !wp.getProceso().isEmpty()) {
				String st = wp.getProceso().stream().map(ProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and PROCESO in ('"+st+"') ");
			}else {
				strQry.append(" and PROCESO not in('DEVOLUCION','CANCELACION_DEVOLUCION') ");
			}	
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and FECHA_CREACION >=:desde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and FECHA_CREACION <=:hasta ");
			}
			if(StringUtils.isNotBlank(wp.getActividad()) ) {
				strQry.append(" and actividad =:actividad ");
			}
			if(StringUtils.isNotBlank(wp.getNombreCompleto() )) {
				strQry.append(" and nombre_completo iLIKE :nombre ");
			}
			if(StringUtils.isNotBlank(wp.getIdentificacion())) {
				strQry.append(" and CEDULA_CLIENTE =:cedula ");
			}
			if(StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and codigo iLIKE :codigoBpm ");
			}
			if(StringUtils.isNotBlank(wp.getCodigoSoft() ) ) {
				strQry.append(" and (operacion iLIKE :codigosoft or operacion_ant iLIKE :codigosoft)");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			
			if(wp.getAsesor() != null && !wp.getAsesor().isEmpty() ) {
				query.setParameter("asesor", wp.getAsesor() );
			}
			if(wp.getAgencia() != null && !wp.getAgencia().isEmpty() ) {
				query.setParameter("agencia", wp.getAgencia() );
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("desde", wp.getFechaCreacionDesde() );
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("hasta", wp.getFechaCreacionHasta() );
			}
			if(StringUtils.isNotBlank(wp.getActividad() )) {
				query.setParameter("actividad", wp.getActividad());
			}
			if(StringUtils.isNotBlank(wp.getNombreCompleto())) {
				query.setParameter("nombre", "%"+wp.getNombreCompleto().trim()+"%");
			}
			if(StringUtils.isNotBlank(wp.getIdentificacion())) {
				query.setParameter("cedula", wp.getIdentificacion());
			}
			if(StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+wp.getCodigoBpm().trim()+"%");
			}			
			if(StringUtils.isNotBlank(wp.getCodigoSoft() )) {
				query.setParameter("codigosoft", "%"+wp.getCodigoSoft().trim()+"%");
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OperacionesWrapper> findOperacion( BusquedaOperacionesWrapper wp ) throws RelativeException {
		try {
			String querySelect = "select * from (select cre.id_negociacion as id , cre.codigo, coalesce(cre.numero_operacion,' ') as operacion,coalesce(cre.numero_operacion_anterior,' ') as operacion_ant, cli.nombre_completo, cli.cedula_cliente, coalesce(cre.monto_financiado,0) as monto, proceso.FECHA_CREACION, cre.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, nego.asesor, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad, coalesce(cre.codigo_devuelto,' ')  as motivo   " + 
					"	from tb_qo_credito_negociacion cre inner join  tb_qo_negociacion nego on nego.id=cre.id_negociacion inner join tb_qo_cliente cli on cli.id = nego.id_cliente inner join tb_qo_proceso proceso on proceso.id_referencia = cre.id_negociacion and (cre.numero_operacion_madre is null and proceso = 'NUEVO' or cre.numero_operacion_madre is not null and proceso = 'RENOVACION')  " + 
					"union  " + 
					"select devo.id,devo.codigo,coalesce(devo.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, DEVO.NOMBRE_CLIENTE, devo.CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, devo.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, devo.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad, '' as motivo  " + 
					"	from tb_qo_devolucion devo inner join tb_qo_proceso proceso on proceso.id_referencia = devo.id and proceso.proceso in('DEVOLUCION','CANCELACION_DEVOLUCION')  " + 
					"union  " + 
					"select pago.id,pago.codigo,coalesce(pago.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, pago.NOMBRE_CLIENTE, pago.CEDULA as CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, pago.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, pago.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad, '' as motivo  " + 
					"	from tb_qo_cliente_pago pago inner join tb_qo_proceso proceso on proceso.id_referencia = pago.id and proceso.proceso ='PAGO'  " + 
					"union  " + 
					"select veri.id,veri.codigo,coalesce(veri.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, veri.NOMBRE_CLIENTE, veri.CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, veri.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, veri.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad, '' as motivo  " + 
					"	from TB_QO_VERIFICACION_TELEFONICA veri inner join tb_qo_proceso proceso on proceso.id_referencia = veri.id and proceso.proceso ='VERIFICACION_TELEFONICA') as TABL where 1=1  ";
			StringBuilder strQry = new StringBuilder( querySelect );
		
			
			if (wp.getEstado() == null || wp.getEstado().isEmpty()) {
				strQry.append(" and (ESTADO_PROCESO != 'CANCELADO' and  ESTADO_PROCESO != 'APROBADO' and ESTADO_PROCESO != 'RECHAZADO'  and ESTADO_PROCESO != 'CADUCADO') ");
			} else {
				String st = wp.getEstado().stream().map(EstadoProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and ESTADO_PROCESO in ('"+st+"') ");
			}
			if(wp.getAsesor() != null && !wp.getAsesor().isEmpty() ) {
				strQry.append(" and asesor in :asesor");
			}
			if(wp.getAgencia() != null && !wp.getAgencia().isEmpty() ) {
				strQry.append(" and ID_AGENCIA in :agencia");
			}
			if (wp.getProceso() != null && !wp.getProceso().isEmpty()) {
				String st = wp.getProceso().stream().map(ProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and PROCESO in ('"+st+"') ");
			}else {
				strQry.append(" and PROCESO not in('DEVOLUCION','CANCELACION_DEVOLUCION') ");
			}
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and FECHA_CREACION >=:desde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and FECHA_CREACION <=:hasta ");
			}
			if(StringUtils.isNotBlank(wp.getActividad()) ) {
				strQry.append(" and actividad =:actividad ");
			}
			if(StringUtils.isNotBlank(wp.getNombreCompleto() )) {
				strQry.append(" and nombre_completo iLIKE :nombre ");
			}
			if(StringUtils.isNotBlank(wp.getIdentificacion())) {
				strQry.append(" and CEDULA_CLIENTE =:cedula ");
			}
			if(StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and codigo iLIKE :codigoBpm ");
			}
			if(StringUtils.isNotBlank(wp.getCodigoSoft() ) ) {
				strQry.append(" and (operacion iLIKE :codigosoft or operacion_ant iLIKE :codigosoft)");
			}
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if(wp.getAsesor() != null && !wp.getAsesor().isEmpty() ) {
				query.setParameter("asesor", wp.getAsesor() );
			}
			if(wp.getAgencia() != null && !wp.getAgencia().isEmpty() ) {
				query.setParameter("agencia", wp.getAgencia() );
			}
			
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("desde", wp.getFechaCreacionDesde() );
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("hasta", wp.getFechaCreacionHasta() );
			}
			if(StringUtils.isNotBlank(wp.getActividad() )) {
				query.setParameter("actividad", wp.getActividad());
			}
			if(StringUtils.isNotBlank(wp.getNombreCompleto())) {
				query.setParameter("nombre", "%"+wp.getNombreCompleto().trim()+"%");
			}
			if(StringUtils.isNotBlank(wp.getIdentificacion())) {
				query.setParameter("cedula", wp.getIdentificacion());
			}
			if(StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+wp.getCodigoBpm().trim()+"%");
			}			
			if(StringUtils.isNotBlank(wp.getCodigoSoft() )) {
				query.setParameter("codigosoft", "%"+wp.getCodigoSoft().trim()+"%");
			}
			query.setParameter("limite", wp.getNumberItems() );
			Long salto = wp.getNumberItems() * (wp.getNumberPage());
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), OperacionesWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}

	@Override
	public TbQoProceso findByIdCreditoNuevo(Long id) throws RelativeException {
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
	public TbQoProceso findByIdCreditoNovacion(Long id) throws RelativeException {
		try {
			List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByIdNovacion(id));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OpPorAprobarWrapper> findOperacionPorAprobar(BusquedaPorAprobarWrapper wp) throws RelativeException {
		try {
			String querySelect = "select TABL.id, TABL.id_referencia, TABL.codigo, TABL.operacion, TABL.nombre_completo, TABL.cedula_cliente, TABL.monto, TABL.fecha_creacion, TABL.id_agencia, TABL.estado_proceso, TABL.proceso, TABL.asesor, TABL.usuario, TABL.actividad, TABL.aprobador, TABL.fecha_actualizacion, TABL.aciertos, CASE WHEN (TABL.proceso ='NUEVO') THEN "
					+ "								CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN "
					+ "									CASE WHEN  (TABL.APROBADOR = 'NULL' or TABL.APROBADOR = '')  "
					+ "										then 1  "
					+ "										ELSE 8  "
					+ "									end "
					+ "								ELSE CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION_DEVUELTO') THEN "
					+ "									CASE WHEN  (APROBADOR= 'NULL' or TABL.APROBADOR = '')  "
					+ "										then 2  "
					+ "										ELSE 9  "
					+ "									end "
					+ "								ELSE 100 END END "
					+ "							ELSE CASE WHEN (TABL.proceso ='RENOVACION') THEN  "
					+ "									CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN "
					+ "										CASE WHEN  (TABL.APROBADOR= 'NULL' or TABL.APROBADOR= '')  "
					+ "										then 3  "
					+ "										ELSE 10  "
					+ "										end "
					+ "									ELSE CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION_DEVUELTO') THEN "
					+ "										CASE WHEN  (TABL.APROBADOR = 'NULL' or TABL.APROBADOR = '')  "
					+ "										then 4  "
					+ "										ELSE 11  "
					+ "										end "
					+ "									ELSE 100 END END "
					+ "							ELSE CASE WHEN (TABL.proceso ='PAGO') THEN  "
					+ "									CASE WHEN  (TABL.APROBADOR= 'NULL' or TABL.APROBADOR = '')  "
					+ "										then 5   "
					+ "										ELSE 12  "
					+ "									end "
					+ "							ELSE CASE WHEN (TABL.proceso ='DEVOLUCION' or TABL.proceso ='CANCELACION_DEVOLUCION') THEN  "
					+ "									CASE WHEN  (TABL.APROBADOR= 'NULL'  or TABL.APROBADOR = '' )  "
					+ "										then 6   "
					+ "										ELSE 13  "
					+ "									end "
					+ "							ELSE CASE WHEN (TABL.proceso ='VERIFICACION_TELEFONICA') THEN  "
					+ "									CASE WHEN  (TABL.APROBADOR = 'NULL' or TABL.APROBADOR = '')  "
					+ "										then 7   "
					+ "										ELSE 14  "
					+ "									end "
					+ "							ELSE 100 END END END END END AS orden  from (select  pro.id, cre.id_negociacion  as ID_REFERENCIA , cre.codigo, coalesce(cre.numero_operacion,'NULL') as operacion, cli.nombre_completo, cli.cedula_cliente, coalesce(cre.monto_financiado,0) as monto,  pro.FECHA_CREACION, cre.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, nego.asesor, COALESCE( pro.USUARIO, 'NULL') USUARIO,'SIN ACTIVIDAD' as actividad , coalesce( nego.aprobador, 'NULL')    as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION, coalesce( cre.aciertos_bot_documento , 'NULL') as aciertos "
					+ "						from tb_qo_credito_negociacion cre inner join  tb_qo_negociacion nego on nego.id=cre.id_negociacion inner join tb_qo_cliente cli on cli.id = nego.id_cliente inner join tb_qo_proceso pro on  pro.id_referencia = cre.id_negociacion and (cre.numero_operacion_madre is null and proceso = 'NUEVO' or cre.numero_operacion_madre is not null and proceso = 'RENOVACION') "
					+ "					union  "
					+ "					select  pro.id, devo.id as ID_REFERENCIA, devo.codigo,coalesce(devo.CODIGO_OPERACION,'NULL') as operacion, DEVO.NOMBRE_CLIENTE, devo.CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, devo.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, devo.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, 'SIN ACTIVIDAD' as actividad , coalesce( devo.aprobador, 'NULL')  as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION  , 'NULL' as aciertos "
					+ "						from tb_qo_devolucion devo inner join tb_qo_proceso pro on  pro.id_referencia = devo.id and  pro.proceso in('DEVOLUCION','CANCELACION_DEVOLUCION')  "
					+ "					union  "
					+ "					select  pro.id, pago.id  as ID_REFERENCIA, pago.codigo,coalesce(pago.CODIGO_OPERACION,'NULL') as operacion, pago.NOMBRE_CLIENTE, pago.CEDULA as CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, pago.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, pago.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, 'SIN ACTIVIDAD' as actividad , coalesce( pago.aprobador, 'NULL')    as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION ,'NULL' as aciertos "
					+ "						from tb_qo_cliente_pago pago inner join tb_qo_proceso pro on  pro.id_referencia = pago.id and  pro.proceso ='PAGO'  "
					+ "					union  "
					+ "					select  pro.id, veri.id  as ID_REFERENCIA, veri.codigo,coalesce(veri.CODIGO_OPERACION,'NULL') as operacion, veri.NOMBRE_CLIENTE, veri.CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, veri.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, veri.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, 'SIN ACTIVIDAD' as actividad , coalesce( veri.aprobador, 'NULL')   as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION ,'NULL' as aciertos "
					+ "						from TB_QO_VERIFICACION_TELEFONICA veri inner join tb_qo_proceso pro on  pro.id_referencia = veri.id and  pro.proceso ='VERIFICACION_TELEFONICA') as TABL where 1=1  "
					+ "";
			
			StringBuilder strQry = new StringBuilder(querySelect).append(" and (ESTADO_PROCESO =:primerEstado or ESTADO_PROCESO =:segundoEstado or ESTADO_PROCESO =:tercerEstado ) ");
			if (StringUtils.isNotBlank(wp.getCodigo())) {
				strQry.append(" and codigo iLIKE :codigo");
			}
			if (StringUtils.isNotBlank(wp.getCedula() )) {
				strQry.append(" and  cedula_cliente =:cedula");
			}
			if(wp.getProceso() != null) {
				String st = wp.getProceso().stream().map(ProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and PROCESO in ('"+st+"') ");
			}
			if(wp.getIdAgencia() != null && !wp.getIdAgencia().isEmpty()) {
				strQry.append(" and id_agencia in :idAgencia ");
			}
			strQry.append(" ORDER BY fecha_actualizacion DESC LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			query.setParameter("primerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA.toString() );			
			query.setParameter("tercerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO.toString() );			
			if (wp.getCodigo() != null && !wp.getCodigo().equalsIgnoreCase("")) {
				query.setParameter("codigo",  "%"+wp.getCodigo().trim()+"%" );
			}
			if (wp.getCedula() != null && !wp.getCedula().equalsIgnoreCase("")) {
				query.setParameter("cedula", wp.getCedula() );
			}
			
			if(wp.getIdAgencia() != null && !wp.getIdAgencia().isEmpty()) {
				query.setParameter("idAgencia", wp.getIdAgencia() );
			}
			query.setParameter("limite", wp.getNumberItems() );
			Long salto = wp.getNumberItems() * (wp.getNumberPage());
			query.setParameter("salto", salto );
			return QuskiOroUtil.getResultList(query.getResultList(), OpPorAprobarWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
	
	@Override
	public Long countOperacionAprobar(BusquedaPorAprobarWrapper wp) throws RelativeException {
		try {
			String querySelect = "select count(*)  from (select  pro.id as id, cre.id_negociacion  as ID_REFERENCIA , cre.codigo, coalesce(cre.numero_operacion,'NULL') as operacion, cli.nombre_completo, cli.cedula_cliente, coalesce(cre.monto_financiado,0) as monto,  pro.FECHA_CREACION, cre.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, nego.asesor, COALESCE( pro.USUARIO, 'NULL') USUARIO,'SIN ACTIVIDAD' as actividad , coalesce( nego.aprobador, 'NULL')    as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION " + 
					"	from tb_qo_credito_negociacion cre inner join  tb_qo_negociacion nego on nego.id=cre.id_negociacion inner join tb_qo_cliente cli on cli.id = nego.id_cliente inner join tb_qo_proceso pro on  pro.id_referencia = cre.id_negociacion and (cre.numero_operacion_madre is null and proceso = 'NUEVO' or cre.numero_operacion_madre is not null and proceso = 'RENOVACION') " + 
					"union  " + 
					"select  pro.id as id, devo.id as ID_REFERENCIA, devo.codigo,coalesce(devo.CODIGO_OPERACION,'NULL') as operacion, DEVO.NOMBRE_CLIENTE, devo.CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, devo.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, devo.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, 'SIN ACTIVIDAD' as actividad , coalesce( devo.aprobador, 'NULL')  as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION  " + 
					"	from tb_qo_devolucion devo inner join tb_qo_proceso pro on  pro.id_referencia = devo.id and  pro.proceso in('DEVOLUCION','CANCELACION_DEVOLUCION')  " + 
					"union  " + 
					"select  pro.id as id, pago.id  as ID_REFERENCIA, pago.codigo,coalesce(pago.CODIGO_OPERACION,'NULL') as operacion, pago.NOMBRE_CLIENTE, pago.CEDULA as CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, pago.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, pago.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, 'SIN ACTIVIDAD' as actividad , coalesce( pago.aprobador, 'NULL')    as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION " + 
					"	from tb_qo_cliente_pago pago inner join tb_qo_proceso pro on  pro.id_referencia = pago.id and  pro.proceso ='PAGO'  " + 
					"union  " + 
					"select  pro.id as id, veri.id  as ID_REFERENCIA, veri.codigo,coalesce(veri.CODIGO_OPERACION,'NULL') as operacion, veri.NOMBRE_CLIENTE, veri.CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, veri.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, veri.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, 'SIN ACTIVIDAD' as actividad , coalesce( veri.aprobador, 'NULL')   as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION " + 
					"	from TB_QO_VERIFICACION_TELEFONICA veri inner join tb_qo_proceso pro on  pro.id_referencia = veri.id and  pro.proceso ='VERIFICACION_TELEFONICA') as TABL where 1=1 ";
			StringBuilder strQry = new StringBuilder( querySelect ).append(" and ( ESTADO_PROCESO =:primerEstado or  ESTADO_PROCESO =:segundoEstado ) ");
			if (StringUtils.isNotBlank(wp.getCodigo())) {
				strQry.append(" and codigo iLIKE :codigo");
			}
			if (StringUtils.isNotBlank(wp.getCedula() )) {
				strQry.append(" and  cedula_cliente =:cedula");
			}
			if(wp.getProceso() != null) {
				String st = wp.getProceso().stream().map(ProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and PROCESO in ('"+st+"') ");
			}
			if(wp.getIdAgencia() != null) {
				strQry.append(" and id_agencia in :idAgencia ");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			query.setParameter("primerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA.toString() );
			if (wp.getCodigo() != null && !wp.getCodigo().equalsIgnoreCase("")) {
				query.setParameter("codigo",  "%"+wp.getCodigo().trim()+"%" );
			}
			if (wp.getCedula() != null && !wp.getCedula().equalsIgnoreCase("")) {
				query.setParameter("cedula", wp.getCedula() );
			}
			if(wp.getIdAgencia() != null && !wp.getIdAgencia().isEmpty()) {
				query.setParameter("idAgencia", wp.getIdAgencia() );
			}
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcesoDevoActivoWrapper> findDevolucionesActivas(String numeroOperacion) throws RelativeException {
		try {
			String querySelect = " select j.id, j.codigo, jon.estado_proceso from tb_qo_devolucion j inner join ( select d.estado_proceso, d.id_referencia from tb_qo_proceso d where d.proceso = 'DEVOLUCION') "
					+ " as jon on jon.id_referencia = j.id where 1 = 1 ";
			StringBuilder strQry = new StringBuilder(querySelect);
	
			if (StringUtils.isNotBlank(numeroOperacion)) {
				strQry.append(" and j.codigo_operacion =:numeroOperacion ");
			}
			
			strQry.append(" and (jon.estado_proceso != 'RECHAZADO' AND jon.estado_proceso != 'CANCELADO' ) ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			log.info("=========> QUERY VALIDACION =======> "+ strQry.toString() +" <====");
			if (StringUtils.isNotBlank(numeroOperacion)) {
				query.setParameter("numeroOperacion", numeroOperacion);
			}
			return QuskiOroUtil.getResultList(query.getResultList(), ProcesoDevoActivoWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, " AL CONSULTAR EN  findDevolucionesActivas " + e.getMessage());
		}
	}
	
	@Override
	public Long caducarProcesos() throws RelativeException {
		try {
			Query query = this.getEntityManager().createNativeQuery(" call caducar_estado() ");
			int number = query.executeUpdate();
			Long count = Long.valueOf(number);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
	@Override
	public List<ProcesoCaducadoWrapper> findByTiempoBaseAprobadorProcesoEstadoProceso(Long tiempoBase, List<String> aprobadores, List<ProcesoEnum> procesos, List<EstadoProcesoEnum> estados)
			throws RelativeException {
		try {
			List<TbQoProceso> listProceso = this.findAllBySpecification( new ProcesoByProcesosEstadosAndTimeDiferenceSpec( procesos, estados, tiempoBase ) );
			log.info(" HORA DE TIEMPO DE BASE ========================================> " + tiempoBase );
			if( listProceso == null || listProceso.isEmpty() ) {
				return null;
			}
			if( procesos == null || procesos.isEmpty() ) {
				return null;
			}
			List<ProcesoCaducadoWrapper> lista = new ArrayList<>();
			for(TbQoProceso  proceso:listProceso) {
				ProcesoCaducadoWrapper wrapper = new ProcesoCaducadoWrapper();
				wrapper.setProceso(proceso.getProceso().toString());
				wrapper.setTiempoInicio(QuskiOroUtil.formatSringToDate(proceso.getHoraAprobador(), QuskiOroUtil.DATE_FORMAT_FULL) );
				try {
					wrapper.setTiempoTranscurrido((new Date().getTime() - proceso.getHoraAprobador().getTime() )/60000);
				} catch (Exception e) {
					wrapper.setTiempoTranscurrido(Long.valueOf("0"));
				}
				if( proceso.getProceso().compareTo( ProcesoEnum.NUEVO ) == 0 || proceso.getProceso().compareTo( ProcesoEnum.RENOVACION ) == 0 ) {
					List<TbQoCreditoNegociacion> listCredito = this.findAllBySpecification( new CreditoByListIdsAndAprobadoresSpec( proceso.getIdReferencia(), aprobadores ) );
					log.info(" LISTA DE CREDITOS ========================================> " + listCredito.size() );
					if(listCredito != null && !listCredito.isEmpty()) {
						log.info(" LISTA DE CREDITOS ========================================> " + listCredito.get(0).getTbQoNegociacion().getAprobador() );
						wrapper.setAprobador(listCredito.get(0).getTbQoNegociacion().getAprobador());
						wrapper.setCodigoBpm(listCredito.get(0).getCodigo());
						wrapper.setCodigSoftbank(listCredito.get(0).getNumeroOperacion());
						lista.add(wrapper);
					}
				
				}else if( proceso.getProceso().compareTo( ProcesoEnum.PAGO ) == 0 ) {
					List<TbQoClientePago> listPago  =  this.findAllBySpecification( new PagoByListIdsAndAprobadoresSpec( proceso.getIdReferencia(), aprobadores ) );
					log.info(" LISTA DE CREDITOS ========================================> " + listPago.size() );
					if(listPago != null && !listPago.isEmpty()) {
						log.info(" LISTA DE PAGOS  ========================================> " + listPago.get(0).getAprobador() );

						wrapper.setAprobador(listPago.get(0).getAprobador());
						wrapper.setCodigoBpm(listPago.get(0).getCodigo());
						wrapper.setCodigSoftbank(listPago.get(0).getCodigoOperacion());
						lista.add(wrapper);
					}
				}else if( proceso.getProceso().compareTo( ProcesoEnum.VERIFICACION_TELEFONICA ) == 0 ) {
					List<TbQoVerificacionTelefonica> listVerificacion =  this.findAllBySpecification( new VerificacionByListIdsAndAprobadoresSpec( proceso.getIdReferencia(), aprobadores ) );
					log.info(" LISTA DE CREDITOS ========================================> " + listVerificacion.size() );
					if(listVerificacion != null && !listVerificacion.isEmpty()) {
						log.info(" LISTA DE VERIFICACION ========================================> " + listVerificacion.get(0).getAprobador() );

						wrapper.setAprobador(listVerificacion.get(0).getAprobador());
						wrapper.setCodigoBpm(listVerificacion.get(0).getCodigo());
						lista.add(wrapper);
					}
				}else if( proceso.getProceso().compareTo( ProcesoEnum.DEVOLUCION ) == 0 ) {
					List<TbQoDevolucion> listDevolucion  =  this.findAllBySpecification( new DevolucionByListIdsAndAprobadoresSpec( proceso.getIdReferencia(), aprobadores ) );
					log.info(" LISTA DE CREDITOS ========================================> " + listDevolucion.size() );
					if(listDevolucion != null && !listDevolucion.isEmpty()) {
						log.info(" LISTA DE DEVOLUCION ========================================> " + listDevolucion.get(0).getAprobador() );

						wrapper.setAprobador(listDevolucion.get(0).getAprobador());
						wrapper.setCodigoBpm(listDevolucion.get(0).getCodigo());
						wrapper.setCodigSoftbank(listDevolucion.get(0).getCodigoOperacion());
						lista.add(wrapper);
					}
				}
				
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
	@Override
	public Integer findByNumeroOperacionMadreAndProcesoAndEstado(String numeroOperacionMadre, ProcesoEnum proceso,
			List<EstadoProcesoEnum> estados) throws RelativeException {
		try {
			List<Long> creditos = null;
			if (StringUtils.isBlank(numeroOperacionMadre)) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL NUMERO DE OPERACION MADRE");
			}
				CriteriaBuilder cbb = getEntityManager().getCriteriaBuilder();
				CriteriaQuery<Long> queryy = cbb.createQuery(Long.class);
				Root<TbQoCreditoNegociacion> pollRol = queryy.from(TbQoCreditoNegociacion.class);
				//Join<TbQoNegociacion,TbQoCreditoNegociacion> joinNegociacion = pollRol.join("tbQoNegociacion");
				queryy.where(cbb.equal(pollRol.get("numeroOperacionMadre"), numeroOperacionMadre));
				queryy.select(pollRol.get("tbQoNegociacion").get("id"));
				TypedQuery<Long> createQue = this.getEntityManager().createQuery(queryy);
				creditos = createQue.getResultList();
			if(creditos == null || creditos.isEmpty() ) {
				return Integer.valueOf("0");
			}
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<TbQoProceso> poll = query.from(TbQoProceso.class);
			List<Predicate> where = new ArrayList<>();
			where.add(poll.get("idReferencia").in(creditos));
			if (estados != null && !estados.isEmpty()) {
				where.add(poll.get("estadoProceso").in(estados));
			}
			where.add(cb.equal(poll.get("proceso"), ProcesoEnum.RENOVACION));
			

			query.where(cb.and(where.toArray(new Predicate[] {})));

			query.select(cb.count(poll.get("id")));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EXCEPCIONES");
		}
	}
	@Override
	public String generarSecuencia(String codigo) throws RelativeException {
		try {
			StringBuilder strQry = new StringBuilder();
			if(codigo.equalsIgnoreCase(QuskiOroConstantes.CODIGO_RENOVACION)) {
				strQry.append("select nextval('seq_renovacion')");
			}else if(codigo.equalsIgnoreCase(QuskiOroConstantes.CODIGO_NUEVO)) {
				strQry.append("select nextval('seq_nuevo')");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			BigInteger  seq = (BigInteger) query.getSingleResult();
			
			return codigo.concat(StringUtils.leftPad(String.valueOf(seq), 7, "0"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL CREAR SECUENCIAL");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CabeceraWrapper getCabecera(String idReferencia, String proceso) throws RelativeException {

		try {
			List<CabeceraWrapper> tmp;
			String querySelect = "";		
			if(StringUtils.isNotBlank(proceso) ) {
				if(proceso.equalsIgnoreCase(ProcesoEnum.NUEVO.toString()) || proceso.equalsIgnoreCase(ProcesoEnum.RENOVACION.toString())) {
					querySelect = "select " + 
							"	coalesce(cli.nombre_completo, 'NULL') as nombre_completo, " + 
							"	coalesce(cli.cedula_cliente, 'NULL') as cedula_cliente, " + 
							"	coalesce(cre.numero_operacion, 'NULL') as numero_operacion, " + 
							"	coalesce(cre.codigo, 'NULL') as codigo, " + 
							"	coalesce(cre.monto_financiado, '0') as monto_financiado, " + 
							"	coalesce(cre.plazo_credito, '0') as plazo_credito, " + 
							"	coalesce(cre.periodo_plazo, 'NULL') as periodo_plazo, " + 
							"	coalesce(cuenta.cuenta, 'NULL') as cuenta, " + 
							"	coalesce(nego.nombre_asesor, 'NULL') as nombre_asesor, " + 
							"	coalesce(cre.numero_operacion_anterior, 'NULL') as numero_operacion_anterior " + 
							"from tb_qo_negociacion nego " + 
							"inner join tb_qo_credito_negociacion cre on " + 
							"	cre.id_negociacion = nego.id " + 
							"inner join tb_qo_cliente cli on " + 
							"	cli.id = nego.id_cliente " + 
							"left join tb_qo_cuenta_bancaria_cliente cuenta on " + 
							"	cuenta.id_cliente = nego.id_cliente where nego.id = :idReferencia";
				}
				if(proceso.equalsIgnoreCase(ProcesoEnum.DEVOLUCION.toString())) {
					querySelect = "select coalesce(dev.nombre_cliente,'NULL') as nombre_cliente, coalesce(dev.cedula_cliente,'NULL') as cedula_cliente, coalesce(dev.codigo_operacion,'NULL') as codigo_operacion, coalesce(dev.codigo,'NULL') as codigo, 'NA' as monto , 'NA' as plazo, 'NA' as tipo, 'NA' as cuenta , coalesce(dev.asesor,'NULL') as asesor " + 
							"from tb_qo_devolucion dev where dev.id = :idReferencia";
				}
				if(proceso.equalsIgnoreCase(ProcesoEnum.PAGO.toString())) {
					querySelect = "select coalesce(pago.nombre_cliente,'NULL') as nombre, coalesce(pago.cedula,'NULL') as cedula, coalesce(codigo_operacion,'NULL') as codigo_operacion, coalesce(codigo,'NULL') as codigo, 'NA' as monto, 'NA' as plazo, coalesce(pago.tipo_credito,'NULL') as tipo,  'NA' as cuenta, coalesce(pago.asesor,'NULL') as asesor " + 
							"from tb_qo_cliente_pago pago where pago.id = :idReferencia";
				}
				
			}
			StringBuilder strQry = new StringBuilder( querySelect );
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			query.setParameter("idReferencia", Long.valueOf(idReferencia) );
			
			if(query.getResultList() != null && !query.getResultList().isEmpty()) {
				tmp = QuskiOroUtil.getResultList(query.getResultList(), CabeceraWrapper.class);
				return tmp.get(0);
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	@Override
	public List<EstadoProcesoEnum> getEstadosProceso(List<ProcesoEnum> proceso) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<EstadoProcesoEnum> query = cb.createQuery(EstadoProcesoEnum.class);
			Root<TbQoProceso> poll = query.from(TbQoProceso.class);
			if(proceso != null && !proceso.isEmpty()) {
				query.where(poll.get("proceso").in(proceso));
			}
			query.select(poll.get("estadoProceso")).distinct(true);
			TypedQuery<EstadoProcesoEnum> tq = this.getEntityManager().createQuery(query);
			List<EstadoProcesoEnum> resultList = tq.getResultList();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL LEER LAS ACTIVIDADES DE TRAKING");
		}
	}
	@Override
	public BigDecimal getMontoFinanciado(BusquedaOperacionesWrapper wp) throws RelativeException {
		try {
			String querySelect = "select sum(monto) from (select cre.id_negociacion as id , cre.codigo, coalesce(cre.numero_operacion,' ') as operacion,coalesce(cre.numero_operacion_anterior,' ') as operacion_ant, cli.nombre_completo, cli.cedula_cliente, coalesce(cre.monto_financiado,0) as monto, proceso.FECHA_CREACION, cre.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, nego.asesor, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD'  as actividad   " + 
					"	from tb_qo_credito_negociacion cre inner join  tb_qo_negociacion nego on nego.id=cre.id_negociacion inner join tb_qo_cliente cli on cli.id = nego.id_cliente inner join tb_qo_proceso proceso on proceso.id_referencia = cre.id_negociacion and (cre.numero_operacion_madre is null and proceso = 'NUEVO' or cre.numero_operacion_madre is not null and proceso = 'RENOVACION')  " + 
					"union  " + 
					"select devo.id,devo.codigo,coalesce(devo.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, DEVO.NOMBRE_CLIENTE, devo.CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, devo.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, devo.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad  " + 
					"	from tb_qo_devolucion devo inner join tb_qo_proceso proceso on proceso.id_referencia = devo.id and proceso.proceso in('DEVOLUCION','CANCELACION_DEVOLUCION')  " + 
					"union  " + 
					"select pago.id,pago.codigo,coalesce(pago.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, pago.NOMBRE_CLIENTE, pago.CEDULA as CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, pago.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, pago.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad  " + 
					"	from tb_qo_cliente_pago pago inner join tb_qo_proceso proceso on proceso.id_referencia = pago.id and proceso.proceso ='PAGO'  " + 
					"union  " + 
					"select veri.id,veri.codigo,coalesce(veri.CODIGO_OPERACION,' ') as operacion,' ' as operacion_ant, veri.NOMBRE_CLIENTE, veri.CEDULA_CLIENTE, 0 as monto, proceso.FECHA_CREACION, veri.ID_AGENCIA, PROCESO.ESTADO_PROCESO, PROCESO.PROCESO, veri.ASESOR, COALESCE(PROCESO.USUARIO, ' ') USUARIO, 'SIN ACTIVIDAD' as actividad  " + 
					"	from TB_QO_VERIFICACION_TELEFONICA veri inner join tb_qo_proceso proceso on proceso.id_referencia = veri.id and proceso.proceso ='VERIFICACION_TELEFONICA') as TABL where 1=1 ";
			StringBuilder strQry = new StringBuilder( querySelect );
		
			
			if (wp.getEstado() == null || wp.getEstado().isEmpty()) {
				strQry.append(" and (ESTADO_PROCESO != 'CANCELADO' and  ESTADO_PROCESO != 'APROBADO' and ESTADO_PROCESO != 'RECHAZADO'  and ESTADO_PROCESO != 'CADUCADO') ");
			} else {
				String st = wp.getEstado().stream().map(EstadoProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and ESTADO_PROCESO in ('"+st+"') ");
			}
			if(wp.getAsesor() != null && !wp.getAsesor().isEmpty() ) {
				strQry.append(" and asesor in :asesor");
			}
			if(wp.getAgencia() != null && !wp.getAgencia().isEmpty() ) {
				strQry.append(" and ID_AGENCIA in :agencia");
			}
			if (wp.getProceso() != null && !wp.getProceso().isEmpty()) {
				String st = wp.getProceso().stream().map(ProcesoEnum::name).collect(Collectors.joining("','") );
				strQry.append(" and PROCESO in ('"+st+"') ");
			}else {
				strQry.append(" and PROCESO not in('DEVOLUCION','CANCELACION_DEVOLUCION') ");
			}	
			if (wp.getFechaCreacionDesde() != null) {
				strQry.append(" and FECHA_CREACION >=:desde ");
			}	
			if (wp.getFechaCreacionHasta() != null) {
				strQry.append(" and FECHA_CREACION <=:hasta ");
			}
			if(StringUtils.isNotBlank(wp.getActividad()) ) {
				strQry.append(" and actividad =:actividad ");
			}
			if(StringUtils.isNotBlank(wp.getNombreCompleto() )) {
				strQry.append(" and nombre_completo iLIKE :nombre ");
			}
			if(StringUtils.isNotBlank(wp.getIdentificacion())) {
				strQry.append(" and CEDULA_CLIENTE =:cedula ");
			}
			if(StringUtils.isNotBlank(wp.getCodigoBpm())) {
				strQry.append(" and codigo iLIKE :codigoBpm ");
			}
			if(StringUtils.isNotBlank(wp.getCodigoSoft() ) ) {
				strQry.append(" and (operacion iLIKE :codigosoft or operacion_ant iLIKE :codigosoft)");
			}

			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			
			if(wp.getAsesor() != null && !wp.getAsesor().isEmpty() ) {
				query.setParameter("asesor", wp.getAsesor() );
			}
			if(wp.getAgencia() != null && !wp.getAgencia().isEmpty() ) {
				query.setParameter("agencia", wp.getAgencia() );
			}
			if (wp.getFechaCreacionDesde() != null) {
				query.setParameter("desde", wp.getFechaCreacionDesde() );
			}	
			if (wp.getFechaCreacionHasta() != null) {
				query.setParameter("hasta", wp.getFechaCreacionHasta() );
			}
			if(StringUtils.isNotBlank(wp.getActividad() )) {
				query.setParameter("actividad", wp.getActividad());
			}
			if(StringUtils.isNotBlank(wp.getNombreCompleto())) {
				query.setParameter("nombre", "%"+wp.getNombreCompleto().trim()+"%");
			}
			if(StringUtils.isNotBlank(wp.getIdentificacion())) {
				query.setParameter("cedula", wp.getIdentificacion());
			}
			if(StringUtils.isNotBlank(wp.getCodigoBpm())) {
				query.setParameter("codigoBpm", "%"+wp.getCodigoBpm().trim()+"%");
			}			
			if(StringUtils.isNotBlank(wp.getCodigoSoft() )) {
				query.setParameter("codigosoft", "%"+wp.getCodigoSoft().trim()+"%");
			}
			return (BigDecimal) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OpPorAprobarWrapper> buscarOperacionesAprobador(Long idProceso) throws RelativeException {
		try {
			String querySelect = "select TABL.id, TABL.id_referencia, TABL.codigo, TABL.operacion, TABL.nombre_completo, TABL.cedula_cliente, TABL.monto, TABL.fecha_creacion, TABL.id_agencia, TABL.estado_proceso, TABL.proceso, TABL.asesor, TABL.usuario, TABL.actividad, TABL.aprobador, TABL.fecha_actualizacion,CASE WHEN (TABL.proceso ='NUEVO') THEN " + 
					"			CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN " + 
					"				CASE WHEN  (TABL.APROBADOR = 'NULL' or TABL.APROBADOR = '')  " + 
					"					then 1  " + 
					"					ELSE 8  " + 
					"				end " + 
					"			ELSE CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION_DEVUELTO') THEN " + 
					"				CASE WHEN  (APROBADOR= 'NULL' or TABL.APROBADOR = '')  " + 
					"					then 2  " + 
					"					ELSE 9  " + 
					"				end " + 
					"			ELSE 100 END END " + 
					"		ELSE CASE WHEN (TABL.proceso ='RENOVACION') THEN  " + 
					"				CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN " + 
					"					CASE WHEN  (TABL.APROBADOR= 'NULL' or TABL.APROBADOR= '')  " + 
					"					then 3  " + 
					"					ELSE 10  " + 
					"					end " + 
					"				ELSE CASE WHEN (TABL.ESTADO_PROCESO = 'PENDIENTE_APROBACION_DEVUELTO') THEN " + 
					"					CASE WHEN  (TABL.APROBADOR = 'NULL' or TABL.APROBADOR = '')  " + 
					"					then 4  " + 
					"					ELSE 11  " + 
					"					end " + 
					"				ELSE 100 END END " + 
					"		ELSE CASE WHEN (TABL.proceso ='PAGO') THEN  " + 
					"				CASE WHEN  (TABL.APROBADOR= 'NULL' or TABL.APROBADOR = '')  " + 
					"					then 5   " + 
					"					ELSE 12  " + 
					"				end " + 
					"		ELSE CASE WHEN (TABL.proceso ='DEVOLUCION' or TABL.proceso ='CANCELACION_DEVOLUCION') THEN  " + 
					"				CASE WHEN  (TABL.APROBADOR= 'NULL'  or TABL.APROBADOR = '' )  " + 
					"					then 6   " + 
					"					ELSE 13  " + 
					"				end " + 
					"		ELSE CASE WHEN (TABL.proceso ='VERIFICACION_TELEFONICA') THEN  " + 
					"				CASE WHEN  (TABL.APROBADOR = 'NULL' or TABL.APROBADOR = '')  " + 
					"					then 7   " + 
					"					ELSE 14  " + 
					"				end " + 
					"		ELSE 100 END END END END END AS orden  from (select  pro.id, cre.id_negociacion  as ID_REFERENCIA , cre.codigo, coalesce(cre.numero_operacion,'NULL') as operacion, cli.nombre_completo, cli.cedula_cliente, coalesce(cre.monto_financiado,0) as monto,  pro.FECHA_CREACION, cre.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, nego.asesor, COALESCE( pro.USUARIO, 'NULL') USUARIO, coalesce ((select tra.ACTIVIDAD from TB_QO_TRACKING tra, TB_QO_CREDITO_NEGOCIACION cr where cr.CODIGO = tra.CODIGO_BPM  order by tra.FECHA_INICIO desc limit 1),'SIN ACTIVIDAD' ) as actividad , coalesce( nego.aprobador, 'NULL')    as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION " + 
					"	from tb_qo_credito_negociacion cre inner join  tb_qo_negociacion nego on nego.id=cre.id_negociacion inner join tb_qo_cliente cli on cli.id = nego.id_cliente inner join tb_qo_proceso pro on  pro.id_referencia = cre.id_negociacion and (cre.numero_operacion_madre is null and proceso = 'NUEVO' or cre.numero_operacion_madre is not null and proceso = 'RENOVACION') " + 
					"union  " + 
					"select  pro.id, devo.id as ID_REFERENCIA, devo.codigo,coalesce(devo.CODIGO_OPERACION,'NULL') as operacion, DEVO.NOMBRE_CLIENTE, devo.CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, devo.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, devo.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, coalesce ((select tra.ACTIVIDAD from TB_QO_DEVOLUCION dev, TB_QO_TRACKING tra where dev.CODIGO = tra.CODIGO_BPM order by tra.FECHA_INICIO desc limit 1),'SIN ACTIVIDAD') as actividad , coalesce( devo.aprobador, 'NULL')  as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION  " + 
					"	from tb_qo_devolucion devo inner join tb_qo_proceso pro on  pro.id_referencia = devo.id and  pro.proceso in('DEVOLUCION','CANCELACION_DEVOLUCION')  " + 
					"union  " + 
					"select  pro.id, pago.id  as ID_REFERENCIA, pago.codigo,coalesce(pago.CODIGO_OPERACION,'NULL') as operacion, pago.NOMBRE_CLIENTE, pago.CEDULA as CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, pago.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, pago.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, coalesce ((select tra.ACTIVIDAD from TB_QO_TRACKING tra, TB_QO_CLIENTE_PAGO pag where pag.codigo = tra.CODIGO_BPM order by tra.FECHA_INICIO desc limit 1),'SIN ACTIVIDAD') as actividad , coalesce( pago.aprobador, 'NULL')    as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION " + 
					"	from tb_qo_cliente_pago pago inner join tb_qo_proceso pro on  pro.id_referencia = pago.id and  pro.proceso ='PAGO'  " + 
					"union  " + 
					"select  pro.id, veri.id  as ID_REFERENCIA, veri.codigo,coalesce(veri.CODIGO_OPERACION,'NULL') as operacion, veri.NOMBRE_CLIENTE, veri.CEDULA_CLIENTE, 0 as monto,  pro.FECHA_CREACION, veri.ID_AGENCIA,  pro.ESTADO_PROCESO,  pro.PROCESO, veri.ASESOR, COALESCE( pro.USUARIO, 'NULL') USUARIO, coalesce ((select tra.ACTIVIDAD from TB_QO_TRACKING tra, TB_QO_VERIFICACION_TELEFONICA ver where ver.codigo = tra.CODIGO_BPM order by tra.FECHA_INICIO desc limit 1),'SIN ACTIVIDAD') as actividad , coalesce( veri.aprobador, 'NULL')   as APROBADOR, coalesce(  pro.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION " + 
					"	from TB_QO_VERIFICACION_TELEFONICA veri inner join tb_qo_proceso pro on  pro.id_referencia = veri.id and  pro.proceso ='VERIFICACION_TELEFONICA') as TABL where 1=1 ";
			
			StringBuilder strQry = new StringBuilder(querySelect).append(" and (ESTADO_PROCESO =:primerEstado or ESTADO_PROCESO =:segundoEstado or ESTADO_PROCESO =:tercerEstado ) ");
			if (idProceso != null) {
				strQry.append(" and TABL.id = :idProceso");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			query.setParameter("primerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA.toString() );			
			query.setParameter("tercerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO.toString() );			
			if (idProceso != null) {
				query.setParameter("idProceso",  idProceso);
			}
			return QuskiOroUtil.getResultList(query.getResultList(), OpPorAprobarWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
		}
	}
	@Override
	public TbQoCreditoNegociacion findRenovacionByNumeroOperacionMadreAndEstado(String numeroOperacionMadre,
			List<EstadoProcesoEnum> estados) throws RelativeException {
		try {
			List<Long> creditos = null;
			if (StringUtils.isBlank(numeroOperacionMadre)) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL NUMERO DE OPERACION MADRE");
			}
				CriteriaBuilder cbb = getEntityManager().getCriteriaBuilder();
				CriteriaQuery<Long> queryy = cbb.createQuery(Long.class);
				Root<TbQoCreditoNegociacion> pollRol = queryy.from(TbQoCreditoNegociacion.class);
				//Join<TbQoNegociacion,TbQoCreditoNegociacion> joinNegociacion = pollRol.join("tbQoNegociacion");
				queryy.where(cbb.equal(pollRol.get("numeroOperacionMadre"), numeroOperacionMadre));
				queryy.select(pollRol.get("tbQoNegociacion").get("id"));
				TypedQuery<Long> createQue = this.getEntityManager().createQuery(queryy);
				creditos = createQue.getResultList();
			if(creditos == null || creditos.isEmpty() ) {
				return null;
			}
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<TbQoProceso> poll = query.from(TbQoProceso.class);
			List<Predicate> where = new ArrayList<>();
			where.add(poll.get("idReferencia").in(creditos));
			if (estados != null && !estados.isEmpty()) {
				where.add(poll.get("estadoProceso").in(estados));
			}
			where.add(cb.equal(poll.get("proceso"), ProcesoEnum.RENOVACION));
			

			query.where(cb.and(where.toArray(new Predicate[] {})));

			query.select(poll.get("idReferencia"));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			
			List<Long> procesos = createQuery.getResultList();
			
			if(procesos == null || procesos.isEmpty() ) {
				return null;
			}
			
			List<TbQoCreditoNegociacion> tmp = this.findAllBySpecification(new CreditoNegociacionSpec(procesos));
			
			if(tmp == null || tmp.isEmpty() ) {
				return null;
			}
			
			return tmp.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR EXCEPCIONES");
		}
	}

}
