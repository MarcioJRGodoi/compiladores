package lexico;

import java.util.ArrayList;

public class AnalisadorLexico {
    private String Palavras;
    private String Lexama;

    private ArrayList<Integer> Tokens;
    private ArrayList<String> Lexamas;

    public AnalisadorLexico(ArrayList<String> lexamas, ArrayList<Integer> tokens, String lexama, String palavras) {
        Lexamas = lexamas;
        Tokens = tokens;
        Lexama = lexama;
        Palavras = palavras;
    }

    public void FazerAsParada(){
            for (int i = 0; i < Palavras.length(); i++) {
                if (Palavras.charAt(i) == '{') {
                    Lexama = String.valueOf(Palavras.charAt(i));
                } else if (Palavras.charAt(i) != ' ') {
                    Lexama += Palavras.charAt(i);
                } else {
                    Lexama = "";
                }

                System.out.println(Lexama);  // Print opcional para ver o andamento

                switch (Lexama) {
                    case "void":
                        Tokens.add(2);
                        Lexamas.add(Lexama);
                        break;
                    case "main":
                        Tokens.add(11);
                        Lexamas.add(Lexama);
                        break;
                    case "}":
                        Tokens.add(38);
                        Lexamas.add(Lexama);
                        break;
                    case "{":
                        Tokens.add(39);
                        Lexamas.add(Lexama);
                        Lexama = "";
                        break;
                    case "inicio":
                        Tokens.add(15);
                        Lexamas.add(Lexama);
                        break;
                    case "fim":
                        Tokens.add(20);
                        Lexamas.add(Lexama);
                        break;
                    case ";":
                        Tokens.add(40);
                        Lexamas.add(Lexama);
                        break;
                }
            }

            // Entrega do lexico - token - lexema - linha
            for (int i = 0; i < Tokens.size(); i++) {
                System.out.println("Token: " + Tokens.get(i) + " - Lexema: " + Lexamas.get(i) + " - Linha: 1");
            }

    }


}
