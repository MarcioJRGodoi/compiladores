package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import Parse.TabelaParsing;
import Parse.TabelaProd;

public class Sintatico {
    static int SIFRAO = 45;
    static int MENOS = 48;
    static int NULO = 16;
    private static TabelaParsing tabelaParser;
    private static TabelaProd tabelaProd;

    public static void AnaliseSintatica(List<Integer> tokens) throws Exception {
        tabelaParser = new TabelaParsing();
        tabelaProd = new TabelaProd();

        Stack<Integer> pilha = SintaticoUtil.geraPilha();

        int valorPilha = pilha.peek();
        int valorTerminal = tokens.get(0);
        int contagem = 0;

        while (valorPilha != SIFRAO) {
            contagem++;

            geraPrintInicial(contagem, valorPilha, valorTerminal, pilha);

            if (valorPilha == NULO) {
                pilha.pop();
                valorPilha = pilha.peek();
            } else {
                if (valorPilha <= MENOS) { // topo da pilha é um terminal
                    if (valorPilha == valorTerminal) { 
                        pilha.pop();

                        if (!tokens.isEmpty() && tokens.size() > 1) {
                            tokens = tokens.subList(1, tokens.size());
                        }

                        valorPilha = pilha.peek();
                        if (tokens.size() != 0) {
                            valorTerminal = tokens.get(0);
                        }
                    } else {
                        geraErro(
                                "Não foi possível concluir a Análise Sintática pois o sistema detectou que " +
                                        "o código enviado está inválido e necessita correção");
                    }
                } else {
                    int producao = tabelaParser.getRegra(valorPilha, valorTerminal);
                    if (producao != -1) {
                        System.out.println("Produção encontrada: " + producao);
                        pilha.pop();
                        HashMap<Integer, Integer> linhaBloco = tabelaProd.tabProd.get(producao);
                        if (linhaBloco != null) {
                            List<Integer> valores = new ArrayList<>(linhaBloco.values());
                            Collections.reverse(valores);
                            for (int valor : valores) {
                                pilha.push(valor);
                            }
                        }
                        valorPilha = pilha.peek();
                    } else {
                        if (SintaticoUtil.VerificaPossuiNulo(valorPilha)) {
                            pilha.pop();
                            pilha.push(16);
                            valorPilha = pilha.peek();
                        } else {
                            geraErro(
                                "Não foi possível concluir a Análise Sintática pois o sistema detectou que " +
                                        "não há produções para continuar, logo, o código enviado estando inválido");
                        }
                    }
                }
            }
            geraPrintFinal();
        }

        contagem++;
        geraPrintConclusao(contagem, valorPilha, pilha);
    }

    private static void geraErro(String mensagem) throws Exception {
        throw new Exception(mensagem);
    }

    private static void geraPrintInicial(int contagem, int valorPilha, int valorTerminal, Stack<Integer> pilha) {
        System.out.println("----- Contador: " + contagem + " ----");
        System.out.println("Valor atual da Pilha: " + valorPilha);
        System.out.println("Token atual: " + valorTerminal);
        System.out.println("Pilha Restante: " + pilha);
    }

    private static void geraPrintFinal() {
        System.out.println("------------------------------------");
        System.out.println("\n");
    }

    private static void geraPrintConclusao(int contagem, int valorPilha, Stack<Integer> pilha) {
        System.out.println("----- Contador: " + contagem + " ----");
        System.out.println("Valor atual da Pilha: " + valorPilha);
        System.out.println("Pilha Restante: " + pilha);
        System.out.println("------------------------------------");
        System.out.println("\n");
        System.out.println("Sentença reconhecida com sucesso");
    }
}
