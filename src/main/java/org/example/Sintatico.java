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

    public static void AnaliseSintatica(List<Integer> listaTokens, List<String> listaLexemas, List<Integer> listaLinhas)
            throws Exception {
        // Cria as 2 Tabelas necessárias para realizar a Análise Sintatica
        /*
         * A Tabela de Parser tem como objetivo apontar qual produção da Tabela Produção
         * deve ser colocada na Análise.
         * Só é utilizada quando o valor da pilha é um NÃO TERMINAL
         * e necessitamos de uma Produção que
         * combine o NÃO TERMINAL com o Token Terminal que estamos
         * enviando da Análise Léxica
         */
        tabelaParser = new TabelaParsing();
        /*
         * A Tabela de Produção tem como objetivo adicionar na pilha toda a Produção
         * para que consiga continuar o processo de eliminação dos token terminais que
         * vieram da Análise Léxica
         */
        tabelaProd = new TabelaProd();

        // Inicia a Análise Semantica, que será usada mais tarde
        Semantico semantico = new Semantico();

        /*
         * Para começo da Pilha, é sempre necessário gerar com o $ além da primeira
         * produção da Tabela de Produções
         * Os valores dessa tabelas ficariam tipo
         * $ = Na pilha seria o último valor, ou seja, o valor mais abaixo da pilha
         * Primeiro Token da Produção = Fica como primeiro valor,
         * no mais alto ponto da pilha
         * Último Token da Produção = Fica antes do $, na penúltima posição
         */
        Stack<Integer> pilha = SintaticoUtil.geraPilha();

        // Busca o valor mais alto na pilha, neste caso, o primeiro token da produção
        int valorPilha = pilha.peek();

        // Vai retornar o primeiro token que veio da Análise Léxica
        int valorTerminal = listaTokens.get(0);
        // Vai retornar o lexema (palavra em String) que corresponde ao valorTerminal
        String palavraLexema = listaLexemas.get(0);
        // Deixa salvo a linha onde o valorTerminal está para caso gere erro
        int numeroLinha = listaLinhas.get(0);

        // Tem como único objeto gerar o print para melhor visualização de quantas
        // vezes ocorreu as iterações
        int contagem = 0;

        /*
         * Só vai finalizar o While quando o valorPilha chegar no SIFRAO
         * significando que todas as funções rodaram bonitinho e não houve erros
         */
        while (valorPilha != SIFRAO) {
            contagem++;

            geraPrintInicial(contagem, valorPilha, valorTerminal, pilha);

            if (valorPilha == NULO) {
                /*
                 * Alguns Tokens não terminais tem a possibilidade de serem NULOS
                 * então no caso aconteça de ser nulo, esse valor na pilha é
                 * ignorado e retirado da pilha,
                 * após isso, é pego o proximo valor para comparar com o valorTerminal
                 */
                pilha.pop();
                valorPilha = pilha.peek();

            } else {
                /*
                 * Caso o valorPilha não seja NULO, será enviado para cá,
                 * sendo a única validação
                 * se o valorPilha é menor ou igual ao MENOS, pois este Terminal
                 * é o último da lista
                 * de terminais, sendo o próximo numero, um não terminal, que no caso
                 * será enviado para o ELSE lá embaixo
                 */
                if (valorPilha <= MENOS) {
                    /*
                     * Aqui é um caso mais delicado, abaixo vai fazer a validação
                     * se o terminal do valorPilha é igual ao valorTerminal que veio do Léxico.
                     * 
                     * É obrigatório que entre no IF, pois se for para o ELSE,
                     * existe 2 possibilidades de ter gerado o erro:
                     * 
                     * > Foi escrito algo errado no TXT (Erro de Usuário)
                     * > Não foi inserida a Produção Correta, sendo necessário verificar
                     * a Tabela de Parsing e corrigir o erro (Erro de Desenvolvedor)
                     */
                    if (valorPilha == valorTerminal) {
                        pilha.pop();

                        /*
                         * Exclui o primeiro item das listas pois
                         * passaram corretamente na Análise Sintática.
                         */
                        if (!listaTokens.isEmpty() && listaTokens.size() > 1) {
                            listaTokens = listaTokens.subList(1, listaTokens.size());
                            listaLexemas = listaLexemas.subList(1, listaLexemas.size());
                            listaLinhas = listaLinhas.subList(1, listaLinhas.size());
                        }

                        // Busca o próximo valor da pilha
                        valorPilha = pilha.peek();

                        if (listaTokens.size() != 0) {
                            /*
                             * Atualiza os valores que queremos encontrar e comparar com a pilha.
                             * Como excluímos o primeiro item das listas, o get com 0
                             * sempre irá pegar o próximo valor corretamente.
                             */

                            valorTerminal = listaTokens.get(0);
                            palavraLexema = listaLexemas.get(0);
                            numeroLinha = listaLinhas.get(0);
                        }
                    } else {
                        geraErro(
                                "\n" + "ERRO!" + "\n" +
                                        "Lexema: " + palavraLexema + "\n" +
                                        "Linha do Código: " + numeroLinha + "\n" +
                                        "Não foi possível concluir a Análise Sintática pois o sistema detectou que " +
                                        "o código enviado está inválido e necessita correção.");
                    }
                } else {
                    /*
                     * Se o valor da pilha for um NÃO TERMINAL,
                     * devemos buscar uma Produção e adiciona-lá na pilha
                     * Neste caso será apenas para isso.
                     */
                    int producao = tabelaParser.getRegra(valorPilha, valorTerminal);
                    if (producao != -1) {
                        System.out.println("Produção encontrada: " + producao);
                        // Remove o NÃO Terminal
                        pilha.pop();

                        /*
                         * Adiciona a produção na pilha de modo reverso, para que o valor do token dessa
                         * produção seja o último da pilha, ou seja, o mais alto
                         */
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
                        /*
                         * Existe a possibilidade de não ser encontrado a produção,
                         * Deverá ser verificado se o NãO TERMINAL pode ser enviado NULO
                         * Caso possa, vai remover o NÃO TERMINAL e colocar um NULO no lugar.
                         * 
                         * Não era necessário colocar o NULO, mas botamos só para ficar bonitinho
                         */
                        if (SintaticoUtil.VerificaPossuiNulo(valorPilha)) {
                            pilha.pop();
                            pilha.push(16);
                            valorPilha = pilha.peek();
                        } else {
                            /*
                             * Caso não seja encontrado uma Produção para colocar na PILHA
                             * e o NÃO TERMINAL não tem NULO, irá gerar um erro obrigatoriamente
                             */
                            geraErro(
                                    "\n" + "ERRO!" + "\n" +
                                            "Lexema: " + palavraLexema + "\n" +
                                            "Linha do Código: " + numeroLinha + "\n" +
                                            "Não foi possível concluir a Análise Sintática pois o sistema detectou que "
                                            +
                                            "não há produções para continuar, logo, o código enviado está inválido e necessita correção.");
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
