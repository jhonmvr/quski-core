package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.ProcesoByAsesorSpec;
import com.relative.quski.repository.spec.ProcesoByIdNegociacion;
import com.relative.quski.repository.spec.ProcesoByIdReferenciaSpec;
import com.relative.quski.repository.spec.ProcesoByIdSpec;
import com.relative.quski.util.QueryConstantes;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.OpPorAprobarWrapper;
import com.relative.quski.wrapper.OperacionesWrapper;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "procesoRepository")
public class ProcesoRepositoryImp extends GeneralRepositoryImp<Long, TbQoProceso> implements ProcesoRepository {
	@Inject
	Logger log;
	private final String ID_OP = " case when "+QueryConstantes.WHEN_NEGO+"  then " + 
			"(select nego.ID from tb_qo_negociacion nego where nego.id = proceso.id_referencia) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"		(select devo.ID from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
			"		else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"			(select pago.ID from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
			"			else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"				(select veri.ID from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
			"				else 0 end end end end ID ";
	private final String CODIGO_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			"(select cre.CODIGO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	(select devo.CODIGO from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		(select pago.CODIGO from TB_QO_CLIENTE_PAGO pago where pago.id = proceso.id_referencia) " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			(select veri.CODIGO from TB_QO_VERIFICACION_TELEFONICA veri where veri.id = proceso.id_referencia) " + 
			"			else ' ' end end end end CODIGO_OPERACION ";
	private final String CEDULA_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			"(select cli.cedula_cliente from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id ) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	(select devo.CEDULA_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia ) " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		(select pago.CEDULA  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ) " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			(select veri.CEDULA_CLIENTE from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) " + 
			"			else ' ' end end end end cedula_cliente ";
	private final String NOMBRE_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			"(select cli.nombre_completo from tb_qo_negociacion nego, tb_qo_cliente cli where nego.id = proceso.id_referencia and nego.id_cliente = cli.id ) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	(select DEVO.NOMBRE_CLIENTE from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia ) " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		(select pago.NOMBRE_CLIENTE  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ) " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			(select veri.NOMBRE_CLIENTE from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) " + 
			"			else ' ' end end end end nombre_cliente ";
	private final String AGENCIA_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			"(select cre.ID_AGENCIA from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia) " + 
			"else case when  "+QueryConstantes.WHEN_DEVO+"  then " + 
			"	(select devo.ID_AGENCIA from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		(select pago.ID_AGENCIA  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ) " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			(select veri.ID_AGENCIA from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) " + 
			"			else 0 end end end end AGENCIA	";
	private final String ASESOR_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			"(select nego.ASESOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia) " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	(select devo.ASESOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia) " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		(select pago.ASESOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ) " + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			(select veri.ASESOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) " + 
			"			else ' ' end end end end ASESOR ";
	private final String APROBADOR_OP = " case when "+QueryConstantes.WHEN_NEGO+" then " + 
			" COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') " + 
			"else case when "+QueryConstantes.WHEN_DEVO+" then " + 
			"	COALESCE((select devo.APROBADOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia),'null') " + 
			"	else case when "+QueryConstantes.WHEN_PAGO+" then " + 
			"		COALESCE((select pago.APROBADOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ),'null')" + 
			"		else case when "+QueryConstantes.WHEN_VERI+" then " + 
			"			COALESCE((select veri.APROBADOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ),'null') " + 
			"			else ' ' end end end end APROBADOR ";
	private final String ORDEN_OP = " CASE WHEN (proceso.proceso ='NUEVO') THEN" + 
			"			CASE WHEN (PROCESO.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN" + 
			"				CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = 'null') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = '') then 1 ELSE 8 end" + 
			"			ELSE CASE WHEN (PROCESO.ESTADO_PROCESO = 'DEVUELTO') THEN" + 
			"				CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = 'null') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = '') then 2 ELSE 9 end" + 
			"			ELSE 0 END END" + 
			"		ELSE CASE WHEN (proceso.proceso ='RENOVACION') THEN " + 
			"				CASE WHEN (PROCESO.ESTADO_PROCESO = 'PENDIENTE_APROBACION') THEN" + 
			"					CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = 'null') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = '') then 3 ELSE 10 end" + 
			"				ELSE CASE WHEN (PROCESO.ESTADO_PROCESO = 'DEVUELTO') THEN" + 
			"					CASE WHEN  (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = 'null') or (COALESCE((select nego.APROBADOR from tb_qo_negociacion nego where nego.id = proceso.id_referencia),'null') = '') then 4 ELSE 11 end" + 
			"				ELSE 0 END END" + 
			"		ELSE CASE WHEN (proceso.proceso ='PAGO') THEN " + 
			"				CASE WHEN  (COALESCE((select pago.APROBADOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ),'null') = 'null') or (COALESCE((select pago.APROBADOR  from TB_QO_CLIENTE_PAGO pago where pago.ID = proceso.ID_REFERENCIA ),'null') = '') then 5  ELSE 12 end" + 
			"		ELSE CASE WHEN (proceso.proceso ='DEVOLUCION') THEN " + 
			"				CASE WHEN  (COALESCE((select devo.APROBADOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia),'null') = 'null' ) or (COALESCE((select devo.APROBADOR from TB_QO_DEVOLUCION devo where devo.id = proceso.id_referencia),'null') = '' ) then 6  ELSE 13 end" + 
			"		ELSE CASE WHEN (proceso.proceso ='VERIFICACION_TELEFONICA') THEN " + 
			"				CASE WHEN  (COALESCE((select veri.APROBADOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) ,'null') = 'null') or (COALESCE((select veri.APROBADOR from TB_QO_VERIFICACION_TELEFONICA veri where veri.ID = PROCESO.ID_REFERENCIA ) ,'null') = '') then 7  ELSE 14 end" + 
			"		ELSE 0 END END END END END AS orden ";
	private final String MONTO_OP = " case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
			"			COALESCE((select cre.MONTO_PREAPROBADO from tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia),0)" + 
			"			else 0 end MONTO_PREAPROBADO ";
	private final String ACTIVIDAD_OP = " case when (proceso.proceso ='NUEVO' or proceso.proceso ='RENOVACION') then " + 
			"			COALESCE((select tra.ACTIVIDAD from TB_QO_TRACKING tra, tb_qo_negociacion nego, TB_QO_CREDITO_NEGOCIACION cre where cre.CODIGO = tra.CODIGO_BPM and cre.ID_NEGOCIACION = nego.ID and nego.id = proceso.id_referencia ORDER BY tra.FECHA_INICIO DESC limit 1),'null')" + 
			"			else case when (proceso.proceso ='DEVOLUCION') then " + 
			"				COALESCE((select tra.ACTIVIDAD from TB_QO_DEVOLUCION devo, TB_QO_TRACKING tra where devo.id = proceso.id_referencia and devo.CODIGO = tra.CODIGO_BPM ORDER BY tra.FECHA_INICIO DESC limit 1),'null')" + 
			"				else ' ' end  end ACTIVIDAD ";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OperacionesWrapper> findOperacion( BusquedaOperacionesWrapper wp ) throws RelativeException {
		try {
			String querySelect = "SELECT "+ 
									ID_OP 		+ "," + 
									CODIGO_OP 	+ "," + 
									NOMBRE_OP   + "," + 
									CEDULA_OP   + "," +
									MONTO_OP 	+ "," +
									" PROCESO.FECHA_CREACION, " + 
									AGENCIA_OP  + "," +
									" PROCESO.ESTADO_PROCESO, " + 
									" PROCESO.PROCESO, "   		+ 
									ASESOR_OP  	+ "," + 
									" PROCESO.USUARIO, " 		+ 
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
						"				else ' ' end end end end IN (:asesor,:asesorMay) ");
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
			strQry.append(" LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			if (wp.getAsesor() != null) {
				log.info("=========> SET: ASESOR ==> "+ wp.getAsesor() +" <====");
				query.setParameter("asesor", wp.getAsesor() );
				query.setParameter("asesorMay", wp.getAsesor().toUpperCase() );
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

	@SuppressWarnings("unchecked")
	@Override
	public List<OpPorAprobarWrapper> findOperacionPorAprobar(BusquedaPorAprobarWrapper wp) throws RelativeException {
		try {
			String querySelect = "SELECT " + 
									ID_OP 							+ "," + 
									CODIGO_OP 						+ "," +
									" PROCESO.PROCESO " 			+ "," +
									" PROCESO.FECHA_ACTUALIZACION " + "," +
									CEDULA_OP 						+ "," + 
									NOMBRE_OP 						+ "," + 
									AGENCIA_OP 						+ "," +
									ASESOR_OP 						+ "," + 
									APROBADOR_OP 					+ "," + 
									ORDEN_OP 						+ " " + 
									FROM_OP;
			StringBuilder strQry = new StringBuilder(querySelect).append(" WHERE (proceso.ESTADO_PROCESO =:primerEstado or proceso.ESTADO_PROCESO =:segundoEstado ) ");
			
			
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
			strQry.append(" ORDER BY ORDEN LIMIT :limite OFFSET :salto ");
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());
			
			query.setParameter("primerEstado", EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.DEVUELTO.toString() );
			
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
						"				else ' ' end end end end IN (:asesor,:asesorMay) ");
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
			
			if (wp.getAsesor() != null) {
				log.info("=========> SET: ASESOR ==> "+ wp.getAsesor() +" <====");
				query.setParameter("asesor", wp.getAsesor() );
				query.setParameter("asesorMay", wp.getAsesor().toUpperCase() );
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
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
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
			
			query.setParameter("primerEstado", EstadoProcesoEnum.PENDIENTE_APROBACION.toString() );
			query.setParameter("segundoEstado", EstadoProcesoEnum.DEVUELTO.toString() );
			
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
			
			int count = ((BigInteger) query.getSingleResult()).intValue();
			return Long.valueOf( count );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
}
