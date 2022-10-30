package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.Rodovia;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Controller
@RequestMapping("/upload")
public class UploadFilesController {

        @GetMapping
        public String index() {
            return "jsp/uploadFile";
        }


        private void parseRodoviaFile(MultipartFile file) {
            try {
                InputStream inputStream = file.getInputStream();
                Scanner scanner = new Scanner(inputStream);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    String uf;
                    uf = line.substring(0, line.indexOf(';'));
                    line = line.substring(line.indexOf(';') + 1);

                    String nome_rodovia;
                    nome_rodovia = line.substring(0, line.indexOf(';'));

                    line = line.substring(line.indexOf(';') + 1);

                    Rodovia rodovia = new Rodovia();
                    rodovia.setUF(uf);
                    rodovia.setNome(nome_rodovia);

                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                        daoFactory.getRodoviaDAO().insert(rodovia);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @PostMapping("/analisar")
        public String analisar(@RequestParam("arquivo") MultipartFile file, @RequestParam("tipoArquivo") String tipoArquivo) {

            if(tipoArquivo.equals("rodovias")) {
                parseRodoviaFile(file);
            }
            else{

            }

            return "jsp/uploadFile";

        }

}
