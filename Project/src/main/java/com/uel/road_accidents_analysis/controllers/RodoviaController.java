package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.Rodovia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rodovias")
public class RodoviaController {

    @GetMapping
    public String getRodovias(Model model) {
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            List<Rodovia> rodovias = daoFactory.getRodoviaDAO().getAll();

            model.addAttribute("rodovias", rodovias);
            return "jsp/rodovias";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao buscar rodovias";
        }

    }

    @PostMapping("create")
    public String postRodovia() {
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            Rodovia rodovia = new Rodovia();
            rodovia.setNome("BD-666");
            rodovia.setUF("SP");
            daoFactory.getRodoviaDAO().insert(rodovia);

            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao criar rodovia";
        }

    }
}
