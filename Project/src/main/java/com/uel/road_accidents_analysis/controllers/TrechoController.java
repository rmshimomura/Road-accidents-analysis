package com.uel.road_accidents_analysis.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trecho")
public class TrechoController {

    @GetMapping
    public String getTrechos() {
        return "Trechos";
    }

}
