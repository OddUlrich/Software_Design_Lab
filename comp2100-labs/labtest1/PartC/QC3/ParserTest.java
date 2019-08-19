import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

    private static PseudoTokeniser tokeniser;
    private static Parser parser;
    
    @Test
    public void test1() {
    	Token[] equation = {new Token(TokenType.LEFTBRACKET), new Token(TokenType.TRUE), new Token(TokenType.AND), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET)};
    	tokeniser = new PseudoTokeniser(equation);
    	parser = new Parser(tokeniser);
    	assertEquals(false, parser.parseBool().evaluate());
    }
    
    @Test
    public void test2() {
    	Token[] equation = {new Token(TokenType.NOT), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET), new Token(TokenType.TRUE), new Token(TokenType.OR), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET), new Token(TokenType.RIGHTBRACKET)};
    	tokeniser = new PseudoTokeniser(equation);
    	parser = new Parser(tokeniser);
    	assertEquals(false, parser.parseBool().evaluate());
    }
    
    @Test
    public void test3() {
    	Token[] equation = {new Token(TokenType.NOT), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET), new Token(TokenType.LEFTBRACKET),new Token(TokenType.TRUE), new Token(TokenType.AND), new Token(TokenType.TRUE), new Token(TokenType.RIGHTBRACKET),new Token(TokenType.AND), new Token(TokenType.LEFTBRACKET), new Token(TokenType.FALSE), new Token(TokenType.OR), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET),  new Token(TokenType.RIGHTBRACKET), new Token(TokenType.RIGHTBRACKET)};
    	tokeniser = new PseudoTokeniser(equation);
    	parser = new Parser(tokeniser);
    	assertEquals(true, parser.parseBool().evaluate());
    }    

    @Test
    public void test4() {
    	Token[] equation = {new Token(TokenType.LEFTBRACKET), new Token(TokenType.TRUE), new Token(TokenType.OR), new Token(TokenType.FALSE), new Token(TokenType.RIGHTBRACKET)};
    	tokeniser = new PseudoTokeniser(equation);
    	parser = new Parser(tokeniser);
    	assertEquals(true, parser.parseBool().evaluate());
    }  
}
