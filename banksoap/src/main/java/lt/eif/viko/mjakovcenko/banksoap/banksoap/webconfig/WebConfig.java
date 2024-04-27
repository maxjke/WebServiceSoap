package lt.eif.viko.mjakovcenko.banksoap.banksoap.webconfig;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
/**
 * Configuration class for Spring Web Services.
 */
@EnableWs
@Configuration
public class WebConfig extends WsConfigurerAdapter {


    /**
     * Configures the MessageDispatcherServlet specific for handling SOAP requests.
     * Registers the servlet with the Spring application context and sets the servlet to transform WSDL locations.
     *
     * @param applicationContext the Spring application context to which the servlet is to be registered
     * @return a ServletRegistrationBean that wraps the configured MessageDispatcherServlet
     */
        @Bean
        public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
            MessageDispatcherServlet servlet = new MessageDispatcherServlet();
            servlet.setApplicationContext(applicationContext);
            servlet.setTransformWsdlLocations(true);
            return new ServletRegistrationBean<>(servlet, "/ws/*");
        }
    /**
     * Creates and configures a WSDL definition for the Client service.
     * Sets the WSDL's port type name, location URI, target namespace, and the schema that defines the service.
     *
     * @param clientSchema the XSD schema that the WSDL should use to define the SOAP service's data types
     * @return a DefaultWsdl11Definition configured for the Client SOAP service
     */
        @Bean(name = "client")
        public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema clientSchema) {
            DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
            wsdl11Definition.setPortTypeName("ClientPort");
            wsdl11Definition.setLocationUri("/ws");
            wsdl11Definition.setTargetNamespace("http://eif.viko.lt/mj/springsoap/gen");
            wsdl11Definition.setSchema(clientSchema);
            return wsdl11Definition;
        }
    /**
     * Provides the XSD schema resource for the Client SOAP service.
     *
     * @return an XsdSchema object representing the client.xsd schema
     */
        @Bean
        public XsdSchema clientSchema() {
            return new SimpleXsdSchema(new ClassPathResource("client.xsd"));
        }
    }


