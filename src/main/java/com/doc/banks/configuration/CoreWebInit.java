package com.doc.banks.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.Conventions;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CoreWebInit implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    /*
     * DispatcherServlet Context: defines this servlet's request-processing infrastructure
     */
    AnnotationConfigWebApplicationContext appServletContext = new AnnotationConfigWebApplicationContext();
    appServletContext.register(PrimaryConfig.class);
    ServletRegistration.Dynamic appServlet =
        servletContext.addServlet("appServlet", new DispatcherServlet(appServletContext));
    appServlet.addMapping("/*");
    appServlet.setLoadOnStartup(1);

    registerServletFilter(servletContext, new FilterCORS());
  }

  protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
    String filterName = Conventions.getVariableName(filter);
    FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
    registration.setAsyncSupported(true);
    registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
    return registration;
  }

}