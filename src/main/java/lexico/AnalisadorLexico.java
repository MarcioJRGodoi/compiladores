package lexico;

import enuns.CodigoToken;
import regerx.Regex;

import java.util.ArrayList;
import java.util.Map;

public class AnalisadorLexico {
    private String Palavras;
    private String Lexema;

    private ArrayList<Integer> Tokens;
    private ArrayList<String> Lexemas;

    public AnalisadorLexico(ArrayList<String> lexemas, ArrayList<Integer> tokens, String lexema, String palavras) {
        this.Lexemas = lexemas;
        this.Tokens = tokens;
        this.Lexema = lexema;
        this.Palavras = palavras;
    }

    public void FazerAsParada() throws Exception {

        for (int i = 0; i < Palavras.length(); i++) {
            if (Palavras.charAt(i) == '{') {
                Lexema = String.valueOf(Palavras.charAt(i));
            } else if (Palavras.charAt(i) != ' ') {
                Lexema += Palavras.charAt(i);
            } else {
                Lexema = "";
            }

            System.out.println(Lexema);


            CodigoToken codigoToken = CodigoToken.fromString(Lexema);
            if (codigoToken != null) {
                boolean matched = false;

                // Verifique cada expressão regular
                for (Map.Entry<String, String> entry : Regex.REGEX_MAP.entrySet()) {
                    if (Lexema.matches(entry.getValue())) {
                        matched = true;
                        System.out.println(matched);
                        break;
                    }
                }
                if(!matched){
                    throw new Exception("Invalido!");
                }

                Tokens.add(codigoToken.getIndex());
                Lexemas.add(Lexema);
                Lexema = "";
            }
        }

        // Entrega do lexico - token - Lexema - linha
        for (int i = 0; i < Tokens.size(); i++) {
            System.out.println("Token: " + Tokens.get(i) + " - Lexema: " + Lexemas.get(i) + " - Linha: 1");
        }
    }
}