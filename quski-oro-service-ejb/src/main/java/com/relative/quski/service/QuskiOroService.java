package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.enums.TipoRubroEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoArchivoCliente;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoIngresoEgresoCliente;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoPatrimonio;
import com.relative.quski.model.TbQoPrecioOro;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.model.TbQoRiesgoAcumulado;
import com.relative.quski.model.TbQoRubro;
import com.relative.quski.model.TbQoTasacion;
import com.relative.quski.model.TbQoTelefonoCliente;
import com.relative.quski.model.TbQoTipoArchivo;
import com.relative.quski.model.TbQoTipoDocumento;
import com.relative.quski.model.TbQoTracking;
import com.relative.quski.model.TbQoVariablesCrediticia;
import com.relative.quski.repository.ArchivoClienteRepository;
import com.relative.quski.repository.ClientePagoRepository;
import com.relative.quski.repository.ClienteRepository;
import com.relative.quski.repository.CotizadorRepository;
import com.relative.quski.repository.CreditoNegociacionRepository;
import com.relative.quski.repository.CuentaBancariaRepository;
import com.relative.quski.repository.DatoTrabajoClienteRepository;
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
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.repository.RiesgoAcumuladoRepository;
import com.relative.quski.repository.RubroRepository;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.TelefonoClienteRepository;
import com.relative.quski.repository.TipoArchivoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.VariablesCrediticiaRepository;
import com.relative.quski.repository.spec.ClienteByIdentificacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.AutorizacionBuroWrapper;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.CalculadoraEntradaWrapper;
import com.relative.quski.wrapper.CalculadoraRespuestaWrapper;
import com.relative.quski.wrapper.ClienteCompletoWrapper;
import com.relative.quski.wrapper.ConsultaTablaWrapper;
import com.relative.quski.wrapper.CreacionClienteRespuestaCoreWp;
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.CreditoCreadoSoftbank;
import com.relative.quski.wrapper.CrmEntidadWrapper;
import com.relative.quski.wrapper.CrmGuardarProspectoWrapper;
import com.relative.quski.wrapper.CrmProspectoCortoWrapper;
import com.relative.quski.wrapper.CrmProspectoWrapper;
import com.relative.quski.wrapper.CuotasAmortizacionWrapper;
import com.relative.quski.wrapper.DatosCuentaClienteWrapper;
import com.relative.quski.wrapper.DatosGarantiasWrapper;
import com.relative.quski.wrapper.DatosRegistroWrapper;
import com.relative.quski.wrapper.EquifaxPersonaWrapper;
import com.relative.quski.wrapper.EquifaxVariableWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.Informacion;
import com.relative.quski.wrapper.Informacion.DATOSCLIENTE;
import com.relative.quski.wrapper.Informacion.INGRESOSEGRESOS;
import com.relative.quski.wrapper.Informacion.INGRESOSEGRESOS.RUBRO;
import com.relative.quski.wrapper.Informacion.XmlVariablesInternas.VariablesInternas;
import com.relative.quski.wrapper.Informacion.XmlVariablesInternas.VariablesInternas.Variable;
import com.relative.quski.wrapper.IntegracionDatosClienteWrapper;
import com.relative.quski.wrapper.IntegracionEntidadWrapper;
import com.relative.quski.wrapper.IntegracionRubroWrapper;
import com.relative.quski.wrapper.IntegracionVariableWrapper;
import com.relative.quski.wrapper.JoyaWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;
import com.relative.quski.wrapper.OperacionCreditoNuevoWrapper;
import com.relative.quski.wrapper.RespuestaCrearClienteWrapper;
import com.relative.quski.wrapper.ResultOperacionesAprobarWrapper;
import com.relative.quski.wrapper.ResultOperacionesWrapper;
import com.relative.quski.wrapper.SoftbankActividadEconomicaWrapper;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;
import com.relative.quski.wrapper.SoftbankContactosWrapper;
import com.relative.quski.wrapper.SoftbankCuentasBancariasWrapper;
import com.relative.quski.wrapper.SoftbankDatosTrabajoWrapper;
import com.relative.quski.wrapper.SoftbankDireccionWrapper;
import com.relative.quski.wrapper.SoftbankOperacionWrapper;
import com.relative.quski.wrapper.SoftbankRespuestaWrapper;
import com.relative.quski.wrapper.SoftbankRiesgoWrapper;
import com.relative.quski.wrapper.SoftbankTablaAmortizacionWrapper;
import com.relative.quski.wrapper.SoftbankTelefonosWrapper;
import com.relative.quski.wrapper.TelefonosContactoClienteWrapper;
import com.relative.quski.wrapper.TokenWrapper;
import com.relative.quski.wrapper.TrackingWrapper;

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
	private TelefonoClienteRepository telefonoClienteRepository;
	@Inject
	private DatoTrabajoClienteRepository datoTrabajoClienteRepository;
	@Inject
	private CuentaBancariaRepository cuentaBancariaRepository;
	@Inject
	private ExcepcionesRepository excepcionesRepository;
	@Inject
	private ClientePagoRepository clientePagoRepository;
	@Inject
	private RegistrarPagoRepository registrarPagoRepository;
	@Inject
	private ExcepcionRolRepository excepcionRolRepository;
	@Inject
	private ProcesoRepository procesoRepository;
	@Inject
	private RubroRepository rubroRepository;

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
					return clienteRepository.add(setearCampos(send));
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

	private TbQoCliente setearCampos(TbQoCliente send) {
		send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		send.setEstado(EstadoEnum.ACT);
		if( send.getApellidoPaterno() != null || send.getApellidoMaterno() != null || send.getPrimerNombre() != null || send.getSegundoNombre() != null) {
			String apP = send.getApellidoPaterno() != null ? send.getApellidoPaterno() : "" + " ";
			String apM = send.getApellidoMaterno() != null ? send.getApellidoMaterno() : "" + " ";
			String noP = send.getPrimerNombre() != null ? send.getPrimerNombre() : "" + " ";
			String noS = send.getSegundoNombre() != null ? send.getSegundoNombre() : "";
			String nombre = apP +" "+ apM +" "+ noP +" "+ noS;
			send.setNombreCompleto(nombre);
		}

		return send;
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
			if( !StringUtils.isBlank( send.getActividadEconomica() ) ) {
				persisted.setActividadEconomica(  send.getActividadEconomica() ); 
			}
			if( !StringUtils.isBlank( send.getApellidoMaterno() ) ) {
				persisted.setApellidoMaterno(  send.getApellidoMaterno() ); 
			}
			if( send.getAgencia() != null ) {
				persisted.setAgencia(  send.getAgencia() ); 
			}
			if( !StringUtils.isBlank( send.getUsuario() ) ) {
				persisted.setUsuario(  send.getUsuario() ); 
			}
			if( !StringUtils.isBlank( send.getApellidoPaterno() ) ) {
				persisted.setApellidoPaterno(  send.getApellidoPaterno() ); 
			}
			if( !StringUtils.isBlank( send.getCampania() ) ) {
				persisted.setCampania(  send.getCampania() ); 
			}
			if( !StringUtils.isBlank( send.getCanalContacto() ) ) {
				persisted.setCanalContacto(  send.getCanalContacto() ); 
			}	
			if( send.getCargasFamiliares() != null ) {
				persisted.setCargasFamiliares(  send.getCargasFamiliares() ); 
			}
			if( send.getEdad() != null ) {
				persisted.setEdad(  send.getEdad() ); 
			}
			if( !StringUtils.isBlank( send.getEmail() ) ) {
				persisted.setEmail(  send.getEmail() ); 
			}	
			if( send.getEstado() != null ) {
				persisted.setEstado(  send.getEstado() ); 
			}else { persisted.setEstado( EstadoEnum.ACT );  }
			if( !StringUtils.isBlank( send.getEstadoCivil() ) ) {
				persisted.setEstadoCivil(  send.getEstadoCivil() ); 
			}
			if( send.getFechaNacimiento() != null ) {
				persisted.setFechaNacimiento(  send.getFechaNacimiento() ); 
			}
			if( !StringUtils.isBlank( send.getGenero() ) ) {
				persisted.setGenero(  send.getGenero() ); 
			}
			if( !StringUtils.isBlank( send.getLugarNacimiento() ) ) {
				persisted.setLugarNacimiento(  send.getLugarNacimiento() ); 
			}
			if( send.getNacionalidad() != null ) {
				persisted.setNacionalidad(  send.getNacionalidad() ); 
			}
			if( !StringUtils.isBlank( send.getNivelEducacion() ) ) {
				persisted.setNivelEducacion(  send.getNivelEducacion() ); 
			}
			if( !StringUtils.isBlank( send.getPrimerNombre() ) ) {
				persisted.setPrimerNombre(  send.getPrimerNombre() ); 
			}
			if( !StringUtils.isBlank( send.getProfesion() ) ) {
				persisted.setProfesion(  send.getProfesion() ); 
			}
			if( !StringUtils.isBlank( send.getPublicidad() ) ) {
				persisted.setPublicidad(  send.getPublicidad() ); 
			}
			if( !StringUtils.isBlank( send.getSegundoNombre() ) ) {
				persisted.setSegundoNombre(  send.getSegundoNombre() ); 
			}
			if( !StringUtils.isBlank( send.getSeparacionBienes() ) ) {
				persisted.setSeparacionBienes(  send.getSeparacionBienes() ); 
			}
			if( !StringUtils.isBlank( send.getAprobacionMupi() ) ) {
				persisted.setAprobacionMupi(  send.getAprobacionMupi() ); 
			}
			if( !StringUtils.isBlank( send.getNombreCompleto() ) ) {
				persisted.setNombreCompleto(  send.getNombreCompleto() ); 
			}
			if( send.getApellidoPaterno() != null || send.getApellidoMaterno() != null || send.getPrimerNombre() != null || send.getSegundoNombre() != null) {
				String apP = send.getApellidoPaterno() != null ? send.getApellidoPaterno() + " ": "";
				String apM = send.getApellidoMaterno() != null ? send.getApellidoMaterno() + " ": "";
				String noP = send.getPrimerNombre() != null ? send.getPrimerNombre()  + " ": "";
				String noS = send.getSegundoNombre() != null ? send.getSegundoNombre()  + " ": "";
				String nombre = apP + apM + noP + noS;
				send.setNombreCompleto(nombre);
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
					errores.put("direccion-" + dc.getDivisionPolitica(), "ERROR REGISTRO DIRRECION" + e.getMensaje());
				}
			});
		}
		return errores;
	}
	private Map<String, String> createDatosTrabajoCliente(TbQoCliente cliente, List<TbQoDatoTrabajoCliente> datos) {
		Map<String, String> errores = new HashMap<>();
		if (datos != null && !datos.isEmpty()) {
			datos.forEach(dc -> {
				dc.setTbQoCliente(cliente);
				try {
					this.manageDatoTrabajoCliente(dc);
				} catch (RelativeException e) {
					errores.put("DATOS Error-" + dc.getIdSoftbank(), "ERROR REGISTRO DIRRECION" + e.getMensaje());
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
	 * @throws RelativeException 
	 */
	private Map<String, String> createIngresoEgresoCliente(TbQoCliente cliente, List<TbQoIngresoEgresoCliente> ingresoEgreso) throws RelativeException {
		Map<String, String> errores = new HashMap<>();
		List<TbQoIngresoEgresoCliente> existentes = this.ingresoEgresoClienteRepository.findByIdCliente( cliente.getId() );
		if(existentes != null) {
			existentes.forEach(f->{
				f.setEstado( EstadoEnum.INA );
				log.info("==========> INGRESO ID: "+ f.getId() +" || =========> ESTADO: "+ f.getEstado() + " <===========");
			});			
		}
		if (ingresoEgreso != null && !ingresoEgreso.isEmpty()) {
			ingresoEgreso.forEach(ie -> {
				ie.setTbQoCliente(cliente);
				if(ie.getId() != null && existentes != null) {
					existentes.forEach(e->{
						if( ie.getId().equals(e.getId() )) {
							e.setEstado( EstadoEnum.ACT );
							log.info("==========> ACTUALIZANDO EN FOREACH INGRESO ID: "+ e.getId() +" || =========> ESTADO: "+ e.getEstado() + " <===========");
							try {
								this.ingresoEgresoClienteRepository.update( e );
							} catch (RelativeException e1) {
								e1.printStackTrace();
								errores.put("Ingreso Egreso-" + e.getId()," ERROR REGISTRO INGRESOS EGRESOS " + e1.getMensaje());
							}
						}
					});
					existentes.forEach(a ->{
						if( a.getEstado().equals( EstadoEnum.INA ) ) {
							try {
								this.ingresoEgresoClienteRepository.update( a );
							} catch (RelativeException e1) {
								e1.printStackTrace();
								errores.put("Ingreso Egreso-" + a.getId()," ERROR REGISTRO INGRESOS EGRESOS " + e1.getMensaje());
							}
						}
					});
					
				}else {
					try {
						this.manageIngresoEgresoCliente(ie);
					} catch (RelativeException e) {
						e.printStackTrace();
						errores.put("Ingreso Egreso-" + ie.getTbQoCliente(),
								"ERROR REGISTRO INGRESOS EGRESOS " + e.getMensaje());
					}					
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
	 * @throws RelativeException 
	 */
	private Map<String, String> createPatrimonioCliente(TbQoCliente cliente, List<TbQoPatrimonio> patrimonio) throws RelativeException {
		Map<String, String> errores = new HashMap<>();
		List<TbQoPatrimonio> existentes = this.patrimonioRepository.findByIdCliente( cliente.getId() );
		if(existentes != null) {
			existentes.forEach(f->{
				f.setEstado( EstadoEnum.INA );
			});			
		}
		if (patrimonio != null && !patrimonio.isEmpty()) {
			patrimonio.forEach(pa -> {
				pa.setTbQoCliente(cliente);
				if( pa.getId() != null && existentes != null) {
					existentes.forEach(e->{
						if( pa.getId().equals( e.getId())) {
							e.setEstado( EstadoEnum.ACT );							
							try {
								this.managePatrimonio( e );
							} catch (RelativeException e1) {
								errores.put("Patrimonio " + e.getId()," ERROR REGISTRO PATRIMONIO " + e1.getMensaje());
							}
						}
					});
					existentes.forEach(a->{
						if(a.getEstado().equals( EstadoEnum.INA )) {
							try {
								this.managePatrimonio( a );
							} catch (RelativeException e1) {
								errores.put("Patrimonio " + a.getId()," ERROR REGISTRO PATRIMONIO " + e1.getMensaje());
							}
						}
					});
				}
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
	 * @throws RelativeException 
	 */
	private Map<String, String> createReferenciasPersonales(TbQoCliente cliente,List<TbQoReferenciaPersonal> referencia) throws RelativeException {
		Map<String, String> errores = new HashMap<>();
		List<TbQoReferenciaPersonal> existentes = this.referenciaPersonalRepository.findByIdCliente( cliente.getId() );
		if(existentes != null) {
			existentes.forEach(f->{
				f.setEstado( EstadoEnum.INA );
			});
		}
		if (referencia != null && !referencia.isEmpty()) {
			referencia.forEach(re -> {
				re.setTbQoCliente(cliente);
				if( re.getId() != null && existentes != null ) {
					existentes.forEach(e->{
						if( re.getId().equals( e.getId() ) ) {
							e.setEstado( EstadoEnum.ACT );
							try {
								this.manageReferenciaPersonal( e );
							} catch (RelativeException e1) {
								errores.put("Referencia " + e.getId()," ERROR REGISTRO REFERENCIA " + e1.getMensaje());
							}
						}
					});
					existentes.forEach(a ->{
						if(a.getEstado().equals(EstadoEnum.INA)) {
							try {
								this.manageReferenciaPersonal( a );
							} catch (RelativeException e1) {
								errores.put("Referencia " + a.getId()," ERROR REGISTRO REFERENCIA " + e1.getMensaje());
							}
						}
					});
				}
				try {
					this.manageReferenciaPersonal(re);
				} catch (RelativeException e) {
					errores.put("Referencia -" + re.getTbQoCliente(), "Error registro Referencia " + e.getMensaje());
				}
			});
		}
		return errores;
	}
	private Map<String, String> createTelefonosCliente(TbQoCliente cliente, List<TbQoTelefonoCliente> telefonos) throws RelativeException {
		Map<String, String> errores = new HashMap<>();
		List<TbQoTelefonoCliente> existentes = this.telefonoClienteRepository.findByIdCliente( cliente.getId() );
		if(existentes != null) {
			existentes.forEach(f->{
				f.setEstado( EstadoEnum.INA );
			});			
		}
		if (telefonos != null && !telefonos.isEmpty()) {
			telefonos.forEach(re -> {
				re.setTbQoCliente(cliente);
				if( re.getId() != null && existentes != null ) {
					existentes.forEach(e->{
						if( re.getId().equals( e.getId() ) ) {
							e.setEstado( EstadoEnum.ACT );
							try {
								this.manageTelefonoCliente( e );
							} catch (RelativeException e1) {
								errores.put("telefono " + e.getId()," ERROR REGISTRO telefono " + e1.getMensaje());
							}
						}
					});
					existentes.forEach(a->{
						if(a.getEstado().equals( EstadoEnum.INA)) {
							try {
								this.manageTelefonoCliente(a );
							} catch (RelativeException e1) {
								errores.put("telefono " + a.getId()," ERROR REGISTRO telefono " + e1.getMensaje());
							}
						}
					});
				}
				try {
					this.manageTelefonoCliente(re);
				} catch (RelativeException e) {
					errores.put("telefono-" + re.getTbQoCliente(), "Error registro telefono " + e.getMensaje());
				}
			});
		}
		return errores;
	}
	private Map<String, String> createCuentasBancarias(TbQoCliente cliente, List<TbQoCuentaBancariaCliente> cuentas) throws RelativeException {
		Map<String, String> errores = new HashMap<>();
		List<TbQoCuentaBancariaCliente> existentes = this.cuentaBancariaRepository.findByIdCliente( cliente.getId() );
		if(existentes != null) {
			existentes.forEach(f->{
				f.setEstado( EstadoEnum.INA );
			});			
		}
		if (cuentas != null && !cuentas.isEmpty()) {
			cuentas.forEach(re -> {
				re.setTbQoCliente(cliente);
				if( re.getId() != null && existentes != null ) {
					existentes.forEach(e->{
						if( re.getId().equals( e.getId() ) ) {
							e.setEstado( EstadoEnum.ACT );
							try {
								this.manageCuentaBancariaCliente( e );
							} catch (RelativeException e1) {
								errores.put("Cuenta " + e.getId()," ERROR REGISTRO Cuenta " + e1.getMensaje());
							}
						}
					});
					existentes.forEach(a->{
						if(a.getEstado().equals( EstadoEnum.INA)) {
							try {
								this.manageCuentaBancariaCliente( a );
							} catch (RelativeException e1) {
								errores.put("Cuenta " + a.getId()," ERROR REGISTRO Cuenta " + e1.getMensaje());
							}
						}
					});
				}
				try {
					this.manageCuentaBancariaCliente(re);
				} catch (RelativeException e) {
					errores.put("cuentas-" + re.getTbQoCliente(), "Error registro cuentas " + e.getMensaje());
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
	 * metodo que actualiza los datos del cliente desde negociacion
	 * @param cliente
	 * @return
	 * @throws RelativeException 
	 */
	public TbQoCliente updateCliente(TbQoCliente cliente) throws RelativeException {
		if( cliente == null || cliente.getId() == null) {
			return null;
		}
		if(cliente.getTbQoTelefonoClientes() != null && !cliente.getTbQoTelefonoClientes().isEmpty()) {
			cliente.getTbQoTelefonoClientes().forEach( t ->{
				try {
					t.setTbQoCliente(cliente);
					t=manageTelefonoCliente(t);
				} catch (RelativeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info(">>>======= ERRO AL ACTUALIZAR TELEFONOS =======<<<");
				}
			});
		}
		manageCliente(cliente);
		return cliente;
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
	public TbQoCotizador findCotizadorById(Long id) throws RelativeException {
		try {
			return cotizadorRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoPrecioOro registrarPrecioOroByCotizacion(TbQoPrecioOro po) throws RelativeException {
		TbQoCotizador cot = this.findCotizadorById(po.getTbQoCotizador().getId());
		po.setTbQoCotizador(cot);
		return this.managePrecioOro(po);
	}

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
		try {
			persisted.setCodigoCotizacion( QuskiOroConstantes.CODIGO_COTIZADOR.concat(StringUtils.leftPad(persisted.getId().toString(), 7, "0")));
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

	/** * * * * * * * * * * * @DETALLE @CREDITO */
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
	 ************************************ @Cliente
	 */

	private TbQoCliente createCliente(String cedula) throws RelativeException {
		try {
			TbQoCliente cliente = this.findClienteByIdentifiacion(cedula);
			if (cliente != null) {
				log.info("CLIENTE EN BASE =================>   " + cliente + "   <===========");
				return cliente;
			} 
			cliente = this.clienteSoftToTbQoCliente(this.findClienteSoftbank(cedula));
			if (cliente != null) {
				log.info("CLIENTE EN SOFTBANK =============>   " + cliente + "   <=============");
				return this.manageCliente(cliente);
			} 
			cliente = this.prospectoCrmToTbQoCliente(this.findProspectoCrm(cedula));
			if (cliente != null) {
				log.info("CLIENTE EN CRM ===================>   " + cliente + "   <==============");
				return this.manageCliente(cliente);
			}
			return null;			
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_CREATE_CLIENTE + e.getMensaje());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
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
				} else if (element.getTbQoNegociacion() != null) {
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
	 * * * * * * * * * * * @NEGOCIACION
	 */
	public TbQoNegociacion findNegociacionById(Long id) throws RelativeException {
		try {
			return this.negociacionRepository.findById(id);
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
				if (send.getTbQoCliente() == null && send.getTbQoCliente().getId() == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Ingrese un id de cliente");
				}
				return this.negociacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}
	public TbQoDatoTrabajoCliente manageDatoTrabajoCliente(TbQoDatoTrabajoCliente send) throws RelativeException {
		try {
			TbQoDatoTrabajoCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.datoTrabajoClienteRepository.findById( send.getId());
				return this.updateDatoTrabajoCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				if (send.getTbQoCliente() == null && send.getTbQoCliente().getId() == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Ingrese un id de cliente");
				}
				return this.datoTrabajoClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}
	private TbQoDatoTrabajoCliente updateDatoTrabajoCliente(TbQoDatoTrabajoCliente send, TbQoDatoTrabajoCliente persisted)
			throws RelativeException {
		try {
			if( send.getActividadEconomica() != null ) {
				persisted.setActividadEconomica(   send.getActividadEconomica() );
			}
			if( !StringUtils.isBlank( send.getActividadEconomicaMupi() ) ) {
				persisted.setActividadEconomicaMupi(   send.getActividadEconomicaMupi() );
			}
			if( !StringUtils.isBlank( send.getCargo() ) ) {
				persisted.setCargo(   send.getCargo() );
			}
			if( send.getEsRelacionDependencia() != null ) {
				persisted.setEsRelacionDependencia(   send.getEsRelacionDependencia() );
			}
			if( !StringUtils.isBlank( send.getNombreEmpresa() ) ) {
				persisted.setNombreEmpresa(   send.getNombreEmpresa() );
			}
			if( send.getEsprincipal() != null ) {
				persisted.setEsprincipal(   send.getEsprincipal() );
			}
			if( send.getTbQoCliente() != null ) {
				persisted.setTbQoCliente(   send.getTbQoCliente() );
			}
			if( send.getIdSoftbank() != null ) {
				persisted.setIdSoftbank(   send.getIdSoftbank() );
			}
			if( !StringUtils.isBlank( send.getOcupacion() ) ) {
				persisted.setOcupacion(   send.getOcupacion() );
			}
			if( !StringUtils.isBlank( send.getOrigenIngreso() ) ) {
				persisted.setOrigenIngreso(   send.getOrigenIngreso() );
			}
			return datoTrabajoClienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	private TbQoNegociacion updateNegociacion(TbQoNegociacion send, TbQoNegociacion persisted)
			throws RelativeException {
		try {
			if (send.getAsesor() != null) {
				persisted.setAsesor(send.getAsesor());
			}
			if (send.getAprobador() != null) {
				persisted.setAprobador(send.getAprobador());
			}
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return negociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	
	public TbQoTelefonoCliente manageTelefonoCliente(TbQoTelefonoCliente send) throws RelativeException {
		try {
			TbQoTelefonoCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.telefonoClienteRepository.findById( send.getId());
				return this.updateTelefonoCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				if (send.getTbQoCliente() == null && send.getTbQoCliente().getId() == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Ingrese un id de cliente");
				}
				send.setEstado( EstadoEnum.ACT );
				return this.telefonoClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}
	private TbQoTelefonoCliente updateTelefonoCliente(TbQoTelefonoCliente send, TbQoTelefonoCliente persisted)
			throws RelativeException {
		try {
			if (send.getIdSoftbank() != null) {
				persisted.setIdSoftbank(send.getIdSoftbank());
			}
			if(!StringUtils.isBlank( send.getNumero() )) {
				persisted.setNumero( send.getNumero() );
			}
			if( send.getEstado()  != null ) {
				persisted.setEstado( send.getEstado() );
			}else {
				persisted.setEstado( EstadoEnum.ACT );
			}
			if(!StringUtils.isBlank( send.getTipoTelefono() ) ) {
				persisted.setTipoTelefono( send.getTipoTelefono() );
			}
			if( send.getTbQoCliente() != null ) {
				persisted.setTbQoCliente( send.getTbQoCliente() );
			}
			return telefonoClienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	public TbQoCuentaBancariaCliente manageCuentaBancariaCliente(TbQoCuentaBancariaCliente send) throws RelativeException {
		try {
			TbQoCuentaBancariaCliente persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.cuentaBancariaRepository.findById( send.getId());
				return this.updateCuentaBancariaCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				if (send.getTbQoCliente() == null && send.getTbQoCliente().getId() == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "Ingrese un id de cliente");
				}
				send.setEstado( EstadoEnum.ACT );
				return this.cuentaBancariaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}
	private TbQoCuentaBancariaCliente updateCuentaBancariaCliente(TbQoCuentaBancariaCliente send, TbQoCuentaBancariaCliente persisted)
			throws RelativeException {
		try {
			if (send.getIdSoftbank() != null) {
				persisted.setIdSoftbank(send.getIdSoftbank());
			}
			if(send.getBanco() != null ){
				persisted.setBanco( send.getBanco() );
			}
			if(send.getEsAhorros() != null ) {
				persisted.setEsAhorros( send.getEsAhorros() );
			}
			if(!StringUtils.isBlank( send.getCuenta()) )  {
				persisted.setCuenta( send.getCuenta() );
			}
			if( send.getTbQoCliente() != null ) {
				persisted.setTbQoCliente( send.getTbQoCliente() );
			}
			if( send.getEstado() != null ) {
				persisted.setEstado( send.getEstado());
			}else {
				persisted.setEstado( EstadoEnum.ACT );
			}
			return cuentaBancariaRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	/**
	 * * * * * * * * * * * @TASACION
	 */
	public TbQoTasacion findTasacionById(Long id) throws RelativeException {
		try {
			return tasacionRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
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
	/**
	 * * * * * * * * * * * @NEGOCIACION_WRAPPER
	 */
	public NegociacionWrapper iniciarNegociacion(String cedula, String asesor) throws RelativeException {
		try {
			TbQoCliente cliente = this.createCliente(cedula);
			Informacion data = informacionCliente(cedula);
			if (cliente != null) {
				return generarTablasIniciales(cliente, asesor,data);
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}

	/** ******************************* @INTEGRACION **********************/
	public TbQoCliente createClienteFromEquifax(DATOSCLIENTE cliente, INGRESOSEGRESOS ingresos) throws RelativeException {
		if (cliente != null) {
				TbQoCliente c = new TbQoCliente();
				c.setCedulaCliente(StringUtils.leftPad(String.valueOf(cliente.getIDENTIFICACION()), 10, "0"));
				c.setNombreCompleto(cliente.getNOMBRESCOMPLETOS());
				c.setEmail(cliente.getCORREOELECTRONICO());
				c.setCargasFamiliares(Long.valueOf(cliente.getCARGASFAMILIARES()));
				c = this.manageCliente(c);
				if (c.getId() != null && ingresos != null && ingresos.getRUBRO() != null && !ingresos.getRUBRO().isEmpty()) {
					this.createIngresosEgresosFromEquifax(ingresos.getRUBRO(), c);
				}
				return c;
			
		} else {
			return null;
		}
	}

	private List<TbQoIngresoEgresoCliente> createIngresosEgresosFromEquifax(List<RUBRO> rubros,
			TbQoCliente cliente) throws RelativeException {
		List<TbQoIngresoEgresoCliente> list = new ArrayList<>();
		if (rubros != null) {
			rubros.forEach(e -> {
				try {
					TbQoIngresoEgresoCliente r = new TbQoIngresoEgresoCliente();
					r.setValor(BigDecimal.valueOf(e.getVALOR()));
					r.setTbQoCliente(cliente);
					if (e.getTIPORUBROECONOMICO().equalsIgnoreCase(TipoRubroEnum.EGR.toString())) {
						r.setEsEgreso(true);
						r.setEsIngreso(false);
					} else if (e.getTIPORUBROECONOMICO().equalsIgnoreCase(TipoRubroEnum.ING.toString())) {
						r.setEsEgreso(false);
						r.setEsIngreso(true);
					} else {
						r.setEsEgreso(false);
						r.setEsIngreso(false);
					}
					list.add(this.manageIngresoEgresoCliente(r));
				} catch (RelativeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log.info("ERROR AL GUARDAR RUBROS DEL CLIENTE ==== >>>");
				}
			});
			return list;
		} else {
			return null;
		}
	}

	/*private IntegracionEntidadWrapper traerEntidadPersonaEquifax(String cedula) throws RelativeException {
		try {
			IntegracionEntidadWrapper data = IntegracionApiClient.callPersonaRest(cedula);
			return data != null ? data : null;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}*/

	public Informacion informacionCliente(String cedula) throws RelativeException, UnsupportedEncodingException {
		try {
			TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
					this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
			String content = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_PERSONA).getValor()
					.replace("--tipoidentificacion--", "C")
					.replace("--identificacion--", cedula)
					.replace("--tipoconsulta--", "CC")
					.replace("--calificacionmupi--","N");
			return ApiGatewayClient.callConsultarClienteEquifaxRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_PERSONA).getValor(),
					token.getTokenType()+" "+token.getAccessToken(), content);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private List<TbQoVariablesCrediticia> createVariablesFromEquifax(List<Variable> variables,
			TbQoNegociacion negociacion) throws RelativeException {
			if (variables != null) {
				List<TbQoVariablesCrediticia> list = new ArrayList<>();
				variables.forEach(e -> {
					TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
					v.setCodigo(e.getCodigo());
					v.setNombre(e.getNombre());
					v.setOrden(String.valueOf(e.getOrden()));
					v.setValor(e.getValor());
					v.setTbQoNegociacion(negociacion);
					try {
						list.add(this.manageVariablesCrediticia(v));						
					} catch (RelativeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.info("ERROR AL INTENTRAR GUARDAR VARIABLE CREDITICIA ");
					}
					
				});
				return list;
			} else {
				return null;
			}
		
	}
	
	public NegociacionWrapper iniciarNegociacionEquifax(String cedula, String asesor) throws RelativeException {
		try {
			Informacion data = informacionCliente(cedula);
			
			TbQoCliente cliente = this.createClienteFromEquifax(data.getDATOSCLIENTE(),data.getINGRESOSEGRESOS());
			if (cliente != null) {
				return generarTablasIniciales(cliente, asesor,data);
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}

	public NegociacionWrapper iniciarNegociacionFromCot(Long id, String asesor) throws RelativeException {
		try {
			TbQoCliente cliente = this.findClienteByCotizador(id);
			if (cliente != null) {
				Informacion data = informacionCliente(cliente.getCedulaCliente());
				return generarTablasIniciales(cliente, asesor,data);
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}

	private TbQoCliente findClienteByCotizador(Long id) throws RelativeException {
		try {
			TbQoCotizador cot = this.findCotizadorById(id);
			if (cot != null) {
				return this.findClienteByIdentifiacion(cot.getTbQoCliente().getCedulaCliente());
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public NegociacionWrapper traerNegociacionExistente(Long id) throws RelativeException {
		try {
			NegociacionWrapper tmp = new NegociacionWrapper();
			tmp.setCredito(this.creditoNegociacionRepository.findCreditoByIdNegociacion(id));
			if (tmp.getCredito() != null) {
				TbQoProceso  proceso = this.findProcesoByIdReferencia( id, ProcesoEnum.NUEVO);
				if(proceso != null) {
					EstadoProcesoEnum estado = proceso.getEstadoProceso();
					if( estado == EstadoProcesoEnum.APROBADO || estado == EstadoProcesoEnum.CANCELADO) {
						return new NegociacionWrapper(false);
					}else {
						tmp.setVariables(this.variablesCrediticiaRepository.findByIdNegociacion(id));
						tmp.setRiesgos(this.riesgoAcumuladoRepository.findByIdNegociacion(id));
						tmp.setJoyas(this.tasacionRepository.findByIdCredito(tmp.getCredito().getId() ));
						tmp.setExcepciones(this.excepcionesRepository.findByIdNegociacion(id));
						tmp.setRespuesta(true);
						tmp.setProceso( proceso );
						tmp.setTelefonos( this.telefonoClienteRepository.findByIdCliente(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId()) );
						return tmp;
					}
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_READ,
							QuskiOroConstantes.ERROR_SIN_PROCESO);
				}
				
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}
	public ClienteCompletoWrapper traerClienteByIdNegociacion(Long id) throws RelativeException {
		try {
			TbQoNegociacion nego = this.findNegociacionById( id );
			if(nego != null) {
				return buscarGuardarTraerCliente( nego.getTbQoCliente().getCedulaCliente() );
			}else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}
	public ClienteCompletoWrapper buscarGuardarTraerCliente( String cedula ) throws RelativeException{
		try {
			TbQoCliente cliente = this.clienteSoftToTbQoCliente( this.findClienteSoftbank(  cedula  ) );
			ClienteCompletoWrapper wrapper;
			if(cliente != null) {
				wrapper = new ClienteCompletoWrapper( cliente );
				wrapper.setIsSoftbank( true );
			}else {
				cliente = this.findClienteByIdentifiacion( cedula );
				if(cliente != null) {
					wrapper = new ClienteCompletoWrapper( cliente );
					wrapper.setIsSoftbank( false );
				}else {
					return null;
				}
			}	
			wrapper.setIngresos( this.ingresoEgresoClienteRepository.findByIdCliente( cliente.getId() ) );
			wrapper.setDirecciones( this.direccionClienteRepository.findByIdCliente( cliente.getId() ) );
			wrapper.setPatrimonios( this.patrimonioRepository.findByIdCliente( cliente.getId() ) );
			wrapper.setReferencias( this.referenciaPersonalRepository.findByIdCliente( cliente.getId() ) );
			wrapper.setDatosTrabajo( this.datoTrabajoClienteRepository.findByIdCliente( cliente.getId() ) );
			wrapper.setTelefonos( this.telefonoClienteRepository.findByIdCliente( cliente.getId() ));
			wrapper.setCuentas( this.cuentaBancariaRepository.findByIdCliente( cliente.getId() ));
			return wrapper;
		}catch(RelativeException e ) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM , e.getMensaje());
		}
	}
	public CreacionClienteRespuestaCoreWp registrarCliente(ClienteCompletoWrapper wp) throws RelativeException {
		try {
			TbQoCliente persistedCli =  this.manageCliente( wp.getCliente() ) ;
			Map<String, String> erroresD = this.createDireccionesCliente(persistedCli, wp.getDirecciones() );
			if (erroresD.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, erroresD.toString());
			}
			Map<String, String> erroresI = this.createIngresoEgresoCliente(persistedCli, wp.getIngresos() );
			if (erroresI.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, erroresI.toString());
			}
			Map<String, String> erroresP = this.createPatrimonioCliente(persistedCli ,wp.getPatrimonios());
			if (erroresP.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, erroresP.toString());
			}
			Map<String, String> erroresR = this.createReferenciasPersonales(persistedCli,	wp.getReferencias() );
			if (erroresR.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, erroresR.toString());
			}
			this.manageDatoTrabajoCliente( wp.getDatosTrabajo() );
			Map<String, String> erroresT = this.createTelefonosCliente(persistedCli, wp.getTelefonos() );
			if (erroresT.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, erroresT.toString());
			}
			if(wp.getCuentas() != null) {
				Map<String, String> erroresC = this.createCuentasBancarias(persistedCli, wp.getCuentas() );
				if (erroresC.size() > 0) {
					throw new RelativeException(Constantes.ERROR_CODE_CREATE, erroresC.toString());
				}				
			}
			CreacionClienteRespuestaCoreWp rp = new CreacionClienteRespuestaCoreWp();
			rp.setIsCore(true);
			if( wp.getIsSoftbank() ) {
				Boolean result = this.editarClienteSoftbank( this.clienteToClienteSoftbank( wp.getCliente() ) );
				if( result ) {
					log.info(" =======> GUADANDO CLIENTE RECIEN EDITADO EN SOFTBANK ");
					this.clienteSoftToTbQoCliente( this.findClienteSoftbank( wp.getCliente().getCedulaCliente() ));
				}
				rp.setIsSoftbank(  result  ); 
			}else {
				Boolean result = this.crearClienteSoftbank( this.clienteToClienteSoftbank( wp.getCliente() ) );
				if(result) {
					log.info(" =======> GUADANDO CLIENTE RECIEN CREADO EN SOFTBANK ");
					this.clienteSoftToTbQoCliente( this.findClienteSoftbank( wp.getCliente().getCedulaCliente() ));
				}
				rp.setIsSoftbank(  result  );				
			}
			return rp;
			
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje());
		}

	}

	private NegociacionWrapper generarTablasIniciales(TbQoCliente cliente, String asesor, Informacion data) throws RelativeException {
		try {
			TbQoCreditoNegociacion credito = this.createCredito(cliente, asesor);
			if (credito != null) {
				NegociacionWrapper wrapper = new NegociacionWrapper();
				TbQoProceso proceso = this.createProcesoNegociacion( credito.getTbQoNegociacion().getId(), asesor);
				List<SoftbankOperacionWrapper> riesgos = consultarRiesgoSoftbank(cliente.getCedulaCliente());
				wrapper.setCredito(credito);
				wrapper.setRiesgos(this.createRiesgoFrontSoftBank(riesgos, credito.getTbQoNegociacion()));
				wrapper.setVariables(this.createVariablesFromEquifax(data.getXmlVariablesInternas().getVariablesInternas().getVariable(), credito.getTbQoNegociacion()));
				//traer excepcion 
				if(data.getCodigoError()== 3) {
					wrapper.setExcepcionBre(data.getMensaje());
				}
				wrapper.setRespuesta(true);
				wrapper.setProceso( proceso );
				this.guardarProspectoCrm(cliente);
				return wrapper;
			} else {
				return new NegociacionWrapper(false);
			}

		} catch (RelativeException | UnsupportedEncodingException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}

	private TbQoCreditoNegociacion createCredito(TbQoCliente cl, String asesor) throws RelativeException { 
		try {
			if (cl != null) {
				TbQoNegociacion ng = new TbQoNegociacion();
				ng.setAsesor(asesor);
				ng.setTbQoCliente(cl);
				TbQoNegociacion negociacion = this.manageNegociacion(ng);
				if (negociacion != null) {
					TbQoCreditoNegociacion cr = new TbQoCreditoNegociacion();
					cr.setTbQoNegociacion(negociacion);
					return this.manageCreditoNegociacion(cr);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CREATE,
							QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
				}
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}

	/**
	 * * * * * * * * * * * @HABILITANTES
	 */
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

	public TbQoTipoDocumento findTipoDocumentoById(Long id) throws RelativeException {
		try {
			return tipoDocumentoRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public Long countdocumento() throws RelativeException {
		try {
			return tipoDocumentoRepository.countAll(TbQoTipoDocumento.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

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
	public TbQoCreditoNegociacion findCreditoByIdNegociacion(Long idNego) throws RelativeException {
		try {
			return creditoNegociacionRepository.findCreditoByIdNegociacion(idNego);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,	QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
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

	public TbQoReferenciaPersonal findReferenciaPersonalById(Long id) throws RelativeException {
		try {
			return referenciaPersonalRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

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

	public Long countReferenciaPersonal() throws RelativeException {
		try {
			return referenciaPersonalRepository.countAll(TbQoReferenciaPersonal.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoReferenciaPersonal manageReferenciaPersonal(TbQoReferenciaPersonal send) throws RelativeException {
		try {
			TbQoReferenciaPersonal persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.referenciaPersonalRepository.findById(send.getId());
				return this.updateReferenciaPersonal(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
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

	public TbQoReferenciaPersonal updateReferenciaPersonal(TbQoReferenciaPersonal send,
			TbQoReferenciaPersonal persisted) throws RelativeException {
		try {
			if(!StringUtils.isBlank( send.getNombres()) ) {
				persisted.setNombres(send.getNombres());				
			}
			if(!StringUtils.isBlank( send.getApellidos()) ) {
				persisted.setApellidos(send.getApellidos());				
			}
			if(!StringUtils.isBlank( send.getParentesco() ) ) {
				persisted.setParentesco(send.getParentesco());
			}
			if(!StringUtils.isBlank( send.getDireccion() ) ) {
				persisted.setDireccion(send.getDireccion());	
			}
			if(!StringUtils.isBlank( send.getTelefonoMovil() ) ) {
				persisted.setTelefonoMovil(send.getTelefonoMovil());
			}
			if(!StringUtils.isBlank( send.getTelefonoFijo() ) ) {
				persisted.setTelefonoFijo(send.getTelefonoFijo());
			}
			if( send.getIdSoftbank() != null ) {
				persisted.setIdSoftbank( send.getIdSoftbank() );
			}
			if( send.getTbQoCliente() != null ) {
				persisted.setTbQoCliente( send.getTbQoCliente() );
			}
			if(send.getEstado() != null) {
				persisted.setEstado( send.getEstado() );
			}else {
				persisted.setEstado(EstadoEnum.ACT);				
			}
			persisted.setFechaActualizacion( new Timestamp(System.currentTimeMillis()) );
			return referenciaPersonalRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public TbQoArchivoCliente findArchivoClienteById(Long id) throws RelativeException {
		try {
			return archivoClienteRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

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
				send.setEstado(EstadoEnum.ACT);
				if (send.getFechaCreacion() != null && send.getFechaActualizacion() != null) {
					// send.setTotalTiempo(new Time(QuskiOroUtil.diasFecha(send.getFechaInicio(),
					// send.getFechaFin())));
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
			persisted.setEstado(EstadoEnum.ACT);

			if (send.getSeccion() != null) {
				persisted.setSeccion(send.getSeccion());
			}
			if (send.getFechaInicio() != null) {
				persisted.setFechaInicio(send.getFechaInicio());
			}

			if (send.getFechaFin() != null) {
				persisted.setFechaFin(send.getFechaFin());
			}
			if (send.getObservacion() != null) {
				persisted.setObservacion(send.getObservacion());

			}
			if (send.getFechaInicio() != null && send.getFechaFin() != null) {
				persisted.setTiempoTranscurrido(
						new BigDecimal(QuskiOroUtil.horasFecha(send.getFechaInicio(), send.getFechaFin())));
			}
			if (send.getNombreAsesor() != null) {
				persisted.setNombreAsesor(send.getNombreAsesor());
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
				persisted = this.findIngresoEgresoClienteById( Long.valueOf( send.getId() ) );
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
			if(send.getEsEgreso() != null ) {
				persisted.setEsEgreso(send.getEsEgreso());				
			}
			if( send.getEsIngreso() != null) {
				persisted.setEsIngreso(send.getEsIngreso());				
			}
			if(send.getValor() != null ) {
				persisted.setValor(send.getValor());				
			}
			if( send.getEstado() != null ) {
				persisted.setEstado(send.getEstado());
			}else {
				persisted.setEstado(EstadoEnum.ACT);				
			}
			log.info("==========> UPDATE DE INGRESO MANAGE =======>" + persisted.getId());
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
				send.setEstado(EstadoEnum.ACT);
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
			if( send.getEstado() != null) {
				persisted.setEstado( send.getEstado() );
			}else {				
				persisted.setEstado(EstadoEnum.ACT);
			}
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

	public List<TbQoDireccionCliente> findPatrimonioClienteByIdCliente(Long id) throws RelativeException {
		try {
			return direccionClienteRepository.findByIdCliente(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
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
				if(cambioInac != null) {
					this.ponerInactivoDireccionCliente(send, cambioInac);					
				}
				send.setEstado(EstadoEnum.ACT);
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
	private void ponerInactivoDireccionCliente(TbQoDireccionCliente send, List<TbQoDireccionCliente> cambioInac){
		if(cambioInac != null) {
			cambioInac.forEach(l ->{
				if(send.getId() != null) {
					if( send.getId() != l.getId() ) {
						if (send.getTipoDireccion().equals(l.getTipoDireccion() ) ) {
							l.setEstado(EstadoEnum.INA);
							try {
								this.direccionClienteRepository.update(l);
							} catch (RelativeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}	
					}		
				}else {
					if (send.getTipoDireccion().equals(l.getTipoDireccion() ) ) {
						l.setEstado(EstadoEnum.INA);
						try {
							this.direccionClienteRepository.update(l);
						} catch (RelativeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}		
				}
				
			});			
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
			persisted.setDivisionPolitica(send.getDivisionPolitica());
			if( !StringUtils.isBlank( send.getBarrio() )) {
				persisted.setBarrio(send.getBarrio());				
			}
			persisted.setSector(send.getSector());
			persisted.setCallePrincipal(send.getCallePrincipal());
			persisted.setCalleSegundaria(send.getCalleSegundaria());
			persisted.setNumeracion(send.getNumeracion());
			persisted.setReferenciaUbicacion(send.getReferenciaUbicacion());
			persisted.setTipoVivienda(send.getTipoVivienda());
			if( send.getEstado() != null) {
				persisted.setEstado( send.getEstado() );
			}else {
				persisted.setEstado(EstadoEnum.ACT);
			}
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
					} else if (element.getTbQoNegociacion() != null) {
						persisted.add(this.riesgoAcumuladoRepository.add(element));
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
	 * **************************************** @Excepciones
	 * 
	 * @throws RelativeException
	 */

	/**
	 * @author Jeroham Cadenas
	 * @param Long id
	 * @return TbQoExcepcion
	 * @throws RelativeException
	 */
	public TbQoExcepcion finExcepcionById(Long id) throws RelativeException {
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
	 * @return TbQoExcepcion
	 * @throws RelativeException
	 */
	public TbQoExcepcion findByIdNegociacionAndTipoExcepcionAndEstadoExcepcion(Long idNegociacion,
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
	 * @return List<TbQoExcepcion>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcion> findByIdNegociacion(PaginatedWrapper pw, Long idNegociacion) throws RelativeException {

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
	 * @return List<TbQoExcepcion>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcion> findByIdCliente(PaginatedWrapper pw, Long idCliente) throws RelativeException {

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
	 * @return List<TbQoExcepcion>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacion(PaginatedWrapper pw, String tipoExcepcion,
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
	 * @return List<TbQoExcepcion>
	 * @throws RelativeException
	 */
	public List<TbQoExcepcion> findByTipoExcepcionAndIdNegociacionAndCaracteristica(PaginatedWrapper pw,
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
	 * @param TbQoExcepcion send
	 * @param TbQoExcepcion persisted
	 * @return TbQoExcepcion
	 * @throws RelativeException
	 */
	private TbQoExcepcion updateExcepcion(TbQoExcepcion send, TbQoExcepcion persisted) throws RelativeException {
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
	 * @param TbQoExcepcion send
	 * @return TbQoExcepcion
	 * @throws RelativeException
	 */
	public TbQoExcepcion manageExcepcion(TbQoExcepcion send) throws RelativeException {
		TbQoExcepcion persisted = null;
		try {
			if (send != null && send.getId() != null) {
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
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}
	public List<TbQoTasacion> findTasacionByIdCredito(PaginatedWrapper pw, Long idCredito)
			throws RelativeException {
		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tasacionRepository.findByIdNegociacion(idCredito, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tasacionRepository.findByIdCredito(idCredito);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
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
	public CrearOperacionRespuestaWrapper crearOperacion(CrearOperacionEntradaWrapper datosOperacion)
			throws RelativeException {
		try {
			return SoftBankApiClient.callCrearOperacion01Rest(datosOperacion);

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
			SoftbankClienteWrapper persisted = SoftBankApiClient.callConsultaClienteRest(consulta);
			if (persisted.getIdentificacion() != null) {
				return persisted;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<SoftbankOperacionWrapper> consultarRiesgoSoftbank(String identificacion) throws RelativeException {
		try {
			SoftbankConsultaWrapper consulta = new SoftbankConsultaWrapper(identificacion);
			SoftbankRiesgoWrapper persisted = SoftBankApiClient.callConsultaRiesgoRest(consulta);
			if (!persisted.getOperaciones().isEmpty()) {
				return persisted.getOperaciones();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public List<CuotasAmortizacionWrapper> consultarTablaAmortizacion (String numeroOperacion, String urlHabilitantes, DatosRegistroWrapper registro) throws RelativeException {
		try {
			ConsultaTablaWrapper entrada = new 	ConsultaTablaWrapper(numeroOperacion, urlHabilitantes != null ? urlHabilitantes : QuskiOroConstantes.SOFT_POR_DEFECTO, registro);
			SoftbankTablaAmortizacionWrapper persisted = SoftBankApiClient.callConsultaTablaAmortizacionRest(entrada);
			if (!persisted.getCuotas().isEmpty()) {
				return persisted.getCuotas();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	private List<TbQoRiesgoAcumulado> createRiesgoFrontSoftBank(List<SoftbankOperacionWrapper> operaciones,
			TbQoNegociacion negociacion) throws RelativeException {
		try {
			if (operaciones != null) {
				List<TbQoRiesgoAcumulado> list = new ArrayList<>();
				operaciones.forEach(e -> {
					TbQoRiesgoAcumulado r = new TbQoRiesgoAcumulado(negociacion);
					r.setReferencia(e.getReferencia());
					r.setNumeroOperacion(e.getNumeroOperacion());
					r.setCodigoCarteraQuski(e.getCodigoCarteraQuski());
					r.setTipoOperacion(e.getTipoOperacion());
					r.setEsDemandada(e.getEsDemandada());
					try {
						r.setFechaEfectiva(QuskiOroUtil.formatSringToDate(e.getFechaEfectiva()));
					} catch (RelativeException e1) {
						e1.printStackTrace();
					}
					try {
						r.setFechaVencimiento(QuskiOroUtil.formatSringToDate(e.getFechaVencimiento()));
					} catch (RelativeException e1) {
						e1.printStackTrace();
					}
					r.setInteresMora(e.getInteresMora());
					r.setSaldo(e.getSaldo());
					r.setValorAlDia(e.getValorAlDia());
					r.setValorAlDiaMasCuotaActual(e.getValorAlDiaMasCuotaActual());
					r.setValorProyectadoCuotaActual(e.getValorProyectadoCuotaActual());
					r.setValorCancelaPrestamo(e.getValorCancelaPrestamo());
					r.setDiasMoraActual(e.getDiasMoraActual());
					r.setNumeroCuotasTotales(e.getNumeroCuotasTotales());
					r.setNumeroCuotasFaltantes(e.getNumeroCuotasFaltantes());
					r.setEstadoPrimeraCuotaVigente(e.getEstadoPrimeraCuotaVigente());
					r.setNombreProducto(e.getNombreProducto());
					r.setPrimeraCuotaVigente(e.getPrimeraCuotaVigente());
					r.setNumeroGarantiasReales(e.getNumeroGarantiasReales());
					r.setEstadoOperacion(e.getEstadoOperacion());
					r.setIdMoneda(e.getIdMoneda());
					list.add(r);
				});
				return this.manageListRiesgoAcumulados(list);
			} else {
				return null;
			}

		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}

	private TbQoCliente clienteSoftToTbQoCliente(SoftbankClienteWrapper s) throws RelativeException {
		
			if (s != null) {
				TbQoCliente cliente = this.findClienteByIdentifiacion( s.getIdentificacion() );
				if( cliente != null ) {					
					cliente.setActividadEconomica( s.getActividadEconomica().getIdActividadEconomica().toString() );
					cliente.setApellidoMaterno( s.getSegundoApellido() );
					cliente.setApellidoPaterno( s.getPrimerApellido() );
					cliente.setAgencia( s.getIdAgencia() );
					cliente.setUsuario( s.getCodigoUsuarioAsesor() );
					cliente.setNombreCompleto( s.getNombreCompleto() );
					cliente.setCargasFamiliares( s.getNumeroCargasFamiliares() );
					if( !StringUtils.isBlank(s.getFechaNacimiento())) {
						cliente.setFechaNacimiento( QuskiOroUtil.formatSringToDate(s.getFechaNacimiento(), QuskiOroConstantes.SOFT_DATE_FORMAT) );
						if(cliente.getFechaNacimiento() != null ) {
							cliente.setEdad( Long.valueOf(QuskiOroUtil.calculateEdad( cliente.getFechaNacimiento() )));						
						}
					}
					cliente.setEmail( s.getEmail() );
					cliente.setEstadoCivil( s.getCodigoEstadoCivil() );
					cliente.setGenero( s.getCodigoSexo() );
					cliente.setLugarNacimiento( s.getIdLugarNacimiento().toString() );
					cliente.setNacionalidad( s.getIdPaisNacimiento() );
					cliente.setNivelEducacion( s.getCodigoEducacion() );
					cliente.setPrimerNombre( s.getPrimerNombre() );
					cliente.setSegundoNombre( s.getSegundoNombre() );
					cliente.setProfesion( s.getCodigoProfesion() );
					if( !s.getDirecciones().isEmpty() ) { this.direccionesSoftToDireccionesCore(s.getDirecciones(), cliente ); }
					if( !s.getTelefonos().isEmpty() ) { this.tlfSoftToTlfCore(s.getTelefonos(), cliente); }
					if( !s.getContactosCliente().isEmpty() ) { this.contactoToReferenciaPersonal(s.getContactosCliente(), cliente);	}
					if( !s.getDatosTrabajoCliente().isEmpty() ) { this.trabajoSoftToTrabajoCore(s.getDatosTrabajoCliente(), cliente); }
					if( !s.getCuentasBancariasCliente().isEmpty() ) { this.cuentasSoftToCuentasCore(s.getCuentasBancariasCliente(), cliente); }

					return this.manageCliente( cliente);				
				}else {
					return null;
				}
			} else {
				return null;
			}
		
	}
	private void cuentasSoftToCuentasCore(List<SoftbankCuentasBancariasWrapper> list, TbQoCliente cliente) throws RelativeException {
		try {
			List<TbQoCuentaBancariaCliente> listUpdate = this.cuentaBancariaRepository.findByIdCliente(cliente.getId() );
			List<TbQoCuentaBancariaCliente> listCreate = new ArrayList<>();

			list.forEach(e ->{
				if(listUpdate != null) {
					listUpdate.forEach(l ->{
						if( e.getCuenta().equals( l.getCuenta() ) ) {
							l.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
							l.setIdSoftbank( e.getId() );
							l.setBanco( e.getIdBanco() );
							l.setEsAhorros( e.getEsAhorros() );
							l.setIdSoftbank( e.getId() );
						}
					});
				}else {
					TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente();
					cuenta.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
					cuenta.setIdSoftbank( e.getId() );
					cuenta.setBanco( e.getIdBanco() );
					cuenta.setEsAhorros( e.getEsAhorros() );
					cuenta.setIdSoftbank( e.getId() );
					cuenta.setCuenta( e.getCuenta() );
					listCreate.add( cuenta );
				}
			});
			if(listUpdate != null ) {
				this.createCuentasBancarias(cliente, listUpdate);
			}else {
				this.createCuentasBancarias(cliente, listCreate);
			}
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	private void tlfSoftToTlfCore( List<SoftbankTelefonosWrapper>  list, TbQoCliente cliente) throws RelativeException{
		try {
			List<TbQoTelefonoCliente> listUpdate = this.telefonoClienteRepository.findByIdCliente(cliente.getId() );
			List<TbQoTelefonoCliente> listCreate = new ArrayList<>();

			list.forEach(e ->{
				if(listUpdate != null) {
					listUpdate.forEach(l ->{
						if( e.getNumero().equals( l.getNumero() ) ) {
							l.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
							l.setIdSoftbank( e.getId() );
							l.setTipoTelefono( e.getCodigoTipoTelefono() );
						}
					});
				}else {
					TbQoTelefonoCliente tele = new TbQoTelefonoCliente();
					tele.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
					tele.setIdSoftbank( e.getId() );
					tele.setNumero( e.getNumero() );
					tele.setTipoTelefono( e.getCodigoTipoTelefono() );
					listCreate.add( tele );
				}
			});
			if(listUpdate != null ) {
				this.createTelefonosCliente(cliente, listUpdate);
			}else {
				this.createTelefonosCliente(cliente, listCreate);
			}
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}
	
	private void direccionesSoftToDireccionesCore( List<SoftbankDireccionWrapper> list, TbQoCliente cliente ) throws RelativeException{
		try {
			List<TbQoDireccionCliente> listUpdate = this.direccionClienteRepository.findByIdCliente(cliente.getId() );
			List<TbQoDireccionCliente> listCreate = new ArrayList<>();

			list.forEach( e ->{
				if(listUpdate != null) {
					listUpdate.forEach(l ->{
						if( e.getCodigoTipoDireccion().equals( l.getTipoDireccion()  )) {
							l.setIdSoftbank( e.getId() );
							l.setCallePrincipal( e.getCallePrincipal() );
							l.setCalleSegundaria( e.getCalleSecundaria() );
							l.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.ACT ); //@Todo: Inactivar cuando se arregle el servicio
							l.setDireccionEnvioCorrespondencia( e.getEsDireccionEnvio() );
							l.setDireccionLegal( e.getEsDireccionLegal() );
							l.setNumeracion( e.getNumero() );
							l.setReferenciaUbicacion( e.getReferencia() );
							l.setDivisionPolitica( e.getIdUbicacion() );
							l.setSector( e.getCodigoSectorVivienda() );
							l.setTipoDireccion( e.getCodigoTipoDireccion() );
							l.setTipoVivienda( e.getCodigoVivienda() );
						}
					});
				}else {
					TbQoDireccionCliente direccion = new TbQoDireccionCliente();
					direccion.setCallePrincipal( e.getCallePrincipal() );
					direccion.setCalleSegundaria( e.getCalleSecundaria() );
					direccion.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.ACT ); //@Todo: Inactivar cuando se arregle el servicio
					direccion.setDireccionEnvioCorrespondencia( e.getEsDireccionEnvio() );
					direccion.setDireccionLegal( e.getEsDireccionLegal() );
					direccion.setNumeracion( e.getNumero() );
					direccion.setReferenciaUbicacion( e.getReferencia() );
					direccion.setDivisionPolitica( e.getIdUbicacion() );
					direccion.setSector( e.getCodigoSectorVivienda() );
					direccion.setTipoDireccion( e.getCodigoTipoDireccion() );
					direccion.setTipoVivienda( e.getCodigoVivienda() );
					direccion.setIdSoftbank( e.getId() );
					listCreate.add( direccion );
				}
			});
			if(listUpdate != null ) {
				this.createDireccionesCliente(cliente, listUpdate);
			}else {
				this.createDireccionesCliente(cliente, listCreate);
			}
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}
	
	private void contactoToReferenciaPersonal(List<SoftbankContactosWrapper> list, TbQoCliente cliente)  throws RelativeException {
		try {
			List<TbQoReferenciaPersonal> listUpdate = this.referenciaPersonalRepository.findByIdCliente(cliente.getId() );
			List<TbQoReferenciaPersonal> listCreate= new ArrayList<>();

			list.forEach(e->{
				if(listUpdate != null) {
					listUpdate.forEach(l ->{
						if( e.getNombres().equals( l.getNombres()) && e.getApellidos().equals( l.getApellidos())  ) {
							l.setDireccion( e.getDireccion() );
							l.setEstado(  e.getActivo()  ? EstadoEnum.ACT : EstadoEnum.INA );
							l.setIdSoftbank( e.getId() );
							l.setParentesco( e.getCodigoTipoReferencia() );
							if(e.getTelefonos() != null ) {
								e.getTelefonos().forEach(t ->{
									if( t.getCodigoTipoTelefono().equalsIgnoreCase("F") ){
										l.setTelefonoFijo( t.getNumero() );
									}
									if( t.getCodigoTipoTelefono().equalsIgnoreCase("M") ) {
										l.setTelefonoMovil( t.getNumero() );
									}
								});									
							}
						}
					});
				}else {
					TbQoReferenciaPersonal referencia = new TbQoReferenciaPersonal();
					referencia.setDireccion( e.getDireccion() );
					referencia.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
					referencia.setNombres( e.getNombres() );

					referencia.setApellidos( e.getApellidos() );
					referencia.setParentesco( e.getCodigoTipoReferencia() );
					if(e.getTelefonos() != null ) {
						e.getTelefonos().forEach(t ->{
							if( t.getCodigoTipoTelefono().equalsIgnoreCase("F") ){
								referencia.setTelefonoFijo( t.getNumero() );
							}
							if( t.getCodigoTipoTelefono().equalsIgnoreCase("M") ) {
								referencia.setTelefonoMovil( t.getNumero() );
							}
						});									
					}
					referencia.setIdSoftbank( e.getId() );
					referencia.setTbQoCliente( cliente );
					listCreate.add( referencia );
					
				}
			});
			if(listUpdate != null ) { 
				this.createReferenciasPersonales(cliente, listUpdate); 
			}else {
				this.createReferenciasPersonales(cliente, listCreate); 
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	private void trabajoSoftToTrabajoCore(List<SoftbankDatosTrabajoWrapper> list, TbQoCliente cliente) throws RelativeException {
		try {
			List<TbQoDatoTrabajoCliente> dato = this.datoTrabajoClienteRepository.findByIdClienteList( cliente.getId() );
			TbQoDatoTrabajoCliente send = new TbQoDatoTrabajoCliente();
			list.forEach(e->{
				if(e.getActivo() && e.getEsPrincipal()) {
					if( dato != null ) {
						dato.forEach( d->{
							if( e.getEsPrincipal().equals( d.getEsprincipal() ) ){
								d.setActividadEconomica( e.getIdActividadEconomica() );
								d.setActividadEconomicaMupi( e.getCodigoActividadEconomicaClienteMupi() );
								d.setCargo( e.getCodigoCargo() );
								d.setIdSoftbank( e.getId() );
								d.setEsRelacionDependencia( e.getEsRelacionDependencia() );
								d.setOcupacion( e.getCodigoOcupacion() );
								d.setOrigenIngreso( e.getCodigoOrigenIngreso() );
							}else {
								d.setEsprincipal(false);
							}
						});
					}else {
						send.setActividadEconomica( e.getIdActividadEconomica() );
						send.setActividadEconomicaMupi( e.getCodigoActividadEconomicaClienteMupi() );
						send.setCargo( e.getCodigoCargo() );
						send.setEsRelacionDependencia( e.getEsRelacionDependencia() );
						send.setEsprincipal( e.getEsPrincipal() );
						send.setOcupacion( e.getCodigoOcupacion() );
						send.setOrigenIngreso( e.getCodigoOrigenIngreso() );
						send.setIdSoftbank( e.getId() );
						send.setTbQoCliente( cliente );
					}
				}
			});
			if(dato != null) {
				this.createDatosTrabajoCliente(cliente, dato);
			} else {
				this.manageDatoTrabajoCliente( send );
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}
	
	

	
	private SoftbankClienteWrapper clienteToClienteSoftbank(TbQoCliente cli) throws RelativeException {
		try {
			if (cli != null) {
				SoftbankClienteWrapper sof = new SoftbankClienteWrapper( cli.getCedulaCliente() );
				sof.setNombreCompleto( cli.getNombreCompleto() );                         
				sof.setPrimerApellido( cli.getApellidoPaterno() );                           
				sof.setSegundoApellido( cli.getApellidoMaterno() );              
				sof.setPrimerNombre( cli.getPrimerNombre() );             
				sof.setSegundoNombre( cli.getSegundoNombre() );                               
				sof.setEsCliente( true );              
				sof.setCodigoMotivoVisita( null );                             
				sof.setFechaIngreso( QuskiOroUtil.dateToString( cli.getFechaActualizacion(), QuskiOroConstantes.SOFT_DATE_FORMAT ) );                                  
				sof.setIdPaisNacimiento( cli.getNacionalidad() );                            
				sof.setIdPais( cli.getNacionalidad() );                                      
				sof.setIdLugarNacimiento( Long.valueOf( cli.getLugarNacimiento() ) ); 
				sof.setFechaNacimiento( QuskiOroUtil.dateToString( cli.getFechaNacimiento(), QuskiOroConstantes.SOFT_DATE_FORMAT  ));                            
				sof.setCodigoSexo( cli.getGenero() );                               
				sof.setCodigoProfesion( cli.getProfesion() );                          
				sof.setCodigoEstadoCivil( cli.getEstadoCivil() );                       
				sof.setCodigoEducacion( cli.getNivelEducacion() );                          
				sof.setNumeroCargasFamiliares( cli.getCargasFamiliares() );                  
				sof.setEmail( cli.getEmail() );                                  
				sof.setCodigoUsuario( cli.getUsuario() );                           
				sof.setCodigoUsuarioAsesor( cli.getUsuario() );
				List<TbQoIngresoEgresoCliente> listInEg = this.ingresoEgresoClienteRepository.findByIdCliente( cli.getId() );
				if(listInEg != null ) {
					BigDecimal countIngresos = new BigDecimal("0");
					BigDecimal countEgresos  = new BigDecimal("0");
					listInEg.forEach(e->{ 
						if(e.getEsEgreso()){
							countEgresos.add( e.getValor() );
							log.info("PARA EGRESOS ======> "+ e.getValor());
							log.info("PARA TOTAL ======> "+ countEgresos);

							
						}
						if(e.getEsIngreso()) {
							countIngresos.add( e.getValor() );
							log.info("PARA INGRESOS ======> "+ e.getValor());
							log.info("PARA TOTAL ======> "+ countIngresos);
						}
					} );
					sof.setIngresos( countIngresos );                                   
					sof.setEgresos( countEgresos );					
				}
				
				List<TbQoPatrimonio> listPatrimonio = this.patrimonioRepository.findByIdCliente( cli.getId() );
				if( listPatrimonio != null) {
					BigDecimal countActivos = new BigDecimal("0");
					BigDecimal countPasivos = new BigDecimal("0");
					listPatrimonio.forEach( e->{
						if( e.getActivos() != null && e.getPasivos() == null ){
							countActivos.add( e.getAvaluo() );
							log.info("PARA ACTIVOS ======> "+ e.getAvaluo());
							log.info("PARA TOTAL ======> "+ countActivos);

						}
						if( e.getActivos() == null && e.getPasivos() != null ){
							countPasivos.add( e.getAvaluo());
							log.info("PARA PASIVOS ======> "+ e.getAvaluo());
							log.info("PARA TOTAL ======> "+ countPasivos);
						}
					});
					sof.setActivos( countActivos ); 
					sof.setPasivos( countPasivos );                                   
				}
				sof.setIdAgencia( cli.getAgencia() );
				sof.setActividadEconomica( new SoftbankActividadEconomicaWrapper(  Long.valueOf(cli.getActividadEconomica()) ) );
				log.info("PARA SOFTBANK ======> LOS INGRESOS: "+ sof.getIngresos()+" || ======> LOS EGRESOS: "+ sof.getEgresos());
				log.info("PARA SOFTBANK ======> LOS PASIVOS: "+ sof.getPasivos()+" || ======> LOS ACTIVOS: "+ sof.getActivos());

				List<TbQoDireccionCliente> listDirecciones = this.direccionClienteRepository.findByIdCliente( cli.getId() );
				if(listDirecciones != null) {
					List<SoftbankDireccionWrapper> direcciones = new ArrayList<>();
					listDirecciones.forEach(e->{
						SoftbankDireccionWrapper direccionSof = new SoftbankDireccionWrapper();
						direccionSof.setId( e.getIdSoftbank() );
						direccionSof.setCodigoTipoDireccion( e.getTipoDireccion() ); 
						direccionSof.setCodigoVivienda( e.getTipoVivienda() );
						direccionSof.setCodigoSectorVivienda( e.getSector() );
						direccionSof.setIdUbicacion( e.getDivisionPolitica() );
						direccionSof.setCallePrincipal( e.getCallePrincipal() ); 
						direccionSof.setCalleSecundaria( e.getCalleSegundaria() );
						direccionSof.setNumero( e.getNumeracion() );
						direccionSof.setReferencia( e.getReferenciaUbicacion() );
						direccionSof.setEsDireccionLegal( e.getDireccionLegal() );
						direccionSof.setEsDireccionEnvio( e.getDireccionEnvioCorrespondencia() );
						direccionSof.setActivo( e.getEstado() == EstadoEnum.ACT ? true : false );
						direcciones.add( direccionSof );
					});
					sof.setDirecciones( direcciones );
				}
				List<TbQoTelefonoCliente> listTelefonos = this.telefonoClienteRepository.findByIdCliente( cli.getId() );
				if( listTelefonos != null ) {
					List<SoftbankTelefonosWrapper> telefonos = new ArrayList<>();
					listTelefonos.forEach(e->{
						SoftbankTelefonosWrapper telSof = new SoftbankTelefonosWrapper();
						telSof.setId( e.getIdSoftbank() );
						telSof.setCodigoTipoTelefono( e.getTipoTelefono() );
						telSof.setNumero( e.getNumero() );
						telefonos.add( telSof );
					});
					sof.setTelefonos( telefonos );
				}
				List<TbQoCuentaBancariaCliente> listCuentas = this.cuentaBancariaRepository.findByIdCliente( cli.getId() );
				if( listCuentas != null) {
					List<SoftbankCuentasBancariasWrapper> cuentasBancarias = new ArrayList<>();
					listCuentas.forEach(e->{
						SoftbankCuentasBancariasWrapper cu = new SoftbankCuentasBancariasWrapper();
						cu.setId( e.getIdSoftbank() );
						cu.setIdBanco( e.getBanco() );
						cu.setCuenta( e.getCuenta() );
						cu.setEsAhorros( e.getEsAhorros() );
						cuentasBancarias.add(cu);
					});
					sof.setCuentasBancariasCliente(cuentasBancarias);
				}
				List<TbQoReferenciaPersonal> listReferencia = this.referenciaPersonalRepository.findByIdCliente( cli.getId() );
				if(listReferencia != null ) {
					List<SoftbankContactosWrapper> contactosCliente = new ArrayList<>();
					listReferencia.forEach(e->{
						SoftbankContactosWrapper ref = new SoftbankContactosWrapper();
						ref.setId( e.getIdSoftbank() );
						ref.setApellidos( e.getApellidos() );
						ref.setNombres( e.getNombres() );
						ref.setCodigoTipoReferencia( e.getParentesco() );
						ref.setDireccion( e.getDireccion() );
						List<TelefonosContactoClienteWrapper> subList = new ArrayList<>();
						subList.add( new TelefonosContactoClienteWrapper( "F", e.getTelefonoFijo() ) );
						subList.add( new TelefonosContactoClienteWrapper( "M", e.getTelefonoMovil()) );
						ref.setTelefonos( subList );
						contactosCliente.add(ref);
					});
					sof.setContactosCliente( contactosCliente );
					
				}
				List<TbQoDatoTrabajoCliente> listDatos = this.datoTrabajoClienteRepository.findByIdClienteList( cli.getId() );
				if(listDatos != null) {
					List<SoftbankDatosTrabajoWrapper> datosTrabajo = new ArrayList<>();
					listDatos.forEach(e->{
						SoftbankDatosTrabajoWrapper da = new SoftbankDatosTrabajoWrapper();
						da.setCodigoCargo( e.getCargo() );
						da.setCodigoActividadEconomicaClienteMupi( "001" );
						da.setCodigoOcupacion( e.getOcupacion() );
						da.setCodigoOrigenIngreso( e.getOrigenIngreso() );
						da.setEsPrincipal(e.getEsprincipal() );
						da.setEsRelacionDependencia( e.getEsRelacionDependencia() );
						da.setId( e.getIdSoftbank() );
						da.setIdActividadEconomica( e.getActividadEconomica() );
						datosTrabajo.add( da );
					});
					sof.setDatosTrabajoCliente(datosTrabajo);
				}
				return sof;
			} else {
				return null;
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}
	}

	public Boolean crearClienteSoftbank(SoftbankClienteWrapper cliente) throws RelativeException {
		try {			
			if (cliente.getIdTipoIdentificacion() != null) {
				if (cliente.getIdentificacion() != null) {
					if (cliente.getCodigoUsuario() != null && cliente.getCodigoUsuarioAsesor() != null ) {
						SoftbankRespuestaWrapper result = SoftBankApiClient.callCrearClienteRest(cliente);
						if(!result.getExisteError()) {
							return Boolean.TRUE;
						}else {
							return Boolean.FALSE;
							// throw new RelativeException(Constantes.ERROR_CODE_CREATE + result.getMensaje() );
						}
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
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}

	public Boolean editarClienteSoftbank(SoftbankClienteWrapper cliente) throws RelativeException {
		try {
			if (cliente.getIdTipoIdentificacion() != null) {
				if (cliente.getIdentificacion() != null) {
					if (cliente.getCodigoUsuario() != null) {
						SoftbankRespuestaWrapper result = SoftBankApiClient.callEditarClienteRest(cliente);
						if(!result.getExisteError()) {
							return Boolean.TRUE;
						}else {
							return Boolean.FALSE;
							//throw new RelativeException(Constantes.ERROR_CODE_CREATE + result.getMensaje() );
						}
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
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + "TODOS LOS ABONOS" + e.getMessage());
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
			//wrapper.generateMockup();
			//StringBuilder xml = QuskiOroUtil.CalculadoraToStringXml(wrapper);
			String content ="<soap:Envelope\r\n" + 
					"	xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\r\n" + 
					"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" + 
					"	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\r\n" + 
					"	<soap:Body>\r\n" + 
					"		<CalculadoraQuski\r\n" + 
					"			xmlns=\"http://tempuri.org/\">\r\n" + 
					"			<XMlConsulta>\r\n" + 
					"			<![CDATA[	<carga>\r\n" + 
					"					<XmlParametrosRiesgo>\r\n" + 
					"						<ParametrosRiesgo>\r\n" + 
					"				              <PerfilRiesgo>4</PerfilRiesgo>\r\n" + 
					"				              <OrigenOperacion>N</OrigenOperacion>\r\n" + 
					"				              <RiesgoTotal>15323.65</RiesgoTotal>\r\n" + 
					"				              <FechaNacimiento>28/07/1990</FechaNacimiento>\r\n" + 
					"				              <PerfilPreferencia>B</PerfilPreferencia>\r\n" + 
					"				              <AgenciaOriginacion>014</AgenciaOriginacion>\r\n" + 
					"				              <IdentificacionCliente>1722668702</IdentificacionCliente>\r\n" + 
					"				              <CalificacionMupi>S</CalificacionMupi>\r\n" + 
					"				              <CoberturaExcepcionada>0</CoberturaExcepcionada>\r\n" + 
					"				            </ParametrosRiesgo>\r\n" + 
					"					</XmlParametrosRiesgo>\r\n" + 
					"					<XmlGarantias>\r\n" + 
					"						<Garantias>\r\n" + 
					"							<Garantia>\r\n" + 
					"								<TipoJoya>ANI</TipoJoya>\r\n" + 
					"								<Descripcion>BUEN ESTADO</Descripcion>\r\n" + 
					"								<EstadoJoya>BUE</EstadoJoya>\r\n" + 
					"								<TipoOroKilataje>18K</TipoOroKilataje>\r\n" + 
					"								<PesoGr>7.73</PesoGr>\r\n" + 
					"								<TienePiedras>S</TienePiedras>\r\n" + 
					"								<DetallePiedras>PIEDRAS</DetallePiedras>\r\n" + 
					"								<DescuentoPesoPiedras>0.73</DescuentoPesoPiedras>\r\n" + 
					"								<PesoNeto>7.00</PesoNeto>\r\n" + 
					"								<PrecioOro>263.72</PrecioOro>\r\n" + 
					"								<ValorAplicableCredito>293.02</ValorAplicableCredito>\r\n" + 
					"								<ValorRealizacion>232.07</ValorRealizacion>\r\n" + 
					"								<NumeroPiezas>1</NumeroPiezas>\r\n" + 
					"								<DescuentoSuelda>0.00</DescuentoSuelda>\r\n" + 
					"							</Garantia>\r\n" + 
					"						</Garantias>\r\n" + 
					"					</XmlGarantias>\r\n" + 
					"					<XmlDescuentosNuevaOperacion>\r\n" + 
					"						<DescuentosOperacion>\r\n" + 
					"							<SaldoMontoCreditoAnterior></SaldoMontoCreditoAnterior>\r\n" + 
					"							<SaldoInteresCreditoAnterior></SaldoInteresCreditoAnterior>\r\n" + 
					"							<MoraCreditoAnterior></MoraCreditoAnterior>\r\n" + 
					"							<CobranzaCreditoAnterior></CobranzaCreditoAnterior>\r\n" + 
					"							<TipoCartera></TipoCartera>\r\n" + 
					"							<MontoFinanciadoCreditoAnterior></MontoFinanciadoCreditoAnterior>\r\n" + 
					"							<PlazoCreditoAnterior></PlazoCreditoAnterior>\r\n" + 
					"							<TipoCreditoAnterior></TipoCreditoAnterior>\r\n" + 
					"							<EstadoCreditoAnterior></EstadoCreditoAnterior>\r\n" + 
					"							<FechaEfectivaCreditoAnterior></FechaEfectivaCreditoAnterior>\r\n" + 
					"							<FechaVencimientoCreditoAnterior></FechaVencimientoCreditoAnterior>\r\n" + 
					"							<MontoSolicitadoCliente>0</MontoSolicitadoCliente>\r\n" + 
					"							<NumeroOperacionMadre></NumeroOperacionMadre>\r\n" + 
					"							<NumeroOperacionRenovar></NumeroOperacionRenovar>\r\n" + 
					"							<REFERENCIA_ADICIONAL></REFERENCIA_ADICIONAL>\r\n" + 
					"							<OperacionPropia></OperacionPropia>\r\n" + 
					"						</DescuentosOperacion>\r\n" + 
					"					</XmlDescuentosNuevaOperacion>\r\n" + 
					"				</carga> ]]>\r\n" + 
					"			</XMlConsulta>\r\n" + 
					"		</CalculadoraQuski>\r\n" + 
					"	</soap:Body>\r\n" + 
					"</soap:Envelope>";
			TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
					this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
			return ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
					token.getTokenType() +" "+ token.getAccessToken(), content);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getLocalizedMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.CLIENTE_PAGO_NO_ENCONTRADO + e.getMessage());
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
				return this.updateRegistrarPago(send, persisted);
			} else {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return registrarPagoRepository.add(send);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + " ClientePago" + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.CLIENTE_PAGO_NO_ENCONTRADO + e.getMessage());
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
				return this.updateClientePago(send, persisted);
			} else {
				send.setEstado(EstadoEnum.ACT);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return crearCodigoPago( this.clientePagoRepository.add(send) );
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + "CLIENTE PAGO" + e.getMessage());
		}
	}
	private TbQoClientePago crearCodigoPago(TbQoClientePago persisted) throws RelativeException {
		String cod = QuskiOroConstantes.CODIGO_PAGO+"0000000";
		Long id = persisted.getId();
		try {
			if (id < 9) {
				cod = QuskiOroConstantes.CODIGO_PAGO+"000000";
			} else if (id < 99) {
				cod = QuskiOroConstantes.CODIGO_PAGO+"00000" + id;
			} else if (id < 999) {
				cod = QuskiOroConstantes.CODIGO_PAGO+"0000" + id;
			} else if (id < 9999) {
				cod = QuskiOroConstantes.CODIGO_PAGO+"000" + id;
			} else if (id < 99999) {
				cod = QuskiOroConstantes.CODIGO_PAGO+"00" + id;
			} else if (id < 999999) {
				cod = QuskiOroConstantes.CODIGO_PAGO+"0" + id;
			} else if (id < 9999999) {
				cod = QuskiOroConstantes.CODIGO_PAGO+ "" + id;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"Error. Codigo de PAGO supera los 7 digitos numericos");
			}
			persisted.setCodigo(cod);
			return this.clientePagoRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
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
			if(send.getAsesor() != null) {
				persisted.setAsesor( send.getAsesor());
			}
			if(send.getAprobador() != null) {
				persisted.setAprobador( send.getAprobador());
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado( EstadoEnum.ACT );
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
				send.setEstado(EstadoEnum.ACT);
				return crearCodigoCreditoNuevo(this.creditoNegociacionRepository.add(send));
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}

	private TbQoCreditoNegociacion crearCodigoCreditoNuevo(TbQoCreditoNegociacion persisted) throws RelativeException {
		String cod = QuskiOroConstantes.CODIGO_NUEVO+"0000000";
		Long id = persisted.getId();
		try {
			if (id < 9) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+"000000";
			} else if (id < 99) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+"00000" + id;
			} else if (id < 999) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+"0000" + id;
			} else if (id < 9999) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+"000" + id;
			} else if (id < 99999) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+"00" + id;
			} else if (id < 999999) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+"0" + id;
			} else if (id < 9999999) {
				cod = QuskiOroConstantes.CODIGO_NUEVO+ "" + id;
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
						"Error. Codigo de Credito supera los 7 digitos numericos");
			}
			persisted.setCodigo(cod);
			return this.creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	private TbQoCreditoNegociacion updateCreditoNegociacion(TbQoCreditoNegociacion send,
			TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			if(send.getaPagarCliente() != null) {
				persisted.setaPagarCliente( send.getaPagarCliente());
			}
			if(send.getTipoCarteraQuski() != null) {
				persisted.setTipoCarteraQuski( send.getTipoCarteraQuski());
			}
			if(send.getEstadoSoftbank() != null) {
				persisted.setEstadoSoftbank( send.getEstadoSoftbank());
			}
			if(send.getPlazoCredito() != null) {
				persisted.setPlazoCredito( send.getPlazoCredito());
			}
			if(send.getDestinoOperacion() != null) {
				persisted.setDestinoOperacion( send.getDestinoOperacion());
			}
			if(send.getFechaEfectiva() != null) {
				persisted.setFechaEfectiva( send.getFechaEfectiva());
			}
			if(send.getFechaVencimiento() != null) {
				persisted.setFechaVencimiento( send.getFechaVencimiento());
			}
			if(send.getMontoPreaprobado() != null) {
				persisted.setMontoPreaprobado( send.getMontoPreaprobado());
			}
			if(send.getMontoDesembolso() != null) {
				persisted.setMontoDesembolso( send.getMontoDesembolso());
			}
			if(send.getTotalCostoNuevaOperacion() != null) {
				persisted.setTotalCostoNuevaOperacion( send.getTotalCostoNuevaOperacion());
			}
			if(send.getValorCuota() != null) {
				persisted.setValorCuota( send.getValorCuota());
			}
			if(send.getRiesgoTotalCliente() != null) {
				persisted.setRiesgoTotalCliente( send.getRiesgoTotalCliente());
			}
			if(send.getaRecibirCliente() != null) {
				persisted.setaRecibirCliente( send.getaRecibirCliente());
			}
			if(send.getNetoAlCliente() != null) {
				persisted.setNetoAlCliente( send.getNetoAlCliente());
			}
			if(send.getNumeroFunda() != null) {
				persisted.setNumeroFunda( send.getNumeroFunda());
			}
			if(send.getPesoFunda() != null) {
				persisted.setPesoFunda( send.getPesoFunda());
			}
			if(send.getDescripcionProducto() != null) {
				persisted.setDescripcionProducto( send.getDescripcionProducto());
			}
						
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado( EstadoEnum.ACT );
			return this.creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	/**
	 * Metodo que trae los CreditosNegociacion existentes, tambien trae las
	 * variables,Riesgos Direcciones Referencias Ingresos Egresos Patrimonios
	 * 
	 * @author Kléber Guerra Relative Engine
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public AprobacionWrapper traerCreditoNegociacionExistente(Long id) throws RelativeException {
		try {
			AprobacionWrapper tmp = new AprobacionWrapper();
			tmp.setCredito(this.creditoNegociacionRepository.findCreditoByIdNegociacion(id));

			if (tmp.getCredito() != null) {

				tmp.setVariables(this.variablesCrediticiaRepository
						.findByIdNegociacion(tmp.getCredito().getTbQoNegociacion().getId()));
				tmp.setRiesgos(this.riesgoAcumuladoRepository
						.findByIdNegociacion(tmp.getCredito().getTbQoNegociacion().getId()));
				tmp.setDirecciones(direccionClienteRepository
						.findByIdCliente(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId()));
				tmp.setReferencias(this.referenciaPersonalRepository
						.findByIdCliente(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId()));
				tmp.setIngresosEgresos(this.ingresoEgresoClienteRepository
						.findByIdCliente(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId()));
				tmp.setPatrimonios(this.patrimonioRepository
						.findByIdCliente(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId()));
				tmp.setJoyas(
						this.tasacionRepository.findByIdCredito(tmp.getCredito().getId()));

				tmp.setProceso(this.procesoRepository.findByIdNegociacion(tmp.getCredito().getTbQoNegociacion().getId()));


				return tmp;
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}
	public OperacionCreditoNuevoWrapper traerCreditoNuevo( Long idNegociacion ) throws RelativeException{
		try {
			OperacionCreditoNuevoWrapper op = new OperacionCreditoNuevoWrapper( this.findCreditoByIdNegociacion( idNegociacion ));
			if(op.getCredito() != null) {
				op.setProceso( this.procesoRepository.findByIdReferencia(op.getCredito().getTbQoNegociacion().getId(), ProcesoEnum.NUEVO ));
				if(op.getProceso() != null) {
					op.setJoyas( this.tasacionRepository.findByIdCredito( op.getCredito().getId() ));
					if(op.getJoyas() != null ) {
						op.setExcepciones( this.excepcionesRepository.findByIdNegociacion( op.getCredito().getTbQoNegociacion().getId() ));
					}else {
						op.setExiste(false);
						op.setMensaje("No Posee joyas, Error en base, revisar.");
						return op;
					}
				}else {
					op.setExiste(false);
					op.setMensaje("No Posee un registro de proceso. Error interno.");
					return op;
				}
			}
			op.setCuentas( this.cuentaBancariaRepository.findByIdCliente( op.getCredito().getTbQoNegociacion().getTbQoCliente().getId() ) );
			return op;
		}catch(RelativeException e ){
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getLocalizedMessage());
		}
	}
	
	public CreditoCreadoSoftbank crearOperacionNuevo(  TbQoCreditoNegociacion wp ) throws RelativeException{
		try {
			CrearOperacionEntradaWrapper op = this.convertirCreditoCoreToCreditoSoftbank( wp ); 
			if(op != null ) {
				CrearOperacionRespuestaWrapper operacion = this.crearOperacion( op );
				
				CreditoCreadoSoftbank result = new CreditoCreadoSoftbank( this.guardarOperacion( operacion, wp ) );
				result.setCuotasAmortizacion( this.consultarTablaAmortizacion( operacion.getNumeroOperacion(), operacion.getUriHabilitantes(),  op.getDatosRegistro())  );
				return result; 
			}
				
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
		return null;
	}

	private TbQoCreditoNegociacion guardarOperacion(CrearOperacionRespuestaWrapper operacion, TbQoCreditoNegociacion credito) throws RelativeException {
		try {
			credito.setaPagarCliente( operacion.getaPagarPorCliente() );
			credito.setaRecibirCliente( operacion.getMontoEntregar() );
			credito.setTablaAmortizacion( operacion.getCodigoTablaAmortizacionQuski() );
			credito.setEstadoSoftbank( operacion.getEstado() );
			credito.setDescripcionProducto( operacion.getProducto() );
			credito.setPlazoCredito( operacion.getPlazo() );
			credito.setTipoCarteraQuski( operacion.getCodigoTipoCarteraQuski() );
			credito.setTotalCostoNuevaOperacion( operacion.getCostosOperacion() );
			credito.setValorCuota( operacion.getValorCuota() );
			
			return this.manageCreditoNegociacion( credito );
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	private CrearOperacionEntradaWrapper convertirCreditoCoreToCreditoSoftbank( TbQoCreditoNegociacion credito )  throws RelativeException {
		try {
			if(credito.getId() != null && credito.getTbQoNegociacion() != null ) {
				List<TbQoTasacion> joyas = this.tasacionRepository.findByIdCredito( credito.getId() ); 
				List<TbQoRubro> rubros = this.rubroRepository.findByIdCredito(credito.getId() );
				TbQoCliente cliente = credito.getTbQoNegociacion().getTbQoCliente();
				if(credito.getTbQoNegociacion().getTbQoCliente() != null) {
					CrearOperacionEntradaWrapper result = new CrearOperacionEntradaWrapper(cliente.getCedulaCliente(), cliente.getNombreCompleto() ); 
					if( joyas != null ) {
						if( rubros != null ) {
							log.info(" ====> CARGAR RUBROS ");
							result.cargarImpCom( rubros ) ;
						}
						log.info(" ====> ESTOY LLEGANDO HASTA AQUI PARTE 1 ");
						result.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  );
						result.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion()  );
						result.setCodigoTipoCarteraQuski( credito.getTipoCarteraQuski() );
						result.setCodigoTipoPrestamo( QuskiOroConstantes.SOFT_TIPO_PRESTAMO );
						result.setMontoSolicitado( credito.getMontoSolicitado() );
						result.setMontoFinanciado( credito.getMontoFinanciado() );
						result.setPagoDia( Long.valueOf( credito.getPagoDia().getDate() ));
						result.setCodigoGradoInteres( QuskiOroConstantes.SOFT_GRADO_INTERES );
						result.setDatosRegistro( new DatosRegistroWrapper(credito.getTbQoNegociacion().getAsesor(), credito.getIdAgencia(),  QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT) )  ); 
						List<DatosCuentaClienteWrapper> listCuenta = new ArrayList<>();
						List<TbQoCuentaBancariaCliente> cuentaCliente = this.cuentaBancariaRepository.findByIdCliente( cliente.getId() );
						if(cuentaCliente != null ) {
							cuentaCliente.forEach(c->{
								if( credito.getNumeroCuenta().equals( c.getCuenta() )) {
									listCuenta.add( new DatosCuentaClienteWrapper( c.getBanco(),c.getCuenta(),c.getEsAhorros() ) );									
								}
							});
						}
						result.setDatosCuentaCliente( listCuenta );
						log.info(" ====> ESTOY LLEGANDO HASTA AQUI PARTE 2 ");
						DatosGarantiasWrapper datos = new DatosGarantiasWrapper();
					    datos.setTotaPesoBruto( credito.getTotalPesoBrutoGarantia() );
						datos.setTotalPesoBrutoConFunda( credito.getTotalPesoBrutoConFunda() ); 
						datos.setTotalPesoNeto( credito.getTotalPesoNeto() ); 
						datos.setTotaPesoNetoConFunda( credito.getTotaPesoNetoConFunda() ); 
						datos.setCodigoTipoFunda( credito.getCodigoTipoFunda() ); 
						datos.setTotalValorAvaluo( credito.getTotalValorAvaluo() ); 
						datos.setTotalValorComercial( credito.getTotalValorComercial() );
						datos.setTotalValorRealizacion( credito.getTotalValorRealizacion() );
						datos.setUriImagenSinFunda( credito.getUriImagenSinFunda() );
						datos.setUriImagenConFunda( credito.getUriImagenConFunda() );
						List<JoyaWrapper> listjoyas = new ArrayList<>();
						joyas.forEach(e->{
							log.info(" ====> ESTOY LLEGANDO HASTA AQUI PARTE 3 ");
							JoyaWrapper joyaSoft = new JoyaWrapper();
						    joyaSoft.setNumeroGarantia( e.getNumeroGarantia() );
						    joyaSoft.setNumeroExpediente( e.getNumeroExpediente() );
						    joyaSoft.setCodigoTipoGarantia( e.getTipoGarantia() );
						    joyaSoft.setDescripcion( e.getDescripcion());
						    joyaSoft.setCodigoSubTipoGarantia( e.getSubTipoGarantia() );
						    joyaSoft.setTipoCobertura( QuskiOroConstantes.SOFT_COBERTURA );
						    joyaSoft.setValorComercial( e.getValorComercial());
						    joyaSoft.setValorAvaluo( e.getValorAvaluo());
						    joyaSoft.setValorRealizacion( e.getValorRealizacion());
						    joyaSoft.setValorOro( e.getValorOro());
						    try {
								joyaSoft.setFechaAvaluo( QuskiOroUtil.dateToString( e.getFechaCreacion() , QuskiOroConstantes.SOFT_DATE_FORMAT ) );
							} catch (RelativeException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						    joyaSoft.setIdAgenciaRegistro( credito.getIdAgencia() );
						    joyaSoft.setIdAgenciaCustodia( credito.getIdAgencia() );
						    joyaSoft.setReferencia( QuskiOroConstantes.SOFT_POR_DEFECTO );
						    joyaSoft.setCodigoTipoJoya( e.getTipoJoya() );
						    joyaSoft.setDescripcionJoya( QuskiOroConstantes.SOFT_POR_DEFECTO );
						    joyaSoft.setCodigoEstadoJoya( e.getEstadoJoya());
						    joyaSoft.setCodigoTipoOro( e.getTipoOro());
						    joyaSoft.setPesoBruto( e.getPesoBruto());
						    joyaSoft.setTienePiedras( e.getTienePiedras() );
						    joyaSoft.setDetallePiedras( e.getDetallePiedras() );
						    joyaSoft.setDescuentoPiedras( e.getDescuentoPesoPiedra());
						    joyaSoft.setPesoNeto( e.getPesoNeto());
						    joyaSoft.setNumeroPiezas( Long.valueOf(e.getNumeroPiezas()));
						    joyaSoft.setDescuentoSuelda( e.getDescuentoSuelda());
						    listjoyas.add( joyaSoft );
						});
						datos.setGarantias( listjoyas );
						result.setDatosGarantias( datos );
						return result;
					}else {return null;}					
				}else {return null;}
			}else {return null;}
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	/** ********************************************* @CRM ************ */
	public CrmProspectoCortoWrapper findProspectoCrm(String cedula) throws RelativeException {
		try {
			CrmProspectoCortoWrapper prospecto = CrmApiClient.callConsultaProspectoRest(cedula);
			if (prospecto != null) {
				return prospecto;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	private TbQoCliente prospectoCrmToTbQoCliente(CrmProspectoCortoWrapper p) {
		if (p != null) {
			TbQoCliente c = new TbQoCliente();
			c.setCedulaCliente(p.getCedula());
			c.setNombreCompleto(p.getNombreCompleto());
			c.setEmail(p.getEmail());
//			c.setTelefonoAdicional(p.getPhoneOther());
//			c.setTelefonoFijo(p.getPhoneHome());
//			c.setTelefonoMovil(p.getPhoneMobile());
//			c.setTelefonoTrabajo(p.getPhoneWork());
			return c;
		} else {
			return null;
		}
	}

	private Boolean guardarProspectoCrm(TbQoCliente cliente) throws RelativeException, UnsupportedEncodingException {
		try {
			CrmEntidadWrapper entidad = new CrmEntidadWrapper();
			entidad.setCedulaC(cliente.getCedulaCliente());
			entidad.setEmailAddress(cliente.getEmail());
			entidad.setEmailAddressCaps(StringUtils.isNotBlank(cliente.getEmail())?cliente.getEmail().toUpperCase():null);
			entidad.setFirstName(cliente.getNombreCompleto());
			entidad.setLeadSourceDescription("GESTION QUSKI");
//			entidad.setPhoneMobile(cliente.getTelefonoMovil());
//			entidad.setPhoneHome(cliente.getTelefonoFijo());
			CrmGuardarProspectoWrapper tmp = new CrmGuardarProspectoWrapper(entidad);
			CrmProspectoWrapper pro = CrmApiClient.callPersistProspectoRest(tmp);
			return pro != null ? Boolean.TRUE : Boolean.FALSE;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException(Constantes.ERROR_CODE_CREATE);

		}
	}

	

	/*private String traerExcepcionEquifax(String cedula) throws RelativeException {
		try {
			IntegracionEntidadWrapper data = IntegracionApiClient.callPersonaRest(cedula);
			if (data != null) {
				if (data.getCodigoError() == 3) {
					return data.getMensaje();
				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}*/

	public void mailNotificacion(String[] para, String asunto, String contenido, Map<String, byte[]> adjunto)
			throws RelativeException {
		try {

			String emailSecurityType = this.parametroRepository.findByNombre(QuskiOroConstantes.emailSecurityType)
					.getValor();
			String smtpHostServer = this.parametroRepository.findByNombre(QuskiOroConstantes.smtpHostServer).getValor();
			String portEmail = this.parametroRepository.findByNombre(QuskiOroConstantes.portEmail).getValor();
			String sfPortEmail = this.parametroRepository.findByNombre(QuskiOroConstantes.sfPortEmail).getValor();
			String userEmail = this.parametroRepository.findByNombre(QuskiOroConstantes.userEmail).getValor();
			String fromEmailDesa = this.parametroRepository.findByNombre(QuskiOroConstantes.fromEmailDesa).getValor();
			String authEmail = this.parametroRepository.findByNombre(QuskiOroConstantes.authEmail).getValor();
			String passwordEmail = this.parametroRepository.findByNombre(QuskiOroConstantes.passwordEmail).getValor();
			
			if (adjunto != null) {
				EmailDefinition ed = new EmailDefinition.Builder()
						.emailSecurityType(
								QuskiOroUtil.getEnumFromString(EmailSecurityTypeEnum.class, emailSecurityType))
						.smtpHostServer(smtpHostServer).port(portEmail).sfPort(sfPortEmail)
						.auth(StringUtils.isNotBlank(authEmail) && authEmail == "TRUE").password(passwordEmail)
						.user(userEmail).subject(asunto).tos(Arrays.asList(para)).fromEmail(fromEmailDesa)
						.message(contenido).hasFiles(Boolean.TRUE).attachments(adjunto).build();
				ed.setSession(EmailUtil.provideSession(ed, EmailSecurityTypeEnum.SSL));
				Transport.send(null, null, passwordEmail, passwordEmail);
				EmailUtil.sendEmail(ed);
			} else {
				EmailDefinition ed = new EmailDefinition.Builder()
						.emailSecurityType(
								QuskiOroUtil.getEnumFromString(EmailSecurityTypeEnum.class, emailSecurityType))
						.smtpHostServer(smtpHostServer).port(portEmail).sfPort(sfPortEmail)
						.auth(StringUtils.isNotBlank(authEmail) && authEmail.equalsIgnoreCase("TRUE"))
						.password(passwordEmail).user(userEmail).subject(asunto).tos(Arrays.asList(para))
						.fromEmail(fromEmailDesa).message(contenido).hasFiles(Boolean.FALSE).build();
				ed.setSession(EmailUtil.provideSession(ed, EmailSecurityTypeEnum.SSL));
				EmailUtil.sendEmail(ed);
			}
		} catch (RelativeException e) {

			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
		} catch (Exception e) {
			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Action no encontrada " + e.getMessage());
		}

	}

	public Boolean enviarCorreoPruebas(String para, String asunto, String contenido,
			Map<java.lang.String, byte[]> adjunto) throws RelativeException {
		try {
			String[] array = new String[1];
			array[0] = para;

			this.mailNotificacion(array, asunto, contenido, adjunto);
			return Boolean.TRUE;
		} catch (RelativeException e) {
			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());

		}
	}

	/**
	 * ************************** @PROCESO
	 */
	public TbQoProceso findProcesoById(Long id) throws RelativeException {
		try {
			return procesoRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public TbQoProceso findProcesoByIdReferencia(Long id, ProcesoEnum proceso) throws RelativeException {
		try {
			return procesoRepository.findByIdReferencia(id, proceso);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public TbQoProceso manageProceso(TbQoProceso send) throws RelativeException {
		try {
			if (send != null && send.getId() != null) {
				TbQoProceso persisted = this.findProcesoById(send.getId());
				return this.updateProceso(send, persisted);
			} else if (send.getId() == null) {
				send.setEstado(EstadoEnum.ACT);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return this.procesoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}
	public TbQoProceso updateProceso(TbQoProceso send, TbQoProceso persisted) throws RelativeException {
		try {
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado(EstadoEnum.ACT);
			if (send.getEstadoProceso() != null) {
				persisted.setEstadoProceso(send.getEstadoProceso());
			}
			if (send.getUsuario() != null) {
				persisted.setUsuario(send.getUsuario());
			}
			return procesoRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
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
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public Long countProceso() throws RelativeException {
		try {
			return procesoRepository.countAll(TbQoProceso.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public ResultOperacionesWrapper findOperaciones(BusquedaOperacionesWrapper wp) throws RelativeException {
		try {
			ResultOperacionesWrapper result = new ResultOperacionesWrapper();
			result.setOperaciones(this.procesoRepository.findOperacion( wp ) );
			result.setResult( this.procesoRepository.countOperacion( wp ));
			return result;
			 
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());		
		}
	}
	public ResultOperacionesAprobarWrapper findOperacionesPorAprobar(BusquedaPorAprobarWrapper wp) throws RelativeException {
		try {
			ResultOperacionesAprobarWrapper result = new ResultOperacionesAprobarWrapper();
			result.setOperaciones( this.procesoRepository.findOperacionPorAprobar( wp )  );
			result.setResult( this.procesoRepository.countOperacionAprobar( wp ));
			return result;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());		
		}
	}
	public TbQoProceso cambiarEstado( Long idReferencia, ProcesoEnum proceso, EstadoProcesoEnum newEstado) throws RelativeException {
		try {
			TbQoProceso persisted = this.findProcesoByIdReferencia( idReferencia, proceso );
			persisted.setEstadoProceso( newEstado );
			return this.manageProceso(persisted);
		}catch(RelativeException e) {
			e.getStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());		
		}
	}
	public Boolean reasignarOperacion(Long id, ProcesoEnum proceso, String usuario) throws RelativeException {
		try {
			if(proceso == ProcesoEnum.NUEVO || proceso == ProcesoEnum.RENOVACION) {
				TbQoNegociacion persisted = this.findNegociacionById( id );
				persisted.setAsesor( usuario );
				TbQoNegociacion cambio = this.manageNegociacion(persisted);
				if(cambio != null) {
					return true;					
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
							QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
				}
			}
			if(proceso == ProcesoEnum.DEVOLUCION) {}
			if(proceso == ProcesoEnum.VERIFICACION_TELEFONICA) {}
			
			if(proceso == ProcesoEnum.PAGO) {
				TbQoClientePago persisted = this.findClientePagoById( id );
				persisted.setAsesor( usuario );
				TbQoClientePago cambio = this.manageClientePago( persisted );
				if(cambio != null) {
					return true;					
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
							QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
				}
			}
			return false;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	public String asignarAprobador(Long id, ProcesoEnum proceso, String aprobador) throws RelativeException {
		try {
			TbQoProceso persistedProceso = this.findProcesoByIdReferencia(id, proceso);
			if(persistedProceso != null) {
				persistedProceso.setUsuario( aprobador );
				TbQoProceso cambioProceso = this.manageProceso(persistedProceso);
				
				if(proceso == ProcesoEnum.NUEVO || proceso == ProcesoEnum.RENOVACION) {
					TbQoNegociacion persistedOperacion = this.findNegociacionById( id );
					if(persistedOperacion != null) {
						persistedOperacion.setAprobador(aprobador);
						this.manageNegociacion(persistedOperacion);
					}else {
						throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
								QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
					}
				}				
				if(proceso == ProcesoEnum.PAGO) {
					TbQoClientePago persisted = this.findClientePagoById( id );
					persisted.setAprobador(aprobador);
					this.manageClientePago( persisted );
				}
				if(proceso == ProcesoEnum.DEVOLUCION) {}
				if(proceso == ProcesoEnum.VERIFICACION_TELEFONICA) {}
				return cambioProceso.getUsuario();
			}else {
				return "SIN PROCESO";
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	public TbQoProceso cancelarNegociacion(Long id, String usuario) throws RelativeException {
		if(id != null && usuario != null){
			TbQoProceso persisted = this.findProcesoByIdReferencia( id, ProcesoEnum.NUEVO );
			if(persisted != null) {
				persisted.setEstadoProceso( EstadoProcesoEnum.CANCELADO );
				persisted.setFechaActualizacion(new Date(System.currentTimeMillis()));
				persisted.setUsuario(usuario);
				return this.manageProceso( persisted );					
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_ID_NO_EXISTE );
			}
		}else {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_VALOR_NO_VALIDO );
		}
	}
	private TbQoProceso createProcesoNegociacion( Long idReferencia, String usuario) throws RelativeException {
		try {
			TbQoProceso send = new TbQoProceso( Long.valueOf( idReferencia ));
			send.setProceso( ProcesoEnum.NUEVO );
			send.setEstadoProceso( EstadoProcesoEnum.CREADO );
			send.setUsuario( usuario );
			return this.manageProceso( send );
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		}
	}
	public TbQoProceso createProcesoPago( Long idReferencia, String usuario) throws RelativeException {
		try {
			TbQoProceso send = new TbQoProceso( Long.valueOf( idReferencia ));
			send.setProceso( ProcesoEnum.PAGO );
			send.setEstadoProceso( EstadoProcesoEnum.PENDIENTE_APROBACION );
			send.setUsuario( usuario );
			return this.manageProceso( send );
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
		}
	}
	
	/**
	 * ************************** @TRACKING
	 */
	public List<TbQoTracking> findBusquedaParametros(TrackingWrapper wp, PaginatedWrapper pw) throws RelativeException {
		if(wp == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LOS PARAMETROS DE BUSQUEDA");
		}
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			
				return this.trackingRepository.findByParams(wp,pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			
		}else {
			return this.trackingRepository.findByParams(wp);
		}
	}

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

	public List<ProcesoEnum> findListProcesos() throws RelativeException{
		// TODO Auto-generated method stub
		return trackingRepository.findListProcesos();
	}

	public List<ActividadEnum> findListActividadByProceso(String proceso) throws RelativeException{
		try {
			if(StringUtils.isBlank(proceso)) {
				return null;
			}
			// TODO Auto-generated method stub
			return trackingRepository.findListActividadByProceso(QuskiOroUtil.getEnumFromString(ProcesoEnum.class, proceso));
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR CONVERTIR STRING A ENUM");
		}
	}

	public List<SeccionEnum> findListSeccionByActividad(String actividad) throws RelativeException{
		try {
			if(StringUtils.isBlank(actividad)) {
				return null;
			}
			return trackingRepository.findListSeccionByActividad(QuskiOroUtil.getEnumFromString(ActividadEnum.class, actividad));
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR CONVERTIR STRING A ENUM");
		}
	}

	public Long countTracking(TrackingWrapper wp)throws RelativeException {
		try {
			return trackingRepository.countByParamPaged(wp);
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	/**
	 * * * * * * * * * * * @RUBRO
	 */
	public TbQoRubro findRubroById(Long id) throws RelativeException {
		try {
			return rubroRepository.findById(id);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public List<TbQoRubro> findAllRubros(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.rubroRepository.findAll(TbQoRubro.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.rubroRepository.findAll(TbQoRubro.class, pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {

					return this.rubroRepository.findAll(TbQoRubro.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public TbQoRubro manageRubro(TbQoRubro send) throws RelativeException {
		try {
			if (send != null && send.getId() != null) {
				TbQoRubro persisted = this.rubroRepository.findById(send.getId());
				return this.updateRubro(send, persisted);

			} else {
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				return this.rubroRepository.add(send);
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	public TbQoRubro updateRubro(TbQoRubro send, TbQoRubro persisted) throws RelativeException {
		try {
			persisted.setEstado(EstadoEnum.ACT);
			return this.rubroRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	
}
