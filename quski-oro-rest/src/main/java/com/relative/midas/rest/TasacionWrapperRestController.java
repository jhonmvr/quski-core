package com.relative.midas.rest;
import java.math.BigDecimal;
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
import com.relative.quski.wrapper.DetalleCreditoWrapper;
import com.relative.quski.wrapper.TasacionWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/tasacionWrapperRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "tasacionWrapperController - REST CRUD")

public class TasacionWrapperRestController extends BaseRestController
implements CrudRestControllerInterface<TasacionWrapper, GenericWrapper<TasacionWrapper>>  {
	static final String oro14k = "14k";
	static final String oro15k = "15k";
	private BigDecimal multiplicador = new BigDecimal("0.00") ;
	private BigDecimal valor = new BigDecimal("0.00");
	//private String[] tipoOro = {"QUILATE14", "QUILATE18"};
	@Inject
	Logger log;
	public TasacionWrapperRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public GenericWrapper<DetalleCreditoWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<DetalleCreditoWrapper>", notes = "Metodo Get listAllEntities Retorna un mock de riesgo acumulado ", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<DetalleCreditoWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated
			
			
			) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<DetalleCreditoWrapper> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<DetalleCreditoWrapper> plw = new PaginatedListWrapper<>(pw);
		List<DetalleCreditoWrapper> listaDetalleCredito = new ArrayList<>();
		DetalleCreditoWrapper gestion = new DetalleCreditoWrapper();


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
	public GenericWrapper<DetalleCreditoWrapper> persistEntity(GenericWrapper<DetalleCreditoWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
*/	
	
	@GET
	@Path("/obtenerCalculosTasacion")
	@ApiOperation(value = "TasacionWrapper", notes = "Metodo TasacionWrapper Retorna wrapper de entidades encontradas en TasacionWrapper", response = GenericWrapper.class)
	public TasacionWrapper obtenerCalculosTasacion(
			@QueryParam("pesoBruto") String pesoBruto,
			@QueryParam("numeroPiezas") String numeroPiezas,
			@QueryParam("tipoJoya") String tipoJoya,
			@QueryParam("tipoOro") String tipoOro,
			@QueryParam("estado") String estado
			) throws RelativeException {
		TasacionWrapper calculosTasacion = new TasacionWrapper();
		if(tipoOro.equals("14K") ) {
			multiplicador = BigDecimal.valueOf(Double.valueOf("1.00"));
		} else if(tipoOro.equals("18K")) {
			multiplicador =  BigDecimal.valueOf(Double.valueOf("1.50"));
		}
		calculosTasacion.setDescuentoPesoPiedra(BigDecimal.valueOf(Double.valueOf("1.00")).multiply(multiplicador).setScale(2,BigDecimal.ROUND_HALF_EVEN));
		calculosTasacion.setDescuentoSuelda(new BigDecimal(1).multiply(multiplicador).setScale(2,BigDecimal.ROUND_HALF_EVEN));
		calculosTasacion.setDescripcion("lo que sea");
		calculosTasacion.setPesoNeto(new BigDecimal(pesoBruto).multiply(multiplicador).setScale(2,BigDecimal.ROUND_HALF_EVEN));
		calculosTasacion.setValorAvaluo(new BigDecimal("1000.00").multiply(multiplicador).setScale(2,BigDecimal.ROUND_HALF_EVEN));
		calculosTasacion.setValorComercial(new BigDecimal(10.22).add(multiplicador));
		calculosTasacion.setValorRealizacion(new BigDecimal(10.22).add(multiplicador));
		calculosTasacion.setValorOro(new BigDecimal("50").multiply(multiplicador));
		
		return calculosTasacion;
	}

@Override
public GenericWrapper<TasacionWrapper> getEntity(String arg0) throws RelativeException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public PaginatedListWrapper<TasacionWrapper> listAllEntities(String arg0, String arg1, String arg2, String arg3,
		String arg4) throws RelativeException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public GenericWrapper<TasacionWrapper> persistEntity(GenericWrapper<TasacionWrapper> arg0) throws RelativeException {
	// TODO Auto-generated method stub
	return null;
}
}
