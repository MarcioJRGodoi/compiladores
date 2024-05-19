package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lexico {
    static int FIM_COMENTARIO = 1;
    static int CONTINUA_COMENTARIO = 2;
    static int INICIO_COMENTARIO = 3;
    static int COMENTARIO_LINHA = 4;


    public static List<Integer> analiseLexica(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            // Aqui está gerando todos os tokens terminais da lista da Gramatica, do 1 até o
            // 48
            Map<Integer, String> listaTokens = LexemaUtil.geraListaTokens();
            // Para facilitar a vida, passamos para uma Lista de Strinh pq iterar em hashmap
            // é meio chatinho.
            List<String> arrayTokens = new ArrayList<>(listaTokens.values());
            // Todos os números de Tokens Encontrados
            List<Integer> tokensEncontrados = new ArrayList<>();
            // Quando estiver como comentario de bloco, irá rodar até que encontre o fim do
            // comentário
            boolean ehBlocoDeComentario = false;
            boolean ehStringChar = false;
            boolean ehLiteral = false;

            // Variaveis de busca para a linha, como a linha em si "linha", e o número da
            // linha sendo vista "numeroLinha"
            int numeroLinha = 0;
            String linha = "";
            while ((linha = br.readLine()) != null) {
                numeroLinha++;
                String lexema = "";
                int i = 0;

                // Vai rodar em todas as letras da linha, facil de entender.
                while (i < linha.length()) {
                    int iComentario = LexemaUtil.VerificaComentario(ehBlocoDeComentario, i, linha);

                    if (iComentario == COMENTARIO_LINHA) {
                        // Acha um comentário de linha, ignora o resto após ele e vai pra outra linha
                        break;
                    }

                    if (iComentario == INICIO_COMENTARIO) {
                        // Encontra o começo de um bloco de comentário, dá continue para realizar o
                        // resto
                        ehBlocoDeComentario = true;
                        continue;
                    } else if (iComentario == CONTINUA_COMENTARIO) {
                        // Continua achando o final do bloco de comentário
                        // O seu principal propósito é gerar erro caso não tenha fim do bloco
                        i++;
                        continue;
                    } else if (iComentario == FIM_COMENTARIO) {
                        // Vai achar o final do bloco de comentário e vai sair
                        i += 2;
                        ehBlocoDeComentario = false;
                        continue;        
                    }

                    if (arrayTokens.contains(Character.toString(linha.charAt(i))) && 
                        (!ehLiteral && !ehStringChar)) {
                        if (!lexema.trim().equals("")) {
                            // Caso seja encontrado uma variavel
                            int token = arrayTokens.indexOf("nomevariavel") + 1;
                            AdicionaTokenLexema(tokensEncontrados, token);
                        }
                        lexema = Character.toString(linha.charAt(i));
                    } else if (linha.charAt(i) != ' ' || lexema.startsWith("'")) {
                        // Adiciona as letras até achar algum token
                        lexema = lexema + linha.charAt(i);
                    }

                    if (arrayTokens.contains(lexema)) {
                        // Faz uma verificação de todos os tokens compostos primeiros, só para garantir
                        // Aqui ele já achou um lexema, então está de boas não entrar no IF
                        if (i + 1 < linha.length() && arrayTokens.contains(lexema + linha.charAt(i + 1))) {
                            lexema += linha.charAt(i + 1);
                            int token = arrayTokens.indexOf(lexema) + 1;
                            i += 2;
                            AdicionaTokenLexema(tokensEncontrados, token);
                            lexema = "";
                            continue;
                        }

                        // Caso não ache um composto, só adiciona o simples
                        int token = arrayTokens.indexOf(lexema) + 1;
                        AdicionaTokenLexema(tokensEncontrados, token);
                        lexema = "";
                    } else {
                        // Aqui vai ter todas as verificações de Integer, float, string, char e literal
                        // Cada um tendo sua regra certinho.

                        int token = 0;
                        // Verificação para String
                        // esse booleano abaixo serve para caso até o final da string não ache o fim das
                        // aspas ou encontre alguma lexema
                        // tipo o ;
                        if (lexema.startsWith("'")) {
                            ehStringChar = true;
                            if (LexemaValidar.verificarString(lexema)) {
                                token = LexemaValidar.verificaBuscaTokenString(arrayTokens, lexema, numeroLinha);
                            } if (i == linha.length() - 1) {
                                token = LexemaValidar.verificaBuscaTokenString(arrayTokens, lexema, numeroLinha);
                            }
                        }
                        // Verificação de literal
                        else if (lexema.startsWith("\"")) {
                            ehLiteral = true;
                            if (LexemaValidar.verificarLiteral(lexema)) {
                                token = LexemaValidar.verificaBuscaTokenLiteral(arrayTokens, lexema, numeroLinha);
                            } if (i == linha.length() - 1) {
                                token = LexemaValidar.verificaBuscaTokenLiteral(arrayTokens, lexema, numeroLinha);
                            }
                        }
                        // Verificação para Integer
                        else if (LexemaValidar.verificaInteger(lexema)) {
                            while (i + 1 < linha.length()
                                    && LexemaValidar.verificaInteger(String.valueOf(linha.charAt(i + 1)))) {
                                lexema += linha.charAt(i + 1);
                                i++;
                            }
                            // Verificação se é float
                            if (linha.charAt(i + 1) == '.') {
                                lexema += linha.charAt(i + 1);
                                i++;
                                while (i + 1 < linha.length()
                                        && LexemaValidar.verificaInteger(String.valueOf(linha.charAt(i + 1)))) {
                                    lexema += linha.charAt(i + 1);
                                    i++;
                                }
                                if (LexemaValidar.verificarFloat(lexema)) {
                                    token = LexemaValidar.verificaBuscaTokenFloat(arrayTokens, lexema, numeroLinha);
                                }
                            } else {
                                token = LexemaValidar.verificaBuscaTokenInteger(arrayTokens, lexema, numeroLinha);
                            }
                        } else {
                            token = 0;
                        }

                        if (token > 0) {
                            // Caso de tudo certo, adiciona o token
                            AdicionaTokenLexema(tokensEncontrados, token);
                            lexema = "";
                            ehLiteral = false;
                            ehStringChar = false;
                        } else if (token == -1) {
                            // Caso a validação de problema, exclui o lexema
                            lexema = "";
                        }
                    }

                    i++;
                }
            }

            // Caso não encontre o fim do bloco de linha
            if (ehBlocoDeComentario) {
                System.out.println("Erro léxico: Comentário de bloco está invalido pois não foi possível encontrar um fim.");
            }

            // Aqui é o final, a partir daqui será continuado o Analisador Sintático
            System.out.println(tokensEncontrados);
            return tokensEncontrados;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void AdicionaTokenLexema(List<Integer> tokens, int token) {
        tokens.add(token);
    }
}
