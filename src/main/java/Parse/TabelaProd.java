package Parse;

import java.util.HashMap;

import javax.swing.ComboBoxEditor;

public class TabelaProd {
    HashMap<Integer, HashMap<Integer, Integer>> tabProd;

    static int var_while = 1;
    static int var_void = 2;
    static int var_string = 3;
    static int var_return = 4;
    static int var_numerointeiro = 5;
    static int var_numerofloat = 6;
    static int var_nomevariavel = 7;
    static int var_nomedochar = 8;
    static int var_nomedavariavel = 9;
    static int var_nomedastring = 10;
    static int var_main = 11;
    static int var_literal = 12;
    static int var_integer = 13;
    static int var_inicio = 14;
    static int var_if = 15;
    static int var_nulo = 16;
    static int var_for = 17;
    static int var_float = 18;
    static int var_fim = 19;
    static int var_else = 20;
    static int var_do = 21;
    static int var_cout = 22;
    static int var_cin = 23;
    static int var_char = 24;
    static int var_callfuncao = 25;
    static int var_maiormaior = 26;
    static int var_maiorigual = 27;
    static int var_maior = 28;
    static int var_comparar = 29;
    static int var_igual = 30;
    static int var_menorigual = 31;
    static int var_menormenor = 32;
    static int var_menor = 33;
    static int var_maismais = 34;
    static int var_mais = 35;
    static int var_fechachaves = 36;
    static int var_abrechaves = 37;
    static int var_pontovirgula = 38;
    static int var_doispontos = 39;
    static int var_divisao = 40;
    static int var_virgula = 41;
    static int var_multiplicacao = 42;
    static int var_fechaparenteses = 43;
    static int var_abreparenteses = 44;
    static int var_sifrao = 45;
    static int var_diferente = 46;
    static int var_menosmenos = 47;
    static int var_menos = 48;
    static int BLOCO = 49;
    static int DCLVAR = 50;
    static int DCLFUNC = 51;
    static int CORPO = 52;
    static int REPIDENT = 53;
    static int TIPO = 54;
    static int LDVAR = 55;
    static int REPIDEN = 56;
    static int LID = 57;
    static int TIPO_RETORNO = 58;
    static int DEFPAR = 59;
    static int VALORRETORNO = 60;
    static int PARAM = 61;
    static int LPARAM = 62;
    static int COMANDO = 63;
    static int REPCOMANDO = 64;
    static int EXPRESSAO = 65;
    static int PARAMETROS = 66;
    static int TPARAM = 67;
    static int REPPAR = 68;
    static int COMPARACAO = 69;
    static int ELSEPARTE = 70;
    static int CONTCOMPARACAO = 71;
    static int INCREMENTO = 72;
    static int SEQCOUT = 73;
    static int SEQUENCIA = 74;
    static int EXPSIMP = 75;
    static int REPEXPSIMP = 76;
    static int TERMO = 77;
    static int REPEXP = 78;
    static int FATOR = 79;
    static int REPTERMO = 80;

