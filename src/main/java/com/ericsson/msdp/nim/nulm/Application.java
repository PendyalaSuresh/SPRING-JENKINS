package com.ericsson.msdp.nim.nulm;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class Application  implements WebApplicationInitializer {

	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setConfigLocation("com.ericsson.msdp.nim.nulm");
		ctx.register(AppConfig.class);
		ctx.setServletContext(container);

        // IMPORTANT!!
        container.addListener(new ContextLoaderListener(ctx));
        container.setInitParameter("spring.profiles.default", "dev");
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		// IMPORTANT!!
		servlet.setInitParameter("contextClass", ctx.getClass().getName());
 
        
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		final ServletRegistration.Dynamic appServlet = container.addServlet("appServlet", new SpringServlet());
	    appServlet.setInitParameter("com.sun.jersey.config.property.packages", "com.ericsson.msdp.nim.nulm.controller");
	    /*appServlet.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", "com.myapp.api.SizeLimitFilter");*/
	    appServlet.setLoadOnStartup(1);
	    /*appServlet.addMapping("/");*/
	}

}