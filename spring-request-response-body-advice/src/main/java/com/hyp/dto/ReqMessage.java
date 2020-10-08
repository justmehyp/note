package com.hyp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReqMessage<T> {

    @NotNull
    private String reqSeq;

    @NotNull
    private T reqBody;
}
