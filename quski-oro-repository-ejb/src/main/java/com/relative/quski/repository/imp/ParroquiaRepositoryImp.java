package com.relative.quski.repository.imp;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.Parroquia;
import com.relative.quski.repository.ParroquiaRepository;
import com.relative.quski.repository.spec.ParroquiaCantonProvinciaSpec;


/**
 * Session Bean implementation class CantonRepositoryImp
 */
@Stateless(mappedName = "parroquiaRepository")
public class ParroquiaRepositoryImp extends GeneralRepositoryImp<Long, Parroquia> implements ParroquiaRepository {

    /**
     * Default constructor. 
     */
    public ParroquiaRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    

    @SuppressWarnings("unchecked")
	public List<Parroquia> findByCanton( String canton, String order ) throws RelativeException{
    	try {
			Query q = this.getEntityManager().createNativeQuery( "select * from parroquia  "+
					" where trim(canton_id)=:canton and trim(estado)=:estado ORDER BY nombre_canton "+order, Parroquia.class);
			q.setParameter( "canton" , canton.trim());
			q.setParameter( "estado" , "A");
			return q.getResultList();
		} catch (IllegalStateException e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"IllegalStateException findByProvincia " + e.getMessage() );
		} catch (QueryTimeoutException e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"QueryTimeoutException findByProvincia " + e.getMessage() );
		}catch (TransactionRequiredException e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"TransactionRequiredException findByProvincia " + e.getMessage() );
		}catch (PessimisticLockException e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"TransactionRequiredException findByProvincia " + e.getMessage() );
		}catch (LockTimeoutException e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"LockTimeoutException findByProvincia " + e.getMessage() );
		}catch (PersistenceException e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"PersistenceException findByProvincia " + e.getMessage() );
		}catch (Exception e) {
			throw new RelativeException( Constantes.ERROR_CODE_READ,"Exception findByProvincia " + e.getMessage() );
		}
    	
    	 
    	
    }


	

	
	
	public List<Parroquia> findByCantonProvincia( String nombre) {
		return this.findAllBySpecification((new ParroquiaCantonProvinciaSpec(nombre)));
		
	}
}