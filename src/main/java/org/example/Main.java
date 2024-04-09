package org.example;

import enuns.CodigoToken;
import lexico.AnalisadorLexico;
import regerx.Regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        AnalisadorLexico a = new AnalisadorLexico(
                new ArrayList<String>(),
                new ArrayList<Integer>(),
                "",
                "void main {inicio ; fim }"

        );
        a.FazerAsParada();
        System.out.println(CodigoToken.values()[5].getToken());
        System.out.println(CodigoToken.values()[5].getIndex());

    }
}