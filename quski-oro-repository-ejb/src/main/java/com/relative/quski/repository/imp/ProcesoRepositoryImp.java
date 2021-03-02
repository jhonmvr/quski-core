package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.ProcesoByAsesorSpec;
import com.relative.quski.repository.spec.ProcesoByIdNegociacion;
import com.relative.quski.repository.spec.ProcesoByIdNovacion;
import com.relative.quski.repository.spec.ProcesoByIdReferenciaSpec;
import com.relative.quski.repository.spec.ProcesoByIdSpec;
import com.relative.quski.util.QueryConstantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.OpPorAprobarWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;
import com.relative.quski.wrapper.ProcesoDevoActivoWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "procesoRepository")
public class ProcesoRepositoryImp extends GeneralRepositoryImp<Long, TbQoProceso> implements ProcesoRepository {
	@Inject
	Logger log;
	private final String ID_OP = " case when "+QueryConstantes.WHEN_NEGO+"  then " + 
			"COALESCE( (select nego.ID from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 0) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"		COALESCE( (select devo.ID from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia), 0) " + 
			"		else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"			COALESCE( (select pago.ID from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia), 0) " + 
			"			else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"				COALESCE( (select veri.ID from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia), 0) " + 
			"				else 0 end end end end ID ";
	private final String CODIGO_BPM = " case when "+QueryConstantes.WHEN_NEGO +" then " + 
			"COALESCE( (select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia), 'NULL')  " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	COALESCE( (select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia), 'NULL')  " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE( (select pago.CODIGO from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia), 'NULL')  " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE( (select veri.CODIGO from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia), 'NULL')  " + 
			"			else ' ' end end end end CODIGO_BPM ";
	private final String CODIGO_OP = " case when "+QueryConstantes.WHEN_NEGO +" then " + 
			"COALESCE( (select cre.numero_operacion from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia), 'NULL')  " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	COALESCE( (select devo.CODIGO_OPERACION from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia), 'NULL')  " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE( (select pago.CODIGO_OPERACION from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia), 'NULL')  " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE( (select veri.CODIGO_OPERACION from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia), 'NULL')  " + 
			"			else ' ' end end end end CODIGO_OPERACION ";
	private final String CEDULA_OP = " case when "+QueryConstantes.WHEN_NEGO +" then " + 
			"COALESCE( (select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id ), 'NULL')  " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	COALESCE( (select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia ), 'NULL')  " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE( (select pago.CEDULA  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ), 'NULL')  " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE( (select veri.CEDULA_CLIENTE from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ), 'NULL')  " + 
			"			else ' ' end end end end cedula_cliente ";
	private final String NOMBRE_OP = " case when "+QueryConstantes.WHEN_NEGO +" then " + 
			"COALESCE( (select cli.nombre_completo from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id ), 'NULL') " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	COALESCE( (select DEVO.NOMBRE_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia ), 'NULL') " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE( (select pago.NOMBRE_CLIENTE  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ), 'NULL') " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE( (select veri.NOMBRE_CLIENTE from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ), 'NULL') " + 
			"			else ' ' end end end end nombre_cliente ";
	private final String AGENCIA_OP = " case when " + QueryConstantes.WHEN_NEGO + " then " + 
			" COALESCE( (select cre.ID_AGENCIA from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia), 0) " + 
			"else case when  "+QueryConstantes.WHEN_DEVO+"  then " + 
			"	COALESCE( (select devo.ID_AGENCIA from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia), 0)  " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE( (select pago.ID_AGENCIA  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ), 0)  " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE( (select veri.ID_AGENCIA from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ), 0)  " + 
			"			else 0 end end end end AGENCIA	";
	private final String ASESOR_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			"COALESCE( (select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	COALESCE((select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia), 'NULL') " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE( (select pago.ASESOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ), 'NULL') " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE( (select veri.ASESOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ), 'NULL') " + 
			"			else ' ' end end end end ASESOR ";
	private final String APROBADOR_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			" COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia and nego.APROBADOR != ''), 'NULL') " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " +
			"	COALESCE((select devo.APROBADOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia and devo.APROBADOR != ''), 'NULL') " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " +
			"		COALESCE((select pago.APROBADOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA and pago.APROBADOR != ''), 'NULL')" + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " +
			"			COALESCE((select veri.APROBADOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA and veri.APROBADOR != ''), 'NULL') " + 
			"			else ' ' end end end end APROBADOR ";
	private final String ORDEN_OP = " CASE WHEN (proceso.proceso ='NUEVO') THEN" +
			"			CASE WHEN (PROCESO.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN" +
			"				CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') = 'NULL') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') = '') then 1 ELSE 8 end" +
			"			ELSE CASE WHEN (PROCESO.ESTADO_PROCESO = 'PENDIENTE_APROBACION_DEVUELTO') THEN" +
			"				CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') = 'NULL') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') = '') then 2 ELSE 9 end" +
			"			ELSE 0 END END" +
			"		ELSE CASE WHEN (proceso.proceso ='RENOVACION') THEN " +
			"				CASE WHEN (PROCESO.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN" +
			"					CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'NULL') = 'NULL') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') = '') then 3 ELSE 10 end" +
			"				ELSE CASE WHEN (PROCESO.ESTADO_PROCESO = 'PENDIENTE_APROBACION_DEVUELTO') THEN" + 
			"					CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'NULL') = 'NULL') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia), 'NULL') = '') then 4 ELSE 11 end" +
			"				ELSE 0 END END" + 
			"		ELSE CASE WHEN (proceso.proceso ='PAGO') THEN " + 
			"				CASE WHEN  (COALESCE((select pago.APROBADOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ),'NULL') = 'NULL') or (COALESCE((select pago.APROBADOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ), 'NULL') = '') then 5  ELSE 12 end" +
			"		ELSE CASE WHEN ("+QueryConstantes.WHEN_DEVO+") THEN " + 
			"				CASE WHEN  (COALESCE((select devo.APROBADOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia),'NULL') = 'NULL' ) or (COALESCE((select devo.APROBADOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia), 'NULL') = '' ) then 6  ELSE 13 end" +
			"		ELSE CASE WHEN (proceso.proceso ='VERIFICACION_TELEFONICA') THEN " + 
			"				CASE WHEN  (COALESCE((select veri.APROBADOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) ,'NULL') = 'NULL') or (COALESCE((select veri.APROBADOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ), 'NULL') = '') then 7  ELSE 14 end" + 
			"		ELSE 0 END END END END END AS orden ";
	private final String MONTO_OP = " case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
			"			COALESCE((select cre.monto_financiado from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia), 0)" + 
			"			else 0 end monto_financiado ";
	private final String ACTIVIDAD_OP = " case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
			"			COALESCE((select tra.ACTIVIDAD from TB_QO_TRACKING tra, tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.CODIGO = tra.CODIGO_BPM and cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia ORDER BY tra.FECHA_INICIO DESC limit 1), 'NULL') " + 
			"			else case when (proceso.proceso ='DEVOLUCION'  or proceso.proceso = 'CANCELACION_DEVOLUCION' ) then " + 
			"				COALESCE((select tra.ACTIVIDAD from TB_QO_DEVOLUCION devo, TB_QO_TRACKING tra where devo.id = proceso.id_referencia and devo.CODIGO = tra.CODIGO_BPM ORDER BY tra.FECHA_INICIO DESC limit 1), 'NULL')" + 
			"				else case when (proceso.proceso = 'PAGO') then " +
			"					COALESCE((select tra.ACTIVIDAD from TB_QO_TRACKING tra, TB_QO_CLIENTE_PAGO pago where pago.codigo = tra.CODIGO_BPM and pago.id = proceso.id_referencia order by tra.FECHA_INICIO desc limit 1), 'NULL')"+ 
			"						else ' ' end  end end ACTIVIDAD ";
	private final String COUNT_OP = "SELECT  COUNT(case when "+QueryConstantes.WHEN_NEGO+"  then " + 
			"(select cre.ID from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"		(select devo.ID from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
			"		else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"			(select pago.ID from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
			"			else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"				(select veri.ID from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
			"				else 0 end end end end)  ";
	private final String FROM_OP = " FROM tb_qo_proceso proceso ";
	

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
	public TbQoProceso findByIdReferencia(Long id, ProcesoEnum proceso) throws RelativeException {
		try {
			List<TbQoProceso> list = this.findAllBySpecification(new ProcesoByIdReferenciaSpec(id, proceso));
			if (!list.isEmpty()) {
				if (list.size() == 1) {
					return list.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							QuskiOroConstantes.ERROR_MAS_DE_UN_REGISTRO);
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
	@Override
	public Long countOperacion(BusquedaOperacionesWrapper wp) throws RelativeException {
		try {
			String querySelect =  COUNT_OP + FROM_OP;
			StringBuilder strQry = new StringBuilder( querySelect );
			if (wp.getEstado() != null) {
				strQry.append(" where proceso.ESTADO_PROCESO =:estado ");
			} else {
				strQry.append(" where (proceso.ESTADO_PROCESO != 'CANCELADO' and  proceso.ESTADO_PROCESO != 'APROBADO' and proceso.ESTADO_PROCESO != 'RECHAZADO') ");
			}
			if(wp.getAsesor() != null) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+" then " + 
						"	(select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia) " + 
						"	else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"		(select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
						"		else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"			(select pago.ASESOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ) " + 
						"			else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"				(select veri.ASESOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) " + 
						"				else ' ' end end end end ilike :asesor");
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
			if(wp.getCodigoBpm() != null ) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO +" then " + 
						"			(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
						"				else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"					(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
						"						else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"							(select pago.CODIGO from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
						"								else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"									(select veri.CODIGO from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
						"										else ' ' end end end end iLIKE :codigoBpm ");
			}
			if(wp.getCodigoSoft() != null ) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO +" then " + 
						"			(select cre.numero_operacion from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
						"				else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"					(select devo.CODIGO_OPERACION from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)  " + 
						"						else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"							(select pago.CODIGO_OPERACION from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia)   " + 
						"								else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"									(select veri.CODIGO_OPERACION from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
						"										else ' ' end end end end iLIKE :codigosoft ");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if (wp.getAsesor() != null) {
				log.info("=========> SET: ASESOR ==> "+ wp.getAsesor() +" <====");
				query.setParameter("asesor", wp.getAsesor() );
			}
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
			if(wp.getCodigoBpm() != null) {
				log.info("=========> SET: CODIGO BPM ==> "+ wp.getCodigoBpm() +" <====");
				query.setParameter("codigoBpm", "%"+wp.getCodigoBpm()+"%");
			}			
			if(wp.getCodigoSoft() != null) {
				log.info("=========> SET: CODIGO SOFTBANK ==> "+ wp.getCodigoSoft() +" <====");
				query.setParameter("codigosoft", "%"+wp.getCodigoSoft()+"%");
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
			String querySelect = "SELECT "+ 
									ID_OP 		+ "," + 
									CODIGO_BPM 	+ "," + 
									CODIGO_OP 	+ "," + 
									NOMBRE_OP   + "," + 
									CEDULA_OP   + "," +
									MONTO_OP 	+ "," +
									" PROCESO.FECHA_CREACION, " + 
									AGENCIA_OP  + "," +
									" PROCESO.ESTADO_PROCESO, " + 
									" PROCESO.PROCESO, "   		+ 
									ASESOR_OP  	+ "," + 
									" COALESCE(PROCESO.USUARIO, 'NULL') USUARIO "+ "," + 
									ACTIVIDAD_OP+ " " + 
									FROM_OP;
			StringBuilder strQry = new StringBuilder( querySelect );
			if (wp.getEstado() != null) {
				strQry.append(" where proceso.ESTADO_PROCESO =:estado ");
			} else {
				strQry.append(" where (proceso.ESTADO_PROCESO != 'CANCELADO' and  proceso.ESTADO_PROCESO != 'APROBADO' and proceso.ESTADO_PROCESO != 'RECHAZADO') ");
			}
			if(wp.getAsesor() != null) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+" then " + 
						"	(select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia) " + 
						"	else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"		(select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
						"		else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"			(select pago.ASESOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ) " + 
						"			else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"				(select veri.ASESOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) " + 
						"				else ' ' end end end end ilike :asesor");
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
			if(wp.getCodigoBpm() != null ) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO +" then " + 
						"			(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
						"				else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"					(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
						"						else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"							(select pago.CODIGO from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
						"								else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"									(select veri.CODIGO from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
						"										else ' ' end end end end iLIKE :codigoBpm ");
			}
			if(wp.getCodigoSoft() != null ) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO +" then " + 
						"			(select cre.numero_operacion from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
						"				else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"					(select devo.CODIGO_OPERACION from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)  " + 
						"						else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"							(select pago.CODIGO_OPERACION from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
						"								else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"									(select veri.CODIGO_OPERACION from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
						"										else ' ' end end end end iLIKE :codigosoft ");
			}
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if (wp.getAsesor() != null) {
				log.info("=========> SET: ASESOR ==> "+ wp.getAsesor() +" <====");
				query.setParameter("asesor", wp.getAsesor() );
			}
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
			if(wp.getCodigoBpm() != null) {
				log.info("=========> SET: CODIGO BPM ==> "+ wp.getCodigoBpm() +" <====");
				query.setParameter("codigoBpm", "%"+wp.getCodigoBpm()+"%");
			}			
			if(wp.getCodigoSoft() != null) {
				log.info("=========> SET: CODIGO SOFTBANK ==> "+ wp.getCodigoSoft() +" <====");
				query.setParameter("codigosoft", "%"+wp.getCodigoSoft()+"%");
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
			String querySelect = "SELECT " + 
									ID_OP 							+ "," +
									CODIGO_BPM 						+ "," +
									CODIGO_OP 						+ "," +
									" PROCESO.PROCESO " 			+ "," +
									"coalesce( PROCESO.FECHA_ACTUALIZACION, '0001-01-01') FECHA_ACTUALIZACION, "+
									CEDULA_OP 						+ "," +
									NOMBRE_OP 						+ "," +
									AGENCIA_OP 						+ "," +
									ASESOR_OP 						+ "," +
									APROBADOR_OP 					+ "," +
									ORDEN_OP 						+ " " +
									FROM_OP;
			StringBuilder strQry = new StringBuilder(querySelect).append(" WHERE (proceso.ESTADO_PROCESO =:primerEstado or proceso.ESTADO_PROCESO =:segundoEstado or proceso.ESTADO_PROCESO =:tercerEstado ) ");
			if (wp.getCodigo() != null && !wp.getCodigo().equalsIgnoreCase("")) {
				strQry.append(" and case when "+ QueryConstantes.WHEN_NEGO +"  then " + 
						"			(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
						"			else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"				(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
						"				else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"					(select pago.CODIGO from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
						"					else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"						(select veri.CODIGO from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
						"						else ' ' end end end end =:codigo  ");
			}if (wp.getCedula() != null && !wp.getCedula().equalsIgnoreCase("")) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+" then " + 
						"		(select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id )" + 
						"		else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"				(select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia )" + 
						"				else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"						(select pago.CEDULA from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia)" + 
						"						else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"							(select veri.CEDULA_CLIENTE from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia)" + 
						"							else ' ' end end end end =:cedula   ");
			}if(wp.getProceso() != null) {
				strQry.append(" and proceso.PROCESO =:proceso ");
			}if(wp.getIdAgencia() != null && wp.getIdAgencia() != 0) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+"  then " +
						"		(select cre.ID_AGENCIA from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
						"			else case when  "+QueryConstantes.WHEN_DEVO+" then " +
						"				(select devo.ID_AGENCIA from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
						"				else case when "+QueryConstantes.WHEN_PAGO+" then " +
						"					(select pago.ID_AGENCIA from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia)" + 
						"					else case when "+QueryConstantes.WHEN_VERI+" then " +
						"					(select veri.ID_AGENCIA from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia)" + 
						"					else 0 end end end end =:idAgencia ");
			}
			strQry.append(" ORDER BY ORDEN LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			query.setParameter("primerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA.toString() );			
			query.setParameter("tercerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO.toString() );			
			if (wp.getCodigo() != null && !wp.getCodigo().equalsIgnoreCase("")) {
				query.setParameter("codigo", wp.getCodigo() );
			}
			if (wp.getCedula() != null && !wp.getCedula().equalsIgnoreCase("")) {
				query.setParameter("cedula", wp.getCedula() );
			}
			if(wp.getProceso() != null ) {
				query.setParameter("proceso", wp.getProceso().toString() );
			}
			if(wp.getIdAgencia() != null && wp.getIdAgencia() != 0) {
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
			String querySelect =  COUNT_OP + FROM_OP;
			StringBuilder strQry = new StringBuilder( querySelect ).append(" WHERE (proceso.ESTADO_PROCESO =:primerEstado or proceso.ESTADO_PROCESO =:segundoEstado ) ");
			if (wp.getCodigo() != null && !wp.getCodigo().equalsIgnoreCase("")) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+"  then " + 
						"			(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
						"			else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"				(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
						"				else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"					(select pago.CODIGO from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
						"					else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"						(select veri.CODIGO from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
						"						else ' ' end end end end =:codigo  ");
			}	
			if (wp.getCedula() != null && !wp.getCedula().equalsIgnoreCase("")) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+" then " + 
						"		(select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id )" + 
						"		else case when "+QueryConstantes.WHEN_DEVO+" then " + 
						"				(select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia )" + 
						"				else case when "+QueryConstantes.WHEN_PAGO+" then " + 
						"						(select pago.CEDULA from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia)" + 
						"						else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"							(select veri.CEDULA_CLIENTE from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia)" + 
						"							else ' ' end end end end =:cedula   ");
			}	
			if(wp.getProceso() != null) {
				strQry.append(" and proceso.PROCESO =:proceso ");
			} 
			if(wp.getIdAgencia() != null && wp.getIdAgencia() != 0) {
				strQry.append(" and case when "+QueryConstantes.WHEN_NEGO+"  then " +
						"		(select cre.ID_AGENCIA from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia)" + 
						"			else case when  "+QueryConstantes.WHEN_DEVO+" then " +
						"				(select devo.ID_AGENCIA from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia)" + 
						"				else case when "+QueryConstantes.WHEN_PAGO+" then " +
						"					(select pago.ID_AGENCIA from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia)" + 
						"					else case when "+QueryConstantes.WHEN_VERI+" then " + 
						"					(select veri.ID_AGENCIA from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia)" + 
						"					else 0 end end end end =:idAgencia ");
			}
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			query.setParameter("primerEstado",  EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA.toString() );
			if (wp.getCodigo() != null && !wp.getCodigo().equalsIgnoreCase("")) {
				query.setParameter("codigo", wp.getCodigo() );
			}if (wp.getCedula() != null && !wp.getCedula().equalsIgnoreCase("")) {
				query.setParameter("cedula", wp.getCedula() );
			}if(wp.getProceso() != null ) {
				query.setParameter("proceso", wp.getProceso().toString() );
			}if(wp.getIdAgencia() != null && wp.getIdAgencia() != 0) {
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
}
