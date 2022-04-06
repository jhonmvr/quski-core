/**
 * 
 */
package com.relative.quski.repository.imp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.TipoExcepcionEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.ExcepcionRolRepository;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.DevolucionReporteWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;

/**
 * @author KLÉBER GUERRA relative Engine
 *
 */
@Stateless(mappedName = "excepcionRolRepository")
public class ExcepcionRolRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcionRol>
		implements ExcepcionRolRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<ExcepcionRolWrapper> findByRolAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String rol, String identificacion) throws RelativeException {
		try {

			String querySelect = "select ex.id, ex.tipo_excepcion, coalesce(cli.primer_nombre, ' ') as primer_nombre, coalesce(cli.apellido_paterno, ' ') as apellido_paterno, " + 
					"nego.id as id_negociacion, " + 
					"cli.cedula_cliente, cli.nombre_completo, ex.observacion_asesor, " + 
					"ex.estado_excepcion, ex.mensaje_bre, nego.asesor,  " + 
					"coalesce(cre.numero_operacion,' ') as operacion, " + 
					"cre.codigo   from tb_qo_excepcion_rol exrol  " + 
					"inner join tb_qo_excepcion ex on ex.tipo_excepcion = exrol.excepcion  " + 
					"inner join tb_qo_credito_negociacion cre on cre.id_negociacion = ex.id_negociacion " + 
					"inner join tb_qo_negociacion nego on nego.id = ex.id_negociacion " + 
					"inner join tb_qo_cliente cli on cli.id =  nego.id_cliente " + 
					"where 1=1  and ex.estado_excepcion = 'PENDIENTE' ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(identificacion)) {
				strQry.append(" and cli.cedula_cliente  iLIKE :identificacion ");
			}
			if (StringUtils.isNotBlank(rol)) {
				strQry.append(" and exrol.rol  = :rol ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(identificacion)) {
				query.setParameter("identificacion", "%"+ identificacion+"%");
			}
			if (StringUtils.isNotBlank(rol)) {
				query.setParameter("rol", rol);
			}
			query.setFirstResult(startRecord);
			query.setMaxResults(pageSize);	
			return QuskiOroUtil.getResultList(query.getResultList(), ExcepcionRolWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExcepcionRolWrapper> findByRolAndIdentificacion(String rol, String identificacion)
			throws RelativeException {
		try {

			String querySelect = "select ex.id, ex.tipo_excepcion, coalesce(cli.primer_nombre, ' ') as primer_nombre, coalesce(cli.apellido_paterno, ' ') as apellido_paterno, " + 
					"nego.id as id_negociacion, " + 
					"cli.cedula_cliente, cli.nombre_completo, ex.observacion_asesor, " + 
					"ex.estado_excepcion, ex.mensaje_bre, nego.asesor,  " + 
					"coalesce(cre.numero_operacion,' ') as operacion, " + 
					"cre.codigo   from tb_qo_excepcion_rol exrol  " + 
					"inner join tb_qo_excepcion ex on ex.tipo_excepcion = exrol.excepcion  " + 
					"inner join tb_qo_credito_negociacion cre on cre.id_negociacion = ex.id_negociacion " + 
					"inner join tb_qo_negociacion nego on nego.id = ex.id_negociacion " + 
					"inner join tb_qo_cliente cli on cli.id =  nego.id_cliente " + 
					"where 1=1 and ex.estado_excepcion = 'PENDIENTE' ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(identificacion)) {
				strQry.append(" and cli.cedula_cliente  iLIKE :identificacion ");
			}
			if (StringUtils.isNotBlank(rol)) {
				strQry.append(" and exrol.rol  = :rol ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(identificacion)) {
				query.setParameter("identificacion", "%"+ identificacion+"%");
			}
			if (StringUtils.isNotBlank(rol)) {
				query.setParameter("rol", rol);
			}
			return QuskiOroUtil.getResultList(query.getResultList(), ExcepcionRolWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@Override
	public Integer countByRolAndIdentificacion(String rol, String identificacion) throws RelativeException {
		try {

			String querySelect = "select count(*)   from tb_qo_excepcion_rol exrol  " + 
					"inner join tb_qo_excepcion ex on ex.tipo_excepcion = exrol.excepcion  " + 
					"inner join tb_qo_credito_negociacion cre on cre.id_negociacion = ex.id_negociacion " + 
					"inner join tb_qo_negociacion nego on nego.id = ex.id_negociacion " + 
					"inner join tb_qo_cliente cli on cli.id =  nego.id_cliente " + 
					"where 1=1  and ex.estado_excepcion = 'PENDIENTE' ";


			StringBuilder strQry = new StringBuilder(querySelect);
			if (StringUtils.isNotBlank(identificacion)) {
				strQry.append(" and cli.cedula_cliente  iLIKE :identificacion ");
			}
			if (StringUtils.isNotBlank(rol)) {
				strQry.append(" and exrol.rol  = :rol ");
			}
			
			Query query = this.getEntityManager().createNativeQuery(strQry.toString());

			if (StringUtils.isNotBlank(identificacion)) {
				query.setParameter("identificacion", "%"+ identificacion+"%");
			}
			if (StringUtils.isNotBlank(rol)) {
				query.setParameter("rol", rol);
			}
			return ((BigInteger) query.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();

			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL consultar " + e);
		}
	}

	@Override
	public List<String> findCorreoByTipoExcepcion(TipoExcepcionEnum tipoExcepcion) throws RelativeException {
		try {
			List<TbQoExcepcionRol> listRol = null;
			CriteriaBuilder cbb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<String> queryy = cbb.createQuery(String.class);
			Root<TbQoExcepcionRol> pollRol = queryy.from(TbQoExcepcionRol.class);
			queryy.where( cbb.equal(pollRol.get("excepcion"),tipoExcepcion) );
			queryy.select(pollRol.get("correo"));
			TypedQuery<String> createQue = this.getEntityManager().createQuery(queryy);
			return createQue.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR CORREO EXCEPCION");
		}
	}
}
