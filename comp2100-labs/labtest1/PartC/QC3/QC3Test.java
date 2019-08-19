
public class QC3Test {

    private static PseudoTokeniser tokeniser;
    private static Parser parser;
    
    private static void parse(Token[] equation) {
        System.out.println("Parsing equation:");
        tokeniser.showTokens();
        Bool bool = parser.parseBool();
        if(bool == null) {
            System.out.println("Got an null answer");
        } else {
            System.out.println(" = " + bool.evaluate());
        }
    }
    
    public static void main(String[] args) {

        
        Token[][] equations = {{new Token(TokenType.LEFTBRACKET), new Token(TokenType.TRUE), new Token(TokenType.AND), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET)},
                               {new Token(TokenType.NOT), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET), new Token(TokenType.TRUE), new Token(TokenType.OR), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET), new Token(TokenType.RIGHTBRACKET)},
                               {new Token(TokenType.NOT), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET),new Token(TokenType.TRUE), new Token(TokenType.AND), new Token(TokenType.TRUE), new Token(TokenType.RIGHTBRACKET),new Token(TokenType.AND), new Token(TokenType.LEFTBRACKET), new Token(TokenType.FALSE), new Token(TokenType.OR), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET),  new Token(TokenType.RIGHTBRACKET), new Token(TokenType.RIGHTBRACKET)}
                              };
                              
        for(Token[] equation : equations) {
            tokeniser = new PseudoTokeniser(equation);
            parser = new Parser(tokeniser);        	
            parse(equation);
        }
    }

}
