package enuns;

public enum NaoTerminais {
    BLOCO(49, "BLOCO"),
    DCLVAR(50, "DCLVAR"),
    DCLFUNC(51, "DCLFUNC"),
    CORPO(52, "CORPO"),
    REPIDENT(53, "REPIDENT"),
    TIPO(54, "TIPO"),
    LDVAR(55, "LDVAR"),
    REPIDEN(56, "REPIDEN"),
    LID(57, "LID"),
    TIPO_RETORNO(58, "TIPO_RETORNO"),
    DEFPAR(59, "DEFPAR"),
    VALORRETORNO(60, "VALORRETORNO"),
    PARAM(61, "PARAM"),
    LPARAM(62, "LPARAM"),
    COMANDO(63, "COMANDO"),
    REPCOMANDO(64, "REPCOMANDO"),
    EXPRESSAO(65, "EXPRESSAO"),
    PARAMETROS(66, "PARAMETROS"),
    TPARAM(67, "TPARAM"),
    REPPAR(68, "REPPAR"),
    COMPARACAO(69, "COMPARACAO"),
    ELSEPARTE(70, "ELSEPARTE"),
    CONTCOMPARACAO(71, "CONTCOMPARACAO"),
    INCREMENTO(72, "INCREMENTO"),
    SEQCOUT(73, "SEQCOUT"),
    SEQUENCIA(74, "SEQUENCIA"),
    EXPSIMP(75, "EXPSIMP"),
    REPEXPSIMP(76, "REPEXPSIMP"),
    TERMO(77, "TERMO"),
    REPEXP(78, "REPEXP"),
    FATOR(79, "FATOR"),
    REPTERMO(80, "REPTERMO");

    private final int codigo;
    private final String simbolo;

    NaoTerminais(int codigo, String simbolo) {
        this.codigo = codigo;
        this.simbolo = simbolo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static NaoTerminais BuscarNaoTerminalPorSimbolo(String simbolo) {
        for (NaoTerminais naoTerminal : NaoTerminais.values()) {
            if (naoTerminal.getSimbolo().equals(simbolo)) {
                return naoTerminal;
            }
        }
        return null;
    }
}
