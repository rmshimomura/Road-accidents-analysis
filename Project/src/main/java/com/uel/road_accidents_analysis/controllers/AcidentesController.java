package com.uel.road_accidents_analysis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acidentes")
public class AcidentesController {

        @GetMapping
        public String getAcidentes() {
                return "jsp/acidentes";
        }

}
