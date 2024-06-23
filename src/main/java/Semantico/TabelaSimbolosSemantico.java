package Semantico;

import enuns.CodigoToken;

public class TabelaSimbolosSemantico {
    private CodigoToken tokenVariavel;
    private CodigoToken tokenTipoVariavel;

    public CodigoToken getTokenVariavel() {
        return tokenVariavel;
    }

    public void setTokenVariavel(CodigoToken tokenVariavel) {
        this.tokenVariavel = tokenVariavel;
    }

    public CodigoToken getTokenTipoVariavel() {
        return tokenTipoVariavel;
    }

    public void setTokenTipoVariavel(CodigoToken tokenTipoVariavel) {
        this.tokenTipoVariavel = tokenTipoVariavel;
    }
}
