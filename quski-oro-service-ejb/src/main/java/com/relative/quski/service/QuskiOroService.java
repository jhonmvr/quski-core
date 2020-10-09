
package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Transport;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.enums.EmailSecurityTypeEnum;
import com.relative.core.util.mail.EmailDefinition;
import com.relative.core.util.mail.EmailUtil;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.bpms.api.ApiGatewayClient;
import com.relative.quski.bpms.api.CrmApiClient;
import com.relative.quski.bpms.api.IntegracionApiClient;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.enums.SituacionEnum;
import com.relative.quski.enums.TipoRubroEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoArchivoCliente;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoExcepcione;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTipoArchivo;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.ArchivoClienteRepository;
import com.relative.quski.repository.ClientePagoRepository;
import com.relative.quski.repository.ClienteRepository;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.DetalleCreditoRepository;
import com.relative.quski.repository.DireccionClienteRepository;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.ExcepcionRolRepository;
import com.relative.quski.repository.ExcepcionesRepository;
import com.relative.quski.repository.IngresoEgresoClienteRepository;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.PatrimonioRepository;
import com.relative.quski.repository.PrecioOroRepository;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.repository.RiesgoAcumuladoRepository;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.TipoArchivoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.VariablesCrediticiaRepository;
import com.relative.quski.repository.spec.ClienteByIdentificacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AutorizacionBuroWrapper;
import com.relative.quski.wrapper.CalculadoraEntradaWrapper;
import com.relative.quski.wrapper.CalculadoraRespuestaWrapper;
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.CrmEntidadWrapper;
import com.relative.quski.wrapper.CrmGuardarProspectoWrapper;
import com.relative.quski.wrapper.CrmProspectoCortoWrapper;
import com.relative.quski.wrapper.CrmProspectoWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.IntegracionDatosClienteWrapper;
import com.relative.quski.wrapper.IntegracionEntidadWrapper;
import com.relative.quski.wrapper.IntegracionRubroWrapper;
import com.relative.quski.wrapper.IntegracionVariableWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;
import com.relative.quski.wrapper.RespuestaCrearClienteWrapper;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;
import com.relative.quski.wrapper.SoftbankOperacionWrapper;
import com.relative.quski.wrapper.SoftbankRespuestaWrapper;
import com.relative.quski.wrapper.SoftbankRiesgoWrapper;
import com.relative.quski.wrapper.TokenWrapper;

@Stateless
public class QuskiOroService {
	@Inject
	Logger log;
	@Inject
	private CotizadorRepository cotizadorRepository;
	@Inject
	private RiesgoAcumuladoRepository riesgoAcumuladoRepository;
	@Inject
	private DetalleCreditoRepository detalleCreditoRepository;
	@Inject
	private PrecioOroRepository precioOroRepository;
	@Inject
	private CreditoNegociacionRepository creditoNegociacionRepository;
	@Inject
	private ClienteRepository clienteRepository;
	@Inject
	private NegociacionRepository negociacionRepository;
	@Inject
	private VariablesCrediticiaRepository variablesCrediticiaRepository;
	@Inject
	private TasacionRepository tasacionRepository;
	@Inject
	private DocumentoHabilitanteRepository documentoHabilitanteRepository;
	@Inject
	private TipoDocumentoRepository tipoDocumentoRepository;
	@Inject
	private ReferenciaPersonalRepository referenciaPersonalRepository;
	@Inject
	private ArchivoClienteRepository archivoClienteRepository;
	@Inject
	private TipoArchivoRepository tipoArchivoRepository;
	@Inject
	private TrackingRepository trackingRepository;
	@Inject
	private ParametrosSingleton parametrosSingleton;
	@Inject
	private ParametroRepository parametroRepository;
	@Inject
	private IngresoEgresoClienteRepository ingresoEgresoClienteRepository;
	@Inject
	private PatrimonioRepository patrimonioRepository;
	@Inject
	private DireccionClienteRepository direccionClienteRepository;
	@Inject
	private ExcepcionesRepository excepcionesRepository;

	@Inject
	private ClientePagoRepository clientePagoRepository;
	@Inject
	private RegistrarPagoRepository registrarPagoRepository;
	@Inject
	private ExcepcionRolRepository excepcionRolRepository;

