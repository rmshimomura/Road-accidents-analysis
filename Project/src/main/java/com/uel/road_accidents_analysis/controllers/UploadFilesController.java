package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.Trecho;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Scanner;


@Named
@RequestScoped
@Controller
public class UploadFilesController implements Serializable {

    public void parseAcidente(FileUploadEvent event){
        UploadedFile file = event.getFile();

        FacesMessage message;

        if (file != null) {
            message = new FacesMessage(file.getFileName() + " uploaded.");
        } else {
            message = new FacesMessage("Upload failed.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);

        try {
            assert file != null;
            InputStream inputStream = file.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            boolean isFirstLine = true;

            String headerLine = null;

            while (scanner.hasNextLine() && headerLine == null) {
                String line = scanner.nextLine();
                if (line.charAt(1) == ';' || line.isBlank()) {
                    continue;
                }

                headerLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parseFile(FileUploadEvent event) {

        UploadedFile file = event.getFile();

        FacesMessage message;

        if (file != null) {
            message = new FacesMessage(file.getFileName() + " uploaded.");
        } else {
            message = new FacesMessage("Upload failed.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);

        try {
            assert file != null;
            InputStream inputStream = file.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            boolean isFirstLine = true;

            String headerLine = null;

            while (scanner.hasNextLine() && headerLine == null) {
                String line = scanner.nextLine();
                if (line.charAt(1) == ';' || line.isBlank()) {
                    continue;
                }

                headerLine = line;
            }

            Boolean hasObservacao = headerLine.contains("Observação");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String contents[] = line.split(";");

                if (contents.length == 0) {
                    continue;
                }

                Rodovia rodovia = new Rodovia();
                rodovia.setUF(contents[0]);
                rodovia.setNome(contents[1]);

                try (DAOFactory daoFactory = DAOFactory.getInstance()){
                    daoFactory.getRodoviaDAO().insert(rodovia);
                } catch (Exception e) {

                }

                Trecho trecho = new Trecho();
                double kmInicial = Double.parseDouble(contents[2].replace(",", "."));
                double kmFinal = Double.parseDouble(contents[3].replace(",", "."));
                if(kmInicial > kmFinal) {
                    trecho.setKmInicial(kmFinal);
                    trecho.setKmFinal(kmInicial);
                } else {
                    trecho.setKmInicial(kmInicial);
                    trecho.setKmFinal(kmFinal);
                }

                trecho.setDataAvaliacao(new SimpleDateFormat("dd/MM/yyyy").parse(contents[5]));

                if(hasObservacao == true) {
                    trecho.setICC(Double.parseDouble(contents[9].replace(",", ".")));
                    trecho.setICP(Double.parseDouble(contents[10].replace(",", ".")));
                    trecho.setICM(Double.parseDouble(contents[11].replace(",", ".")));
                }
                else {
                    trecho.setICC(Double.parseDouble(contents[8].replace(",", ".")));
                    trecho.setICP(Double.parseDouble(contents[9].replace(",", ".")));
                    trecho.setICM(Double.parseDouble(contents[10].replace(",", ".")));
                }

                try (DAOFactory daoFactory = DAOFactory.getInstance()){
                    trecho.setIdRodovia(daoFactory.getRodoviaDAO().getIdByInfo(rodovia));
                    daoFactory.getTrechoDAO().insert(trecho);
                } catch (Exception e) {

                }

            }


            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("File uploaded");
        FacesMessage message = new FacesMessage(event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
