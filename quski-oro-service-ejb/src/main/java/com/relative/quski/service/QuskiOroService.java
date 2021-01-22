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
import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlOpcionesRenovacion.OpcionesRenovacion.Opcion;
import com.relative.quski.wrapper.SimularResponse.SimularResult.XmlGarantias.Garantias.Garantia;
import com.relative.quski.bpms.api.ApiGatewayClient;
import com.relative.quski.bpms.api.CrmApiClient;
import com.relative.quski.bpms.api.SoftBankApiClient;
import com.relative.quski.enums.ActividadEnum;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.enums.ProcessEnum;
import com.relative.quski.enums.SeccionEnum;
//import com.relative.quski.enums.TipoRubroEnum;
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
import com.relative.quski.model.TbQoNegociacion;
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
import com.relative.quski.repository.NegociacionRepository;
import com.relative.quski.repository.ParametroRepository;
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
import com.relative.quski.wrapper.AprobacionNovacionWrapper;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.AutorizacionBuroWrapper;
import com.relative.quski.wrapper.BusquedaOperacionesWrapper;
import com.relative.quski.wrapper.BusquedaPorAprobarWrapper;
import com.relative.quski.wrapper.CalculadoraOpcionWrapper;
import com.relative.quski.wrapper.CatalogoResponseWrapper;
import com.relative.quski.wrapper.CatalogoTablaAmortizacionWrapper;
import com.relative.quski.wrapper.CatalogoWrapper;
import com.relative.quski.wrapper.CatalogosSoftbankWrapper;
import com.relative.quski.wrapper.ClienteCompletoWrapper;
import com.relative.quski.wrapper.ConsultaGarantiaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalRespuestaWrapper;
import com.relative.quski.wrapper.ConsultaGlobalWrapper;
import com.relative.quski.wrapper.ConsultaOperacionGlobalWrapper;
import com.relative.quski.wrapper.ConsultaRubrosWrapper;
import com.relative.quski.wrapper.ConsultaTablaWrapper;
import com.relative.quski.wrapper.CreacionClienteRespuestaCoreWp;
import com.relative.quski.wrapper.CrearOperacionEntradaWrapper;
import com.relative.quski.wrapper.CrearOperacionRenovacionWrapper;
import com.relative.quski.wrapper.CrearOperacionRespuestaWrapper;
import com.relative.quski.wrapper.CreditoCreadoSoftbank;
//import com.relative.quski.wrapper.CrmEntidadWrapper;
//import com.relative.quski.wrapper.CrmGuardarProspectoWrapper;
import com.relative.quski.wrapper.CrmProspectoCortoWrapper;
//import com.relative.quski.wrapper.CrmProspectoWrapper;
import com.relative.quski.wrapper.CuentaWrapper;
import com.relative.quski.wrapper.CuotasAmortizacionWrapper;
import com.relative.quski.wrapper.DatosCuentaClienteWrapper;
import com.relative.quski.wrapper.DatosGarantiasWrapper;
import com.relative.quski.wrapper.DatosImpComWrapper;
import com.relative.quski.wrapper.DatosRegistroWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import com.relative.quski.wrapper.ExcepcionRolWrapper;
import com.relative.quski.wrapper.FileWrapper;
import com.relative.quski.wrapper.GaranteWrapper;
import com.relative.quski.wrapper.GarantiaOperacionWrapper;
import com.relative.quski.wrapper.Informacion;
import com.relative.quski.wrapper.Informacion.DATOSCLIENTE;
//import com.relative.quski.wrapper.Informacion.INGRESOSEGRESOS.RUBRO;
import com.relative.quski.wrapper.Informacion.XmlVariablesInternas.VariablesInternas.Variable;
import com.relative.quski.wrapper.InformacionWrapper;
import com.relative.quski.wrapper.JoyaWrapper;
import com.relative.quski.wrapper.NegociacionWrapper;
import com.relative.quski.wrapper.OpcionWrapper;
import com.relative.quski.wrapper.OperacionCreditoNuevoWrapper;
import com.relative.quski.wrapper.RenovacionWrapper;
import com.relative.quski.wrapper.RespuestaConsultaGlobalWrapper;
import com.relative.quski.wrapper.RespuestaCrearClienteWrapper;
import com.relative.quski.wrapper.ResultOperacionesAprobarWrapper;
import com.relative.quski.wrapper.ResultOperacionesWrapper;
import com.relative.quski.wrapper.SimularResponse;
import com.relative.quski.wrapper.SimularResponseExcepcion;
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
import com.relative.quski.wrapper.TipoOroWrapper;
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
	@Inject
	private DevolucionService ds;
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
				String apP = send.getApellidoPaterno() != null ? send.getApellidoPaterno() + " ": "";
				String apM = send.getApellidoMaterno() != null ? send.getApellidoMaterno() + " ": "";
				String noP = send.getPrimerNombre() != null ? send.getPrimerNombre()  + " ": "";
				String noS = send.getSegundoNombre() != null ? send.getSegundoNombre()  + " ": "";
				String nombre = apP + apM + noP + noS;
				send.setNombreCompleto(nombre);
			}
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
							e.setEstado( re.getEstado() );
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
			log.info("CLIENTE EN BASE =================>   " + cliente + "   <===========");
			if (cliente != null) {
				
				return cliente;
			} 
			cliente = this.clienteSoftToTbQoCliente(this.findClienteSoftbank(cedula));
			log.info("CLIENTE EN SOFTBANK =============>   " + cliente + "   <=============");
			if (cliente != null) {
				
				return this.manageCliente(cliente);
			} 
			/*
			 * cliente = this.prospectoCrmToTbQoCliente(this.findProspectoCrm(cedula));
			 * log.info("CLIENTE EN CRM ===================>   " + cliente +
			 * "   <=============="); if (cliente != null) { return
			 * this.manageCliente(cliente); }
			 */
			log.info("NO SE ENCONTRO CLIENTE  ===================>   " + cliente + "   <==============");
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
			if( send.getEstado() != null ) {
				persisted.setEstado(   send.getEstado() );
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
			log.info( "EL CAMPO DE SI ES ACTIVO O NO PARTE 5 (ESTO SE GUARDA) ======> " +  persisted.getEstado().toString() );

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
			if(send.getDescripcion() != null) {
				persisted.setDescripcion(send.getDescripcion());
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
			}else {
				persisted.setEstado( EstadoEnum.INA );
			}
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
			return tasacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	/**
	 * * * * * * * * * * * @NEGOCIACION_WRAPPER
	 */
	public NegociacionWrapper iniciarNegociacion(String cedula, String asesor, Long idAgencia) throws RelativeException {
		try {
			log.info("<<=================ENTRAR A INICIAR NEGOCIACION=============>>>");
			TbQoCliente cliente = this.createCliente(cedula);
			
			if (cliente != null) {
				Informacion data = informacionCliente(cedula);
				return generarTablasIniciales(cliente, asesor, idAgencia, data);
			} else {
				return new NegociacionWrapper(false);
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					QuskiOroConstantes.ERROR_NEGOCIACION + e.getMessage());
		}
	}

	

	public List<TbQoTasacion> agregarJoya(TbQoTasacion joya, String asesor) throws RelativeException {
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
		manageCreditoNegociacion(credito);
		return this.getDetalleJoya(credito.getTbQoNegociacion().getTbQoCliente(), joya);
	
	}
	

	public List<TipoOroWrapper> verPrecio(TbQoCliente cliente) throws RelativeException {
		
		if(cliente == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DEL CLIENTE");
		}
		
		if(cliente.getFechaNacimiento() == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA FECHA DE NACIMIENTO DEL CLIENTE");
		}
		
		if(StringUtils.isBlank( cliente.getAprobacionMupi() ) ) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER LA INFORMACION DE APROBACION MUPI DEL CLIENTE");
		}
		
		return this.tipoOro(this.manageCliente(cliente));
	}


	/**
	 * Metodo que guarda la opcion del credito seleccionado
	 * @param opcionCredito
	 * @param asesor
	 * @param valueOf
	 * @return
	 * @throws RelativeException 
	 */
	public TbQoCreditoNegociacion guardarOpcionCredito(List<CalculadoraOpcionWrapper> opcionCredito, String asesor,
			Long idCredito) throws RelativeException {
		
		log.info("==============> ENTRA A GUARDAR OPCION CREDITO");
		CalculadoraOpcionWrapper opcion = opcionCredito.get(0);
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
		List<CatalogoTablaAmortizacionWrapper>  listTablas =  SoftBankApiClient.callCatalogoTablaAmortizacionRest(
				this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TABLA_AMOTIZACION).getValor());
		if(listTablas == null || listTablas.isEmpty()) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CATALOGO DE TABLA DE AMORTIZACION SOFTBANK");
		}
		listTablas.forEach(e->{
			if( e.getPeriodoPlazo().equalsIgnoreCase( credito.getPeriodoPlazo()) && 
				e.getPeriodicidadPlazo().equalsIgnoreCase( credito.getPeriodicidadPlazo()) &&
				e.getTipoOferta().equalsIgnoreCase( credito.getTipoOferta() ) &&
				e.getPlazo() == credito.getPlazoCredito() 
			){
				credito.setTablaAmortizacion( e.getCodigo() );
				credito.setNumeroCuotas(e.getNumeroCuotas());					
			}
		});
		if(StringUtils.isBlank(credito.getTablaAmortizacion())) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR UN CODIGO DE TABLA DE AMORTIZACION PARA LA OPCION DE CREDITO SELECCCIONADA");
		}
		return this.creditoNegociacionRepository.update(credito);
	}

	
	/** ******************************* @INTEGRACION **********************/
	public TbQoCliente createClienteFromEquifax(DATOSCLIENTE cliente) throws RelativeException {
		if (cliente != null) {
				TbQoCliente c = new TbQoCliente();
				c.setCedulaCliente(StringUtils.leftPad(String.valueOf(cliente.getIDENTIFICACION()), 10, "0"));
				c.setNombreCompleto(cliente.getNOMBRESCOMPLETOS());
				c.setEmail(cliente.getCORREOELECTRONICO());
				c.setCargasFamiliares(Long.valueOf(cliente.getCARGASFAMILIARES()));
				c = this.manageCliente(c);
				return c;
		} else {
			return null;
		}
	}
