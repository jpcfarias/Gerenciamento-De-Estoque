package com.projeto.control;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.model.Caneca;
import com.projeto.model.Curso;
import com.projeto.model.Filial;

public class Controller{

    public boolean salvarFilial(String nome, String endereco){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Filial cadastro = new Filial(nome, endereco);

        String filePath = "filiais.json";
        Path path = Paths.get(filePath);

        boolean exists = Files.exists(path);
        boolean notExists = Files.notExists(path);
        boolean isDir = Files.isDirectory(path);

        if (isDir) {
            System.out.println("File is a Directory");
        }
        else if (exists) {
            System.out.println("Arquivo existe");
            try {
                java.util.List<Filial> listafilial = new ArrayList<Filial>();
                
                FileReader reader = new FileReader(filePath);
                JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);

                for (JsonElement jsonElement : jsonArray){
                    Filial cadastro1 = new Gson().fromJson(jsonElement, Filial.class);
                    listafilial.add(cadastro1);
                }
                listafilial.add(new Filial(nome, endereco));
                String updatedJsonString = gson.toJson(listafilial);
                Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (notExists) {
            System.out.println("Arquivo nao existe");
            try {
                java.util.List<Filial> cadastrolista = new ArrayList<Filial>();
                cadastrolista.add(cadastro);

                String jsonUser = gson.toJson(cadastrolista);
        
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonUser);
                    fileWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar o arquivo");
            }
        }
        else {
            System.out.println("Program doesn't have access to the file!!");
        }

        return true;
    }

    public JComboBox<String> comboFilial(String cominhodoarquivo,JComboBox<String> combofilial){
        try{
            FileReader readerfilial = new FileReader(cominhodoarquivo);
            JsonArray jsonArrayfilial = (JsonArray) JsonParser.parseReader(readerfilial);
        
            for (JsonElement jsonElement : jsonArrayfilial){
                Filial cadastrofilial = new Gson().fromJson(jsonElement, Filial.class);
                combofilial.addItem(cadastrofilial.getNome());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return combofilial;
    }
    
    public ArrayList<Filial> listaFilial(String cominhodoarquivo,ArrayList<Filial> listafilial){
        try{
            FileReader readerfilial = new FileReader(cominhodoarquivo);
            JsonArray jsonArrayfilial = (JsonArray) JsonParser.parseReader(readerfilial);
        
            for (JsonElement jsonElement : jsonArrayfilial){
                Filial cadastrofilial = new Gson().fromJson(jsonElement, Filial.class);
                listafilial.add(cadastrofilial);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listafilial;
    }

    public boolean salvarCurso(Gson gson, String nome, String descricao, int codigo, int quantidade, float preco, JComboBox<String> combofilial) {
        Curso cadastro = new Curso(nome, descricao, codigo, quantidade, preco, String.valueOf(combofilial.getSelectedItem()));
        
        String filePath = "curso.json";
        Path path = Paths.get(filePath);

        boolean exists = Files.exists(path);
        boolean notExists = Files.notExists(path);
        boolean isDir = Files.isDirectory(path);

        if (isDir) {
            System.out.println("File is a Directory");
        }
        else if (exists) {
            System.out.println("Arquivo existe");
            
            try {
                FileReader reader = new FileReader("curso.json");
                JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                java.util.List<Curso> listacadastro = new ArrayList<Curso>();
                
                for (JsonElement jsonElement : jsonArray){
                    Curso cadastro1 = new Gson().fromJson(jsonElement, Curso.class);
                    listacadastro.add(cadastro1);
                }
                
                listacadastro.add(cadastro);

                String updatedJsonString = gson.toJson(listacadastro);
                Files.write(Paths.get("curso.json"), updatedJsonString.getBytes());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (notExists) {
            System.out.println("Arquivo nao existe");
            
            try {
                java.util.List<Curso> cadastrolista = new ArrayList<Curso>();
                
                cadastrolista.add(cadastro);
                
                String jsonUser = gson.toJson(cadastrolista);
                
                FileWriter fileWriter = new FileWriter("curso.json");
                fileWriter.write(jsonUser);
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null ,"Ocorreu um erro ao gravar o arquivo");
            }
        }
        else {
            System.out.println("Program doesn't have access to the file!!");
        }
        ArrayList<Filial> listafilial = new ArrayList<Filial>();
        listafilial = listaFilial("filiais.json", listafilial);
        for (int i = 0; i < listafilial.size(); i++) {
            if(listafilial.get(i).getNome().equals(combofilial.getSelectedItem())){
                listafilial.get(i).setNumerodeprodutos(listafilial.get(i).getNumerodeprodutos() + quantidade);
            }
        }
        System.out.println(listafilial);
        String updatedJsonString = gson.toJson(listafilial);
        try {
            Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
        return true;
    }
    
    public boolean salvarCaneca(Gson gson, String nome, String descricao, int codigo, int quantidade, float preco, JComboBox<String> combofilial, float peso) {
        Caneca cadastro = new Caneca(nome, descricao, codigo, quantidade, preco, String.valueOf(combofilial.getSelectedItem()),peso);

        String filePath = "caneca.json";
        Path path = Paths.get(filePath);

        boolean exists = Files.exists(path);
        boolean notExists = Files.notExists(path);
        boolean isDir = Files.isDirectory(path);

        if (isDir) {
            System.out.println("File is a Directory");
        }
        else if (exists) {
            System.out.println("Arquivo existe");
            
            try {
                FileReader reader = new FileReader("caneca.json");
                JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                java.util.List<Caneca> listacadastro = new ArrayList<Caneca>();
                
                for (JsonElement jsonElement : jsonArray){
                    Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
                    listacadastro.add(cadastro1);
                }
                
                listacadastro.add(cadastro);
                
                String updatedJsonString = gson.toJson(listacadastro);
                Files.write(Paths.get("caneca.json"), updatedJsonString.getBytes());
                System.out.println(listacadastro);
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (notExists) {
            System.out.println("Arquivo nao existe");
            
            try {
                java.util.List<Caneca> cadastrolista = new ArrayList<Caneca>();
                
                cadastrolista.add(cadastro);
                
                String jsonUser = gson.toJson(cadastrolista);
                
                FileWriter fileWriter = new FileWriter("caneca.json");
                fileWriter.write(jsonUser);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar o arquivo");
            }
        }
        else {
            System.out.println("Program doesn't have access to the file!!");
        }
        ArrayList<Filial> listafilial = new ArrayList<Filial>();
        listafilial = listaFilial("filiais.json", listafilial);
        for (int i = 0; i < listafilial.size(); i++) {
            if(listafilial.get(i).getNome().equals(combofilial.getSelectedItem())){
                listafilial.get(i).setNumerodeprodutos(listafilial.get(i).getNumerodeprodutos() + quantidade);
            }
        }
        System.out.println(listafilial);
        String updatedJsonString = gson.toJson(listafilial);
        try {
            Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
        return true;
    }
}
