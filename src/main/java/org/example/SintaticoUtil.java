package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.print.attribute.standard.MediaSize.NA;

import Parse.TabelaProd;
import enuns.NaoTerminais;

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
            List<Integer> valores = new ArrayList<>(linhaBloco.values());
            Collections.reverse(valores);
            for (int valor : valores) {
                pilha.push(valor);
            }
        }

        System.out.println(pilha);

        return pilha;
    }

    public static boolean VerificaPossuiNulo(int valorPilha) {
        if ((valorPilha == NaoTerminais.DCLVAR.getCodigo()) ||
            (valorPilha == NaoTerminais.REPIDENT.getCodigo()) ||
            (valorPilha == NaoTerminais.LDVAR.getCodigo()) ||
            (valorPilha == NaoTerminais.DCLFUNC.getCodigo()) ||
            (valorPilha == NaoTerminais.VALORRETORNO.getCodigo()) ||
            (valorPilha == NaoTerminais.DEFPAR.getCodigo()) ||
            (valorPilha == NaoTerminais.LPARAM.getCodigo()) ||
            (valorPilha == NaoTerminais.REPCOMANDO.getCodigo()) ||
            (valorPilha == NaoTerminais.COMANDO.getCodigo()) ||
            (valorPilha == NaoTerminais.PARAMETROS.getCodigo()) ||
            (valorPilha == NaoTerminais.REPPAR.getCodigo()) ||
            (valorPilha == NaoTerminais.ELSEPARTE.getCodigo()) ||
            (valorPilha == NaoTerminais.SEQCOUT.getCodigo()) ||
            (valorPilha == NaoTerminais.SEQUENCIA.getCodigo()) ||
            (valorPilha == NaoTerminais.REPEXP.getCodigo()) ||
            (valorPilha == NaoTerminais.REPTERMO.getCodigo())) {
                return true;
        } else {
            return false;
        }
    }
}
