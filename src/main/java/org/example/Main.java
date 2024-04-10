package org.example;

import enuns.CodigoToken;
import lexico.AnalisadorLexico;
import regerx.Regex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        String main = "../codigosParaLer/Main.txt";
        try {
            FileInputStream stream = new FileInputStream("/home/marcio/Compiladores/texto");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            StringBuilder conteudo = new StringBuilder();
            String linha = br.readLine();
            while(linha != null) {
                conteudo.append(linha).append("\n");
                linha = br.readLine();
            }
            br.close();

            AnalisadorLexico a = new AnalisadorLexico(
                    new ArrayList<String>(),
                    new ArrayList<Integer>(),
                    "",
                    conteudo.toString()
            );
            a.FazerAsParada();



        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        

    }
}