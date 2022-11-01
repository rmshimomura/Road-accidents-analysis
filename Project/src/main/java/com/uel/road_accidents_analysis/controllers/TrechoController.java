package com.uel.road_accidents_analysis.controllers;


import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.Trecho;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/trechos")
public class TrechoController {

    @GetMapping
    public String getTrechos(Model model) {
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            List<Trecho> trechos = daoFactory.getTrechoDAO().getAll();

            model.addAttribute("trechos", trechos);
            return "jsp/trechos";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao buscar rodovias";
        }

    }

}
