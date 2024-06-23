package org.example;

import Semantico.TabelaSimbolosSemantico;
import enuns.CodigoToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnalisadorSemantico {

    private List<TabelaSimbolosSemantico> lista = new ArrayList<>();
    private List<CodigoToken> listaComparacao = new ArrayList<>();

    public void realizarAnaliseSemantica(List<Integer> tokensEncontrados) {
        List<CodigoToken> tokensConvertidos = converterTokens(tokensEncontrados);
        for (CodigoToken token : tokensConvertidos) {
            aplicarRegrasSemanticas(token);
        }
    }

    private List<CodigoToken> converterTokens(List<Integer> tokensEncontrados) {
        List<CodigoToken> tokensConvertidos = new ArrayList<>();
        for (Integer codigo : tokensEncontrados) {
            CodigoToken token = CodigoToken.BuscarTokenPorCodigo(codigo);
            tokensConvertidos.add(token);
        }
        return tokensConvertidos;
    }

    private void aplicarRegrasSemanticas(CodigoToken token) {
        regraSemantica100(token);
        regraSemantica101(token);
        regraSemantica102(token);
        regraSemantica103();
        regraSemantica104(token);
    }

    private void regraSemantica100(CodigoToken token) {
        TabelaSimbolosSemantico simboloTabela = new TabelaSimbolosSemantico();
        if (lista.isEmpty()) {
            simboloTabela.setTokenVariavel(token);
            lista.add(simboloTabela);
        } else {
            if (pesquisaNomeToken(token)) {
                throw new RuntimeException("[Erro Semantico] variavel Ambigua: " + token.getTokenString());
            } else {
                simboloTabela.setTokenVariavel(token);
                lista.add(simboloTabela);
            }
        }
    }

    private void regraSemantica101(CodigoToken token) {
        colocarTipoTodasVariaveisSemTipo(token);
    }

    private void regraSemantica102(CodigoToken token) {
        listaComparacao.add(token);
    }

    private void regraSemantica103() {
        verificaTipoListaComparacao();
    }

    private void regraSemantica104(CodigoToken token) {
        for (TabelaSimbolosSemantico simbolo : lista) {
            if (simbolo.getTokenVariavel().getTokenString().equals(token.getTokenString())) {
                return;
            }
        }
        throw new RuntimeException("[Erro Semantico] variavel precisa ser declarada: " + token.getTokenString());
    }

    private Boolean pesquisaNomeToken(CodigoToken token) {
        for (TabelaSimbolosSemantico simbolo : lista) {
            if (simbolo.getTokenVariavel().getTokenString().equals(token.getTokenString())) {
                return true;
            }
        }
        return false;
    }

    private void colocarTipoTodasVariaveisSemTipo(CodigoToken token) {
        for (TabelaSimbolosSemantico simbolo : lista) {
            if (simbolo.getTokenTipoVariavel() == null) {
                simbolo.setTokenTipoVariavel(token);
            }
        }
    }

    private void verificaTipoListaComparacao() {
        String tipo = "";
        String tipoAtual;
        for (CodigoToken token : listaComparacao) {
            if (buscaTipoToken(token) != null && tipo.isEmpty()) {
                tipo = Objects.requireNonNull(buscaTipoToken(token)).getTokenString();
            } else if (buscaTipoToken(token) != null) {
                if (!Objects.requireNonNull(buscaTipoToken(token)).getTokenString().equals(tipo)) {
                    throw new RuntimeException("[Erro Semantico] use apenas um tipo de variavel");
                }
            } else {
                tipoAtual = token.getTokenString();
                switch (tipoAtual) {
                    case "numeroInteiro":
                        tipoAtual = "integer";
                        break;
                    case "numeroFloat":
                        tipoAtual = "float";
                        break;
                    case "nomeDaString":
                        tipoAtual = "string";
                        break;
                    case "nomeDoChar":
                        tipoAtual = "char";
                        break;
                }
                if (tipoAtual.equals(tipo)) {
                    continue;
                } else if (!tipoAtual.equals(tipo)) {
                    throw new RuntimeException("[Erro Semantico] use apenas um tipo de variavel");
                } else {
                    throw new RuntimeException("[Erro Semantico] Variavel nula");
                }
            }
        }
        listaComparacao.clear();
    }

    private CodigoToken buscaTipoToken(CodigoToken token) {
        for (TabelaSimbolosSemantico simbolo : lista) {
            if (simbolo.getTokenVariavel().getTokenString().equals(token.getTokenString())) {
                return simbolo.getTokenTipoVariavel();
            }
        }
        return null;
    }
}
