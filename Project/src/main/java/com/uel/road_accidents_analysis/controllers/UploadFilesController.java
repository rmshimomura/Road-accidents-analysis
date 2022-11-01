package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.Trecho;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;


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
                    try (DAOFactory daoFactory = DAOFactory.getInstance()) {
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

                        String kmInicial, kmFinal;
                        kmInicial = line.substring(0, line.indexOf(';'));
                        kmInicial = kmInicial.replace(',', '.');
                        line = line.substring(line.indexOf(';') + 1);
                        kmFinal = line.substring(0, line.indexOf(';'));
                        kmFinal = kmFinal.replace(',', '.');
                        line = line.substring(line.indexOf(';') + 1);
                        line = line.substring(line.indexOf(';') + 1);
                        String dataAvaliacao;
                        dataAvaliacao = line.substring(0, line.indexOf(';'));
                        line = line.substring(line.indexOf(';') + 1);
                        line = line.substring(line.indexOf(';') + 1);
                        line = line.substring(line.indexOf(';') + 1);
                        line = line.substring(line.indexOf(';') + 1);

                        String ICC, ICP, ICM;
                        ICC = line.substring(0, line.indexOf(';'));
                        ICC = ICC.replace(',', '.');
                        line = line.substring(line.indexOf(';') + 1);
                        ICP = line.substring(0, line.indexOf(';'));
                        ICP = ICP.replace(',', '.');
                        line = line.substring(line.indexOf(';') + 1);
                        ICM = line;
                        ICM = ICM.replace(',', '.');

                        Trecho trecho = new Trecho();
                        double dkmInicial, dkmFinal;
                        dkmInicial = Double.parseDouble(kmInicial);
                        dkmFinal = Double.parseDouble(kmFinal);
                        if(dkmInicial > dkmFinal) {
                            double aux = dkmInicial;
                            dkmInicial = dkmFinal;
                            dkmFinal = aux;
                        }
                        trecho.setKmInicial(dkmInicial);
                        trecho.setKmFinal(dkmFinal);
                        trecho.setICC(Double.parseDouble(ICC));
                        trecho.setICM(Double.parseDouble(ICM));
                        trecho.setICP(Double.parseDouble(ICP));
                        trecho.setDataAvaliacao(new SimpleDateFormat("dd/MM/yyyy").parse(dataAvaliacao));

                        try {
                            daoFactory.getRodoviaDAO().insert(rodovia);
                        } catch (Exception e) {
                            System.out.println("Rodovia já cadastrada");
                        }

                        trecho.setIdRodovia(daoFactory.getRodoviaDAO().getIdByInfo(rodovia));
                        daoFactory.getTrechoDAO().insert(trecho);
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
