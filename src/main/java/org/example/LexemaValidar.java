package org.example;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemaValidar {
    public static boolean verificarString(String lexema) {
        Pattern pattern = Pattern.compile("^'.*?'");
        Matcher matcher = pattern.matcher(lexema);
        return matcher.matches();
    }

    public static int verificaBuscaTokenString(List<String> arrayTokens, String lexema, int numeroLinha) {
        if (!(lexema.startsWith("'") && lexema.endsWith("'"))) {
            System.out.println("Erro léxico: A String/Char não está correto pois não está fechando as aspas simples na linha " + numeroLinha + ".");
        } else if (lexema.length() > 100) {
            System.out.println("Erro léxico: A String não está correta pois ultrapassou os 100 caracteres na linha " + numeroLinha + ".");
        } else if (lexema.length() == 100 && lexema.startsWith("'") || lexema.endsWith("'")) {
            return arrayTokens.indexOf("nomedochar") + 1;
        } else {
            return arrayTokens.indexOf("nomedastring") + 1;
        }
        return -1;
    }

    public static boolean verificarChar(String lexema) {
        return lexema.length() == 3 && lexema.charAt(0) == '\'';
    }

    public static int verificaBuscaTokenChar(List<String> arrayTokens, String lexema, int numeroLinha) {
        if (!(lexema.startsWith("'") && lexema.endsWith("'"))) {
            System.out.println("Erro léxico: A String/Char não está correto pois não está fechando as aspas simples na linha " + numeroLinha + ".");
        } else if (lexema.length() > 3) {
            System.out.println("Erro léxico: O Char não está correto pois ultrapassou formou mais que um caractere na linha " + numeroLinha + ".");
        } else {
            return arrayTokens.indexOf("nomedochar") + 1;
        }
        return -1;
    }

    public static boolean verificarLiteral(String lexema) {
        Pattern pattern = Pattern.compile("^\".*\"");
        Matcher matcher = pattern.matcher(lexema);
        return matcher.matches();
    }

    public static int verificaBuscaTokenLiteral(List<String> arrayTokens, String lexema, int numeroLinha) {
        if (!(lexema.startsWith("\"") && lexema.endsWith("\""))) {
            System.out.println("Erro léxico: O Literal não está correto pois não está fechando as aspas duplas na linha " + numeroLinha + ".");
        } else if (lexema.length() > 100) {
            System.out.println("Erro léxico: O Literal não está correta pois ultrapassou os 100 caracteres na linha " + numeroLinha + ".");
        } else {
            return arrayTokens.indexOf("literal") + 1;
        }
        return -1;
    }

    public static boolean verificaInteger(String lexema) {
        Pattern pattern = Pattern.compile("^[-+]?\\d+$");
        Matcher matcher = pattern.matcher(lexema);
        return matcher.matches();
    }

    public static int verificaBuscaTokenInteger(List<String> arrayTokens, String lexema, int numeroLinha) {
        try {
            int valor = Integer.parseInt(lexema);
            if (valor < 0) {
                System.out.println("Erro léxico: Inteiro está negativo na linha " + numeroLinha + ".");    
            } else if (valor > 500000) {
                System.out.println("Erro léxico: Inteiro ultrapassou o valor de 500000 na linha " + numeroLinha + ".");   
            } else {
                return arrayTokens.indexOf("numerointeiro") + 1;
            }
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Erro léxico: Inteiro não está válido na linha " + numeroLinha + ".");
            return -1;
        }
    }

    public static boolean verificarFloat(String lexema) {
        Pattern pattern = Pattern.compile("^[-+]?\\d+\\.\\d+$");
        Matcher matcher = pattern.matcher(lexema);
        return matcher.matches();
    }

    public static int verificaBuscaTokenFloat(List<String> arrayTokens, String lexema, int numeroLinha) {
        try {
            double valor = Double.parseDouble(lexema);
            if (valor < 0) {
                System.out.println("Erro léxico: Float está negativo na linha " + numeroLinha + ".");    
            } else if (valor > 500000) {
                System.out.println("Erro léxico: Float ultrapassou o valor de 500000 na linha " + numeroLinha + ".");   
            } else if (lexema.chars().filter(ch -> ch == '.').count() != 1 ) {
                System.out.println("Erro léxico: Float está inválido na linha " + numeroLinha + ".");  
            } else if (lexema.split("\\.")[1].length() > 3) {
                System.out.println("Erro léxico: Float está inválido na linha " + numeroLinha + ", pois possui mais de 3 digitos após o ponto.");  
            } else {
                return arrayTokens.indexOf("numerofloat") + 1;
            }
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Erro léxico: Float não está válido na linha " + numeroLinha + ".");
            return -1;
        }
    }
}
