package com.hyp.controller;

import com.hyp.dto.ReqMessage;
import com.hyp.dto.RespMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String sayHello(@RequestParam String name) {
        return "hello " + name;
    }


    @RequestMapping("/hello2")
    public RespMessage<User> sayHello2(@Valid @RequestBody ReqMessage<User> req) {
        RespMessage<User> respMessage = RespMessage.from(req);
        respMessage.setRespBody(req.getReqBody());
        return respMessage;
    }

    static class User {

        @NotNull(message = "name不能为空")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

