package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.DAOFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rodovia")
public class RodoviaController {

    @GetMapping
    public String getRodovias() {
        return "Rodovias";
    }

}
