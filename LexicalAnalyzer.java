import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {

    private static final Map<String, String> TOKENS = new HashMap<>();

    static {
        TOKENS.put("NUMBER", "\\d+");
        TOKENS.put("PLUS", "\\+");
        TOKENS.put("MINUS", "-");
        TOKENS.put("MULTIPLY", "\\*");
        TOKENS.put("DIVIDE", "/");
        TOKENS.put("LPAREN", "\\(");
        TOKENS.put("RPAREN", "\\)");
    }

    public static List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        while (!code.isEmpty()) {
            boolean matched = false;
            for (Map.Entry<String, String> entry : TOKENS.entrySet()) {
                Pattern pattern = Pattern.compile(entry.getValue());
                Matcher matcher = pattern.matcher(code);
                if (matcher.find()) {
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

    // Complete the DFA logic here
    public static void simulateDFA(List<Token> tokens) {
        // Incomplete: Add DFA simulation logic
    }

    public static void main(String[] args) {
        String code = "3 + 5 * (10 - 4)";
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

    @Override
    public String toString() {
        return String.format("<%s: %s>", type, value);
    }
}
