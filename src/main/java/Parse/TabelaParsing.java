package Parse;

import java.util.HashMap;


public class TabelaParsing {

    HashMap<Integer, HashMap<Integer, Integer>> tabParsing;

    public TabelaParsing() {
        tabParsing = new HashMap<>();
        // Adicionando todas as regras fornecidas na tabela
        adicionarRegra(0, 0, 2);  // BLOCO, Void -> 2

        adicionarRegra(1, 0, 2);  // DCLVAR, Void -> 2
        adicionarRegra(1, 1, 7);  // DCLVAR, NomeVariavel -> 7
        adicionarRegra(1, 3, 3);  // DCLVAR, Numerointeiro -> 3
        adicionarRegra(1, 4, 3);  // DCLVAR, Numerofloat -> 3

        adicionarRegra(2, 1, 41); // REPIDENT, NomeVariavel -> 41
        adicionarRegra(2, 3, 38); // REPIDENT, Numerointeiro -> 38
        adicionarRegra(2, 4, 39); // REPIDENT, Numerofloat -> 39

        adicionarRegra(3, 9, 13); // TIPO, Integer -> 13
        adicionarRegra(3, 10, 24); // TIPO, Char -> 24
        adicionarRegra(3, 11, 18); // TIPO, Float -> 18
        adicionarRegra(3, 12, 3);  // TIPO, String -> 3

        adicionarRegra(4, 1, 9);   // LDVAR, NomeVariavel -> 9
        adicionarRegra(4, 37, 37); // LDVAR, 37 -> 37
        adicionarRegra(4, 19, 19); // LDVAR, 19 -> 19

        adicionarRegra(5, 1, 9);   // LID, NomeVariavel -> 9

        adicionarRegra(6, 9, 13);  // DCLFUNC, Integer -> 13
        adicionarRegra(6, 10, 24); // DCLFUNC, Char -> 24
        adicionarRegra(6, 11, 18); // DCLFUNC, Float -> 18
        adicionarRegra(6, 12, 3);  // DCLFUNC, String -> 3

        adicionarRegra(7, 0, 9);   // TIPO_RETORNO, Void -> 9
        adicionarRegra(7, 9, 13);  // TIPO_RETORNO, Integer -> 13
        adicionarRegra(7, 10, 24); // TIPO_RETORNO, Char -> 24
        adicionarRegra(7, 11, 18); // TIPO_RETORNO, Float -> 18
        adicionarRegra(7, 12, 3);  // TIPO_RETORNO, String -> 3

        adicionarRegra(8, 0, 9);   // VALORRETORNO, Void -> 9
        adicionarRegra(8, 1, 5);   // VALORRETORNO, NomeVariavel -> 5
        adicionarRegra(8, 3, 6);   // VALORRETORNO, Numerointeiro -> 6
        adicionarRegra(8, 4, 8);   // VALORRETORNO, Numerofloat -> 8
        adicionarRegra(8, 12, 10); // VALORRETORNO, String -> 10
        adicionarRegra(8, 43, 43); // VALORRETORNO, 43 -> 43
        adicionarRegra(8, 38, 38); // VALORRETORNO, 38 -> 38
        adicionarRegra(8, 36, 36); // VALORRETORNO, 36 -> 36
        adicionarRegra(8, 19, 19); // VALORRETORNO, 19 -> 19

        adicionarRegra(9, 10, 44); // DEFPAR, Char -> 44
        adicionarRegra(9, 37, 37); // DEFPAR, 37 -> 37
        adicionarRegra(9, 19, 19); // DEFPAR, 19 -> 19

        adicionarRegra(10, 9, 13); // PARAM, Integer -> 13
        adicionarRegra(10, 10, 24); // PARAM, Char -> 24
        adicionarRegra(10, 11, 18); // PARAM, Float -> 18
        adicionarRegra(10, 12, 3);  // PARAM, String -> 3

        adicionarRegra(11, 43, 43); // LPARAM, 43 -> 43
        adicionarRegra(11, 38, 38); // LPARAM, 38 -> 38

        adicionarRegra(12, 15, 14); // CORPO, If -> 14

        adicionarRegra(13, 1, 9);   // REPCOMANDO, NomeVariavel -> 9
        adicionarRegra(13, 15, 15); // REPCOMANDO, If -> 15
        adicionarRegra(13, 1, 1);   // REPCOMANDO, While -> 1
        adicionarRegra(13, 17, 17); // REPCOMANDO, For -> 17
        adicionarRegra(13, 21, 21); // REPCOMANDO, Do -> 21
        adicionarRegra(13, 23, 23); // REPCOMANDO, Cin -> 23
        adicionarRegra(13, 22, 22); // REPCOMANDO, Cout -> 22
        adicionarRegra(13, 37, 37); // REPCOMANDO, 37 -> 37
        adicionarRegra(13, 19, 19); // REPCOMANDO, 19 -> 19

        adicionarRegra(14, 1, 9);   // COMANDO, NomeVariavel -> 9
        adicionarRegra(14, 8, 8);   // COMANDO, 8 -> 8
        adicionarRegra(14, 10, 10); // COMANDO, 10 -> 10
        adicionarRegra(14, 38, 38); // COMANDO, 38 -> 38
        adicionarRegra(14, 36, 36); // COMANDO, 36 -> 36
        adicionarRegra(14, 15, 15); // COMANDO, If -> 15
        adicionarRegra(14, 1, 1);   // COMANDO, While -> 1
        adicionarRegra(14, 17, 17); // COMANDO, For -> 17
        adicionarRegra(14, 21, 21); // COMANDO, Do -> 21
        adicionarRegra(14, 23, 23); // COMANDO, Cin -> 23
        adicionarRegra(14, 22, 22); // COMANDO, Cout -> 22
        adicionarRegra(14, 19, 19); // COMANDO, 19 -> 19
        adicionarRegra(14, 25, 25); // COMANDO, Cin -> 25

        adicionarRegra(15, 38, 38); // ELSEPARTE, 38 -> 38
        adicionarRegra(15, 36, 36); // ELSEPARTE, 36 -> 36
        adicionarRegra(15, 20, 20); // ELSEPARTE, 20 -> 20
        adicionarRegra(15, 19, 19); // ELSEPARTE, 19 -> 19

        adicionarRegra(16, 29, 29); // COMPARACAO, 29 -> 29
        adicionarRegra(16, 46, 46); // COMPARACAO, 46 -> 46
        adicionarRegra(16, 28, 28); // COMPARACAO, 28 -> 28
        adicionarRegra(16, 33, 33); // COMPARACAO, 33 -> 33
        adicionarRegra(16, 27, 27); // COMPARACAO, 27 -> 27
        adicionarRegra(16, 31, 31); // COMPARACAO, 31 -> 31

        adicionarRegra(17, 1, 9);   // CONTCOMPARACAO, NomeVariavel -> 9
        adicionarRegra(17, 5, 5);   // CONTCOMPARACAO, 5 -> 5
        adicionarRegra(17, 6, 6);   // CONTCOMPARACAO, 6 -> 6
        adicionarRegra(17, 8, 8);   // CONTCOMPARACAO, 8 -> 8
        adicionarRegra(17, 10, 10); // CONTCOMPARACAO, 10 -> 10

        adicionarRegra(18, 34, 34); // INCREMENTO, 34 -> 34
        adicionarRegra(18, 47, 47); // INCREMENTO, 47 -> 47

        adicionarRegra(19, 1, 41);  // SEQUENCIA, NomeVariavel -> 41
        adicionarRegra(19, 3, 38);  // SEQUENCIA, Numerointeiro -> 38
        adicionarRegra(19, 4, 19);  // SEQUENCIA, Numerofloat -> 19
        adicionarRegra(19, 32, 32); // SEQUENCIA, If -> 32

        adicionarRegra(20, 1, 9);   // EXPRESSAO, NomeVariavel -> 9
        adicionarRegra(20, 10, 44); // EXPRESSAO, Char -> 44
        adicionarRegra(20, 25, 25); // EXPRESSAO, Cin -> 25

        adicionarRegra(21, 43, 43); // REPEXP, 43 -> 43
        adicionarRegra(21, 38, 38); // REPEXP, 38 -> 38
        adicionarRegra(21, 36, 36); // REPEXP, 36 -> 36
        adicionarRegra(21, 19, 19); // REPEXP, 19 -> 19
        adicionarRegra(21, 35, 35); // REPEXP, 35 -> 35
        adicionarRegra(21, 48, 48); // REPEXP, 48 -> 48

        adicionarRegra(22, 1, 9);   // TERMO, NomeVariavel -> 9
        adicionarRegra(22, 5, 5);   // TERMO, 5 -> 5
        adicionarRegra(22, 6, 6);   // TERMO, 6 -> 6
        adicionarRegra(22, 8, 8);   // TERMO, 8 -> 8
        adicionarRegra(22, 10, 10); // TERMO, 10 -> 10
        adicionarRegra(22, 44, 44); // TERMO, 44 -> 44

        adicionarRegra(23, 42, 42); // REPTERMO, 42 -> 42
        adicionarRegra(23, 43, 43); // REPTERMO, 43 -> 43
        adicionarRegra(23, 38, 38); // REPTERMO, 38 -> 38
        adicionarRegra(23, 36, 36); // REPTERMO, 36 -> 36
        adicionarRegra(23, 19, 19); // REPTERMO, 19 -> 19
        adicionarRegra(23, 35, 35); // REPTERMO, 35 -> 35
        adicionarRegra(23, 48, 48); // REPTERMO, 48 -> 48
        adicionarRegra(23, 30, 30); // REPTERMO, 30 -> 30
        adicionarRegra(23, 40, 40); // REPTERMO, 40 -> 40

        adicionarRegra(24, 1, 9);   // FATOR, NomeVariavel -> 9
        adicionarRegra(24, 5, 5);   // FATOR, 5 -> 5
        adicionarRegra(24, 6, 6);   // FATOR, 6 -> 6
        adicionarRegra(24, 8, 8);   // FATOR, 8 -> 8
        adicionarRegra(24, 10, 10); // FATOR, 10 -> 10
        adicionarRegra(24, 44, 44); // FATOR, 44 -> 44
    }

    public void adicionarRegra(int linha, int coluna, int regra) {
        // Se o HashMap para 'x' não existir, crie um novo
        if (!tabParsing.containsKey(linha)) {
            tabParsing.put(linha, new HashMap<>());
        }

        tabParsing.get(linha).put(coluna, regra);
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

