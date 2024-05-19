package ru.pish.inadapter.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.pish.inadapter.models.dto.EmployeesDTO;

import java.util.UUID;

@Component
public class RestRequestRoute extends RouteBuilder {

//    JaxbDataFormat jaxb = new JaxbDataFormat(true);
    private final String CHARSET = "UTF-8";

    @Override
    public void configure() throws Exception {
        from("direct:SendRestRequestToRabbit")
                .id("RestRequestRouteId")
                .streamCaching()

                .setHeader("header_messageId", () -> UUID.randomUUID().toString())
                .log(LoggingLevel.INFO, "Received message: ${body}")

//                .convertBodyTo(String.class, "UTF-8")

                .removeHeaders("*", "header_*")
                .setProperty("CamelRabbitmqDeliveryMode", simple("2"))


                .setHeader("CamelRabbitmqMessageClass", simple("application/json"))

                // Преобразование объекта Java в строку JSON
                .process(exchange -> {
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(exchange.getIn().getBody(EmployeesDTO.class));
                    exchange.getIn().setBody(json);
                })

                .wireTap("spring-rabbitmq:{{spring.rabbitmq.template.exchange}}?queues={{spring.rabbitmq.queue.rest-receive-request}}&routingKey={{spring.rabbitmq.queue.rest-receive-request}}");

//                .process("responseProcessor");
    }
}
