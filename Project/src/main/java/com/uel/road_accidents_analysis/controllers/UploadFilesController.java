package com.uel.road_accidents_analysis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadFilesController {

        @GetMapping
        public String index() {
            return "jsp/uploadFile";
        }

        @PostMapping("/analisar")
        public String analisar(@RequestParam("arquivo") MultipartFile file, @RequestParam("tipoArquivo") String tipoArquivo) {

            // Print file info
            System.out.println("Nome do arquivo: " + file.getOriginalFilename());
            System.out.println("Tipo do arquivo: " + file.getContentType());
            System.out.println("Tamanho do arquivo: " + file.getSize());

            // Print file type
            System.out.println("Tipo do arquivo: " + tipoArquivo);



            return "jsp/uploadFile";

        }

}
