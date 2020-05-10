package com.hyp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Arrays;

public class ToBeAuthorizedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // github返回的 code 授权码
        Authorization.CODE = req.getParameter("code");

        // 获取token
        try {
            final String getTokenUrl = "https://github.com/login/oauth/access_token?client_id="
                        + Authorization.CLIENT_ID + "&client_secret=" + Authorization.CLIENT_SECRET + "&code=" + Authorization.CODE;
            final URI uri = new URI(getTokenUrl);
            try (BufferedReader isr = new BufferedReader(new InputStreamReader(uri.toURL().openStream()))) {
                Authorization.ACCESS_TOKEN = parseAndGetToken(isr.readLine());
//                System.out.println(Authorization.ACCESS_TOKEN); // access_token=24dc116d58ac422aaac467e8b8e62cc137b41e23&scope=&token_type=bearer
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println("access token: " + Authorization.ACCESS_TOKEN);

        // 获取用户的github信息,
        StringBuilder sb = new StringBuilder();
        try {
            String getUserInfoUrl = "https://api.github.com/user";
            final URI uri = new URI(getUserInfoUrl);
            final URLConnection urlConnection = uri.toURL().openConnection();
            urlConnection.setRequestProperty("Authorization", "token " + Authorization.ACCESS_TOKEN);
            try (BufferedReader isr = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                sb.append(isr.readLine()).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Writer writer = resp.getWriter()) {
            writer.write(sb.toString());
        }
    }

    private String parseAndGetToken(String token) {
        if (token == null || token.isEmpty()) {
            return "";
        }
        return Arrays.stream(token.split("&"))
                .filter((paramPair -> paramPair.startsWith("access_token")))
                .map(accessToken -> accessToken.substring(accessToken.indexOf('=') + 1))
                .findFirst()
                .orElse("");
    }
}
