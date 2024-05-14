package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args) {
        String caminhoArquivoEntrada = selecionarArquivo();
        if (caminhoArquivoEntrada != null) {
            lerArquivo(caminhoArquivoEntrada);
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
    }

    // Função para selecionar o arquivo de entrada
    public static String selecionarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            return arquivoSelecionado.getAbsolutePath();
        }
        return null;
    }

    public static void lerArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            // Aqui está gerando todos os tokens terminais da lista da Gramatica, do 1 até o 48
            Map<Integer, String> listaTokens = LexemaUtil.geraListaTokens();
            // Para facilitar a vida, passamos para uma Lista de Strinh pq iterar em hashmap é meio chatinho.
            List<String> arrayTokens = new ArrayList<>(listaTokens.values());
            // Todos os números de Tokens Encontrados
            List<Integer> tokensEncontrados = new ArrayList<>();
            // Todas os lexemas, que se colocando em ordem, irão gerar o token + lexema certinho
            List<String> lexemasEncontrados = new ArrayList<>();
            //Quando estiver como comentario de bloco, irá rodar até que encontre o fim do comentário
            boolean ehBlocoDeComentario = false;
            
            // Variaveis de busca para a linha, como a linha em si "linha", e o número da linha sendo vista "numeroLinha"
            int numeroLinha = 0;
            String linha = "";
            while ((linha = br.readLine()) != null) {
                numeroLinha++;
                String lexeme = "";
                int i = 0;

                // Vai rodar em todas as letras da linha, facil de entender.
                while (i < linha.length()) {
                    switch (LexemaUtil.VerificaComentario(ehBlocoDeComentario, i, linha)) {
                        case 1:
                            // Vai achar o final do bloco de comentário e vai sair ignora-lo
                            i += 2;
                            ehBlocoDeComentario = false;
                            continue;
                        case 2:
                            // Continua achando o final do bloco de comentário
                            // O seu principal propósito é gerar erro caso não tenha fim do bloco
                            i++;
                            continue;
                        case 3:
                            // Encontra o começo de um bloco de comentário, dá continue para realizar o resto
                            ehBlocoDeComentario = true;
                            continue;
                        case 4:
                            // Bloco de linha, ignora o resto após ele e vai pra outra linha
                            break;
                    }
                    

                    if (arrayTokens.contains(Character.toString(linha.charAt(i)))) {
                        if (!lexeme.trim().equals("")) {
                            // Caso seja encontrado uma variavel
                            int token = arrayTokens.indexOf("nomevariavel") + 1;
                            AdicionaTokenLexema(tokensEncontrados, lexemasEncontrados, token, lexeme);
                        }
                        lexeme = Character.toString(linha.charAt(i));
                    } else if (linha.charAt(i) != ' ' || lexeme.startsWith("'")) {
                        // Adiciona as letras até achar algum token
                        lexeme = lexeme + linha.charAt(i);
                    }

                    if (arrayTokens.contains(lexeme)) {
                        // Faz uma verificação de todos os tokens compostos primeiros, só para garantir
                        // Aqui ele já achou um lexema, então está de boas não entrar no IF
                        if (i + 1 < linha.length() && arrayTokens.contains(lexeme + linha.charAt(i + 1))) {
                            lexeme += linha.charAt(i + 1);
                            int token = arrayTokens.indexOf(lexeme) + 1;
                            i += 2;
                            AdicionaTokenLexema(tokensEncontrados, lexemasEncontrados, token, lexeme);
                            lexeme = "";
                            continue;
                        }
                        
                        // Caso não ache um composto, só adiciona o simples
                        int token = arrayTokens.indexOf(lexeme) + 1;
                        AdicionaTokenLexema(tokensEncontrados, lexemasEncontrados, token, lexeme);
                        lexeme = "";
                    } else {
                        // Aqui vai ter todas as verificações de Integer, float, string, char e literal
                        // Cada um tendo sua regra certinho.

                        int token = 0;
                        // Verificação para String
                        // esse booleano abaixo serve para caso até o final da string não ache o fim das aspas ou encontre alguma lexema
                        // tipo o ;
                        boolean validaString = (i == linha.length() - 1) || arrayTokens.contains(Character.toString(linha.charAt(i + 1)));
                        if (lexeme.startsWith("'")) {
                            if (LexemaValidar.verificarString(lexeme)) {
                                token = LexemaValidar.verificaBuscaTokenString(arrayTokens, lexeme, numeroLinha);
                            } else if (validaString) {
                                token = LexemaValidar.verificaBuscaTokenString(arrayTokens, lexeme, numeroLinha);
                            }
                        }
                        // Verificação de literal
                        else if (LexemaValidar.verificarLiteral(lexeme)) {
                            token = LexemaValidar.verificaBuscaTokenLiteral(arrayTokens, lexeme, numeroLinha);
                        } 
                        // Verificação para Integer
                        else if (LexemaValidar.verificaInteger(lexeme)) {
                            while (i + 1 < linha.length() && LexemaValidar.verificaInteger(String.valueOf(linha.charAt(i + 1)))) {
                                lexeme += linha.charAt(i + 1);
                                i++;
                            }
                            // Verificação se é float
                            if (linha.charAt(i + 1) == '.') {
                                lexeme += linha.charAt(i + 1);
                                i++;
                                while (i + 1 < linha.length() && LexemaValidar.verificaInteger(String.valueOf(linha.charAt(i + 1)))) {
                                    lexeme += linha.charAt(i + 1);
                                    i++;
                                }
                                if (LexemaValidar.verificarFloat(lexeme)) {
                                    token = LexemaValidar.verificaBuscaTokenFloat(arrayTokens, lexeme, numeroLinha);
                                }
                            } else {
                                token = LexemaValidar.verificaBuscaTokenInteger(arrayTokens, lexeme, numeroLinha);
                            }
                        } 
                        else {
                            token = 0;
                        }
                    
                        if (token > 0) {
                            // Caso de tudo certo, adiciona o token
                            AdicionaTokenLexema(tokensEncontrados, lexemasEncontrados, token, lexeme);
                            lexeme = "";
                        } else if (token == -1){
                            // Caso a validação de problema, exclui o lexema
                            lexeme = "";
                        }
                    }

                    i++;
                }
            }  

            // Caso não encontre o fim do bloco de linha
            if (ehBlocoDeComentario) {
                System.out.println("Erro léxico: Comentário de bloco está invalido.");
            }

            // Aqui é o final, a partir daqui será continuado o Analisador Sintático
            System.out.println(tokensEncontrados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void AdicionaTokenLexema(List<Integer> tokens, List<String> lexemes, int token, String lexeme) {
        tokens.add(token);
        lexemes.add(lexeme);
    }
}