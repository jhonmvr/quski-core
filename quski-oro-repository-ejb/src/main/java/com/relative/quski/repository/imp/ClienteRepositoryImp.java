package com.relative.quski.repository.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.repository.ClienteRepository;
import com.relative.quski.repository.spec.ClienteByIdentificacionSpec;
import com.relative.quski.repository.spec.ClienteByParamsSpec;

/**
 * Session Bean implementation class ParametrosRepositoryImp
 */
@Stateless(mappedName = "clienteRepository")
public class ClienteRepositoryImp extends GeneralRepositoryImp<Long, TbQoCliente> implements ClienteRepository {
	@Inject
	Logger log;

	@Override
	public List<TbQoCliente> findByParams(PaginatedWrapper pw, String identificacion, String primerNombre,
			String segundoNombre, String apellidoPaterno, String apellidoMaterno, String telefono, String celular,
			String correo, EstadoEnum estado) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre,
						apellidoPaterno, apellidoMaterno, telefono, celular, correo, estado));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(
							new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre, apellidoPaterno,
									apellidoMaterno, telefono, celular, correo, estado),
							pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new ClienteByParamsSpec(identificacion, primerNombre,
							segundoNombre, apellidoPaterno, apellidoMaterno, telefono, celular, correo, estado));
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer clientes, " + e.getMessage());
		}
	}

	@Override
	public Long countByParams(String identificacion, String primerNombre, String segundoNombre, String apellidoPaterno,
			String apellidoMaterno, String telefono, String celular, String correo, EstadoEnum estado)
			throws RelativeException {
		try {
			return this.countBySpecification(new ClienteByParamsSpec(identificacion, primerNombre, segundoNombre,
					apellidoPaterno, apellidoMaterno, telefono, celular, correo, estado));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Conteo de registros cliente, " + e.getMessage());
		}
	}

	@Override
	/**
	 * Método que realiza la búsqueda por identificacion
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param identificacion
	 * @return TbQoCliente
	 */
	public TbQoCliente findClienteByIdentificacion(String identificacion) throws RelativeException {
		try {
			TbQoCliente cliente = new TbQoCliente();
			cliente.setCedulaCliente(identificacion);
			log.info("INGRESA A findClienteByIdentificacion ===> " + identificacion);
			List<TbQoCliente> listCliente = new ArrayList<>();
			
			//listCliente=this.findByParams(null, identificacion, "", "", "", "", "", "", "",EstadoEnum.ACT);
					
			listCliente = this.findAllBySpecification(new ClienteByIdentificacionSpec(identificacion));
			log.info("VALORES QUE RETORNAN=====> "+listCliente.size());
			log.info("NUMERO DE CLIENTES RECUPERADOS>> " + listCliente.toString());
			if (!listCliente.isEmpty()) {
				if (listCliente.size() <= 1) {
					log.info("Retorna el valor de la lista " + listCliente.size());
					cliente=listCliente.get(0);
					return cliente;

				} else {

					// listCliente.add(this.findClienteByIdentificacion(identificacion));
					return new TbQoCliente();				}
			} else {
				log.info("2do ELSE Retorna el valor de la lista " );
				return cliente;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Ocurrio un error al leer cliente, " + e.getMessage());
		}
	}
}
