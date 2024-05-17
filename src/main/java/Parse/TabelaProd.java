package Parse;

import java.util.HashMap;

public class TabelaProd {
    HashMap<Integer, HashMap<Integer, Integer>> tabProd;

    public TabelaProd() {
        tabProd = new HashMap<>();
        // <BLOCO> ::= 'void' 'main' '{' <DCLVAR> <DCLFUNC> <CORPO> '}'
        adicionarRegra(0, 0, 2);

        // <DCLVAR> ::= 'nomevariavel' <REPIDENT> ':' <TIPO> ';' <LDVAR>
        adicionarRegra(1, 0, 7);

        // <DCLVAR> ::= î
        adicionarRegra(2, 0, 16);

        // <REPIDENT> ::= î
        adicionarRegra(3, 0, 16);

        // <REPIDENT> ::= ',' 'nomevariavel' <REPIDENT>
        adicionarRegra(4, 0, 41);

        // <TIPO> ::= 'integer'
        adicionarRegra(5, 0, 13);

        // <TIPO> ::= 'float'
        adicionarRegra(6, 0, 18);

        // <TIPO> ::= 'string'
        adicionarRegra(7, 0, 3);

        // <TIPO> ::= 'char'
        adicionarRegra(8, 0, 24);

        // <LDVAR> ::= <LID> ':' <TIPO> ';' <LDVAR>
        // First(<LID>) is 'nomevariavel' = 7
        adicionarRegra(9, 0, 7); 

        // <LDVAR> ::= î
        adicionarRegra(10, 0, 16);

        // <LID> ::= 'nomevariavel' <REPIDENT>
        adicionarRegra(11, 0, 7);

        // <DCLFUNC> ::= <TIPO_RETORNO> 'nomevariavel' <DEFPAR> '{' <DCLVAR> <DCLFUNC>
        // <CORPO> 'return' '(' <VALORRETORNO> ')' '}' <DCLFUNC>
        // First(<TIPO_RETORNO>) inclui 'integer', 'void', 'char', 'float', 'string'
        // {13, 2, 24, 18, 3}
        adicionarRegra(12, 0, 13);            
        adicionarRegra(12, 1, 2);
        adicionarRegra(12, 2, 24);
        adicionarRegra(12, 3, 18);
        adicionarRegra(12, 4, 3);

        // <TIPO_RETORNO> ::= 'integer'
        adicionarRegra(13, 0, 13);

        // <TIPO_RETORNO> ::= 'void'
        adicionarRegra(14, 0, 2);

        // <TIPO_RETORNO> ::= 'char'
        adicionarRegra(15, 0, 24);

        // <TIPO_RETORNO> ::= 'float'
        adicionarRegra(16, 0, 18);

        // <TIPO_RETORNO> ::= 'string'
        adicionarRegra(17, 0, 3);

        // <DCLFUNC> ::= î
        adicionarRegra(18, 0, 16);

        // <VALORRETORNO> ::= 'numerointeiro'
        adicionarRegra(19, 0, 5);

        // <VALORRETORNO> ::= 'numerofloat'
        adicionarRegra(20, 0, 6);

        // <VALORRETORNO> ::= 'nomevariavel'
        adicionarRegra(21, 0, 7);

        // <VALORRETORNO> ::= 'nomedochar'
        adicionarRegra(22, 0, 8);

        // <VALORRETORNO> ::= 'nomedastring'
        adicionarRegra(23, 0, 10);

        // <VALORRETORNO> ::= î
        adicionarRegra(24, 0, 16);

        // <DEFPAR> ::= î
        adicionarRegra(25, 0, 16);

        // <DEFPAR> ::= '(' <PARAM> ')'
        adicionarRegra(26, 0, 44);

        // <PARAM> ::= <TIPO> nomevariavel <LPARAM>
        // First(<TIPO>) inclui 'integer', 'float', 'string', 'char' 
        // {13, 18, 3, 24}
        adicionarRegra(27, 0, 13);
        adicionarRegra(27, 1, 18);
        adicionarRegra(27, 2, 3);
        adicionarRegra(27, 3, 24);

        // <LPARAM> ::= ';' <TIPO> nomevariavel <LPARAM>
        adicionarRegra(28, 0, 38);

        // <LPARAM> ::= î
        adicionarRegra(29, 0, 16);

        // <CORPO> ::= 'inicio' <COMANDO> ';' <REPCOMANDO> 'fim'
        adicionarRegra(30, 0, 14);

        // <REPCOMANDO> ::= î
        adicionarRegra(31, 0, 16);

        // <REPCOMANDO> ::= <COMANDO> ';' <REPCOMANDO>
        // First(<COMANDO>) inclui 'nomevariavel', 'nomedastring', 'nomedochar',
        // 'callfuncao', 'if', 'while', 'for', 'do', 'cin', 'cout' 
        // {7, 10, 8, 25, 15, 1, 17, 21, 23, 22}
        adicionarRegra(32, 0, 7); 
        adicionarRegra(32, 1, 10);
        adicionarRegra(32, 2, 8);
        adicionarRegra(32, 3, 25);
        adicionarRegra(32, 4, 15);
        adicionarRegra(32, 5, 1);
        adicionarRegra(32, 6, 17);
        adicionarRegra(32, 7, 21);
        adicionarRegra(32, 8, 23);
        adicionarRegra(32, 9, 22);

        // <COMANDO> ::= 'nomevariavel' '=' <EXPRESSAO>
        adicionarRegra(33, 0, 7);

        // <COMANDO> ::= 'nomedastring' '=' <EXPRESSAO>
        adicionarRegra(34, 0, 10);

        // <COMANDO> ::= 'nomedochar' '=' <EXPRESSAO>
        adicionarRegra(35, 0, 8);

        // <COMANDO> ::= î
        adicionarRegra(36, 0, 16);

        // <COMANDO> ::= 'callfuncao' 'nomevariavel' <PARAMETROS>
        adicionarRegra(37, 0, 25);

        // <PARAMETROS> ::= î
        adicionarRegra(38, 0, 16);

        // <PARAMETROS> ::= '(' <TPARAM> <REPPAR> ')'
        adicionarRegra(39, 0, 44);

        // <REPPAR> ::= î
        adicionarRegra(40, 0, 16);

        // <REPPAR> ::= ',' <TPARAM> <REPPAR>
        adicionarRegra(41, 0, 41);

        // <TPARAM> ::= 'numerointeiro'
        adicionarRegra(42, 0, 5);

        // <TPARAM> ::= 'nomedastring'
        adicionarRegra(43, 0, 10);

        // <TPARAM> ::= 'numerofloat'
        adicionarRegra(44, 0, 6);

        // <TPARAM> ::= 'nomedochar'
        adicionarRegra(45, 0, 8);

        // <TPARAM> ::= 'nomevariavel'
        adicionarRegra(46, 0, 7);

        // <COMANDO> ::= 'if' '(' 'nomevariavel' <COMPARACAO> ')' '{' <COMANDO> ';'
        // <REPCOMANDO> '}' <ELSEPARTE>
        adicionarRegra(47, 0, 15);

        // <ELSEPARTE> ::= 'else' '{' <COMANDO> ';' <REPCOMANDO> '}'
        adicionarRegra(48, 0, 20);

        // <ELSEPARTE> ::= î
        adicionarRegra(49, 0, 16);

        // <COMANDO> ::= 'while' '(' 'nomevariavel' <COMPARACAO> ')' '{' <COMANDO> ';'
        // <REPCOMANDO> '}'
        adicionarRegra(50, 0, 1);

        // <COMPARACAO> ::= '==' <CONTCOMPARACAO>
        adicionarRegra(51, 0, 29);

        // <COMPARACAO> ::= '!=' <CONTCOMPARACAO>
        adicionarRegra(52, 0, 46);

        // <COMPARACAO> ::= '>' <CONTCOMPARACAO>
        adicionarRegra(53, 0, 28);

        // <COMPARACAO> ::= '>=' <CONTCOMPARACAO>
        adicionarRegra(54, 0, 27);

        // <COMPARACAO> ::= '<' <CONTCOMPARACAO>
        adicionarRegra(55, 0, 33);

        // <COMPARACAO> ::= '<=' <CONTCOMPARACAO>
        adicionarRegra(56, 0, 31);

        // <CONTCOMPARACAO> ::= 'numerointeiro'
        adicionarRegra(57, 0, 5);

        // <CONTCOMPARACAO> ::= 'numerofloat'
        adicionarRegra(58, 0, 6);

        // <CONTCOMPARACAO> ::= 'nomedastring'
        adicionarRegra(59, 0, 10);

        // <CONTCOMPARACAO> ::= 'nomedochar'
        adicionarRegra(60, 0, 8);

        // <CONTCOMPARACAO> ::= 'nomevariavel'
        adicionarRegra(61, 0, 7);

        // <COMANDO> ::= 'for' '(' 'nomevariavel' '=' <CONTCOMPARACAO> ';'
        // 'nomevariavel' <COMPARACAO> ';' <INCREMENTO> ')' '{' <COMANDO> ';'
        // <REPCOMANDO> '}'
        adicionarRegra(62, 0, 17);

        // <INCREMENTO> ::= '++' 'numerointeiro'
        adicionarRegra(63, 0, 34);

        // <INCREMENTO> ::= '--' 'numerointeiro'
        adicionarRegra(64, 0, 47);

        // <COMANDO> ::= 'do' '{' <COMANDO> ';' <REPCOMANDO> '}' 'while' '('
        // 'nomevariavel' <COMPARACAO> ')'
        adicionarRegra(65, 0, 21);

        // <COMANDO> ::= 'cin' '>>' 'nomevariavel'
        adicionarRegra(66, 0, 23);

        // <COMANDO> ::= 'cout' '<<' 'literal' <SEQCOUT>
        adicionarRegra(67, 0, 22);

        // <SEQCOUT> ::= î
        adicionarRegra(68, 0, 16);

        // <SEQCOUT> ::= '<<' 'nomevariavel' <SEQUENCIA> <SEQCOUT>
        adicionarRegra(69, 0, 32);

        // <SEQUENCIA> ::= î
        adicionarRegra(70, 0, 16);

        // <SEQUENCIA> ::= ',' 'nomevariavel' <SEQUENCIA>
        adicionarRegra(71, 0, 41);

        // <EXPRESSAO> ::= <TERMO> <REPEXP>
        // First(<TERMO>) inclui 'numerointeiro', 'numerofloat', 'nomevariavel',
        // 'nomedastring', 'nomedochar', '(' 
        // {5, 6, 7, 10, 8, 44}
        adicionarRegra(72, 0, 5);                  
        adicionarRegra(72, 1, 6);
        adicionarRegra(72, 2, 7);
        adicionarRegra(72, 3, 10);
        adicionarRegra(72, 4, 8);
        adicionarRegra(72, 5, 44);

        // <EXPRESSAO> ::= 'callfuncao' 'nomevariavel' <PARAMETROS>
        adicionarRegra(73, 0, 25);

        // <REPEXP> ::= '+' <TERMO> <REPEXP>
        adicionarRegra(74, 0, 35);

        // <REPEXP> ::= '-' <TERMO> <REPEXP>
        adicionarRegra(75, 0, 48);

        // <REPEXP> ::= î
        adicionarRegra(76, 0, 16);

        // <TERMO> ::= <FATOR> <REPTERMO>
        // First(<FATOR>) inclui 'numerointeiro', 'numerofloat', 'nomevariavel',
        // 'nomedastring', 'nomedochar', '(' 
        //{5, 6, 7, 10, 8, 44}
        adicionarRegra(77, 0, 5);               
        adicionarRegra(77, 1, 6);
        adicionarRegra(77, 2, 7);
        adicionarRegra(77, 3, 10);
        adicionarRegra(77, 4, 8);
        adicionarRegra(77, 5, 44);

        // <REPTERMO> ::= î
        adicionarRegra(78, 0, 16);

        // <REPTERMO> ::= '*' <FATOR> <REPTERMO>
        adicionarRegra(79, 0, 42);

        // <REPTERMO> ::= '/' <FATOR> <REPTERMO>
        adicionarRegra(80, 0, 40);

        // <FATOR> ::= 'numerointeiro'
        adicionarRegra(81, 0, 5);

        // <FATOR> ::= 'numerofloat'
        adicionarRegra(82, 0, 6);

        // <FATOR> ::= 'nomevariavel'
        adicionarRegra(83, 0, 7);

        // <FATOR> ::= 'nomedastring'
        adicionarRegra(84, 0, 10);

        // <FATOR> ::= 'nomedochar'
        adicionarRegra(85, 0, 8);

        // <FATOR> ::= '(' <EXPRESSAO> ')'
        adicionarRegra(86, 0, 44);
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
