package com.relative.quski.rest;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.enums.EstadoExcepcionEnum;
import com.relative.quski.enums.ProcesoEnum;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.service.ExcepcionOperativaService;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.wrapper.AprobacionWrapper;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.ExcepcionServiciosFlujoVariableWrapper;
import com.relative.quski.wrapper.RenovacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/excepcionOperativaRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "ExcepcionOperativaController - REST CRUD")
public class ExcepcionOperativaRestController extends BaseRestController
        implements CrudRestControllerInterface<TbQoExcepcionOperativa, GenericWrapper<TbQoExcepcionOperativa>> {

    public ExcepcionOperativaRestController() throws RelativeException {
        super();
    }

    @Inject
    QuskiOroService qos;
    @Inject
    ExcepcionOperativaService excepcionOperativaService;

    @Override
    @GET
    @Path("/getEntity")
    @ApiOperation(value = "id", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoExcepcionOperativa", response = GenericWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public GenericWrapper<TbQoExcepcionOperativa> getEntity(@QueryParam("id") String id) throws RelativeException {
        GenericWrapper<TbQoExcepcionOperativa> loc = new GenericWrapper<>();
        TbQoExcepcionOperativa a = this.qos.findExcepcionOperativaById(Long.valueOf(id));
        loc.setEntidad(a);
        return loc;
    }

    @Override
    @GET
    @Path("/listAllEntities")
    @ApiOperation(value = "PaginatedListWrapper<TbQoExcepcionOperativa>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = PaginatedListWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public PaginatedListWrapper<TbQoExcepcionOperativa> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") String pageSize,
                                                                        @QueryParam("sortFields") @DefaultValue("id") String sortFields,
                                                                        @QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
                                                                        @QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
        Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
        return findAll(
                new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

    }

    private PaginatedListWrapper<TbQoExcepcionOperativa> findAll(PaginatedWrapper pw) throws RelativeException {
        PaginatedListWrapper<TbQoExcepcionOperativa> plw = new PaginatedListWrapper<>(pw);
        List<TbQoExcepcionOperativa> actions = this.qos.findAllExcepcionOperativa(pw);
        if (actions != null && !actions.isEmpty()) {
            plw.setTotalResults(this.qos.countExcepcionOperativa().intValue());
            plw.setList(actions);
        }
        return plw;
    }

    @Override
    @POST
    @Path("/persistEntity")
    @ApiOperation(value = "GenericWrapper<TbQoExcepcionOperativa>", notes = "Metodo Post persistEntity Registra  y retorna la entidad TbQoExcepcionOperativa", response = GenericWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public GenericWrapper<TbQoExcepcionOperativa> persistEntity(GenericWrapper<TbQoExcepcionOperativa> wp)
            throws RelativeException {
        GenericWrapper<TbQoExcepcionOperativa> loc = new GenericWrapper<>();
        loc.setEntidad(this.qos.manageExcepcionOperativa(wp.getEntidad()));
        return loc;
    }

    @Override
    public void deleteEntity(String arg0) throws RelativeException {
        // TODO Auto-generated method stub

    }

    @GET
    @Path("/listAllByParams")
    @ApiOperation(value = "PaginatedListWrapper<TbQoExcepcionOperativa>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = PaginatedListWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public PaginatedListWrapper<TbQoExcepcionOperativa> listAllByParams(@QueryParam("page") @DefaultValue("1") String page,
                                                                        @QueryParam("pageSize") @DefaultValue("10") String pageSize,
                                                                        @QueryParam("sortFields") @DefaultValue("id") String sortFields,
                                                                        @QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
                                                                        @QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
                                                                        @QueryParam("usuario") String usuario,
                                                                        @QueryParam("estado") String estado,
                                                                        @QueryParam("codigo") String codigo,
                                                                        @QueryParam("codigoOperacion") String codigoOperacion,
                                                                        @QueryParam("idNegociacion") String idNegociacion) throws RelativeException {
        Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
        return this.excepcionOperativaService.listAllByParams(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated, usuario, estado,codigo,codigoOperacion,idNegociacion);


    }

    @POST
    @Path("/solicitarExcepcionServicios")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoExcepcionOperativa solicitarExcepcionServicios(@QueryParam("proceso") ProcesoEnum proceso, TbQoExcepcionOperativa ex) throws RelativeException {
        return  this.excepcionOperativaService.solicitarExcepcionServicios(ex,proceso,ex.getUsuarioSolicitante());

    }
    @POST
    @Path("/excepcionServiciosFlujoVariable")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoExcepcionOperativa excepcionServiciosFlujoVariable(@QueryParam("proceso") ProcesoEnum proceso, ExcepcionServiciosFlujoVariableWrapper wp) throws RelativeException {
    	
    	RenovacionWrapper rw = this.qos.crearCreditoRenovacion( 
    			wp.getOpcionAndGarantiasWrapper(), 
    			wp.getNumeroOperacion(), 
    			wp.getIdNegociacion() != null ? Long.valueOf( wp.getIdNegociacion() ): null,
				wp.getAsesor(), 
				Long.valueOf(wp.getIdAgencia()), 
				wp.getNumeroOperacionMadre(), 
				wp.getAutorizacion(),
				wp.getNombreAgencia(), 
				wp.getTelefonoAsesor(),
				wp.getNombreAsesor(), 
				wp.getCorreoAsesor());
    	TbQoExcepcionOperativa eo = wp.getEx();
    	eo.setIdNegociacion(rw.getCredito().getTbQoNegociacion());
    	eo.setCodigoOperacion(rw.getCredito().getCodigo());
    	
        return  this.excepcionOperativaService.solicitarExcepcionServicios(eo,proceso,wp.getAsesor());

    }
    @POST
    @Path("/solicitarExcepcionFabrica")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoExcepcionOperativa solicitarExcepcionFabrica(@QueryParam("proceso") ProcesoEnum proceso, TbQoExcepcionOperativa ex) throws RelativeException {
        return  this.excepcionOperativaService.solicitarExcepcionFabrica(ex,proceso);

    }

    @GET
    @Path("/findByNegociacionAndTipo")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public  TbQoExcepcionOperativa findByNegociacionAndTipo(@QueryParam("idNegociacion") Long idNegociacion,
                                                            @QueryParam("tipoExcepcion") String tipoExcepcion,
                                                            @QueryParam("estado") EstadoExcepcionEnum estado) throws RelativeException {
        return this.excepcionOperativaService.findByNegociacionAndTipo(idNegociacion,tipoExcepcion, estado);
    }
    @GET
    @Path("/findByNegociacion")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public  GenericWrapper<TbQoExcepcionOperativa> findByNegociacionAndTipo(@QueryParam("idNegociacion") Long idNegociacion) throws RelativeException {
    	GenericWrapper<TbQoExcepcionOperativa> loc = new GenericWrapper<>();
        loc.setEntidades(this.excepcionOperativaService.findByNegociacion(idNegociacion));
        return loc;
    }

    @POST
    @Path("/resolverExcepcion")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoExcepcionOperativa resolverExcepcion(@QueryParam("proceso") ProcesoEnum proceso,@QueryParam("nombreAsesor") String nombreAsesor, TbQoExcepcionOperativa ex) throws RelativeException {
        return  this.excepcionOperativaService.resolverExcepcion(ex,proceso, nombreAsesor);

    }
    @POST
    @Path("/cancelarExcepcion")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoExcepcionOperativa cancelarExcepcion(@QueryParam("proceso") ProcesoEnum proceso,@QueryParam("nombreAsesor") String nombreAsesor, TbQoExcepcionOperativa ex) throws RelativeException {
        return  this.excepcionOperativaService.cancelarExcepcion(ex,proceso, nombreAsesor);

    }
    

    @GET
    @Path("/traerCreditoNegociacionByExcepcionOperativa")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public DetalleCreditoEnProcesoWrapper traerCreditoNegociacionByExcepcionOperativa(@QueryParam("idExcepcionOperativa") Long idExcepcionOperativa) throws RelativeException {
        return this.excepcionOperativaService.traerCreditoNegociacionByExcepcionOperativa(idExcepcionOperativa);
    }
    @GET
    @Path("/traerCreditoNegociacionExistenteByExcepcionOperativa")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public AprobacionWrapper traerCreditoNegociacionExistenteByExcepcionOperativa(
            @QueryParam("autorizacion") String autorizacion,
            @QueryParam("idExcepcionOperativa") Long idExcepcionOperativa) throws RelativeException {
        return this.excepcionOperativaService.traerCreditoNegociacionExistenteByExcepcionOperativa(idExcepcionOperativa,autorizacion);
    }

}
