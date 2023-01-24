package com.relative.quski.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relative.core.exception.RelativeException;
import com.relative.core.util.enums.EmailSecurityTypeEnum;
import com.relative.core.util.mail.EmailDefinition;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.bpms.api.ApiGatewayClient;
import com.relative.quski.bpms.api.CrmApiClient;
import com.relative.quski.bpms.api.LocalStorageClient;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.enums.SeccionEnum;
import com.relative.quski.enums.TipoExcepcionEnum;
import com.relative.quski.model.TbMiParametro;
import com.relative.quski.model.TbQoArchivoCliente;
import com.relative.quski.model.TbQoCliente;
import com.relative.quski.model.TbQoClientePago;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.model.TbQoCreditoNegociacion;
import com.relative.quski.model.TbQoCuentaBancariaCliente;
import com.relative.quski.model.TbQoDatoTrabajoCliente;
import com.relative.quski.model.TbQoDetalleCredito;
import com.relative.quski.model.TbQoDevolucion;
import com.relative.quski.model.TbQoDireccionCliente;
import com.relative.quski.model.TbQoDocumentoHabilitante;
import com.relative.quski.model.TbQoExcepcion;
import com.relative.quski.model.TbQoExcepcionRol;
import com.relative.quski.model.TbQoHistoricoObservacion;
import com.relative.quski.model.TbQoHistoricoObservacionEntrega;
import com.relative.quski.model.TbQoHistoricoOperativa;
import com.relative.quski.model.TbQoNegociacion;
import com.relative.quski.model.TbQoProceso;
import com.relative.quski.model.TbQoPublicidad;
import com.relative.quski.model.TbQoReferenciaPersonal;
import com.relative.quski.model.TbQoReferido;
import com.relative.quski.model.TbQoRegistrarPago;
import com.relative.quski.model.TbQoRiesgoAcumulado;
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
import com.relative.quski.repository.DevolucionRepository;
import com.relative.quski.repository.DireccionClienteRepository;
import com.relative.quski.repository.DocumentoHabilitanteRepository;
import com.relative.quski.repository.ExcepcionRolRepository;
import com.relative.quski.repository.ExcepcionesRepository;
import com.relative.quski.repository.HistoricoObservacionEntregaRepository;
import com.relative.quski.repository.HistoricoObservacionRepository;
import com.relative.quski.repository.HistoricoOperativaRepository;
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.ProcesoRepository;
import com.relative.quski.repository.PublicidadRepository;
import com.relative.quski.repository.ReferenciaPersonalRepository;
import com.relative.quski.repository.ReferidoRepository;
import com.relative.quski.repository.RegistrarPagoRepository;
import com.relative.quski.repository.RiesgoAcumuladoRepository;
import com.relative.quski.repository.TasacionRepository;
import com.relative.quski.repository.TelefonoClienteRepository;
import com.relative.quski.repository.TipoArchivoRepository;
import com.relative.quski.repository.TipoDocumentoRepository;
import com.relative.quski.repository.TrackingRepository;
import com.relative.quski.repository.VariablesCrediticiaRepository;
import com.relative.quski.repository.spec.ClienteByIdentificacionSpec;
import com.relative.quski.repository.spec.CreditoNegociacionByParamsSpec;
import com.relative.quski.util.EmailUtil;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.util.QuskiOroUtil;
import com.relative.quski.wrapper.AbonoWrapper;
import com.relative.quski.wrapper.ActualizarGaratiaWrapper;
import com.relative.quski.wrapper.AprobacionNovacionWrapper;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.AprobarWrapper;
import com.relative.quski.wrapper.ArchivoComprobanteWrapper;
import com.relative.quski.wrapper.AutorizacionBuroWrapper;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.CabeceraWrapper;
import com.relative.quski.wrapper.CalculadoraOpcionWrapper;
import com.relative.quski.wrapper.CatalogoResponseWrapper;
import com.relative.quski.wrapper.CatalogoTablaAmortizacionWrapper;
import com.relative.quski.wrapper.CatalogoWrapper;
import com.relative.quski.wrapper.CatalogosSoftbankWrapper;
import com.relative.quski.wrapper.ClienteCompletoWrapper;
import com.relative.quski.wrapper.ClienteYReferidoWrapper;
import com.relative.quski.wrapper.ConsultaGarantiaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalRespuestaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalWrapper;
import com.relative.quski.wrapper.ConsultaOperacionGlobalWrapper;
import com.relative.quski.wrapper.ConsultaRubrosWrapper;
import com.relative.quski.wrapper.ConsultaTablaWrapper;
import com.relative.quski.wrapper.CotizacionWrapper;
import com.relative.quski.wrapper.CreacionClienteRespuestaCoreWp;
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRenovacionWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.CrearRenovacionWrapper;
import com.relative.quski.wrapper.CreditoCreadoSoftbank;
import com.relative.quski.wrapper.CrmEntidadWrapper;
import com.relative.quski.wrapper.CrmGuardarProspectoWrapper;
import com.relative.quski.wrapper.CrmProspectoCortoWrapper;
import com.relative.quski.wrapper.CrmProspectoWrapper;
import com.relative.quski.wrapper.CuentaWrapper;
import com.relative.quski.wrapper.CuotasAmortizacionWrapper;
import com.relative.quski.wrapper.DatosCuentaClienteWrapper;
import com.relative.quski.wrapper.DatosGarantiasWrapper;
import com.relative.quski.wrapper.DatosImpComWrapper;
import com.relative.quski.wrapper.DatosRegistroWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;
import com.relative.quski.wrapper.FileObjectStorage;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.GaranteWrapper;
import com.relative.quski.wrapper.GarantiaOperacionWrapper;
import com.relative.quski.wrapper.GaratiaAvaluoWrapper;
import com.relative.quski.wrapper.HistoricoObservacionWrapper;
import com.relative.quski.wrapper.HistoricoOperativaWrapper;
import com.relative.quski.wrapper.Informacion;
import com.relative.quski.wrapper.Informacion.DATOSCLIENTE;
import com.relative.quski.wrapper.Informacion.XmlVariablesInternas.VariablesInternas.Variable;
import com.relative.quski.wrapper.InformacionWrapper;
import com.relative.quski.wrapper.JoyaWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;
import com.relative.quski.wrapper.OpPorAprobarWrapper;
import com.relative.quski.wrapper.OpcionAndGarantiasWrapper;
import com.relative.quski.wrapper.OpcionWrapper;
import com.relative.quski.wrapper.OperacionCreditoNuevoWrapper;
import com.relative.quski.wrapper.PagosNovacionSoftWrapper;
import com.relative.quski.wrapper.ProcesoCaducadoWrapper;
import com.relative.quski.wrapper.ProcesoDevoActivoWrapper;
import com.relative.quski.wrapper.RegistroPagoRenovacionWrapper;
import com.relative.quski.wrapper.RenovacionWrapper;
import com.relative.quski.wrapper.ResponseWebMupi;
import com.relative.quski.wrapper.RespuestaAbonoWrapper;
import com.relative.quski.wrapper.RespuestaAprobarWrapper;
import com.relative.quski.wrapper.RespuestaConsultaGlobalWrapper;
import com.relative.quski.wrapper.RespuestaCrearClienteWrapper;
import com.relative.quski.wrapper.RespuestaObjectWrapper;
import com.relative.quski.wrapper.ResultOperacionesAprobarWrapper;
import com.relative.quski.wrapper.ResultOperacionesWrapper;
import com.relative.quski.wrapper.SimularResponse;
import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlGarantias.Garantias.Garantia;
import com.relative.quski.wrapper.SimularResponseExcepcion;
import com.relative.quski.wrapper.SoftbankActividadEconomicaWrapper;
import com.relative.quski.wrapper.SoftbankClienteWrapper;
import com.relative.quski.wrapper.SoftbankConsultaWrapper;
import com.relative.quski.wrapper.SoftbankContactosWrapper;
import com.relative.quski.wrapper.SoftbankCuentasBancariasWrapper;
import com.relative.quski.wrapper.SoftbankDatosTrabajoWrapper;
import com.relative.quski.wrapper.SoftbankDireccionWrapper;
import com.relative.quski.wrapper.SoftbankOperacionWrapper;
import com.relative.quski.wrapper.SoftbankRiesgoWrapper;
import com.relative.quski.wrapper.SoftbankTablaAmortizacionWrapper;
import com.relative.quski.wrapper.SoftbankTelefonosWrapper;
import com.relative.quski.wrapper.TelefonosContactoClienteWrapper;
import com.relative.quski.wrapper.TipoOroWrapper;
import com.relative.quski.wrapper.TokenWrapper;
import com.relative.quski.wrapper.TrackingWrapper;
import com.relative.quski.wrapper.TrakingProcesoWrapper;

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
	private DevolucionService ds;
	@Inject
	private ReportService rs;
	@Inject
	private DevolucionRepository devolucionRepository;
	@Inject
	PagoService ps;
	@Inject
	private PublicidadRepository publicidadRepository;
	@Inject
	private ReferidoRepository referidoRepository;
	@Inject
	private HistoricoOperativaRepository historicoOperativaRepository;
	@Inject
	private HistoricoObservacionEntregaRepository historicoObservacionEntregaRepository;
	
	
	@Inject
	HistoricoObservacionRepository historicoObservacionRepository;
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
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,"NO SE PUEDE LEER LA IDENTIFICACION DEL CLIENTE");
			}
		} catch (RelativeException e) {
			throw e;
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
				StringBuilder str = new StringBuilder();
				str.append(StringUtils.isNotBlank(send.getApellidoPaterno() )? send.getApellidoPaterno().trim() + " ": "");
				str.append(StringUtils.isNotBlank(send.getApellidoMaterno() )?send.getApellidoMaterno().trim() + " ": "");
				str.append(StringUtils.isNotBlank(send.getPrimerNombre() )? send.getPrimerNombre().trim()  + " ": "");
				str.append(StringUtils.isNotBlank(send.getSegundoNombre() )? send.getSegundoNombre().trim(): "");			
				persisted.setNombreCompleto(str.toString());
			}
			log.info("NOMBRE COMPLETO LUEGO DE SETEAR EN UPDATE ====================> " + persisted.getNombreCompleto() );
			if( send.getIngresos() != null ) {
				persisted.setIngresos( send.getIngresos() );
			}
			if( send.getEgresos() != null ) {
				persisted.setEgresos( send.getEgresos() );
			}
			if( send.getPasivos() != null ) {
				persisted.setPasivos( send.getPasivos() );
			}
			if( send.getActivos() != null ) {
				persisted.setActivos( send.getActivos() );
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
					errores.put("direccion-" + dc.getDivisionPolitica(), "ERROR REGISTRO DIRRECION" + e.getDetalle());
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
					errores.put("DATOS Error-" + dc.getIdSoftbank(), "ERROR REGISTRO DIRRECION" + e.getDetalle());
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
							e.setEstado( re.getEstado() );
							try {
								this.manageReferenciaPersonal( e );
							} catch (RelativeException e1) {
								errores.put("Referencia " + e.getId()," ERROR REGISTRO REFERENCIA " + e1.getDetalle());
							}
						}
					});
					existentes.forEach(a ->{
						if(a.getEstado().equals(EstadoEnum.INA)) {
							try {
								this.manageReferenciaPersonal( a );
							} catch (RelativeException e1) {
								errores.put("Referencia " + a.getId()," ERROR REGISTRO REFERENCIA " + e1.getDetalle());
							}
						}
					});
				}
				try {
					this.manageReferenciaPersonal(re);
				} catch (RelativeException e) {
					errores.put("Referencia -" + re.getTbQoCliente(), "Error registro Referencia " + e.getDetalle());
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
							e.setEstado( re.getEstado() );
							try {
								this.manageTelefonoCliente( e );
							} catch (RelativeException e1) {
								errores.put("telefono " + e.getId()," ERROR REGISTRO telefono " + e1.getDetalle());
							}
						}
					});
					existentes.forEach(a->{
						if(a.getEstado().equals( EstadoEnum.INA)) {
							try {
								this.manageTelefonoCliente(a );
							} catch (RelativeException e1) {
								errores.put("telefono " + a.getId()," ERROR REGISTRO telefono " + e1.getDetalle());
							}
						}
					});
				}
				try {
					this.manageTelefonoCliente(re);
				} catch (RelativeException e) {
					errores.put("telefono-" + re.getTbQoCliente(), "Error registro telefono " + e.getDetalle());
				}
			});
		}
		return errores;
	}
	private Map<String, String> createCuentasBancarias(TbQoCliente cliente, List<TbQoCuentaBancariaCliente> cuentas) throws RelativeException {
		Map<String, String> errores = new HashMap<>();
		List<TbQoCuentaBancariaCliente> existentes = this.cuentaBancariaRepository.findByAllIdCliente( cliente.getId() );
		if(existentes != null) {
			existentes.forEach(f->{
				log.info( "EL CAMPO DE SI ES ACTIVO O NO PARTE 3 (EXITENTES EN BASE) ======> " +  f.getEstado().toString());
				f.setEstado( EstadoEnum.INA );
			});			
		}
		if (cuentas != null && !cuentas.isEmpty()) {
			cuentas.forEach(re -> {
				re.setTbQoCliente(cliente);
				if( re.getId() != null && existentes != null ) {
					existentes.forEach(e->{
						if( re.getId().equals( e.getId() ) ) {
							e.setEstado( re.getEstado() );
							log.info( "EL CAMPO DE SI ES ACTIVO O NO PARTE 4 (VALOR FINAL) ======> " +  e.getEstado().toString());
							try {
								this.manageCuentaBancariaCliente( e );
							} catch (RelativeException e1) {
								errores.put("Cuenta " + e.getId()," ERROR REGISTRO Cuenta " + e1.getDetalle());
							}
						}
					});
					existentes.forEach(a->{
						if(a.getEstado().equals( EstadoEnum.INA)) {
							try {
								this.manageCuentaBancariaCliente( a );
							} catch (RelativeException e1) {
								errores.put("Cuenta " + a.getId()," ERROR REGISTRO Cuenta " + e1.getDetalle());
							}
						}
					});
				}
				try {
					this.manageCuentaBancariaCliente(re);
				} catch (RelativeException e) {
					errores.put("cuentas-" + re.getTbQoCliente(), "Error registro cuentas " + e.getDetalle());
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
			Map<String, String> erroresReferencia = this.createReferenciasPersonales(locCliente,
					cliente.getTbQoReferenciaPersonals());
			if (erroresReferencia.size() > 0) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + erroresReferencia.toString());
			}
		} catch (RelativeException e) {
			throw e;
		}
	}

	/**
	 * * * * * * * * * * * @COTIZADOR
	 */
	public TbQoCotizador findCotizadorById(Long id) throws RelativeException {
		try {
			return cotizadorRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		}
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
			throw e;
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
			throw e;
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
			throw e;
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
			if( StringUtils.isNotBlank( send.getGradoInteres() ) ) {
				persisted.setGradoInteres(send.getGradoInteres());				
			}
			if( StringUtils.isNotBlank( send.getMotivoDeDesestimiento() )) {
				persisted.setMotivoDeDesestimiento(send.getMotivoDeDesestimiento());				
			}
			if( send.getIdAgencia() != null) {
				persisted.setIdAgencia( send.getIdAgencia());
			}
			if( send.getEstado() != null ) {
				persisted.setEstado(EstadoEnum.ACT);				
			}
			if( StringUtils.isNotBlank( send.getAsesor() )) {
				persisted.setAsesor( send.getAsesor());
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return this.cotizadorRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
		}
	}

	public List<TbQoDetalleCredito> manageDetalleCreditos(List<TbQoDetalleCredito> sends) {
		List<TbQoDetalleCredito> persisteds = new ArrayList<>();
		sends.forEach(element -> {
			element.setEstado(EstadoEnum.ACT);
			element.setId(null);
			element.setFechaCreacion(new Date(System.currentTimeMillis()));
			try {
				this.manageDetalleCredito(element);
			} catch (RelativeException e) {
				e.printStackTrace();
			}
		});
		return persisteds;
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
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " AL LEER LA ENTIDAD DE DETALLE DE CREDITO.");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
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
			persisted.setFechaActualizacion( send.getFechaActualizacion() );	
			if( send.getEstado() != null ){
			    persisted.setEstado( send.getEstado() );
			}
			if( send.getPeriodoPlazo() != null ){
			    persisted.setPeriodoPlazo( send.getPeriodoPlazo() );
			}
			if( send.getTbQoCotizador() != null ){
			    persisted.setTbQoCotizador( send.getTbQoCotizador() );
			}
			if( send.getCostoCredito() != null ){
			    persisted.setCostoCredito( send.getCostoCredito() );
			}
			if( send.getCostoCustodia() != null ){
			    persisted.setCostoCustodia( send.getCostoCustodia() );
			}
			if( send.getCostoEstimado() != null ){
			    persisted.setCostoEstimado( send.getCostoEstimado() );
			}
			if( send.getCostoNuevaOperacion() != null ){
			    persisted.setCostoNuevaOperacion( send.getCostoNuevaOperacion() );
			}
			if( send.getCostoResguardado() != null ){
			    persisted.setCostoResguardado( send.getCostoResguardado() );
			}
			if( send.getCostoSeguro() != null ){
			    persisted.setCostoSeguro( send.getCostoSeguro() );
			}
			if( send.getCostoTasacion() != null ){
			    persisted.setCostoTasacion( send.getCostoTasacion() );
			}
			if( send.getCostoTransporte() != null ){
			    persisted.setCostoTransporte( send.getCostoTransporte() );
			}
			if( send.getCostoValoracion() != null ){
			    persisted.setCostoValoracion( send.getCostoValoracion() );
			}
			if( send.getMontoPreaprobado() != null ){
			    persisted.setMontoPreaprobado( send.getMontoPreaprobado() );
			}
			if( send.getPlazo() != null ){
			    persisted.setPlazo( send.getPlazo() );
			}
			if( send.getRecibirCliente() != null ){
			    persisted.setRecibirCliente( send.getRecibirCliente() );
			}
			if( send.getSolca() != null ){
			    persisted.setSolca( send.getSolca() );
			}
			if( send.getValorCuota() != null ){
			    persisted.setValorCuota( send.getValorCuota() );
			}
			
			return detalleCreditoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
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
	public List<TbQoCotizador> cotizacionByCedula(PaginatedWrapper pw, String cedula) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return cotizadorRepository.findByCedula(pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(),
					pw.getSortDirections(), cedula);
		} else {
			return cotizadorRepository.findByCedula(cedula);
		}
	}
	public Long cotizacionCountByCedula(String cedula) throws RelativeException {
		return this.cotizadorRepository.countByCedula(cedula);
	}

	/**
	 ************************************ @Cliente
	 */

	private TbQoCliente createCliente(String cedula, String autorizacion, Informacion data) throws RelativeException {
		try {
			TbQoCliente cliente = this.findClienteByIdentifiacion(cedula);
			if (cliente != null) {
				return cliente;
			} 
			cliente = this.clienteSoftToTbQoCliente(this.findClienteSoftbank(cedula, autorizacion));
			if (cliente != null) {
				return this.manageCliente(cliente);
			} 
			cliente = this.prospectoCrmToTbQoCliente( this.findProspectoCrm(cedula, autorizacion) );
			if (cliente != null) { 
				return this.manageCliente(cliente); 
			}
			return this.createClienteFromEquifax(data.getDATOSCLIENTE());
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_CREATE_CLIENTE);
		}
	}
	private TbQoCliente prospectoCrmToTbQoCliente(CrmProspectoCortoWrapper crmW ) throws RelativeException {
		try {
			if (crmW == null) {
				return null;
			}
			TbQoCliente cliente = new TbQoCliente();
			cliente.setNombreCompleto( crmW.getNombreCompleto() );
			cliente.setCedulaCliente( crmW.getCedula() );
			cliente.setEmail( crmW.getEmail() );
			cliente = this.manageCliente(cliente); 
			if( StringUtils.isNotBlank( crmW.getPhoneMobile() ) ) {
				TbQoTelefonoCliente tlfMovil = new TbQoTelefonoCliente();
				tlfMovil.setTipoTelefono( "CEL" );
				tlfMovil.setNumero( crmW.getPhoneMobile() );
				tlfMovil.setTbQoCliente( cliente );
				tlfMovil = this.manageTelefonoCliente( tlfMovil );			
			}
			if( StringUtils.isNotBlank( crmW.getPhoneHome() ) ) {
				TbQoTelefonoCliente tlfCasa = new TbQoTelefonoCliente();
				tlfCasa.setTipoTelefono( "DOM" );
				tlfCasa.setTbQoCliente( cliente );
				tlfCasa.setNumero( crmW.getPhoneHome() );
				tlfCasa = this.manageTelefonoCliente( tlfCasa );			
			}
			return cliente;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_CREATE_CLIENTE);
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
			String segundoNombre, String apellidoMaterno, String telefono, String celular, String correo, String nombreCompleto,
			EstadoEnum estado) throws RelativeException {
		return this.clienteRepository.countByParams(identificacion, primerNombre, apellidoPaterno, segundoNombre,
				apellidoMaterno, telefono, celular, correo, nombreCompleto, estado);
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
			String correo, String nombreCompleto, EstadoEnum estado) throws RelativeException {
		
		log.info("=================> " + identificacion);
		log.info("=================> " + nombreCompleto);
		return this.clienteRepository.findByParams(pw, identificacion, primerNombre, apellidoPaterno, segundoNombre,
				apellidoMaterno, telefono, celular, correo, nombreCompleto, estado);
	}

	/**
	 * * * * * * * * * * * @VARIABLE @CREDITICIA
	 */

	public ArrayList<TbQoVariablesCrediticia> manageListVariablesCrediticias(List<TbQoVariablesCrediticia> send, TbQoNegociacion nego)
			throws RelativeException {
		try {
			ArrayList<TbQoVariablesCrediticia> persisted = new ArrayList<>();
			send.forEach(element -> {
				log.info( "====================================> VARIABLE: " + element.getCodigo());
				element.setEstado(EstadoEnum.ACT);
				element.setFechaCreacion(new Date(System.currentTimeMillis()));
				if( nego != null ) {
					element.setTbQoNegociacion(nego);					
				}
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
		}
	}

	/**
	 * * * * * * * * * * * @NEGOCIACION
	 */
	public TbQoNegociacion findNegociacionById(Long id) throws RelativeException {
		try {
			return this.negociacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
		}

	}
	
	public TbQoDevolucion manageDevolucion(TbQoDevolucion send) throws RelativeException {
		try {
			TbQoDevolucion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.devolucionRepository.findById(send.getId());
				return this.updateDevolucion(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				return this.devolucionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
		}

	}
	
	public TbQoDevolucion updateDevolucion(TbQoDevolucion send, TbQoDevolucion pesisted) throws RelativeException {
		try {
			pesisted.setMontoCredito(pesisted.getMontoCredito());
			pesisted.setPlazoCredito(pesisted.getPlazoCredito());
			pesisted.setTipoCredito(pesisted.getTipoCredito());
			pesisted.setNumeroCuentaCliente(pesisted.getNumeroCuentaCliente());
			pesisted.setNombreAsesor(pesisted.getNombreAsesor());
			pesisted.setAsesor(send.getAsesor());
			
			return this.devolucionRepository.update(pesisted);
		} catch (RelativeException e) {
			
			e.printStackTrace();
			throw e;
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
				send.setEstado( send.getEstado() != null ? send.getEstado() : EstadoEnum.ACT );
				return this.datoTrabajoClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
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
			if( send.getEstado() != null ) {
				persisted.setEstado(   send.getEstado() );
			}
			return datoTrabajoClienteRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}

	private TbQoNegociacion updateNegociacion(TbQoNegociacion send, TbQoNegociacion persisted)
			throws RelativeException {
		try {
			if (StringUtils.isNotBlank(send.getAsesor()) ) {
				persisted.setAsesor(send.getAsesor());
			}
			if (StringUtils.isNotBlank(send.getAprobador()) ) {
				persisted.setAprobador(send.getAprobador());
			}
			if (StringUtils.isNotBlank(send.getCorreoAsesor()) ) {
				persisted.setCorreoAsesor(send.getCorreoAsesor());
			}
			if (StringUtils.isNotBlank(send.getNombreAsesor()) ) {
				persisted.setNombreAsesor(send.getNombreAsesor());
			}
			if (StringUtils.isNotBlank(send.getObservacionAsesor()) ) {
				persisted.setObservacionAsesor(send.getObservacionAsesor());
			}
			if (StringUtils.isNotBlank(send.getMotivo()) ) {
				persisted.setMotivo(send.getMotivo());
			}
			if (StringUtils.isNotBlank(send.getEstadoCredito()) ) {
				persisted.setEstadoCredito(send.getEstadoCredito());
			}
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return negociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
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
				send.setEstado( send.getEstado() != null ? send.getEstado() : EstadoEnum.ACT );
				return this.telefonoClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		}catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL GUARDAR TELEFONOS DEL CLIENTE"+e.getCause());
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
			throw e;
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
				send.setEstado( send.getEstado() != null ? send.getEstado() : EstadoEnum.ACT );
				return this.cuentaBancariaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		}catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}
	private TbQoCuentaBancariaCliente updateCuentaBancariaCliente(TbQoCuentaBancariaCliente send, TbQoCuentaBancariaCliente persisted)
			throws RelativeException {
		try {
			if (send.getIdSoftbank() != null) {
				persisted.setIdSoftbank(send.getIdSoftbank());
			}
			if (send.getEsNueva() != null) {
				persisted.setEsNueva(send.getEsNueva());
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
			log.info( "EL CAMPO DE SI ES ACTIVO O NO PARTE 5 (ESTO SE GUARDA) ======> " +  persisted.getEstado().toString() );

			return cuentaBancariaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}
	/**
	 * * * * * * * * * * * @TASACION
	 */
	public TbQoTasacion findTasacionById(Long id) throws RelativeException {
		try {
			return tasacionRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
		}

	}

	private TbQoTasacion updateTasacion(TbQoTasacion send, TbQoTasacion persisted) throws RelativeException {
		try {
			if(send.getDescripcion() != null) {
				persisted.setDescripcion(send.getDescripcion());
			}
			if(StringUtils.isNotBlank(send.getTipoJoya()) ) {
				persisted.setTipoJoya(send.getTipoJoya());
			}
			
			if( send.getTienePiedras() != null && send.getTienePiedras()) {
				persisted.setTienePiedras( send.getTienePiedras() );
				persisted.setDescuentoPesoPiedra(send.getDescuentoPesoPiedra());
				persisted.setDetallePiedras( send.getDetallePiedras() );
			}else {
				persisted.setTienePiedras( Boolean.FALSE );
				persisted.setDescuentoPesoPiedra( BigDecimal.valueOf( 0 ) );
				persisted.setDetallePiedras( StringUtils.EMPTY );
			}
			if(send.getDescuentoSuelda() != null) {
				persisted.setDescuentoSuelda(send.getDescuentoSuelda());
			}
			if(send.getEstadoJoya() != null) {
				persisted.setEstadoJoya(send.getEstadoJoya());
			}
			if(send.getNumeroPiezas() != null) {
				persisted.setNumeroPiezas(send.getNumeroPiezas());
			}
			if(send.getPesoBruto() != null) {
				persisted.setPesoBruto(send.getPesoBruto());
			}
			if(send.getPesoNeto() != null) {
				persisted.setPesoNeto(send.getPesoNeto());
			}
			if(send.getValorAvaluo() != null) {
				persisted.setValorAvaluo(send.getValorAvaluo());
			}
			if(send.getValorComercial() != null) {
				persisted.setValorComercial(send.getValorComercial());
			}
			if(send.getValorOro() != null) {
				persisted.setValorOro(send.getValorOro());
			}
			if(send.getValorRealizacion() != null) {
				persisted.setValorRealizacion(send.getValorRealizacion());
			}
			if(persisted.getFechaCreacion() != null) {
				persisted.setFechaCreacion(persisted.getFechaCreacion());
			}
			if( send.getEstado() != null) {
				persisted.setEstado( send.getEstado() );
			}
			if( StringUtils.isNotBlank( send.getTipoOro())) {
				persisted.setTipoOro( send.getTipoOro());
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return tasacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}
	/**
	 * * * * * * * * * * * @NEGOCIACION_WRAPPER
	 */
	public NegociacionWrapper iniciarNegociacion(String cedula, String asesor, Long idAgencia, String autorizacion, String nombreAgencia, String telefonoAsesor) throws RelativeException {
		try {
			log.info("<<=================ENTRAR A INICIAR NEGOCIACION=============>>>");
			
			Informacion data = informacionCliente(cedula);
			if( data == null || data.getDATOSCLIENTE() == null ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," NO SE PUDO ENCONTRAR INFORMACION DEL CLIENTE");
			}
			TbQoCliente cliente = this.createCliente(cedula, autorizacion, data);
			if (cliente != null) {
				
				TbQoCreditoNegociacion credito = this.createCredito(cliente, asesor, idAgencia, nombreAgencia, telefonoAsesor);
				TbQoProceso proceso = this.createProcesoNegociacion( credito.getTbQoNegociacion().getId(), asesor);
				return generarTablasIniciales(proceso, credito, cliente, asesor, idAgencia, data,autorizacion, nombreAgencia, telefonoAsesor);
			} 
			
			return new NegociacionWrapper(false);
			
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
		
	}
	public CotizacionWrapper iniciarCotizacion(String cedula, String asesor, Long idAgencia, String autorizacion) throws RelativeException {
		try {
			log.info("<<=================ENTRAR A INICIAR COTIZACION=============>>>");
			Informacion data = informacionCliente(cedula);
			if( data == null || data.getDATOSCLIENTE() == null ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," NO SE PUDO ENCONTRAR INFORMACION DEL CLIENTE");
			}
			TbQoCliente cliente = this.createCliente(cedula,autorizacion, data);
			if (cliente != null) {
				return generarTablasInicialesCotizacion(cliente, asesor, idAgencia, data, autorizacion);
			} else {
				return null;
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_COTIZACION + e.getMessage());
		}
	}

	

	public List<TbQoTasacion> agregarJoya(TbQoTasacion joya, String asesor,String nombreAsesor, String correoAsesor) throws RelativeException {
		if(joya == null || joya.getTbQoCreditoNegociacion() == null || joya.getTbQoCreditoNegociacion().getId() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE LA JOYA");
		}
		
		TbQoCreditoNegociacion credito = this.findCreditoNegociacionById(joya.getTbQoCreditoNegociacion().getId());
		
		if(credito == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA LA NEGOCIACION");
		}
		this.excepcionesRepository.inactivarExcepcionByTipoExcepcionAndIdNegociacion("EXCEPCION_RIESGO", credito.getTbQoNegociacion().getId());
		this.excepcionesRepository.inactivarExcepcionByTipoExcepcionAndIdNegociacion("EXCEPCION_COBERTURA", credito.getTbQoNegociacion().getId());
		credito.setCobertura(null);
		credito.getTbQoNegociacion().setNombreAsesor(asesor);
		credito.getTbQoNegociacion().setCorreoAsesor(correoAsesor);
		manageNegociacion(credito.getTbQoNegociacion());
		manageCreditoNegociacion(credito);
		return this.getDetalleJoya(joya);
	
	}
	public List<TbQoTasacion> agregarJoyaCotizacion(TbQoTasacion joya, String asesor) throws RelativeException {
		if(joya == null || joya.getTbQoCotizador() == null || joya.getTbQoCotizador().getId() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE LA JOYA");
		}
		TbQoCotizador cotizador = this.cotizadorRepository.findById( joya.getTbQoCotizador().getId()  );
		
		if(cotizador == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA LA COTIZACION");
		}
		this.manageCotizador(cotizador);
		return this.getDetalleJoyaCotizacion(cotizador.getTbQoCliente(), joya);
	}
	
	public List<TbQoTasacion> getDetalleJoyaCotizacion(TbQoTasacion j) throws RelativeException {	
		this.manageTasacion(j);
	
		return this.tasacionRepository.findByIdCotizador(j.getTbQoCotizador().getId());
	}
	
	public List<TbQoTasacion> getDetalleJoyaCotizacion(TbQoCliente cliente, TbQoTasacion joya) throws RelativeException {		
		try {

			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			contentXMLGarantia= contentXMLGarantia
					.replace( "--tipo-joya--" ,"ANI")
					.replace("--descripcion--","BUEN ESTADO")
					.replace("--estado-joya--", "BUE")
					.replace("--tipo-oro-quilataje--", joya.getTipoOro())
					.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ))
					.replace("--tiene-piedras--", "s")
					.replace("--detalle-piedras--", "PIEDRAS")
					.replace("--descuento-peso-piedras--","0.73")
					.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
					.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
					.replace("--valor-aplicable-credito--", "293.02")
					.replace("--valor-realizacion--", "232.07")
					.replace("--numero-piezas--", "1")
					.replace("--descuento-suelda--", "0.00")
					.replace("--numero-garantia--", "0")
					.replace("--numero-expediente--", "0");
				log.info("==============>>>>> XML garantia");
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")
					.replace("--origen-operacion--", "N")
					.replace("--riesgo-total--", "0.00")
					.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(cliente.getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--perfil-preferencia--", "A")
					.replace("--agencia-originacion--", "020")
					.replace("--identificacion-cliente--",cliente.getCedulaCliente())
					.replace("--calificacion-mupi--", cliente.getAprobacionMupi())
					.replace("--cobertura-exepcionada--", "0")
					.replace("--garanttias-detalle--", contentXMLGarantia)
					.replace("--monto-solicitado--", "0");
				log.info("==============>>>>> XML calculadora");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
						this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				SimularResponse responseSimulador = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				if(responseSimulador != null && responseSimulador.getSimularResult() != null && responseSimulador.getSimularResult().getXmlGarantias() != null
						 && responseSimulador.getSimularResult().getXmlGarantias().getGarantias() != null && responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia() != null
						 && !responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia().isEmpty()) {
					log.info("==============> Resultado de garantias calculadora ");
					for ( Garantia g : responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia()) {
						TbQoTasacion j = new TbQoTasacion();
						j.setDescripcion(g.getDescripcion());
						j.setDescuentoPesoPiedra(BigDecimal.valueOf(g.getDescuentoPesoPiedras()) );
						j.setDescuentoSuelda(BigDecimal.valueOf(g.getDescuentoSuelda()) );
						j.setDetallePiedras(g.getDetallePiedras());
						j.setEstado(EstadoEnum.ACT);
						j.setEstadoJoya(g.getEstadoJoya());
						j.setId(joya.getId());
						j.setNumeroPiezas(Long.valueOf(g.getNumeroPiezas()) );
						j.setPesoBruto(BigDecimal.valueOf(g.getPesoGr()) );
						j.setTienePiedras(StringUtils.isNotBlank(g.getTienePiedras()) && g.getTienePiedras().equalsIgnoreCase("S")?Boolean.TRUE:Boolean.FALSE);
						j.setPesoNeto(BigDecimal.valueOf(g.getPesoNeto()) );
						j.setTipoJoya(g.getTipoJoya());
						j.setTipoOro(g.getTipoOroKilataje());
						j.setValorAvaluo(BigDecimal.valueOf(g.getValorAvaluo()) );
						j.setValorRealizacion(BigDecimal.valueOf(g.getValorRealizacion()) );
						j.setValorComercial(BigDecimal.valueOf(g.getValorAplicable()) );
						j.setTbQoCotizador( joya.getTbQoCotizador());
						j.setValorOro(BigDecimal.valueOf(g.getValorOro()));
						this.manageTasacion(j);
					}
					return this.tasacionRepository.findByIdCotizador(joya.getTbQoCotizador().getId());
				}	
			return null;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	

	public List<TipoOroWrapper> verPrecio(ClienteYReferidoWrapper clienteWrapper) throws RelativeException {
		
		if(clienteWrapper.getIdCreditoNegociacion() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER ID DEL CREDITO");
		}
		
		if(clienteWrapper.getCliente() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE");
		}
		
		if(clienteWrapper.getCliente().getFechaNacimiento() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA FECHA DE NACIMIENTO DEL CLIENTE");
		}
		
		if(StringUtils.isBlank( clienteWrapper.getCliente().getAprobacionMupi() ) ) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE APROBACION MUPI DEL CLIENTE");
		}
		if(clienteWrapper.getBandera() == true ) {
			if(clienteWrapper.getReferido() ==  null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL REFERIDO");
			}else {
				this.manageReferido(clienteWrapper.getReferido());
			}
		}
		
		if(clienteWrapper.getCliente().getTbQoTelefonoClientes() != null && !clienteWrapper.getCliente().getTbQoTelefonoClientes().isEmpty() ) {
			updateCliente(clienteWrapper.getCliente());
			this.createTelefonosCliente(clienteWrapper.getCliente(), clienteWrapper.getCliente().getTbQoTelefonoClientes());
		} else {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LOS NUMEROS DE TELEFONO");
		}
		TbQoCreditoNegociacion credito =  this.findCreditoNegociacionById(clienteWrapper.getIdCreditoNegociacion());
		credito.setNombreCompletoApoderado(clienteWrapper.getNombreApoderado());
		credito.setNombreCompletoCodeudor(clienteWrapper.getNombreCodeudor());
		credito.setTipoCliente(clienteWrapper.getTipoCliente());
		credito.setIdentificacionApoderado(clienteWrapper.getIdentificacionApoderado());
		credito.setIdentificacionCodeudor(clienteWrapper.getIdentificacionCodeudor());
		credito.setFechaNacimientoApoderado(clienteWrapper.getFechaNacimientoApoderado());
		credito.setFechaNacimientoCodeudor(clienteWrapper.getFechaNacimientoCodeudor());
		this.creditoNegociacionRepository.update(credito);
		return this.tipoOro();
	}
	


	/**
	 * Metodo que guarda la opcion del credito seleccionado
	 * @param opcionCredito
	 * @param asesor
	 * @param valueOf
	 * @return
	 * @throws RelativeException 
	 */
	public TbQoCreditoNegociacion guardarOpcionCredito(ClienteYReferidoWrapper opcionCredito, String asesor,
			Long idCredito, String autorizacion, String nombreAsesor, String correoAsesor) throws RelativeException {
		
		log.info("==============> ENTRA A GUARDAR OPCION CREDITO");
		CalculadoraOpcionWrapper opcion = opcionCredito.getOpcionCredito().get(0);
		TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findById(idCredito);
		credito.setId(idCredito);
		credito.setPlazoCredito(opcion.getPlazo());
		credito.setPeriodoPlazo(opcion.getPeriodoPlazo());
		credito.setPeriodicidadPlazo(opcion.getPeriodicidadPlazo());
		credito.setMontoFinanciado(opcion.getMontoFinanciado());
		credito.setValorARecibir(opcion.getValorARecibir());
		credito.setValorAPagar(opcion.getValorAPagar());
		credito.setCostoCustodia(opcion.getCostoCustodia());
		credito.setCostoFideicomiso(opcion.getCostoFideicomiso());
		credito.setCostoSeguro(opcion.getCostoSeguro());
		credito.setCostoTasacion(opcion.getCostoTasacion());
		credito.setCostoTransporte(opcion.getCostoTransporte());
		credito.setCostoValoracion(opcion.getCostoValoracion());
		credito.setImpuestoSolca(opcion.getImpuestoSolca());
		credito.setFormaPagoImpuestoSolca(opcion.getFormaPagoImpuestoSolca());
		credito.setFormaPagoCapital(opcion.getFormaPagoCapital());
		credito.setFormaPagoCustodia(opcion.getFormaPagoCustodia());
		credito.setFormaPagoFideicomiso(opcion.getFormaPagoFideicomiso());
		credito.setFormaPagoInteres(opcion.getFormaPagoInteres());
		credito.setFormaPagoMora(opcion.getFormaPagoMora());
		credito.setFormaPagoGastoCobranza(opcion.getFormaPagoGastoCobranza());
		credito.setFormaPagoSeguro(opcion.getFormaPagoSeguro());
		credito.setFormaPagoTasador(opcion.getFormaPagoTasador());
		credito.setFormaPagoTransporte(opcion.getFormaPagoTransporte());
		credito.setFormaPagoValoracion(opcion.getFormaPagoValoracion());
		credito.setSaldoInteres(opcion.getSaldoInteres());
		credito.setSaldoMora(opcion.getSaldoMora());
		credito.setGastoCobranza(opcion.getGastoCobranza());
		credito.setCuota(opcion.getCuota());
		credito.setSaldoCapitalRenov(opcion.getSaldoCapitalRenov());
		credito.setMontoPrevioDesembolso(opcion.getMontoPrevioDesembolso());
		credito.setTotalGastosNuevaOperacion(opcion.getTotalGastosNuevaOperacion());
		credito.setTotalCostosOperacionAnterior(opcion.getTotalCostosOperacionAnterior());
		credito.setCustodiaDevengada(opcion.getCustodiaDevengada());
		credito.setFormaPagoCustodiaDevengada(opcion.getFormaPagoCustodiaDevengada());
		credito.setTipoOferta(opcion.getTipooferta());
		credito.setPorcentajeFlujoPlaneado(opcion.getPorcentajeflujoplaneado());
		credito.setDividendoFlujoPlaneado(opcion.getDividendoflujoplaneado());
		credito.setDividendoProrrateo(opcion.getDividendosprorrateoserviciosdiferido());
		credito.setCanalContacto(credito.getTbQoNegociacion().getTbQoCliente().getCanalContacto());
		credito.getTbQoNegociacion().setNombreAsesor(nombreAsesor);
		credito.getTbQoNegociacion().setCorreoAsesor(correoAsesor);
		credito.setNombreCompletoApoderado(opcionCredito.getNombreApoderado());
		credito.setNombreCompletoCodeudor(opcionCredito.getNombreCodeudor());
		credito.setIdentificacionApoderado(opcionCredito.getIdentificacionApoderado());
		credito.setIdentificacionCodeudor(opcionCredito.getIdentificacionCodeudor());
		credito.setTipoCliente(opcionCredito.getTipoCliente());
		credito.setFechaNacimientoApoderado(opcionCredito.getFechaNacimientoApoderado());
		credito.setFechaNacimientoCodeudor(opcionCredito.getFechaNacimientoCodeudor());
		this.negociacionRepository.update(credito.getTbQoNegociacion());
		List<CatalogoTablaAmortizacionWrapper>  listTablas =  SoftBankApiClient.callCatalogoTablaAmortizacionRest(this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TABLA_AMOTIZACION).getValor(), autorizacion);
//		if(listTablas == null || listTablas.isEmpty()) {
//			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CATALOGO DE TABLA DE AMORTIZACION SOFTBANK");
//		}
//		listTablas.forEach(e->{
//			if( e.getPeriodoPlazo().equalsIgnoreCase( credito.getPeriodoPlazo()) && 
//				e.getPeriodicidadPlazo().equalsIgnoreCase( credito.getPeriodicidadPlazo()) &&
//				e.getTipoOferta().equalsIgnoreCase( credito.getTipoOferta() ) &&
//				e.getPlazo() == credito.getPlazoCredito() 
//			){
//				credito.setTablaAmortizacion( e.getCodigo() );
//				credito.setNumeroCuotas(e.getNumeroCuotas());					
//			}
//		});
//		if(StringUtils.isBlank(credito.getTablaAmortizacion())) {
//			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR UN CODIGO DE TABLA DE AMORTIZACION PARA LA OPCION DE CREDITO SELECCCIONADA");
//		}
		if(listTablas != null && !listTablas.isEmpty()) {
			listTablas.forEach(e->{
				if(e.getCodigo().equalsIgnoreCase(opcion.getCodigoTabla())) {
					credito.setNumeroCuotas(e.getNumeroCuotas());
				}
				
			});
		}
		
		credito.setTablaAmortizacion(opcion.getCodigoTabla() );
		return this.creditoNegociacionRepository.update(credito);
	}

	
	/** ******************************* @INTEGRACION **********************/
	public TbQoCliente createClienteFromEquifax(DATOSCLIENTE cliente) throws RelativeException {
		if (cliente != null) {
				TbQoCliente c = new TbQoCliente();
				c.setCedulaCliente(cliente.getIDENTIFICACION());
				c.setNombreCompleto(cliente.getNOMBRESCOMPLETOS());
				c.setEmail(cliente.getCORREOELECTRONICO());
				c.setCargasFamiliares(Long.valueOf(cliente.getCARGASFAMILIARES()));
				c = this.manageCliente(c);
				return c;
		} else {
			return null;
		}
	}
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
					token.getToken_type()+" "+token.getAccess_token(), content);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private List<TbQoVariablesCrediticia> createVariablesFromEquifax(List<Variable> variables, TbQoNegociacion negociacion, TbQoCotizador cotizador) throws RelativeException {
			if (variables != null) {
				List<TbQoVariablesCrediticia> list = new ArrayList<>();
				for(Variable e : variables) {
					TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
					v.setCodigo(e.getCodigo());
					v.setNombre(e.getNombre());
					v.setOrden(String.valueOf(e.getOrden()));
					v.setValor(e.getValor());
					if(negociacion != null) {
						v.setTbQoNegociacion(negociacion);						
					}
					if(cotizador != null) {
						v.setTbQoCotizador(cotizador);
					}
					list.add(this.manageVariablesCrediticia(v));				
				}
	
				return list;
			} else {
				return null;
			}
		
	}
	private List<TbQoVariablesCrediticia> createVariablesFromEquifax(List<TbQoVariablesCrediticia> variables, TbQoNegociacion negociacion) throws RelativeException {
		if (variables != null) {
			List<TbQoVariablesCrediticia> list = new ArrayList<>();
			for(TbQoVariablesCrediticia e : variables) {
				TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
				v.setCodigo(e.getCodigo());
				v.setNombre(e.getNombre());
				v.setOrden(String.valueOf(e.getOrden()));
				v.setValor(e.getValor());
				if(negociacion != null) {
					v.setTbQoNegociacion(negociacion);						
				}
				list.add(this.manageVariablesCrediticia(v));				
			}

			return list;
		} else {
			return null;
		}
	
}
	
	public NegociacionWrapper iniciarNegociacionEquifax(String cedula, String asesor, Long idAgencia, String autorizacion, String nombreAgencia, String telefonoAsesor) throws RelativeException {
		try {
			Informacion data = informacionCliente(cedula);
			
			TbQoCliente cliente = this.createClienteFromEquifax(data.getDATOSCLIENTE());
			if (cliente != null) {
				TbQoCreditoNegociacion credito = this.createCredito(cliente, asesor, idAgencia, nombreAgencia, telefonoAsesor);
				TbQoProceso proceso = this.createProcesoNegociacion( credito.getTbQoNegociacion().getId(), asesor);
				return generarTablasIniciales(proceso, credito, cliente, asesor, idAgencia, data, autorizacion, nombreAgencia, telefonoAsesor);
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}
	
	public CotizacionWrapper iniciarCotizacionEquifax(String cedula, String asesor, Long idAgencia, String autorizacion) throws RelativeException {
		try {
			Informacion data = informacionCliente(cedula);
			TbQoCliente cliente = this.createClienteFromEquifax(data.getDATOSCLIENTE());
			if (cliente == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " AL CREAR CLIENTE COTIZACION DESDE EQUIFAX. ");
			}
			return generarTablasInicialesCotizacion(cliente, asesor, idAgencia, data, autorizacion);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_COTIZACION + e.getMessage());
		}
	}

	public NegociacionWrapper iniciarNegociacionFromCot(Long id, String asesor, Long idAgencia, String autorizacion, String nombreAgencia, String telefonoAsesor) throws RelativeException {
		try {
			TbQoCliente cliente = this.findClienteByCotizador(id);
			if (cliente != null) {
				TbQoCreditoNegociacion credito = this.createCredito(cliente, asesor, idAgencia, nombreAgencia, telefonoAsesor);
				TbQoProceso proceso = this.createProcesoNegociacion( credito.getTbQoNegociacion().getId(), asesor);
				Informacion data = informacionCliente(cliente.getCedulaCliente());
				return generarTablasIniciales(proceso, credito, cliente, asesor, idAgencia, data, autorizacion, nombreAgencia, telefonoAsesor);
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			
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
			throw e;
		}
	}

	public NegociacionWrapper traerNegociacionExistente(Long id, String autorizacion) throws RelativeException {
		try {
			NegociacionWrapper tmp = new NegociacionWrapper();
			tmp.setCredito(this.creditoNegociacionRepository.findCreditoByIdNegociacion(id));
			if (tmp.getCredito() != null) {
				TbQoProceso  proceso = this.findProcesoByIdReferencia( id, ProcesoEnum.NUEVO);
				if(proceso == null) {
					proceso = this.findProcesoByIdReferencia( id, ProcesoEnum.RENOVACION);
				}
				if(proceso != null) {
					EstadoProcesoEnum estado = proceso.getEstadoProceso();
					if( estado == EstadoProcesoEnum.APROBADO || estado == EstadoProcesoEnum.CANCELADO) {
						return new NegociacionWrapper(false);
					}else {
						tmp.setVariables(this.variablesCrediticiaRepository.findByIdNegociacion(id));
						this.riesgoAcumuladoRepository.deleteByIdNegociacion(id);
						List<TbQoRiesgoAcumulado> riesgosTb = this.manageListRiesgoAcumulados( 
								this.createRiesgoFrontSoftBank(
										consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), autorizacion), tmp.getCredito().getTbQoNegociacion(), null ));
						tmp.setRiesgos( riesgosTb );
						tmp.setJoyas(this.tasacionRepository.findByIdCredito(tmp.getCredito().getId() ));
						tmp.setExcepciones(this.excepcionesRepository.findByIdNegociacion(id));
						tmp.setRespuesta(true);
						tmp.setProceso( proceso );
						tmp.setTelefonoDomicilio(this.telefonoClienteRepository
								.findByClienteAndTipo(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), "DOM"));
						tmp.setTelefonoMovil(this.telefonoClienteRepository
								.findByClienteAndTipo(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), "CEL"));
						tmp.setCodigoExcepcionBre(Long.valueOf(tmp.getCredito().getTbQoNegociacion().getCodigoBre()) );
						tmp.setExcepcionBre(tmp.getCredito().getTbQoNegociacion().getMensajeBre() );
						tmp.setReferedio(this.referidoRepository.findByIdNegociacion(id));
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
			throw e;
		}
	}
	public ClienteCompletoWrapper traerClienteByIdNegociacion(Long id, String autorizacion) throws RelativeException {
		try {
			TbQoNegociacion nego = this.findNegociacionById( id );
			
			if(nego != null) {
				TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion(id);
				ClienteCompletoWrapper wp = traerCliente( nego.getTbQoCliente().getCedulaCliente(), credito.getCodigo() , autorizacion);
				BigDecimal totalAvaluo = this.tasacionRepository.totalAvaluo(id);
				wp.setTotalAvaluo(totalAvaluo);
				return wp;
			}else {
				return null;
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}	
	public ClienteCompletoWrapper traerClienteByNumeroOperacion(String numeroOperacion, String autorizacion) throws RelativeException {
		try {
			DetalleCreditoWrapper detalle = this.traerCreditoVigente(numeroOperacion,autorizacion);
			if(detalle != null) {
				ClienteCompletoWrapper wp = traerCliente( detalle.getCliente().getIdentificacion(), null , autorizacion);
				if(wp != null && wp.getCliente() != null) {
					wp.setTotalAvaluo(wp.getCliente().getActivos());
				}
				return wp;
			}else {
				log.info("=================> No traje nada? <==================");
				return null;
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
	}
	public ClienteCompletoWrapper traerCliente( String cedula, String codigoBpm, String autorizacion) throws RelativeException{
		try {
			ClienteCompletoWrapper wr = this.mapearClienteCompleto( this.findClienteSoftbank(  cedula , autorizacion ) );
			if( wr == null) {
				wr = new ClienteCompletoWrapper( this.findClienteByIdentifiacion( cedula ) );
				wr.setIsSoftbank( false );
				wr.setCodigoBpm( codigoBpm );
				if(wr.getExisteError()) {
					return wr;
				}
				setDatosCliente(cedula, wr);
				return wr;
			}else {
				wr.setIsSoftbank( true );
				wr.setCodigoBpm( codigoBpm );
				return wr;
			}
		}catch(RelativeException e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM , e.getMensaje());
		}
	}

	/**
	 * SETEA LAS RELACIONES DE TBQOCLIENTE EN UN SOLO WRAPPER
	 * @param cedula
	 * @param wr
	 * @throws RelativeException
	 */
	private void setDatosCliente(String cedula, ClienteCompletoWrapper wr) throws RelativeException {
		wr.setDirecciones( this.direccionClienteRepository.findByIdCliente( wr.getCliente().getId() ) );
		wr.setReferencias( this.referenciaPersonalRepository.findByIdCliente( wr.getCliente().getId() ) );
		wr.setDatosTrabajos( this.datoTrabajoClienteRepository.findByIdCliente( wr.getCliente().getId() ) );
		/*
		 * TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente(); String
		 * codigoCuentaMupi =
		 * parametroRepository.findByNombre(QuskiOroConstantes.CODIGO_BANCO_MUPI).
		 * getValor(); List<TbQoCuentaBancariaCliente> listCreate = new ArrayList<>();
		 * CuentaWrapper cuentaWS = consultaCuentaApiGateWay(cedula);
		 * cuenta.setBanco(Long.valueOf(codigoCuentaMupi));
		 * cuenta.setCuenta(cuentaWS.getNumeroCuenta());
		 * cuenta.setEsAhorros(cuentaWS.getTipoCuenta().equalsIgnoreCase("AH"));
		 * cuenta.setEstado(EstadoEnum.ACT); cuenta.setTbQoCliente( wr.getCliente() );
		 * cuenta.setEsNueva(cuentaWS.getCuentaNueva().equalsIgnoreCase("S"));
		 * listCreate.add( cuenta ); wr.setCuentas( listCreate );
		 */
		wr.setTelefonos( this.telefonoClienteRepository.findByIdCliente( wr.getCliente().getId() ) );
	}
	private ClienteCompletoWrapper mapearClienteCompleto( SoftbankClienteWrapper s) throws RelativeException {
		if (s == null) {return null;}
		TbQoCliente  cliente = this.mapearCliente( s );
		List<TbQoTelefonoCliente> telefonos = this.telefonoClienteRepository.findAllByIdCliente(cliente.getId());
		List<TbQoDireccionCliente> direcciones = this.direccionClienteRepository.findAllByIdCliente(cliente.getId());
		List<TbQoReferenciaPersonal> referencias;
		List<TbQoDatoTrabajoCliente> trabajos;
		List<TbQoCuentaBancariaCliente> cuentas;
		if(s.getTelefonos() != null && !s.getTelefonos().isEmpty()) {
			telefonos =  this.mapearTelefonos(   s.getTelefonos(), telefonos ); 
		}
		if(s.getDirecciones() != null && !s.getDirecciones().isEmpty()) {
			direcciones =  this.mapearDirecciones(   s.getDirecciones(), direcciones ); 
		}
		if(s.getContactosCliente() != null && !s.getContactosCliente().isEmpty()) {
			referencias =  this.mapearReferencias(   s.getContactosCliente() ); 
		}else {
			referencias = this.referenciaPersonalRepository.findAllByIdCliente(cliente.getId());
		}
		if(s.getDatosTrabajoCliente() != null && !s.getDatosTrabajoCliente().isEmpty()) {
			trabajos =  this.mapearTrabajo(   s.getDatosTrabajoCliente() ); 
		}else {
			trabajos = this.datoTrabajoClienteRepository.findAllByIdCliente(cliente.getId());
		}
		if(s.getCuentasBancariasCliente() != null && !s.getCuentasBancariasCliente().isEmpty()) {
			cuentas =  this.mapearCuentas(   s.getCuentasBancariasCliente(), cliente ); 
		}else {
			cuentas = this.cuentaBancariaRepository.findByIdCliente(cliente.getId());
		}
		
		return new ClienteCompletoWrapper(cliente, direcciones, referencias, telefonos,  trabajos, cuentas);
	}
	private List<TbQoDatoTrabajoCliente> mapearTrabajo(List<SoftbankDatosTrabajoWrapper> datosTrabajoCliente ) {
		List<TbQoDatoTrabajoCliente> listCreate = new ArrayList<>();
		datosTrabajoCliente.forEach(e->{
			TbQoDatoTrabajoCliente send = new TbQoDatoTrabajoCliente();
			send.setIdSoftbank( e.getId() );
			send.setActividadEconomica( e.getIdActividadEconomica() );
			send.setActividadEconomicaMupi( e.getCodigoActividadEconomicaClienteMupi() );
			send.setEsRelacionDependencia( e.getEsRelacionDependencia() );
			send.setOrigenIngreso( e.getCodigoOrigenIngreso() );
			send.setOcupacion( e.getCodigoOcupacion() );
			send.setNombreEmpresa( e.getNombreEmpresa() );
			send.setCargo( e.getCodigoCargo() );
			send.setEsprincipal( e.getEsPrincipal() );
			send.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			listCreate.add( send );
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoCuentaBancariaCliente> mapearCuentas(List<SoftbankCuentasBancariasWrapper> cuentaSoft, TbQoCliente cliente ) throws RelativeException {
		List<TbQoCuentaBancariaCliente> listCreate = new ArrayList<>();
		/*
		 * String idCuentaMupi =
		 * parametroRepository.findByNombre(QuskiOroConstantes.CODIGO_BANCO_MUPI).
		 * getValor(); CuentaWrapper cuentaWS =
		 * consultaCuentaApiGateWay(cliente.getCedulaCliente());
		 */
		cuentaSoft.forEach( c ->{
			TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente();
			cuenta.setEstado( c.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA);
			cuenta.setBanco(c.getIdBanco());
			cuenta.setCuenta(c.getCuenta());
			cuenta.setEsAhorros(c.getEsAhorros());
			cuenta.setIdSoftbank( c.getId() );
			cuenta.setEsNueva(c.getEsNueva());
			listCreate.add( cuenta );				
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoReferenciaPersonal> mapearReferencias(List<SoftbankContactosWrapper> contactosCliente ) {
		List<TbQoReferenciaPersonal> listCreate= new ArrayList<>();
		contactosCliente.forEach(e->{
			TbQoReferenciaPersonal referencia = new TbQoReferenciaPersonal();
			referencia.setDireccion( e.getDireccion() );
			referencia.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			referencia.setNombres( e.getNombres() );
			referencia.setApellidos( e.getApellidos() );
			referencia.setParentesco( e.getCodigoTipoReferencia() );
			referencia.setIdSoftbank( e.getId() );
			if(e.getTelefonos() != null ) {
				e.getTelefonos().forEach(t ->{
					if( t.getCodigoTipoTelefono().equalsIgnoreCase("DOM") ){
						referencia.setTelefonoFijo( t.getNumero() );
					}
					if( t.getCodigoTipoTelefono().equalsIgnoreCase("CEL") ) {
						referencia.setTelefonoMovil( t.getNumero() );
					}
				});									
			}
			listCreate.add( referencia );
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoTelefonoCliente> mapearTelefonos(List<SoftbankTelefonosWrapper> telefonos, List<TbQoTelefonoCliente> telefonos2 ) {
		
		List<TbQoTelefonoCliente> listCreate = new ArrayList<>();
		TbQoTelefonoCliente celular = telefonos2.stream().filter( var -> "CEL".equals(var.getTipoTelefono())).findAny().orElse( null);

		TbQoTelefonoCliente domicilio = telefonos2.stream().filter( var -> "DOM".equals(var.getTipoTelefono())).findAny().orElse( null);
		telefonos.forEach(e ->{
			TbQoTelefonoCliente tele = new TbQoTelefonoCliente();
			tele.setEstado( EstadoEnum.ACT );
			tele.setIdSoftbank( e.getId() );
			tele.setNumero( e.getNumero() );
			tele.setTipoTelefono( e.getCodigoTipoTelefono() );
			tele.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			if( e.getCodigoTipoTelefono().equals("CEL") && celular != null ) {
				tele.setNumero(celular.getNumero());
			}
			if( e.getCodigoTipoTelefono().equals("DOM") && domicilio != null ) {
				tele.setNumero(domicilio.getNumero());
			}
			
			listCreate.add( tele );	
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoDireccionCliente> mapearDirecciones(List<SoftbankDireccionWrapper> direcciones, List<TbQoDireccionCliente> direcciones2) {
		List<TbQoDireccionCliente> listCreate = new ArrayList<>();
		direcciones.forEach( e ->{
			TbQoDireccionCliente direccion = new TbQoDireccionCliente();
			direccion.setCallePrincipal( e.getCallePrincipal() );
			direccion.setCalleSegundaria( e.getCalleSecundaria() );
			direccion.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			direccion.setDireccionEnvioCorrespondencia( e.getEsDireccionEnvio() );
			direccion.setDireccionLegal( e.getEsDireccionLegal() );
			direccion.setNumeracion( e.getNumero() );
			direccion.setReferenciaUbicacion( e.getReferencia() );
			direccion.setBarrio( e.getBarrio() );
			direccion.setDivisionPolitica( e.getIdUbicacion() );
			direccion.setSector( e.getCodigoSectorVivienda() );
			direccion.setTipoDireccion( e.getCodigoTipoDireccion() );
			direccion.setTipoVivienda( e.getCodigoVivienda() );
			direccion.setIdSoftbank( e.getId() );
			listCreate.add( direccion );				
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private TbQoCliente mapearCliente(SoftbankClienteWrapper s) throws RelativeException {
		try {
			TbQoCliente cliente = this.clienteRepository.findClienteByIdentificacion( s.getIdentificacion() );
			if(cliente == null) {
				cliente = new TbQoCliente();
			}
			if( StringUtils.isBlank(cliente.getCedulaCliente())) {
				cliente.setCedulaCliente(s.getIdentificacion());
			}
			cliente.setActividadEconomica( s.getActividadEconomica().getIdActividadEconomica().toString() );
			cliente.setApellidoMaterno( s.getSegundoApellido() );
			cliente.setApellidoPaterno( s.getPrimerApellido() );
			cliente.setAgencia( s.getIdAgencia() );
			cliente.setUsuario( s.getCodigoUsuarioAsesor() );
			cliente.setNombreCompleto( s.getNombreCompleto() );
			cliente.setCanalContacto( s.getCodigoMotivoVisita() );
			cliente.setCargasFamiliares( s.getNumeroCargasFamiliares() );
			if( s.getFechaNacimiento() != null && cliente.getFechaNacimiento() == null) {
				cliente.setFechaNacimiento(QuskiOroUtil.formatSringToDate(s.getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));
			}
			if(StringUtils.isBlank(cliente.getEmail())) {
				cliente.setEmail( s.getEmail() );
			}
				cliente.setLugarNacimiento( s.getIdLugarNacimiento().toString() );
				if(StringUtils.isBlank(cliente.getLugarNacimiento())) {
			}
			cliente.setEstadoCivil( s.getCodigoEstadoCivil() );
			cliente.setGenero( s.getCodigoSexo() );
			if(cliente.getNacionalidad() == null) {
				cliente.setNacionalidad( s.getIdPaisNacimiento() );
			}
			cliente.setNivelEducacion( s.getCodigoEducacion() );
			cliente.setPrimerNombre( s.getPrimerNombre() );
			cliente.setSegundoNombre( s.getSegundoNombre() );
			cliente.setProfesion( s.getCodigoProfesion() );
			cliente.setIngresos( s.getIngresos() );
			cliente.setEgresos( s.getEgresos() );
			cliente.setPasivos( s.getPasivos() );
			cliente.setActivos( s.getActivos() );
			return cliente;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;

		}
		
	}
	public CreacionClienteRespuestaCoreWp registrarCliente(ClienteCompletoWrapper wp, String autorizacion) throws RelativeException {
		TbQoCliente cliente =  guardarClienteLocal( wp ) ;
		try {			
			CreacionClienteRespuestaCoreWp rp = new CreacionClienteRespuestaCoreWp();
			wp.setCliente( cliente ); 
			if (this.findClienteSoftbank(cliente.getCedulaCliente(), autorizacion) == null) {
				this.crearClienteSoftbank( this.clienteToClienteSoftbank( wp ), autorizacion );
			}else {
				this.editarClienteSoftbank( this.clienteToClienteSoftbank( wp ), autorizacion );
				
			}
			SoftbankClienteWrapper softCliente = this.findClienteSoftbank(  wp.getCliente().getCedulaCliente()  , autorizacion);
			if(softCliente != null) {
				this.guardarTrabajoCliente(softCliente, cliente);
				this.guardarDirecciones(softCliente, cliente);
				this.guardarReferencias(softCliente, cliente);
				this.guardarTelefonos(softCliente, cliente);
				this.guardarCuentas(softCliente, cliente);
				rp.setIsCore(true);
			}else {
				rp.setIsCore(false);
			}
				return rp;
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_REGISTRO + e.getMessage());
		}

	}
	
	/**
	 * GUARDA LOS DATOS DEL CLIENTE Y TODOS LOS DATOS RELACIONACIONADOS
	 * @param wp
	 * @return
	 * @throws RelativeException
	 */
	public TbQoCliente guardarClienteLocal(ClienteCompletoWrapper wp)throws RelativeException {
		try {
			TbQoCliente cliente = this.manageCliente(wp.getCliente());
			/*
			 * if(wp.getCuentas() != null && !wp.getCuentas().isEmpty()) {
			 * this.cuentaBancariaRepository.deleteAllByIdCliente(cliente.getId()); for
			 * (TbQoCuentaBancariaCliente cb :wp.getCuentas()) { cb.setId(null);
			 * cb.setTbQoCliente(cliente); this.manageCuentaBancariaCliente(cb); } }
			 * if(wp.getDatosTrabajos() != null && !wp.getDatosTrabajos().isEmpty()) {
			 * this.datoTrabajoClienteRepository.deleteAllByIdCliente(cliente.getId());
			 * for(TbQoDatoTrabajoCliente dt : wp.getDatosTrabajos()){ dt.setId(null);
			 * dt.setTbQoCliente(cliente); this.manageDatoTrabajoCliente(dt); } }
			 * if(wp.getDirecciones() != null && !wp.getDirecciones().isEmpty()) {
			 * this.direccionClienteRepository.deleteAllByIdCliente(cliente.getId());
			 * for(TbQoDireccionCliente dir :wp.getDirecciones() ) { dir.setId(null);
			 * dir.setTbQoCliente(cliente); this.manageDireccionCliente(dir); } }
			 * if(wp.getReferencias() != null && !wp.getReferencias().isEmpty()) {
			 * this.referenciaPersonalRepository.deleteAllByIdCliente(cliente.getId());
			 * for(TbQoReferenciaPersonal ref : wp.getReferencias()) { ref.setId(null);
			 * ref.setTbQoCliente(cliente); this.manageReferenciaPersonal(ref); } }
			 * if(wp.getTelefonos() != null && !wp.getTelefonos().isEmpty()) {
			 * this.telefonoClienteRepository.deleteAllByIdCliente(cliente.getId());
			 * for(TbQoTelefonoCliente tel : wp.getTelefonos()) { tel.setId(null);
			 * tel.setTbQoCliente(cliente); this.manageTelefonoCliente(tel); } }
			 */
			return cliente;
		}catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL GUARDAR CLIENTE EN BASE LOCAL: " + e.getMessage());
		}
		
	}

	private void guardarTrabajoCliente(SoftbankClienteWrapper sw, TbQoCliente cliente) throws RelativeException {
		this.datoTrabajoClienteRepository.deleteAllByIdCliente( cliente.getId() );
		for(SoftbankDatosTrabajoWrapper e: sw.getDatosTrabajoCliente()) {
			TbQoDatoTrabajoCliente trabajo = new TbQoDatoTrabajoCliente();
			trabajo.setActividadEconomica( e.getIdActividadEconomica() );
			trabajo.setActividadEconomicaMupi( e.getCodigoActividadEconomicaClienteMupi() ) ;
			trabajo.setTbQoCliente( cliente ) ;
			trabajo.setNombreEmpresa( e.getNombreEmpresa() );
			trabajo.setIdSoftbank( e.getId() ) ;
			trabajo.setCargo(e.getCodigoCargo()) ;
			trabajo.setNombreEmpresa( e.getNombreEmpresa() ) ;
			trabajo.setEsRelacionDependencia( e.getEsRelacionDependencia() ) ;
			trabajo.setEsprincipal( e.getEsPrincipal() ) ;
			trabajo.setOcupacion( e.getCodigoOcupacion() );
			trabajo.setOrigenIngreso( e.getCodigoOrigenIngreso() ) ;
			trabajo.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA ) ;		
			this.manageDatoTrabajoCliente( trabajo );
		}
		
	}
	private void guardarDirecciones(SoftbankClienteWrapper sw, TbQoCliente cliente) throws RelativeException {
		this.direccionClienteRepository.deleteAllByIdCliente( cliente.getId() );
		for(SoftbankDireccionWrapper e: sw.getDirecciones()) {
			TbQoDireccionCliente direccion = new TbQoDireccionCliente();
			direccion.setCallePrincipal( e.getCallePrincipal() );
			direccion.setBarrio( e.getBarrio() );
			direccion.setCalleSegundaria( e.getCalleSecundaria() );
			direccion.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			direccion.setDireccionEnvioCorrespondencia( e.getEsDireccionEnvio() );
			direccion.setDireccionLegal( e.getEsDireccionLegal() );
			direccion.setNumeracion( e.getNumero() );
			direccion.setReferenciaUbicacion( e.getReferencia() );
			direccion.setDivisionPolitica( e.getIdUbicacion() );
			direccion.setSector( e.getCodigoSectorVivienda() );
			direccion.setTipoDireccion( e.getCodigoTipoDireccion() );
			direccion.setTipoVivienda( e.getCodigoVivienda() );
			direccion.setIdSoftbank( e.getId() );
			direccion.setTbQoCliente( cliente );					
			this.manageDireccionCliente( direccion );
		}
	}
	private void guardarReferencias(SoftbankClienteWrapper sw, TbQoCliente cliente)  throws RelativeException {
		this.referenciaPersonalRepository.deleteAllByIdCliente( cliente.getId() );
		for(SoftbankContactosWrapper e: sw.getContactosCliente()) {
			TbQoReferenciaPersonal referencia = new TbQoReferenciaPersonal();
			referencia.setDireccion( e.getDireccion() );
			referencia.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			referencia.setNombres( e.getNombres() );
			referencia.setApellidos( e.getApellidos() );
			referencia.setParentesco( e.getCodigoTipoReferencia() );
			referencia.setIdSoftbank( e.getId() );
			referencia.setTbQoCliente(cliente);
			if(e.getTelefonos() != null ) {
				e.getTelefonos().forEach(t ->{
					if( t.getCodigoTipoTelefono().equalsIgnoreCase("DOM") ){
						referencia.setTelefonoFijo( t.getNumero() );
					}
					if( t.getCodigoTipoTelefono().equalsIgnoreCase("CEL") ) {
						referencia.setTelefonoMovil( t.getNumero() );
					}
				});									
			}
			this.manageReferenciaPersonal( referencia );				
		}
	}
	private void guardarTelefonos(SoftbankClienteWrapper sw, TbQoCliente cliente) throws RelativeException {
		this.telefonoClienteRepository.deleteAllByIdCliente( cliente.getId() );
		for(SoftbankTelefonosWrapper e: sw.getTelefonos() ) {
			TbQoTelefonoCliente tele = new TbQoTelefonoCliente();
			tele.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			tele.setIdSoftbank( e.getId() );
			tele.setNumero( e.getNumero() );
			tele.setTipoTelefono( e.getCodigoTipoTelefono() );
			tele.setTbQoCliente(cliente);					
			this.manageTelefonoCliente( tele );
			
		}
	}
	private void guardarCuentas(SoftbankClienteWrapper sw, TbQoCliente cliente) throws RelativeException {
		this.cuentaBancariaRepository.deleteAllByIdCliente( cliente.getId() );
		for(SoftbankCuentasBancariasWrapper e: sw.getCuentasBancariasCliente() ) {
			TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente();
			cuenta.setIdSoftbank( e.getId() );
			cuenta.setBanco( e.getIdBanco() );
			cuenta.setEsAhorros( e.getEsAhorros() );
			cuenta.setCuenta( e.getCuenta() );
			cuenta.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			cuenta.setEsNueva(e.getEsNueva());
			cuenta.setTbQoCliente( cliente );
			this.manageCuentaBancariaCliente( cuenta ); 
		}		
	}
	
	
	
	
	private NegociacionWrapper generarTablasIniciales(TbQoProceso proceso, TbQoCreditoNegociacion credito, TbQoCliente cliente, String asesor, Long idAgencia, Informacion data, String autorizacion, String nombreAgencia, String telefonoAsesor) throws RelativeException {
		try {
			
			this.guardarProspectoCrm(cliente, autorizacion);
			if (credito != null) {
				NegociacionWrapper wrapper = new NegociacionWrapper();
				
				List<TbQoRiesgoAcumulado> riesgosTb = this.manageListRiesgoAcumulados( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank(cliente.getCedulaCliente(),autorizacion), credito.getTbQoNegociacion(), null ));
				wrapper.setRiesgos( riesgosTb );
				wrapper.setCredito(credito);
				wrapper.setVariables(this.createVariablesFromEquifax(data.getXmlVariablesInternas().getVariablesInternas().getVariable(), credito.getTbQoNegociacion(), null));
				//traer excepcion 
				wrapper.setCodigoExcepcionBre(new Long(data.getCodigoError()) );
				if(data.getCodigoError()== 3 || data.getCodigoError()== 1) {
					wrapper.setExcepcionBre(data.getMensaje());
				}
				if(data.getCodigoError()== 1) {
					proceso.setEstadoProceso(EstadoProcesoEnum.CADUCADO);
					proceso = this.manageProceso(proceso);
				}
				credito.getTbQoNegociacion().setCodigoBre(String.valueOf(data.getCodigoError()));
				credito.getTbQoNegociacion().setMensajeBre(data.getMensaje());
				this.manageNegociacion(credito.getTbQoNegociacion());
				wrapper.setRespuesta(true);
				wrapper.setProceso( proceso );
				wrapper.setTelefonoDomicilio(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(), "DOM"));
				wrapper.setTelefonoMovil(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(), "CEL"));
				//wrapper.setTipoOro(this.tipoOro(cliente));
				try {
					//this.guardarProspectoCrm(cliente);
				} catch (Exception e) {
					
					e.printStackTrace();
					log.info("===============>>>> ERROR AL INTENTAR GUARDAR EN EL CRM <<<<==================");
				}
				return wrapper;
			} else {
				return new NegociacionWrapper(false);
			}

		} catch (RelativeException e) {
			throw e;
		}

	}
	private CotizacionWrapper generarTablasInicialesCotizacion(TbQoCliente cliente, String asesor, Long idAgencia, Informacion data, String autorizacion) throws RelativeException {
		try {
			TbQoCotizador cot = this.createGestionCotizacion(cliente, asesor, idAgencia);
			if (cot == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE, " AL GENERAR TODAS LAS TABLAS INICIALES.");			
			}
			this.guardarProspectoCrm(cliente, autorizacion);
			CotizacionWrapper wrapper = new CotizacionWrapper();
			List<SoftbankOperacionWrapper> riesgos = consultarRiesgoSoftbank(cliente.getCedulaCliente(), autorizacion);
			wrapper.setCotizacion(cot);
			List<TbQoRiesgoAcumulado> riesgosTb = this.createRiesgoFrontSoftBank(riesgos, null, cot );
			wrapper.setRiesgos( riesgosTb != null ? this.manageListRiesgoAcumulados( riesgosTb) : null );
			wrapper.setVariables(this.createVariablesFromEquifax(data.getXmlVariablesInternas().getVariablesInternas().getVariable(), null, cot));
			if(data.getCodigoError()== 3) {
				wrapper.setExcepcionBre(data.getMensaje());
			}
			wrapper.setTelefonoDomicilio(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(), "DOM"));
			wrapper.setTelefonoMovil(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(), "CEL"));
			return wrapper;
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}
	private TbQoCotizador createGestionCotizacion(TbQoCliente cliente, String asesor, Long idAgencia) throws RelativeException { 
		try {
			if (cliente == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " EL CLIENTE NO EXISTE A LA HORA DE CREAR EL CREDITO.");				
			}
			TbQoCotizador cotizacion = new TbQoCotizador();
			cotizacion.setAsesor(asesor);
			cotizacion.setTbQoCliente(cliente);
			cotizacion.setIdAgencia(idAgencia);
			return this.manageCotizador(cotizacion);		
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
		}

	}
	private TbQoCreditoNegociacion createCredito(TbQoCliente cl, String asesor, Long idAgencia, String nombreAgencia, String telefonoAsesor) throws RelativeException { 
		try {
			if (cl != null) {
				TbQoNegociacion ng = new TbQoNegociacion();
				ng.setAsesor(asesor);
				ng.setTbQoCliente(cl);
				ng.setTelefonoAsesor(telefonoAsesor);
				TbQoNegociacion negociacion = this.manageNegociacion(ng);
				if (negociacion != null) {
					TbQoCreditoNegociacion cr = new TbQoCreditoNegociacion();
					cr.setTbQoNegociacion(negociacion);
					cr.setIdAgencia(idAgencia);
					cr.setNombreAgencia(nombreAgencia);
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
	/** ********************************************* @CRM ************ */
	public CrmProspectoCortoWrapper findProspectoCrm(String cedula, String autorizacion) throws RelativeException {
		try {
			CrmProspectoCortoWrapper prospecto = CrmApiClient
					.callConsultaProspectoRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_CRM_PROSPECTO_CORTO).getValor(),cedula, autorizacion);
			if (prospecto != null) {
				return prospecto;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public CrmProspectoWrapper guardarProspectoCrm(TbQoCliente cliente, String autorizacion) throws RelativeException {
		try {
			CrmEntidadWrapper entidad = new CrmEntidadWrapper();
			entidad.setCedulaC(cliente.getCedulaCliente());
			entidad.setEmailAddress(cliente.getEmail());
			entidad.setEmailAddressCaps(StringUtils.isNotBlank(cliente.getEmail())?cliente.getEmail().toUpperCase():null);
			entidad.setFirstName(cliente.getNombreCompleto());
			entidad.setLeadSourceDescription("GESTION BPM QUSKI");
			TbQoTelefonoCliente tlfMovil = this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(),"CEL");
			if(tlfMovil != null ) {
				entidad.setPhoneMobile( tlfMovil.getNumero() );				
			}
			TbQoTelefonoCliente tlfCasa = this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(),"DOM");
			if(tlfCasa != null ) {
				entidad.setPhoneMobile( tlfCasa.getNumero() );				
			}
			CrmGuardarProspectoWrapper tmp = new CrmGuardarProspectoWrapper(entidad);
			CrmProspectoWrapper pro = CrmApiClient.callPersistProspectoRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_CRM_PERSIST).getValor(),tmp, autorizacion);
			log.info( " GUARDANDO EN CRM =====================================> " + cliente.getCedulaCliente() );
			return pro;
		} catch (RelativeException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}


	/**
	 * * * * * * * * * * * @HABILITANTES
	 */
	public AutorizacionBuroWrapper setAutorizacionBuroWrapper(String identificacionCliente, String nombreCliente, String ciudad, String codigo)
			throws RelativeException {

		AutorizacionBuroWrapper autorizacion = new AutorizacionBuroWrapper();
		autorizacion.setCedulaCliente(identificacionCliente);
		autorizacion.setNombreCliente(nombreCliente);
		autorizacion.setFechaActual(QuskiOroUtil.dateToFullString(new Date()));
		autorizacion.setCiudad(ciudad);
		autorizacion.setCodigo(codigo);
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
			ProcessEnum proceso, String referencia) throws RelativeException {
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
			throw e;
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
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return documentoHabilitanteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
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
			throw e;
		}
	}

	public TbQoTipoDocumento findTipoDocumentoById(Long id) throws RelativeException {
		try {
			return tipoDocumentoRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
		}
	}

	public Long countdocumento() throws RelativeException {
		try {
			return tipoDocumentoRepository.countAll(TbQoTipoDocumento.class);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
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
			dhs = this.findDocumentoHabilitanteByTipoDocumentoAndIdentificacionCliente(fw.getRelatedIdStr(), null, null,
					Long.valueOf(fw.getTypeAction()));
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
	
	public TbQoDocumentoHabilitante generateDocumentoHabilitanteBuro(FileWrapper fw) throws RelativeException {
		
		return null;
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
			throw e;
		}
	}
	public TbQoCreditoNegociacion findCreditoByIdNegociacion(Long idNego) throws RelativeException {
		try {
			return creditoNegociacionRepository.findCreditoByIdNegociacion(idNego);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
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
			throw e;
		}
	}

	public Long countReferenciaPersonal() throws RelativeException {
		try {
			return referenciaPersonalRepository.countAll(TbQoReferenciaPersonal.class);
		} catch (RelativeException e) {
			throw e;
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
				send.setEstado( send.getEstado() != null ? send.getEstado() : EstadoEnum.ACT );
				return referenciaPersonalRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		}catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
				return this.trackingRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
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
				persisted.setTiempoTranscurrido(send.getFechaFin().getTime() - send.getFechaInicio().getTime() );
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
		}
	}

	public Long countParametros() throws RelativeException {
		try {
			return parametroRepository.countAll(TbMiParametro.class);
		} catch (RelativeException e) {
			throw e;
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
		}
	}
	/**
	 * ************************** @DireccionCliente
	 */
	public List<TbQoDireccionCliente> finddireccionClienteByIdCliente(Long id) throws RelativeException {
		try {
			return direccionClienteRepository.findByIdCliente(id);
		} catch (RelativeException e) {
			throw e;
		}
	}
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
			throw e;
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
			throw e;
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
			if (send != null && send.getId() != null) {
				persisted = this.findDireccionClienteById(send.getId());
				return this.updateDireccionCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
				send.setEstado( send.getEstado() != null ? send.getEstado() : EstadoEnum.ACT );
				return this.direccionClienteRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}
	}

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
			throw e;
		}
	}

	public List<TbQoDireccionCliente> findDireccionClienteByIdClienteAndTipoDireccion(Long idC, String tipoDireccion)
			throws RelativeException {
		try {
			return direccionClienteRepository.findByIdClienteAndTipoDireccion(idC, tipoDireccion);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
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
			if(send == null || send.isEmpty()) {
				return null;
			}
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
					} else if (element.getTbQoCotizador() != null) {
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
			e.printStackTrace();
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			EstadoExcepcionEnum[] values = EstadoExcepcionEnum.values();
			for (int i = 0; i < values.length; i++) {
				EstadoExcepcionEnum estadoExcepcionEnum = values[i];
				if (estadoExcepcionEnum.equals(send.getEstadoExcepcion())) {
					persisted.setEstadoExcepcion(estadoExcepcionEnum);
				}
			}
			persisted.setCaracteristica(send.getCaracteristica());
			persisted.setIdAprobador(send.getIdAprobador());
			if( StringUtils.isNotBlank( send.getObservacionAprobador() )) {
				persisted.setObservacionAprobador(send.getObservacionAprobador());				
			}
			
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return this.excepcionesRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
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
			throw e;
		}
	}

	/**
	 * REGISTRA UNA EXCEPCION EN FUNCION DE UNA NEGOCIACION 
	 * @param excepcion
	 * @return
	 * @throws RelativeException 
	 */
	public TbQoExcepcion solicitarExcepcion(TbQoExcepcion excepcion, String autorizacion, String correoAsesor, String nombreAsesor) throws RelativeException {
		try {
			TbQoProceso proceso = this.findProcesoByIdReferencia(excepcion.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO);
			if(proceso == null ) {
				proceso = this.findProcesoByIdReferencia(excepcion.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION);
			}
			if(proceso == null || (!proceso.getEstadoProceso().equals(EstadoProcesoEnum.CREADO) && !proceso.getEstadoProceso().equals(EstadoProcesoEnum.DEVUELTO) && !proceso.getEstadoProceso().equals(EstadoProcesoEnum.EXCEPCIONADO)) ) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE SOLICITAR UNA EXCEPCION INTENTE MAS TARDE");
			}
			proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_EXCEPCION);
			proceso.setUsuario( QuskiOroConstantes.EN_COLA);
			excepcion.getTbQoNegociacion().setCorreoAsesor(correoAsesor);
			excepcion.getTbQoNegociacion().setNombreAsesor(nombreAsesor);
			manageNegociacion(excepcion.getTbQoNegociacion());
			manageProceso(proceso);
			this.excepcionesRepository.inactivarExcepcionByTipoExcepcionAndIdNegociacion(excepcion.getTipoExcepcion(), excepcion.getTbQoNegociacion().getId());
			this.mailExcepcion(excepcion, this.findCreditoByIdNegociacion(excepcion.getTbQoNegociacion().getId()),proceso, autorizacion);
			return this.manageExcepcion(excepcion);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, e.getMessage());
		}
	}
	

	public ArrayList<TbQoVariablesCrediticia>  actualizarVariables(List<TbQoVariablesCrediticia> list, Long idNego) throws RelativeException {
		try {
			if(idNego == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL ACTUALIZAR VARIABLES CREDITICIAS, NO SEPUEDE LEER ID." );
			}
			TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion( idNego );
			if(credito == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL ACTUALIZAR VARIABLES CREDITICIAS, NO SEPUEDE LEER ID." );
			}
			this.variablesCrediticiaRepository.deleteVariablesByNegociacionId(idNego);
			ArrayList<TbQoVariablesCrediticia> lisrtDos = new ArrayList<>();
			for(TbQoVariablesCrediticia e : list) {
				TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
				v.setCodigo(e.getCodigo());
				v.setNombre(e.getNombre());
				v.setOrden(String.valueOf(e.getOrden()));
				v.setValor(e.getValor());
				if(credito.getTbQoNegociacion() != null) {
					v.setTbQoNegociacion(credito.getTbQoNegociacion());						
				}
				lisrtDos.add(this.manageVariablesCrediticia(v));				
			}
			return lisrtDos;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ACCION_NO_ENCONTRADA + e.getMessage());
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			throw e;
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
			return this.tasacionRepository.findByIdCredito(idCreditoNegociacion, pw.getStartRecord(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.tasacionRepository.findByIdCredito(idCreditoNegociacion);
		}
	}

	public Long countTasacionByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException {
		return this.tasacionRepository.countFindByIdCredito(idCreditoNegociacion);
	}

	public void eliminarJoya(Long id) throws RelativeException {
		this.tasacionRepository.deleteTasacionById(id);
	}
	public List<TbQoTasacion> findTasacionByIdCredito(PaginatedWrapper pw, Long idCredito)
			throws RelativeException {
		try {
			if (pw != null && pw.getIsPaginated() != null
					&& pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.tasacionRepository.findByIdCredito(idCredito, pw.getStartRecord(), pw.getPageSize(),
						pw.getSortFields(), pw.getSortDirections());
			} else {
				return this.tasacionRepository.findByIdCredito(idCredito);
			}
		} catch (RelativeException e) {
			throw e;
		}
	}
	public Long countTasacionByByIdNegociacion(Long idNegociacion) throws RelativeException {
		try {
			return this.tasacionRepository.countFindByIdNegociacion(idNegociacion);
		} catch (RelativeException e) {
			throw e;
		}
	}



	/**
	 * 
	 * @param identificacion
	 * @return
	 * @throws RelativeException
	 */
	public SoftbankClienteWrapper findClienteSoftbank(String identificacion, String autorizacion) throws RelativeException {
		try {
			
			SoftbankClienteWrapper persisted = SoftBankApiClient.callConsultaClienteRest(this.parametroRepository
					.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_CLIENTE).getValor(),autorizacion,identificacion);
			if (StringUtils.isNotBlank( persisted.getIdentificacion() ) ) {
				return persisted;
			} else {
				return null;
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CUSTOM," ERROR AL LLAMAR METODO findClienteSoftbank. " + e.getMessage()  );
		}

	}
	public CatalogosSoftbankWrapper traerCatalogos( String autorizacion) throws RelativeException {
		try {
			String service;
			CatalogosSoftbankWrapper wp = new CatalogosSoftbankWrapper();
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_AGENCIA).getValor();
			wp.setCatAgencia( SoftBankApiClient.callCatalogoAgenciaRest( service, autorizacion ) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ACTIVIDAD_ECONOMICA).getValor();
			wp.setCatActividadEconomica( SoftBankApiClient.callCatalogoActividadEconomicaRest( service , autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_DIVISION_POLITICA).getValor();
			wp.setCatDivicionPolitica( SoftBankApiClient.callCatalogoDivicionPoliticaRest( service, autorizacion ) );
			
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_BANCO).getValor();
			wp.setCatBanco( SoftBankApiClient.callCatalogoConIdRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_PAIS).getValor();
			wp.setCatPais( SoftBankApiClient.callCatalogoConIdRest(service, autorizacion) );
			
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_PROFESION).getValor();
			wp.setCatProfesion( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_EDUCACION).getValor();
			wp.setCatEducacion( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ESTADO_CIVIL).getValor();
			wp.setCatEstadoCivil( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_SECTOR_VIVIENDA).getValor();
			wp.setCatSectorvivienda( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_VIVIENDA).getValor();
			wp.setCatTipoVivienda( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_REFERENCIA).getValor();
			wp.setCatTipoReferencia( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_DIRECCION).getValor();
			wp.setCatTipoDireccion( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_IMP_COM).getValor();
			wp.setCatImpCom( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_JOYA).getValor();
			wp.setCatTipoJoya( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ESTADO_JOYA).getValor();
			wp.setCatEstadoJoya( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_ORO).getValor();
			wp.setCatTipoOro( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ESTADO_PROCESO).getValor();
			wp.setCatEstadoProceso( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_FIRMANTE_OPERACION).getValor();
			wp.setCatFirmanteOperacion( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_MOTIVO_DEVOLUCION_APROBACION).getValor();
			wp.setCatMotivoDevolucionAprobacion( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ACTIVIDAD_ECONOMICA_MUPI).getValor();
			wp.setCatActividadEconomicaMupi( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_SEXO).getValor();
			wp.setCatSexo( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_FUNDA).getValor();
			wp.setCatTipoFunda( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_CARGO).getValor();
			wp.setCatCargo( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_OCUPACION).getValor();
			wp.setCatOcupacion( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ORIGEN_INGRESOS).getValor();
			wp.setCatOrigenIngreso( SoftBankApiClient.callCatalogoRest(service, autorizacion) );
			return wp;
			
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public List<SoftbankOperacionWrapper> consultarRiesgoSoftbank(String identificacion, String autorizacion) throws RelativeException {
		try {
			SoftbankConsultaWrapper consulta = new SoftbankConsultaWrapper(identificacion);
			SoftbankRiesgoWrapper persisted = SoftBankApiClient.callConsultaRiesgoRest(consulta,autorizacion,
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SOFTBANK_RIESGO_ACUMULADO).getValor());
			if (!persisted.getOperaciones().isEmpty()) {
				return persisted.getOperaciones();
			} else {
				return null;
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"RIESGO ACUMULADO" + e.getMessage());
		}
	}

	public List<CuotasAmortizacionWrapper> consultarTablaAmortizacion (String numeroOperacion, String urlHabilitantes, DatosRegistroWrapper registro, String autorizacion) throws RelativeException {
		try {
			ConsultaTablaWrapper entrada = new 	ConsultaTablaWrapper(numeroOperacion, urlHabilitantes != null ? urlHabilitantes : null, registro);
			SoftbankTablaAmortizacionWrapper persisted = SoftBankApiClient.callConsultaTablaAmortizacionRest(
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SOFTBANK_TABLA_AMORTIZACION).getValor(),autorizacion,entrada);
			if (!persisted.getCuotas().isEmpty()) {
				return persisted.getCuotas();
			} else {
				return null;
			}
		}catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	private List<CatalogoWrapper>  catalogoImpCom(String autorizacion) throws RelativeException {
		try {
			String service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_IMP_COM).getValor();
			return SoftBankApiClient.callCatalogoRest( service, autorizacion );
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}
	}
//	private List<CatalogoIdWrapper>  catalogoBanco() throws RelativeException {
//		try {
//			String service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_BANCO).getValor();
//			return SoftBankApiClient.callCatalogoConIdRest( service );
//		} catch (RelativeException e) {
//			e.printStackTrace();
//			throw new RelativeException(Constantes.ERROR_CODE_READ,
//					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
//		}
//	}
	private List<TbQoRiesgoAcumulado> createRiesgoFrontSoftBank(List<SoftbankOperacionWrapper> operaciones, TbQoNegociacion negociacion, TbQoCotizador cotizacion) throws RelativeException {
		if (operaciones != null) {
			List<TbQoRiesgoAcumulado> list = new ArrayList<>();
			operaciones.forEach(e -> {
				TbQoRiesgoAcumulado r = null;				
				if(negociacion != null) {
					r = new TbQoRiesgoAcumulado(negociacion);					
				}
				if(cotizacion != null) {
					r = new TbQoRiesgoAcumulado( cotizacion);
				}
				r.setBloqueo( e.getBloqueo() );
				r.setCapital( e.getCapital() );
				r.setCoberturaActual( e.getCoberturaActual() );
				r.setCoberturaInicial( e.getCoberturaInicial() );
				r.setCodigoCarteraQuski( e.getCodigoCarteraQuski() );
				r.setCustodia( e.getCustodia() );
				r.setDiasMoraActual( e.getDiasMoraActual() );
				r.setEsDemandada( e.getEsDemandada() );
				r.setEstado( EstadoEnum.ACT );
				r.setEstadoOperacion( e.getEstadoOperacion() );
				r.setEstadoPrimeraCuotaVigente( e.getEstadoPrimeraCuotaVigente() );
				r.setFechaCreacion( new Timestamp(System.currentTimeMillis()) );
				r.setFechaEfectiva( e.getFechaEfectiva() );
				r.setFechaVencimiento( e.getFechaVencimiento() );
				r.setGastosCobranza( e.getGastosCobranza() );
				r.setIdMoneda( e.getIdMoneda() );
				r.setIdPrestamoOrigen( e.getIdPrestamoOrigen() );
				r.setIdSoftbank( e.getId() );
				r.setInteresMora( e.getInteresMora() );
				r.setMontoFinanciado( e.getMontoFinanciado() );
				r.setMora( e.getMora() );
				r.setNombreProducto( e.getNombreProducto() );
				r.setNumeroCuotasFaltantes( e.getNumeroCuotasFaltantes() );
				r.setNumeroCuotasTotales( e.getNumeroCuotasTotales() );
				r.setNumeroGarantiasReales( e.getNumeroGarantiasReales() );
				r.setNumeroOperacion( e.getNumeroOperacion() );
				r.setNumeroOperacionMadre( e.getNumeroOperacionMadre() );
				r.setNumeroOperacionMupi( e.getNumeroOperacionMupi() );
				r.setPlazo( e.getPlazo() );
				r.setPrimeraCuotaVigente( e.getPrimeraCuotaVigente() );
				r.setReferencia( e.getReferencia() );
				r.setSaldo( e.getSaldo() );
				r.setTipoOperacion( e.getTipoOperacion() );
				r.setValorAlDia( e.getValorAlDia() );
				r.setValorAlDiaMasCuotaActual( e.getValorAlDiaMasCuotaActual() );
				r.setValorCancelaPrestamo( e.getValorCancelaPrestamo() );
				r.setValorProyectadoCuotaActual( e.getValorProyectadoCuotaActual() );
				r.setValorTotalPrestamoVencimiento(e.getValorTotalPrestamoVencimiento());
				r.setInteres(e.getInteres());
				list.add(r);
			});
			return list;
		} else {
			return null;
		}
	}

	private TbQoCliente clienteSoftToTbQoCliente(SoftbankClienteWrapper s) throws RelativeException {
		
			if (s == null) {
				return null;
			}
			TbQoCliente cliente = new TbQoCliente();
			cliente.setCedulaCliente(s.getIdentificacion());
			cliente.setActividadEconomica( s.getActividadEconomica().getIdActividadEconomica().toString() );
			cliente.setApellidoMaterno( s.getSegundoApellido() );
			cliente.setApellidoPaterno( s.getPrimerApellido() );
			cliente.setAgencia( s.getIdAgencia() );
			cliente.setUsuario( s.getCodigoUsuarioAsesor() );
			cliente.setNombreCompleto( s.getNombreCompleto() );
			cliente.setCargasFamiliares( s.getNumeroCargasFamiliares() );
			cliente.setFechaNacimiento(QuskiOroUtil.formatSringToDate(s.getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));
			if(cliente.getFechaNacimiento() != null ) {
				cliente.setEdad( Long.valueOf(QuskiOroUtil.calculateEdad( cliente.getFechaNacimiento() )));						
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
			cliente = this.manageCliente( cliente);		
			if( !s.getDirecciones().isEmpty() ) {
				this.direccionesSoftToDireccionesCore(s.getDirecciones(), cliente ); 
				}
			if( s.getTelefonos() != null && !s.getTelefonos().isEmpty() ) {
				this.tlfSoftToTlfCore(s.getTelefonos(), cliente);
				}
			if( s.getContactosCliente() != null && !s.getContactosCliente().isEmpty() ) {
				this.contactoToReferenciaPersonal(s.getContactosCliente(), cliente);	
				}
			if( s.getDatosTrabajoCliente() != null && !s.getDatosTrabajoCliente().isEmpty() ) { 
				this.trabajoSoftToTrabajoCore(s.getDatosTrabajoCliente(), cliente); 
				}
			if( s.getCuentasBancariasCliente() != null && !s.getCuentasBancariasCliente().isEmpty() ) {
				this.cuentasSoftToCuentasCore(s.getCuentasBancariasCliente(), cliente); 
				}
			return cliente;		
	}
	
	private void cuentasSoftToCuentasCore(List<SoftbankCuentasBancariasWrapper> list, TbQoCliente cliente) throws RelativeException {
		try {
			List<TbQoCuentaBancariaCliente> listUpdate = this.cuentaBancariaRepository.findByAllIdCliente(cliente.getId() );
			List<TbQoCuentaBancariaCliente> listCreate = new ArrayList<>();

			list.forEach(e ->{
				if(listUpdate != null) {
					listUpdate.forEach(l ->{
						if( e.getCuenta().equals( l.getCuenta() ) ) {
							log.info( "EL CAMPO DE SI ES ACTIVO O NO PARTE 1 (BOOLEANO DE SOFTBANK) ======> " +  e.getActivo() );
							l.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
							log.info( "EL CAMPO DE SI ES ACTIVO O NO PARTE 2 (MI NUEVO CAMPO DE ESTADO) ======> " +  l.getEstado().toString());
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
			throw e;
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
							l.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA ); 
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
					direccion.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
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
			throw e;
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
									if( t.getCodigoTipoTelefono().equalsIgnoreCase("DOM") ){
										l.setTelefonoFijo( t.getNumero() );
									}
									if( t.getCodigoTipoTelefono().equalsIgnoreCase("CEL") ) {
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
							if( t.getCodigoTipoTelefono().equalsIgnoreCase("DOM") ){
								referencia.setTelefonoFijo( t.getNumero() );
							}
							if( t.getCodigoTipoTelefono().equalsIgnoreCase("CEL") ) {
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
			throw e;
		}
	}

	private void trabajoSoftToTrabajoCore(List<SoftbankDatosTrabajoWrapper> list, TbQoCliente cliente) throws RelativeException {
		try {
			List<TbQoDatoTrabajoCliente> dato = this.datoTrabajoClienteRepository.findByIdCliente( cliente.getId() );
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
			throw e;
		}
	}
	
	private SoftbankClienteWrapper clienteToClienteSoftbank(ClienteCompletoWrapper wp) throws RelativeException {
		try {
			TbQoCliente cli = wp.getCliente();
			SoftbankClienteWrapper sof = new SoftbankClienteWrapper(  );
			sof.setIdentificacion(wp.getCliente().getCedulaCliente());
			sof.setIdTipoIdentificacion(Integer.valueOf("1"));
			sof.setNombreCompleto( cli.getNombreCompleto() );                         
			sof.setPrimerApellido( cli.getApellidoPaterno() );                           
			sof.setSegundoApellido( StringUtils.isBlank(cli.getApellidoMaterno())?" ":cli.getApellidoMaterno()  );              
			sof.setPrimerNombre( cli.getPrimerNombre() );             
			sof.setSegundoNombre( StringUtils.isBlank(cli.getSegundoNombre())?" ":cli.getSegundoNombre()  );      
			sof.setCodigoMotivoVisita(wp.getCliente().getCanalContacto());
			sof.setEsCliente( true );                                        
			sof.setFechaIngreso(QuskiOroUtil.dateToString(cli.getFechaActualizacion(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));                                  
			sof.setIdPaisNacimiento( cli.getNacionalidad() );                            
			sof.setIdPais( cli.getNacionalidad() );                                      
			sof.setIdLugarNacimiento( Long.valueOf( cli.getLugarNacimiento() ) ); 
			sof.setFechaNacimiento(QuskiOroUtil.dateToString(cli.getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));                            
			sof.setCodigoSexo( cli.getGenero() );                               
			sof.setCodigoProfesion( cli.getProfesion() );                          
			sof.setCodigoEstadoCivil( cli.getEstadoCivil() );                       
			sof.setCodigoEducacion( cli.getNivelEducacion() );                          
			sof.setNumeroCargasFamiliares( cli.getCargasFamiliares() );                  
			sof.setEmail( cli.getEmail() );                                  
			sof.setCodigoUsuario( cli.getUsuario() );                           
			sof.setCodigoUsuarioAsesor( cli.getUsuario() );
			sof.setEgresos( cli.getEgresos() );					
			sof.setIngresos( cli.getIngresos() );                                   
			sof.setPasivos( cli.getPasivos() );                                   
			sof.setActivos( cli.getActivos() ); 
			sof.setIdAgencia( cli.getAgencia() );
			sof.setActividadEconomica( new SoftbankActividadEconomicaWrapper(  Long.valueOf(cli.getActividadEconomica()) ) );			
			if(wp.getDirecciones() != null) {
				List<SoftbankDireccionWrapper> direcciones = new ArrayList<>();
				wp.getDirecciones().forEach(e->{
					SoftbankDireccionWrapper direccionSof = new SoftbankDireccionWrapper();
					direccionSof.setId( e.getIdSoftbank() );
					direccionSof.setCodigoTipoDireccion( e.getTipoDireccion() ); 
					direccionSof.setCodigoVivienda( e.getTipoVivienda() );
					direccionSof.setCodigoSectorVivienda( e.getSector() );
					direccionSof.setBarrio( e.getBarrio() );
					direccionSof.setIdUbicacion( e.getDivisionPolitica() );
					direccionSof.setCallePrincipal( e.getCallePrincipal() ); 
					direccionSof.setCalleSecundaria( e.getCalleSegundaria() );
					direccionSof.setNumero( e.getNumeracion() );
					direccionSof.setReferencia( e.getReferenciaUbicacion() );
					direccionSof.setEsDireccionLegal( e.getDireccionLegal() );
					direccionSof.setEsDireccionEnvio( e.getDireccionEnvioCorrespondencia() );
					direccionSof.setActivo( e.getEstado() == EstadoEnum.ACT );
					direcciones.add( direccionSof );
				});
				sof.setDirecciones( direcciones );
			}
			if( wp.getTelefonos() != null ) {
				List<SoftbankTelefonosWrapper> telefonos = new ArrayList<>();
				wp.getTelefonos().forEach(e->{
					SoftbankTelefonosWrapper telSof = new SoftbankTelefonosWrapper();
					telSof.setId( e.getIdSoftbank() );
					telSof.setCodigoTipoTelefono( e.getTipoTelefono() );
					telSof.setNumero( e.getNumero() );
					telSof.setActivo( e.getEstado() == EstadoEnum.ACT );
					telefonos.add( telSof );
				});
				sof.setTelefonos( telefonos );
			}
			if( wp.getCuentas() != null) {
				List<SoftbankCuentasBancariasWrapper> cuentasBancarias = new ArrayList<>();
				wp.getCuentas().forEach(e->{
					SoftbankCuentasBancariasWrapper cu = new SoftbankCuentasBancariasWrapper();
					cu.setId( e.getIdSoftbank() );
					cu.setIdBanco( e.getBanco() );
					cu.setCuenta( e.getCuenta() );
					cu.setEsAhorros( e.getEsAhorros() );
					cu.setActivo( e.getEstado() == EstadoEnum.ACT );
					cu.setEsNueva(e.getEsNueva());
					cuentasBancarias.add(cu);
				});
				sof.setCuentasBancariasCliente(cuentasBancarias);
			}
			if(wp.getReferencias() != null ) {
				List<SoftbankContactosWrapper> contactosCliente = new ArrayList<>();
				wp.getReferencias().forEach(e->{
					SoftbankContactosWrapper ref = new SoftbankContactosWrapper();
					ref.setId( e.getIdSoftbank() );
					ref.setApellidos( e.getApellidos() );
					ref.setNombres( e.getNombres() );
					ref.setCodigoTipoReferencia( e.getParentesco() );
					ref.setDireccion( e.getDireccion() );
					ref.setActivo( e.getEstado() == EstadoEnum.ACT );
					List<TelefonosContactoClienteWrapper> subList = new ArrayList<>();
					if(e.getTelefonoFijo() != null) {
						subList.add( new TelefonosContactoClienteWrapper( "DOM", e.getTelefonoFijo() ) );						
					}
					if(e.getTelefonoMovil() != null) {
						subList.add( new TelefonosContactoClienteWrapper( "CEL", e.getTelefonoMovil()) );						
					}
					ref.setTelefonos( subList );
					contactosCliente.add(ref);
				});
				sof.setContactosCliente( contactosCliente );
			}
			if(wp.getDatosTrabajos() != null) {
				String codigoCargo = this.parametroRepository.findByNombre(QuskiOroConstantes.CARGO_DEFAULT).getValor();
				List<SoftbankDatosTrabajoWrapper> datosTrabajo = new ArrayList<>();
				wp.getDatosTrabajos().forEach( t ->{
					SoftbankDatosTrabajoWrapper da = new SoftbankDatosTrabajoWrapper();
					da.setCodigoCargo( StringUtils.isNotBlank(t.getCargo())?t.getCargo():codigoCargo );
					da.setCodigoActividadEconomicaClienteMupi( t.getActividadEconomicaMupi() != null ?  t.getActividadEconomicaMupi() : QuskiOroConstantes.OTRAS_ACTIVIDADES );
					da.setCodigoOcupacion( t.getOcupacion() );
					da.setCodigoOrigenIngreso( t.getOrigenIngreso() );
					da.setNombreEmpresa( t.getNombreEmpresa() );
					da.setEsRelacionDependencia( t.getEsRelacionDependencia() );
					da.setEsPrincipal( t.getEsprincipal() );
					da.setIdActividadEconomica( t.getActividadEconomica() != null ? t.getActividadEconomica() : 
						//Long.valueOf(QuskiOroConstantes.ACTIVIDADES_NO_ECONOMICAS) 
						null
						);
					da.setActivo( t.getEstado() == EstadoEnum.ACT );
					da.setId( t.getIdSoftbank() );
					datosTrabajo.add( da );					
				});
				sof.setDatosTrabajoCliente(datosTrabajo);
			}
			return sof;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,e.getMessage());
		}
	}

	public void crearClienteSoftbank(SoftbankClienteWrapper cliente, String autorizacion) throws RelativeException {
		try {					
			SoftBankApiClient.callCrearClienteRest(this.parametroRepository
				.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_CLIENTE).getValor(),autorizacion, cliente);
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION_SOFTBANK + e.getMessage());
		}

	}

	public void editarClienteSoftbank(SoftbankClienteWrapper cliente, String autorizacion) throws RelativeException {
		try {
			
			SoftBankApiClient.callEditarClienteRest(cliente,autorizacion, 
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE).getValor());
			

		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_SOFTBANK + e.getMessage());
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

	
	public CalculadoraRespuestaWrapper simularOfertasCalculadoraPrueba(CalculadoraEntradaWrapper wrapper)
			throws RelativeException {
		try {
			return CalculadoraRespuestaWrapper.generateMockupEstandar();
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	} */
	
	public List<TipoOroWrapper> tipoOro() throws RelativeException {		
		CatalogoResponseWrapper response = ApiGatewayClient.getTipoOro(this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_ORO).getValor());
		
		if(response != null && response.getCatalogo() != null && !response.getCatalogo().isEmpty()) {
			List<TipoOroWrapper> tiposOro = new ArrayList<>();
			for (CatalogoWrapper c:response.getCatalogo()) {
				TipoOroWrapper tipo = new TipoOroWrapper();
				tipo.setNombre(c.getNombre());
				tipo.setCodigo(c.getCodigo());
				tiposOro.add(tipo);
			}
			return tiposOro;
		}
		return null;
		
	}
	
	public List<TipoOroWrapper> tipoOro(TbQoCliente cliente) throws RelativeException {		
		
		CatalogoResponseWrapper response = ApiGatewayClient.getTipoOro(this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_ORO).getValor());
		
		if(response != null && response.getCatalogo() != null && !response.getCatalogo().isEmpty()) {
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			StringBuilder garantiaXML= new StringBuilder();
			for (CatalogoWrapper c:response.getCatalogo()) {
				garantiaXML.append( 
						contentXMLGarantia.replace( "--tipo-joya--" ,"ANI")
						.replace("--descripcion--","BUEN ESTADO")
						.replace("--estado-joya--", "BUE")
						.replace("--tipo-oro-quilataje--", c.getCodigo())
						.replace("--peso-gr--", "7.73")
						.replace("--tiene-piedras--", "S")
						.replace("--detalle-piedras--", "PIEDRAS")
						.replace("--descuento-peso-piedras--", "0.73")
						.replace("--peso-neto--", "7.00")
						.replace("--precio-oro--", "23.72")
						.replace("--valor-aplicable-credito--", "293.02")
						.replace("--valor-realizacion--", "232.07")
						.replace("--numero-piezas--", "1")
						.replace("--descuento-suelda--", "0.00")
						.replace("--numero-garantia--", "0")
						.replace("--numero-expediente--", "0")
						);
			}
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")
					.replace("--origen-operacion--", "N")
					.replace("--riesgo-total--", "0.00")
					 		.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(cliente.getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--perfil-preferencia--", "A")
					.replace("--agencia-originacion--", "01")
					.replace("--identificacion-cliente--",cliente.getCedulaCliente())
							.replace("--calificacion-mupi--", cliente.getAprobacionMupi()) // error
					.replace("--cobertura-exepcionada--", "0")
					.replace("--garanttias-detalle--", garantiaXML.toString())
					.replace("--monto-solicitado--", "0");
			
			TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
					this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
			SimularResponse responseSimulador = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
					token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
			if(responseSimulador != null && responseSimulador.getSimularResult() != null && responseSimulador.getSimularResult().getXmlGarantias() != null
					 && responseSimulador.getSimularResult().getXmlGarantias().getGarantias() != null && responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia() != null
					 && !responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia().isEmpty()) {
				List<TipoOroWrapper> tiposOro = new ArrayList<>();
				for ( Garantia g : responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia()) {
					TipoOroWrapper tipo = new TipoOroWrapper();
					for (CatalogoWrapper c:response.getCatalogo()) {
						if(c.getCodigo().equalsIgnoreCase(g.getTipoOroKilataje())) {
							tipo.setNombre(c.getNombre());
						}
					}
					tipo.setCodigo(g.getTipoOroKilataje());
					tipo.setValorOro(g.getValorOro() );
					tiposOro.add(tipo);
				}
				return tiposOro;
			}
		}
		return null;
	}
	
	public CuentaWrapper consultaCuentaApiGateWay(String cedula) throws RelativeException {		
			try {
				String contentXMLcuenta = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_CUENTA_MUPI ).getValor();
				contentXMLcuenta = contentXMLcuenta.replace("--tipo-cliente--", "C").replace("--identificacion--", cedula).replace("--tipo-consulta--", "IF");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				InformacionWrapper response = ApiGatewayClient.callCuentaRest(
						this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CUENTA_MUPI).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(),
						contentXMLcuenta
				);		
				if(response != null && response.getCodigoError() == 0 && response.getINFOFINAN() != null) {
					CuentaWrapper retorno = new CuentaWrapper( String.valueOf( response.getIdentificacion() )  );
					retorno.setTipoPago( response.getINFOFINAN().getTIPOPAGO() );
					retorno.setInstitucionFinanciera( parametroRepository.findByNombre(QuskiOroConstantes.CODIGO_BANCO_MUPI).getValor());
//					if( String.valueOf( response.getINFOFINAN().getINSTITUCIONFINANCIERA()) == "15") {
//					}else {
//						retorno.setInstitucionFinanciera( String.valueOf( response.getINFOFINAN().getINSTITUCIONFINANCIERA()) );
//					}
					retorno.setTipoCuenta( response.getINFOFINAN().getTIPOCUENTA() );
					retorno.setNumeroCuenta( String.valueOf( response.getINFOFINAN().getNUMEROCUENTA() ) );
					retorno.setFirmaRegularizada( response.getINFOFINAN().getFIRMAREGULARIZADA());
					retorno.setCuentaNueva(response.getINFOFINAN().getCUENTANUEVA());
					return retorno;
				}
				return null;
			} catch (RelativeException e) {
				e.printStackTrace();
				throw e;

			}
	}
	public List<TbQoTasacion> getDetalleJoya(TbQoTasacion j) throws RelativeException {			
		this.manageTasacion(j);
		
		return this.tasacionRepository.findByIdCredito(j.getTbQoCreditoNegociacion().getId());
	}
	
	public List<TbQoTasacion> getDetalleJoya(TbQoCliente cliente, TbQoTasacion joya) throws RelativeException {		
		
		try {
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			contentXMLGarantia= contentXMLGarantia
					.replace( "--tipo-joya--" ,joya.getTipoJoya())
					.replace("--descripcion--",joya.getDescripcion())
					.replace("--estado-joya--", joya.getEstadoJoya())
					.replace("--tipo-oro-quilataje--", joya.getTipoOro())
					.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ))
					.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
					.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
					.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra() != null ? QuskiOroUtil.formatoDecimal( joya.getDescuentoPesoPiedra() ):"0.00")
					.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
					.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
					.replace("--valor-aplicable-credito--", "293.02")
					.replace("--valor-realizacion--", "232.07")
					.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
					.replace("--descuento-suelda--", QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ))
					.replace("--numero-garantia--", "0")
					.replace("--numero-expediente--", "0");
				log.info("==============>>>>> XML garantia");
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")
					.replace("--origen-operacion--", "N")
					.replace("--riesgo-total--", "0.00")
					.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(cliente.getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--perfil-preferencia--", "A")
					.replace("--agencia-originacion--", "01")
					.replace("--identificacion-cliente--",cliente.getCedulaCliente())
					.replace("--calificacion-mupi--", cliente.getAprobacionMupi())
					.replace("--cobertura-exepcionada--", "0")
					.replace("--garanttias-detalle--", contentXMLGarantia)
					.replace("--monto-solicitado--", "0");
				log.info("==============>>>>> XML calculadora");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
						this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				SimularResponse responseSimulador = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				if(responseSimulador != null && responseSimulador.getSimularResult() != null && responseSimulador.getSimularResult().getXmlGarantias() != null
						 && responseSimulador.getSimularResult().getXmlGarantias().getGarantias() != null && responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia() != null
						 && !responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia().isEmpty()) {
					log.info("==============> Resultado de garantias calculadora ");
					for ( Garantia g : responseSimulador.getSimularResult().getXmlGarantias().getGarantias().getGarantia()) {
						TbQoTasacion j = new TbQoTasacion();
						j.setDescripcion(g.getDescripcion());
						j.setDescuentoPesoPiedra(BigDecimal.valueOf(g.getDescuentoPesoPiedras()) );
						j.setDescuentoSuelda(BigDecimal.valueOf(g.getDescuentoSuelda()) );
						j.setDetallePiedras(g.getDetallePiedras());
						j.setEstado(EstadoEnum.ACT);
						j.setEstadoJoya(g.getEstadoJoya());
						j.setId(joya.getId());
						j.setNumeroPiezas(Long.valueOf(g.getNumeroPiezas()) );
						j.setPesoBruto(BigDecimal.valueOf(g.getPesoGr()) );
						j.setTienePiedras(StringUtils.isNotBlank(g.getTienePiedras()) && g.getTienePiedras().equalsIgnoreCase("S")?Boolean.TRUE:Boolean.FALSE);
						j.setPesoNeto(BigDecimal.valueOf(g.getPesoNeto()) );
						j.setTipoJoya(g.getTipoJoya());
						j.setTipoOro(g.getTipoOroKilataje());
						j.setValorAvaluo(BigDecimal.valueOf(g.getValorAvaluo()) );
						j.setValorRealizacion(BigDecimal.valueOf(g.getValorRealizacion()) );
						j.setValorComercial(BigDecimal.valueOf(g.getValorAplicable()) );
						j.setTbQoCreditoNegociacion(joya.getTbQoCreditoNegociacion());
						j.setValorOro(BigDecimal.valueOf(g.getValorOro()));
						this.manageTasacion(j);
					}
					return this.tasacionRepository.findByIdCredito(joya.getTbQoCreditoNegociacion().getId());
				}	
			
			return null;
		} catch (RelativeException e) {
			
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	public SimularResponse simularOfertasCalculadoraCotizacion(Long idCotizador) throws RelativeException {				
		try {
			TbQoCotizador cotizacion = this.cotizadorRepository.findById(idCotizador);
			List<TbQoTasacion> joyas = this.tasacionRepository.findByIdCotizador(idCotizador);
			if( joyas == null || joyas.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORACION DE LAS GARANTIAS");
			}
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			StringBuilder XMLGarantias = new StringBuilder();
			for(TbQoTasacion joya:joyas) {
				String x = contentXMLGarantia
						.replace( "--tipo-joya--" ,joya.getTipoJoya())
						.replace("--descripcion--",joya.getDescripcion())
						.replace("--estado-joya--", joya.getEstadoJoya())
						.replace("--tipo-oro-quilataje--", joya.getTipoOro())
						.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ))
						.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", QuskiOroUtil.formatoDecimal( joya.getDescuentoPesoPiedra() ))
						.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
						.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
						.replace("--valor-aplicable-credito--", QuskiOroUtil.formatoDecimal( joya.getValorComercial() ))
						.replace("--valor-realizacion--", QuskiOroUtil.formatoDecimal( joya.getValorRealizacion() ))
						.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
						.replace("--descuento-suelda--", QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ))
						.replace("--numero-garantia--", "0")
						.replace("--numero-expediente--", "0");
				XMLGarantias.append(x);
			}
			log.info("==============>>>>> XML garantia");
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")
					.replace("--origen-operacion--", "N")
					.replace("--riesgo-total--", "0.00")
					.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(cotizacion.getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--perfil-preferencia--", "A") 
					.replace("--agencia-originacion--", cotizacion.getIdAgencia() != null ? cotizacion.getIdAgencia().toString() : "01")
					.replace("--identificacion-cliente--",cotizacion.getTbQoCliente().getCedulaCliente())
					.replace("--calificacion-mupi--", cotizacion.getTbQoCliente().getAprobacionMupi())
					.replace("--cobertura-exepcionada--", "0")
					.replace("--garanttias-detalle--", XMLGarantias.toString())
					.replace("--monto-solicitado--", "0");
				log.info("==============>>>>> XML calculadora");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
						this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				SimularResponse res = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				if (res.getSimularResult().getXmlVariablesInternas().getVariablesInternas().getVariable() != null) {
					//ELIMINO LAS VARIABLES CREDITIAS Y MUESTRO LAS DEL CREDITO
					variablesCrediticiaRepository.deleteVariablesByCotizacionId(cotizacion.getId());
					for(com.relative.quski.wrapper.SimularResponse.SimularResult.XmlVariablesInternas.VariablesInternas.Variable e
							: res.getSimularResult().getXmlVariablesInternas().getVariablesInternas().getVariable()) {
						TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
						v.setCodigo(e.getCodigo());
						v.setNombre(e.getNombre());
						v.setOrden(String.valueOf(e.getOrden()));
						v.setValor(e.getValor());
						v.setTbQoCotizador(cotizacion);
						manageVariablesCrediticia(v);
					}
				} 
				return res;
		} catch (RelativeException e) {
			
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	public List<ProcesoCaducadoWrapper> findByTiempoBaseAprobadorProcesoEstadoProceso( List<ProcesoEnum> listProceso,List<String> listAprobador,List<EstadoProcesoEnum> listEstados) throws RelativeException {
		try {
			Long time = Long.valueOf(this.parametroRepository.findByNombre(QuskiOroConstantes.TIEMPO_APROBACION).getValor());
			return this.procesoRepository.findByTiempoBaseAprobadorProcesoEstadoProceso( time, listAprobador, listProceso, listEstados );
			
		}catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	public List<ProcesoCaducadoWrapper> listAlertaDeProcesos() throws RelativeException {
		try {
			Long time = Long.valueOf(this.parametroRepository.findByNombre(QuskiOroConstantes.TIEMPO_APROBACION).getValor());
			List<EstadoProcesoEnum> estados = new ArrayList<>();
			estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION );
			estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO );
			estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA );
		
			List<ProcesoEnum> procesos = new ArrayList<>();
			procesos.add( ProcesoEnum.CANCELACION_DEVOLUCION );
			procesos.add( ProcesoEnum.RENOVACION );
			procesos.add( ProcesoEnum.DEVOLUCION );
			procesos.add( ProcesoEnum.NUEVO );
			procesos.add( ProcesoEnum.PAGO );
			procesos.add( ProcesoEnum.VERIFICACION_TELEFONICA );
			List<ProcesoCaducadoWrapper> list = this.procesoRepository.findByTiempoBaseAprobadorProcesoEstadoProceso( time, null, procesos, estados );
			Map<String, byte[]> map = new HashMap<>();
			map.put("REPORTE.xls", generarReporteProcesoCaducado( list ));
			String[] listCorreos = {this.parametroRepository.findByNombre(QuskiOroConstantes.MAIL_JEFE_OPERACIONES).getValor()};
			this.mailNotificacion( listCorreos, "REPORTE DE ALERTA DE APROBADOR", "Lista de operaciones por vencer", map );
			return list;
		}catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	public List<ProcesoCaducadoWrapper> listAlertaDeProcesosAprobador( String aprobador ) throws RelativeException {
		try {
			Long time = Long.valueOf(this.parametroRepository.findByNombre(QuskiOroConstantes.TIEMPO_APROBACION).getValor());
			List<EstadoProcesoEnum> estados = new ArrayList<>();
			estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION );
			estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO );
			estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_FIRMA );
			List<ProcesoEnum> procesos = new ArrayList<>();
			procesos.add( ProcesoEnum.CANCELACION_DEVOLUCION );
			procesos.add( ProcesoEnum.RENOVACION );
			procesos.add( ProcesoEnum.DEVOLUCION );
			procesos.add( ProcesoEnum.NUEVO );
			procesos.add( ProcesoEnum.PAGO );
			procesos.add( ProcesoEnum.VERIFICACION_TELEFONICA );
			List<String> usuarios = new ArrayList<>();
			usuarios.add( aprobador );
			return this.procesoRepository.findByTiempoBaseAprobadorProcesoEstadoProceso( time, usuarios, procesos, estados );
		}catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	// TODO: Testear metodo por conflictos
	public SimularResponse simularOfertasCalculadora(Long idCredito, BigDecimal montoSolicitado, BigDecimal riesgoTotal,String codigoAgencia) throws RelativeException {				
		try {
			TbQoCreditoNegociacion credito = creditoNegociacionRepository.findById(idCredito);
			String fechaNacimiento =  QuskiOroUtil.dateToString(credito.getTbQoNegociacion().getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI);
			List<TbQoExcepcion> exs = this.excepcionesRepository.findByIdNegociacion(credito.getTbQoNegociacion().getId());
			if(exs != null && !exs.isEmpty() && exs.removeIf(r -> r.getTipoExcepcion().equals("EXCEPCION_RIESGO") && r.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO))) {
				fechaNacimiento =  QuskiOroUtil.dateToString(QuskiOroUtil.adicionEnAnios(new Date(), -20), QuskiOroUtil.DATE_FORMAT_QUSKI);
			}
			List<TbQoTasacion> joyas = this.tasacionRepository.findByIdCredito(idCredito);
			if( joyas == null || joyas.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORACION DE LAS GARANTIAS");
			}
			if ( montoSolicitado != null && !montoSolicitado.equals(BigDecimal.ZERO) ) {
				this.excepcionesRepository.inactivarExcepcionByTipoExcepcionAndIdNegociacion("EXCEPCION_RIESGO", credito.getTbQoNegociacion().getId());
				this.excepcionesRepository.inactivarExcepcionByTipoExcepcionAndIdNegociacion("EXCEPCION_COBERTURA", credito.getTbQoNegociacion().getId());
			}
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			StringBuilder XMLGarantias = new StringBuilder();
			for(TbQoTasacion joya:joyas) {
				String x = contentXMLGarantia
						.replace( "--tipo-joya--" ,joya.getTipoJoya())
						.replace("--descripcion--",joya.getDescripcion())
						.replace("--estado-joya--", joya.getEstadoJoya())
						.replace("--tipo-oro-quilataje--", joya.getTipoOro())
						.replace("--peso-gr--", joya.getPesoBruto() != null ? QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ): "0")
						.replace("--tiene-piedras--", joya.getTienePiedras() ? "S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras() ? joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra() != null ? QuskiOroUtil.formatoDecimal( joya.getDescuentoPesoPiedra() ): "0.00")
						.replace("--peso-neto--", joya.getPesoNeto() != null ? QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ): "0")
						.replace("--precio-oro--", joya.getValorOro() != null ? QuskiOroUtil.formatoDecimal( joya.getValorOro() ): "0")
						.replace("--valor-aplicable-credito--", joya.getValorComercial() != null ?QuskiOroUtil.formatoDecimal( joya.getValorComercial() ): "0.00")
						.replace("--valor-realizacion--", joya.getValorRealizacion() != null ? QuskiOroUtil.formatoDecimal( joya.getValorRealizacion() ): "0.00")
						.replace("--numero-piezas--", joya.getNumeroPiezas() != null ?joya.getNumeroPiezas().toString():"0")
						.replace("--descuento-suelda--", joya.getDescuentoSuelda() != null ? QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ): "0.00")
						.replace("--numero-garantia--", "0")
						.replace("--numero-expediente--", "0");
				XMLGarantias.append(x);
			}
			log.info("==============>>>>> XML garantia");
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")//donde saco el perfil
					.replace("--origen-operacion--", "N")
					.replace("--riesgo-total--", riesgoTotal.toString())
					.replace("--fecha-nacimiento--",fechaNacimiento)
					.replace("--perfil-preferencia--", "A") //donde saco el tipo
					.replace("--agencia-originacion--", codigoAgencia)
					.replace("--identificacion-cliente--",credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
					.replace("--calificacion-mupi--", StringUtils.isNotBlank(credito.getTbQoNegociacion().getTbQoCliente().getAprobacionMupi())?credito.getTbQoNegociacion().getTbQoCliente().getAprobacionMupi() :"N")
					.replace("--cobertura-exepcionada--", StringUtils.isNotBlank(credito.getCobertura())?credito.getCobertura():"0" )
					.replace("--garanttias-detalle--", XMLGarantias.toString())
					.replace("--monto-solicitado--", montoSolicitado.toString());
				log.info("==============>>>>> XML calculadora");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
						this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				SimularResponse res = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				if (res.getSimularResult().getXmlVariablesInternas().getVariablesInternas().getVariable() != null
						&& !res.getSimularResult().getXmlVariablesInternas().getVariablesInternas().getVariable().isEmpty()) {
					//ELIMINO LAS VARIABLES CREDITIAS Y MUESTRO LAS DEL CREDITO
					variablesCrediticiaRepository.deleteVariablesByNegociacionId(credito.getTbQoNegociacion().getId());
					for(com.relative.quski.wrapper.SimularResponse.SimularResult.XmlVariablesInternas.VariablesInternas.Variable e
							: res.getSimularResult().getXmlVariablesInternas().getVariablesInternas().getVariable()) {
						TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
						v.setCodigo(e.getCodigo());
						v.setNombre(e.getNombre());
						v.setOrden(String.valueOf(e.getOrden()));
						v.setValor(e.getValor());
						v.setTbQoNegociacion(credito.getTbQoNegociacion());		
						manageVariablesCrediticia(v);
					}
				} 
				if(res != null && res.getSimularResult() != null && res.getSimularResult().getXmlGarantias() != null
						 && res.getSimularResult().getXmlGarantias().getGarantias() != null && res.getSimularResult().getXmlGarantias().getGarantias().getGarantia() != null
						 && !res.getSimularResult().getXmlGarantias().getGarantias().getGarantia().isEmpty()) {
					log.info("==============> Resultado de garantias calculadora ");
					this.tasacionRepository.deleteTasacionByNegociacionId(credito.getId());
					if(res.getSimularResult().getXmlGarantias().getGarantias().getGarantia() != null 
							&& !res.getSimularResult().getXmlGarantias().getGarantias().getGarantia().isEmpty()) {
						for ( Garantia g : res.getSimularResult().getXmlGarantias().getGarantias().getGarantia()) {
							TbQoTasacion j = new TbQoTasacion();
							j.setDescripcion(g.getDescripcion());
							j.setDescuentoPesoPiedra(BigDecimal.valueOf(g.getDescuentoPesoPiedras()) );
							j.setDescuentoSuelda(BigDecimal.valueOf(g.getDescuentoSuelda()) );
							j.setDetallePiedras(g.getDetallePiedras());
							j.setEstado(EstadoEnum.ACT);
							j.setEstadoJoya(g.getEstadoJoya());
							//j.setId(joya.getId());
							j.setNumeroPiezas(Long.valueOf(g.getNumeroPiezas()) );
							j.setPesoBruto(BigDecimal.valueOf(g.getPesoGr()) );
							j.setTienePiedras(StringUtils.isNotBlank(g.getTienePiedras()) && g.getTienePiedras().equalsIgnoreCase("S")?Boolean.TRUE:Boolean.FALSE);
							j.setPesoNeto(BigDecimal.valueOf(g.getPesoNeto()) );
							j.setTipoJoya(g.getTipoJoya());
							j.setTipoOro(g.getTipoOroKilataje());
							j.setValorAvaluo(BigDecimal.valueOf(g.getValorAvaluo()) );
							j.setValorRealizacion(BigDecimal.valueOf(g.getValorRealizacion()) );
							j.setValorComercial(BigDecimal.valueOf(g.getValorAplicable()) );
							j.setTbQoCreditoNegociacion(credito);
							j.setValorOro(BigDecimal.valueOf(g.getValorOro()));
							this.manageTasacion(j);
						}
					}					
				}	
				return res;
		} catch (RelativeException e) {
			
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}


		public SimularResponse simularOfertasCalculadoraRenovacion(DetalleCreditoWrapper creditoSoft, 
				BigDecimal riesgoTotal,String codigoAgencia,String coberturaExcepcionada,BigDecimal montoSolicitado) throws RelativeException {				
			try {
				if( creditoSoft.getGarantias() == null || creditoSoft.getGarantias().isEmpty()) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORACION DE LAS GARANTIAS");
				}
	
				String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
				StringBuilder XMLGarantias = new StringBuilder();
				for(GarantiaOperacionWrapper joya:creditoSoft.getGarantias()) {
					String x = contentXMLGarantia
							.replace( "--tipo-joya--" ,joya.getCodigoTipoJoya())
							.replace("--descripcion--",joya.getDescripcionJoya())
							.replace("--estado-joya--", joya.getCodigoEstadoJoya())
							.replace("--tipo-oro-quilataje--", joya.getCodigoTipoOro())
							.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto()))
							.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
							.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
							.replace("--descuento-peso-piedras--", QuskiOroUtil.formatoDecimal( joya.getDescuentoPiedras()))
							.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
							.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
							.replace("--valor-aplicable-credito--", QuskiOroUtil.formatoDecimal( joya.getValorComercial() ))
							.replace("--valor-realizacion--", QuskiOroUtil.formatoDecimal( joya.getValorRealizacion() ))
							.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
							.replace("--descuento-suelda--", QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ))
							.replace("--numero-garantia--", String.valueOf(joya.getNumeroGarantia()))
							.replace("--numero-expediente--", String.valueOf(joya.getNumeroExpediente()));
					XMLGarantias.append(x);
				}
				log.info("==============>>>>> XML garantia");
				String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA_RENOVAR).getValor();
				contentXMLcalculadora = contentXMLcalculadora
						.replace("--perfil-riesgo--", "1")
						.replace("--origen-operacion--", creditoSoft.getCredito().getEsMigrado()?"E":"Q")
						.replace("--riesgo-total--", riesgoTotal.toString())
						.replace("--fecha-nacimiento--", creditoSoft.getCliente().getFechaNacimiento())
						.replace("--perfil-preferencia--", "A") 
						.replace("--agencia-originacion--", codigoAgencia)
						.replace("--identificacion-cliente--",creditoSoft.getCliente().getIdentificacion())
						.replace("--calificacion-mupi--", "N")//consultar que pasa con la aprobacion de mupi
						.replace("--cobertura-exepcionada--", StringUtils.isNotBlank(coberturaExcepcionada)?coberturaExcepcionada:"0" )
						.replace("--garanttias-detalle--", XMLGarantias.toString())

						.replace("--saldo-monto-credito-anterior--", creditoSoft.getCredito().getSaldo().toString())
						.replace("--saldo-interes-credito-anterior--", "0")
						.replace("--mora-credito-anterior--", "0")
						.replace("--cobranza-credito-anterior--", "0")
						.replace("--tipo-cartera--",creditoSoft.getCredito().getCodigoTipoCarteraQuski())// agregar el tipo de cartera
						.replace("--monto-financiado-credito-anterior--", creditoSoft.getCredito().getMontoFinanciado().toString())
						.replace("--plazo-credito-anterior--", creditoSoft.getCredito().getPlazo().toString())
						.replace("--tipo-credito-anterior--", creditoSoft.getCredito().getPeriodoPlazo())//agregar el codigo del tipo de credito
						.replace("--estado-credito-anterior--", creditoSoft.getCredito().getCodigoEstadoOperacion())
						.replace("--fecha-efectiva-credito-anterior--",QuskiOroUtil.dateToString( creditoSoft.getCredito().getFechaAprobacion(),QuskiOroUtil.DATE_FORMAT_QUSKI) )
						.replace("--fecha-vencimiento-credito-anterior--", QuskiOroUtil.dateToString(creditoSoft.getCredito().getFechaVencimiento(),QuskiOroUtil.DATE_FORMAT_QUSKI))
						.replace("--monto-solicitado--", montoSolicitado.toString())
						.replace("--numero-operacion-madre--",StringUtils.isNotBlank(creditoSoft.getCredito().getNumeroOperacionMadre())?
								creditoSoft.getCredito().getNumeroOperacionMadre():creditoSoft.getCredito().getNumeroOperacion())
						.replace("--numero-operacion-renovar--", creditoSoft.getCredito().getNumeroOperacion())
						.replace("--referencia-adicional--", creditoSoft.getCredito().getNumeroOperacionMupi())
						.replace("--operacion-propia--", creditoSoft.getCredito().getEsMigrado()?"NO":"SI");
					log.info("==============>>>>> XML calculadora");
					TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
							this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
					SimularResponse res = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
							token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				
					return res;
			} catch (RelativeException e) {
				
				e.printStackTrace();
				throw e;
			}catch (Exception e) {
				
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
			}
			
		}
		public SimularResponse simularOfertaRenovacionExcepcion(Long idCredito, String cobertura,String codigoAgencia, String autorizacion) throws RelativeException {				
			try {
				TbQoCreditoNegociacion credito = this.findCreditoNegociacionById(idCredito);
				if( credito == null ) { throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DEL CREDITO");}
				List<TbQoTasacion> listTasacion = this.tasacionRepository.findByIdCredito(idCredito);
				if(listTasacion == null || listTasacion.isEmpty()) {throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DE LAS JOYAS");}
				DetalleCreditoWrapper creditoSoft = this.traerCreditoVigente( credito.getNumeroOperacionAnterior(), autorizacion );
				
				String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
				StringBuilder XMLGarantias = new StringBuilder();
				for(TbQoTasacion joya:listTasacion) {
					String x = contentXMLGarantia
							.replace( "--tipo-joya--" ,joya.getTipoJoya())
							.replace("--descripcion--",joya.getDescripcion())
							.replace("--estado-joya--", joya.getEstadoJoya())
							.replace("--tipo-oro-quilataje--", joya.getTipoOro())
							.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ))
							.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
							.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
							.replace("--descuento-peso-piedras--", QuskiOroUtil.formatoDecimal( joya.getDescuentoPesoPiedra() ))
							.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
							.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
							.replace("--valor-aplicable-credito--", QuskiOroUtil.formatoDecimal( joya.getValorComercial() ))
							.replace("--valor-realizacion--", QuskiOroUtil.formatoDecimal( joya.getValorRealizacion() ))
							.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
							.replace("--descuento-suelda--", QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ))
							.replace("--numero-garantia--", "0")
							.replace("--numero-expediente--", "0");
					XMLGarantias.append(x);
				}
				log.info("==============>>>>> XML garantia");
				String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA_RENOVAR).getValor();
				contentXMLcalculadora = contentXMLcalculadora
						.replace("--perfil-riesgo--", "1")
						.replace("--origen-operacion--", creditoSoft.getCredito().getEsMigrado()?"E":"Q")
						.replace("--riesgo-total--", "0.00")
						.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(credito.getTbQoNegociacion().getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
						.replace("--perfil-preferencia--", "A") 
						.replace("--agencia-originacion--", codigoAgencia)
						.replace("--identificacion-cliente--",credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
						.replace("--calificacion-mupi--", "N")//consultar que pasa con la aprobacion de mupi
						.replace("--cobertura-exepcionada--", StringUtils.isNotBlank(cobertura)?cobertura:"0" )
						.replace("--garanttias-detalle--", XMLGarantias.toString())

						.replace("--saldo-monto-credito-anterior--", creditoSoft.getCredito().getSaldo().toString())
						.replace("--saldo-interes-credito-anterior--", "0")
						.replace("--mora-credito-anterior--", "0")
						.replace("--cobranza-credito-anterior--", "0")
						.replace("--tipo-cartera--",creditoSoft.getCredito().getCodigoTipoCarteraQuski())// agregar el tipo de cartera
						.replace("--monto-financiado-credito-anterior--", creditoSoft.getCredito().getMontoFinanciado().toString())
						.replace("--plazo-credito-anterior--", creditoSoft.getCredito().getPlazo().toString())
						.replace("--tipo-credito-anterior--", creditoSoft.getCredito().getPeriodoPlazo())//agregar el codigo del tipo de credito
						.replace("--estado-credito-anterior--", creditoSoft.getCredito().getCodigoEstadoOperacion())
						.replace("--fecha-efectiva-credito-anterior--", QuskiOroUtil.dateToString(creditoSoft.getCredito().getFechaAprobacion(),QuskiOroUtil.DATE_FORMAT_QUSKI) )
						.replace("--fecha-vencimiento-credito-anterior--", QuskiOroUtil.dateToString( creditoSoft.getCredito().getFechaVencimiento() , QuskiOroUtil.DATE_FORMAT_QUSKI))
						.replace("--monto-solicitado--", credito.getMontoSolicitado() == null ? "0.00" : credito.getMontoSolicitado().toString())
						.replace("--numero-operacion-madre--",StringUtils.isNotBlank(creditoSoft.getCredito().getNumeroOperacionMadre())?
								creditoSoft.getCredito().getNumeroOperacionMadre():creditoSoft.getCredito().getNumeroOperacion())
						.replace("--numero-operacion-renovar--", credito.getNumeroOperacionAnterior())
						.replace("--referencia-adicional--", creditoSoft.getCredito().getNumeroOperacionMupi())
						.replace("--operacion-propia--", creditoSoft.getCredito().getEsMigrado()?"NO":"SI");
					log.info("==============>>>>> XML calculadora");
					TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
							this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
					SimularResponse res = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
							token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
					return res;
			} catch (RelativeException e) {
				
				e.printStackTrace();
				throw e;
			}catch (Exception e) {
				
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
			}
		}
	public List<OpcionWrapper> simularOfertaExcepcionada(Long idCredito, Long cobertura, Long idAgencia, String codigoAgencia) throws Exception {				
		try {
			TbQoCreditoNegociacion credito = creditoNegociacionRepository.findById(idCredito);
			List<TbQoTasacion> joyas = this.tasacionRepository.findByIdCredito(idCredito);
			if( joyas == null || joyas.isEmpty()) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORACION DE LAS GARANTIAS");
			}
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			StringBuilder XMLGarantias = new StringBuilder();
			for(TbQoTasacion joya:joyas) {
				String x = contentXMLGarantia
						.replace( "--tipo-joya--" ,joya.getTipoJoya())
						.replace("--descripcion--",joya.getDescripcion())
						.replace("--estado-joya--", joya.getEstadoJoya())
						.replace("--tipo-oro-quilataje--", joya.getTipoOro())
						.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ))
						.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", QuskiOroUtil.formatoDecimal( joya.getDescuentoPesoPiedra() ))
						.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
						.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
						.replace("--valor-aplicable-credito--", QuskiOroUtil.formatoDecimal( joya.getValorComercial() ))
						.replace("--valor-realizacion--", QuskiOroUtil.formatoDecimal( joya.getValorRealizacion() ))
						.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
						.replace("--descuento-suelda--", QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ))
						.replace("--numero-garantia--", "0")
						.replace("--numero-expediente--", "0");
				XMLGarantias.append(x);
			}
				log.info("==============>>>>> XML garantia");
				String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
				contentXMLcalculadora = contentXMLcalculadora
						.replace("--perfil-riesgo--", "1")//donde saco el perfil
						.replace("--origen-operacion--", "S")
						.replace("--riesgo-total--", "0.00")
						.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(credito.getTbQoNegociacion().getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
						.replace("--perfil-preferencia--", "A") //donde saco el tipo
						.replace("--agencia-originacion--", codigoAgencia)
						.replace("--identificacion-cliente--",credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
						.replace("--calificacion-mupi--", credito.getTbQoNegociacion().getTbQoCliente().getAprobacionMupi())
						.replace("--cobertura-exepcionada--", cobertura.toString())//de donde saco esto
						.replace("--garanttias-detalle--", XMLGarantias.toString())
						.replace("--monto-solicitado--", "0");
					log.info("==============>>>>> XML calculadora");
					TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
							this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
					SimularResponseExcepcion halo = ApiGatewayClient.callCalculadoraExcepcionadoRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
							token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
					List<OpcionWrapper> opciones = new ArrayList<>();
					halo.getSimularResult().getXmlOfertasSimuladas().getOfertasSimuladas().getOpcion().forEach(e->{
						OpcionWrapper opcion = new OpcionWrapper();
						opcion.setPlazo( Long.valueOf( e.getPlazo() ) );
						opcion.setRiesgoAcumulado( e.getRiesgoAcumulado() );
						opcion.setValorDesembolso( e.getValorDesembolso() );
						opcion.setCuota( e.getCuota() );
						opcion.setMontoCredito( e.getMontoCredito() );
						opciones.add( opcion );
					});
					return opciones.isEmpty() ? null : opciones;
			} catch (Exception e) {
				
				e.printStackTrace();
				throw e;
			}			
	}
	public List<OpcionWrapper> simularOfertaExcepcionadaRenovacion(Long idCredito, String cobertura, String codigoAgencia, String autorizacion) throws Exception {				
		try {
			TbQoCreditoNegociacion credito = this.findCreditoNegociacionById(idCredito);
			if( credito == null ) { throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DEL CREDITO");}
			List<TbQoTasacion> listTasacion = this.tasacionRepository.findByIdCredito(idCredito);
			if(listTasacion == null || listTasacion.isEmpty()) {throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DE LAS JOYAS");}
			DetalleCreditoWrapper creditoSoft = this.traerCreditoVigente( credito.getNumeroOperacionAnterior(), autorizacion );
			
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			StringBuilder XMLGarantias = new StringBuilder();
			for(TbQoTasacion joya:listTasacion) {
				String x = contentXMLGarantia
						.replace( "--tipo-joya--" ,joya.getTipoJoya())
						.replace("--descripcion--",joya.getDescripcion())
						.replace("--estado-joya--", joya.getEstadoJoya())
						.replace("--tipo-oro-quilataje--", joya.getTipoOro())
						.replace("--peso-gr--", QuskiOroUtil.formatoDecimal( joya.getPesoBruto() ))
						.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", QuskiOroUtil.formatoDecimal( joya.getDescuentoPesoPiedra() ))
						.replace("--peso-neto--", QuskiOroUtil.formatoDecimal( joya.getPesoNeto() ))
						.replace("--precio-oro--", QuskiOroUtil.formatoDecimal( joya.getValorOro() ))
						.replace("--valor-aplicable-credito--", QuskiOroUtil.formatoDecimal( joya.getValorComercial() ))
						.replace("--valor-realizacion--", QuskiOroUtil.formatoDecimal( joya.getValorRealizacion() ))
						.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
						.replace("--descuento-suelda--", QuskiOroUtil.formatoDecimal( joya.getDescuentoSuelda() ))
						.replace("--numero-garantia--", "0")
						.replace("--numero-expediente--", "0");
				XMLGarantias.append(x);
			}
			log.info("==============>>>>> XML garantia");
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA_RENOVAR).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")
					.replace("--origen-operacion--", "S")
					.replace("--riesgo-total--", "0.00")
					.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(credito.getTbQoNegociacion().getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--perfil-preferencia--", "A") 
					.replace("--agencia-originacion--", codigoAgencia)
					.replace("--identificacion-cliente--",credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
					.replace("--calificacion-mupi--", "N")//consultar que pasa con la aprobacion de mupi
					.replace("--cobertura-exepcionada--", StringUtils.isNotBlank(cobertura)?cobertura:"0" )
					.replace("--garanttias-detalle--", XMLGarantias.toString())

					.replace("--saldo-monto-credito-anterior--", creditoSoft.getCredito().getSaldo().toString())
					.replace("--saldo-interes-credito-anterior--", "0")
					.replace("--mora-credito-anterior--", "0")
					.replace("--cobranza-credito-anterior--", "0")
					.replace("--tipo-cartera--",creditoSoft.getCredito().getCodigoTipoCarteraQuski())// agregar el tipo de cartera
					.replace("--monto-financiado-credito-anterior--", creditoSoft.getCredito().getMontoFinanciado().toString())
					.replace("--plazo-credito-anterior--", creditoSoft.getCredito().getPlazo().toString())
					.replace("--tipo-credito-anterior--", creditoSoft.getCredito().getPeriodoPlazo())//agregar el codigo del tipo de credito
					.replace("--estado-credito-anterior--", creditoSoft.getCredito().getCodigoEstadoOperacion())
					.replace("--fecha-efectiva-credito-anterior--", QuskiOroUtil.dateToString(creditoSoft.getCredito().getFechaAprobacion() , QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--fecha-vencimiento-credito-anterior--", QuskiOroUtil.dateToString(creditoSoft.getCredito().getFechaVencimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--monto-solicitado--", credito.getMontoSolicitado() == null ? "0.00" : credito.getMontoSolicitado().toString())
					.replace("--numero-operacion-madre--",StringUtils.isNotBlank(creditoSoft.getCredito().getNumeroOperacionMadre())?
							creditoSoft.getCredito().getNumeroOperacionMadre():creditoSoft.getCredito().getNumeroOperacion())
					.replace("--numero-operacion-renovar--", credito.getNumeroOperacionAnterior())
					.replace("--referencia-adicional--", creditoSoft.getCredito().getNumeroOperacionMupi())
					.replace("--operacion-propia--", creditoSoft.getCredito().getEsMigrado()?"NO":"SI");
				log.info("==============>>>>> XML calculadora");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
						this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				SimularResponseExcepcion halo = ApiGatewayClient.callCalculadoraExcepcionadoRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				List<OpcionWrapper> opciones = new ArrayList<>();
				halo.getSimularResult().getXmlOfertasSimuladas().getOfertasSimuladas().getOpcion().forEach(e->{
					OpcionWrapper opcion = new OpcionWrapper();
					opcion.setPlazo( Long.valueOf( e.getPlazo() ) );
					opcion.setValorDesembolso( e.getValorDesembolso() );
					opcion.setCuota( e.getCuota() );
					opcion.setMontoCredito( e.getMontoCredito() );
					opciones.add( opcion );
					opcion.setRiesgoAcumulado( e.getRiesgoAcumulado() );				
					});
					return opciones.isEmpty() ? null : opciones;
			} catch (Exception e) {
				
				e.printStackTrace();
				throw e;
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
			throw e;
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
			if( StringUtils.isNotBlank(send.getIdComprobante() ) ){
		        persisted.setIdComprobante( send.getIdComprobante() );
		    }
			if( StringUtils.isNotBlank(send.getCuentas() ) ){
		        persisted.setCuentas( send.getCuentas() );
		    }
			if( StringUtils.isNotBlank(send.getInstitucionFinanciera() ) ){
		        persisted.setInstitucionFinanciera( send.getInstitucionFinanciera() );
		    }
			if( StringUtils.isNotBlank(send.getTipoPago() ) ){
		        persisted.setTipoPago( send.getTipoPago() );
		    }
			if( StringUtils.isNotBlank(send.getUsuarioActualizacion() ) ){
		        persisted.setUsuarioActualizacion( send.getUsuarioActualizacion() );
		    }
			if( StringUtils.isNotBlank(send.getUsuarioCreacion() ) ){
		        persisted.setUsuarioCreacion( send.getUsuarioCreacion() );
		    }
			if( send.getFechaPago() != null ){
		        persisted.setFechaPago( send.getFechaPago() );
		    }
			if( send.getEstado() != null ){
		        persisted.setEstado( send.getEstado() );
		    }
			if( send.getNumeroDeposito() != null ){
		        persisted.setNumeroDeposito( send.getNumeroDeposito() );
		    }
			if( send.getValorPagado() != null ){
		        persisted.setValorPagado( send.getValorPagado() );
		    }
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
				if( send.getTipo() != null && send.getTipo().equalsIgnoreCase( "PAGO" )) {
					return crearCodigoPago( this.clientePagoRepository.add(send) );					
				}else if( send.getTipo() != null && send.getTipo().equalsIgnoreCase( "BLOQ" ) ) {
					return crearCodigoBloq( this.clientePagoRepository.add(send) );					
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO ESTA DEFININIDO UN TIPO DE PROCESO PAGO O BLOQUEO.");
				}
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + "CLIENTE PAGO" + e.getMessage());
		}
	}
	
	private TbQoClientePago crearCodigoPago(TbQoClientePago persisted) throws RelativeException {
		try {
			persisted.setCodigo( QuskiOroConstantes.CODIGO_PAGO.concat(StringUtils.leftPad(persisted.getId().toString(), 7, "0")));
			return this.clientePagoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}
	private TbQoClientePago crearCodigoBloq(TbQoClientePago persisted) throws RelativeException {
		try {
			persisted.setCodigo( QuskiOroConstantes.CODIGO_BLOQUEO.concat(StringUtils.leftPad(persisted.getId().toString(), 7, "0")));
			return this.clientePagoRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
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
			if( StringUtils.isNotBlank( send.getTipoPagoProceso() )) {
				persisted.setTipoPagoProceso( send.getTipoPagoProceso() );
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
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}/** ********************************************** @CREDITONEGOCIACION */

	public TbQoCreditoNegociacion manageCreditoNegociacionApp(TbQoCreditoNegociacion send) throws RelativeException {
		try {
			TbQoCreditoNegociacion persisted = null;
			if (send.getId() != null) {
				persisted = this.creditoNegociacionRepository.findById(send.getId());
				return this.updateCreditoNegociacion(send, persisted);
			} else if (send.getId() == null) {
				send.setFechaCreacion(new Date(System.currentTimeMillis()));
				send.setEstado(EstadoEnum.ACT);
				return crearCodigoRenovacion(this.creditoNegociacionRepository.add(send));
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION_O_CREACION + e.getMessage());
		}

	}
	private TbQoCreditoNegociacion crearCodigoCreditoNuevo(TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			
			persisted.setCodigo(procesoRepository.generarSecuencia(QuskiOroConstantes.CODIGO_NUEVO));
			return this.creditoNegociacionRepository.update(persisted);
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	private TbQoCreditoNegociacion updateCreditoNegociacion(TbQoCreditoNegociacion send,
			TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			
			if(StringUtils.isNotBlank(send.getNombreAgencia())) {
				persisted.setNombreAgencia(send.getNombreAgencia());
			}

			if( send.getaPagarCliente() != null ) {
			    persisted.setaPagarCliente(  send.getaPagarCliente() );
			}
			if( send.getaRecibirCliente() != null ) {
			    persisted.setaRecibirCliente(  send.getaRecibirCliente() );
			}
			if( send.getDeudaInicial() != null ) {
			    persisted.setDeudaInicial(  send.getDeudaInicial() );
			}
			if( StringUtils.isNotBlank( send.getCodigoCash() ) ) {
			    persisted.setCodigoCash(  send.getCodigoCash() );
			}
			if( send.getCodigoDevuelto() != null ) {
			    persisted.setCodigoDevuelto(  send.getCodigoDevuelto() );
			}
			if( StringUtils.isNotBlank( send.getDescripcionDevuelto() ) ) {
			    persisted.setDescripcionDevuelto(  send.getDescripcionDevuelto() );
			}
			if( !StringUtils.isBlank( send.getTablaAmortizacion() ) ) {
			    persisted.setTablaAmortizacion(  send.getTablaAmortizacion() );
			}
			if( send.getMontoFinanciado() != null ) {
			    persisted.setMontoFinanciado(  send.getMontoFinanciado() );
			}
			if( send.getEstado() != null ) {
			    persisted.setEstado(  send.getEstado() );
			}else {
				persisted.setEstado( EstadoEnum.ACT );
			}
			if( !StringUtils.isBlank( send.getEstadoSoftbank() ) ) {
			    persisted.setEstadoSoftbank(  send.getEstadoSoftbank() );
			}
			if( send.getPagoDia() != null ) {
			    persisted.setPagoDia(  send.getPagoDia() );
			}
			if( send.getFechaEfectiva() != null ) {
			    persisted.setFechaEfectiva(  send.getFechaEfectiva() );
			}
			if( send.getFechaVencimiento() != null ) {
			    persisted.setFechaVencimiento(  send.getFechaVencimiento() );
			}
			if( send.getIdAgencia() != null ) {
			    persisted.setIdAgencia(  send.getIdAgencia() );
			}
			if( StringUtils.isNotBlank( send.getNumeroOperacion() ) ) {
			    persisted.setNumeroOperacion(  send.getNumeroOperacion() );
			}
			if( send.getTotalInteresVencimiento() != null ) {
			    persisted.setTotalInteresVencimiento(  send.getTotalInteresVencimiento() );
			}
			if( send.getTotalCostoNuevaOperacion() != null ) {
			    persisted.setTotalCostoNuevaOperacion(  send.getTotalCostoNuevaOperacion() );
			}
			if( send.getNumeroFunda() != null  ) {
			    persisted.setNumeroFunda(  send.getNumeroFunda() );
			}
			if( !StringUtils.isBlank( send.getDescripcionProducto() ) ) {
			    persisted.setDescripcionProducto(  send.getDescripcionProducto() );
			}
			if( send.getMontoSolicitado() != null ) {
			    persisted.setMontoSolicitado(  send.getMontoSolicitado() );
			}
			if( send.getPlazoCredito() != null ) {
			    persisted.setPlazoCredito(  send.getPlazoCredito() );
			}
			if( send.getRiesgoTotalCliente() != null ) {
			    persisted.setRiesgoTotalCliente(  send.getRiesgoTotalCliente() );
			}
			if( !StringUtils.isBlank( send.getTipoCarteraQuski() ) ) {
			    persisted.setTipoCarteraQuski(  send.getTipoCarteraQuski() );
			}
			if( send.getValorCuota() != null ) {
			    persisted.setValorCuota(  send.getValorCuota() );
			}
			if( !StringUtils.isBlank( send.getCodigoTipoFunda() ) ) {
			    persisted.setCodigoTipoFunda(  send.getCodigoTipoFunda() );
			}
			if( !StringUtils.isBlank( send.getUriImagenSinFunda() ) ) {
			    persisted.setUriImagenSinFunda(  send.getUriImagenSinFunda() );
			}
			if( !StringUtils.isBlank( send.getNumeroCuenta() ) ) {
			    persisted.setNumeroCuenta(  send.getNumeroCuenta() );
			}
			if( !StringUtils.isBlank( send.getUriImagenConFunda() ) ) {
			    persisted.setUriImagenConFunda(  send.getUriImagenConFunda() );
			}
			if( !StringUtils.isBlank( send.getFirmanteOperacion() ) ) {
				persisted.setFirmanteOperacion(  send.getFirmanteOperacion() );
			}
			if( !StringUtils.isBlank( send.getTipoCliente() ) ) {
				persisted.setTipoCliente(  send.getTipoCliente() );
			}
			if( send.getTbQoNegociacion() != null ) {
			    persisted.setTbQoNegociacion(  send.getTbQoNegociacion() );
			}
			if( StringUtils.isNotBlank(send.getCobertura())) {
				persisted.setCobertura( send.getCobertura() );
			}
			if( StringUtils.isNotBlank(send.getIdentificacionApoderado() )) {
				persisted.setIdentificacionApoderado( send.getIdentificacionApoderado() );
			}
			if( StringUtils.isNotBlank(send.getNombreCompletoApoderado() )) {
				persisted.setNombreCompletoApoderado( send.getNombreCompletoApoderado() );
			}
			if( StringUtils.isNotBlank(send.getIdentificacionCodeudor() )) {
				persisted.setIdentificacionCodeudor( send.getIdentificacionCodeudor() );
			}
			if( StringUtils.isNotBlank(send.getNombreCompletoCodeudor() )) {
				persisted.setNombreCompletoCodeudor( send.getNombreCompletoCodeudor() );
			}
			if( send.getFechaNacimientoApoderado() != null ) {
				persisted.setFechaNacimientoApoderado( send.getFechaNacimientoApoderado() );
			}
			if( send.getFechaNacimientoCodeudor() != null ) {
				persisted.setFechaNacimientoCodeudor( send.getFechaNacimientoCodeudor() );
			}
			if( StringUtils.isNotBlank(send.getExcepcionOperativa() )) {
				persisted.setExcepcionOperativa( send.getExcepcionOperativa() );
			}
			if( send.getFechaRegularizacion() != null ) {
				persisted.setFechaRegularizacion( send.getFechaRegularizacion() );
			}
			if( send.getValorCash() != null) {
				persisted.setValorCash(send.getValorCash());
			}
			if(StringUtils.isNotBlank(send.getCanalContacto()) ) {
				persisted.setCanalContacto(send.getCanalContacto());
			}
						
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			persisted.setEstado( EstadoEnum.ACT );
			return this.creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}

	/**
	 * Metodo que trae los CreditosNegociacion existentes, tambien trae las
	 * variables,Riesgos Direcciones Referencias
	 * 
	 * @author Kléber Guerra Relative Engine
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public AprobacionWrapper traerCreditoNegociacionExistente(Long idNego, String autorizacion) throws RelativeException {
		try {
			AprobacionWrapper tmp = new AprobacionWrapper( Boolean.FALSE );
			tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( idNego ) );
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( idNego ) );
			tmp.setProceso( this.procesoRepository.findByIdCreditoNuevo( idNego ) );
			tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( idNego ) );
			tmp.setJoyas( this.tasacionRepository.findByIdCredito( tmp.getCredito().getId() ) );
			if(tmp.getExisteError()) {return tmp;}
			List<TbQoRiesgoAcumulado> riesgosTb =  this.createRiesgoFrontSoftBank(
					consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), autorizacion),
					tmp.getCredito().getTbQoNegociacion(), null );
			tmp.setRiesgos( riesgosTb );
			Long idCliente = tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId();
			tmp.setCuentas(     this.cuentaBancariaRepository.findByClienteAndCuenta( idCliente, tmp.getCredito().getNumeroCuenta() ));
			tmp.setTelefonos(   this.telefonoClienteRepository.findByIdCliente( idCliente));
			tmp.setDirecciones( this.direccionClienteRepository.findByIdCliente( idCliente));
			tmp.setTrabajos(    this.datoTrabajoClienteRepository.findByIdCliente( idCliente));
			tmp.setReferencias( this.referenciaPersonalRepository.findByIdCliente( idCliente));
			return tmp;
		} catch (RelativeException e) {
			throw e;
		}
	}
	public AprobacionNovacionWrapper traerCreditonovacionPorAprobar(Long idNego, String autorizacion) throws RelativeException {
		try {
			AprobacionNovacionWrapper tmp = new AprobacionNovacionWrapper( Boolean.FALSE );
			tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( idNego ) );
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( idNego ) );
			tmp.setProceso( this.procesoRepository.findByIdCreditoNovacion( idNego ) );
			if(tmp.getExisteError()) {return tmp;}			
			tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( idNego ) );
			tmp.setRiesgos( this.createRiesgoFrontSoftBank(
					consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), autorizacion), tmp.getCredito().getTbQoNegociacion(), null) );
			tmp.setCreditoAnterior( this.traerCreditoVigente( tmp.getCredito().getNumeroOperacionAnterior(), autorizacion ));
			tmp.setPagos( this.registrarPagoRepository.findByIdCredito(tmp.getCredito().getId() ));
			Long idCliente = tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId();
			tmp.setCuenta(     this.cuentaBancariaRepository.findByClienteAndCuenta( idCliente, tmp.getCredito().getNumeroCuenta() ));
			tmp.setTelefonos(   this.telefonoClienteRepository.findByIdCliente( idCliente));
			tmp.setDirecciones( this.direccionClienteRepository.findByIdCliente( idCliente));
			tmp.setTrabajos(    this.datoTrabajoClienteRepository.findByIdCliente( idCliente));
			tmp.setReferencias( this.referenciaPersonalRepository.findByIdCliente( idCliente));
			return tmp;
		} catch (RelativeException e) {
			throw e;
		}
	}

	public DetalleCreditoEnProcesoWrapper traerCreditoNegociacion(Long idNego) throws RelativeException {
		try {
			DetalleCreditoEnProcesoWrapper tmp = new DetalleCreditoEnProcesoWrapper( Boolean.FALSE );
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( idNego ) );
			tmp.setProceso( this.procesoRepository.findByIdCreditoNuevo( idNego ) );
			tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( idNego ) );
			if( tmp.getExisteError() ) {return tmp;}
			tmp.setRiesgos( this.riesgoAcumuladoRepository.findByIdNegociacion( idNego ) );
			tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( idNego ) );
			tmp.setJoyas( this.tasacionRepository.findByIdCredito( tmp.getCredito().getId() ) );
			Long idCliente = tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId();
			tmp.setTelefonos(   this.telefonoClienteRepository.findByIdCliente( idCliente));			
			return tmp;
		} catch (RelativeException e) {
			throw e;
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
						op.setCuentas( this.cuentaBancariaRepository.findByIdCliente( op.getCredito().getTbQoNegociacion().getTbQoCliente().getId() ) );
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
			return op;
		} catch (RelativeException e ){
			throw e;
		}
	}
	public DetalleCreditoWrapper traerCreditoVigente( String numeroOperacion, String autorizacion ) throws RelativeException{
		try {
			DetalleCreditoWrapper detalle = new DetalleCreditoWrapper();
			String urlCredito  = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GLOBAL).getValor();
			String urlCliente  = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_CLIENTE).getValor();
			String urlGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GARANTIA).getValor();
			String urlRubro    = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_RUBRO).getValor();
			
			RespuestaConsultaGlobalWrapper rCredito = SoftBankApiClient.callConsultarOperacionRest( new ConsultaOperacionGlobalWrapper( numeroOperacion ),autorizacion, urlCredito); 
			if(rCredito.getNumeroTotalRegistros() != Long.valueOf( 1 ) ) { return null;}
			detalle.setCredito( rCredito.getOperaciones().get( 0 ));
			detalle.setCliente( SoftBankApiClient.callConsultaClienteRest(urlCliente, autorizacion, rCredito.getOperaciones().get( 0 ).getIdentificacion() ) );
			detalle.setGarantias( SoftBankApiClient.callConsultarGarantiasRest( new ConsultaGarantiaWrapper(  
					StringUtils.isNotBlank(rCredito.getOperaciones().get( 0 ).getNumeroOperacionMadre())? rCredito.getOperaciones().get( 0 ).getNumeroOperacionMadre() :numeroOperacion), autorizacion,urlGarantia ) );
			detalle.setRubros( SoftBankApiClient.callConsultarRubrosRest( new ConsultaRubrosWrapper( numeroOperacion ),autorizacion, urlRubro ) );
			return detalle;
			
			
		}catch(RelativeException e ){
			throw e;
		}catch(Exception e ){
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getLocalizedMessage());
		}
	}
	
	public TbQoCreditoNegociacion optenerNumeroDeFunda(TbQoCreditoNegociacion c, String autorizacion) throws RelativeException {
		TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findById(c.getId());
		CrearOperacionEntradaWrapper operacionSoftBank = 
				new CrearOperacionEntradaWrapper(credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente(),
						credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() ); 
		operacionSoftBank.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  );
		String gradoInteres = this.findByNombre( QuskiOroConstantes.SOFT_GRADO_INTERES ).getValor();
		String tipoPrestamo = this.findByNombre( QuskiOroConstantes.SOFT_TIPO_PRESTAMO ).getValor();
		operacionSoftBank.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion()  ); 				

		operacionSoftBank.setDatosImpCom( this.generarImpCom( credito, autorizacion ) );
		operacionSoftBank.setCodigoTipoCarteraQuski( credito.getTipoCarteraQuski() );
		operacionSoftBank.setNumeroOperacion( credito.getNumeroOperacion() );
		operacionSoftBank.setCodigoTipoPrestamo( tipoPrestamo );
		operacionSoftBank.setMontoSolicitado( credito.getMontoSolicitado() );
		operacionSoftBank.setMontoFinanciado( credito.getMontoFinanciado() );
		operacionSoftBank.setPagoDia(  Long.valueOf( credito.getPagoDia() != null ? credito.getPagoDia().getDate() :  c.getPagoDia() != null ? c.getPagoDia().getDate() : 1  )  );
		operacionSoftBank.setCodigoGradoInteres( gradoInteres );
		operacionSoftBank.setDatosRegistro( 
				new DatosRegistroWrapper(
						credito.getTbQoNegociacion().getAsesor(), 
						c.getIdAgencia(),  
						QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT),
						credito.getTbQoNegociacion().getTbQoCliente().getPublicidad(),
						credito.getCodigo())
				); 
		DatosGarantiasWrapper datos = new DatosGarantiasWrapper();

		datos.setCodigoTipoFunda( c.getCodigoTipoFunda() ); 
		datos.setGarantias(new ArrayList<>());
		operacionSoftBank.setDatosGarantias( datos );
		CrearOperacionRespuestaWrapper result;
		try {
			result = SoftBankApiClient.callCrearOperacion01Rest(operacionSoftBank,autorizacion,
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_OPERACION).getValor());
		} catch (RelativeException e) {
			
			e.printStackTrace();
			throw e;
		}
		if(StringUtils.isBlank(result.getNumeroFundaJoya())) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"EL NUMERO DE FUNDA ASIGNADO ESTA VACIO");
		}
		credito.setNumeroFunda(result.getNumeroFundaJoya());
		credito.setCodigoTipoFunda(c.getCodigoTipoFunda());
		credito.setNumeroOperacion(result.getNumeroOperacion());
		return this.manageCreditoNegociacion(credito);
	}

	
	
	public CreditoCreadoSoftbank crearOperacionNuevo(  TbQoCreditoNegociacion wp, String autorizacion) throws RelativeException{
		try {
			
			CrearOperacionEntradaWrapper op = this.convertirCreditoCoreToCreditoSoftbank( this.manageCreditoNegociacion( wp ), autorizacion ); 
			if(op != null ) {
				CrearOperacionRespuestaWrapper operacion = 	SoftBankApiClient.callCrearOperacion01Rest(
						op,autorizacion, this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_OPERACION).getValor());
				CreditoCreadoSoftbank result = new CreditoCreadoSoftbank( this.guardarOperacion( operacion, wp ) );
				result.setCuotasAmortizacion( this.consultarTablaAmortizacion( operacion.getNumeroOperacion(), operacion.getUriHabilitantes(),  op.getDatosRegistro(), autorizacion)  );
				this.manageNegociacion(wp.getTbQoNegociacion());
				this.guardaraObservacion(wp.getTbQoNegociacion().getObservacionAsesor(), wp, wp.getTbQoNegociacion().getAsesor());
				String sinExcepcion = "SIN EXCEPCION";
				if(StringUtils.isNotBlank(wp.getExcepcionOperativa()) && !wp.getExcepcionOperativa().equalsIgnoreCase( sinExcepcion )) {
					this.notificarExcepcionOperativa( wp, Boolean.FALSE );
					this.guardaraOperativa(wp.getExcepcionOperativa(), wp.getFechaRegularizacion(),  wp.getTbQoNegociacion(), wp.getTbQoNegociacion().getAsesor());
				}
				return result; 
			}

			return null;
			
				
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	public CreditoCreadoSoftbank crearOperacionRenovacion(  CrearRenovacionWrapper wp , String autorizacion) throws RelativeException{
		try {
			borrarDatosObjectStorageByPagos(this.registrarPagoRepository.findByIdCredito(wp.getCredito().getId()), autorizacion);
			registrarPagoRepository.borrarPagos(wp.getCredito().getId());
			List<PagosNovacionSoftWrapper> listPagos = this.ps.crearRegistrarComprobanteRenovacion( wp );
			TbQoCreditoNegociacion credito = this.manageCreditoNegociacion( wp.getCredito() );
			CrearOperacionRenovacionWrapper op = this.convertirCreditoCoreToCreditoSoftbankRenovacion( credito, listPagos, autorizacion  ); 
			this.manageNegociacion(wp.getCredito().getTbQoNegociacion());
			this.guardaraObservacion(wp.getCredito().getTbQoNegociacion().getObservacionAsesor(), wp.getCredito(), wp.getCredito().getTbQoNegociacion().getAsesor());
			if(op != null ) {
				CrearOperacionRespuestaWrapper operacion = 	SoftBankApiClient.callRenovarOperacionRest(
						op, autorizacion, this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_RENOVAR_OPERACION).getValor());
				this.guardarOperacionRenovacion( operacion, wp.getCredito() );
				CreditoCreadoSoftbank result = new CreditoCreadoSoftbank(  );
				this.actualizarGarantiasSoftBank(credito.getTbQoTasacions(), wp.getCredito().getNumeroOperacionMadre(),wp.getCredito().getNumeroOperacionAnterior(), Boolean.FALSE, wp.getAsesor(), autorizacion);
				result.setCuotasAmortizacion( this.consultarTablaAmortizacion( operacion.getNumeroOperacion(), operacion.getUriHabilitantes(),  op.getDatosRegistro(),autorizacion)  );
				String sinExcepcion = "SIN EXCEPCION";
				if(StringUtils.isNotBlank(wp.getCredito().getExcepcionOperativa()) && !wp.getCredito().getExcepcionOperativa().equalsIgnoreCase( sinExcepcion )) {
					this.notificarExcepcionOperativa( wp.getCredito(), Boolean.TRUE );
					this.guardaraOperativa(wp.getCredito().getExcepcionOperativa(), wp.getCredito().getFechaRegularizacion(), wp.getCredito().getTbQoNegociacion(), wp.getAsesor());
				}
				return result; 
			}
			return null;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch( Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	public RespuestaAbonoWrapper aplicarAbono(  AbonoWrapper wp , String autorizacion) throws RelativeException{
		try {
			if(wp != null) {
				return	SoftBankApiClient.callAbonoRest(wp,autorizacion, this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_ABONO_OPERACION).getValor());
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "NO EXISTE WRAPPER PARA APLICAR ABONO.");
			}
		}catch(RelativeException e) {
			//e.printStackTrace();
			throw e;
		}catch( Exception e) {
			//e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	void borrarDatosObjectStorageByPagos(List<TbQoRegistrarPago> pagos, String autorizacion) {
		if(pagos != null && !pagos.isEmpty() ) {
			for(TbQoRegistrarPago p : pagos) {
				try {
					String urlService = parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor();
					String databaseName = parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor();
					String collectionName = parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor();
					LocalStorageClient.updateObject(urlService, databaseName, collectionName, new FileObjectStorage(),p.getIdComprobante(), autorizacion);
				} catch (Exception e) {
					log.info("NO SE LOGRO ACTUALIZAR OBJECT STORAGE borrarDatosObjectStorageByPagos");
				}
			}			
		}
	}
	
	public List<CuotasAmortizacionWrapper> consultarTablaAmortizacion(String numeroOperacion, String usuario, Long agencia, String autorizacion) throws RelativeException{
		try {
			ConsultaTablaWrapper entrada = new 	ConsultaTablaWrapper(numeroOperacion, null, null);
			SoftbankTablaAmortizacionWrapper persisted = SoftBankApiClient.callConsultaTablaAmortizacionRest(
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SOFTBANK_TABLA_AMORTIZACION).getValor(), autorizacion, entrada);
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
	private void guardarOperacionRenovacion(CrearOperacionRespuestaWrapper operacion, TbQoCreditoNegociacion credito) throws RelativeException {
		try {
			credito.setNumeroOperacion( operacion.getNumeroOperacion() );
			credito.setPlazoCredito( operacion.getPlazo() );
			credito.setDescripcionProducto( operacion.getProducto() );
			credito.setEstadoSoftbank( operacion.getEstado() );
			credito.setDeudaInicial( operacion.getDeudaInicial() );
			credito.setaRecibirCliente( operacion.getMontoEntregar() );
			credito.setaPagarCliente( operacion.getaPagarPorCliente() );
			credito.setValorCuota(  operacion.getValorCuota() );
			credito.setTotalCostoNuevaOperacion( operacion.getCostosOperacion() );
			credito.setFechaEfectiva(operacion.getFechaEfectiva());
			credito.setFechaVencimiento(operacion.getFechaVencimiento());
			credito.setTotalInteresVencimiento( operacion.getTotalInteresVencimiento() );
			credito.setTablaAmortizacion( operacion.getCodigoTablaAmortizacionQuski() );
			credito.setTipoCarteraQuski( operacion.getCodigoTipoCarteraQuski() );
			credito.setNumeroFunda( operacion.getNumeroFundaJoya() );
			credito = this.manageCreditoNegociacion( credito );
			
		}catch(RelativeException e) {
			throw e;
		}catch(Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	private TbQoCreditoNegociacion guardarOperacion(CrearOperacionRespuestaWrapper operacion, TbQoCreditoNegociacion credito) throws RelativeException {
		try {
			credito.setNumeroOperacion( operacion.getNumeroOperacion() );
			credito.setPlazoCredito( operacion.getPlazo() );
			credito.setDescripcionProducto( operacion.getProducto() );
			credito.setEstadoSoftbank( operacion.getEstado() );
			credito.setDeudaInicial( operacion.getDeudaInicial() );
			credito.setaRecibirCliente( operacion.getMontoEntregar() );
			credito.setaPagarCliente( operacion.getaPagarPorCliente() );
			credito.setValorCuota(  operacion.getValorCuota() );
			credito.setTotalCostoNuevaOperacion( operacion.getCostosOperacion() );
			credito.setFechaEfectiva(operacion.getFechaEfectiva());
			credito.setFechaVencimiento(operacion.getFechaVencimiento());
			credito.setTotalInteresVencimiento( operacion.getTotalInteresVencimiento() );
			credito.setTablaAmortizacion( operacion.getCodigoTablaAmortizacionQuski() );
			credito.setTipoCarteraQuski( operacion.getCodigoTipoCarteraQuski() );
			credito.setNumeroFunda( operacion.getNumeroFundaJoya() );
			
			if(operacion.getGarantias() != null ) {
				List<TbQoTasacion> list = this.tasacionRepository.findByIdCredito( credito.getId() );
				if(list != null) { 
					list.forEach(l->{
						l.setEstado( EstadoEnum.INA );
						try {
							this.tasacionRepository.update( l );
						} catch (RelativeException e) {
							e.printStackTrace();
						}
					});
				}
				operacion.getGarantias().forEach( g ->{
					TbQoTasacion joya = new TbQoTasacion();
					joya.setNumeroGarantia( g.getNumeroGarantia() );
					joya.setNumeroExpediente( g.getNumeroExpediente() );
					joya.setTipoGarantia( g.getCodigoTipoGarantia() );
					joya.setDescripcion( g.getDescripcion() );
					joya.setSubTipoGarantia( g.getCodigoSubTipoGarantia() );
					joya.setValorComercial( g.getValorComercial() );
					joya.setValorAvaluo( g.getValorAvaluo() );
					joya.setValorRealizacion( g.getValorRealizacion() );
					joya.setValorOro( g.getValorOro() );
					joya.setTipoJoya( g.getCodigoTipoJoya() );
					joya.setEstadoJoya( g.getCodigoEstadoJoya() );
					joya.setTipoOro( g.getCodigoTipoOro() );
					joya.setPesoBruto( g.getPesoBruto() );
					joya.setTienePiedras( g.getTienePiedras() );
					joya.setDetallePiedras( g.getDetallePiedras() );
					joya.setDescuentoPesoPiedra( g.getDescuentoPiedras() );
					joya.setPesoNeto( g.getPesoNeto() );
					joya.setNumeroPiezas( g.getNumeroPiezas() );
					joya.setDescuentoSuelda( g.getDescuentoSuelda() );
					joya.setTbQoCreditoNegociacion( credito );
					try {
						this.manageTasacion( joya );
					} catch (RelativeException e) {
						e.printStackTrace();
					}
					
				});
			}
			return this.manageCreditoNegociacion( credito );
			
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	@SuppressWarnings("deprecation")
	private CrearOperacionRenovacionWrapper convertirCreditoCoreToCreditoSoftbankRenovacion( TbQoCreditoNegociacion credito, List<PagosNovacionSoftWrapper> listPagos, String autorizacion )  throws RelativeException {
		try {			
			TbQoCliente cliente = credito.getTbQoNegociacion().getTbQoCliente();
			List<TbQoCuentaBancariaCliente> cuentaCliente = this.cuentaBancariaRepository.findByIdCliente( cliente.getId() );
			String gradoInteres = this.findByNombre( QuskiOroConstantes.SOFT_GRADO_INTERES ).getValor();
			String tipoPrestamo = this.findByNombre( QuskiOroConstantes.SOFT_TIPO_PRESTAMO ).getValor();
			if( cuentaCliente == null ){
				throw new RelativeException(Constantes.ERROR_CODE_READ);
			}
			CrearOperacionRenovacionWrapper result = new CrearOperacionRenovacionWrapper(); 
			if( listPagos != null ) {
				result.setPagosNovacion( listPagos );
			}
			result.setIdTipoIdentificacion( Long.valueOf( 1 ) ); 
			result.setIdentificacion(credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() ); 
			result.setNombreCliente( credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() ); 
			result.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  ) ;
			
			result.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion() );
			if(result.getCodigoTablaAmortizacionQuski() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ); 
			}
			result.setCodigoTipoPrestamo( tipoPrestamo );
			result.setCodigoGradoInteres( gradoInteres );
			result.setMontoFinanciado( credito.getMontoFinanciado() ) ;
			result.setPagoDia( Long.valueOf( credito.getPagoDia() != null ? credito.getPagoDia().getDate() : 1 ) );
			GaranteWrapper garante = null;
			if(credito.getTipoCliente().equalsIgnoreCase("SAP")) {
				garante = new GaranteWrapper( Long.valueOf(1), credito.getIdentificacionApoderado() , "SAP", credito.getNombreCompletoApoderado());	
			}else if(credito.getTipoCliente().equalsIgnoreCase("SCD")) {
				garante = new GaranteWrapper( Long.valueOf(1), credito.getIdentificacionCodeudor() , "SCD", credito.getNombreCompletoCodeudor());	
			}
			result.setDatosCodeudorApoderado( garante );
			result.setDatosRegistro(
					new DatosRegistroWrapper(
					credito.getTbQoNegociacion().getAsesor(), 
					credito.getIdAgencia(),  
					QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT),
					StringUtils.isBlank(cliente.getPublicidad())?"RENOVACION":cliente.getPublicidad(),
					credito.getCodigo())
					);
			result.setDatosImpCom(this.generarImpCom( credito, autorizacion )  );
			List<DatosCuentaClienteWrapper> listCuenta = new ArrayList<>();
			cuentaCliente.forEach(c->{
				if( credito.getNumeroCuenta().equals( c.getCuenta() )) {
					listCuenta.add( new DatosCuentaClienteWrapper( c.getBanco(),c.getCuenta(),c.getEsAhorros() ) );									
				}
			});
			result.setDatosCuentaCliente(listCuenta);
			result.setNumeroOperacionMadre( credito.getNumeroOperacionMadre() );
			return result;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}
	}
	@SuppressWarnings("deprecation")
	private CrearOperacionEntradaWrapper convertirCreditoCoreToCreditoSoftbank( TbQoCreditoNegociacion credito, String autorizacion )  throws RelativeException {
		try {			
			TbQoCliente cliente = credito.getTbQoNegociacion().getTbQoCliente();
			List<TbQoTasacion> joyas = this.tasacionRepository.findByIdCredito( credito.getId() ); 
			List<TbQoCuentaBancariaCliente> cuentaCliente = this.cuentaBancariaRepository.findByIdCliente( cliente.getId() );
			String tipoPrestamo = this.findByNombre( QuskiOroConstantes.SOFT_TIPO_PRESTAMO ).getValor();
			String gradoInteres = this.findByNombre( QuskiOroConstantes.SOFT_GRADO_INTERES ).getValor();
			if( joyas == null )        { 
				return null; 
			}
			if( cuentaCliente == null ){
				return null; 
			}
			CrearOperacionEntradaWrapper result = new CrearOperacionEntradaWrapper(cliente.getCedulaCliente(), cliente.getNombreCompleto() ); 
			result.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  );
		
			result.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion()  ); 				
			if(result.getCodigoTablaAmortizacionQuski() == null) {
				return null;
			}
			result.setDatosImpCom( this.generarImpCom( credito, autorizacion ) );
			result.setCodigoTipoCarteraQuski( credito.getTipoCarteraQuski() );
			if(StringUtils.isNotBlank(credito.getNumeroOperacion() ) ) {
				result.setNumeroOperacion( credito.getNumeroOperacion() );
			}
			result.setCodigoTipoPrestamo( tipoPrestamo );
			result.setMontoSolicitado( credito.getMontoSolicitado() );
			result.setMontoFinanciado( credito.getMontoFinanciado() );
			result.setPagoDia( Long.valueOf( credito.getPagoDia() != null ? credito.getPagoDia().getDate() : 1 ) );
			result.setCodigoGradoInteres( gradoInteres );
			result.setDatosRegistro( 
					new DatosRegistroWrapper(
							credito.getTbQoNegociacion().getAsesor(), 
							credito.getIdAgencia(),  
							QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT),
							cliente.getPublicidad(),
							credito.getCodigo()) 
					); 
			List<DatosCuentaClienteWrapper> listCuenta = new ArrayList<>();
			cuentaCliente.forEach(c->{
				if( credito.getNumeroCuenta().equals( c.getCuenta() )) {
					listCuenta.add( new DatosCuentaClienteWrapper( c.getBanco(),c.getCuenta(),c.getEsAhorros() ) );									
				}
			});
			result.setDatosCuentaCliente( listCuenta );
			DatosGarantiasWrapper datos = new DatosGarantiasWrapper();
			if( StringUtils.isNotBlank( credito.getNumeroFunda() ) ) {
				datos.setNumeroFundaJoya( credito.getNumeroFunda().toString() );								
			}
			datos.setCodigoTipoFunda( credito.getCodigoTipoFunda() ); 
			TbQoDocumentoHabilitante fotoJoya = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("6"), ProcessEnum.FUNDA, String.valueOf(credito.getTbQoNegociacion().getId()));
			TbQoDocumentoHabilitante fotoFunda = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("7"), ProcessEnum.FUNDA, String.valueOf(credito.getTbQoNegociacion().getId()));
			if(fotoJoya == null || fotoFunda == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LAS FOTOS DE LA FUNDA");
			}
			datos.setUriImagenSinFunda(fotoJoya.getObjectId() );
			datos.setUriImagenConFunda(fotoFunda.getObjectId() );
			List<JoyaWrapper> listjoyas = generarJoyas(credito, joyas);
			datos.setGarantias( listjoyas );
			result.setDatosGarantias( datos );
			
			GaranteWrapper garante = null;
			if(credito.getTipoCliente().equalsIgnoreCase("SAP")) {
				garante = new GaranteWrapper( Long.valueOf(1), credito.getIdentificacionApoderado() , "SAP", credito.getNombreCompletoApoderado());	
			}else if(credito.getTipoCliente().equalsIgnoreCase("SCD")) {
				garante = new GaranteWrapper( Long.valueOf(1), credito.getIdentificacionCodeudor() , "SCD", credito.getNombreCompletoCodeudor());	
			}
			result.setDatosCodeudorApoderado( garante );
			return result;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	/**
	 * Mapeado de valores extaidos de la calculadora quski al seleccionar la opccion. Solo se mapean aquellos impuestos comisiones que su valor sean diferentes a cero.
	 * @param credito
	 * @return
	 * @throws RelativeException
	 */
	private List<DatosImpComWrapper> generarImpCom(TbQoCreditoNegociacion credito, String autorizacion) throws RelativeException {
		try {
			List<DatosImpComWrapper> listImpCom = new ArrayList<DatosImpComWrapper>();
			List<CatalogoWrapper>  listCatalogo = this.catalogoImpCom(autorizacion);
			String costoCustodia = this.parametroRepository.findByNombre(QuskiOroConstantes.COSTO_CUSTODIA).getValor();
			String costoFideicomiso = this.parametroRepository.findByNombre(QuskiOroConstantes.COSTO_FIDEICOMISO).getValor();
			String costoSeguro = this.parametroRepository.findByNombre(QuskiOroConstantes.COSTO_SEGURO).getValor();
			String costoTasacion = this.parametroRepository.findByNombre(QuskiOroConstantes.COSTO_TASACION).getValor();
			String costoTransporte = this.parametroRepository.findByNombre(QuskiOroConstantes.COSTO_TRANSPORTE).getValor();
			String costoValoracion = this.parametroRepository.findByNombre(QuskiOroConstantes.COSTO_VALORACION).getValor();
			String saldoCapitalRenov = this.parametroRepository.findByNombre(QuskiOroConstantes.SALDO_CAPITAL_RENOV).getValor();
			String saldoInteres = this.parametroRepository.findByNombre(QuskiOroConstantes.SALDO_INTERES).getValor();
			String saldoMora = this.parametroRepository.findByNombre(QuskiOroConstantes.SALDO_MORA).getValor();
			String gastoCobranza = this.parametroRepository.findByNombre(QuskiOroConstantes.GASTO_COBRANZA).getValor();
			String custodiaDevengada = this.parametroRepository.findByNombre(QuskiOroConstantes.CUSTODIA_DEVENGADA).getValor();
			String impuestoSolca = this.parametroRepository.findByNombre(QuskiOroConstantes.IMPUESTO_SOLCA).getValor();
			String abonoCapital = this.parametroRepository.findByNombre(QuskiOroConstantes.ABONO_CAPITAL).getValor();

			listCatalogo.forEach(e->{
				DatosImpComWrapper item = new DatosImpComWrapper();
				if(credito.getAbonoCapital() != null && e.getCodigo().equalsIgnoreCase(abonoCapital) && credito.getAbonoCapital().compareTo( new BigDecimal( 0 ) ) > 0 ){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoAbonoCapital() );
					item.setValor( credito.getAbonoCapital() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equalsIgnoreCase(costoCustodia) && credito.getCostoCustodia().compareTo( new BigDecimal( 0 ) ) > 0 ){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoCustodia() );
					item.setValor( credito.getCostoCustodia() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(costoFideicomiso) && credito.getCostoFideicomiso().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoFideicomiso() );
					item.setValor( credito.getCostoFideicomiso() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(costoSeguro) && credito.getCostoSeguro().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoSeguro() );
					item.setValor( credito.getCostoSeguro() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(costoTasacion) && credito.getCostoTasacion().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoTasador() );
					item.setValor( credito.getCostoTasacion() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(costoTransporte) && credito.getCostoTransporte().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoTransporte() );
					item.setValor( credito.getCostoTransporte() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(costoValoracion) && credito.getCostoValoracion().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoValoracion() );
					item.setValor( credito.getCostoValoracion() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(saldoCapitalRenov) && credito.getSaldoCapitalRenov().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoCapital() );
					item.setValor( credito.getSaldoCapitalRenov() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(saldoInteres) && credito.getSaldoInteres().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoInteres() );
					item.setValor( credito.getSaldoInteres() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(saldoMora) && credito.getSaldoMora().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoMora() );
					item.setValor( credito.getSaldoMora() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(gastoCobranza) && credito.getGastoCobranza().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoGastoCobranza() );
					item.setValor( credito.getGastoCobranza() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(custodiaDevengada) && credito.getCustodiaDevengada().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoCustodiaDevengada() );
					item.setValor( credito.getCustodiaDevengada() );
					listImpCom.add( item );
				}
				if( e.getCodigo().equals(impuestoSolca) && credito.getImpuestoSolca().compareTo( new BigDecimal( 0 ) )> 0){
					item.setCodigo( e.getCodigo() );
					item.setCodigoFormaPagoQuski( credito.getFormaPagoImpuestoSolca() );
					item.setValor( credito.getImpuestoSolca() );
					listImpCom.add( item );
				}
			});
			return listImpCom;
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}

	}
	private List<JoyaWrapper> generarJoyas(TbQoCreditoNegociacion credito, List<TbQoTasacion> joyas) throws RelativeException {
		List<JoyaWrapper> listjoyas = new ArrayList<>();
		String cobertura = this.findByNombre( QuskiOroConstantes.SOFT_COBERTURA ).getValor();
		joyas.forEach(e->{
			JoyaWrapper joyaSoft = new JoyaWrapper();
			joyaSoft.setCodigoTipoGarantia( e.getTipoGarantia() );
			joyaSoft.setDescripcion( e.getDescripcion());
			joyaSoft.setCodigoSubTipoGarantia( e.getSubTipoGarantia() );
			joyaSoft.setTipoCobertura( cobertura );
			joyaSoft.setValorComercial( e.getValorComercial());
			joyaSoft.setValorAvaluo( e.getValorAvaluo());
			joyaSoft.setValorRealizacion( e.getValorRealizacion());
			joyaSoft.setValorOro( e.getValorOro());
			try {
				joyaSoft.setFechaAvaluo( QuskiOroUtil.dateToString( e.getFechaCreacion() , QuskiOroConstantes.SOFT_DATE_FORMAT ) );
			} catch (RelativeException e1) {
				e1.printStackTrace();
			}
			joyaSoft.setIdAgenciaRegistro( credito.getIdAgencia() );
			joyaSoft.setIdAgenciaCustodia( credito.getIdAgencia() );
			joyaSoft.setReferencia( credito.getCodigo() );
			joyaSoft.setCodigoTipoJoya( e.getTipoJoya() );
			joyaSoft.setDescripcionJoya( e.getDescripcion() );
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
		return listjoyas;
	}

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
			log.info("smtpHostServer>>>>"+smtpHostServer);
			log.info("portEmail>>>>"+portEmail);
			log.info("sfPortEmail>>>>"+sfPortEmail);
			log.info("userEmail>>>>"+userEmail);
			log.info("fromEmailDesa>>>>"+fromEmailDesa);
			log.info("authEmail>>>>"+authEmail);
			log.info("passwordEmail>>>>"+passwordEmail);
			log.info("para>>>>"+para[0]);
			log.info("asunto>>>>"+asunto);
			log.info("contenido>>>>"+contenido);
			log.info("adjunto>>>>"+adjunto);
			if (adjunto != null) {
				EmailDefinition ed = new EmailDefinition.Builder()
						.emailSecurityType(
								QuskiOroUtil.getEnumFromString(EmailSecurityTypeEnum.class, emailSecurityType))
						.smtpHostServer(smtpHostServer).port(portEmail).sfPort(sfPortEmail)
						.auth(authEmail.equalsIgnoreCase("TRUE")).password(passwordEmail)
						.user(userEmail).subject(asunto).tos(Arrays.asList(para)).fromEmail(fromEmailDesa)
						.message(contenido).hasFiles(Boolean.TRUE).attachments(adjunto).build();
				ed.setSession(EmailUtil.provideSession(ed, EmailSecurityTypeEnum.SSL));
				//Transport.send(null, null, passwordEmail, passwordEmail);
				EmailUtil.sendEmail(ed);
			} else {
				log.info("ESTOY LLEGANDO HASTA AQUI? ========> "+ authEmail);
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
			e.printStackTrace();
			log.info("ERROR ========>" + QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getCause() + e.getMessage() + e.getDetalle());
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
			e.printStackTrace();
			throw e;
		}
	}
	public void notificarExcepcionOperativa(TbQoCreditoNegociacion wrapper, Boolean novacion) {
		try {
			List<TbMiParametro> paras = this.parametroRepository.findByNombreAndTipoOrdered(null, QuskiOroConstantes.MAIL_PARA_EXC, false);
			String[] array = new String[paras.size()];
			for (int i = 0; i < paras.size(); ++i) {
				array[i] = paras.get(i).getValor().replace("--Correo asesor--", wrapper.getTbQoNegociacion().getCorreoAsesor() );
				log.info(" ESTE ES UN PARA DEL CORREO ===========> "+ array[i]);
			}
					
			String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.M_ASUNTO).getValor()
					.replace("--nombreCliente--", wrapper.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
					.replace("--cedulaCliente--", wrapper.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() )
					.replace("--codigoBpm--", wrapper.getCodigo() )
					.replace("--numeroSoftbank--", wrapper.getNumeroOperacion())
					.replace("--numeroOperacionAnterior--", novacion ? wrapper.getNumeroOperacionAnterior() : " ");

			String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.M_CONTENIDO).getValor()
					.replace("--nombreAsesor--", StringUtils.isNotBlank(wrapper.getTbQoNegociacion().getNombreAsesor())?wrapper.getTbQoNegociacion().getNombreAsesor():" " )
					.replace("--codigoBpm--", wrapper.getCodigo())
					.replace("--nombreCliente--", wrapper.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
					.replace("--monto--", wrapper.getMontoFinanciado() != null ?wrapper.getMontoFinanciado().toString() :" " )
					.replace("--numeroSoftbank--", wrapper.getNumeroOperacion())
					.replace("--plazo--", wrapper.getPlazoCredito() != null? String.valueOf(wrapper.getPlazoCredito()) : " ")
					.replace("--fechaVencimiento--", QuskiOroUtil.dateToString(wrapper.getFechaVencimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI) )
					.replace("--numeroOperacionSoftbank--", novacion ? wrapper.getNumeroOperacionMadre() : " ")
					.replace("--excepcionOperativa--", wrapper.getExcepcionOperativa() )
					.replace("--totalCostos--", String.valueOf(wrapper.getTotalGastosNuevaOperacion().doubleValue()) )
					.replace("--gastoCobranza--", String.valueOf(wrapper.getGastoCobranza().doubleValue() ) )
					.replace("--mora--", String.valueOf(wrapper.getSaldoMora().doubleValue() ) )
					.replace("--fechaExcepcion--", wrapper.getFechaRegularizacion() != null ?QuskiOroUtil.dateToString(wrapper.getFechaRegularizacion(), QuskiOroUtil.DATE_FORMAT_QUSKI): " ")
					.replace("--Observaciones--", StringUtils.isNotBlank(wrapper.getTbQoNegociacion().getObservacionAsesor())?wrapper.getTbQoNegociacion().getObservacionAsesor() :" ");
			this.mailNotificacion(array, asunto, contenido, null);
		} catch (RelativeException e) {
			e.printStackTrace();
			log.info("ERROR ========>" + QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getDetalle());
			//throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
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
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getDetalle());
		}
	}
	public TbQoProceso findProcesoByIdReferencia(Long id, ProcesoEnum proceso) throws RelativeException {
		try {
			return procesoRepository.findByIdReferencia(id, proceso);
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	public List<ProcesoDevoActivoWrapper> findProcesoByIdReferencia(String numeroOperacion) throws RelativeException {
		try {
			return procesoRepository.findDevolucionesActivas(numeroOperacion);
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
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
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.procesoRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
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
			if(send.getHoraAprobador() != null ) {
				persisted.setHoraAprobador( send.getHoraAprobador() );
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
			throw e;
		}
	}
	public ResultOperacionesWrapper findOperaciones(BusquedaOperacionesWrapper wp) throws RelativeException {
		try {
			log.info("entra con estados ====>>>>>");
			if(wp != null && wp.getEstado() != null && !wp.getEstado().isEmpty()) {
				for(EstadoProcesoEnum e : wp.getEstado()) {
					log.info("estado =="+e.toString());
				}
			}
			ResultOperacionesWrapper result = new ResultOperacionesWrapper();
			result.setOperaciones(this.procesoRepository.findOperacion( wp ) );
			result.setResult( this.procesoRepository.countOperacion( wp ));
			return result;
			 
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	public ResultOperacionesAprobarWrapper findOperacionesPorAprobar(BusquedaPorAprobarWrapper wp) throws RelativeException {
		try {
			ResultOperacionesAprobarWrapper result = new ResultOperacionesAprobarWrapper();
			result.setOperaciones( this.procesoRepository.findOperacionPorAprobar( wp )  );
			result.setResult( this.procesoRepository.countOperacionAprobar( wp ));
			return result;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getDetalle());		
		}
	}
	public TbQoProceso cambiarEstado( Long idReferencia, ProcesoEnum proceso, EstadoProcesoEnum newEstado, String usuario) throws RelativeException {
		try {
			TbQoProceso persisted = this.findProcesoByIdReferencia( idReferencia, proceso );
			if(persisted == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR EN PROCESO PARA LA REFERENCIA:" +idReferencia);
			}
			persisted.setEstadoProceso( newEstado );
			if(usuario != null ) {
				persisted.setUsuario( usuario );				
			}
			return this.manageProceso(persisted);
		}catch(RelativeException e) {
			throw e;		
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());		
		}
	}
	public String validarAprobador( Long id, ProcesoEnum proceso, String aprobador, Long idProceso) throws RelativeException {
		try {
			List<OpPorAprobarWrapper> procesoValidar = procesoRepository.buscarOperacionesAprobador(idProceso);
			if(procesoValidar == null || procesoValidar.isEmpty() || procesoValidar.get(0) == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR EN PROCESO ID:" +id);
			}
			
			return procesoValidar.get(0).getAprobador();
					
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;		
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());		
		}
	}
	public Boolean reasignarOperacion(Long id, ProcesoEnum proceso, String usuario) throws RelativeException {
		try {
			if(StringUtils.isBlank(usuario)) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL ASESOR");
			}
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
			if(proceso == ProcesoEnum.DEVOLUCION) {
				TbQoDevolucion devolucion = this.devolucionRepository.findById(id);
				devolucion.setAsesor(usuario);
				this.manageDevolucion(devolucion);
				return true;
			}
			if(proceso == ProcesoEnum.VERIFICACION_TELEFONICA) {
				
			}
			
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
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	public String asignarAprobadorExcepcion(Long id, String aprobador) throws RelativeException {
		try {
			TbQoProceso persistedProceso = this.findProcesoByIdReferencia(id, ProcesoEnum.NUEVO );
			if(persistedProceso == null) {
				persistedProceso = this.findProcesoByIdReferencia(id, ProcesoEnum.RENOVACION );
			}
			if(persistedProceso == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ, " PROCESO NO ENCONTRADO. ");
			}
			persistedProceso.setUsuario( aprobador );
			persistedProceso.setHoraAprobador( new Timestamp(System.currentTimeMillis()) );
			persistedProceso = this.manageProceso(persistedProceso);
			if(persistedProceso.getProceso() == ProcesoEnum.NUEVO || persistedProceso.getProceso() == ProcesoEnum.RENOVACION) {
				TbQoNegociacion persistedOperacion = this.findNegociacionById( id );
				if(persistedOperacion != null) {
					persistedOperacion.setAprobador(aprobador);
					this.manageNegociacion(persistedOperacion);
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
				}
			}					
			return persistedProceso.getUsuario();
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, e.getMessage());
		}
	}
	public String asignarAprobador(Long id, ProcesoEnum proceso, String aprobador, Long idProceso) throws RelativeException {
		try {
			TbQoProceso persistedProceso = procesoRepository.findById(idProceso);
			log.info("entra en persistedProceso ID: " + persistedProceso.getId());
			if(persistedProceso != null) {
				persistedProceso.setUsuario( aprobador );
				persistedProceso.setHoraAprobador( new Timestamp(System.currentTimeMillis()) );
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
				if(proceso == ProcesoEnum.DEVOLUCION || proceso == ProcesoEnum.CANCELACION_DEVOLUCION) {
					log.info("entra en devolucione");
					TbQoDevolucion persistedDevolucion  = devolucionRepository.findById(id);
					if(persistedDevolucion != null) {
						persistedDevolucion.setAprobador( aprobador );
						ds.manageDevolucion( persistedDevolucion );
					}else {
						throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
								QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
					}
				}
				if(proceso == ProcesoEnum.VERIFICACION_TELEFONICA) {}
				return cambioProceso.getUsuario();
			}else {
				return "SIN PROCESO";
			}
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	public String asignarAprobadorValidate(Long id, ProcesoEnum proceso, String aprobador, Long idProceso) throws RelativeException {
		try {
			TbQoProceso persistedProceso = procesoRepository.findById(idProceso);
			log.info("entra en persistedProceso ID: " + persistedProceso.getId());
			if(persistedProceso != null) {
				persistedProceso.setUsuario( aprobador );
				persistedProceso.setHoraAprobador( new Timestamp(System.currentTimeMillis()) );
				TbQoProceso cambioProceso = this.manageProceso(persistedProceso);
				
				if(proceso == ProcesoEnum.NUEVO || proceso == ProcesoEnum.RENOVACION) {
					TbQoNegociacion persistedOperacion = this.findNegociacionById( id );
					if(persistedOperacion != null) {
						if(StringUtils.isNotBlank(persistedOperacion.getAprobador()) && !aprobador.equals(persistedOperacion.getAprobador())) {
							return persistedOperacion.getAprobador();
						}
						persistedOperacion.setAprobador(aprobador);
						this.manageNegociacion(persistedOperacion);
					}else {
						throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
								QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
					}
				}				
				if(proceso == ProcesoEnum.PAGO) {
					TbQoClientePago persisted = this.findClientePagoById( id );
					if(StringUtils.isNotBlank(persisted.getAprobador()) && !aprobador.equals(persisted.getAprobador())) {
						return persisted.getAprobador();
					}
					persisted.setAprobador(aprobador);
					this.manageClientePago( persisted );
				}
				if(proceso == ProcesoEnum.DEVOLUCION || proceso == ProcesoEnum.CANCELACION_DEVOLUCION) {
					log.info("entra en devolucione");
					TbQoDevolucion persistedDevolucion  = devolucionRepository.findById(id);
					if(persistedDevolucion != null) {
						if(StringUtils.isNotBlank(persistedDevolucion.getAprobador()) && !aprobador.equals(persistedDevolucion.getAprobador())) {
							return persistedDevolucion.getAprobador();
						}
						persistedDevolucion.setAprobador( aprobador );
						ds.manageDevolucion( persistedDevolucion );
					}else {
						throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
								QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION);
					}
				}
				if(proceso == ProcesoEnum.VERIFICACION_TELEFONICA) {}
				return cambioProceso.getUsuario();
			}else {
				return "SIN PROCESO";
			}
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
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
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	private TbQoProceso createProcesoNovacion( Long idReferencia, String usuario) throws RelativeException {
		try {
			TbQoProceso send = new TbQoProceso( Long.valueOf( idReferencia ));
			send.setProceso( ProcesoEnum.RENOVACION );
			send.setEstadoProceso( EstadoProcesoEnum.CREADO );
			send.setUsuario( usuario );
			return this.manageProceso( send );
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	public TbQoProceso createProcesoPago( Long idReferencia, String usuario) throws RelativeException {
		try {
			TbQoProceso send = new TbQoProceso( Long.valueOf( idReferencia ));
			send.setProceso( ProcesoEnum.PAGO );
			send.setEstadoProceso( EstadoProcesoEnum.PENDIENTE_APROBACION );
			send.setUsuario( usuario );
			return this.manageProceso( send );
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	/**
	 * ************************** @TRACKING
	 */
	public List<TbQoTracking> findBusquedaParametros(TrackingWrapper wp, PaginatedWrapper pw) throws RelativeException {
		try {
			if(wp == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LOS PARAMETROS DE BUSQUEDA");
			}
			
			if (pw != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
				return this.trackingRepository.findByParams(wp,pw.getStartRecord(), pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
			}else {
				return this.trackingRepository.findByParams(wp);
			}
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
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
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
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
			
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			
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
			
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL INTENTAR CONVERTIR STRING A ENUM");
		}
	}

	public Long countTracking(TrackingWrapper wp)throws RelativeException {
		try {
			return trackingRepository.countByParamPaged(wp);
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	/**
	 * Notificacion popr email. Codigo Cash
	 * @param persisted
	 * @param cash
	 * @param descripcion
	 * @param codigo
	 * @throws RelativeException
	 */
	public void enviarCorreoCashNuevo(TbQoCreditoNegociacion persisted, String cash, String descripcion, String codigo) throws RelativeException {
		try {
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.APROBACION_ASUNTO ).getValor()					
						.replace("--codigoCash--", cash)
						.replace("--cedula--", persisted.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
						.replace("--banco--", "-")
						.replace("--numeroCuenta--", "-")
						.replace("--numeroTransaccion--", "-")
						.replace("--tipoCuenta--", "-")
						.replace("--valor--", persisted.getaRecibirCliente().toString() );
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.APROBACION_CONTENIDO ).getValor()
						.replace("--codigoCash--", cash)
						.replace("--cedula--", persisted.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
						.replace("--banco--", "-")
						.replace("--numeroCuenta--", "-")
						.replace("--numeroTransaccion--", "-")
						.replace("--tipoCuenta--", "-")
						.replace("--valor--", persisted.getaRecibirCliente().toString() );
				String[] listCorreos = {persisted.getTbQoNegociacion().getCorreoAsesor()};
				this.mailNotificacion( listCorreos, asunto, contenido,  null);
		}catch ( Exception e) {
			//e.printStackTrace();
			log.info("NO SE ENVIO CORREO CASH:" + "cash");
			//throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " DESCONOCIDO EN METODO devolverAprobarCredito() => " + e.getMessage() );
		}
	}
	public void enviarCodigoCashRenovacion(TbQoCreditoNegociacion persisted, String cash, String descripcion, String codigo, BigDecimal valorCash) throws RelativeException {
		try {
				String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.APROBACION_ASUNTO ).getValor()					
						.replace("--codigoCash--", cash)
						.replace("--cedula--", persisted.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
						.replace("--banco--", "-")
						.replace("--numeroCuenta--", "-")
						.replace("--numeroTransaccion--", "-")
						.replace("--tipoCuenta--", "-")
						.replace("--valor--",  valorCash != null ? valorCash.toString(): "0" );
				String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.APROBACION_CONTENIDO ).getValor()
						.replace("--codigoCash--", cash)
						.replace("--cedula--", persisted.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
						.replace("--banco--", "-")
						.replace("--numeroCuenta--", "-")
						.replace("--numeroTransaccion--", "-")
						.replace("--tipoCuenta--", "-")
						.replace("--valor--", valorCash != null ? valorCash.toString(): "0"  );
				String[] listCorreos = {persisted.getTbQoNegociacion().getCorreoAsesor()};
				this.mailNotificacion( listCorreos, asunto, contenido,  null);
		}catch ( Exception e) {
			//e.printStackTrace();
			//throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " DESCONOCIDO EN METODO devolverAprobarCredito() => " + e.getMessage() );
			log.info("NO SE ENVIO CODIGO CASH:" + cash);
		}
	}


	public Boolean negarExcepcion(Long idExc, String obsAprobador, String aprobador, ProcesoEnum tipoProceso, String autorizacion)  throws RelativeException {
		try {
			TbQoExcepcion exc = this.finExcepcionById(idExc);
			if(exc == null) {
				return false; 
			}
			TbQoProceso proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), tipoProceso);
			TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion(exc.getTbQoNegociacion().getId());
			if(proceso == null) {
				return null; 
			}
			exc.setEstadoExcepcion( EstadoExcepcionEnum.NEGADO );
			exc.setIdAprobador(aprobador);
			exc.setObservacionAprobador( obsAprobador );
			exc = this.manageExcepcion(exc);
			if(exc == null) {
				return false; 
			}
			proceso.setEstadoProceso( EstadoProcesoEnum.EXCEPCIONADO );
			proceso.setUsuario(aprobador);
			proceso = this.manageProceso(proceso);
			this.mailExcepcion(exc, credito, proceso, autorizacion);
			if(proceso == null) {
				return false; 
			} else {
				return true; 
			}
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	public Boolean aprobarCobertura(Long idExc, String obsAprobador, String aprobador, String cobertura, ProcesoEnum procesoTipo, String autorizacion) throws RelativeException {
		try {
			TbQoExcepcion exc = this.finExcepcionById(idExc);
			if(exc == null) {
				return false; 
				}
			TbQoProceso proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), procesoTipo);
			if(proceso == null) {
				return false; 
				}
			TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion( exc.getTbQoNegociacion().getId() );
			if(credito == null) {
				return false; 
				}
			credito.setCobertura( cobertura );
			credito = this.manageCreditoNegociacion( credito );
			if( credito == null) {
				return false; 
				}
			exc.setEstadoExcepcion( EstadoExcepcionEnum.APROBADO );
			exc.setIdAprobador(aprobador);
			exc.setObservacionAprobador( obsAprobador );
			exc = this.manageExcepcion(exc);
			if(exc == null) {
				return false; 
				}
			proceso.setEstadoProceso( EstadoProcesoEnum.EXCEPCIONADO );
			proceso.setUsuario(aprobador);
			proceso = this.manageProceso(proceso);
			
			this.mailExcepcion(exc, credito, proceso, autorizacion);
			if(proceso == null) {
				return false; 
			} else { 
				return true; 
			}
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	public TbQoExcepcion excepcionCliente(Long id, String obsAprobador, String aprobador, String aprobado, String autorizacion) throws RelativeException {
		try {
			TbQoExcepcion exc = this.finExcepcionById(id);
			TbQoProceso proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO);
			TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion(exc.getTbQoNegociacion().getId());
			if(proceso == null) {
				proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION);
			}
			if(proceso == null || !proceso.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_EXCEPCION)) { 
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR EL PROCESO INTENTE MAS TARDE");
			}
			exc.setEstadoExcepcion( aprobado.equalsIgnoreCase("SI")?EstadoExcepcionEnum.APROBADO:EstadoExcepcionEnum.NEGADO );
			exc.setIdAprobador(aprobador);
			exc.setObservacionAprobador( obsAprobador );
			proceso.setEstadoProceso( EstadoProcesoEnum.EXCEPCIONADO );
			proceso.setUsuario(aprobador);
			this.manageProceso(proceso);
			exc =  manageExcepcion(exc);
			this.mailExcepcion(exc, credito, proceso, autorizacion);
			return exc;
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	public ConsultaGlobalRespuestaWrapper buscarCreditos(ConsultaGlobalWrapper wrapper, String autorizacion) throws RelativeException {
		return SoftBankApiClient.callConsultaGlobalRest(this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GLOBAL).getValor(),autorizacion, wrapper);
	
	}
	public RenovacionWrapper buscarRenovacionOperacionMadre( String numeroOperacion, String autorizacion ) throws RelativeException{
		try {
			DetalleCreditoWrapper detalle = this.traerCreditoVigente( numeroOperacion, autorizacion );
			if( detalle == null ) { 
				throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION + " DE SOFTBANK.");
			}
			return new RenovacionWrapper( detalle ); 
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION );
		}
	}
	
	public RenovacionWrapper buscarRenovacionNegociacion( Long idNegociacion, String autorizacion ) throws RelativeException{
		try {
			TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
			if(credito == null) { 
				throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION); 
			}
			DetalleCreditoWrapper detalle = this.traerCreditoVigente( credito.getNumeroOperacionAnterior() , autorizacion);
			if( detalle == null ) { 
				throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION + " DE SOFTBANK.");
			}
			RenovacionWrapper novacion = new RenovacionWrapper( detalle ); 
			novacion.setCredito( credito );
			novacion.setProceso( this.procesoRepository.findByIdReferencia(credito.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION ));
			novacion.setExcepciones( this.excepcionesRepository.findByIdNegociacion( credito.getTbQoNegociacion().getId() ));
			novacion.setTasacion( this.tasacionRepository.findByIdCredito( credito.getId() ));
			novacion.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion(idNegociacion));
			novacion.setRiesgos( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() , autorizacion), credito.getTbQoNegociacion(), null) );
			novacion.setCuentas( this.cuentaBancariaRepository.findByIdCliente( credito.getTbQoNegociacion().getTbQoCliente().getId() ));
			novacion.setPagos(mapPagos(this.registrarPagoRepository.findByIdCredito(credito.getId()), autorizacion) );
			return novacion;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);
		}
	}
	
	List<RegistroPagoRenovacionWrapper> mapPagos(List<TbQoRegistrarPago> registroPago, String autorizacion) throws RelativeException{
		List<RegistroPagoRenovacionWrapper>  list = new ArrayList<>();
		if( registroPago == null || registroPago.size() < 1 ) {
			return null;
		}
		for(TbQoRegistrarPago pago :  registroPago) {
			RegistroPagoRenovacionWrapper p = new RegistroPagoRenovacionWrapper();
			ArchivoComprobanteWrapper comprobante = null;
			try {
				if(StringUtils.isNotBlank(pago.getIdComprobante())) {
					String urlService = parametroRepository.findByNombre(QuskiOroConstantes.URL_STORAGE).getValor();
					String databaseName = parametroRepository.findByNombre(QuskiOroConstantes.DATA_BASE_NAME).getValor();
					String collectionName = parametroRepository.findByNombre(QuskiOroConstantes.COLLECTION_NAME).getValor();
					RespuestaObjectWrapper objeto = LocalStorageClient.findObjectById(urlService, databaseName, collectionName, pago.getIdComprobante(), autorizacion);
					Gson gsons = new GsonBuilder().create();
					FileObjectStorage file = gsons.fromJson( new String(Base64.getDecoder().decode(objeto.getEntidad()) ), FileObjectStorage.class);
					comprobante = new ArchivoComprobanteWrapper();
					comprobante.setFileBase64(file.getFileBase64());
					comprobante.setName(file.getName());
					comprobante.setProcess(file.getProcess() != null ?file.getProcess().toString(): null);
					comprobante.setRelatedId(file.getRelatedId());
					comprobante.setRelatedIdStr(file.getRelatedIdStr());
					comprobante.setType(file.getType());
					comprobante.setTypeAction(StringUtils.isNotBlank(file.getTypeAction())? Long.valueOf(file.getTypeAction()): null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			p.setComprobante(comprobante);
			p.setCuenta(pago.getCuentas());
			p.setFechaPago(pago.getFechaPago());
			p.setIntitucionFinanciera(pago.getInstitucionFinanciera());
			p.setNumeroDeposito(pago.getNumeroDeposito());
			p.setTipoPago(pago.getTipoPago());
			p.setValorDepositado(pago.getValorPagado());
			list.add(p);
		}
		return list;
		
	}
	
	public Informacion informacionClienteRenovacion(String cedula) throws RelativeException, UnsupportedEncodingException {
			TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
					this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
			String content = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_PERSONA).getValor()
					.replace("--tipoidentificacion--", "C")
					.replace("--identificacion--", cedula)
					.replace("--tipoconsulta--", "CC")
					.replace("--calificacionmupi--","N");
			return ApiGatewayClient.callConsultarClienteEquifaxRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_PERSONA).getValor(),
					token.getToken_type()+" "+token.getAccess_token(), content);
	}
	
	/**
	 * ACTUALIZA LAS GARANTIAS EN SOFBANK
	 * @param tasacion
	 * @param numeroOperacionMadre
	 * @param asesor
	 * @throws RelativeException
	 */
	private void actualizarGarantiasSoftBank(List<TbQoTasacion> tasacion, String numeroOperacionMadre,String numeroOperacion,Boolean esAprobacion, String asesor, String autorizacion) throws RelativeException {
		try {
			if(tasacion != null && !tasacion.isEmpty()) {
				ActualizarGaratiaWrapper wr = new ActualizarGaratiaWrapper();
				wr.setCodigoUsuario(asesor);
				wr.setFechaAvaluo(QuskiOroUtil.dateToString(new Date(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));
				wr.setNumeroOperacionMadre(numeroOperacionMadre);
				wr.setNumeroOperacion(numeroOperacion);
				wr.setEsAprobacion(esAprobacion);
				List<GaratiaAvaluoWrapper> avaluo = new ArrayList<>();
				for(TbQoTasacion g : tasacion) {
					GaratiaAvaluoWrapper garantia = new GaratiaAvaluoWrapper();
					garantia.setFechaAvaluo(QuskiOroUtil.dateToString(new Date(), QuskiOroUtil.DATE_FORMAT_SOFTBANK));
					garantia.setNumeroExpediente(g.getNumeroExpediente());
					garantia.setNumeroGarantia(g.getNumeroGarantia());
					garantia.setValorAvaluo(g.getValorAvaluo());
					garantia.setValorComercial(g.getValorComercial());
					garantia.setValorOro(g.getValorOro());
					garantia.setValorRealizacion(g.getValorRealizacion());
					avaluo.add(garantia);
				}
				wr.setGarantiaAvaluo(avaluo);
				SoftBankApiClient.upDateGarantia(wr,autorizacion, this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_ACTUALIZAR_GARANTIA).getValor());
				
			}
		} catch (RelativeException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ERROR AL EDITAR GARANTIAS EN SOFTBANK | "+ e.getMessage());
		}
		
	}
	
	public RenovacionWrapper crearRenovacionApp(OpcionAndGarantiasWrapper wp, String numeroOperacion, 
			String asesor, Long idAgencia, String numeroOperacionMadre, String autorizacion,
			String nombreAgencia, String telefonoAsesor, String nombreAsesor, String correoAsesor) throws RelativeException {
		List<EstadoProcesoEnum> estados = new ArrayList<>();
		//estados.add( EstadoProcesoEnum.CREADO );
		estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION );
		estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO );
		//estados.add( EstadoProcesoEnum.EXCEPCIONADO );
		//estados.add( EstadoProcesoEnum.PENDIENTE_EXCEPCION );
		//estados.add( EstadoProcesoEnum.DEVUELTO );
		if(this.procesoRepository.findByNumeroOperacionMadreAndProcesoAndEstado(numeroOperacionMadre,  ProcesoEnum.RENOVACION, estados) > 0 ) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"LA RENOVACION ESTA PENDIENTE DE APROBACION"+ numeroOperacionMadre);
		}
		estados = new ArrayList<>();
		estados.add( EstadoProcesoEnum.CREADO );
		//estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION );
		//estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO );
		estados.add( EstadoProcesoEnum.EXCEPCIONADO );
		//estados.add( EstadoProcesoEnum.PENDIENTE_EXCEPCION );
		estados.add( EstadoProcesoEnum.DEVUELTO );
		TbQoCreditoNegociacion credito = this.procesoRepository.findRenovacionByNumeroOperacionMadreAndEstado( numeroOperacionMadre, estados);

		log.info("==================>>>>>> valido operacion " + credito);
		DetalleCreditoWrapper detalle = this.traerCreditoVigente( numeroOperacion, autorizacion );
		RenovacionWrapper novacion  = new RenovacionWrapper(detalle);		
		TbQoCliente cliente = this.clienteRepository.findClienteByIdentificacion( novacion.getOperacionAnterior().getCliente().getIdentificacion());
		if( cliente == null) {
			cliente = this.clienteSoftToTbQoCliente( novacion.getOperacionAnterior().getCliente() );
			cliente = this.manageCliente(cliente);
		}
		
		TbQoNegociacion negociacion = null;
		if( credito == null) {
			credito = new TbQoCreditoNegociacion();
			negociacion = new TbQoNegociacion();
		
		}else {
			negociacion = credito.getTbQoNegociacion();
		}
		negociacion.setAsesor(asesor);
		negociacion.setEstado( EstadoEnum.ACT);
		negociacion.setTbQoCliente( cliente );
		negociacion.setTelefonoAsesor(telefonoAsesor);
		negociacion.setNombreAsesor(nombreAsesor);
		negociacion.setCorreoAsesor(correoAsesor);
		negociacion = this.manageNegociacion(negociacion);
		credito = mapOpcionCredito(wp, numeroOperacion, numeroOperacionMadre, idAgencia, autorizacion,
				nombreAgencia, credito);
		credito.setTbQoNegociacion( negociacion );
		credito = this.manageCreditoNegociacionApp(credito);
		
		
		novacion.setCredito( credito );
		TbQoProceso proceso = this.findProcesoByIdReferencia(negociacion.getId(), ProcesoEnum.RENOVACION);
		if( proceso == null) {
			proceso =this.createProcesoNovacion(negociacion.getId(), asesor);
		
		}
		novacion.setProceso( proceso );
		
		if( novacion.getOperacionAnterior().getGarantias() != null ) {
			List<TbQoTasacion> tasacion =  this.createTasacionByVigente( novacion.getOperacionAnterior().getGarantias(), credito);
			//actualizarGarantiasSoftBank( tasacion, credito.getNumeroOperacionMadre(),asesor, autorizacion);
			novacion.setTasacion(tasacion);					
		}
		
		novacion.setVariables(this.createVariablesFromEquifax(wp.getVariablesInternas(), negociacion));
		novacion.setRiesgos( this.manageListRiesgoAcumulados( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( cliente.getCedulaCliente() , autorizacion), negociacion, null) ) );
	
		
		
		return novacion;
	}
	public RenovacionWrapper crearCreditoRenovacion(OpcionAndGarantiasWrapper wp, String numeroOperacion, Long idNego,
			String asesor, Long idAgencia, String numeroOperacionMadre, String autorizacion,
			String nombreAgencia, String telefonoAsesor, String nombreAsesor, String correoAsesor) throws RelativeException {
		try {
			
			RenovacionWrapper novacion;
			if(idNego == null) {
				List<EstadoProcesoEnum> estados = new ArrayList<>();
				estados.add( EstadoProcesoEnum.CREADO );
				estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION );
				estados.add( EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO );
				estados.add( EstadoProcesoEnum.EXCEPCIONADO );
				estados.add( EstadoProcesoEnum.PENDIENTE_EXCEPCION );
				estados.add( EstadoProcesoEnum.DEVUELTO );
				if(this.procesoRepository.findByNumeroOperacionMadreAndProcesoAndEstado(numeroOperacionMadre,  ProcesoEnum.RENOVACION, estados) > 0 ) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"YA EXISTE UN PROCESO DE RENOVACION ACTIVO PARA EL NUMERO DE OPERACION MADRE: "+ numeroOperacionMadre);
				}
				novacion = this.buscarRenovacionOperacionMadre(numeroOperacion, autorizacion);		
				TbQoCliente cliente = this.clienteRepository.findClienteByIdentificacion( novacion.getOperacionAnterior().getCliente().getIdentificacion());
				if( cliente == null) {
					cliente = this.clienteSoftToTbQoCliente( novacion.getOperacionAnterior().getCliente() );
					cliente = this.manageCliente(cliente);
				}
				TbQoNegociacion negociacion = new TbQoNegociacion();
				negociacion.setAsesor(asesor);
				negociacion.setEstado( EstadoEnum.ACT);
				negociacion.setTbQoCliente( cliente );
				negociacion.setTelefonoAsesor(telefonoAsesor);
				negociacion.setNombreAsesor(nombreAsesor);
				negociacion.setCorreoAsesor(correoAsesor);
				negociacion = this.manageNegociacion(negociacion);
				TbQoCreditoNegociacion credito = this.createCreditoNovacion(wp, numeroOperacion, numeroOperacionMadre,  idAgencia, null, autorizacion, nombreAgencia);
				credito.setTbQoNegociacion( negociacion );
				credito = this.manageCreditoNegociacion(credito);
				credito = this.crearCodigoRenovacion(credito);
				novacion.setCredito( credito );
				novacion.setProceso( this.createProcesoNovacion(negociacion.getId(), asesor) );
				if( wp.getGarantias() != null ) {
					List<TbQoTasacion> tasacion =  this.createTasacionByGarantia(wp.getGarantias(), novacion.getOperacionAnterior().getGarantias(), credito);
					//actualizarGarantiasSoftBank( tasacion, credito.getNumeroOperacionMadre(),asesor, autorizacion);
					novacion.setTasacion(tasacion);					
				}
				
				novacion.setVariables(this.createVariablesFromEquifax(wp.getVariablesInternas(), negociacion));
				novacion.setRiesgos( this.manageListRiesgoAcumulados( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( cliente.getCedulaCliente() , autorizacion), negociacion, null) ) );
			}else {
				novacion = this.buscarRenovacionNegociacion(idNego, autorizacion);				
				log.info( "============> ACTUALIZANDO CREDITO <============");
				novacion.getCredito().getTbQoNegociacion().setAsesor(asesor);
				novacion.getCredito().getTbQoNegociacion().setNombreAsesor(nombreAsesor);
				novacion.getCredito().getTbQoNegociacion().setCorreoAsesor(correoAsesor);
				novacion.getCredito().getTbQoNegociacion().setTelefonoAsesor(telefonoAsesor);
				novacion.getCredito().setTbQoNegociacion( this.manageNegociacion( novacion.getCredito().getTbQoNegociacion()));
				novacion.setCredito( this.manageCreditoNegociacion( this.createCreditoNovacion(wp, numeroOperacion, numeroOperacionMadre, idAgencia, novacion.getCredito().getId(), autorizacion,nombreAgencia)));
				if(  wp.getGarantias() != null ) {
					List<TbQoTasacion> tasacion = this.createTasacionByGarantia( wp.getGarantias(), novacion.getOperacionAnterior().getGarantias(), novacion.getCredito() ); 
					//actualizarGarantiasSoftBank(tasacion,novacion.getCredito().getNumeroOperacionMadre(),asesor, autorizacion);
					novacion.setTasacion( tasacion);
				}
				this.variablesCrediticiaRepository.deleteVariablesByNegociacionId(novacion.getCredito().getTbQoNegociacion().getId());
				novacion.setVariables(this.createVariablesFromEquifax(wp.getVariablesInternas(), novacion.getCredito().getTbQoNegociacion()));
				this.riesgoAcumuladoRepository.deleteByIdNegociacion(idNego);
				novacion.setRiesgos( this.manageListRiesgoAcumulados( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( novacion.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente() , autorizacion), novacion.getCredito().getTbQoNegociacion(), null) ) );
			}
			return novacion;
		}catch(RelativeException e) {
			//e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_CREATE_NOVACION );
		}
	}

	private List<TbQoTasacion> createTasacionByGarantia(List<Garantia> garantias, List<GarantiaOperacionWrapper> asd, TbQoCreditoNegociacion credito) throws RelativeException {
		List<TbQoTasacion> listTasacion = new ArrayList<>();
		try {
			this.tasacionRepository.deleteTasacionByNegociacionId(credito.getId());
			log.info( "============> DELETE JOYA <============");

		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
		for(Garantia s : garantias) {
			TbQoTasacion tasacion = new TbQoTasacion();
			tasacion.setNumeroGarantia( Long.valueOf(s.getNumeroGarantia() ) );
			tasacion.setNumeroExpediente( Long.valueOf(s.getNumeroExpediente() ) );
			//tasacion.setTipoGarantia( s.getTipoJoya() );
			//tasacion.setSubTipoGarantia( s.tipo );
			tasacion.setEstado(EstadoEnum.ACT );
			tasacion.setDescripcion( s.getDescripcion() );
			tasacion.setDescuentoPesoPiedra( BigDecimal.valueOf( s.getDescuentoPesoPiedras() ) );
			tasacion.setDescuentoSuelda( BigDecimal.valueOf(s.getDescuentoSuelda()) );
			tasacion.setEstadoJoya( s.getEstadoJoya() );
			tasacion.setNumeroPiezas( Long.valueOf( s.getNumeroPiezas() ) );
			tasacion.setPesoBruto(BigDecimal.valueOf( s.getPesoGr()) );
			tasacion.setPesoNeto( BigDecimal.valueOf( s.getPesoNeto()) );
			tasacion.setTipoJoya( s.getTipoJoya() );
			tasacion.setValorAvaluo( BigDecimal.valueOf( s.getValorAvaluo() ) );
			tasacion.setValorComercial( BigDecimal.valueOf( s.getValorAplicable() ) );
			tasacion.setValorOro( BigDecimal.valueOf( s.getValorOro() ) );
			tasacion.setValorRealizacion(BigDecimal.valueOf( s.getValorRealizacion() ) );
			tasacion.setTbQoCreditoNegociacion( credito );
			tasacion.setTipoOro( s.getTipoOroKilataje() );
			tasacion.setTienePiedras(StringUtils.isNotBlank(s.getTienePiedras()) && s.getTienePiedras().equalsIgnoreCase("S")? Boolean.TRUE: Boolean.FALSE );
			tasacion.setDetallePiedras( s.getDetallePiedras() );
			tasacion.setTbQoCreditoNegociacion(credito);
			listTasacion.add( this.manageTasacion(tasacion) );
			
		}
		
		return listTasacion;
		
	}
	private List<TbQoTasacion> createTasacionByVigente(List<GarantiaOperacionWrapper> garantias, TbQoCreditoNegociacion credito) throws RelativeException {
		List<TbQoTasacion> listTasacion = new ArrayList<>();
		try {
			this.tasacionRepository.deleteTasacionByNegociacionId(credito.getId());
			log.info( "============> DELETE JOYA <============");

		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
		for(GarantiaOperacionWrapper s : garantias) {
			TbQoTasacion tasacion = new TbQoTasacion();
			tasacion.setNumeroGarantia( Long.valueOf(s.getNumeroGarantia() ) );
			tasacion.setNumeroExpediente( Long.valueOf(s.getNumeroExpediente() ) );
			//tasacion.setTipoGarantia( s.getTipoJoya() );
			//tasacion.setSubTipoGarantia( s.tipo );
			tasacion.setEstado(EstadoEnum.ACT );
			tasacion.setDescripcion( s.getDescripcion() );
			tasacion.setDescuentoPesoPiedra( s.getDescuentoPiedras()  );
			tasacion.setDescuentoSuelda(s.getDescuentoSuelda() );
			tasacion.setEstadoJoya( s.getCodigoEstadoJoya() );
			tasacion.setNumeroPiezas( s.getNumeroPiezas()  );
			tasacion.setPesoBruto(s.getPesoBruto() );
			tasacion.setPesoNeto( s.getPesoNeto() );
			tasacion.setTipoJoya( s.getCodigoTipoGarantia() );
			tasacion.setValorAvaluo(  s.getValorAvaluo()  );
			tasacion.setValorComercial( s.getValorComercial()  );
			tasacion.setValorOro(  s.getValorOro()  );
			tasacion.setValorRealizacion(s.getValorRealizacion()  );
			tasacion.setTipoOro( s.getCodigoTipoOro() );
			tasacion.setTienePiedras(s.getTienePiedras());
			tasacion.setDetallePiedras( s.getDetallePiedras() );
			tasacion.setTbQoCreditoNegociacion(credito);
			listTasacion.add( this.manageTasacion(tasacion) );
			
		}
		
		return listTasacion;
		
	}
	private TbQoCreditoNegociacion createCreditoNovacion(OpcionAndGarantiasWrapper wp, String numeroOperacionAnterior, String numeroOperacionMadre, 
			Long idAgencia, Long id, String autorizacion, String nombreAgencia) throws RelativeException {
		TbQoCreditoNegociacion credito;						
		if(id != null) {
			credito = this.findCreditoNegociacionById(id);
			if(credito == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA );
				}
			
		}else {
			credito = new TbQoCreditoNegociacion();
		}
		return mapOpcionCredito(wp, numeroOperacionAnterior, numeroOperacionMadre, idAgencia, autorizacion,
				nombreAgencia, credito);
	}

	/**
	 * @param wp
	 * @param numeroOperacionAnterior
	 * @param numeroOperacionMadre
	 * @param idAgencia
	 * @param autorizacion
	 * @param nombreAgencia
	 * @param credito
	 * @return
	 * @throws RelativeException
	 */
	private TbQoCreditoNegociacion mapOpcionCredito(OpcionAndGarantiasWrapper wp, String numeroOperacionAnterior,
			String numeroOperacionMadre, Long idAgencia, String autorizacion, String nombreAgencia,
			TbQoCreditoNegociacion credito) throws RelativeException {
		if(wp.getOpcion() != null) {
			credito.setNombreAgencia(nombreAgencia);
			credito.setaPagarCliente( BigDecimal.valueOf( wp.getOpcion().getValorAPagar() ));
			credito.setMontoFinanciado( BigDecimal.valueOf(wp.getOpcion().getMontoFinanciado()) );
			credito.setPlazoCredito( Long.valueOf(wp.getOpcion().getPlazo()) );
			credito.setValorCuota( BigDecimal.valueOf(wp.getOpcion().getCuota() ));
			credito.setCostoCustodia( BigDecimal.valueOf(wp.getOpcion().getCostoCustodia()));
			credito.setCostoFideicomiso(BigDecimal.valueOf( wp.getOpcion().getCostoFideicomiso()));
			credito.setCostoSeguro(BigDecimal.valueOf( wp.getOpcion().getCostoSeguro()));
			credito.setCostoTasacion( BigDecimal.valueOf( wp.getOpcion().getCostoTasacion() ) );
			credito.setCostoTransporte( BigDecimal.valueOf( wp.getOpcion().getCostoTransporte()) );
			credito.setCostoValoracion( BigDecimal.valueOf( wp.getOpcion().getCostoValoracion()) );
			credito.setCuota( BigDecimal.valueOf(wp.getOpcion().getCuota() ));
			credito.setCustodiaDevengada( BigDecimal.valueOf(wp.getOpcion().getCustodiaDevengada() ));
			credito.setDividendoFlujoPlaneado( BigDecimal.valueOf(wp.getOpcion().getDIVIDENDOFLUJOPLANEADO() ));
			credito.setDividendoProrrateo( BigDecimal.valueOf(wp.getOpcion().getDIVIDENDOSPRORRATEOSERVICIOSDIFERIDO() ));
			credito.setFormaPagoCapital( wp.getOpcion().getFormaPagoCapital());
			credito.setFormaPagoCustodia( wp.getOpcion().getFormaPagoCustodia() );
			credito.setFormaPagoCustodiaDevengada( wp.getOpcion().getFormaPagoCustodiaDevengada() );
			credito.setFormaPagoFideicomiso( wp.getOpcion().getFormaPagoFideicomiso() );
			credito.setFormaPagoGastoCobranza( wp.getOpcion().getFormaPagoGastoCobranza() );
			credito.setFormaPagoImpuestoSolca( wp.getOpcion().getFormaPagoImpuestoSolca() );
			credito.setFormaPagoInteres( wp.getOpcion().getFormaPagoInteres() );
			credito.setFormaPagoMora( wp.getOpcion().getFormaPagoMora() );
			credito.setFormaPagoSeguro( wp.getOpcion().getFormaPagoSeguro() );
			credito.setFormaPagoTasador( wp.getOpcion().getFormaPagoTasador() );
			credito.setFormaPagoTransporte( wp.getOpcion().getFormaPagoTransporte() );
			credito.setFormaPagoValoracion( wp.getOpcion().getFormaPagoValoracion() );
			credito.setGastoCobranza(  BigDecimal.valueOf(wp.getOpcion().getGastoCobranza()) );
			credito.setImpuestoSolca(  BigDecimal.valueOf(wp.getOpcion().getImpuestoSolca()) );
			//credito.setaRecibirCliente( BigDecimal.valueOf( wp.getOpcion().getValorARecibir()) );
			credito.setMontoPrevioDesembolso(  BigDecimal.valueOf(wp.getOpcion().getMontoPrevioDesembolso() ));
			credito.setPeriodicidadPlazo( wp.getOpcion().getPeriodicidadPlazo() );
			credito.setPeriodoPlazo( wp.getOpcion().getPeriodoPlazo() );
			credito.setPorcentajeFlujoPlaneado(  BigDecimal.valueOf(wp.getOpcion().getPORCENTAJEFLUJOPLANEADO()) );
			credito.setSaldoCapitalRenov(  BigDecimal.valueOf(wp.getOpcion().getSaldoCapitalRenov()) );
			credito.setSaldoInteres(  BigDecimal.valueOf(wp.getOpcion().getSaldoInteres()) );
			credito.setSaldoMora(  BigDecimal.valueOf(wp.getOpcion().getSaldoMora() ));
			credito.setTipoOferta( wp.getOpcion().getTIPOOFERTA() );
			credito.setTotalCostosOperacionAnterior( BigDecimal.valueOf( wp.getOpcion().getTotalCostosOperacionAnterior()) );
			credito.setTotalGastosNuevaOperacion( BigDecimal.valueOf( wp.getOpcion().getTotalGastosNuevaOperacion()));
			credito.setValorAPagar( BigDecimal.valueOf( wp.getOpcion().getValorAPagar() ));
			credito.setValorARecibir(BigDecimal.valueOf( wp.getOpcion().getValorARecibir()));
			List<CatalogoTablaAmortizacionWrapper>  listTablas =  SoftBankApiClient.callCatalogoTablaAmortizacionRest( this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TABLA_AMOTIZACION).getValor(), autorizacion);
			credito.setAbonoCapital(BigDecimal.valueOf(wp.getOpcion().getAbonoCapital()));
			credito.setFormaPagoAbonoCapital(wp.getOpcion().getFormaPagoAbonoCapital());
			if(listTablas != null && !listTablas.isEmpty()) {
				listTablas.forEach(e->{
					if(e.getCodigo().equalsIgnoreCase(wp.getOpcion().getCodigoTabla())) {
						credito.setNumeroCuotas(e.getNumeroCuotas());
					}
					
				});
			}
			credito.setTablaAmortizacion( wp.getOpcion().getCodigoTabla() );
		}
		credito.setIdAgencia( idAgencia );
		//credito.setcobertura();
		//credito.setpagoDia( );
		//credito.setmontoSolicitado();
		//credito.setriesgoTotalCliente( opcion.get);
		credito.setTipoCliente(wp.getTipoCliente());
		credito.setNombreCompletoApoderado(wp.getNombreApoderado());
		credito.setNombreCompletoCodeudor(wp.getNombreCodeudor());
		credito.setIdentificacionApoderado(wp.getIdentificacionApoderado());
		credito.setIdentificacionCodeudor(wp.getIdentificacionCodeudor());
		credito.setFechaNacimientoApoderado(wp.getFechaNacimientoApoderado());
		credito.setFechaNacimientoCodeudor(wp.getFechaNacimientoCodeudor());
		credito.setEstado( EstadoEnum.ACT );
		credito.setNumeroOperacionMadre(numeroOperacionMadre);
		credito.setNumeroOperacionAnterior( numeroOperacionAnterior );
		credito.setNombreAgencia(nombreAgencia);
		return credito;
	}
	private TbQoCreditoNegociacion crearCodigoRenovacion(TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			persisted.setCodigo(procesoRepository.generarSecuencia(QuskiOroConstantes.CODIGO_RENOVACION));
			return this.creditoNegociacionRepository.update(persisted);
		} catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	public String generarCodigo(String codigo) throws RelativeException {
		return procesoRepository.generarSecuencia(codigo);
	}
	public String traerNumeroOperacionMadre(String codigoBpm)  throws RelativeException {
		try {
			TbQoCreditoNegociacion nego = this.creditoNegociacionRepository.findCreditoByCodigoBpm( codigoBpm );
			if(nego != null && nego.getNumeroOperacionMadre() != null) {
				return nego.getNumeroOperacionMadre();
			}else { return null; }
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}

	public TbQoProceso aprobarNuevo(Long idCredito, String descripcion, String cash, String codigoMotivo, Long agencia,
			String usuario, Boolean aprobar, String autorizacion) throws RelativeException {
		try {
			TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findById(idCredito);
			if( credito == null || StringUtils.isBlank( credito.getNumeroOperacion())) {
				throw new RelativeException( Constantes.ERROR_CODE_READ, " NO EXISTE EL CREDITO O EL NUMERO DE OPERACION"); 
			}
			if(StringUtils.isBlank(descripcion)) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA DESCRIPCION");
			}
			TbQoProceso persisted = this.findProcesoByIdReferencia( credito.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO );
			if(persisted == null || 
					(persisted.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION 
					&& persisted.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO )) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"LA OPERACION NO SE ENCUENTRA DISPONIBLE O YA FUE PROCESADA");
			}

			credito.setCodigoDevuelto(codigoMotivo);
			credito.setDescripcionDevuelto(descripcion);
			this.guardaraObservacion(descripcion, credito, usuario);
			if(aprobar) {
				if(StringUtils.isBlank(cash)) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER CODIGO CASH");
				}
				AprobarWrapper ap = new AprobarWrapper();
				ap.setNumeroOperacion( credito.getNumeroOperacion() );
				TbQoDocumentoHabilitante doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("16"),
						ProcessEnum.NUEVO, String.valueOf(credito.getTbQoNegociacion().getId()));
				if(doc != null && StringUtils.isNotBlank(doc.getObjectId())) {
					ap.setUriHabilitantesFirmados(doc.getObjectId());
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS FIRMADOS");
				}
				
				TbQoDocumentoHabilitante docAutorizacion = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("18"),
						ProcessEnum.NUEVO, String.valueOf(credito.getTbQoNegociacion().getId()));
				if(docAutorizacion != null && StringUtils.isNotBlank(docAutorizacion.getObjectId())) {
					TbQoDocumentoHabilitante docAut = new TbQoDocumentoHabilitante();
					TbQoTipoDocumento tipoDoc = new TbQoTipoDocumento();
					tipoDoc.setId(Long.valueOf("19"));
					docAut.setId(null);
					docAut.setIdReferencia(credito.getNumeroOperacion());
					docAut.setTbQoTipoDocumento(tipoDoc);
					docAut.setProceso(ProcessEnum.AUTORIZACION);
					docAut.setArchivo(docAutorizacion.getArchivo());
					docAut.setEstado(docAutorizacion.getEstado());
					docAut.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					docAut.setNombreArchivo(docAutorizacion.getNombreArchivo());
					docAut.setObjectId(docAutorizacion.getObjectId());
					this.manageDocumentoHabilitante(docAut);
					
					
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER AUTORIZACION DE BURO");
				}
				ap.setDatosRegistro( new DatosRegistroWrapper( usuario, agencia, QuskiOroUtil.dateToString(new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT), null, credito.getCodigo() ) );
				credito.setCodigoCash(cash);
				
				//FIN APROBACION
				TbQoTracking last = this.trackingRepository.findByParams(credito.getCodigo(),ProcesoEnum.NUEVO);
				if(last != null) {
					last.setFechaFin(new Timestamp(System.currentTimeMillis()));
					this.manageTracking(last);
				}

				this.manageCreditoNegociacion(credito);
				persisted =  this.cambiarEstado(credito.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO, EstadoProcesoEnum.APROBADO, null);
				ap.setCodigoCash(cash);
				RespuestaAprobarWrapper result = SoftBankApiClient.callAprobarRest( this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APROBAR_NUEVO).getValor(),autorizacion, ap);
				if(result.getMontoEntregado() == null || result.getNumeroOperacion() == null) {
					throw new RelativeException( Constantes.ERROR_CODE_CREATE, " LA RESPUESTA NO TRAJO EL MONTO O EL NUMERO DE OPERACION APROBADO" + result.getMensaje() );
				}	
				enviarCorreoAprobacionBienvenida(credito);
				this.enviarCorreoCashNuevo(credito, cash, descripcion, null);
				
				
			}else {
				//this.devolverAprobarCredito(credito, null, descripcion, codigoMotivo); 
				TbQoTracking traking = new TbQoTracking();
				traking.setActividad("DEVUELTO");
				traking.setCodigoBpm(credito.getCodigo());
				traking.setCodigoOperacionSoftbank(credito.getNumeroOperacion());
				traking.setEstado(EstadoEnum.ACT);
				traking.setFechaActualizacion(new Date());
				traking.setFechaCreacion(new Date());
				traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				traking.setNombreAsesor(credito.getTbQoNegociacion().getNombreAsesor());
				traking.setUsuarioCreacion(usuario);
				traking.setObservacion(descripcion);
				traking.setProceso(ProcesoEnum.NUEVO);
				traking.setSeccion("DEVUELTO");
				this.registrarTraking(traking);

				this.manageCreditoNegociacion(credito);
				persisted =   this.cambiarEstado(credito.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO, EstadoProcesoEnum.DEVUELTO, credito.getTbQoNegociacion().getAsesor() );
			}
			credito.getTbQoNegociacion().setMotivo(null);

			credito.getTbQoNegociacion().setEstadoCredito("CREDITO");
			this.manageNegociacion(credito.getTbQoNegociacion());
			this.mailSolicitudAprobacion(credito, persisted);
			return persisted;

		} catch( RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR APROBAR: " + e.getMessage());
		}
	}
	public TbQoProceso aprobarNovacion(BigDecimal valorCash, Long idCredito, String descripcion, String cash, String codigoMotivo, Long agencia,
			String usuario, Boolean aprobar, String autorizacion) throws RelativeException {
		try {
			TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findById(idCredito);
			if( credito == null ) {
				throw new RelativeException( Constantes.ERROR_CODE_READ, "NO EXISTE EL CREDITO"); 
			}
			if(StringUtils.isBlank(descripcion)) {
				throw new RelativeException(Constantes.ERROR_CODE_READ,"NO SE PUEDE LEER LA DESCRIPCION");
			}
			TbQoProceso persisted = this.findProcesoByIdReferencia( credito.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION );
			if(persisted == null || 
					(persisted.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION 
					&& persisted.getEstadoProceso() != EstadoProcesoEnum.PENDIENTE_APROBACION_DEVUELTO )) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"LA OPERACION NO SE ENCUENTRA DISPONIBLE O YA FUE PROCESADA");
			}
			credito.setCodigoDevuelto(codigoMotivo);
			credito.setDescripcionDevuelto(descripcion);
			this.guardaraObservacion(descripcion, credito, usuario);
			if(aprobar) {
				//if(StringUtils.isBlank(cash) ) {
					//throw new RelativeException(Constantes.ERROR_CODE_READ,"NO SE PUEDE LEER CODIGO CASH");
				//} 
				if( StringUtils.isBlank( credito.getNumeroOperacion()) ) {
					throw new RelativeException(Constantes.ERROR_CODE_READ,"NO EXISTE EL NUMERO DE OPERACION");
				}
				AprobarWrapper ap = new AprobarWrapper();
				ap.setNumeroOperacion( credito.getNumeroOperacion() );
				TbQoDocumentoHabilitante doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("10"),
						ProcessEnum.NOVACION, String.valueOf(credito.getTbQoNegociacion().getId()));
				if(doc != null && StringUtils.isNotBlank(doc.getObjectId())) {
					ap.setUriHabilitantesFirmados(doc.getObjectId());
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS FIRMADOS");
				}
				ap.setDatosRegistro( new DatosRegistroWrapper( usuario, agencia, QuskiOroUtil.dateToString(new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT), null, credito.getCodigo() ) );
				
				
				//FIN APROBACION
				TbQoTracking last = this.trackingRepository.findByParams(credito.getCodigo(),ProcesoEnum.RENOVACION);
				if(last != null) {
					last.setFechaFin(new Timestamp(System.currentTimeMillis()));
					this.manageTracking(last);
				}

				this.manageCreditoNegociacion(credito);
				persisted = this.cambiarEstado(credito.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION, EstadoProcesoEnum.APROBADO, credito.getTbQoNegociacion().getAsesor() );
				ap.setCodigoCash(cash);
				RespuestaAprobarWrapper result = SoftBankApiClient.callAprobarRest( this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APROBAR_NUEVO).getValor(), autorizacion, ap);
				if(result.getMontoEntregado() == null || result.getNumeroOperacion() == null) {
					throw new RelativeException( Constantes.ERROR_CODE_CREATE, " LA RESPUESTA NO TRAJO EL MONTO O EL NUMERO DE OPERACION APROBADO" + result.getMensaje() );
				}	
				if(StringUtils.isNotBlank(cash)) {
					credito.setCodigoCash(cash);
					credito.setValorCash(valorCash);
					this.enviarCodigoCashRenovacion(credito, cash, descripcion, null,valorCash);
				}
				this.actualizarGarantiasSoftBank(credito.getTbQoTasacions(), credito.getNumeroOperacionMadre(),credito.getNumeroOperacionAnterior(), Boolean.TRUE, usuario, autorizacion);
				this.enviarCorreoAprobacionBienvenida(credito);
			}else {
				//this.devolverAprobarCreditoRenovacion(credito, null, descripcion, codigoMotivo); 
				TbQoTracking traking = new TbQoTracking();
				traking.setActividad("DEVUELTO");
				traking.setCodigoBpm(credito.getCodigo());
				traking.setCodigoOperacionSoftbank(credito.getNumeroOperacion());
				traking.setEstado(EstadoEnum.ACT);
				traking.setFechaActualizacion(new Date());
				traking.setFechaCreacion(new Date());
				traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				traking.setNombreAsesor(credito.getTbQoNegociacion().getNombreAsesor());
				traking.setUsuarioCreacion(usuario);
				traking.setObservacion(descripcion);
				traking.setProceso(ProcesoEnum.RENOVACION);
				traking.setSeccion("DEVUELTO");
				this.registrarTraking(traking);
				this.manageCreditoNegociacion(credito);
				persisted = this.cambiarEstado(credito.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION, EstadoProcesoEnum.DEVUELTO, credito.getTbQoNegociacion().getAsesor() );
				
				
			}
			credito.getTbQoNegociacion().setMotivo(null);

			credito.getTbQoNegociacion().setEstadoCredito("CREDITO");
			this.manageNegociacion(credito.getTbQoNegociacion());
			this.mailSolicitudAprobacion(credito, persisted);
			return persisted;
		} catch( RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR APROBAR: " + e.getMessage());
		}
	}

	public TbQoCotizador guardarGestion(CotizacionWrapper wrapper) throws RelativeException {
		try {
			if( wrapper.getCotizacion() == null || wrapper.getCotizacion().getId() == null || wrapper.getCotizacion().getTbQoCliente() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," NO SE PUEDE GUARDAR SIN UNA COTIZACION EXISTENTE.");
			}
			if(wrapper.getTelefonoMovil() != null) {
				this.manageTelefonoCliente( wrapper.getTelefonoMovil() );
			}
			if(wrapper.getTelefonoDomicilio() != null) {
				this.manageTelefonoCliente( wrapper.getTelefonoDomicilio() );
			}
			if(wrapper.getOpciones() != null) {
				this.manageDetalleCreditos( wrapper.getOpciones());
			}
			this.manageCliente( wrapper.getCotizacion().getTbQoCliente() );
			return this.manageCotizador( wrapper.getCotizacion() );
		} catch( RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR GUARDAR GESTION: " + e.getMessage());
		}
		
	}

	public CotizacionWrapper buscarGestionCotizacion(Long id) throws RelativeException {
		try {
			CotizacionWrapper wp = new CotizacionWrapper();
			wp.setCotizacion( this.cotizadorRepository.findById( id ));
			wp.setVariables( this.variablesCrediticiaRepository.findByIdCotizacion( id ));
			wp.setOpciones( this.detalleCreditoRepository.findDetalleCreditoByIdCotizador(id));
			wp.setJoyas( this.tasacionRepository.findByIdCotizador(id));
			if( wp.getCotizacion() == null || wp.getVariables() == null) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," NO EXISTE COTIZACION O VARIABLES RELACIONADAS AL ID: ");
			}
			return wp;
		} catch( RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR GUARDAR GESTION: " + e.getMessage());
		}
		
	}

	public TbQoNegociacion solicitarAprobacionNuevo(Long idNegociacion, String correoAsesor, String nombreAsesor, String observacionAsesor) throws RelativeException {
		try {
			TbQoProceso proceso = this.procesoRepository.findByIdReferencia(idNegociacion, ProcesoEnum.NUEVO);
			if(proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.CREADO)==0 || 
					proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.EXCEPCIONADO)==0 || 
					proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.DEVUELTO)==0) {
				TbQoCreditoNegociacion wp = this.creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
				if(wp == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA NEGOCIACION ID:"+idNegociacion);
				}
				solicitarAprobacionNuevo(idNegociacion);
				TbQoDocumentoHabilitante doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("16"),
						ProcessEnum.NUEVO, String.valueOf(wp.getTbQoNegociacion().getId()));
				if(doc == null || StringUtils.isBlank(doc.getObjectId())) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS FIRMADOS");
				}
				
				TbQoDocumentoHabilitante docCliente = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("2"),
						ProcessEnum.CLIENTE, wp.getTbQoNegociacion().getTbQoCliente().getCedulaCliente());
				if(docCliente == null || StringUtils.isBlank(docCliente.getObjectId())) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS DEL CLIENTE");
				}
				TbQoDocumentoHabilitante docAutorizacion = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("18"),
						ProcessEnum.NUEVO, String.valueOf(wp.getTbQoNegociacion().getId()));
				if(docAutorizacion == null || StringUtils.isBlank(docCliente.getObjectId())) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER AUTORIZACION DE BURO");
				}
				TbQoNegociacion nego = wp.getTbQoNegociacion();
				nego.setCorreoAsesor(correoAsesor);
				nego.setNombreAsesor( nombreAsesor );
				nego.setObservacionAsesor(observacionAsesor);
				nego.setAprobador(null);
				TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion(idNegociacion);
				TbQoTracking traking = new TbQoTracking();
				traking.setActividad("ENVIADO A APROBAR");
				traking.setCodigoBpm(credito.getCodigo());
				traking.setCodigoOperacionSoftbank(credito.getNumeroOperacion());
				traking.setEstado(EstadoEnum.ACT);
				traking.setFechaActualizacion(new Date());
				traking.setFechaCreacion(new Date());
				traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				traking.setNombreAsesor(nombreAsesor);
				traking.setUsuarioCreacion(nego.getAsesor());
				traking.setObservacion(observacionAsesor);
				traking.setProceso(ProcesoEnum.NUEVO);
				traking.setSeccion("ENVIADO A APROBAR");
				this.registrarTraking(traking);
				nego = this.manageNegociacion( nego );
				this.mailSolicitudAprobacion(credito, proceso);
				return nego;
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"PARA SOLICITAR APROBACION DEBE ESTAR EN ESTADO CREADO O DEVUELTO");
			}
			
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	 
	public TbQoProceso solicitarAprobacionRenovacion(Long idNegociacion,String nombreAsesor,String correoAsesor, String observacionAsesor) throws RelativeException{
		try {
			
			TbQoProceso proceso = this.procesoRepository.findByIdReferencia(idNegociacion, ProcesoEnum.RENOVACION);
			
			if(proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.CREADO)==0 || 
					proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.EXCEPCIONADO)==0 || 
					proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.DEVUELTO)==0) {
				
				proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
				proceso.setUsuario( QuskiOroConstantes.EN_COLA);
				TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
				if(credito == null) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA NEGOCIACION ID:"+idNegociacion);
				}
				TbQoDocumentoHabilitante doc = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("10"), ProcessEnum.NOVACION, String.valueOf(idNegociacion));
				if(doc == null || StringUtils.isBlank(doc.getObjectId())) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS FIRMADOS");
				}
				TbQoDocumentoHabilitante docCliente = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("2"),
						ProcessEnum.CLIENTE, credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente());
				if(docCliente == null || StringUtils.isBlank(docCliente.getObjectId())) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER DOCUMENTOS DEL CLIENTE");
				}

				TbQoDocumentoHabilitante docAutorizacion = this.documentoHabilitanteRepository.findByTipoDocumentoAndReferenciaAndProceso(Long.valueOf("19"),
						ProcessEnum.AUTORIZACION, credito.getNumeroOperacionMadre());
				if(docAutorizacion == null || StringUtils.isBlank(docCliente.getObjectId()) ||  QuskiOroUtil.diasFecha(docAutorizacion.getFechaActualizacion(), new Date()) > 360) {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER AUTORIZACION DE BURO o ESTA CADUCADO");
				}
				TbQoNegociacion nego = credito.getTbQoNegociacion();
				
				nego.setCorreoAsesor(correoAsesor);
				nego.setNombreAsesor( nombreAsesor );
				nego.setObservacionAsesor(observacionAsesor);
				nego.setAprobador(null);
				TbQoTracking traking = new TbQoTracking();
				traking.setActividad("ENVIADO A APROBAR");
				traking.setCodigoBpm(credito.getCodigo());
				traking.setCodigoOperacionSoftbank(credito.getNumeroOperacion());
				traking.setEstado(EstadoEnum.ACT);
				traking.setFechaActualizacion(new Date());
				traking.setFechaCreacion(new Date());
				traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
				traking.setNombreAsesor(nombreAsesor);
				traking.setUsuarioCreacion(nego.getAsesor());
				traking.setObservacion(observacionAsesor);
				traking.setProceso(ProcesoEnum.RENOVACION);
				traking.setSeccion("ENVIADO A APROBAR");
				this.registrarTraking(traking);
				this.manageNegociacion( nego );

				this.mailSolicitudAprobacion(credito, proceso);
				return this.manageProceso(proceso);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"PARA SOLICITAR APROBACION DEBE ESTAR EN ESTADO CREADO O DEVUELTO");
			}
		}catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	private void solicitarAprobacionNuevo(Long idNegociacion) throws RelativeException{
		try {
			TbQoProceso proceso =this.procesoRepository.findByIdReferencia(idNegociacion, ProcesoEnum.NUEVO);
			if(proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.CREADO)==0 || 
					proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.EXCEPCIONADO)==0 || 
					proceso.getEstadoProceso().compareTo(EstadoProcesoEnum.DEVUELTO)==0) {
				proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
				proceso.setUsuario( QuskiOroConstantes.EN_COLA);
				manageProceso(proceso);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"PARA SOLICITAR APROBACION DEBE ESTAR CREADO O DEVUELTO");
			}
		}catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	public Long caducarCreditos() throws RelativeException {
		try {
			return this.procesoRepository.caducarProcesos();
			
		}catch( RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL INTENTAR GUARDAR GESTION: " + e.getMessage());
		}
	}	

	private byte[] generarReporteProcesoCaducado(List<ProcesoCaducadoWrapper> procesoCaducado) throws RelativeException{
		Map<String, Object> map = new HashMap<>();
		String path= this.parametroRepository.findByNombre(QuskiOroConstantes.PATH_REPORTE).getValor();
		String nombreReporte= this.parametroRepository.findByNombre(QuskiOroConstantes.REPORT_TIEMPO_TRANSCURRIDO).getValor();
		map.put("LIST_DS", procesoCaducado);
		log.info( "============================================> PATH + NOMBRE REPORTE ===============>" + path+nombreReporte);
		log.info( "============================================> SIZE DE LISTA ===============>" + procesoCaducado.size());
		return rs.generateReporteBeanExcel(procesoCaducado, map, path+nombreReporte);
	}

	public TbQoTracking registrarTraking(TbQoTracking wp) throws RelativeException {
		if(StringUtils.isBlank(wp.getCodigoBpm())) {
			return null;
		}
		TbQoTracking last = this.trackingRepository.findByParams(wp.getCodigoBpm(),wp.getProceso());
		wp.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		wp.setFechaCreacion(new Date());
		if(last != null) {
			last.setFechaFin(new Timestamp(System.currentTimeMillis()));
			this.manageTracking(last);
		}
		return this.manageTracking(wp);
	}
	public void enviarCorreoAprobacionBienvenida(TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			if( persisted == null ) { 
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE ENCONTRO CREDITO EN LA EDICION");
			}
			String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_APROBACION_BIENVENIDA ).getValor()					
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() )
						.replace("--nombreAgencia--", StringUtils.isNotBlank(persisted.getNombreAgencia())?persisted.getNombreAgencia(): " ");
			String telefonoQuski = this.parametroRepository.findByNombre(QuskiOroConstantes.TELEFONO_QUSKI).getValor();
			String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_APROBACION_BIENVENIDA ).getValor()
						.replace("--telefonoQuski--",  telefonoQuski)
						.replace("--nombreAsesor--",   persisted.getTbQoNegociacion().getNombreAsesor())
						.replace("--telefonoAsesor--", StringUtils.isNotBlank(persisted.getTbQoNegociacion().getTelefonoAsesor())?persisted.getTbQoNegociacion().getTelefonoAsesor(): " ");
				String[] listCorreos = {persisted.getTbQoNegociacion().getTbQoCliente().getEmail()};
				this.mailNotificacion( listCorreos, asunto, contenido,  null);
		} catch (RelativeException e) {
			e.printStackTrace();
			log.info("error correo bienvenida");
		}catch ( Exception e) {
			e.printStackTrace();
			log.info("error correo bienvenida");
		}
	}
	public void enviarCorreoDevolucionOperaciones(TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			if( persisted == null) { 
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE ENCONTRO CREDITO EN LA EDICION");
			}
			String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_APROBACION_BIENVENIDA ).getValor()					
						.replace("--numeroProceso--", persisted.getCodigo() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() );
			
			String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_APROBACION_BIENVENIDA ).getValor()
						.replace("--numeroProceso--", persisted.getCodigo() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
						.replace("--motivoDevolucion--", persisted.getDescripcionDevuelto() );
				String[] listCorreos = {persisted.getTbQoNegociacion().getCorreoAsesor()};
				this.mailNotificacion( listCorreos, asunto, contenido,  null);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " DESCONOCIDO EN METODO enviarCorreoAprobacionBienvenida() => " + e.getMessage() );
		}
	}
	public void enviarCorreoExcepcionAprobador(TbQoCreditoNegociacion persisted, TbQoExcepcion excepcion, String correoAprobador) throws RelativeException {
		try {
			if( persisted == null) { 
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE ENCONTRO CREDITO EN LA EDICION");
			}
			String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_APROBACION_BIENVENIDA ).getValor()					
						.replace("--tipoExcepcion--", excepcion.getTipoExcepcion() )
						.replace("--numeroProceso--", persisted.getCodigo())
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() );

			
			String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_APROBACION_BIENVENIDA ).getValor()
						.replace("--numeroProceso--", persisted.getCodigo() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
						.replace("--tipoExcepcion", excepcion.getTipoExcepcion() )
						.replace("--observacionAsesor", excepcion.getObservacionAsesor() );
			
				String[] listCorreos = {correoAprobador};
				this.mailNotificacion( listCorreos, asunto, contenido,  null);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " DESCONOCIDO EN METODO enviarCorreoAprobacionBienvenida() => " + e.getMessage() );
		}
	}
	public void enviarCorreoExcepcionAsesor(TbQoCreditoNegociacion persisted, TbQoExcepcion excepcion) throws RelativeException {
		try {
			if( persisted == null) { 
				throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE ENCONTRO CREDITO EN LA EDICION");
			}
			String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.ASUNTO_APROBACION_BIENVENIDA ).getValor()					
						.replace("--tipoExcepcion--", excepcion.getTipoExcepcion() )
						.replace("--numeroProceso--", persisted.getCodigo())
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() );

				
			String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.CONTENIDO_APROBACION_BIENVENIDA ).getValor()
						.replace("--numeroProceso--", persisted.getCodigo() )
						.replace("--nombreCliente--", persisted.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
						.replace("--tipoExcepcion", excepcion.getTipoExcepcion() )
						.replace("--estadoExcepcion", excepcion.getEstadoExcepcion() != null ? excepcion.getEstadoExcepcion().toString(): " " )
						.replace("--obvervacionAsesor", excepcion.getObservacionAsesor() );
			
				String[] listCorreos = {persisted.getTbQoNegociacion().getCorreoAsesor()};
				this.mailNotificacion( listCorreos, asunto, contenido,  null);
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch ( Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " DESCONOCIDO EN METODO enviarCorreoAprobacionBienvenida() => " + e.getMessage() );
		}
	}
	
	

	public TbQoHistoricoObservacion findHistoricoObservacionById(Long id) throws RelativeException {
	
			return historicoObservacionRepository.findById(id);
		
	}

	public List<TbQoHistoricoObservacion> findAllHistoricoObservacion(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.historicoObservacionRepository.findAll(TbQoHistoricoObservacion.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.historicoObservacionRepository.findAll(TbQoHistoricoObservacion.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

				} else {
					return this.historicoObservacionRepository.findAll(TbQoHistoricoObservacion.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw e;
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author DIEGO SERRANO - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countHistoricoObservacion() throws RelativeException {
		try {
			return historicoObservacionRepository.countAll(TbQoHistoricoObservacion.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	public TbQoHistoricoObservacion manageHistoricoObservacion(TbQoHistoricoObservacion send) throws RelativeException {
		try {
			TbQoHistoricoObservacion persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findHistoricoObservacionById(send.getId());
				return this.updateHistoricoObservacion(send, persisted);
			} else if (send != null && send.getId() == null) {

				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return historicoObservacionRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
		}
	}

	public TbQoHistoricoObservacion updateHistoricoObservacion(TbQoHistoricoObservacion send, TbQoHistoricoObservacion persisted) throws RelativeException {
		try {

			persisted.setFechaCreacion(send.getFechaCreacion());
			persisted.setObservacion(send.getObservacion());
			persisted.setTbQoCreditoNegociacion(send.getTbQoCreditoNegociacion());
			persisted.setUsuario(send.getUsuario());
			return historicoObservacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}
	
	public TbQoHistoricoObservacion guardaraObservacion(String observacion, TbQoCreditoNegociacion credito, String usuario) throws RelativeException {
		try {
			TbQoHistoricoObservacion historico = new TbQoHistoricoObservacion();
			historico.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
			historico.setUsuario(usuario);
			historico.setObservacion(observacion);
			historico.setTbQoCreditoNegociacion(credito);
			return manageHistoricoObservacion(historico);
		} catch (RelativeException e) {
			throw e;
		}
	}

	public List<HistoricoObservacionWrapper> findHistoricoObservacionByIdCredito(Long idCredito) throws RelativeException {
		return this.historicoObservacionRepository.findByIdCredito(idCredito);
	}
	
	
	/// PUBLICIDAD
	/**
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author Diego Serrano - Relative Engine
	 * @throws RelativeException
	 */
	public TbQoPublicidad findPublicidadById(Long id) throws RelativeException {
		try {
			return publicidadRepository.findById(id);
		} catch (RelativeException e) {
			throw e;
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
	public List<TbQoPublicidad> findAllPublicidad(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.publicidadRepository.findAll(TbQoPublicidad.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.publicidadRepository.findAll(TbQoPublicidad.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

				} else {
					return this.publicidadRepository.findAll(TbQoPublicidad.class, pw.getSortFields(),
							pw.getSortDirections());

				}
			}
		} catch (RelativeException e) {
			throw e;
		}
	}

	public List<TbQoPublicidad> managePublicidades(List<TbQoPublicidad> sends) {
		List<TbQoPublicidad> persisteds = new ArrayList<>();
		sends.forEach(element -> {
			element.setEstado(EstadoEnum.ACT);
			element.setId(null);
			element.setFechaCreacion(new Date(System.currentTimeMillis()));
			try {
				this.managePublicidad(element);
			} catch (RelativeException e) {
				e.printStackTrace();
			}
		});
		return persisteds;
	}

	/**
	 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
	 * 
	 * @author Diego Serrano - Relative Engine
	 * @param send entidad con la informacion de creacion o actualizacion
	 * @return Entidad modificada o actualizada
	 * @throws RelativeException
	 */
	public TbQoPublicidad managePublicidad(TbQoPublicidad send) throws RelativeException {
		try {
			TbQoPublicidad persisted = null;
			if (send.getId() != null) {
				persisted = this.findPublicidadById(send.getId());
				send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
				return this.updatePublicidad(send, persisted);
			} else if (send.getId() == null) {
				send.setEstado(EstadoEnum.ACT);
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return publicidadRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " AL LEER LA ENTIDAD DE DETALLE DE CREDITO.");
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
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

	public TbQoPublicidad updatePublicidad(TbQoPublicidad send, TbQoPublicidad persisted)
			throws RelativeException {
		try {
			persisted.setFechaActualizacion( send.getFechaActualizacion() );	
			if( send.getEstado() != null ){
			    persisted.setEstado( send.getEstado() );
			}
			if( send.getNombre() != null ){
			    persisted.setNombre( send.getNombre() );
			}
			
			persisted.setBandera( send.isBandera());
			
			
			
			return publicidadRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}

	/**
	 * Metodo que cuenta la cantidad de entidades existentes
	 * 
	 * @author Diego Serrano - Relative Engine
	 * @return Cantidad de entidades encontradas
	 * @throws RelativeException
	 */
	public Long countPublicidad() throws RelativeException {
		try {
			return publicidadRepository.countAll(TbQoPublicidad.class);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}

	/// REFERIDO
		/**
		 * 
		 * @param id Pk de la entidad
		 * @return Entidad encontrada
		 * @author Diego Serrano - Relative Engine
		 * @throws RelativeException
		 */
		public TbQoReferido findReferidoById(Long id) throws RelativeException {
			try {
				return referidoRepository.findById(id);
			} catch (RelativeException e) {
				throw e;
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
		public List<TbQoReferido> findAllReferido(PaginatedWrapper pw) throws RelativeException {
			try {
				if (pw == null) {
					return this.referidoRepository.findAll(TbQoReferido.class);
				} else {
					if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
						return this.referidoRepository.findAll(TbQoReferido.class, pw.getStartRecord(),
								pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());

					} else {
						return this.referidoRepository.findAll(TbQoReferido.class, pw.getSortFields(),
								pw.getSortDirections());

					}
				}
			} catch (RelativeException e) {
				throw e;
			}
		}

		public List<TbQoReferido> manageReferidos(List<TbQoReferido> sends) {
			List<TbQoReferido> persisteds = new ArrayList<>();
			sends.forEach(element -> {
				element.setEstado(EstadoEnum.ACT);
				element.setId(null);
				element.setFechaCreacion(new Date(System.currentTimeMillis()));
				try {
					this.manageReferido(element);
				} catch (RelativeException e) {
					e.printStackTrace();
				}
			});
			return persisteds;
		}

		/**
		 * Metodo que se encarga de gestionar la entidad sea creacion o actualizacion
		 * 
		 * @author Diego Serrano - Relative Engine
		 * @param send entidad con la informacion de creacion o actualizacion
		 * @return Entidad modificada o actualizada
		 * @throws RelativeException
		 */
		public TbQoReferido manageReferido(TbQoReferido send) throws RelativeException {
			try {
				TbQoReferido persisted = null;
				if (send.getId() != null) {
					persisted = this.findReferidoById(send.getId());
					send.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
					return this.updateReferido(send, persisted);
				} else if (send.getId() == null) {
					send.setEstado(EstadoEnum.ACT);
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					return referidoRepository.add(send);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, " AL LEER LA ENTIDAD DE DETALLE DE CREDITO.");
				}
			} catch (RelativeException e) {
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
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

		public TbQoReferido updateReferido(TbQoReferido send, TbQoReferido persisted)
				throws RelativeException {
			try {
				persisted.setFechaActualizacion( send.getFechaActualizacion() );	
				if( send.getEstado() != null ){
				    persisted.setEstado( send.getEstado() );
				}
				if( send.getNombre() != null ){
				    persisted.setNombre( send.getNombre() );
				}
				
				if( send.getTelefono() != null ){
				    persisted.setTelefono( send.getTelefono() );
				}
				
				
				return referidoRepository.update(persisted);
			} catch (RelativeException e) {
				throw e;
			}
		}

		/**
		 * Metodo que cuenta la cantidad de entidades existentes
		 * 
		 * @author Diego Serrano - Relative Engine
		 * @return Cantidad de entidades encontradas
		 * @throws RelativeException
		 */
		public Long countReferido() throws RelativeException {
			try {
				return referidoRepository.countAll(TbQoReferido.class);
			} catch (RelativeException e) {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
			}
		}

		public CabeceraWrapper getCabecera(String idReferencia, String proceso) throws RelativeException {

			return this.procesoRepository.getCabecera(idReferencia, proceso);
		}

		public List<String> getActividades(List<ProcesoEnum> proceso) throws RelativeException {
			return this.trackingRepository.getActividad(proceso);
			
		}

		public List<EstadoProcesoEnum> getEstadosProceso(List<ProcesoEnum> proceso) throws RelativeException {
			return this.procesoRepository.getEstadosProceso(proceso);
		}

		public BigDecimal getMontoFinanciado(BusquedaOperacionesWrapper wp) throws RelativeException {
			
			return this.procesoRepository.getMontoFinanciado(wp);
		}


		private void mailExcepcion( TbQoExcepcion  excepcion, TbQoCreditoNegociacion credito,TbQoProceso proceso, String autorizacion) throws RelativeException {
			
			
			
			try {
				List<SoftbankOperacionWrapper> riesgo = this.consultarRiesgoSoftbank(credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente(),autorizacion);
				Integer numeroCreditos = 0;
				Integer noCreditosImpagos = 0;
				Double riesgoAcumulado = Double.valueOf("0");
				TbQoExcepcion excepcionCliente = null;
				TbQoExcepcion excepcionCobertura = null;
				TbQoExcepcion excepcionRiesgo = null;
				String cobertura = " ";
				String coberturaActual = " ";
				if(riesgo != null && !riesgo.isEmpty()) {
					riesgoAcumulado = riesgo.stream().mapToDouble(a -> a.getValorTotalPrestamoVencimiento().doubleValue()).sum();
					if(proceso.getProceso().equals(ProcesoEnum.RENOVACION)) {
						numeroCreditos = riesgo.size() == 0? 0 : riesgo.size() - 1;
					}else {
						numeroCreditos = riesgo.size();
					}
					riesgo.removeIf(p->p.getDiasMoraActual().compareTo(BigDecimal.ZERO) <= 0);
					noCreditosImpagos = riesgo.size();
				}
				List<TbQoVariablesCrediticia> variables = this.variablesCrediticiaRepository.findByIdNegociacion(credito.getTbQoNegociacion().getId());

				String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_ASESOR_SOLICITA_UNA_EXCEPCION).getValor();
				if(excepcion.getEstadoExcepcion() != null && (excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO) || excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.NEGADO)) ){
					asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_EXCEPCIONADOR_APRUEBA_RECHAZA_UNA_EXCEPCION).getValor();
				}
				
				
				asunto = asunto.replace("--tipoExcepcion--", excepcion.getTipoExcepcion())
						.replace("--statusAprobacion--", excepcion.getEstadoExcepcion() != null ? excepcion.getEstadoExcepcion().toString(): " ")
						.replace("--nombreCliente--", credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
						.replace("--cedulaCliente--", credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
						.replace("--asesor--", StringUtils.isNotBlank(credito.getTbQoNegociacion().getNombreAsesor())?credito.getTbQoNegociacion().getNombreAsesor():"")
						.replace("--agencia--", credito.getNombreAgencia());
				String textoContenido = "";
				if(excepcion.getTipoExcepcion().equalsIgnoreCase(TipoExcepcionEnum.EXCEPCION_CLIENTE.toString())) {
					textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_ASESOR_SOLICITA_EXCEPCION_CLIENTE).getValor();
					if(excepcion.getEstadoExcepcion() != null && (excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO) || excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.NEGADO)) ){
						textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_APROBACION_EXCEPCION_CLIENTE).getValor();
					}
				}
				if(excepcion.getTipoExcepcion().equalsIgnoreCase(TipoExcepcionEnum.EXCEPCION_COBERTURA.toString())) {
					textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_ASESOR_SOLICITA_EXCEPCION_COBERTURA).getValor();
					excepcionCliente = this.excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(credito.getTbQoNegociacion().getId(), TipoExcepcionEnum.EXCEPCION_CLIENTE.toString(), EstadoExcepcionEnum.APROBADO);
					excepcionRiesgo = this.excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(credito.getTbQoNegociacion().getId(), TipoExcepcionEnum.EXCEPCION_RIESGO.toString(), EstadoExcepcionEnum.APROBADO);
					cobertura = variables.stream().filter( var -> "Cobertura".equals(var.getCodigo())).findAny().orElse(new TbQoVariablesCrediticia()).getValor();
					coberturaActual = variables.stream().filter( var -> "CoberturaActual".equals(var.getCodigo())).findAny().orElse(new TbQoVariablesCrediticia()).getValor();
					if(excepcion.getEstadoExcepcion() != null && (excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO) || excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.NEGADO)) ){
						textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_APROBACION_EXCEPCION_COBERTURA).getValor();
					}
					
				}
				if(excepcion.getTipoExcepcion().equalsIgnoreCase(TipoExcepcionEnum.EXCEPCION_RIESGO.toString())) {
					textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_ASESOR_SOLICITA_EXCEPCION_RIESGO).getValor();
					excepcionCliente = this.excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(credito.getTbQoNegociacion().getId(), TipoExcepcionEnum.EXCEPCION_CLIENTE.toString(), EstadoExcepcionEnum.APROBADO);
					excepcionCobertura = this.excepcionesRepository.findByTipoExcepcionAndIdNegociacionAndestadoExcepcion(credito.getTbQoNegociacion().getId(), TipoExcepcionEnum.EXCEPCION_COBERTURA.toString(), EstadoExcepcionEnum.APROBADO);
					if(variables != null && !variables.isEmpty()) {
						cobertura = variables.stream().filter( var -> "Cobertura".equals(var.getCodigo())).findAny().orElse(new TbQoVariablesCrediticia()).getValor();
					}
					if(excepcion.getEstadoExcepcion() != null && (excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO) || excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.NEGADO)) ){
						textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.CORREO_APROBACION_EXCEPCION_RIESGO).getValor();
					}
				}
				String perfilInterno = " ";
				String perfilExterno = " ";
				if(variables != null && !variables.isEmpty()) {
					 perfilInterno = variables.stream().filter( var -> "PerfilInterno".equals(var.getCodigo())).findAny().orElse(new TbQoVariablesCrediticia()).getValor();
					 perfilExterno = variables.stream().filter( var -> "PerfilExterno".equals(var.getCodigo())).findAny().orElse(new TbQoVariablesCrediticia()).getValor();
				}
				textoContenido = textoContenido
						.replace("--codigoBPM--", StringUtils.isNotBlank(credito.getCodigo())? credito.getCodigo() : " ")
						.replace("--nombreCliente--", credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
						.replace("--riesgoAcumulado--", String.valueOf(riesgoAcumulado))
						.replace("--asesor--",StringUtils.isNotBlank(credito.getTbQoNegociacion().getNombreAsesor())?credito.getTbQoNegociacion().getNombreAsesor():"")
						.replace("--noCreditosImpagos--", String.valueOf(noCreditosImpagos) )
						.replace("--creditosVigentes--", String.valueOf(numeroCreditos) )
						.replace("--excepcion--", StringUtils.isNotBlank(excepcion.getMensajeBre())? excepcion.getMensajeBre() : " "  )
						.replace("--edad--", String.valueOf(credito.getTbQoNegociacion().getTbQoCliente().getEdad() != null? credito.getTbQoNegociacion().getTbQoCliente().getEdad():"") )
						.replace("--observacionAsesor--", StringUtils.isNotBlank(excepcion.getObservacionAsesor())? excepcion.getObservacionAsesor() : " ")
						.replace("--numeroOperacion--", StringUtils.isNotBlank(credito.getNumeroOperacion())? credito.getNumeroOperacion() : " ")
						.replace("--perfilInterno--", StringUtils.isNotBlank(perfilInterno)? perfilInterno:" ")
						.replace("--perfilExterno--", StringUtils.isNotBlank(perfilExterno)? perfilExterno: " ")
						.replace("--cobertura--",  StringUtils.isNotBlank(cobertura )? cobertura: " ")
						.replace("--monto--", credito.getMontoFinanciado() != null? String.valueOf(credito.getMontoFinanciado().doubleValue()) : " ")
						.replace("--plazo--", String.valueOf(credito.getPlazoCredito()) )
						.replace("--tipoCredito--", StringUtils.isNotBlank(credito.getPeriodoPlazo())? credito.getPeriodoPlazo().equalsIgnoreCase("C")?"Cuotas":"Vencimiento" : " ")
						.replace("--excepcionCliente--", excepcionCliente != null ? "SI": "NO")
						.replace("--excepcionCobertura--",  excepcionCobertura != null ? "SI": "NO")
						.replace("--excepcionRiesgo--",  excepcionRiesgo != null ? "SI": "NO")
						.replace("--coberturaActual--", StringUtils.isNotBlank(coberturaActual)? coberturaActual :" ")
						.replace("--coberturaOferta--",  StringUtils.isNotBlank(cobertura) ? cobertura:" ")
						.replace("--observacionAprobador--",StringUtils.isNotBlank( excepcion.getObservacionAprobador()) ?  excepcion.getObservacionAprobador() :" ") ;
				String[] para = null;
				if(excepcion.getEstadoExcepcion() != null && (excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO) || excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.NEGADO)) ){
					para = Stream.of(credito.getTbQoNegociacion().getCorreoAsesor()).toArray(String[]::new); 
				}else {
					 para = this.excepcionRolRepository.findCorreoByTipoExcepcion( TipoExcepcionEnum.valueOf(excepcion.getTipoExcepcion()) ).toArray(new String[0]);
				}
				
				mailNotificacion(para, asunto, textoContenido, null);
			}  catch (RelativeException e) {
				throw e;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,e.getMessage());
			}
			
			
		}
		
		
	private void mailSolicitudAprobacion(TbQoCreditoNegociacion credito, TbQoProceso proceso) throws RelativeException {
		String asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_ASESOR_ENVIA_CREDITO_A_APROBACION).getValor();
		String textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.ASESOR_ENVIA_CREDITO_A_APROBACION).getValor();
		if(proceso.getEstadoProceso().equals(EstadoProcesoEnum.APROBADO) || proceso.getEstadoProceso().equals(EstadoProcesoEnum.DEVUELTO)) {
			textoContenido = this.parametroRepository.findByNombre(QuskiOroConstantes.APROBADOR_APRUEBA_DEVUELVE_EL_CREDITO).getValor();
			asunto = this.parametroRepository.findByNombre(QuskiOroConstantes.ASUNTO_CORREO_DE_SOLICITUD_DE_APROBACION_RESPUESTA).getValor();
		}
		asunto = asunto
				.replace("--operacionNovada--", StringUtils.isNotBlank(credito.getNumeroOperacionAnterior())?credito.getNumeroOperacionAnterior() : "")
				.replace("--statusAprobacion--", proceso.getEstadoProceso() != null ? proceso.getEstadoProceso().toString(): " ")
				.replace("--flujoCredito--", proceso.getProceso() != null ? proceso.getProceso().toString() : " ")
				.replace("--codigoBPM--", StringUtils.isNotBlank(credito.getCodigo()) ? credito.getCodigo() : " ")
				.replace("--numeroOperacion--", StringUtils.isNotBlank(credito.getNumeroOperacion())? credito.getNumeroOperacion() : " ")
				.replace("--nombreCliente--", credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
				.replace("--cedulaCliente--", credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
				.replace("--asesor--", StringUtils.isNotBlank(credito.getTbQoNegociacion().getNombreAsesor())?credito.getTbQoNegociacion().getNombreAsesor():"")
				.replace("--agencia--", credito.getNombreAgencia());
		textoContenido = textoContenido
				.replace("--codigoBPM--", StringUtils.isNotBlank(credito.getCodigo()) ? credito.getCodigo() : " ")
				.replace("--nombreCliente--", credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto())
				.replace("--asesor--",StringUtils.isNotBlank(credito.getTbQoNegociacion().getNombreAsesor())?credito.getTbQoNegociacion().getNombreAsesor():"")
				.replace("--edad--", String.valueOf(credito.getTbQoNegociacion().getTbQoCliente().getEdad() != null? credito.getTbQoNegociacion().getTbQoCliente().getEdad():"") )
				.replace("--numeroOperacion--", StringUtils.isNotBlank(credito.getNumeroOperacion())? credito.getNumeroOperacion() : " ")
				.replace("--monto--", credito.getMontoFinanciado() != null ? String.valueOf(credito.getMontoFinanciado().doubleValue()): " ")
				.replace("--plazo--", credito.getPlazoCredito() != null ? String.valueOf(credito.getPlazoCredito()): " " )
				.replace("--tipoCredito--", StringUtils.isNotBlank(credito.getPeriodoPlazo())? credito.getPeriodoPlazo().equalsIgnoreCase("C")?"Cuotas":"Vencimiento" : " ")
				.replace("--observacionAsesor--", StringUtils.isNotBlank(credito.getDescripcionDevuelto()) ? credito.getDescripcionDevuelto() : " ")
				.replace("--observacionAprobador--", StringUtils.isNotBlank(credito.getDescripcionDevuelto())? credito.getDescripcionDevuelto(): "");
		String[] para = null;
		if(proceso.getEstadoProceso().equals(EstadoProcesoEnum.APROBADO) || proceso.getEstadoProceso().equals(EstadoProcesoEnum.DEVUELTO)) {
			para = Stream.of(credito.getTbQoNegociacion().getCorreoAsesor()).toArray(String[]::new); 
		}else {
			para = Stream.of(this.parametroRepository.findByNombre(QuskiOroConstantes.MAIL_SOLICITUD_CREDITO).getValor()).toArray(String[]::new);
		}
		
		mailNotificacion(para, asunto, textoContenido, null);
			
			
	}

	public List<TrakingProcesoWrapper> findTrakingByCodigoBpm(String codigoBpm, PaginatedWrapper pw) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.trackingRepository.trakingByCodigoBpm(codigoBpm, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.trackingRepository.trakingByCodigoBpm(codigoBpm,0, 10,"","");
		}
	}

	public Long countTrakingByCodigoBpm(String codigoBpm) throws RelativeException {
		return this.trackingRepository.trakingByCodigoBpm(codigoBpm);
	}

	public List<TrakingProcesoWrapper> findTrakingActividadByCodigoBpm(String codigoBpm, PaginatedWrapper pw) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.trackingRepository.trakingActividadByCodigoBpm(codigoBpm, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.trackingRepository.trakingActividadByCodigoBpm(codigoBpm,0, 10,"","");
		}
	}

	public Long countTrakingActividadByCodigoBpm(String codigoBpm) throws RelativeException {
		return this.trackingRepository.trakingActividadByCodigoBpm(codigoBpm);
	}

	public List<TrakingProcesoWrapper> findTrakingSeccionByCodigoBpm(String codigoBpm, PaginatedWrapper pw) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.trackingRepository.trakingSeccionByCodigoBpm(codigoBpm, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.trackingRepository.trakingSeccionByCodigoBpm(codigoBpm,0, 10,"","");
		}
	}

	public Long countTrakingSeccionByCodigoBpm(String codigoBpm) throws RelativeException {
		return this.trackingRepository.trakingSeccionByCodigoBpm(codigoBpm);
	}

	public List<TrakingProcesoWrapper> findTrakingSeccionConsolidadoByCodigoBpm(String codigoBpm, PaginatedWrapper pw) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.trackingRepository.trakingSeccionConsolidadoByCodigoBpm(codigoBpm, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.trackingRepository.trakingSeccionConsolidadoByCodigoBpm(codigoBpm,0, 10,"","");
		}
	}

	public Long countTrakingSeccionConsolidadoByCodigoBpm(String codigoBpm) throws RelativeException {
		return this.trackingRepository.trakingSeccionConsolidadoByCodigoBpm(codigoBpm);
	}

	public List<TrakingProcesoWrapper> findTrakingAreaByCodigoBpm(String codigoBpm, PaginatedWrapper pw) throws RelativeException {
		if (pw != null && pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
			return this.trackingRepository.trakingAreaByCodigoBpm(codigoBpm, pw.getStartRecord(), pw.getPageSize(),
					pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.trackingRepository.trakingAreaByCodigoBpm(codigoBpm,0, 10,"","");
		}
	}

	public Long countTrakingAreaByCodigoBpm(String codigoBpm) throws RelativeException {
		return this.trackingRepository.trakingAreaByCodigoBpm(codigoBpm);
	}
	
	///hitorico operativa
	
	public TbQoHistoricoOperativa findHistoricoOperativaById(Long id) throws RelativeException {
		
		return historicoOperativaRepository.findById(id);
	
	}
	
	public List<TbQoHistoricoOperativa> findAllHistoricoOperativa(PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.historicoOperativaRepository.findAll(TbQoHistoricoOperativa.class);
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.historicoOperativaRepository.findAll(TbQoHistoricoOperativa.class, pw.getStartRecord(),
							pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
	
				} else {
					return this.historicoOperativaRepository.findAll(TbQoHistoricoOperativa.class, pw.getSortFields(),
							pw.getSortDirections());
				}
			}
		} catch (RelativeException e) {
			throw e;
		}
	}
	
	public Long countHistoricoOperativa() throws RelativeException {
		try {
			return historicoOperativaRepository.countAll(TbQoHistoricoOperativa.class);
		} catch (RelativeException e) {
			throw e;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	
	public TbQoHistoricoOperativa manageHistoricoOperativa(TbQoHistoricoOperativa send) throws RelativeException {
		try {
			TbQoHistoricoOperativa persisted = null;
			if (send != null && send.getId() != null) {
				persisted = this.findHistoricoOperativaById(send.getId());
				return this.updateHistoricoOperativa(send, persisted);
			} else if (send != null && send.getId() == null) {
	
				send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				return historicoOperativaRepository.add(send);
			} else {
				throw new RelativeException(Constantes.ERROR_CODE_CREATE,
						QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
			}
		} catch (RelativeException e) {
			throw e;
		}
	}
	
	public TbQoHistoricoOperativa updateHistoricoOperativa(TbQoHistoricoOperativa send, TbQoHistoricoOperativa persisted) throws RelativeException {
		try {
	
			persisted.setFechaCreacion(send.getFechaCreacion());
		
			persisted.setTbQoNegociacion(send.getTbQoNegociacion());
			persisted.setUsuario(send.getUsuario());
			return historicoOperativaRepository.update(persisted);
		} catch (RelativeException e) {
			throw e;
		}
	}
	
	public TbQoHistoricoOperativa guardaraOperativa(String excepcionOperativa,Date fechaRegularizacion, TbQoNegociacion credito, String usuario) throws RelativeException {
		try {
			TbQoHistoricoOperativa historico = new TbQoHistoricoOperativa();
			historico.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
			historico.setUsuario(usuario);
			historico.setFechaRegularizacion(fechaRegularizacion);
			historico.setExcepcion(excepcionOperativa);
			historico.setTbQoNegociacion(credito);
			return manageHistoricoOperativa(historico);
		} catch (RelativeException e) {
			throw e;
		}
	}
	
	public List<HistoricoOperativaWrapper> findHistoricoOperativaByIdCredito(Long idCredito) throws RelativeException {
		return this.historicoOperativaRepository.findByIdCredito(idCredito);
	}
	
	///hitorico observacion entrega
	
		public TbQoHistoricoObservacionEntrega findHistoricoObservacionEntregaById(Long id) throws RelativeException {
			
			return historicoObservacionEntregaRepository.findById(id);
		
		}
		
		public List<TbQoHistoricoObservacionEntrega> findAllHistoricoObservacionEntrega(PaginatedWrapper pw) throws RelativeException {
			try {
				if (pw == null) {
					return this.historicoObservacionEntregaRepository.findAll(TbQoHistoricoObservacionEntrega.class);
				} else {
					if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
						return this.historicoObservacionEntregaRepository.findAll(TbQoHistoricoObservacionEntrega.class, pw.getStartRecord(),
								pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		
					} else {
						return this.historicoObservacionEntregaRepository.findAll(TbQoHistoricoObservacionEntrega.class, pw.getSortFields(),
								pw.getSortDirections());
					}
				}
			} catch (RelativeException e) {
				throw e;
			}
		}
		
		public Long countHistoricoObservacionEntrega() throws RelativeException {
			try {
				return historicoObservacionEntregaRepository.countAll(TbQoHistoricoObservacionEntrega.class);
			} catch (RelativeException e) {
				throw e;
			} catch (Exception e) {
				throw new RelativeException(Constantes.ERROR_CODE_READ,
						QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
			}
		}
		
		public TbQoHistoricoObservacionEntrega manageHistoricoObservacionEntrega(TbQoHistoricoObservacionEntrega send) throws RelativeException {
			try {
				TbQoHistoricoObservacionEntrega persisted = null;
				if (send != null && send.getId() != null) {
					persisted = this.findHistoricoObservacionEntregaById(send.getId());
					return this.updateHistoricoObservacionEntrega(send, persisted);
				} else if (send != null && send.getId() == null) {
		
					send.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
					return historicoObservacionEntregaRepository.add(send);
				} else {
					throw new RelativeException(Constantes.ERROR_CODE_CREATE,
							QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION);
				}
			} catch (RelativeException e) {
				throw e;
			}
		}
		
		public TbQoHistoricoObservacionEntrega updateHistoricoObservacionEntrega(TbQoHistoricoObservacionEntrega send, TbQoHistoricoObservacionEntrega persisted) throws RelativeException {
			try {
		
				persisted.setFechaCreacion(send.getFechaCreacion());
			
				//persisted.setTbQoNegociacion(send.getTbQoNegociacion());
				persisted.setUsuario(send.getUsuario());
				return historicoObservacionEntregaRepository.update(persisted);
			} catch (RelativeException e) {
				throw e;
			}
		}
		
		public TbQoHistoricoObservacionEntrega guardaraObservacionEntrega(String observacion, BigDecimal idDevolucion, String usuario) throws RelativeException {
			try {
				TbQoHistoricoObservacionEntrega historico = new TbQoHistoricoObservacionEntrega();
				historico.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
				historico.setUsuario(usuario);
				historico.setIdDevolucion(idDevolucion);
				historico.setObservacion(observacion);
				return manageHistoricoObservacionEntrega(historico);
			} catch (RelativeException e) {
				throw e;
			}
		}
		
		public List<TbQoHistoricoObservacionEntrega> findHistoricoObservacionEntregaByIdCredito(Long idCredito) throws RelativeException {
			return this.historicoObservacionEntregaRepository.findByIdCredito(idCredito);
		}

		public TbQoNegociacion guardarEstadoMotivo(Long idNego, String estadoCredito, String motivo) throws RelativeException {
			if(idNego == null) {
				return null;
			}
			TbQoNegociacion nego = this.findNegociacionById(idNego);
			nego.setEstadoCredito(estadoCredito);
			nego.setMotivo(motivo);
			return this.manageNegociacion(nego);
		}

		public ResponseWebMupi consultaWebMupi(Long idCliente,String autorizacion) throws RelativeException{
			TbQoCliente cliente =this.findClienteById(idCliente);
			if(cliente == null) {
				throw new RelativeException(Constantes.ERROR_CODE_READ,"No existe un cliente con ID: " + idCliente);
			}
			String nombres= "";
			String apellidos= "";
			if(StringUtils.isNotBlank(cliente.getPrimerNombre()) && StringUtils.isNotBlank(cliente.getSegundoNombre())
					&& StringUtils.isNotBlank(cliente.getApellidoMaterno()) && StringUtils.isNotBlank(cliente.getApellidoPaterno())) {
				nombres = cliente.getPrimerNombre().concat(" ").concat(cliente.getSegundoNombre());
				apellidos = cliente.getApellidoPaterno().concat(" ").concat(cliente.getApellidoMaterno());
				
			}else if ( StringUtils.isNotBlank(cliente.getNombreCompleto())) {
				String[] nombreCompleto = cliente.getNombreCompleto().split(" ");
				if(nombreCompleto.length >3 ) {
					apellidos = nombreCompleto[0] + " " + nombreCompleto[1];
					nombres = nombreCompleto[2] + " " + nombreCompleto[3];
				} else if(nombreCompleto.length == 3 ) {
					apellidos = nombreCompleto[0] + " " + nombreCompleto[1];
					nombres = nombreCompleto[2];
				}else {
					throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"No se puede leer el nombre del cliente");
				}
			}
			ResponseWebMupi res =  ApiGatewayClient.consultaWebMupi(cliente.getNombreCompleto(),nombres,apellidos,cliente.getCedulaCliente(),autorizacion,
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_RUN_SERVER_WEB_MUPI_ROBOT).getValor(),
					this.parametroRepository.findByNombre(QuskiOroConstantes.USER_ROBOT).getValor(),
					this.parametroRepository.findByNombre(QuskiOroConstantes.PASS_ROBOT).getValor());
			cliente.setDetalleWebMupi(res.getDetalle());
			cliente.setAprobacionMupi(res.getEstado().equalsIgnoreCase(QuskiOroConstantes.RECHAZADO)?"N":"S");
			this.manageCliente(cliente);
			
			return res;
			
		}

		public TbQoTracking verActividad(String codigoBpm) throws RelativeException {
			// TODO Auto-generated method stub
			return trackingRepository.verActividad(codigoBpm);
		}

		public TbQoCreditoNegociacion findCreditoByOperacionMadre(String operacionMadre) throws RelativeException {
			// TODO Auto-generated method stub
			return this.creditoNegociacionRepository.findCreditoByNumeroOperacionMadre(operacionMadre);
		}

		public TbQoCreditoNegociacion findCreditoByOperacionYOperacionMadre(String operacion) throws RelativeException {
			// TODO Auto-generated method stub
			return this.creditoNegociacionRepository.findCreditoByNumeroOperacion(operacion);
		}

		

}
