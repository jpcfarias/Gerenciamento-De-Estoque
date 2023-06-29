package com.projeto.control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.projeto.model.Caneca;
import com.projeto.model.Curso;
import com.projeto.model.Filial;

/**
 * @author Leonardo, Felipe, Joao
*/

public class Controller{


    /**
     * @param nome
     * @param endereco
     * @return retona true se a filial for salva com sucesso
     */
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
            return false;
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

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
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
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar o arquivo");
                return false;
            }
        }
        else {
            System.out.println("Program doesn't have access to the file!!");
            return false;
        }

    }

    /**
     * @param cominhodoarquivo
     * @param combofilial
     * @return combobox com todas as filiais
     */
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
    
    /**
     * @param cominhodoarquivo
     * @param listafilial
     * @return retorna um array list com todas as filiais
     */
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

    /**
     * @param cominhodoarquivo
     * @param listaf
     * @return retorna um array list com todas as canecas
     * @throws FileNotFoundException
     */
    public ArrayList<Caneca> listaCaneca(String cominhodoarquivo, ArrayList<Caneca> listaf) throws FileNotFoundException{
        FileReader readerf = new FileReader("caneca.json");
        JsonArray jsonArrayf = (JsonArray) JsonParser.parseReader(readerf);
        
        for (JsonElement jsonElement : jsonArrayf){
            Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
            listaf.add(cadastro1);
        }
        return listaf;
    }

    /**
     * @param cominhodoarquivo
     * @param listav
     * @return retorna um array list com todas os cursos
     * @throws FileNotFoundException
     */
    public ArrayList<Curso> listaCurso(String cominhodoarquivo, ArrayList<Curso> listav) throws FileNotFoundException{
        FileReader readerv = new FileReader("curso.json");
        JsonArray jsonArrayv = (JsonArray) JsonParser.parseReader(readerv);
        
        for (JsonElement jsonElement : jsonArrayv){
            Curso cadastro2 = new Gson().fromJson(jsonElement, Curso.class);
            listav.add(cadastro2);
        }
        return listav;
    }
    
    
    /**
     * @param nome
     * @param descricao
     * @param codigo
     * @param quantidade
     * @param preco
     * @param combofilial
     * @return retona true se o curso for salvo com sucesso
     */
    public boolean salvarCurso(String nome, String descricao, int codigo, int quantidade, float preco, JComboBox<String> combofilial) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Curso cadastro = new Curso(nome, descricao, codigo, quantidade, preco, String.valueOf(combofilial.getSelectedItem()));
        
        String filePath = "curso.json";
        Path path = Paths.get(filePath);

        boolean exists = Files.exists(path);
        boolean notExists = Files.notExists(path);
        boolean isDir = Files.isDirectory(path);

        if (isDir) {
            System.out.println("File is a Directory");
            return false;
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
                return false;
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
                return false;
            }
        }
        else {
            System.out.println("Program doesn't have access to the file!!");
            return false;
        }
        ArrayList<Filial> listafilial = new ArrayList<Filial>();
        listafilial = listaFilial("filiais.json", listafilial);
        for (int i = 0; i < listafilial.size(); i++) {
            if(listafilial.get(i).getNome().equals(combofilial.getSelectedItem())){
                listafilial.get(i).setNumerodeprodutos(listafilial.get(i).getNumerodeprodutos() + quantidade);
                listafilial.get(i).addCursoNaFilial(cadastro);
            }
        }
        System.out.println(listafilial);
        String updatedJsonString = gson.toJson(listafilial);
        try {
            Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    
    /**
     * @param nome
     * @param descricao
     * @param codigo
     * @param quantidade
     * @param preco
     * @param combofilial
     * @param peso
     * @return retona true se a caneca for salva com sucesso
     */
    public boolean salvarCaneca(String nome, String descricao, int codigo, int quantidade, float preco, JComboBox<String> combofilial, float peso) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Caneca cadastro = new Caneca(nome, descricao, codigo, quantidade, preco, String.valueOf(combofilial.getSelectedItem()),peso);

        String filePath = "caneca.json";
        Path path = Paths.get(filePath);

        boolean exists = Files.exists(path);
        boolean notExists = Files.notExists(path);
        boolean isDir = Files.isDirectory(path);

        if (isDir) {
            System.out.println("File is a Directory");
            return false;
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
                return false;
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
                return false;
            }
        }
        else {
            System.out.println("Program doesn't have access to the file!!");
            return false;
        }
        ArrayList<Filial> listafilial = new ArrayList<Filial>();
        listafilial = listaFilial("filiais.json", listafilial);
        for (int i = 0; i < listafilial.size(); i++) {
            if(listafilial.get(i).getNome().equals(combofilial.getSelectedItem())){
                listafilial.get(i).setNumerodeprodutos(listafilial.get(i).getNumerodeprodutos() + quantidade);
                listafilial.get(i).addCanecaNaFilial(cadastro);
            }
        }
        System.out.println(listafilial);
        String updatedJsonString = gson.toJson(listafilial);
        try {
            Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    /**
     * @param buttonnome
     * @param textonome
     * @param buttonendereco
     * @param textoendereco
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void removerFilial(JRadioButton buttonnome, String textonome, JRadioButton buttonendereco, String textoendereco ) throws FileNotFoundException, Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileReader reader = new FileReader("filiais.json");
        JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
        java.util.List<Filial> listacadastro = new ArrayList<Filial>();
        
        for (JsonElement jsonElement : jsonArray){
            Filial cadastro1 = new Gson().fromJson(jsonElement, Filial.class);
            listacadastro.add(cadastro1);
        }
        int aux = 0;

        if(buttonnome.isSelected() == true){
            String nome = textonome;
            for (int i = 0; i < listacadastro.size(); i++) {
                if (String.valueOf(listacadastro.get(i).getNome()).equals(nome)) {
                    listacadastro.remove(i);
                    aux = 1;
                    break;
                }
            }
        }

        if(buttonendereco.isSelected() == true){
            String codigo = textoendereco;
            for (int i = 0; i < listacadastro.size(); i++) {
                if (String.valueOf(listacadastro.get(i).getEndereco()).equals(codigo)) {
                    listacadastro.remove(i);
                    aux = 1;
                    break;
                }
            }
        }

        if(aux == 0){
            throw new Exception("O item nao existe");
        }

        String updatedJsonString = gson.toJson(listacadastro);
        Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());

    }

    /**
     * @param buttonnome
     * @param textonome
     * @param buttoncodigo
     * @param textocodigo
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void removerProduto(JRadioButton buttonnome, String textonome, JRadioButton buttoncodigo, String textocodigo)throws FileNotFoundException, Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        FileReader reader = new FileReader("caneca.json");
        JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
        java.util.List<Caneca> listacadastro = new ArrayList<Caneca>();

        FileReader readerv = new FileReader("curso.json");
        JsonArray jsonArrayv = (JsonArray) JsonParser.parseReader(readerv);
        java.util.List<Curso> listacadastrov = new ArrayList<Curso>();
        
        for (JsonElement jsonElement : jsonArrayv){
            Curso cadastro2 = new Gson().fromJson(jsonElement, Curso.class);
            listacadastrov.add(cadastro2);
        }
        
        for (JsonElement jsonElement : jsonArray){
            Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
            listacadastro.add(cadastro1);
        }

        int aux = 0;

        if(buttonnome.isSelected() == true){
            String nome = textonome;
            for (int i = 0; i < listacadastro.size(); i++) {
                if (String.valueOf(listacadastro.get(i).getNome()).equals(nome)) {
                    listacadastro.remove(i);
                    aux = 1;
                    break;
                }
            }
            for (int i = 0; i < listacadastrov.size(); i++) {
                if (String.valueOf(listacadastrov.get(i).getNome()).equals(nome)) {
                    listacadastrov.remove(i);
                    aux = 1;
                    break;
                }
            }
        }

        if(buttoncodigo.isSelected() == true){
            String codigo = textocodigo;
            for (int i = 0; i < listacadastro.size(); i++) {
                if (String.valueOf(listacadastro.get(i).getCodigo()).equals(codigo)) {
                    listacadastro.remove(i);
                    aux = 1;
                    break;
                }
            }
            for (int i = 0; i < listacadastrov.size(); i++) {
                if (String.valueOf(listacadastrov.get(i).getCodigo()).equals(codigo)) {
                    listacadastrov.remove(i);
                    aux = 1;
                    break;
                }
            }
        }

        if(aux == 0){
            throw new Exception("O item nao existe");
        }

        String updatedJsonString = gson.toJson(listacadastro);
        Files.write(Paths.get("caneca.json"), updatedJsonString.getBytes());

        String updatedJsonStringv = gson.toJson(listacadastrov);
        Files.write(Paths.get("curso.json"), updatedJsonStringv.getBytes());
            
    }

    /**
     * @param listaf
     * @param textonome
     * @param textodescricao
     * @param textocodigo
     * @param textoquantidade
     * @param textopreco
     * @param textopeso
     */
    public void updateProdutoCaneca(JList<Caneca> listaf, String textonome, String textodescricao, String textocodigo, String textoquantidade, String textopreco, String textopeso){
            Caneca p = listaf.getSelectedValue();
            if(textonome != null && !textonome.isEmpty()){
                p.setNome(textonome);
            }
            if (textodescricao != null && !textodescricao.isEmpty()) {
                p.setDescricao(textodescricao);
            }
            if (textocodigo != null && !textocodigo.isEmpty()) {
                int codigo = Integer.parseInt(textocodigo);
                p.setCodigo(codigo);
            }
            if (textoquantidade != null && !textoquantidade.isEmpty()) {
                int quantidade = Integer.parseInt(textoquantidade);
                p.setQuantidade(quantidade);
            }
            if (textopreco != null && !textopreco.isEmpty()) {
                float preco = Float.parseFloat(textopreco);
                p.setPreco(preco); 
            }
            if (textopeso != null && !textopeso.isEmpty()) {
                float peso = Float.parseFloat(textopeso);
                p.setPeso(peso);   
            }
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileReader reader = new FileReader("caneca.json");
                JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
                java.util.List<Caneca> listacadastro = new ArrayList<Caneca>();
                for (JsonElement jsonElement : jsonArray){
                    Caneca cadastro1 = new Gson().fromJson(jsonElement, Caneca.class);
                    listacadastro.add(cadastro1);
                }
                listacadastro.set(listaf.getSelectedIndex(), p);
                String updatedJsonString = gson.toJson(listacadastro);
                Files.write(Paths.get("caneca.json"), updatedJsonString.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    /**
     * @param listav
     * @param textonome
     * @param textodescricao
     * @param textocodigo
     * @param textoquantidade
     * @param textopreco
     * @param textoplano
     */
    public void updateProdutoCurso(JList<Curso> listav, String textonome, String textodescricao, String textocodigo, String textoquantidade, String textopreco, String textoplano){
        Curso p = listav.getSelectedValue();
        if(textonome != null && !textonome.isEmpty()){
            p.setNome(textonome);
        }
        if (textodescricao != null && !textodescricao.isEmpty()) {
            p.setDescricao(textodescricao);
        }
        if (textocodigo != null && !textocodigo.isEmpty()) {
            int codigo = Integer.parseInt(textocodigo);
            p.setCodigo(codigo);
        }
        if (textoquantidade != null && !textoquantidade.isEmpty()) {
            int quantidade = Integer.parseInt(textoquantidade);
            p.setQuantidade(quantidade);
        }
        if (textopreco != null && !textopreco.isEmpty()) {
            float preco = Float.parseFloat(textopreco);
            p.setPreco(preco); 
        }
        if (textoplano != null && !textoplano.isEmpty()) {
            String plano = textoplano;
            p.setPlano(plano);   
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader("curso.json");
            JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
            java.util.List<Curso> listacadastro = new ArrayList<Curso>();
            for (JsonElement jsonElement : jsonArray){
                Curso cadastro1 = new Gson().fromJson(jsonElement, Curso.class);
                listacadastro.add(cadastro1);
            }
            listacadastro.set(listav.getSelectedIndex(), p);
            String updatedJsonString = gson.toJson(listacadastro);
            Files.write(Paths.get("curso.json"), updatedJsonString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param listaFilial
     * @param textonome
     * @param textoendereco
     */
    public void updateFilial(JList<Filial> listaFilial, String textonome, String textoendereco){
        Filial p = listaFilial.getSelectedValue();
        if(textonome != null && !textonome.isEmpty()){
            p.setNome(textonome);
        }
        if (textoendereco != null && !textoendereco.isEmpty()) {
            p.setEndereco(textoendereco);
        }
        
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader("filiais.json");
            JsonArray jsonArray = (JsonArray) JsonParser.parseReader(reader);
            java.util.List<Filial> listacadastro = new ArrayList<Filial>();
            for (JsonElement jsonElement : jsonArray){
                Filial cadastro1 = new Gson().fromJson(jsonElement, Filial.class);
                listacadastro.add(cadastro1);
            }
            listacadastro.set(listaFilial.getSelectedIndex(), p);
            String updatedJsonString = gson.toJson(listacadastro);
            Files.write(Paths.get("filiais.json"), updatedJsonString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
