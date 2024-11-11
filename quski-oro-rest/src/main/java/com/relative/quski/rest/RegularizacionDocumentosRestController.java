package com.relative.quski.rest;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoExcepcionOperativa;
import com.relative.quski.model.TbQoRegularizacionDocumento;
import com.relative.quski.service.QuskiOroService;
import com.relative.quski.service.RegularizacionDocumentosService;
import com.relative.quski.wrapper.DetalleCreditoEnProcesoWrapper;
import com.relative.quski.wrapper.RegularizacionClienteWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/regularizacionDocumentosRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "RegularizacionDocumentosController - REST CRUD")
public class RegularizacionDocumentosRestController extends BaseRestController
        implements CrudRestControllerInterface<TbQoRegularizacionDocumento, GenericWrapper<TbQoRegularizacionDocumento>> {

    @Inject
    RegularizacionDocumentosService regularizacionDocumentosService;
    public RegularizacionDocumentosRestController() throws RelativeException {
        super();
    }

    @Inject
    QuskiOroService qos;

    @Override
    @GET
    @Path("/getEntity")
    @ApiOperation(value = "id", notes = "Metodo getEntity Retorna wrapper de entidades encontradas en TbQoRegularizacionDocumento", response = GenericWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
    public GenericWrapper<TbQoRegularizacionDocumento> getEntity(@QueryParam("id") String id) throws RelativeException {
        GenericWrapper<TbQoRegularizacionDocumento> loc = new GenericWrapper<>();
        TbQoRegularizacionDocumento a = this.qos.findRegularizacionDocumentosById(Long.valueOf(id));
        loc.setEntidad(a);
        return loc;
    }

    @Override
    @GET
    @Path("/listAllEntities")
    @ApiOperation(value = "PaginatedListWrapper<TbQoRegularizacionDocumento>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoRegularizacionDocumento", response = PaginatedListWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
    public PaginatedListWrapper<TbQoRegularizacionDocumento> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
                                                                    @QueryParam("pageSize") @DefaultValue("10") String pageSize,
                                                                    @QueryParam("sortFields") @DefaultValue("id") String sortFields,
                                                                    @QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
                                                                    @QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
        Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
        return findAll(
                new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

    }

    private PaginatedListWrapper<TbQoRegularizacionDocumento> findAll(PaginatedWrapper pw) throws RelativeException {
        PaginatedListWrapper<TbQoRegularizacionDocumento> plw = new PaginatedListWrapper<>(pw);
        List<TbQoRegularizacionDocumento> actions = this.qos.findAllRegularizacionDocumentos(pw);
        if (actions != null && !actions.isEmpty()) {
            plw.setTotalResults(this.qos.countRegularizacionDocumentos().intValue());
            plw.setList(actions);
        }
        return plw;
    }

    @Override
    @POST
    @Path("/persistEntity")
    @ApiOperation(value = "GenericWrapper<TbQoRegularizacionDocumento>", notes = "Metodo Post persistEntity Registra  y retorna la entidad TbQoRegularizacionDocumento", response = GenericWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class) })
    public GenericWrapper<TbQoRegularizacionDocumento> persistEntity(GenericWrapper<TbQoRegularizacionDocumento> wp)
            throws RelativeException {
        GenericWrapper<TbQoRegularizacionDocumento> loc = new GenericWrapper<>();
        loc.setEntidad(this.qos.manageRegularizacionDocumentos(wp.getEntidad()));
        return loc;
    }

    @Override
    public void deleteEntity(String arg0) throws RelativeException {
        // TODO Auto-generated method stub

    }


    @GET
    @Path("/listAllByParams")
    @ApiOperation(value = "PaginatedListWrapper<TbQoRegularizacionDocumento>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoRegularizacionDocumento", response = PaginatedListWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public PaginatedListWrapper<TbQoRegularizacionDocumento> listAllByParams(@QueryParam("page") @DefaultValue("1") String page,
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
        return this.regularizacionDocumentosService.listAllByParams(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated, usuario, estado,codigo,codigoOperacion,idNegociacion);
    }
    @GET
    @Path("/findAllByParamsWithClient")
    @ApiOperation(value = "PaginatedListWrapper<TbQoExcepcionOperativa>", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = PaginatedListWrapper.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public List<RegularizacionClienteWrapper> findAllByParamsWithClient(
                                                                        @QueryParam("usuario") String usuario,
                                                                        @QueryParam("cedula") String cedula) throws RelativeException {
        return this.regularizacionDocumentosService.listAllByParamsClient(usuario, cedula);
    }

    @GET
    @Path("/traerCreditoNegociacionByRegularizacion")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public DetalleCreditoEnProcesoWrapper traerCreditoNegociacionByRegularizacion(@QueryParam("idRegularizacion") Long idRegularizacion) throws RelativeException {
        return this.regularizacionDocumentosService.traerCreditoNegociacionByRegularizacion(idRegularizacion);
    }

    @POST
    @Path("/solicitarAprobacion")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoRegularizacionDocumento solicitarAprobacion( TbQoRegularizacionDocumento regularizacion) throws RelativeException {
        return this.regularizacionDocumentosService.solicitarAprobacion(regularizacion);
    }

    @POST
    @Path("/enviarRespuesta")
    @ApiOperation(value = "TbQoExcepcionOperativa", notes = "Metodo Get listAllEntities Retorna wrapper de informacion de paginacion y entidades encontradas en TbQoExcepcionOperativa", response = TbQoExcepcionOperativa.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorno existoso de informacion", response = GenericWrapper.class),
            @ApiResponse(code = 500, message = "Retorno con ERROR en la carga de acciones", response = RelativeException.class)})
    public TbQoRegularizacionDocumento enviarRespuesta( TbQoRegularizacionDocumento regularizacion) throws RelativeException {
        return this.regularizacionDocumentosService.enviarRespuesta(regularizacion);
    }
}
