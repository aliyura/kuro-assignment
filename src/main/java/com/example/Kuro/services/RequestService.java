package com.example.Kuro.services;

import com.example.Kuro.dtos.Body;
import com.example.Kuro.dtos.Request;
import com.example.Kuro.dtos.Validation;
import com.example.Kuro.dtos.result.Result;
import com.example.Kuro.dtos.result.ResultBody;
import com.example.Kuro.dtos.result.Static;
import com.example.Kuro.utils.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RequestService {


    private final Util util;

    public Static formatJson(Validation validation) {
        Object vData = validation.getValidation().get(0);
        Request request = util.getObjectMapper().convertValue(vData, Request.class);
        Body body= request.getRequest().getBody();

        ResultBody resultBody = new ResultBody();
        Map<String, String> multiComplexKV= new HashMap<>();

        body.getData().forEach((reBody)-> {

            switch (reBody.getKey()) {
                case "state.body.data":
                    resultBody.setData(reBody.getValue());
                    break;
                case "state.body.party[]":
                    List pList = resultBody.getParty() != null ? resultBody.getParty() : new ArrayList();
                    pList.add(reBody.getValue());
                    resultBody.setParty(pList);
                    break;
                case "state.body.complex_list[0][amount]":
                    List cList = resultBody.getComplex_list() != null ? resultBody.getComplex_list() : new ArrayList();
                    Map<String, String> complexKV = new HashMap<>();
                    complexKV.put("amount", reBody.getValue());
                    cList.add(complexKV);
                    resultBody.setComplex_list(cList);
                    break;
                case "state.body.complex_list[1][key]":
                    List cList1 = resultBody.getComplex_list() != null ? resultBody.getComplex_list() : new ArrayList();
                    multiComplexKV.put("key", reBody.getValue());
                    cList1.add(multiComplexKV);
                    resultBody.setComplex_list(cList1);
                    break;
                case "state.body.complex_list[1][tech]":
                    multiComplexKV.put("tech", reBody.getValue());
                    break;
            }
        });
        return Static.builder().statics(Result.builder().body(resultBody).build()).build();
    }
}
