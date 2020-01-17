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
import com.relative.quski.wrapper.DetalleCreditoNegociacionWrapper;
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/detalleCreditoNegociacionWraperRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "detalleCreditoNegociacionWraperRestController - REST CRUD")

public class DetalleCreditoNegociacionWraperRestController extends BaseRestController
implements CrudRestControllerInterface<DetalleCreditoNegociacionWrapper, GenericWrapper<DetalleCreditoNegociacionWrapper>>  {
	static final String oro14k = "14k";
	static final String oro15k = "15k";
	private double valor;
	@Inject
	Logger log;
	public DetalleCreditoNegociacionWraperRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<DetalleCreditoWrapper>", notes = "Metodo Get listAllEntities Retorna un mock de riesgo acumulado ", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<DetalleCreditoNegociacionWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			
			
			) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<DetalleCreditoNegociacionWrapper> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<DetalleCreditoNegociacionWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DetalleCreditoNegociacionWrapper> listaDetalleCredito = new ArrayList<>();
		DetalleCreditoNegociacionWrapper gestion = new DetalleCreditoNegociacionWrapper();


		gestion.setPlazoCredito("60");
		gestion.setMontoPreaprobado(3.22 );
		gestion.setRecibirCliente(800.22);
		gestion.setCostoNuevaOperacion(1000.00);
		gestion.setCostoCustodia(1000.00);
		gestion.setCostoTransporte(10.22);
		gestion.setCostoCredito(10.22);
		gestion.setCostoSeguro(50.32);
		gestion.setCostoResguardo(20.22);
		gestion.setCostoEstimado(50.22);
		gestion.setValorCuota(52.33);
		listaDetalleCredito.add(gestion);    
		plw.setTotalResults(listaDetalleCredito.size());
		plw.setList(listaDetalleCredito);
		return plw;
		
	}

	@Override
	public GenericWrapper<DetalleCreditoNegociacionWrapper> persistEntity(GenericWrapper<DetalleCreditoNegociacionWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@GET
	@Path("/obtenerSimuladorDetalleCredito")
	@ApiOperation(value = "GenericWrapper<DetalleCreditoNegociacionWrapper>", notes = "Metodo DetalleCreditoWrapper Retorna wrapper de entidades encontradas en DetalleCreditoWrapper", response = GenericWrapper.class)
	public PaginatedListWrapper<DetalleCreditoNegociacionWrapper> obtenerSimuladorDetalleCredito(
			@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated,
			@QueryParam("joyasSeleccionadas") String joyasSeleccionadas,
			@QueryParam("totalJoyasSeleccionadas") String totalJoyasSeleccionadas
			) throws RelativeException {
		return obtenerSimuladorDetalleCredito(new PaginatedWrapper(Integer.valueOf(page), Integer.valueOf(pageSize), sortFields,
				sortDirections, isPaginated),Double.valueOf(totalJoyasSeleccionadas), joyasSeleccionadas);
	}
	
	private PaginatedListWrapper<DetalleCreditoNegociacionWrapper> obtenerSimuladorDetalleCredito(PaginatedWrapper pw, Double totalJoyasSeleccionadas, String joyasSeleccionadas) throws RelativeException {
		PaginatedListWrapper<DetalleCreditoNegociacionWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DetalleCreditoNegociacionWrapper> listaDetalleCredito = new ArrayList<>();
		DetalleCreditoNegociacionWrapper gestion1 = new DetalleCreditoNegociacionWrapper();
		DetalleCreditoNegociacionWrapper gestion2 = new DetalleCreditoNegociacionWrapper();
		gestion1.setJoyasSeleccionadas(joyasSeleccionadas);
		gestion1.setPlazoCredito("30");
		gestion1.setMontoPreaprobado(3.22 + totalJoyasSeleccionadas);
		gestion1.setRecibirCliente(800.22+ totalJoyasSeleccionadas);
		gestion1.setCostoNuevaOperacion(1000.00+ totalJoyasSeleccionadas);
		gestion1.setCostoCustodia(1000.00+ totalJoyasSeleccionadas);
		gestion1.setCostoTransporte(10.22+ totalJoyasSeleccionadas);
		gestion1.setCostoCredito(10.22+ totalJoyasSeleccionadas);
		gestion1.setCostoSeguro(50.32+ totalJoyasSeleccionadas);
		gestion1.setCostoResguardo(20.22+ totalJoyasSeleccionadas);
		gestion1.setCostoEstimado(50.22+ totalJoyasSeleccionadas);
		gestion1.setValorCuota(52.33+ totalJoyasSeleccionadas);
		
		gestion2.setJoyasSeleccionadas(joyasSeleccionadas);
		gestion2.setPlazoCredito("60"+ totalJoyasSeleccionadas);
		gestion2.setMontoPreaprobado(6.66+ totalJoyasSeleccionadas);
		gestion2.setRecibirCliente(8.22+ totalJoyasSeleccionadas);
		gestion2.setCostoNuevaOperacion(5.00+ totalJoyasSeleccionadas);
		gestion2.setCostoCustodia(56.00+ totalJoyasSeleccionadas);
		gestion2.setCostoTransporte(20.22+ totalJoyasSeleccionadas);
		gestion2.setCostoCredito(10.22+ totalJoyasSeleccionadas);
		gestion2.setCostoSeguro(20.32+ totalJoyasSeleccionadas);
		gestion2.setCostoResguardo(80.22+ totalJoyasSeleccionadas);
		gestion2.setCostoEstimado(60.22+ totalJoyasSeleccionadas);
		gestion2.setValorCuota(85.33+ totalJoyasSeleccionadas);
		
		
		//log.info("===============>valor<=============" + valor);
		//log.info("===============>constante<=============" + oro14k);
		//if(valor.equals(oro14k)  ){
			listaDetalleCredito.add(gestion1); 
			plw.setTotalResults(listaDetalleCredito.size());
			plw.setList(listaDetalleCredito);	
		//}
		//if(valor.equals(oro15k)) {
			listaDetalleCredito.add(gestion2); 
			plw.setTotalResults(listaDetalleCredito.size());
			plw.setList(listaDetalleCredito);
		//}
		return plw;
	}



	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public GenericWrapper<DetalleCreditoNegociacionWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
}
