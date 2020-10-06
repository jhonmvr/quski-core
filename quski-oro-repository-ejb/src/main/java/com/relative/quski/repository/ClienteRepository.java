package com.relative.quski.repository;

import java.util.List;

import javax.ejb.Local;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;

@Local
public interface ClienteRepository extends CrudRepository<Long, TbQoCliente> {
	public List<TbQoCliente>  findByParams(PaginatedWrapper pw, String identificacion, String primerNombre, 
			String segundoNombre, String apellidoPaterno, String apellidoMaterno, 
			String telefono, String celular, String correo,  EstadoEnum estado) throws RelativeException;
	
	
	public Long countByParams(String identificacion, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, 
			String telefono, String celular, String correo,  EstadoEnum estado) throws RelativeException;

	public TbQoCliente findClienteByIdentificacion(String identificacion)  throws RelativeException;

}
