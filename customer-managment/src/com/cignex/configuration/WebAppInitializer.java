package com.cignex.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(WebMvcConfig.class);
		applicationContext.setServletContext(servletContext);
		
		ServletRegistration.Dynamic dispather = servletContext.addServlet
				("SpringDispatcher", new DispatcherServlet(applicationContext));
		dispather.setLoadOnStartup(1);
		dispather.addMapping("/");
	}

}
