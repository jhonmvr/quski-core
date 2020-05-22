package com.relative.quski.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.quski.model.TbMiParametro;

@Singleton
@Startup
@DependsOn({"QuskiOroService"})
public class ParametrosSingleton {
	private final Map<String,TbMiParametro> parametros= new HashMap<>();
	@Inject
	Logger log;
	@Inject
	private QuskiOroService qos;
	
	 /**
     * Default constructor. 
     */
    public ParametrosSingleton() {
        System.out.println("===!!!####===>CONSTRUCTOR ParametrosSingleton");
    }
    
    @Lock(LockType.READ) 
    public Map<String,TbMiParametro>  getParametros() { 
        return parametros;
    }
 
    @Lock(LockType.WRITE)
    public void setParametros(List<TbMiParametro> parameters) throws RelativeException {
        this.setVaues(parameters);
    }

    @PostConstruct
    public void initialize() throws RelativeException {
    	log.info("===!!!####===>INICIALIZANDO ParametrosSingleton");
    	this.setVaues(null);
       
    }
    
    private void setVaues(List<TbMiParametro> parameters) throws RelativeException {
    	log.info("===!!!####===>SET DATOS DE PARAMETROS");
    	if( parameters ==null || parameters.isEmpty() ) {
    		List<TbMiParametro> tmp=this.qos.findAllParametro(null);
    	    tmp.stream().forEach( p->  this.parametros.put(p.getNombre(), p));
    		log.info("===!!!####===>va a buscar parametros en bae");
    	} else {
    		parameters.stream().forEach( p->  this.parametros.put(p.getNombre(), p));
    		//this.parametros.addAll(parameters);
    		log.info("===!!!####===>llena de parametrs enviados");
    	}
    	log.info("===!!!####===>SALE SET DATOS DE PARAMETROS ");
    }
	
	

}
