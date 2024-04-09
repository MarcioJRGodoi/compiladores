package enuns;

public enum CodigoToken {
    WHILE(1, "while"),
    VOID(2, "void"),
    STRING(3, "string"),
    RETURN(4, "return"),
    NUMERO_INTEIRO(5, "numerointeiro"),
    NUMERO_FLOAT(6, "numerofloat"),
    NOME_VARIAVEL(7, "nomevariavel"),
    NOME_CHAR(8, "nomedochar"),
    NOME_VARIAVEL_2(9, "nomedavariavel"),
    NOME_STRING(10, "nomedastring"),
    MAIN(11, "main"),
    LITERAL(12, "literal"),
    INTEGER(13, "integer"),
    INICIO(14, "inicio"),
    IF(15, "if"),
    I(16, "î"),
    FOR(17, "for"),
    FLOAT(18, "float"),
    FIM(19, "fim"),
    ELSE(20, "else"),
    DO(21, "do"),
    COUT(22, "cout"),
    CIN(23, "cin"),
    CHAR(24, "char"),
    CALL_FUNCAO(25, "callfuncao"),
    MAIOR_IGUAL(26, ">>"),
    MAIOR(27, ">"),
    IGUAL(28, "=="),
    ATRIBUICAO(29, "="),
    MENOR_IGUAL(30, "<="),
    SHIFT_LEFT(31, "<<"),
    MENOR(32, "<"),
    INCREMENTO(33, "++"),
    ADICAO(34, "+"),
    FECHA_CHAVES(35, "}"),
    ABRE_CHAVES(36, "{"),
    PONTO_VIRGULA(37, ";"),
    DOIS_PONTOS(38, ":"),
    DIVISAO(39, "/"),
    VIRGULA(40, ","),
    MULTIPLICACAO(41, "*"),
    FECHA_PARENTESES(42, ")"),
    ABRE_PARENTESES(43, "("),
    DOLLAR(44, "$"),
    DIFERENTE(45, "!="),
    DECREMENTO(46, "--"),
    SUBTRACAO(47, "-");

    private final int codigo;
    private final String tokenString;

    CodigoToken(int codigo, String tokenString) {
        this.codigo = codigo;
        this.tokenString = tokenString;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTokenString() {
        return tokenString;
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