package it.maggioli.pagopa.pagonet.jppanotify.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import it.gov.pagopa.pagopa_api.pafornode.PaForNode;

import org.apache.cxf.ext.logging.LoggingFeature;

import jakarta.servlet.Servlet;

@Configuration
public class SoapConfig
{
	@Autowired
	private Bus bus;
	
	@Autowired
	// private PaForNode mgpInternalFacet;
	
	@Bean
	public ServletRegistrationBean<Servlet> servletRegistrationBean(ApplicationContext context) 
	{
		return new ServletRegistrationBean<Servlet>(new CXFServlet(), "/services/*");
	}

	@Bean
	public EndpointImpl productService() 
	{
		LoggingFeature loggingFeature = new LoggingFeature();
		loggingFeature.setPrettyLogging(true);
		
		bus.setFeatures(Arrays.asList(loggingFeature));
		
		EndpointImpl endpoint = new EndpointImpl(bus, null);
		endpoint.publish("/notify");
		
		return endpoint;
	}
}
