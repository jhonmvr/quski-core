package com.relative.quski.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.PaginatedListWrapper;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.core.web.util.BaseRestController;
import com.relative.core.web.util.CrudRestControllerInterface;
import com.relative.core.web.util.GenericWrapper;
import com.relative.quski.model.TbQoCotizador;
import com.relative.quski.wrapper.RiesgoAcumuladoWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/riesgoAcumuladoRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "RiesgoAcumuladoRestController - REST CRUD")

public class RiesgoAcumuladoRestController extends BaseRestController
implements CrudRestControllerInterface<RiesgoAcumuladoWrapper, GenericWrapper<RiesgoAcumuladoWrapper>> {

	public RiesgoAcumuladoRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericWrapper<RiesgoAcumuladoWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<RiesgoAcumuladoWrapper>", notes = "Metodo Get listAllEntities Retorna un mock de riesgo acumulado ", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<RiesgoAcumuladoWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<RiesgoAcumuladoWrapper> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<RiesgoAcumuladoWrapper> plw = new PaginatedListWrapper<>(pw);
		List<RiesgoAcumuladoWrapper> l = new ArrayList<>();
		RiesgoAcumuladoWrapper riesgo1 = new RiesgoAcumuladoWrapper();
		riesgo1.setNroOperacion("12");
		riesgo1.setTipoOferta("Tipo Oferta");
		riesgo1.setNroPrestamo("564");
		riesgo1.setCuentaIndividual("Ahorros");
		riesgo1.setTipoCredito("OPERACIÓN CREDITO");
		riesgo1.setCapitalInicial(1000.00);
		riesgo1.setSaldoCapital(1000.00);
		riesgo1.setPlazo(2);
		riesgo1.setFechaAprobacion("20-08-1981");
		riesgo1.setFechaFinalCredito("3-5-2019");
		riesgo1.setCoberturaAnterior("Corto plazo");
		riesgo1.setCoberturaActual("Cobertura Actual");
		riesgo1.setEstatusCredito("activo");
		riesgo1.setMotivoBloqueo("Motivo Credito");
		riesgo1.setDiasMora(5);
		riesgo1.setEstadoMediacion("Estado Mediacion");
		riesgo1.setRetanqueo("Retanqueo");
		riesgo1.setCuota(143.00);
		riesgo1.setCapitalCuotaAtrasada(98.00);
		riesgo1.setInteresesCuotaAtrasada(10.20);
		riesgo1.setMora(2.0);
		riesgo1.setGestionCobranza(5.00);
		riesgo1.setCustodia(3.00);
		riesgo1.setTotalDeuda(350.00);
		riesgo1.setNroCuotaImpagas(3);
		riesgo1.setUltDivPagado(23.00);
		
	
		l.add(riesgo1);    
		RiesgoAcumuladoWrapper riesgo2 = new RiesgoAcumuladoWrapper();
		riesgo2.setNroOperacion("13");
		riesgo2.setTipoOferta("Tipo Ofertas");
		riesgo2.setNroPrestamo("565");
		riesgo2.setCuentaIndividual("Credito");
		riesgo2.setTipoCredito("OPERACIÓN CREDITO");
		riesgo2.setCapitalInicial(300.00);
		riesgo2.setSaldoCapital(1000.00);
		riesgo2.setPlazo(2);
		riesgo2.setFechaAprobacion("20-08-1981");
		riesgo2.setFechaFinalCredito("3-5-2019");
		riesgo2.setCoberturaAnterior("Corto plazo");
		riesgo2.setCoberturaActual("Cobertura Actual");
		riesgo2.setEstatusCredito("activo");
		riesgo2.setMotivoBloqueo("Motivo Credito");
		riesgo2.setDiasMora(2);
		riesgo2.setEstadoMediacion("Estado Mediacion");
		riesgo2.setRetanqueo("Retanqueo");
		riesgo2.setCuota(143.00);
		riesgo2.setCapitalCuotaAtrasada(98.00);
		riesgo2.setInteresesCuotaAtrasada(10.20);
		riesgo2.setMora(2.0);
		riesgo2.setGestionCobranza(5.00);
		riesgo2.setCustodia(3.00);
		riesgo2.setTotalDeuda(350.00);
		riesgo2.setNroCuotaImpagas(3);
		riesgo2.setUltDivPagado(23.00);
		l.add(riesgo2);
		

		plw.setTotalResults(l.size());
		plw.setList(l);
		return plw;
		
	}
		// TODO Auto-generated method stub
		
	

	@Override
	public GenericWrapper<RiesgoAcumuladoWrapper> persistEntity(GenericWrapper<RiesgoAcumuladoWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}

