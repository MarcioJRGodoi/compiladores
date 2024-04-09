package org.example;

import java.util.ArrayList;
import enuns.*;

public class Main {
    public static void main(String[] args) {
        String frase = "void main{inicio;fim}";
        ArrayList<Integer> tokens = new ArrayList<>();
        int i = 0;

        while (i < frase.length()) {
            StringBuilder tokenBuilder = new StringBuilder();
            char currentChar = frase.charAt(i);

            if (Character.isWhitespace(currentChar)) {
                i++;
                continue;
            }

            if (Character.isLetter(currentChar) || currentChar == '_') {
                int j = i + 1;
                while (j <= frase.length()) {
                    String subString = frase.substring(i, j);
                    CodigoToken token = CodigoToken.BuscarTokenPorString(subString);
                    if (token != null) {
                        tokens.add(token.getCodigo());
                        i = j;
                        break;
                    }
                    j++;
                }

                if (j == i + 1) {
                    while (i < frase.length() && (Character.isLetterOrDigit(frase.charAt(i)) || frase.charAt(i) == '_')) {
                        tokenBuilder.append(frase.charAt(i));
                        i++;
                    }
                    
                    String tokenString = tokenBuilder.toString();
                    CodigoToken token = CodigoToken.BuscarTokenPorString(tokenString);
                    if (token != null) {
                        tokens.add(token.getCodigo());
                    }
                }
            } else {
                // Verifica subsequências de tokens
                int j = i + 1;
                while (j <= frase.length()) {
                    String subString = frase.substring(i, j);
                    CodigoToken token = CodigoToken.BuscarTokenPorString(subString);
                    if (token != null) {
                        tokens.add(token.getCodigo());
                        i = j;
                        break;
                    }
                    j++;
                }
                // Se nenhum token correspondente for encontrado, avança para o próximo caractere
                if (j == i + 1) {
                    i++;
                }
            }
        }
        System.out.println("Números dos tokens encontrados: " + tokens);
    }
}
