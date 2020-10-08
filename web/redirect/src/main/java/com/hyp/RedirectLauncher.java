package com.hyp;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
@ServletComponentScan
public class RedirectLauncher {

    public static Map<String, String> cookies = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.useSystemProxies", "true");
        login();
        SpringApplication.run(RedirectLauncher.class);
    }

    public static String getCookieString() {
        final StringBuilder sb = new StringBuilder();
        int size = cookies.size();
        cookies.forEach((name, value) -> {
            sb.append(name).append("=").append(value).append("; ");
        });
        return sb.toString();
    }

    private static void login() throws Exception {
        doLogin("https://github.com/session", "justmehyp", "Hyp101895,");
    }

    private static void doLogin(String url, String username, String password) throws Exception {
        // 1) 打开登录页面
        String[] authenticity_token = new String[1];
        Request.Get("https://github.com/login")
                .viaProxy(new HttpHost("127.0.0.1", 8888))
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                .execute()
                .handleResponse((response -> {
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    response.getEntity().writeTo(baos);
                    final String content = baos.toString();
                    final Pattern pattern = Pattern.compile("<input type=\"hidden\" name=\"authenticity_token\" value=\"(.*)\" />");
                    final Matcher matcher = pattern.matcher(content);
                    authenticity_token[0] = matcher.find() ? matcher.group(1) : "";
                    return null;
                }));


        System.out.println("authenticity_token: " + authenticity_token[0]);

        // 2) 登录
        Request.Post(url)
                .viaProxy(new HttpHost("127.0.0.1", 8888))
                .addHeader("Origin", "https://github.com")
                .addHeader("Referer", "https://github.com/login")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                .bodyForm(Form.form()
                        .add("login", username)
                        .add("password", password)
                        .add("commit", "Sign in")
                        .add("authenticity_token", authenticity_token[0])
                        .add("return_to", "")
                        .add("timestamp", String.valueOf(System.currentTimeMillis()))
                        .build()
                )
                .execute()
                .handleResponse((resp) -> {
                    final Header[] cookies = resp.getHeaders("Set-Cookie");
                    for (Header cookie : cookies) {
//                        System.out.println(cookie.getValue());
                        final String[] keyValue = cookie.getValue().split("=", 2);
                        saveCookie(keyValue[0], keyValue[1]);

                    }
                    return null;
                });
    }

    private static void saveCookie(String name, String value) {
//        Cooki
        cookies.put(name, value);
    }
}
