package regerx;

import java.util.HashMap;
import java.util.Map;

public class Regex {

    public static final Map<String, String> REGEX_MAP = new HashMap<String, String>() {{
        put("REGEX_INTEIRO", "^(0|[1-9][0-9]{0,5})$");
        put("REGEX_REAL", "^(0|[1-9][0-9]{0,5})(\\.[0-9]{1,3})?$");
        put("REGEX_CHAR", "^[a-zA-Z0-9]$");
        put("REGEX_STRING", "^[a-zA-Z0-9]*$");
        put("REGEX_COMENTARIO", "(^//[^/]*$)|(^/\\*[^/]*\\*/$)");
        put("REGEX_VARIAVEL", "^[a-zA-Z]{1,15}$");
        put("REGEX_LITERAL", "^\".*\"$");
        put("REGEX_PALAVRA_CHAVE", "^(void|main|integer|float|string|char|if|else|while|for|return|cin|cout|begin|end|callfuncao)$");
        put("REGEX_IDENTIFICADOR", "^[a-zA-Z_][a-zA-Z0-9_]*$");
        put("REGEX_OPERADOR_ARITMETICO", "^[+\\-*/]$");
        put("REGEX_OPERADOR_COMPARACAO", "^(==|!=|>|<|>=|<=)$");
        put("REGEX_OPERADOR_OUTRO", "^(=|\\+\\+|--|>>|<<)$");
        put("REGEX_DELIMITADOR", "^(\\(|\\)|\\{|\\}|;|,)$");
    }};
    public static final String REGEX_INTEIRO = "^(0|[1-9][0-9]{0,5})$";
    public static final String REGEX_REAL = "^(0|[1-9][0-9]{0,5})(\\.[0-9]{1,3})?$";
    public static final String REGEX_CHAR = "^[a-zA-Z0-9]$";
    public static final String REGEX_STRING = "^[a-zA-Z0-9]*$";
    public static final String REGEX_COMENTARIO = "(?s)//.*|/\\*.*?\\*/";
    public static final String REGEX_VARIAVEL = "^[a-zA-Z]{1,15}$";
    public static final String REGEX_LITERAL = "^\".*\"$";
    public static final String REGEX_PALAVRA_CHAVE = "^(void|main|integer|float|string|char|if|else|while|for|return|cin|cout|begin|end|callfuncao)$";
    public static final String REGEX_IDENTIFICADOR = "^[a-zA-Z_][a-zA-Z0-9_]*$";
    public static final String REGEX_OPERADOR_ARITMETICO = "^[+\\-*/]$";
    public static final String REGEX_OPERADOR_COMPARACAO = "(==|!=|>|<|>=|<=)";
    public static final String REGEX_OPERADOR_OUTRO = "(=|\\+\\+|--|>>|<<)";
    public static final String REGEX_DELIMITADOR = "^(\\(|\\)|\\{|\\}|;|,|:)$";
}
