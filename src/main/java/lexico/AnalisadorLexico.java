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

    public int FazerAsParada(AnalisadorLexico anal){
        return 1;
    }


}
