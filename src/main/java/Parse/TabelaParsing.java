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

        adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 1);
        adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.I.getCodigo(), 2);// DCLVAR, NomeVariavel -> 7
        //adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 3);  // DCLVAR, Numerointeiro -> 3
        //adicionarRegra(NaoTerminais.DCLVAR.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 3);  // DCLVAR, Numerofloat -> 3

        adicionarRegra(NaoTerminais.REPIDENT.getCodigo(), CodigoToken.I.getCodigo(), 3);  // REPIDENT, NomeVariavel -> 41
        //adicionarRegra(NaoTerminais.REPIDENT.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 38);  // REPIDENT, Numerointeiro -> 38
        adicionarRegra(NaoTerminais.REPIDENT.getCodigo(), CodigoToken.VIRGULA.getCodigo(), 4);  // REPIDENT, Numerofloat -> 39

        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.INTEGER.getCodigo(), 5);  // TIPO, Integer -> 13
        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.CHAR.getCodigo(), 8);  // TIPO, Char -> 24
        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.FLOAT.getCodigo(), 6);  // TIPO, Float -> 18
        adicionarRegra(NaoTerminais.TIPO.getCodigo(), CodigoToken.STRING.getCodigo(), 7);  // TIPO, String -> 3

        adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 9);  // LDVAR, NomeVariavel -> 9
        //adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 37);  // LDVAR, 37 -> 37
        //adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.FIM.getCodigo(), 19);  // LDVAR, 19 -> 19
        adicionarRegra(NaoTerminais.LDVAR.getCodigo(), CodigoToken.I.getCodigo(), 10);  // LDVAR, NomeVariavel -> 9


        adicionarRegra(NaoTerminais.LID.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 11);  // LID, NomeVariavel -> 9

        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.INTEGER.getCodigo(), 13);  // DCLFUNC, Integer -> 13
        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.CHAR.getCodigo(), 15);  // DCLFUNC, Char -> 24
        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.FLOAT.getCodigo(), 16);  // DCLFUNC, Float -> 18
        adicionarRegra(NaoTerminais.DCLFUNC.getCodigo(), CodigoToken.STRING.getCodigo(), 17);  // DCLFUNC, String -> 3

        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.VOID.getCodigo(), 14);  // TIPO_RETORNO, Void -> 9
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.INTEGER.getCodigo(), 13);  // TIPO_RETORNO, Integer -> 13
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.CHAR.getCodigo(), 15);  // TIPO_RETORNO, Char -> 24
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.FLOAT.getCodigo(), 16);  // TIPO_RETORNO, Float -> 18
        adicionarRegra(NaoTerminais.TIPO_RETORNO.getCodigo(), CodigoToken.STRING.getCodigo(), 17);  // TIPO_RETORNO, String -> 3

        //adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.VOID.getCodigo(), 9);  // VALORRETORNO, Void -> 9
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 21);  // VALORRETORNO, NomeVariavel -> 5
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 19);  // VALORRETORNO, Numerointeiro -> 6
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 20);  // VALORRETORNO, Numerofloat -> 8
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.STRING.getCodigo(), 23);  // VALORRETORNO, String -> 10
        adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.I.getCodigo(), 24);  // VALORRETORNO, 43 -> 43
        //adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38);  // VALORRETORNO, 38 -> 38
        //adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36);  // VALORRETORNO, 36 -> 36
        //adicionarRegra(NaoTerminais.VALORRETORNO.getCodigo(), CodigoToken.FIM.getCodigo(), 19);  // VALORRETORNO, 19 -> 19

        //adicionarRegra(NaoTerminais.DEFPAR.getCodigo(), CodigoToken.CHAR.getCodigo(), 44);  // DEFPAR, Char -> 44
        adicionarRegra(NaoTerminais.DEFPAR.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 26);  // DEFPAR, 37 -> 37
        adicionarRegra(NaoTerminais.DEFPAR.getCodigo(), CodigoToken.I.getCodigo(), 25);  // DEFPAR, 19 -> 19

        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.INTEGER.getCodigo(), 5);  // PARAM, Integer -> 13
        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.CHAR.getCodigo(), 8);  // PARAM, Char -> 24
        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.FLOAT.getCodigo(), 6);  // PARAM, Float -> 18
        adicionarRegra(NaoTerminais.PARAM.getCodigo(), CodigoToken.STRING.getCodigo(), 7);  // PARAM, String -> 3

        adicionarRegra(NaoTerminais.LPARAM.getCodigo(), CodigoToken.I.getCodigo(), 30);  // LPARAM, 43 -> 43
        adicionarRegra(NaoTerminais.LPARAM.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 29);  // LPARAM, 38 -> 38

        adicionarRegra(NaoTerminais.CORPO.getCodigo(), CodigoToken.INICIO.getCodigo(), 30);  // CORPO, If -> NaoTerminais.COMANDO.getCodigo()
        
        adicionarRegra(NaoTerminais.PARAMETROS.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 39);

        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 32);  // REPCOMANDO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 32);  // REPCOMANDO, Cin -> 23
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.CALL_FUNCAO.getCodigo(), 32);  // REPCOMANDO, Cout -> 22
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 32);  // REPCOMANDO, 37 -> 37
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.I.getCodigo(), 31);  // REPCOMANDO, 19 -> 19
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.WHILE.getCodigo(), 32); 
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.FOR.getCodigo(), 32);
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.DO.getCodigo(), 32);
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.CIN.getCodigo(), 32); 
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.COUT.getCodigo(), 32);
        adicionarRegra(NaoTerminais.REPCOMANDO.getCodigo(), CodigoToken.IF.getCodigo(), 32);

        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 33);   // COMANDO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 35);   // COMANDO, 8 -> 8
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 34); // COMANDO, 10 -> 10
        //adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // COMANDO, 38 -> 38
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 47); // COMANDO, 36 -> 36
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.IF.getCodigo(), 47); // COMANDO, If -> 15
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.WHILE.getCodigo(), 50);   // COMANDO, While -> 1
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.FOR.getCodigo(), 62); // COMANDO, For -> 17
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.DO.getCodigo(), 65); // COMANDO, Do -> 21
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.CIN.getCodigo(), 66); // COMANDO, Cin -> 23
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.COUT.getCodigo(), 67); // COMANDO, Cout -> 22
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.I.getCodigo(), 36); // COMANDO, 19 -> 19
        adicionarRegra(NaoTerminais.COMANDO.getCodigo(), CodigoToken.CALL_FUNCAO.getCodigo(), 37); // COMANDO, Cin -> 25

        //adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // ELSEPARTE, 38 -> 38
        //adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36); // ELSEPARTE, 36 -> 36
        adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.ELSE.getCodigo(), 48); // ELSEPARTE, 20 -> 20
        adicionarRegra(NaoTerminais.ELSEPARTE.getCodigo(), CodigoToken.I.getCodigo(), 49); // ELSEPARTE, 19 -> 19

        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.IGUAL.getCodigo(), 51); // COMPARACAO, 29 -> 29
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.DIFERENTE.getCodigo(), 52); // COMPARACAO, 46 -> 46
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MAIOR.getCodigo(), 53); // COMPARACAO, 28 -> 28
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MENOR.getCodigo(), 55); // COMPARACAO, 33 -> 33
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MAIOR_IGUAL.getCodigo(), 54); // COMPARACAO, 27 -> 27
        adicionarRegra(NaoTerminais.COMPARACAO.getCodigo(), CodigoToken.MENOR_IGUAL.getCodigo(), 56); // COMPARACAO, 31 -> 31

        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 59);   // CONTCOMPARACAO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 57);   // CONTCOMPARACAO, 5 -> 5
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 58);   // CONTCOMPARACAO, 6 -> 6
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 60);   // CONTCOMPARACAO, 8 -> 8
        adicionarRegra(NaoTerminais.CONTCOMPARACAO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 61); // CONTCOMPARACAO, 10 -> 10

        adicionarRegra(NaoTerminais.INCREMENTO.getCodigo(), CodigoToken.INCREMENTO.getCodigo(), 63); // INCREMENTO, 34 -> 34
        adicionarRegra(NaoTerminais.INCREMENTO.getCodigo(), CodigoToken.DECREMENTO.getCodigo(), 64); // INCREMENTO, 47 -> 47

        //adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.WHILE.getCodigo(), 41);  // SEQUENCIA, NomeVariavel -> 41
        //adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.STRING.getCodigo(), 38);  // SEQUENCIA, Numerointeiro -> 38
        adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 71);  // SEQUENCIA, Numerofloat -> 19
        adicionarRegra(NaoTerminais.SEQUENCIA.getCodigo(), CodigoToken.I.getCodigo(), 70); // SEQUENCIA, If -> 32

        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.CALL_FUNCAO.getCodigo(), 73); // EXPRESSAO, Cin -> 25
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 72);   // CONTCOMPARACAO, NomeVariavel -> 9
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 72);   // CONTCOMPARACAO, 5 -> 5
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 72);   // CONTCOMPARACAO, 6 -> 6
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 72);   // CONTCOMPARACAO, 8 -> 8
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 72); // CONTCOMPARACAO, 10 -> 10
        adicionarRegra(NaoTerminais.EXPRESSAO.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 72); // TERMO, 44 -> 44

        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.I.getCodigo(), 76); // REPEXP, 19 -> 19
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.ADICAO.getCodigo(), 74); // REPEXP, 35 -> 35
        adicionarRegra(NaoTerminais.REPEXP.getCodigo(), CodigoToken.SUBTRACAO.getCodigo(), 75); // REPEXP, 48 -> 48

        adicionarRegra(NaoTerminais.SEQCOUT.getCodigo(), CodigoToken.MENOR_MENOR.getCodigo(), 69); 

        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 81);   // TERMO, 5 -> 5
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 82);   // TERMO, 6 -> 6
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 83);
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 85);   // TERMO, 8 -> 8
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 84); // TERMO, 10 -> 10
        adicionarRegra(NaoTerminais.TERMO.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 86); // TERMO, 44 -> 44

        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.MULTIPLICACAO.getCodigo(), 79); // REPTERMO, 42 -> 42
        //adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.FECHA_PARENTESES.getCodigo(), 43); // REPTERMO, 43 -> 43
        //adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.PONTO_VIRGULA.getCodigo(), 38); // REPTERMO, 38 -> 38
        //adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.FECHA_CHAVES.getCodigo(), 36); // REPTERMO, 36 -> 36
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.I.getCodigo(), 78); // REPTERMO, 19 -> 19
        //adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.ADICAO.getCodigo(), 35); // REPTERMO, 35 -> 35
        //adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.SUBTRACAO.getCodigo(), 48); // REPTERMO, 48 -> 48
        //adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.ATRIBUICAO.getCodigo(), 30); // REPTERMO, 30 -> 30
        adicionarRegra(NaoTerminais.REPTERMO.getCodigo(), CodigoToken.DIVISAO.getCodigo(), 80); // REPTERMO, 40 -> 40

        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 81);   // TERMO, 5 -> 5
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 82);   // TERMO, 6 -> 6
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 85);   // TERMO, 8 -> 8
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 84); // TERMO, 10 -> 10
        adicionarRegra(NaoTerminais.FATOR.getCodigo(), CodigoToken.ABRE_PARENTESES.getCodigo(), 86); // TERMO, 44 -> 44

        adicionarRegra(NaoTerminais.TPARAM.getCodigo(), CodigoToken.NUMERO_INTEIRO.getCodigo(), 42); 
        adicionarRegra(NaoTerminais.TPARAM.getCodigo(), CodigoToken.NOME_CHAR.getCodigo(), 45);  
        adicionarRegra(NaoTerminais.TPARAM.getCodigo(), CodigoToken.NUMERO_FLOAT.getCodigo(), 44);
        adicionarRegra(NaoTerminais.TPARAM.getCodigo(), CodigoToken.NOME_STRING.getCodigo(), 43); 
        adicionarRegra(NaoTerminais.TPARAM.getCodigo(), CodigoToken.NOME_VARIAVEL.getCodigo(), 46);
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

