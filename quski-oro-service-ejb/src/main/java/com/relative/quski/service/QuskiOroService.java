package com.relative.quski.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.model.Canton;
import com.relative.quski.model.Parroquia;
import com.relative.quski.model.Provincia;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoAgencia;
import com.relative.quski.model.TbQoArchivoCliente;
import com.relative.quski.model.TbQoCatalogo;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoNegociacionCalculo;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTipoArchivo;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.model.TbQoTipoOro;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.AgenciaRepository;
import com.relative.quski.repository.ArchivoClienteRepository;
import com.relative.quski.repository.CantonRepository;
import com.relative.quski.repository.CatalogoRepository;
import com.relative.quski.repository.ClienteRepository;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.DetalleCreditoRepository;
import com.relative.quski.repository.DireccionClienteRepository;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.IngresoEgresoClienteRepository;
import com.relative.quski.repository.NegociacionCalculoRepository;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.ParroquiaRepository;
import com.relative.quski.repository.PatrimonioRepository;
import com.relative.quski.repository.PrecioOroRepository;
import com.relative.quski.repository.ProvinciaRepository;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.TipoArchivoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.TipoOroRepository;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.VariablesCrediticiaRepository;
import com.relative.quski.repository.spec.CatalogoByNombreSpec;
import com.relative.quski.repository.spec.ClienteByIdentificacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.repository.spec.TipoOroByQuilateSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AsignacionesWrapper;
import com.relative.quski.wrapper.AutorizacionBuroWrapper;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.PrecioOroWrapper;
import com.relative.quski.wrapper.VariableCrediticiaWrapper;

@Stateless
public class QuskiOroService {
	@Inject
	Logger log;

	@Inject
	private CotizadorRepository cotizadorRepository;
	@Inject
	private TipoOroRepository tipoOroRepository;
	@Inject
	private DetalleCreditoRepository detalleCreditoRepository;
	@Inject
	private PrecioOroRepository precioOroRepository;
	@Inject
	private CreditoNegociacionRepository creditoNegociacionRepository;
	@Inject
	private NegociacionCalculoRepository negociacionCalculoRepository;
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
	private ProvinciaRepository provinciaRepository;
	@Inject
	private CantonRepository cantonRepository;
	@Inject
	private ParroquiaRepository parroquiaRepository;
	@Inject
	private CatalogoRepository catalogoRepository;
	@Inject
	private AgenciaRepository agenciaRepository;
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

	/**
	 * CLIENTE
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoCliente findClienteById(Long id) throws RelativeException {
		try {
			return clienteRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Abonos " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author BRAYAN MONGE - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCliente() throws RelativeException {
		try {
			return clienteRepository.countAll(TbQoCliente.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Cliente no encontrado " + e.getMessage());
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
	public TbQoCliente manageCliente(TbQoCliente send) throws RelativeException {
		try {
			log.info("==> entra a manage Abono");
			TbQoCliente persisted = null;
			if (send != null && send.getId() != null) {
				log.info("==================>   Ingresa a actualizacion ===================> ");
				persisted = this.clienteRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				log.info("INGRESA A CREACION");

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return clienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
		}
	}

	public TbQoCliente updateCliente(TbQoCliente send, TbQoCliente persisted) throws RelativeException {
		try {
			persisted.setId(send.getId());
			persisted.setCedulaCliente(send.getCedulaCliente());
			;
			persisted.setPrimerNombre(send.getPrimerNombre());
			persisted.setSegundoNombre(send.getSegundoNombre());
			persisted.setApellidoPaterno(send.getApellidoPaterno());
			persisted.setApellidoMaterno(send.getApellidoMaterno());
			persisted.setGenero(send.getGenero());
			persisted.setEstadoCivil(send.getEstadoCivil());
			persisted.setCargasFamiliares(send.getCargasFamiliares());
			persisted.setFechaNacimiento(send.getFechaNacimiento());
			persisted.setLugarNacimiento(send.getLugarNacimiento());
			persisted.setNacionalidad(send.getNacionalidad());
			persisted.setNivelEducacion(send.getNivelEducacion());
			persisted.setActividadEconomica(send.getActividadEconomica());
			persisted.setEdad(send.getEdad());

			if (!StringUtils.isEmpty(send.getCedulaCliente())) {
				persisted.setCedulaCliente(send.getCedulaCliente());
			}

			if (!StringUtils.isEmpty(send.getActividadEconomica())) {
				persisted.setActividadEconomica(send.getActividadEconomica());
			}
			if (!StringUtils.isEmpty(send.getApellidoMaterno())) {
				persisted.setApellidoMaterno(send.getApellidoMaterno());
			}
			if (!StringUtils.isEmpty(send.getApellidoPaterno())) {
				persisted.setApellidoPaterno(send.getApellidoPaterno());
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
			if (!StringUtils.isEmpty(send.getNacionalidad())) {
				persisted.setNacionalidad(send.getNacionalidad());
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
			if (send.getFechaNacimiento() != null) {
				persisted.setFechaNacimiento(send.getFechaNacimiento());
			}
			if (send.getActividadEconomicaEmpresa() != null) {
				persisted.setActividadEconomicaEmpresa(send.getActividadEconomicaEmpresa());
			}
			if (send.getCargo() != null) {
				persisted.setCargo(send.getCargo());
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
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
			// pon las validaciones par ESTOS CAMPOS Y PRUE OK YA DE NACIONALIDAD YA ESTA YA
			// LE PONGO EL DE EDAD
			if (send.getEdad() != null) {
				persisted.setEdad(send.getEdad());
			}
			return clienteRepository.update(persisted);
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					" ERROR ACTUALIZANDO CLIENTE ===> " + e.getMessage());
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
			// log.info("===>>> Entre a la creacion del direcciones ===========> " +
			// direcciones);
			direcciones.forEach(dc -> {
				// log.info("===>>> Entre al forchEach del direcciones ===========> " +
				// direcciones);
				dc.setTbQoCliente(cliente);
				try {
					this.manageDireccionCliente(dc);
				} catch (RelativeException e) {
					e.printStackTrace();
					errores.put("direccion-" + dc.getCallePrincipal(), "Error registro direccion " + e.getMensaje());
				}
			});
		} else {
			log.info("===>>> No Entre a la creacion del direcciones ===========> " + direcciones);
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
			// log.info("===>>> Entre a la creacion del ingresoEgreso ===========> " +
			// ingresoEgreso);
			ingresoEgreso.forEach(ie -> {
				// log.info("===>>> Entre al forchEach del ingresoEgreso ===========> " +
				// ingresoEgreso);

				ie.setTbQoCliente(cliente);
				try {
					this.manageIngresoEgresoCliente(ie);
				} catch (RelativeException e) {
					e.printStackTrace();
					errores.put("Ingreso Egreso-" + ie.getTbQoCliente(),
							"Error registro IngresoEgreso " + e.getMensaje());
				}
			});
		} else {
			log.info("===>>> No Entre a la creacion del ingresoEgreso ===========> " + ingresoEgreso);
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
			// log.info("===>>> Entre a la creacion del patrimonio ===========> " +
			// patrimonio);
			patrimonio.forEach(pa -> {
				// log.info("===>>> Entre al forchEach del patrimonio ===========> " +
				// patrimonio);
				pa.setTbQoCliente(cliente);
				try {
					this.managePatrimonio(pa);
				} catch (RelativeException e) {
					e.printStackTrace();
					errores.put("Patrimonio-" + pa.getTbQoCliente(), "Error registro Patrimonio " + e.getMensaje());
				}
			});
		} else {
			log.info("===>>> No Entre a la creacion del patrimonio ===========> " + patrimonio);
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
			// log.info("===>>> Entre a la creacion del referencia ===========> " +
			// referencia);

			referencia.forEach(re -> {
				// log.info("===>>> Entre al forchEach del referencia ===========> " +
				// referencia);

				re.setTbQoCliente(cliente);
				try {
					this.manageReferenciaPersonal(re);
				} catch (RelativeException e) {
					e.printStackTrace();
					errores.put("Patrimonio-" + re.getTbQoCliente(), "Error registro Referencia " + e.getMensaje());
				}
			});
		} else {
			log.info("===>>> No Entre a la creacion del referencia ===========> " + referencia);
		}
		return errores;
	}

	/**
	 * 
	 * @param cliente
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCliente crearCliente(TbQoCliente cliente) throws RelativeException {
		TbQoCliente locCliente = new TbQoCliente();
		Map<String, String> erroresDireccion = new HashMap<>();
		Map<String, String> erroresIngresoEgreso = new HashMap<>();
		Map<String, String> erroresPatrimonio = new HashMap<>();
		Map<String, String> erroresReferencia = new HashMap<>();
		if (cliente != null) {
			locCliente = this.manageCliente(cliente);

			erroresDireccion = this.createDireccionesCliente(locCliente, cliente.getTbQoDireccionClientes());
			log.info("===>>> datos guardados a persisted Direcciones ===========> " + cliente.getTbQoDireccionClientes()
					+ " " + erroresDireccion);
			if (erroresDireccion.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN LA CREACION DE DIRECCION DE CLIENTE " + erroresDireccion.toString());
			}
			erroresIngresoEgreso = this.createIngresoEgresoCliente(locCliente, cliente.getTbQoIngresoEgresoClientes());
			log.info("===>>> datos guardados a persisted Ingreso Egreso ===========> " + cliente + " "
					+ erroresIngresoEgreso);
			if (erroresIngresoEgreso.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN LA CREACION DE INGRESO EGRESO DE CLIENTE " + erroresIngresoEgreso.toString());
			}
			erroresPatrimonio = this.createPatrimonioCliente(locCliente, cliente.getTbQoPatrimonios());
			log.info("===>>> datos guardados a persisted Patrimonio ===========> " + cliente + " " + erroresPatrimonio);
			if (erroresPatrimonio.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN LA CREACION DE PATRIMONIO DE CLIENTE " + erroresPatrimonio.toString());
			}
			erroresReferencia = this.createReferenciasPersonales(locCliente, cliente.getTbQoReferenciaPersonals());
			log.info("===>>> datos guardados a persisted Referencia ===========> " + cliente + " " + erroresReferencia);
			if (erroresReferencia.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR EN LA CREACION DE REFERENCIAS PERSONALES DEL CLIENTE " + erroresReferencia.toString());
			}

		}
		return locCliente;
	}

	/**
	 * COTIZADOR
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoCotizador findCotizadorById(Long id) throws RelativeException {
		try {
			return cotizadorRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Método que realiza el registro del precio oro por Cotización de uno por uno
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param identificacion
	 * @return {@link TbQoPrecioOro}
	 * @throws RelativeException
	 */
	public TbQoPrecioOro registrarPrecioOroByCotizacion(TbQoPrecioOro po) throws RelativeException {
		TbQoCotizador cot = this.findCotizadorById(po.getTbQoCotizador().getId());
		TbQoTipoOro tpo = this.manageTipoOro(po.getTbQoTipoOro());
		// TbQoTipoOro tpo=this.findTipoOroById( po.getTbQoTipoOro().getId() );
		po.setTbQoCotizador(cot);
		po.setTbQoTipoOro(tpo);
		return this.managePrecioOro(po);
	}

