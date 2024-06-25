package org.example;

import Semantico.TabelaSimbolosSemantico;

import java.util.ArrayList;
import java.util.List;

public class Semantico {

    private List<TabelaSimbolosSemantico> lista = new ArrayList<>();

    public void AdicionarTokenSemantica(String lexema, String categoria, String tipo, int nivel, int numeroLinha) {
        TabelaSimbolosSemantico simboloTabela = new TabelaSimbolosSemantico();
        if (lista.isEmpty()) {
            simboloTabela.setTokenLexema(lexema);
            simboloTabela.setTokenCategoria(categoria);
            simboloTabela.setTokenTipo(tipo);
            simboloTabela.setTokenNivel(nivel);

            lista.add(simboloTabela);
        } else {
            if (verificaExisteLexema(lexema)) {
                throw new RuntimeException(
                        "\n" + "[Erro Semantico]" + "\n" +
                                "Lexema: " + lexema + "\n" +
                                "Linha do Código: " + numeroLinha + "\n" +
                                "A Variável já foi declarada!");
            } else {
                simboloTabela.setTokenLexema(lexema);
                simboloTabela.setTokenCategoria(categoria);
                simboloTabela.setTokenTipo(tipo);
                simboloTabela.setTokenNivel(nivel);

                lista.add(simboloTabela);
            }
        }
    }

    public void verificaVariavelDeclarada(String lexema, int numeroLinha) {
        for (TabelaSimbolosSemantico simbolo : lista) {
            String simboloLexema = simbolo.getTokenLexema();
            if (simboloLexema.toLowerCase().equals(lexema.toLowerCase())) {
                return;
            }
        }
        throw new RuntimeException(
                "\n" + "[Erro Semantico]" + "\n" +
                        "Lexema: " + lexema + "\n" +
                        "Linha do Código: " + numeroLinha + "\n" +
                        "A Variável precisa ser declarada!");
    }

    private Boolean verificaExisteLexema(String lexema) {
        for (TabelaSimbolosSemantico simbolo : lista) {
            String simboloLexema = simbolo.getTokenLexema();
            if (simboloLexema.toLowerCase().equals(lexema.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
