
public class QC2Test {

    private static PseudoTokeniser tokeniser;
    private static Parser parser;
    
    private static void parse(Token[] equation) {
        System.out.println("Parsing equation: ");
        tokeniser.showTokens();
        
        Exp exp = parser.parseExp();
        if(exp == null) {
            System.out.println("Got an null expression.");
        } else {
            System.out.println(" = " + exp.evaluate());
        }
    }
    
    public static void main(String[] args) {
        Token[][] equations = {{new Token(TokenType.LEFTBRACKET), new Token(20), new Token(TokenType.MULTIPLY), new Token(30), new Token(TokenType.RIGHTBRACKET)},
                               {new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET), new Token(12), new Token(TokenType.DIVIDE), new Token(4), new Token(TokenType.RIGHTBRACKET), new Token(TokenType.MULTIPLY), new Token(6), new Token(TokenType.RIGHTBRACKET)},
                               {new Token(TokenType.EXCLAMATION), new Token(TokenType.LEFTBRACKET), new Token(6), new Token(TokenType.RIGHTBRACKET)},
                               {new Token(TokenType.EXCLAMATION), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET), new Token(8), new Token(TokenType.DIVIDE), new Token(2), new Token(TokenType.RIGHTBRACKET), new Token(TokenType.RIGHTBRACKET)}
                              };
                              
        for(Token[] equation : equations) {
        	tokeniser = new PseudoTokeniser(equation);
        	parser = new Parser(tokeniser);
            parse(equation);
        }
    }

}
