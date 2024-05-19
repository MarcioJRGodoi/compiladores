package org.example;

import java.io.File;

import javax.swing.JFileChooser;

public class MainUtil {
    // Função para selecionar o arquivo de entrada
    public static String buscarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            if (arquivoSelecionado.getName().toLowerCase().endsWith(".txt")) {
                return arquivoSelecionado.getAbsolutePath();
            }
        }
        return null;
    }

    public static void erroArquivoNF() {
        System.out.println("O arquivo não é válido para iniciar o processo de Análise Léxica. Escolha um TXT Válido.");
    }

    public static void erroLexicaSemToken() {
        System.out.println("Não foi possível encontrar e gerar os tokens para iniciar o processo de Análise Sintática.");   
    }
}