	/**
	 * * * * * * * * * * ********************************** * @TBQOCLIENTE
	 */
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCliente findClienteById(Long id) throws RelativeException {
		try {
			return clienteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoCliente> findAllCliente(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.clienteRepository.findAll(TbQoCliente.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.clienteRepository.findAll(TbQoCliente.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.clienteRepository.findAll(TbQoCliente.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws RelativeException
	 */
	public Long countCliente() throws RelativeException {
		try {
			return clienteRepository.countAll(TbQoCliente.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param send
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCliente manageCliente(TbQoCliente send) throws RelativeException {
		try {
			if (send.getCedulaCliente() != null) {
				TbQoCliente persisted = this.clienteRepository.findClienteByIdentificacion(send.getCedulaCliente());
				if (persisted != null && persisted.getId() != null) {
					return this.updateCliente(send, persisted);
				} else {
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					send.setEstado(EstadoEnum.ACT);
					return clienteRepository.add(send);
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * @author Developer Twelve
	 * @param TbQoCliente send
	 * @param TbQoCliente persisted
	 * @return TbQoCliente
	 * @throws RelativeException
	 */
	public TbQoCliente updateCliente(TbQoCliente send, TbQoCliente persisted) throws RelativeException {
		try {
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			if (!StringUtils.isEmpty(send.getActividadEconomica())) {
				persisted.setActividadEconomica(send.getActividadEconomica());
			}
			if (!StringUtils.isEmpty(send.getPrimerNombre())) {
				persisted.setPrimerNombre(send.getPrimerNombre());
			}
			if (!StringUtils.isEmpty(send.getSegundoNombre())) {
				persisted.setSegundoNombre(send.getSegundoNombre());
			}
			if (!StringUtils.isEmpty(send.getApoderadoCliente())) {
				persisted.setApoderadoCliente(send.getApoderadoCliente());
			}
			if (!StringUtils.isEmpty(send.getCampania())) {
				persisted.setCampania(send.getCampania());
			}
			if (!StringUtils.isEmpty(send.getCanalContacto())) {
				persisted.setCanalContacto(send.getCanalContacto());
			}
			if (!StringUtils.isEmpty(send.getEmail())) {
				persisted.setEmail(send.getEmail());
			}
			if (!StringUtils.isEmpty(send.getEstadoCivil())) {
				persisted.setEstadoCivil(send.getEstadoCivil());
			}
			if (!StringUtils.isEmpty(send.getGenero())) {
				persisted.setGenero(send.getGenero());
			}
			if (!StringUtils.isEmpty(send.getLugarNacimiento())) {
				persisted.setLugarNacimiento(send.getLugarNacimiento());
			}
			if (!StringUtils.isEmpty(send.getNivelEducacion())) {
				persisted.setNivelEducacion(send.getNivelEducacion());
			}
			if (!StringUtils.isEmpty(send.getPublicidad())) {
				persisted.setPublicidad(send.getPublicidad());
			}
			if (!StringUtils.isEmpty(send.getSeparacionBienes())) {
				persisted.setSeparacionBienes(send.getSeparacionBienes());
			}
			if (!StringUtils.isEmpty(send.getTelefonoFijo())) {
				persisted.setTelefonoFijo(send.getTelefonoFijo());
			}
			if (!StringUtils.isEmpty(send.getTelefonoMovil())) {
				persisted.setTelefonoMovil(send.getTelefonoMovil());
			}
			if (send.getActividadEconomicaEmpresa() != null) {
				persisted.setActividadEconomicaEmpresa(send.getActividadEconomicaEmpresa());
			}
			if (send.getCargo() != null) {
				persisted.setCargo(send.getCargo());
			}
			if (send.getNombreEmpresa() != null) {
				persisted.setNombreEmpresa(send.getNombreEmpresa());
			}
			if (send.getOcupacion() != null) {
				persisted.setOcupacion(send.getOcupacion());
			}
			if (send.getOrigenIngreso() != null) {
				persisted.setOrigenIngreso(send.getOrigenIngreso());
			}
			if (send.getProfesion() != null) {
				persisted.setProfesion(send.getProfesion());
			}
			if (send.getRelacionDependencia() != null) {
				persisted.setRelacionDependencia(send.getRelacionDependencia());
			}
			return clienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @param cliente
	 * @param direcciones
	 * @return
	 */
	private Map<String, String> createDireccionesCliente(TbQoCliente cliente, List<TbQoDireccionCliente> direcciones) {
		Map<String, String> errores = new HashMap<>();
		if (direcciones != null && !direcciones.isEmpty()) {
			direcciones.forEach(dc -> {
				dc.setTbQoCliente(cliente);
				try {
					this.manageDireccionCliente(dc);
				} catch (RelativeException e) {
					errores.put("direccion-" + dc.getCallePrincipal(), "ERROR REGISTRO DIRRECION" + e.getMensaje());
				}
			});
		}
		return errores;
	}

	/**
	 * 
	 * @param cliente
	 * @param ingresoEgreso
	 * @return
	 */
	private Map<String, String> createIngresoEgresoCliente(TbQoCliente cliente,
			List<TbQoIngresoEgresoCliente> ingresoEgreso) {
		Map<String, String> errores = new HashMap<>();
		if (ingresoEgreso != null && !ingresoEgreso.isEmpty()) {
			ingresoEgreso.forEach(ie -> {
				ie.setTbQoCliente(cliente);
				try {
					this.manageIngresoEgresoCliente(ie);
				} catch (RelativeException e) {
					errores.put("Ingreso Egreso-" + ie.getTbQoCliente(),
							"ERROR REGISTRO INGRESOS EGRESOS " + e.getMensaje());
				}
			});
		}
		return errores;
	}

	/**
	 * 
	 * @param cliente
	 * @param ingresoEgreso
	 * @return
	 */
	private Map<String, String> createPatrimonioCliente(TbQoCliente cliente, List<TbQoPatrimonio> patrimonio) {
		Map<String, String> errores = new HashMap<>();
		if (patrimonio != null && !patrimonio.isEmpty()) {
			patrimonio.forEach(pa -> {
				pa.setTbQoCliente(cliente);
				try {
					this.managePatrimonio(pa);
				} catch (RelativeException e) {
					errores.put("Patrimonio-" + pa.getTbQoCliente(), "ERROR REGISTRO PATRIMONIO" + e.getMensaje());
				}
			});
		}
		return errores;
	}

	/**
	 * 
	 * @param cliente
	 * @param ingresoEgreso
	 * @return
	 */
	private Map<String, String> createReferenciasPersonales(TbQoCliente cliente,
			List<TbQoReferenciaPersonal> referencia) {
		Map<String, String> errores = new HashMap<>();
		if (referencia != null && !referencia.isEmpty()) {
			referencia.forEach(re -> {
				re.setTbQoCliente(cliente);
				try {
					this.manageReferenciaPersonal(re);
				} catch (RelativeException e) {
					errores.put("Patrimonio-" + re.getTbQoCliente(), "Error registro Referencia " + e.getMensaje());
				}
			});
		}
		return errores;
	}

	/**
	 * 
	 * @param cliente
	 * @param idNegociacion
	 * @return
	 * @throws RelativeException
	 */
	public RespuestaCrearClienteWrapper crearCliente(TbQoCliente cliente, Long idNegociacion) throws RelativeException {
		try {
			RespuestaCrearClienteWrapper respuesta = null;
			TbQoCliente locCliente = this.manageCliente(cliente);
			this.crearRelacionesCliente(cliente, locCliente);
			if (idNegociacion != null) {
				respuesta = new RespuestaCrearClienteWrapper();
				respuesta.setIdNegociacion(idNegociacion);
				TbQoCreditoNegociacion credito = creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
				if (credito != null) {
					respuesta.setIdCredito(credito.getId());
					respuesta.setNumeroCreditos(Long.valueOf("1"));
				} else {
					respuesta.setNumeroCreditos(Long.valueOf("0"));
				}
			}
			return respuesta;
		} catch (NumberFormatException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje());
		}

	}

	/**
	 * @param cliente
	 * @param locCliente
	 * @throws RelativeException
	 */
	private void crearRelacionesCliente(TbQoCliente cliente, TbQoCliente locCliente) throws RelativeException {
		try {
			Map<String, String> erroresDireccion;
			erroresDireccion = this.createDireccionesCliente(locCliente, cliente.getTbQoDireccionClientes());
			if (erroresDireccion.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + erroresDireccion.toString());
			}
			Map<String, String> erroresIngresoEgreso = this.createIngresoEgresoCliente(locCliente,
					cliente.getTbQoIngresoEgresoClientes());
			if (erroresIngresoEgreso.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + erroresIngresoEgreso.toString());
			}
			Map<String, String> erroresPatrimonio = this.createPatrimonioCliente(locCliente,
					cliente.getTbQoPatrimonios());
			if (erroresPatrimonio.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + erroresPatrimonio.toString());
			}
			Map<String, String> erroresReferencia = this.createReferenciasPersonales(locCliente,
					cliente.getTbQoReferenciaPersonals());
			if (erroresReferencia.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + erroresReferencia.toString());
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	/**
	 * * * * * * * * * * * @COTIZADOR
	 */

	/**
	 * 
	 * @author Developer Twelve
	 * @param Long id
	 * @return TbQoCotizador
	 * @throws RelativeException
	 */
	public TbQoCotizador findCotizadorById(Long id) throws RelativeException {
		try {
			return cotizadorRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param identificacion
	 * @return {@link TbQoPrecioOro}
	 * @throws RelativeException
	 */
	public TbQoPrecioOro registrarPrecioOroByCotizacion(TbQoPrecioOro po) throws RelativeException {
		TbQoCotizador cot = this.findCotizadorById(po.getTbQoCotizador().getId());
		po.setTbQoCotizador(cot);
		return this.managePrecioOro(po);
	}

	/**
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param id
	 * @return {@link TbQoPrecioOro}
	 * @throws RelativeException
	 */
	public TbQoPrecioOro eliminarPrecioOro(Long id) throws RelativeException {
		TbQoPrecioOro precioOro = this.precioOroRepository.findById(id);
		precioOro.setEstado(EstadoEnum.INA);
		try {
			return this.precioOroRepository.update(precioOro);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE BUSCA
	 * LOS PRECIOS OROS LIGADOS A LA COTIZACION PARA RECUPERAR LOS PRECIOS ORO
	 * 
	 * @param pw
	 * @param idCotizador
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoPrecioOro> findByIdCotizacion(PaginatedWrapper pw, Long idCotizador) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return precioOroRepository.findByIdCotizacion(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), idCotizador);

		} else {
			return precioOroRepository.findByIdCotizacion(idCotizador);
		}
	}

	public Long countByIdCotizacion(Long idCotizador) throws RelativeException {

		return precioOroRepository.countfindByIdCotizacion(idCotizador);
	}

	/**
	 * 
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoCotizador> findAllCotizador(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.cotizadorRepository.findAll(TbQoCotizador.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.cotizadorRepository.findAll(TbQoCotizador.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {

					return this.cotizadorRepository.findAll(TbQoCotizador.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author KLEBER GUERRA - Relative Engine
	 * @param pw
	 * @param cedulaCliente
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoCotizador> listByCliente(PaginatedWrapper pw, String cedulaCliente) throws RelativeException {
		try {

			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

				return cotizadorRepository.findByCliente(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
						pw.getSortDirections(), cedulaCliente);

			} else {
				return cotizadorRepository.findByCliente(cedulaCliente);
			}

		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	public Long countByCliente(String cedulaCliente) throws RelativeException {

		return cotizadorRepository.countByCliente(cedulaCliente);
	}

	/**
	 * 
	 * @param persisted
	 * @return TbQoCotizador
	 * @throws RelativeException
	 */
	private TbQoCotizador crearCodigoCotizacion(TbQoCotizador persisted) throws RelativeException {
		String cod = "COD0000000";
		Long id = persisted.getId();
		log.info("id en crearCodigoCotizacion " + persisted.getId());
		try {
			if (id < 9) {
				cod = "COD000000";
			} else if (id < 99) {
				cod = "COD00000" + id;
			} else if (id < 999) {
				cod = "COD0000" + id;
			} else if (id < 9999) {
				cod = "COD000" + id;
			} else if (id < 99999) {
				cod = "COD00" + id;
			} else if (id < 999999) {
				cod = "COD0" + id;
			} else if (id < 9999999) {
				cod = "COD" + id;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"Error. Codigo de cotizacion supera los 7 digitos numericos");
			}
			persisted.setCodigoCotizacion(cod);
			return this.cotizadorRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCotizador updateCotizador(TbQoCotizador send, TbQoCotizador persisted) throws RelativeException {
		try {
			persisted.setGradoInteres(send.getGradoInteres());
			persisted.setMotivoDeDesestimiento(send.getMotivoDeDesestimiento());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			return this.cotizadorRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @param send
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCotizador manageCotizador(TbQoCotizador send) throws RelativeException {
		try {
			if (send != null && send.getId() != null) {
				TbQoCotizador persisted = this.cotizadorRepository.findById(send.getId());
				return this.updateCotizador(send, persisted);

			} else {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				TbQoCotizador cotSinCotido = this.cotizadorRepository.add(send);
				return crearCodigoCotizacion(cotSinCotido);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws RelativeException
	 */
	public Long countCotizador() throws RelativeException {
		try {
			return cotizadorRepository.countAll(TbQoCotizador.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * * * * * * * * * * * @DETALLE @CREDITO
	 */
	/**
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param PaginatedWrapper pw
	 * @param Long             idCotizador
	 * @return List<TbQoDetalleCredito>
	 * @throws RelativeException
	 */
	public List<TbQoDetalleCredito> listByIdCotizador(PaginatedWrapper pw, Long idCotizador) throws RelativeException {
		try {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.detalleCreditoRepository.findDetalleCreditoByIdCotizador(idCotizador, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.detalleCreditoRepository.findDetalleCreditoByIdCotizador(idCotizador);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param Long idCotizador
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countListByIdCotizador(Long idCotizador) throws RelativeException {
		try {
			return detalleCreditoRepository.countByIdCotizador(idCotizador);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoDetalleCredito findDetalleCreditoById(Long id) throws RelativeException {
		try {
			return detalleCreditoRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoDetalleCredito> findAllDetalleCredito(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.detalleCreditoRepository.findAll(TbQoDetalleCredito.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.detalleCreditoRepository.findAll(TbQoDetalleCredito.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

				} else {
					return this.detalleCreditoRepository.findAll(TbQoDetalleCredito.class, pw.getSortFields(),
							pw.getSortDirections());

				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<TbQoDetalleCredito> manageDetalleCreditos(List<TbQoDetalleCredito> sends) {
		List<TbQoDetalleCredito> persisteds = new ArrayList<>();
		sends.forEach(element -> {
			element.setEstado(EstadoEnum.ACT);
			element.setId(null);
			element.setFechaCreacion(new Date(System.currentTimeMillis()));
			try {
				persisteds.add(this.relacionarCotizadorAndCliente(element));
			} catch (RelativeException e) {
				e.printStackTrace();
			}
		});
		return persisteds;
	}

	/**
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param persisteds
	 * @param element
	 */
	private TbQoDetalleCredito relacionarCotizadorAndCliente(TbQoDetalleCredito element) throws RelativeException {
		try {
			if (element.getTbQoCotizador() != null) {
				this.manageCotizador(element.getTbQoCotizador());
				if (element.getTbQoCotizador().getTbQoCliente() != null) {
					this.manageCliente(element.getTbQoCotizador().getTbQoCliente());
				}
			}
			return this.detalleCreditoRepository.add(element);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoDetalleCredito manageDetalleCredito(TbQoDetalleCredito send) throws RelativeException {
		try {

			TbQoDetalleCredito persisted = null;
			if (send.getId() != null) {
				persisted = this.findDetalleCreditoById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateDetalleCredito(send, persisted);
			} else if (send.getId() == null) {
				send.setEstado(EstadoEnum.ACT);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return detalleCreditoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * 
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */

	public TbQoDetalleCredito updateDetalleCredito(TbQoDetalleCredito send, TbQoDetalleCredito persisted)
			throws RelativeException {
		try {

			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			return detalleCreditoRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countDetalleCredito() throws RelativeException {
		try {
			return detalleCreditoRepository.countAll(TbQoDetalleCredito.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * * * * * * * * * * * @PRECIO ORO
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoPrecioOro findPrecioOroById(Long id) throws RelativeException {
		try {
			return precioOroRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoPrecioOro> findAllPrecioOro(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.precioOroRepository.findAll(TbQoPrecioOro.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.precioOroRepository.findAll(TbQoPrecioOro.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.precioOroRepository.findAll(TbQoPrecioOro.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoPrecioOro managePrecioOro(TbQoPrecioOro send) throws RelativeException {
		try {
			TbQoPrecioOro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.precioOroRepository.findById(send.getId()); // Crear implementacion para solo activos
				return this.updatePrecioOro(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				return precioOroRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @authorSAUL MENDEZ- Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbQoPrecioOro updatePrecioOro(TbQoPrecioOro send, TbQoPrecioOro persisted) throws RelativeException {
		try {
			persisted.setPesoNetoEstimado(send.getPesoNetoEstimado());
			persisted.setPrecio(send.getPrecio());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setTipoOro(send.getTipoOro());
			return precioOroRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countPrecioOro() throws RelativeException {
		try {
			return precioOroRepository.countAll(TbQoPrecioOro.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION Y TAMBIEN AL
	 * CLIENTE.
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param PaginatedWrapper pw
	 * @param String           cedula
	 * @return List<TbQoPrecioOro>
	 * @throws RelativeException
	 */
	public List<TbQoPrecioOro> listByCedula(PaginatedWrapper pw, String cedula) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {

			return precioOroRepository.findByCedula(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), cedula);

		} else {
			return precioOroRepository.findByCedula(cedula);
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param String cedula
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countByCedula(String cedula) throws RelativeException {

		return precioOroRepository.countByCedula(cedula);
	}

	/**
	 * * * * * * * * * * * @VARIABLE @CREDITICIA
	 */
	public List<TbQoVariablesCrediticia> manageListVariablesCrediticias(List<TbQoVariablesCrediticia> send)
			throws RelativeException {
		try {
			List<TbQoVariablesCrediticia> persisted = new ArrayList<>();
			send.forEach(element -> {
				element.setEstado(EstadoEnum.ACT);
				element.setId(null);
				element.setFechaCreacion(new Date(System.currentTimeMillis()));
				if (element.getTbQoCotizador() != null) {
					try {
						persisted.add(this.variablesCrediticiaRepository.add(element));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if( element.getTbQoNegociacion() != null ) {
					try {
						persisted.add(this.variablesCrediticiaRepository.add(element));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return persisted;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		}
	}

	/**
	 ************************************ @Cliente
	 */

	private TbQoCliente createCliente( String cedula ) throws RelativeException{
		try {
			TbQoCliente cliente = this.findClienteByIdentifiacion( cedula );
			if( cliente != null) {
				log.info("CLIENTE EN BASE =================>   "+ cliente +"   <===========");
				return this.manageCliente( cliente );
			} else {
				 cliente = this.clienteSoftToTbQoCliente( this.findClienteSoftbank( cedula ));
				if( cliente != null ) {
					log.info("CLIENTE EN SOFTBANK =============>   "+ cliente +"   <=============");
					return this.manageCliente( cliente );
				} else {
					cliente = this.prospectoCrmToTbQoCliente( this.findProspectoCrm( cedula ));
					if( cliente != null ) {
						log.info("CLIENTE EN CRM ===================>   "+ cliente +"   <==============");
						return this.manageCliente( cliente );
					} else {
						return null;
					}
				}
			}
		}catch( RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_CREATE_CLIENTE + e.getMensaje());
		}
		
	} 
	/**
	 * Metodo que busca al cliente por el numero de cedula del cliente
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @update Jeroham Cadenas - Developer Twelve
	 * @param String identificacion
	 * @return TbQoCliente
	 * @throws RelativeException
	 */
	public TbQoCliente findClienteByIdentifiacion(String identificacion) throws RelativeException {
		try {
			return this.clienteRepository.findClienteByIdentificacion(identificacion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ +" Mi error EN SERVICE -> "+ identificacion);
		}

	}
	/**
	 * METODO QUE BUSCA AL CLIENTE POR IDENTIFICACION
	 * 
	 * @author KLÉBER GUERRA - Relative Engine TbQoCotizador
	 * @param identificacion
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoCliente> findClienteByIdentifiacionList(String identificacion) throws RelativeException {
		List<TbQoCliente> tmp;
		try {
			tmp = this.clienteRepository.findAllBySpecification(new ClienteByIdentificacionSpec(identificacion));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
		return null;

	}

	public Long countClienteByParams(String identificacion, String primerNombre, String apellidoPaterno,
			String segundoNombre, String apellidoMaterno, String telefono, String celular, String correo,
			EstadoEnum estado) throws RelativeException {
		return this.clienteRepository.countByParams(identificacion, primerNombre, apellidoPaterno, segundoNombre,
				apellidoMaterno, telefono, celular, correo, estado);
	}

	/**
	 * 
	 * METODO QUE BUSCA POR MEDIO DE UN PARAMETRO
	 * 
	 * @param pw
	 * @param identificacion
	 * @param primerNombre
	 * @param apellidoPaterno
	 * @param segundoNombre
	 * @param apellidoMaterno
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param estado
	 * @return List<TbQoCliente>
	 * @throws RelativeException
	 */
	public List<TbQoCliente> findClienteByParams(PaginatedWrapper pw, String identificacion, String primerNombre,
			String apellidoPaterno, String segundoNombre, String apellidoMaterno, String telefono, String celular,
			String correo, EstadoEnum estado) throws RelativeException {
		return this.clienteRepository.findByParams(pw, identificacion, primerNombre, apellidoPaterno, segundoNombre,
				apellidoMaterno, telefono, celular, correo, estado);
	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param idCotizador
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoVariablesCrediticia> findVariablesCrediticiaByIdCotizacion(PaginatedWrapper pw, Long idCotizador)
			throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return variablesCrediticiaRepository.findByIdCotizacion(pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections(), idCotizador);
		} else {
			return variablesCrediticiaRepository.findByIdCotizacion(idCotizador);
		}
	}

	/**
	 * METODO QUE CUENTA LAS VARIABLES CREDITICIAS LIGADAS A LA COTIZACION
	 * 
	 * @param pw
	 * @param idCotizador
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 * @{@link returnType}
	 */

	public Long countVariblesCrediticiaByIdCotizacion(Long idCotizador) throws RelativeException {

		return variablesCrediticiaRepository.countByIdCotizacion(idCotizador);

	}

	/**
	 * METODO QUE BUSCA LA VARIABLE CREDITICA POR ID
	 * 
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public TbQoVariablesCrediticia findVariablesCrediticiaById(Long id) throws RelativeException {

		try {
			return variablesCrediticiaRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * METODO QUE DEVUELVE TODAS LAS VARIABLES CREDITICIAS
	 * 
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoVariablesCrediticia> findAllVariablesCrediticias(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.variablesCrediticiaRepository.findAll(TbQoVariablesCrediticia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.variablesCrediticiaRepository.findAll(TbQoVariablesCrediticia.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

			} else {
				return this.variablesCrediticiaRepository.findAll(TbQoVariablesCrediticia.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * METODO QUE CUENTA EL NUMERO DE VARIABLES CREDITICIAS
	 * 
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countVariablesCrediticias() throws RelativeException {
		try {
			return variablesCrediticiaRepository.countAll(TbQoVariablesCrediticia.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	/**
	 * @author Desconocido - Creacion
	 * @editor Jeroham Cadenas - Actualizacion
	 * @param TbQoVariablesCrediticia send
	 * @return TbQoVariablesCrediticia
	 * @throws RelativeException
	 * @comment Metodo crea o actualiza una entidad de variables crediticias
	 * @comment Preguntar antes de editar.
	 */
	public TbQoVariablesCrediticia manageVariablesCrediticia(TbQoVariablesCrediticia send) throws RelativeException {
		try {
			TbQoVariablesCrediticia persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findVariablesCrediticiaById(send.getId());
				return this.updateVariablesCrediticia(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				if (send.getTbQoNegociacion() != null && send.getTbQoNegociacion().getId() == null) {
					send.setTbQoNegociacion(null);
				}
				return variablesCrediticiaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	private TbQoVariablesCrediticia updateVariablesCrediticia(TbQoVariablesCrediticia send,
			TbQoVariablesCrediticia persisted) throws RelativeException {
		try {
			persisted.setNombre(send.getNombre());
			persisted.setValor(send.getValor());
			if (send.getTbQoCotizador() != null) {
				persisted.setTbQoCotizador(send.getTbQoCotizador());
			}
			if (send.getTbQoNegociacion() != null) {
				persisted.setTbQoNegociacion(send.getTbQoNegociacion());
			}
			persisted.setFechaActualizacion(new Date());
			persisted.setEstado(send.getEstado());

			return this.variablesCrediticiaRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * @Comment Busca las variables crediticias relacionadas a una negociacion.
	 * @author Jeroham Cadenas
	 * @param Long idNegociacion
	 * @return List<TbQoVariablesCrediticia>
	 * @throws RelativeException
	 */
	public List<TbQoVariablesCrediticia> findVariablesCrediticiaByIdNegociacion(Long idNegociacion)
			throws RelativeException {
		try {
			return variablesCrediticiaRepository.findByIdNegociacion(idNegociacion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * @author Desconocido - Creacion
	 * @editor Jeroham Cadenas - Actualizacion
	 * @param Long id
	 * @return TbQoNegociacion
	 * @throws RelativeException
	 * @comment Metodo Busca la negociacion y las variables crediticias asociadas
	 * @comment Preguntar antes de editar.
	 */
	public TbQoNegociacion findNegociacionById(Long id) throws RelativeException {

		try {
			return negociacionRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<TbQoNegociacion> findAllNegociacion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.negociacionRepository.findAll(TbQoNegociacion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.negociacionRepository.findAll(TbQoNegociacion.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.negociacionRepository.findAll(TbQoNegociacion.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	public Long countNegociacion() throws RelativeException {
		try {
			return negociacionRepository.countAll(TbQoNegociacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoNegociacion manageNegociacion(TbQoNegociacion send) throws RelativeException {
		try {

			TbQoNegociacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findNegociacionById(send.getId());
				return this.updateNegociacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				send.setSituacion(SituacionEnum.EN_PROCESO);
				if (send.getTbQoCliente().getId() == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Ingrese un id de cliente");
				}
				return negociacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}

	/**
	 * @author Jeroham Cadenas - Actualizacion
	 * @param Long id
	 * @return TbQoNegociacion
	 * @throws RelativeException
	 * @comment Finaliza una negociacion en caso de que sea completado el flujo de
	 *          negociacion.
	 * @comment Preguntar antes de editar.
	 */
	public TbQoNegociacion finalizarNegociacion(TbQoNegociacion send) throws RelativeException {
		try {
			TbQoNegociacion persisted = null;
			persisted = this.findNegociacionById(send.getId());
			persisted.setSituacion(SituacionEnum.FINALIZADO);
			persisted.setFechaActualizacion(new Date(System.currentTimeMillis()));
			return negociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * @author Jeroham Cadenas - Actualizacion
	 * @param Long id
	 * @return TbQoNegociacion
	 * @throws RelativeException
	 * @comment Cancela una negociacion en caso de que se detenga el flujo de
	 *          negociacion.
	 * @comment Preguntar antes de editar.
	 */
	public TbQoNegociacion cancelarNegociacion(TbQoNegociacion send) throws RelativeException {
		try {
			TbQoNegociacion persisted = null;
			persisted = this.findNegociacionById(send.getId());
			persisted.setSituacion(SituacionEnum.CANCELADO);
			persisted.setFechaActualizacion(new Date(System.currentTimeMillis()));
			return negociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	private TbQoNegociacion updateNegociacion(TbQoNegociacion send, TbQoNegociacion persisted)
			throws RelativeException {
		try {
			persisted.setTbQoCliente(send.getTbQoCliente());
			persisted.setEstado(send.getEstado());
			persisted.setIdAsesor(send.getIdAsesor());
			persisted.setSituacion(send.getSituacion());
			persisted.setTipo(send.getTipo());
			persisted.setId(persisted.getId());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return negociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public TbQoTasacion findTasacionById(Long id) throws RelativeException {
		try {
			return tasacionRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public NegociacionWrapper iniciarNegociacion(String cedula, String asesor) throws RelativeException {
		try {
			TbQoCliente cliente = this.createCliente( cedula );
			if( cliente != null) {
				return generarTablasIniciales( cliente, asesor);
			} else {
				return new NegociacionWrapper (false);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM ,QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}
	
	public NegociacionWrapper iniciarNegociacionEquifax(String cedula, String asesor) throws RelativeException {
		try {
			TbQoCliente cliente = this.createClienteFromEquifax( this.traerEntidadPersonaEquifax( cedula ) ); 
			if( cliente != null) {
				return generarTablasIniciales( cliente, asesor);
			} else {
				return new NegociacionWrapper (false);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM ,QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}
	public NegociacionWrapper iniciarNegociacionFromCot(Long id, String asesor) throws RelativeException {
		try {
			TbQoCliente cliente = this.findClienteByCotizador( id );
			if( cliente != null) {
				return generarTablasIniciales( cliente, asesor);
			} else {
				return new NegociacionWrapper (false);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM ,QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}
	private TbQoCliente findClienteByCotizador ( Long id ) throws RelativeException {
		try {
			TbQoCotizador cot = this.findCotizadorById( id );
			if( cot != null ) {
				return this.findClienteByIdentifiacion( cot.getTbQoCliente().getCedulaCliente() );
			} else {
				return null;
			}
		}catch( RelativeException e ) {
			throw new RelativeException(Constantes.ERROR_CODE_READ ,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public NegociacionWrapper traerNegociacionExistente( Long id ) throws RelativeException {
		try {
			NegociacionWrapper tmp = new NegociacionWrapper();
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( id ));
			if(tmp.getCredito() != null) {
				tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( id ));
				tmp.setRiesgos(this.riesgoAcumuladoRepository.findByIdNegociacion( id ) );
				tmp.setJoyas( this.tasacionRepository.findByIdNegociacion( id ));
				tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( id ));
				tmp.setRespuesta(true);
				return tmp;				
			} else {
				return null;
			}
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}

	private NegociacionWrapper generarTablasIniciales(TbQoCliente cliente, String asesor) throws RelativeException{
		try {
			NegociacionWrapper wrapper = new NegociacionWrapper();
			TbQoCreditoNegociacion credito = this.createCredito( cliente, asesor );
			List<SoftbankOperacionWrapper> riesgos = consultarRiesgoSoftbank( cliente.getCedulaCliente() );		
			List<IntegracionVariableWrapper> variables = this.traerVariablesEquifax( credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() );
						
			wrapper.setCredito( credito );
			wrapper.setRiesgos( this.createRiesgoFrontSoftBank( riesgos, credito.getTbQoNegociacion()) );
			wrapper.setVariables( this.createVariablesFromEquifax( variables, credito.getTbQoNegociacion() ) );
			wrapper.setExcepcionBre( this.traerExcepcionEquifax( cliente.getCedulaCliente() ));
			wrapper.setRespuesta(true);
			if(wrapper.getCredito() != null) {
				
				this.guardarProspectoCrm(cliente);
				return wrapper;
			}else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException | UnsupportedEncodingException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());			
		}
		
	}
	private TbQoCreditoNegociacion createCredito( TbQoCliente cl, String asesor) throws RelativeException{
		try {
			if(cl != null ) {
				TbQoNegociacion ng = new TbQoNegociacion();
				ng.setIdAsesor( asesor );
				ng.setTbQoCliente( cl );
				TbQoNegociacion negociacion = this.manageNegociacion( ng );
				if(negociacion != null) {
					TbQoCreditoNegociacion cr = new TbQoCreditoNegociacion();
					cr.setIdUsuario( asesor );
					cr.setTbQoNegociacion( negociacion );
					return this.manageCreditoNegociacion( cr );
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
				}
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);			
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());			
		}
		
		
	}

	public List<TbQoTasacion> findAllTasacion(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.tasacionRepository.findAll(TbQoTasacion.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tasacionRepository.findAll(TbQoTasacion.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tasacionRepository.findAll(TbQoTasacion.class, pw.getSortFields(), pw.getSortDirections());

			}
		}
	}

	public Long countTasacion() throws RelativeException {
		try {
			return tasacionRepository.countAll(TbQoTasacion.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoTasacion manageTasacion(TbQoTasacion send) throws RelativeException {
		try {
			TbQoTasacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTasacionById(send.getId());
				return this.updateTasacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setEstado(EstadoEnum.ACT);
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return tasacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}

	private TbQoTasacion updateTasacion(TbQoTasacion send, TbQoTasacion persisted) throws RelativeException {
		try {
			persisted.setId(send.getId());
			persisted.setDescripcion(send.getDescripcion());
			persisted.setDescuentoPesoPiedra(send.getDescuentoPesoPiedra());
			persisted.setDescuentoSuelda(send.getDescuentoSuelda());
			persisted.setEstadoJoya(send.getEstadoJoya());
			persisted.setNumeroPiezas(send.getNumeroPiezas());
			persisted.setPesoBruto(send.getPesoBruto());
			persisted.setPesoNeto(send.getPesoNeto());
			persisted.setValorAvaluo(send.getValorAvaluo());
			persisted.setValorComercial(send.getValorComercial());
			persisted.setValorOro(send.getValorOro());
			persisted.setValorRealizacion(send.getValorRealizacion());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setTbQoCreditoNegociacion(send.getTbQoCreditoNegociacion());
			return tasacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public AutorizacionBuroWrapper setAutorizacionBuroWrapper(String identificacionCliente, String nombreCliente)
			throws RelativeException {

		AutorizacionBuroWrapper autorizacion = new AutorizacionBuroWrapper();
		autorizacion.setCedulaCliente(identificacionCliente);
		autorizacion.setNombreCliente(nombreCliente);
		autorizacion.setFechaActual(QuskiOroUtil.dateToFullString(new Date()));
		return autorizacion;
	}

	public TbQoDocumentoHabilitante findDocumentoHabilitanteById(Long id) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante findDocumentoHabilitanteByTipoDocumentoReferenciaProceso(Long idTipoDocumento,
			ProcessEnum proceso, Long referencia) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(idTipoDocumento, proceso,
					referencia);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<TbQoDocumentoHabilitante> findAllDocumentoHabilitante(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.documentoHabilitanteRepository.findAll(TbQoDocumentoHabilitante.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.documentoHabilitanteRepository.findAll(TbQoDocumentoHabilitante.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

			} else {
				return this.documentoHabilitanteRepository.findAll(TbQoDocumentoHabilitante.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	public Long countDocumentoHabilitante() throws RelativeException {
		try {
			return documentoHabilitanteRepository.countAll(TbQoDocumentoHabilitante.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante manageDocumentoHabilitante(TbQoDocumentoHabilitante send) throws RelativeException {
		try {
			if (send != null && send.getId() != null) {
				TbQoDocumentoHabilitante persisted;
				persisted = this.findDocumentoHabilitanteById(send.getId());
				return this.updateDocumentoHabilitante(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return documentoHabilitanteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante updateDocumentoHabilitante(TbQoDocumentoHabilitante send,
			TbQoDocumentoHabilitante persisted) throws RelativeException {
		try {
			persisted.setArchivo(send.getArchivo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setNombreArchivo(send.getNombreArchivo());
			persisted.setTbQoTipoDocumento(send.getTbQoTipoDocumento());

			if (send.getTbQoCotizador() != null) {
				persisted.setTbQoCotizador(send.getTbQoCotizador());
			}
			if (send.getTbQoNegociacion() != null) {
				persisted.setTbQoNegociacion(send.getTbQoNegociacion());
			}
			if (send.getTbQoCliente() != null) {
				persisted.setTbQoCliente(send.getTbQoCliente());
			}
			return documentoHabilitanteRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @throws RelativeException
	 */
	public TbQoTipoDocumento findTipoDocumentoById(Long id) throws RelativeException {
		try {
			return tipoDocumentoRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countdocumento() throws RelativeException {
		try {
			return tipoDocumentoRepository.countAll(TbQoTipoDocumento.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * 
	 * 
	 * @throws RelativeException
	 */
	public List<TbQoTipoDocumento> findAllDocumento(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.tipoDocumentoRepository.findAll(TbQoTipoDocumento.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tipoDocumentoRepository.findAll(TbQoTipoDocumento.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

			} else {
				return this.tipoDocumentoRepository.findAll(TbQoTipoDocumento.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	public TbQoTipoDocumento manageDocumento(TbQoTipoDocumento send) throws RelativeException {
		try {
			TbQoTipoDocumento persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoDocumentoById(send.getId());
				return this.updateDocumento(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoDocumentoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	public TbQoTipoDocumento updateDocumento(TbQoTipoDocumento send, TbQoTipoDocumento persisted)
			throws RelativeException {
		try {

			persisted.setDescripcion(send.getDescripcion());
			persisted.setEstado(send.getEstado());
			persisted.setTipoDocumento(send.getTipoDocumento());
			persisted.setFechaCreacion(send.getFechaCreacion());
			persisted.setPlantilla(send.getPlantilla());
			return tipoDocumentoRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante generateDocumentoHabilitante(FileWrapper fw) throws RelativeException {
		TbQoDocumentoHabilitante dhs = null;
		TbQoCliente cl = null;
		TbQoCotizador cz = null;
		TbQoNegociacion ng = null;
		TbQoTipoDocumento td = null;
		TbQoDocumentoHabilitante da = null;
		try {
			if (fw.getProcess() == null || fw.getProcess().equalsIgnoreCase("CLIENTE")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(fw.getRelatedIdStr(),
						Long.valueOf(fw.getTypeAction()), null, null);
			} else if (fw.getProcess().equalsIgnoreCase("COTIZADOR")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(null, null, null,
						Long.valueOf(fw.getTypeAction()));
			} else if (fw.getProcess().equalsIgnoreCase("NEGOCIACION")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(null,
						Long.valueOf(fw.getRelatedIdStr()), null, null);
			}

		} catch (NumberFormatException e) {
			dhs = null;
		}
		da = new TbQoDocumentoHabilitante();
		if (dhs != null) {
			da.setId(dhs.getId());
		}
		td = this.findTipoDocumentoById(Long.valueOf(fw.getTypeAction()));
		da.setTbQoTipoDocumento(td);
		if (fw.getProcess() == null || fw.getProcess().equalsIgnoreCase("CLIENTE")) {
			List<TbQoCliente> clientes = this.findClienteByIdentifiacionList(fw.getRelatedIdStr());

			if (clientes != null && !clientes.isEmpty()) {

				if (!clientes.isEmpty()) {
					da.setTbQoCliente(clientes.get(0));
				} else {
					cl = new TbQoCliente();
					cl.setCedulaCliente(fw.getRelatedIdStr());
					da.setTbQoCliente(this.manageCliente(cl));
				}
			} else if (fw.getProcess().equalsIgnoreCase("COTIZADOR")) {
				cz = this.findCotizadorById(Long.valueOf(fw.getRelatedIdStr()));
				da.setTbQoCotizador(cz);
			} else if (fw.getProcess().equalsIgnoreCase("NEGOCIACION")) {
				ng = this.findNegociacionById(Long.valueOf(fw.getRelatedIdStr()));
				da.setTbQoNegociacion(ng);
			}
		}
		da.setArchivo(fw.getFile());
		da.setEstado(EstadoEnum.ACT);
		da.setFechaCreacion(new Date(System.currentTimeMillis()));
		da.setNombreArchivo(fw.getName());
		return this.manageDocumentoHabilitante(da);
	}

	/**
	 * 
	 * @param identificacionCliente
	 * @param idCotizador
	 * @param idNegociacion
	 * @param idTipoDocumento
	 * @return
	 * @throws RelativeException
	 */
	public TbQoDocumentoHabilitante findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(
			String identificacionCliente, Long idCotizador, Long idNegociacion, Long idTipoDocumento)
			throws RelativeException {
		try {

			return documentoHabilitanteRepository.findByTipoDocumentoAndCliAndCotAndNeg(idTipoDocumento,
					identificacionCliente, idCotizador, idNegociacion);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoCreditoNegociacion findCreditoNegociacionById(Long id) throws RelativeException {
		try {
			return creditoNegociacionRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo q lista todos los Creditos.
	 * 
	 * @param paguinatedWrapper
	 * @param fecha             desde
	 * @param fecha             hasta
	 * @param fecha             desde
	 * @param fecha             hasta
	 * @param codigoOperacion
	 * @param estado
	 * @param identificación    Cliente
	 * @param identificación    Cliente
	 * @return Lista de Creditos
	 * @throws RelativeException
	 * @author Diego Serrano
	 */

	public List<TbQoCreditoNegociacion> findCreditoNegociacionByParams(PaginatedWrapper pw, String fechaDesde,
			String fechaHasta, String codigoOperacion, ProcesoEnum proceso, String identificacion, Long agencia,
			String cliente, EstadoEnum estado) throws RelativeException {

		if (pw == null) {
			return this.creditoNegociacionRepository.findAllBySpecification(new CreditoNegociacionByParamsSpec(
					fechaDesde, fechaHasta, identificacion, agencia, codigoOperacion, proceso, cliente, estado));

		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.creditoNegociacionRepository.findPorCustomFilterCreditos(pw, fechaDesde, fechaHasta,
						identificacion, proceso, codigoOperacion, agencia, cliente, estado);

			} else {
				return this.creditoNegociacionRepository.findAllBySpecification(new CreditoNegociacionByParamsSpec(
						fechaDesde, fechaHasta, identificacion, agencia, codigoOperacion, proceso, cliente, estado));

			}
		}

	}

	public Integer countCreditoNegociacionByParams(String fechaDesde, String fechaHasta, String codigoOperacion,
			ProcesoEnum proceso, String identificacion, Long agencia, String cliente, EstadoEnum estado)
			throws RelativeException {

		return this.creditoNegociacionRepository.countBySpecification(new CreditoNegociacionByParamsSpec(fechaDesde,
				fechaHasta, identificacion, agencia, codigoOperacion, proceso, cliente, estado)).intValue();

	}

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoReferenciaPersonal findReferenciaPersonalById(Long id) throws RelativeException {
		try {
			return referenciaPersonalRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoReferenciaPersonal> findAllReferenciaPersonal(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.referenciaPersonalRepository.findAll(TbQoReferenciaPersonal.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.referenciaPersonalRepository.findAll(TbQoReferenciaPersonal.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

				} else {
					return this.referenciaPersonalRepository.findAll(TbQoReferenciaPersonal.class, pw.getSortFields(),
							pw.getSortDirections());

				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author BRAYAN MONGE - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countReferenciaPersonal() throws RelativeException {
		try {
			return referenciaPersonalRepository.countAll(TbQoReferenciaPersonal.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author BRAYAN MONGE - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoReferenciaPersonal manageReferenciaPersonal(TbQoReferenciaPersonal send) throws RelativeException {
		try {
			TbQoReferenciaPersonal persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.referenciaPersonalRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateReferenciaPersonal(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT.toString());
				return referenciaPersonalRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * Actualiza la Referencia Personal
	 * 
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException
	 */
	public TbQoReferenciaPersonal updateReferenciaPersonal(TbQoReferenciaPersonal send,
			TbQoReferenciaPersonal persisted) throws RelativeException {
		try {
			persisted.setId(send.getId());
			persisted.setNombresCompletos(send.getNombresCompletos());
			persisted.setNombresCompletos(send.getNombresCompletos());
			persisted.setEstado(EstadoEnum.ACT.toString());
			persisted.setParentesco(send.getParentesco());
			persisted.setDireccion(send.getDireccion());
			persisted.setTelefonoMovil(send.getTelefonoMovil());
			persisted.setTelefonoFijo(send.getTelefonoFijo());
			return referenciaPersonalRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * Archivos Cliente
	 * 
	 * @param id
	 * @return
	 * @throws RelativeException
	 */

	public TbQoArchivoCliente findArchivoClienteById(Long id) throws RelativeException {
		try {
			return archivoClienteRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoArchivoCliente> findAllArchivoCliente(PaginatedWrapper pw) throws RelativeException {

		if (pw == null) {
			return this.archivoClienteRepository.findAll(TbQoArchivoCliente.class);
		} else {

			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.archivoClienteRepository.findAll(TbQoArchivoCliente.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.archivoClienteRepository.findAll(TbQoArchivoCliente.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}

	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author BRAYAN MONGE - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */

	public Long countArchivoCliente() throws RelativeException {
		try {
			return archivoClienteRepository.countAll(TbQoArchivoCliente.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author BRAYAN MONGE - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */

	public TbQoArchivoCliente manageArchivoCliente(TbQoArchivoCliente send) throws RelativeException {
		try {
			TbQoArchivoCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.archivoClienteRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateArchivoCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return archivoClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	public TbQoArchivoCliente updateArchivoCliente(TbQoArchivoCliente send, TbQoArchivoCliente persisted)
			throws RelativeException {

		try {
			persisted.setArchivo(send.getArchivo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setNombreArchivo(send.getNombreArchivo());
			persisted.setTbQoTipoArchivo(send.getTbQoTipoArchivo());

			if (send.getTbQoCliente() != null) {
				persisted.setTbQoCliente(send.getTbQoCliente());
			}

			return archivoClienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}

	}

	public TbQoTipoArchivo findTipoArchivoById(Long id) throws RelativeException {
		try {
			return tipoArchivoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<TbQoTipoArchivo> findAllTipoArchivo(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.tipoArchivoRepository.findAll(TbQoTipoArchivo.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.tipoArchivoRepository.findAll(TbQoTipoArchivo.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

				} else {
					return this.tipoArchivoRepository.findAll(TbQoTipoArchivo.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author DIEGO SERRANO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countTipoArchivo() throws RelativeException {
		try {
			return tipoArchivoRepository.countAll(TbQoTipoArchivo.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoTipoArchivo manageTipoArchivo(TbQoTipoArchivo send) throws RelativeException {
		try {
			TbQoTipoArchivo persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoArchivoById(send.getId());
				return this.updateTipoArchivo(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoArchivoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	public TbQoTipoArchivo updateTipoArchivo(TbQoTipoArchivo send, TbQoTipoArchivo persisted) throws RelativeException {
		try {

			persisted.setDescripcion(send.getDescripcion());
			persisted.setEstado(send.getEstado());
			persisted.setTipoArchivo(send.getTipoArchivo());
			persisted.setFechaCreacion(send.getFechaCreacion());
			persisted.setPlantilla(send.getPlantilla());

			return tipoArchivoRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * * * * * * *** * * ** ** * *@Tracking
	 */

	/**
	 * 
	 * @param pw PaginatedWrapper
	 * @return List<TbQoTracking>
	 * @throws RelativeException
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoTracking> findAllTracking(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return trackingRepository.findAll(TbQoTracking.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return trackingRepository.findAll(TbQoTracking.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return trackingRepository.findAll(TbQoTracking.class, pw.getSortFields(), pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param id Long
	 * @return TbQoTracking
	 * @throws RelativeException
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public TbQoTracking findTrackingById(Long id) throws RelativeException {
		try {
			return this.trackingRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @return Long
	 * @throws RelativeException
	 * 
	 */
	public Long countTracking() throws RelativeException {
		try {
			return trackingRepository.countAll(TbQoTracking.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param TbQoTracking send
	 * @return TbQoTracking
	 * @throws RelativeException
	 */
	public TbQoTracking manageTracking(TbQoTracking send) throws RelativeException {
		try {
			TbQoTracking persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTrackingById(send.getId());
				return this.updateTracking(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setEstado(EstadoEnum.ACT.toString());
				if (send.getFechaInicio() != null && send.getFechaFin() != null) {
					send.setTotalTiempo(new Time(QuskiOroUtil.diasFecha(send.getFechaInicio(), send.getFechaFin())));
				}
				return this.trackingRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @param TbQoTracking send
	 * @param TbQoTracking persisted
	 * @return TbQoTracking
	 * @throws RelativeException
	 */
	public TbQoTracking updateTracking(TbQoTracking send, TbQoTracking persisted) throws RelativeException {
		try {
			if (send.getActividad() != null) {
				persisted.setActividad(send.getActividad());
			}
			persisted.setEstado(EstadoEnum.ACT.toString());
			if (send.getSituacion() != null) {
				persisted.setSituacion(send.getSituacion());
			}
			if (send.getFechaInicio() != null) {
				persisted.setFechaInicio(send.getFechaInicio());
			}
			if (send.getFechaAsignacion() != null) {
				persisted.setFechaAsignacion(send.getFechaAsignacion());
			}
			if (send.getFechaInicioAtencion() != null) {
				persisted.setFechaInicioAtencion(send.getFechaInicioAtencion());
			}
			if (send.getFechaFin() != null) {
				persisted.setFechaFin(send.getFechaFin());
			}
			if (send.getObservacion() != null) {
				persisted.setObservacion(send.getObservacion());

			}
			if (send.getFechaInicio() != null && send.getFechaFin() != null) {
				persisted.setTotalTiempo(new Time(QuskiOroUtil.diasFecha(send.getFechaInicio(), send.getFechaFin())));
			}
			if (send.getUsuario() != null) {
				persisted.setUsuario(send.getUsuario());
			}
			return this.trackingRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * PARAMETRO
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbMiParametro findParametroById(Long id) throws RelativeException {
		try {
			return parametroRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * =======
	 * 
	 * /** Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findAllParametro(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.parametroRepository.findAll(TbMiParametro.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.parametroRepository.findAll(TbMiParametro.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.parametroRepository.findAll(TbMiParametro.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbMiParametro manageParametro(TbMiParametro send) throws RelativeException {
		try {

			TbMiParametro persisted = null;

			if (send != null && send.getId() != null) {
				persisted = this.findParametroById(send.getId());
				persisted = this.updateParametro(send, persisted);
				parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
				return persisted;
			} else if (send != null && send.getId() == null) {
				persisted = parametroRepository.add(send);
				parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
				return persisted;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */
	public TbMiParametro updateParametro(TbMiParametro send, TbMiParametro persisted) throws RelativeException {
		try {
			if (send.getNombre() != null)
				persisted.setNombre(send.getNombre());
			if (send.getTipo() != null)
				persisted.setTipo(send.getTipo());
			if (send.getValor() != null)
				persisted.setValor(send.getValor());
			if (send.getCaracteriticaUno() != null)
				persisted.setCaracteriticaUno(send.getCaracteriticaUno());
			if (send.getCaracteristicaDos() != null)
				persisted.setCaracteristicaDos(send.getCaracteristicaDos());
			if (send.getEstado() != null)
				persisted.setEstado(send.getEstado());
			if (send.getOrden() != null)
				persisted.setOrden(send.getOrden());
			return parametroRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public TbMiParametro findByNombre(String nombre) throws RelativeException {
		try {
			TbMiParametro a = parametroRepository.findByNombre(nombre);
			if (a != null) {
				return a;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param nombre
	 * @param tipo
	 * @param ordered
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findByNombreTipoOrdered(String nombre, String tipo, Boolean ordered)
			throws RelativeException {
		try {
			List<TbMiParametro> a = parametroRepository.findByNombreAndTipoOrdered(nombre, tipo, ordered);
			if (a != null) {
				return a;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countParametros(String nombre, String tipo, EstadoEnum estado, String caracteriticaUno,
			String caracteristicaDos) throws RelativeException {
		try {
			return parametroRepository.countByParamPaged(nombre, tipo, estado, caracteriticaUno, caracteristicaDos);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public Long countParametros() throws RelativeException {
		try {
			return parametroRepository.countAll(TbMiParametro.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Buscar parametros por parametros
	 * 
	 * @param nombre
	 * @param tipo
	 * @param estado
	 * @param caracteriticaUno
	 * @param caracteristicaDos
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbMiParametro> findParametroByParam(String nombre, String tipo, EstadoEnum estado,
			String caracteriticaUno, String caracteristicaDos, PaginatedWrapper pw) throws RelativeException {
		try {
			List<TbMiParametro> tmp = parametroRepository.findByParamPaged(nombre, tipo, estado, caracteriticaUno,
					caracteristicaDos, pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections());
			parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
			return tmp;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * ************************** @IngresoEgresoCliente
	 */
	/**
	 * 
	 * @param valueOf
	 * @return
	 * @throws RelativeException
	 */
	public TbQoIngresoEgresoCliente findIngresoEgresoClienteById(Long id) throws RelativeException {
		try {
			return ingresoEgresoClienteRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public List<TbQoIngresoEgresoCliente> manageListIngresoEgresoCliente(List<TbQoIngresoEgresoCliente> send)
			throws RelativeException {
		try {
			List<TbQoIngresoEgresoCliente> persisted = new ArrayList<>();
			send.forEach(element -> {
				element.setEstado(EstadoEnum.ACT);
				element.setId(null);
				try {
					if (element.getTbQoCliente() != null) {
						persisted.add(this.ingresoEgresoClienteRepository.add(element));
					} else {
						throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.FALTA_RELACION);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			return persisted;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		}
	}

	/**
	 * 
	 * @param entidad
	 * @return
	 * @throws RelativeException
	 */
	public TbQoIngresoEgresoCliente manageIngresoEgresoCliente(TbQoIngresoEgresoCliente send) throws RelativeException {
		try {
			TbQoIngresoEgresoCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findIngresoEgresoClienteById(send.getId());
				return this.updateIngresoEgresoCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setEstado(EstadoEnum.ACT);
				return this.ingresoEgresoClienteRepository.add(send);

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException
	 */
	public TbQoIngresoEgresoCliente updateIngresoEgresoCliente(TbQoIngresoEgresoCliente send,
			TbQoIngresoEgresoCliente persisted) throws RelativeException {
		try {
			persisted.setValor(send.getValor());
			persisted.setEsEgreso(send.getEsEgreso());
			persisted.setEsIngreso(send.getEsIngreso());
			persisted.setEstado(EstadoEnum.ACT);
			return this.ingresoEgresoClienteRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * ************************** @Patrimonio
	 */

	/**
	 * 
	 * @param valueOf
	 * @return
	 * @throws RelativeException
	 */
	public TbQoPatrimonio findPatrimonioById(Long id) throws RelativeException {
		try {
			return patrimonioRepository.findById(id);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param entidad
	 * @return
	 * @throws RelativeException
	 */
	public TbQoPatrimonio managePatrimonio(TbQoPatrimonio send) throws RelativeException {
		try {
			TbQoPatrimonio persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findPatrimonioById(send.getId());
				return this.updatePatrimonio(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT.toString());
				return this.patrimonioRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException
	 */
	public TbQoPatrimonio updatePatrimonio(TbQoPatrimonio send, TbQoPatrimonio persisted) throws RelativeException {
		try {
			persisted.setActivos(send.getActivos());
			persisted.setAvaluo(send.getAvaluo());
			persisted.setPasivos(send.getPasivos());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT.toString());
			if (persisted.getActivos() != null && persisted.getPasivos() == null
					&& !persisted.getActivos().equalsIgnoreCase("")) {
				return this.patrimonioRepository.update(persisted);
			} else if (persisted.getActivos() == null && persisted.getPasivos() != null
					&& !persisted.getPasivos().equalsIgnoreCase("")) {
				return this.patrimonioRepository.update(persisted);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * ************************** @DireccionCliente
	 */

	/**
	 * 
	 * @param valueOf
	 * @return
	 * @throws RelativeException
	 */
	public TbQoDireccionCliente findDireccionClienteById(Long id) throws RelativeException {
		try {
			return direccionClienteRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param valueOf
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoDireccionCliente> findDireccionClienteByIdCliente(Long id) throws RelativeException {
		try {
			return direccionClienteRepository.findByIdCliente(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param send
	 * @return
	 * @throws RelativeException
	 */
	public TbQoDireccionCliente manageDireccionCliente(TbQoDireccionCliente send) throws RelativeException {
		try {
			TbQoDireccionCliente persisted = null;
			List<TbQoDireccionCliente> cambioInac = null;
			if (send != null && send.getId() != null) {
				persisted = this.findDireccionClienteById(send.getId());
				cambioInac = this.findDireccionClienteByIdCliente(send.getTbQoCliente().getId());
				this.ponerInactivoDireccionCliente(send, cambioInac);
				return this.updateDireccionCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				cambioInac = this.findDireccionClienteByIdCliente(send.getTbQoCliente().getId());
				this.ponerInactivoDireccionCliente(send, cambioInac);
				send.setEstado(EstadoEnum.ACT.toString());
				return this.direccionClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * @param send
	 * @param cambioInac
	 * @throws RelativeException
	 */
	private void ponerInactivoDireccionCliente(TbQoDireccionCliente send, List<TbQoDireccionCliente> cambioInac)
			throws RelativeException {
		try {
			for (int i = 0; i < cambioInac.size(); i++) {
				if (send.getTipoDireccion().equals(cambioInac.get(i).getTipoDireccion())) {
					cambioInac.get(i).setEstado(EstadoEnum.INA.toString());
					this.direccionClienteRepository.update(cambioInac.get(i));
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMensaje());
		}
	}

	/**
	 * 
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException
	 */
	public TbQoDireccionCliente updateDireccionCliente(TbQoDireccionCliente send, TbQoDireccionCliente persisted)
			throws RelativeException {
		try {
			persisted.setDireccionLegal(send.getDireccionLegal());
			persisted.setDireccionEnvioCorrespondencia(send.getDireccionEnvioCorrespondencia());
			persisted.setTipoDireccion(send.getTipoDireccion());
			persisted.setProvincia(send.getProvincia());
			persisted.setCanton(send.getCanton());
			persisted.setParroquia(send.getParroquia());
			persisted.setBarrio(send.getBarrio());
			persisted.setSector(send.getSector());
			persisted.setCallePrincipal(send.getCallePrincipal());
			persisted.setCalleSegundaria(send.getCalleSegundaria());
			persisted.setNumeracion(send.getNumeracion());
			persisted.setReferenciaUbicacion(send.getReferenciaUbicacion());
			persisted.setTipoVivienda(send.getTipoVivienda());
			persisted.setEstado(EstadoEnum.ACT.toString());
			return this.direccionClienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public List<TbQoDireccionCliente> findDireccionClienteByIdClienteAndTipoDireccion(Long idC, String tipoDireccion)
			throws RelativeException {
		try {
			return direccionClienteRepository.findByIdClienteAndTipoDireccion(idC, tipoDireccion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * **************************************** @RIESGO_ACUMULADO
	 */

	/**
	 * @Comment Busca los Riesgos Acumulados relacionados a un cliente.
	 * @author Jeroham Cadenas
	 * @param PaginatedWrapper pw
	 * @param Long             idCliente
	 * @return List<TbQoRiesgoAcumulado>
	 * @throws RelativeException
	 */
	public List<TbQoRiesgoAcumulado> findRiesgoAcumuladoByIdCliente(PaginatedWrapper pw, Long idCliente)
			throws RelativeException {
		try {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.riesgoAcumuladoRepository.findByIdCliente(idCliente, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.riesgoAcumuladoRepository.findByIdCliente(idCliente);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param Long idCliente
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countRiesgoAcumuladoByIdCliente(Long idCliente) throws RelativeException {
		try {
			return riesgoAcumuladoRepository.countByIdCotizador(idCliente);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<TbQoRiesgoAcumulado> manageListRiesgoAcumulados(List<TbQoRiesgoAcumulado> send)
			throws RelativeException {
		try {
			List<TbQoRiesgoAcumulado> persisted = new ArrayList<>();
			send.forEach(element -> {
				element.setEstado(EstadoEnum.ACT);
				element.setId(null);
				element.setFechaCreacion(new Date(System.currentTimeMillis()));
				try {
					if (element.getTbQoCliente() != null) {
						persisted.add(this.riesgoAcumuladoRepository.add(element));
					} else if( element.getTbQoNegociacion() != null){
						persisted.add(this.riesgoAcumuladoRepository.add(element));
					}else {
						throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.FALTA_RELACION);						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			return persisted;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		}
	}

	/**
	 * **************************************** @Excepciones
	 * 
	 * @throws RelativeException
	 */

	/**
	 * @author Jeroham Cadenas
	 * @param Long id
	 * @return TbQoExcepcione
	 * @throws RelativeException
	 */
	public TbQoExcepcione finExcepcionById(Long id) throws RelativeException {
		try {
			return excepcionesRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param Long   idNegociacion
	 * @param String tipoExcepcion
	 * @param String estadoExcepcion
	 * @return TbQoExcepcione
	 * @throws RelativeException
	 */
	public TbQoExcepcione findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion(Long idNegociacion,
			String tipoExcepcion, String estadoExcepcion) throws RelativeException {
		try {
			if (this.validarTipoExcepcion(tipoExcepcion).equals("true")) {
				EstadoExcepcionEnum[] values = EstadoExcepcionEnum.values();
				EstadoExcepcionEnum enumExc = null;
				for (int i = 0; i < values.length; i++) {
					EstadoExcepcionEnum estadoExcepcionEnum = values[i];
					if (estadoExcepcionEnum.toString().equals(estadoExcepcion)) {
						enumExc = estadoExcepcionEnum;
					}
				}
				return excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(idNegociacion,
						tipoExcepcion, enumExc);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @param String tipoExcepcion
	 * @return String
	 */
	private String validarTipoExcepcion(String tipoExcepcion) {
		try {
			if (tipoExcepcion != null && !tipoExcepcion.isEmpty()) {
				List<TbMiParametro> listTipo = this.parametroRepository.findByNombreAndTipoOrdered("", "TIP-EXC",
						false);
				String[] valor = new String[1];
				valor[0] = " NO EXISTE TIPO DE EXCEPCION ";
				listTipo.forEach(param -> {
					if (param.getNombre().equals(tipoExcepcion)) {
						valor[0] = "true";
					}
				});
				return valor[0];
			} else {
				return " TIPO DE EXCEPCION NO INGRESADA: '" + tipoExcepcion + "'.";
			}
		} catch (RelativeException e) {
			return Constantes.ERROR_CODE_READ + " -- " + QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA;
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param Long             idNegociacion
	 * @param PaginatedWrapper pw
	 * @return List<TbQoExcepcione>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcione> findByIdNegociacion(PaginatedWrapper pw, Long idNegociacion) throws RelativeException {

		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return excepcionesRepository.findByIdNegociacion(pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections(), idNegociacion);
			} else {
				return excepcionesRepository.findByIdNegociacion(idNegociacion);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param Long idNegociacion
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countExcepcionesByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			return excepcionesRepository.countByIdNegociacion(idNegociacion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param Long             idCliente
	 * @param PaginatedWrapper pw
	 * @return List<TbQoExcepcione>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcione> findByIdCliente(PaginatedWrapper pw, Long idCliente) throws RelativeException {

		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return excepcionesRepository.findByIdCliente(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
						pw.getSortDirections(), idCliente);
			} else {
				return excepcionesRepository.findByIdCliente(idCliente);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param Long idCliente
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countExcepcionesByIdCliente(Long idCliente) throws RelativeException {
		try {
			return excepcionesRepository.countByIdCliente(idCliente);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param PaginatedWrapper pw
	 * @param String           tipoExcepcion
	 * @param Long             idNegociacion
	 * @return List<TbQoExcepcione>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacion(PaginatedWrapper pw, String tipoExcepcion,
			Long idNegociacion) throws RelativeException {
		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return excepcionesRepository.findByTipoExcepcionAndIdNegociacion(pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections(), tipoExcepcion, idNegociacion);
			} else {
				return excepcionesRepository.findByTipoExcepcionAndIdNegociacion(tipoExcepcion, idNegociacion);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param String tipoExcepcion
	 * @param Long   idNegociacion
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countExcepcionesByTipoExcepcionAndIdNegociacion(String tipoExcepcion, Long idNegociacion)
			throws RelativeException {
		try {
			return excepcionesRepository.countByTipoExcepcionAndIdNegociacion(tipoExcepcion, idNegociacion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Diego Serrano
	 * @param PaginatedWrapper pw
	 * @param String           tipoExcepcion
	 * @param Long             idNegociacion
	 * @return List<TbQoExcepcione>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcione> findByTipoExcepcionAndIdNegociacionAndCaracteristica(PaginatedWrapper pw,
			String tipoExcepcion, Long idNegociacion, String caracteristica) throws RelativeException {
		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndCaracteristica(pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections(), tipoExcepcion, idNegociacion,
						caracteristica);
			} else {
				return excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndCaracteristica(tipoExcepcion,
						idNegociacion, caracteristica);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas
	 * @param String tipoExcepcion
	 * @param Long   idNegociacion
	 * @return Long
	 * @throws RelativeException
	 */
	public Long countExcepcionesByTipoExcepcionAndIdNegociacionAndCaracteristica(String tipoExcepcion,
			Long idNegociacion, String caracteristica) throws RelativeException {
		try {
			return excepcionesRepository.countByTipoExcepcionAndIdNegociacionAndCaracteristica(tipoExcepcion,
					idNegociacion, caracteristica);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param TbQoExcepcione send
	 * @param TbQoExcepcione persisted
	 * @return TbQoExcepcione
	 * @throws RelativeException
	 */
	private TbQoExcepcione updateExcepcion(TbQoExcepcione send, TbQoExcepcione persisted) throws RelativeException {
		try {
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			EstadoExcepcionEnum[] values = EstadoExcepcionEnum.values();
			for (int i = 0; i < values.length; i++) {
				EstadoExcepcionEnum estadoExcepcionEnum = values[i];
				if (estadoExcepcionEnum.equals(send.getEstadoExcepcion())) {
					persisted.setEstadoExcepcion(estadoExcepcionEnum);
				}
			}
			persisted.setCaracteristica(send.getCaracteristica());
			persisted.setIdAprobador(send.getIdAprobador());
			persisted.setObservacionAprobador(send.getObservacionAprobador());
			return this.excepcionesRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * 
	 * @author Jeroham Cadenas - Developer Twelve
	 * @param TbQoExcepcione send
	 * @return TbQoExcepcione
	 * @throws RelativeException
	 */
	public TbQoExcepcione manageExcepcion(TbQoExcepcione send) throws RelativeException {
		TbQoExcepcione persisted = null;
		try {
			if (send != null && send.getId() != null) {
				log.info("INGRESA AL IF");
				persisted = this.excepcionesRepository.findById(send.getId());
				return this.updateExcepcion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setEstado(EstadoEnum.ACT);
				send.setEstadoExcepcion(EstadoExcepcionEnum.PENDIENTE);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				persisted = this.excepcionesRepository.add(send);
				persisted.setTbQoNegociacion(send.getTbQoNegociacion());
				return persisted;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * * * * * * * * * * ********************************** * @EXCEPCION_ROL
	 */
	/**
	 * Método que realiza la busqueda de la Excepcion Rol por id
	 * 
	 * @author Kléber Guerra Developer Four
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public TbQoExcepcionRol findById(Long id) throws RelativeException {
		try {
			return excepcionRolRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
		}
	}

	/**
	 * Método que busca todos los ExcepcionRol
	 * 
	 * @author Kléber Guerra - Relative Engine
	 * @param pw
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoExcepcionRol> findAllExcepcionRol(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.excepcionRolRepository.findAll(TbQoExcepcionRol.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.excepcionRolRepository.findAll(TbQoExcepcionRol.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.excepcionRolRepository.findAll(TbQoExcepcionRol.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Método que realiza el conteo de los ExcepcionRol
	 * 
	 * @author Kléber Guerra -Relative Engine
	 * @return
	 * @throws RelativeException
	 */
	public Long countExcepcionRol() throws RelativeException {
		try {
			return excepcionRolRepository.countAll(TbQoExcepcionRol.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * Método que realiza la administración del ExcepcionRol
	 * 
	 * @author Kléber Guerra - Relative Engine
	 * @param send
	 * @return
	 * @throws RelativeException
	 */
	public TbQoExcepcionRol manageExcepcionRol(TbQoExcepcionRol send) throws RelativeException {

		try {
			if (send != null && send.getId() != null) {
				TbQoExcepcionRol persisted = this.excepcionRolRepository.findById(send.getId());
				if (persisted != null && persisted.getId() != null) {
					return this.updateExcepcionRol(send, persisted);
				} else {
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					send.setEstado(EstadoEnum.ACT);
					return excepcionRolRepository.add(send);
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

	/**
	 * Método que realiza la actualización de la ExcepcionRol
	 * 
	 * @author Kléber Guerra - Relative Engine
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException
	 */
	public TbQoExcepcionRol updateExcepcionRol(TbQoExcepcionRol send, TbQoExcepcionRol persisted)
			throws RelativeException {
		try {
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setExcepcion(send.getExcepcion());
			if (!StringUtils.isEmpty(send.getRol())) {
				persisted.setRol(send.getRol());
			}
			return excepcionRolRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * @author Kléber Guerra - Relative Engine
	 * @param pw
	 * @param rol
	 * @param identificacion
	 * @return
	 * @throws RelativeException
	 */
	public List<ExcepcionRolWrapper> findExcepcionByRolAndIdentificacion(PaginatedWrapper pw, String rol,
			String identificacion) throws RelativeException {
		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.excepcionRolRepository.findByRolAndIdentificacion(pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections(), rol, identificacion);
			} else {
				return this.excepcionRolRepository.findByRolAndIdentificacion(rol, identificacion);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA+"todos los Abonos " + e.getMessage());
		}
	}

	public Integer countExcepcionByRolAndIdentificacion(String rol, String identificacion) throws RelativeException {
		return this.excepcionRolRepository.countByRolAndIdentificacion(rol, identificacion);
	}

	/**
	 * ******************************* @TASACION
	 */
	/**
	 * 
	 * @param pw
	 * @param idCreditoNegociacion
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoTasacion> findTasacionByIdCreditoNegociacion(PaginatedWrapper pw, Long idCreditoNegociacion)
			throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.tasacionRepository.findByIdCreditoNegociacionPaged(idCreditoNegociacion, pw.getStartRecord(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.tasacionRepository.findByIdCreditoNegociacion(idCreditoNegociacion);
		}
	}

	public Long countTasacionByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException {
		return this.tasacionRepository.countFindByIdCreditoNegociacion(idCreditoNegociacion);
	}
	
	public TbQoTasacion eliminarJoya(Long id) throws RelativeException {
		TbQoTasacion tasacion = this.tasacionRepository.findById(id);
		tasacion.setEstado(EstadoEnum.INA);
		try {
			return this.tasacionRepository.update(tasacion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	
	/*
	 * @author Jeroham Cadenas - Developer Twelve
	 * 
	 * @param PaginatedWrapper pw
	 * 
	 * @param Long idNegociacion
	 * 
	 * @return List<TbQoTasacion>
	 * 
	 * @throws RelativeException
	 */
	public List<TbQoTasacion> findTasacionByIdNegociacion(PaginatedWrapper pw, Long idNegociacion)
			throws RelativeException {
		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tasacionRepository.findByIdNegociacion(idNegociacion, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tasacionRepository.findByIdNegociacion(idNegociacion);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}



	/*
	 * @author Jeroham Cadenas - Developer Twelve
	 * 
	 * @param Long idNegociacion
	 * 
	 * @return Long
	 * 
	 * @throws RelativeException
	 */
	public Long countTasacionByByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			return this.tasacionRepository.countFindByIdNegociacion(idNegociacion);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/**
	 * * * ** * * * * * * * * * * * * * * * * * * * * @SOFTBANK
	 */
	/**
	 * 
	 * @param datosOperacion
	 * @return
	 * @throws RelativeException
	 */
	public CrearOperacionRespuestaWrapper crearOperacion(CrearOperacionEntradaWrapper datosOperacion) throws RelativeException {
		CrearOperacionRespuestaWrapper operacionWrapper = null;
		try {
			operacionWrapper = SoftBankApiClient.callCrearOperacion01Rest(
					QuskiOroConstantes.URLCLOUDSTUDIO + "credito/operacion/crear", "", datosOperacion);
			return operacionWrapper;
		} catch (RelativeException | UnsupportedEncodingException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS);
		}
	}

	/**
	 * 
	 * @param identificacion
	 * @return
	 * @throws RelativeException
	 */
	public SoftbankClienteWrapper findClienteSoftbank(String identificacion) throws RelativeException {
		try {
			SoftbankConsultaWrapper consulta = new SoftbankConsultaWrapper(identificacion);
			SoftbankClienteWrapper persisted = SoftBankApiClient.callConsultaClienteRest(
					QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CONSULTA_CLIENTE, null, consulta);
			log.info("=========> QUE JESTO? ========> "+ persisted.getIdentificacion());
			if(persisted.getIdentificacion() != null) {
				return persisted; 
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}
	public List<SoftbankOperacionWrapper> consultarRiesgoSoftbank(String identificacion) throws RelativeException {
		try {
			SoftbankConsultaWrapper consulta = new SoftbankConsultaWrapper(identificacion);
			SoftbankRiesgoWrapper persisted = SoftBankApiClient.callConsultaRiesgoRest(consulta);
			if(!persisted.getOperaciones().isEmpty()) {
				return persisted.getOperaciones(); 
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	private List<TbQoRiesgoAcumulado> createRiesgoFrontSoftBank( List<SoftbankOperacionWrapper> operaciones, TbQoNegociacion negociacion) throws RelativeException{
		try {
			if(operaciones != null) {
				List<TbQoRiesgoAcumulado> list = new ArrayList<>();
				operaciones.forEach(e->{
					TbQoRiesgoAcumulado r = new TbQoRiesgoAcumulado( negociacion );
					r.setReferencia( e.getReferencia() );
					r.setNumeroOperacion( e.getNumeroOperacion() );
					r.setCodigoCarteraQuski( e.getCodigoCarteraQuski() );
					r.setTipoOperacion( e.getTipoOperacion() );
					r.setEsDemandada( e.getEsDemandada() );
					try {
						r.setFechaEfectiva( QuskiOroUtil.formatSringToDate( e.getFechaEfectiva() ) );
					} catch (RelativeException e1) {
						e1.printStackTrace();
					} 
					try {
						r.setFechaVencimiento( QuskiOroUtil.formatSringToDate( e.getFechaVencimiento() ) );
					} catch (RelativeException e1) {
						e1.printStackTrace();
					}
					r.setInteresMora( e.getInteresMora() );
					r.setSaldo( e.getSaldo() );
					r.setValorAlDia( e.getValorAlDia());
					r.setValorAlDiaMasCuotaActual( e.getValorAlDiaMasCuotaActual() );
					r.setValorProyectadoCuotaActual( e.getValorProyectadoCuotaActual() );
					r.setValorCancelaPrestamo( e.getValorCancelaPrestamo() );
					r.setDiasMoraActual( e.getDiasMoraActual() );
					r.setNumeroCuotasTotales( e.getNumeroCuotasTotales() );
					r.setNumeroCuotasFaltantes( e.getNumeroCuotasFaltantes() );
					r.setEstadoPrimeraCuotaVigente( e.getEstadoPrimeraCuotaVigente() );
					r.setNombreProducto( e.getNombreProducto() );
					r.setPrimeraCuotaVigente( e.getPrimeraCuotaVigente() );
					r.setNumeroGarantiasReales( e.getNumeroGarantiasReales() );
					r.setEstadoOperacion( e.getEstadoOperacion() );
					r.setIdMoneda( e.getIdMoneda() );
					list.add(r);
				});
				return this.manageListRiesgoAcumulados( list );
			}else {
				return null;
			}
			
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
		
	}
	private TbQoCliente clienteSoftToTbQoCliente( SoftbankClienteWrapper s ) throws RelativeException {
		try {
			if(s != null) {
				TbQoCliente c = new TbQoCliente();
				c.setApellidoMaterno( s.getSegundoApellido() );
				c.setApellidoPaterno( s.getPrimerApellido()  );
				c.setCedulaCliente( s.getIdentificacion() );
				c.setEdad( BigDecimal.valueOf( QuskiOroUtil.calculateEdad( QuskiOroUtil.formatSringToDate( s.getFechaNacimiento() )) ));
				c.setEmail( s.getEmail() );
				c.setFechaNacimiento( QuskiOroUtil.formatSringToDate( s.getFechaNacimiento() ) );
				c.setPrimerNombre( s.getPrimerNombre() );
				c.setNacionalidad( s.getIdPaisNacimiento().toString() );
				c.setSegundoNombre(s.getSegundoNombre() );
				c.setNombreCompleto( s.getNombreCompleto() );
				return c;
			} else {
				return null;
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	public SoftbankRespuestaWrapper crearClienteSoftbank(SoftbankClienteWrapper cliente) throws RelativeException {
		try {
			return SoftBankApiClient.callCrearClienteRest(
					QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_CLIENTE, null, cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}

	public SoftbankRespuestaWrapper editarClienteSoftbank(SoftbankClienteWrapper cliente) throws RelativeException {
		try {
			if (!cliente.getIdTipoIdentificacion().equals(0) && cliente.getIdTipoIdentificacion() != null) {
				if (!cliente.getIdentificacion().equals("") && cliente.getIdentificacion() != null) {
					if (!cliente.getCodigoUsuario().equals("") && cliente.getCodigoUsuario() != null) {
						return SoftBankApiClient.callEditarClienteRest(
								QuskiOroConstantes.URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE,null, cliente);
					} else {
						throw new RelativeException(Constantes.ERROR_CODE_UPDATE + "FALTA CODIGO USUARIO");
					}
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_UPDATE + "FALTA IDENTIFICACION");
				}
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_UPDATE + "FALTA ID DE TIPO IDENTIFICACION");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id_pago Pk de la entidad
	 * @return Entidad encontrada
	 * @author Relative Engine
	 * @throws RelativeException
	 */
	public TbQoRegistrarPago findRegistrarPagoById(Long id) throws RelativeException {
		try {
			return registrarPagoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoRegistrarPago> findAllRegistrarPago(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.registrarPagoRepository.findAll(TbQoRegistrarPago.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.registrarPagoRepository.findAll(TbQoRegistrarPago.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.registrarPagoRepository.findAll(TbQoRegistrarPago.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA+
					"TODOS LOS ABONOS" + e.getMessage());
		}
	}
	/**
	 * * * ** * * * * * * * * * * * * * * * * * * * * @APIGATEWAY
	 */
	public CalculadoraRespuestaWrapper simularOfertasCalculadoraPrueba(CalculadoraEntradaWrapper wrapper)
			throws RelativeException {
		try {
			return CalculadoraRespuestaWrapper.generateMockupEstandar();
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}
	// TODO: Testear metodo por conflictos
	public CalculadoraRespuestaWrapper simularOfertasCalculadora(CalculadoraEntradaWrapper wrapper)
			throws RelativeException {
		try {
			wrapper.generateMockup();
			StringBuilder xml = QuskiOroUtil.CalculadoraToStringXml(wrapper);
			TokenWrapper token = ApiGatewayClient.getToken();
			return ApiGatewayClient.callCalculadoraRest(token.getAccessToken(), wrapper);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countRegistrarPago() throws RelativeException {
		try {
			return registrarPagoRepository.countAll(TbQoRegistrarPago.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.CLIENTE_PAGO_NO_ENCONTRADO + e.getMessage());
		}
	}
	/**
	 * @Description Metodo que se encarga de gestionar la entidad sea creacion o
	 *              actualizacion
	 * @author Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoRegistrarPago manageRegistrarPago(TbQoRegistrarPago send) throws RelativeException {
		try {
			if (send != null && send.getId() != null) {
				TbQoRegistrarPago persisted = this.registrarPagoRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				log.info("==================>   Ingresa a actualizacion manageCliente ===================> ");
				return this.updateRegistrarPago(send, persisted);
			} else {
				log.info("==================>   INGRESA A CREACION manageClientePago ===================> ");
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return registrarPagoRepository.add(send);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION+
					" ClientePago" + e.getMessage());
		}
	}
	/**
	 * @author
	 * @param TbQoRegistrarPago send
	 * @param TbQoRegistrarPago persisted
	 * @return TbQoRegistrarPago
	 * @throws RelativeException
	 */
	public TbQoRegistrarPago updateRegistrarPago(TbQoRegistrarPago send, TbQoRegistrarPago persisted)
			throws RelativeException {
		try {
			return registrarPagoRepository.update(persisted);
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					" ERROR ACTUALIZANDO ClientePago ===> " + e.getMessage());

		}
	}
	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id_pago Pk de la entidad
	 * @return Entidad encontrada
	 * @author Relative Engine
	 * @throws RelativeException
	 */
	public TbQoClientePago findClientePagoById(Long id) throws RelativeException {
		try {
			return clientePagoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoClientePago> findAllClientePago(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.clientePagoRepository.findAll(TbQoClientePago.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.clientePagoRepository.findAll(TbQoClientePago.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.clientePagoRepository.findAll(TbQoClientePago.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countClientePago() throws RelativeException {
		try {
			return clientePagoRepository.countAll(TbQoClientePago.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.CLIENTE_PAGO_NO_ENCONTRADO + e.getMessage());
		}

	}
	/**
	 * @Description Metodo que se encarga de gestionar la entidad sea creacion o
	 *              actualizacion
	 * 
	 * @author Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoClientePago manageClientePago(TbQoClientePago send) throws RelativeException {
		try {
			if (send.getId() != null) {
				TbQoClientePago persisted = this.clientePagoRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				log.info("==================>   Ingresa a actualizacion manageCliente ===================> ");
				return this.updateClientePago(send, persisted);
			} else {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				TbQoClientePago persisted = this.clientePagoRepository.add(send);
				return persisted;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					 QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION +"CLIENTE PAGO" + e.getMessage());
		}
	}

	/**
	 * @author
	 * @param TbQoClientePago send
	 * @param TbQoClientePago persisted
	 * @return TbQoClientePago
	 * @throws RelativeException
	 */
	public TbQoClientePago updateClientePago(TbQoClientePago send, TbQoClientePago persisted) throws RelativeException {
		try {

			return clientePagoRepository.update(persisted);
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					" ERROR ACTUALIZANDO ClientePago ===> " + e.getMessage());

		}
	}
	/** ********************************************** @CREDITONEGOCIACION */
	public TbQoCreditoNegociacion manageCreditoNegociacion(TbQoCreditoNegociacion send) throws RelativeException {
		try {
			TbQoCreditoNegociacion persisted = null;
			if (send.getId() != null) {
				persisted = this.creditoNegociacionRepository.findById(send.getId());
				return this.updateCreditoNegociacion(send, persisted);
			} else if (send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				send.setEstado( EstadoEnum.ACT);
				send.setSituacion( SituacionEnum.EN_PROCESO);
				return crearCodigoCreditoNuevo( this.creditoNegociacionRepository.add(send) );
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}
	private TbQoCreditoNegociacion crearCodigoCreditoNuevo(TbQoCreditoNegociacion persisted) throws RelativeException {
		String cod = "NUEV0000000";
		Long id = persisted.getId();
		log.info(" ID EN crearCodigoCotizacion =======================> " + persisted.getId()+ " <============== ");
		try {
			if (id < 9) {
				cod = "NUEV000000";
			} else if (id < 99) {
				cod = "NUEV00000" + id;
			} else if (id < 999) {
				cod = "NUEV0000" + id;
			} else if (id < 9999) {
				cod = "NUEV000" + id;
			} else if (id < 99999) {
				cod = "NUEV00" + id;
			} else if (id < 999999) {
				cod = "NUEV0" + id;
			} else if (id < 9999999) {
				cod = "NUEV" + id;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"Error. Codigo de Credito supera los 7 digitos numericos");
			}
			persisted.setCodigo(cod);
			return this.creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	private TbQoCreditoNegociacion updateCreditoNegociacion(TbQoCreditoNegociacion send, TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			return this.creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	/** ********************w************************** @CRM ****************************************************/
	public CrmProspectoCortoWrapper findProspectoCrm(String cedula) throws RelativeException {
		try {
			CrmProspectoCortoWrapper prospecto = CrmApiClient.callConsultaProspectoRest( cedula );
			if(prospecto != null) {
				return prospecto; 
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}
	private TbQoCliente prospectoCrmToTbQoCliente( CrmProspectoCortoWrapper p ) {
		if( p != null ) {
			TbQoCliente c = new TbQoCliente();
			c.setCedulaCliente( p.getCedula() );
			c.setNombreCompleto( p.getNombreCompleto() );
			c.setEmail( p.getEmail() );
			c.setTelefonoAdicional( p.getPhoneOther() );
			c.setTelefonoFijo( p.getPhoneHome() );
			c.setTelefonoMovil( p.getPhoneMobile() );
			c.setTelefonoTrabajo( p.getPhoneWork() );
			return c;
		} else {
			return null; 
		}
	}
	private Boolean guardarProspectoCrm( TbQoCliente cliente ) throws RelativeException, UnsupportedEncodingException {
		try {
			CrmEntidadWrapper entidad = new CrmEntidadWrapper();
			entidad.setCedulaC( cliente.getCedulaCliente() );
			entidad.setEmailAddress( cliente.getEmail() );
			entidad.setEmailAddressCaps( cliente.getEmail().toUpperCase() );
			entidad.setFirstName( cliente.getNombreCompleto() );
			entidad.setLeadSourceDescription( "GESTION QUSKI");
			entidad.setPhoneMobile( cliente.getTelefonoMovil() );
			entidad.setPhoneHome( cliente.getTelefonoFijo() );
			CrmGuardarProspectoWrapper tmp = new CrmGuardarProspectoWrapper( entidad );
			CrmProspectoWrapper pro = CrmApiClient.callPersistProspectoRest(tmp);
			return pro != null ? Boolean.TRUE : Boolean.FALSE ;
		}catch(RelativeException e ) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException(Constantes.ERROR_CODE_CREATE);
		}
	}
	
	/** ********************************************* @INTEGRACION ****************************************************/
	
	public TbQoCliente createClienteFromEquifax ( IntegracionEntidadWrapper entidad) throws RelativeException{
		if(entidad != null) {
			IntegracionDatosClienteWrapper datos = entidad.getDatoscliente();
			List<IntegracionRubroWrapper> rubros = entidad.getIngresosegresos().getRubros();
			if( datos!= null) {
				TbQoCliente c = new TbQoCliente();
				c.setCedulaCliente( datos.getIdentificacion().toString() );
				c.setNombreCompleto( datos.getNombrescompletos() );
				c.setNacionalidad( datos.getNacionalidad() );
				c.setEmail( datos.getCorreoelectronico() );
				c.setCargo( datos.getCargo() );
				c.setCargasFamiliares( BigDecimal.valueOf( datos.getCargasfamiliares() )  );
				c.setProfesion( datos.getProfesion() );
				c = this.manageCliente( c );
				if( c.getId() != null && !rubros.isEmpty() ) {
					this.createIngresosEgresosFromEquifax( rubros, c );
				}
	            return c;
			}else {
				return null;
			}
		} else {
			return null;
		}
	}
	private List<TbQoIngresoEgresoCliente> createIngresosEgresosFromEquifax( List<IntegracionRubroWrapper> rubros, TbQoCliente cliente) throws RelativeException{
		List<TbQoIngresoEgresoCliente> list = new ArrayList<>();
		if(rubros != null) {
			rubros.forEach(e->{
				TbQoIngresoEgresoCliente r = new TbQoIngresoEgresoCliente( cliente );
				r.setValor( e.getValor() );
				if( e.getTiporubroeconomico().equalsIgnoreCase( TipoRubroEnum.EGR.toString() )) {
					r.setEsEgreso( true );
					r.setEsIngreso( false );
				}else if(  e.getTiporubroeconomico().equalsIgnoreCase( TipoRubroEnum.ING.toString() ) ) {
					r.setEsEgreso( false );
					r.setEsIngreso( true );
				} else {
					r.setEsEgreso( false );
					r.setEsIngreso( false );
				}
				list.add(r);
			});
			return this.manageListIngresoEgresoCliente(list);
		}else {
			return null;
		}
	}
	private IntegracionEntidadWrapper traerEntidadPersonaEquifax( String cedula ) throws RelativeException{
		try {
			IntegracionEntidadWrapper data = IntegracionApiClient.callPersonaRest(cedula); 
			return data != null ? data : null; 
		} catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	private List<IntegracionVariableWrapper> traerVariablesEquifax( String cedula ) throws RelativeException{
		try {
			IntegracionEntidadWrapper data = IntegracionApiClient.callPersonaRest(cedula); 
			if(data != null) {
				List<IntegracionVariableWrapper> list = data.getXmlVariablesInternas().getVariablesInternas().getVariables();
				return !list.isEmpty() ? list : null; 
			} else {
				return null;
			}
		} catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	private List<TbQoVariablesCrediticia> createVariablesFromEquifax( List<IntegracionVariableWrapper> equifax, TbQoNegociacion negociacion ) throws RelativeException{
		try {
			if( equifax != null ) {
				List<TbQoVariablesCrediticia> list = new ArrayList<>();
				equifax.forEach(e->{
					TbQoVariablesCrediticia v = new TbQoVariablesCrediticia(negociacion);
					v.setCodigo( e.getCodigo() );
					v.setNombre( e.getNombre() );
					v.setOrden( e.getOrden().toString() );
					v.setValor( e.getValor() );
					list.add( v );
				});
				return this.manageListVariablesCrediticias( list );
			}else {
				return null;
			}
		} catch( RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	private String traerExcepcionEquifax( String cedula ) throws RelativeException{
		try {
			IntegracionEntidadWrapper data = IntegracionApiClient.callPersonaRest(cedula); 
			if(data != null) {
				if(data.getCodigoError() == 3) {
					return data.getMensaje();
				}else { 
					return null;
				}
			} else {
				return null;
			}
			
		} catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	
	public void mailNotificacion(String[] para,String asunto,String contenido,Map<String,byte[]> adjunto) throws RelativeException {
		try {
			log.info("=====>>> ENTRA A NOTIFICACIONES MAIL");
			log.info("=====>>> PARA: "+para);
			log.info("=====>>> ASUNTO: "+asunto);
			log.info("=====>>> CONTENIDO: "+contenido);
			log.info("=====>>> ADJUNTO: "+adjunto);
			log.info("=====>>> Inicializa mail");
			String emailSecurityType = this.parametroRepository.findByNombre(QuskiOroConstantes.emailSecurityType).getValor();
			String smtpHostServer =	this.parametroRepository.findByNombre(QuskiOroConstantes.smtpHostServer).getValor();
			String portEmail=this.parametroRepository.findByNombre(QuskiOroConstantes.portEmail).getValor();
			String sfPortEmail=this.parametroRepository.findByNombre(QuskiOroConstantes.sfPortEmail).getValor();
			String userEmail=this.parametroRepository.findByNombre(QuskiOroConstantes.userEmail).getValor();
			String fromEmailDesa=this.parametroRepository.findByNombre(QuskiOroConstantes.fromEmailDesa).getValor();
			String authEmail=this.parametroRepository.findByNombre(QuskiOroConstantes.authEmail).getValor();
			String passwordEmail=this.parametroRepository.findByNombre(QuskiOroConstantes.passwordEmail).getValor();
			 log.info("parametro email emailSecurityType ===> "+emailSecurityType);
			 log.info("parametro email smtpHostServer    ===> "+smtpHostServer);
			 log.info("parametro email portEmail         ===> "+portEmail);
			 log.info("parametro email sfPortEmail       ===> "+sfPortEmail);
			 log.info("parametro email userEmail         ===> "+userEmail);
			 log.info("parametro email fromEmailDesa     ===> "+fromEmailDesa);
			 log.info("parametro email authEmail         ===> "+authEmail);
			 log.info("parametro email passwordEmail     ===> "+passwordEmail);
			 if(adjunto != null) {
				 EmailDefinition ed = new EmailDefinition.Builder()
						  .emailSecurityType(QuskiOroUtil.getEnumFromString(EmailSecurityTypeEnum.class, emailSecurityType))
						  .smtpHostServer(smtpHostServer) .port(portEmail) .sfPort(sfPortEmail)
						  .auth(StringUtils.isNotBlank(authEmail) && authEmail =="TRUE") .password(passwordEmail)
						  .user(userEmail) .subject(asunto) .tos( Arrays.asList(para)
						  ) .fromEmail( fromEmailDesa ) .message( contenido )
						  .hasFiles(Boolean.TRUE).attachments(adjunto).build();
						  ed.setSession( EmailUtil.provideSession(ed, EmailSecurityTypeEnum.SSL) );
						  Transport.send(null, null, passwordEmail, passwordEmail);
						  EmailUtil.sendEmail( ed );  
			 }else {
				 EmailDefinition ed = new EmailDefinition.Builder()
						  .emailSecurityType(QuskiOroUtil.getEnumFromString(EmailSecurityTypeEnum.class, emailSecurityType))
						  .smtpHostServer(smtpHostServer) .port(portEmail) .sfPort(sfPortEmail)
						  .auth(StringUtils.isNotBlank(authEmail) && authEmail.equalsIgnoreCase("TRUE") ).password(passwordEmail)
						  .user(userEmail).subject(asunto).tos( Arrays.asList(para) ) 
						  .fromEmail( fromEmailDesa ) .message( contenido )
						  .hasFiles(Boolean.FALSE).build();
						  ed.setSession( EmailUtil.provideSession(ed, EmailSecurityTypeEnum.SSL) );
						  EmailUtil.sendEmail( ed ); 
			 }
			
		} catch (RelativeException e) {
			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS+ e.getMessage());			
		} catch (Exception e) {
			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	public Boolean enviarCorreoPruebas(String para, String asunto, String contenido, Map<java.lang.String,byte[]> adjunto) throws RelativeException {
		try {
			String[] array = new String[1];
			array[0] = para;
			
			this.mailNotificacion(array, asunto, contenido, adjunto);
			return Boolean.TRUE;
		} catch (RelativeException e) {
			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS+ e.getMessage());

		}
	}
}
