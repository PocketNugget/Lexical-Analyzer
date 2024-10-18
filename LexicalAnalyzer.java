import java.nio.file.*;
import java.io.IOException;
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
        TOKENS.put("FLOAT", "\\d+\\.\\d+");  // Números de punto flotante
        TOKENS.put("INTEGER", "\\d+");        // Números enteros

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

    // Function to print tokens in a table format
    public static void printTable(List<Token> tokens) {
        System.out.println("+-----------------+----------------+");
        System.out.println("| Token Type      | Token Value    |");
        System.out.println("+-----------------+----------------+");

        for (Token token : tokens) {
            System.out.printf("| %-15s | %-14s |\n", token.getType(), token.getValue());
        }

        System.out.println("+-----------------+----------------+");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LexicalAnalyzer <file_path>");
            return;
        }

        String filePath = args[0];
        try {
            // Read the contents of the file
            String code = new String(Files.readAllBytes(Paths.get(filePath)));

            // Tokenize the content of the file
            List<Token> tokens = tokenize(code);

            // Print tokens in a table format
            printTable(tokens);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
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
