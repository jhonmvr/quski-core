package com.relative.quski.rest;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoComprobante;
import com.relative.quski.service.ComprobanteService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comprobanteRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clienteRestController - REST CRUD")
public class ComprobanteRestController extends BaseRestController implements CrudRestControllerInterface<TbQoComprobante, GenericWrapper<TbQoComprobante>> {
    @Inject
    ComprobanteService  comprobanteService;
    public ComprobanteRestController() throws RelativeException {
        super();
    }

    @Override
    public PaginatedListWrapper<TbQoComprobante> listAllEntities(String s, String s1, String s2, String s3, String s4) throws RelativeException {
        return null;
    }

    @Override
    @GET
    @Path("/getEntity")
    public GenericWrapper<TbQoComprobante> getEntity(String s) throws RelativeException {
        return null;
    }

    @Override

    public GenericWrapper<TbQoComprobante> persistEntity(GenericWrapper<TbQoComprobante> tbQoComprobanteGenericWrapper) throws RelativeException {
        return null;
    }

    @Override
    public void deleteEntity(String s) throws RelativeException {

    }

    @GET
    @Path("/listAllByIdNegociacion")
    public List<TbQoComprobante> listAllByIdNegociacion(@QueryParam("idNegociacion") Long idNegociacion)  throws RelativeException{
        return comprobanteService.findAllByIdNegociacion(idNegociacion);
    }

    @POST
    @Path("/agregarComprobante")
    public TbQoComprobante agregarComprobante(TbQoComprobante comprobante)  throws RelativeException{
        return comprobanteService.agregarComprobante(comprobante);
    }

    @DELETE
    @Path("/eliminarComprobante")
    public void eliminarComprobante(@QueryParam("id")Long id)  throws RelativeException{
        comprobanteService.eliminarComprobante(id);
    }
}
