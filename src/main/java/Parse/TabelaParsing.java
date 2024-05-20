package Parse;

import enuns.CodigoToken;
import enuns.NaoTerminais;

import java.util.HashMap;


public class TabelaParsing {

    HashMap<Integer, HashMap<Integer, Integer>> tabParsing;

    public TabelaParsing() {
        tabParsing = new HashMap<>();
        // Adicionando todas as regras fornecidas na tabela
        adicionarRegra(NaoTerminais.BLOCO.getCodigo(), CodigoToken.VOID.getCodigo(), 0);  // BLOCO, Void -> 2

        adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.VOID.getCodigo(), 2);  // DCLVAR, Void -> 2
        adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 7);  // DCLVAR, NomeVariavel -> 7
        adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 3);  // DCLVAR, Numerointeiro -> 3
        adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 3);  // DCLVAR, Numerofloat -> 3

        adicionarRegra(NaoTerminais.REPIDENT.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 41);  // REPIDENT, NomeVariavel -> 41
        adicionarRegra(NaoTerminais.REPIDENT.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 38);  // REPIDENT, Numerointeiro -> 38
        adicionarRegra(NaoTerminais.REPIDENT.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 39);  // REPIDENT, Numerofloat -> 39

        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.INTEGER.getCodigo(), 13);  // TIPO, Integer -> 13
        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.CHAR.getCodigo(), 24);  // TIPO, Char -> 24
        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.FLOAT.getCodigo(), 18);  // TIPO, Float -> 18
        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.STRING.getCodigo(), 3);  // TIPO, String -> 3

        adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 9);  // LDVAR, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 37);  // LDVAR, 37 -> 37
        adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.FIM.getCodigo(), 19);  // LDVAR, 19 -> 19

        adicionarRegra(NaoTerminais.LID.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 9);  // LID, NomeVariavel -> 9

        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.INTEGER.getCodigo(), 13);  // DCLFUNC, Integer -> 13
        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.CHAR.getCodigo(), 24);  // DCLFUNC, Char -> 24
        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.FLOAT.getCodigo(), 18);  // DCLFUNC, Float -> 18
        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.STRING.getCodigo(), 3);  // DCLFUNC, String -> 3

        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.VOID.getCodigo(), 9);  // TIPO_RETORNO, Void -> 9
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.INTEGER.getCodigo(), 13);  // TIPO_RETORNO, Integer -> 13
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.CHAR.getCodigo(), 24);  // TIPO_RETORNO, Char -> 24
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.FLOAT.getCodigo(), 18);  // TIPO_RETORNO, Float -> 18
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.STRING.getCodigo(), 3);  // TIPO_RETORNO, String -> 3

        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.VOID.getCodigo(), 9);  // VALORRETORNO, Void -> 9
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 5);  // VALORRETORNO, NomeVariavel -> 5
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 6);  // VALORRETORNO, Numerointeiro -> 6
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 8);  // VALORRETORNO, Numerofloat -> 8
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.STRING.getCodigo(), 10);  // VALORRETORNO, String -> 10
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.FECHA_PARENTESES.getCodigo(), 43);  // VALORRETORNO, 43 -> 43
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38);  // VALORRETORNO, 38 -> 38
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36);  // VALORRETORNO, 36 -> 36
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.FIM.getCodigo(), 19);  // VALORRETORNO, 19 -> 19

        adicionarRegra(NaoTerminais.DEFPAR.getCodigo(), CodigoToken.CHAR.getCodigo(), 44);  // DEFPAR, Char -> 44
        adicionarRegra(NaoTerminais.DEFPAR.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 37);  // DEFPAR, 37 -> 37
        adicionarRegra(NaoTerminais.DEFPAR.getCodigo(), CodigoToken.FIM.getCodigo(), 19);  // DEFPAR, 19 -> 19

        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.INTEGER.getCodigo(), 13);  // PARAM, Integer -> 13
        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.CHAR.getCodigo(), 24);  // PARAM, Char -> 24
        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.FLOAT.getCodigo(), 18);  // PARAM, Float -> 18
        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.STRING.getCodigo(), 3);  // PARAM, String -> 3

        adicionarRegra(NaoTerminais.LPARAM.getCodigo(), CodigoToken.FECHA_PARENTESES.getCodigo(), 43);  // LPARAM, 43 -> 43
        adicionarRegra(NaoTerminais.LPARAM.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38);  // LPARAM, 38 -> 38

        adicionarRegra(NaoTerminais.CORPO.getCodigo(), CodigoToken.IF.getCodigo(), NaoTerminais.COMANDO.getCodigo());  // CORPO, If -> NaoTerminais.COMANDO.getCodigo()

        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 9);  // REPCOMANDO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.IF.getCodigo(), 15);  // REPCOMANDO, If -> 15
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.WHILE.getCodigo(), 1);  // REPCOMANDO, While -> 1
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.FOR.getCodigo(), 17);  // REPCOMANDO, For -> 17
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.DO.getCodigo(), 21);  // REPCOMANDO, Do -> 21
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.CIN.getCodigo(), 23);  // REPCOMANDO, Cin -> 23
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.COUT.getCodigo(), 22);  // REPCOMANDO, Cout -> 22
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 37);  // REPCOMANDO, 37 -> 37
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.FIM.getCodigo(), 19);  // REPCOMANDO, 19 -> 19

        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 9);   // COMANDO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 8);   // COMANDO, 8 -> 8
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 10); // COMANDO, 10 -> 10
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // COMANDO, 38 -> 38
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36); // COMANDO, 36 -> 36
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.IF.getCodigo(), 15); // COMANDO, If -> 15
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.WHILE.getCodigo(), 1);   // COMANDO, While -> 1
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.FOR.getCodigo(), 17); // COMANDO, For -> 17
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.DO.getCodigo(), 21); // COMANDO, Do -> 21
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.CIN.getCodigo(), 23); // COMANDO, Cin -> 23
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.COUT.getCodigo(), 22); // COMANDO, Cout -> 22
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.FIM.getCodigo(), 19); // COMANDO, 19 -> 19
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.CALL_FUNCAO.getCodigo(), 25); // COMANDO, Cin -> 25

        adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // ELSEPARTE, 38 -> 38
        adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36); // ELSEPARTE, 36 -> 36
        adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.ELSE.getCodigo(), 20); // ELSEPARTE, 20 -> 20
        adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.FIM.getCodigo(), 19); // ELSEPARTE, 19 -> 19

        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.IGUAL.getCodigo(), 29); // COMPARACAO, 29 -> 29
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.DIFERENTE.getCodigo(), 46); // COMPARACAO, 46 -> 46
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MAIOR.getCodigo(), 28); // COMPARACAO, 28 -> 28
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MENOR.getCodigo(), 33); // COMPARACAO, 33 -> 33
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MAIOR_IGUAL.getCodigo(), 27); // COMPARACAO, 27 -> 27
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MENOR_IGUAL.getCodigo(), 31); // COMPARACAO, 31 -> 31

        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.WHILE.getCodigo(), 9);   // CONTCOMPARACAO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 5);   // CONTCOMPARACAO, 5 -> 5
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 6);   // CONTCOMPARACAO, 6 -> 6
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 8);   // CONTCOMPARACAO, 8 -> 8
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 10); // CONTCOMPARACAO, 10 -> 10

        adicionarRegra(NaoTerminais.INCREMENTO.getCodigo(), CodigoToken.INCREMENTO.getCodigo(), 34); // INCREMENTO, 34 -> 34
        adicionarRegra(NaoTerminais.INCREMENTO.getCodigo(), CodigoToken.DECREMENTO.getCodigo(), 47); // INCREMENTO, 47 -> 47

        adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.WHILE.getCodigo(), 41);  // SEQUENCIA, NomeVariavel -> 41
        adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.STRING.getCodigo(), 38);  // SEQUENCIA, Numerointeiro -> 38
        adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.RETURN.getCodigo(), 19);  // SEQUENCIA, Numerofloat -> 19
        adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.MENOR_MENOR.getCodigo(), 32); // SEQUENCIA, If -> 32

        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.WHILE.getCodigo(), 9);   // EXPRESSAO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 44); // EXPRESSAO, Char -> 44
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.CALL_FUNCAO.getCodigo(), 25); // EXPRESSAO, Cin -> 25

        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.FECHA_PARENTESES.getCodigo(), 43); // REPEXP, 43 -> 43
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // REPEXP, 38 -> 38
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36); // REPEXP, 36 -> 36
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.FIM.getCodigo(), 19); // REPEXP, 19 -> 19
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.ADICAO.getCodigo(), 35); // REPEXP, 35 -> 35
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.SUBTRACAO.getCodigo(), 48); // REPEXP, 48 -> 48

        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.WHILE.getCodigo(), 9);   // TERMO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 5);   // TERMO, 5 -> 5
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 6);   // TERMO, 6 -> 6
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 8);   // TERMO, 8 -> 8
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 10); // TERMO, 10 -> 10
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 44); // TERMO, 44 -> 44

        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.MULTIPLICACAO.getCodigo(), 42); // REPTERMO, 42 -> 42
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.FECHA_PARENTESES.getCodigo(), 43); // REPTERMO, 43 -> 43
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // REPTERMO, 38 -> 38
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36); // REPTERMO, 36 -> 36
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.FIM.getCodigo(), 19); // REPTERMO, 19 -> 19
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.ADICAO.getCodigo(), 35); // REPTERMO, 35 -> 35
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.SUBTRACAO.getCodigo(), 48); // REPTERMO, 48 -> 48
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.ATRIBUICAO.getCodigo(), 30); // REPTERMO, 30 -> 30
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.DIVISAO.getCodigo(), 40); // REPTERMO, 40 -> 40

        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.WHILE.getCodigo(), 9);   // FATOR, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 5);   // FATOR, 5 -> 5
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 6);   // FATOR, 6 -> 6
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 8);   // FATOR, 8 -> 8
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 10); // FATOR, 10 -> 10
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 44); // FATOR, 44 -> 44
    }

    public void adicionarRegra(int pilha, int codigoToken, int producao) {
        // Se o HashMap para 'x' não existir, crie um novo
        if (!tabParsing.containsKey(pilha)) {
            tabParsing.put(pilha, new HashMap<>());
        }

        tabParsing.get(pilha).put(codigoToken, producao);
    }

    public int getRegra(int x, int a) {
        if (!tabParsing.containsKey(x)) {
            return -1;
        }

        if (!tabParsing.get(x).containsKey(a)) {
            return -1;
        }

        // Retorne a regra
        return tabParsing.get(x).get(a);
    }
}

