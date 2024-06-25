package Semantico;

public class TabelaSimbolosSemantico {
    private String tokenLexema;
    private String tokenCategoria;
    private String tokenTipo;
    private int tokenNivel;

    public String getTokenLexema() {
        return tokenLexema;
    }

    public void setTokenLexema(String tokenLexema) {
        this.tokenLexema = tokenLexema;
    }

    public String getTokenCategoria() {
        return tokenCategoria;
    }

    public void setTokenCategoria(String tokenCategoria) {
        this.tokenCategoria = tokenCategoria;
    }

    public String getTokenTipo() {
        return tokenTipo;
    }

    public void setTokenTipo(String tokenTipo) {
        this.tokenTipo = tokenTipo;
    }

    public int getTokenNivel() {
        return tokenNivel;
    }

    public void setTokenNivel(int tokenNivel) {
        this.tokenNivel = tokenNivel;
    }
}
