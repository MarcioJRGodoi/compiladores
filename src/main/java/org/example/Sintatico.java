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
    static int VARIAVEL = 7;
    static int FECHA_CHAVES = 36;
    static int INICIO = 14;
    static int INTEGER = 13;
    static int FLOAT = 18;
    static int CHAR = 24;
    static int STRING = 3;
    static int VOID = 2;
    static int GLOBAL = 0;
    static int LOCAL = 1;

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

        boolean validaVariaveisSemantica = false;
        boolean ehFuncaoSemantica = false;
        boolean geraFuncaoSemantica = false;
        boolean geraVariavelSemantica = false;
        List<String> listaVariavelSemantica = new ArrayList<>();

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
                         * Essa validação serve para sabermos quando precisamos parar de adicionar
                         * as variáveis e começar a validar elas
                         */
                        if ((!validaVariaveisSemantica) && (valorPilha == INICIO)) {
                            validaVariaveisSemantica = true;
                        }

                        if ((ehFuncaoSemantica) && (valorPilha == INICIO)) {
                            validaVariaveisSemantica = true;
                        }

                        if (!validaVariaveisSemantica) {
                            /*
                             * Enquanto não for realizada a validação das variaveis, iremos adiciona-las
                             * aqui
                             * Basicamente nesta parte do if (geraVariavelSemantica), é verificado
                             * se o sistema encontrou já uma VARIAVEL e esta esperando receber
                             * que tipo de variável é para poder adicionar ela na tabela semantica
                             * 
                             * O Processo funciona seguindo o seguinte exemplo:
                             * 
                             * > 1 - o geraVariavelSemantica vai estar false até que ele encontre
                             * um token VARIAVEL, que irá entrar no else do código abaixo
                             * 
                             * > 2 - Sabendo a variável, vai deixar o geraVariavelSemantica como true
                             * e salvar o lexema localmente em outra variavel, pois iremos
                             * necessitar passar por outros token's
                             * 
                             * > 3 - com o geraVariavelSemantica, vai entrar no IF e verificar
                             * que tipo de variavel é, adicionando-a na tabela Semantica
                             * 
                             * Observe que ela vai como GLOBAL, pois as LOCAIS são somentes as dentro de
                             * Funções
                             * 
                             * > 4 - reseta o geraVariavelSemantica até vir uma próxima variavel
                             */
                            if (geraVariavelSemantica) {
                                int NIVEL = ehFuncaoSemantica ? LOCAL : GLOBAL;
                                if (valorTerminal == INTEGER) {
                                    // Salva na tabela Semantica como INTEGER
                                    for (int i = 0; i < listaVariavelSemantica.size(); i++) {
                                        semantico.AdicionarTokenSemantica(
                                                listaVariavelSemantica.get(i),
                                                "variavel",
                                                "integer",
                                                NIVEL,
                                                numeroLinha);
                                    }
                                    // Vai resetar as variaveis para a próxima adição
                                    geraVariavelSemantica = false;
                                    listaVariavelSemantica.clear();
                                } else if (valorTerminal == FLOAT) {
                                    // Salva na tabela Semantica como FLOAT
                                    for (int i = 0; i < listaVariavelSemantica.size(); i++) {
                                        semantico.AdicionarTokenSemantica(
                                                listaVariavelSemantica.get(i),
                                                "variavel",
                                                "float",
                                                NIVEL,
                                                numeroLinha);
                                    }

                                    // Vai resetar as variaveis para a próxima adição
                                    geraVariavelSemantica = false;
                                    listaVariavelSemantica.clear();
                                } else if (valorTerminal == CHAR) {
                                    // Salva na tabela Semantica como CHAR
                                    for (int i = 0; i < listaVariavelSemantica.size(); i++) {
                                        semantico.AdicionarTokenSemantica(
                                                listaVariavelSemantica.get(i),
                                                "variavel",
                                                "char",
                                                NIVEL,
                                                numeroLinha);
                                    }

                                    // Vai resetar as variaveis para a próxima adição
                                    geraVariavelSemantica = false;
                                    listaVariavelSemantica.clear();
                                } else if (valorTerminal == STRING) {
                                    // Salva na tabela Semantica como STRING
                                    for (int i = 0; i < listaVariavelSemantica.size(); i++) {
                                        semantico.AdicionarTokenSemantica(
                                                listaVariavelSemantica.get(i),
                                                "variavel",
                                                "string",
                                                NIVEL,
                                                numeroLinha);
                                    }

                                    // Vai resetar as variaveis para a próxima adição
                                    geraVariavelSemantica = false;
                                    listaVariavelSemantica.clear();
                                } else if (valorTerminal == VARIAVEL) {
                                    if (!geraFuncaoSemantica) {
                                        if (!ehFuncaoSemantica) {
                                            /*
                                             * Caso tenha mais de uma variável sendo declarada no mesmo tipo:
                                             * Exemplo: "var1, var2, var3 : integer;
                                             */
                                            listaVariavelSemantica.add(palavraLexema);
                                        } else {
                                            /*
                                             * caso o ehFuncaoSemantica esteja como true e chegou aqui, significa que
                                             * estamos tendo que adicionar um parâmetro na tabela de Semantica, pois
                                             * diferente das variaveis, eles são atribuidos de maneira diferente
                                             */
                                            semantico.AdicionarTokenSemantica(
                                                    palavraLexema,
                                                    "parâmetro",
                                                    listaVariavelSemantica.get(0),
                                                    GLOBAL,
                                                    numeroLinha);
                                            geraVariavelSemantica = false;
                                            listaVariavelSemantica.clear();
                                        }
                                    } else {
                                        /*
                                         * Só vai entrar aqui quando sabermos que o código está prestes a entrar em uma
                                         * função local, já salvando seu nomeFunc na tabela de Semantica
                                         */
                                        semantico.AdicionarTokenSemantica(
                                                palavraLexema,
                                                "procedure",
                                                listaVariavelSemantica.get(0),
                                                GLOBAL,
                                                numeroLinha);
                                        geraVariavelSemantica = false;
                                        geraFuncaoSemantica = false;
                                        ehFuncaoSemantica = true;
                                        listaVariavelSemantica.clear();
                                    }
                                } else {
                                    if (geraFuncaoSemantica) {
                                        /*
                                         * Quando houver o caso de uma variavel do tipo
                                         * INTEGER,FLOAT,CHAR,STRING e VOID ter ativado a geração de função
                                         * local, porém o lexema após não é uma variável. Um exemplo seria o começo do
                                         * código "void main ..."
                                         * 
                                         * Com isso, o sistema sabe que não precisa se preocupar que está vindo uma
                                         * função local
                                         */
                                        geraFuncaoSemantica = false;
                                        ehFuncaoSemantica = false;
                                        listaVariavelSemantica.clear();
                                    }
                                }
                            } else {
                                if (valorTerminal == VARIAVEL) {
                                    /*
                                     * Este primeiro IF será responsável pela adição de variaveis dentro de uma
                                     * função global ou local
                                     */
                                    geraVariavelSemantica = true;
                                    listaVariavelSemantica.clear();
                                    listaVariavelSemantica.add(palavraLexema);
                                } else if ((valorTerminal == INTEGER) ||
                                        (valorTerminal == FLOAT) ||
                                        (valorTerminal == CHAR) ||
                                        (valorTerminal == STRING) ||
                                        (valorTerminal == VOID)) {
                                    /*
                                     * Aqui existem 2 Casos, quando queremos descobrir se o LEXEMA
                                     * após o INTEGER,FLOAT,CHAR,STRING e VOID é uma
                                     * procedure ou um parâmetro
                                     */
                                    if (!ehFuncaoSemantica) {
                                        /*
                                         * Neste caso, é quando o sistema acha um dos tokens
                                         * INTEGER, FLOAT, CHAR, STRING e VOID e não está dentro de uma função local,
                                         * reconhecendo que pode ser o inicio de uma função local
                                         */
                                        listaVariavelSemantica.clear();
                                        listaVariavelSemantica.add(palavraLexema);
                                        // Vai permitir adicionar uma variavel na tabela semantica de categoria
                                        // "procedure"
                                        geraVariavelSemantica = true;
                                        // Vai permitir entrar na função para adicionar a "procedure"
                                        geraFuncaoSemantica = true;
                                    } else {
                                        /*
                                         * Caso seja um parâmetro de função, entrará nesta parte
                                         * já que a atribuição na gramáica é feito por TIPO NOME_VARIAVEL
                                         */
                                        listaVariavelSemantica.clear();
                                        listaVariavelSemantica.add(palavraLexema);
                                        geraVariavelSemantica = true;
                                    }
                                }
                            }
                        } else {
                            /*
                             * Nesta parte é após as inserções de variaveis na tabela
                             * Irá realizar a validação para ver se todas as variaveis que estão
                             * sendo usadas estão corretamente na tabela de Semantica
                             */
                            if (valorTerminal == VARIAVEL) {
                                /*
                                 * Neste caso aqui, irá validar as variaveis tanto na função local quanto na função global
                                 */
                                semantico.verificaVariavelDeclarada(palavraLexema, numeroLinha);
                            } else if ((ehFuncaoSemantica) && (valorTerminal == FECHA_CHAVES)) {
                                /*
                                 * Aqui serve com o único propósito de quando estivermos saindo da validação local
                                 * Irá deixar tudo como false, principalmente a verificação de variavel declarada
                                 */
                                ehFuncaoSemantica = false;
                                validaVariaveisSemantica = false;
                                geraFuncaoSemantica = false;
                                geraVariavelSemantica = false;

                                /*
                                 * Para finalizar esta função, iremos remover todas as variaveis da função local
                                 * para não gerar erros futuros em outros escopos
                                 */
                                semantico.excluiVariaveisLocais();
                            }
                        }

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
                                "\n" + "[Erro Sintático]" + "\n" +
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
                                    "\n" + "[Erro Sintático]" + "\n" +
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