    public TabelaProd() {
        tabProd = new HashMap<>();
        // <BLOCO> ::= 'void' 'main' '{' <DCLVAR> <DCLFUNC> <CORPO> '}'
        adicionarRegra(0, 0, var_void);
        adicionarRegra(0, 1, var_main);
        adicionarRegra(0, 2, var_abrechaves);
        adicionarRegra(0, 3, DCLVAR);
        adicionarRegra(0, 4, DCLFUNC);
        adicionarRegra(0, 5, CORPO);
        adicionarRegra(0, 6, var_fechachaves);

        // <DCLVAR> ::= 'nomevariavel' <REPIDENT> ':' <TIPO> ';' <LDVAR>
        adicionarRegra(1, 0, var_nomevariavel);
        adicionarRegra(1, 1, REPIDENT);
        adicionarRegra(1, 2, var_doispontos);
        adicionarRegra(1, 3, TIPO);
        adicionarRegra(1, 4, var_pontovirgula);
        adicionarRegra(1, 5, LDVAR);

        // <DCLVAR> ::= î
        adicionarRegra(2, 0, var_nulo);

        // <REPIDENT> ::= î
        adicionarRegra(3, 0, var_nulo);

        // <REPIDENT> ::= ',' 'nomevariavel' <REPIDENT>
        adicionarRegra(4, 0, var_virgula);
        adicionarRegra(4, 1, var_nomevariavel);
        adicionarRegra(4, 3, REPIDENT);

        // <TIPO> ::= 'integer'
        adicionarRegra(5, 0, var_integer);

        // <TIPO> ::= 'float'
        adicionarRegra(6, 0, var_float);

        // <TIPO> ::= 'string'
        adicionarRegra(7, 0, var_string);

        // <TIPO> ::= 'char'
        adicionarRegra(8, 0, 24);

        // <LDVAR> ::= <LID> ':' <TIPO> ';' <LDVAR>
        adicionarRegra(9, 0, LID);
        adicionarRegra(9, 1, var_doispontos);
        adicionarRegra(9, 2, TIPO);
        adicionarRegra(9, 3, var_pontovirgula);
        adicionarRegra(9, 4, LDVAR);

        // <LDVAR> ::= î
        adicionarRegra(10, 0, var_nulo);

        // <LID> ::= 'nomevariavel' <REPIDENT>
        adicionarRegra(11, 0, var_nomevariavel);
        adicionarRegra(11, 1, REPIDENT);

        // <DCLFUNC> ::= <TIPO_RETORNO> 'nomevariavel' <DEFPAR> '{' <DCLVAR> <DCLFUNC>
        // <CORPO> 'return' '(' <VALORRETORNO> ')' '}' <DCLFUNC>
        adicionarRegra(12, 0, TIPO_RETORNO);
        adicionarRegra(12, 1, var_nomevariavel);
        adicionarRegra(12, 2, DEFPAR);
        adicionarRegra(12, 3, var_abrechaves);
        adicionarRegra(12, 4, DCLVAR);
        adicionarRegra(12, 5, DCLFUNC);
        adicionarRegra(12, 6, CORPO);
        adicionarRegra(12, 7, var_return);
        adicionarRegra(12, 8, var_abreparenteses);
        adicionarRegra(12, 9, VALORRETORNO);
        adicionarRegra(12, 10, var_fechaparenteses);
        adicionarRegra(12, 11, var_fechachaves);
        adicionarRegra(12, 12, DCLFUNC);

        // <TIPO_RETORNO> ::= 'integer'
        adicionarRegra(13, 0, var_integer);

        // <TIPO_RETORNO> ::= 'void'
        adicionarRegra(14, 0, var_void);

        // <TIPO_RETORNO> ::= 'char'
        adicionarRegra(15, 0, var_char);

        // <TIPO_RETORNO> ::= 'float'
        adicionarRegra(16, 0, var_float);

        // <TIPO_RETORNO> ::= 'string'
        adicionarRegra(17, 0, var_string);

        // <DCLFUNC> ::= î
        adicionarRegra(18, 0, var_nulo);

        // <VALORRETORNO> ::= 'numerointeiro'
        adicionarRegra(19, 0, var_numerointeiro);

        // <VALORRETORNO> ::= 'numerofloat'
        adicionarRegra(20, 0, var_numerofloat);

        // <VALORRETORNO> ::= 'nomevariavel'
        adicionarRegra(21, 0, var_nomevariavel);

        // <VALORRETORNO> ::= 'nomedochar'
        adicionarRegra(22, 0, var_nomedochar);

        // <VALORRETORNO> ::= 'nomedastring'
        adicionarRegra(23, 0, var_nomedastring);

        // <VALORRETORNO> ::= î
        adicionarRegra(24, 0, var_nulo);

        // <DEFPAR> ::= î
        adicionarRegra(25, 0, var_nulo);

        // <DEFPAR> ::= '(' <PARAM> ')'
        adicionarRegra(26, 0, var_abreparenteses);
        adicionarRegra(26, 1, PARAM);
        adicionarRegra(26, 2, var_fechaparenteses);

        // <PARAM> ::= <TIPO> nomevariavel <LPARAM>
        adicionarRegra(27, 0, TIPO);
        adicionarRegra(27, 1, var_nomevariavel);
        adicionarRegra(27, 2, LPARAM);

        // <LPARAM> ::= ';' <TIPO> nomevariavel <LPARAM>
        adicionarRegra(28, 0, var_pontovirgula);
        adicionarRegra(28, 1, TIPO);
        adicionarRegra(28, 2, var_nomevariavel);
        adicionarRegra(28, 3, LPARAM);

        // <LPARAM> ::= î
        adicionarRegra(29, 0, var_nulo);

        // <CORPO> ::= 'inicio' <COMANDO> ';' <REPCOMANDO> 'fim'
        adicionarRegra(30, 0, var_inicio);
        adicionarRegra(30, 1, COMANDO);
        adicionarRegra(30, 2, var_pontovirgula);
        adicionarRegra(30, 3, REPCOMANDO);
        adicionarRegra(30, 4, var_fim);

        // <REPCOMANDO> ::= î
        adicionarRegra(31, 0, var_nulo);

        // <REPCOMANDO> ::= <COMANDO> ';' <REPCOMANDO>
        adicionarRegra(32, 0, COMANDO);
        adicionarRegra(32, 1, var_pontovirgula);
        adicionarRegra(32, 2, REPCOMANDO);

        // <COMANDO> ::= 'nomevariavel' '=' <EXPRESSAO>
        adicionarRegra(33, 0, var_nomevariavel);
        adicionarRegra(33, 1, var_igual);
        adicionarRegra(33, 2, EXPRESSAO);

        // <COMANDO> ::= 'nomedastring' '=' <EXPRESSAO>
        adicionarRegra(34, 0, var_nomedastring);
        adicionarRegra(34, 1, var_igual);
        adicionarRegra(34, 2, EXPRESSAO);

        // <COMANDO> ::= 'nomedochar' '=' <EXPRESSAO>
        adicionarRegra(35, 0, var_nomedochar);
        adicionarRegra(35, 1, var_igual);
        adicionarRegra(35, 2, EXPRESSAO);

        // <COMANDO> ::= î
        adicionarRegra(36, 0, var_nulo);

        // <COMANDO> ::= 'callfuncao' 'nomevariavel' <PARAMETROS>
        adicionarRegra(37, 0, var_callfuncao);
        adicionarRegra(37, 1, var_nomevariavel);
        adicionarRegra(37, 2, PARAMETROS);

        // <PARAMETROS> ::= 'î'
        adicionarRegra(38, 0, var_nulo);

        // <PARAMETROS> ::= '(' <TPARAM> <REPPAR> ')'
        adicionarRegra(39, 0, var_abreparenteses);
        adicionarRegra(39, 1, TPARAM);
        adicionarRegra(39, 2, REPPAR);
        adicionarRegra(39, 3, var_fechaparenteses);

        // <REPPAR> ::= 'î'
        adicionarRegra(40, 0, var_nulo);

        // <REPPAR> ::= ',' <TPARAM> <REPPAR>
        adicionarRegra(41, 0, var_virgula);
        adicionarRegra(41, 1, TPARAM);
        adicionarRegra(41, 2, REPPAR);

        // <TPARAM> ::= 'numerointeiro'
        adicionarRegra(42, 0, var_numerointeiro);

        // <TPARAM> ::= 'nomedastring'
        adicionarRegra(43, 0, var_nomedastring);

        // <TPARAM> ::= 'numerofloat'
        adicionarRegra(44, 0, var_numerofloat);

        // <TPARAM> ::= 'nomedochar'
        adicionarRegra(45, 0, var_nomedochar);

        // <TPARAM> ::= 'nomevariavel'
        adicionarRegra(46, 0, var_nomevariavel);

        // <COMANDO> ::= 'if' '(' 'nomevariavel' <COMPARACAO> ')' '{' <COMANDO> ';'
        // <REPCOMANDO> '}' <ELSEPARTE>
        adicionarRegra(47, 0, var_if);
        adicionarRegra(47, 1, var_abreparenteses);
        adicionarRegra(47, 2, var_nomevariavel);
        adicionarRegra(47, 3, COMPARACAO);
        adicionarRegra(47, 4, var_fechaparenteses);
        adicionarRegra(47, 5, var_abrechaves);
        adicionarRegra(47, 6, COMANDO);
        adicionarRegra(47, 7, var_pontovirgula);
        adicionarRegra(47, 8, REPCOMANDO);
        adicionarRegra(47, 9, var_fechachaves);
        adicionarRegra(47, 10, ELSEPARTE);

        // <ELSEPARTE> ::= 'else' '{' <COMANDO> ';' <REPCOMANDO> '}'
        adicionarRegra(48, 0, var_else);
        adicionarRegra(48, 1, var_abrechaves);
        adicionarRegra(48, 2, COMANDO);
        adicionarRegra(48, 3, var_pontovirgula);
        adicionarRegra(48, 4, REPCOMANDO);
        adicionarRegra(48, 5, var_fechachaves);

        // <ELSEPARTE> ::= î
        adicionarRegra(49, 0, var_nulo);

        // <COMANDO> ::= 'while' '(' 'nomevariavel' <COMPARACAO> ')' '{' <COMANDO> ';'
        // <REPCOMANDO> '}'
        adicionarRegra(50, 0, var_while);
        adicionarRegra(50, 1, var_abreparenteses);
        adicionarRegra(50, 2, var_nomevariavel);
        adicionarRegra(50, 3, COMPARACAO);
        adicionarRegra(50, 4, var_fechaparenteses);
        adicionarRegra(50, 5, var_abrechaves);
        adicionarRegra(50, 6, COMANDO);
        adicionarRegra(50, 7, var_pontovirgula);
        adicionarRegra(50, 8, REPCOMANDO);
        adicionarRegra(50, 9, var_fechachaves);

        // <COMPARACAO> ::= '==' <CONTCOMPARACAO>
        adicionarRegra(51, 0, var_comparar);
        adicionarRegra(51, 1, CONTCOMPARACAO);

        // <COMPARACAO> ::= '!=' <CONTCOMPARACAO>
        adicionarRegra(52, 0, var_diferente);
        adicionarRegra(52, 1, CONTCOMPARACAO);

        // <COMPARACAO> ::= '>' <CONTCOMPARACAO>
        adicionarRegra(53, 0, var_maior);
        adicionarRegra(53, 1, CONTCOMPARACAO);

        // <COMPARACAO> ::= '>=' <CONTCOMPARACAO>
        adicionarRegra(54, 0, var_maiorigual);
        adicionarRegra(54, 1, CONTCOMPARACAO);

        // <COMPARACAO> ::= '<' <CONTCOMPARACAO>
        adicionarRegra(55, 0, var_menor);
        adicionarRegra(55, 1, CONTCOMPARACAO);

        // <COMPARACAO> ::= '<=' <CONTCOMPARACAO>
        adicionarRegra(56, 0, var_menorigual);
        adicionarRegra(56, 1, CONTCOMPARACAO);

        // <CONTCOMPARACAO> ::= 'numerointeiro'
        adicionarRegra(57, 0, var_numerointeiro);

        // <CONTCOMPARACAO> ::= 'numerofloat'
        adicionarRegra(58, 0, var_numerofloat);

        // <CONTCOMPARACAO> ::= 'nomedastring'
        adicionarRegra(59, 0, var_nomedastring);

        // <CONTCOMPARACAO> ::= 'nomedochar'
        adicionarRegra(60, 0, var_nomedochar);

        // <CONTCOMPARACAO> ::= 'nomevariavel'
        adicionarRegra(61, 0, var_nomevariavel);

        // <COMANDO> ::= 'for' '(' 'nomevariavel' '=' <CONTCOMPARACAO> ';'
        // 'nomevariavel' <COMPARACAO> ';' <INCREMENTO> ')' '{' <COMANDO> ';'
        // <REPCOMANDO> '}'
        adicionarRegra(62, 0, var_for);
        adicionarRegra(62, 1, var_abreparenteses);
        adicionarRegra(62, 2, var_nomevariavel);
        adicionarRegra(62, 3, var_igual);
        adicionarRegra(62, 4, CONTCOMPARACAO);
        adicionarRegra(62, 5, var_pontovirgula);
        adicionarRegra(62, 6, var_nomevariavel);
        adicionarRegra(62, 7, COMPARACAO);
        adicionarRegra(62, 8, var_pontovirgula);
        adicionarRegra(62, 9, INCREMENTO);
        adicionarRegra(62, 10, var_fechaparenteses);
        adicionarRegra(62, 11, var_abrechaves);
        adicionarRegra(62, 12, COMANDO);
        adicionarRegra(62, 13, var_pontovirgula);
        adicionarRegra(62, 14, REPCOMANDO);
        adicionarRegra(62, 15, var_fechachaves);

        // <INCREMENTO> ::= '++' 'numerointeiro'
        adicionarRegra(63, 0, var_maismais);
        adicionarRegra(63, 1, var_numerointeiro);

        // <INCREMENTO> ::= '--' 'numerointeiro'
        adicionarRegra(64, 0, var_menosmenos);
        adicionarRegra(64, 1, var_numerointeiro);

        // <COMANDO> ::= 'do' '{' <COMANDO> ';' <REPCOMANDO> '}' 'while' '('
        // 'nomevariavel' <COMPARACAO> ')'
        adicionarRegra(65, 0, var_do);
        adicionarRegra(65, 1, var_abrechaves);
        adicionarRegra(65, 2, COMANDO);
        adicionarRegra(65, 3, var_pontovirgula);
        adicionarRegra(65, 4, REPCOMANDO);
        adicionarRegra(65, 5, var_fechachaves);
        adicionarRegra(65, 6, var_while);
        adicionarRegra(65, 7, var_abreparenteses);
        adicionarRegra(65, 8, var_nomevariavel);
        adicionarRegra(65, 9, COMPARACAO);
        adicionarRegra(65, 10, var_fechaparenteses);

        // <COMANDO> ::= 'cin' '>>' 'nomevariavel'
        adicionarRegra(66, 0, var_cin);
        adicionarRegra(66, 1, var_maiormaior);
        adicionarRegra(66, 2, var_nomevariavel);

        // <COMANDO> ::= 'cout' '<<' 'literal' <SEQCOUT>
        adicionarRegra(67, 0, var_cout);
        adicionarRegra(67, 1, var_menormenor);
        adicionarRegra(67, 2, var_literal);
        adicionarRegra(67, 3, SEQCOUT);

        // <SEQCOUT> ::= î
        adicionarRegra(68, 0, var_nulo);

        // <SEQCOUT> ::= '<<' 'nomevariavel' <SEQUENCIA> <SEQCOUT>
        adicionarRegra(69, 0, var_menormenor);
        adicionarRegra(69, 1, var_nomevariavel);
        adicionarRegra(69, 2, SEQUENCIA);
        adicionarRegra(69, 3, SEQCOUT);

        // <SEQUENCIA> ::= î
        adicionarRegra(70, 0, var_nulo);

        // <SEQUENCIA> ::= ',' 'nomevariavel' <SEQUENCIA>
        adicionarRegra(71, 0, var_virgula);
        adicionarRegra(71, 1, var_nomevariavel);
        adicionarRegra(71, 2, SEQUENCIA);

        // <EXPRESSAO> ::= <TERMO> <REPEXP>
        adicionarRegra(72, 0, TERMO);
        adicionarRegra(72, 1, REPEXP);

        // <EXPRESSAO> ::= 'callfuncao' 'nomevariavel' <PARAMETROS>
        adicionarRegra(73, 0, var_callfuncao);
        adicionarRegra(73, 1, var_nomevariavel);
        adicionarRegra(73, 2, PARAMETROS);

        // <REPEXP> ::= '+' <TERMO> <REPEXP>
        adicionarRegra(74, 0, var_mais);
        adicionarRegra(74, 1, TERMO);
        adicionarRegra(74, 2, REPEXP);

        // <REPEXP> ::= '-' <TERMO> <REPEXP>
        adicionarRegra(75, 0, var_menos);
        adicionarRegra(75, 1, TERMO);
        adicionarRegra(75, 2, REPEXP);

        // <REPEXP> ::= î
        adicionarRegra(76, 0, var_nulo);

        // <TERMO> ::= <FATOR> <REPTERMO>
        adicionarRegra(77, 0, FATOR);
        adicionarRegra(77, 1, REPTERMO);

        // <REPTERMO> ::= î
        adicionarRegra(78, 0, var_nulo);

        // <REPTERMO> ::= '*' <FATOR> <REPTERMO>
        adicionarRegra(79, 0, var_multiplicacao);
        adicionarRegra(79, 1, FATOR);
        adicionarRegra(79, 2, REPTERMO);

        // <REPTERMO> ::= '/' <FATOR> <REPTERMO>
        adicionarRegra(80, 0, var_divisao);
        adicionarRegra(80, 1, FATOR);
        adicionarRegra(80, 2, REPTERMO);

        // <FATOR> ::= 'numerointeiro'
        adicionarRegra(81, 0, var_numerointeiro);

        // <FATOR> ::= 'numerofloat'
        adicionarRegra(82, 0, var_numerofloat);

        // <FATOR> ::= 'nomevariavel'
        adicionarRegra(83, 0, var_nomevariavel);

        // <FATOR> ::= 'nomedastring'
        adicionarRegra(84, 0, var_nomedastring);

        // <FATOR> ::= 'nomedochar'
        adicionarRegra(85, 0, var_nomedochar);

        // <FATOR> ::= '(' <EXPRESSAO> ')'
        adicionarRegra(86, 0, var_abreparenteses);
        adicionarRegra(86, 1, EXPRESSAO);
        adicionarRegra(86, 2, var_fechaparenteses);
    }

    public void adicionarRegra(int linha, int coluna, int regra) {
        if (!tabProd.containsKey(linha)) {
            tabProd.put(linha, new HashMap<>());
        }

        tabProd.get(linha).put(coluna, regra);
    }

    public int getRegra(int x, int a) {
        if (!tabProd.containsKey(x)) {
            return -1;
        }

        if (!tabProd.get(x).containsKey(a)) {
            return -1;
        }
        return tabProd.get(x).get(a);
    }
}
