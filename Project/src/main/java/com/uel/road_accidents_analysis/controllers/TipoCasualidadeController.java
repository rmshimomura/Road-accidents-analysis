package com.uel.road_accidents_analysis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipo_casualidade")
public class TipoCasualidadeController {

    @GetMapping
    public String getTipoCasualidades() {
        return "Tipo de Casualidades";
    }
}
