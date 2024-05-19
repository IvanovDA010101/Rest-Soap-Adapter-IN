package ru.pish.inadapter.configuration.handler;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.FaultOutInterceptor;
import org.apache.cxf.message.Message;
import org.springframework.stereotype.Component;

@Component
public class FaultInterceptor extends FaultOutInterceptor {
    @Override
    public void handleMessage(Message message) throws Fault {
        message.put(Message.RESPONSE_CODE, 400);
    }

}
