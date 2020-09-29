package com.relative.quski.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.wrapper.BloquearFondosWrapper;
import com.relative.quski.wrapper.RegistrarPagoWrapper;

@Stateless
public class PagoService {
	@Inject
	Logger log;
	@Inject
	private QuskiOroService qos;
	@Inject
	private RegistrarPagoRepository registrarPagoRepository;
	
	
	public RegistrarPagoWrapper crearRegistrarPago(RegistrarPagoWrapper registroPago) throws RelativeException {
		try {
			if(registroPago == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL PAGO");
			}
			if(registroPago.getCliente() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE PAGO");
			}
			
			TbQoClientePago cliente = qos.manageClientePago(registroPago.getCliente());
			List<TbQoRegistrarPago> listaPagos = null;
			if(registroPago.getPagos() != null && !registroPago.getPagos().isEmpty()) {
				listaPagos = new ArrayList<>();
				for(TbQoRegistrarPago registro : registroPago.getPagos()) {
					//registro.setComprobante(//llamar al localStorage para q me guarde el archivo)
					registro.setTbQoClientePago(cliente);
					listaPagos.add(qos.manageRegistrarPago(registro));
					
				}
			}
			registroPago.setCliente(cliente);
			registroPago.setPagos(listaPagos);
			return registroPago;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public BloquearFondosWrapper crearBloquearFondos(BloquearFondosWrapper bloquearFondos) throws RelativeException {
		try {
			if(bloquearFondos == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL PAGO");
			}
			if(bloquearFondos.getCliente() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE PAGO");
			}
			
			TbQoClientePago cliente = qos.manageClientePago(bloquearFondos.getCliente());
			List<TbQoRegistrarPago> listaPagos = null;
			if(bloquearFondos.getPagos() != null && !bloquearFondos.getPagos().isEmpty()) {
				listaPagos = new ArrayList<>();
				for(TbQoRegistrarPago registro : bloquearFondos.getPagos()) {
					registro.setTbQoClientePago(cliente);
					listaPagos.add(qos.manageRegistrarPago(registro));
					
				}
			}
			bloquearFondos.setCliente(cliente);
			bloquearFondos.setPagos(listaPagos);
			return bloquearFondos;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public List<TbQoRegistrarPago> findRegistrarPagoByIdClientePago(Long id) throws RelativeException {
		// TODO Auto-generated method stub
		return registrarPagoRepository.findByIdClientePago(id);
	}

	

	public List<TbQoClientePago> findClientePagoByIdClientePago(Long id) throws RelativeException{
		// TODO Auto-generated method stub
		return null;
	}
	

}
