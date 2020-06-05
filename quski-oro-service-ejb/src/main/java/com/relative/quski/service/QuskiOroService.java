package com.relative.quski.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
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
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoNegociacionCalculo;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoProceso;
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
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.NegociacionCalculoRepository;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.ParroquiaRepository;
import com.relative.quski.repository.PrecioOroRepository;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.ProvinciaRepository;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.TipoArchivoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.TipoOroRepository;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.VariableCrediticiaRepository;
import com.relative.quski.repository.spec.CatalogoByNombreSpec;
import com.relative.quski.repository.spec.ClienteByIdentificacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.repository.spec.DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec;
import com.relative.quski.repository.spec.TipoOroByQuilateSpec;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AsignacionesWrapper;
import com.relative.quski.wrapper.AutorizacionBuroWrapper;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.ListadoOperacionDevueltaWrapper;

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
	private VariableCrediticiaRepository variableCrediticiaRepository;
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
	private ProcesoRepository procesoRepository;
	@Inject
	private TrackingRepository trackingRepository;
	@Inject
	private ParametrosSingleton parametrosSingleton;
	@Inject
	private ParametroRepository parametroRepository;

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
			TbQoCliente persisted = null;
			if (send != null && send.getId() != null) {
				log.info("ingresa a actualizacion");
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
			if( send.getFechaNacimiento() != null ) {
				persisted.setFechaNacimiento(send.getFechaNacimiento());
			}
			// pon las validaciones par ESTOS CAMPOS Y PRUE OK YA DE NACIONALIDAD YA ESTA YA LE PONGO EL DE EDAD
			if (send.getEdad()!=null) {
				persisted.setEdad(send.getEdad());
			}
			 
		

			return clienteRepository.update(persisted);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Cliente " + e.getMessage());
		}
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
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
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
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param cedulaCliente
	 * @author SAUL MENDEZ - Relative Engine
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
			return cotizadorRepository.update(persisted);
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
			TbQoCotizador persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.cotizadorRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateCotizador(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return cotizadorRepository.add(send);
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
			persisted.setQuilate(send.getQuilate());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(send.getEstado());
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
			log.info("==> entra a manage Abono>>>>>>>>>>>>>>>>>" + send.getId());
			TbQoDetalleCredito persisted = null;
			if (send != null && send.getId() != null) {

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
			log.info("==> entra a manage Abono");
			TbQoPrecioOro persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.precioOroRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateDetalleCredito(send, persisted);
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
	public TbQoPrecioOro updateDetalleCredito(TbQoPrecioOro send, TbQoPrecioOro persisted) throws RelativeException {
		try {
			persisted.setPesoNetoEstimado(send.getPesoNetoEstimado());
			persisted.setEstado(send.getEstado());
			persisted.setPrecio(send.getPrecio());
			persisted.setFechaCreacion(persisted.getFechaCreacion());
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(send.getEstado());
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
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
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
	 * Metodo que busca por el numero de cedula del cliente
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @return Cantidad de entidades encontradas
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

	public List<TbQoCliente> findClienteByParams(PaginatedWrapper pw, String identificacion, String primerNombre,
			String apellidoPaterno, String segundoNombre, String apellidoMaterno, String telefono, String celular,
			String correo, EstadoEnum estado) throws RelativeException {
		return this.clienteRepository.findByParams(pw, identificacion, primerNombre, apellidoPaterno, segundoNombre,
				apellidoMaterno, telefono, celular, correo, estado);
	}

	public Long countClienteByParams(String identificacion, String primerNombre, String apellidoPaterno,
			String segundoNombre, String apellidoMaterno, String telefono, String celular, String correo,
			EstadoEnum estado) throws RelativeException {
		return this.clienteRepository.countByParams(identificacion, primerNombre, apellidoPaterno, segundoNombre,
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

	public List<TbQoVariablesCrediticia> findVariableCrediticiaByIdCotizacion(PaginatedWrapper pw, Long idCotizador)
			throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return variableCrediticiaRepository.findByIdCotizacion(pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections(), idCotizador);
		} else {
			return variableCrediticiaRepository.findByIdCotizacion(idCotizador);
		}
	}

	public Long countVariblesCrediticiaByIdCotizacion(Long idCotizador) throws RelativeException {

		return variableCrediticiaRepository.countByIdCotizacion(idCotizador);
	}

	public TbQoVariablesCrediticia findVariableCrediticiaById(Long id) throws RelativeException {

		try {
			return variableCrediticiaRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	public List<TbQoVariablesCrediticia> findAllVariablesCrediticias(PaginatedWrapper pw) throws RelativeException {
		if (pw == null) {
			return this.variableCrediticiaRepository.findAll(TbQoVariablesCrediticia.class);
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.variableCrediticiaRepository.findAll(TbQoVariablesCrediticia.class, pw.getStartRecord(),
						pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.variableCrediticiaRepository.findAll(TbQoVariablesCrediticia.class, pw.getSortFields(),
						pw.getSortDirections());
			}
		}
	}

	public Long countVariablesCrediticias() throws RelativeException {
		try {
			return variableCrediticiaRepository.countAll(TbQoVariablesCrediticia.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Parametros no encontrado " + e.getMessage());
		}
	}

	public TbQoVariablesCrediticia manageVariableCrediticia(TbQoVariablesCrediticia send) throws RelativeException {
		try {
			log.info("==> entra a manage variableCrediticia");
			TbQoVariablesCrediticia persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findVariableCrediticiaById(send.getId());
				return this.updateVariableCrediticia(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaActualizacion(new Date(System.currentTimeMillis()));
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				return variableCrediticiaRepository.add(send);
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

	private TbQoVariablesCrediticia updateVariableCrediticia(TbQoVariablesCrediticia send,
			TbQoVariablesCrediticia persisted) throws RelativeException {
		try {
			persisted.setNombre(send.getNombre());
			persisted.setValor(send.getValor());
			persisted.setTbQoCotizador(send.getTbQoCotizador());
			persisted.setTbQoNegociacion(send.getTbQoNegociacion());
			persisted.setFechaActualizacion(new Date());
			persisted.setEstado(send.getEstado());

			return this.variableCrediticiaRepository.update(persisted);
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
			List<TbQoCliente> clientes = this.findClienteByIdentifiacion(fw.getRelatedIdStr());

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
		DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec docHabilitanteSpec = new DocumentoByTipoDocumentoAndClienteAndCotAndNegSpec();

		try {
			return documentoHabilitanteRepository.findByTipoDocumentoAndCliAndCotAndNeg(idTipoDocumento,
					identificacionCliente, idCotizador, idNegociacion);

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}
	}

	/**
	 * Variable Crediticia Metodo que busca por el IdCotizador en las variables
	 * crediticias
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
	 * ======= public List<TbQoVariableCrediticia>
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
	 */
	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author SAUL MENDEZ - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
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
	 * @param codigoOperacion
	 * @param estado
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
					fechaDesde, fechaHasta, codigoOperacion, proceso, identificacion, agencia));
		} else {
			if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.creditoNegociacionRepository.findPorCustomFilterCreditos(pw, fechaDesde, fechaHasta,
						codigoOperacion, proceso, identificacion, agencia);
			} else {
				return this.creditoNegociacionRepository.findAllBySpecification(new CreditoNegociacionByParamsSpec(
						fechaDesde, fechaHasta, codigoOperacion, proceso, identificacion, agencia));
			}
		}

	}

	public List<ListadoOperacionDevueltaWrapper> listOperacionesDevueltas(PaginatedWrapper pw, String codigo,
			String agencia, String proceso, String cedula) throws RelativeException {
		return this.creditoNegociacionRepository.listOperacionesDevueltas(pw, codigo, agencia, proceso, cedula);
	}

	public Integer countCreditoNegociacionByParams(String fechaDesde, String fechaHasta, String codigoOperacion,
			String proceso, String identificacion, String agencia) throws RelativeException {

		return this.creditoNegociacionRepository.countBySpecification(new CreditoNegociacionByParamsSpec(fechaDesde,
				fechaHasta, codigoOperacion, proceso, identificacion, agencia)).intValue();
	}

	public Integer countOperacionesDevueltas(PaginatedWrapper pw, String codigo, String agencia, String proceso,
			String cedula) throws RelativeException {
		try {
			return this.creditoNegociacionRepository.countOperacionesDevueltas(pw, codigo, agencia, proceso, cedula);
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
			return negociacionCalculoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "Error actualizando Tipo oro " + e.getMessage());
		}
	}

	/**
	 * Referencias Personales
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
			TbQoReferenciaPersonal persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.referenciaPersonalRepository.findById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updateReferenciaPersonal(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
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
	 * Metodo que lista la informacion de las entidades encontradas
	 * 
	 * @param pw Objeto generico que tiene la informacion que determina si el
	 *           resultado es total o paginado
	 * @return Listado de entidades encontradas
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
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param nombre
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoCatalogo> findNombreByCatalogo(PaginatedWrapper pw, String nombre) throws RelativeException {
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
	 * METODO QUE BUSCA LOS PRECIOS OROS LIGADOS A LA COTIZACION
	 * 
	 * @param pw
	 * @param tipo
	 * @author BRAYAN MONGE - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoCatalogo> findTipoByCatalogo(PaginatedWrapper pw, String tipo) throws RelativeException {
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
	 * @param
	 * @author JEROHAM CADENAS - Relative Engine
	 * @throws RelativeException
	 */
	public List<AsignacionesWrapper> findAsignacionesByParamsPaginated(PaginatedWrapper pw, String codigoOperacion,
			String nombreAgencia, String nombreProceso, String cedula) throws RelativeException {
		return this.creditoNegociacionRepository.findAsignacionesByParamsPaginated(pw, codigoOperacion, nombreAgencia,
				nombreProceso, cedula);
	}

	/**
	 * METODO QUE BUSCA LAS AGENCIAS
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
	 * METODO QUE BUSCA LOS PROCESOS
	 * 
	 * @param PaginatedWrapper
	 * @author JEROHAM CADENAS - Relative Engine
	 * @throws RelativeException
	 */

	public List<TbQoProceso> findAllProceso(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.procesoRepository.findAll(TbQoProceso.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.procesoRepository.findAll(TbQoProceso.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.procesoRepository.findAll(TbQoProceso.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Procesos no encontrados " + e.getMessage());
		}

	}

	public Long countProceso() throws RelativeException {
		try {
			return procesoRepository.countAll(TbQoProceso.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Procesos no encontrados " + e.getMessage());
		}
	}

	public List<AsignacionesWrapper> findClienteBycodigoOperacion(String codigoOperacion) throws RelativeException {
		return this.clienteRepository.clienteBycodigoOperacion(codigoOperacion);
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
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error. No se puede contar registros" + e.getMessage());
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
					@SuppressWarnings("unused")
					String mensaje = "ERROR EN BUSQUEDA DE PROSPECTO " + e.getMessage();
					// log.log(Level.SEVERE, mensaje,e);
				}
				log.info("===>>> NO SE CREO, VA A ACTUALIZAR ===========> " + persisted);
				return this.updateTracking(send, persisted);
			} else if (send != null && send.getId() == null) {
				log.info("===>>> NO SE ACTUALIZA, VA A CREAR ===========> " + send);
				send.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				return this.trackingRepository.add(send);
				
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Error no se realizo Operacion");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
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
			log.info("===>>> Entrando a Update Tracking ===========>2 " + send + " " +  persisted);
			persisted.setActividad(send.getActividad());
			persisted.setEstado(send.getEstado());
			persisted.setFechaAsignacion(send.getFechaAsignacion());
			persisted.setFechaFin(send.getFechaFin());
			persisted.setFechaInicioAtencion(send.getFechaInicioAtencion());
			persisted.setObservacion(send.getObservacion());
			persisted.setTotalTiempo(send.getTotalTiempo());
			persisted.setUsuario(send.getUsuario());
			log.info("===>>> datos guardados a persisted ===========>2 " + send + " " +  persisted);
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
=======

	/**
>>>>>>> a6648385b36308fd5bb6c7c30ba6c715fb58c169
	 * Metodo que lista la informacion de las entidades encontradas
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

}