	/**
	 * Método que realiza la eliminación lógica del precio oro
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param id
	 * @return {@link TbQoPrecioOro}
	 * @throws RelativeException
	 */
	public TbQoPrecioOro eliminarPrecioOro(Long id) throws RelativeException {
		TbQoPrecioOro precioOro = this.precioOroRepository.findById(id);
		precioOro.setEstado(EstadoEnum.INA);
		return this.managePrecioOro(precioOro);
	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE BUSCA
	 * LOS PRECIOS OROS LIGADOS A LA COTIZACION
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
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author SAUL MENDEZ - Relative Engine
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Agencia " + e.getMessage());
		}
	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE BUSCA
	 * LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE DEVUELVE LA LISTA DE
	 * COTIZACIONES QUE SE ENCUENTRAN LIGADOS A UN CLIENTE
	 * 
	 * @author KLEBER GUERRA - Relative Engine
	 * @param pw
	 * @param cedulaCliente
	 * @return
	 * @throws RelativeException
	 */
	public List<TbQoCotizador> listByCliente(PaginatedWrapper pw, String cedulaCliente) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return cotizadorRepository.findByCliente(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), cedulaCliente);
		} else {
			return cotizadorRepository.findByCliente(cedulaCliente);
		}
	}

	public Long countByCliente(String cedulaCliente) throws RelativeException {

		return cotizadorRepository.countByCliente(cedulaCliente);
	}

	/**
	 * Método para crear el código de la cotización
	 * 
	 * @param persisted
	 * @return TbQoCotizador
	 * @throws RelativeException
	 */
	private TbQoCotizador crearCodigoCotizacion(TbQoCotizador persisted) throws RelativeException {
		log.info("VALOR QUE LLEGA A CREAR CODIGO" + persisted);
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
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error Creando codigo de Cotizacion " + e.getMessage());

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
	public TbQoCotizador updateCotizador(TbQoCotizador send, TbQoCotizador persisted) throws RelativeException {
		try {
			persisted.setAprobacionMupi(send.getAprobacionMupi());
			persisted.setGradoInteres(send.getGradoInteres());
			persisted.setMotivoDeDesestimiento(send.getMotivoDeDesestimiento());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(send.getEstado());
			return crearCodigoCotizacion(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Abono " + e.getMessage());
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
	public TbQoCotizador manageCotizador(TbQoCotizador send) throws RelativeException {
		try {
			log.info("==> entra a manager Cotizador>> " + send);
			TbQoCotizador persisted = null;
			if (send != null && send.getId() != null) {
				log.info("==> entra AL IF  a manage Cotizador");
				persisted = this.cotizadorRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.INA);

				return this.updateCotizador(send, persisted);
			} else if (send != null && send.getId() == null) {
				log.info("INGRESA AL ELSE IF manageCotizador " + send);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);

				log.info("VALORES ACTUALIZADOS>>> " + send);
				persisted = cotizadorRepository.add(send);
				log.info("VALOR INSERTADO" + persisted);

				return crearCodigoCotizacion(persisted);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCotizador() throws RelativeException {
		try {
			return cotizadorRepository.countAll(TbQoCotizador.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Cotizador no encontrado " + e.getMessage());
		}
	}

	/**
	 * TipoOro
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoTipoOro findTipoOroById(Long id) throws RelativeException {
		try {
			return tipoOroRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
	public List<TbQoTipoOro> findAllTipoOro(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.tipoOroRepository.findAll(TbQoTipoOro.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.tipoOroRepository.findAll(TbQoTipoOro.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.tipoOroRepository.findAll(TbQoTipoOro.class, pw.getSortFields(),
							pw.getSortDirections());

				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Abonos " + e.getMessage());
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
	public TbQoTipoOro manageTipoOro(TbQoTipoOro send) throws RelativeException {
		try {
			log.info("==> entra a manage Abono");
			TbQoTipoOro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.tipoOroRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateTipoOro(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoOroRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
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
	public TbQoTipoOro updateTipoOro(TbQoTipoOro send, TbQoTipoOro persisted) throws RelativeException {
		try {
			if (send.getQuilate() != null) {
				persisted.setQuilate(send.getQuilate());
			}
			if (send.getEstado() != null) {
				persisted.setEstado(send.getEstado());
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return tipoOroRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Tipo oro " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countTipoOro() throws RelativeException {
		try {
			return tipoOroRepository.countAll(TbQoTipoOro.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "TipoOro no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que busca por el quilate del tipo de oro
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public List<TbQoTipoOro> findTipoOroByQuilate(String quilate) throws RelativeException {
		List<TbQoTipoOro> tmp;
		try {
			tmp = this.tipoOroRepository.findAllBySpecification(new TipoOroByQuilateSpec(quilate));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR tipo oro por quilate: " + quilate);
		}
		return null;

	}

	/**
	 * Detalle Credito
	 */

	/**
	 * Metodo que busca la entidad por su PK
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los detalle credito " + e.getMessage());
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
			log.info("==> entra a manage Abono");
			log.info("==> entra a manage Abono>>>>>>>>>>>>>>>>>" + send.getId());
			TbQoDetalleCredito persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.detalleCreditoRepository.findById(send.getId());

				persisted = this.findDetalleCreditoById(send.getId());

				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateDetalleCredito(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return detalleCreditoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al actualizar el detalle de credito " + e.getMessage());

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
			persisted.setPlazoCredito(send.getPlazoCredito());
			persisted.setMontoPreaprobado(send.getMontoPreaprobado());
			persisted.setRecibirCliente(send.getRecibirCliente());
			persisted.setCostoNuevaOperacion(send.getCostoNuevaOperacion());
			persisted.setCostoCustodia(send.getCostoCustodia());
			persisted.setCostoTransporte(send.getCostoTransporte());
			persisted.setCostoCredito(send.getCostoCredito());
			persisted.setCostoSeguro(send.getCostoSeguro());
			persisted.setCostoResguardado(send.getCostoResguardado());
			persisted.setCostoEstimado(send.getCostoEstimado());
			persisted.setValorCuota(send.getValorCuota());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(send.getEstado());
			return detalleCreditoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Abono " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Detalle Credito no encontrado " + e.getMessage());
		}
	}

	/**
	 * Precio oro
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los precios oro " + e.getMessage());
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
	public TbQoPrecioOro managePrecioOro(TbQoPrecioOro send) throws RelativeException {
		try {
			log.info("==> entra a manage managePrecioOro");
			TbQoPrecioOro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.precioOroRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updatePrecioOro(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return precioOroRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al actualzar el precio oro " + e.getMessage());

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
			persisted.setEstado(send.getEstado());
			persisted.setPrecio(send.getPrecio());
			// persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(send.getEstado());
			if (send.getTbQoTipoOro() != null) {
				persisted.setTbQoTipoOro(send.getTbQoTipoOro());
			}
			return precioOroRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Abono " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Detalle Credito no encontrado " + e.getMessage());
		}
	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE BUSCA
	 * LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param idCotizador
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoPrecioOro> listByIdCotizador(PaginatedWrapper pw, String idCotizador) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return precioOroRepository.findByIdCotizador(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), idCotizador);

		} else {
			return precioOroRepository.findByIdCotizador(idCotizador);
		}
	}

	public Long countByIdCotizador(String idCotizador) throws RelativeException {

		return precioOroRepository.countByIdCotizador(idCotizador);
	}

	/**
	 * Cliente
	 */

	/**
	 * Copia la informacion del wrapper de variable crediticia al entity variable
	 * crediticia
	 * 
	 * @author KLÉBER GUERRA Relative - Engine
	 * @param idCotizacion
	 * @return
	 * @throws RelativeException
	 */
	private List<TbQoVariablesCrediticia> copyVariablesCrediticiaData(Long idCotizacion) throws RelativeException {
		List<TbQoVariablesCrediticia> varCrediticia = new ArrayList<>();
		log.info("========>copyVariablesCrediticiasData " + idCotizacion);
		List<VariableCrediticiaWrapper> variablesCrediticias = this.variablesCrediticiaRepository
				.findByIdCotizadorCustom(idCotizacion);

		if (variablesCrediticias != null && !variablesCrediticias.isEmpty()) {
			log.info("========>copyVariablesCrediticiaData variablesCrediticias " + variablesCrediticias.size());
			variablesCrediticias.forEach(vcw -> {
				log.info("========>leyendo elemento pow " + vcw.getId());
				TbQoVariablesCrediticia vc = new TbQoVariablesCrediticia();
				vc.setId(vcw.getId());
				vc.setOrden(vcw.getOrden());
				vc.setNombre(vcw.getNombre());
				vc.setValor(vcw.getValor());

				varCrediticia.add(vc);
			});
		}
		return varCrediticia;

	}

	/**
	 * Copia la informacion del wrapper de precio de oro al entity precio de oro
	 * 
	 * @author KLÉBER GUERRA Relative - Engine
	 * @param idCotizacion
	 * @return List<TbQoPrecioOro>
	 * @throws RelativeException
	 */
	private List<TbQoPrecioOro> copyPrecioOroData(Long idCotizacion) throws RelativeException {
		List<TbQoPrecioOro> pos = new ArrayList<>();

		log.info("========>copyPrecioOroData " + idCotizacion);
		List<PrecioOroWrapper> pows = this.precioOroRepository.findByIdCotizadorCustom(idCotizacion);
		List<PrecioOroWrapper> powact = pows.stream().filter(c -> c.getEstado().compareTo(EstadoEnum.ACT) == 0)
				.collect(Collectors.toList());
		if (powact != null && !powact.isEmpty()) {
			log.info("========>copyPrecioOroData pows " + powact.size());
			powact.forEach(pow -> {

				log.info("========>leyendo elemento pow " + pow.getId());
				log.info("========>leyendo elemento pow " + pow.getPrecio());
				log.info("========>leyendo elemento pow " + pow.getPesoNetoEstimado());
				log.info("========> leyendo elemento pow " + pow.getEstado());
				TbQoPrecioOro po = new TbQoPrecioOro();
				TbQoTipoOro to = new TbQoTipoOro();
				po.setId(pow.getId());
				to.setId(pow.getIdTipoOro());
				to.setPrecio(pow.getPrecio());
				to.setQuilate(pow.getQuilate());
				po.setPrecio(pow.getPrecio());
				po.setPesoNetoEstimado(pow.getPesoNetoEstimado());
				po.setTbQoTipoOro(to);
				po.setEstado(pow.getEstado());

				pos.add(po);

			});
		}
		return pos;

	}

	/**
	 ************************************ @Cliente
	 */

	/**
	 * Metodo que busca por el numero de cedula del cliente
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @param  String identificacion
	 * @return TbQoCliente 
	 * @throws RelativeException
	 */
	public List<TbQoCliente> findClienteByIdentifiacion(String identificacion) throws RelativeException {
		List<TbQoCliente> tmp;
		try {
			tmp = this.clienteRepository.findAllBySpecification(new ClienteByIdentificacionSpec(identificacion));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
		throw new RelativeException(Constantes.ERROR_CODE_READ,
				"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + identificacion);
	}
	return null;

	}
	/**
	 * Método que realiza la busqueda por Identificacion con cotizaciones
	 * 
	 * @author KLÉBER GUERRA - Relative Engine
	 * @param  String identificacion
	 * @return TbQoCliente
	 * @throws RelativeException
	 */
	public TbQoCliente findClienteByIdentificacionWithCotizacion(String identificacion) throws RelativeException {
		try {
			TbQoCliente cliente = this.clienteRepository.findClienteByIdentificacion(identificacion);
			TbQoCliente clienteId = new TbQoCliente();
			if (cliente != null && cliente.getTbQoCotizador() != null && !cliente.getTbQoCotizador().isEmpty()) {
				clienteId.setId(cliente.getId());
				List<TbQoCotizador> tmp = cliente.getTbQoCotizador().stream()
						.filter(c -> c.getEstado().compareTo(EstadoEnum.ACT) == 0).collect(Collectors.toList());
				if (tmp != null && !tmp.isEmpty()) {
					tmp.get(0).setTbQoPrecioOros(this.copyPrecioOroData(tmp.get(0).getId()));
					tmp.get(0).setTbQoVariablesCrediticias(this.copyVariablesCrediticiaData(tmp.get(0).getId()));
					tmp.get(0).setTbQoCliente(null);
					tmp.get(0).setTbQoCliente(clienteId);
				}
				cliente.setTbQoCotizador(tmp);
				return cliente;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						"ERROR AL BUSCAR CLIENTE: NO EXISTE CLIENTE, NO TIENE COTIZACIONES O COTIZACIONES ACTIVAS: " + identificacion);
			}
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + identificacion);
		}
	}

	public TbQoCotizador findCotizadorByIdAndEstado(TbQoCotizador cotizador, final EstadoEnum estadoEnum)
			throws RelativeException {
		TbQoCotizador cotizacion = new TbQoCotizador();
		try {
			if (cotizador != null && estadoEnum.equals(EstadoEnum.ACT)) {
				cotizacion = this.cotizadorRepository.findById(cotizador.getId());
			}

		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR COTIZACION POR ID Y ESTADO: " + cotizador.getId());
		}
		return cotizacion;

	}

	public List<TbQoCliente> findClienteByIdentifiacionList(String identificacion) throws RelativeException {
		List<TbQoCliente> tmp;
		try {
			tmp = this.clienteRepository.findAllBySpecification(new ClienteByIdentificacionSpec(identificacion));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + identificacion);
		}
		return null;

	}

	public Long countClienteByParams(String identificacion, String primerNombre, String apellidoPaterno,
			String segundoNombre, String apellidoMaterno, String telefono, String celular, String correo,
			EstadoEnum estado) throws RelativeException {
		return this.clienteRepository.countByParams(identificacion, primerNombre, apellidoPaterno, segundoNombre,
				apellidoMaterno, telefono, celular, correo, estado);
	}

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
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param idCotizador
	 * @author SAUL MENDEZ - Relative Engine
	 * @throws RelativeException
	 */

	public Long countVariblesCrediticiaByIdCotizacion(Long idCotizador) throws RelativeException {

		return variablesCrediticiaRepository.countByIdCotizacion(idCotizador);

	}

	public TbQoVariablesCrediticia findVariablesCrediticiaById(Long id) throws RelativeException {

		try {
			return variablesCrediticiaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

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

	public Long countVariablesCrediticias() throws RelativeException {
		try {
			return variablesCrediticiaRepository.countAll(TbQoVariablesCrediticia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}

	}

	public TbQoVariablesCrediticia manageVariablesCrediticia(TbQoVariablesCrediticia send) throws RelativeException {
		try {
			log.info("==> entra a manage variableCrediticia");
			TbQoVariablesCrediticia persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findVariablesCrediticiaById(send.getId());
				return this.updateVariablesCrediticia(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return variablesCrediticiaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Agencia " + e.getMessage());
		}
	}

	private TbQoVariablesCrediticia updateVariablesCrediticia(TbQoVariablesCrediticia send,
			TbQoVariablesCrediticia persisted) throws RelativeException {
		try {
			persisted.setNombre(send.getNombre());
			persisted.setValor(send.getValor());
			persisted.setTbQoCotizador(send.getTbQoCotizador());
			persisted.setTbQoNegociacion(send.getTbQoNegociacion());
			persisted.setFechaActualizacion(new Date());
			persisted.setEstado(send.getEstado());

			return this.variablesCrediticiaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Agencia " + e.getMessage());
		}
	}

	public TbQoNegociacion findNegociacionById(Long id) throws RelativeException {
		try {
			return negociacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	public TbQoNegociacion manageNegociacion(TbQoNegociacion send) throws RelativeException {
		try {
			log.info("==> entra a manage variableCrediticia");
			log.info("==> entra a manage Negociacion");
			TbQoNegociacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findNegociacionById(send.getId());
				return this.updateNegociacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return negociacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Agencia " + e.getMessage());
		}

	}

	private TbQoNegociacion updateNegociacion(TbQoNegociacion send, TbQoNegociacion persisted)
			throws RelativeException {
		try {
			persisted.setId(send.getId());
			persisted.setTbQoCliente(send.getTbQoCliente());
			persisted.setTbQoVariablesCrediticias(send.getTbQoVariablesCrediticias());
			persisted.setEstado(send.getEstado());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

			return negociacionRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Cliente " + e.getMessage());
		}
	}

	public TbQoTasacion findTasacionById(Long id) throws RelativeException {
		try {
			return tasacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	public TbQoTasacion manageTasacion(TbQoTasacion send) throws RelativeException {
		try {
			log.info("==> entra a manage variableCrediticia");
			TbQoTasacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTasacionById(send.getId());
				return this.updateTasacion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return tasacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Agencia " + e.getMessage());
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
			persisted.setTbQoTipoOro(send.getTbQoTipoOro());
			// persisted.setTbTipoJoya(send.getTbTipoJoya());
			// persisted.setTbTipoJoya(send.getTbTipoJoya());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setTbQoCreditoNegociacion(send.getTbQoCreditoNegociacion());

			return tasacionRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Tasacion " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}
	
	public TbQoDocumentoHabilitante findDocumentoHabilitanteByTipoDocumentoReferenciaProceso(Long idTipoDocumento, ProcessEnum proceso, Long referencia) throws RelativeException {
		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(idTipoDocumento, proceso, referencia);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante manageDocumentoHabilitante(TbQoDocumentoHabilitante send) throws RelativeException {
		try {

			log.info("==> entra a manage TbQoDocumentoHabilitante" + send);
			TbQoDocumentoHabilitante persisted = new TbQoDocumentoHabilitante();
			log.info("ANTES DE PERDERSE");

			if (send != null && send.getId() != null) {
				log.info("Ingresa al findDocumento ****findDocumentoHabilitanteById" + send.getId());
				persisted = this.findDocumentoHabilitanteById(send.getId());
				return this.updateDocumentoHabilitante(send, persisted);
			} else if (send != null && send.getId() == null) {
				log.info("INGRESO AL ELSE");
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return documentoHabilitanteRepository.add(send);
			} else {
				log.info("INGRESA AL ERRRORRRRR");
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la HistoricoJoya " + e.getMessage());
		}
	}

	public TbQoDocumentoHabilitante updateDocumentoHabilitante(TbQoDocumentoHabilitante send,
			TbQoDocumentoHabilitante persisted) throws RelativeException {
		log.info("INGRESA A+++++++++++++ updateDocumentoHabilitante ");
		try {
			persisted.setArchivo(send.getArchivo());
			persisted.setEstado(send.getEstado());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setNombreArchivo(send.getNombreArchivo());
			persisted.setTbQoTipoDocumento(send.getTbQoTipoDocumento());

			log.info("ANTES DEL IF EN updateDocumentoHabilitante " + send.getNombreArchivo());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando documentoHabilitanteRepository " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "documento no encontrado " + e.getMessage());
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
			log.info("==> entra a manage Documento");
			TbQoTipoDocumento persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoDocumentoById(send.getId());
				return this.updateDocumento(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoDocumentoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Documento " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Documento " + e.getMessage());
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
				log.info("Ingreso en GENERAR DOCUMENTO ");
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(fw.getRelatedIdStr(),
						Long.valueOf(fw.getTypeAction()), null, null);
			} else if (fw.getProcess().equalsIgnoreCase("COTIZADOR")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(null, null, null,
						Long.valueOf(fw.getTypeAction()));
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(null, null, null,
						Long.valueOf(fw.getTypeAction()));
			} else if (fw.getProcess().equalsIgnoreCase("NEGOCIACION")) {
				dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(null,
						Long.valueOf(fw.getRelatedIdStr()), null, null);
			}

		} catch (NumberFormatException e) {
			// e.printStackTrace();
			dhs = null;
			log.info("===================: error no existe datos para contrato y accion " + fw.getRelatedIdStr() + "  "
					+ fw.getTypeAction());
		}
		da = new TbQoDocumentoHabilitante();
		if (dhs != null) {
			da.setId(dhs.getId());
		}
		td = this.findTipoDocumentoById(Long.valueOf(fw.getTypeAction()));
		da.setTbQoTipoDocumento(td);
		if (fw.getProcess() == null || fw.getProcess().equalsIgnoreCase("CLIENTE")) {
			// mc = this.findContratoByCodigo(fw.getRelatedIdStr());
			List<TbQoCliente> clientes = this.findClienteByIdentifiacionList(fw.getRelatedIdStr());

			if (clientes != null && !clientes.isEmpty()) {

				if (clientes != null && !clientes.isEmpty()) {
					da.setTbQoCliente(clientes.get(0));
				} else {
					cl = new TbQoCliente();
					cl.setCedulaCliente(fw.getRelatedIdStr());
					da.setTbQoCliente(this.manageCliente(cl));
				}

			} else if (fw.getProcess().equalsIgnoreCase("COTIZADOR")) {
				log.info("CUANDO ES COTIZADOR");
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
		//DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec docHabilitanteSpec = new DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec();
		try {

			return documentoHabilitanteRepository.findByTipoDocumentoAndCliAndCotAndNeg(idTipoDocumento,
					identificacionCliente, idCotizador, idNegociacion);

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Variable Crediticia Metodo que busca por el IdCotizador en las variables
	 * crediticias Variable Crediticia Metodo que busca por el IdCotizador en las
	 * variables crediticias
	 * 
	 * @author Brayan Monge - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	
	
	
	/*
	 * public List<TbQoVariableCrediticia> listaByIdCotizador(PaginatedWrapper pw,
	 * String idCotizador) throws RelativeException { if (pw != null &&
	 * pw.getIsPaginated() != null &&
	 * pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) { return
	 * variableCrediticiaRepository.findByIdCotizador(pw.getStartRecord(),
	 * pw.getPageSize(), pw.getSortFields(), pw.getSortDirections(),idCotizador ); }
	 * else { return variableCrediticiaRepository.findByIdCotizador(idCotizador);
	 * 
	 * }
	 * 
	 * /*======= public List<TbQoVariableCrediticia>
	 * findVariableCrediticiaByidCotizador(Long idCotizador) throws
	 * RelativeException { List<TbQoVariableCrediticia> tmp; try { tmp =
	 * this.variableCrediticiaRepository.findAllBySpecification(new
	 * VariablesCrediticiasByIdCotizacionSpec(idCotizador)); if (tmp != null &&
	 * !tmp.isEmpty()) { return tmp; } } catch (Exception e) {
	 * 
	 * throw new RelativeException(Constantes.ERROR_CODE_READ,
	 * "ERROR: AL BUSCAR variable crediticia: " + idCotizador); >>>>>>>
	 * 013f20b21d1f4d10371a9c59544c974751b8a20b } return null;
	 * 
	 * return variableCrediticiaRepository.countByIdCotizador(idCotizador); }
	 * 
	 * public List<TbQoVariableCrediticia> listaByIdCotizador(PaginatedWrapper pw,
	 * String idCotizador) throws RelativeException { if (pw != null &&
	 * pw.getIsPaginated() != null &&
	 * pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) { return
	 * variableCrediticiaRepository.findByIdCotizador(pw.getStartRecord(),
	 * pw.getPageSize(), pw.getSortFields(), pw.getSortDirections(),idCotizador ); }
	 * else { return variableCrediticiaRepository.findByIdCotizador(idCotizador);
	 * ======= public List<TbQoVariableCrediticia>
	 * findVariableCrediticiaByidCotizador(Long idCotizador) throws
	 * RelativeException { List<TbQoVariableCrediticia> tmp; try { tmp =
	 * this.variableCrediticiaRepository.findAllBySpecification(new
	 * VariablesCrediticiasByIdCotizacionSpec(idCotizador)); if (tmp != null &&
	 * !tmp.isEmpty()) { return tmp; } } catch (Exception e) {
	 * 
	 * throw new RelativeException(Constantes.ERROR_CODE_READ,
	 * "ERROR: AL BUSCAR variable crediticia: " + idCotizador); } return null;
	 * 
	 * return variableCrediticiaRepository.countByIdCotizador(idCotizador); }
	 * 
	 * /** Metodo que se encarga de gestionar la entidad sea creacion o
	 * actualizacion
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * 
	 * @param send entidad con la informacion de creacion o actualizacion
	 * 
	 * @return Entidad modificada o actualizada
	 * 
	 * @throws RelativeException
	 */
	public TbQoCreditoNegociacion manageCalculoNegociacion(TbQoCreditoNegociacion send) throws RelativeException {
		try {
			log.info("==> entra a manage TbQoCalculoNegociacion");
			TbQoCreditoNegociacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.creditoNegociacionRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateCalculoNegociacion(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return creditoNegociacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
		}
	}

	public TbQoCreditoNegociacion findCreditoNegociacionById(Long id) throws RelativeException {
		try {
			return creditoNegociacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que actualiza la entidad
	 * 
	 * @author SAUL MENDEZ- Relative Engine
	 * @param send      informacion enviada para update
	 * @param persisted entidad existente sobre la que se actualiza
	 * @return Entidad actualizada
	 * @throws RelativeException
	 */

	public TbQoCreditoNegociacion updateCalculoNegociacion(TbQoCreditoNegociacion send,
			TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			/*
			 * persisted.setQuilate(send.getQuilate());
			 * persisted.setFechaCreacion(persisted.getFechaCreacion());
			 * persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			 * persisted.setEstado(send.getEstado());
			 */
			/*
			 * persisted.setQuilate(send.getQuilate());
			 * persisted.setFechaCreacion(persisted.getFechaCreacion());
			 * persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			 * persisted.setEstado(send.getEstado());
			 */
			return creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Tipo oro " + e.getMessage());
		}
	}

	/**
	 * Metodo que realiza la busqueda de la negociacionCalculo por ID
	 * 
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public TbQoNegociacionCalculo findNegociacionCalculoById(Long id) throws RelativeException {
		try {
			return negociacionCalculoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
	public TbQoNegociacionCalculo manageNegociacionCalculo(TbQoNegociacionCalculo send) throws RelativeException {
		try {
			log.info("==> entra a manage TbQoNegociacionCalculo");
			TbQoNegociacionCalculo persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.negociacionCalculoRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateNegociacionCalculo(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return negociacionCalculoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
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
			String fechaHasta, String codigoOperacion, String proceso, String identificacion, String agencia)
			throws RelativeException {

		if (pw == null) {
			return this.creditoNegociacionRepository.findAllBySpecification(new CreditoNegociacionByParamsSpec(
					fechaDesde, fechaHasta, identificacion, agencia));

		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.creditoNegociacionRepository.findPorCustomFilterCreditos(pw, fechaDesde, fechaHasta,
						identificacion, agencia);

			} else {
				return this.creditoNegociacionRepository.findAllBySpecification(new CreditoNegociacionByParamsSpec(
						fechaDesde, fechaHasta, identificacion, agencia));

			}
		}

	}

//	public List<ListadoOperacionDevueltaWrapper> listOperacionesDevueltas(PaginatedWrapper pw, String codigo,
//			String agencia, String proceso, String cedula) throws RelativeException {
//		return this.creditoNegociacionRepository.listOperacionesDevueltas(pw, codigo, agencia, proceso, cedula);
//	}

	public Integer countCreditoNegociacionByParams(String fechaDesde, String fechaHasta, String codigoOperacion,
			String proceso, String identificacion, String agencia) throws RelativeException {

		return this.creditoNegociacionRepository.countBySpecification(new CreditoNegociacionByParamsSpec(fechaDesde,
				fechaHasta, identificacion, agencia)).intValue();

	}

	public Integer countOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia, String proceso,
			String cedula) throws RelativeException {
		try {
			return this.creditoNegociacionRepository.countOperacionesDevueltas(pw, agencia, cedula);
		} catch (Exception e) {
			throw new RelativeException("" + e);
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

	public TbQoNegociacionCalculo updateNegociacionCalculo(TbQoNegociacionCalculo send,
			TbQoNegociacionCalculo persisted) throws RelativeException {
		try {
			/*
			 * persisted.setQuilate(send.getQuilate());
			 * persisted.setFechaCreacion(persisted.getFechaCreacion());
			 * persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			 * persisted.setEstado(send.getEstado());
			 */
			/*
			 * persisted.setQuilate(send.getQuilate());
			 * persisted.setFechaCreacion(persisted.getFechaCreacion());
			 * persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			 * persisted.setEstado(send.getEstado());
			 */
			return negociacionCalculoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Tipo oro " + e.getMessage());
		}
	}

	/**
	 * Referencias Personales Referencias Personales
	 */

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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Abonos " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Referencia personal no encontrado " + e.getMessage());

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
			log.info("==> entra a manage Abono");
			log.info("===>>> ENTRANDO A MANAGE REFERENCIA PERSONAL ===========> " + send);
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
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando la Abono " + e.getMessage());
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
			;
			persisted.setNombresCompletos(send.getNombresCompletos());
			persisted.setEstado(EstadoEnum.ACT.toString());
			persisted.setParentesco(send.getParentesco());
			persisted.setDireccion(send.getDireccion());
			persisted.setTelefonoMovil(send.getTelefonoMovil());
			persisted.setTelefonoFijo(send.getTelefonoFijo());
			return referenciaPersonalRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Cliente " + e.getMessage());
		}
	}

	/**
	 * Catalogo
	 */
	/**
	 * Catalogo
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoCatalogo findCatalogoById(Long id) throws RelativeException {
		try {
			return catalogoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * 
	 * @author DIEGO SERRANO - Relative Engine Archivos Cliente
	 * 
	 * @author DIEGO SERRANO - Relative Engine Archivos Cliente
	 * @param id
	 * @return
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoCatalogo> findAllCatalogo(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.catalogoRepository.findAll(TbQoCatalogo.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.catalogoRepository.findAll(TbQoCatalogo.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.catalogoRepository.findAll(TbQoCatalogo.class, pw.getSortFields(),
							pw.getSortDirections());

				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Abonos " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author BRAYAN MONGE - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCatalogo() throws RelativeException {
		try {
			return catalogoRepository.countAll(TbQoCatalogo.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Catalogo no encontrado " + e.getMessage());
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
	public TbQoCatalogo manageCatalogo(TbQoCatalogo send) throws RelativeException {
		try {
			log.info("==> entra a Catalogo");
			TbQoCatalogo persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.catalogoRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateCatalogo(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return catalogoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando al actualizar el catalogo " + e.getMessage());

		}
	}

	public TbQoCatalogo updateCatalogo(TbQoCatalogo send, TbQoCatalogo persisted) throws RelativeException {
		try {
			persisted.setId(send.getId());
			persisted.setNombreCatalogo(send.getNombreCatalogo());
			;
			persisted.setNombreCatalogo(send.getNombreCatalogo());
			persisted.setDescripcionCatalogo(send.getDescripcionCatalogo());
			persisted.setTipoCatalogo(send.getTipoCatalogo());
			persisted.setValorCatalogo(send.getValorCatalogo());

			return catalogoRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando catalogo " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Archivo Cliente no encontrado " + e.getMessage());
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
			log.info("==> entra a manage Patrimonio");
			TbQoArchivoCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.archivoClienteRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateArchivoCliente(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return archivoClienteRepository.add(send);

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando al actualizar el catalogo " + e.getMessage());

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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando documentoHabilitanteRepository " + e.getMessage());
		}

	}

	public TbQoTipoArchivo findTipoArchivoById(Long id) throws RelativeException {
		try {
			return tipoArchivoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los Abonos " + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Patrimonio no encontrado " + e.getMessage());
		}
	}

	public TbQoTipoArchivo manageTipoArchivo(TbQoTipoArchivo send) throws RelativeException {
		try {
			log.info("==> entra a manage TipoArchivo");
			TbQoTipoArchivo persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findTipoArchivoById(send.getId());
				return this.updateTipoArchivo(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return tipoArchivoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la Documento " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Documento " + e.getMessage());
		}
	}

	/**
	 * PROVINCIA
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public Provincia findProvinciaById(Long id) throws RelativeException {
		try {
			return provinciaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Provincia no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countProvincia() throws RelativeException {
		try {
			return provinciaRepository.countAll(Provincia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Provincia no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public List<Provincia> findAllProvincia(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.provinciaRepository.findAll(Provincia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.provinciaRepository.findAll(Provincia.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.provinciaRepository.findAll(Provincia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * CANTON
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public Canton findCantonById(Long id) throws RelativeException {
		try {
			return cantonRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Canton no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countCanton() throws RelativeException {
		try {
			return cantonRepository.countAll(Canton.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Canton no encontrado " + e.getMessage());
		}

	}

	/**
	 * 
	 * 
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Diego Serrano - Relative Engine
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public List<Canton> findAllCanton(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.cantonRepository.findAll(Canton.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.cantonRepository.findAll(Canton.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.cantonRepository.findAll(Canton.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Carga los cantones asociados a la provincia
	 * 
	 * @param provincia Criterio de busqueda
	 * @return Listado de cantones por provincia
	 * @throws RelativeException
	 */
	public List<Canton> findCantonesByProvincia(String provincia, String order) throws RelativeException {
		return this.cantonRepository.findByProvincia(provincia, order);
	}

	/**
	 * PARROQUIA
	 */

	/**
	 * Metodo que busca la entidad por su PK
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public Parroquia findParroquiaById(Long id) throws RelativeException {
		try {
			return parroquiaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Canton no encontrada " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countParroquia() throws RelativeException {
		try {
			return parroquiaRepository.countAll(Parroquia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Canton no encontrado " + e.getMessage());
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author Diego Serrano - Relative Engine
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public List<Parroquia> findAllParroquia(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.parroquiaRepository.findAll(Parroquia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.parroquiaRepository.findAll(Parroquia.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.parroquiaRepository.findAll(Parroquia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	/**
	 * Carga los cantones asociados a la provincia
	 * 
	 * @param provincia Criterio de busqueda
	 * @return Listado de cantones por provincia
	 * @throws RelativeException
	 */

	public List<Parroquia> finAllUbicacion(String nombre) throws RelativeException {
		return parroquiaRepository.findByCantonProvincia(nombre);
	}

	/**
	 * Metodo que busca por el nombre a catalogo
	 * 
	 * @author Brayan Monge - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public List<TbQoCatalogo> findCatalogoByNombre(String nombre) throws RelativeException {
		List<TbQoCatalogo> tmp;
		try {
			tmp = this.catalogoRepository.findAllBySpecification(new CatalogoByNombreSpec(nombre));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {

			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: AL BUSCAR CLIENTE CON IDENTIFICACION: " + nombre);
		}
		return null;

	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE BUSCA
	 * LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param nombre
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoCatalogo> findNombreByCatalogo(PaginatedWrapper pw, String nombre) throws RelativeException {
		log.info("ENTRA A BUSCAR CATALOGO CON NOMBRE :" + nombre);
		log.info("ENTRA A BUSCAR CATALOGO CON NOMBRE :" + nombre);
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return catalogoRepository.findByNombreCatalogo(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), nombre);

		} else {
			return catalogoRepository.findByNombreCatalogo(nombre);
		}
	}

	public Long countNombreByCatalogo(String nombre) throws RelativeException {

		return catalogoRepository.countByNombreCatalogo(nombre);
	}

	/**
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION METODO QUE BUSCA
	 * LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param tipo
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoCatalogo> findTipoByCatalogo(PaginatedWrapper pw, String tipo) throws RelativeException {
		log.info("ENTRA A BUSCAR CATALOGO CON NOMBRE :" + tipo);
		log.info("ENTRA A BUSCAR CATALOGO CON NOMBRE :" + tipo);
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return catalogoRepository.findByTipoCatalogo(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), tipo);

		} else {
			return catalogoRepository.findByTipoCatalogo(tipo);
		}
	}

	public Long countTipoByCatalogo(String nombre) throws RelativeException {

		return catalogoRepository.countByTipoCatalogo(nombre);
	}

	/**
	 * METODO QUE BUSCA LAS ASIGNACIONES PENDIENTES DE APROBACION PAGINADO
	 * 
	 * @param METODO QUE BUSCA LAS ASIGNACIONES PENDIENTES DE APROBACION PAGINADO
	 * 
	 * @param
	 * @author JEROHAM CADENAS - Relative Engine
	 * @throws RelativeException
	 */
	public List<AsignacionesWrapper> findAsignacionesByParamsPaginated(PaginatedWrapper pw, String codigoOperacion,
			String nombreAgencia, String nombreProceso, String cedula) throws RelativeException {
		return this.creditoNegociacionRepository.findAsignacionesByParamsPaginated(pw,  nombreAgencia,
				 cedula);
	}

	/**
	 * METODO QUE BUSCA LAS AGENCIAS public List<AsignacionesWrapper>
	 * findAsignacionesByParamsPaginated(PaginatedWrapper pw, String
	 * codigoOperacion, String nombreAgencia, String nombreProceso, String cedula)
	 * throws RelativeException { return
	 * this.creditoNegociacionRepository.findAsignacionesByParamsPaginated(pw,
	 * codigoOperacion, nombreAgencia, nombreProceso, cedula); }
	 * 
	 * /** METODO QUE BUSCA LAS AGENCIAS
	 * 
	 * @param id
	 * @author JEROHAM CADENAS - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoAgencia findAgenciaById(Long id) throws RelativeException {
		try {
			return this.agenciaRepository.findById(id);

		} catch (Exception e) {
			throw new RelativeException(Constantes.MSG_ERROR_BUSQUEDA,
					"ERROR AL BUSCAR AGENCIA CON EL PARAMETRO: " + id);
		}
	}

	/**
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
	 * @author JEROHAM CADENAS - Relative Engine
	 * @throws RelativeException
	 */
	public List<TbQoAgencia> findAllAgencia(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.agenciaRepository.findAll(TbQoAgencia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.agenciaRepository.findAll(TbQoAgencia.class, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.agenciaRepository.findAll(TbQoAgencia.class, pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	public Long countAgencias() throws RelativeException {
		try {
			return agenciaRepository.countAll(TbQoAgencia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Agencias no encontradas " + e.getMessage());
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
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error al buscar todos los tracking " + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error en la busqueda" + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Error. No se puede contar registros" + e.getMessage());
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
				try {
					persisted = this.trackingRepository.findById(send.getId());
				} catch (RelativeException e) {
					String mensaje = "ERROR EN BUSQUEDA DE PROSPECTO " + e.getMessage();
					log.log(Level.SEVERE, mensaje, e);
				}
				log.info("===>>> NO SE CREO, VA A ACTUALIZAR ===========> " + persisted);
				return this.updateTracking(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setEstado(EstadoEnum.ACT.toString());
				if (send.getFechaInicio() != null && send.getFechaFin() != null) {
					log.info("===>>> NO SE CREO, VA A ACTUALIZAR ===========> "
							+ QuskiOroUtil.diasFecha(send.getFechaInicio(), send.getFechaFin()) + " // ");
					send.setTotalTiempo(new Time(QuskiOroUtil.diasFecha(send.getFechaInicio(), send.getFechaFin())));
				}
				return this.trackingRepository.add(send);

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo Operacion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error al actualizar" + e.getMessage());
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
			log.info("===>>> Entrando a Update Tracking ===========>  " + send + " " + persisted);
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
			log.info("===>>> datos guardados a persisted ===========>2 " + send + " " + persisted);
			return this.trackingRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando" + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
				// send.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
				// send.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				persisted = parametroRepository.add(send);
				parametrosSingleton.setParametros(this.parametroRepository.findAll(TbMiParametro.class));
				return persisted;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo transaccion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando la CausaNegativa " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					"Error actualizando CausaNegativa " + e.getMessage());
		}
	}

	public TbMiParametro findByNombre(String nombre) throws RelativeException {
		try {
			TbMiParametro a = parametroRepository.findByNombre(nombre);
			if (a != null) {
				return a;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "TbMiParametro no encontrada ");

			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "en la busqueda TbMiParametro " + e.getMessage());
		}
	}

	/**
	 * 
	 * 
	 * @param send
	 * @param persisted
	 * @return
	 * @throws RelativeException "id": 1, "actividad": "Busqueda de cliente",
	 *                           "codigoRegistro": 1, "estado": "Ingresado",
	 *                           "fechaAsignacion": 1593972500000, "fechaFin":
	 *                           1593973060000, "fechaInicio": 1593972500000,
	 *                           "fechaInicioAtencion": 1593972500000,
	 *                           "observacion": "", "proceso": "Cotizacion",
	 *                           "tiempoTotal": "00:09:00", "usuario": "Asesor"
	 */

	/**
	 * Busca los parametros por nombre, tipo o los dos parametros, si se envia
	 * ordenar se ordena por el campo orden
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
				throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrados por nombre o tipo ");

			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Parametros no encontrados por nombre o tipo " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	public Long countParametros() throws RelativeException {
		try {
			return parametroRepository.countAll(TbMiParametro.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
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
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
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
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error en la busqueda" + e.getMessage());
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
			log.info("===>>> ENTRANDO A MANAGE INGRESO EGRESO ===========> " + send);

			TbQoIngresoEgresoCliente persisted = null;
			if (send != null && send.getId() != null) {
				try {
					persisted = this.ingresoEgresoClienteRepository.findById(send.getId());
				} catch (RelativeException e) {
					String mensaje = "ERROR EN BUSQUEDA DE INGRESOS Y EGRESOS  DE CLIENTE AL MOMENTO DE ACTUALIZAR"
							+ e.getMessage();
					log.log(Level.SEVERE, mensaje, e);
				}
				log.info("===>>> NO SE CREO, VA A ACTUALIZAR ===========> " + persisted);
				return this.updateIngresoEgresoCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				log.info("===>>> NO SE ACTUALIZA, VA A CREAR ===========> " + send);
				send.setEstado(EstadoEnum.ACT.toString());
				return this.ingresoEgresoClienteRepository.add(send);

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR: ID NO ENCONTRADO EN JSON PARA ACTUALIZAR");
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error al actualizar" + e.getMessage());
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
			// log.info("===>>> Entrando a Update TbQoIngresoEgresoCliente ===========> " +
			// send + " " + persisted);
			persisted.setValor(send.getValor());
			persisted.setEsEgreso(send.getEsEgreso());
			persisted.setEsIngreso(send.getEsIngreso());
			persisted.setEstado(EstadoEnum.ACT.toString());
			// log.info("===>>> datos guardados a persisted ===========> " + send + " " +
			// persisted);
			return this.ingresoEgresoClienteRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando" + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error en la busqueda" + e.getMessage());
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
			// log.info("===>>> ENTRANDO A MANAGE PATRIMONIO ===========> " + send);
			TbQoPatrimonio persisted = null;
			if (send != null && send.getId() != null) {
				try {
					persisted = this.patrimonioRepository.findById(send.getId());
				} catch (RelativeException e) {
					String mensaje = "ERROR EN BUSQUEDA DE PATRIMONIO AL MOMENTO DE ACTUALIZAR" + e.getMessage();
					log.log(Level.SEVERE, mensaje, e);
				}
				// log.info("===>>> NO SE CREO, VA A ACTUALIZAR ===========> " + persisted);
				return this.updatePatrimonio(send, persisted);
			} else if (send != null && send.getId() == null) {
				// log.info("===>>> NO SE ACTUALIZA, VA A CREAR ===========> " + send);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT.toString());
				return this.patrimonioRepository.add(send);

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR: ID NO ENCONTRADO EN JSON PARA ACTUALIZAR");
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error al actualizar" + e.getMessage());
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
			// log.info("===>>> Entrando a Update TbQoIngresoEgresoCliente ===========> " +
			// send + " " + persisted);
			persisted.setActivos(send.getActivos());
			persisted.setAvaluo(send.getAvaluo());
			persisted.setPasivos(send.getPasivos());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT.toString());
			if (persisted.getActivos() != null && persisted.getPasivos() == null && persisted.getActivos() != "") {
				// log.info("===>>> datos guardados a persisted ACtivo ===========> " + send + "
				// " + persisted);
				return this.patrimonioRepository.update(persisted);

			} else if (persisted.getActivos() == null && persisted.getPasivos() != null
					&& persisted.getPasivos() != "") {
				// log.info("===>>> datos guardados a persisted Pasivo ===========> " + send + "
				// " + persisted);
				return this.patrimonioRepository.update(persisted);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR: ACTIVO Y PASIVO NO PUEDEN TENER VALORES AL MISMO TIEMPO");
			}

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando" + e.getMessage());
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
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error en la busqueda" + e.getMessage());
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error en la busqueda" + e.getMessage());
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
			// log.info("===>>> ENTRANDO A MANAGE DIRECCION ===========> " + send);

			TbQoDireccionCliente persisted = null;
			List<TbQoDireccionCliente> cambioInac = null;
			if (send != null && send.getId() != null) {
				try {
					persisted = this.direccionClienteRepository.findById(send.getId());
					cambioInac = this.direccionClienteRepository.findByIdCliente(send.getTbQoCliente().getId());
				} catch (RelativeException e) {
					String mensaje = "ERROR EN BUSQUEDA DE DIRECCION DE CLIENTE AL MOMENTO DE ACTUALIZAR"
							+ e.getMessage();
					log.log(Level.SEVERE, mensaje, e);
				}
				// log.info("===>>> NO SE CREO, VA A ACTUALIZAR DIRECCION ===========> " +
				// persisted);
				for (int i = 0; i < cambioInac.size(); i++) {
					if (send.getTipoDireccion().equals(cambioInac.get(i).getTipoDireccion())) {
						cambioInac.get(i).setEstado(EstadoEnum.INA.toString());
						this.direccionClienteRepository.update(cambioInac.get(i));
					}
				}
				return this.updateDireccionCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				// log.info("===>>> NO SE ACTUALIZA, VA A CREAR DIRECCION ===========> " +
				// send);
				try {
					cambioInac = this.direccionClienteRepository.findByIdCliente(send.getTbQoCliente().getId());
				} catch (RelativeException e) {
					String mensaje = "ERROR EN BUSQUEDA DE DIRECCION DE CLIENTE AL MOMENTO DE CREAR" + e.getMessage();
					log.log(Level.SEVERE, mensaje, e);
				}
				for (int i = 0; i < cambioInac.size(); i++) {
					if (send.getTipoDireccion().equals(cambioInac.get(i).getTipoDireccion())) {
						cambioInac.get(i).setEstado(EstadoEnum.INA.toString());
						this.direccionClienteRepository.update(cambioInac.get(i));
					}
				}
				send.setEstado(EstadoEnum.ACT.toString());
				return this.direccionClienteRepository.add(send);

			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"ERROR: ID NO ENCONTRADO EN JSON PARA ACTUALIZAR");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error al actualizar" + e.getMessage());
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
			// log.info("===>>> Entrando a Update TbQoIngresoEgresoCliente ===========> " +
			// send + " " + persisted);
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
			// log.info("===>>> datos guardados a persisted Activo ===========> " + send + "
			// " + persisted);
			return this.direccionClienteRepository.update(persisted);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando" + e.getMessage());
		}
	}

	public List<TbQoDireccionCliente> findDireccionClienteByIdClienteAndTipoDireccion(Long idC, String tipoDireccion)
			throws RelativeException {
		try {
			return direccionClienteRepository.findByIdClienteAndTipoDireccion(idC, tipoDireccion);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error en la busqueda " + e.getMessage());
		}
	}

	
	public List<TbQoTasacion> findTasacionByIdCreditoNegociacion(PaginatedWrapper pw, Long idCreditoNegociacion) throws RelativeException {
		if (pw == null) {
			return this.tasacionRepository.findByIdCreditoNegociacion(idCreditoNegociacion);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tasacionRepository.findByIdCreditoNegociacionPaged(idCreditoNegociacion, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tasacionRepository.findByIdCreditoNegociacionPaged(idCreditoNegociacion, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			}
		}
	}

	public Long countTasacionByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException {
		return this.tasacionRepository.countFindByIdCreditoNegociacion(idCreditoNegociacion);
	}

	
}
