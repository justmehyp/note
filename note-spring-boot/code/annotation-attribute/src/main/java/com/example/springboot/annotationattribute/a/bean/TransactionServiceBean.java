package com.example.springboot.annotationattribute.a.bean;

import com.example.springboot.annotationattribute.a.TransactionService;

@TransactionService
public class TransactionServiceBean {
    public void save() {
        System.out.println("save...");
    }
}
