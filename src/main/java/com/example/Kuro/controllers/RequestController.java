package com.example.Kuro.controllers;
import com.example.Kuro.dtos.Validation;
import com.example.Kuro.dtos.result.Static;
import com.example.Kuro.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/decode")
    public Static getFormattedJson(@RequestBody Validation validation) {
        return requestService.formatJson(validation);
    }

}