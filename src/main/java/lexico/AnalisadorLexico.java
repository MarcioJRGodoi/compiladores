package lexico;

import enuns.CodigoToken;

import java.util.ArrayList;

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

    public void FazerAsParada(){
        for (int i = 0; i < Palavras.length(); i++) {
            if (Palavras.charAt(i) == '{') {
                Lexema = String.valueOf(Palavras.charAt(i));
            } else if (Palavras.charAt(i) != ' ') {
                Lexema += Palavras.charAt(i);
            } else {
                Lexema = "";
            }

            System.out.println(Lexema); // Print opcional para ver o andamento

            CodigoToken codigoToken = CodigoToken.fromString(Lexema);
            if (codigoToken != null) {
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