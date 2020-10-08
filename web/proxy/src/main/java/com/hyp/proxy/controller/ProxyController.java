package com.hyp.proxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/**/*.action")
public class ProxyController {

    @RequestMapping
    public String doService(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        final String requestURI = req.getRequestURI();
        if (requestURI.equals("/loginPage.action")) {
            return loginPage();
        }
        else if (requestURI.equals("/login.action")) {
            resp.getWriter().write("{\"status\":\"success\"}");
            return null;
        }
        else {
            return doProxy(req, resp);
        }
    }

    private String loginPage() {
        return "loginPage.html";
    }

    public String doProxy(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.getWriter().write("You are requesting proxy");
        return null;
    }

}
