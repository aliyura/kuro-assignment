package com.example.Kuro.dtos.result;


import lombok.Data;

import java.util.List;

@Data
public class ResultBody {

    private String data;
    private List party;

    private List complex_list;

}
