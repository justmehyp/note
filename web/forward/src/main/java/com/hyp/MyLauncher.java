package com.hyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * 请求转发
 */
@SpringBootApplication
@ServletComponentScan
public class MyLauncher {

    public static void main(String[] args) {
        SpringApplication.run(MyLauncher.class);
    }
}
