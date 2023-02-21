package com.example.Kuro.dtos;
import lombok.Data;


@Data
public class RequestData {

    private String method;
    private boolean call_client;
    private Body body;
}
