package enuns;

public enum CodigoToken {
    WHILE(1, "while", "WHILE"),
    VOID(2, "void", "VOID"),
    STRING(3, "string", "STRING"),
    RETURN(4, "return", "RETURN"),
    NUMERO_INTEIRO(5, "numerointeiro", "NUMERO_INTEIRO"),
    NUMERO_FLOAT(6, "numerofloat", "NUMERO_FLOAT"),
    NOME_VARIAVEL(7, "nomevariavel", "NOME_VARIAVEL"),
    NOME_CHAR(8, "nomedochar", "NOME_CHAR"),
    NOME_VARIAVEL_2(9, "nomedavariavel", "NOME_VARIAVEL_2"),
    NOME_STRING(10, "nomedastring", "NOME_STRING"),
    MAIN(11, "main", "MAIN"),
    LITERAL(12, "literal", "LITERAL"),
    INTEGER(13, "integer", "INTEGER"),
    INICIO(14, "inicio", "INICIO"),
    IF(15, "if", "IF"),
    I(16, "î", "I"),
    FOR(17, "for", "FOR"),
    FLOAT(18, "float", "FLOAT"),
    FIM(19, "fim", "FIM"),
    ELSE(20, "else", "ELSE"),
    DO(21, "do", "DO"),
    COUT(22, "cout", "COUT"),
    CIN(23, "cin", "CIN"),
    CHAR(24, "char", "CHAR"),
    CALL_FUNCAO(25, "callfuncao", "CALL_FUNÇÃO"),
    MAIOR_MAIOR(26, ">>", "MAIOR_MAIOR"),
    MAIOR_IGUAL(27, ">=", "MAIOR_IGUAL"),
    MAIOR(28, ">", "MAIOR"),
    IGUAL(29, "==", "IGUAL"),
    ATRIBUICAO(30, "=", "ATRIBUIÇÃO"),
    MENOR_IGUAL(31, "<=", "MENOR_IGUAL"),
    SHIFT_LEFT(32, "<<", "MENOR_MENOR"),
    MENOR(33, "<", "MENOR"),
    INCREMENTO(34, "++", "MAIS_MAIS"),
    ADICAO(35, "+", "MAIS"),
    FECHA_CHAVES(36, "}", "FECHA_CHAVES"),
    ABRE_CHAVES(37, "{", "ABRE_CHAVES"),
    PONTO_VIRGULA(38, ";", "PONTO_VIRGULA"),
    DOIS_PONTOS(39, ":", "DOIS_PONTOS"),
    DIVISAO(40, "/", "DIVISÃO"),
    VIRGULA(41, ",", "VIRGULA"),
    MULTIPLICACAO(42, "*", "MULTIPLICAÇÃO"),
    FECHA_PARENTESES(43, ")", "FECHA_PARENTESES"),
    ABRE_PARENTESES(44, "(", "ABRE_PARENTES"),
    DOLLAR(45, "$", "DOLLAR"),
    DIFERENTE(46, "!=", "DIFERENTE"),
    DECREMENTO(47, "--", "DECREMENTO"),
    SUBTRACAO(48, "-", "SUBTRAÇÃO"),
    COMENTARIO(47, "//", "COMENTARIO_DE_LINHA"),
    COMENTARIOBLOCO(48, "/**/", "COMENTARIO_DE_BLOCO");



    private final int codigo;
    private final String tokenString;
    private final String tokenDescricao;

    CodigoToken(int codigo, String tokenString, String tokenDescricao) {
        this.codigo = codigo;
        this.tokenString = tokenString;
        this.tokenDescricao = tokenDescricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTokenString() {
        return tokenString;
    }

    public String getTokenDescricao() {
        return tokenDescricao;
    }

    public static CodigoToken BuscarTokenPorString(String tokenString) {
        for (CodigoToken token : CodigoToken.values()) {
            if (token.getTokenString().equals(tokenString)) {
                return token;
            }
        }
        return null;
    }
}