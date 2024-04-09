package enuns;

public enum CodigoToken {
    WHILE("while", 1),
    VOID("void", 2),
    STRING("string", 3),
    RETURN("return", 4),
    NUMERO_INTEIRO("numerointeiro", 5),
    NUMERO_FLOAT("numerofloat", 6),
    NOME_VARIAVEL("nomevariavel", 7),
    NOME_CHAR("nomedochar", 8),
    NOME_VARIAVEL_2("nomedavariavel", 9),
    NOME_STRING("nomedastring", 10),
    MAIN("main", 11),
    LITERAL("literal", 12),
    INTEGER("integer", 13),
    INICIO("inicio", 14),
    IF("if", 15),
    I("î", 16),
    FOR("for", 17),
    FLOAT("float", 18),
    FIM("fim", 19),
    ELSE("else", 20),
    DO("do", 21),
    COUT("cout", 22),
    CIN("cin", 23),
    CHAR("char", 24),
    CALL_FUNCAO("callfuncao", 25),
    MAIOR_IGUAL(">>", 26),
    MAIOR(">=", 27),
    IGUAL(">", 28),
    ATRIBUICAO("==", 29),
    MENOR_IGUAL("=", 30),
    MENOR("<<", 31),
    INCREMENTO("<", 32),
    ADICAO("++", 33),
    FECHA_CHAVES("+", 34),
    ABRE_CHAVES("}", 35),
    PONTO_VIRGULA("{", 36),
    DOIS_PONTOS(";", 37),
    DIVISAO(":", 38),
    VIRGULA("/", 39),
    MULTIPLICACAO(",", 40),
    FECHA_PARENTESES("*", 41),
    ABRE_PARENTESES(")", 42),
    DOLLAR("(", 43),
    DIFERENTE("$", 44),
    DECREMENTO("!=", 45),
    SUBTRACAO("--", 46);

    private final String token;
    private final int index;

    CodigoToken(String token, int index) {
        this.token = token;
        this.index = index;
    }

    public String getToken() {
        return this.token;
    }

    public int getIndex() {
        return this.index;
    }
}