package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Named
@RequestScoped
@Controller
public class UploadFilesController implements Serializable {
    private List<VeiculoAcidenteSemCasualidade> createVeiculosAcidenteSc(Long id, String [] contents){

        List<VeiculoAcidenteSemCasualidade> veiculos = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            VeiculoAcidenteSemCasualidade veiculo = new VeiculoAcidenteSemCasualidade();
            veiculo.setIdAcidenteSemCasualidade(id);
            veiculo.setIdVeiculo((long) (i+1));

            if(contents[i+8].equals("")){
                veiculo.setQuantidade(0);
            }else{
                veiculo.setQuantidade(Integer.parseInt(contents[i+8]));
            }

            veiculos.add(veiculo);
        }


        return veiculos;
    }

    private List<VeiculoAcidenteComCasualidade> createVeiculosAcidenteCc(Long id, String [] contents) {
        //create list of 10 veiculos
        List<VeiculoAcidenteComCasualidade> veiculos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VeiculoAcidenteComCasualidade veiculo = new VeiculoAcidenteComCasualidade();
            veiculo.setIdAcidenteComCasualidade(id);
            veiculo.setIdVeiculo((long) (i + 1));

            if (contents[i + 8].equals("")) {
                veiculo.setQuantidade(0);
            } else {
                veiculo.setQuantidade(Integer.parseInt(contents[i + 8]));
            }

            veiculos.add(veiculo);
        }

        return veiculos;
    }

    private List<CasualidadeNoAcidente> createCasualidadesAcidente(Long id, String [] contents){
        List<CasualidadeNoAcidente> casualidades = new ArrayList<>();

        String [] fixedContents = new String[23];
        for(int i = 0; i < 23; i++){
            if(i < contents.length){
                fixedContents[i] = contents[i];
            }else{
                fixedContents[i] = "0";
            }
        }

        for(int i = 0; i < 5; i++){
            CasualidadeNoAcidente casualidade = new CasualidadeNoAcidente();
            casualidade.setIdAcidente((id));
            casualidade.setIdTipoCasualidade((long) (i+1));

            if(fixedContents[i+18].equals("")){
                casualidade.setQuantidade(0);
            }else{
                casualidade.setQuantidade(Integer.parseInt(fixedContents[i+18]));
            }

            casualidades.add(casualidade);
        }

        return casualidades;
    }

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

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String contents[] = line.split(";");

                if(contents[0].equals("data")){
                    continue;
                }

                //UF and nome rodovia comes in BR/SP get before and after /
                String[] trecho_content = contents[5].split("/");

                if(contents[3].toLowerCase().contains("sem")){
                    AcidenteSemCasualidade acidenteSemCasualidade = new AcidenteSemCasualidade();
                    acidenteSemCasualidade.setData(new SimpleDateFormat("dd/MM/yyyy").parse(contents[0]));
                    acidenteSemCasualidade.setHorario(new Time(new SimpleDateFormat("HH:mm:ss").parse(contents[1]).getTime()));
                    acidenteSemCasualidade.setKm(Double.parseDouble(contents[4].replace(",", ".")));
                    acidenteSemCasualidade.setSentido(contents[6]);
                    acidenteSemCasualidade.setTipo(contents[7]);

                    try (DAOFactory daoFactory = DAOFactory.getInstance()){
                        Rodovia rodovia = daoFactory.getRodoviaDAO().getRodoviaByUfAndName(trecho_content[1], trecho_content[0]).get(0);
                        Trecho trecho = daoFactory.getTrechoDAO().getTrechoByRodoviaKmAndData(rodovia.getId(), acidenteSemCasualidade.getKm(), acidenteSemCasualidade.getData());
                        acidenteSemCasualidade.setIdTrecho(trecho.getId());
                        daoFactory.getAcidenteSemCasualidadeDAO().insert(acidenteSemCasualidade);

                        Long id = daoFactory.getAcidenteSemCasualidadeDAO().getByTrechoHorarioDataAndKm(trecho.getId(), acidenteSemCasualidade.getData(), acidenteSemCasualidade.getHorario(), acidenteSemCasualidade.getKm()).getId();
                        List<VeiculoAcidenteSemCasualidade> veiculos = createVeiculosAcidenteSc(id, contents);

                        for(VeiculoAcidenteSemCasualidade veiculo : veiculos){
                            daoFactory.getVeiculoAcidenteSemCasualidadeDAO().insert(veiculo);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                else{
                    AcidenteComCasualidade acidenteComCasualidade = new AcidenteComCasualidade();
                    acidenteComCasualidade.setData(new SimpleDateFormat("dd/MM/yyyy").parse(contents[0]));
                    acidenteComCasualidade.setHorario(new Time(new SimpleDateFormat("HH:mm:ss").parse(contents[1]).getTime()));
                    acidenteComCasualidade.setKm(Double.parseDouble(contents[4].replace(",", ".")));
                    acidenteComCasualidade.setSentido(contents[6]);
                    acidenteComCasualidade.setTipo(contents[7]);

                    try (DAOFactory daoFactory = DAOFactory.getInstance()){
                        Rodovia rodovia = daoFactory.getRodoviaDAO().getRodoviaByUfAndName(trecho_content[1], trecho_content[0]).get(0);
                        Trecho trecho = daoFactory.getTrechoDAO().getTrechoByRodoviaKmAndData(rodovia.getId(), acidenteComCasualidade.getKm(), acidenteComCasualidade.getData());
                        acidenteComCasualidade.setIdTrecho(trecho.getId());
                        daoFactory.getAcidenteComCasualidadeDAO().insert(acidenteComCasualidade);

                        Long id = daoFactory.getAcidenteComCasualidadeDAO().getByTrechoHorarioDataAndKm(trecho.getId(), acidenteComCasualidade.getData(), acidenteComCasualidade.getHorario(), acidenteComCasualidade.getKm()).getId();
                        List<VeiculoAcidenteComCasualidade> veiculos = createVeiculosAcidenteCc(id, contents);

                        for(VeiculoAcidenteComCasualidade veiculo : veiculos){
                            daoFactory.getVeiculoAcidenteComCasualidadeDAO().insert(veiculo);
                        }

                        List<CasualidadeNoAcidente> casualidades = createCasualidadesAcidente(id, contents);
                        for(CasualidadeNoAcidente casualidade : casualidades){
                            daoFactory.getCasualidadeNoAcidenteDAO().insert(casualidade);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        } catch (Exception e) {
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
                    trecho.setIdRodovia(daoFactory.getRodoviaDAO().getRodoviaByUfAndName(rodovia.getUF(), rodovia.getNome()).get(0).getId());
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
