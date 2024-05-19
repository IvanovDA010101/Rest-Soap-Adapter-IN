package ru.pish.inadapter.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.common.DataFormat;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.pish.inadapter.configuration.authorization.BasicAuthAuthorizationInterceptor;
import ru.pish.inadapter.configuration.handler.FaultInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan
public class InAdapterConfiguration {


    @Autowired
    CamelContext camelContext;

    @Autowired
    SpringBus bus;

    @Bean
    public CxfEndpoint webServiceIn(BasicAuthAuthorizationInterceptor authInterceptor,
                                     FaultInterceptor faultInterceptor,
                                     @Value("${service.webservice_url}") String webserviceUrl) {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setCamelContext(camelContext);
        cxfEndpoint.setBus(bus);
        cxfEndpoint.setAddress(webserviceUrl);
        cxfEndpoint.setWsdlURL("META-INF/wsdl/Example.wsdl");
        cxfEndpoint.setServiceClass(ru.pish.education.IEmployeePortType.class);
        cxfEndpoint.setDataFormat(DataFormat.PAYLOAD);

        Map<String, Object> cxfProperties = new HashMap<>();
        cxfProperties.put("relayHeaders", "true");
        cxfProperties.put("schema-validation-enabled", "true");
        cxfEndpoint.setProperties(cxfProperties);
        cxfEndpoint.setContinuationTimeout(60000);
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        cxfEndpoint.setInInterceptors(List.of(authInterceptor, loggingInInterceptor));
        cxfEndpoint.setOutFaultInterceptors(List.of(faultInterceptor));

        return cxfEndpoint;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Adapter  with json")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                )
                );
    }

}
