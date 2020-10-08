package com.hyp.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class RespMessage<T> {

    private String reqSeq;

    private String respTime;

    private T respBody;

    public static <T> RespMessage<T> from(ReqMessage<T> req) {
        String reqSeq = req.getReqSeq();
        RespMessage<T> respMessage = new RespMessage<>();
        respMessage.setReqSeq(reqSeq);
        respMessage.setRespTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd HHmmss")));
        return respMessage;
    }
}
