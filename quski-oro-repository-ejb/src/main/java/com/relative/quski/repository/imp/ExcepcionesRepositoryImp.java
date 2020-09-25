package com.relative.quski.repository.imp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoExcepcione;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.repository.ExcepcionesRepository;
import com.relative.quski.repository.spec.ExcepcionByIdSpec;
import com.relative.quski.repository.spec.ExcepcionesByIdClienteSpec;
import com.relative.quski.repository.spec.ExcepcionesByIdNegociacionSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionAndestadoExcepcionSpec;
import com.relative.quski.repository.spec.ExcepcionesByTipoExcepcionAndIdNegociacionSpec;
import com.relative.quski.wrapper.ExceptionWrapper;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "excepcionesRepository")
public class ExcepcionesRepositoryImp extends GeneralRepositoryImp<Long, TbQoExcepcione> implements ExcepcionesRepository  {
	@Inject
	Logger log;
	String mensaje = "OCURRIO UN ERROR AL LEER LAS EXCEPCIONES";
    /**
     * Default constructor. 
     */
    public ExcepcionesRepositoryImp() {
    	//Void
    }
	@Override
	public TbQoExcepcione findById(Long id) throws RelativeException {
		try {
			List<TbQoExcepcione> listExcepciones = this.findAllBySpecification(new ExcepcionByIdSpec( id ));
			if (!listExcepciones.isEmpty()) {
				if (listExcepciones.size() <= 1) {
					return listExcepciones.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ, "EXISTE MAS DE UNA EXCEPCION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				return null;			
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}
	@Override
	public List<TbQoExcepcione> findByIdNegociacion( int startRecord, Integer pageSize, String sortFields, String sortDirections, Long idNegociacion) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByIdNegociacionSpec( idNegociacion ), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje  + e.getMessage());
		}
	}
	
