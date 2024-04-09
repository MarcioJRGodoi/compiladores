package org.example;

import regerx.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String regexChar = Regex.REGEX_CHAR;
        Pattern pattern = Pattern.compile(regexChar);
        String number = "123";

        // Cria um objeto Matcher para a string que você quer verificar
        Matcher matcher = pattern.matcher(number);

        // Verifica se a string corresponde à expressão regular
        boolean matches = matcher.matches();

        // Imprime o resultado
        System.out.println("A string "+ number +" corresponde à regexChar? " + matches);

    }
}