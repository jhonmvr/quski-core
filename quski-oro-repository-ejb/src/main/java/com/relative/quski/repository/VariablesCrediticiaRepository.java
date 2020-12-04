package com.relative.quski.repository;
import java.util.List;
import javax.ejb.Local;
import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.wrapper.VariableCrediticiaWrapper;
 

@Local
public interface VariablesCrediticiaRepository extends CrudRepository<Long, TbQoVariablesCrediticia> {
	public List<TbQoVariablesCrediticia> findByIdCotizacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections,  Long idCotizador) throws RelativeException ;

	public List<TbQoVariablesCrediticia> findByIdCotizacion(Long idCotizador) throws RelativeException ;
	
	public List<TbQoVariablesCrediticia> findByIdNegociacion(Long idNegociacion) throws RelativeException ;

	public Long countByIdCotizacion(Long idCotizador) throws RelativeException ;
	
	public List<VariableCrediticiaWrapper> findByIdCotizadorCustom(Long idCotizador) throws RelativeException;

	public VariableCrediticiaWrapper findByIdNegociacionAndCodigo(Long idNegociacion, String codigo)throws RelativeException;
}