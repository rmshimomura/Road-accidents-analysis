package com.uel.road_accidents_analysis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/casualidades")
public class TipoCasualidadeController {

    @GetMapping
    public String getTipoCasualidades() {
        return "jsp/casualidades";
    }
}
