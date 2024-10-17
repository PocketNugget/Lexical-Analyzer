import java.util.*;
import java.util.regex.*;

public class LexicalAnalyzer {

    private static final Map<String, String> TOKENS = new HashMap<>();

    static {
        // Keywords
        TOKENS.put("ELSE", "e*l*s*e");
        TOKENS.put("ENUM", "e*n*u*m");
        TOKENS.put("WHILE", "w*h*i*l*e");
        TOKENS.put("WITH", "w*i*t*h");
        TOKENS.put("RETURN", "r*e*t*u*r*n");
        TOKENS.put("RESTRICT", "r*e*s*t*r*i*c*t");
        TOKENS.put("THEN", "t*h*e*n");
        TOKENS.put("THIS", "t*h*i*s");
        TOKENS.put("FOR", "f*o*r");
        TOKENS.put("FALSE", "f*a*l*s*e");
        // Identifiers
        TOKENS.put("IDENTIFIER", "[a-zA-Z][a-zA-Z0-9]*");

        // Numbers
        TOKENS.put("NUMBER", "\\d+(\\.\\d+)?");

        // Operators
        TOKENS.put("LEQ", "<=");
        TOKENS.put("GEQ", ">=");
        TOKENS.put("EQ", "==");
        TOKENS.put("NEQ", "!=");
        TOKENS.put("ASSIGN", "=");
        TOKENS.put("LT", "<");
        TOKENS.put("GT", ">");
        TOKENS.put("PLUS", "\\+");
        TOKENS.put("MINUS", "-");
        TOKENS.put("MULTIPLY", "\\*");
        TOKENS.put("DIVIDE", "/");

        // Delimiters
        TOKENS.put("LPAREN", "\\(");  // Left Parenthesis
        TOKENS.put("RPAREN", "\\)");  // Right Parenthesis
        TOKENS.put("LBRACE", "\\{");  // Left Brace
        TOKENS.put("RBRACE", "\\}");  // Right Brace
        TOKENS.put("SEMICOLON", ";"); // Semicolon
    }

    public static List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        while (!code.isEmpty()) {
            code = code.trim();  // Remove leading and trailing spaces

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

    // Improved DFA logic using switch-case grouped by categories
    public static void simulateDFA(List<Token> tokens) {
        for (Token token : tokens) {
            switch (token.getType()) {
                // Case for Keywords
                case "ELSE":
                case "ENUM":
                case "WHILE":
                    System.out.println("Keyword: " + token.getType());
                    break;

                // Case for Identifiers
                case "IDENTIFIER":
                    System.out.println("Identifier: " + token.getValue());
                    break;

                // Case for Numbers
                case "NUMBER":
                    if (token.getValue().contains(".")) {
                        System.out.println("Number (float): " + token.getValue());
                    } else {
                        System.out.println("Number (int): " + token.getValue());
                    }
                    break;

                // Case for Operators
                case "PLUS":
                case "MINUS":
                case "MULTIPLY":
                case "DIVIDE":
                case "ASSIGN":
                case "EQ":
                case "NEQ":
                case "LT":
                case "GT":
                case "LEQ":
                case "GEQ":
                    System.out.println("Operator: " + token.getValue());
                    break;

                // Case for Delimiters
                case "LPAREN":
                case "RPAREN":
                case "LBRACE":
                case "RBRACE":
                case "SEMICOLON":
                    System.out.println("Delimiter: " + token.getValue());
                    break;

                // Case for unknown inputs
                default:
                    System.out.println("DFA rejected the input.");
                    break;
            }
        }

        System.out.println("DFA finished processing without error.");
    }

    public static void main(String[] args) {
        String code = "in9t x = 5 + y;";
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

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("<%s: %s>", type, value);
    }
}
