package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String caminhoArquivoEntrada = MainUtil.buscarArquivo();
        if (caminhoArquivoEntrada != null) {
            List<Integer> tokensEncontrados = Lexico.analiseLexica(caminhoArquivoEntrada);
            if (!tokensEncontrados.isEmpty()) {
                Sintatico.AnaliseSintatica(tokensEncontrados);
            } else {
              MainUtil.erroLexicaSemToken();
            }
        } else {
            MainUtil.erroArquivoNF();    
        }
    }
}