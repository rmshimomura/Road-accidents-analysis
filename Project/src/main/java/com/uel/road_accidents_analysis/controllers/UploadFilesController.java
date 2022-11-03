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

            boolean firstLine = true;

            try (DAOFactory daoFactory = DAOFactory.getInstance()) {

                while (scanner.hasNextLine()) {

                    if (firstLine) {
                        firstLine = false;
                        scanner.nextLine();
                        continue;
                    }

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
                    if (dkmInicial > dkmFinal) {
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

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("File uploaded");
        FacesMessage message = new FacesMessage(event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
