package org.example;

import java.util.HashMap;
import java.util.Map;
import enuns.CodigoToken;

public class LexemaUtil {
    public static Map<Integer, String> geraListaTokens() {
        Map<Integer, String> lista = new HashMap<>();

        for (CodigoToken token : CodigoToken.values()) {
            lista.put(token.getCodigo(), token.getTokenString());
        }

        return lista;
    }

    public static int VerificaComentario(boolean ehBlocoDeComentario, int posicaoLetra, String linhaAtual) {
        if (ehBlocoDeComentario) {
            if (posicaoLetra + 2 < linhaAtual.length() && linhaAtual.substring(posicaoLetra, posicaoLetra + 2).equals("*/")) {
                posicaoLetra += 2;
                ehBlocoDeComentario = false;
                return 1;
            } else {
                posicaoLetra++;
                return 2;
            }
        }

        if (posicaoLetra + 2 < linhaAtual.length() && linhaAtual.substring(posicaoLetra, posicaoLetra + 2).equals("//")) {
            return 4;
        }

        if (posicaoLetra + 2 < linhaAtual.length() && linhaAtual.substring(posicaoLetra, posicaoLetra + 2).equals("/*")) {
            return 3;
        }
        
        return 0;
    }
}
