package com.relative.quski.rest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
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
import com.relative.quski.wrapper.PatrimonioWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/patrimonioWrapperRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "patrimonioWrapperRestController - REST CRUD")

public class PatrimonioWraperRestController extends BaseRestController
implements CrudRestControllerInterface<PatrimonioWrapper, GenericWrapper<PatrimonioWrapper>>  {
	static final String oro14k = "14k";
	static final String oro15k = "15k";
	private double valor;
	@Inject
	Logger log;
	public PatrimonioWraperRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<DetalleCreditoWrapper>", notes = "Metodo Get listAllEntities Retorna un mock de riesgo acumulado ", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<PatrimonioWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			
			
			) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<PatrimonioWrapper> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<PatrimonioWrapper> plw = new PaginatedListWrapper<>(pw);
		List<PatrimonioWrapper> listaDetalleCredito = new ArrayList<>();
		PatrimonioWrapper pasivos = new PatrimonioWrapper();


		pasivos.setActivo("");
		pasivos.setAvaluo("");
		pasivos.setPasivo("Deuda chulquero");
		pasivos.setIfis(25000.00);
		pasivos.setInfocorp(1000.00);
		listaDetalleCredito.add(pasivos);    
		plw.setTotalResults(listaDetalleCredito.size());
		plw.setList(listaDetalleCredito);
		return plw;
		
	}

	@Override
	public GenericWrapper<PatrimonioWrapper> persistEntity(GenericWrapper<PatrimonioWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@GET
	@Path("/obtenerPasivos")
	@ApiOperation(value = "GenericWrapper<DetalleCreditoNegociacionWrapper>", notes = "Metodo DetalleCreditoWrapper Retorna wrapper de entidades encontradas en DetalleCreditoWrapper", response = GenericWrapper.class)
	public PaginatedListWrapper<PatrimonioWrapper> obtenerSimuladorDetalleCredito(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("idCliente") String idCliente			
			) throws RelativeException {
		return obtenerSimuladorDetalleCredito(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Long.valueOf(idCliente) );
	}
	
	private PaginatedListWrapper<PatrimonioWrapper> obtenerSimuladorDetalleCredito(PaginatedWrapper pw, Long idCliente) throws RelativeException {
		PaginatedListWrapper<PatrimonioWrapper> plw = new PaginatedListWrapper<>(pw);
		List<PatrimonioWrapper> listaPasivos = new ArrayList<>();
		PatrimonioWrapper pasivos1 = new PatrimonioWrapper();
		PatrimonioWrapper pasivos2 = new PatrimonioWrapper();
		
		pasivos1.setActivo("");
		pasivos1.setAvaluo("");
		pasivos1.setPasivo("Deuda chulquero");
		pasivos1.setIfis(25000.00);
		pasivos1.setInfocorp(1000.00);
		
		
		pasivos2.setActivo("");
		pasivos2.setAvaluo("");
		pasivos2.setPasivo("Deuda hipoteca");
		pasivos2.setIfis(4000.00);
		pasivos2.setInfocorp(1000.00);
		
		
		listaPasivos.add(pasivos1); 
		listaPasivos.add(pasivos2);
			plw.setTotalResults(listaPasivos.size());
			plw.setList(listaPasivos);	
		

		return plw;
	}



	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public GenericWrapper<PatrimonioWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
}
