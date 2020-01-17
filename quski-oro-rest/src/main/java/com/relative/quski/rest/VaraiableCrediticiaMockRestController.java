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
import com.relative.quski.wrapper.VariableCrediticiaWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/VaraiableCrediticiaMockRestController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "RiesgoAcumuladoRestController - REST CRUD")

public class VaraiableCrediticiaMockRestController  extends BaseRestController
implements CrudRestControllerInterface<VariableCrediticiaWrapper, GenericWrapper<VariableCrediticiaWrapper>> {

	public VaraiableCrediticiaMockRestController() throws RelativeException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deleteEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericWrapper<VariableCrediticiaWrapper> getEntity(String arg0) throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	@Path("/listAllEntities")
	@ApiOperation(value = "PaginatedListWrapper<VariableCrediticiaWrapper>", notes = "Metodo Get listAllEntities Retorna un mock de variables creiticias ", response = PaginatedListWrapper.class)
	public PaginatedListWrapper<VariableCrediticiaWrapper> listAllEntities(@QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("pageSize") @DefaultValue("10") String pageSize,
			@QueryParam("sortFields") @DefaultValue("id") String sortFields,
			@QueryParam("sortDirections") @DefaultValue("asc") String sortDirections,
			@QueryParam("isPaginated") @DefaultValue("N") String isPaginated) throws RelativeException {
		Integer firstItem = Integer.valueOf(page) * Integer.valueOf(pageSize);
		return findAll(
				new PaginatedWrapper(firstItem, Integer.valueOf(pageSize), sortFields, sortDirections, isPaginated));

	}
	
	private PaginatedListWrapper<VariableCrediticiaWrapper> findAll(PaginatedWrapper pw) throws RelativeException {
		PaginatedListWrapper<VariableCrediticiaWrapper> plw = new PaginatedListWrapper<>(pw);
		List<VariableCrediticiaWrapper> l = new ArrayList<>();
		VariableCrediticiaWrapper variable1 = new VariableCrediticiaWrapper();
		variable1.setOrden(1);
		variable1.setNombre("Tipo Cliente");
		variable1.setValor("C1");
		
	
		l.add(variable1);    
		VariableCrediticiaWrapper variable2 = new VariableCrediticiaWrapper();
		variable2.setOrden(2);
		variable2.setNombre("Perfil externo");
		variable2.setValor("3");
		
		l.add(variable2);
		VariableCrediticiaWrapper variable3 = new VariableCrediticiaWrapper();
		variable3.setOrden(3);
		variable3.setNombre("Cart. Vencida");
		variable3.setValor("0.00");
		
		l.add(variable3);
		

		plw.setTotalResults(l.size());
		plw.setList(l);
		return plw;
		
	}

	@Override
	public GenericWrapper<VariableCrediticiaWrapper> persistEntity(GenericWrapper<VariableCrediticiaWrapper> arg0)
			throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
