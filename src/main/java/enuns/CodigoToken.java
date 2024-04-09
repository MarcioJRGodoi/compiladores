package enuns;

public enum CodigoToken {
    WHILE(1),
    VOID(2),
    STRING(3),
    RETURN(4),
    NUMERO_INTEIRO(5),
    NUMERO_FLOAT(6),
    NOME_VARIAVEL(7),
    NOME_CHAR(8),
    NOME_VARIAVEL_2(9),
    NOME_STRING(10),
    MAIN(11),
    LITERAL(12),
    INTEGER(13),
    INICIO(14),
    IF(15),
    I(16),
    FOR(17),
    FLOAT(18),
    FIM(19),
    ELSE(20),
    DO(21),
    COUT(22),
    CIN(23),
    CHAR(24),
    CALL_FUNCAO(25),
    MAIOR_IGUAL(26),
    MAIOR(27),
    IGUAL(28),
    ATRIBUICAO(29),
    MENOR_IGUAL(30),
    MENOR(31),
    INCREMENTO(32),
    ADICAO(33),
    FECHA_CHAVES(34),
    ABRE_CHAVES(35),
    PONTO_VIRGULA(36),
    DOIS_PONTOS(37),
    DIVISAO(38),
    VIRGULA(39),
    MULTIPLICACAO(40),
    FECHA_PARENTESES(41),
    ABRE_PARENTESES(42),
    DOLLAR(43),
    DIFERENTE(44),
    DECREMENTO(45),
    SUBTRACAO(46);

    private final int codigo;

    CodigoToken(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }
}