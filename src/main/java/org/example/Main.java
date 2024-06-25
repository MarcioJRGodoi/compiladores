package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String caminhoArquivoEntrada = MainUtil.buscarArquivo();
        if (caminhoArquivoEntrada != null) {
            List<String> listaLexemas = new ArrayList<>();
            List<Integer> listaLinhas = new ArrayList<>();
            List<Integer> listaTokens = Lexico.analiseLexica(caminhoArquivoEntrada, listaLexemas, listaLinhas);
            if (!listaTokens.isEmpty()) {
                Sintatico.AnaliseSintatica(listaTokens, listaLexemas, listaLinhas);
            } else {
              MainUtil.erroLexicaSemToken();
            }
        } else {
            MainUtil.erroArquivoNF();    
        }
    }
}