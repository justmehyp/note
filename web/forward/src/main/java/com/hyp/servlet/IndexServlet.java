package com.hyp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("MyServlet before");
//        req.getRequestDispatcher("/hello").forward(req, resp);
        req.getRequestDispatcher("/hello").include(req, resp); // 这里虽然是 include，但如果 hello 里使用了 forward，最终还是forward
        resp.getWriter().println("MyServlet after");
    }
}
