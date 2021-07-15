package com.relative.quski.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.relative.quski.rest.ClienteRestController;

import io.swagger.jaxrs.config.BeanConfig;

@WebServlet(
        urlPatterns = "/SwaggerServlet",
        loadOnStartup = 1,
        asyncSupported = true
        )

public class SwaggerServlet extends HttpServlet {
	public void init(ServletConfig config) {
		
        System.out.println("My servlet has been initialized");
        System.out.println("===============initi bean config");   
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/quski-oro-rest/resources/");
        beanConfig.setResourcePackage(ClienteRestController.class.getPackage().getName());
        beanConfig.setTitle("QUSKI CORE- API REFERENCES");
        beanConfig.setDescription("MODULO PARA QUSKI CORE");
        beanConfig.setScan(true);
    }

}
