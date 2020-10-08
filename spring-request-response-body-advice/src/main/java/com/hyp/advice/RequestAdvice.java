package com.hyp.advice;

import com.hyp.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;


//@ControllerAdvice(assignableTypes = HelloController.class)
//@ControllerAdvice
public class RequestAdvice implements RequestBodyAdvice {

    private static Logger logger = LoggerFactory.getLogger(RequestAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        try (InputStream body = inputMessage.getBody()) {
            byte[] bytes = StreamUtils.copyToByteArray(body);
            inputMessage = new BufferedHttpInputMessage(inputMessage, bytes);
            logReq(bytes);
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        logReq(new byte[0]);
        return body;
    }

    private void logReq(byte[] body) {
        try {
            logger.info("reqbean: {}", new String(body, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    static class BufferedHttpInputMessage implements HttpInputMessage {

        private HttpInputMessage origin;
        private byte[] body;

        public BufferedHttpInputMessage(HttpInputMessage origin, byte[] body) {
            this.origin = origin;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(body);
        }

        @Override
        public HttpHeaders getHeaders() {
            return origin.getHeaders();
        }
    }
}
