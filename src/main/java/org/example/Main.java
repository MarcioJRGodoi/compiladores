package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import enuns.CodigoToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static regerx.Regex.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<TokenEncontrado> tokens = new ArrayList<TokenEncontrado>();
        String caminhoArquivoEntrada = selecionarArquivo();
        if (caminhoArquivoEntrada != null) {
            String frase = lerArquivo(caminhoArquivoEntrada, tokens);
            if (frase != null) {
                encontrarTokens(frase, tokens);
                String caminhoArquivoSaida = obterCaminhoSaida(caminhoArquivoEntrada);
                escreverArquivo(caminhoArquivoSaida, tokens);
            } else {
                System.out.println("Erro: Não foi possível ler o arquivo de entrada.");
            }
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
    }

    // Classe para representar um token encontrado juntamente com a palavra
    // correspondente
    static class TokenEncontrado {
        int codigoToken;
        String palavra;
        String descricaoToken;

        TokenEncontrado(int codigoToken, String palavra, String descricaoToken) {
            this.codigoToken = codigoToken;
            this.palavra = palavra;
            this.descricaoToken = descricaoToken;
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

    public static String lerArquivo(String caminhoArquivo, ArrayList<TokenEncontrado> tokens) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String frase2 = identificarComentarios(linha, tokens);
                conteudo.append(frase2).append(" "); // Adiciona um espaço no lugar do line break
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return conteudo.toString();
    }

    // Função para encontrar os tokens na string
    public static void encontrarTokens(String frase, ArrayList<TokenEncontrado> tokens) {
        String frase2 = identificarComentarios(frase, tokens);
        int i = 0;
        while (i < frase2.length()) {
            int j = frase2.length();
            while (j > i) {
                String subString = frase2.substring(i, j);
                CodigoToken codigoToken = buscarTokenPorString(subString);
                if (codigoToken != null) {
                    // Se a subString for um token, avança para o próximo caractere
                    tokens.add(
                            new TokenEncontrado(codigoToken.getCodigo(), subString, codigoToken.getTokenDescricao()));
                    frase2 = frase2.substring(0, i) + frase2.substring(j);
                    i--;
                    break;
                }
                j--;
            }
            i++;
        }
        // Processar o restante da string após a identificação dos tokens
        ArrayList<TokenEncontrado> tokensRestantes = processarRestante(frase2);
        tokens.addAll(tokensRestantes);
    }

    // Função para buscar o token correspondente a uma string
    public static CodigoToken buscarTokenPorString(String tokenString) {
        for (CodigoToken token : CodigoToken.values()) {
            if (token.getTokenString().equals(tokenString)) {
                return token;
            }
        }
        return null;
    }

    // Função para processar o restante da string e identificar os tokens
    public static ArrayList<TokenEncontrado> processarRestante(String frase) {
        ArrayList<TokenEncontrado> tokensRestantes = new ArrayList<>();
        String replaceFrase = frase;
        replaceFrase = identificarChar(replaceFrase, tokensRestantes);
        replaceFrase = identificarString(replaceFrase, tokensRestantes);
        replaceFrase = identificarLiteral(replaceFrase, tokensRestantes);
        replaceFrase = identificarNumeroFloat(replaceFrase, tokensRestantes);
        replaceFrase = identificarNumeroInteiro(replaceFrase, tokensRestantes);
        replaceFrase = identificarDelimitadores(replaceFrase, tokensRestantes);
        replaceFrase = identificarOperadoreAritmetico(replaceFrase, tokensRestantes);
        replaceFrase = identificarDeclaracaoVariavel(replaceFrase, tokensRestantes);
        replaceFrase = identificarOperadoreDeComparacao(replaceFrase, tokensRestantes);
        replaceFrase = identificarOutrosOperadores(replaceFrase, tokensRestantes);
        return tokensRestantes;
    }

    public static String identificarDelimitadores(String frase, ArrayList<TokenEncontrado> tokensRestantes){
        String fraseTemp = frase;

        for ( int i = 0; i < fraseTemp.length(); i++){
            String currentChar = String.valueOf(fraseTemp.charAt(i));
            if(Pattern.matches(REGEX_DELIMITADOR, currentChar)){
               CodigoToken token =  CodigoToken.BuscarTokenPorString(currentChar);
                tokensRestantes.add(new TokenEncontrado(token.getCodigo(),token.getTokenString(), token.getTokenDescricao()));
                fraseTemp = fraseTemp.replaceFirst(Pattern.quote(currentChar), "");
                i--; // Decrementa o índice para compensar a remoção do caractere
            }
        }

        return fraseTemp;
    }

    public static String identificarOperadoreAritmetico(String frase, ArrayList<TokenEncontrado> tokensRestantes){
        String fraseTemp = frase;

        for ( int i = 0; i < fraseTemp.length(); i++){
            String currentChar = String.valueOf(fraseTemp.charAt(i));
            if(Pattern.matches(REGEX_DELIMITADOR, currentChar)){
                CodigoToken token =  CodigoToken.BuscarTokenPorString(currentChar);
                tokensRestantes.add(new TokenEncontrado(token.getCodigo(),token.getTokenString(), token.getTokenDescricao()));
                fraseTemp = fraseTemp.replaceFirst(Pattern.quote(currentChar), "");
                i--; // Decrementa o índice para compensar a remoção do caractere
            }
        }

        return fraseTemp;
    }

    public static String identificarOperadoreDeComparacao(String frase, ArrayList<TokenEncontrado> tokensRestantes){
        String fraseTemp = frase;

        Pattern pattern = Pattern.compile(REGEX_OPERADOR_COMPARACAO);
        Matcher matcher = pattern.matcher(fraseTemp);

        while (matcher.find()) {
            CodigoToken token =  CodigoToken.BuscarTokenPorString(matcher.group());
            tokensRestantes.add(new TokenEncontrado(token.getCodigo(),token.getTokenString(), token.getTokenDescricao()));
            fraseTemp = fraseTemp.replaceFirst(Pattern.quote(matcher.group()), "");
            System.out.println("Operador de comparação encontrado: " + matcher.group());
        }

        return fraseTemp;
    }

    public static String identificarOutrosOperadores(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;

        Pattern pattern = Pattern.compile(REGEX_OPERADOR_OUTRO);
        Matcher matcher = pattern.matcher(fraseTemp);

        while (matcher.find()) {
            String group = matcher.group();
            for (int i = 0; i < group.length(); i++) {
                String currentChar = String.valueOf(group.charAt(i));
                CodigoToken token = CodigoToken.BuscarTokenPorString(currentChar);
                if(token == null){
                    break;
                }
                tokensRestantes.add(new TokenEncontrado(token.getCodigo(),token.getTokenString(), token.getTokenDescricao()));
                removerNumeroDaFrase(fraseTemp,Pattern.quote(currentChar));
            }

            System.out.println("Outros Operadores encontrado: " + group);
        }

        return fraseTemp;
    }

    public static String identificarComentarios(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;

        Pattern pattern = Pattern.compile(REGEX_COMENTARIO);
        Matcher matcher = pattern.matcher(fraseTemp);

        while (matcher.find()) {
            String group = matcher.group();
            group = group.replaceAll("\\s", "");
            StringBuilder sb = new StringBuilder();
            boolean isComment = false;
            for (int i = 0; i < group.length(); i++) {
                sb.append(group.charAt(i)); // Adiciona o caractere ao StringBuilder
                if (sb.length() >= 2) { // Verifica se o StringBuilder tem pelo menos dois caracteres
                    String sbStr = sb.toString();
                    if(sbStr.equals("/*")){
                        isComment = true;
                    }
                    if(!isComment){
                        CodigoToken token = CodigoToken.BuscarTokenPorString(sbStr);
                        if(token != null){
                            tokensRestantes.add(new TokenEncontrado(token.getCodigo(),"'" + matcher.group() + "'", token.getTokenDescricao()));
                            removerNumeroDaFrase(fraseTemp,matcher.group());
                            sb.setLength(0); // Limpa o StringBuilder quando um token é encontrado

                            // Adiciona o código para percorrer até o final da linha e apagar o restante dos caracteres
                            int index = i + 1;
                            while(index < group.length()){
                                group = group.substring(0, index);
                                index++;
                            }
                            break; // Sai do loop for
                        }
                    }
                    if(isComment){
                        String tokenStr = sbStr.substring(0, 2) + sbStr.substring(sbStr.length() - 2);
                        CodigoToken token = CodigoToken.BuscarTokenPorString(tokenStr);
                        if(token != null && !sbStr.equals("/*/")){
                            tokensRestantes.add(new TokenEncontrado(token.getCodigo(),"'" + matcher.group() + "'", token.getTokenDescricao()));
                            removerNumeroDaFrase(fraseTemp,matcher.group());
                            isComment = false;
                            sb.setLength(0); // Limpa o StringBuilder quando o fim do comentário é encontrado
                        }
                    }
                }
            }

            fraseTemp = fraseTemp.replaceFirst(Pattern.quote(matcher.group()), "");
            System.out.println("Comentarios encontrado: " + matcher.group());
        }

        return fraseTemp;
    }

        public static String identificarNumeroInteiro(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;
        StringBuilder numeroAtual = new StringBuilder();
        for (int i = 0; i < fraseTemp.length(); i++) {
            char c = fraseTemp.charAt(i);
            // Verifica se o caractere é um dígito
            if (Character.isDigit(c)) {
                // Ignora os zeros à esquerda
                if (c != '0' || numeroAtual.length() > 0) {
                    numeroAtual.append(c);
                }
            } else if (c == '-') { // Verifica se o caractere é um sinal negativo
                // Se houver um sinal negativo e não houver número acumulado, adiciona ao número
                // atual
                if (numeroAtual.length() == 0) {
                    numeroAtual.append(c);
                } else {
                    // Se houver um número acumulado, consideramos que encontramos um token anterior
                    // Adicionamos esse token e resetamos o número acumulado
                    int numeroInteiro = Integer.parseInt(numeroAtual.toString());
                    if (numeroInteiro <= 500000) {
                        tokensRestantes.add(new TokenEncontrado(CodigoToken.NUMERO_INTEIRO.getCodigo(),
                                numeroAtual.toString(), CodigoToken.NUMERO_INTEIRO.getTokenDescricao()));
                    }
                    numeroAtual.setLength(0);
                }
            } else if (numeroAtual.length() > 0) { // Se houver um número acumulado e o caractere atual não for um
                                                   // dígito
                int numeroInteiro = Integer.parseInt(numeroAtual.toString());
                if (numeroInteiro <= 500000) {
                    tokensRestantes.add(new TokenEncontrado(CodigoToken.NUMERO_INTEIRO.getCodigo(),
                            numeroAtual.toString(), CodigoToken.NUMERO_INTEIRO.getTokenDescricao()));
                }
                // Remove o número inteiro encontrado da frase
                fraseTemp = removerNumeroDaFrase(fraseTemp, numeroAtual.toString());
                numeroAtual.setLength(0);
            }
        }
        // Verifica se ainda há um número acumulado após percorrer toda a string
        if (numeroAtual.length() > 0) {
            int numeroInteiro = Integer.parseInt(numeroAtual.toString());
            if (numeroInteiro <= 500000) {
                tokensRestantes.add(new TokenEncontrado(CodigoToken.NUMERO_INTEIRO.getCodigo(), numeroAtual.toString(),
                        CodigoToken.NUMERO_INTEIRO.getTokenDescricao()));
            }
            // Remove o número inteiro encontrado da frase
            fraseTemp = removerNumeroDaFrase(fraseTemp, numeroAtual.toString());
        }

        return fraseTemp;
    }

    public static String identificarNumeroFloat(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;
        StringBuilder numeroAtual = new StringBuilder();
        boolean temPontoDecimal = false;
        boolean numeroEncontrado = false;
        int posicaoInicioNumero = -1;
        int i = 0;
        while (i < fraseTemp.length()) {
            char c = fraseTemp.charAt(i);
            if (Character.isDigit(c)) {
                if (!numeroEncontrado) {
                    posicaoInicioNumero = i;
                }
                numeroAtual.append(c);
                numeroEncontrado = true;
            } else if (c == '.' && !temPontoDecimal) {
                numeroAtual.append(c);
                temPontoDecimal = true;
            } else if (numeroEncontrado && c != '.' && c != '-' && !Character.isDigit(c)) {
                if (temPontoDecimal) {
                    String numeroFloatString = extrairParteFloat(numeroAtual.toString());
                    if (numeroFloatString != null) {
                        float numeroFloat = Float.parseFloat(numeroFloatString);
                        if (numeroFloat <= 500000) {
                            tokensRestantes.add(new TokenEncontrado(CodigoToken.NUMERO_FLOAT.getCodigo(),
                                    numeroFloatString, CodigoToken.NUMERO_FLOAT.getTokenDescricao()));
                            // Remove o número float encontrado da frase
                            fraseTemp = removerNumeroDaFrase(fraseTemp, numeroFloatString);
                            // Atualiza o índice para o próximo caractere após o número float
                            i = posicaoInicioNumero;
                            // Reseta as variáveis
                            numeroAtual.setLength(0);
                            temPontoDecimal = false;
                            numeroEncontrado = false;
                            posicaoInicioNumero = -1;
                            continue; // Avança para o próximo caractere sem incrementar i
                        }
                    }
                }
                numeroAtual.setLength(0);
                temPontoDecimal = false;
                numeroEncontrado = false;
                posicaoInicioNumero = -1;
            } else if (c == '-' && numeroAtual.length() == 0) {
                numeroAtual.append(c);
            } else if (numeroAtual.length() > 0 && (c == '-' || !Character.isDigit(c))) {
                if (temPontoDecimal) {
                    String numeroFloatString = extrairParteFloat(numeroAtual.toString());
                    if (numeroFloatString != null) {
                        float numeroFloat = Float.parseFloat(numeroFloatString);
                        if (numeroFloat <= 500000) {
                            tokensRestantes.add(new TokenEncontrado(CodigoToken.NUMERO_FLOAT.getCodigo(),
                                    numeroFloatString, CodigoToken.NUMERO_FLOAT.getTokenDescricao()));
                            // Remove o número float encontrado da frase
                            fraseTemp = removerNumeroDaFrase(fraseTemp, numeroFloatString);
                            // Atualiza o índice para o próximo caractere após o número float
                            i = posicaoInicioNumero;
                            // Reseta as variáveis
                            numeroAtual.setLength(0);
                            temPontoDecimal = false;
                            numeroEncontrado = false;
                            posicaoInicioNumero = -1;
                            continue; // Avança para o próximo caractere sem incrementar i
                        }
                    }
                }
                numeroAtual.setLength(0);
                temPontoDecimal = false;
                numeroEncontrado = false;
                posicaoInicioNumero = -1;
            }
            i++;
        }
        // Verifica se ainda há um número acumulado após percorrer toda a string
        if (temPontoDecimal && numeroAtual.length() > 0) {
            String numeroFloatString = extrairParteFloat(numeroAtual.toString());
            if (numeroFloatString != null) {
                float numeroFloat = Float.parseFloat(numeroFloatString);
                if (numeroFloat <= 500000) {
                    tokensRestantes.add(new TokenEncontrado(CodigoToken.NUMERO_FLOAT.getCodigo(), numeroFloatString,
                            CodigoToken.NUMERO_FLOAT.getTokenDescricao()));
                    // Remove o número float encontrado da frase
                    fraseTemp = removerNumeroDaFrase(fraseTemp, numeroFloatString);
                }
            }
        }
        return fraseTemp;
    }

    // Função para extrair a parte do número float com até 3 dígitos após o ponto
    // decimal
    private static String extrairParteFloat(String numero) {
        int posicaoPonto = numero.indexOf(".");
        if (posicaoPonto != -1) {
            return numero.substring(0, posicaoPonto + 4); // Acrescenta 4 para incluir o ponto e os três dígitos após o
                                                          // ponto
        }
        return null;
    }

    private static String removerNumeroDaFrase(String frase, String numero) {
        int index = frase.indexOf(numero);
        if (index != -1) {
            // Encontra o índice do início da parte do número float na frase
            int inicio = index;
            while (inicio > 0 && Character.isDigit(frase.charAt(inicio - 1))) {
                inicio--;
            }
            int fim = index + numero.length();

            // Remove a parte do número float encontrada da frase
            return frase.substring(0, inicio) + frase.substring(fim);
        }
        return frase;
    }

    public static String identificarChar(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;
        int posicaoInicioChar = fraseTemp.indexOf("'");
        while (posicaoInicioChar != -1) {
            int posicaoFimChar = fraseTemp.indexOf("'", posicaoInicioChar + 1);
            if (posicaoFimChar != -1 && posicaoFimChar - posicaoInicioChar == 2) {
                // Encontra o caractere char
                String charEncontrado = fraseTemp.substring(posicaoInicioChar + 1, posicaoFimChar);
                // Adiciona o token na lista de tokens
                tokensRestantes.add(new TokenEncontrado(CodigoToken.NOME_CHAR.getCodigo(), "'" + charEncontrado + "'",
                        CodigoToken.NOME_CHAR.getTokenDescricao()));
                // Remove o caractere char encontrado da frase
                fraseTemp = fraseTemp.substring(0, posicaoInicioChar) + fraseTemp.substring(posicaoFimChar + 1);
            }
            // Busca o próximo caractere char a partir da posição seguinte ao fim do último
            // encontrado
            posicaoInicioChar = fraseTemp.indexOf("'", posicaoFimChar + 1);
        }
        return fraseTemp;
    }

    public static String identificarLiteral(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;
        int posicaoInicioString = fraseTemp.indexOf("\"");
        while (posicaoInicioString != -1) {
            int posicaoFimString = fraseTemp.indexOf("'", posicaoInicioString + 1);
            if (posicaoFimString != -1) {
                // Encontra a string entre aspas
                String stringEncontrada = fraseTemp.substring(posicaoInicioString + 1, posicaoFimString);
                // Adiciona o token na lista de tokens
                tokensRestantes
                        .add(new TokenEncontrado(CodigoToken.LITERAL.getCodigo(), "\"" + stringEncontrada + "\"",
                                CodigoToken.LITERAL.getTokenDescricao()));
                // Remove a string entre aspas encontrada da frase
                fraseTemp = fraseTemp.substring(0, posicaoInicioString) + fraseTemp.substring(posicaoFimString + 1);
            }
            // Busca a próxima string entre aspas a partir da posição seguinte ao fim da
            // última encontrada
            posicaoInicioString = fraseTemp.indexOf("\"", posicaoFimString + 1);
        }
        return fraseTemp;
    }

    public static String identificarString(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;
        int posicaoInicioString = fraseTemp.indexOf("'");
        while (posicaoInicioString != -1) {
            int posicaoFimString = fraseTemp.indexOf("'", posicaoInicioString + 1);
            if (posicaoFimString != -1) {
                // Encontra a string entre aspas
                String stringEncontrada = fraseTemp.substring(posicaoInicioString + 1, posicaoFimString);
                // Adiciona o token na lista de tokens
                tokensRestantes
                        .add(new TokenEncontrado(CodigoToken.NOME_STRING.getCodigo(), "'" + stringEncontrada + "'",
                                CodigoToken.NOME_STRING.getTokenDescricao()));
                // Remove a string entre aspas encontrada da frase
                fraseTemp = fraseTemp.substring(0, posicaoInicioString) + fraseTemp.substring(posicaoFimString + 1);
            }
            // Busca a próxima string entre aspas a partir da posição seguinte ao fim da
            // última encontrada
            posicaoInicioString = fraseTemp.indexOf("'", posicaoFimString + 1);
        }
        return fraseTemp;
    }

    public static String identificarDeclaracaoVariavel(String frase, ArrayList<TokenEncontrado> tokensRestantes) {
        String fraseTemp = frase;
        int posicaoInicio = 0;

        // Loop para identificar cada possível variável na frase
        while (posicaoInicio < fraseTemp.length()) {
            // Encontra o próximo caractere alfabético
            while (posicaoInicio < fraseTemp.length() && !Character.isLetter(fraseTemp.charAt(posicaoInicio))) {
                posicaoInicio++;
            }

            // Verifica se o final da frase foi alcançado
            if (posicaoInicio == fraseTemp.length()) {
                break;
            }

            // Encontra o fim da variável (até 15 caracteres ou o próximo não alfanumérico)
            int posicaoFim = posicaoInicio + 1;
            while (posicaoFim < fraseTemp.length() && posicaoFim - posicaoInicio <= 15 &&
                    Character.isLetterOrDigit(fraseTemp.charAt(posicaoFim))) {
                posicaoFim++;
            }

            // Verifica se a variável possui pelo menos um caractere
            if (posicaoFim > posicaoInicio + 1) {
                // Obtém a variável encontrada
                String variavel = fraseTemp.substring(posicaoInicio, posicaoFim);
                // Adiciona o token na lista de tokens
                tokensRestantes.add(new TokenEncontrado(CodigoToken.NOME_VARIAVEL.getCodigo(), variavel,
                        CodigoToken.NOME_VARIAVEL.getTokenDescricao()));
            }

            // Atualiza a posição de início para o próximo caractere após a variável
            // encontrada
            posicaoInicio = posicaoFim;
        }

        return fraseTemp;
    }

    // Função para obter o caminho do arquivo de saída na mesma pasta do arquivo de
    // entrada
    public static String obterCaminhoSaida(String caminhoArquivoEntrada) {
        File arquivoEntrada = new File(caminhoArquivoEntrada);
        String pastaEntrada = arquivoEntrada.getParent();
        return pastaEntrada + File.separator + "saida.txt";
    }

    // Função para escrever os tokens encontrados em um arquivo
    public static void escreverArquivo(String caminhoArquivoSaida, ArrayList<TokenEncontrado> tokens) {
        try (FileWriter writer = new FileWriter(caminhoArquivoSaida)) {
            writer.write("Tokens encontrados:\n");
            for (TokenEncontrado token : tokens) {
                writer.write("Token: ( " + token.codigoToken + " - " + token.descricaoToken + " ) Palavra: "
                        + token.palavra + "\n");
            }
            System.out.println("Os tokens foram escritos com sucesso no arquivo " + caminhoArquivoSaida);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro: Não foi possível escrever os tokens no arquivo " + caminhoArquivoSaida);
        }
    }
}