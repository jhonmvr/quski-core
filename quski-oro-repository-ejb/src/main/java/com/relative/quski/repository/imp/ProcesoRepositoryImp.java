package com.relative.quski.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.spec.ProcesoByIdSpec;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "procesoRepository")
public class ProcesoRepositoryImp extends GeneralRepositoryImp<Long, TbQoProceso> implements ProcesoRepository  {

    /**
     * Default constructor. 
     */
    public ProcesoRepositoryImp() {
        super();
    }
    

	@Override
	public List<TbQoProceso> findById(long id) throws RelativeException {
		try {
        	return findAllBySpecification(
        			new ProcesoByIdSpec(id));
    	} catch (Exception e) {
    		e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Al buscar proceso por id");
    	}
	}
}
