package com.relative.quski.repository.imp;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoAgencia;
import com.relative.quski.repository.AgenciaRepository;
import com.relative.quski.repository.spec.AgenciaByParamsSpec;

/**
 * Session Bean implementation class AgenciaRepositoryImp
 */
@Stateless(mappedName = "agenciaRepository")
public class AgenciaRepositoryImp extends GeneralRepositoryImp<Long, TbQoAgencia> implements AgenciaRepository  {

    /**
     * Default constructor. 
     */
    public AgenciaRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    public List<TbQoAgencia> findByParams(String nombreAgencia, String direccionAgencia) throws RelativeException{
    	try {
        	return findAllBySpecification(
        			new AgenciaByParamsSpec(nombreAgencia,direccionAgencia));
    	} catch (Exception e) {
    		e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Al buscar agencia por parametros");
    	}
    }
}
