package com.uel.road_accidents_analysis.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/trechos")
public class TrechoController {

    @GetMapping
    public String getTrechos(Model model) {

        return "jsp/trechos";
    }

}
