package ch.admin.seco.jobs.services.jobadservice.infrastructure.messagebroker.avam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class AvamEndpointConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(
            ApplicationContext applicationContext,
            @Value("${jobroom.ws.avam.source.url-mapping:/services/*}") String urlMapping) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, urlMapping);
    }

    @Bean(name = "SecoEgovService")
    public Wsdl11Definition secoEgovServiceWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/schema/AVAMToEgov.wsdl")); //your schema location
        return wsdl11Definition;
    }

    @Bean(name = "SecoEgovServiceXsd")
    public XsdSchema secoEgovSchema() {
        return new SimpleXsdSchema(new ClassPathResource("/schema/AVAMToEgov.xsd"));
    }

    @Bean
    public JobAdvertisementFromAvamAssembler jobAdvertisementFromAvamAssembler() {
        return new JobAdvertisementFromAvamAssembler();
    }
}