	@Override
	public List<TbQoExcepcione> findByIdNegociacion( Long idNegociacion) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByIdNegociacionSpec( idNegociacion ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ+ this.mensaje + e.getMessage());
    	}
	}
	@Override
	public Long countByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByIdNegociacionSpec( idNegociacion ));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM + this.mensaje + e.getMessage());
		}
	}
	@Override
	public List<TbQoExcepcione> findByIdCliente(int startRecord, Integer pageSize, String sortFields, String sortDirections, Long idCliente) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByIdClienteSpec( idCliente ), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
		}
	}
	@Override
	public List<TbQoExcepcione> findByIdCliente(Long idCliente) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByIdClienteSpec( idCliente ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ + this.mensaje + e.getMessage());
    	}
	}
	@Override
	public Long countByIdCliente(Long idCliente) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByIdClienteSpec( idCliente ));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " No  se puedieron contar los registros " + e.getMessage());
		}
	}
	@Override
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByTipoExcepcionAndIdNegociacionSpec( tipoExcepcion, idNegociacion ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
    	}
	}
	@Override
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacion(int startRecord, Integer pageSize, String sortFields, String sortDirections, String tipoExcepcion, Long idNegociacion) throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByTipoExcepcionAndIdNegociacionSpec( tipoExcepcion, idNegociacion ), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, " Ocurrio un error al leer Excepciones: "  + e.getMessage());
		}
	}
	@Override
	public Long countByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByTipoExcepcionAndIdNegociacionSpec( tipoExcepcion, idNegociacion ));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " No  se puedieron contar los registros " + e.getMessage());
		}
	}
	@Override
	public TbQoExcepcione findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(Long idNegociacion, String tipoExcepcion,
			 EstadoExcepcionEnum estadoExcepcion) throws RelativeException {
		try {
			List<TbQoExcepcione> listExcepciones = this.findAllBySpecification( new ExcepcionesByTipoExcepcionAndIdNegociacionAndestadoExcepcionSpec( tipoExcepcion,  idNegociacion, estadoExcepcion ) );
			if (!listExcepciones.isEmpty()) {
				if (listExcepciones.size() <= 1) {
					return listExcepciones.get(0);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_READ, "EXISTE MAS DE UNA EXCEPCION, ERROR DE DESARROLLO (IMP)");
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
		}
	}
	@Override
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacionAndCaracteristica(int startRecord, Integer pageSize,
			String sortFields, String sortDirections, String tipoExcepcion, Long idNegociacion, String caracteristica)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(new ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec( tipoExcepcion, idNegociacion, caracteristica ), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, " Ocurrio un error al leer Excepciones: "  + e.getMessage());
		}
	}
	@Override
	public Long countByTipoExcepcionAndIdNegociacionAndCaracteristica(String tipoExcepcion, Long idNegociacion,
			String caracteristica) throws RelativeException {
		try {
			return countBySpecification(new ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec( tipoExcepcion, idNegociacion, caracteristica ));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " No  se puedieron contar los registros " + e.getMessage());
		}
	}
	
	@Override
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacionAndCaracteristica(String tipoExcepcion, Long idNegociacion, String caracteristica) throws RelativeException {
		try {
        	return findAllBySpecification( new ExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristicaSpec( tipoExcepcion, idNegociacion, caracteristica ) );
    	} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer Excepciones: " + e.getMessage());
    	}
	}
	@Override
	public List<ExceptionWrapper> findByRolAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String rol, String identificacion) throws RelativeException {
		try {
			// ~~> QUERY
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ExceptionWrapper> query = cb.createQuery(ExceptionWrapper.class);
			// ~~> FROM
			Root<TbQoExcepcione> poll = query.from(TbQoExcepcione.class);
			Join<TbQoExcepcione, TbQoExcepcionRol> joinExcepcionRol = poll.join("tipoExcepcion");
			Join<TbQoExcepcione, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");
				
			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if(StringUtils.isNotBlank(rol)) {
			    where.add(cb.equal(joinExcepcionRol.get("rol") , rol));
			}

			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));
			
			
			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"),poll.get("tipoException"),joinCliente.get("cedulaCliente"),joinCliente.get("primerNombre"),
					joinCliente.get("apellidoPaterno"));

			// ~~> ORDER BY
			if (sortDirections.equals("asc")) {
				query.orderBy(cb.asc(poll.get(sortFields)));
			} else {
				query.orderBy(cb.desc(poll.get(sortFields)));
			}
			// ~~> EJECUTAR CONSULTA 

			TypedQuery<ExceptionWrapper> createQuery = this.getEntityManager().createQuery(query);
		
				return createQuery.setFirstResult(startRecord).setMaxResults(pageSize).getResultList();
			
			// ~~> FIN
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
		
	}
	@Override
	public List<ExceptionWrapper> findByRolAndIdentificacion(String rol, String identificacion) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ExceptionWrapper> query = cb.createQuery(ExceptionWrapper.class);
			// ~~> FROM
			Root<TbQoExcepcione> poll = query.from(TbQoExcepcione.class);
			Join<TbQoExcepcione, TbQoExcepcionRol> joinExcepcionRol = poll.join("tipoExcepcion");
			Join<TbQoExcepcione, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");
				
			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if(StringUtils.isNotBlank(rol)) {
			    where.add(cb.equal(joinExcepcionRol.get("rol") , rol));
			}

			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));
			
			
			query.where(cb.and(where.toArray(new Predicate[] {})));

			// ~~> SELECT
			query.multiselect(poll.get("id"),poll.get("tipoException"),joinCliente.get("cedulaCliente"),joinCliente.get("primerNombre"),
					joinCliente.get("apellidoPaterno"));

			
			// ~~> EJECUTAR CONSULTA 

			TypedQuery<ExceptionWrapper> createQuery = this.getEntityManager().createQuery(query);

				return createQuery.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException();
		}
	}

	@Override
	public Integer countByRolAndIdentificacion(String rol, String identificacion) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			// ~~> FROM
			Root<TbQoExcepcione> poll = query.from(TbQoExcepcione.class);
			Join<TbQoExcepcione, TbQoExcepcionRol> joinExcepcionRol = poll.join("tipoExcepcion");
			Join<TbQoExcepcione, TbQoNegociacion> joinNegocia = poll.join("tbQoNegociacion");
			Join<TbQoNegociacion, TbQoCliente> joinCliente = joinNegocia.join("tbQoCliente");
				
			// ~~> WHERE
			List<Predicate> where = new ArrayList<>();
			if(StringUtils.isNotBlank(rol)) {
			    where.add(cb.equal(joinExcepcionRol.get("rol") , rol));
			}

			if (StringUtils.isNotBlank(identificacion)) {
				where.add(cb.equal(joinCliente.get("cedulaCliente"), identificacion));
			}
			where.add(cb.equal(poll.get("estadoExcepcion"), EstadoExcepcionEnum.PENDIENTE));
			
			
			query.where(cb.and(where.toArray(new Predicate[] {})));

			query.select(cb.count(poll.get("id")));
			TypedQuery<Long> createQuery = this.getEntityManager().createQuery(query);
			return createQuery.getSingleResult().intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException();
		}
	}
}
