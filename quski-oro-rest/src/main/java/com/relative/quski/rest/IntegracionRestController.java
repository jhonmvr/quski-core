package com.relative.quski.rest;

import javax.ws.rs.GET;
import java.io.Serializable;
import com.relative.integracion.calculadora.cliente.wrapper.Informacion;
import com.relative.quski.service.IntegracionServiceImp;
import com.relative.core.util.main.BaseWrapper;
import javax.ws.rs.QueryParam;
import com.relative.core.exception.RelativeException;
import javax.inject.Inject;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import com.relative.core.web.util.BaseRestController;

@Path("/integracionRestController")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@ApiOperation(value = "IntegracionRestController", notes = "Metodo Get que retorna la informacion del cliente mediante SOAP")
public class IntegracionRestController extends BaseRestController
{
    @Inject
    IntegracionServiceImp is;
    
    public IntegracionRestController() throws RelativeException {
    }
    
    @GET
    @Path("/getInformacionPersona")
    public BaseWrapper<Informacion> getInformacionPersona(@QueryParam("tipoIdentificacion") final String tipoIdentificacion, @QueryParam("identificacion") final String identificacion, @QueryParam("tipoConsulta") final String tipoConsulta, @QueryParam("calificacion") final String calificacion) throws RelativeException {
        final BaseWrapper<Informacion> info = (BaseWrapper<Informacion>)new BaseWrapper();
        info.setEntidad((Informacion)this.is.getClienteInformacion(tipoIdentificacion, identificacion, tipoConsulta, calificacion));
        return info;
    }}
