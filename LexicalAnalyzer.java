import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {

    private static final Map<String, String> TOKENS = new HashMap<>();

    static {
        TOKENS.put("ELSE", "e*l*s*e");
        TOKENS.put("ENUM", "e*n*u*m");
        TOKENS.put("WHILE", "w*h*i*l*e");



    }

    public static List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        while (!code.isEmpty()) {
            boolean matched = false;
            for (Map.Entry<String, String> entry : TOKENS.entrySet()) {
                Pattern pattern = Pattern.compile(entry.getValue());
                Matcher matcher = pattern.matcher(code);
                if (matcher.find() && matcher.start() == 0) {
                    String tokenValue = matcher.group(0);
                    tokens.add(new Token(entry.getKey(), tokenValue));
                    code = code.substring(tokenValue.length());
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                throw new RuntimeException("Unexpected character: " + code.charAt(0));
            }
        }
        return tokens;
    }

    // Lógica del DFA para "else" y "enum"
    public static void simulateDFA(List<Token> tokens) {
        // Definir estados PARA KEYWORDS
        final int START = 0;
        final int ELSE = 1;
        final int ENUM = 2;
        final int WHILE = 3;
        final int ERROR = -1;

        int currentState = START;

        for (Token token : tokens) {
            switch (currentState) {
                case START:
                    if (token.getType().equals("ELSE")) {
                        currentState = ELSE;
                    } else if (token.getType().equals("ENUM")) {
                        currentState = ENUM;
                    } else if (token.getType().equals("WHILE")) {
                        currentState = WHILE;
                    } else {
                        currentState = ERROR;
                    }
                    break;
                case ELSE:
                    // Estado aceptado para la palabra clave else
                    System.out.println("Keyword: ELSE.");
                    return;
                case ENUM:
                    // Estado aceptado para la palabra clave enum
                    System.out.println("Keyword: ENUM.");
                    return;
                case WHILE:
                    // Estado aceptado para la palabra clave enum
                    System.out.println("Keyword: WHILE.");
                    return;
                case ERROR:
                    System.out.println("DFA rejected the token sequence.");
                    return;
            }
        }

        if (currentState == ELSE || currentState == ENUM|| currentState == WHILE) {
            System.out.println("Keyword.");
            System.out.println(currentState);
        } else {
            System.out.println("DFA rejected the input.");
        }
    }

    public static void main(String[] args) {
        String code = "while";
        List<Token> tokens = tokenize(code);
        System.out.println(tokens);
        simulateDFA(tokens);

    }
}

class Token {
    private final String type;
    private final String value;

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("<%s: %s>", type, value);
    }
}