package com.relative.quski.service;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.quski.enums.EstadoEnum;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.EstadoProcesoEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.*;
import com.relative.quski.repository.ExcepcionOperativaRepository;
import com.relative.quski.repository.ParametroRepository;
import com.relative.quski.repository.RegularizacionDocumentosRepository;
import com.relative.quski.util.QuskiOroConstantes;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.ExcepcionOperativaClienteWrapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Stateless
public class ExcepcionOperativaService {
    private static final BigDecimal COBRANZAS_NIVEL1 = new BigDecimal(10);
    private static final BigDecimal COBRANZAS_NIVEL2 = new BigDecimal(30);

    @Inject
    Logger log;
    @Inject
    private ExcepcionOperativaRepository excepcionOperativaRepository;
    @Inject
    private QuskiOroService qos;
    @Inject
    private RegularizacionDocumentosRepository regularizacionDocumentosRepository;
	@Inject
	private ParametroRepository parametroRepository;
	
    public PaginatedListWrapper<TbQoExcepcionOperativa> listAllByParams(Integer firstItem, Integer pageSize, String sortFields, String sortDirections, String isPaginated, String usuario, String estado, String codigo, String codigoOperacion, String idNegociacion, String rol) throws RelativeException {
        PaginatedListWrapper<TbQoExcepcionOperativa> plw = new PaginatedListWrapper<>(new PaginatedWrapper(firstItem, pageSize, sortFields, sortDirections, isPaginated));
        plw.setIsPaginated(isPaginated);
        List<TbMiParametro> parametroNivel = this.parametroRepository.findByNombreAndTipoOrdered(rol, QuskiOroConstantes.TIPO_ROL, null);
        if (parametroNivel == null || parametroNivel.isEmpty()) {
            throw new RelativeException(
                    Constantes.ERROR_CODE_CUSTOM,
                    "No se puede encontrar un nivel de aprobación para el rol: " + rol
            );
        }
        List<Long> nivelesAprobacion = parametroNivel.stream()
                .map(parametro -> {
                    try {
                        return Long.parseLong(parametro.getValor());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "El valor del parámetro no es convertible a Long: " + parametro.getValor(), e);
                    }
                })
                .collect(Collectors.toList());
        List<TbQoExcepcionOperativa> actions = this.excepcionOperativaRepository.listAllByParams(plw, usuario, estado,codigo,codigoOperacion,idNegociacion, nivelesAprobacion);
        if (actions != null && !actions.isEmpty()) {
            plw.setTotalResults(this.excepcionOperativaRepository.countListAllByParams(usuario, estado,codigo,codigoOperacion,idNegociacion, nivelesAprobacion));
            plw.setList(actions);
        }
        return plw;
    }
    public List<ExcepcionOperativaClienteWrapper> listAllByParamsClient(String rol, String cedula) throws RelativeException {
        try {
            List<TbMiParametro> parametroNivel = this.parametroRepository.findByNombreAndTipoOrdered(rol, QuskiOroConstantes.TIPO_ROL, null);
            if (parametroNivel == null || parametroNivel.isEmpty()) {
                throw new RelativeException(
                        Constantes.ERROR_CODE_CUSTOM,
                        "No se puede encontrar un nivel de aprobación para el rol: " + rol
                );
            }
            List<Long> nivelesAprobacion = parametroNivel.stream()
                    .map(parametro -> {
                        try {
                            return Long.parseLong(parametro.getValor());
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(
                                    "El valor del parámetro no es convertible a Long: " + parametro.getValor(), e);
                        }
                    })
                    .collect(Collectors.toList());
            return this.excepcionOperativaRepository.listAllByParamsClient(cedula, nivelesAprobacion);
        } catch (RelativeException e) {
            throw e;
        } catch (Exception e) {
            throw new RelativeException(
                    Constantes.ERROR_CODE_CUSTOM,
                    "Error al listar excepciones operativas por parámetros: " + e.getMessage()
            );
        }
    }


    public TbQoExcepcionOperativa solicitarExcepcionServicios(TbQoExcepcionOperativa ex, ProcesoEnum pro, String asesor) throws RelativeException {
        TbQoProceso proceso =  this.qos.findProcesoByIdReferencia(ex.getIdNegociacion().getId(),pro);
        if(proceso == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO PROCESO ACTIVO");
        }
        proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_EXCEPCION_OPERATIVA);
        this.qos.manageProceso(proceso);
        ex.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
        if(ex.getMontoInvolucrado().compareTo(COBRANZAS_NIVEL1)<=0){
            ex.setNivelAprobacion(2);
        } else if(ex.getMontoInvolucrado().compareTo(COBRANZAS_NIVEL2)<=0){
            ex.setNivelAprobacion(3);
        } else if(ex.getMontoInvolucrado().compareTo(COBRANZAS_NIVEL2)>0){
            ex.setNivelAprobacion(4);
        }
        
        DetalleCreditoEnProcesoWrapper wp = this.qos.traerCreditoNegociacion(ex.getIdNegociacion().getId());
        TbQoTracking traking = new TbQoTracking();
		traking.setActividad("EXCEPCION OPERATIVA");
		traking.setCodigoBpm(wp.getCredito().getCodigo());
		traking.setCodigoOperacionSoftbank(wp.getCredito().getNumeroOperacion());
		traking.setEstado(EstadoEnum.ACT);
		traking.setFechaActualizacion(new Date());
		traking.setFechaCreacion(new Date());
		traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		traking.setNombreAsesor(wp.getCredito().getTbQoNegociacion().getNombreAsesor());
		traking.setUsuarioCreacion(asesor);
		traking.setObservacion(ex.getObservacionAsesor());
		traking.setProceso(ProcesoEnum.RENOVACION);
		traking.setSeccion("Enviado a excepcion operativa");
		this.qos.registrarTraking(traking);
		this.qos.notificarExcepcionServicio( wp.getCredito(), ex, Boolean.FALSE );
        validarExcepcionServicios(ex.getIdNegociacion().getId());
        return this.excepcionOperativaRepository.add(ex);
    }
    private void validarExcepcionServicios(Long idNegociacion) throws RelativeException{
        List<TbQoExcepcionOperativa> excepciones = this.excepcionOperativaRepository.findByNegociacion(idNegociacion);
        if(excepciones == null){
            return;
        }
        List<TbQoExcepcionOperativa> excepcionesFiltradas = excepciones.stream()
                .filter(excepcion -> excepcion.getNivelAprobacion() > 1 && excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO.toString()))
                .collect(Collectors.toList());
        if (!excepcionesFiltradas.isEmpty()) {
            for (TbQoExcepcionOperativa excepcion : excepcionesFiltradas) {
                try {
                    excepcion.setEstadoExcepcion(EstadoExcepcionEnum.NEGADO.toString());
                    excepcion.setObservacionAprobador("SE NEGO AUTOMATICAMENTE POR NUEVA EXCEPCION");
                    this.excepcionOperativaRepository.update(excepcion);
                } catch (RelativeException e) {
                    throw new RelativeException("Error al eliminar la excepción operativa: " + excepcion.getId());
                }
            }
        }

    }
    public TbQoExcepcionOperativa findByNegociacionAndTipo(Long idNegociacion, String tipoExcepcion, EstadoExcepcionEnum estado ) throws RelativeException {
        return excepcionOperativaRepository.findByNegociacionAndTipo(idNegociacion, tipoExcepcion, estado);
    }
    public List<TbQoExcepcionOperativa> findByNegociacion(Long idNegociacion ) throws RelativeException {
        return excepcionOperativaRepository.findByNegociacion(idNegociacion);
    }
    public TbQoExcepcionOperativa cancelarExcepcion(TbQoExcepcionOperativa ex, ProcesoEnum proceso, String nombreAsesor) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(ex.getId());
        TbQoCreditoNegociacion credito = this.qos.findCreditoByIdNegociacion(excepcion.getIdNegociacion().getId());
		if(credito == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA NEGOCIACION ID:"+excepcion.getIdNegociacion().getId());
		}
        if(!excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO.toString())){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ESTA EXCEPCION YA FUE PROCESADA");
        }
        excepcion.setEstadoExcepcion("NEGADO");
        excepcion.setFechaRespuesta(new Timestamp(System.currentTimeMillis()));
        excepcion.setObservacionAprobador(ex.getObservacionAprobador());
        excepcion.setUsuarioAprobador(ex.getUsuarioAprobador());
        
        return  this.excepcionOperativaRepository.update(excepcion);
    }
    public TbQoExcepcionOperativa resolverExcepcion(TbQoExcepcionOperativa ex, ProcesoEnum proceso, String nombreAsesor) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(ex.getId());
        TbQoCreditoNegociacion credito = this.qos.findCreditoByIdNegociacion(excepcion.getIdNegociacion().getId());
		if(credito == null) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCUENTRA NEGOCIACION ID:"+excepcion.getIdNegociacion().getId());
		}
        if(!excepcion.getEstadoExcepcion().equals(EstadoExcepcionEnum.PENDIENTE.toString())){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ESTA EXCEPCION YA FUE PROCESADA");
        }
        excepcion.setEstadoExcepcion(ex.getEstadoExcepcion());
        excepcion.setFechaRespuesta(new Timestamp(System.currentTimeMillis()));
        excepcion.setObservacionAprobador(ex.getObservacionAprobador());
        excepcion.setUsuarioAprobador(ex.getUsuarioAprobador());
        TbQoProceso proce =  this.qos.findProcesoByIdReferencia(excepcion.getIdNegociacion().getId(),proceso);
        if(!proce.getEstadoProceso().equals(EstadoProcesoEnum.PENDIENTE_EXCEPCION_OPERATIVA)){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"ESTA EXCEPCION YA FUE PROCESADA");
        }
        if(ex.getEstadoExcepcion().equals(EstadoExcepcionEnum.APROBADO.toString())){

            if(excepcion.getNivelAprobacion() == 1){
                proce.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_APROBACION);
                this.regularizacionDocumentosRepository.add(getTbQoRegularizacionDocumento(excepcion));
            }else{
                proce.setEstadoProceso(EstadoProcesoEnum.EXCEPCIONADO_OPERATIVA);
            }
        }else{

            if(excepcion.getNivelAprobacion() == 1){
                proce.setEstadoProceso(EstadoProcesoEnum.EXCEPCIONADO_OPERATIVA);
            }else{
                proce.setEstadoProceso(EstadoProcesoEnum.EXCEPCIONADO_OPERATIVA);
            }
        }
        TbQoTracking traking = new TbQoTracking();
		traking.setActividad("EXCEPCION OPERATIVA");
		traking.setCodigoBpm(credito.getCodigo());
		traking.setCodigoOperacionSoftbank(credito.getNumeroOperacion());
		traking.setEstado(EstadoEnum.ACT);
		traking.setFechaActualizacion(new Date());
		traking.setFechaCreacion(new Date());
		traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		traking.setNombreAsesor(nombreAsesor);
		traking.setUsuarioCreacion(excepcion.getIdNegociacion().getAsesor());
		traking.setObservacion(ex.getObservacionAprobador());
		traking.setProceso(proce.getProceso());
		if(excepcion.getNivelAprobacion() == 1){
			traking.setSeccion("Excepcion tipo regularizacion revisada");
		}else{
			traking.setSeccion("Excepcion operativa revisada");
		}
		this.qos.registrarTraking(traking);
        this.qos.manageProceso(proce);
        return  this.excepcionOperativaRepository.update(excepcion);
    }

    private TbQoRegularizacionDocumento getTbQoRegularizacionDocumento(TbQoExcepcionOperativa excepcion) {
        TbQoRegularizacionDocumento documento = new TbQoRegularizacionDocumento();
        documento.setTipoExcepcion(excepcion.getTipoExcepcion());
        documento.setCodigoOperacion(excepcion.getCodigoOperacion());
        documento.setIdNegociacion(excepcion.getIdNegociacion());
        documento.setFechaSolicitud(excepcion.getFechaSolicitud());
        documento.setUsuarioSolicitante(excepcion.getUsuarioSolicitante());
        documento.setIdentificacionCliente(excepcion.getIdNegociacion().getTbQoCliente().getCedulaCliente());
        documento.setEstadoRegularizacion(EstadoExcepcionEnum.PENDIENTE.toString());
        return documento;
    }

    public DetalleCreditoEnProcesoWrapper traerCreditoNegociacionByExcepcionOperativa(Long idExcepcionOperativa) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(idExcepcionOperativa);
        if(excepcion == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO EXCEPCION OPERATIVA CON ID:"+ idExcepcionOperativa);
        }
        DetalleCreditoEnProcesoWrapper detalle = this.qos.traerCreditoNegociacion(excepcion.getIdNegociacion().getId());
        detalle.setExcepcionOperativa(excepcion);
        return detalle;
    }

    public TbQoProceso solicitarExcepcionFabrica(TbQoExcepcionOperativa ex, ProcesoEnum pro) throws RelativeException {
        TbQoProceso proceso =  this.qos.findProcesoByIdReferencia(ex.getIdNegociacion().getId(),pro);
        proceso.setEstadoProceso(EstadoProcesoEnum.PENDIENTE_EXCEPCION_OPERATIVA);
        proceso.setUsuario( QuskiOroConstantes.EN_COLA);
        
        ex.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));
        ex.setNivelAprobacion(1);

        DetalleCreditoEnProcesoWrapper wp = this.qos.traerCreditoNegociacion(ex.getIdNegociacion().getId());
        TbQoTracking traking = new TbQoTracking();
		traking.setActividad("EXCEPCION OPERATIVA");
		traking.setCodigoBpm(wp.getCredito().getCodigo());
		traking.setCodigoOperacionSoftbank(wp.getCredito().getNumeroOperacion());
		traking.setEstado(EstadoEnum.ACT);
		traking.setFechaActualizacion(new Date());
		traking.setFechaCreacion(new Date());
		traking.setFechaInicio(new Timestamp(System.currentTimeMillis()));
		traking.setNombreAsesor(wp.getCredito().getTbQoNegociacion().getNombreAsesor());
		traking.setUsuarioCreacion(ex.getUsuarioSolicitante());
		traking.setObservacion(ex.getObservacionAsesor());
		traking.setProceso(pro);
		traking.setSeccion("Enviado a excepcion operativa tipo regularizacion");
		this.qos.registrarTraking(traking);
		this.excepcionOperativaRepository.add(ex);
		this.qos.notificarExcepcionServicio( wp.getCredito(), ex, pro.compareTo(ProcesoEnum.NUEVO) == 0 ? Boolean.FALSE : Boolean.TRUE );
        return this.qos.manageProceso(proceso);
    }

    public AprobacionWrapper traerCreditoNegociacionExistenteByExcepcionOperativa(Long idExcepcionOperativa, String autorizacion) throws RelativeException {
        TbQoExcepcionOperativa excepcion = this.excepcionOperativaRepository.findById(idExcepcionOperativa);
        if(excepcion == null){
            throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"NO SE ENCONTRO EXCEPCION OPERATIVA CON ID:"+ idExcepcionOperativa);
        }
        AprobacionWrapper detalle = this.qos.traerCreditoNegociacionExistente(excepcion.getIdNegociacion().getId(), autorizacion);
        detalle.setExcepcion(excepcion);
        return detalle;
    }
}
