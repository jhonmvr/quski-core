package com.relative.quski.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.repository.ClienteRepository;
import com.relative.quski.repository.spec.ClienteByParamsSpec;
import com.relative.quski.wrapper.AsignacionesWrapper;
/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "clienteRepository")
public class ClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoCliente> implements ClienteRepository {
	@Override
	public List<TbQoCliente> findByParams(PaginatedWrapper pw, String identificacion, String primerNombre, 
			String segundoNombre, String apellidoPaterno, String apellidoMaterno, 
			String telefono, String celular, String correo,  EstadoEnum estado) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre,
						apellidoPaterno, apellidoMaterno, telefono, celular, correo,  estado));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre,
							apellidoPaterno, apellidoMaterno, telefono, celular, correo,  estado), pw.getStartRecord(), 
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre,
							apellidoPaterno, apellidoMaterno, telefono, celular, correo,  estado));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer clientes, " + e.getMessage());
		}
	}
	
	
	@Override
	public Long countByParams(String identificacion, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, 
			String telefono, String celular, String correo,  EstadoEnum estado) throws RelativeException {
		try {
			return this.countBySpecification(new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre,
					apellidoPaterno, apellidoMaterno, telefono, celular, correo,  estado));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Conteo de registros cliente, " + e.getMessage());
		}
	}


	@Override
	public List<AsignacionesWrapper> clienteBycodigoOperacion(String codigoOperacion) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
}
