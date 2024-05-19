package org.example;

import java.util.HashMap;
import java.util.Stack;

import Parse.TabelaProd;

public class SintaticoUtil {
    static int SIFRAO = 45;

    public static Stack<Integer> geraPilha() {
        TabelaProd tabelaProd = new TabelaProd();
        Stack<Integer> pilha = new Stack<>();
        //  Para começo padrão da pilha, é necessário adicionar um SIFRÃO
        //  Serve apenas para saber que ao final de tudo, conseguimos realizar 
        // a analíse sintática.
        pilha.push(SIFRAO);

        HashMap<Integer, Integer> linhaBloco = tabelaProd.tabProd.get(0);
        if (linhaBloco != null) {
            for (int valor : linhaBloco.values()) {
                pilha.push(valor);
            }
        }

        System.out.println(pilha);

        return pilha;
    }
}