//	private BigDecimal createIngresosFromEquifax(List<RUBRO> rubros) throws RelativeException {
//		BigDecimal valor = new BigDecimal(0);
//		rubros.forEach(e -> { 
//			if (e.getTIPORUBROECONOMICO().equalsIgnoreCase(TipoRubroEnum.ING.toString())) {
//				valor.add( BigDecimal.valueOf(e.getVALOR()) );
//			}
//		});
//		return valor;
//	}
//	private BigDecimal createEgresosFromEquifax(List<RUBRO> rubros) throws RelativeException {
//		BigDecimal valor = new BigDecimal(0);
//		rubros.forEach(e -> {
//			if (e.getTIPORUBROECONOMICO().equalsIgnoreCase(TipoRubroEnum.EGR.toString())) {
//				valor.add( BigDecimal.valueOf(e.getVALOR()) );
//			} 
//		});
//		return valor;
//	}
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

	private List<TbQoVariablesCrediticia> createVariablesFromEquifax(List<Variable> variables,
			TbQoNegociacion negociacion) throws RelativeException {
			if (variables != null) {
				List<TbQoVariablesCrediticia> list = new ArrayList<>();
				for(Variable e : variables) {
					TbQoVariablesCrediticia v = new TbQoVariablesCrediticia();
					v.setCodigo(e.getCodigo());
					v.setNombre(e.getNombre());
					v.setOrden(String.valueOf(e.getOrden()));
					v.setValor(e.getValor());
					v.setTbQoNegociacion(negociacion);
					list.add(this.manageVariablesCrediticia(v));				
				}
	
				return list;
			} else {
				return null;
			}
		
	}
	
	public NegociacionWrapper iniciarNegociacionEquifax(String cedula, String asesor, Long idAgencia) throws RelativeException {
		try {
			Informacion data = informacionCliente(cedula);
			
			TbQoCliente cliente = this.createClienteFromEquifax(data.getDATOSCLIENTE());
			if (cliente != null) {
				return generarTablasIniciales(cliente, asesor, idAgencia, data);
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

	public NegociacionWrapper iniciarNegociacionFromCot(Long id, String asesor, Long idAgencia) throws RelativeException {
		try {
			TbQoCliente cliente = this.findClienteByCotizador(id);
			if (cliente != null) {
				Informacion data = informacionCliente(cliente.getCedulaCliente());
				return generarTablasIniciales(cliente, asesor, idAgencia, data);
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
				if(proceso == null) {
					proceso = this.findProcesoByIdReferencia( id, ProcesoEnum.RENOVACION);
				}
				if(proceso != null) {
					EstadoProcesoEnum estado = proceso.getEstadoProceso();
					if( estado == EstadoProcesoEnum.APROBADO || estado == EstadoProcesoEnum.CANCELADO) {
						return new NegociacionWrapper(false);
					}else {
						tmp.setVariables(this.variablesCrediticiaRepository.findByIdNegociacion(id));
						tmp.setRiesgos(this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente()), tmp.getCredito().getTbQoNegociacion()));
						tmp.setJoyas(this.tasacionRepository.findByIdCredito(tmp.getCredito().getId() ));
						tmp.setExcepciones(this.excepcionesRepository.findByIdNegociacion(id));
						tmp.setRespuesta(true);
						tmp.setProceso( proceso );
						tmp.setTelefonoDomicilio(this.telefonoClienteRepository
								.findByClienteAndTipo(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), "DOM"));
						tmp.setTelefonoMovil(this.telefonoClienteRepository
								.findByClienteAndTipo(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente(), "CEL"));
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
				return traerCliente( nego.getTbQoCliente().getCedulaCliente() );
			}else {
				return null;
			}
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}	
	public ClienteCompletoWrapper traerClienteByNumeroOperacion(String numeroOperacionMadre) throws RelativeException {
		try {
			DetalleCreditoWrapper detalle = this.traerCreditoVigente(numeroOperacionMadre);
			if(detalle != null) {
				return traerCliente( detalle.getCliente().getIdentificacion() );
			}else {
				log.info("=================> No traje nada? <==================");
				return null;
			}
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}
	public ClienteCompletoWrapper traerCliente( String cedula ) throws RelativeException{
		try {
			ClienteCompletoWrapper wr = this.mapearClienteCompleto( this.findClienteSoftbank(  cedula  ) );
			if( wr == null) {
				wr = new ClienteCompletoWrapper( this.findClienteByIdentifiacion( cedula ) );
				wr.setIsSoftbank( false );
				if(wr.getExisteError()) {return wr;}
				wr.setDirecciones( this.direccionClienteRepository.findByIdCliente( wr.getCliente().getId() ) );
				wr.setReferencias( this.referenciaPersonalRepository.findByIdCliente( wr.getCliente().getId() ) );
				wr.setDatosTrabajos( this.datoTrabajoClienteRepository.findByIdCliente( wr.getCliente().getId() ) );
				TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente();
				String codigoCuentaMupi = parametroRepository.findByNombre(QuskiOroConstantes.CODIGO_BANCO_MUPI).getValor();
				List<TbQoCuentaBancariaCliente> listCreate = new ArrayList<>();
				CuentaWrapper cuentaWS = consultaCuentaApiGateWay(cedula);
				cuenta.setBanco(Long.valueOf(codigoCuentaMupi));
				cuenta.setCuenta(cuentaWS.getNumeroCuenta());
				cuenta.setEsAhorros(cuentaWS.getTipoCuenta().equalsIgnoreCase("AH"));
				cuenta.setEstado(EstadoEnum.ACT);
				cuenta.setTbQoCliente( wr.getCliente() );
				listCreate.add( cuenta );
				wr.setCuentas( listCreate );
				wr.setTelefonos( this.telefonoClienteRepository.findByIdCliente( wr.getCliente().getId() ) );
				return wr;
			}else {
				wr.setIsSoftbank( true );
				return wr;
			}
		}catch(RelativeException e ) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM , e.getMensaje());
		}
	}
	private ClienteCompletoWrapper mapearClienteCompleto( SoftbankClienteWrapper s) throws RelativeException {
		if (s == null) {return null;}
		TbQoCliente  cliente 					= this.mapearCliente( s );
		List<TbQoTelefonoCliente> telefonos     = !s.getTelefonos().isEmpty() ? this.mapearTelefonos(   s.getTelefonos(), cliente )       : null;
		List<TbQoDireccionCliente> direcciones 	= !s.getDirecciones().isEmpty() ?  this.mapearDirecciones(s.getDirecciones(), cliente  )     : null; 
		List<TbQoReferenciaPersonal> referencias= !s.getContactosCliente().isEmpty() ? this.mapearReferencias( s.getContactosCliente(), cliente ) : null;
		List<TbQoDatoTrabajoCliente> trabajos   = !s.getDatosTrabajoCliente().isEmpty() ? this.mapearTrabajo( s.getDatosTrabajoCliente(), cliente ) : null;
		List<TbQoCuentaBancariaCliente> cuentas = !s.getCuentasBancariasCliente().isEmpty() ? this.mapearCuentas( s.getCuentasBancariasCliente(), cliente ) : null;
		return new ClienteCompletoWrapper(cliente, direcciones, referencias, telefonos,  trabajos, cuentas);
	}
	private List<TbQoDatoTrabajoCliente> mapearTrabajo(List<SoftbankDatosTrabajoWrapper> datosTrabajoCliente, TbQoCliente cliente) {
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
			send.setTbQoCliente(cliente);
			listCreate.add( send );
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoCuentaBancariaCliente> mapearCuentas(List<SoftbankCuentasBancariasWrapper> cuentaSoft, TbQoCliente cliente ) throws RelativeException {
		List<TbQoCuentaBancariaCliente> listCreate = new ArrayList<>();
		String idCuentaMupi = parametroRepository.findByNombre(QuskiOroConstantes.CODIGO_BANCO_MUPI).getValor();
		CuentaWrapper cuentaWS = consultaCuentaApiGateWay(cliente.getCedulaCliente());
		cuentaSoft.forEach( c ->{
			TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente();
			cuenta.setEstado( c.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA);
			cuenta.setBanco(Long.valueOf(idCuentaMupi));
			cuenta.setCuenta(cuentaWS.getNumeroCuenta() );
			cuenta.setEsAhorros(cuentaWS.getTipoCuenta().equalsIgnoreCase("AH"));
			cuenta.setIdSoftbank( c.getId() );
			cuenta.setTbQoCliente( cliente );
			listCreate.add( cuenta );				
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoReferenciaPersonal> mapearReferencias(List<SoftbankContactosWrapper> contactosCliente, TbQoCliente cliente) {
		List<TbQoReferenciaPersonal> listCreate= new ArrayList<>();
		contactosCliente.forEach(e->{
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
			listCreate.add( referencia );
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoTelefonoCliente> mapearTelefonos(List<SoftbankTelefonosWrapper> telefonos, TbQoCliente cliente) {
		List<TbQoTelefonoCliente> listCreate = new ArrayList<>();
		telefonos.forEach(e ->{
			TbQoTelefonoCliente tele = new TbQoTelefonoCliente();
			tele.setEstado( EstadoEnum.ACT );
			tele.setIdSoftbank( e.getId() );
			tele.setNumero( e.getNumero() );
			tele.setTipoTelefono( e.getCodigoTipoTelefono() );
			tele.setTbQoCliente(cliente);
			tele.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
			listCreate.add( tele );	
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private List<TbQoDireccionCliente> mapearDirecciones(List<SoftbankDireccionWrapper> direcciones, TbQoCliente cliente) {
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
			direccion.setTbQoCliente( cliente );
			listCreate.add( direccion );				
		});
		return listCreate.isEmpty() ? null : listCreate;
	}
	private TbQoCliente mapearCliente(SoftbankClienteWrapper s) throws RelativeException {
		try {
			TbQoCliente cliente = this.clienteRepository.findClienteByIdentificacion( s.getIdentificacion() );
			if(cliente == null) { cliente = new TbQoCliente(); }
			cliente.setCedulaCliente(s.getIdentificacion());
			cliente.setActividadEconomica( s.getActividadEconomica().getIdActividadEconomica().toString() );
			cliente.setApellidoMaterno( s.getSegundoApellido() );
			cliente.setApellidoPaterno( s.getPrimerApellido() );
			cliente.setAgencia( s.getIdAgencia() );
			cliente.setUsuario( s.getCodigoUsuarioAsesor() );
			cliente.setNombreCompleto( s.getNombreCompleto() );
			cliente.setCanalContacto( s.getCodigoMotivoVisita() );
			cliente.setCargasFamiliares( s.getNumeroCargasFamiliares() );
			if( s.getFechaNacimiento() != null) {
				cliente.setFechaNacimiento(s.getFechaNacimiento());
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
			cliente.setIngresos( s.getIngresos() );
			cliente.setEgresos( s.getEgresos() );
			cliente.setPasivos( s.getPasivos() );
			cliente.setActivos( s.getActivos() );
			return cliente;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje());

		}
		
	}
	public CreacionClienteRespuestaCoreWp registrarCliente(ClienteCompletoWrapper wp) throws RelativeException {
		try {
			TbQoCliente cliente =  this.manageCliente( wp.getCliente() ) ;
			CreacionClienteRespuestaCoreWp rp = new CreacionClienteRespuestaCoreWp();
			wp.setCliente( cliente ); 
			if( wp.getIsSoftbank() ) {
				Boolean result = this.editarClienteSoftbank( this.clienteToClienteSoftbank( wp ) );
				rp.setIsSoftbank(  result  ); 
			}else {
				Boolean result = this.crearClienteSoftbank( this.clienteToClienteSoftbank( wp ) );
				rp.setIsSoftbank(  result  );				
			}
			
			if(!rp.getIsSoftbank()) { rp.setIsCore( false ); return rp; }
			SoftbankClienteWrapper softCliente = this.findClienteSoftbank(  wp.getCliente().getCedulaCliente()  );
			Boolean eliminado = this.borrarRegistrosPrevios( cliente ); 
			if(eliminado) {
				this.guardarTrabajoCliente(softCliente, cliente);
				this.guardarDirecciones(softCliente, cliente);
				this.guardarReferencias(softCliente, cliente);
				this.guardarTelefonos(softCliente, cliente);
				this.guardarCuentas(softCliente, cliente);
				rp.setIsCore(true);
				return rp;
			}else {
				rp.setIsCore(false);
				return rp;
			}
				
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje());
		}

	}

	private Boolean borrarRegistrosPrevios(TbQoCliente cliente) throws RelativeException {
		try {
			Boolean result = true;
			List<TbQoDatoTrabajoCliente> baseTrabajos    = this.datoTrabajoClienteRepository.findAllByIdCliente( cliente.getId() );
			List<TbQoDireccionCliente> baseDirecciones   = this.direccionClienteRepository.findAllByIdCliente( cliente.getId() );
			List<TbQoReferenciaPersonal> baseReferencias = this.referenciaPersonalRepository.findAllByIdCliente( cliente.getId() );
			List<TbQoTelefonoCliente> baseTelefonos      = this.telefonoClienteRepository.findAllByIdCliente( cliente.getId() );
			List<TbQoCuentaBancariaCliente> baseCuentas  = this.cuentaBancariaRepository.findByAllIdCliente( cliente.getId() );
			if(baseTrabajos != null ) {
				baseTrabajos.forEach( r ->{
					try {
						this.datoTrabajoClienteRepository.remove( r );
					} catch (RelativeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				baseTrabajos  = this.datoTrabajoClienteRepository.findAllByIdCliente( cliente.getId() );
				if(baseTrabajos != null) {result = false;}else {log.info( "ELIMINO TODO baseTrabajos ======>  " + baseTrabajos );}
			}
			if(baseDirecciones != null ) {
				baseDirecciones.forEach( r ->{
					try {
						this.direccionClienteRepository.remove( r );
					} catch (RelativeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				baseDirecciones  = this.direccionClienteRepository.findAllByIdCliente( cliente.getId() );
				if(baseDirecciones != null) {result = false;}else {log.info( "ELIMINO TODO baseDirecciones ======> " + baseDirecciones );}
			}
			if(baseReferencias != null ) {
				baseReferencias.forEach( r ->{
					try {
						this.referenciaPersonalRepository.remove( r );
					} catch (RelativeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				baseReferencias  = this.referenciaPersonalRepository.findAllByIdCliente( cliente.getId() );
				if(baseReferencias != null) {result = false; } else {log.info( "ELIMINO TODO baseReferencias ======> " + baseReferencias );}
			}
			if(baseTelefonos != null ) {
				baseTelefonos.forEach( r ->{
					try {
						this.telefonoClienteRepository.remove( r );
					} catch (RelativeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				baseTelefonos  = this.telefonoClienteRepository.findAllByIdCliente( cliente.getId() );
				if(baseTelefonos != null) {result = false; } else {log.info( "ELIMINO TODO baseTelefonos ======> " + baseTelefonos );}
			}
			if(baseCuentas != null ) {
				baseCuentas.forEach( r ->{
					try {
						this.cuentaBancariaRepository.remove( r );
					} catch (RelativeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				baseCuentas  = this.cuentaBancariaRepository.findByAllIdCliente( cliente.getId() );
				if(baseCuentas != null) {result = false; } else {log.info( "ELIMINO TODO baseCuentas ======> " + baseCuentas );}
			}
			return result;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje());
		}
	}

	private void guardarTrabajoCliente(SoftbankClienteWrapper sw, TbQoCliente cliente) throws RelativeException {
		TbQoDatoTrabajoCliente trabajo = new TbQoDatoTrabajoCliente();
		sw.getDatosTrabajoCliente().forEach(e->{
			if( e.getEsPrincipal() && e.getActivo() ) {
				trabajo.setActividadEconomica( e.getIdActividadEconomica() );
				trabajo.setActividadEconomicaMupi( e.getCodigoActividadEconomicaClienteMupi() ) ;
				trabajo.setTbQoCliente( cliente ) ;
				trabajo.setNombreEmpresa( e.getNombreEmpresa() );
				trabajo.setIdSoftbank( e.getId() ) ;
				trabajo.setCargo( e.getCodigoCargo() ) ;
				trabajo.setNombreEmpresa( e.getNombreEmpresa() ) ;
				trabajo.setEsRelacionDependencia( e.getEsRelacionDependencia() ) ;
				trabajo.setEsprincipal( e.getEsPrincipal() ) ;
				trabajo.setOcupacion( e.getCodigoOcupacion() );
				trabajo.setOrigenIngreso( e.getCodigoOrigenIngreso() ) ;
				trabajo.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA ) ;				
			}
		});
		this.manageDatoTrabajoCliente( trabajo );
	}
	private void guardarDirecciones(SoftbankClienteWrapper sw, TbQoCliente cliente) {
		sw.getDirecciones().forEach(e->{
			if(e.getActivo()) {
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
				try {
					this.manageDireccionCliente( direccion );
				} catch (RelativeException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	private void guardarReferencias(SoftbankClienteWrapper sw, TbQoCliente cliente) {
		sw.getContactosCliente().forEach(e->{
			if(e.getActivo()) {
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
				try {
					this.manageReferenciaPersonal( referencia );
				} catch (RelativeException e1) {
					e1.printStackTrace();
				}					
			}
		});
	}
	private void guardarTelefonos(SoftbankClienteWrapper sw, TbQoCliente cliente) {
		sw.getTelefonos().forEach(e->{
			if( e.getActivo()) {
				TbQoTelefonoCliente tele = new TbQoTelefonoCliente();
				tele.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
				tele.setIdSoftbank( e.getId() );
				tele.setNumero( e.getNumero() );
				tele.setTipoTelefono( e.getCodigoTipoTelefono() );
				tele.setTbQoCliente(cliente);					
				try {
					this.manageTelefonoCliente( tele );
				} catch (RelativeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	private void guardarCuentas(SoftbankClienteWrapper sw, TbQoCliente cliente) {
		sw.getCuentasBancariasCliente().forEach(e->{
			if(e.getActivo()) {
				TbQoCuentaBancariaCliente cuenta = new TbQoCuentaBancariaCliente();
				cuenta.setIdSoftbank( e.getId() );
				cuenta.setBanco( e.getIdBanco() );
				cuenta.setEsAhorros( e.getEsAhorros() );
				cuenta.setCuenta( e.getCuenta() );
				cuenta.setEstado( e.getActivo() ? EstadoEnum.ACT : EstadoEnum.INA );
				cuenta.setTbQoCliente( cliente );
				try {
					this.manageCuentaBancariaCliente( cuenta ); 
				} catch (RelativeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}									
			}
		});
	}
	
	
	
	
	private NegociacionWrapper generarTablasIniciales(TbQoCliente cliente, String asesor, Long idAgencia, Informacion data) throws RelativeException {
		try {
			TbQoCreditoNegociacion credito = this.createCredito(cliente, asesor, idAgencia);
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
				wrapper.setTelefonoDomicilio(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(), "DOM"));
				wrapper.setTelefonoMovil(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(), "CEL"));
				//wrapper.setTipoOro(this.tipoOro(cliente));
				try {
					//this.guardarProspectoCrm(cliente);
				} catch (Exception e) {
					// TODO Auto-generated catch block
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

	private TbQoCreditoNegociacion createCredito(TbQoCliente cl, String asesor, Long idAgencia) throws RelativeException { 
		try {
			if (cl != null) {
				TbQoNegociacion ng = new TbQoNegociacion();
				ng.setAsesor(asesor);
				ng.setTbQoCliente(cl);
				TbQoNegociacion negociacion = this.manageNegociacion(ng);
				if (negociacion != null) {
					TbQoCreditoNegociacion cr = new TbQoCreditoNegociacion();
					cr.setTbQoNegociacion(negociacion);
					cr.setIdAgencia(idAgencia);
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
	public CrmProspectoCortoWrapper findProspectoCrm(String cedula) throws RelativeException {
		try {
			CrmProspectoCortoWrapper prospecto = CrmApiClient
					.callConsultaProspectoRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_CRM_PROSPECTO_CORTO).getValor(),cedula);
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

//	private TbQoCliente prospectoCrmToTbQoCliente(CrmProspectoCortoWrapper p) {
//		if (p != null) {
//			TbQoCliente c = new TbQoCliente();
//			c.setCedulaCliente(p.getCedula());
//			c.setNombreCompleto(p.getNombreCompleto());
//			c.setEmail(p.getEmail());
////			c.setTelefonoAdicional(p.getPhoneOther());
////			c.setTelefonoFijo(p.getPhoneHome());
////			c.setTelefonoMovil(p.getPhoneMobile());
////			c.setTelefonoTrabajo(p.getPhoneWork());
//			return c;
//		} else {
//			return null;
//		}
//	}
//
//	private Boolean guardarProspectoCrm(TbQoCliente cliente) throws RelativeException {
//		try {
//			CrmEntidadWrapper entidad = new CrmEntidadWrapper();
//			entidad.setCedulaC(cliente.getCedulaCliente());
//			entidad.setEmailAddress(cliente.getEmail());
//			entidad.setEmailAddressCaps(StringUtils.isNotBlank(cliente.getEmail())?cliente.getEmail().toUpperCase():null);
//			entidad.setFirstName(cliente.getNombreCompleto());
//			entidad.setLeadSourceDescription("GESTION QUSKI");
//			entidad.setPhoneMobile(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(),"M").getNumero());
//			entidad.setPhoneHome(this.telefonoClienteRepository.findByClienteAndTipo(cliente.getCedulaCliente(),"F").getNumero());
//			CrmGuardarProspectoWrapper tmp = new CrmGuardarProspectoWrapper(entidad);
//			CrmProspectoWrapper pro = CrmApiClient.callPersistProspectoRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_CRM_PERSIST).getValor(),tmp);
//			return pro != null;
//		} catch (RelativeException e) {
//			throw e;
//		} catch (Exception e) {
//			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
//					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
//
//		}
//	}


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
	 * ************************** @DireccionCliente
	 */
	public List<TbQoDireccionCliente> finddireccionClienteByIdCliente(Long id) throws RelativeException {
		try {
			return direccionClienteRepository.findByIdCliente(id);
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
			if (send != null && send.getId() != null) {
				persisted = this.findDireccionClienteById(send.getId());
				return this.updateDireccionCliente(send, persisted);
			} else if (send != null && send.getId() == null) {
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
			
			persisted.setEstado(EstadoEnum.ACT);
			persisted.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
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
	 * REGISTRA UNA EXCEPCION EN FUNCION DE UNA NEGOCIACION 
	 * @param excepcion
	 * @return
	 * @throws RelativeException 
	 */
	public TbQoExcepcion solicitarExcepcion(TbQoExcepcion excepcion) throws RelativeException {
		TbQoProceso proceso = this.findProcesoByIdReferencia(excepcion.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO);
		if(proceso == null ) {
			proceso = this.findProcesoByIdReferencia(excepcion.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION);
		}
		if(!proceso.getEstadoProceso().equals(EstadoProcesoEnum.CREADO) && !proceso.getEstadoProceso().equals(EstadoProcesoEnum.DEVUELTO) && !proceso.getEstadoProceso().equals(EstadoProcesoEnum.EXCEPCIONADO) ) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE SOLICITAR UNA EXCEPCION INTENTE MAS TARDE");
		}
		cambiarEstado(proceso.getIdReferencia(), proceso.getProceso(), EstadoProcesoEnum.PENDIENTE_EXCEPCION);
		this.excepcionesRepository.inactivarExcepcionByTipoExcepcionAndIdNegociacion(excepcion.getTipoExcepcion(), excepcion.getTbQoNegociacion().getId());
		
		return this.manageExcepcion(excepcion);
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
			return this.tasacionRepository.findByIdCredito(idCreditoNegociacion, pw.getStartRecord(),
					pw.getPageSize(), pw.getSortFields(), pw.getSortDirections());
		} else {
			return this.tasacionRepository.findByIdCredito(idCreditoNegociacion);
		}
	}

	public Long countTasacionByIdCreditoNegociacion(Long idCreditoNegociacion) throws RelativeException {
		return this.tasacionRepository.countFindByIdCredito(idCreditoNegociacion);
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
				return this.tasacionRepository.findByIdCredito(idCredito, pw.getStartRecord(), pw.getPageSize(),
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
	 * 
	 * @param identificacion
	 * @return
	 * @throws RelativeException
	 */
	public SoftbankClienteWrapper findClienteSoftbank(String identificacion) throws RelativeException {
		try {
			
			SoftbankClienteWrapper persisted = SoftBankApiClient.callConsultaClienteRest(this.parametroRepository
					.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_CLIENTE).getValor(),identificacion);
			if (StringUtils.isNotBlank( persisted.getIdentificacion() ) ) {
				return persisted;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public CatalogosSoftbankWrapper traerCatalogos() throws RelativeException {
		try {
			String service;
			CatalogosSoftbankWrapper wp = new CatalogosSoftbankWrapper();
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_AGENCIA).getValor();
			wp.setCatAgencia( SoftBankApiClient.callCatalogoAgenciaRest( service ) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ACTIVIDAD_ECONOMICA).getValor();
			wp.setCatActividadEconomica( SoftBankApiClient.callCatalogoActividadEconomicaRest( service ) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_DIVISION_POLITICA).getValor();
			wp.setCatDivicionPolitica( SoftBankApiClient.callCatalogoDivicionPoliticaRest( service ) );
			
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_BANCO).getValor();
			wp.setCatBanco( SoftBankApiClient.callCatalogoConIdRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_PAIS).getValor();
			wp.setCatPais( SoftBankApiClient.callCatalogoConIdRest(service) );
			
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_PROFESION).getValor();
			wp.setCatProfesion( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_EDUCACION).getValor();
			wp.setCatEducacion( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ESTADO_CIVIL).getValor();
			wp.setCatEstadoCivil( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_SECTOR_VIVIENDA).getValor();
			wp.setCatSectorvivienda( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_VIVIENDA).getValor();
			wp.setCatTipoVivienda( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_REFERENCIA).getValor();
			wp.setCatTipoReferencia( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_DIRECCION).getValor();
			wp.setCatTipoDireccion( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_IMP_COM).getValor();
			wp.setCatImpCom( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_JOYA).getValor();
			wp.setCatTipoJoya( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ESTADO_JOYA).getValor();
			wp.setCatEstadoJoya( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_ORO).getValor();
			wp.setCatTipoOro( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ESTADO_PROCESO).getValor();
			wp.setCatEstadoProceso( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_FIRMANTE_OPERACION).getValor();
			wp.setCatFirmanteOperacion( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_MOTIVO_DEVOLUCION_APROBACION).getValor();
			wp.setCatMotivoDevolucionAprobacion( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ACTIVIDAD_ECONOMICA_MUPI).getValor();
			wp.setCatActividadEconomicaMupi( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_SEXO).getValor();
			wp.setCatSexo( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TIPO_FUNDA).getValor();
			wp.setCatTipoFunda( SoftBankApiClient.callCatalogoRest(service) );
			
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_CARGO).getValor();
			wp.setCatCargo( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_OCUPACION).getValor();
			wp.setCatOcupacion( SoftBankApiClient.callCatalogoRest(service) );
			service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_ORIGEN_INGRESOS).getValor();
			wp.setCatOrigenIngreso( SoftBankApiClient.callCatalogoRest(service) );
			return wp;
			
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	}

	public List<SoftbankOperacionWrapper> consultarRiesgoSoftbank(String identificacion) throws RelativeException {
		try {
			SoftbankConsultaWrapper consulta = new SoftbankConsultaWrapper(identificacion);
			SoftbankRiesgoWrapper persisted = SoftBankApiClient.callConsultaRiesgoRest(consulta,
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SOFTBANK_RIESGO_ACUMULADO).getValor());
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
			SoftbankTablaAmortizacionWrapper persisted = SoftBankApiClient.callConsultaTablaAmortizacionRest(
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SOFTBANK_TABLA_AMORTIZACION).getValor(),entrada);
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

	private List<CatalogoWrapper>  catalogoImpCom() throws RelativeException {
		try {
			String service = this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_IMP_COM).getValor();
			return SoftBankApiClient.callCatalogoRest( service );
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}
	}
	private List<TbQoRiesgoAcumulado> createRiesgoFrontSoftBank(List<SoftbankOperacionWrapper> operaciones,
			TbQoNegociacion negociacion) throws RelativeException {
		if (operaciones != null) {
			List<TbQoRiesgoAcumulado> list = new ArrayList<>();
			operaciones.forEach(e -> {
				TbQoRiesgoAcumulado r = new TbQoRiesgoAcumulado(negociacion);
				r.setReferencia(e.getReferencia());
				r.setNumeroOperacion(e.getNumeroOperacion());
				r.setCodigoCarteraQuski(e.getCodigoCarteraQuski());
				r.setTipoOperacion(e.getTipoOperacion());
				r.setEsDemandada(e.getEsDemandada());
				r.setFechaEfectiva(e.getFechaEfectiva());
				r.setFechaVencimiento(e.getFechaVencimiento());
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
			cliente.setFechaNacimiento(s.getFechaNacimiento());
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
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
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
			throw new RelativeException(Constantes.ERROR_CODE_CREATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMessage());
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
			sof.setSegundoApellido( cli.getApellidoMaterno() );              
			sof.setPrimerNombre( cli.getPrimerNombre() );             
			sof.setSegundoNombre( cli.getSegundoNombre() );      
			sof.setCodigoMotivoVisita( cli.getCanalContacto() );
			sof.setEsCliente( true );              
			sof.setCodigoMotivoVisita( cli.getCanalContacto() );                             
			sof.setFechaIngreso(cli.getFechaActualizacion());                                  
			sof.setIdPaisNacimiento( cli.getNacionalidad() );                            
			sof.setIdPais( cli.getNacionalidad() );                                      
			sof.setIdLugarNacimiento( Long.valueOf( cli.getLugarNacimiento() ) ); 
			sof.setFechaNacimiento(cli.getFechaNacimiento());                            
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
					subList.add( new TelefonosContactoClienteWrapper( "DOM", e.getTelefonoFijo() ) );
					subList.add( new TelefonosContactoClienteWrapper( "CEL", e.getTelefonoMovil()) );
					ref.setTelefonos( subList );
					contactosCliente.add(ref);
				});
				sof.setContactosCliente( contactosCliente );
			}
			if(wp.getDatosTrabajos() != null) {
				List<SoftbankDatosTrabajoWrapper> datosTrabajo = new ArrayList<>();
				wp.getDatosTrabajos().forEach( t ->{
					SoftbankDatosTrabajoWrapper da = new SoftbankDatosTrabajoWrapper();
					da.setCodigoCargo( t.getCargo() );
					da.setCodigoActividadEconomicaClienteMupi( t.getActividadEconomicaMupi() != null ?  t.getActividadEconomicaMupi() : QuskiOroConstantes.OTRAS_ACTIVIDADES );
					da.setCodigoOcupacion( t.getOcupacion() );
					da.setCodigoOrigenIngreso( t.getOrigenIngreso() );
					da.setNombreEmpresa( t.getNombreEmpresa() );
					da.setEsRelacionDependencia( t.getEsRelacionDependencia() );
					da.setEsPrincipal( t.getEsprincipal() );
					da.setIdActividadEconomica( t.getActividadEconomica() != null ? t.getActividadEconomica() : Long.valueOf(QuskiOroConstantes.ACTIVIDADES_NO_ECONOMICAS) );
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

	public Boolean crearClienteSoftbank(SoftbankClienteWrapper cliente) throws RelativeException {
		try {			
			if (cliente.getIdTipoIdentificacion() != null) {
				if (cliente.getIdentificacion() != null) {
					if (cliente.getCodigoUsuario() != null && cliente.getCodigoUsuarioAsesor() != null ) {
						SoftbankRespuestaWrapper result = SoftBankApiClient.callCrearClienteRest(this.parametroRepository
								.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_CLIENTE).getValor(),cliente);
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
		} catch (RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
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
						SoftbankRespuestaWrapper result = SoftBankApiClient.callEditarClienteRest(cliente,
								this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_EDITAR_CLIENTE).getValor());
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

	
	public CalculadoraRespuestaWrapper simularOfertasCalculadoraPrueba(CalculadoraEntradaWrapper wrapper)
			throws RelativeException {
		try {
			return CalculadoraRespuestaWrapper.generateMockupEstandar();
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMessage());
		}

	} */
	

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
					retorno.setInstitucionFinanciera( String.valueOf( response.getINFOFINAN().getINSTITUCIONFINANCIERA()  ));
					retorno.setTipoCuenta( response.getINFOFINAN().getTIPOCUENTA() );
					retorno.setNumeroCuenta( String.valueOf( response.getINFOFINAN().getNUMEROCUENTA() ) );
					retorno.setFirmaRegularizada( response.getINFOFINAN().getFIRMAREGULARIZADA());
					return retorno;
				}
				return null;
			} catch (RelativeException e) {
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");

			}
	}
	public List<TbQoTasacion> getDetalleJoya(TbQoCliente cliente, TbQoTasacion joya) throws RelativeException {		
		
		try {
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			contentXMLGarantia= contentXMLGarantia
					.replace( "--tipo-joya--" ,joya.getTipoJoya())
					.replace("--descripcion--",joya.getDescripcion())
					.replace("--estado-joya--", joya.getEstadoJoya())
					.replace("--tipo-oro-quilataje--", joya.getTipoOro())
					.replace("--peso-gr--", joya.getPesoBruto().toString())
					.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
					.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
					.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra() != null ?joya.getDescuentoPesoPiedra().toString():"0")
					.replace("--peso-neto--", joya.getPesoNeto().toString())
					.replace("--precio-oro--", joya.getValorOro().toString())
					.replace("--valor-aplicable-credito--", "293.02")
					.replace("--valor-realizacion--", "232.07")
					.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
					.replace("--descuento-suelda--", joya.getDescuentoSuelda().toString());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}
	
	// TODO: Testear metodo por conflictos
	public SimularResponse simularOfertasCalculadora(Long idCredito, BigDecimal montoSolicitado, BigDecimal riesgoTotal,String codigoAgencia) throws RelativeException {				
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
						.replace("--peso-gr--", joya.getPesoBruto().toString())
						.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra().toString())
						.replace("--peso-neto--", joya.getPesoNeto().toString())
						.replace("--precio-oro--", joya.getValorOro().toString())
						.replace("--valor-aplicable-credito--", joya.getValorComercial().toString())
						.replace("--valor-realizacion--", joya.getValorRealizacion().toString())
						.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
						.replace("--descuento-suelda--", joya.getDescuentoSuelda().toString());
				XMLGarantias.append(x);
			}
			log.info("==============>>>>> XML garantia");
			String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA).getValor();
			contentXMLcalculadora = contentXMLcalculadora
					.replace("--perfil-riesgo--", "1")//donde saco el perfil
					.replace("--origen-operacion--", "N")
					.replace("--riesgo-total--", riesgoTotal.toString())
					.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(credito.getTbQoNegociacion().getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
					.replace("--perfil-preferencia--", "A") //donde saco el tipo
					.replace("--agencia-originacion--", StringUtils.isBlank(codigoAgencia)?"01":codigoAgencia)
					.replace("--identificacion-cliente--",credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente())
					.replace("--calificacion-mupi--", credito.getTbQoNegociacion().getTbQoCliente().getAprobacionMupi())
					.replace("--cobertura-exepcionada--", StringUtils.isNotBlank(credito.getCobertura())?credito.getCobertura():"0" )
					.replace("--garanttias-detalle--", XMLGarantias.toString())
					.replace("--monto-solicitado--", montoSolicitado.toString());
				log.info("==============>>>>> XML calculadora");
				TokenWrapper token = ApiGatewayClient.getToken(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_APIGW).getValor(),
						this.parametroRepository.findByNombre(QuskiOroConstantes.AUTH_APIGW).getValor());
				SimularResponse res = ApiGatewayClient.callCalculadoraRest(this.parametroRepository.findByNombre(QuskiOroConstantes.URL_WS_QUSKI_CALCULADORA).getValor(),
						token.getToken_type() +" "+ token.getAccess_token(), contentXMLcalculadora);
				if (res.getSimularResult().getXmlVariablesInternas().getVariablesInternas().getVariable() != null) {
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
				return res;
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
		}
		
	}


		public SimularResponse simularOfertasCalculadoraRenovacion(DetalleCreditoWrapper creditoSoft, 
				BigDecimal riesgoTotal,String codigoAgencia,String coberturaExcepcionada) throws RelativeException {				
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
							.replace("--peso-gr--", joya.getPesoBruto().toString())
							.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
							.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
							.replace("--descuento-peso-piedras--", joya.getDescuentoPiedras().toString())
							.replace("--peso-neto--", joya.getPesoNeto().toString())
							.replace("--precio-oro--", joya.getValorOro().toString())
							.replace("--valor-aplicable-credito--", joya.getValorComercial().toString())
							.replace("--valor-realizacion--", joya.getValorRealizacion().toString())
							.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
							.replace("--descuento-suelda--", joya.getDescuentoSuelda().toString());
					XMLGarantias.append(x);
				}
				log.info("==============>>>>> XML garantia");
				String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA_RENOVAR).getValor();
				contentXMLcalculadora = contentXMLcalculadora
						.replace("--perfil-riesgo--", "1")
						.replace("--origen-operacion--", creditoSoft.getCredito().getEsMigrado()?"O":"E")
						.replace("--riesgo-total--", riesgoTotal.toString())
						.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(creditoSoft.getCliente().getFechaNacimiento(),QuskiOroUtil.DATE_FORMAT_QUSKI) )
						.replace("--perfil-preferencia--", "A") 
						.replace("--agencia-originacion--", StringUtils.isBlank(codigoAgencia)?"01":codigoAgencia)
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
						.replace("--monto-solicitado--", "0.00")
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
			}
			
		}
		public SimularResponse simularOfertaRenovacionExcepcion(Long idCredito, String cobertura) throws RelativeException {				
			try {
				TbQoCreditoNegociacion credito = this.findCreditoNegociacionById(idCredito);
				if( credito == null ) { throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DEL CREDITO");}
				List<TbQoTasacion> listTasacion = this.tasacionRepository.findByIdCredito(idCredito);
				if(listTasacion == null || listTasacion.isEmpty()) {throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DE LAS JOYAS");}
				DetalleCreditoWrapper creditoSoft = this.traerCreditoVigente( credito.getNumeroOperacionMadre() );
				
				String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
				StringBuilder XMLGarantias = new StringBuilder();
				for(TbQoTasacion joya:listTasacion) {
					String x = contentXMLGarantia
							.replace( "--tipo-joya--" ,joya.getTipoJoya())
							.replace("--descripcion--",joya.getDescripcion())
							.replace("--estado-joya--", joya.getEstadoJoya())
							.replace("--tipo-oro-quilataje--", joya.getTipoOro())
							.replace("--peso-gr--", joya.getPesoBruto().toString())
							.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
							.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
							.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra().toString())
							.replace("--peso-neto--", joya.getPesoNeto().toString())
							.replace("--precio-oro--", joya.getValorOro().toString())
							.replace("--valor-aplicable-credito--", joya.getValorComercial().toString())
							.replace("--valor-realizacion--", joya.getValorRealizacion().toString())
							.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
							.replace("--descuento-suelda--", joya.getDescuentoSuelda().toString());
					XMLGarantias.append(x);
				}
				log.info("==============>>>>> XML garantia");
				String contentXMLcalculadora = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_QUSKI_CALCULADORA_RENOVAR).getValor();
				contentXMLcalculadora = contentXMLcalculadora
						.replace("--perfil-riesgo--", "1")
						.replace("--origen-operacion--", creditoSoft.getCredito().getEsMigrado()?"O":"E")
						.replace("--riesgo-total--", "0.00")
						.replace("--fecha-nacimiento--", QuskiOroUtil.dateToString(credito.getTbQoNegociacion().getTbQoCliente().getFechaNacimiento(), QuskiOroUtil.DATE_FORMAT_QUSKI))
						.replace("--perfil-preferencia--", "A") 
						.replace("--agencia-originacion--", credito.getIdAgencia() != null ? credito.getIdAgencia().toString() : "1" )
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM," AL LLAMAR WS CALCULADORA Y AGREGAR LA GARANTIA");
			}
		}
	public List<OpcionWrapper> simularOfertaExcepcionada(Long idCredito, Long cobertura, Long idAgencia) throws Exception {				
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
						.replace("--peso-gr--", joya.getPesoBruto().toString())
						.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra().toString())
						.replace("--peso-neto--", joya.getPesoNeto().toString())
						.replace("--precio-oro--", joya.getValorOro().toString())
						.replace("--valor-aplicable-credito--", joya.getValorComercial().toString())
						.replace("--valor-realizacion--", joya.getValorRealizacion().toString())
						.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
						.replace("--descuento-suelda--", joya.getDescuentoSuelda().toString());
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
						.replace("--agencia-originacion--", idAgencia == null ? "01" : idAgencia.toString())
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}			
	}
	public List<OpcionWrapper> simularOfertaExcepcionadaRenovacion(Long idCredito, String cobertura) throws Exception {				
		try {
			TbQoCreditoNegociacion credito = this.findCreditoNegociacionById(idCredito);
			if( credito == null ) { throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DEL CREDITO");}
			List<TbQoTasacion> listTasacion = this.tasacionRepository.findByIdCredito(idCredito);
			if(listTasacion == null || listTasacion.isEmpty()) {throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO LA INFORMACION DE LAS JOYAS");}
			DetalleCreditoWrapper creditoSoft = this.traerCreditoVigente( credito.getNumeroOperacionMadre() );
			
			String contentXMLGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.CONTENT_XML_GARANTIA).getValor();
			StringBuilder XMLGarantias = new StringBuilder();
			for(TbQoTasacion joya:listTasacion) {
				String x = contentXMLGarantia
						.replace( "--tipo-joya--" ,joya.getTipoJoya())
						.replace("--descripcion--",joya.getDescripcion())
						.replace("--estado-joya--", joya.getEstadoJoya())
						.replace("--tipo-oro-quilataje--", joya.getTipoOro())
						.replace("--peso-gr--", joya.getPesoBruto().toString())
						.replace("--tiene-piedras--", joya.getTienePiedras()?"S":"N")
						.replace("--detalle-piedras--", joya.getTienePiedras()?joya.getDetallePiedras():" ")
						.replace("--descuento-peso-piedras--", joya.getDescuentoPesoPiedra().toString())
						.replace("--peso-neto--", joya.getPesoNeto().toString())
						.replace("--precio-oro--", joya.getValorOro().toString())
						.replace("--valor-aplicable-credito--", joya.getValorComercial().toString())
						.replace("--valor-realizacion--", joya.getValorRealizacion().toString())
						.replace("--numero-piezas--", joya.getNumeroPiezas().toString())
						.replace("--descuento-suelda--", joya.getDescuentoSuelda().toString());
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
					.replace("--agencia-originacion--", credito.getIdAgencia() != null ? credito.getIdAgencia().toString() : "1" )
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
					.replace("--numero-operacion-renovar--", creditoSoft.getCredito().getNumeroOperacion())
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
				// TODO Auto-generated catch block
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
			if( StringUtils.isNotBlank( send.getCodigoDevuelto() ) ) {
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
			if( send.getMontoDiferido() != null ) {
			    persisted.setMontoDiferido(  send.getMontoDiferido() );
			}
			if( send.getMontoPreaprobado() != null ) {
			    persisted.setMontoPreaprobado(  send.getMontoPreaprobado() );
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
			if( send.getTipo() != null ) {
			    persisted.setTipo(  send.getTipo() );
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
	 * variables,Riesgos Direcciones Referencias
	 * 
	 * @author Kléber Guerra Relative Engine
	 * @param id
	 * @return
	 * @throws RelativeException
	 */
	public AprobacionWrapper traerCreditoNegociacionExistente(Long idNego) throws RelativeException {
		try {
			AprobacionWrapper tmp = new AprobacionWrapper( Boolean.FALSE );
			tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( idNego ) );
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( idNego ) );
			tmp.setProceso( this.procesoRepository.findByIdCreditoNuevo( idNego ) );
			tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( idNego ) );
			tmp.setJoyas( this.tasacionRepository.findByIdCredito( tmp.getCredito().getId() ) );
			if(tmp.getExisteError()) {return tmp;}
			tmp.setRiesgos( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente()), tmp.getCredito().getTbQoNegociacion()) );
			Long idCliente = tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId();
			tmp.setCuentas(     this.cuentaBancariaRepository.findByClienteAndCuenta( idCliente, tmp.getCredito().getNumeroCuenta() ));
			tmp.setTelefonos(   this.telefonoClienteRepository.findByIdCliente( idCliente));
			tmp.setDirecciones( this.direccionClienteRepository.findByIdCliente( idCliente));
			tmp.setTrabajos(    this.datoTrabajoClienteRepository.findByIdCliente( idCliente));
			tmp.setReferencias( this.referenciaPersonalRepository.findByIdCliente( idCliente));
			return tmp;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}
	public AprobacionNovacionWrapper traerCreditonovacionPorAprobar(Long idNego) throws RelativeException {
		try {
			AprobacionNovacionWrapper tmp = new AprobacionNovacionWrapper( Boolean.FALSE );
			tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( idNego ) );
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( idNego ) );
			tmp.setProceso( this.procesoRepository.findByIdCreditoNovacion( idNego ) );
			tmp.setJoyas( this.tasacionRepository.findByIdCredito( tmp.getCredito().getId() ) );
			if(tmp.getExisteError()) {return tmp;}			
			tmp.setRiesgos( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente()), tmp.getCredito().getTbQoNegociacion()) );
			tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( idNego ) );
			tmp.setCreditoAnterior( this.traerCreditoVigente( tmp.getCredito().getNumeroOperacionMadre() ));
			tmp.setPagos( this.registrarPagoRepository.findByIdCredito(tmp.getCredito().getId() ));
			Long idCliente = tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId();
			tmp.setCuenta(     this.cuentaBancariaRepository.findByClienteAndCuenta( idCliente, tmp.getCredito().getNumeroCuenta() ));
			tmp.setTelefonos(   this.telefonoClienteRepository.findByIdCliente( idCliente));
			tmp.setDirecciones( this.direccionClienteRepository.findByIdCliente( idCliente));
			tmp.setTrabajos(    this.datoTrabajoClienteRepository.findByIdCliente( idCliente));
			tmp.setReferencias( this.referenciaPersonalRepository.findByIdCliente( idCliente));
			return tmp;
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getMensaje());
		}
	}

	public DetalleCreditoEnProcesoWrapper traerCreditoNegociacion(Long idNego) throws RelativeException {
		try {
			DetalleCreditoEnProcesoWrapper tmp = new DetalleCreditoEnProcesoWrapper( Boolean.FALSE );
			tmp.setCredito( this.creditoNegociacionRepository.findCreditoByIdNegociacion( idNego ) );
			tmp.setProceso( this.procesoRepository.findByIdCreditoNuevo( idNego ) );
			if( tmp.getExisteError() ) {return tmp;}
			tmp.setExcepciones( this.excepcionesRepository.findByIdNegociacion( idNego ) );
			tmp.setRiesgos(this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank(tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente()), tmp.getCredito().getTbQoNegociacion()));
			tmp.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion( idNego ) );
			tmp.setJoyas( this.tasacionRepository.findByIdCredito( tmp.getCredito().getId() ) );
			Long idCliente = tmp.getCredito().getTbQoNegociacion().getTbQoCliente().getId();
			tmp.setTelefonos(   this.telefonoClienteRepository.findByIdCliente( idCliente));			
			return tmp;
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
		}catch(RelativeException e ){
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getLocalizedMessage());
		}
	}
	public DetalleCreditoWrapper traerCreditoVigente( String numeroOperacion ) throws RelativeException{
		try {
			DetalleCreditoWrapper detalle = new DetalleCreditoWrapper();
			String urlCredito  = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GLOBAL).getValor();
			String urlCliente  = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_CLIENTE).getValor();
			String urlGarantia = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GARANTIA).getValor();
			String urlRubro    = this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_RUBRO).getValor();
			
			RespuestaConsultaGlobalWrapper rCredito = SoftBankApiClient.callConsultarOperacionRest( new ConsultaOperacionGlobalWrapper( numeroOperacion ), urlCredito); 
			if(rCredito.getNumeroTotalRegistros() != Long.valueOf( 1 ) ) { return null;}
			detalle.setCredito( rCredito.getOperaciones().get( 0 ));
			detalle.setCliente( SoftBankApiClient.callConsultaClienteRest(urlCliente, rCredito.getOperaciones().get( 0 ).getIdentificacion() ) );
			detalle.setGarantias( SoftBankApiClient.callConsultarGarantiasRest( new ConsultaGarantiaWrapper( numeroOperacion ), urlGarantia ) );
			detalle.setRubros( SoftBankApiClient.callConsultarRubrosRest( new ConsultaRubrosWrapper( numeroOperacion ), urlRubro ) );
			return detalle;
			
			
		}catch(RelativeException | UnsupportedEncodingException e ){
			throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA + e.getLocalizedMessage());
		}
	}
	
	public TbQoCreditoNegociacion optenerNumeroDeFunda(TbQoCreditoNegociacion c) throws RelativeException {
		TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findById(c.getId());
		CrearOperacionEntradaWrapper operacionSoftBank = 
				new CrearOperacionEntradaWrapper(credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente(),
						credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() ); 
		operacionSoftBank.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  );
	
		operacionSoftBank.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion()  ); 				

		operacionSoftBank.setDatosImpCom( this.generarImpCom( credito ) );
		operacionSoftBank.setCodigoTipoCarteraQuski( credito.getTipoCarteraQuski() );
		operacionSoftBank.setNumeroOperacion( credito.getNumeroOperacion() );
		operacionSoftBank.setCodigoTipoPrestamo( QuskiOroConstantes.SOFT_TIPO_PRESTAMO );
		operacionSoftBank.setMontoSolicitado( credito.getMontoSolicitado() );
		operacionSoftBank.setMontoFinanciado( credito.getMontoFinanciado() );
		operacionSoftBank.setPagoDia( Long.valueOf(1) );//arreglar
		operacionSoftBank.setCodigoGradoInteres( QuskiOroConstantes.SOFT_GRADO_INTERES );
		operacionSoftBank.setDatosRegistro( 
				new DatosRegistroWrapper(
						credito.getTbQoNegociacion().getAsesor(), 
						c.getIdAgencia(),  
						QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT),
						credito.getTbQoNegociacion().getTbQoCliente().getCanalContacto(),
						credito.getCodigo())
				); 
		DatosGarantiasWrapper datos = new DatosGarantiasWrapper();

		datos.setCodigoTipoFunda( c.getCodigoTipoFunda() ); 
		datos.setGarantias(new ArrayList<>());
		operacionSoftBank.setDatosGarantias( datos );
		CrearOperacionRespuestaWrapper result;
		try {
			result = SoftBankApiClient.callCrearOperacion01Rest(operacionSoftBank,
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_OPERACION).getValor());
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL CREAR OPERACION EN SOFTBANK Y ASIGNAR NUMERO DE FUNDA");
		}
		if(StringUtils.isBlank(result.getNumeroFundaJoya())) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"EL NUMERO DE FUNDA ASIGNADO ESTA VACIO");
		}
		credito.setNumeroFunda(Long.valueOf(result.getNumeroFundaJoya()) );
		credito.setCodigoTipoFunda(c.getCodigoTipoFunda());
		credito.setNumeroOperacion(result.getNumeroOperacion());
		return this.manageCreditoNegociacion(credito);
	}

	
	
	public CreditoCreadoSoftbank crearOperacionNuevo(  TbQoCreditoNegociacion wp, String correoAsesor) throws RelativeException{
		try {
			
			CrearOperacionEntradaWrapper op = this.convertirCreditoCoreToCreditoSoftbank( this.manageCreditoNegociacion( wp ) ); 
			String sinExcepcion = this.parametroRepository.findByNombre( QuskiOroConstantes.SIN_EXCEPCION).getValor();
			log.info("ESTA ES EL PARAMETRO ============> "+ sinExcepcion);
			if(  wp.getExcepcionOperativa() != null && !wp.getExcepcionOperativa().equalsIgnoreCase( sinExcepcion )) {
				this.notificarExcepcionOperativa( wp.getTbQoNegociacion().getAsesor(), correoAsesor, wp.getExcepcionOperativa());
			}
			
			if(op != null ) {
				CrearOperacionRespuestaWrapper operacion = 	SoftBankApiClient.callCrearOperacion01Rest(
						op, this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SERVICIO_SOFTBANK_CREAR_OPERACION).getValor());
				CreditoCreadoSoftbank result = new CreditoCreadoSoftbank( this.guardarOperacion( operacion, wp ) );
				result.setCuotasAmortizacion( this.consultarTablaAmortizacion( operacion.getNumeroOperacion(), operacion.getUriHabilitantes(),  op.getDatosRegistro())  );
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
	public CreditoCreadoSoftbank crearOperacionRenovacion(  TbQoCreditoNegociacion wp ) throws RelativeException{
		try {
			CrearOperacionRenovacionWrapper op = this.convertirCreditoCoreToCreditoSoftbankRenovacion( this.manageCreditoNegociacion( wp ) ); 
			if(op != null ) {
				CrearOperacionRespuestaWrapper operacion = 	SoftBankApiClient.callRenovarOperacionRest(
						op, this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_RENOVAR_OPERACION).getValor());
				CreditoCreadoSoftbank result = new CreditoCreadoSoftbank( this.guardarOperacion( operacion, wp ) );
				result.setCuotasAmortizacion( this.consultarTablaAmortizacion( operacion.getNumeroOperacion(), operacion.getUriHabilitantes(),  op.getDatosRegistro())  );
				return result; 
			}
			return null;
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	
	public List<CuotasAmortizacionWrapper> consultarTablaAmortizacion(String numeroOperacion, String usuario, Long agencia) throws RelativeException{
		try {
			ConsultaTablaWrapper entrada = new 	ConsultaTablaWrapper(numeroOperacion, null, null);
			SoftbankTablaAmortizacionWrapper persisted = SoftBankApiClient.callConsultaTablaAmortizacionRest(
					this.parametroRepository.findByNombre(QuskiOroConstantes.URL_SOFTBANK_TABLA_AMORTIZACION).getValor(),entrada);
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
			credito.setNumeroFunda( Long.valueOf( operacion.getNumeroFundaJoya() ) );
			
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
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	@SuppressWarnings("deprecation")
	private CrearOperacionRenovacionWrapper convertirCreditoCoreToCreditoSoftbankRenovacion( TbQoCreditoNegociacion credito )  throws RelativeException {
		try {			
			TbQoCliente cliente = credito.getTbQoNegociacion().getTbQoCliente();
			List<TbQoCuentaBancariaCliente> cuentaCliente = this.cuentaBancariaRepository.findByIdCliente( cliente.getId() );
			if( cuentaCliente == null ){
				throw new RelativeException(Constantes.ERROR_CODE_READ);
			}
			CrearOperacionRenovacionWrapper result = new CrearOperacionRenovacionWrapper(); 
			result.setIdTipoIdentificacion( Long.valueOf( 1 ) ); 
			result.setIdentificacion(credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() ); 
			result.setNombreCliente( credito.getTbQoNegociacion().getTbQoCliente().getNombreCompleto() ); 
			result.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  ) ;
			
			result.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion() );
			if(result.getCodigoTablaAmortizacionQuski() == null) { throw new RelativeException(Constantes.ERROR_CODE_READ); }
			result.setCodigoTipoPrestamo( QuskiOroConstantes.SOFT_TIPO_PRESTAMO );
			result.setCodigoGradoInteres( QuskiOroConstantes.SOFT_GRADO_INTERES );
			result.setMontoFinanciado( credito.getMontoFinanciado() ) ;
			result.setPagoDia( Long.valueOf( credito.getPagoDia() != null ? credito.getPagoDia().getDate() : 1 ) );
			GaranteWrapper garante = null;
			if(credito.getIdentificacionApoderado() != null && credito.getNombreCompletoApoderado() != null) {
				garante = new GaranteWrapper( Long.valueOf(1), Long.valueOf( credito.getIdentificacionApoderado() ), "SAP", credito.getNombreCompletoApoderado());				
			}
			if(credito.getIdentificacionCodeudor() != null && credito.getNombreCompletoCodeudor() != null) {
				garante = new GaranteWrapper( Long.valueOf(1), Long.valueOf( credito.getIdentificacionCodeudor() ), "SCD", credito.getNombreCompletoCodeudor());				
			}
			result.setDatosCodeudorApoderado( garante );
			result.setDatosRegistro(
					new DatosRegistroWrapper(
					credito.getTbQoNegociacion().getAsesor(), 
					credito.getIdAgencia(),  
					QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT),
					cliente.getCanalContacto(),
					credito.getCodigo())
					);
			result.setDatosImpCom(this.generarImpCom( credito )  );
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
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	@SuppressWarnings("deprecation")
	private CrearOperacionEntradaWrapper convertirCreditoCoreToCreditoSoftbank( TbQoCreditoNegociacion credito )  throws RelativeException {
		try {			
			TbQoCliente cliente = credito.getTbQoNegociacion().getTbQoCliente();
			List<TbQoTasacion> joyas = this.tasacionRepository.findByIdCredito( credito.getId() ); 
			List<TbQoCuentaBancariaCliente> cuentaCliente = this.cuentaBancariaRepository.findByIdCliente( cliente.getId() );
			if( joyas == null )        { 
				return null; 
			}
			if( cuentaCliente == null ){
				return null; 
			}
			CrearOperacionEntradaWrapper result = new CrearOperacionEntradaWrapper(cliente.getCedulaCliente(), cliente.getNombreCompleto() ); 
			result.setFechaEfectiva( QuskiOroUtil.dateToString(credito.getFechaCreacion(), QuskiOroConstantes.SOFT_DATE_FORMAT)  );
		
			result.setCodigoTablaAmortizacionQuski( credito.getTablaAmortizacion()  ); 				
			if(result.getCodigoTablaAmortizacionQuski() == null) { return null;}
			result.setDatosImpCom( this.generarImpCom( credito ) );
			result.setCodigoTipoCarteraQuski( credito.getTipoCarteraQuski() );
			if(StringUtils.isNotBlank(credito.getNumeroOperacion() ) ) {
				result.setNumeroOperacion( credito.getNumeroOperacion() );
			}
			result.setCodigoTipoPrestamo( QuskiOroConstantes.SOFT_TIPO_PRESTAMO );
			result.setMontoSolicitado( credito.getMontoSolicitado() );
			result.setMontoFinanciado( credito.getMontoFinanciado() );
			result.setPagoDia( Long.valueOf( credito.getPagoDia() != null ? credito.getPagoDia().getDate() : 1 ) );
			result.setCodigoGradoInteres( QuskiOroConstantes.SOFT_GRADO_INTERES );
			result.setDatosRegistro( 
					new DatosRegistroWrapper(
							credito.getTbQoNegociacion().getAsesor(), 
							credito.getIdAgencia(),  
							QuskiOroUtil.dateToString( new Timestamp(System.currentTimeMillis()), QuskiOroConstantes.SOFT_DATE_FORMAT),
							cliente.getCanalContacto(),
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
			if( credito.getNumeroFunda() != Long.valueOf( 0 ) && credito.getNumeroFunda() != null ) {
				datos.setNumeroFundaJoya( credito.getNumeroFunda().toString() );								
			}
			datos.setCodigoTipoFunda( credito.getCodigoTipoFunda() ); 
			datos.setUriImagenSinFunda( credito.getUriImagenSinFunda() );
			datos.setUriImagenConFunda( credito.getUriImagenConFunda() );
			List<JoyaWrapper> listjoyas = generarJoyas(credito, joyas);
			datos.setGarantias( listjoyas );
			result.setDatosGarantias( datos );
			return result;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}
	}
	private List<DatosImpComWrapper> generarImpCom(TbQoCreditoNegociacion credito) throws RelativeException {
		try {
			List<DatosImpComWrapper> listImpCom = new ArrayList<DatosImpComWrapper>();
			List<CatalogoWrapper>  listCatalogo = this.catalogoImpCom();
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

			listCatalogo.forEach(e->{
				DatosImpComWrapper item = new DatosImpComWrapper();
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
			});
			return listImpCom;
		} catch (RelativeException e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, e.getMessage());
		}

	}
	private List<JoyaWrapper> generarJoyas(TbQoCreditoNegociacion credito, List<TbQoTasacion> joyas) {
		List<JoyaWrapper> listjoyas = new ArrayList<>();
		joyas.forEach(e->{
			JoyaWrapper joyaSoft = new JoyaWrapper();
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
			joyaSoft.setReferencia( e.getId().toString() );
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
	public void notificarExcepcionOperativa(String asesor, String correoAsesor, String excepcionOperativa) {
		try {
			log.info("ESTOY LLEGANDO HASTA AQUI? ========> "+ correoAsesor);
			List<TbMiParametro> paras = this.parametroRepository.findByNombreAndTipoOrdered(null, QuskiOroConstantes.PARA_EXC, false);
			String[] array = new String[paras.size()];
			for (int i = 0; i < paras.size(); ++i) {
				array[i] = paras.get(i).getValor().replace("--Correo asesor--", correoAsesor);
				log.info(" ESTE ES UN PARA DEL CORREO ===========> "+ array[i]);
			}
					
			String asunto = this.parametroRepository.findByNombre( QuskiOroConstantes.M_ASUNTO).getValor()
					.replace("--Tipo Excepcion--", excepcionOperativa);
			String contenido = this.parametroRepository.findByNombre( QuskiOroConstantes.M_CONTENIDO).getValor()
					.replace("--asesor--", asesor)
					.replace("--Tipo Excepcion--", excepcionOperativa);

			this.mailNotificacion(array, asunto, contenido, null);
		} catch (RelativeException e) {
			e.getStackTrace();
			log.info("ERROR ========>" + QuskiOroConstantes.ERROR_AL_CONSUMIR_SERVICIOS + e.getMessage());
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
				if(proceso == ProcesoEnum.DEVOLUCION || proceso == ProcesoEnum.CANCELACION_DEVOLUCION) {
					TbQoDevolucion persistedDevolucion  = ds.findDevolucionById( id );
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
	private TbQoProceso createProcesoNovacion( Long idReferencia, String usuario) throws RelativeException {
		try {
			TbQoProceso send = new TbQoProceso( Long.valueOf( idReferencia ));
			send.setProceso( ProcesoEnum.RENOVACION );
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

	public Boolean devolverAprobarCredito(Long id, String cash, String descripcion, String codigo) throws RelativeException {
		if( id == null) { return false; }
		TbQoCreditoNegociacion persisted = this.findCreditoNegociacionById(id);
		if( persisted == null) { return false;}
		persisted.setCodigoCash(cash);
		persisted.setCodigoDevuelto(codigo);
		persisted.setDescripcionDevuelto(descripcion);
		persisted = this.manageCreditoNegociacion(persisted);
		if(persisted.getDescripcionDevuelto() != null) { return true; }else {return false;}
	}

	public Boolean negarExcepcion(Long idExc, String obsAprobador, String aprobador, ProcesoEnum tipoProceso)  throws RelativeException {
		try {
			TbQoExcepcion exc = this.finExcepcionById(idExc);
			if(exc == null) { return false; }
			TbQoProceso proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), tipoProceso);
			if(proceso == null) { return null; }
			exc.setEstadoExcepcion( EstadoExcepcionEnum.NEGADO );
			exc.setIdAprobador(aprobador);
			exc.setObservacionAsesor( obsAprobador );
			exc = this.manageExcepcion(exc);
			if(exc == null) { return false; }
			proceso.setEstadoProceso( EstadoProcesoEnum.EXCEPCIONADO );
			proceso.setUsuario(aprobador);
			proceso = this.manageProceso(proceso);
			if(proceso == null) { return false; } else { return true; }
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}

	public Boolean aprobarCobertura(Long idExc, String obsAprobador, String aprobador, String cobertura, ProcesoEnum procesoTipo) throws RelativeException {
		try {
			TbQoExcepcion exc = this.finExcepcionById(idExc);
			if(exc == null) { return false; }
			TbQoProceso proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), procesoTipo);
			if(proceso == null) { return false; }
			TbQoCreditoNegociacion credito = this.findCreditoByIdNegociacion( exc.getTbQoNegociacion().getId() );
			if(credito == null) { return false; }
			credito.setCobertura( cobertura );
			credito = this.manageCreditoNegociacion( credito );
			if( credito == null) { return false; }
			exc.setEstadoExcepcion( EstadoExcepcionEnum.APROBADO );
			exc.setIdAprobador(aprobador);
			exc.setObservacionAprobador( obsAprobador );
			exc = this.manageExcepcion(exc);
			if(exc == null) { return false; }
			proceso.setEstadoProceso( EstadoProcesoEnum.EXCEPCIONADO );
			proceso.setUsuario(aprobador);
			proceso = this.manageProceso(proceso);
			
			if(proceso == null) { return false; } else { return true; }
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMensaje() );
		}
	}

	public TbQoExcepcion excepcionCliente(Long id, String obsAprobador, String aprobador, String aprobado) throws RelativeException {
		try {
			TbQoExcepcion exc = this.finExcepcionById(id);
			TbQoProceso proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), ProcesoEnum.NUEVO);
			if(proceso == null) {
				proceso = this.findProcesoByIdReferencia(exc.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION);
			}
			if(proceso == null || !proceso.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_EXCEPCION)) { 
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR EL PROCESO INTENTE MAS TARDE");
			}
			exc.setEstadoExcepcion( aprobado.equalsIgnoreCase("SI")?EstadoExcepcionEnum.APROBADO:EstadoExcepcionEnum.NEGADO );
			exc.setIdAprobador(aprobador);
			exc.setObservacionAsesor( obsAprobador );
			proceso.setEstadoProceso( EstadoProcesoEnum.EXCEPCIONADO );
			proceso.setUsuario(aprobador);
			this.manageProceso(proceso);
			return manageExcepcion(exc);
		} catch (RelativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public ConsultaGlobalRespuestaWrapper buscarCreditos(ConsultaGlobalWrapper wrapper) throws RelativeException {
		try {
			return SoftBankApiClient.callConsultaGlobalRest(this.parametroRepository.findByNombre(QuskiOroConstantes.SOFTBANK_CONSULTA_GLOBAL).getValor(), wrapper);
		}catch( RelativeException | UnsupportedEncodingException e ) {
			throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION );
		}
	}
	public RenovacionWrapper buscarRenovacionOperacionMadre( String numeroOperacion ) throws RelativeException{
		try {
			DetalleCreditoWrapper detalle = this.traerCreditoVigente( numeroOperacion );
			if( detalle == null ) { throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);}
			
			return new RenovacionWrapper( detalle ); 
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje() );
		}
	}
	public RenovacionWrapper buscarRenovacionNegociacion( Long idNegociacion ) throws RelativeException{
		try {
			TbQoCreditoNegociacion credito = this.creditoNegociacionRepository.findCreditoByIdNegociacion(idNegociacion);
			if(credito == null) { throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION); }
			DetalleCreditoWrapper detalle = this.traerCreditoVigente( credito.getNumeroOperacionMadre() );
			if( detalle == null ) { throw new RelativeException( Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION);}
			RenovacionWrapper novacion = new RenovacionWrapper( detalle ); 
			novacion.setCredito( credito );
			novacion.setProceso( this.procesoRepository.findByIdReferencia(credito.getTbQoNegociacion().getId(), ProcesoEnum.RENOVACION ));
			novacion.setExcepciones( this.excepcionesRepository.findByIdNegociacion( credito.getTbQoNegociacion().getId() ));
			novacion.setTasacion( this.tasacionRepository.findByIdCredito( credito.getId() ));
			novacion.setVariables( this.variablesCrediticiaRepository.findByIdNegociacion(idNegociacion));
			novacion.setRiesgos( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( credito.getTbQoNegociacion().getTbQoCliente().getCedulaCliente() ), credito.getTbQoNegociacion()) );
			novacion.setCuentas( this.cuentaBancariaRepository.findByIdCliente( credito.getTbQoNegociacion().getTbQoCliente().getId() ));
			return novacion;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw new RelativeException( Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_AL_REALIZAR_CREACION + e.getMensaje() );
		}
	}

	public RenovacionWrapper crearCreditoRenovacion(Opcion opcion, List<Garantia> garantias, String numeroOperacionMadre, Long idNego, String asesor, Long idAgencia) throws RelativeException {
		try {
			RenovacionWrapper novacion;
			if(idNego == null) {
				novacion = this.buscarRenovacionOperacionMadre(numeroOperacionMadre);		
				log.info( "============> CREANDO CREDITO <============");
				TbQoCliente cliente = this.clienteRepository.findClienteByIdentificacion( novacion.getOperacionAnterior().getCliente().getIdentificacion());
				if( cliente == null) {
					log.info( "============> ESTOY CREANDO CLIENTE <============");
					cliente = this.clienteSoftToTbQoCliente( novacion.getOperacionAnterior().getCliente() );
					cliente = this.manageCliente(cliente);
				}
				TbQoNegociacion negociacion = new TbQoNegociacion();
				negociacion.setAsesor(asesor);
				negociacion.setEstado( EstadoEnum.ACT);
				negociacion.setTbQoCliente( cliente );
				negociacion = this.manageNegociacion(negociacion);
				TbQoCreditoNegociacion credito = this.createCreditoNovacion(opcion, numeroOperacionMadre, idAgencia, null);
				credito.setTbQoNegociacion( negociacion );
				credito = this.manageCreditoNegociacion(credito);
				credito = this.crearCodigoRenovacion(credito);
				novacion.setCredito( credito );
				novacion.setProceso( this.createProcesoNovacion(negociacion.getId(), asesor) );
				novacion.setTasacion( this.createTasacionByGarantia(garantias, novacion.getOperacionAnterior().getGarantias(), credito) );
				Informacion info = this.informacionCliente(cliente.getCedulaCliente());
				if(info != null && info.getXmlVariablesInternas() != null && info.getXmlVariablesInternas().getVariablesInternas() != null && info.getXmlVariablesInternas().getVariablesInternas().getVariable() != null) {
					novacion.setVariables( this.createVariablesFromEquifax(info.getXmlVariablesInternas().getVariablesInternas().getVariable(), negociacion));	
				}
				novacion.setRiesgos( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( cliente.getCedulaCliente() ), negociacion)  );
			}else {
				novacion = this.buscarRenovacionNegociacion(idNego);				
				log.info( "============> ACTUALIZANDO CREDITO <============");
				novacion.getCredito().getTbQoNegociacion().setAsesor(asesor);
				novacion.getCredito().setTbQoNegociacion( this.manageNegociacion( novacion.getCredito().getTbQoNegociacion()));
				novacion.setCredito( this.manageCreditoNegociacion( this.createCreditoNovacion(opcion, numeroOperacionMadre, idAgencia, novacion.getCredito().getId())));
				novacion.setTasacion(this.createTasacionByGarantia(garantias, novacion.getOperacionAnterior().getGarantias(), novacion.getCredito()));
				Informacion info = this.informacionCliente(novacion.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente());
				if(info != null && info.getXmlVariablesInternas() != null && info.getXmlVariablesInternas().getVariablesInternas() != null && info.getXmlVariablesInternas().getVariablesInternas().getVariable() != null) {
					novacion.setVariables( this.createVariablesFromEquifax(info.getXmlVariablesInternas().getVariablesInternas().getVariable(), novacion.getCredito().getTbQoNegociacion()));	
				}
				novacion.setRiesgos( this.createRiesgoFrontSoftBank(consultarRiesgoSoftbank( novacion.getCredito().getTbQoNegociacion().getTbQoCliente().getCedulaCliente() ), novacion.getCredito().getTbQoNegociacion())  );
			}
			return novacion;
		}catch(RelativeException e) {
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, QuskiOroConstantes.ERROR_CREATE_NOVACION );
		}
	}
	
	private List<TbQoTasacion> createTasacionByGarantia(List<Garantia> garantias, List<GarantiaOperacionWrapper> garantiasSoft, TbQoCreditoNegociacion credito) throws RelativeException {
		List<TbQoTasacion> listTasacion = new ArrayList<>();
		try {
			this.tasacionRepository.deleteTasacionByNegociacionId(credito.getId());
			log.info( "============> DELETE JOYA <============");

		} catch (RelativeException e2) {
			e2.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION );
		}
		garantias.forEach(e->{
			garantiasSoft.forEach(s ->{
				log.info( "============> DESCRIPCION EN SOFTBANK  ===> '"+s.getDescripcionJoya()+"' DESCRIPCION EN SIMULADOR  ===> '"+ e.getDescripcion()+"' <========");
				log.info( "============> TIPO JOYA EN SOFTBANK  ===> '"+s.getCodigoTipoJoya()+"' TIPO JOYA EN SIMULADOR   ===> '"+ e.getTipoJoya()+"' <========");
				log.info( "============> TIPO ORO EN SOFTBANK  ===> '"+s.getCodigoTipoOro()+"' TIPO ORO EN SIMULADOR  ===> '"+ e.getTipoOroKilataje()+"' <========");
				log.info( "============> GRAMOS COMPARADOS ===> " +s.getPesoBruto().compareTo(BigDecimal.valueOf(e.getPesoGr())));
				if( e.getDescripcion().equalsIgnoreCase( s.getDescripcionJoya() ) && 
						e.getTipoJoya().equalsIgnoreCase( s.getCodigoTipoJoya() ) &&
						e.getTipoOroKilataje().equalsIgnoreCase(s.getCodigoTipoOro() ) &&
						s.getPesoBruto().compareTo(BigDecimal.valueOf(e.getPesoGr()) ) == 0
						) {
					log.info( "============> CREANDO JOYA <============");
					TbQoTasacion tasacion = new TbQoTasacion();
					tasacion.setNumeroGarantia( s.getNumeroGarantia() );
					tasacion.setNumeroExpediente( s.getNumeroExpediente() );
					tasacion.setTipoGarantia( s.getCodigoTipoGarantia() );
					tasacion.setSubTipoGarantia( s.getCodigoSubTipoGarantia() );
					tasacion.setEstado(EstadoEnum.ACT );
					tasacion.setDescripcion( s.getDescripcionJoya() );
					log.info( "============> DESCRIPCION JOYA <============> "+ tasacion.getDescripcion());
					tasacion.setDescuentoPesoPiedra( BigDecimal.valueOf( e.getDescuentoPesoPiedras() ) );
					tasacion.setDescuentoSuelda( BigDecimal.valueOf(e.getDescuentoSuelda()) );
					tasacion.setEstadoJoya( e.getEstadoJoya() );
					tasacion.setNumeroPiezas( Long.valueOf( e.getNumeroPiezas() ) );
					tasacion.setPesoBruto( BigDecimal.valueOf( e.getPesoGr()) );
					tasacion.setPesoNeto(BigDecimal.valueOf( e.getPesoNeto() ) );
					tasacion.setTipoJoya( s.getCodigoTipoJoya() );
					tasacion.setValorAvaluo( BigDecimal.valueOf( e.getValorAvaluo() ) );
					tasacion.setValorComercial( BigDecimal.valueOf( e.getValorAplicable() ) );
					tasacion.setValorOro( BigDecimal.valueOf( e.getValorOro() ) );
					tasacion.setValorRealizacion(BigDecimal.valueOf( e.getValorRealizacion() ) );
					tasacion.setTbQoCreditoNegociacion( credito );
					tasacion.setTipoOro( s.getCodigoTipoOro() );
					tasacion.setTienePiedras( s.getTienePiedras() );
					tasacion.setDetallePiedras( s.getDetallePiedras() );
					tasacion.setTbQoCreditoNegociacion(credito);
					try {
						listTasacion.add( this.manageTasacion(tasacion) );
					} catch (RelativeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		});
		return listTasacion;
		
	}
	private TbQoCreditoNegociacion createCreditoNovacion(Opcion opcion, String numeroOperacionMadre, Long idAgencia, Long id) throws RelativeException {
		TbQoCreditoNegociacion credito;						
		if(id != null) {
			credito = this.findCreditoNegociacionById(id);
			if(credito == null) {throw new RelativeException(Constantes.ERROR_CODE_READ, QuskiOroConstantes.ERROR_AL_REALIZAR_BUSQUEDA );}
			
		}else {
			credito = new TbQoCreditoNegociacion();
		}
		if(opcion != null) {
			credito.setaPagarCliente( BigDecimal.valueOf( opcion.getValorAPagar() ));
			credito.setMontoFinanciado( BigDecimal.valueOf(opcion.getMontoFinanciado()) );
			credito.setPlazoCredito( Long.valueOf(opcion.getPlazo()) );
			credito.setValorCuota( BigDecimal.valueOf(opcion.getCuota() ));
			credito.setCostoCustodia( BigDecimal.valueOf(opcion.getCostoCustodia()));
			credito.setCostoFideicomiso(BigDecimal.valueOf( opcion.getCostoFideicomiso()));
			credito.setCostoSeguro(BigDecimal.valueOf( opcion.getCostoSeguro()));
			credito.setCostoTasacion( BigDecimal.valueOf( opcion.getCostoTasacion() ) );
			credito.setCostoTransporte( BigDecimal.valueOf( opcion.getCostoTransporte()) );
			credito.setCostoValoracion( BigDecimal.valueOf( opcion.getCostoValoracion()) );
			credito.setCuota( BigDecimal.valueOf(opcion.getCuota() ));
			credito.setCustodiaDevengada( BigDecimal.valueOf(opcion.getCustodiaDevengada() ));
			credito.setDividendoFlujoPlaneado( BigDecimal.valueOf(opcion.getDIVIDENDOFLUJOPLANEADO() ));
			credito.setDividendoProrrateo( BigDecimal.valueOf(opcion.getDIVIDENDOSPRORRATEOSERVICIOSDIFERIDO() ));
			credito.setFormaPagoCapital( opcion.getFormaPagoCapital());
			credito.setFormaPagoCustodia( opcion.getFormaPagoCustodia() );
			credito.setFormaPagoCustodiaDevengada( opcion.getFormaPagoCustodiaDevengada() );
			credito.setFormaPagoFideicomiso( opcion.getFormaPagoFideicomiso() );
			credito.setFormaPagoGastoCobranza( opcion.getFormaPagoGastoCobranza() );
			credito.setFormaPagoImpuestoSolca( opcion.getFormaPagoImpuestoSolca() );
			credito.setFormaPagoInteres( opcion.getFormaPagoInteres() );
			credito.setFormaPagoMora( opcion.getFormaPagoMora() );
			credito.setFormaPagoSeguro( opcion.getFormaPagoSeguro() );
			credito.setFormaPagoTasador( opcion.getFormaPagoTasador() );
			credito.setFormaPagoTransporte( opcion.getFormaPagoTransporte() );
			credito.setFormaPagoValoracion( opcion.getFormaPagoValoracion() );
			credito.setGastoCobranza(  BigDecimal.valueOf(opcion.getGastoCobranza()) );
			credito.setImpuestoSolca(  BigDecimal.valueOf(opcion.getImpuestoSolca()) );
			credito.setaRecibirCliente( BigDecimal.valueOf( opcion.getValorARecibir()) );
			credito.setMontoPrevioDesembolso(  BigDecimal.valueOf(opcion.getMontoPrevioDesembolso() ));
			credito.setPeriodicidadPlazo( opcion.getPeriodicidadPlazo() );
			credito.setPeriodoPlazo( opcion.getPeriodoPlazo() );
			credito.setPorcentajeFlujoPlaneado(  BigDecimal.valueOf(opcion.getPORCENTAJEFLUJOPLANEADO()) );
			credito.setSaldoCapitalRenov(  BigDecimal.valueOf(opcion.getSaldoCapitalRenov()) );
			credito.setSaldoInteres(  BigDecimal.valueOf(opcion.getSaldoInteres()) );
			credito.setSaldoMora(  BigDecimal.valueOf(opcion.getSaldoMora() ));
			credito.setTipoOferta( opcion.getTIPOOFERTA() );
			credito.setTotalCostosOperacionAnterior( BigDecimal.valueOf( opcion.getTotalCostosOperacionAnterior()) );
			credito.setTotalGastosNuevaOperacion( BigDecimal.valueOf( opcion.getTotalGastosNuevaOperacion()));
			credito.setValorAPagar( BigDecimal.valueOf( opcion.getValorAPagar() ));
			credito.setValorARecibir(BigDecimal.valueOf( opcion.getValorARecibir()));
			List<CatalogoTablaAmortizacionWrapper>  listTablas =  SoftBankApiClient.callCatalogoTablaAmortizacionRest( this.parametroRepository.findByNombre(QuskiOroConstantes.CATALOGO_TABLA_AMOTIZACION).getValor());
			if(listTablas == null || listTablas.isEmpty()) { throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE LEER EL CATALOGO DE TABLA DE AMORTIZACION SOFTBANK"); }
			listTablas.forEach(e->{
				if( e.getPeriodoPlazo().equalsIgnoreCase( credito.getPeriodoPlazo()) && 
						e.getPeriodicidadPlazo().equalsIgnoreCase( credito.getPeriodicidadPlazo()) &&
						e.getTipoOferta().equalsIgnoreCase( credito.getTipoOferta() ) &&
						e.getPlazo() == credito.getPlazoCredito() 
						){
					credito.setTablaAmortizacion( e.getCodigo() );
					credito.setNumeroCuotas(e.getNumeroCuotas());					
				}
			});
			if(StringUtils.isBlank(credito.getTablaAmortizacion())) {
				throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE PUEDE ENCONTRAR UN CODIGO DE TABLA DE AMORTIZACION PARA LA OPCION DE CREDITO SELECCCIONADA");
			}
		}
		credito.setIdAgencia( idAgencia );
		//credito.setcobertura();
		//credito.setpagoDia( );
		//credito.setmontoSolicitado();
		//credito.setriesgoTotalCliente( opcion.get);
		credito.setEstado( EstadoEnum.ACT );
		credito.setNumeroOperacionMadre(numeroOperacionMadre);
		return credito;
	}
	private TbQoCreditoNegociacion crearCodigoRenovacion(TbQoCreditoNegociacion persisted) throws RelativeException {
		try {
			persisted.setCodigo( QuskiOroConstantes.CODIGO_RENOVACION.concat(StringUtils.leftPad(persisted.getId().toString(), 7, "0")));
			return this.creditoNegociacionRepository.update(persisted);
		} catch (RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE,
					QuskiOroConstantes.ERROR_AL_REALIZAR_ACTUALIZACION + e.getMessage());
		}
	}
	public String traerNumeroOperacionMadre(String codigoBpm)  throws RelativeException {
		try {
			TbQoCreditoNegociacion nego = this.creditoNegociacionRepository.findCreditoByCodigoBpm( codigoBpm );
			if(nego != null && nego.getNumeroOperacionMadre() != null) {
				return nego.getNumeroOperacionMadre();
			}else { return null; }
		}catch(RelativeException e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					QuskiOroConstantes.ERROR_AL_INTENTAR_LEER_LA_INFORMACION + e.getMessage());
		}
	}	
}
