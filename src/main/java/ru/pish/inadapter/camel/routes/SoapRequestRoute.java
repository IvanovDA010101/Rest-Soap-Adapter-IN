package ru.pish.inadapter.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SoapRequestRoute extends RouteBuilder {

//    JaxbDataFormat jaxb = new JaxbDataFormat(true);
    private final String CHARSET = "UTF-8";

    @Override
    public void configure() throws Exception {
        from("cxf:bean:webServiceIn")
                .id("SoapRequestRouteId")
                .streamCaching()

                .setHeader("header_messageId", () -> UUID.randomUUID().toString())
                .log(LoggingLevel.INFO, "Received message: ${body}")

                .convertBodyTo(String.class, "UTF-8")

                .removeHeaders("*", "header_*")
                .setProperty("CamelRabbitmqDeliveryMode", simple("2"))
                .convertBodyTo(String.class, CHARSET)

                .wireTap("spring-rabbitmq:{{spring.rabbitmq.template.exchange}}?queues={{spring.rabbitmq.queue.soap-receive-request}}&routingKey={{spring.rabbitmq.queue.soap-receive-request}}");

//                .process("responseProcessor");
    }
}
