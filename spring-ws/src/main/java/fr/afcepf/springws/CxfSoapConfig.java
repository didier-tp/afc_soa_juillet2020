package fr.afcepf.springws;

import fr.afcepf.springws.service.CalculTva;
import fr.afcepf.springws.service.DeviseService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;



@Configuration
@EnableAutoConfiguration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CxfSoapConfig {

	@Autowired
    private ApplicationContext applicationContext;
	
	// Replaces the need for web.xml
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        return new ServletRegistrationBean(new CXFServlet(), "/service/*");
    }
    
    @Autowired 
    private CalculTva calculTva; //service spring interne
    
    @Autowired
    private DeviseService deviseService;

    @Bean
    public EndpointImpl deviseServiceEndpoint() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, deviseService /* implementor */);
        endpoint.publish("/deviseService");
        //URL soap complete:
        //http://localhost:8383/spring-ws/service/deviseService
        //http://localhost:8383/spring-ws/service/deviseService?wsdl
        return endpoint;
    }
 
    // Replaces cxf-servlet.xml
    @Bean
    // <jaxws:endpoint id="tvaServiceEndpoint" implementor="#calculTvaImpl" address="/calculTva	"/>
    public EndpointImpl tvaServiceEndpoint() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, calculTva /* implementor */);
        endpoint.publish("/calculTva");
        //URL soap complete:
        //http://localhost:8383/spring-ws/service/calculTva
        //http://localhost:8383/spring-ws/service/calculTva?wsdl
        return endpoint;
    }


}
 
