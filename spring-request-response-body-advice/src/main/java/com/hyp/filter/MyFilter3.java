package com.hyp.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyp.dto.ReqMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter
@Slf4j
public class MyFilter3 extends OncePerRequestFilter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
            setMDC(req);
            logReq(req);
            filterChain.doFilter(req, response);
            logResp(response);
        } catch (Throwable e) {
            MDC.clear();
        }
    }

    private void setMDC(ContentCachingRequestWrapper req) {
        try {
            ReqMessage reqMessage = objectMapper.readValue(req.getContentAsByteArray(), ReqMessage.class);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logResp(HttpServletResponse response) {

    }

    private void logReq(HttpServletRequest request) {
        byte[] content = ((ContentCachingRequestWrapper) request).getContentAsByteArray();
        try {
            String req = new String(content, "UTF-8");
            log.info("reqbean: {}", req);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
