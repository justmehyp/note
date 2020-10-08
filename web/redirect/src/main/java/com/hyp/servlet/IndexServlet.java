package com.hyp.servlet;

import com.hyp.RedirectLauncher;
import org.apache.http.client.fluent.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/github/*")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = "https://github.com" + req.getRequestURI().substring("/github".length());

        Request request;
        if ("GET".equals(req.getMethod())) {
            request = Request.Get(uri);
        } else {
            request = Request.Post(uri);
        }

        request.addHeader("Cookie", RedirectLauncher.getCookieString())
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                .execute()
                .handleResponse(response -> {
                    try (OutputStream os = resp.getOutputStream()) {
                        response.getEntity().writeTo(os);
                        return null;
                    }
                });
    }

}
