package com.relative.quski.rest;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoComprobantePago;
import com.relative.quski.service.ComprobantePagoService;
import com.relative.quski.service.ComprobanteService;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comprobantePagoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clienteRestController - REST CRUD")
public class ComprobantePagoRestController extends BaseRestController implements CrudRestControllerInterface<TbQoComprobantePago, GenericWrapper<TbQoComprobantePago>> {
    @Inject
    ComprobantePagoService comprobantePagoService;
    public ComprobantePagoRestController() throws RelativeException {
        super();
    }

    @Override
    public PaginatedListWrapper<TbQoComprobantePago> listAllEntities(String s, String s1, String s2, String s3, String s4) throws RelativeException {
        return null;
    }

    @Override
    @GET
    @Path("/getEntity")
    public GenericWrapper<TbQoComprobantePago> getEntity(String s) throws RelativeException {
        return null;
    }

    @Override

    public GenericWrapper<TbQoComprobantePago> persistEntity(GenericWrapper<TbQoComprobantePago> tbQoComprobanteGenericWrapper) throws RelativeException {
        return null;
    }

    @Override
    public void deleteEntity(String s) throws RelativeException {

    }

    @GET
    @Path("/listAllByIdNegociacion")
    public List<TbQoComprobantePago> listAllByIdNegociacion(@QueryParam("idNegociacion") Long idNegociacion)  throws RelativeException{
        return comprobantePagoService.findAllByIdNegociacion(idNegociacion);
    }

    @POST
    @Path("/agregarComprobante")
    public TbQoComprobantePago agregarComprobante(TbQoComprobantePago comprobante)  throws RelativeException{
        return comprobantePagoService.agregarComprobantePago(comprobante);
    }

    @DELETE
    @Path("/eliminarComprobante")
    public void eliminarComprobante(@QueryParam("id")Long id)  throws RelativeException{
        comprobantePagoService.eliminarComprobantePago(id);
    }
}
