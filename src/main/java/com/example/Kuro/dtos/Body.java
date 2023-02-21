package com.example.Kuro.dtos;
import lombok.Data;

import java.util.HashMap;
import java.util.List;


@Data
public class Body {
    private String mode;
    private List<KeyValue> data;
    private String raw;
    private String option;
}
